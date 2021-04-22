package com.snl.savemehomes.service;

import java.util.List;

import com.snl.savemehomes.common.UserRole;
import com.snl.savemehomes.dao.NoticeDao;
import com.snl.savemehomes.dao.NoticeDaoImpl;
import com.snl.savemehomes.dao.UserDao;
import com.snl.savemehomes.dao.UserDaoImpl;
import com.snl.savemehomes.dto.NoticeDto;

public class NoticeServiceImpl implements NoticeService {

	static NoticeService noticeService;
	
	private NoticeServiceImpl() {}

	public static NoticeService getInstance() {
		if(noticeService == null)
			noticeService = new NoticeServiceImpl();
		return noticeService;
	}
	
	@Override
	public boolean writeNotice(NoticeDto noticeDto) {
		NoticeDao noticeDao = NoticeDaoImpl.getInstance();
		UserDao userDao = UserDaoImpl.getInstance();
		
		//관리자 확인
		if(userDao.ReadUserRoleById(noticeDto.getNoticeWriter())
				!= UserRole.ADMINISTRATOR) {
			return false;
		}
		//공지사항 저장
		if(!noticeDao.CreateNotice(noticeDto))
			return false;
	
		return true;
	}

	@Override
	public List<NoticeDto> readNotice(int pageNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifyNotice(NoticeDto noticeDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteNotice(int idx) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public NoticeDto readNoticeByIdx(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

}
