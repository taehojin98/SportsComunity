<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">	
	<div class="d-flex mt-5 mb-5" style="justify-content: center;">
		<img alt="write" src="/image/write.png">
	</div>
	<form>
		<div class="form-group d-flex">
			<input type="text" class="form-control" placeholder="Enter Title" id="title">
			<select id="category" name="category" style="font-weight: bold; font-size: 15px; border-radius: 5px 5px 5px 5px;">
			  <option value="Category">Category</option>
			  <option value="Football">Football</option>
			  <option value="Baseball">Baseball</option>
			  <option value="Soccer">Soccer</option>
			  <option value="Basketball">Basketball</option>
			  <option value="Common">Common</option>
			</select>
		</div>

		<div class="form-group">
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>

	</form>
	<button id="btn-write" class="btn btn-primary">글쓰기</button>
</div>
<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 300
	});
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>