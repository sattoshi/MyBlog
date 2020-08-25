package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountBean;
import model.AccountDB;
import model.AccountDao;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//コンストラクタ
    public ProfileServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String btn = request.getParameter("submit");

		if(btn.equals("登録")){

			String name = request.getParameter("name");
			String profile = request.getParameter("profile");
			String userName  = request.getParameter("userName");
			String password = request.getParameter("password");


			System.out.println("デバック"+userName);
			System.out.println("デバック"+password);

			AccountDao dao = new AccountDao();
			HttpSession session = request.getSession();	// セッション
			RequestDispatcher rd;

			//USERデータベースへユーザ情報を登録
			try {
				dao.insertUser(name , profile , userName , password);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dao.close();
			}

			AccountDB account = new AccountDB();
			AccountBean bean = account.getUserData(userName, password);
			//モデルの情報が存在する場合はsetAttributeメソッドを使ってセッションに情報をセット
			if(bean != null) {
				// モデル（ユーザ情報）
				session.setAttribute("user_db", bean);
				// ログイン状態
				session.setAttribute("login_db", "login");
				// 投稿一覧ページの一覧を表示するためのサーブレットを指定
				rd = request.getRequestDispatcher("PostServlet");
			}else {
				request.setAttribute("error","error_msg");
				// つぎに表示させる画面（ビュー）を指定
				rd = request.getRequestDispatcher("./ProfileEditing.jsp");
			}

			rd.forward(request, response);

		}


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
