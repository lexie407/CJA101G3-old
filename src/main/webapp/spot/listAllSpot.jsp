<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
    <title>景點列表</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .btn { padding: 5px 10px; margin: 2px; text-decoration: none; }
        .btn-add { background-color: #4CAF50; color: white; }
    </style>
</head>
<body>
    <h2>景點列表</h2>
    
    <!-- 功能按鈕區 -->
    <div style="margin-bottom: 16px;">
        <a href="${pageContext.request.contextPath}/spot/addSpot.jsp" class="btn btn-add">新增景點</a>
        <a href="${pageContext.request.contextPath}/spot/select_page.jsp" class="btn">查詢景點</a>
        <a href="${pageContext.request.contextPath}/spot/SpotServlet?action=listAll" class="btn">重新整理列表</a>
    </div>

    <!-- 景點列表 -->
    <c:choose>
        <c:when test="${not empty spotList}">
            <table>
                <tr>
                    <th>景點編號</th>
                    <th>景點名稱</th>
                    <th>建立者ID</th>
                    <th>地址</th>
                    <th>狀態</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${spotList}" var="spot">
                    <tr>
                        <td>${spot.spotId}</td>
                        <td>${spot.spotName}</td>
                        <td>${spot.crtId}</td>
                        <td>${spot.spotLoc}</td>
                        <td>
                            <c:choose>
                                <c:when test="${spot.spotStatus == 1}">啟用</c:when>
                                <c:otherwise>停用</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/spot/SpotServlet?action=getOne_For_Display&spotId=${spot.spotId}" 
                               class="btn">查看</a>
                            <a href="${pageContext.request.contextPath}/spot/SpotServlet?action=getOne_For_Update&spotId=${spot.spotId}" 
                               class="btn">修改</a>
                            <form action="${pageContext.request.contextPath}/spot/SpotServlet" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="spotId" value="${spot.spotId}">
                                <button type="submit" class="btn" 
                                        onclick="return confirm('確定要刪除嗎？')">刪除</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <div style="color:red;">目前沒有任何景點資料。</div>
        </c:otherwise>
    </c:choose>
</body>
</html>
