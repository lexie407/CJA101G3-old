<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- 宣告這裡有用JSTL -->
    
    
<!DOCTYPE html>
<html lang="zh-Hant">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>討論區 - 島遊kak</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet">
  <style>
    * {
      box-sizing: border-box;
    }

    body {
      margin: 0;
      font-family: "Noto Sans TC", sans-serif;
      background: #f6fafd;
      display: flex;
    }

    aside.sidebar {
      width: 220px;
      background: #ffffff;
      padding-top: 1rem;
      border-right: 1px solid #d0d0d0;
      height: 100vh;
    }

    aside.sidebar .logo {
      font-size: 1.5rem;
      font-weight: bold;
      color: #0288d1;
      margin-left: 1rem;
      margin-bottom: 2rem;
    }
    
    aside.sidebar .logo span{
    color: #FFB347;
    }

    aside.sidebar nav ul {
      list-style: none;
      padding: 0;
    }

    aside.sidebar nav ul li {
      padding: 1rem;
      cursor: pointer;
      display: flex;
      align-items: center;
      color: #333;
    }

    aside.sidebar nav ul li:hover,
    aside.sidebar nav ul li.active {
      background-color: #e0f7fa;
      font-weight: bold;
    }

    aside.sidebar nav ul li .material-symbols-outlined {
      margin-right: 0.5rem;
    }

    main {
      flex: 1;
      display: flex;
      flex-direction: column;
    }

    header.topbar {
      background: #ffffff;
      padding: 1rem;
      display: flex;
      align-items: center;
      border-bottom: 1px solid #d0d0d0;
    }

    header.topbar input[type="search"] {
      margin-left: 1rem;
      padding: 0.5rem 1rem;
      border-radius: 8px;
      border: 1px solid #ccc;
      width: 300px;
    }

    .content {
      padding: 1rem;
    }

    .article-card {
      display: flex;
      justify-content: space-between;
      align-items: center;
      background: #fff;
      border: 1px solid #e0e0e0;
      border-radius: 8px;
      padding: 1rem;
      margin-bottom: 1rem;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
      transition: background-color 0.2s ease, box-shadow 0.2s ease;
    }
    
    .article-card:hover {
  	  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
      cursor: pointer;
    }

    .article-info {
      display: flex;
      flex-direction: column;
      flex-grow: 1;
    }

    .article-tag {
      background: #e0f2ff;
      color: #0288d1;
      font-size: 0.9rem;
      padding: 0.2rem 0.5rem;
      border-radius: 12px;
      display: inline-block;
      margin-bottom: 0.5rem;
    }

    .article-title {
      font-size: 1.1rem;
      font-weight: 600;
      margin-bottom: 0.3rem;
    }

    .article-memid,
    .article-cretime {
      font-size: 0.85rem;
      color: #555;
    }

    .article-likes,
    .article-actions {
      flex-shrink: 0;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .article-likes {
      width: 80px;
      font-weight: 500;
      color: #333;
    }

    .article-actions {
      width: 100px;
      text-align: right;
      color: #aaa;
    }

    .-hide{
      display: none;
    }
  </style>
</head>

<body>
  <aside class="sidebar">
    <div class="logo">島遊 <span>kak</span></div>
    <nav>
      <ul>
      	<li>這是測試用網頁</li>
        <li><span class="material-symbols-outlined">home</span>首頁</li>
        <li><span class="material-symbols-outlined">route</span>行程</li>
        <li><span class="material-symbols-outlined">groups</span>揪團</li>
        <li><span class="material-symbols-outlined">store</span>商城</li>
        <li  class="active"><span class="material-symbols-outlined">forum</span>討論區</li>
        <li><span class="material-symbols-outlined">person</span>會員中心</li>
        <li><span class="material-symbols-outlined">military_tech</span>成為嚮導</li>
      </ul>
    </nav>
  </aside>
  <main>
    <header class="topbar">
      <span class="material-symbols-outlined">menu</span>
      <input type="search" placeholder="搜尋文章...">
    </header>

    <div class="content">

    <c:forEach var="article" items="${articlesList}">
      <div class="article-card ${article.artCat == 1 ? "" : "-hide"} ">
        <div class="article-info">
          <div class="article-tag">${article.artCat == 1 ? "文章" : "問題"}</div>
          <div class="article-title">${article.artTitle}</div>
          <div class="article-memid">${article.mamName}</div>
          <div class="article-cretime">${article.artCreTime}</div>
        </div>
        <div class="article-likes">
          <span class="material-symbols-outlined">thumb_up</span>
          <span>${article.artLike}</span>
        </div>
        <div class="article-actions">文章編號：${article.artId}</div>
      </div>
    </c:forEach>

      <div class="article-card -hide">
        <div class="article-info">
          <div class="article-tag">文章類別</div>
          <div class="article-title">文章標題</div>
          <div class="article-memid">會員名字</div>
          <div class="article-cretime">文章建立時間</div>
        </div>
        <div class="article-likes">
          <span class="material-symbols-outlined">thumb_up</span>
          <span>99</span>
        </div>
        <div class="article-actions">文章編號</div>
      </div>
    
    
    
    </div>
  </main>
</body>

</html>
