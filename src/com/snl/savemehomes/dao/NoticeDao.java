package com.snl.savemehomes.dao;

import java.util.List;

import com.snl.savemehomes.dto.NoticeDto;

public interface NoticeDao {
	public boolean createNotice(NoticeDto noticeDto);
	public List<NoticeDto> readNotice(int pageNum);
	public boolean updateNotice(NoticeDto noticeDto);
	public boolean deleteNotice(int idx);
	public NoticeDto readNoticeByIdx(int idx);
	public int readNoticePageCount();
}
