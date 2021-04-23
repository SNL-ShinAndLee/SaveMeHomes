<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "com.snl.savemehomes.common.UserRole"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%-- <%@ include file = "header.jsp" %>--%>
<c:import url = "../header.jsp"/>

<c:if test="${param.act.equals('notice')}">
	<c:set var="act" value="noticeModifySave"/> 
</c:if>
<c:if test="${param.act.equals('board')}">
	<c:set var="act" value="boardModifySave"/> 
</c:if>
<c:set var="noticeAuthority" value="${signInUser.getUserRole() == UserRole.ADMINISTRATOR && act.equals('noticeModifySave')}"/>
<c:set var="boardAuthority" value="${signInUser.getUserId().equals(writer) && act.equals('boardModifySave')}"/>

<div class="my-5 fs-1 row justify-content-center">
	<c:if test="${param.act.equals('notice')}">
		공지사항
	</c:if>
	<c:if test="${param.act.equals('board')}">
		게시판
	</c:if>
</div>
<div class="container border boredr-5">
	<div class="row">
		<div id="idx" class="col-2 p-3">
		
		</div>
		<div class="fs-3 col-10 p-3">
		${title}
		</div>
	</div>
	
	<div class="row">
		<div class="col-4 p-3">
			<c:if test="${param.act.equals('notice')}">
				관리자
			</c:if>
			<c:if test="${param.act.equals('board')}">
				${writer}
			</c:if>
		</div>
	
		<div class="offset-4 col-4 p-3">
		${postingdate}
		</div>
	</div>

	<div class="row">
		<div class="col-12 bg-light bg-gradient p-5">
		${contents}
		</div>
	</div>
	
</div>
<div class="my-4 text-center">
	<button id="listbtn" type="button" class="m-3 btn btn-outline-secondary">목록으로</button>
	<c:if test="${noticeAuthority || boardAuthority}">
		<button id="modifybtn" type="button" class="m-3 btn btn-outline-success">수정하기</button>
		<button id="deletebtn" type="button" class="m-3 btn btn-outline-danger">삭제하기</button>
	</c:if>
</div>

<script>
	const idx = searchParam("idx");
	document.getElementById("idx").innerHTML = idx;
	
	function getBack() {
		history.back();
	}
	
	const listbtn = document.getElementById("listbtn");
	listbtn.addEventListener("click", getBack);
	
	function modifying(){
		const act = searchParam("act");	
		let url;
		if(act == "notice"){
			url = root + "/posting?act=noticeModify&idx="+idx;
		}
		else{
			url = root + "/posting?act=boardModify&idx="+idx;
		}
		console.log(url);
		location.href = url;
	}
	const modifybtn = document.getElementById("modifybtn");
	modifybtn.addEventListener("click", modifying);
	
	function deleteCheck() {
		const act = searchParam("act");
		const idx = searchParam("idx");
		
		if(act == "notice") {
			if(confirm("공지사항을 삭제하시겠습니까?")) 
				document.location = root + "/posting?act=noticeDelete&idx=" + idx;	
		}
		else {
			if(confirm("게시글을 삭제하시겠습니까?")) 
				document.location = root + "/posting?act=boardDelete&idx=" + idx;
		}
		
	}
	
	const deletebtn = document.getElementById("deletebtn");
	deletebtn.addEventListener("click", deleteCheck);
	
</script>


<%-- <%@ include file = "footer.jsp" %> --%>
<c:import url = "../footer.jsp"/>