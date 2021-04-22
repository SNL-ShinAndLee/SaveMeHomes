<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:if test="${signInUser != null}">
  <c:set var="signInUserId" value = "${signInUser.getUserId()}"/>
  <c:set var="signInUserName" value = "${signInUser.getUserName()}"/>
  <c:set var="signInUserEmail" value = "${signInUser.getUserEmail()}"/>
  <c:set var="signInUserAddress" value = "${signInUser.getUserAddress()}"/>
</c:if>

<%-- <%@ include file = "header.jsp" %>--%>
<c:import url = "../header.jsp"/>

<%-- <form class="p-4" method="post" action="${root}/user?act=modifyUser"> --%>
    <div class="mt-5 container">
      <div>
          <div class="fs-1 text-center">User Information</div>
          <div class="mb-3">
            <label for="modifyId" class="form-label">ID</label>
            <input type="text" class="form-control" id="modifyId" value="${signInUserId}" disabled readonly>
          </div>
          <div class="mb-3">
            <label for="originPassword" class="form-label">Origin Password</label>
            <input type="password" class="form-control" id="originPassword" placeholder="Password" required>
          </div>
          <div class="mb-3">
            <label for="modifyPassword" class="form-label">New Password</label>
            <input type="password" class="form-control" id="modifyPassword" placeholder="Password" required>
          </div>
          <div class="mb-3">
            <label for="modifyPasswordCheck" class="form-label">New Password Check</label>
            <input type="password" class="form-control" id="modifyPasswordCheck" placeholder="Password" required>
          </div>
          <div class="mb-3">
            <label for="modifyName" class="form-label">Name</label>
            <input type="text" class="form-control" id="modifyName" value="${signInUserName}" required>
          </div>
          
          <div class="mb-3">
            <label for="modifyEmail" class="form-label">Email address</label>
            <input type="email" class="form-control" id="modifyEmail"  value="${signInUserEmail}" disabled readonly>
          </div>
          <div class="mb-3">
            <label for="modifyAddress" class="form-label">Address</label>
            <input type="text" class="form-control" id="modifyAddress"  value="${signInUserAddress}">
          </div>
      </div>
    
      <div class="d-flex justify-content-center">
        <button id="modifyCancelBtn" type="button" class="btn btn-outline-secondary m-3">뒤로가기</button>
        <button id="modifyBtn" type="button" class="btn btn-outline-primary m-3">수정하기</button>
        <button id="withdrawBtn" type="button" class="btn btn-outline-danger m-3">탈퇴하기</button>
      </div>
    </div>
      <!-- </form> -->
<script>




</script>

<%-- <%@ include file = "footer.jsp" %> --%>
<c:import url = "../footer.jsp"/>