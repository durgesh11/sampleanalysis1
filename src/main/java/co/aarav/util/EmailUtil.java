/**
*
* @author  : Durgesh Mudras
* @Date    : 16-10-2019
* @version : 1.0.0
* 
*/
package co.aarav.util;

import java.io.File;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailUtil {

	@Autowired
	private MailSender mailService;

	public void readyToSendEmail(String toAddress, String fromAddress, String subject, String msgBody) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		// Using gmail
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("");
		mailSender.setPassword("MyPassword");

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(fromAddress);
		msg.setTo(toAddress);
		msg.setSubject(subject);
		msg.setText(msgBody);
		mailService.send(msg);
	}

	public String sendEmail(String email_host, String emailFrom, String[] emailTo, String emailCC, String emailSubject,
			String emailBody, File f, String fileName) {
		String errorMsg = "Success";
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		Properties properties = new Properties();
		MimeMessageHelper helper = null;
		MimeMessage message=null;
		try {
			
			if (StringUtils.testEmpty(email_host)) {
				errorMsg = "email_host";
			}
			if (StringUtils.testEmpty(emailFrom)) {
				errorMsg = "emailFrom";
			}
			if (emailTo.length < 1) {
				errorMsg = "emailTo";
			}
			if (StringUtils.testEmpty(emailSubject)) {
				errorMsg = "emailSubject";
			}
			if (StringUtils.testEmpty(emailBody)) {
				errorMsg = "emailBody";
			}
			if (!errorMsg.equals("Success")) {
				return errorMsg;
			}

			
			properties.setProperty("mail.smtp.connectiontimeout", "10000");
			properties.setProperty("mail.smtp.timeout", "10000");
			properties.setProperty("mail.smtp.allow8bitmime", "true");
			sender.setJavaMailProperties(properties);
			sender.setHost(email_host);

			message = sender.createMimeMessage();

			helper = new MimeMessageHelper(message, true);
			helper.setTo(emailTo);
			if (!StringUtils.testEmpty(emailCC)) {
				helper.setCc(emailCC);
			}
			helper.setFrom(emailFrom);
			helper.setSubject(emailSubject);
			helper.setText(emailBody, true);
			if (f != null) {
				helper.addAttachment(fileName, f);

			}
			
			if (message!=null) {
				sender.send(message);
			}
			
		} catch (Exception e) {
			//System.out.println("Exception in Email Util in sendEmail() : " + e.getMessage());
			errorMsg=e.getMessage();
			//e.printStackTrace();
		}  
		return errorMsg;
	}
}
