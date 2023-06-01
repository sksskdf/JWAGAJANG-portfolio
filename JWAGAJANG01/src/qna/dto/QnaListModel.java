package qna.dto;

import java.util.List;

public class QnaListModel {
	
	private List<QnaVO> qnaList;
	private Paging paging;

	public List<QnaVO> getQnaList() {
		return qnaList;
	}
	
	public void setQnaList(List<QnaVO> qnaList) {
		this.qnaList = qnaList;
	}

	public boolean isHasBoard() {
		return ! qnaList.isEmpty();
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	public int getSize() {
		return qnaList.size();
	}
}