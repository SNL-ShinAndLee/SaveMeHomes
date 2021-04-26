<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<div class="bg-light">
  <div class="container">
    <div class="row align-items-center">
      <img class="col-3 logo" alt="logo" src="${root}/images/logo.png"/>
      <div class="col-9 fs-3"> Find Us</div>
    </div>
    <div class="row">
      <div class="offset-3 col-9">
        	SNL@pusan.ac.kr
      </div>
    </div>
  </div>
  
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<script src="${root}/js/signup.js"></script>
<script src="${root}/js/signout.js"></script>
<script src="${root}/js/city.js"></script>

</body>
</html>