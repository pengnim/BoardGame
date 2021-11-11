package web;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class JoinGrp
 */
@WebServlet("/JoinGrp")
public class JoinGrp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinGrp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user=request.getParameter("user");
		String game=request.getParameter("game");
		String group=request.getParameter("group");
		request.setAttribute("game", game);
		request.setAttribute("user", user);
		ServletContext sc=getServletContext();
		Connection conn=(Connection)sc.getAttribute("DBconnection");
		request.setAttribute("connect", conn);
		if(group==null) {
			RequestDispatcher v=request.getRequestDispatcher("joingrp.jsp");
			v.forward(request, response);
		}
		try {
			if(!group.equals(user)) {
				String g=DBUtil.getGrp(conn, user);
				if(game.equals("2players")) {
					if(!g.equals(group))
						DBUtil.modMember(conn, group, 2, user);
					DBUtil.modGroup(conn, user, group);
				}
				else if(game.equals("3players")) {
					if(!g.equals(group))
						DBUtil.modMember(conn, group, 3, user);
					DBUtil.modGroup(conn, user, group);
				}
				else{
					if(!g.equals(group))
						DBUtil.modMember(conn, group, 4, user);
					DBUtil.modGroup(conn, user, group);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher v=request.getRequestDispatcher("makegrp.jsp");
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
