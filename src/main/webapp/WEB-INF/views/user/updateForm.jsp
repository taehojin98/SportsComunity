<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!-- ---------------------------header---------------------------------------------- -->
<%@ include file="../layout/header.jsp"%>

  <!-- ---------------------------container---------------------------------------------- -->
<div class="container justify-content-center" style="width: 20%; padding: 3% 0 10% 0">
	<div class="d-flex justify-content-center pb-4" style="width: 100%">
		<img alt="user" src="/image/user.png" width="18%">
	</div>
	<form>
	  <div class="form-group">
	    <label for="username">Username</label>
	    <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter Username"  id="username" readonly>
	  </div>
	  <div class="form-group">
	    <label for="pwd">Password</label>
	    <input type="password" class="password form-control" placeholder="Enter password" id="password" maxlength="20">
		<div id="validation" style="font-size: 13px;">영문, 숫자, 특수문자를 조합하여 8~16자로 구성하세요.</div>
	  </div>
	  <div class="form-group">
	    <label for="re-password">Confirm Password</label>
	    <input type="password" class="re-password form-control" placeholder="Enter re-password" id="re-password" maxlength="20">
	  </div>
	</form>
	  <button id="btn-update" class="btn btn-primary">변경하기</button>
	  <span id="check-password" style="color: red; font-size: 13px;"></span>
</div>

  <!-- ---------------------------footer---------------------------------------------- -->
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>