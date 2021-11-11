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

import model.DBUtil;

/**
 * Servlet implementation class GoToHome
 */
@WebServlet("/GoToHome")
public class GoToHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoToHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  String name=request.getParameter("user");
	      request.setAttribute("username", name);
	      System.out.println(name);
	      ServletContext sc=getServletContext();
	      Connection conn=null;
	      ResultSet rsc=null;
	      try {
	         conn=(Connection)sc.getAttribute("DBconnection");
	         rsc=DBUtil.findScore(conn, name);
	         if(rsc!=null) {
	            int Vd=0,Dd=0,Vm=0,Vh=0,Dh=0;
	            while(rsc.next()) {
	               Vd=rsc.getInt("victory_d");
	               Dd=rsc.getInt("defeat_d");
	               Vm=rsc.getInt("matching_score");
	               Vh=rsc.getInt("victory_h");
	               Dh=rsc.getInt("defeat_h");
	            }
	            request.setAttribute("vd", Vd);
	            request.setAttribute("dd", Dd);
	            request.setAttribute("vm", Vm);
	            request.setAttribute("vh", Vh);
	            request.setAttribute("dh", Dh);
	            System.out.println(Vd+","+Dd+","+Vm+","+Vh+","+Dh);
	         }
	      }
	      catch(SQLException e) {
	         e.printStackTrace();
	      }
	      RequestDispatcher v=request.getRequestDispatcher("home.jsp");
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
