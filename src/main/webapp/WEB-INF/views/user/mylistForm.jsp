<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!-- ---------------------------header---------------------------------------------- -->
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/topad.jsp"%>

<!-- ---------------------------content---------------------------------------------- -->
<div class="container d-flex">

<div class="container pt-4" style="width: 73%">

  <div class="d-flex justify-content-between">
	  <h2 class="pb-3">내 게시물</h2>
	  <span class="mt-4" style="font-weight: bold; font-size: 15px;">총 게시글 : ${totalNum}</span>
  </div>
  
  <table class="table table-hover" style="width: 100%; font-size: 12px;">
    <thead>
      <tr style="text-align: center">
        <th style="width: 5%">Number</th>
        <th style="width: 40%">Title</th>
        <th style="width: 25%">Writer</th>
        <th style="width: 25%">Date</th>
        <th style="width: 5%">Hit</th>
      </tr>
    </thead>
    
    <tbody>
     	<c:set var="size" value="${fn:length(boards.content)}" />
	    <c:forEach var="i" begin="1" end="${size}">
	    	<c:set var="rNum" value="${fn:length(boards.content[i-1].replys)}" />
	      <tr>
	        <c:if test="${boards.number eq 0}"><td style="text-align: center">${boards.totalElements+1-i}</td></c:if>
	        <c:if test="${boards.number ne 0}"><td style="text-align: center">${boards.totalElements-(boards.number*10)+1-i}</td></c:if>
	        <td>
	        	<a id="detailPage" href="/auth/list/detail/${boards.content[i-1].category}/${boards.content[i-1].id}" style="color: rgb(33, 37, 41);">${boards.content[i-1].title} 
		        	<c:if test="${rNum ne 0}">
		        		<span class="ml-1" style="color: #ec4908; font-weight: bold;">[${rNum}]</span>
		        	</c:if>
	        	</a>
	        </td>
	        <td style="text-align: center">${boards.content[i-1].user.username}</td>
	        <td style="text-align: center"><fmt:formatDate value="${boards.content[i-1].dateCreated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	        <td style="text-align: right">${boards.content[i-1].count}</td>
	      </tr>
		 </c:forEach>
     </tbody>
  </table>
  
  <div class="d-flex" style="justify-content: space-between;">
  		<div>
  		</div>
	  <ul class="pagination pagination-sm">
	  	  	<c:choose>
		  	  	<c:when test="${boards.first}">
		  	  		<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Prev</a></li>
		  	  	</c:when>
		  	  	<c:otherwise>
		  	  		<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Prev</a></li>
		  	  	</c:otherwise>
		  	  </c:choose>
		  	  	  	  
		  	  <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
		  	  	<c:choose>
		  	  		<c:when test="${boards.number+1 == i}">
		  	  			<li class="page-item disabled"><a class="page-link" href="?page=${i-1}" style="background-color: #007bff; color: #fff">${i}</a></li>
		  	  		</c:when>
		  	  		<c:otherwise>
		  	  			<li class="page-item"><a class="page-link" href="?page=${i-1}">${i}</a></li>
		  	  		</c:otherwise>
		  	  	</c:choose>
		  	  </c:forEach>
		  	  
		  	  <c:choose>
		  	  	<c:when test="${boards.last}">
		  	  		<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
		  	  	</c:when>
		  	  	<c:otherwise>
		  	  		<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
		  	  	</c:otherwise>
		  	  </c:choose>
		</ul>
		<div style="font-weight: bold; font-size: 12px;">${boards.number+1}<span>/</span>${boards.totalPages}</div>
	</div>
</div>

<!-- ---------------------------right content---------------------------------------------- -->
<%@ include file="../layout/rightcontent.jsp"%>

</div>

<!-- ---------------------------footer---------------------------------------------- -->
<%@ include file="../layout/footer.jsp"%>