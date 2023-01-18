package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.KadaiAccountDAO;
import dto.KadaiAccount;
import util.KadaiGenerateHashedPw;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("UTF-8");
		
		String mail = request.getParameter("mail");
		
		String pw = request.getParameter("pwd");
		String salt = KadaiAccountDAO.getSalt(mail);
	
		System.out.println(mail);
		System.out.println(pw);
		System.out.println(salt);
		
		if(salt == null) {
			String view = "WEB-INF/view/login.jsp?error=1";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
			return;
		}
		
		String hashedPw = KadaiGenerateHashedPw.getSaftyPassword(pw, salt);
		
		KadaiAccount account = KadaiAccountDAO.login(mail, hashedPw);
		if(account == null) {
			String view = "WEB-INF/view/login.jsp?error=1";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		} else {
			// ログイン成功
			HttpSession session = request.getSession();
			session.setAttribute("user", account);
			
			String view = "WEB-INF/view/menu.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
