package notice.dto;

import java.util.List;

public class NoticeListModel {
	
	private List<NoticeVO> noticeList;
	private Paging paging;

	public List<NoticeVO> getNoticeList() {
		return noticeList;
	}
	
	public void setNoticeList(List<NoticeVO> noticeList) {
		this.noticeList = noticeList;
	}

	public boolean isHasBoard() {
		return ! noticeList.isEmpty();
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}
}