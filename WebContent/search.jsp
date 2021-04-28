<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "com.snl.savemehomes.constants.Constants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <%@ include file = "header.jsp" %>--%>
<c:import url = "header.jsp"/>

<!-- google map -->
<div class="">
<c:import url = "cityDropdown.jsp"/>
 
<div class="container my-3">
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

  
<%-- <%@ include file = "footer.jsp" %> --%>
<script src="${pageContext.request.contextPath}/js/readapartapi.js">
<!--

//-->
</script>
<c:import url = "footer.jsp"/>