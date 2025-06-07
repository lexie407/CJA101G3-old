package com.toiukha.hibernate;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InitializerListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("context started");
        HibernateUtil.getSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("context ended");

        // --- 1. 關閉 Hibernate SessionFactory ---
        HibernateUtil.shutdown();

        // --- 2. 優先取消註冊 JDBC 驅動程式 ---
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == sce.getServletContext().getClassLoader()) {
                try {
                    DriverManager.deregisterDriver(driver);
                    System.out.println(String.format("[InitializerListener] 取消註冊 JDBC 驅動程式: %s", driver.getClass().getName()));
                } catch (SQLException ex) {
                    System.err.println(String.format("[InitializerListener] 取消註冊 JDBC 驅動程式失敗: %s", driver.getClass().getName()));
                    ex.printStackTrace();
                }
            }
        }

        AbandonedConnectionCleanupThread.checkedShutdown();
		System.out.println("[InitializerListener] 成功關閉 MySQL JDBC 的 AbandonedConnectionCleanupThread。");

        // --- 結尾 ---
        System.out.println("[InitializerListener] 所有應用程式資源清理完成。");
    }
}
