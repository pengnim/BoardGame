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
 * Servlet implementation class SignupAction
 */
@WebServlet("/SignUp")
public class SignupAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uid=request.getParameter("id");
		String pw=request.getParameter("pw");
		String checkPw=request.getParameter("pwCk");
		System.out.println(uid+","+pw+","+checkPw);
		if(uid.length()==0||pw.length()==0||checkPw.length()==0) {
			int status=-2;
			request.setAttribute("status",status);
			request.setAttribute("uid", uid);
			System.out.println("empty name space!");
			System.out.println("----------------------------------------");
			RequestDispatcher v=request.getRequestDispatcher("signUpError.jsp");
			v.forward(request, response);
		}
		else {
			System.out.println("input is not emty.");
			if(!pw.equals(checkPw)) {
				int status=0;
				System.out.println("passwd doesn't match!");
				System.out.println("----------------------------------------");
				request.setAttribute("status", status);
				RequestDispatcher v=request.getRequestDispatcher("signUpError.jsp");
				v.forward(request, response);
			}
			else {
				ServletContext sc=getServletContext();
				Connection conn=null;
				System.out.println("input is valid");
				try{
					conn=(Connection)sc.getAttribute("DBconnection");
					boolean b=DBUtil.checkAccount(conn, uid);
					System.out.println(b);
					if(b) {
						DBUtil.addAccount(conn, uid, pw);
						DBUtil.addPlayer(conn, uid);
						System.out.println("Success!!");
						System.out.println(uid+","+pw+",NULL,NULL");
						System.out.println("----------------------------------------");
						RequestDispatcher v=request.getRequestDispatcher("index.html");
						v.forward(request, response);
					}
					else {
						int status=-1;
						request.setAttribute("status",status);
						request.setAttribute("uid", uid);
						System.out.println("id already exists!!");
						System.out.println("----------------------------------------");
						RequestDispatcher v=request.getRequestDispatcher("signUpError.jsp");
						v.forward(request, response);
					}
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
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
