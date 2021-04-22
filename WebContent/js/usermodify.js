document.write("<script src='signup.js'></script>");

//회원정보 수정(메인에서 수정페이지로 이동)
const headeruserinfoBtn = document.getElementById("headeruserinfoBtn");

function userInfo(){
	const userinfoURL = root + "/user?act=userInfo";
	location.href = userinfoURL;
}
headeruserinfoBtn.addEventListener("click", userInfo);

//회원정보 수정페이지 : 뒤로가기
const modifyCancelBtn = document.getElementById("modifyCancelBtn");
function getBack() {
	history.back();
}
modifyCancelBtn.addEventListener("click", getBack);


const modifyId = document.getElementById("modifyId");
const originpass = document.getElementById("originPassword");
const newpass = document.getElementById("modifyPassword");
const newpasscheck = document.getElementById("modifyPasswordCheck");
const modifyName = document.getElementById("modifyName");
const modifyEmail = document.getElementById("modifyEmail");
const modifyAddress = document.getElementById("modifyAddress");
const modifybtn = document.getElementById("modifyBtn");

//password 검사
let newpassCk = false;
function equalCheck() {
	if(newpass.value != newpasscheck.value) {
		newpasscheck.style.boxShadow = "0 0 0 0.25rem #dc3545";
		newpassCk = false;
	}
	else {
		newpasscheck.style.boxShadow = "#1faa69 0px 0px 0px 0.25rem";
		newpassCk = true;
	}
	
	if(newpassCk)
		modifybtn.disabled = false;
	else
		modifybtn.disabled = true;
	
}
newpasscheck.addEventListener("input", equalCheck);
newpass.addEventListener("input", equalCheck);


//회원정보 수정
function modify(){
	const modifyURL = root + "/user?act=modifyUser";

	const modifyDataJson = {
			modifyId : modifyId.value,
			originpass : originpass.value,
			newpass : newpass.value,
			newpasscheck : newpasscheck.value,
			modifyName : modifyName.value,
			modifyEmail : modifyEmail.value,
			modifyAddress : modifyAddress.value
	};
	
	//origin pw가 맞는지 -> 
	fetch(modifyURL, {
		method : 'POST', 
		body : JSON.stringify(modifyDataJson),
		headers : {
			'Content-Type' : 'application/json'
		}
	}).then((response)=>{
		return response.text();
	}).then((text)=>{
		if(text.trim() == "success"){ //pw맞음 && 회원 정보 수정 완료
			if(confirm("회원정보 수정 완료\n다시 로그인 하여 이용하세요.")) 
				document.location = root;
		}
		else if(text.trim() == "WrongPass"){ //pw틀림
			alert("틀린 패스워드 입니다.");
		}
		else{ // 그냥 수정 실패
			alert("회원정보 수정에 실패하였습니다.");
		}
	});
	
}
modifybtn.addEventListener("click", modify);


//회원 탈퇴
const withdrawbtn = document.getElementById("withdrawBtn");
function withdraw(){
	const withdrawURL = root + "/user?act=withdrawUser"
	const withdrawDataJson = {
			modifyId : modifyId.value,
			originpass : originpass.value,
	};
	
	//origin pw가 맞는지
	fetch(withdrawURL, {
		method : 'POST', 
		body : JSON.stringify(withdrawDataJson),
		headers : {
			'Content-Type' : 'application/json'
		}
	}).then((response)=>{
		return response.text();
	}).then((text)=>{
		if(text.trim() == "success"){ 
			if(confirm(modifyId.value+"("+ modifyName.value +")"+ "님,\n회원 탈퇴가 완료되었습니다.")) 
				document.location = root;
		}
		else{
			alert("회원탈퇴에 실패 했습니다.");
		}
	});
}
withdrawbtn.addEventListener("click", withdraw);


