<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="user_db" scope="session" class="model.AccountBean" />


<footer>
  <div class = "logo"><a href="main">MyBlog</a></div>

  <!-- ログアウト状態時 -->
  <% if(session.getAttribute("login_db")==null) { %>
	  <div>
	    <a href = "main?value=login">ログイン</a>
	    <a href = "main?value=signup">サインアップ</a>
	  </div>
  <% } %>


  <!-- ログイン状態時 -->
  <% if("login".equals(session.getAttribute("login_db"))) { %>
    <div>
      <a href = "PostServlet?value=author&author_id=<%=user_db.getId()%>">マイページ</a>
      <a href = "PostServlet?value=postEditing">投稿する</a>
      <a href = "LoginServlet?value=logout">ログアウト</a>
    </div>
  <% } %>

</footer>