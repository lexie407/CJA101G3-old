<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>查詢單一景點</title>
</head>
<body>
	<h2>查詢單一景點</h2>
	<form method="post" action="<c:url value='/spot/SpotServlet'/>">
		請輸入景點編號：<input type="text" name="spotId" /> <input type="hidden"
			name="action" value="getOne_For_Display" /> <input type="submit"
			value="查詢" />
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
