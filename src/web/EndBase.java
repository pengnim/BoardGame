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
 * Servlet implementation class EndBase
 */
@WebServlet("/EndBase")
public class EndBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EndBase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("uname").trim();
		String res=request.getParameter("result");
		String rn = request.getParameter("rn");
		request.setAttribute("rn", rn);
		request.setAttribute("user", name);
		Connection conn=null;
		ServletContext sc=getServletContext();
		try {
			conn=(Connection)sc.getAttribute("DBconnection");
			int r=0;
			if(res.equals("d")) { r=0; request.setAttribute("result", r);}
			else if(res.equals("v")) { r=1;request.setAttribute("result", r);}
			DBUtil.modScore(conn, name, r,"b");
			String s="select * from player where id='"+name+"';";
			Statement stmt=conn.createStatement();
			stmt.execute(s);
			ResultSet rs=stmt.getResultSet();
			int vic=0;
			int def=0;
			while(rs.next()) {
				vic=rs.getInt("victory_b");
				System.out.println(vic);
				def=rs.getInt("defeat_b");
				System.out.println(def);
			}
			request.setAttribute("vic", vic);
			request.setAttribute("def", def);
			float score=(float)vic/(vic+def);
			request.setAttribute("vicRate", score);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher v=request.getRequestDispatcher("resultBase.jsp");
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
