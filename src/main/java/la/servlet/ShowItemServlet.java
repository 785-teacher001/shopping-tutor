package la.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import la.bean.CategoryBean;
import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

@WebServlet("/ShowItemServlet")
public class ShowItemServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            // パラメータの解析
            String action = request.getParameter("action");
            if (action == null || action.length() == 0 || action.equals("top")) {
                // topまたはパラメータなしの場合はトップページを表示 
                gotoPage(request, response, "/top.jsp");
            } else if (action.equals("list")) {
                int categoryCode = Integer.parseInt(request.getParameter("code"));
                ItemDAO dao = new ItemDAO();
                List<ItemBean> list = dao.findByCategory(categoryCode);
                // レコード数の取得
                int count = dao.countByCategory(categoryCode);
                // Listをリクエストスコープに入れてJSPへフォーワードする
                request.setAttribute("items", list);
                request.setAttribute("count", count);
                gotoPage(request, response, "/list.jsp");
            } else if (action.equals("detail")) {
            	// リクエストパラメータの取得
            	String codeString = request.getParameter("code");
            	// 取得したパラメータのデータ型変換
            	int code = Integer.parseInt(codeString);
            	// 詳細表示する商品を取得
            	ItemDAO dao = new ItemDAO();
            	ItemBean bean = dao.findByPrimaryKey(code);
                // 取得した商品インスタンスをリクエストスコープに入れてJSPへフォーワードする
            	request.setAttribute("item", bean);
                gotoPage(request, response, "/item.jsp");
            } else if (action.equals("search")) {
            	// リクエストパラメータを取得
            	String keyword = request.getParameter("keyword");
            	// キーワードによる商品名あいまい検索
            	ItemDAO dao = new ItemDAO();
            	List<ItemBean> list = dao.findByName(keyword);
            	// レコード数の取得
            	int count = dao.countByName(keyword);
                // 取得した商品リストをリクエストスコープに入れてJSPへフォーワードする
            	request.setAttribute("items", list);
            	request.setAttribute("count", count);
                gotoPage(request, response, "/list.jsp");
            } else {
                request.setAttribute("message", "正しく操作してください。");
                gotoPage(request, response, "/errInternal.jsp");
            }
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("message", "内部エラーが発生しました。");
            gotoPage(request, response, "/errInternal.jsp");
        }
    }

    private void gotoPage(HttpServletRequest request,
            HttpServletResponse response, String page) throws ServletException,
            IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }

    public void init() throws ServletException {
        try {
            // カテゴリ一覧は最初にアプリケーションスコープへ入れる
            ItemDAO dao = new ItemDAO();
            List<CategoryBean> list = dao.findAllCategory();
            getServletContext().setAttribute("categories", list);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServletException();
        }
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}