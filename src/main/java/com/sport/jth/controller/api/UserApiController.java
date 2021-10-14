package com.sport.jth.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.jth.model.User;
import com.sport.jth.repository.JoinReqDto;
import com.sport.jth.repository.ResponseDto;
import com.sport.jth.service.EmailService;
import com.sport.jth.service.UserService;

@RestController
public class UserApiController {
	
	boolean isChecked = false;
	
	String tempUsername = "";
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	//회원가입
	@PostMapping("/auth/joinProc")
	public ResponseDto<String> joinProc(@RequestBody JoinReqDto dto) {
		User userEntity = userService.joinCheck(dto.getUsername());
		System.out.println(userEntity);
		System.out.println(isChecked);
		if(dto.getPassword().equals(dto.getRepassword()) && dto.getUsername() != "" && dto.getPassword() != "" && dto.getRepassword() != "" && isChecked && userEntity == null) {
			User user = new User();
			user.setEmail(dto.getEmail());
			user.setUsername(dto.getUsername());
			user.setPassword(dto.getPassword());
			userService.join(user);
			isChecked = false;
			return new ResponseDto<>(HttpStatus.OK.value(), "true");
		} else if((!dto.getPassword().equals(dto.getRepassword()) || dto.getPassword() == "" || dto.getRepassword() == "") && userEntity != null) {
			return new ResponseDto<String>(HttpStatus.NO_CONTENT.value(), "false");
		} else if((!dto.getPassword().equals(dto.getRepassword()) || dto.getPassword() == "" || dto.getRepassword() == "") && userEntity == null) {
			return new ResponseDto<String>(HttpStatus.NO_CONTENT.value(), "true");
		} else if((dto.getPassword().equals(dto.getRepassword()) && dto.getPassword() != "" && dto.getRepassword() != "") && userEntity != null) {
			return new ResponseDto<String>(HttpStatus.CONFLICT.value(), "false");
		}
		isChecked = false;
		return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "false");
		
	}
	
	
	//유저네임 중복확인
	@GetMapping("/auth/joinCheck/{username}")
	public ResponseDto<String> joinCheck(@PathVariable String username) {
		tempUsername = username;
		User userEntity = userService.joinCheck(username);
		if(userEntity == null) {
			isChecked = true;
			return new ResponseDto<>(HttpStatus.OK.value(), "check");
		} else if(username == "") {
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "fail");
		} else {
			isChecked = false;
			return new ResponseDto<>(HttpStatus.CONFLICT.value(), "fail");
		}
	}
	
	//비밀번호 확인
	@PostMapping("/enter")
	public ResponseDto<String> enter(@RequestBody User data) {
		System.out.println("enter : " + data);
		User userEntity = userService.findUser(data.getId());
		System.out.println(userEntity);
		if(encoder.matches(data.getPassword(), userEntity.getPassword())) {
			return new ResponseDto<>(HttpStatus.OK.value(), "enter");
		} else {
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "wrong");
		}		
	}
	
	//비밀번호 확인2
	@PostMapping("/enter2")
	public ResponseDto<String> enter2(@RequestBody User data) {
		System.out.println("enter2 :" + data);
		User userEntity = userService.findUser(data.getId());
		if(encoder.matches(data.getPassword(), userEntity.getPassword())) {
			return new ResponseDto<>(HttpStatus.OK.value(), "enter");
		} else {
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "wrong");
		}		
	}

	//비밀번호 변경
	@PutMapping("/user/update")
	public ResponseDto<String> update(@RequestBody JoinReqDto dto) {
		if(dto.getPassword().equals(dto.getRepassword())) {
			userService.update(dto.getUsername(), dto.getPassword());
			return new ResponseDto<>(HttpStatus.OK.value(), "update");			
		} else {
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "wrong");
		}
	}
	
	//회원탈퇴
	@DeleteMapping("/user/delete/{id}")
	public ResponseDto<String> delete(@PathVariable int id) {
		userService.delete(id);
		return new ResponseDto<>(HttpStatus.OK.value(), "delete");	
	}
	
	//아이디와 이메일을 모두 충족하는 유저찾기
	@PostMapping("/auth/findpw/mail")
	public ResponseDto<String> findUser(@RequestBody String email) {
		
		String tempEmail = email.substring(1, email.length()-1);
		User userEntity = userService.findByUsernameAndEmail(tempUsername, tempEmail);
		
		System.out.println(userEntity);
		
		if(userEntity == null) {
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "fail");
		}
		
		SimpleMailMessage simpleMailMessage = emailService.sendEmail(email);		
		String code = simpleMailMessage.getText().substring(8, simpleMailMessage.getText().length());
		javaMailSender.send(simpleMailMessage);
		
		return new ResponseDto<>(HttpStatus.OK.value(), code);
	}
	
	//임시 비밀번호 전송&변경
	@PutMapping("/auth/findpw/temppw")
	public ResponseDto<String> sendTempPw(@RequestBody String email) {
		String tempEmail = email.substring(1, email.length()-1);
		
		User userEntity = userService.findByUsernameAndEmail(tempUsername, tempEmail);
		String tempPassword = emailService.createTempPassword();
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("tempjth@gmail.com");
		simpleMailMessage.setTo(userEntity.getEmail());
		simpleMailMessage.setSubject("3355 임시 비밀번호");
		simpleMailMessage.setText("임시 비밀번호 : " + tempPassword);
		javaMailSender.send(simpleMailMessage);
				
		userService.update(userEntity.getUsername(), tempPassword);
		
		return new ResponseDto<>(HttpStatus.OK.value(), "success");
	}
}
