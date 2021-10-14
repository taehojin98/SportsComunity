package com.sport.jth.service;

import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public SimpleMailMessage sendEmail(String emailAddr) {
		Random random = new Random(); //난수 생성
		String key = ""; //인증번호
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		for(int i=0; i<3; i++) {
			int index = random.nextInt(25)+65; //A~Z까지 랜덤 알파벳 생성
			key += (char)index; //key에 문자로 변환 후 append
		}
		int numIndex = random.nextInt(9999)+1000; //4자리 랜덤 정수 생성
		key += numIndex; //key에 append
		
		simpleMailMessage.setFrom("tempjth@gmail.com");
		simpleMailMessage.setTo(emailAddr);
		simpleMailMessage.setSubject("3355 이메일 인증");
		simpleMailMessage.setText("인증 번호 : " + key);
		
		return simpleMailMessage;
	}
	
	public String createTempPassword() {
		Random random = new Random(); //난수 생성
		String key = ""; //인증번호
		
		for(int i=0; i<3; i++) {
			int index = random.nextInt(25)+65; //A~Z까지 랜덤 알파벳 생성
			key += (char)index; //key에 문자로 변환 후 append
		}
		int numIndex = random.nextInt(9999)+1000; //4자리 랜덤 정수 생성
		key += numIndex; //key에 append
		
		return key;
	}
}
