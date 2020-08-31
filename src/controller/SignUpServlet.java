package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountDB;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//コンストラクタ
    public SignUpServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String btn = request.getParameter("submit");

		RequestDispatcher rd;						// 画面の情報
		HttpSession session = request.getSession();	// セッション

		if(btn.equals("サインアップ")){
			String userName  = request.getParameter("userName");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirm_pass");


			//パスワードと確認用パスワードが一致している場合
			if(password.equals(confirmPassword)) {
				AccountDB account = new AccountDB();

				//ユーザ名が既に登録されていない場合は、ユーザ名とパスワードをProfileEditing.jspに送る。
				if(account.findUserData(userName) == false) {

					request.setAttribute("userName", userName);
					request.setAttribute("password", password);

					//sessionを初期化
					session.removeAttribute("login_db");
					session.removeAttribute("user_db");

					rd = request.getRequestDispatcher("./ProfileEditing.jsp");

				}
				//ユーザ名がデータベースに既に登録されている場合
				//エラー情報をSign Up Formに送る
				else {
					request.setAttribute("error","already_usename");
					rd = request.getRequestDispatcher("./SignUpForm.jsp");
				}

			}

			//パスワードと確認用パスワードが一致していない場合
			else {
				//エラー情報をSignUpFormに送る
				request.setAttribute("error","missmatch_pass");
				rd = request.getRequestDispatcher("./SignUpForm.jsp");
			}

			rd.forward(request, response);
		}
	}

	//GETで送られてきた場合
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
		}

}
