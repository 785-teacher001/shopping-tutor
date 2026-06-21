package la.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import la.bean.CustomerBean;
import la.dao.CustomerDAO;
import la.dao.DAOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. リクエストパラメータの文字コード設定
		request.setCharacterEncoding("utf-8");
		// 2. リクエストパラメータのactionキーを取得
		String action = request.getParameter("action");
		// 3. actionキーの値によって処理を分岐：遷移先URLを格納する変数を初期化
		String nextPage = "errInternal.jsp";
		
		try {
			if (action == null || action.isEmpty()) {
				nextPage = "login.jsp";
			} else if (action.equals("login")) {
				// 1. リクエストパラメータを取得
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				// 2. CustomerDAOをインスタンス化
				CustomerDAO dao = new CustomerDAO();
				// 3. リクエストパラメータをもとに顧客インスタンスを取得
				CustomerBean customer = dao.findByEmailAndPassword(email, password);
				// 4. 取得した顧客インスタンスによって遷移先を分岐
				if (customer != null) {
					// 遷移先URLを設定
					nextPage = "/top.jsp";
				} else {
					// エラーメッセージをスコープに登録
					request.setAttribute("message", "メールアドレスとパスワードが一致しませんでした。");
					// 遷移先URLを設定
					nextPage = "/login.jsp";
				}
			}
			// 次画面遷移
			gotoPage(request, response, nextPage);
		} catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("message", "内部エラーが発生しました。");
            gotoPage(request, response, "/errInternal.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 指定されたURLにフォワードする
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @param nextPage 遷移先画面（URL）
	 * @throws ServletException
	 * @throws IOException
	 */
	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String nextPage) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

}
