package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBUtil;

/**
 * Servlet implementation class EndMole
 */
@WebServlet("/EndMole")
public class EndMole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EndMole() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("uname").trim();
		String res=request.getParameter("result");
		int po = Integer.parseInt(res);
		request.setAttribute("user", name);
		request.setAttribute("res", res);
		Connection conn=null;
		ServletContext sc=getServletContext();
		try {
			conn=(Connection)sc.getAttribute("DBconnection");
			DBUtil.modScore(conn, name, po,"mo");
			String s="select * from player where id='"+name+"';";
			Statement stmt=conn.createStatement();
			stmt.execute(s);
			ResultSet rs=stmt.getResultSet();
			int vic=0;
			while(rs.next()) {
				vic=rs.getInt("mole_score");
				System.out.println(vic);

			}
			request.setAttribute("vic", vic);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher v=request.getRequestDispatcher("resultMole.jsp");
		v.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
