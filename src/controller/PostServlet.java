package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AccountBean;
import model.AccountDB;
import model.PostBean;
import model.PostDB;


@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    //コンストラクタ
    public PostServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String val = request.getParameter("value");
		String nextPage = null;

		//詳細ページへ
		//必要パラメータ：postId
		if(val.equals("detail")){
			String postId = request.getParameter("postId");
			nextPage = "/BlogDetails.jsp";
			//ブログの情報をBeanクラスに格納する。
			PostDB postInfo = new PostDB();
			PostBean postBean = postInfo.getPostDetail(postId);
			request.setAttribute("post_db", postBean);
		}

		//編集者別ページへ
		//必要パラメータ：author_id
		else if(val.equals("author")) {
			nextPage = "/BlogByAuthor.jsp";

			request.setAttribute("author_id", Integer.parseInt(request.getParameter("author_id")));

			//登録されているユーザの情報をBeanクラス
			AccountDB accountInfo = new AccountDB();
			ArrayList<AccountBean> accountBeanList = accountInfo.getAccountData();
			request.setAttribute("account_db", accountBeanList);

			//ブログの情報をBeanクラスに格納する。
			PostDB postInfo = new PostDB();
			ArrayList<PostBean> postBeanList = postInfo.getPostData();

			request.setAttribute("post_db", postBeanList);
		}

		//トップページへ
		else if(val.equals("home")) {
			String option = "all";

			//categoryが選択されている場合
			if(request.getParameter("category")!=null) {
				option = request.getParameter("category");
			}
			//tagが選択されている場合
			if(request.getParameter("tag")!=null) {
				option = request.getParameter("tag");
				request.setAttribute("tagOption", option);
			}
			request.setAttribute("categoryOption", option);
			nextPage = "/HomePage.jsp";

			//登録されているユーザの情報をBeanクラス
			AccountDB accountInfo = new AccountDB();
			ArrayList<AccountBean> accountBeanList = accountInfo.getAccountData();
			request.setAttribute("account_db", accountBeanList);

			//ブログの情報をBeanクラスに格納する。
			PostDB postInfo = new PostDB();
			ArrayList<PostBean> postBeanList = postInfo.getPostData();

			request.setAttribute("post_db", postBeanList);
		}

		//投稿編集画面へ
		else if(val.equals("postEditing")) {
			nextPage = "/PostEditing.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String val = (String)request.getAttribute("next_page");
		String nextPage = null;

		//ページ遷移
		if(val !=null) {
			if(val.equals("BlogByAuthor")) {
				//編集者のユーザIDをセット
				request.setAttribute("author_id", request.getAttribute("author_id"));
				nextPage = "/BlogByAuthor.jsp";
			}
			else if(val.equals("HomePage")) {
				nextPage = "/HomePage.jsp";
			}
		}

		//登録されているユーザの情報をBeanクラス
		AccountDB accountInfo = new AccountDB();
		ArrayList<AccountBean> accountBeanList = accountInfo.getAccountData();
		request.setAttribute("account_db", accountBeanList);

		//ブログの情報をBeanクラスに格納する。
		PostDB postInfo = new PostDB();
		ArrayList<PostBean> postBeanList = postInfo.getPostData();

		request.setAttribute("post_db", postBeanList);


		RequestDispatcher rd = request.getRequestDispatcher(nextPage);

		rd.forward(request, response);

	}

}
