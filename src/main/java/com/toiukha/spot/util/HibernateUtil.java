package com.toiukha.spot.util;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // 1. 檢查檔案路徑並載入 config.properties
                System.out.println("【HibernateUtil DEBUG】開始載入 config.properties...");
                
                // 先檢查檔案是否存在
                URL configUrl = HibernateUtil.class.getClassLoader().getResource("config.properties");
                if (configUrl != null) {
                    System.out.println("【HibernateUtil DEBUG】找到檔案路徑：" + configUrl.getPath());
                } else {
                    System.out.println("【HibernateUtil DEBUG】在 classpath 根目錄找不到 config.properties");
                }
                
                InputStream input = HibernateUtil.class.getClassLoader()
                    .getResourceAsStream("config.properties");
                
                if (input == null) {
                    // 嘗試其他載入方式
                    System.out.println("【HibernateUtil DEBUG】嘗試其他載入方式...");
                    input = HibernateUtil.class.getResourceAsStream("/config.properties");
                }
                
                if (input == null) {
                    // 列出 classpath 中的檔案幫助除錯
                    System.err.println("【HibernateUtil ERROR】找不到 config.properties！");
                    System.err.println("請確認檔案位於以下任一位置：");
                    System.err.println("1. src/main/resources/config.properties");
                    System.err.println("2. src/config.properties");
                    throw new RuntimeException("找不到 config.properties 檔案！");
                }
                
                Properties dbSettings = new Properties();
                dbSettings.load(input);
                input.close();
                
                // Debug：確認載入成功並檢查必要屬性
                System.out.println("【HibernateUtil DEBUG】成功載入 config.properties");
                System.out.println("【HibernateUtil DEBUG】JDBC URL: " + dbSettings.getProperty("jdbc.url"));
                System.out.println("【HibernateUtil DEBUG】JDBC User: " + dbSettings.getProperty("jdbc.user"));
                
                // 檢查必要屬性是否存在
                String[] requiredProps = {"jdbc.driver", "jdbc.url", "jdbc.user", "jdbc.password"};
                for (String prop : requiredProps) {
                    if (dbSettings.getProperty(prop) == null) {
                        throw new RuntimeException("缺少必要屬性：" + prop);
                    }
                }

                // 2. 建立 Hibernate 設定
                Properties hibernateProps = new Properties();
                hibernateProps.setProperty("hibernate.connection.driver_class", dbSettings.getProperty("jdbc.driver"));
                hibernateProps.setProperty("hibernate.connection.url", dbSettings.getProperty("jdbc.url"));
                hibernateProps.setProperty("hibernate.connection.username", dbSettings.getProperty("jdbc.user"));
                hibernateProps.setProperty("hibernate.connection.password", dbSettings.getProperty("jdbc.password"));
                
                // 從 config.properties 讀取 Hibernate 設定，如果沒有則使用預設值
                hibernateProps.setProperty("hibernate.dialect", 
                    dbSettings.getProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"));
                hibernateProps.setProperty("hibernate.show_sql", 
                    dbSettings.getProperty("hibernate.show_sql", "true"));
                hibernateProps.setProperty("hibernate.format_sql", 
                    dbSettings.getProperty("hibernate.format_sql", "true"));
                hibernateProps.setProperty("hibernate.hbm2ddl.auto", 
                    dbSettings.getProperty("hibernate.hbm2ddl.auto", "validate"));

                System.out.println("【HibernateUtil DEBUG】Hibernate 設定完成");

                // 3. 建立 StandardServiceRegistry
                registry = new StandardServiceRegistryBuilder()
                    .applySettings(hibernateProps)
                    .build();

                System.out.println("【HibernateUtil DEBUG】StandardServiceRegistry 建立成功");

                // 4. 註冊所有 Entity 類別
                MetadataSources sources = new MetadataSources(registry);
                sources.addAnnotatedClass(com.toiukha.spot.model.SpotVO.class);
                
                System.out.println("【HibernateUtil DEBUG】已註冊 SpotVO Entity");

                // 5. 建立 Metadata 和 SessionFactory
                System.out.println("【HibernateUtil DEBUG】開始建立 SessionFactory...");
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
                
                System.out.println("【HibernateUtil DEBUG】SessionFactory 建立成功！");

            } catch (Exception e) {
                System.err.println("【HibernateUtil ERROR】初始化失敗：" + e.getMessage());
                e.printStackTrace();
                
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
                throw new RuntimeException("Hibernate 初始化失敗: " + e.getMessage());
            }
        }
        return sessionFactory;
    }

    // 測試連線的方法
    public static boolean testConnection() {
        try {
            SessionFactory factory = getSessionFactory();
            if (factory != null) {
                System.out.println("【HibernateUtil DEBUG】連線測試成功！");
                return true;
            }
        } catch (Exception e) {
            System.err.println("【HibernateUtil ERROR】連線測試失敗：" + e.getMessage());
        }
        return false;
    }

    public static void shutdown() {
        System.out.println("【HibernateUtil DEBUG】開始關閉 Hibernate...");
        if (sessionFactory != null) {
            sessionFactory.close();
            System.out.println("【HibernateUtil DEBUG】SessionFactory 已關閉");
        }
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
            System.out.println("【HibernateUtil DEBUG】StandardServiceRegistry 已銷毀");
        }
    }
}
