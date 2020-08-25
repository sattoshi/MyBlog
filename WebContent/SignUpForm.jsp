<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="user_db" scope="session" class="model.AccountBean" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link href="/MyBlog/css/style.css" rel="stylesheet" type="text/css" />
	</head>

  <body id="signup">
   <!-- ヘッダー読み込み -->
    <jsp:include page="header.jsp"/>

    <div>
     <div class="main_image">
        <div class="main_image_bg">
        </div>
        <div class="main_title">
          <h2>サインアップ</h2>
        </div>
      </div>
    </div>

    <!-- サインアップフォーム -->
    <section class="maxwidth800">
      <div class="signup-form">
       <form action="./SignUpServlet" method="post">
         <p>ユーザ名：<input type="text" name="userName" /></p>

         <%-- エラー時にメッセージを表示 --%>
         <% System.out.println(request.getAttribute("error"));//デバック %>
          <% if("already_usename".equals(request.getAttribute("error"))) { %>
            <p class="error-msg">このユーザ名は既に登録されています。</p>
          <% } %>


         <p>パスワード：<input type="password" name="password" /></p>

         <p>確認用パスワード：<input type="password" name="confirm_pass"></p>

         <%-- エラー時にメッセージを表示 --%>
          <% if("missmatch_pass".equals(request.getAttribute("error"))) { %>
            <p class="error-msg">確認用パスワードと異なります。</p>
          <% } %>

         <input type="submit" name="submit" value="サインアップ" />
       </form>
      </div>

       <a class="text-link" href = "main?value=login">アカウントをお持ちの方はこちら</a>
     </section>

    <!-- サインアップフォーム -->
    <section>
    </section>

    <!-- フッター読み込み -->
    <jsp:include page="footer.jsp"/>
	</body>
</html>