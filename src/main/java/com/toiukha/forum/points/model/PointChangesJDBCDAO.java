package com.toiukha.forum.points.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.toiukha.forum.points.entity.PointChangesVO;
import com.toiukha.forum.util.*;

//此類別實作DAO interface，並將資料庫操作細節封裝起來
public class PointChangesJDBCDAO implements IPointChangesDAO {
	// SQL指令們
	private static final String GET_ALL = "SELECT * FROM pointchanges";
	private static final String INSERT_STMT = "INSERT INTO pointchanges (CHATIME, MEMID, AMOUNT, CHADESCRIPTION, CHACAT) VALUES ( ? , ? , ? , ? , ?)";
	private static final String FIND_BYCHAID = "SELECT * FROM pointchanges WHERE CHAID = ?";
	


	//資料庫新增，成功會回傳1，新增時失敗回傳-1，沒有新增會傳0
	@Override
	public int add(PointChangesVO pcv) {
		Debug.log("add方法");
		Connection con = null; 			// 資料庫連線
		PreparedStatement pstmt = null; // 用來塞寫好的sql指令，如果指令內有?，也是藉由這個類別填入
//		ResultSet rs = null; 			// 查詢後的結果物件，這裡不是查詢指令所以不需要他
		Debug.log("準備開始連線...");
		try {
//			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
			con = JDBCUtil.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1,pcv.getChaId());  		//chaId由資料庫自動生成，所以SQL指令也不用新增他
			pstmt.setDate(1, pcv.getChaTime());		//可能之後改成資料庫自動生成
			pstmt.setInt(2, pcv.getMemId());
			pstmt.setInt(3, pcv.getAmount());
			pstmt.setString(4, pcv.getChaDescription());
			pstmt.setByte(5, pcv.getChaCat());		//異動紀錄的正向負向狀態，是一隻名叫Cha的貓貓

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.closeConnection(con, pstmt);
		}
		return -1; //失敗回傳-1
	}

	@Override
	public void update(PointChangesVO pcv) {
		
	}

	
	@Override
	public PointChangesVO findByPrimaryKey(Integer chaId) {
		Debug.log("findByPrimaryKey方法");

		PointChangesVO pcv = null; 		 // 存最後要回傳的的資料

		Connection con = null;			 // 資料庫連線
		PreparedStatement pstmt = null;  // 用來塞寫好的sql指令，如果指令內有?，也是藉由這個類別填入
		ResultSet rs = null;			 // 查詢後的結果物件，可以用next查下一筆(在這裡不需要)
		Debug.log("準備開始連線...");
		
		try {
			// 建立連線，連線資料是從util的JDBCUtil叫出來，當呼叫JDBCUtil
//			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
			con = JDBCUtil.getConnection();
			pstmt = con.prepareStatement(FIND_BYCHAID); // 把sql指令塞給PreparedStatement

			pstmt.setInt(1, chaId);
			
			//			Debug.log("準備送出指令...");
			rs = pstmt.executeQuery(); // 送出指令，回傳查詢結果

			// 從回傳查詢結果裡，把對應的數值塞進PointChangesVO物件，成為一筆物件型態的資料
			// 其實單筆查詢的話不需要while迴圈
			while (rs.next()) {
				pcv = new PointChangesVO();
				pcv.setChaId(rs.getInt("CHAID"));
				pcv.setChaTime(rs.getDate("CHATIME"));
				pcv.setMemId(rs.getInt("MEMID"));
				pcv.setAmount(rs.getInt("AMOUNT"));
				pcv.setChaDescription(rs.getString("CHADESCRIPTION"));
				pcv.setChaCat(rs.getByte("CHACAT"));

//				不用理我XD
//				Debug.log(pcv.getChaId().toString()
//						,pcv.getChaTime().toString()
//						,pcv.getMemId().toString()
//						,pcv.getAmount().toString()
//						,pcv.getChaDescription()
//						,pcv.getChaCat().toString());

			}
		} catch (SQLException e) {
			e.printStackTrace(); // console顯示錯誤訊息

		} finally {
			//執行closeResources方法
			JDBCUtil.closeConnection(con, pstmt, rs);
		}
		
		return pcv;
	}

	@Override
	// 還沒實作
	public List<PointChangesVO> findByDates(Date begin, Date end) {
		List<PointChangesVO> pointChangesList = new ArrayList<PointChangesVO>();
		return pointChangesList;
	}

	@Override
	public List<PointChangesVO> getAll() {
		Debug.log("getAll方法");

		// 存最後要回傳的的資料列表，裡面的資料都是VO物件
		List<PointChangesVO> pointChangesList = new ArrayList<PointChangesVO>();
		PointChangesVO pcv = null; // 用來塞資料進列表的VO

		Connection con = null; // 資料庫連線
		PreparedStatement pstmt = null; // 用來塞寫好的sql指令，如果指令內有?，也是藉由這個類別填入
		ResultSet rs = null; // 查詢後的結果物件，可以用next查下一筆
		Debug.log("準備開始連線...");
		try {
			// 建立連線，連線資料是從util的JDBCUtil叫出來，當呼叫JDBCUtil
//			con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
			con = JDBCUtil.getConnection();
			pstmt = con.prepareStatement(GET_ALL); // 把sql指令塞給PreparedStatement
//			Debug.log("準備送出指令...");
			rs = pstmt.executeQuery(); // 送出指令，回傳查詢結果

			// 從回傳查詢結果裡，把對應的數值塞進PointChangesVO物件，成為一筆物件型態的資料
			while (rs.next()) {
				pcv = new PointChangesVO();
				pcv.setChaId(rs.getInt("CHAID"));
				pcv.setChaTime(rs.getDate("CHATIME"));
				pcv.setMemId(rs.getInt("MEMID"));
				pcv.setAmount(rs.getInt("AMOUNT"));
				pcv.setChaDescription(rs.getString("CHADESCRIPTION"));
				pcv.setChaCat(rs.getByte("CHACAT"));

//				不用理我XD
//				Debug.log(pcv.getChaId().toString()
//						,pcv.getChaTime().toString()
//						,pcv.getMemId().toString()
//						,pcv.getAmount().toString()
//						,pcv.getChaDescription()
//						,pcv.getChaCat().toString());

				pointChangesList.add(pcv); // 把這筆物件型態的資料存入之後要回傳的List
			}
		} catch (SQLException e) {
			e.printStackTrace(); // console顯示錯誤訊息

		} finally {
			//執行closeResources方法
			JDBCUtil.closeConnection(con, pstmt, rs);
		}
		Debug.log("找到" + pointChangesList.size() + "筆資料");
		return pointChangesList; // 回傳結果List
	}

	@Override
	public List<PointChangesVO> findByMemId(Integer memId) {
		// TODO Auto-generated method stub
		return null;
	}
	



	// 測試用main方法
	public static void main(String[] args) {
		PointChangesJDBCDAO pChangesDAO = new PointChangesJDBCDAO();
		pChangesDAO.getAll();
		
//		//pChangesDAO.add(pvo)測試區塊
//		Date currentDate = new Date(System.currentTimeMillis());
//		Byte chacat = 1;
//		PointChangesVO pvo = new PointChangesVO(null, currentDate, 1, 15, "發表文章分享獲得點數", chacat);
//		System.out.println("result: " + (pChangesDAO.add(pvo) == -1 ? "fail" : "success"));
//
//		pChangesDAO.getAll();
//
//		Debug.log("===========");
		
//		//pChangesDAO.findByPrimaryKey測試
//		PointChangesVO pvo = pChangesDAO.findByPrimaryKey(20);
//		System.out.println(pvo.toString());
		
		Debug.log("===========");
	}

}
