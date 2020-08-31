package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/main")
public class main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String val = request.getParameter("value");

		String nextPage = null;

		//ページ遷移
		if(val !=null) {
			if(val.equals("login")) {
				nextPage = "/LoginForm.jsp";
			}
			else if(val.equals("signup")) {
				nextPage = "/SignUpForm.jsp";
			}
		}else {
			nextPage = "PostServlet?value=home";

		}

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);

		rd.forward(request, response);
	}

}
