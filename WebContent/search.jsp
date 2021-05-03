<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "com.snl.savemehomes.constants.Constants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <%@ include file = "header.jsp" %>--%>
<c:import url = "header.jsp"/>

<!-- google map -->
<div class="">
<c:import url = "cityMap.jsp"/>
 
<div id="homeInfoBody" class="container my-3 overflow-scroll" style="height: 45vh;">
  
    
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
      </div>
    </div>
  </div>
</div>
</div>


<!-- kakao map불러오기 -->
<%-- <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=<%=Constants.getMapAPIKey()%>"></script> --%>
<!-- services 라이브러리 불러오기 -->

  
<%-- <%@ include file = "footer.jsp" %> --%>
<script src="${pageContext.request.contextPath}/js/homesinfo.js"></script>
<c:import url = "footer.jsp"/>