package com.google.dearapp.util;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.dearapp.entity.User;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendFirstEmail(User u) {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setTo(u.getEmail());
			helper.setSubject("Account Created Successfully for DearApp");
			helper.setText("Dear" +u.getName()+" , Your Account Created Successfully for DearApp, Start Your Journey with Your Perfect Matches here, "+u.getOtp()+"All The Best");

		
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		
		javaMailSender.send(mimeMessage);
	}
	
	public int getOTP() {
		int otp = 0;
		while(otp <= 999) {
			otp = (int)(Math.random()*10000);
		}
		return otp;
	}
	
	
}
