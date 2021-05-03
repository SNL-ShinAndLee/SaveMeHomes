var geocoder = new kakao.maps.services.Geocoder();

window.onload = function() {
	const detailModal = new bootstrap.Modal(document.getElementById('detailViewModal'));
	const dongCode = searchParam("citycode");
	
	const url = root + "/city?act=homesinfo&dongcode=" + dongCode;
	
	fetch(url).then((response)=>{
		return response.text();
	}).then((text)=>{
		const homeInfoBody = document.querySelector("#homeInfoBody");
		if(!text){
			homeInfoBody.innerHTML = "<div class=\"text-center fs-3 mt-5\">검색 결과가 존재하지않습니다.</div>"
			return;
		}
		const homesinfoListJSON = JSON.parse(text);
		
		homeInfoBody.innerHTML ="";
		let homeInfoList = "";
		let bounds = new kakao.maps.LatLngBounds();
		for(let i=0; i<homesinfoListJSON.length;){
			homeInfoList += `<div class="row my-3">`;
			let cnt = 3;
			while(cnt-->0 && i<homesinfoListJSON.length) {
				let address = homesinfoListJSON[i].dong + " " + homesinfoListJSON[i].lotNum;
				let apartname = homesinfoListJSON[i].apartName;
				const homeinfo = homesinfoListJSON[i];
				geocoder.addressSearch(address, function (result, status) {
				    // 정상적으로 검색이 완료됐으면
				     if (status === kakao.maps.services.Status.OK) {
				        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

				        // 결과값으로 받은 위치를 마커로 표시합니다
				        var marker = new kakao.maps.Marker({
				            map: map,
				            position: coords
				        });
				        
				        // 마커에 마우스오버 하면 장소명을 표출할 인포윈도우
				        var infowindow = new kakao.maps.InfoWindow({zIndex:1});

				        kakao.maps.event.addListener(marker, 'mouseover', function() {
				            infowindow.setContent('<div class="text-center" style="padding:5px;">' + apartname + '</div>');
				            infowindow.open(map, marker);
				        });
				        kakao.maps.event.addListener(marker, 'mouseout', function() {
				            infowindow.close();
				        });

				        // 마커에 클릭이벤트를 등록합니다
				        kakao.maps.event.addListener(marker, 'click', function() {
				        	document.getElementById("detailViewModalLabel").innerHTML = apartname;
				        	
				        	var modalChild = document.querySelector("#detailView");
				        	modalChild.innerHTML = "";
				        	
			        		modalChild.innerHTML += ("<p>가격 : " + homeinfo.dealAmount.toLocaleString('ko-KR') + "&nbsp;(만원)</p>");
			        		modalChild.innerHTML += ("<p>건축년도 : " + homeinfo.buildYear + "</p>");
			        		modalChild.innerHTML += ("<p>거래일 : " + homeinfo.dealDate + "</p>");
			        		modalChild.innerHTML += ("<p>전용면적 : " + homeinfo.dedicatedArea  + "&nbsp;(m²)</p>");
			        		modalChild.innerHTML += ("<p>대지면적 : " + homeinfo.landArea  + "&nbsp;(m²)</p>");
			        		modalChild.innerHTML += ("<span>주소 : " + homeinfo.dong + "&nbsp;" + homeinfo.lotNum + "&nbsp;" +  homeinfo.floor + "층&nbsp;</span>");
			        		
			        		detailModal.show();
			        		
				        });

				        bounds.extend(coords);
				        map.setBounds(bounds);
				    } 
				});    
				homeInfoList += 
				`<div class="col-4">
			      <div class="card text-center">
			        <div class="card-body">
			          <h5 id="apartName" class="card-title">` + homesinfoListJSON[i].apartName + `</h5>
			          <p id="dealAmount" class="card-text"> 가격 : ` + homesinfoListJSON[i].dealAmount.toLocaleString('ko-KR') + `&nbsp;(만원)</p>
			          <p id="buildYear" class="card-text" style="display:none;"> 건축년도 : ` + homesinfoListJSON[i].buildYear + `</p>
			          <p id="dealDate" class="card-text"> 거래일 : ` + homesinfoListJSON[i].dealDate + `</p>
			          <p id="dedicatedArea" class="card-text"> 전용면적 : ` + homesinfoListJSON[i].dedicatedArea + `&nbsp;(m²)</p>
			          <p id="landArea" class="card-text" style="display:none;"> 대지면적 : ` + homesinfoListJSON[i].landArea + `&nbsp;(m²)</p>
			          <p id="dong" class="card-text" style="display:none;"> 주소 : ` + homesinfoListJSON[i].dong + `</p>
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

/* 아파트 상세보기 */
function detailView(e){
	var cur = e.currentTarget;
	var par = cur.parentNode;
	
	document.getElementById("detailViewModalLabel").innerHTML = par.querySelector(".card-title").innerText;
	
	var children = par.querySelectorAll(".card-text");
	var modalChild = document.querySelector("#detailView");
	modalChild.innerHTML = "";
	for(let i=0; i < children.length-1 ; ++i){
		if(i<5) modalChild.innerHTML += ("<p>" + children[i].innerText + "</p>");
		else modalChild.innerHTML += ("<span>" + children[i].innerText + "&nbsp;</span>");
	}
}

/* 드롭다운 데려오기 */
function getDropdownData(){
	const url = root + "/city?act=dropdowndata&citycode=" + searchParam("citycode");

	fetch(url).then((response)=>{
		return response.text();
	}).then((text)=>{
		const cityJSON = JSON.parse(text);
    
		const sidobtn = document.querySelector("#sidoBtn");
		sidobtn.innerHTML = cityJSON.citySido + "<span class=\"d-none\">" + parseInt(cityJSON.cityCode/100000000)*100000000 + "</span>";
		sidoSelect({
			currentTarget : sidobtn
		});

		const gugunbtn = document.querySelector("#gugunBtn");
		gugunbtn.innerHTML = cityJSON.cityGugun + "<span class=\"d-none\">" + parseInt(cityJSON.cityCode/100000)*100000 + "</span>";
		gugunSelect({
			currentTarget : gugunbtn
		})
    
		const dongbtn = document.querySelector("#dongBtn");
		dongbtn.innerHTML = cityJSON.cityDong + "<span class=\"d-none\">" + cityJSON.cityCode + "</span>";
	})
}
getDropdownData();

