<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.toiukha.notification.model.*"%>
<%
NotificationService notiService = new NotificationService();
List<NotificationVO> list = notiService.getMemNoti(1);
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="ZH-Hant">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>島遊Kak-會員中心-通知</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/notification/css/light.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/notification/css/style.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body class="light">
	<div class="app-container">
		<aside class="left-nav">
			<div class="logo-area">
				<img src="#" alt="島遊 kak"> <span class="site-name">島遊kha</span>
			</div>

			<ul>
				<li><a href="#"><span class="material-icons">home</span>首頁</a></li>
				<li><a href="#"><span class="material-icons">card_travel</span>行程</a></li>
				<li><a href="#"><span class="material-icons">groups</span>揪團</a></li>
				<li><a href="#"><span class="material-icons">store</span>商店</a></li>
				<li><a href="#"><span class="material-icons">edit</span>討論區</a></li>
				<hr>
				<li class="active"><a href="#"><span class="material-icons">account_circle</span>會員中心</a></li>
				<li><a href="#"><span class="material-icons">add_circle_outline</span>成為廠商</a></li>
			</ul>



			<!-- <div class="bottom-notification">
                <a href="#"><span class="material-icons">notifications</span>通知</a>
            </div> -->

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

					<li>
						<div class="member-info-area">
							<img src="#" alt="User Avatar" class="member-avatar"> <span
								class="member-name">會員名稱</span>
						</div>
					</li>
					<li>
						<div class="logout-area">
							<button class="logout-btn material-button">
								<span class="material-icons">logout</span> 登出
							</button>
						</div>
					</li>
				</ul>
			</header>

			<main class="content-area-main">
				<div class="notification-center-container">
					<div class="notification-actions">
						<label class="checkbox-container"> <input type="checkbox"
							class="selectAllNotifications"> <span class="checkmark"></span>
							全選通知
						</label>
						<button class="delete-selected-btn material-button"
							data-action="<%=request.getContextPath()%>/notification/noti.do">
							<span class="material-icons">delete</span> 刪除所選通知
						</button>
					</div>

					<div class="notification-list">
						<c:forEach var="NotificationVO" items="${list}">
							<!--先篩選已讀與未讀的通知 -->
							<c:if
								test="${NotificationVO.notiStatus == 1 || NotificationVO.notiStatus == 0}">
								<c:choose>
									<c:when test="${NotificationVO.notiStatus == 0}">
										<div class="notification-item unread">
									</c:when>
									<c:otherwise>
										<div class="notification-item">
									</c:otherwise>
								</c:choose>
								<label class="checkbox-container"
									for="notiId_${NotificationVO.notiId}"> <input
									type="checkbox" class="notification-checkbox" name="notiId"
									value="${NotificationVO.notiId}"
									id="notiId_${NotificationVO.notiId}"> <span
									class="checkmark"></span>
								</label>
								<div class="notification-content">
									<div class="notification-header">
										<span class="notification-title">${NotificationVO.notiTitle}</span>
										<span class="notification-date"><fmt:formatDate
												value="${NotificationVO.notiSendAt}"
												pattern="yyyy-MM-dd HH:mm:ss" />
											</p></span>
									</div>
									<p class="notification-message">${NotificationVO.notiCont}</p>
								</div>
								<div class="notification-form-data"
									data-action="<%=request.getContextPath()%>/notification/noti.do"
									data-noti-id="${NotificationVO.notiId}"
									data-noti-sta="${NotificationVO.notiStatus}">
									<input type="hidden" name="action" value="getNotiDetail">
									<input type="hidden" name="notiId"
										value="${NotificationVO.notiId}"> <input type="hidden"
										name="notiStatus" value="${NotificationVO.notiStatus}">
								</div>
					</div>
					</c:if>
					</c:forEach>
				</div>
		</div>
		</main>
	</div>
	</div>

	<script
		src="<%=request.getContextPath()%>/notification/js/membersNotification.js"></script>

</body>
</html>