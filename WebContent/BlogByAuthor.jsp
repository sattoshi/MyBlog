<%@ page import="java.util.ArrayList"%>
<%@ page import="model.AccountBean"%>
<%@ page import="model.PostBean"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="user_db" scope="session" class="model.AccountBean" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ別ページ</title>
<link href="/MyBlog/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!-- ヘッダー読み込み -->
    <jsp:include page="header.jsp"/>

    <div>
     <div class="main_image">
        <div class="main_image_bg">
        </div>
        <div class="main_title">

        <%
        ArrayList<AccountBean> accountList = (ArrayList<AccountBean>)request.getAttribute("account_db");
        String author = "";
        String profile = "";

        for (AccountBean accountInfo: accountList){

        	//編集者のユーザの名前を調べる。
	        if(accountInfo.getId() == (int)request.getAttribute("author_id")){
        		  author =  accountInfo.getName();
        		  profile = accountInfo.getProfile();
        	}
				}
				%>

        <%if(author.equals(user_db.getName())){ %>
         <h2>マイページ</h2>
        <% } %>>

        <%if(!(author.equals(user_db.getName()))){ %>
         <h2>【<%= author %>】のブログ</h2>
        <% } %>>

        </div>
      </div>
    </div>

    <!-- プロフィール -->
    <section class="maxwidth800">
    <h3>ユーザ情報</h3>
    <%if(author.equals(user_db.getName())){ %>
        <a href="" class = "text-link">編集する</a>
    <% } %>

    <div class="user-info">
      <div class="flex">
        <p>氏名</p>
        <p><%= author %></p>
      </div>
      <div class="flex">
        <p>プロフィール</p>
        <p><%= profile %></p>
      </div>
    </div>

    </section>

    <!-- 投稿一覧の吐き出し -->
    <section class="maxwidth800">
      <h3>【<%= author %>】の投稿一覧</h3>

    <%
        ArrayList<PostBean> postList = (ArrayList<PostBean>)request.getAttribute("post_db");
        ArrayList<String> categoryList =  new ArrayList<String>();
        ArrayList<String> tagList =  new ArrayList<String>();

        for(PostBean postInfo: postList){

        	//投稿者の投稿かどうか判断
        	if(postInfo.getUser_id() == (int)request.getAttribute("author_id")){
        		int post_id = postInfo.getId();
        		String title =  postInfo.getTitle();
        	  String eyecatch_path = postInfo.getEyecatchPath();
            String description = postInfo.getDescription();
            Date date = postInfo.getPost_date();
            categoryList = postInfo.getCategoryList();
            tagList = postInfo.getTagList();
    %>
      <div class = "post-area">
	      <div class = "flex">
	         <div class = "image-area">
	          <img src="<%= eyecatch_path %>" alt="アイキャッチ">
	         </div>
		       <div class = "text-area">
		       <a href = "PostServlet?value=detail&postId=<%= post_id %>">
			       <p><%= date %></p>
			       <p class = "post-titile"><%= title %></p>
			       <p><%= description %></p>
		       </a>

           <p class = "topic">カテゴリ</p>
		       <% for(String category : categoryList){
		    	   if(category != null){%>

		         <a href="PostServlet?value=home&category=<%= category %>" class="text-link"><%= category %></a>
	         <% }
		    	   } %>

		       </div>
	      </div>
      </div>


    <% } }%>

    </section>

    <!-- フッター読み込み -->
    <jsp:include page="footer.jsp"/>

</body>
</html>