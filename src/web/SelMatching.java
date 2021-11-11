package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SelMatching
 */
@WebServlet("/SelMatching")
public class SelMatching extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelMatching() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String s=(String)session.getAttribute("name");
		System.out.println("sel:"+s);
		String name=request.getParameter("user");
		String game=request.getParameter("game");
		System.out.println("selMat:"+name);
		request.setAttribute("username", name);
		if(game.equals("easy")) {
			RequestDispatcher v=request.getRequestDispatcher("easyMat.jsp");
			v.forward(request, response);
		}
		else if(game.equals("nomal")) {
			RequestDispatcher v=request.getRequestDispatcher("nomalMat.jsp");
			v.forward(request, response);
		}
		else {
			RequestDispatcher v=request.getRequestDispatcher("hardMat.jsp");
			v.forward(request, response);
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
