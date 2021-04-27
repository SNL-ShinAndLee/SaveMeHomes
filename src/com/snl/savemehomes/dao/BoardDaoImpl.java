package com.snl.savemehomes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.snl.savemehomes.common.UserRole;
import com.snl.savemehomes.dto.BoardDto;
import com.snl.savemehomes.dto.UserDto;
import com.snl.savemehomes.util.DBUtil;

public class BoardDaoImpl implements BoardDao {
	
	static BoardDao boardDao;
	
	private BoardDaoImpl() {}

	public static BoardDao getInstance() {
		if(boardDao == null)
			boardDao = new BoardDaoImpl();
		return boardDao;
	}

	@Override
	public boolean createBoard(BoardDto boardDto) {
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String query = new String();
	    
	    query ="INSERT INTO Board (boardWriter, boardTitle, boardContent)"
	    		+ " VALUES (?, ?, ?)";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardDto.getBoardWriter());
			pstmt.setString(2, boardDto.getBoardTitle());
			pstmt.setString(3, boardDto.getBoardContent());
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
	public List<BoardDto> readBoard(int pageNum) {
		List<BoardDto> listBoardDto = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String query = new String();
	    
	    query = "SELECT idx, boardTitle, boardWriter, boardDate FROM Board order by idx DESC limit "+ (pageNum-1)*10 + ", 10;";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setIdx(rs.getInt(1));
				boardDto.setBoardTitle(rs.getString(2));
				boardDto.setBoardWriter(rs.getString(3));
				boardDto.setBoardDate(rs.getString(4));
				listBoardDto.add(boardDto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
		
		return listBoardDto;
	}

	@Override
	public boolean updateBoard(BoardDto boardDto) {
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String query = new String();
	    
	    query ="UPDATE Board SET boardWriter=?, boardTitle=?, boardContent=? WHERE idx=?";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardDto.getBoardWriter());
			pstmt.setString(2, boardDto.getBoardTitle());
			pstmt.setString(3, boardDto.getBoardContent());
			pstmt.setInt(4, boardDto.getIdx());
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
	public boolean deleteBoard(int idx) {
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String query = new String();
	    
	    query = "DELETE FROM Board WHERE idx=?";
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
	public BoardDto readBoardByIdx(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		BoardDto boardDto = null;
	    String query = new String();
	    
	    query = "SELECT boardWriter, boardTitle, boardDate, boardContent"
	    		+ " FROM Board WHERE idx=?";
	    try {
	    	conn = DBUtil.getConnect();
	    	boardDto = new BoardDto();
	    	boardDto.setIdx(idx);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardDto.setBoardWriter(rs.getString(1));
				boardDto.setBoardTitle(rs.getString(2));
				boardDto.setBoardDate(rs.getString(3));
				boardDto.setBoardContent(rs.getString(4));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	    
		return boardDto;
	}

	@Override
	public int readBoardPageCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int pageCount = 1;
	    String query = new String();
	    
	    query = "SELECT count(idx) FROM Board";
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

	@Override
	public UserDto readWriterByIdx(int idx) {
		
		UserDto userDto = new UserDto();
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String query = new String();
	    
	    query = "SELECT u.idx, u.userId, u.userPass, u.userName, u.userRole, u.userEmail, u.userAddress " + 
	    		"FROM Board as b " + 
	    		"INNER JOIN User as u ON b.boardWriter = u.userId " + 
	    		"WHERE b.idx = ?;";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				 userDto.setIdx(rs.getInt(1));
				 userDto.setUserId(rs.getString(2));
				 userDto.setUserPass(rs.getString(3));
				 userDto.setUserName(rs.getString(4));
				 userDto.setUserRole(UserRole.valueOf(rs.getInt(5)));
				 userDto.setUserEmail(rs.getString(6));
				 userDto.setUserAddress(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
		
		
		return userDto;
	}

}
