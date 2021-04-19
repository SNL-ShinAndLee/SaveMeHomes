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
    <div id="map" class="col-12 bg-warning" style="height: 40vh;">
              지도
    </div>
  </div>
  <div class="row">
  
    <div class="col-4">
      <div class="card text-center">
        <div class="card-body">
          <h5 id="apartName" class="card-title">아파트명</h5>
          <p id="dealAmount" class="card-text">가격</p>
          <p id="buildYear" class="card-text" style="display:none;">건축년도</p>
          <p id="landArea" class="card-text" style="display:none;">대지면적</p>
          <p id="dong" class="card-text" style="display:none;">??동</p>
          <p id="dealDate" class="card-text">거래일</p>
          <p id="dedicatedArea" class="card-text">전용면적</p>
          <p id="lotNum" class="card-text" style="display:none;">지번</p>
          <p id="cityCode" class="card-text" style="display:none;">지역코드</p>
          <p id="floor" class="card-text" style="display:none;">층</p>
          <!-- 
                          동, 지번, 층 순서대로
                          지역코드 숨기기            
           -->
          <button type="button" class="btn btn-primary cardBtn" data-bs-toggle="modal" data-bs-target="#detailViewModal">
                            상세보기
          </button>
        </div>
      </div>
    </div>
    
    <!-- card 추가 -->
    
  </div>
</div>
<!-- container end -->

<!-- Modal -->
<div class="modal fade" id="detailViewModal" tabindex="-1" aria-labelledby="detailViewModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="detailViewModalLabel">아파트명</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="detailView">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
</div>


<!-- kakao map불러오기 -->
<%-- <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=<%=Constants.getMapAPIKey()%>"></script> --%>
<!-- services 라이브러리 불러오기 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=<%=Constants.getMapAPIKey()%>&libraries=services,clusterer,drawing"></script>

<script>
/*아파트 상세보기*/
function detailView(e){
	var cur = e.currentTarget;
	var par = cur.parentNode;
	
	document.getElementById("detailViewModalLabel").innerHTML = par.querySelector(".card-title").innerText;
	
	var children = par.querySelectorAll(".card-text");
	var modalChild = document.querySelector("#detailView");
	modalChild.innerHTML = "";
	for(let i=0; i < children.length ; ++i){
		modalChild.innerHTML += ("<p>" + children[i].innerText + "</p>");
	}
	
}
const cardBtn = document.getElementsByClassName("cardBtn");
for (let i=0; i<cardBtn.length; ++i)
{
	cardBtn[i].addEventListener("click", detailView);
}

/*카카오맵 불러오기*/
 /* 
var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
  center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
  level: 3 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
 */

/*카카오맵 서비스*/
// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places(); 

// 키워드로 장소를 검색합니다
ps.keywordSearch('충신동 25-59', placesSearchCB); 
ps.keywordSearch('혜화동 6-26', placesSearchCB); 

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCB (data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        var bounds = new kakao.maps.LatLngBounds();

        for (var i=0; i<data.length; i++) {
            displayMarker(data[i]);    
            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }       

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);
    } 
}

// 지도에 마커를 표시하는 함수입니다
function displayMarker(place) {
    
    // 마커를 생성하고 지도에 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x) 
    });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
        infowindow.open(map, marker);
    });
}

</script>
  
<%-- <%@ include file = "footer.jsp" %> --%>
<c:import url = "footer.jsp"/>