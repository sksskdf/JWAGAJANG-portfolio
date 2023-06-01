function execDaumPostcode() {
	new daum.Postcode({
	oncomplete: function(data) {
		var totalAddr = ''; //주소 + 참고항목
		var addr = ''; // 주소 변수
    	var extraAddr = ''; // 참고항목 변수
    	
    	//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
        if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
            addr = data.roadAddress;
        } else { // 사용자가 지번 주소를 선택했을 경우(J)
            addr = data.jibunAddress;
        }
			
        // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
        if(data.userSelectedType === 'R'){
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
                extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraAddr !== ''){
                extraAddr = ' (' + extraAddr + ')';
            }
            // 본 주소와 참고항목을 합친다.
            totalAddr = addr + extraAddr;
        
        } else {
        	totalAddr = addr;
        }
     	// 우편번호와 주소 정보를 해당 필드에 넣는다.
        document.getElementById('postnum').value = data.zonecode;
        document.getElementById("address").value = totalAddr;
        // 커서를 상세주소 필드로 이동한다.
        document.getElementById("addDetail").focus();
	}
	}).open();
}

$(function() {
	var idFlag = false;
	var pw1Flag = false;
	var pw2Flag = false;
	var passId = false;
	

	$("#id").on("keyup", function(e) {
		//정규표현식 알파벳 대소문자 상관없이 숫자는 0~9까지 조합한 6자 이상 20자 이하의 문자열
		var regEx = /^[a-zA-Z0-9]{6,20}$/;
		var idVal = $("#id").val();
		if(idVal.length > 0 && !regEx.test(idVal)) {
			$("#id").css("color", "red");
			$("#idcheck").text("6자 이상 20자 이하의 아이디를 입력해주세요!");
			idFlag = false;
		} else {
			$("#id").css("color", "blue");
			$("#idcheck").text("");
			idFlag = true;
		}
		if(idVal.length == 0){
			$("#idcheck").text("");
		}
	});
	
	$("#pwd").on("keyup", function(e) {
		var regEx = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
		var pwVal = $("#pwd").val();
		if(pwVal.length > 0 && !regEx.test(pwVal)) {
			$("#pwd").css("color", "red");
			$("#pwdcheck").text("8자 이상 20자 이하의 영문,특수문자,숫자의 조합")
			pw1Flag = false;
		} else {
			$("#pwd").css("color", "blue");
			$("#pwdcheck").text("");
			pw1Flag = true;
		}
		if(pwVal.length == 0){
			$("#pwdcheck").text("");
		}
	});
	
	$("#pwd2").on("keyup", function(e) {
		var regEx = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
		var pwVaal = $("#pwd").val();
		var pwVal = $("#pwd2").val();
		if(pwVal != pwVaal || !regEx.test(pwVal) || pwVal.length != pwVaal.length) {
			$("#pwd2").css("color", "red");
			$("#pwd2check").text("입력하신 비밀번호와 다르거나 비밀번호 작성조건에 맞지 않습니다!")
			pw2Flag = false;
		} else if(pwVaal == pwVal){
			$("#pwd2").css("color", "blue");
			$("#pwd2check").text("");
			pw2Flag = true;
		}
		if(pwVal.length == 0){
			$("#pwd2check").text("");
		}
		
		
	});
	

	
	$("#duplicateCheck").on("click", function(e) {
		var id = $("#id").val();
		
		if(id.length == 0) {
			alert("아이디를 입력해 주세요!");
			return;
		}
		if(idFlag == false) {
			alert("아이디를 올바르게 입력해 주세요!");
			return;
		}
		var query = { id: id };
		$.ajax({
			type: "POST",
			url: "/idCheck.do",
			data: query,
			success: function(data) {
				if(data == "using") {	// id 존재
					alert("이미 존재하는 아이디 입니다.");
					passId = false;
					$("#id").css("color", "red");
					$("#idcheck").text("이미 존재하는 아이디 입니다!");
				}
				else {
					alert("사용 가능한 아이디 입니다.");
					passId = true;
				}
			}
		});
	});
	
	var joinForm = $("#joinForm");
	$("#submit").on("click", function(e) {
		if(checkInputValidity() == false) {
			e.preventDefault();
			return false;
		}
		joinForm.submit();
	});

	
	function checkInputValidity() {
		if($("#id").val().length == 0) {
			alert("아이디를 입력해 주세요!");
			$("#id").focus();
			return false;
		}
		if(idFlag == false) {
			alert("아이디를 작성 정책에 맞게 입력해 주세요!");
			$("#id").focus();
			return false;
		}
		if(passId == false) {
			alert("아이디 중복검사를 진행해 주세요!");
			return false;
		}
		if($("#pwd").val().length == 0) {
			alert("비밀번호를 입력해 주세요!");
			$("#pwd1").focus();
			return false;
		}
		if(pw1Flag == false) {
			alert("8자 이상 20자 이하의 영문,특수문자,숫자의 조합");
			$("#pwd1").focus();
			return false;
		}
		if($("#pwd2").val().length == 0) {
			alert("비밀번호확인을 입력해 주세요!");
			$("#pwd2").focus();
			return false;
		}
		if(pw2Flag == false) {
			alert("비밀번호를 확인해주세요!");
			$("#pwd2").focus();
			return false;
		}
		if($("#pwd").val() != $("#pwd2").val()) {
			alert("비밀번호가 일치하지 않습니다. 비밀번호를 다시 입력해 주세요!");
			$("#pwd2").focus();
			return false;
		}
		
		return true;
	}
});

