package goods.dto;

import java.util.List;

public class MdListModel {
	private List<GoodsVO> mdList;
	private Paging paging;
	
	public List<GoodsVO> getMdList() {
		return mdList;
	}
	public void setMdList(List<GoodsVO> mdList) {
		this.mdList = mdList;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
}
