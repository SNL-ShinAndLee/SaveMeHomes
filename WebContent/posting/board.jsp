<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%-- <%@ include file = "header.jsp" %>--%>
<c:import url = "../header.jsp"/>
<div class="container">
<div class="fs-1 row justify-content-center">게시판</div>
<span class="d-flex flex-row-reverse">
	<button id="writebtn" type="button" class="btn btn-outline-dark">글쓰기</button>
</span>
<table class="table table-striped table-hover mt-5">
  <thead>
    <tr>
      <th scope="col">글번호</th>
      <th scope="col">제목</th>
      <th scope="col">작성자</th>
      <th scope="col">작성일</th>
    </tr>
  </thead>
  <tbody>
    <tr class="board">
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr class="board">
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr class="board">
      <th scope="row">3</th>
      <td colspan="2">Larry the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
</div>

<script>
/* 	function getContextPath() {
	    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
	}
	
	const root = getContextPath(); */
	
	function getBoard(e) {
		const idx = e.currentTarget.children[0].innerText;
		const url = root + "/posting?act=board&idx=" + idx;
		location.href = url;
	}
	const boardtr = document.getElementsByClassName("board");
	for (let i=0; i<boardtr.length; ++i)
	{
		boardtr[i].addEventListener("click", getBoard);
	}
	
	function writing() {
		const url = root + "/posting?act=boardWrite";
		location.href = url;
	}
	
	const writebtn = document.getElementById("writebtn");
	writebtn.addEventListener("click", writing);
</script>

<%-- <%@ include file = "footer.jsp" %> --%>
<c:import url = "../footer.jsp"/>