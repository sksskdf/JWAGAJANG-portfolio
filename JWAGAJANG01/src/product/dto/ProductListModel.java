package product.dto;

import java.util.List;

public class ProductListModel {
	
	private List<ProductVO> productList;
	private Paging paging;

	public List<ProductVO> getNoticeList() {
		return productList;
	}
	
	public void setNoticeList(List<ProductVO> productList) {
		this.productList = productList;
	}

	public boolean isHasBoard() {
		return ! productList.isEmpty();
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}
}