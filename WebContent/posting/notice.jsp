<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "com.snl.savemehomes.common.UserRole"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <%@ include file = "header.jsp" %>--%>
<c:import url = "../header.jsp"/>
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
      <th scope="col">작성자</th>
      <th scope="col">작성일</th>
    </tr>
  </thead>
  <tbody>
    <tr class="notice">
      <th scope="row">1</th>
      <td>제목예제1</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr class="notice">
      <th scope="row">2</th>
      <td>제목예제2</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr class="notice">
      <th scope="row">3</th>
      <td>제목예제3</td>
      <td>dd</td>
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
	function getContextPath() {
	    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
	}
	
	const root = getContextPath();
	function getNotice(e) {
		const idx = e.currentTarget.children[0].innerText;
		const url = root + "/posting?act=notice&idx=" + idx;
		location.href = url;
	}
	const noticetr = document.getElementsByClassName("notice");
	for (let i=0; i<noticetr.length; ++i)
	{
		noticetr[i].addEventListener("click", getNotice);
	}
	
	function writing() {
		const url = root + "/posting?act=noticeWrite";
		location.href = url;
	}
	
	const writebtn = document.getElementById("writebtn");
	writebtn.addEventListener("click", writing);
</script>

<%-- <%@ include file = "footer.jsp" %> --%>
<c:import url = "../footer.jsp"/>