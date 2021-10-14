<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!-- ---------------------------header---------------------------------------------- -->
<%@ include file="../layout/header.jsp"%>

  <!-- ---------------------------container---------------------------------------------- -->
<div class="container justify-content-center" style="width: 20%; padding: 3% 0 10% 0">
	<div class="d-flex justify-content-center pb-4" style="width: 100%">
		<img alt="user" src="/image/user.png" width="18%">
	</div>
	<input type="hidden" id="id" value="${principal.user.id}" />
	<form>
	  <div class="form-group mt-4">
	    <span style="font-size: x-large;large; font-weight: bold;">정말로 탈퇴하시겠습니까?</span>
		  <button type="button" id="btn-delete" class="btn btn-danger ml-1 mb-1">탈퇴하기</button>
	  </div>
	</form>
</div>

  <!-- ---------------------------footer---------------------------------------------- -->
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>