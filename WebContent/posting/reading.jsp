<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%-- <%@ include file = "header.jsp" %>--%>
<c:import url = "../header.jsp"/>
<style>

</style>


<div class="container border boredr-5">
	<div class="row">
		<div id="idx" class="col-2 p-3">
		
		</div>
		<div class="col-10 p-3">
		제 목
		</div>
	</div>
	
	<div class="row">
		<div class="col-4 p-3">
		작성자
		</div>
		
		<div class="offset-4 col-4 p-3">
		작성 날짜
		</div>
	</div>

	<div class="row">
		<div class="col-12 bg-light bg-gradient p-5">
		그으을
		</div>
	</div>
	
	<div class="row">
		<div class="offset-4 col-4 text-center">
			<button id="listbtn" type="button" class="m-3 btn btn-outline-secondary">목록으로</button>
			<button id="modify" type="button" class="m-3 btn btn-outline-success">수정하기</button>
		</div>
	</div>
</div>

<script>
	function searchParam(key) {
	  return new URLSearchParams(location.search).get(key);
	};
	
	const idx = searchParam("idx");
	document.getElementById("idx").innerHTML = idx;
	
	function getBack() {
		history.back();
	}
	
	const listbtn = document.getElementById("listbtn");
	listbtn.addEventListener("click", getBack);
	
</script>


<%-- <%@ include file = "footer.jsp" %> --%>
<c:import url = "../footer.jsp"/>