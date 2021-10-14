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
	    <label for="pwd">이메일을 입력해주세요.</label>
	    <button type="button" id="btn-email-enter2" class="btn btn-outline-primary btn-sm mb-1 ml-2">인증번호발송</button>
	    <input type="email" name="email" class="form-control" placeholder="Enter email" id="email">
	  </div>
	</form>
	<div class="d-flex">
	  <input type="text" id="code" style="width: 30%; height: 38px;">
	  <button id="btn-code-enter2" class="btn btn-primary ml-1">Enter</button>
	</div>
</div>

  <!-- ---------------------------footer---------------------------------------------- -->
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>