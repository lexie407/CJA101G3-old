package com.toiukha.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
			
			// 1. 建立 StandardServiceRegistry
            // StandardServiceRegistry 是 Hibernate 5.x 之後用於管理各種服務的核心組件。
            // .configure() 會自動載入 classpath 根目錄下的 hibernate.cfg.xml 設定檔。
            // .build() 則根據配置檔建立並返回服務註冊表。
			registry = new StandardServiceRegistryBuilder()
					.configure()
					.build();
			
			// 2. 根據服務註冊表建立 SessionFactory
            // MetadataSources 負責從配置檔中讀取實體映射元數據 (如 @Entity, @Table 等)。
            // .buildMetadata() 構建元數據模型。
            // .buildSessionFactory() 最終使用這些元數據和服務註冊表來建立 SessionFactory 實例。
			SessionFactory sessionFactory = new MetadataSources(registry)
					.buildMetadata()
					.buildSessionFactory();
			
			return sessionFactory;
			
		} catch (Exception e) {
			
			// 捕獲在建立 SessionFactory 過程中可能發生的任何異常。
			// 列印堆疊追蹤，便於除錯。
			e.printStackTrace();
			// 拋出 ExceptionInInitializerError，表示在靜態初始化塊中發生了異常，
            // 通常是因為配置錯誤或資料庫連線問題。
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
