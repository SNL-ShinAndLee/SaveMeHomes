<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <%@ include file = "header.jsp" %>--%>
<c:import url = "../header.jsp"/>
<div class="container">
	<form>
		<div class="d-flex justify-content-center fs-1">게시물 수정</div>
		<div class="d-flex justify-content-center fs-1">공지사항 수정</div>
		<div class="mb-3">
		  <label for="writerId" class="form-label">작성자</label>
		  <input type="text" class="form-control" id="writerId" placeholder="아이디" disabled readonly>
		</div>
		<div class="mb-3">
		  <label for="title" class="form-label">제목</label>
		  <input type="text" class="form-control" id="title">
		</div>
		<div class="mb-3">
		  <label for="contents" class="form-label">글 내용</label>
		  <textarea class="form-control" id="contents" rows="6"></textarea>
		</div>
		<div class="d-flex justify-content-center mb-5">
		<button type="button" class="btn btn-outline-secondary m-2">뒤로가기</button>
		<button type="button" class="btn btn-outline-success m-2">수정하기</button>
		</div>
	</form>
</div>


<%-- <%@ include file = "footer.jsp" %> --%>
<c:import url = "../footer.jsp"/>