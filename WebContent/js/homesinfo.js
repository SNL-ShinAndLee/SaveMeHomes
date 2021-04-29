window.onload = function() {
	const dongCode = searchParam("citycode");
	
	const url = root + "/city?act=homesinfo&dongcode=" + dongCode;
	
	fetch(url).then((response)=>{
		return response.text();
	}).then((text)=>{
		const homesinfoListJSON = JSON.parse(text);
		const homeInfoBody = document.querySelector("#homeInfoBody");
		
		homeInfoBody.innerHTML ="";
		let homeInfoList = "";
		for(let i=0; i<homesinfoListJSON.length;){
			homeInfoList += `<div class="row my-3">`;
			let cnt = 3;
			while(cnt-->0 && i<homesinfoListJSON.length) {
				let address = homesinfoListJSON[i].dong + " " + homesinfoListJSON[i].lotNum;
//				ps.keywordSearch(address, placesSearchCB);
				let apartname = homesinfoListJSON[i].apartName;
				geocoder.addressSearch(address, function (result, status) {

				    // 정상적으로 검색이 완료됐으면 
				     if (status === kakao.maps.services.Status.OK) {

				        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

				        // 결과값으로 받은 위치를 마커로 표시합니다
				        var marker = new kakao.maps.Marker({
				            map: map,
				            position: coords
				        });

				        // 인포윈도우로 장소에 대한 설명을 표시합니다
				        var infowindow = new kakao.maps.InfoWindow({
				            content: '<div style="width:150px;text-align:center;padding:6px 0;">' + apartname + '</div>'
				        });
				        infowindow.open(map, marker);

				        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				        map.setCenter(coords);
				    } 
				});    
				
				homeInfoList += 
				`<div class="col-4">
			      <div class="card text-center">
			        <div class="card-body">
			          <h5 id="apartName" class="card-title">` + homesinfoListJSON[i].apartName + `</h5>
			          <p id="dealAmount" class="card-text"> 가격 : ` + homesinfoListJSON[i].dealAmount + `</p>
			          <p id="buildYear" class="card-text" style="display:none;"> 건축년도 : ` + homesinfoListJSON[i].buildYear + `</p>
			          <p id="dealDate" class="card-text"> 거래일 : ` + homesinfoListJSON[i].dealDate + `</p>
			          <p id="dedicatedArea" class="card-text"> 전용면적 : ` + homesinfoListJSON[i].dedicatedArea + `</p>
			          <p id="landArea" class="card-text" style="display:none;"> 대지면적 : ` + homesinfoListJSON[i].landArea + `</p>
			          <p id="dong" class="card-text" style="display:none;">` + homesinfoListJSON[i].dong + `</p>
			          <p id="lotNum" class="card-text" style="display:none;">` + homesinfoListJSON[i].lotNum + `</p>
			          <p id="floor" class="card-text" style="display:none;">` + homesinfoListJSON[i].floor + `층</p>
			          <p id="cityCode" class="card-text" style="display:none;"> 지역코드 : ` + homesinfoListJSON[i].cityCode + `</p>
			          <button type="button" class="btn btn-primary cardBtn" data-bs-toggle="modal" data-bs-target="#detailViewModal">
			                            상세보기
			          </button>
			        </div>
			      </div>
			    </div>`;
				i++;
			}
			homeInfoList += `</div>`;
		}
		homeInfoBody.innerHTML = homeInfoList;
		const cardBtn = document.getElementsByClassName("cardBtn");
		for (let i=0; i<cardBtn.length; ++i)
		{
			cardBtn[i].addEventListener("click", detailView);
		}
		
	})
}

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


