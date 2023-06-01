function mainCategoryFilter(){
		
		let order = document.querySelector("#selBoxFirst").value;
		
		var query={order:order};
			
		$.ajax({
			type: 'post',
			url: "productList.do",
			data: query,
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			success: function(data) {
				$('.filter').html(data);
			},
			error: function(request, status, error) {
				alert(error);
			}
		});
}