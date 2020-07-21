package it.dstech.service;

import javax.mail.MessagingException;

public interface EmailService {

	public void sendEmail(String destinatario, String oggetto, String messaggio) throws MessagingException;
}
