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
    <title>島遊 kak - 管理員後台 - 通知詳情</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/notification/css/light.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/notification/css/style.css">
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

        <div class="main-content">
            <header class="secondary-nav">
                <ul>
                    <li><a href="<%= request.getContextPath() %>/notification/back/notiAdmin.jsp">待發送通知清單</a></li>
                    <li><a href="<%= request.getContextPath() %>/notification/back/notiCreate.jsp">建立通知</a></li>
                    <li><a href="<%= request.getContextPath() %>/notification/back/notiSearch.jsp">查詢通知</a></li>
                </ul>
            </header>

			<main id="adminNotificationEditPage" class="content-area-main hidden">
                <div class="single-notification-container notification-center-container">
                    <div class="single-notification-actions">
                        <button id="backToAdminNotificationsBtn" class="material-button" data-originalUri="${originalUri}">
                            <span class="material-icons">arrow_back</span>
                            返回
                        </button>
                        <c:if test="${notificationVO.notiSendAt > nowTime && notificationVO.notiStatus != 3}">
                        	<button id="deleteAdminNotificationBtn" class="material-button delete-btn" data-formAction="<%=request.getContextPath()%>/notification/noti.do" data-action="deleteNotiForAdmin" data-notiId="${notificationVO.notiId}" data-originalUri="${originalUri}">
	                            <span class="material-icons">delete</span>
	                            刪除此通知
                       		</button>
                        </c:if>
                    </div>

                    <div class="admin-notification-form">
                        <c:choose>
                        	<c:when test="${notificationVO.notiSendAt > nowTime && notificationVO.notiStatus != 3}">
                        		<h2>編輯通知</h2>
                        	</c:when>
                        	<c:otherwise>
                        		<h2>通知詳情</h2>
                        	</c:otherwise>
                        </c:choose>
                        <form id="notificationEditForm" method="post" action="<%=request.getContextPath()%>/notification/noti.do">
                        	<input type="hidden" name="action" value="updateNoti">
                        	<input type="hidden" name="notiId" value="${notificationVO.notiId}">
<!--                         	到時候要放session 的 adminID -->
                        	<input type="hidden" name="adminId" value="1">
                        	<input type="hidden" name="originalUri" value="${originalUri}">
                            <div class="form-group">
                                <label for="notificationMemberId">通知會員 ID:</label>
                                <input type="text" id="notificationMemberId" name="memId" value="${notificationVO.memId}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="notificationMemberName">會員名稱:</label>
                                <input type="text" id="notificationMemberName" value="開發中..." readonly>
                            </div>
                            <div class="form-group">
                                <label for="notificationStatus">通知狀態:</label>
                                <input type="hidden" name="notiStatus" value="${notificationVO.notiStatus}">
                                <c:choose>
                                	<c:when test="${notificationVO.notiStatus == 3}">
                                		<input type="text" id="notificationStatus" value="管理員刪除" readonly>
                                	</c:when>
                                	<c:when test="${notificationVO.notiStatus == 2}">
                                		<input type="text" id="notificationStatus" value="使用者刪除" readonly>
                                	</c:when>
                                	<c:when test="${notificationVO.notiStatus == 1}">
                                		<input type="text" id="notificationStatus" value="已讀" readonly>
                                	</c:when>
                                	<c:when test="${notificationVO.notiStatus == 0 && notificationVO.notiSendAt < nowTime}">
                                		<input type="text" id="notificationStatus" value="未讀" readonly>
                                	</c:when>
                                	<c:when test="${notificationVO.notiStatus == 0 && notificationVO.notiSendAt > nowTime}">
                                		<input type="text" id="notificationStatus" value="未發送" readonly>
                                	</c:when>
                                </c:choose>
                            </div>
                            <div class="form-group">
                                <label for="notificationCreatedAt">通知建立時間:</label>
                                <input type="text" id="notificationCreatedAt" value="<fmt:formatDate value='${notificationVO.notiCreatedAt}' pattern='yyyy-MM-dd HH:mm'/>" readonly>
                            </div>
                            <c:choose>
                            	<c:when test="${notificationVO.notiSendAt > nowTime && notificationVO.notiStatus != 3}">
                            		<div class="form-group">
		                                <label for="notificationSentAt">通知發送時間:</label>
		                                <input type="datetime-local" name="notiSendAt" value="${notificationVO.notiSendAt}" id="notificationSentAt" required>
                            		</div>
		                            <div class="form-group">
		                                <label for="notificationTitle">通知標題:</label>
		                                <input type="text" id="notificationTitle" name="notiTitle" value="${notificationVO.notiTitle}" required>
		                            </div>
		                            <div class="form-group full-width">
		                                <label for="notificationContent">通知內容:</label>
		                                <textarea id="notificationContent" name="notiCont" rows="8" required>${notificationVO.notiCont}</textarea>
		                            </div>
                            	</c:when>
                            	<c:otherwise>
                            		<div class="form-group">
		                                <label for="notificationSentAt">通知發送時間:</label>
		                                <input type="datetime-local" value="${notificationVO.notiSendAt}" id="notificationSentAt" readonly>
                            		</div>
		                            <div class="form-group">
		                                <label for="notificationTitle">通知標題:</label>
		                                <input type="text" id="notificationTitle" value="${notificationVO.notiTitle}" readonly>
		                            </div>
		                            <div class="form-group full-width">
		                                <label for="notificationContent">通知內容:</label>
		                                <textarea id="notificationContent" rows="8" readonly>${notificationVO.notiCont}</textarea>
		                            </div>
                            	</c:otherwise>
                            </c:choose>
                            <c:if test="${notificationVO.notiSendAt > nowTime && notificationVO.notiStatus != 3}">
                            	<div class="form-actions">
	                                <button type="submit" class="material-button">
	                                    <span class="material-icons">check_circle</span>
	                                    確定修改
	                                </button>
                            	</div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </main>

        </div>
    </div>
    
    <script src="<%=request.getContextPath()%>/notification/js/notiEdit.js"></script>
    
    </body>
</html>