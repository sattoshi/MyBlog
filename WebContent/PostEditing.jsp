<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="user_db" scope="session" class="model.AccountBean" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投稿編集画面</title>
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
          <h2>投稿編集画面</h2>
        </div>
      </div>
    </div>

    <!-- プロフィール編集フォーム -->
    <section class="maxwidth800">
      <div class="login-form">
       <form action="./PostEditServlet" method="post" enctype="multipart/form-data" >
         <p>タイトル：<input type="text" name="title" value="" required/></p>
         <p>ディスクリプション：<input type="text" name="description" value="" /></p>
         <p>アイキャッチ画像：<input type="file" name="eyecatch"/></p>
         <p>投稿内容：<input type="text" name="post_content" value="" /></p>
         <p>カテゴリ：<input type="text" name="categories" value="" /></p>
         <p class="error-msg">※複数入力する場合は「、」で区切って入力してください。</p>
         <p>タグ：<input type="text" name="tags" value="" /></p>
         <p class="error-msg">※複数入力する場合は「、」で区切って入力してください。</p>

         <input type="hidden" name="name" value="<%= user_db.getName() %>">
         <input type="hidden" name="user_id" value="<%= user_db.getId() %>">

         <input type="submit" name="submit" value="編集完了" />
       </form>
      </div>

     </section>

    <!-- フッター読み込み -->
    <jsp:include page="footer.jsp"/>

</body>
</html>