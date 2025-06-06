<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.toiukha.notification.model.*"%>
<% 
java.util.Date now = new java.util.Date();
Timestamp nowTime = new Timestamp(now.getTime());
pageContext.setAttribute("nowTime", nowTime);
%>

<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>島遊 kak - 管理員後台 - 查詢通知</title>
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
                <li class="active"><a href="<%= request.getContextPath() %>/notification/back/notiAdmin.jsp"><span class="material-icons">notifications</span>通知管理</a></li>                
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
                    <li><a href="<%= request.getContextPath() %>/notification/back/notiAdmin.jsp">待發送通知清單</a></li>
                    <li><a href="<%= request.getContextPath() %>/notification/back/notiCreate.jsp">建立通知</a></li>
                    <li><a href="<%= request.getContextPath() %>/notification/back/notiSearch.jsp" class="active">查詢通知</a></li>
                </ul>
            </header>

            <main id="adminDashboardPage" class="content-area-main">
                <div class="admin-dashboard-container notification-center-container"> <h1>查詢通知</h1>

                    <div class="admin-notification-form">
                        <div class="widget">
                            <h2 style="margin-top: 0px; margin-bottom: 20px;">查詢條件</h2>
	                        <form id="notificationEditForm" action="<%= request.getContextPath() %>/notification/noti.do" method="post">
	                            <div class="form-group">
                                		<select id="notificationMemberId" name="memIds" multiple>
											<option value="1">會員1</option>
											<option value="2">會員2</option>
											<option value="3">會員3</option>
										</select>
	                            </div>
	
	                            <div class="form-group">
	                                <label for="notificationTimeStart">通知時間 (開始):</label>
	                                <input type="datetime-local" id="notificationTimeStart" name="notiSendAtStart">
	                            </div>
	
	                            <div class="form-group">
	                                <label for="notificationTimeEnd">通知時間 (結束):</label>
	                                <input type="datetime-local" id="notificationTimeEnd" name="notiSendAtEnd">
	                            </div>
	
	                            <div class="form-group">
	                                <label for="notificationTitle">通知標題:</label>
	                                <input type="text" id="notificationTitle" name="notiTitle" placeholder="輸入通知標題">
	                            </div>
	
	                            <div class="form-group full-width">
	                                <label for="notificationContent">通知內容:</label>
	                                <textarea id="notificationContent" name="notiCont" rows="4" placeholder="輸入通知內容"></textarea>
	                            </div>
	
	                            <div class="form-group">
	                                <label for="notificationStatus">通知狀態:</label>
	                                <select id="notificationStatus" name="notiStatus">
	                                    <option value="">-- 請選擇狀態 --</option>
	                                    <option value="0">已發送 - 未讀</option>
	                                    <option value="1">已讀</option>
	                                    <option value="2">使用者刪除</option>
	                                    <option value="3">管理員刪除</option>
	                                    <option value="4">待發送</option>
	                                </select>
	                            </div>
	
	                            <div class="form-actions">
	                                <button type="submit" class="material-button">
	                                    <span class="material-icons">search</span>
	                                    查詢
	                                </button>
	                                <input type="hidden" name="action" value="searchNoti">
	                            </div>
	                        </form>
                        </div>
                    </div>

                    <h2 style="margin-top: 40px; margin-bottom: 20px;">查詢結果</h2>
                    <table class="admin-table">
                        <thead>
                            <tr>
                                <th>通知時間</th>
                                <th>會員</th>
                                <th>狀態</th>
                                <th>標題</th>
                                <th>內容</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="NotificationVO" items="${list}">
                        			<tr class="noti-table-line" data-form-action="<%= request.getContextPath() %>/notification/noti.do" data-action="editNoti" data-notiId="${NotificationVO.notiId}" data-originalUri="<%= request.getServletPath() %>">
		                                <td><fmt:formatDate value="${NotificationVO.notiSendAt}" pattern="yyyy-MM-dd HH:mm"/></td>
		                                <td>${NotificationVO.memId}</td>
		                                <c:choose>
		                                	<c:when test="${NotificationVO.notiStatus == 0 && NotificationVO.notiSendAt < nowTime}">
		                                		<td>未讀</td>
		                                	</c:when>
		                                	<c:when test="${NotificationVO.notiStatus == 1}">
		                                		<td>已讀</td>
		                                	</c:when>
		                                	<c:when test="${NotificationVO.notiStatus == 2}">
		                                		<td>使用者刪除</td>
		                                	</c:when>
		                                	<c:when test="${NotificationVO.notiStatus == 3}">
		                                		<td>管理員刪除</td>
		                                	</c:when>
		                                	<c:otherwise>
		                                		<td>待發送</td>
		                                	</c:otherwise>
		                                </c:choose>
		                                <td>${NotificationVO.notiTitle}</td>
		                                <td>${NotificationVO.notiCont}</td>
                            		</tr>
                        	</c:forEach>
                        </tbody>
                    </table>
                </div>
            </main>

        </div>
    </div>
    
    <script src="<%= request.getContextPath() %>/notification/js/jquery-3.7.1.min.js"></script>
    <script src="<%= request.getContextPath() %>/notification/js/jquery.multi-select.js"></script>
    <script src="<%= request.getContextPath() %>/notification/js/notiSearch.js"></script>
    
    </body>
</html>