<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!-- ---------------------------header---------------------------------------------- -->
<%@ include file="../layout/header.jsp"%>

  <!-- ---------------------------container---------------------------------------------- -->
<div class="container justify-content-center" style="width: 20%; padding: 3% 0 10% 0">
	<div class="d-flex justify-content-center pb-4" style="width: 100%">
		<img alt="user" src="/image/user.png" width="18%">
	</div>
	<div class="container justify-content-center">
	  <c:choose>
	  	<c:when test="${!empty userList}">
	  		<table class="table table-bordered table-sm" style="text-align: center;">
			    <thead>
			      <tr>
			        <th>Username</th>
			        <th>dateCreated</th>
			      </tr>
			    </thead>
			    <tbody>
			      <c:forEach items="${userList}" var="user">
				      <tr>
				        <td>${user.username}</td>
				        <td><fmt:formatDate value="${user.dateCreated}" pattern="yyyy-MM-dd"/></td>
				      </tr>
				   </c:forEach>
			    </tbody>
			  </table>
			  <div class="d-flex justify-content-center mt-2">
				 <a href="/auth/loginForm"><span class="pr-2" style="font-size: 13px; border-right: 1px solid #dee2e6; color: #8e8e8e;">로그인</span></a>
				 <a href="#"><span class="pl-2" style="font-size: 13px; color: #8e8e8e;">비밀번호 찾기</span></a>
			  </div>
	  	</c:when>
	  	
	  	<c:otherwise>
	  		<div>
		  		<div style="text-align: center; font-size: 15px;">해당 이메일을 이용하는 회원이 존재하지 않습니다.</div><br/>
		  		<div style="text-align: center;"><a href="/auth/joinForm" style="font-size: 13px; color: #8e8e8e;">회원가입</a></div>
	  		</div>
	  	</c:otherwise>
	  </c:choose>
	  
	</div>
</div>

  <!-- ---------------------------footer---------------------------------------------- -->
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>