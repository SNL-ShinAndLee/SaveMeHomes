<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:if test="${cookie.saveId.value != null}">
  <c:set var="saveId" value="${cookie.saveId.value}"/>
  <c:set var="idcheck" value=" checked=\"checked\""/>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Save Me Homes : MainPage</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<link rel="stylesheet" href="${root}/css/style.css"/>
<script src="${root}/js/constants.js"></script>
<script src="${root}/js/header.js"></script>
</head>
<body>

<!-- navbar1 -->
<nav class="navbar navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="${root}">Save Me Homes</a>
    <div class="d-flex align-items-center">
    
      <c:if test="${signInUser == null}">
        <button id="headersigninBtn" class="btn btn-outline-light mx-1" type="button" data-bs-toggle="modal" data-bs-target="#signInModal">로그인</button>
        <button id="headersignupBtn" class="btn btn-outline-light mx-1" type="button" data-bs-toggle="modal" data-bs-target="#signUpModal">회원가입</button>
      </c:if>
      <c:if test="${signInUser != null}"> 
        <c:set var="signInUserId" value = "${signInUser.getUserId()}"/>
        <c:set var="signInUserName" value = "${signInUser.getUserName()}"/>
      
        <div class="fs-5 text-light mx-3">${signInUserId}(${signInUserName})님 </div>
        <a href="${root}/user?act=userInfo" id="headeruserinfoBtn" class="btn btn-outline-light mx-1" type="button">회원정보</a>
        <button id="headersignoutBtn" class="btn btn-outline-light mx-1" type="button">로그아웃</button>
      </c:if>
      
    </div>
  </div>
</nav>


<!-- navbar2 -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="${root}">
      <img alt="logo" src="${root}/images/logo.png" class="logo"/>
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div id="headermenuNav" class="navbar-nav">
        <a class="nav-link active" aria-current="page" href="${root}">Home</a>
        <a class="nav-link" href="${root}/main?act=notice">공지사항</a>
        <a class="nav-link" href="${root}/main?act=todaynews">오늘의 뉴스</a>
        <a class="nav-link" href="${root}/main?act=board">게시판</a>
        
        <!-- <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a> -->
      </div>
    </div>
  </div>
</nav>

<!-- sign in modal -->
<div class="modal fade" id="signInModal" tabindex="-1" aria-labelledby="signInModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="signInModalLabel">Sign In</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form class="p-4" method="post" action="${root}/user?act=signin">
        <div class="modal-body">
          <div class="mb-3">
            <label for="signInId" class="form-label">ID</label>
            <input type="text" class="form-control" id="signInId" name="signInId" value="${saveId}">
          </div>
          <div class="mb-3">
            <label for="signInPassword" class="form-label">Password</label>
            <input type="password" class="form-control" id="signInPassword" name="signInPassword" placeholder="Password">
          </div>
          <div class="mb-3">
            <div class="form-check">
              <input type="checkbox" class="form-check-input" id="signInCheck" name="signInCheck" value="saveCheck" ${idcheck}>
              <label class="form-check-label" for="signInCheck">
                Remember me
              </label>
            </div>
          </div>        
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="submit" class="btn btn-primary">Sign In</button>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- login modal end -->

<!-- sign up modal -->
<div class="modal fade" id="signUpModal" tabindex="-1" aria-labelledby="signUpModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="signUpModalLabel">Sign Up</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      
      
      <form id="signUpForm" class="p-4" method="post" action="${root}/user?act=signup">
        <div class="modal-body">
        
        <!-- toast start -->
          <div id="dupToast" class="d-none toast align-items-center text-white bg-primary border-0" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
          <div id="dupContent" class="toast-body">
            
          </div>
          <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
      </div>
        <!-- toast end -->
        
            <div class="mb-3">
              <label for="signUpId" class="form-label">ID</label>
              <div class="container">
                <div class="row"> 
                  <input type="text" class="col-9 form-control" id="signUpId" name="signUpId" style="width: 300px; margin-right: 13.5px;">
                  <button id="dupBtn" type="button" class="col-3 btn btn-danger">중복확인</button>
                </div>
              </div>
            </div>
            <div class="mb-3">
              <label for="signUpPassword" class="form-label">Password</label>
              <input type="password" class="form-control" id="signUpPassword" name="signUpPassword" placeholder="Password" required>
            </div>
            <div class="mb-3">
              <label for="signUpPasswordCheck" class="form-label">Password Check</label>
              <input type="password" class="form-control" id="signUpPasswordCheck" name="signUpPasswordCheck" placeholder="Password" required>
            </div>
            <div class="mb-3">
              <label for="signUpName" class="form-label">Name</label>
              <input type="text" class="form-control" id="signUpName" name="signUpName" required>
            </div>
            
            <div class="mb-3">
              <label for="signUpEmail" class="form-label">Email address</label>
              <input type="email" class="form-control" id="signUpEmail" name="signUpEmail" placeholder="email@example.com" required>
            </div>
            <div class="mb-3">
              <label for="signUpAddress" class="form-label">Address</label>
              <input type="text" class="form-control" id="signUpAddress" name="signUpAddress">
            </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button id="signUpBtn" type="submit" class="btn btn-primary" disabled>Sign Up</button>
        </div>
      </form>
    </div>
  </div>
</div>
