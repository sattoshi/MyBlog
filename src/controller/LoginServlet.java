package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountBean;
import model.AccountDB;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//コンストラクタ
	public LoginServlet() {
        super();
    }


	//POSTで送られてきた場合
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String btn = request.getParameter("submit");

		HttpSession session = request.getSession();	// セッション
		RequestDispatcher rd;						// 画面の情報

		if(btn.equals("ログイン")){

			String userName  = request.getParameter("userName");
			String password = request.getParameter("password");

			AccountDB account = new AccountDB();
			AccountBean bean = account.getUserData(userName, password);

			//モデルの情報が存在する場合はsetAttributeメソッドを使ってセッションに情報をセット
			if(bean != null) {
				// モデル（ユーザ情報）
				session.setAttribute("user_db", bean);
				// ログイン状態
				session.setAttribute("login_db", "login");

				//次に遷移するページのフラグ
				request.setAttribute("next_page", "BlogByAuthor");

				//編集者ユーザID
				request.setAttribute("author_id",bean.getId());

				// 投稿一覧ページの一覧を表示するためのサーブレットを指定
				rd = request.getRequestDispatcher("PostServlet");
			}
			//モデルの情報が当てはまらない場合エラーメッセージを出力
			else {
				request.setAttribute("error","error_msg");
				// つぎに表示させる画面（ビュー）を指定
				rd = request.getRequestDispatcher("./LoginForm.jsp");
			}
			rd.forward(request, response);
		}
	}

	//GETで送られてきた場合
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String val = request.getParameter("value");
		HttpSession session = request.getSession();	// セッション
		RequestDispatcher rd;						// 画面の情報

		//ログアウトボタン押下時、セッションの情報を削除しHomePage.jspへ
		if(val.equals("logout")){
			session.removeAttribute("login_db");
			session.removeAttribute("user_db");

			rd = request.getRequestDispatcher("PostServlet?value=home");
			rd.forward(request, response);
		}
	}

}
