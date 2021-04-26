const sidoBtn = document.querySelector("#sidoBtn");
const gugunBtn = document.querySelector("#gugunBtn");
const dongBtn = document.querySelector("#dongBtn");

const sidoListBody = document.querySelector("#sido");
const gugunListBody = document.querySelector("#gugun");
const dongListBody = document.querySelector("#dong");

function getSidoList(){
  const url = root + "/city?act=sidolist";
  
  fetch(url).then((response)=>{
	  return response.text();
  }).then((text)=>{
	  const sidoListJSON = JSON.parse(text);
	  sidoListBody.innerHTML ="";
	  for(let i=0; i<sidoListJSON.length; ++i) {
		  sidoListBody.innerHTML += `<li class="dropdown-item">` + sidoListJSON[i].citySido 
		  								+ `<span class="d-none">`+sidoListJSON[i].cityCode+`</span>`
		  						  + `</li>`;
	  }
	  
	  // 시도 선택
	  const sidoSelectList = sidoListBody.querySelectorAll("li");
	  for(let i = 0 ; i < sidoSelectList.length; ++i){
		  sidoSelectList[i].addEventListener("click", sidoSelect);
	  }  
  })
  
}
sidoBtn.addEventListener("click", getSidoList);

function sidoSelect(e){
	const selectedSido = e.currentTarget;
	sidoBtn.innerHTML = selectedSido.innerHTML; 
	
	const sidoCode = selectedSido.querySelector("span").innerText;
	const url = root + "/city?act=gugunlist&citycode=" + sidoCode;
		  
	fetch(url).then((response)=>{
		return response.text();
	}).then((text)=>{
		const gugunListJSON = JSON.parse(text);
	  
		gugunListBody.innerHTML ="";
		for(let i=0; i<gugunListJSON.length; ++i){
			console.log(gugunListJSON[i]);
			gugunListBody.innerHTML += `<li class="dropdown-item">` + gugunListJSON[i].cityGugun 
										+ `<span class="d-none">` +gugunListJSON[i].cityCode + `</span>`
									+ `</li>`;
		}
		// 구군 선택
		const gugunSelectList = gugunListBody.querySelectorAll("li");
		for(let i = 0 ; i < gugunSelectList.length; ++i){
			gugunSelectList[i].addEventListener("click", gugunSelect);
		}  
	})
}

function gugunSelect(e){
	const selectedGugun = e.currentTarget;
	gugunBtn.innerHTML = selectedGugun.innerHTML; 
	
	const gugunCode = selectedGugun.querySelector("span").innerText;
	const url = root + "/city?act=donglist&citycode=" + gugunCode;
		  
	fetch(url).then((response)=>{
		return response.text();
	}).then((text)=>{
		const dongListJSON = JSON.parse(text);
	  
		dongListBody.innerHTML ="";
		for(let i=0; i<dongListJSON.length; ++i){
			dongListBody.innerHTML += `<li class="dropdown-item">` + dongListJSON[i].cityDong 
										+ `<span class="d-none">` + dongListJSON[i].cityCode + `</span>`
									+ `</li>`;
		}
		// 동 선택
		const dongSelectList = dongListBody.querySelectorAll("li");
		for(let i = 0 ; i < dongSelectList.length; ++i){
			dongSelectList[i].addEventListener("click", dongSelect);
		} 
	})
}
function dongSelect(e){
	const selectedDong = e.currentTarget;
	dongBtn.innerHTML = selectedDong.innerHTML; 
	console.log(selectedDong);
	
	const dongCode = selectedGugun.querySelector("span").innerText;
	const url = root + "/city?act=citysearch&citycode=" + dongCode;
	
	fetch(url).then((response)=>{
		return response.text();
	}).then((text)=>{
		//
	})
}