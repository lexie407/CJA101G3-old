<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.toiukha.notification.model.*"%>

<!DOCTYPE html>
<html lang="ZH-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>島遊Kak-會員中心-通知</title>
    <link rel="stylesheet" href="./css/light.css">
    <link rel="stylesheet" href="./css/style.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body class="light">
    <div class="app-container">
        <aside class="left-nav">
            <div class="logo-area">
                <img src="https://via.placeholder.com/60x30/007bff/ffffff?text=島遊+kak" alt="島遊 kak">
                <span class="site-name">島遊 kak</span>
            </div>

            <ul>
                <li><a href="#"><span class="material-icons">home</span>首頁</a></li>
                <li><a href="#"><span class="material-icons">person</span>成員</a></li>
                <li><a href="#"><span class="material-icons">card_travel</span>行程</a></li>
                <li><a href="#"><span class="material-icons">groups</span>朋友圈</a></li>
                <li><a href="#"><span class="material-icons">store</span>商店</a></li>
                <li><a href="#"><span class="material-icons">edit</span>討論區</a></li>
                <li class="active"><a href="#"><span class="material-icons">account_circle</span>會員中心</a></li>
                <li><a href="#"><span class="material-icons">add_circle_outline</span>成為廠商</a></li>
            </ul>

            <div class="member-info-area">
                <img src="#" alt="User Avatar" class="member-avatar">
                <span class="member-name">會員名稱</span>
            </div>

            <!-- <div class="bottom-notification">
                <a href="#"><span class="material-icons">notifications</span>通知</a>
            </div> -->
            <div class="logout-area">
                <button class="logout-btn material-button">
                    <span class="material-icons">logout</span>
                    登出
                </button>
            </div>
        </aside>

        <div class="main-content">
            <header class="secondary-nav">
                <ul>
                    <li><a href="#" class="active">通知中心</a></li>
                    <li><a href="#">Video</a></li>
                    <li><a href="#">Photos</a></li>
                    <li><a href="#">Audio</a></li>
                    <li><a href="#">Tab</a></li>
                    <li><a href="#">Tab</a></li>
                    <li><a href="#">Tab</a></li>
                </ul>
            </header>

            <main class="content-area-main">
                <div class="notification-detail-container";">
                	<div class="botton-container">
                		<button class="back-to-list-btn material-button" data-backUri="<%= request.getContextPath() %>/notification/front/membersNotification.jsp">
	                        <span class="material-icons">arrow_back</span>
	                        返回通知列表
	                    </button>
                	</div>
                    <div class="notification-detail-card">
                    	<div class="button_obj">
		                	<button class="delete-btn material-button" data-action="<%=request.getContextPath()%>/notification/noti.do" data-notiId="${notificationVO.notiId}">
		                         <span class="material-icons">delete</span>
		                          刪除此通知
	                    	</button>
		                </div>
                        <h2 id="detail-notification-title" class="notification-detail-title">${notificationVO.notiTitle}</h2>
                        <p id="detail-notification-date" class="notification-detail-date"><fmt:formatDate value="${notificationVO.notiSendAt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                        <div id="detail-notification-message" class="notification-detail-message">${notificationVO.notiCont}</div>
                    </div>
                </div>
                
            </main>
        </div>
    </div>

    <script src="./js/membersDetailNotification.js"></script>
    
</body>
</html>