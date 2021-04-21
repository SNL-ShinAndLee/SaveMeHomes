<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<%-- <%@ include file = "header.jsp" %>--%>
<c:import url = "../header.jsp"/>

	<div class="d-flex flex-column justify-content-center align-items-center" style="height: 50vh;">
		<div class="fs-1">
			회원가입에 성공하였습니다.
		</div>
		<div>
			<a class="btn btn-dark" href="${root}" role="button">메인으로 이동</a>
		</div>
	</div>

<script>
	document.getElementById("headersigninBtn").style.display = "none";
	document.getElementById("headersignupBtn").style.display = "none";
	
	const menuEls = document.querySelectorAll("#headermenuNav > a");
	for(let i=1; i<menuEls.length; ++i) {
		menuEls[i].style.display = "none";
	}
		
	
</script>
<%-- <%@ include file = "footer.jsp" %> --%>
<c:import url = "../footer.jsp"/>
