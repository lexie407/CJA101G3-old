<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.toiukha.spot.model.*"%>
<%
SpotService spotSvc = new SpotService();
List<SpotVO> list = spotSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有景點資料 - listAllSpot.jsp</title>
</head>
<body>
	<h2>所有景點資料</h2>
	<a href="addSpot.jsp">新增景點</a>
	<table border="1">
		<tr>
			<th>景點編號</th>
			<th>景點名稱</th>
			<th>建立者ID</th>
			<th>地址</th>
			<th>緯度</th>
			<th>經度</th>
			<th>狀態</th>
			<th>描述</th>
			<th>操作</th>
		</tr>
		<c:forEach var="spotVO" items="${list}">
			<tr>
				<td>${spotVO.spotId}</td>
				<td>${spotVO.spotName}</td>
				<td>${spotVO.crtId}</td>
				<td>${spotVO.spotLoc}</td>
				<td>${spotVO.spotLat}</td>
				<td>${spotVO.spotLng}</td>
				<td>${spotVO.spotStatus}</td>
				<td>${spotVO.spotDesc}</td>
				<td>
					<form style="display: inline;" method="post" action="SpotServlet">
						<input type="hidden" name="spotId" value="${spotVO.spotId}" /> <input
							type="hidden" name="action" value="getOne_For_Update" /> <input
							type="submit" value="修改" />
					</form>
					<form style="display: inline;" method="post" action="SpotServlet">
						<input type="hidden" name="spotId" value="${spotVO.spotId}" /> <input
							type="hidden" name="action" value="delete" /> <input
							type="submit" value="刪除" onclick="return confirm('確定要刪除嗎？');" />
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="select_page.jsp">查詢單一景點</a>
</body>
</html>
