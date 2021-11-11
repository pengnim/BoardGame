package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBUtil;

/**
 * Servlet implementation class ToHome
 */
@WebServlet("/ToHome")
public class ToHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user=request.getParameter("user");
		String grp=request.getParameter("grp");
		String nums=request.getParameter("num");
		int num=Integer.parseInt(nums);
		String[] mem=new String[num];
		request.setAttribute("username", user);
		System.out.println(user);
		ServletContext sc=getServletContext();
		Connection conn=null;
		ResultSet rsc=null;
		try {
			conn=(Connection)sc.getAttribute("DBconnection");
			rsc=DBUtil.findScore(conn, user);
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
				System.out.println(Vd+","+Dd+","+Vm+","+Vh+","+Dh);
			}
			ResultSet rs=DBUtil.getGrpMem(conn, grp, num);
			while(rs.next()) {
				for(int i=0;i<num;i++)
					mem[i]=rs.getString("p"+(i+1));
			}
			if(num==2) {
				if(user.equals(mem[0])) {
					while(mem[1]!=null&&(DBUtil.getGrp(conn, mem[1]).equals(mem[0]))) {
						try {
							Thread.sleep(2000);
						}
						catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
						DBUtil.deleteDeq2(conn, user);	
						DBUtil.deleteMember(conn, user, 2);
						DBUtil.delGroup(conn, user);
					
				}
				else if(user.equals(mem[1])) {
					DBUtil.deleteMember(conn, user, 2);
					DBUtil.delGroup(conn, user);
				}
			}
			else if(num==3) {
				if(user.equals(mem[0])) { 
					while((mem[1]!=null&&DBUtil.getGrp(conn, mem[1]).equals(mem[0]))||(mem[2]!=null&&DBUtil.getGrp(conn, mem[2]).equals(mem[0]))) {
						try {
							Thread.sleep(2000);
						}
						catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
						DBUtil.deleteDeq3(conn, user);	
						DBUtil.deleteMember(conn, user, 3);
						DBUtil.delGroup(conn, user);
				}
				else {
					DBUtil.deleteMember(conn, user, 3);
					DBUtil.delGroup(conn, user);
				}
			}
			else if(num==4) {
				if(user.equals(mem[0])) {
					while((mem[1]!=null&&DBUtil.getGrp(conn, mem[1]).equals(mem[0]))||(mem[2]!=null&&DBUtil.getGrp(conn, mem[2]).equals(mem[0]))||(mem[3]!=null&&DBUtil.getGrp(conn, mem[3]).equals(mem[0]))) {
						try {
							Thread.sleep(2000);
						}
						catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
						DBUtil.deleteDeq4(conn, user);	
						DBUtil.deleteMember(conn, user, 4);
						DBUtil.delGroup(conn, user);
				}
				else {
					DBUtil.deleteMember(conn, user, 4);
					DBUtil.delGroup(conn, user);
				}
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
