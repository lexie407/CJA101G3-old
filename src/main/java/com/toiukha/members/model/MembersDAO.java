package com.toiukha.members.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MembersDAO implements MembersDAO_interface {
	
	
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	
	
	
	private static final String INSERT_STMT = "INSERT INTO members (MEMACC, MEMPWD, MEMNAME, MEMGENDER, MEMBIRTHDATE, MEMMOBILE, MEMEMAIL, MEMADDR, MEMREGTIME, MEMUPDATEDAT, MEMAVATAR, MEMUSERNAME, MEMAVATARFRAME, MEMLOGERRTIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String GET_ALL_STMT = "SELECT MEMID, MEMACC, MEMPWD, MEMNAME, MEMGENDER, MEMBIRTHDATE, MEMMOBILE, MEMEMAIL, MEMADDR, MEMREGTIME, MEMPOINT, MEMUPDATEDAT, MEMSTATUS, MEMAVATAR, MEMUSERNAME, MEMAVATARFRAME, MEMLOGERRCOUNT, MEMLOGERRTIME, MEMGROUPAUTH, MEMGROUPPOINT, MEMSTOREAUTH, MEMSTOREPOINT FROM members ORDER BY MEMID";
	
	private static final String GET_ONE_STMT = "SELECT MEMID, MEMACC, MEMPWD, MEMNAME, MEMGENDER, MEMBIRTHDATE, MEMMOBILE, MEMEMAIL, MEMADDR, MEMREGTIME, MEMPOINT, MEMUPDATEDAT, MEMSTATUS, MEMAVATAR, MEMUSERNAME, MEMAVATARFRAME, MEMLOGERRCOUNT, MEMLOGERRTIME, MEMGROUPAUTH, MEMGROUPPOINT, MEMSTOREAUTH, MEMSTOREPOINT FROM members WHERE MEMID = ?";
	
	private static final String UPDATE = "UPDATE members SET MEMACC=?, MEMPWD=?, MEMNAME=?, MEMGENDER=?, MEMBIRTHDATE=?, MEMMOBILE=?, MEMEMAIL=?, MEMADDR=?, MEMREGTIME=?, MEMPOINT=?, MEMUPDATEDAT=?, MEMSTATUS=?, MEMAVATAR=?, MEMUSERNAME=?, MEMAVATARFRAME=?, MEMLOGERRCOUNT=?, MEMLOGERRTIME=?, MEMGROUPAUTH=?, MEMGROUPPOINT=?, MEMSTOREAUTH=?, MEMSTOREPOINT=? WHERE MEMID=?";

	
	@Override
	public void insert(MembersVO membersVO) {
	    Connection con = null;
	    PreparedStatement pstmt = null;

	    try {
	        con = ds.getConnection();
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
	    } catch (SQLException se) {
	        throw new RuntimeException("A database error occurred. " + se.getMessage());
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
	    Connection con = null;
	    PreparedStatement pstmt = null;

	    try {
	        con = ds.getConnection();
	        pstmt = con.prepareStatement(UPDATE);

	        pstmt.setString(1, membersVO.getMemAcc());
	        pstmt.setString(2, membersVO.getMemPwd());
	        pstmt.setString(3, membersVO.getMemName());
	        pstmt.setString(4, membersVO.getMemGender());
	        pstmt.setDate(5, membersVO.getMemBirthDate());
	        pstmt.setString(6, membersVO.getMemMobile());
	        pstmt.setString(7, membersVO.getMemEmail());
	        pstmt.setString(8, membersVO.getMemAddr());
	        pstmt.setTimestamp(9, membersVO.getMemRegTime());
	        pstmt.setInt(10, membersVO.getMemPoint());
	        pstmt.setTimestamp(11, membersVO.getMemUpdatedAt());
	        pstmt.setInt(12, membersVO.getMemStatus());
	        pstmt.setBytes(13, membersVO.getMemAvatar());
	        pstmt.setString(14, membersVO.getMemUsername());
	        pstmt.setBytes(15, membersVO.getMemAvatarFrame());
	        pstmt.setInt(16, membersVO.getMemLogErrCount());
	        pstmt.setTimestamp(17, membersVO.getMemLogErrTime());
	        pstmt.setInt(18, membersVO.getMemGroupAuth());
	        pstmt.setInt(19, membersVO.getMemGroupPoint());
	        pstmt.setInt(20, membersVO.getMemStoreAuth());
	        pstmt.setInt(21, membersVO.getMemStorePoint());
	        pstmt.setInt(22, membersVO.getMemId());

	        pstmt.executeUpdate();
	    } catch (SQLException se) {
	        throw new RuntimeException("A database error occurred. " + se.getMessage());
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
	public MembersVO findByPrimaryKey(Integer memId) {
	    MembersVO membersVO = null;
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        con = ds.getConnection();
	        pstmt = con.prepareStatement(GET_ONE_STMT);
	        pstmt.setInt(1, memId);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            membersVO = new MembersVO();
	            membersVO.setMemId(rs.getInt("MEMID"));
	            membersVO.setMemAcc(rs.getString("MEMACC"));
	            membersVO.setMemPwd(rs.getString("MEMPWD"));
	            membersVO.setMemName(rs.getString("MEMNAME"));
	            membersVO.setMemGender(rs.getString("MEMGENDER"));
	            membersVO.setMemBirthDate(rs.getDate("MEMBIRTHDATE"));
	            membersVO.setMemMobile(rs.getString("MEMMOBILE"));
	            membersVO.setMemEmail(rs.getString("MEMEMAIL"));
	            membersVO.setMemAddr(rs.getString("MEMADDR"));
	            membersVO.setMemRegTime(rs.getTimestamp("MEMREGTIME"));
	            membersVO.setMemPoint(rs.getInt("MEMPOINT"));
	            membersVO.setMemUpdatedAt(rs.getTimestamp("MEMUPDATEDAT"));
	            membersVO.setMemStatus(rs.getInt("MEMSTATUS"));
	            membersVO.setMemAvatar(rs.getBytes("MEMAVATAR"));
	            membersVO.setMemUsername(rs.getString("MEMUSERNAME"));
	            membersVO.setMemAvatarFrame(rs.getBytes("MEMAVATARFRAME"));
	            membersVO.setMemLogErrCount(rs.getInt("MEMLOGERRCOUNT"));
	            membersVO.setMemLogErrTime(rs.getTimestamp("MEMLOGERRTIME"));
	            membersVO.setMemGroupAuth(rs.getInt("MEMGROUPAUTH"));
	            membersVO.setMemGroupPoint(rs.getInt("MEMGROUPPOINT"));
	            membersVO.setMemStoreAuth(rs.getInt("MEMSTOREAUTH"));
	            membersVO.setMemStorePoint(rs.getInt("MEMSTOREPOINT"));
	        }
	    } catch (SQLException se) {
	        throw new RuntimeException("A database error occurred. " + se.getMessage());
	    } finally {
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
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return membersVO;
	}

	@Override
	public List<MembersVO> getAll() {
	    List<MembersVO> list = new ArrayList<>();
	    MembersVO membersVO = null;
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        con = ds.getConnection();
	        pstmt = con.prepareStatement(GET_ALL_STMT);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            membersVO = new MembersVO();
	            membersVO.setMemId(rs.getInt("MEMID"));
	            membersVO.setMemAcc(rs.getString("MEMACC"));
	            membersVO.setMemPwd(rs.getString("MEMPWD"));
	            membersVO.setMemName(rs.getString("MEMNAME"));
	            membersVO.setMemGender(rs.getString("MEMGENDER"));
	            membersVO.setMemBirthDate(rs.getDate("MEMBIRTHDATE"));
	            membersVO.setMemMobile(rs.getString("MEMMOBILE"));
	            membersVO.setMemEmail(rs.getString("MEMEMAIL"));
	            membersVO.setMemAddr(rs.getString("MEMADDR"));
	            membersVO.setMemRegTime(rs.getTimestamp("MEMREGTIME"));
	            membersVO.setMemPoint(rs.getInt("MEMPOINT"));
	            membersVO.setMemUpdatedAt(rs.getTimestamp("MEMUPDATEDAT"));
	            membersVO.setMemStatus(rs.getInt("MEMSTATUS"));
	            membersVO.setMemAvatar(rs.getBytes("MEMAVATAR"));
	            membersVO.setMemUsername(rs.getString("MEMUSERNAME"));
	            membersVO.setMemAvatarFrame(rs.getBytes("MEMAVATARFRAME"));
	            membersVO.setMemLogErrCount(rs.getInt("MEMLOGERRCOUNT"));
	            membersVO.setMemLogErrTime(rs.getTimestamp("MEMLOGERRTIME"));
	            membersVO.setMemGroupAuth(rs.getInt("MEMGROUPAUTH"));
	            membersVO.setMemGroupPoint(rs.getInt("MEMGROUPPOINT"));
	            membersVO.setMemStoreAuth(rs.getInt("MEMSTOREAUTH"));
	            membersVO.setMemStorePoint(rs.getInt("MEMSTOREPOINT"));
	            list.add(membersVO);
	        }
	    } catch (SQLException se) {
	        throw new RuntimeException("A database error occurred. " + se.getMessage());
	    } finally {
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
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
