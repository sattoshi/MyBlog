<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="user_db" scope="session" class="model.LoginUserBean" />

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

	  <div class="main-bg-image">
	   <h2>ログイン画面</h2>
	  </div>

    <div>
	   <form action="./LoginServlet" method="post">
		   ユーザ名：<input type="text" name="userName" value="<%=user_db.getUserName()%>" />

		   パスワード：<input type="password" name="password" />

		   <%-- エラー時にメッセージを表示 --%>
        <% if("logout".equals(session.getAttribute("login_db"))) { %>
          <p>ユーザ名またはパスワードが異なります。</p>
        <% } %>

		   <input type="submit" name="submit" value="ログイン" />
		 </form>
    </div>

	   <a href = "main?value=signup">アカウントがない方はこちら</a>

	  <!-- フッター読み込み -->
    <jsp:include page="footer.jsp"/>

  </body>
</html>