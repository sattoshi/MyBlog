package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.PostDB;
import model.PostDao;


@WebServlet("/PostEditServlet")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class PostEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//コンストラクタ
    public PostEditServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String btn = request.getParameter("submit");
		String nextPage = null;

		if(btn.equals("編集完了")){
			PostDao dao = new PostDao();
			PostDB post = new PostDB();

			//カテゴリの処理
			String categories = request.getParameter("categories");
			String[] categoryList = categories.split("、", 0);
			for(String category:categoryList) {
				//データベース内に入力されたカテゴリが存在しない場合、データベースに登録
				if(post.findCategoryData(category) == false) {
					try {
						dao.insertCategory(category);
					} catch (SQLException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}finally {
						dao.close();
					}
				}
			}

			//タグの処理
			String tags = request.getParameter("tags");
			String[] tagList = tags.split("、", 0);
			for(String tag:tagList) {
				//データベース内に入力されたタグが存在しない場合、データベースに登録
				if(post.findTagData(tag) == false) {
					try {
						dao.insertTag(tag);
					} catch (SQLException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}finally {
						dao.close();
					}
				}
			}

			//投稿の登録
//			String name = request.getParameter("name");
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			String title = request.getParameter("title");
			String description  = request.getParameter("description");
			String post_content = request.getParameter("post_content");
			//現在時刻を格納
			Date dateObj = new Date();
			SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
			String date = format.format( dateObj );


			//アイキャッチ画像の処理
			String eyecatch_path = null;
			if(request.getPart("eyecatch") != null) {
				Part part = request.getPart("eyecatch");
		        String filename = this.getFileName(part);

		        part.write(getServletContext().getRealPath("/images") + "/" + filename);

		        eyecatch_path = getServletContext().getRealPath("/images") + "/" + filename;

			}

			try {
				dao.insertNewPost(title,description,date,eyecatch_path,post_content,user_id);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}finally {
				dao.close();
			}

			//post_idの取得(date/user/titleで絞り込む)
			int post_id = post.getPostId(title,user_id,date);

			for(String category:categoryList) {
				//category_idの取得
				int category_id = post.getCategoryId(category);
				//カテゴリマップの登録
				try {
					dao.insertCategoryMap(post_id,category_id);
				} catch (SQLException e) {
					//自動生成された catch ブロック
					e.printStackTrace();
				}finally {
					dao.close();
				}
			}

			for(String tag:tagList) {
				//tag_idの取得
				int tag_id = post.getTagId(tag);
				//タグマップの登録
				try {
					dao.insertTagMap(post_id,tag_id);
				} catch (SQLException e) {
					//自動生成された catch ブロック
					e.printStackTrace();
				}finally {
					dao.close();
				}
			}

			request.setAttribute("next_page", "BlogByAuthor");
			nextPage = "PostServlet";
			request.setAttribute("author_id",user_id);
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);

	}

	private String getFileName(Part part) {
		// TODO 自動生成されたメソッド・スタブ
		String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


}
