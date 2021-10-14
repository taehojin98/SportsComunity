<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!-- ---------------------------header---------------------------------------------- -->
<%@ include file="../layout/header.jsp"%>

  <!-- ---------------------------container---------------------------------------------- -->
<div class="container justify-content-center" style="width: 20%; padding: 3% 0 10% 0">
	<div class="d-flex justify-content-center pb-4" style="width: 100%">
		<img alt="user" src="/image/user.png" width="18%">
	</div>
	
	  <!-- Username -->
	  <div class="form-group">
	    <label for="username">Username</label><span style="font-size: 12px;" class="ml-2">소문자와 숫자로 구성된 5~15자리 아이디를 입력하세요.</span>
	    <div class="input-username">
	    <input type="text" class="form-control" placeholder="Enter username" id="username"  maxlength="20">
	    </div>
	  </div>
	  
	  <!-- Username Check -->
	  <div class="check-username" style="margin-top: -10px; margin-bottom: 10px;">
		  <button id="btn-check" class="btn btn-secondary mr-3">중복체크</button>
		  <span id="msg1" style="color: blue; font-size: 13px; display: none;">사용 가능한 아이디입니다.</span>
		  <span id="msg2" style="color: red; font-size: 13px; display: none;">사용 불가능한 아이디입니다.</span>
	  </div>
	  
	  <!-- Email -->
	  <div class="form-group">
	  	<span>
	  		<label for="email">Email</label>
	  		<button class="btn btn-outline-primary btn-sm mb-1"" id="btn-send-mail">인증번호발송</button>
	  		<input type="text" id="email-code" style="width: 30%">	  		
	  	</span>
	    <input type="email" class="form-control" placeholder="Enter email" id="email"  maxlength="30">
	    <div class="d-flex" style="align-items: flex-end;">
		    <button class="btn btn-outline-success btn-sm mt-1"" id="btn-check-mail" style="margin-bottom: -10px;">확인</button>
		    <span class="ml-2" id="emailmsg1" style="color: blue; font-size: 13px; display: none;">인증이 완료되었습니다.</span>
			<span class="ml-2" id="emailmsg2" style="color: red; font-size: 13px; display: none;">인증 코드를 확인해주세요.</span>
		</div>
	  </div>
	  
	  <!-- Password -->
	  <div class="form-group">
	    <label for="password">Password</label>
	    <input type="password" class="password form-control" placeholder="Enter password" id="password" maxlength="20">
	    <div id="validation" style="font-size: 13px;">영문, 숫자, 특수문자를 조합하여 8~16자로 구성하세요.</div>
	  </div>
	  
	  <!-- Confirm Password -->
	  <div class="form-group">
	    <label for="re-password">Confirm Password</label>
	    <input type="password" class="re-password form-control" placeholder="Enter re-password" id="re-password" maxlength="20">
	  </div>
	  
	  <!-- Join btn -->
	  <div>
		<button id="btn-join" class="btn btn-primary mr-3">회원가입</button>
		<span id="check-password" style="color: red; font-size: 13px;"></span>
	  </div>
	  
</div>

  <!-- ---------------------------footer---------------------------------------------- -->
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>