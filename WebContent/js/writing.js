//뒤로가기
function getBack() {
	history.back();
}

const listbtn = document.getElementById("listbtn");
listbtn.addEventListener("click", getBack);

// 공지 or 게시판
function searchParam(key) {
	return new URLSearchParams(location.search).get(key);
}

function selectOne() {
	const act = searchParam("act");
	if(act == "noticeWrite")
	{
		document.getElementById("selectOne").innerHTML = "공지사항 작성";	
	}
	else
	{
		document.getElementById("selectOne").innerHTML = "게시물 작성";
	}
}
selectOne();
