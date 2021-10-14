<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <!-- ---------------------------header---------------------------------------------- -->
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>3355</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
  <!-- <link href="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.carousel.css" rel="stylesheet" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.carousel.js"></script> -->
  <link href="/css/slide.css" rel="stylesheet" />
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css" />
  <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->

  
</head>
<body style="width: 100%; background-color: #ededed;">

<nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-between">
  <!-- Brand/logo -->
  <div class="d-flex" style="align-items: baseline;">
  <input type="hidden" id="userId" value="${principal.user.id}" />
  <a class="navbar-brand" href="/"><img alt="logo" src="/image/logo.png" width="100px;"></a>
  
  <!-- Links -->
  <c:choose>
				<c:when test="${empty principal}">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/auth/loginForm">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/detailForm/${principal.user.id}" id="userDetail">회원정보</a></li>
						<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
					</ul>
				</c:otherwise>
  </c:choose>
  </div>
  <ul class="navbar-nav">
    <li class="nav-item px-5">
      <a class="nav-link" href="/auth/list/Football" id="category">Football</a>
    </li>
    <li class="nav-item px-5">
      <a class="nav-link" href="/auth/list/Baseball" id="category">Baseball</a>
    </li>
    <li class="nav-item px-5">
      <a class="nav-link" href="/auth/list/Soccer" id="category">Soccer</a>
    </li>
    <li class="nav-item px-5">
      <a class="nav-link" href="/auth/list/Basketball" id="category">Basketball</a>
    </li>
    <li class="nav-item px-5">
      <a class="nav-link" href="/auth/list/Common" id="category">Common</a>
    </li>
  </ul>
  <form class="form-inline" action="/auth/list" method="GET">
  	<c:choose>
  		<c:when test="${empty param.search}">
  			<input id="search" name="search" class="form-control mr-sm-2" type="text" placeholder="Search">
  		</c:when>
  		<c:otherwise>
  			<input id="search" name="search" class="form-control mr-sm-2" type="text" placeholder="${param.search}">
  		</c:otherwise>
  	</c:choose>
    <button class="btn btn-success" type="submit" id="btn-search">Search</button>
  </form>
</nav>