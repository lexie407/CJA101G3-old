package com.toiukha.spot.model;

import java.sql.*;
import java.util.*;
import java.io.InputStream;

public class SpotJDBCDAO implements SpotDAO_interface {

    private String driver;
    private String url;
    private String userid;
    private String passwd;

    // 建構子：一建立物件就讀取 XML 設定
    public SpotJDBCDAO() {
        loadConfigFromXML();
    }

    // 讀取 db-config.xml
    private void loadConfigFromXML() {
        try {
            Properties props = new Properties();
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("db-config.xml");
            if (is == null) {
                throw new RuntimeException("找不到 db-config.xml，請確認檔案放在 src/main/resources/");
            }
            props.loadFromXML(is);
            this.driver = props.getProperty("driver");
            this.url = props.getProperty("url");
            this.userid = props.getProperty("user");
            this.passwd = props.getProperty("password");
            is.close();
        } catch (Exception e) {
            throw new RuntimeException("無法載入資料庫設定檔：" + e.getMessage());
        }
    }
	
	private static final String INSERT_STMT = "INSERT INTO spot (spotName, crtId, spotLoc, spotLat, spotLng, spotStatus, spotDesc) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT spotId, spotName, crtId, spotLoc, spotLat, spotLng, spotStatus, spotDesc FROM spot ORDER BY spotId";
	private static final String GET_ONE_STMT = "SELECT spotId, spotName, crtId, spotLoc, spotLat, spotLng, spotStatus, spotDesc FROM spot WHERE spotId = ?";
	private static final String DELETE = "DELETE FROM spot WHERE spotId = ?";
	private static final String UPDATE = "UPDATE spot SET spotName=?, crtId=?, spotLoc=?, spotLat=?, spotLng=?, spotStatus=?, spotDesc=? WHERE spotId=?";

	@Override
	public void insert(SpotVO spotVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, spotVO.getSpotName());
			pstmt.setInt(2, spotVO.getCrtId());
			pstmt.setString(3, spotVO.getSpotLoc());
			pstmt.setDouble(4, spotVO.getSpotLat());
			pstmt.setDouble(5, spotVO.getSpotLng());
			pstmt.setInt(6, spotVO.getSpotStatus());
			pstmt.setString(7, spotVO.getSpotDesc());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(SpotVO spotVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, spotVO.getSpotName());
			pstmt.setInt(2, spotVO.getCrtId());
			pstmt.setString(3, spotVO.getSpotLoc());
			pstmt.setDouble(4, spotVO.getSpotLat());
			pstmt.setDouble(5, spotVO.getSpotLng());
			pstmt.setInt(6, spotVO.getSpotStatus());
			pstmt.setString(7, spotVO.getSpotDesc());
			pstmt.setInt(8, spotVO.getSpotId());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer spotId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, spotId);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public SpotVO findByPrimaryKey(Integer spotId) {
		SpotVO spotVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, spotId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				spotVO = new SpotVO();
				spotVO.setSpotId(rs.getInt("spotId"));
				spotVO.setSpotName(rs.getString("spotName"));
				spotVO.setCrtId(rs.getInt("crtId"));
				spotVO.setSpotLoc(rs.getString("spotLoc"));
				spotVO.setSpotLat(rs.getDouble("spotLat"));
				spotVO.setSpotLng(rs.getDouble("spotLng"));
				spotVO.setSpotStatus(rs.getByte("spotStatus"));
				spotVO.setSpotDesc(rs.getString("spotDesc"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return spotVO;
	}

	@Override
	public List<SpotVO> getAll() {
		List<SpotVO> list = new ArrayList<SpotVO>();
		SpotVO spotVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				spotVO = new SpotVO();
				spotVO.setSpotId(rs.getInt("spotId"));
				spotVO.setSpotName(rs.getString("spotName"));
				spotVO.setCrtId(rs.getInt("crtId"));
				spotVO.setSpotLoc(rs.getString("spotLoc"));
				spotVO.setSpotLat(rs.getDouble("spotLat"));
				spotVO.setSpotLng(rs.getDouble("spotLng"));
				spotVO.setSpotStatus(rs.getByte("spotStatus"));
				spotVO.setSpotDesc(rs.getString("spotDesc"));
				list.add(spotVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public static void main(String[] args) {
		SpotJDBCDAO dao = new SpotJDBCDAO();

		// 新增
		// SpotVO spotVO1 = new SpotVO();
		// spotVO1.setSpotName("測試景點");
		// spotVO1.setCrtId(1);
		// spotVO1.setSpotLoc("台中市西區");
		// spotVO1.setSpotLat(24.12345);
		// spotVO1.setSpotLng(120.6789);
		// spotVO1.setSpotStatus(1);
		// spotVO1.setSpotDesc("這是測試資料");
		// dao.insert(spotVO1);

		// 修改
		// SpotVO spotVO2 = new SpotVO();
		// spotVO2.setSpotId(1);
		// spotVO2.setSpotName("新名稱");
		// spotVO2.setCrtId(1);
		// spotVO2.setSpotLoc("新地點");
		// spotVO2.setSpotLat(25.0);
		// spotVO2.setSpotLng(121.0);
		// spotVO2.setSpotStatus(1);
		// spotVO2.setSpotDesc("新描述");
		// dao.update(spotVO2);

		// 刪除
		// dao.delete(1);

		// 查單筆
		// SpotVO spotVO3 = dao.findByPrimaryKey(1);
		// System.out.println(spotVO3.getSpotName());

		// 查全部
		List<SpotVO> list = dao.getAll();
		for (SpotVO aSpot : list) {
			System.out.print(aSpot.getSpotId() + ",");
			System.out.print(aSpot.getSpotName() + ",");
			System.out.print(aSpot.getCrtId() + ",");
			System.out.print(aSpot.getSpotLoc() + ",");
			System.out.print(aSpot.getSpotLat() + ",");
			System.out.print(aSpot.getSpotLng() + ",");
			System.out.print(aSpot.getSpotStatus() + ",");
			System.out.print(aSpot.getSpotDesc());
			System.out.println();
		}
	}
}
