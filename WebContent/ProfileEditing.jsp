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
<body id="profile">
    <!-- ヘッダー読み込み -->
    <jsp:include page="header.jsp"/>

    <div>
     <div class="main_image">
        <div class="main_image_bg">
        </div>
        <div class="main_title">
          <h2>プロフィール編集</h2>
        </div>
      </div>
    </div>

    <!-- プロフィール編集フォーム -->
    <section class="maxwidth800">
      <div class="login-form">
       <form action="./ProfileServlet" method="post">
         <p>名前：<input type="text" name="name" value="<%=user_db.getUserName()%>" required/></p>

         <p>プロフィール：<input type="text" name="profile" value="<%=user_db.getProfile()%>" /></p>

         <% if("error_msg".equals(request.getAttribute("error"))) { %>
            <p class="error-msg">登録が正常にできませんでした。再度お試しください。</p>
          <% } %>

         <input type="hidden" name="userName" value="<%= request.getAttribute("userName") %>"/>
         <input type="hidden" name="password" value="<%= request.getAttribute("password") %>"/>

         <input type="submit" name="submit" value="登録" />
       </form>
      </div>

     </section>

    <!-- フッター読み込み -->
    <jsp:include page="footer.jsp"/>
  </body>
</html>