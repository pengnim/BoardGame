package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBUtil;
import model.GetWords;

/**
 * Servlet implementation class SelMatching
 */
@WebServlet("/SelHangman")
public class SelHangman extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelHangman() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		ServletContext sc=getServletContext();
		Connection conn=null;
		ResultSet rsc=null;
		
		String s=(String)session.getAttribute("name");
		System.out.println("sel:"+s);
		String name=request.getParameter("user");
		String game=request.getParameter("game");
		request.setAttribute("username", name);
		System.out.println("selHang:"+name);
		conn=(Connection)sc.getAttribute("DBconnection");
		
		int n = (int) (Math.random()*500)+1;
		
		if(game.equals("easy")) {
			try {
				rsc=GetWords.get4w(conn, n);
				if(rsc!=null) {
					String words = "";
					String mean ="";
					while(rsc.next()) {
						words = rsc.getString("letter");
						mean = rsc.getString("meaning");
					}
					request.setAttribute("words", words);
					request.setAttribute("mean", mean);
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher v=request.getRequestDispatcher("hang4.jsp");
			v.forward(request, response);
		}
		else if(game.equals("nomal")) {
			try {
				rsc=GetWords.get5w(conn, n);
				if(rsc!=null) {
					String words = "";
					String mean ="";
					while(rsc.next()) {
						words = rsc.getString("letter");
						mean = rsc.getString("meaning");
					}
					request.setAttribute("words", words);
					request.setAttribute("mean", mean);
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher v=request.getRequestDispatcher("hang5.jsp");
			v.forward(request, response);
		}
		else {
			try {
				rsc=GetWords.get6w(conn, n);
				if(rsc!=null) {
					String words = "";
					String mean ="";
					while(rsc.next()) {
						words = rsc.getString("letter");
						mean = rsc.getString("meaning");
					}
					request.setAttribute("words", words);
					request.setAttribute("mean", mean);
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher v=request.getRequestDispatcher("hang6.jsp");
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
