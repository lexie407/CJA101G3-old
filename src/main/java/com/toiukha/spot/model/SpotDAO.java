package com.toiukha.spot.model;

import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SpotDAO implements SpotDAO_interface {

    // 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
    private static DataSource ds = null;
    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/YourJNDIName"); // 這裡要改成你的JNDI名稱
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static final String INSERT_STMT =
        "INSERT INTO spot (spotName, crtId, spotLoc, spotLat, spotLng, spotStatus, spotDesc) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_STMT =
        "SELECT spotId, spotName, crtId, spotLoc, spotLat, spotLng, spotStatus, spotDesc FROM spot ORDER BY spotId";
    private static final String GET_ONE_STMT =
        "SELECT spotId, spotName, crtId, spotLoc, spotLat, spotLng, spotStatus, spotDesc FROM spot WHERE spotId = ?";
    private static final String DELETE =
        "DELETE FROM spot WHERE spotId = ?";
    private static final String UPDATE =
        "UPDATE spot SET spotName=?, crtId=?, spotLoc=?, spotLat=?, spotLng=?, spotStatus=?, spotDesc=? WHERE spotId=?";

    @Override
    public void insert(SpotVO spotVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);
            pstmt.setString(1, spotVO.getSpotName());
            pstmt.setInt(2, spotVO.getCrtId());
            pstmt.setString(3, spotVO.getSpotLoc());
            pstmt.setDouble(4, spotVO.getSpotLat());
            pstmt.setDouble(5, spotVO.getSpotLng());
            pstmt.setByte(6, spotVO.getSpotStatus());
            pstmt.setString(7, spotVO.getSpotDesc());
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            if (con != null) try { con.close(); } catch (Exception e) { e.printStackTrace(System.err); }
        }
    }

    @Override
    public void update(SpotVO spotVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);
            pstmt.setString(1, spotVO.getSpotName());
            pstmt.setInt(2, spotVO.getCrtId());
            pstmt.setString(3, spotVO.getSpotLoc());
            pstmt.setDouble(4, spotVO.getSpotLat());
            pstmt.setDouble(5, spotVO.getSpotLng());
            pstmt.setByte(6, spotVO.getSpotStatus());
            pstmt.setString(7, spotVO.getSpotDesc());
            pstmt.setInt(8, spotVO.getSpotId());
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            if (con != null) try { con.close(); } catch (Exception e) { e.printStackTrace(System.err); }
        }
    }

    @Override
    public void delete(Integer spotId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(DELETE);
            pstmt.setInt(1, spotId);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            if (con != null) try { con.close(); } catch (Exception e) { e.printStackTrace(System.err); }
        }
    }

    @Override
    public SpotVO findByPrimaryKey(Integer spotId) {
        SpotVO spotVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
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
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            if (pstmt != null) try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            if (con != null) try { con.close(); } catch (Exception e) { e.printStackTrace(System.err); }
        }
        return spotVO;
    }

    @Override
    public List<SpotVO> getAll() {
        List<SpotVO> list = new ArrayList<>();
        SpotVO spotVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
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
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            if (pstmt != null) try { pstmt.close(); } catch (SQLException se) { se.printStackTrace(System.err); }
            if (con != null) try { con.close(); } catch (Exception e) { e.printStackTrace(System.err); }
        }
        return list;
    }
}
