<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!-- ---------------------------header---------------------------------------------- -->
<%@ include file="../layout/header.jsp"%>

  <!-- ---------------------------container---------------------------------------------- -->
<div class="container justify-content-center" style="width: 20%; padding: 3% 0 10% 0">
	<div class="d-flex justify-content-center pb-4" style="width: 100%">
		<img alt="user" src="/image/user.png" width="18%">
	</div>
	<input type="hidden" id="userId" value="${principal.user.id}" />
	<form>
	  <div class="form-group">
	    <label for="pwd">Password</label>
	    <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
	  </div>
	</form>
	  <button id="btn-enter2" class="btn btn-primary">Enter</button>
</div>

  <!-- ---------------------------footer---------------------------------------------- -->
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>