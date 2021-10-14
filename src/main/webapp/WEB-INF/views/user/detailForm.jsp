<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container justify-content-center" style="width: 20%; padding: 3% 0 10% 0">
	<div class="d-flex justify-content-center pb-4" style="width: 100%">
		<img alt="user" src="/image/user.png" width="18%">
	</div>
	<form>
		<input type="hidden" id="userId" value="${principal.user.id}" />
	  <div class="form-group">
	    <label for="username">Username</label>
	    <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter Username"  id="username" readonly>
	  </div>
	  <c:if test="${empty principal.user.oauth}">
		  <div class="form-group">
		    <label for="pwd" class="pr-2">Password</label>
		    <a href="/user/checkForm"><button type="button" id="btn-change-password" class="btn btn-outline-primary btn-sm mb-1" >비밀번호 변경</button></a>
		    <input type="password" name="password" class="form-control" id="password" disabled>
		  </div>
	  </c:if>
	  <div class="form-group form-check">
	  </div>
	</form>
	<div  class="d-flex  justify-content-between">
		<a href="/user/mylistForm/${principal.user.id}"><button id="btn-mylists" class="btn btn-primary"">내 게시물</button></a>
		<div>
			<button id="btn-back" class="btn btn-primary" onclick="history.back()">돌아가기</button>
			<a href="/user/checkForm2"><button id="btn-predelete" class="btn btn-danger">회원탈퇴</button></a>
		</div>
	</div>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>




