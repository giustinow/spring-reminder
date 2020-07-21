package it.dstech.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import it.dstech.model.Activity;
import it.dstech.model.MyRunnable;
import it.dstech.model.User;
import it.dstech.service.ActivityServiceImpl;
import it.dstech.service.EmailServiceImpl;
import it.dstech.service.UserServiceImpl;

@Controller
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private ActivityServiceImpl activityService;
	
	@Autowired
	private EmailServiceImpl emailService;
	
	@Autowired
	private TaskScheduler taskScheduler;

	@GetMapping(value = "/utente/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		if (user != null) {
			modelAndView.addObject("image", Base64.getEncoder().encodeToString(user.getImage().getData()));
			modelAndView.addObject("activity", new Activity());
			modelAndView.addObject("lista", user.getListaActivity());
			modelAndView.setViewName("utente/home");
			return modelAndView;
		}
		return new WebController().login();
	}

	@PostMapping(value = "/utente/newActivity")
	public ModelAndView newActivity(Activity activity, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		activity.setUser(user);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    	LocalDateTime dateTime = LocalDateTime.parse(activity.getData(), formatter);
    	LOGGER.info(String.format("Il date time è di: %s", dateTime));
    	String format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(dateTime);
    	activity.setScadenza(LocalDateTime.parse(format, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    	LOGGER.info(String.format("La scadenza è: %s", activity.getScadenza()));
    	activity.setData(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(LocalDateTime.now()));
		Activity savedActivity = activityService.save(activity);
		user.getListaActivity().add(savedActivity);
		userService.save(user);
		if(savedActivity != null) {
			LocalDateTime dateTimeActivity = savedActivity.getScadenza();
			int minute = dateTimeActivity.getMinute();
			int hour = dateTimeActivity.getHour();
			int day = dateTimeActivity.getDayOfMonth();
			int month = dateTimeActivity.getMonth().getValue();
			String expression = "";
			if ((minute - 5)<0) {
				expression += "0 " + (minute+55) + " " + (hour-1) + " " + day + " " + month + " " + "?";
				if ((hour-1)<0) {
					expression += "0 " + (minute+55) + " " + (hour+23) + " " + day + " " + month + " " + "?";
				} 
			} else {
					expression += "0 " + (minute-5) + " " + (hour) + " " + day + " " + month + " " + "?";
				}
	    	LOGGER.info(String.format("L'expression è: %s", expression));
			CronTrigger trigger = new CronTrigger(expression, TimeZone.getTimeZone(TimeZone.getDefault().getID()));
			MyRunnable myRunnable = new MyRunnable(emailService, savedActivity);
			taskScheduler.schedule(myRunnable, trigger);
			modelAndView.addObject("messaggio", "Attivita aggiunta con successo");
		}
		modelAndView.addObject("messaggio", "Errore con il salvataggio dell'attivita");
		return home();
	}
}
