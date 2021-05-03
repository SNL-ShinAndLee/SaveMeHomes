<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "com.snl.savemehomes.constants.Constants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <%@ include file = "header.jsp" %>--%>
<c:import url = "header.jsp"/>

<!-- google map -->
<div class="">
  <c:import url = "cityMap.jsp"/>
  
  <div class="container my-3">
    
    <div class="row">
      <div class="col-4 bg-warning">오늘의 뉴스</div>
      <div class="col-4 bg-warning">선별진료소</div>
      <div class="col-4 bg-warning">?</div>
    </div>
  </div>
</div>


<%-- <%@ include file = "footer.jsp" %> --%>
<c:import url = "footer.jsp"/>