<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%-- <%@ include file = "header.jsp" %>--%>
<c:import url = "../header.jsp"/>

<form class="p-4" method="post" action="${root}/user?act=modifyUser">
        <div>
            <div class="mb-3">
              <label for="modifyId" class="form-label">ID</label>
              <input type="text" class="form-control" id="modifyId" placeholder="아이디" disabled readonly>
            </div>
            <div class="mb-3">
              <label for="modifyPassword" class="form-label">Password</label>
              <input type="password" class="form-control" id="modifyPassword" placeholder="Password">
            </div>
            <div class="mb-3">
              <label for="modifyPasswordCheck" class="form-label">Password Check</label>
              <input type="password" class="form-control" id="modifyPasswordCheck" placeholder="Password">
            </div>
            <div class="mb-3">
              <label for="modifyName" class="form-label">Name</label>
              <input type="text" class="form-control" id="modifyName">
            </div>
            
            <div class="mb-3">
              <label for="modifyEmail" class="form-label">Email address</label>
              <input type="email" class="form-control" id="modifyEmail" placeholder="email@example.com" disabled readonly>
            </div>
            <div class="mb-3">
              <label for="modifyAddress" class="form-label">Address</label>
              <input type="text" class="form-control" id="modifyAddress">
            </div>
        </div>
        <div>
          <button type="button" class="btn btn-outline-secondary">취소</button>
		  <button type="submit" class="btn btn-outline-primary">수정</button>
        </div>
      </form>



<%-- <%@ include file = "footer.jsp" %> --%>
<c:import url = "../footer.jsp"/>