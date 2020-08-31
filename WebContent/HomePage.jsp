<%@ page import="java.util.ArrayList"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="model.PostBean"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link href="/MyBlog/css/style.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	  <!-- ヘッダー読み込み -->
	  <jsp:include page="header.jsp"/>

    <%
    String option = null;
    if(request.getAttribute("tagOption") != null){
      option = (String)request.getAttribute("tagOption");
    }
    else if(request.getAttribute("categoryOption") != "all"){
    	option = (String)request.getAttribute("categoryOption");
    }
    else{
    	option = "全て";
    }
    %>

	  <div>
	   <div class="main_image">
		    <div class="main_image_bg">
		    </div>
		    <div class="main_title">
		      <h2>【<%= option %>】の記事一覧</h2>
		    </div>
		  </div>
	  </div>

	  <!-- 投稿一覧の吐き出し -->
	  <section class="maxwidth800">
	  <%
        ArrayList<PostBean> postList = (ArrayList<PostBean>)request.getAttribute("post_db");
        ArrayList<String> categoryList =  new ArrayList<String>();
        ArrayList<String> tagList =  new ArrayList<String>();

        int post_id = 0;
        int user_id = 0;
        String title = null;
        String eyecatch_path = null;
        String description = null;
        Date date = null;
        String name = null;

        for(PostBean postInfo: postList){


          //オプション（タグ）の絞り込み
          if(request.getAttribute("tagOption") != null){
            tagList = postInfo.getTagList();
            if(tagList.indexOf((String)request.getAttribute("tagOption")) != -1){
                post_id = postInfo.getId();
                user_id = postInfo.getUser_id();
                title =  postInfo.getTitle();
                eyecatch_path = postInfo.getEyecatchPath();
                description = postInfo.getDescription();
                date = postInfo.getPost_date();
                name = postInfo.getName();
                categoryList = postInfo.getCategoryList();
            }
            else{
            	continue;
            }
          }
          //オプション（カテゴリ）の絞り込み
          else if(request.getAttribute("categoryOption") != "all"){
            categoryList = postInfo.getCategoryList();
            if(categoryList.indexOf((String)request.getAttribute("categoryOption")) != -1){
              post_id = postInfo.getId();
              user_id = postInfo.getUser_id();
              title =  postInfo.getTitle();
              eyecatch_path = postInfo.getEyecatchPath();
              description = postInfo.getDescription();
              date = postInfo.getPost_date();
              name = postInfo.getName();
              tagList = postInfo.getCategoryList();
            }
            else{
            	continue;
            }
          }

          //指定されていない場合
          else{
            post_id = postInfo.getId();
            user_id = postInfo.getUser_id();
            title =  postInfo.getTitle();
            eyecatch_path = postInfo.getEyecatchPath();
            description = postInfo.getDescription();
            date = postInfo.getPost_date();
            name = postInfo.getName();
            categoryList = postInfo.getCategoryList();
            tagList = postInfo.getTagList();
          }
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
           <p class = "topic">投稿者</p>
           <a href="PostServlet?value=author&author_id=<%= user_id %>" class="text-link"><%= name %></a>
           <p class = "topic">カテゴリ</p>
           <% for(String category : categoryList){
        	    if(category != null){%>
             <a href="PostServlet?value=home&category=<%= category %>" class="text-link"><%= category %></a>
           <% }} %>

           </div>
        </div>
      </div>
    <% } %>

    </section>


	  <!-- フッター読み込み -->
	  <jsp:include page="footer.jsp"/>
	 </body>
</html>