<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>修改景點</title>
    <style>
        body {
            font-family: "微軟正黑體", Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
            color: #333;
        }
        
        .container {
            max-width: 700px;
            margin: 0 auto;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        
        .header {
            background-color: #ffffff;
            border-bottom: 3px solid #007bff;
            padding: 30px;
            text-align: center;
        }
        
        .header h2 {
            margin: 0;
            font-size: 28px;
            color: #007bff;
            font-weight: 600;
        }
        
        .header p {
            margin: 10px 0 0 0;
            color: #6c757d;
            font-size: 16px;
        }
        
        .spot-info {
            background-color: #f8f9fa;
            padding: 20px;
            border-bottom: 1px solid #dee2e6;
        }
        
        .spot-id {
            display: inline-block;
            background-color: #007bff;
            color: white;
            padding: 8px 16px;
            border-radius: 20px;
            font-weight: 600;
            font-size: 14px;
        }
        
        .form-content {
            padding: 40px;
            background-color: #ffffff;
        }
        
        .form-group {
            margin-bottom: 25px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #495057;
            font-size: 14px;
        }
        
        .required {
            color: #dc3545;
        }
        
        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 12px 16px;
            border: 2px solid #e9ecef;
            border-radius: 8px;
            font-size: 16px;
            transition: all 0.3s ease;
            box-sizing: border-box;
            background-color: #ffffff;
        }
        
        .form-group input:focus,
        .form-group select:focus,
        .form-group textarea:focus {
            outline: none;
            border-color: #007bff;
            box-shadow: 0 0 0 3px rgba(0, 123, 255, 0.1);
        }
        
        .form-row {
            display: flex;
            gap: 20px;
        }
        
        .form-row .form-group {
            flex: 1;
        }
        
        .btn-group {
            display: flex;
            gap: 15px;
            justify-content: center;
            margin-top: 30px;
        }
        
        .btn {
            padding: 12px 30px;
            border: 2px solid transparent;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            text-align: center;
            transition: all 0.3s ease;
        }
        
        .btn-primary {
            background-color: #007bff;
            color: white;
            border-color: #007bff;
        }
        
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0, 123, 255, 0.3);
        }
        
        .btn-secondary {
            background-color: #6c757d;
            color: white;
            border-color: #6c757d;
        }
        
        .btn-secondary:hover {
            background-color: #545b62;
            border-color: #545b62;
            transform: translateY(-2px);
        }
        
        .btn-outline {
            background-color: transparent;
            color: #007bff;
            border-color: #007bff;
        }
        
        .btn-outline:hover {
            background-color: #007bff;
            color: white;
        }
        
        .error-messages {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            color: #721c24;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 25px;
        }
        
        .status-badge {
            display: inline-block;
            padding: 6px 12px;
            border-radius: 15px;
            font-size: 12px;
            font-weight: 600;
            margin-left: 10px;
        }
        
        .status-active {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        
        .status-inactive {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h2>✏️ 修改景點</h2>
            <p>編輯景點資訊</p>
        </div>
        
        <c:choose>
            <c:when test="${not empty spotVO}">
                <div class="spot-info">
                    <span class="spot-id">ID: ${spotVO.spotId}</span>
                    <span class="status-badge ${spotVO.spotStatus == 1 ? 'status-active' : 'status-inactive'}">
                        ${spotVO.spotStatus == 1 ? '🟢 啟用中' : '🔴 已停用'}
                    </span>
                </div>
                
                <div class="form-content">
                    <c:if test="${not empty errorMsgs}">
                        <div class="error-messages">
                            <strong>⚠️ 請修正以下錯誤：</strong>
                            <ul style="margin: 10px 0 0 0; padding-left: 20px;">
                                <c:forEach var="message" items="${errorMsgs}">
                                    <li>${message}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/spot/SpotServlet" method="post">
                        <input type="hidden" name="action" value="updateSpot">
                        <input type="hidden" name="spotId" value="${spotVO.spotId}">
                        
                        <div class="form-group">
                            <label>🏞️ 景點名稱 <span class="required">*</span></label>
                            <input type="text" name="spotName" value="${spotVO.spotName}" required>
                        </div>
                        
                        <div class="form-group">
                            <label>📍 地址 <span class="required">*</span></label>
                            <input type="text" name="spotLoc" value="${spotVO.spotLoc}" required>
                        </div>
                        
                        <div class="form-row">
                            <div class="form-group">
                                <label>👤 建立者ID</label>
                                <input type="number" name="crtId" value="${spotVO.crtId}" readonly 
                                       style="background-color: #f8f9fa; color: #6c757d;">
                            </div>
                            
                            <div class="form-group">
                                <label>📊 狀態</label>
                                <select name="spotStatus">
                                    <option value="1" ${spotVO.spotStatus == 1 ? 'selected' : ''}>🟢 啟用</option>
                                    <option value="0" ${spotVO.spotStatus == 0 ? 'selected' : ''}>🔴 停用</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label>📝 景點描述</label>
                            <textarea name="spotDesc" rows="4" placeholder="請輸入景點描述...">${spotVO.spotDesc}</textarea>
                        </div>
                        
                        <div class="btn-group">
                            <button type="submit" class="btn btn-primary">💾 儲存修改</button>
                            <a href="${pageContext.request.contextPath}/spot/SpotServlet?action=getOne_For_Display&spotId=${spotVO.spotId}" 
                               class="btn btn-outline">👁️ 查看詳情</a>
                            <a href="${pageContext.request.contextPath}/spot/SpotServlet?action=listAll" 
                               class="btn btn-secondary">📋 回到列表</a>
                        </div>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <div class="form-content" style="text-align: center; padding: 60px 40px;">
                    <div style="font-size: 60px; margin-bottom: 20px; color: #6c757d;">😕</div>
                    <h3 style="color: #6c757d;">查無景點資料</h3>
                    <p style="color: #6c757d;">請從列表重新選擇要修改的景點</p>
                    <div style="margin-top: 30px;">
                        <a href="${pageContext.request.contextPath}/spot/SpotServlet?action=listAll" 
                           class="btn btn-primary">📋 回到列表</a>
                        <a href="${pageContext.request.contextPath}/spot/select_page.jsp" 
                           class="btn btn-secondary">🔍 重新查詢</a>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
