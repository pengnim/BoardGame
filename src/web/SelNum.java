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
 * Servlet implementation class SelNum
 */
@WebServlet("/SelNum")
public class SelNum extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelNum() {
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
		String game = request.getParameter("game");
		String user = request.getParameter("uname");
		String grp = request.getParameter("gname");
		String input = request.getParameter("result");
		String block = request.getParameter("block");
		System.out.println("game:" + game);
		int max_cor = -1;
		int input_valid = 1;
		if (block == null&&(!input.equals("-1"))) {
			input_valid = 0;
			block="b1";
		}
		try {
			Integer.parseInt(input);
		}
		catch(NumberFormatException e) {
			input_valid=0;
		}
		try {
			String hidden = request.getParameter("hidden");
			System.out.println("hidden:" + hidden);
			System.out.println("selNum");
			int num = 0;
			if (game.equals("2players"))
				num = 2;
			else if (game.equals("3players"))
				num = 3;
			else
				num = 4;
			request.setAttribute("num", num);
			String[] mem = new String[num];
			ServletContext sc = getServletContext();
			Connection conn = (Connection) sc.getAttribute("DBconnection");
			String redirUrl = "";
			if (game.equals("2players")) {
				redirUrl = "davinci2.jsp?game=" + game + "&user=" + user + "&grp=" + grp + " ";
			} else if (game.equals("3players")) {
				redirUrl = "davinci.jsp?game=" + game + "&user=" + user + "&grp=" + grp + " ";
			} else if (game.equals("4players")) {
				redirUrl = "davinci4.jsp?game=" + game + "&user=" + user + "&grp=" + grp + " ";
			}
			request.setAttribute("connect", conn);
			request.setAttribute("grp", grp);
			request.setAttribute("user", user);
			request.setAttribute("game", game);

			int left = 4, my_cor = 0, turn = 0, my_left = 4, left2 = 4, left3 = 4, cor = 0, cor2 = 0, cor3 = 0;
			int ans = -1;
			ResultSet rs = null;
			try {
				rs = DBUtil.getGrpMem(conn, grp, num);
				while (rs.next()) {
					for (int i = 0; i < num; i++)
						mem[i] = rs.getString("p" + (i + 1));
				}
				rs = DBUtil.findDPlayer(conn, user);
				while (rs.next()) {
					my_cor = rs.getInt("correct");
					my_left = rs.getInt("closed");
					max_cor = rs.getInt("max_cor");
				}
				System.out.println("my correct:" + my_cor + " my_left:" + my_left);
				if (game.equals("2players")) {
					if (user.equals(mem[0])) {
						rs = DBUtil.findDPlayer(conn, mem[1]);
						while (rs.next()) {
							int bl = Integer.parseInt(block);
							if (bl > 4)
								block = "b" + (bl - 4);
							ans = rs.getInt(block + "_num");
							left = rs.getInt("closed");
							turn = rs.getInt("next_turn");
						}
						rs = DBUtil.findDPlayer(conn, user);
						while (rs.next()) {
							my_cor = rs.getInt("correct");
							my_left = rs.getInt("closed");
						}
						if ((hidden.equals("1")) && (turn == 1) && (left > 0) && (my_left > 0)&&input_valid==1) {
							DBUtil.modTurn(conn, mem[0], 2);
							DBUtil.modTurn(conn, mem[1], 2);
						}
						System.out.println("ans:" + ans + " input:" + input + " turn=" + turn);
						if ((hidden.equals("1")) && (ans == Integer.parseInt(input)) && (turn == 1)) {
							DBUtil.modOpen(conn, mem[1], block, 1);
							DBUtil.modCor(conn, user);
							DBUtil.modLeft(conn, mem[1]);
							left -= 1;
							my_cor += 1;
						}

					} else {
						rs = DBUtil.findDPlayer(conn, mem[0]);
						while (rs.next()) {
							block = "b" + block;
							ans = rs.getInt(block + "_num");
							left = rs.getInt("closed");
							turn = rs.getInt("next_turn");
						}

						if ((hidden.equals("1")) && (turn == 2) && (left > 0) && (my_left > 0)&&input_valid==1) {
							DBUtil.modTurn(conn, mem[0], 1);
							DBUtil.modTurn(conn, mem[1], 1);
						}
						System.out.println("ans:" + ans + " input:" + input + " turn=" + turn);
						if ((hidden.equals("1")) && (ans == Integer.parseInt(input)) && (turn == 2)) {
							DBUtil.modOpen(conn, mem[0], block, 1);
							DBUtil.modCor(conn, user);
							DBUtil.modLeft(conn, mem[0]);
							left -= 1;
							my_cor += 1;
						}
					}
				} else if (game.equals("3players")) {
					if (input.equals("-1") && hidden.equals("0")) {
						System.out.println(user + " concluded");
						System.out.println("ans:" + ans + " input:" + input + " turn=" + turn);
						left = 0;
					} else {
						max_cor = my_cor;
						if (user.equals(mem[0])) {
							int bl = Integer.parseInt(block);
							if (bl > 4 && bl < 9) {
								block = "b" + (bl - 4);
								rs = DBUtil.findDPlayer(conn, mem[1]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
								}
								if (max_cor < cor)
									max_cor = cor;
								rs = DBUtil.findDPlayer(conn, mem[2]);
								while (rs.next()) {
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
								}
								if (max_cor < cor2)
									max_cor = cor2;
							} else if (bl >= 9) {
								block = "b" + (bl - 8);
								rs = DBUtil.findDPlayer(conn, mem[1]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
								}
								if (max_cor < cor)
									max_cor = cor;
								rs = DBUtil.findDPlayer(conn, mem[2]);
								while (rs.next()) {
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
								}
								if (max_cor < cor2)
									max_cor = cor2;
							}
							if ((hidden.equals("1")) && (turn == 1) && (left > 0) && (my_left > 0) && (left2 > 0)&&input_valid==1) {
								DBUtil.modMaxCor(conn, mem[0], max_cor);
								DBUtil.modMaxCor(conn, mem[1], max_cor);
								DBUtil.modMaxCor(conn, mem[2], max_cor);
								if (left > 0) {
									DBUtil.modTurn(conn, mem[0], 2);
									DBUtil.modTurn(conn, mem[1], 2);
									DBUtil.modTurn(conn, mem[2], 2);
								} else if (left2 > 0) {
									DBUtil.modTurn(conn, mem[0], 3);
									DBUtil.modTurn(conn, mem[1], 3);
									DBUtil.modTurn(conn, mem[2], 3);
								}
							}
							System.out.println("ans:" + ans + " input:" + input + " turn=" + turn);
							if ((hidden.equals("1")) && (ans == Integer.parseInt(input)) && (turn == 1)) {
								if (bl > 4 && bl < 9) {
									DBUtil.modOpen(conn, mem[1], block, 1);
									DBUtil.modLeft(conn, mem[1]);
									left -= 1;
								} else if (bl >= 9) {
									DBUtil.modOpen(conn, mem[2], block, 1);
									DBUtil.modLeft(conn, mem[2]);
									left -= 1;
								}
								DBUtil.modCor(conn, user);
								my_cor += 1;
							}
						} else if (user.equals(mem[1])) {
							int bl = Integer.parseInt(block);
							if (bl > 0 && bl <= 4) {
								block = "b" + bl;
								rs = DBUtil.findDPlayer(conn, mem[0]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
								}
								if(max_cor<cor) max_cor=cor;
								rs = DBUtil.findDPlayer(conn, mem[2]);
								while (rs.next()) {
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
								}
								if(max_cor<cor2) max_cor=cor2;
							} else if (bl >= 9) {
								block = "b" + (bl - 8);
								rs = DBUtil.findDPlayer(conn, mem[0]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
								}
								if(max_cor<cor) max_cor=cor;
								rs = DBUtil.findDPlayer(conn, mem[2]);
								while (rs.next()) {
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
								}
								if(max_cor<cor2) max_cor=cor2;
							}
							if ((hidden.equals("1")) && (turn == 2) && (left > 0) && (my_left > 0) && (left2 > 0)&&input_valid==1) {
								DBUtil.modMaxCor(conn, mem[0], max_cor);
								DBUtil.modMaxCor(conn, mem[1], max_cor);
								DBUtil.modMaxCor(conn, mem[2], max_cor);
								if (left2 > 0) {
									DBUtil.modTurn(conn, mem[0], 3);
									DBUtil.modTurn(conn, mem[1], 3);
									DBUtil.modTurn(conn, mem[2], 3);
								} else if (left > 0) {
									DBUtil.modTurn(conn, mem[0], 1);
									DBUtil.modTurn(conn, mem[1], 1);
									DBUtil.modTurn(conn, mem[2], 1);
								}
							}
							System.out.println("ans:" + ans + " input:" + input + " turn=" + turn);
							if ((hidden.equals("1")) && (ans == Integer.parseInt(input)) && (turn == 2)) {
								if (bl > 0 && bl < 5) {
									DBUtil.modOpen(conn, mem[0], block, 1);
									DBUtil.modLeft(conn, mem[0]);
									left -= 1;
								} else if (bl >= 9) {
									DBUtil.modOpen(conn, mem[2], block, 1);
									DBUtil.modLeft(conn, mem[2]);
									left -= 1;
								}
								DBUtil.modCor(conn, user);
								my_cor += 1;
							}

						} else if (user.equals(mem[2])) {
							int bl = Integer.parseInt(block);
							if (bl > 0 && bl <= 4) {
								block = "b" + bl;
								rs = DBUtil.findDPlayer(conn, mem[0]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
								}
								if(max_cor<cor) max_cor=cor;
								rs = DBUtil.findDPlayer(conn, mem[1]);
								while (rs.next()) {
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
								}
								if(max_cor<cor2) max_cor=cor2;
							} else if (bl > 4 && bl < 9) {
								block = "b" + (bl - 4);
								rs = DBUtil.findDPlayer(conn, mem[0]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
								}
								if(max_cor<cor) max_cor=cor;
								rs = DBUtil.findDPlayer(conn, mem[1]);
								while (rs.next()) {
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
								}
								if(max_cor<cor2) max_cor=cor2;
							}
							if ((hidden.equals("1")) && (turn == 3) && (left > 0) && (my_left > 0) && (left2 > 0)&&input_valid==1) {
								DBUtil.modMaxCor(conn, mem[0], max_cor);
								DBUtil.modMaxCor(conn, mem[1], max_cor);
								DBUtil.modMaxCor(conn, mem[2], max_cor);
								if (left > 0) {
									DBUtil.modTurn(conn, mem[0], 1);
									DBUtil.modTurn(conn, mem[1], 1);
									DBUtil.modTurn(conn, mem[2], 1);
								} else if (left2 > 0) {
									DBUtil.modTurn(conn, mem[0], 2);
									DBUtil.modTurn(conn, mem[1], 2);
									DBUtil.modTurn(conn, mem[2], 2);
								}
							}
							System.out.println("ans:" + ans + " input:" + input + " turn=" + turn);
							if ((hidden.equals("1")) && (ans == Integer.parseInt(input)) && (turn == 3)) {
								if (bl > 0 && bl < 5) {
									DBUtil.modOpen(conn, mem[0], block, 1);
									DBUtil.modLeft(conn, mem[0]);
									left -= 1;
								} else if (bl > 4 && bl < 9) {
									DBUtil.modOpen(conn, mem[1], block, 1);
									DBUtil.modLeft(conn, mem[1]);
									left -= 1;
								}
								DBUtil.modCor(conn, user);
								my_cor += 1;
							}
						}
					}
				}
				else if(game.equals("4players")) {
					if (input.equals("-1") && hidden.equals("0")) {
						System.out.println(user + " concluded");
						System.out.println("ans:" + ans + " input:" + input + " turn=" + turn);
						left = 0;
					}
					else {
						max_cor = my_cor;
						if(user.equals(mem[0])) {
							int bl = Integer.parseInt(block);
							if (bl > 4 && bl < 9) {
								block = "b" + (bl - 4);
								rs = DBUtil.findDPlayer(conn, mem[1]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
								}
								if (max_cor < cor)
									max_cor = cor;
								rs = DBUtil.findDPlayer(conn, mem[2]);
								while (rs.next()) {
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
								}
								if (max_cor < cor2)
									max_cor = cor2;
								rs = DBUtil.findDPlayer(conn, mem[3]);
								while (rs.next()) {
									left3 = rs.getInt("closed");
									cor3 = rs.getInt("correct");
								}
								if (max_cor < cor3)
									max_cor = cor3;
							}
							else if (13>bl&&bl >= 9) {
								block = "b" + (bl - 8);
								rs = DBUtil.findDPlayer(conn, mem[1]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
								}
								if (max_cor < cor)
									max_cor = cor;
								rs = DBUtil.findDPlayer(conn, mem[2]);
								while (rs.next()) {
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
								}
								if (max_cor < cor2)
									max_cor = cor2;
								rs = DBUtil.findDPlayer(conn, mem[3]);
								while (rs.next()) {
									left3 = rs.getInt("closed");
									cor3 = rs.getInt("correct");
								}
								if (max_cor < cor3)
									max_cor = cor3;
							}
							else if(bl>=13) {
								block = "b" + (bl - 12);
								rs = DBUtil.findDPlayer(conn, mem[1]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
								}
								if (max_cor < cor)
									max_cor = cor;
								rs = DBUtil.findDPlayer(conn, mem[2]);
								while (rs.next()) {
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
								}
								if (max_cor < cor2)
									max_cor = cor2;
								rs = DBUtil.findDPlayer(conn, mem[3]);
								while (rs.next()) {
									left3 = rs.getInt("closed");
									cor3 = rs.getInt("correct");
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
								}
							}
							if ((hidden.equals("1")) && (turn == 1) && (left > 0) && (my_left > 0) && (left2 > 0)&&(left3>0)&&input_valid==1) {
								DBUtil.modMaxCor(conn, mem[0], max_cor);
								DBUtil.modMaxCor(conn, mem[1], max_cor);
								DBUtil.modMaxCor(conn, mem[2], max_cor);
								DBUtil.modMaxCor(conn, mem[3], max_cor);
								if (left > 0) {
									DBUtil.modTurn(conn, mem[0], 2);
									DBUtil.modTurn(conn, mem[1], 2);
									DBUtil.modTurn(conn, mem[2], 2);
									DBUtil.modTurn(conn, mem[3], 2);
								} else if (left2 > 0) {
									DBUtil.modTurn(conn, mem[0], 3);
									DBUtil.modTurn(conn, mem[1], 3);
									DBUtil.modTurn(conn, mem[2], 3);
									DBUtil.modTurn(conn, mem[3], 3);
								}
								else if(left3>0) {
									DBUtil.modTurn(conn, mem[0], 4);
									DBUtil.modTurn(conn, mem[1], 4);
									DBUtil.modTurn(conn, mem[2], 4);
									DBUtil.modTurn(conn, mem[3], 4);
								}
							}
							System.out.println("ans:" + ans + " input:" + input + " turn=" + turn);
							if ((hidden.equals("1")) && (ans == Integer.parseInt(input)) && (turn == 1)) {
								if (bl > 4 && bl < 9) {
									DBUtil.modOpen(conn, mem[1], block, 1);
									DBUtil.modLeft(conn, mem[1]);
									left -= 1;
								} else if (bl<13&&bl >= 9) {
									DBUtil.modOpen(conn, mem[2], block, 1);
									DBUtil.modLeft(conn, mem[2]);
									left -= 1;
								}
								else if (bl>=13) {
									DBUtil.modOpen(conn, mem[3], block, 1);
									DBUtil.modLeft(conn, mem[3]);
									left -= 1;
								}
								DBUtil.modCor(conn, user);
								my_cor += 1;
							}
						}
						else if (user.equals(mem[1])) {
							int bl = Integer.parseInt(block);
							if (bl > 0 && bl <= 4) {
								block = "b" + bl;
								rs = DBUtil.findDPlayer(conn, mem[0]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
								}
								if(max_cor<cor) max_cor=cor;
								rs = DBUtil.findDPlayer(conn, mem[2]);
								while (rs.next()) {
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
								}
								if(max_cor<cor2) max_cor=cor2;
								rs = DBUtil.findDPlayer(conn, mem[3]);
								while (rs.next()) {
									left3 = rs.getInt("closed");
									cor3 = rs.getInt("correct");
								}
								if(max_cor<cor3) max_cor=cor3;
							}
							else if (bl >=9 && bl <13) {
								block = "b" + (bl-8);
								rs = DBUtil.findDPlayer(conn, mem[0]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
								}
								if(max_cor<cor) max_cor=cor;
								rs = DBUtil.findDPlayer(conn, mem[2]);
								while (rs.next()) {
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
								}
								if(max_cor<cor2) max_cor=cor2;
								rs = DBUtil.findDPlayer(conn, mem[3]);
								while (rs.next()) {
									left3 = rs.getInt("closed");
									cor3 = rs.getInt("correct");
								}
								if(max_cor<cor3) max_cor=cor3;
							}
							else if(bl>=13) {
								block = "b" + (bl-12);
								rs = DBUtil.findDPlayer(conn, mem[0]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
								}
								if(max_cor<cor) max_cor=cor;
								rs = DBUtil.findDPlayer(conn, mem[2]);
								while (rs.next()) {
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
								}
								if(max_cor<cor2) max_cor=cor2;
								rs = DBUtil.findDPlayer(conn, mem[3]);
								while (rs.next()) {
									left3 = rs.getInt("closed");
									cor3 = rs.getInt("correct");
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
								}
								if(max_cor<cor3) max_cor=cor3;
							}
							if ((hidden.equals("1")) && (turn == 2) && (left > 0) && (my_left > 0) && (left2 > 0)&&(left3>0)&&input_valid==1) {
								DBUtil.modMaxCor(conn, mem[0], max_cor);
								DBUtil.modMaxCor(conn, mem[1], max_cor);
								DBUtil.modMaxCor(conn, mem[2], max_cor);
								DBUtil.modMaxCor(conn, mem[3], max_cor);
								if (left2 > 0) {
									DBUtil.modTurn(conn, mem[0], 3);
									DBUtil.modTurn(conn, mem[1], 3);
									DBUtil.modTurn(conn, mem[2], 3);
									DBUtil.modTurn(conn, mem[3], 3);
								}
								else if(left3>0) {
									DBUtil.modTurn(conn, mem[0], 4);
									DBUtil.modTurn(conn, mem[1], 4);
									DBUtil.modTurn(conn, mem[2], 4);
									DBUtil.modTurn(conn, mem[3], 4);
								}
								else if (left > 0) {
									DBUtil.modTurn(conn, mem[0], 2);
									DBUtil.modTurn(conn, mem[1], 2);
									DBUtil.modTurn(conn, mem[2], 2);
									DBUtil.modTurn(conn, mem[3], 2);
								} 
							}
							System.out.println("ans:" + ans + " input:" + input + " turn=" + turn);
							if ((hidden.equals("1")) && (ans == Integer.parseInt(input)) && (turn == 2)) {
								if (bl > 0 && bl < 5) {
									DBUtil.modOpen(conn, mem[0], block, 1);
									DBUtil.modLeft(conn, mem[0]);
									left -= 1;
								} else if (bl<13&&bl >= 9) {
									DBUtil.modOpen(conn, mem[2], block, 1);
									DBUtil.modLeft(conn, mem[2]);
									left -= 1;
								}
								else if (bl>=13) {
									DBUtil.modOpen(conn, mem[3], block, 1);
									DBUtil.modLeft(conn, mem[3]);
									left -= 1;
								}
								DBUtil.modCor(conn, user);
								my_cor += 1;
							}
						}
						else if(user.equals(mem[2])) {
							int bl = Integer.parseInt(block);
							if (bl > 0 && bl <= 4) {
								block = "b" + bl;
								rs = DBUtil.findDPlayer(conn, mem[0]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
								}
								if(max_cor<cor) max_cor=cor;
								rs = DBUtil.findDPlayer(conn, mem[1]);
								while (rs.next()) {
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
								}
								if(max_cor<cor2) max_cor=cor2;
								rs = DBUtil.findDPlayer(conn, mem[3]);
								while (rs.next()) {
									left3 = rs.getInt("closed");
									cor3 = rs.getInt("correct");
								}
								if(max_cor<cor3) max_cor=cor3;
							}
							else if (bl >=5 && bl <9) {
								block = "b" + (bl-4);
								rs = DBUtil.findDPlayer(conn, mem[0]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
								}
								if(max_cor<cor) max_cor=cor;
								rs = DBUtil.findDPlayer(conn, mem[1]);
								while (rs.next()) {
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
								}
								if(max_cor<cor2) max_cor=cor2;
								rs = DBUtil.findDPlayer(conn, mem[3]);
								while (rs.next()) {
									left3 = rs.getInt("closed");
									cor3 = rs.getInt("correct");
								}
								if(max_cor<cor3) max_cor=cor3;
							}
							else if(bl>=13) {
								block = "b" + (bl-12);
								rs = DBUtil.findDPlayer(conn, mem[0]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
								}
								if(max_cor<cor) max_cor=cor;
								rs = DBUtil.findDPlayer(conn, mem[1]);
								while (rs.next()) {
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
								}
								if(max_cor<cor2) max_cor=cor2;
								rs = DBUtil.findDPlayer(conn, mem[3]);
								while (rs.next()) {
									left3 = rs.getInt("closed");
									cor3 = rs.getInt("correct");
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
								}
								if(max_cor<cor3) max_cor=cor3;
							}
							if ((hidden.equals("1")) && (turn == 3) && (left > 0) && (my_left > 0) && (left2 > 0)&&(left3>0)&&input_valid==1) {
								DBUtil.modMaxCor(conn, mem[0], max_cor);
								DBUtil.modMaxCor(conn, mem[1], max_cor);
								DBUtil.modMaxCor(conn, mem[2], max_cor);
								DBUtil.modMaxCor(conn, mem[3], max_cor);
								
								if(left3>0) {
									DBUtil.modTurn(conn, mem[0], 4);
									DBUtil.modTurn(conn, mem[1], 4);
									DBUtil.modTurn(conn, mem[2], 4);
									DBUtil.modTurn(conn, mem[3], 4);
								}
								else if (left > 0) {
									DBUtil.modTurn(conn, mem[0], 1);
									DBUtil.modTurn(conn, mem[1], 1);
									DBUtil.modTurn(conn, mem[2], 1);
									DBUtil.modTurn(conn, mem[3], 1);
								}
								else if (left2 > 0) {
									DBUtil.modTurn(conn, mem[0], 2);
									DBUtil.modTurn(conn, mem[1], 2);
									DBUtil.modTurn(conn, mem[2], 2);
									DBUtil.modTurn(conn, mem[3], 2);
								}
							}
							System.out.println("ans:" + ans + " input:" + input + " turn=" + turn);
							if ((hidden.equals("1")) && (ans == Integer.parseInt(input)) && (turn == 3)) {
								if (bl > 0 && bl < 5) {
									DBUtil.modOpen(conn, mem[0], block, 1);
									DBUtil.modLeft(conn, mem[0]);
									left -= 1;
								} else if (bl<9&&bl >= 5) {
									DBUtil.modOpen(conn, mem[1], block, 1);
									DBUtil.modLeft(conn, mem[1]);
									left -= 1;
								}
								else if (bl>=13) {
									DBUtil.modOpen(conn, mem[3], block, 1);
									DBUtil.modLeft(conn, mem[3]);
									left -= 1;
								}
								DBUtil.modCor(conn, user);
								my_cor += 1;
							}
						}
						else if(user.equals(mem[3])) {
							int bl = Integer.parseInt(block);
							if (bl > 0 && bl <= 4) {
								block = "b" + bl;
								rs = DBUtil.findDPlayer(conn, mem[0]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
								}
								if(max_cor<cor) max_cor=cor;
								rs = DBUtil.findDPlayer(conn, mem[1]);
								while (rs.next()) {
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
								}
								if(max_cor<cor2) max_cor=cor2;
								rs = DBUtil.findDPlayer(conn, mem[2]);
								while (rs.next()) {
									left3 = rs.getInt("closed");
									cor3 = rs.getInt("correct");
								}
								if(max_cor<cor3) max_cor=cor3;
							}
							else if (bl >=5 && bl <9) {
								block = "b" + (bl-4);
								rs = DBUtil.findDPlayer(conn, mem[0]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
								}
								if(max_cor<cor) max_cor=cor;
								rs = DBUtil.findDPlayer(conn, mem[1]);
								while (rs.next()) {
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
								}
								if(max_cor<cor2) max_cor=cor2;
								rs = DBUtil.findDPlayer(conn, mem[2]);
								while (rs.next()) {
									left3 = rs.getInt("closed");
									cor3 = rs.getInt("correct");
								}
								if(max_cor<cor3) max_cor=cor3;
							}
							else if(bl>=9&&bl<13) {
								block = "b" + (bl-8);
								rs = DBUtil.findDPlayer(conn, mem[0]);
								while (rs.next()) {
									left = rs.getInt("closed");
									cor = rs.getInt("correct");
								}
								if(max_cor<cor) max_cor=cor;
								rs = DBUtil.findDPlayer(conn, mem[1]);
								while (rs.next()) {
									left2 = rs.getInt("closed");
									cor2 = rs.getInt("correct");
								}
								if(max_cor<cor2) max_cor=cor2;
								rs = DBUtil.findDPlayer(conn, mem[2]);
								while (rs.next()) {
									left3 = rs.getInt("closed");
									cor3 = rs.getInt("correct");
									ans = rs.getInt(block + "_num");
									turn = rs.getInt("next_turn");
								}
								if(max_cor<cor3) max_cor=cor3;
							}
							if ((hidden.equals("1")) && (turn == 4) && (left > 0) && (my_left > 0) && (left2 > 0)&&(left3>0)&&input_valid==1) {
								DBUtil.modMaxCor(conn, mem[0], max_cor);
								DBUtil.modMaxCor(conn, mem[1], max_cor);
								DBUtil.modMaxCor(conn, mem[2], max_cor);
								DBUtil.modMaxCor(conn, mem[3], max_cor);
								 if (left > 0) {
									DBUtil.modTurn(conn, mem[0], 1);
									DBUtil.modTurn(conn, mem[1], 1);
									DBUtil.modTurn(conn, mem[2], 1);
									DBUtil.modTurn(conn, mem[3], 1);
								} 
								 else if (left2 > 0) {
										DBUtil.modTurn(conn, mem[0], 2);
										DBUtil.modTurn(conn, mem[1], 2);
										DBUtil.modTurn(conn, mem[2], 2);
										DBUtil.modTurn(conn, mem[3], 2);
								}
								else if(left3>0) {
										DBUtil.modTurn(conn, mem[0], 3);
										DBUtil.modTurn(conn, mem[1], 3);
										DBUtil.modTurn(conn, mem[2], 3);
										DBUtil.modTurn(conn, mem[3], 3);
								}
							}
							System.out.println("ans:" + ans + " input:" + input + " turn=" + turn);
							if ((hidden.equals("1")) && (ans == Integer.parseInt(input)) && (turn == 4)) {
								if (bl > 0 && bl < 5) {
									DBUtil.modOpen(conn, mem[0], block, 1);
									DBUtil.modLeft(conn, mem[0]);
									left -= 1;
								} else if (bl<9&&bl >= 5) {
									DBUtil.modOpen(conn, mem[1], block, 1);
									DBUtil.modLeft(conn, mem[1]);
									left -= 1;
								}
								else if (bl>=9&&bl<13) {
									DBUtil.modOpen(conn, mem[2], block, 1);
									DBUtil.modLeft(conn, mem[2]);
									left -= 1;
								}
								DBUtil.modCor(conn, user);
								my_cor += 1;
							}
						}
					}
				}
			} catch (NumberFormatException e) {
				input_valid = 0;
			}
			System.out.println("user:" + user + " my correct:" + my_cor + " left:" + left + " my left:" + my_left);
			int r = 0;
			if (game.equals("2players")) {
				if (left > 0) {
					// 상대방 패가 남았고 내 패도 남았을 때
					if (my_left > 0 || input_valid == 0&&(!input.equals("-1"))) {
						response.sendRedirect(redirUrl);
						// RequestDispatcher v=request.getRequestDispatcher("davinci2.jsp");
						// v.forward(request, response);
					} else {
						// 상대방 패가 남았고 내 패는 없을 때 패배
						request.setAttribute("result", 0);
						r = 0;
						DBUtil.modScore(conn, user, r, "d");
						String s = "select * from player where id='" + user + "';";
						Statement stmt = conn.createStatement();
						stmt.execute(s);
						ResultSet rsc = stmt.getResultSet();
						int vic = 0;
						int def = 0;
						while (rsc.next()) {
							vic = rsc.getInt("victory_d");
							System.out.println("vic:" + vic);
							def = rsc.getInt("defeat_d");
							System.out.println("def:" + def);
						}
						request.setAttribute("vic", vic);
						request.setAttribute("def", def);
						float score = (float) vic / (vic + def);
						request.setAttribute("vicRate", score);
						String url = "resultDav.jsp?vic=" + vic + "&def=" + def + "&vicRate=" + score + "&user=" + user
								+ "&game=" + game + "&grp=" + grp + "&num=" + num + "&result=" + r;
						response.sendRedirect(url);
						// RequestDispatcher v=request.getRequestDispatcher("resultDav.jsp");
						// v.forward(request, response);
					}
				} else {
					// 상대방 패가 안 남았고 내 가 다 맞췄을 떄
					if (left <= 0 && my_cor >= 4) {
						request.setAttribute("result", 1);
						r = 1;
					} else if (my_cor < 4 || my_left <= 0) {
						// 상대방패가 안 남았고 내가 다 못 맞췄을 때
						request.setAttribute("result", 0);
						r = 0;
					}
					DBUtil.modScore(conn, user, r, "d");
					String s = "select * from player where id='" + user + "';";
					Statement stmt = conn.createStatement();
					stmt.execute(s);
					ResultSet rsc = stmt.getResultSet();
					int vic = 0;
					int def = 0;
					while (rsc.next()) {
						vic = rsc.getInt("victory_d");
						System.out.println("vic:" + vic);
						def = rsc.getInt("defeat_d");
						System.out.println("def:" + def);
					}
					request.setAttribute("vic", vic);
					request.setAttribute("def", def);
					float score = (float) vic / (vic + def);
					request.setAttribute("vicRate", score);
					String url = "resultDav.jsp?vic=" + vic + "&def=" + def + "&vicRate=" + score + "&user=" + user
							+ "&game=" + game + "&grp=" + grp + "&num=" + num + "&result=" + r;
					response.sendRedirect(url);
					// RequestDispatcher v=request.getRequestDispatcher("resultDav.jsp");
					// v.forward(request, response);
				}
			} else if (game.equals("3players")) {
				// 세명 다 패가 남아 있을 때
				if ((!input.equals("-1"))&&(my_left > 0 && left > 0 && left2 > 0 || input_valid == 0)) {
					response.sendRedirect(redirUrl);
				} else {
					// 둘 중 한 명의 패가 다 떨어졌을 때
					if ((left <= 0 || left2 <= 0) && my_left > 0) {
						if (my_cor < max_cor)
							r = 0;
						else
							r = 1;
					} else if (my_left <= 0) {
						// 내 패가 없을 떄
						r = 0;
					}
					DBUtil.modScore(conn, user, r, "d");
					String s = "select * from player where id='" + user + "';";
					Statement stmt = conn.createStatement();
					stmt.execute(s);
					ResultSet rsc = stmt.getResultSet();
					int vic = 0;
					int def = 0;
					while (rsc.next()) {
						vic = rsc.getInt("victory_d");
						System.out.println("vic:" + vic);
						def = rsc.getInt("defeat_d");
						System.out.println("def:" + def);
					}
					float score = (float) vic / (vic + def);
					String url = "resultDav.jsp?vic=" + vic + "&def=" + def + "&vicRate=" + score + "&user=" + user
							+ "&game=" + game + "&grp=" + grp + "&num=" + num + "&result=" + r;
					response.sendRedirect(url);
				}
			}
			else if(game.equals("4players")) {
				// 네명 다 패가 남아 있을 때
				if ((!input.equals("-1"))&&(my_left > 0 && left > 0 && left2 > 0 &&left3>0|| input_valid == 0) ){
					response.sendRedirect(redirUrl);
				} else {
					// 셋 중 한 명의 패가 다 떨어졌을 때
					if ((left <= 0 || left2 <= 0||left3<=0) && my_left > 0) {
						if (my_cor < max_cor)
							r = 0;
						else
							r = 1;
					} else if (my_left <= 0) {
						// 내 패가 없을 떄
						r = 0;
					}
					DBUtil.modScore(conn, user, r, "d");
					String s = "select * from player where id='" + user + "';";
					Statement stmt = conn.createStatement();
					stmt.execute(s);
					ResultSet rsc = stmt.getResultSet();
					int vic = 0;
					int def = 0;
					while (rsc.next()) {
						vic = rsc.getInt("victory_d");
						System.out.println("vic:" + vic);
						def = rsc.getInt("defeat_d");
						System.out.println("def:" + def);
					}
					float score = (float) vic / (vic + def);
					String url = "resultDav.jsp?vic=" + vic + "&def=" + def + "&vicRate=" + score + "&user=" + user
							+ "&game=" + game + "&grp=" + grp + "&num=" + num + "&result=" + r;
					response.sendRedirect(url);
				}
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
