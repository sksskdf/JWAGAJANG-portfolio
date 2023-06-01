//전체 체크 및 일부 체크 해제 시 전체체크 해제되는 메소드
$(function() {
	$("#allchk").click(function() {
		if($("#allchk").is(":checked")) {
			$("input[name=mdchk]").prop("checked", true);
			totalSum();
		}
		
		else {
			$("input[name=mdchk]").prop("checked", false);
			totalSum();
		}
	});
	
	$("input[name=mdchk]").click(function() {
		var total = $("input[name=mdchk]").length;
		var checked = $("input[name=mdchk]:checked").length;
		
		if(total != checked){
			$("#allchk").prop("checked", false);
			totalSum();
		}
		else {
			$("#allchk").prop("checked", true); 
			totalSum();
		}
	});
	
	//주문금액이 3만원이 넘을 시 배송비 0원으로 만드는 메소드
	//if($("#"))
	
});
//금액 총 합계.
function totalSum() {
    var str = "";
    var sum = 0;
    var count = $(".mdchk").length;
    for (var i = 0; i < count; i++) {
        if ($(".mdchk")[i].checked == true) {
            sum += parseInt($(".mdchk")[i].value);
        }
    }
    $("#totalOrderPrice").html(sum);
}

//선택한 항목 삭제
$("#delete_select").click(function () {
	 var checkArr = new Array();

     $("input[name='mdchk']:checked").each(function () {
         checkArr.push($(this).attr("data-cartcode"));
     });
     
     if(checkArr.length==0) alert("선택된 상품이 없습니다.");
	    else {
		    var confirm_val = confirm("선택한 상품을 삭제하시겠습니까?");
		    if (confirm_val) {
		        $.ajax({
		            url: "cartSelectDelete.do",
		            type: "POST",
		            data: { "checkArr": checkArr },
		            success: function (data) {
		            	alert('삭제하였습니다.');
		                location.replace("cartPut.do");
		            },
		            error: function(request,status,error) {
		        		alert("code = "+ request.status + " message = " + request.responseText + " error = " + error);
		        	}
		        });
		    }
	    }
});
//form없이 POST 방식으로 전송하기.
/* 
* path : 전송 URL 
* params : 전송 데이터 {'q':'a','s':'b','c':'d'...}으로 묶어서 배열 입력 
* method : 전송 방식(생략가능) 
*/ 
function post_to_url(path, params, method) {     
method = method || "post"; 
// Set method to post by default, if not specified.     
// The rest of this code assumes you are not using a library.     
// It can be made less wordy if you use one.     
	var form = document.createElement("form");     
	form.setAttribute("method", method);     
	form.setAttribute("action", path);     
	for(var key in params) {         
		var hiddenField = document.createElement("input");        
		hiddenField.setAttribute("type", "hidden");         
		hiddenField.setAttribute("name", key);         
		hiddenField.setAttribute("value", params[key]);        
		form.appendChild(hiddenField);     
	}     
	document.body.appendChild(form);     
	form.submit(); 
}

//선택한 항목 주문
$("#order_select").click(function() {
	 var checkArr = new Array();
	 
	  $("input[name='mdchk']:checked").each(function () {
	         checkArr.push($(this).attr("data-cartcode"));
	     });
	  
	  if(checkArr.length==0) alert("선택된 상품이 없습니다.");
	  else {
		  post_to_url('order.do',{ "checkArr": checkArr });
	  }
});

//상품 개별 삭제
$(".delete_one").click(function () {
	 var checkArr = new Array();

	 checkArr.push($(this).attr("data-cartcode"));
   
	 if(checkArr.length==0) alert("선택된 상품이 없습니다.");
	    else {
		    var confirm_val = confirm("선택한 상품을 삭제하시겠습니까?");
		    if (confirm_val) {
		        $.ajax({
		            url: "cartSelectDelete.do",
		            type: "POST",
		            data: { "checkArr": checkArr },
		            success: function (data) {
		            	alert('삭제하였습니다.');
		                location.replace("cartPut.do");
		            },
		            error: function(request,status,error) {
		        		alert("code = "+ request.status + " message = " + request.responseText + " error = " + error);
		        	}
		        });
		    }
	    }
});
//상품 개별 주문
$(".order_one").click(function () {
	 var ordercode = $(this).attr("data-cartcode");
  
	 if(ordercode!=null && ordercode!="") {
	        $.ajax({
	            url: "order.do?md_code=" + ordercode,
	            type: "GET",
	            data: { "md_code": ordercode },
	            success: function (data) {
	                //location.replace("cartPut.do");
	            	location.href="order.do?md_code=" + ordercode;
	            }
	        });
	 }
	        
});
