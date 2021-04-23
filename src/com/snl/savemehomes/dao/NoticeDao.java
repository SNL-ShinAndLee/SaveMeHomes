package com.snl.savemehomes.dao;

import java.util.List;

import com.snl.savemehomes.dto.NoticeDto;

public interface NoticeDao {
	public boolean CreateNotice(NoticeDto noticeDto);
	public List<NoticeDto> ReadNotice(int pageNum);
	public boolean UpdateNotice(NoticeDto noticeDto);
	public boolean DeleteNotice(int idx);
	public NoticeDto ReadNoticeByIdx(int idx);
	public int ReadNoticePageCount();
}
