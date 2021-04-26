package com.snl.savemehomes.util;

import java.sql.*;
import com.snl.savemehomes.constants.Constants;


public class DBUtil {
	private static String URL = Constants.getDBURL();
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String ID = Constants.getDBID();
	private static String PASSWORD = Constants.getDBPASSWORD();

	/*private static DBUtil instance;
	
	private DBUtil(){
		try {
			Class.forName(DRIVER);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	public DBUtil getInstance() {
		if(instance == null) instance = new DBUtil();
		return instance;
	}*/
	static {
		try {
			Class.forName(DRIVER);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnect() throws SQLException {
		return DriverManager.getConnection(URL, ID, PASSWORD);
	}

	public static void close(AutoCloseable... closeables) {

		for (AutoCloseable ac : closeables) {
			if (ac != null)
				try {
					ac.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

		}
	}
	
}
