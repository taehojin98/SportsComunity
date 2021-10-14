<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!-- ---------------------------header---------------------------------------------- -->
<%@ include file="layout/header.jsp"%>
<%@ include file="layout/topad.jsp"%>
<!-- ---------------------------top_container---------------------------------------------- -->
<div class="container-fluid d-flex pt-4" style="justify-content: space-around;">

  <div class="card" style="width: 18%;">
	  <div class="card-header d-flex justify-content-between">
	  	<div>Football</div>
	  	<div style="font-size: 13px"><a class="text-secondary" href="/auth/list/Football">전체보기</a></div>
	  </div>
	  <div class="card-body">
	  	<ul class="list-group">
	  		  <c:forEach var="i" begin="0" end="4">
	  		  	<c:if test="${not empty footBoards[i]}">
		  		<c:set var="rNum" value="${fn:length(footBoards[i].replys)}" />
			  	<li class="list-group-item d-flex justify-content-between">
			  		<div>
				  		<a href="/auth/list/detail/${footBoards[i].category}/${footBoards[i].id}" style="color: #212529;">${footBoards[i].title}</a>
				  		<c:if test="${rNum ne 0}">
			        		<span style="color: #ec4908; font-weight: bold;">[${rNum}]</span>
			        	</c:if>
		        	</div>
			  		<span>
			  			<img alt="count" src="/image/eye.png"  width="13px" height="13px" />
			  			<span class="ml-1" style="font-weight: bold; font-size: 13px; color: #0a4fda;">  ${footBoards[i].count}</span>
			  		</span>
			  	</li>
			  	</c:if>
			  </c:forEach>
		</ul>
	  </div>
  </div>
  
  <div class="card" style="width: 18%;">
	  <div class="card-header d-flex justify-content-between">
	  	<div>Baseball</div>
	  	<div style="font-size: 13px"><a class="text-secondary" href="/auth/list/Baseball">전체보기</a></div>
	  </div>
	  <div class="card-body">
	  	<ul class="list-group">
	  		  <c:forEach var="i" begin="0" end="4">
	  		  	<c:if test="${not empty baseBoards[i]}">
		  		<c:set var="rNum" value="${fn:length(baseBoards[i].replys)}" />
			  	<li class="list-group-item d-flex justify-content-between">
			  		<div>
				  		<a href="/auth/list/detail/${baseBoards[i].category}/${baseBoards[i].id}" style="color: #212529;">${baseBoards[i].title}</a>
				  		<c:if test="${rNum ne 0}">
			        		<span style="color: #ec4908; font-weight: bold;">[${rNum}]</span>
			        	</c:if>
		        	</div>
			  		<span>
			  			<img alt="count" src="/image/eye.png"  width="13px" height="13px" />
			  			<span class="ml-1" style="font-weight: bold; font-size: 13px; color: #0a4fda;">  ${baseBoards[i].count}</span>
			  		</span>
			  	</li>
			  	</c:if>
			  </c:forEach>
		</ul>
	  </div>
  </div>
  
<div class="card" style="width: 18%;">
	  <div class="card-header d-flex justify-content-between">
	  	<div>Soccer</div>
	  	<div style="font-size: 13px"><a class="text-secondary" href="/auth/list/Soccer">전체보기</a></div>
	  </div>
	  <div class="card-body">
	  	<ul class="list-group">
	  		  <c:forEach var="i" begin="0" end="4">
	  		  	<c:if test="${not empty socBoards[i]}">
		  		<c:set var="rNum" value="${fn:length(socBoards[i].replys)}" />
			  	<li class="list-group-item d-flex justify-content-between">
			  		<div>
				  		<a href="/auth/list/detail/${socBoards[i].category}/${socBoards[i].id}" style="color: #212529;">${socBoards[i].title}</a>
				  		<c:if test="${rNum ne 0}">
			        		<span style="color: #ec4908; font-weight: bold;">[${rNum}]</span>
			        	</c:if>
		        	</div>
			  		<span>
			  			<img alt="count" src="/image/eye.png"  width="13px" height="13px" />
			  			<span class="ml-1" style="font-weight: bold; font-size: 13px; color: #0a4fda;">  ${socBoards[i].count}</span>
			  		</span>
			  	</li>
			  	</c:if>
			  </c:forEach>
		</ul>
	  </div>
  </div>
  
 <div class="card" style="width: 18%;">
	  <div class="card-header d-flex justify-content-between">
	  	<div>Basketball</div>
	  	<div style="font-size: 13px"><a class="text-secondary" href="/auth/list/Basketball">전체보기</a></div>
	  </div>
	  <div class="card-body">
	  	<ul class="list-group">
	  		  <c:forEach var="i" begin="0" end="4">
	  		  	<c:if test="${not empty basBoards[i]}">
		  		<c:set var="rNum" value="${fn:length(basBoards[i].replys)}" />
			  	<li class="list-group-item d-flex justify-content-between">
			  		<div>
				  		<a href="/auth/list/detail/${basBoards[i].category}/${basBoards[i].id}" style="color: #212529;">${basBoards[i].title}</a>
				  		<c:if test="${rNum ne 0}">
			        		<span style="color: #ec4908; font-weight: bold;">[${rNum}]</span>
			        	</c:if>
		        	</div>
			  		<span>
			  			<img alt="count" src="/image/eye.png"  width="13px" height="13px" />
			  			<span class="ml-1" style="font-weight: bold; font-size: 13px; color: #0a4fda;">  ${basBoards[i].count}</span>
			  		</span>
			  	</li>
			  	</c:if>
			  </c:forEach>
		</ul>
	  </div>
  </div>
  
<div class="card" style="width: 18%;">
	  <div class="card-header d-flex justify-content-between">
	  	<div>Common</div>
	  	<div style="font-size: 13px"><a class="text-secondary" href="/auth/list/Common">전체보기</a></div>
	  </div>
	  <div class="card-body">
	  	<ul class="list-group">
	  		  <c:forEach var="i" begin="0" end="4">
	  		  	<c:if test="${not empty basBoards[i]}">
		  		<c:set var="rNum" value="${fn:length(comBoards[i].replys)}" />
			  	<li class="list-group-item d-flex justify-content-between">
			  		<div>
				  		<a href="/auth/list/detail/${comBoards[i].category}/${comBoards[i].id}" style="color: #212529;">${comBoards[i].title}</a>
				  		<c:if test="${rNum ne 0}">
			        		<span style="color: #ec4908; font-weight: bold;">[${rNum}]</span>
			        	</c:if>
		        	</div>
			  		<span>
			  			<img alt="count" src="/image/eye.png"  width="13px" height="13px" />
			  			<span class="ml-1" style="font-weight: bold; font-size: 13px; color: #0a4fda;">  ${comBoards[i].count}</span>
			  		</span>
			  	</li>
			  	</c:if>
			  </c:forEach>
		</ul>
	  </div>
  </div>
  
</div>
<!-- ---------------------------bottom_container---------------------------------------------- -->
<div class="d-flex pt-5" style="justify-content: space-around; padding-left: 15px; padding-right: 15px;">
	
	
	<!-- Football -->
	<div style="width: 17.91%">
		<div class="owl-carousel owl-theme">
			<c:forEach var="i" begin="0" end="4">
				<div class="item">
		    		<a href="${footballHref[i]}">
						<img class="news-img" src="${footballImg[i]}" width="100%" />
						<span class="news-title">${footballTitle[i]}</span>
					</a>
				</div>
			</c:forEach>    
		</div>
	</div>
	
	
	<!-- Baseball -->
	<div style="width: 17.91%">
		<div class="owl-carousel owl-theme">
			<c:forEach var="i" begin="0" end="4">
				<div class="item">
		    		<a href="${baseballHref[i]}">
						<img class="news-img" src="${baseballImg[i]}" width="100%" />
						<span class="news-title">${baseballTitle[i]}</span>
					</a>
				</div>
			</c:forEach>    
		</div>
	</div>
	
	
	<!-- Soccer -->
	<div style="width: 17.91%">
		<div class="owl-carousel owl-theme">
			<c:forEach var="i" begin="0" end="4">
				<div class="item">
		    		<a href="${soccerHref[i]}">
						<img class="news-img" src="${soccerImg[i]}" width="100%" />
						<span class="news-title">${soccerTitle[i]}</span>
					</a>
				</div>
			</c:forEach>    
		</div>
	</div>
	
	<!-- Basketball -->
	<div style="width: 17.91%">
		<div class="owl-carousel owl-theme">
			<c:forEach var="i" begin="0" end="4">
				<div class="item">
		    		<a href="${basketballHref[i]}">
						<img class="news-img" src="${basketballImg[i]}" width="100%" />
						<span class="news-title">${basketballTitle[i]}</span>
					</a>
				</div>
			</c:forEach>    
		</div>
	</div>
	
	<!-- ad -->
	<div style="width: 17.91%">
		<a href="#"><img alt="ad" src="/image/empty.png" width="100%" height="100%"></a>
	</div>

</div>



<!-- ---------------------------footer---------------------------------------------- -->
<script type="text/javascript">

$('.owl-carousel').owlCarousel({
	autoplay: true,
	autoplayTimeout: 4000,
	items: 1,
    loop:true,
    margin:10,
    nav:true
})
</script>
<%@ include file="layout/footer.jsp"%>