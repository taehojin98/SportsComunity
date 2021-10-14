<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!-- ---------------------------header---------------------------------------------- -->
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/topad.jsp"%>

<!-- ---------------------------content---------------------------------------------- -->
<div class="container d-flex">
<input type="hidden" id="principal" value="${principal}" />
<input type="hidden" id="boardId" value="${board.id}" />
<input type="hidden" id="boardCategory" value="${board.category}" />
<div class="container pt-4" style="width: 73%">
  <div class="card">
	  <div class="card-header" style="background: #fff">
	  	<div class="d-flex justify-content-between pb-4">
	  		<div class="title" style="font-weight: bold;">${board.title}</div>
	  		<div class="writer"><small>${board.user.username}</small></div>
	  	</div>
	  	<div class="d-flex justify-content-between">
	  		<div class="left-content d-flex">
		  		<div class="board-num" style="font-size: 13px; color: #666;">글번호<em class="pl-2 pr-2"  style="font-style: normal; color: #9d9d9d;">${board.id}</em></div>
		  		<div class="div-line" style="font-size: 12.5px; color: #9d9d9d;">|</div>
		  		<div class="datePosted pl-2" style="font-size: 13px; color: #666;"><fmt:formatDate value="${board.dateCreated}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
	  		</div>
	  		<div class="right-content d-flex">
	  			<div class="board-hit" style="font-size: 13px; color: #666;">조회수<em class="pl-2 pr-2"  style="font-style: normal; color: #000; font-weight: bold;">${board.count}</em></div>
	  			<div class="div-line" style="font-size: 12.5px; color: #9d9d9d;">|</div>
	  			<div class="board-reply pl-2" style="font-size: 13px; color: #666;">댓글<em class="pl-2"  style="font-style: normal; color: #ff4b1b; font-weight: bold;">${replyNum}</em></div>
	  		</div>
	  	</div>
	  </div>
	  <div class="card-body" style="background: rgba(0,0,0,.03); color: #343434; font-size: 15px">${board.content}</div>
	</div>
	
	<div class="replys card mt-3" style="width: 100%">
	
	 <div class="card-header" style="background: #fff; color: #5f6679;">댓글<em class="pl-2"  style="font-style: normal; color: #ff4b1b; font-weight: bold;">${replyNum}</em></div>
	  
	  <div class="card-body" style="background: #cfd7e9">
	  	
	  	<c:forEach items="${board.replys}" var="reply">
	  		<c:choose>
	  			<c:when test="${reply.user.id eq board.user.id}">
	  				<div class="comment-box mb-3" style="background: #fcffca; border-radius: 10px 10px 10px 10px; padding: 15px 10px;">
				  		<div class="replier-info d-flex justify-content-between">
				  			<a id="btn-cmt-delete" href="#" style="height: 13px;" onclick="index2.cmtDelete(${board.id}, ${reply.id})"><img alt="삭제" src="/image/xIcon.png" width="13px" height="13px" style="margin-bottom: 11px;"/></a>
					  		<div class="d-flex">
						  		<div class="datePosted pr-2" style="font-size: 13px; color: #666;"><fmt:formatDate value="${reply.dateCreated}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
					  			<div class="div-line" style="font-size: 12.5px; color: #9d9d9d;">|</div>
					  			<div class="replier-name pl-2" style="font-size: 13px; color: #666;">${reply.user.username}</div>
				  			</div>
				  		</div>
				  		<div class="actual-comment pt-2 d-flex justify-content-end" style="font-size: 13px">${reply.content}</div>
				  	  </div>
	  			</c:when>
	  			<c:otherwise>
	  				<div class="comment-box mb-3" style="background: #fff; border-radius: 10px 10px 10px 10px; padding: 15px 10px;">
				  		<div class="replier-info d-flex justify-content-between">
					  		<div class="d-flex">
					  			<div class="replier-name pr-2" style="font-size: 13px; color: #666;">${reply.user.username}</div>
					  			<div class="div-line" style="font-size: 12.5px; color: #9d9d9d;">|</div>
						  		<div class="datePosted pl-2" style="font-size: 13px; color: #666;"><fmt:formatDate value="${reply.dateCreated}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
						  	</div>
						  	<c:if test="${principal.user.id eq reply.user.id}">
					  			<a id="btn-cmt-delete" href="#" style="height: 13px;" onclick="index2.cmtDelete(${board.id}, ${reply.id})"><img alt="삭제" src="/image/xIcon.png" width="13px" height="13px" style="margin-bottom: 11px;"/></a>
					  		</c:if>
				  		</div>
				  		<div class="actual-comment pt-2" style="font-size: 13px">${reply.content}</div>
				  	  </div>
	  			</c:otherwise>
	  		</c:choose>
		  	
	  	  </c:forEach>
	  	  
		  <div class="comment d-flex" style="padding-top: 20px; width: 100%">
		  	<div class="input-comment pr-3">
			  	<textarea id="contentTable" name="contentTable" cols="90" style="height:72px; width: 100%;"></textarea>
		  	</div>
		  	<a class="input-button" href="#" id="btn-cmt-write">
		  		<img src="http://image.donga.com/challenge/mlbpark/images/sub_ok1.gif" border="0" style="margin: 0 0 3px 0px;">
		  	</a>
		  </div>
		  
		  <div class="d-flex justify-content-between mt-2">
		  	<div class="btn btn-secondary" onclick="history.back();">목록</div>
			  <c:if test="${principal.user.username eq board.user.username}">
			  	<div>
				  <a href="/board/updateForm/${board.id}" style="color: #fff;"><button id="btn-pre-update" class="btn btn-secondary">수정하기</button></a>
				  <button id="btn-pre-delete" class="btn btn-secondary ml-2" data-toggle="modal" data-target="#myModal">삭제하기</button>
				</div>
			  </c:if>
		  </div>
		  
		  
		  <!-- The Modal -->
		  <div class="modal" id="myModal">
		    <div class="modal-dialog">
		      <div class="modal-content">
		      
		        <!-- Modal Header -->
		        <div class="modal-header">
		          <h4 class="modal-title">삭제하기</h4>
		        </div>
		        
		        <!-- Modal body -->
		        <div class="modal-body">
		          게시글을 삭제하시겠습니까?
		        </div>
		        
		        <!-- Modal footer -->
		        <div class="modal-footer">
		          <button id="btn-delete" class="btn btn-danger" data-dismiss="modal">삭제</button>
		          <button class="btn btn-secondary" data-dismiss="modal">취소</button>
		        </div>
		        
		      </div>
		    </div>
		  </div>
	  </div>
	</div>
	
	
  	<div class="next-board mt-3" style="border-top: 1px solid #dee2e6; border-bottom: 1px solid #dee2e6;">
  		<span class="px-5 py-1" style="font-size: 13px; color: #979797; background: #f5f5f5; border-right: 1px solid #dee2e6;">다음글</span>
  		<c:choose>
  			<c:when test="${empty nextBoard}">
  				<span class="ml-3" style="font-size: 13px; color: #979797; font-weight: normal;">다음글이 없습니다.</span>
  			</c:when>
  			<c:otherwise>
  				<a href="/auth/list/detail/${nextBoard.category}/${nextBoard.id}"><span class="ml-3" style="font-size: 13px; color: #2572aa; font-weight: bold;">${nextBoard.title}</span></a>
  			</c:otherwise>
  		</c:choose>
  	</div>
  	
  	<div class="next-board" style="border-top: 1px solid #dee2e6; border-bottom: 1px solid #dee2e6;">
  		<span class="px-5 py-1" style="font-size: 13px; color: #979797; background: #f5f5f5; border-right: 1px solid #dee2e6;">이전글</span>
  		<c:choose>
  			<c:when test="${empty prevBoard}">
  				<span class="ml-3" style="font-size: 13px; color: #979797; font-weight: normal;">이전글이 없습니다.</span>
  			</c:when>
  			<c:otherwise>
  				<a href="/auth/list/detail/${prevBoard.category}/${prevBoard.id}"><span class="ml-3" style="font-size: 13px; color: #2572aa; font-weight: bold;">${prevBoard.title}</span></a>
  			</c:otherwise>
  		</c:choose>
  	</div>
	
</div>
	

<!-- ---------------------------right content---------------------------------------------- -->
<%@ include file="../layout/rightcontent.jsp"%>

</div>

<!-- ---------------------------footer---------------------------------------------- -->
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>