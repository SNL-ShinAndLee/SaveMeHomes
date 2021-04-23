<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <%@ include file = "header.jsp" %>--%>
<c:import url = "../header.jsp"/>
<%
	String act, idx;
	if(request.getParameter("act").equals("noticeModify"))
		act = "noticeModifiedSave";
	else
		act = "boardModifiedSave";
    idx = request.getParameter("idx");
%>

<div class="container">
	<form method="post" action="posting?act=<%=act%>">
        <input name="idx" type="hidden" class="form-control" id="idx" value="<%=idx%>" >
		<div id="selectOne" class="d-flex justify-content-center fs-1"></div>
		<div class="mb-3">
		  <label for="writerId" class="form-label">작성자</label>
		  <input name="writerId" type="text" class="form-control" id="writerId" value="${writer}" disabled readonly>
		</div>
		<div class="mb-3">
		  <label for="title" class="form-label">제목</label>
		  <input name="title" type="text" class="form-control" id="title" value="${title}">
		</div>
		<div class="mb-3">
		  <label for="contents" class="form-label">글 내용</label>
		  <textarea name="contents" class="form-control" id="contents" rows="6">${contents}</textarea>
		</div>
		<div class="d-flex justify-content-center mb-5">
		<button id="listbtn" type="button" class="btn btn-outline-secondary m-2">뒤로가기</button>
		<input id="writebtn" type="submit" class="btn btn-outline-success m-2" value="수정하기">
		</div>
	</form>
</div>


<script>
	function getBack() {
		history.back();
	}
	
	const listbtn = document.getElementById("listbtn");
	listbtn.addEventListener("click", getBack);
	
	/* 
	function searchParam(key) {
		return new URLSearchParams(location.search).get(key);
	}  */
	
	function selectOne() {
		const act = searchParam("act");
		if(act == "noticeModify")
			document.getElementById("selectOne").innerHTML = "공지사항 수정";	
		else
			document.getElementById("selectOne").innerHTML = "게시물 수정";
	}
	selectOne();

</script>

<%-- <%@ include file = "footer.jsp" %> --%>
<c:import url = "../footer.jsp"/>