package com.snl.savemehomes.service;

import java.util.List;

import com.snl.savemehomes.dto.NoticeDto;

public interface NoticeService {
	public boolean writeNotice(NoticeDto noticeDto);
	public List<NoticeDto> readNotice(int pageNum);
	public boolean modifyNotice(NoticeDto noticeDto);
	public boolean deleteNotice(int idx);
	public NoticeDto readNoticeByIdx(int idx);
//	public boolean 
}
