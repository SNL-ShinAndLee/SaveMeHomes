<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  
  
