const headersignoutBtn = document.getElementById("headersignoutBtn");

function signOut(){
	const url = root + "/user?act=signout";
	console.log(url);
	location.href = url;
}
headersignoutBtn.addEventListener("click", signOut);