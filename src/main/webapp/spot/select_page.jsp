<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>查詢景點</title>
    <style>
        body {
            font-family: "微軟正黑體", Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
            color: #333;
        }
        
        .container {
            max-width: 500px;
            margin: 50px auto;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        
        .header {
            background-color: #ffffff;
            border-bottom: 3px solid #007bff;
            padding: 40px 30px;
            text-align: center;
        }
        
        .header h2 {
            margin: 0;
            font-size: 32px;
            color: #007bff;
            font-weight: 600;
        }
        
        .header p {
            margin: 15px 0 0 0;
            color: #6c757d;
            font-size: 16px;
        }
        
        .search-content {
            padding: 50px 40px;
            text-align: center;
            background-color: #ffffff;
        }
        
        .icon {
            font-size: 60px;
            margin-bottom: 20px;
            color: #007bff;
        }
        
        .search-box {
            position: relative;
            margin: 30px 0;
        }
        
        .search-input {
            width: 100%;
            padding: 18px 25px;
            font-size: 18px;
            border: 3px solid #e9ecef;
            border-radius: 50px;
            text-align: center;
            transition: all 0.3s ease;
            box-sizing: border-box;
            background-color: #ffffff;
        }
        
        .search-input:focus {
            outline: none;
            border-color: #007bff;
            box-shadow: 0 0 0 4px rgba(0, 123, 255, 0.1);
            transform: scale(1.02);
        }
        
        .search-btn {
            background-color: #007bff;
            color: white;
            border: 2px solid #007bff;
            padding: 18px 40px;
            font-size: 18px;
            font-weight: 600;
            border-radius: 50px;
            cursor: pointer;
            transition: all 0.3s ease;
            margin-top: 20px;
            min-width: 150px;
        }
        
        .search-btn:hover {
            background-color: #0056b3;
            border-color: #0056b3;
            transform: translateY(-3px);
            box-shadow: 0 6px 15px rgba(0, 123, 255, 0.3);
        }
        
        .quick-actions {
            display: flex;
            gap: 15px;
            justify-content: center;
            margin-top: 40px;
            flex-wrap: wrap;
        }
        
        .quick-btn {
            padding: 12px 25px;
            background-color: #f8f9fa;
            color: #495057;
            text-decoration: none;
            border-radius: 25px;
            font-weight: 500;
            transition: all 0.3s ease;
            border: 2px solid #e9ecef;
        }
        
        .quick-btn:hover {
            background-color: #e9ecef;
            transform: translateY(-2px);
            border-color: #007bff;
            color: #007bff;
        }
        
        .error-messages {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            color: #721c24;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 30px;
            text-align: left;
        }
        
        .error-messages ul {
            margin: 10px 0 0 0;
            padding-left: 20px;
        }
        
        .search-hint {
            color: #6c757d;
            font-size: 14px;
            margin-top: 15px;
            padding: 15px;
            background-color: #f8f9fa;
            border-radius: 8px;
            border-left: 4px solid #007bff;
        }
        
        .feature-list {
            text-align: left;
            margin-top: 30px;
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 8px;
        }
        
        .feature-list h4 {
            margin: 0 0 15px 0;
            color: #495057;
            font-size: 16px;
        }
        
        .feature-list ul {
            margin: 0;
            padding-left: 20px;
            color: #6c757d;
        }
        
        .feature-list li {
            margin-bottom: 8px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <div class="icon">🔍</div>
            <h2>景點查詢</h2>
            <p>輸入景點編號查看詳細資訊</p>
        </div>
        
        <div class="search-content">
            <!-- 錯誤訊息顯示 -->
            <c:if test="${not empty errorMsgs}">
                <div class="error-messages">
                    <strong>⚠️ 查詢失敗：</strong>
                    <ul>
                        <c:forEach var="message" items="${errorMsgs}">
                            <li>${message}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/spot/SpotServlet">
                <div class="search-box">
                    <input type="text" name="spotId" class="search-input" 
                           value="${param.spotId}" required 
                           placeholder="請輸入景點編號 (例如：1, 2, 3...)"
                           pattern="[0-9]+" title="請輸入數字">
                    <input type="hidden" name="action" value="getOne_For_Display">
                </div>
                
                <button type="submit" class="search-btn">🔍 開始查詢</button>
            </form>
            
            <div class="search-hint">
                💡 <strong>查詢提示：</strong><br>
                景點編號為數字格式，例如：1、2、3、4、5 等
            </div>
            
            <div class="quick-actions">
                <a href="${pageContext.request.contextPath}/spot/SpotServlet?action=listAll" 
                   class="quick-btn">📋 查看所有景點</a>
                <a href="${pageContext.request.contextPath}/spot/addSpot.jsp" 
                   class="quick-btn">➕ 新增景點</a>
            </div>
            
            <div class="feature-list">
                <h4>🎯 功能說明</h4>
                <ul>
                    <li>輸入景點編號可查看詳細資訊</li>
                    <li>查看景點名稱、地址、狀態等</li>
                    <li>可直接進行修改或刪除操作</li>
                    <li>支援快速導航到其他功能</li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
