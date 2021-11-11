package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Block;
import model.DBUtil;

/**
 * Servlet implementation class StartGame
 */
@WebServlet("/StartGame")
public class StartGame extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StartGame() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("user");
		request.setAttribute("user", user);
		String game = request.getParameter("game");
		String grp = request.getParameter("grp_name");
		request.setAttribute("grp", grp);
		request.setAttribute("game", game);
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		request.setAttribute("connect", conn);
		String redirUrl = "";

		if (game.equals("2players")) {
			redirUrl = "davinci2.jsp?game=" + game + "&user=" + user + "&grp=" + grp + " ";
		} else if (game.equals("3players")) {
			redirUrl = "davinci.jsp?game=" + game + "&user=" + user + "&grp=" + grp + " ";
		} else {
			redirUrl = "davinci4.jsp?game=" + game + "&user=" + user + "&grp=" + grp + " ";
		}
		if (conn == null) {
			System.out.println("connection is null");
		}
		ResultSet rs = null;
		ArrayList<Integer> idx = new ArrayList<Integer>();
		for (int i = 0; i < 24; i++)
			idx.add(i);
		Block[] block = new Block[24];
		for (int i = 0; i < 12; i++)
			block[i] = new Block(i, "black");
		for (int i = 12; i < 24; i++)
			block[i] = new Block(i - 12, "white");
		Block[] b1 = new Block[4];
		Block[] b2 = new Block[4];
		for (int i = 0; i < 4; i++) {
			int r = (int) (Math.random() * idx.size());
			b1[i] = new Block(block[idx.get(r)].num, block[idx.get(r)].color);
			idx.remove(r);
		}
		for (int i = 0; i < 4; i++) {
			int r = (int) (Math.random() * idx.size());
			b2[i] = new Block(block[idx.get(r)].num, block[idx.get(r)].color);
			idx.remove(r);
		}
		try {
			if (game.equals("2players")) {
				Arrays.sort(b1);
				Arrays.sort(b2);
				Block[] deq = new Block[16];
				int j = 0;
				for (int i : idx) {
					deq[j++] = block[i];
				}
				rs = DBUtil.getGrpMem(conn, grp, 2);
				while (rs.next()) {
					String p1 = rs.getString("p1");
					String p2 = rs.getString("p2");
					if (user.equals(p1)) {
						if (DBUtil.checkDPlayer(conn, p1)) {
							DBUtil.addDPlayer(conn, p1, b1[0], b1[1], b1[2], b1[3]);
							System.out.println(user + "p1");
							for (Block b : b1) {
								System.out.println(b.num + " " + b.color);
							}
							System.out.println();
						}
						if (DBUtil.checkDPlayer(conn, p2)) {
							DBUtil.addDPlayer(conn, p2, b2[0], b2[1], b2[2], b2[3]);
							System.out.println(user + "p2");
							for (Block b : b2) {
								System.out.println(b.num + " " + b.color);
							}
							System.out.println();
						}
						if (DBUtil.checkDeq2Col(conn, p1) && DBUtil.checkDeq2Num(conn, p1)) {
							String[] s = new String[16];
							int[] n = new int[16];
							System.out.println(user + "deq");
							for (int i=0;i<16;i++) {
								System.out.println(deq[i].num + " " + deq[i].color);
								n[i] = deq[i].num;
								s[i] = deq[i].color;
							}
							DBUtil.addDeq2Color(conn, p1, s);
							DBUtil.addDeq2Num(conn, p1, n);
						}
					} else {
						while (DBUtil.checkDPlayer(conn, p1) || DBUtil.checkDPlayer(conn, p2)) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
				request.setAttribute("deque", deq);
				response.sendRedirect(redirUrl);
			} else if (game.equals("3players")) {
				Block[] b3 = new Block[4];
				for (int i = 0; i < 4; i++) {
					int r = (int) (Math.random() * idx.size());
					b3[i] = new Block(block[idx.get(r)].num, block[idx.get(r)].color);
					idx.remove(r);
				}
				System.out.println();
				Arrays.sort(b1);
				Arrays.sort(b2);
				Arrays.sort(b3);
				Block[] deq = new Block[12];
				int j = 0;
				for (int i : idx) {
					deq[j++] = block[i];
				}
				rs = DBUtil.getGrpMem(conn, grp, 3);
				while (rs.next()) {
					String p1 = rs.getString("p1");
					String p2 = rs.getString("p2");
					String p3 = rs.getString("p3");
					if (user.equals(p1)) {
						if (DBUtil.checkDPlayer(conn, p1)) {
							DBUtil.addDPlayer(conn, p1, b1[0], b1[1], b1[2], b1[3]);
							System.out.println(user + "p1");
							for (Block b : b1) {
								System.out.println(b.num + " " + b.color);
							}
							System.out.println();
						}
						if (DBUtil.checkDPlayer(conn, p2)) {
							DBUtil.addDPlayer(conn, p2, b2[0], b2[1], b2[2], b2[3]);
							System.out.println(user + "p2");
							for (Block b : b2) {
								System.out.println(b.num + " " + b.color);
							}
							System.out.println();
						}
						if (DBUtil.checkDPlayer(conn, p3)) {
							DBUtil.addDPlayer(conn, p3, b3[0], b3[1], b3[2], b3[3]);
							System.out.println(user + "p3");
							for (Block b : b3) {
								System.out.println(b.num + " " + b.color);
							}
							System.out.println();
						}
						if (DBUtil.checkDeq3Col(conn, p1) && DBUtil.checkDeq3Num(conn, p1)) {
							String[] s = new String[12];
							int[] n = new int[12];
							System.out.println(user + "deq");
							for (int i=0;i<12;i++) {
								System.out.println(deq[i].num + " " + deq[i].color);
								n[i] = deq[i].num;
								s[i] = deq[i].color;
							}
							DBUtil.addDeq3Color(conn, p1, s);
							DBUtil.addDeq3Num(conn, p1, n);
						}
					} else {
						while (DBUtil.checkDPlayer(conn, p1) || DBUtil.checkDPlayer(conn, p2)
								|| DBUtil.checkDPlayer(conn, p3)) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
				request.setAttribute("deque", deq);
				response.sendRedirect(redirUrl);
			}
			if (game.equals("4players")) {
				Block[] b3 = new Block[4];
				for (int i = 0; i < 4; i++) {
					int r = (int) (Math.random() * idx.size());
					b3[i] = new Block(block[idx.get(r)].num, block[idx.get(r)].color);
					idx.remove(r);
				}
				Block[] b4 = new Block[4];
				for (int i = 0; i < 4; i++) {
					int r = (int) (Math.random() * idx.size());
					b4[i] = new Block(block[idx.get(r)].num, block[idx.get(r)].color);
					idx.remove(r);
				}
				Arrays.sort(b1);
				Arrays.sort(b2);
				Arrays.sort(b3);
				Arrays.sort(b4);
				Block[] deq = new Block[8];
				int j = 0;
				for (int i : idx) {
					deq[j++] = block[i];
				}
				rs = DBUtil.getGrpMem(conn, grp, 4);
				while (rs.next()) {
					String p1 = rs.getString("p1");
					String p2 = rs.getString("p2");
					String p3 = rs.getString("p3");
					String p4 = rs.getString("p4");
					if (user.equals(p1)) {
						if (DBUtil.checkDPlayer(conn, p1)) {
							DBUtil.addDPlayer(conn, p1, b1[0], b1[1], b1[2], b1[3]);
							System.out.println(user + "p1");
							for (Block b : b1) {
								System.out.println(b.num + " " + b.color);
							}
							System.out.println();
						}
						if (DBUtil.checkDPlayer(conn, p2)) {
							DBUtil.addDPlayer(conn, p2, b2[0], b2[1], b2[2], b2[3]);
							System.out.println(user + "p2");
							for (Block b : b2) {
								System.out.println(b.num + " " + b.color);
							}
							System.out.println();
						}
						if (DBUtil.checkDPlayer(conn, p3)) {
							DBUtil.addDPlayer(conn, p3, b3[0], b3[1], b3[2], b3[3]);
							System.out.println(user + "p3");
							for (Block b : b3) {
								System.out.println(b.num + " " + b.color);
							}
							System.out.println();
						}
						if (DBUtil.checkDPlayer(conn, p4)) {
							DBUtil.addDPlayer(conn, p4, b4[0], b4[1], b4[2], b4[3]);
							System.out.println(user + "p4");
							for (Block b : b4) {
								System.out.println(b.num + " " + b.color);
							}
							System.out.println();
						}
						if (DBUtil.checkDeq4(conn, p1)) {
							String[] s = new String[8];
							int[] n = new int[8];
							Arrays.sort(deq);
							System.out.println(user + "deq");
							for(int i=0;i<8;i++) {
								System.out.println(deq[i].num+" "+deq[i].color);
								s[i]=deq[i].color;
								n[i]=deq[i].num;
							}
							DBUtil.addDeq4(conn, p1, s, n);
						}
					} else {
						while (DBUtil.checkDPlayer(conn, p1) || DBUtil.checkDPlayer(conn, p2)
								|| DBUtil.checkDPlayer(conn, p3) || DBUtil.checkDPlayer(conn, p4)) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
				request.setAttribute(user + "deque", deq);
				response.sendRedirect(redirUrl);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
