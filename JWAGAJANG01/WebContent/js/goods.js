$(function() {
	// 스크롤
    $(".detail_anchor, .review_anchor, .top_btn").click(function() {
        $('html, body').animate({
            scrollTop: $(this.hash).offset().top}, 400);
    });

	// 검색
	$("#schButton").on("click", function(e) {
		var sch = $("#schText").val();
		
		if(sch.length == 0) {
			alert("검색어를 입력해 주세요.");
			document.querySelector("#schText").focus();
			return false;
		}
	});

	
	// 찜하기 버튼
	$(".favbtn").on("click",function(e){
		e.preventDefault();
		var mdcode = $("input[name='mdcode']").val();
		var favdupchk = $("input[name='favdupchk']").val();
		var userid = $("input[name='user_id']").val();
		var grade = $("input[name='grade']").val();
		var query = { mdcode:mdcode,userid:userid};
		$.ajax({
			type: "POST",
			url: "/favadd.do",
			data: query,
			success: function(data) {
				if(favdupchk === mdcode){
					alert("이미 찜 한 상품입니다!");
				}else if(grade == 1){
					alert('일반회원만 가능합니다!');
					location.href='goods.do?md_code='+md_code;
				}else{
					alert("해당상품이 찜목록에 추가되었습니다!");
					location.href = "goods.do?md_code="+mdcode;
				}
			}
		});
	});
	

	// 정렬
	$(".orderBy").on("click", function(e) {
		// 대분류와 소분류를 구한다.
		var $ultag=$(this).parent().parent();
		var c1=$ultag.attr("data-c1");
		var c2=$ultag.attr("data-c2");
		var order=$(this).data("oper");
		
		var query={category_main:c1, category_sub:c2,order:order};
			// order는 정렬순서 1.최신순 2.판매량순 3.상품평순 4.낮은가격 5.높은가격
		
		$.ajax({
			type: 'POST',
			url: "list.do",
			data: query,
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			success: function(data) {
				$('.goods').html(data);
			},
			error: function(request, status, error) {
				alert(error);
			}
		});
	});
	
	// 리뷰 등록
	$("#submitbtn").on("click", function(e) {
		var md_code = $("input[name='md_code']").val();
		var user_id = $("#writer").val();
		var score = $(".score").text();
		var writervw = $("#writervw").val();
		console.log("나는"+score);
		var query = {md_code:md_code, user_id:user_id, review_rate:score, review_content:writervw};
		
		$.ajax({
			type: 'post',
			url: "reviewWrite.do",
			data: query,
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			success: function(data) {
				$('.review_list').append(data);
				$('.noReview').hide();
			},
			error: function(request, status, error) {
				alert("상품평 등록에 실패하였습니다.");

			}
		});
	});
	
	

	// 장바구니
	$(".cartbtn").on("click",function(e){
		e.preventDefault();
		var userid = $("input[name='user_id']").val();
		var grade = $("input[name='grade']").val();
		var md_code = $("input[name='mdcode']").val();
		var user_id = $("input[name='user_id']").val();
		var md_name = $(".goods_title").text();
		var img_main = $(".goods_img").attr("src");
		var md_price = $(".price").text();
		var md_dc = $(".dc").text();
		var md_count = $("#goods_qty").val();
		var query = { md_code:md_code,user_id:user_id, md_name:md_name, 
				img_main:img_main, md_price:md_price, md_dc:md_dc, md_count:md_count};
		$.ajax({
			type: "POST",
			url: "/cartPut.do",
			data: query,
			success: function(data) {
				if(userid === null || userid === ''){
					alert("로그인 후 이용하실 수 있습니다!");
					location.href='login.do';
				}else if(grade == 1){
					alert('관리자계정은 이용하실 수 없습니다!');
					location.href='goods.do?md_code='+md_code;
				}else{
					
					alert("장바구니에 추가되었습니다!");
					location.href='cartPut.do';
				}
				
				
			}
		});
	});
	
	// 푸터 네비 바 나타나기
	$(window).scroll(function() {
	    if($(this).scrollTop() < 300) {
	        $(".footnav").hide();
	    } else {
	        $(".footnav").show();
	    }
	});
});

// 수량변경 및 총금백
function change_qty(btn) {
    var min_qty = 1;
    var this_qty = $("#goods_qty").val()*1;
    var max_qty = $("#goods_qty").attr("max");

    if(btn=="minus") {
        this_qty -= 1;
        if(this_qty < min_qty) {
            alert("수량은 1개 이상 입력해주세요.")
            return;
        }
    } else if(btn=="plus") {
        this_qty += 1;
        if(this_qty > max_qty) {
            alert("재고가 부족합니다.");
            return;
        }
    }
    
	var dccost = $("#cost").data("oper");
    var basic_amount = parseInt(dccost);
    var show_total_amount = basic_amount * this_qty;

    $("#goods_qty").val(this_qty);
    $(".total_qty").html(this_qty);
    $(".total_cost").html(show_total_amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	
};

// 댓글 창 크기 자동 조절
function resize(obj) {
    obj.style.height = '1px';
    obj.style.height = (obj.scrollHeight) + 'px';
};

// 리뷰 삭제
function del(delBtn) {
	var rStr = delBtn.name;
	var arr = rStr.split(",");
	var query = {review_code: arr[0]};
	var md_code = $("input[name='mdcode']").val();
	
	var review_code = delBtn.name;
	var md_code = $("input[name='mdcode']").val();
	var trTag = delBtn.parentNode.parentNode;
	
	var query = {md_code: md_code, review_code: rStr};
	$.ajax({
		type: "POST",
		url: "reviewDelete.do",
		data: query,
		success: function(data) {
			var str1 = '<p id="ck">';
			var loc = data.indexOf(str1);
			var len = str1.length;
			var check = data.substr(loc+len,1);
			if(check == "1") {
				alert("상품평이 삭제 되었습니다.");
				location.href='goods.do?md_code='+md_code; // 이건 새로고침!

				trTag.remove();
			} else {
				alert("상품평 삭제 실패")
			}
		}
	});
};