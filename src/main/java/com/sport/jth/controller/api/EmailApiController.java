package com.sport.jth.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.jth.model.User;
import com.sport.jth.repository.ResponseDto;
import com.sport.jth.service.EmailService;
import com.sport.jth.service.UserService;

@RestController
public class EmailApiController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	//인증 번호 발송
	@PostMapping("/auth/send/email")
	public ResponseDto<String> sendEmail(@RequestBody String email) {
		List<User> userEntity = userService.findByEmail(email);
		SimpleMailMessage simpleMailMessage = emailService.sendEmail(email);
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
		String tempEmail = email.substring(1, email.length()-1);
		
		String code = simpleMailMessage.getText().substring(8, simpleMailMessage.getText().length());
		
		if(userEntity.size() == 3 || userEntity.size() >= 3) {
			return new ResponseDto<String>(HttpStatus.CONFLICT.value(), "present");
		}
		
		if(tempEmail.matches(regex)) {
			javaMailSender.send(simpleMailMessage);			
			return new ResponseDto<String>(HttpStatus.OK.value(), code);
		} else {
			return new ResponseDto<String>(HttpStatus.BAD_REQUEST.value(), "fail");
		}
		
	}
	
}
