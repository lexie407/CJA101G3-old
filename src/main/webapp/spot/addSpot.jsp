<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>新增景點</title>
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
            margin: 0 auto;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        
        .header {
            background-color: #ffffff;
            border-bottom: 3px solid #28a745;
            padding: 30px;
            text-align: center;
        }
        
        .header h2 {
            margin: 0;
            font-size: 28px;
            color: #28a745;
            font-weight: 600;
        }
        
        .header p {
            margin: 10px 0 0 0;
            color: #6c757d;
            font-size: 16px;
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
            border-color: #28a745;
            box-shadow: 0 0 0 3px rgba(40, 167, 69, 0.1);
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
            background-color: #28a745;
            color: white;
            border-color: #28a745;
        }
        
        .btn-primary:hover {
            background-color: #218838;
            border-color: #218838;
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(40, 167, 69, 0.3);
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
        
        .error-messages {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            color: #721c24;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 25px;
        }
        
        .error-messages ul {
            margin: 0;
            padding-left: 20px;
        }
        
        .input-hint {
            font-size: 12px;
            color: #6c757d;
            margin-top: 5px;
        }
        
        .form-section {
            border-bottom: 1px solid #e9ecef;
            padding-bottom: 25px;
            margin-bottom: 25px;
        }
        
        .form-section:last-child {
            border-bottom: none;
            margin-bottom: 0;
            padding-bottom: 0;
        }
        
        .section-title {
            font-size: 18px;
            font-weight: 600;
            color: #495057;
            margin-bottom: 20px;
            padding-bottom: 8px;
            border-bottom: 2px solid #28a745;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h2>✨ 新增景點</h2>
            <p>添加新的旅遊景點資訊</p>
        </div>
        
        <div class="form-content">
            <!-- 錯誤訊息顯示 -->
            <c:if test="${not empty errorMsgs}">
                <div class="error-messages">
                    <strong>⚠️ 請修正以下錯誤：</strong>
                    <ul>
                        <c:forEach var="message" items="${errorMsgs}">
                            <li>${message}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/spot/SpotServlet" method="post">
                <input type="hidden" name="action" value="addSpot">
                
                <div class="form-section">
                    <div class="section-title">📍 基本資訊</div>
                    
                    <div class="form-group">
                        <label>🏞️ 景點名稱 <span class="required">*</span></label>
                        <input type="text" name="spotName" value="${param.spotName}" required 
                               placeholder="請輸入景點名稱">
                        <div class="input-hint">例如：台北101、故宮博物院</div>
                    </div>
                    
                    <div class="form-group">
                        <label>📍 地址 <span class="required">*</span></label>
                        <input type="text" name="spotLoc" value="${param.spotLoc}" required 
                               placeholder="請輸入詳細地址">
                        <div class="input-hint">例如：台北市信義區信義路五段7號</div>
                    </div>
                    
                    <div class="form-group">
                        <label>📝 景點描述</label>
                        <textarea name="spotDesc" rows="4" 
                                  placeholder="請輸入景點的詳細描述...">${param.spotDesc}</textarea>
                        <div class="input-hint">描述景點的特色、開放時間、注意事項等</div>
                    </div>
                </div>
                
                <div class="form-section">
                    <div class="section-title">⚙️ 設定資訊</div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label>👤 建立者 <span class="required">*</span></label>
                            <select name="crtId" required>
                                <option value="">請選擇建立者</option>
                                <option value="10" ${param.crtId == '10' ? 'selected' : ''}>會員10 - John</option>
                                <option value="11" ${param.crtId == '11' ? 'selected' : ''}>會員11 - Emma</option>
                                <option value="12" ${param.crtId == '12' ? 'selected' : ''}>會員12 - Leo</option>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label>📊 狀態</label>
                            <select name="spotStatus">
                                <option value="1" ${param.spotStatus == '1' ? 'selected' : 'selected'}>🟢 啟用</option>
                                <option value="0" ${param.spotStatus == '0' ? 'selected' : ''}>🔴 停用</option>
                            </select>
                        </div>
                    </div>
                </div>
                
                <div class="btn-group">
                    <button type="submit" class="btn btn-primary">💾 新增景點</button>
                    <a href="${pageContext.request.contextPath}/spot/SpotServlet?action=listAll" 
                       class="btn btn-secondary">❌ 取消</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
