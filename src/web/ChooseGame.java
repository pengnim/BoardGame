package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBUtil;
import model.RandBbNum;


/**
 * Servlet implementation class ChooseGame
 */
@WebServlet("/ChooseGame")
public class ChooseGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChooseGame() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user=request.getParameter("uid");
		String game=request.getParameter("game");
		HttpSession session=request.getSession();
		session.setAttribute("name", user);
		if(game.equals("davinci")) {
			ServletContext sc=getServletContext();
			Connection conn=null;
			
			ResultSet rs=null;
			conn=(Connection)sc.getAttribute("DBconnection");
			request.setAttribute("connect", conn);
			request.setAttribute("name", user);
			RequestDispatcher v=request.getRequestDispatcher("selDavinci.jsp");
			v.forward(request, response);
		}
		else if(game.equals("matching")) {
			RequestDispatcher v=request.getRequestDispatcher("matching.jsp");
			v.forward(request, response);
		}
		else if(game.equals("hangman")) {
			RequestDispatcher v=request.getRequestDispatcher("hangman.jsp");
			v.forward(request, response);
		}
		else if(game.equals("mole")) {
			request.setAttribute("name", user);
			RequestDispatcher v=request.getRequestDispatcher("mole.jsp");
			v.forward(request, response);
		}
		else if(game.equals("baseball")) {
			String rn = RandBbNum.randNum();
			request.setAttribute("rn", rn);
			request.setAttribute("name", user);
			RequestDispatcher v=request.getRequestDispatcher("baseball.jsp");
			v.forward(request, response);
		}
		else {
			RequestDispatcher v=request.getRequestDispatcher("howTo.jsp");
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
