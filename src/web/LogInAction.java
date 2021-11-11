package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class LogInAction
 */
@WebServlet("/LogInAction")
public class LogInAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=(String)request.getParameter("identification");
		String pw=(String)request.getParameter("pw");
		String ip=Inet4Address.getLocalHost().getHostAddress();
		ServletContext sc=getServletContext();
		Cookie c=new Cookie("userid",id);
		response.addCookie(c);
		request.setAttribute("username", id);
		Connection conn=null;
		ResultSet rs=null;
		ResultSet rsc=null;
		boolean o=false;
		System.out.println(ip);
		try {
			conn=(Connection)sc.getAttribute("DBconnection");
			rs=DBUtil.findUser(conn, id);
			rsc=DBUtil.findScore(conn, id);
			o=DBUtil.isOnline(conn, id);
		}	
		catch(SQLException e) {
			e.printStackTrace();
		}
		int status;
		if(rs!=null) {
			try {
				if(rs.next()) {
					if(o==true) {
						status=-2;
						request.setAttribute("status", status);
						RequestDispatcher v=request.getRequestDispatcher("loginerror.jsp");
						v.forward(request, response);
					}
					else {
							String checkpw=rs.getString(1);
							if(checkpw.equals(pw)) {
								if(rsc!=null) {
									int Vd=0,Dd=0,Vm=0,Dm=0,Vh=0,Dh=0;
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
								}
								DBUtil.modIp(conn, id, ip);
								DBUtil.modOnline(conn, id,1);
								HttpSession session=request.getSession();
								session.setAttribute("name",id);
								RequestDispatcher v=request.getRequestDispatcher("home.jsp");
								v.forward(request, response);
								
						}
						else {
							status=0;
							request.setAttribute("status", status);
							RequestDispatcher v=request.getRequestDispatcher("loginerror.jsp");
							v.forward(request, response);
						}
					}
				}
				else {
					status=-1;
					request.setAttribute("status", status);
					RequestDispatcher v=request.getRequestDispatcher("loginerror.jsp");
					v.forward(request, response);
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
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