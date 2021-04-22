package com.snl.savemehomes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.snl.savemehomes.dto.NoticeDto;
import com.snl.savemehomes.util.DBUtil;

public class NoticeDaoImpl implements NoticeDao {

	static NoticeDao noticeDao;
	
	private NoticeDaoImpl() {}

	public static NoticeDao getInstance() {
		if(noticeDao == null)
			noticeDao = new NoticeDaoImpl();
		return noticeDao;
	}
	
	@Override
	public boolean CreateNotice(NoticeDto noticeDto) {
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String query = new String();
	    
	    query ="INSERT INTO Notice (noticeWriter, noticeTitle, noticeContent)"
	    		+ " VALUES (?, ?, ?)";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, noticeDto.getNoticeWriter());
			pstmt.setString(2, noticeDto.getNoticeTitle());
			pstmt.setString(3, noticeDto.getNoticeContent());
			pstmt.executeUpdate();
			ret = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	    
	    return ret;
	}

	@Override
	public List<NoticeDto> ReadNotice(int pageNum) {
		// TODO Auto-generated method stub
		List<NoticeDto> listNoticeDto = null;
		return listNoticeDto;
	}

	@Override
	public boolean UpdateNotice(NoticeDto noticeDto) {
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String query = new String();
	    
	    query ="UPDATE Notice SET noticeTitle=?, noticeContent=? WHERE idx=?";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, noticeDto.getNoticeTitle());
			pstmt.setString(2, noticeDto.getNoticeContent());
			pstmt.setInt(3, noticeDto.getIdx());
			pstmt.executeUpdate();
			ret = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	    
	    return ret;
	}

	@Override
	public boolean DeleteNotice(int idx) {
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String query = new String();
	    
	    query = "DELETE FROM Notice WHERE idx=?";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			if(pstmt.executeUpdate() == 0) ret = false;
			else ret = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	    
	    return ret;
	}

	@Override
	public NoticeDto ReadNoticeByIdx(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		NoticeDto noticeDto = null;
	    String query = new String();
	    
	    query = "SELECT noticeWriter, noticeTitle, noticeDate, noticeContent"
	    		+ " FROM Notice WHERE idx=?";
	    try {
	    	conn = DBUtil.getConnect();
	    	noticeDto = new NoticeDto();
	    	noticeDto.setIdx(idx);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeDto.setNoticeWriter(rs.getString(1));
				noticeDto.setNoticeTitle(rs.getString(2));
				noticeDto.setNoticeDate(rs.getString(3));
				noticeDto.setNoticeContent(rs.getString(4));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	    
		return noticeDto;
	}

}
