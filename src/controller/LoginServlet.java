package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginDB;
import model.LoginUserBean;


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

		System.out.println(btn);//デバック

		HttpSession session = request.getSession();	// セッション
		RequestDispatcher rd;						// 画面の情報

		if(btn.equals("ログイン")){

			String userName  = request.getParameter("userName");
			String password = request.getParameter("password");

			LoginDB login = new LoginDB();


			LoginUserBean bean = login.getUserData(userName, password);

			//モデルの情報が存在する場合はsetAttributeメソッドを使ってセッションに情報をセット
			if(bean != null) {
				// モデル（ユーザ情報）
				session.setAttribute("user_db", bean);
				// ログイン状態
				session.setAttribute("login_db", "login");
				// 投稿一覧ページの一覧を表示するためのサーブレットを指定
				rd = request.getRequestDispatcher("./PostServlet");
			}
			//モデルの情報が当てはまらない場合エラーメッセージを出力
			else {
				// ログアウト状態
				session.setAttribute("login_db", "logout");
				// ③-2-2 つぎに表示させる画面（ビュー）を指定
				rd = request.getRequestDispatcher("./LoginForm.jsp");
			}
			rd.forward(request, response);
		}

		//ログアウトボタン押下時、セッションの情報を削除しHomePage.jspへリダイレクト
		else if(btn.equals("ログアウト")){
			session.removeAttribute("login_db");
			session.removeAttribute("user_db");
			response.sendRedirect("./HomePage.jsp");
		}
	}


	//GETで送られてきた場合
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
