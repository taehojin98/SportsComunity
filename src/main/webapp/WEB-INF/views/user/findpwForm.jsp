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
	    <label for="pwd">아이디를 입력하세요.</label>
	    <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
	  </div>
	</form>
	<button id="btn-username-enter" class="btn btn-primary">Enter</button>
</div>

  <!-- ---------------------------footer---------------------------------------------- -->
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>