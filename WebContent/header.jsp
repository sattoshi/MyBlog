<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="user_db" scope="session" class="model.AccountBean" />

<header>
  <div class = "logo"><h1><a href="main">MyBlog</a></h1></div>

  <div class="flex menu">
    <div class="category-serch"></div>

    <!-- ログイン状態時 -->
    <% if("login".equals(session.getAttribute("login_db"))) { %>
      <div class = "flex">
        <a href = "PostServlet?value=author&author_id=<%=user_db.getId()%>">【<%=user_db.getName()%>】さんページ</a>
        <a href = "PostServlet?value=postEditing">投稿する</a>
        <a href = "LoginServlet?value=logout">ログアウト</a>
      </div>
    <% } %>

    <!-- ログアウト状態時 -->
     <% if(session.getAttribute("login_db")==null) { %>
    <div class = "flex">
      <a href = "main?value=login">ログイン</a>
      <a href = "main?value=signup">サインアップ</a>
    </div>
    <% } %>



  </div>
</header>