<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.toiukha.notification.model.*"%>

<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>島遊 kak - 管理員後台</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/notification/css/light.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/notification/css/style.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/notification/css/multi-select.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/notification/css/custom-material-multiselect.css">
</head>
<body class="light">
    <div class="app-container">
        <aside class="left-nav">
            <div class="logo-area">
                <span class="site-name">島遊 kak 管理</span>
            </div>

            <div class="member-info-area">
                <span class="member-name">管理員：Leo</span>
            </div>

            <ul>
                <li><a href="#"><span class="material-icons">dashboard</span>儀表板</a></li>
                <li><a href="#"><span class="material-icons">people</span>使用者管理</a></li>
                <li><a href="#"><span class="material-icons">article</span>內容管理</a></li>
                <li><a href="#"><span class="material-icons">shopping_cart</span>訂單管理</a></li>
                <li><a href="#"><span class="material-icons">analytics</span>數據分析</a></li>
                <li><a href="#"><span class="material-icons">settings</span>系統設定</a></li>
                <li><a href="#"><span class="material-icons">history</span>操作日誌</a></li>
                <li class="active"><a href="#"><span class="material-icons">notifications</span>通知管理</a></li>                
            </ul>
<!--             <div class="bottom-notification"> -->
<!--                 <a href="#"><span class="material-icons">notifications</span>通知 (後台)</a> -->
<!--             </div> -->
            <div class="logout-area">
                <button class="logout-btn material-button">
                    <span class="material-icons">logout</span>
                    登出後台
                </button>
            </div>
        </aside>

        <div class="main-content">
            <header class="secondary-nav">
                <ul>
                    <li><a href="<%= request.getContextPath() %>/notification/back/notiAdmin.jsp">待發送通知清單</a></li>
                    <li><a href="<%= request.getContextPath() %>/notification/back/notiCreate.jsp" class="active">建立通知</a></li>
                    <li><a href="<%= request.getContextPath() %>/notification/back/notiSearch.jsp">查詢通知</a></li>
                </ul>
            </header>

            <main id="adminNotificationEditPage" class="content-area-main hidden">
                <div class="single-notification-container notification-center-container">
<!--                     <div class="single-notification-actions"> -->
<!--                         <button id="backToAdminNotificationsBtn" class="material-button"> -->
<!--                             <span class="material-icons">arrow_back</span> -->
<!--                             返回 -->
<!--                         </button> -->
<!--                     </div> -->

                    <div class="admin-notification-form">
                    <input type="hidden" id="addMsgs" name="addMsgs" value="${addMsgs}">
                        <h2>新增通知</h2>
                        <form id="notificationEditForm" method="post" action="<%=request.getContextPath()%>/notification/noti.do">
                        	<input type="hidden" name="action" value="createNoti">
<!--                         	到時候要放session 的 adminID -->
                        	<input type="hidden" name="adminId" value="1">
	                        			<div class="form-group">
											<select id="notificationMemberId" name="memIds" multiple>
												<option value="1">會員1</option>
												<option value="2">會員2</option>
												<option value="3">會員3</option>
											</select>
	                            		</div>
                           				<div class="form-group">
	                                		<label for="notificationSentAt">通知發送時間:</label>
	                                		<input type="datetime-local" name="notiSendAt" id="notificationSentAt" min="" required>
                           				</div>
	                            		<div class="form-group">
	                                		<label for="notificationTitle">通知標題:</label>
	                                		<input type="text" id="notificationTitle" name="notiTitle" required>
	                            		</div>
	                            		<div class="form-group full-width">
	                                		<label for="notificationContent">通知內容:</label>
	                                		<textarea id="notificationContent" name="notiCont" rows="8" required></textarea>
	                           			</div>
                            	<div class="form-actions">
	                                <button type="submit" class="material-button">
	                                    <span class="material-icons">check_circle</span>
	                                    確定新增
	                                </button>
                            	</div>
                        </form>
                    </div>
                </div>
            </main>

        </div>
    </div>
    
    <script src="<%= request.getContextPath() %>/notification/js/jquery-3.7.1.min.js"></script>
    <script src="<%= request.getContextPath() %>/notification/js/jquery.multi-select.js"></script>
    <script src="<%= request.getContextPath() %>/notification/js/notiSearch.js"></script>
    
    </body>
</html>