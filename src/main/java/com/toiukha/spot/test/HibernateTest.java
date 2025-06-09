package com.toiukha.spot.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.toiukha.spot.util.HibernateUtil;
import com.toiukha.spot.model.SpotVO;
import java.util.List;

public class HibernateTest {
    public static void main(String[] args) {
        System.out.println("=== 開始測試 Hibernate 連線 ===");
        
        try {
            // 測試 SessionFactory 建立
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            System.out.println("SessionFactory 建立成功！");
            
            // 測試資料庫查詢
            try (Session session = sessionFactory.openSession()) {
                List<SpotVO> spots = session.createQuery("FROM SpotVO", SpotVO.class).list();
                System.out.println("查詢到 " + spots.size() + " 筆景點資料");
                
                // 顯示每筆資料
                for (SpotVO spot : spots) {
                    System.out.println("景點ID: " + spot.getSpotId() + 
                                     ", 名稱: " + spot.getSpotName() + 
                                     ", 地址: " + spot.getSpotLoc());
                }
            }
            
            System.out.println("=== 測試完成 ===");
            
        } catch (Exception e) {
            System.err.println("=== 測試失敗 ===");
            e.printStackTrace();
        }
    }
}
