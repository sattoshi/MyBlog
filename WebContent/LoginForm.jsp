<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="user_db" scope="session" class="model.AccountBean" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link href="/MyBlog/css/style.css" rel="stylesheet" type="text/css" />
	</head>
	<body id="login">
	  <!-- ヘッダー読み込み -->
	  <jsp:include page="header.jsp"/>

	  <div>
     <div class="main_image">
        <div class="main_image_bg">
        </div>
        <div class="main_title">
          <h2>ログイン画面</h2>
        </div>
      </div>
    </div>

    <!-- ログインフォーム -->
    <section class="maxwidth800">
	    <div class="login-form">
		   <form action="./LoginServlet" method="post">
			   <p>ユーザ名：<input type="text" name="userName" value="<%=user_db.getUserName()%>" /></p>

			   <p>パスワード：<input type="password" name="password" /></p>

			   <%-- エラー時にメッセージを表示 --%>
	        <% if("error_msg".equals(request.getAttribute("error"))) { %>
	          <p class="error-msg">ユーザ名またはパスワードが異なります。</p>
	        <% } %>

			   <input type="submit" name="submit" value="ログイン" />
			 </form>
	    </div>

		   <a class="text-link" href = "main?value=signup">アカウントがない方はこちら</a>
	   </section>

	  <!-- フッター読み込み -->
    <jsp:include page="footer.jsp"/>
  </body>
</html>