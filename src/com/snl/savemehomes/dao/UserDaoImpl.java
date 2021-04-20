package com.snl.savemehomes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.snl.savemehomes.dto.UserDto;
import com.snl.savemehomes.util.DBUtil;

public class UserDaoImpl implements UserDao {

	static UserDao userDao;
	
	private UserDaoImpl() {}

	public static UserDao getUserDao() {
		if(userDao == null)
			userDao = new UserDaoImpl();
		return userDao;
	}
	
	@Override
	public boolean CreateUser(UserDto userDto) {
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String query = new String();
	    
	    query ="INSERT INTO User (userId, userPass, userName, userEmail, userAddress) "
	    		+ " VALUES (?, ?, ?, ?, ?)";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userDto.getUserId());
			pstmt.setString(2, userDto.getUserPass());
			pstmt.setString(3, userDto.getUserName());
			pstmt.setString(4, userDto.getUserEmail());
			pstmt.setString(5, userDto.getUserAddress());
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
	public UserDto ReadUser(String userId, String userPass) {
		UserDto userDto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	    String query = new String();
	    
	    query = "SELECT userId, userPass, userName, userEmail, userAdress"
	    		+ " FROM User WHERE userId=? and userPass=?";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPass);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				userDto = new UserDto(rs.getString(1), rs.getString(2), rs.getString(3), 
									  rs.getString(4), rs.getString(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
		
		return userDto;
	}

	@Override
	public boolean UpdateUser(UserDto userDto) {
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String query = new String();
	    
	    query = "UPDATE User SET userPass=?, userName=?, userAddress=? WHERE userId=? ";	//idx로 찾을 지 id로 찾을 지?
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userDto.getUserPass());
			pstmt.setString(2, userDto.getUserName());
			pstmt.setString(3, userDto.getUserAddress());
			pstmt.setString(4, userDto.getUserId());
			pstmt.executeQuery();
			ret = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBUtil.close(pstmt, conn);
		}
		
		return ret;
	}

	@Override
	public boolean DeleteUser(String userId, String userPass) {
		boolean ret = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String query = new String();
	    
	    query = "DELETE FROM User WHERE userID=? and userPass=?";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(1, userPass);
			pstmt.executeQuery();
			ret = true;
		} 
	    catch (SQLException e) {
			e.printStackTrace();
		} 
	    finally {
			DBUtil.close(pstmt, conn);
		}
		return ret;
	}

	@Override
	public boolean ReadUserById(String userId) {
		Integer idCheck = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	    String query = new String();
	    
	    query = "SELECT idx"
	    		+ " FROM User WHERE userId=?";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				idCheck = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
		
	    if(idCheck==null) return false;
		return true;
	}

}
