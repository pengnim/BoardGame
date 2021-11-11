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
 * Servlet implementation class EndMatching
 */
@WebServlet("/EndMatching")
public class EndMatching extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EndMatching() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name= request.getParameter("uname").trim();
		String res= request.getParameter("res");
		String p = request.getParameter("point");
		request.setAttribute("user", name);
		Connection conn=null;
		ServletContext sc=getServletContext();
		int po = Integer.parseInt(p);
		try {
			conn=(Connection)sc.getAttribute("DBconnection");
			int r=0;
			if(res.equals("d")) { r=0; request.setAttribute("result", r);}
			else if(res.equals("v")) { r=1;request.setAttribute("result", r);}
			System.out.println("po:"+po+" name"+name);
			DBUtil.modScore(conn, name, po,"m");
			String s = "select * from player where id='" + name + "';";
			Statement stmt = conn.createStatement();
			stmt.execute(s);
			ResultSet rsc = stmt.getResultSet();
			int score=0;
			while(rsc.next()) {
				score=rsc.getInt("matching_score");
			}
			System.out.println(score);
			ResultSet rs=DBUtil.findScore(conn, name);
			System.out.println("match_rs:"+name);
			int vic=0;
			while(rs.next()) {
				vic=rs.getInt("matching_score");
			}
			System.out.println("vic:"+vic);
			request.setAttribute("p", p);
			request.setAttribute("vic", vic);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher v=request.getRequestDispatcher("resultMat.jsp");
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
