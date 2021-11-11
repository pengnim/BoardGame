package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBUtil;

/**
 * Servlet implementation class SelDavinci
 */
@WebServlet("/SelDavinci")
public class SelDavinci extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelDavinci() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String game=request.getParameter("game");
		String name=request.getParameter("user");
		request.setAttribute("user", name);
		HttpSession session=request.getSession();
		session.setAttribute("name", name);
		request.setAttribute("user", name);
		request.setAttribute("game",game);
		String grp_by=request.getParameter("grp_by");
		ServletContext sc=getServletContext();
		Connection conn=(Connection)sc.getAttribute("DBconnection");
		request.setAttribute("connect", conn);
		if(grp_by.equals("my_grp")) {
			request.setAttribute("group", name);
			try {
				DBUtil.modGroup(conn, name,name);	
				if(game.equals("2players")) {
					boolean val=DBUtil.checkGrpId(conn, name, 2);
					if(val)
						DBUtil.addGrp2(conn,name);
				}
				else if(game.equals("3players")) {
					boolean val=DBUtil.checkGrpId(conn, name, 3);
					if(val)
						DBUtil.addGrp3(conn,name);
				}
				else {
					boolean val=DBUtil.checkGrpId(conn, name, 4);
					if(val)
						DBUtil.addGrp4(conn,name);
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher v=request.getRequestDispatcher("makegrp.jsp");
			v.forward(request, response);
		}
		else {
			RequestDispatcher v=request.getRequestDispatcher("joingrp.jsp");
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
