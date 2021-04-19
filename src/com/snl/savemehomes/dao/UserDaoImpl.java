package com.snl.savemehomes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.snl.savemehomes.dto.UserDto;
import com.snl.savemehomes.util.DBUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean CreateUser(UserDto userDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String query = new String();
	    
	    query ="INSERT INTO USER (userId, userPass, userName, userEmail, userAddress) "
	    		+ " VALUES (?, ?, ?, ?, ?)";
	    try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userDto.getUserId());
			pstmt.setString(2, userDto.getUserPass());
			pstmt.setString(3, userDto.getUserName());
			pstmt.setString(4, userDto.getUserEmail());
			pstmt.setString(5, userDto.getUserAddress());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.close(pstmt, conn);
		}
	    return true;
		
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
	public UserDto UpdateUser(UserDto userDto) {
		
		return null;
	}

	@Override
	public boolean DeleteUser(UserDto userDto) {
		// TODO Auto-generated method stub
		return false;
	}

}
