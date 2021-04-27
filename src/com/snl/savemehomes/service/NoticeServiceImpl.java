package com.snl.savemehomes.service;

import java.util.List;


import com.snl.savemehomes.common.UserRole;
import com.snl.savemehomes.dao.NoticeDao;
import com.snl.savemehomes.dao.NoticeDaoImpl;
import com.snl.savemehomes.dao.UserDao;
import com.snl.savemehomes.dao.UserDaoImpl;
import com.snl.savemehomes.dto.NoticeDto;
import com.snl.savemehomes.exception.NoPermissionsException;

public class NoticeServiceImpl implements NoticeService {

	static NoticeService noticeService;
	
	private NoticeServiceImpl() {}

	public static NoticeService getInstance() {
		if(noticeService == null)
			noticeService = new NoticeServiceImpl();
		return noticeService;
	}
	
	@Override
	public boolean writeNotice(NoticeDto noticeDto) throws NoPermissionsException {
		NoticeDao noticeDao = NoticeDaoImpl.getInstance();
		UserDao userDao = UserDaoImpl.getInstance();
		
		//관리자 확인
		if(userDao.readUserRoleById(noticeDto.getNoticeWriter())
				!= UserRole.ADMINISTRATOR) {
			throw new NoPermissionsException("관리자 권한이 없습니다");

//			return false;
		}
		//공지사항 저장
		if(!noticeDao.createNotice(noticeDto))
			return false;
	
		return true;
	}

	@Override
	public List<NoticeDto> readNoticeList(int pageNum) {
		return NoticeDaoImpl.getInstance().readNotice(pageNum);
	}

	@Override
	public boolean modifyNotice(NoticeDto noticeDto) throws NoPermissionsException {
		NoticeDao noticeDao = NoticeDaoImpl.getInstance();
		UserDao userDao = UserDaoImpl.getInstance();
		
		//수정 권한 확인
		if(userDao.readUserRoleById(noticeDto.getNoticeWriter())
				!= UserRole.ADMINISTRATOR) {
			throw new NoPermissionsException("관리자 권한이 없습니다");
		}
		
		if(!noticeDao.updateNotice(noticeDto)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteNotice(int idx) {
		return NoticeDaoImpl.getInstance().deleteNotice(idx);
	}

	@Override
	public NoticeDto readNoticeByIdx(int idx) {
		return NoticeDaoImpl.getInstance().readNoticeByIdx(idx);
	}

	@Override
	public int readNoticePageCount() {
		return NoticeDaoImpl.getInstance().readNoticePageCount();
	}
	
}



