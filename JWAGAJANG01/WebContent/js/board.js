$(function() {
	var form = $(".reply");
	$("input[name='register']").on("click", function(e) {
		form.append("<input type='hidden' name='type' value='register'>");
		form.submit();
	});
	/* 클릭을 했을 때 이벤트 : hidden input 생성 */
	$("input[name='remove']").on("click", function(e) {
		form.append("<input type='hidden' name='type' value='remove'>");
		form.submit();
	});
});

function noticeCheck() {
	if (document.frm.notice_label.value.length == 0) {
	alert("분류를 선택하세요.");
	return false;
	}
	if (document.frm.notice_title.value.length == 0) {
	alert("제목을 입력하세요.");
	return false;
	}
	if (document.frm.notice_content.value.length == 0) {
	alert("내용을 입력하세요.");
	return false;
	}
	return true;
}

function searchCheck(){
	if (document.searchfrm.searchkeyword.value.length == 0) {
	alert("검색어를 입력하세요.");
	return false;
	}
	return true;
}

function qnaCheck() {
	if (document.qnafrm.qna_title.value.length == 0) {
	alert("제목을 입력하세요.");
	return false;
	}
	if (document.qnafrm.qna_content.value.length == 0) {
	alert("내용을 입력하세요.");
	return false;
	}
	return true;
}


function qnasearchCheck(){
	if (document.srcfrm.searchkeyword.value.length == 0) {
	alert("검색어를 입력하세요.");
	return false;
	}
	return true;
}


function replyDelete() {
			alert("답변이 삭제되었습니다.");
}
