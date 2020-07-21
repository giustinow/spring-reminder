package it.dstech.model;

import javax.mail.MessagingException;

import it.dstech.service.EmailServiceImpl;

public class MyRunnable implements Runnable {
	
	private EmailServiceImpl emailService;
	
	private Activity activity;

	
	public MyRunnable(EmailServiceImpl emailService, Activity activity) {
		super();
		this.emailService = emailService;
		this.activity = activity;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		User user = activity.getUser();
		try {
			emailService.sendEmail(user.getEmail(), activity.getTitolo(), "Questa attività scade tra 5 minuti!");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public EmailServiceImpl getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailServiceImpl emailService) {
		this.emailService = emailService;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	
}
