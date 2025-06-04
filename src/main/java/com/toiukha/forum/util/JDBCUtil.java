package com.toiukha.forum.util;

import java.sql.*;

public class JDBCUtil {

	// 當類別被載入時會執行這一個區塊，叫JVM載入放著驅動資訊的JDBCUtil
	static {
		try {
			Class.forName(JDBCUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	// MySQL 8.0.13以後只需保留serverTimezone設定即可
	public static final String URL = 
				"jdbc:mysql://localhost:3306/toiukha?"
	//			+ "useSSL=false&"                   // 不使用加密連線 (需有憑證才行)
	//			+ "rewriteBatchedStatements=true&"  // 批次更新需要此資訊
				+ "serverTimezone=Asia/Taipei"; // 設定時區資訊
	//			+ "allowPublicKeyRetrieval=true&"   // 配合MySQL 8以後版本對密碼儲存機制的設定
	//			+ "useUnicode=true&"                // 使用Unicode編碼 (中文才不會亂碼)
	//			+ "characterEncoding=utf-8";        // 字元採用UTF-8設定

	public static final String USER = "root";

	public static final String PASSWORD = "CJA10117";

	// 關閉資源
	public static void closeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		// 有連線就關閉
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		} if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}

	// 關閉資源(給查詢以外的方法使用)
	public static void closeConnection(Connection con, PreparedStatement pstmt) {
		closeConnection(con, pstmt, null);
	}

	// 開連線
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
