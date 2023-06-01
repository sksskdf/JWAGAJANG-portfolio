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


$('#checkbox').click(function(){
	var checked = $('#checkbox').is(':checked');
	
	if(checked){
		$('input:checkbox').prop('checked',true);
		
		$("#name").val($("input[name='order_name']").val());
		$("#phone").val($("input[name='order_mobile']").val());
		$("#address").val($("input[name='order_address']").val());
		$("#address2").val($("input[name='order_address2']").val());
	}
});

function orderCheck(e) {
	const nameReg = /^[가-힣]{2,5}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/;      //한글 2~5자 혹은 영어이름.
	const telReg = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/; 
	//e.priventDefault();
	
	if(document.orderfrm.name.value.length == 0) { 
		alert("수령인을 입력하세요.");
		document.orderfrm.name.focus();
		return false;
	}
	if(!nameReg.test(document.orderfrm.name.value)) { 
		alert("이름을 올바르게 입력하세요.");
		document.orderfrm.name.focus();
		return false;
	}
	if(document.orderfrm.phone.value.length == 0) {
		alert("전화번호를 입력하세요.");
		document.orderfrm.phone.focus();
		return false;
	}
	if(!telReg.test(document.orderfrm.phone.value)) { 
		alert("전화번호를 올바르게 입력하세요.");
		document.orderfrm.phone.focus();
		return false;
	}
	if(document.orderfrm.postnum.value.length == 0 || document.orderfrm.address.value.length == 0) {
		alert("'우편번호 찾기'를 통해 주소를 입력하세요.");
		return false;
	}
	if(document.orderfrm.addDetail.value.length == 0) {
		alert("상세주소를 입력하세요.");
		document.orderfrm.addDetail.focus();
		return false;
	}
	document.orderfrm.submit();
	return true;
}