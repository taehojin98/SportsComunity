<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!-- ---------------------------header---------------------------------------------- -->
<%@ include file="../layout/header.jsp"%>

  <!-- ---------------------------container---------------------------------------------- -->
<div class="container justify-content-center" style="width: 20%; padding: 3% 0 10% 0">
	<div class="d-flex justify-content-center pb-4" style="width: 100%">
		<img alt="user" src="/image/user.png" width="18%">
	</div>
	<form action="/auth/loginProc" method="POST">
	  <div class="form-group">
	    <label for="username">Username</label>
	    <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
	  </div>
	  <div class="form-group">
	    <label for="pwd">Password</label>
	    <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
	  </div>
	  <div class="form-group form-check d-flex justify-content-between">
	    <label class="form-check-label" for="remember-me">
	      <input class="form-check-input" type="checkbox" id="remember-me" name="remember-me"> Remember me
	    </label>
		  <button id="btn-login" class="btn btn-primary">로그인</button>
	  </div>
	</form>
	<div class="d-flex mt-5" style="flex-direction: column;">
		<div class="d-flex">
			<a href="https://kauth.kakao.com/oauth/authorize?client_id=3c8d21421486d9550227a2fa2e1fe0d6&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img alt="kakao" src="/image/kakao.png" height="35px;"></a>
			<a href="https://kauth.kakao.com/oauth/authorize?client_id=3c8d21421486d9550227a2fa2e1fe0d6&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code" class="pt-1" style="background: #FFEB00; margin-left: -2px; font-size: 15px; font-weight: bold; width: 100%; text-align: center; color: black;">카카오로 로그인</a>
		</div>
		<div class="d-flex my-2">
			<a href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=XrarIgbgdXwUBqddSK50&state=STATE_STRING&redirect_uri=http://localhost:8000/auth/naver/callback"><img alt="naver" src="/image/naver.png" height="35px;"></a>
			<a href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=XrarIgbgdXwUBqddSK50&state=STATE_STRING&redirect_uri=http://localhost:8000/auth/naver/callback" class="pt-1" style="background: #03C75A; margin-left: -2px; font-size: 15px; font-weight: bold; width: 100%; text-align: center; color: black;">네이버로 로그인</a>
		</div>
		<div class="d-flex mb-2">
			<a href="https://www.facebook.com/v11.0/dialog/oauth?client_id=378172483931199&redirect_uri=http://localhost:8000/auth/facebook/callback&state=email"><img alt="facebook" src="/image/facebook.png" height="35px;"></a>
			<a href="https://www.facebook.com/v11.0/dialog/oauth?client_id=378172483931199&redirect_uri=http://localhost:8000/auth/facebook/callback&state=email" class="pt-1" style="background: #3B579D; margin-left: -2px; font-size: 15px; font-weight: bold; width: 100%; text-align: center; color: black;">페이스북으로 로그인</a>
		</div>
		<div class="d-flex">
			<a href="https://accounts.google.com/o/oauth2/v2/auth?scope=email&response_type=code&client_id=287987745054-hh2ivi0n0so3h1bh3ir56k91tib0ga3d.apps.googleusercontent.com&redirect_uri=http://localhost:8000/auth/google/callback"><img alt="google" src="/image/google.png" height="35px;"></a>
			<a href="https://accounts.google.com/o/oauth2/v2/auth?scope=email&response_type=code&client_id=287987745054-hh2ivi0n0so3h1bh3ir56k91tib0ga3d.apps.googleusercontent.com&redirect_uri=http://localhost:8000/auth/google/callback" class="pt-1" style="background: #FFFFFF; margin-left: -2px; font-size: 15px; font-weight: bold; width: 100%; text-align: center; color: black;">구글로 로그인</a>
		</div>
	</div>
	
	<div class="d-flex justify-content-center mt-2">
		<input type="hidden" id="email" value="${prnicipal.user.email}" />
		<a href="/auth/findidForm"><span class="pr-2" style="font-size: 13px; border-right: 1px solid #dee2e6; color: #8e8e8e;">아이디 찾기</span></a>
		<a href="/auth/findpwForm"><span class="pl-2" style="font-size: 13px; color: #8e8e8e;">비밀번호 찾기</span></a>
	</div>
	
</div>

  <!-- ---------------------------footer---------------------------------------------- -->
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>