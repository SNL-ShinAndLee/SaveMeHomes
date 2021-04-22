let idCk = false;
let passCk = false;

function getContextPath() {
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
}
const root = getContextPath();

// id 중복 확인
const duptoastEl = document.querySelector("#dupToast");
const duptoast = new bootstrap.Toast(duptoastEl, {autohide:false});
const dupbtn = document.getElementById("dupBtn");
const signupbtn = document.getElementById("signUpBtn");
function duplicationCheck() {
	const signupid = document.getElementById("signUpId");
	const dupcontent = document.getElementById("dupContent");
	duptoastEl.classList.remove("bg-danger");
	duptoastEl.classList.remove("bg-primary");
	duptoastEl.classList.remove("d-none");
	if(signupid.value == "") {
		dupcontent.innerHTML = "아이디를 입력하세요";
		duptoastEl.classList.add("bg-danger");
		duptoast.show();
		idCk = false;
	}
	else{
		//중복체크
		const root = getContextPath();
		const idDuplicationURL = root + "/user?act=idDuplication&signupid="+signupid.value;
		console.log(idDuplicationURL);
		console.log(signupid.value);
		fetch(idDuplicationURL).then((response)=>{
			return response.text();
		}).then((text)=>{
			if(text.trim() == "true"){
				dupcontent.innerHTML = "중복된 아이디입니다";
				duptoastEl.classList.add("bg-danger");
				duptoast.show();
				idCk = false;
			}
			else{				
				dupcontent.innerHTML = "사용할 수 있는 아이디입니다";
				duptoastEl.classList.add("bg-primary");
				duptoast.show();
				signupid.readOnly = true;
				dupbtn.disabled = true;
				idCk = true;
			}
		});
				
			/*	{
			,method : "POST", 
			body : JSON.stringify({
				"signupid" : signupid.value
			}),
			headers : {
				"Content-Type" : "application/json"
			}
		})*/
		
	}
	if(idCk && passCk)
		signupbtn.disabled = false;
	else
		signupbtn.disabled = true;
}
dupbtn.addEventListener("click", duplicationCheck);

//password 검사
const passorigin = document.getElementById("signUpPassword");
const passcheck = document.getElementById("signUpPasswordCheck");

function equalCheck() {
	if(passorigin.value != passcheck.value) {
		passcheck.style.boxShadow = "0 0 0 0.25rem #dc3545";
		passCk = false;
	}
	else {
		passcheck.style.boxShadow = "#1faa69 0px 0px 0px 0.25rem";
		passCk = true;
	}
	
	if(idCk && passCk)
		signupbtn.disabled = false;
	else
		signupbtn.disabled = true;
	
}
passcheck.addEventListener("input", equalCheck);
passorigin.addEventListener("input", equalCheck);


