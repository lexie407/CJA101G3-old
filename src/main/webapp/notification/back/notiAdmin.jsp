<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.toiukha.notification.model.*"%>
<% 
NotificationService notificationService = new NotificationService();
List<NotificationVO> list = notificationService.getAllNoti();
pageContext.setAttribute("list", list);

java.util.Date now = new java.util.Date();
Timestamp nowTime = new Timestamp(now.getTime());
pageContext.setAttribute("nowTime", nowTime);
%>

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

        <div class="main-content" data-originalUri="<%= request.getServletPath() %>">
            <header class="secondary-nav">
                <ul>
                    <li><a href="<%= request.getContextPath() %>/notification/back/notiAdmin.jsp" class="active">待發送通知清單</a></li>
                    <li><a href="<%= request.getContextPath() %>/notification/back/notiCreate.jsp">建立通知</a></li>
                    <li><a href="<%= request.getContextPath() %>/notification/back/notiSearch.jsp">查詢通知</a></li>
                </ul>
            </header>

            <main id="adminDashboardPage" class="content-area-main">
                <div class="admin-dashboard-container notification-center-container"> <h1>待發送通知</h1>
                    <p>以下的通知都是已建立待發送。</p>

                    <div class="dashboard-widgets">
                        <div class="widget">
                            <h3><span class="material-icons">rocket_launch</span> 待發送通知數</h3>
                            <p class="large-number">###</p>
                        </div>
                    </div>

                    <h2 style="margin-top: 40px; margin-bottom: 20px;">待發送通知清單</h2>
                    <table class="admin-table">
                        <thead>
                            <tr>
                                <th>通知時間</th>
                                <th>會員</th>
                                <th>標題</th>
                                <th>內容</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="NotificationVO" items="${list}">
                        		<c:if test="${NotificationVO.notiStatus != 3 && NotificationVO.notiSendAt > nowTime}">
                        			<tr class="noti-table-line" data-form-action="<%= request.getContextPath() %>/notification/noti.do" data-action="editNoti" data-notiId="${NotificationVO.notiId}">
		                                <td><fmt:formatDate value="${NotificationVO.notiSendAt}" pattern="yyyy-MM-dd HH:mm"/></td>
		                                <td>${NotificationVO.memId}</td>
		                                <td>${NotificationVO.notiTitle}</td>
		                                <td>${NotificationVO.notiCont}</td>
                            		</tr>
                        		</c:if>
                        	</c:forEach>
                        </tbody>
                    </table>
                </div>
            </main>

        </div>
    </div>
    
    <script src="<%= request.getContextPath() %>/notification/js/notiAdmin.js"></script>
    
    </body>
</html>