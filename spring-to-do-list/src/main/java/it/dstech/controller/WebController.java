package it.dstech.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import it.dstech.model.Image;
import it.dstech.model.User;
import it.dstech.service.ImageServiceImpl;
import it.dstech.service.UserServiceImpl;

@Controller
public class WebController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private ImageServiceImpl imageService;
	
	
	@GetMapping(value = { "/", "/login" })
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homepage");
		return modelAndView;
	}

	@GetMapping(value = "/registrazione")
	public ModelAndView registrazione() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registrazione");
		return modelAndView;
	}
	
	@PostMapping(value= "/registrazione")
	public ModelAndView registrazione(@Valid User utente ,@RequestParam("file") MultipartFile file, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(utente.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("username", "error.user", "Utente gia registrato");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registrazione");
		} else {
			Image saveFile = imageService.saveFile(file);
			utente.setImage(saveFile);
			userService.save(utente);
			modelAndView.addObject("successMessage", "Utente registrato con successo!");
		}
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registrazione");
		return modelAndView;
	}
}