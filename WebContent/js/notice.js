const pagebtnlist = document.querySelectorAll("#noticePagination > li");


function setNumberPagebtn(page) {
	const pageCount = parseInt(document.getElementById("noticePageCount").innerText);

	if(pageCount < 5 || page <= 3){
	    for(let i = 1 ;i <= 5; ++i){
	        if(i > pageCount) pagebtnlist[i].classList.add("d-none");
	        else{
	            pagebtnlist[i].classList.remove("d-none");
	            pagebtnlist[i].querySelector("a").innerHTML = i;
	        } 
	    }
	}
	else if(page < pageCount-2){
	    for(let i = 1, j = -2; i <= 5; ++i, ++j){
	        pagebtnlist[i].classList.remove("d-none");
	        pagebtnlist[i].querySelector("a").innerHTML = parseInt(page)+j;
	    } 
	}
	else{
	    for(let i = 1,j = pageCount-4; i <= 5 ; ++i,++j){
	        pagebtnlist[i].classList.remove("d-none");
	        pagebtnlist[i].querySelector("a").innerHTML = j;
	    }
	}
	
}

setNumberPagebtn(1);


function noticeListByPageNum(e) {
	const pageCount = parseInt(document.getElementById("noticePageCount").innerText);
	const page = e.currentTarget.innerText;
	const url = root + "/posting?act=noticeList&page=" + page;
	fetch(url).then((response)=>{
		return response.text();
	}).then((text)=>{
		const noticeListJSON = JSON.parse(text);
		const noticeListBody = document.querySelector("#noticeListBody");
		noticeListBody.innerHTML ="";
		for(let i=0; i<noticeListJSON.length; ++i) {
			
			noticeListBody.innerHTML += `<tr class="notice">
									     	<th scope="row">` + noticeListJSON[i].idx + `</th>
									     	<td>`+ noticeListJSON[i].noticeTitle + `</td>
									     	<td>`+ noticeListJSON[i].noticeDate + `</td>
									     </tr>`;
		}
		
		const noticetr = document.querySelectorAll(".notice");
		for (let i=0; i<noticetr.length; ++i) {
			noticetr[i].addEventListener("click", getNotice);
		}
		
		setNumberPagebtn(page);
		
		for(let i=1; i<6; ++i) {
			if(pagebtnlist[i].querySelector("a").innerHTML == page) {
				pagebtnlist[i].classList.add("active");
			}
			else {
				pagebtnlist[i].classList.remove("active");
			}
			
		}
		
		if(page==1) {
			pagebtnlist[0].classList.add("disabled");
			pagebtnlist[0].querySelector("a").setAttribute("aria-disabled", true);
		}
		else {
			pagebtnlist[0].classList.remove("disabled");
			pagebtnlist[0].querySelector("a").setAttribute("aria-disabled", false);
		}
		console.log(page);
		console.log(pageCount);
		if(page==pageCount) {
			pagebtnlist[6].classList.add("disabled");
			pagebtnlist[6].querySelector("a").setAttribute("aria-disabled", true);
		}
		else {
			pagebtnlist[6].classList.remove("disabled");
			pagebtnlist[6].querySelector("a").setAttribute("aria-disabled", false);
		}
		
	});
	
}

noticeListByPageNum({
	currentTarget:{
		innerText:1
	}
});

for (let i=1; i<6; i++) {
	pagebtnlist[i].addEventListener("click", noticeListByPageNum);
}

function movePageNumLeft() {
	let nowPage;
	for(let i=1; i<6; i++) {
		if(pagebtnlist[i].classList.contains("active")) {
			nowPage = parseInt(pagebtnlist[i].querySelector("a").innerHTML);
			break;
		}
	}
	
	noticeListByPageNum({
		currentTarget:{
			innerText:nowPage-1
		}
	});
}

function movePageNumRight() {
	let nowPage;
	for(let i=1; i<6; i++) {
		if(pagebtnlist[i].classList.contains("active")) {
			nowPage = parseInt(pagebtnlist[i].querySelector("a").innerHTML);
			break;
		}
	}
	
	noticeListByPageNum({
		currentTarget:{
			innerText:nowPage+1
		}
	});
}

pagebtnlist[0].addEventListener("click", movePageNumLeft);
pagebtnlist[6].addEventListener("click", movePageNumRight);

function getNotice(e) {
	const idx = e.currentTarget.children[0].innerText;
	const url = root + "/posting?act=notice&idx=" + idx;
	location.href = url;
}

//??? ?????? ????????? ??????
function writing() {
	const url = root + "/posting?act=noticeWrite";
	location.href = url;
}

const writebtn = document.getElementById("writebtn");
writebtn.addEventListener("click", writing);