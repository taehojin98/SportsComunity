<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container pt-4" style="width: 27%">
	<div class="pb-2">
		<a href="#"><img alt="ad" src="/image/empty.png" width="100%" height="600pxs"></a>
	</div>
	
	<!-- Bottom-top content -->
  <div class="burning">
      <div class="burning_cont" id="burning_cont">        
      	<div class="tit_cont d-flex" style="justify-content: space-between;">
          <div class="tit">실시간 BURNING</div>
          <span class="time" id="burningTimer"></span>
        </div>
      </div>
  </div>
  <!-- Bottom-bot content -->
  <div class="tab-menu">
  	<input type="radio" name="tabmenu" id="tab01" checked="checked">
  	<label for="tab01" style="border-left: none;">Football</label>
  	<input type="radio" name="tabmenu" id="tab02">
  	<label for="tab02">Baseball</label>
  	<input type="radio" name="tabmenu" id="tab03">
  	<label for="tab03">Soccer</label>
  	<input type="radio" name="tabmenu" id="tab04">
  	<label for="tab04">Basketball</label>
  	<input type="radio" name="tabmenu" id="tab05">
  	<label for="tab05">Common</label>
  	
  	
	<div class="conbox con1">
		<c:forEach var="i" begin="0" end="4">
			<c:if test="${not empty footBoards[i]}">
			<c:set var="rNum" value="${fn:length(footBoards[i].replys)}" />
				<div class="conbox-content d-flex" style="justify-content: space-between;">
					<span class="conbox-title">
						<a href="/auth/list/detail/${footBoards[i].category}/${footBoards[i].id}" style="color: #212529;">${footBoards[i].title}</a>
						<c:if test="${rNum ne 0}">
				     		<span style="color: #ec4908; font-weight: bold;">[${rNum}]</span>
				     	</c:if>
			     	</span>
			     	<span class="conbox-view">${footBoards[i].count}</span>
		     	</div>
		    </c:if> 	
     	</c:forEach>
	</div>
	
	<div class="conbox con2">
		<c:forEach var="i" begin="0" end="4">
			<c:if test="${not empty baseBoards[i]}">
			<c:set var="rNum" value="${fn:length(baseBoards[i].replys)}" />
				<div class="conbox-content d-flex" style="justify-content: space-between;">
					<span class="conbox-title">
						<a href="/auth/list/detail/${baseBoards[i].category}/${baseBoards[i].id}" style="color: #212529;">${baseBoards[i].title}</a>
						<c:if test="${rNum ne 0}">
				     		<span style="color: #ec4908; font-weight: bold;">[${rNum}]</span>
				     	</c:if>
			     	</span>
			     	<span class="conbox-view">${baseBoards[i].count}</span>
		     	</div>
		    </c:if>
     	</c:forEach>
	</div>
	
	<div class="conbox con3">
		<c:forEach var="i" begin="0" end="4">
			<c:if test="${not empty socBoards[i]}">
			<c:set var="rNum" value="${fn:length(socBoards[i].replys)}" />
				<div class="conbox-content d-flex" style="justify-content: space-between;">
					<span class="conbox-title">
						<a href="/auth/list/detail/${socBoards[i].category}/${socBoards[i].id}" style="color: #212529;">${socBoards[i].title}</a>
						<c:if test="${rNum ne 0}">
				     		<span style="color: #ec4908; font-weight: bold;">[${rNum}]</span>
				     	</c:if>
			     	</span>
			     	<span class="conbox-view">${socBoards[i].count}</span>
		     	</div>
		    </c:if>
     	</c:forEach>
	</div>
	
	<div class="conbox con4">
		<c:forEach var="i" begin="0" end="4">
			<c:if test="${not empty basBoards[i]}">
			<c:set var="rNum" value="${fn:length(basBoards[i].replys)}" />
				<div class="conbox-content d-flex" style="justify-content: space-between;">
					<span class="conbox-title">
						<a href="/auth/list/detail/${basBoards[i].category}/${basBoards[i].id}" style="color: #212529;">${basBoards[i].title}</a>
						<c:if test="${rNum ne 0}">
				     		<span style="color: #ec4908; font-weight: bold;">[${rNum}]</span>
				     	</c:if>
			     	</span>
			     	<span class="conbox-view">${basBoards[i].count}</span>
		     	</div>
		    </c:if>
     	</c:forEach>
	</div>
	
	<div class="conbox con5">
		<c:forEach var="i" begin="0" end="4">
			<c:if test="${not empty comBoards[i]}">
			<c:set var="rNum" value="${fn:length(comBoards[i].replys)}" />
				<div class="conbox-content d-flex" style="justify-content: space-between;">
					<span class="conbox-title">
						<a href="/auth/list/detail/${comBoards[i].category}/${comBoards[i].id}" style="color: #212529;">${comBoards[i].title}</a>
						<c:if test="${rNum ne 0}">
				     		<span style="color: #ec4908; font-weight: bold;">[${rNum}]</span>
				     	</c:if>
			     	</span>
			     	<span class="conbox-view">${comBoards[i].count}</span>
		     	</div>
		    </c:if>
     	</c:forEach>
	</div>
  	
  	
  </div>  
	
</div>

<script type="text/javascript">
	const startMinutes = 10;
	let time = startMinutes * 60;
	
	setInterval(updateCountdown, 1000);
	
	const countdownEl = document.getElementById('burningTimer');
	
	function updateCountdown() {
		const minutes = Math.floor(time / 60);
		let seconds = time % 60;
		
		seconds = seconds < 10 ? '0' + seconds : seconds;
		
		if(!(minutes<0)){
			
			if(minutes == 10) {
				countdownEl.innerHTML = minutes+":"+seconds;			
			} else {
				countdownEl.innerHTML = "0"+minutes+":"+seconds;
			}
		} else {
			countdownEl.innerHTML = "";
		}
		time--;
	}
</script>