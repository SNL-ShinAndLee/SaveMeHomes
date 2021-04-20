let newpassCk = false;

//password 검사
const newpass = document.getElementById("modifyPassword");
const newpasscheck = document.getElementById("modifyPasswordCheck");
const modifybtn = document.getElementById("modifyBtn");
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