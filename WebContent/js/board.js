const pagebtnlist = document.querySelectorAll("#boardPagination > li");


function setNumberPagebtn(page) {
	const pageCount = parseInt(document.getElementById("boardPageCount").innerText);
	
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


function boardListByPageNum(e) {
	const pageCount = parseInt(document.getElementById("boardPageCount").innerText);
	const page = e.currentTarget.innerText;
	const url = root + "/posting?act=boardList&page=" + page;
	fetch(url).then((response)=>{
		return response.text();
	}).then((text)=>{
		const boardListJSON = JSON.parse(text);
		const boardListBody = document.querySelector("#boardListBody");
		boardListBody.innerHTML ="";
		for(let i=0; i<boardListJSON.length; ++i) {
			
			boardListBody.innerHTML += `<tr class="board">
									     	<th scope="row" >` + boardListJSON[i].idx + `</th>
									     	<td>`+ boardListJSON[i].boardTitle + `</td>
									     	<td>`+ boardListJSON[i].boardWriter + `</td>
									     	<td>`+ boardListJSON[i].boardDate + `</td>
									     </tr>`;
		}
		
		const boardtr = document.querySelectorAll(".board");
		for (let i=0; i<boardtr.length; ++i) {
			boardtr[i].addEventListener("click", getBoard);
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

boardListByPageNum({
	currentTarget:{
		innerText:1
	}
});

for (let i=1; i<6; i++) {
	pagebtnlist[i].addEventListener("click", boardListByPageNum);
}

function movePageNumLeft() {
	let nowPage;
	for(let i=1; i<6; i++) {
		if(pagebtnlist[i].classList.contains("active")) {
			nowPage = parseInt(pagebtnlist[i].querySelector("a").innerHTML);
			break;
		}
	}
	
	boardListByPageNum({
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
	
	boardListByPageNum({
		currentTarget:{
			innerText:nowPage+1
		}
	});
}

pagebtnlist[0].addEventListener("click", movePageNumLeft);
pagebtnlist[6].addEventListener("click", movePageNumRight);

function getBoard(e) {
	const idx = e.currentTarget.children[0].innerText;
	const url = root + "/posting?act=board&idx=" + idx;
	location.href = url;
}

//글 작성 페이지 이동
function writing() {
	const url = root + "/posting?act=boardWrite";
	location.href = url;
}

const writebtn = document.getElementById("writebtn");
writebtn.addEventListener("click", writing);

