function checkPw() {
	var p1 = document.getElementsByName("password");
	var p2 = document.getElementsByName("passwordChk");
	if(p1 == p2){
		alert("비밀번호 확인.")
		}
	else {
		alert("비밀번호를 확인해주세요.");
	}
}
