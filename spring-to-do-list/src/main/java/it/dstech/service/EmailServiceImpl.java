package it.dstech.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String destinatario, String oggetto, String messaggio) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setSubject(oggetto);
		mimeMessageHelper.setTo(destinatario);
		mimeMessageHelper.setText(messaggio, true);
		javaMailSender.send(mimeMessage);
	}

}
