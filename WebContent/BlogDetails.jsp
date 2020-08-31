<%@ page import="java.util.ArrayList"%>
<%@ page import="model.PostBean"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="user_db" scope="session" class="model.AccountBean" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投稿詳細</title>
<link href="/MyBlog/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!-- ヘッダー読み込み -->
    <jsp:include page="header.jsp"/>

    <%
      PostBean postInfo = (PostBean)request.getAttribute("post_db");
      ArrayList<String> categoryList =  new ArrayList<String>();
      ArrayList<String> tagList =  new ArrayList<String>();

      int post_id = postInfo.getId();
      int user_id = postInfo.getUser_id();
      String name = postInfo.getName();
      String title =  postInfo.getTitle();
      String eyecatch_path = postInfo.getEyecatchPath();
      String description = postInfo.getDescription();
      Date date = postInfo.getPost_date();
      String content = postInfo.getPostContent();
      categoryList = postInfo.getCategoryList();
      tagList = postInfo.getTagList();
    %>

    <div>
     <div class="main_image">
        <div class="main_image_bg">
        </div>
        <div class="main_title">
          <h2><%= title %></h2>
        </div>
     </div>
   </div>

   <section class="maxwidth800 blogDetails">
    <div class="post-info">
      <p class="align-right"><%= date %></p>
      <div class="flex">
        <p>投稿者</p>
        <a href="PostServlet?value=author&author_id=<%= user_id %>" class="text-link"><%= name %></a>
      </div>
      <div class="flex">
        <p>カテゴリ</p>
        <% for(String category : categoryList){
        if(category != null){%>
           <a href="PostServlet?value=home&category=<%= category %>" class="text-link"><%= category %></a>
        <% }} %>
      </div>
      <div class="flex">
        <p>タグ</p>
        <% for(String tag : tagList){
        if(tag != null){%>
          <a href="PostServlet?value=home&tag=<%= tag %>" class="text-link"><%= tag %></a>
        <% }} %>
      </div>
    </div>

    <% if(eyecatch_path!=null){ %>
    <div class="image-area">
      <img src="<%= eyecatch_path %>" alt="アイキャッチ">
    </div>
    <% } %>

    <div class="text-area">
       <p><%= content %></p>
    </div>

   </section>

</body>
</html>