package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.KadaiAccount;

/**
 * Servlet implementation class KadaiRegisterConfirmServlet
 */
@WebServlet("/KadaiRegisterConfirmServlet")
public class KadaiRegisterConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KadaiRegisterConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String mail = request.getParameter("email");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String grade = request.getParameter("grade");
		String department = request.getParameter("department");
		int Grade = Integer.parseInt(grade);
		
		KadaiAccount account = new KadaiAccount(-1, mail, pw, name, Grade, department, null, null);
		
		HttpSession session = request.getSession();
		session.setAttribute("input_data", account);
		
		String view = "WEB-INF/view/Kadai-confirm.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
