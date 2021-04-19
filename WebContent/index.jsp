<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "com.snl.savemehomes.constants.Constants"%>
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
      <div id="map" class="col-12 bg-warning" style="height:40vh;">
         
         지도
         
         
         
      </div>
    </div>
    <div class="row">
      <div class="col-4 bg-warning">오늘의 뉴스</div>
      <div class="col-4 bg-warning">선별진료소</div>
      <div class="col-4 bg-warning"><%=Constants.getMapAPIKey()%></div>
    </div>
  </div>
</div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=<%=Constants.getMapAPIKey()%>"></script>
<script>
var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
	level: 3 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

</script>

<%-- <%@ include file = "footer.jsp" %> --%>
<c:import url = "footer.jsp"/>