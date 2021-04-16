<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <%@ include file = "header.jsp" %>--%>
<c:import url = "header.jsp"/>

<!-- google map -->
<div class="">
  <!-- drop down start -->
  <div class="bg-dark py-3 text-center">
    <form>
      <div class="btn-group">
        <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                    도/광역시
        </button>
        <ul class="dropdown-menu">
          ...
        </ul>
      </div>
      <div class="btn-group">
        <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                   시/구/군
        </button>
        <ul class="dropdown-menu">
          ...
        </ul>
      </div>
      <div class="btn-group">
        <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                    동
        </button>
        <ul class="dropdown-menu">
          ...
        </ul>
      </div>
    </form>
  </div>
  <!-- drop down end -->
  
  <div class="container my-3">
    <div class="row my-3">
      <div class="col-12 bg-warning">
                지도
      </div>
    </div>
    <div class="row">
      <div class="col-4 bg-warning">1</div>
      <div class="col-4 bg-warning">2</div>
      <div class="col-4 bg-warning">33</div>
    </div>
  </div>
</div>
<%-- <%@ include file = "footer.jsp" %> --%>
<c:import url = "footer.jsp"/>