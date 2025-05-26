<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.toiukha.spot.model.SpotVO"%>
<%
SpotVO spotVO = (SpotVO) request.getAttribute("spotVO");
%>
<html>
<head>
<title>景點資料 - listOneSpot.jsp</title>
</head>
<body>
	<h2>景點資料</h2>
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
		</tr>
		<tr>
			<td><%=spotVO.getSpotId()%></td>
			<td><%=spotVO.getSpotName()%></td>
			<td><%=spotVO.getCrtId()%></td>
			<td><%=spotVO.getSpotLoc()%></td>
			<td><%=spotVO.getSpotLat()%></td>
			<td><%=spotVO.getSpotLng()%></td>
			<td><%=spotVO.getSpotStatus()%></td>
			<td><%=spotVO.getSpotDesc()%></td>
		</tr>
	</table>
	<br>
	<a href="listAllSpot.jsp">回所有景點列表</a>
</body>
</html>
