<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>操作錯誤</title>
    <style>
        body {
            font-family: "微軟正黑體", Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
            color: #333;
        }
        
        .container {
            max-width: 600px;
            margin: 50px auto;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        
        .header {
            background-color: #dc3545;
            color: white;
            padding: 30px;
            text-align: center;
        }
        
        .header h2 {
            margin: 0;
            font-size: 28px;
            font-weight: 600;
        }
        
        .header p {
            margin: 10px 0 0 0;
            font-size: 16px;
            opacity: 0.9;
        }
        
        .content {
            padding: 40px;
        }
        
        .error-messages {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            color: #721c24;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 30px;
        }
        
        .error-messages ul {
            margin: 0;
            padding-left: 20px;
        }
        
        .error-messages li {
            margin-bottom: 8px;
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
        
        .icon {
            font-size: 48px;
            margin-bottom: 20px;
        }
        
        .suggestions {
            background-color: #e7f3ff;
            border: 1px solid #b8daff;
            color: #004085;
            padding: 20px;
            border-radius: 8px;
            margin-top: 20px;
        }
        
        .suggestions h4 {
            margin-top: 0;
            color: #004085;
        }
        
        .suggestions ul {
            margin-bottom: 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <div class="icon">❌</div>
            <h2>操作失敗</h2>
            <p>處理您的請求時發生錯誤</p>
        </div>
        
        <div class="content">
            <!-- 錯誤訊息顯示 -->
            <c:if test="${not empty errorMsgs}">
                <div class="error-messages">
                    <strong>錯誤詳情：</strong>
                    <ul>
                        <c:forEach var="message" items="${errorMsgs}">
                            <li>${message}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
            
            <!-- 如果沒有具體錯誤訊息，顯示通用訊息 -->
            <c:if test="${empty errorMsgs}">
                <div class="error-messages">
                    <strong>發生未知錯誤，請稍後再試。</strong>
                </div>
            </c:if>
            
            <!-- 建議解決方案 -->
            <div class="suggestions">
                <h4>💡 建議解決方案：</h4>
                <ul>
                    <li>檢查輸入的資料是否正確</li>
                    <li>確認所有必填欄位都已填寫</li>
                    <li>如果是刪除操作失敗，可能是因為該資料被其他地方參照</li>
                    <li>重新整理頁面後再試一次</li>
                    <li>如果問題持續發生，請聯繫系統管理員</li>
                </ul>
            </div>
            
            <div class="btn-group">
                <a href="javascript:history.back()" class="btn btn-secondary">⬅️ 返回上一頁</a>
                <a href="${pageContext.request.contextPath}/spot/SpotServlet?action=listAll" 
                   class="btn btn-primary">📋 回到景點列表</a>
            </div>
        </div>
    </div>
</body>
</html> 