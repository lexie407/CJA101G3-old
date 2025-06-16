package com.toiukha.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTest {
    public static void main(String[] args) {
        // 資料庫連接參數
        String url = "jdbc:mysql://localhost:3306/toiukha?serverTimezone=Asia/Taipei";
        String username = "root";
        String password = "necz1X23c4V";
        
        System.out.println("🔍 開始測試資料庫連接...");
        
        try {
            // 載入MySQL驅動程式
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL驅動程式載入成功");
            
            // 嘗試連接資料庫
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("✅ 資料庫連接成功！");
            
            // 測試查詢資料庫列表
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW DATABASES");
            System.out.println("\n📋 可用的資料庫：");
            while (rs.next()) {
                String dbName = rs.getString(1);
                System.out.println("  - " + dbName);
                if ("toiukha".equals(dbName)) {
                    System.out.println("    ✅ 找到目標資料庫 toiukha");
                }
            }
            
            // 測試toiukha資料庫中的資料表
            rs = stmt.executeQuery("USE toiukha");
            rs = stmt.executeQuery("SHOW TABLES");
            System.out.println("\n📋 toiukha資料庫中的資料表：");
            boolean hasSpotTable = false;
            while (rs.next()) {
                String tableName = rs.getString(1);
                System.out.println("  - " + tableName);
                if ("spot".equals(tableName)) {
                    hasSpotTable = true;
                    System.out.println("    ✅ 找到景點資料表 spot");
                }
            }
            
            // 如果有spot資料表，檢查資料
            if (hasSpotTable) {
                rs = stmt.executeQuery("SELECT COUNT(*) FROM spot");
                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("📊 spot資料表中有 " + count + " 筆資料");
                    if (count > 0) {
                        System.out.println("✅ 資料表有資料，應用程式應該可以正常運作");
                    } else {
                        System.out.println("⚠️  資料表是空的，需要添加測試資料");
                    }
                }
                
                // 顯示資料表結構
                rs = stmt.executeQuery("DESCRIBE spot");
                System.out.println("\n🏗️ spot資料表結構：");
                while (rs.next()) {
                    System.out.println("  " + rs.getString("Field") + " - " + rs.getString("Type"));
                }
            } else {
                System.out.println("❌ 找不到spot資料表！");
            }
            
            connection.close();
            System.out.println("\n✅ 資料庫測試完成");
            
        } catch (Exception e) {
            System.err.println("❌ 資料庫連接失敗：" + e.getMessage());
            e.printStackTrace();
        }
    }
}