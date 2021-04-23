package com.snl.savemehomes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		List<NoticeDto> listNoticeDto = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String query = new String();
	    
	    query = "SELECT idx, noticeTitle, noticeDate FROM Notice order by idx DESC limit "+ (pageNum-1)*10 + ", 10;";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeDto noticeDto = new NoticeDto();
				noticeDto.setIdx(rs.getInt(1));
				noticeDto.setNoticeTitle(rs.getString(2));
				noticeDto.setNoticeDate(rs.getString(3));
				listNoticeDto.add(noticeDto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
		
		return listNoticeDto;
	}

	@Override
	public boolean UpdateNotice(NoticeDto noticeDto) {
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String query = new String();
	    
	    query ="UPDATE Notice SET noticeWriter=?, noticeTitle=?, noticeContent=? WHERE idx=?";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, noticeDto.getNoticeWriter());
			pstmt.setString(2, noticeDto.getNoticeTitle());
			pstmt.setString(3, noticeDto.getNoticeContent());
			pstmt.setInt(4, noticeDto.getIdx());
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

	@Override
	public int ReadNoticePageCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int pageCount = 1;
	    String query = new String();
	    
	    query = "SELECT count(idx) FROM Notice";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pageCount = rs.getInt(1);
			}
			if(pageCount%10!=0) {
				pageCount = pageCount/10 + 1;
			}
			else {
				pageCount = pageCount/10;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
		return pageCount;
	}

}
