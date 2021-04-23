<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "com.snl.savemehomes.common.UserRole"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<%@ include file = "../header.jsp" %>

<div class="container">
<div class="fs-1 row justify-content-center">공지사항</div>

<span class="d-flex flex-row-reverse">
	<c:if test="${signInUser.getUserRole() == UserRole.ADMINISTRATOR}">
		<button id="writebtn" type="button" class="btn btn-outline-dark">글쓰기</button>
	</c:if>	
</span>
<table class="table table-striped table-hover mt-5">
  <thead>
    <tr>
      <th scope="col">글번호</th>
      <th scope="col">제목</th>
      <th scope="col">작성일</th>
    </tr>
  </thead>
  <tbody id="noticeListBody">
  </tbody>
</table>
<nav aria-label="Page navigation example">
  <ul id="noticePagination" class="pagination justify-content-center">
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li class="page-item"><a class="page-link" href="#"></a></li>
    <li class="page-item"><a class="page-link" href="#"></a></li>
    <li class="page-item"><a class="page-link" href="#"></a></li>
    <li class="page-item"><a class="page-link" href="#"></a></li>
    <li class="page-item"><a class="page-link" href="#"></a></li>
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
</div>

<div id="noticePageCount" class="d-none">
	${noticePageCount}
</div>

<script src="${root}/js/notice.js"></script>

<%@ include file = "../footer.jsp" %>
