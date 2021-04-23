package com.snl.savemehomes.dto;

public class NoticeDto {
	private int idx;
	private String noticeWriter;
	private String noticeTitle;
	private String noticeDate;
	private String noticeContent;
	
	public NoticeDto() {
		super();
	}

	public NoticeDto(String noticeWriter, String noticeTitle, String noticeDate, String noticeContent) {
		super();
		this.noticeWriter = noticeWriter;
		this.noticeTitle = noticeTitle;
		this.noticeDate = noticeDate;
		this.noticeContent = noticeContent;
	}
	
	public int getIdx() {
		return idx;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}
	
	public String getNoticeTitle() {
		return noticeTitle;
	}
	
	public String getNoticeDate() {
		return noticeDate;
	}
	
	public String getNoticeContent() {
		return noticeContent;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
	
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	
	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}
	
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	
	public String toJSONString() {
		return String.format("{\"idx\" : %d, \"noticeTitle\" : \"%s\", \"noticeDate\" : \"%s\"}", idx, noticeTitle, noticeDate);
	}
	
	@Override
	public String toString() {
		return 	"NoticeDto [idx=" + idx + ", noticeWriter=" + noticeWriter + ", noticeTitle=" + noticeTitle
				+ ", noticeDate=" + noticeDate + ", noticeContent=" + noticeContent + "]";
	}
	
}
