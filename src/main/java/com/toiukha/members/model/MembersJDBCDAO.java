package com.toiukha.members.model;



import java.util.*;
import java.sql.*;



public class MembersJDBCDAO implements MembersDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/toiukha?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "12345678";
	
	private static final String INSERT_STMT = "INSERT INTO members (MEMACC, MEMPWD, MEMNAME, MEMGENDER, MEMBIRTHDATE, MEMMOBILE, MEMEMAIL, MEMADDR, MEMREGTIME, MEMUPDATEDAT, MEMAVATAR, MEMUSERNAME, MEMAVATARFRAME, MEMLOGERRTIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String GET_ALL_STMT = "SELECT MEMID, MEMACC, MEMPWD, MEMNAME, MEMGENDER, MEMBIRTHDATE, MEMMOBILE, MEMEMAIL, MEMADDR, MEMREGTIME, MEMPOINT, MEMUPDATEDAT, MEMSTATUS, MEMAVATAR, MEMUSERNAME, MEMAVATARFRAME, MEMLOGERRCOUNT, MEMLOGERRTIME, MEMGROUPAUTH, MEMGROUPPOINT, MEMSTOREAUTH, MEMSTOREPOINT FROM members ORDER BY MEMID";
	
	private static final String GET_ONE_STMT = "SELECT MEMID, MEMACC, MEMPWD, MEMNAME, MEMGENDER, MEMBIRTHDATE, MEMMOBILE, MEMEMAIL, MEMADDR, MEMREGTIME, MEMPOINT, MEMUPDATEDAT, MEMSTATUS, MEMAVATAR, MEMUSERNAME, MEMAVATARFRAME, MEMLOGERRCOUNT, MEMLOGERRTIME, MEMGROUPAUTH, MEMGROUPPOINT, MEMSTOREAUTH, MEMSTOREPOINT FROM members WHERE MEMID = ?";
	
	private static final String UPDATE = "UPDATE members SET MEMACC=?, MEMPWD=?, MEMNAME=?, MEMGENDER=?, MEMBIRTHDATE=?, MEMMOBILE=?, MEMEMAIL=?, MEMADDR=?, MEMREGTIME=?, MEMPOINT=?, MEMUPDATEDAT=?, MEMSTATUS=?, MEMAVATAR=?, MEMUSERNAME=?, MEMAVATARFRAME=?, MEMLOGERRCOUNT=?, MEMLOGERRTIME=?, MEMGROUPAUTH=?, MEMGROUPPOINT=?, MEMSTOREAUTH=?, MEMSTOREPOINT=? WHERE MEMID=?";

	public void insert(MembersVO membersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, membersVO.getMemAcc());
	        pstmt.setString(2, membersVO.getMemPwd());
	        pstmt.setString(3, membersVO.getMemName());
	        pstmt.setString(4, membersVO.getMemGender());
	        pstmt.setDate(5, membersVO.getMemBirthDate());
	        pstmt.setString(6, membersVO.getMemMobile());
	        pstmt.setString(7, membersVO.getMemEmail());
	        pstmt.setString(8, membersVO.getMemAddr());
	        pstmt.setTimestamp(9, membersVO.getMemRegTime());
	        pstmt.setTimestamp(10, membersVO.getMemUpdatedAt());
	        pstmt.setBytes(11, membersVO.getMemAvatar());
	        pstmt.setString(12, membersVO.getMemUsername());
	        pstmt.setBytes(13, membersVO.getMemAvatarFrame());
	        pstmt.setTimestamp(14, membersVO.getMemLogErrTime());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	@Override
	public void update(MembersVO membersVO) {
	    // TODO: implement this method
	    // 暫時不做任何處理
	}

	@Override
	public MembersVO findByPrimaryKey(Integer memID) {
	    // TODO: implement this method
	    // 先回傳 null 以避免錯誤
	    return null;
	}

	@Override
	public List<MembersVO> getAll() {
	    // TODO: implement this method
	    // 先回傳空集合
	    return new ArrayList<MembersVO>();
	}
	
	
	
	public static void main(String[] args) {
		
		MembersJDBCDAO dao = new MembersJDBCDAO();
		
		MembersVO membersVO1 = new MembersVO();
		
		membersVO1.setMemAcc("ACC1");
		membersVO1.setMemPwd("PWD1");
		membersVO1.setMemName("NAME1");
		membersVO1.setMemGender("M");
		membersVO1.setMemBirthDate(java.sql.Date.valueOf("2000-01-01"));
		membersVO1.setMemMobile("0912345678");
		membersVO1.setMemEmail("AAA111@GMAIL.COM");
		membersVO1.setMemAddr("台灣");
		membersVO1.setMemRegTime(Timestamp.valueOf("2023-01-01 10:00:00"));
		membersVO1.setMemUpdatedAt(null);
		membersVO1.setMemAvatar(null);
		membersVO1.setMemUsername("USERNAME1");
		membersVO1.setMemAvatarFrame(null);
		membersVO1.setMemLogErrTime(null);
		dao.insert(membersVO1);
		
	}

}






	
