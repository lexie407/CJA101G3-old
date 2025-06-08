<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>景點詳情</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

.btn {
	padding: 5px 10px;
	margin: 5px;
	text-decoration: none;
}
</style>
</head>
<body>
	<h2>景點詳情</h2>

	 	<!-- Debug 資訊 --> 
<%-- 	<p style="color: blue;">Debug: spotVO = ${spotVO}</p> --%>

	<c:choose>
		<c:when test="${not empty spotVO}">
			<table>
				<tr>
					<th>景點編號</th>
					<td>${spotVO.spotId}</td>
				</tr>
				<tr>
					<th>景點名稱</th>
					<td>${spotVO.spotName}</td>
				</tr>
				<tr>
					<th>建立者ID</th>
					<td>${spotVO.crtId}</td>
				</tr>
				<tr>
					<th>地址</th>
					<td>${spotVO.spotLoc}</td>
				</tr>
				<tr>
					<th>狀態</th>
					<td><c:choose>
							<c:when test="${spotVO.spotStatus == 1}">啟用</c:when>
							<c:otherwise>停用</c:otherwise>
						</c:choose></td>
				</tr>
				<c:if test="${not empty spotVO.spotDesc}">
					<tr>
						<th>描述</th>
						<td>${spotVO.spotDesc}</td>
					</tr>
				</c:if>
			</table>

			<div style="margin-top: 16px;">
				<a
					href="${pageContext.request.contextPath}/spot/SpotServlet?action=getOne_For_Update&spotId=${spotVO.spotId}"
					class="btn">修改此景點</a> <a
					href="${pageContext.request.contextPath}/spot/SpotServlet?action=listAll"
					class="btn">回到列表</a> <a
					href="${pageContext.request.contextPath}/spot/select_page.jsp"
					class="btn">再次查詢</a>
			</div>
		</c:when>
		<c:otherwise>
			<div style="color: red;">查無資料，請重新查詢。</div>
			<div style="margin-top: 16px;">
				<a href="${pageContext.request.contextPath}/spot/select_page.jsp">再次查詢</a>
				<a
					href="${pageContext.request.contextPath}/spot/SpotServlet?action=listAll">回到列表</a>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>
