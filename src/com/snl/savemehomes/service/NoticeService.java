package com.snl.savemehomes.service;

import java.util.List;

import javax.naming.NoPermissionException;

import com.snl.savemehomes.dto.NoticeDto;

public interface NoticeService {
	public boolean writeNotice(NoticeDto noticeDto) throws NoPermissionException;
	public List<NoticeDto> readNoticeList(int pageNum);
	public boolean modifyNotice(NoticeDto noticeDto) throws NoPermissionException;
	public boolean deleteNotice(int idx);
	public NoticeDto readNoticeByIdx(int idx);
	public int readNoticePageCount(); 
}
