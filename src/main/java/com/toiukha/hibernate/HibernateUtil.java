package com.toiukha.hibernate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	// 儲存服務註冊表的靜態變數
	private static StandardServiceRegistry registry;
	
	// 靜態 final 屬性，在類別載入時立即初始化 SessionFactory
	private static final SessionFactory sessionFactory = createSessionFactory();
	
	// 私有建構子，防止外部直接實例化
    // (雖然這個範例沒有明確寫出，但通常會這樣做來強制單例，
    //  在這個實作中，因為 sessionFactory 是靜態 final 的，所以外部無法建立多個實例)
    // private HibernateUtil() {}
	
	
	//獲取 SessionFactory 實例的公共方法
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	
	
    //私有方法，用於創建 SessionFactory。
    //在類別載入時被呼叫一次。
	//@return 建立好的 SessionFactory 實例
	//@throws ExceptionInInitializerError 如果 SessionFactory 建立失敗
	private static SessionFactory createSessionFactory() {
		try {
			// 載入 config.properties
			Properties props = new Properties();
			InputStream inputStream = HibernateUtil.class.getClassLoader().getResourceAsStream("config.properties");
			if (inputStream == null) {
				throw new RuntimeException("無法找到 config.properties");
			}
			props.load(inputStream);
			
			// 建立 Hibernate 配置
			Configuration configuration = new Configuration();
			configuration.setProperty("hibernate.connection.driver_class", props.getProperty("jdbc.driver"));
			configuration.setProperty("hibernate.connection.url", props.getProperty("jdbc.url"));
			configuration.setProperty("hibernate.connection.username", props.getProperty("jdbc.user"));
			configuration.setProperty("hibernate.connection.password", props.getProperty("jdbc.password"));
			configuration.setProperty("hibernate.dialect", props.getProperty("hibernate.dialect"));
			configuration.setProperty("hibernate.show_sql", props.getProperty("hibernate.show_sql"));
			configuration.setProperty("hibernate.format_sql", props.getProperty("hibernate.format_sql"));
			configuration.setProperty("hibernate.hbm2ddl.auto", props.getProperty("hibernate.hbm2ddl.auto"));
			
			// 建立服務註冊表
			registry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.build();
			
			// 建立 SessionFactory
			SessionFactory sessionFactory = new MetadataSources(registry)
					.buildMetadata()
					.buildSessionFactory();
			
			return sessionFactory;
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	
	//關閉 SessionFactory 並銷毀服務註冊表。
	//應用程式關閉時應呼叫此方法以釋放資源。
	public static void shutdown() {
		if (registry != null)
			// 銷毀服務註冊表，這會關閉所有相關的服務，包括資料庫連線池。
			StandardServiceRegistryBuilder.destroy(registry);
	}
	
}
