<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "com.snl.savemehomes.constants.Constants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!-- drop down start -->
  <div class="bg-dark py-3 text-center">
    <form>
      <div class="btn-group">
        <button id="sidoBtn" class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                    도/광역시
        </button>
        <ul id="sido" class="dropdown-menu">         
          
        </ul>
        
      </div>
      <div class="btn-group">
        <button id="gugunBtn" class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                   시/구/군
        </button>
        <ul id="gugun" class="dropdown-menu">
          
        </ul>
      </div>
      <div class="btn-group">
        <button id="dongBtn" class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                    동
        </button>
        <ul id="dong" class="dropdown-menu">
          
        </ul>
      </div>
    </form>
  </div>
  <!-- drop down end -->
  <div class="container">
  <div class="row my-3">
      <div id="map" class="col-12 bg-warning" style="height:50vh;">
         
         지도
         
         
         
      </div>
    </div>
   </div>
  
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=<%=Constants.getMapAPIKey()%>&libraries=services,clusterer,drawing"></script>
<script src="${pageContext.request.contextPath}/js/mapsearch.js"></script>
<script src="${pageContext.request.contextPath}/js/city.js"></script>
