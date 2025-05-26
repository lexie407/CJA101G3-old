<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.toiukha.spot.model.SpotVO"%>
<%
// 這是 SpotServlet 新增失敗時存入 request 的 spotVO 物件
SpotVO spotVO = (SpotVO) request.getAttribute("spotVO");
%>

<html>
<head>
<title>景點資料新增 - addSpot.jsp</title>
</head>
<body>
	<h2>新增景點資料</h2>

	<form method="post" action="<c:url value='/spot/SpotServlet'/>">
		名稱：<input type="text" name="spotName"
			value="<%=spotVO != null ? spotVO.getSpotName() : ""%>" /><br>
		建立者ID：<input type="text" name="crtId"
			value="<%=spotVO != null ? spotVO.getCrtId() : ""%>" /><br> 地址：<input
			type="text" name="spotLoc"
			value="<%=spotVO != null ? spotVO.getSpotLoc() : ""%>" /><br>
		緯度：<input type="text" name="spotLat"
			value="<%=spotVO != null ? spotVO.getSpotLat() : ""%>" /><br>
		經度：<input type="text" name="spotLng"
			value="<%=spotVO != null ? spotVO.getSpotLng() : ""%>" /><br>
		狀態：<input type="text" name="spotStatus"
			value="<%=spotVO != null ? spotVO.getSpotStatus() : ""%>" /><br>
		描述：<input type="text" name="spotDesc"
			value="<%=spotVO != null ? spotVO.getSpotDesc() : ""%>" /><br>
		<input type="hidden" name="action" value="insert" /> <input
			type="submit" value="新增" />
	</form>

	<!-- 錯誤訊息顯示區塊 -->
	<c:if test="${not empty errorMsgs}">
		<font color="red">
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>

	<a href="listAllSpot.jsp">回所有景點列表</a>
</body>
</html>
