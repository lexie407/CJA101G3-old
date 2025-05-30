package com.toiukha.order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderJDBCDAO implements OrderDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/toiukha?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "44300424";

	private static final String INSERT_STMT = "INSERT INTO `orders` (MEMID,ORDSTA,CREDATE) VALUES (?, ?, ?)";
	private static final String DELETE = "DELETE FROM `order` where ORDID = ?";
	private static final String GET_ALL_STMT = "SELECT ORDID,MEMID,ORDSTA,CREDATE FROM `orders` order by ORDID";
	private static final String GET_ONE_STMT = "SELECT ORDID,MEMID,ORDSTA,CREDATE FROM `orders` where ORDID = ?";
	private static final String UPDATE = "UPDATE `orders` set MEMID=?, ORDSTA=?,CREDATE=? where ORDID = ?";

	public static void main(String[] args) {
		OrderJDBCDAO dao = new OrderJDBCDAO();

		// 新增一筆
//		OrderVO newOrder = new OrderVO();
//		newOrder.setMemId(100);
//		newOrder.setOrdSta(1);
//		newOrder.setCreDate(new java.sql.Timestamp(System.currentTimeMillis()));
//		dao.insert(newOrder);
//		System.out.println("新增完成");

//		// 查詢全部
//		List<OrderVO> allOrders = dao.getAll();
//		System.out.println("全部訂單：");
//		for (OrderVO order : allOrders) {
//			System.out.printf("ORDID=%d, MEMID=%d, ORDSTA=%d, CREDATE=%s\n", order.getOrdId(), order.getMemId(),
//					order.getOrdSta(), order.getCreDate());
//		}

//	    // 查詢單筆（用第一筆的 ordId）

//		OrderVO order = dao.findByPrimaryKey(1);
//		System.out.print(order.getOrdId() + " ,");
//		System.out.print(order.getMemId() + " ,");
//		System.out.print(order.getOrdSta() + " ,");
//		System.out.println(order.getCreDate());

//	        // 修改訂單
//		OrderVO orderVO = new OrderVO();
//		orderVO.setOrdId(1);
//		orderVO.setMemId(3);
//		orderVO.setOrdSta(3);
//		orderVO.setCreDate(new java.sql.Timestamp(System.currentTimeMillis()));
//		dao.update(orderVO);
//		System.out.println("修改成功");

//
//	        // 刪除此筆訂單

//		dao.delete(5);
//		System.out.println("刪除完成");
	}

	@Override
	public void insert(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderVO.getMemId());
			pstmt.setInt(2, orderVO.getOrdSta());
			pstmt.setTimestamp(3, orderVO.getCreDate());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, orderVO.getMemId());
			pstmt.setInt(2, orderVO.getOrdSta());
			pstmt.setTimestamp(3, orderVO.getCreDate());
			pstmt.setInt(4, orderVO.getOrdId());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer ordid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ordid);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public OrderVO findByPrimaryKey(Integer ordid) {
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ordid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				orderVO = new OrderVO();
				orderVO.setOrdId(rs.getInt("ORDID"));
				orderVO.setMemId(rs.getInt("MEMID"));
				orderVO.setOrdSta(rs.getInt("ORDSTA"));
				orderVO.setCreDate(rs.getTimestamp("CREDATE"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return orderVO;
	}

	@Override
	public List<OrderVO> getAll() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				orderVO = new OrderVO();
				orderVO.setOrdId(rs.getInt("ordId"));
				orderVO.setMemId(rs.getInt("memId"));
				orderVO.setOrdSta(rs.getInt("ordSta"));
				orderVO.setCreDate(rs.getTimestamp("creDate"));
				list.add(orderVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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