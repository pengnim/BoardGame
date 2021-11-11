<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*,model.DBUtil"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name='viewport'
	content="width=device-width,initial-scale=1.0">
<style>
body {
	width: 100%;
}

#p1 {
	width: 300px;
	margin-left: 35%;
	height: 150px;
}

table, tr, th {
	border-collapse: collapse;
	border: 1px solid black;
}

#deq {
	width: 300px;
	height: 300px;
	position: absolute;
	top: 60%;
	left: 35%;
}

#p3 {
	width: 150px;
	height: 300px;
	position: relative;
	bottom: 500px;
	left: 85%;
}
#p4{
	width: 300px;
	height: 300px;
	position:relative;
	bottom:500px;
	left:60%;
}
#p2 {
	width: 150px;
	height: 300px;
}

#btn {
	font-size: 18pt;
	width: 100px;
	height: auto;
	text-align: center;
	position: relative;
	bottom: 380px;
	left: 52%;
}

#sub {
	font-size: 18pt;
	width: auto;
	height: auto;
	text-align: center;
	position: relative;
	bottom: 250px;
	left: 50%;
}

#grp {
	position: relative;
	left: 48%;
	bottom: 200px;
	text-align: center;
	font-size: 14pt;
}

}
.rad,span {
	text-align: center;
	font-size: 20pt;
}

#name, #gname {
	position: relative;
	left: 48%;
	bottom: 200px;
	text-align: center;
	background-color: #ababab;
	color: white;
	font-size: 14pt;
}

@media screen and (max-width:990px) {
	#p1 {
		width: 200px;
		margin-left: 28%;
		height: 100px;
	}
	#p3 {
		width: 100px;
		height: 200px;
		position: relative;
		bottom: 400px;
		left: 80%;
	}
	#p4{
	position:relative;
	bottom:290px;
	width:200px;
	height:200px;
	left:25%;
	}
	#p2 {
		width: 100px;
		height: 200px;
	}
	#deq {
		width: 200px;
		height: 200px;
		position: absolute;
		top: 72%;
		left: 10%;
	}
	#btn {
		font-size: 14pt;
		width: 50px;
		height: auto;
		text-align: center;
		position: relative;
		bottom: 250px;
		left: 30%;
	}
	#sub {
		font-size: 14pt;
		width: auto;
		height: auto;
		text-align: center;
		position: relative;
		bottom: 100px;
		margin-right: 100px;
		margin-left: 0;
	}
	#grp {
		position: relative;
		left: 30%;
		bottom: 115px;
		text-align: center;
		font-size: 12pt;
		width: 150px;
	}
	.rad {
		position: relative;
		bottom: 90px;
		left: 5%;
		text-align: left;
		font-size: 12pt;
	}
	span {
		position: relative;
		bottom: 90px;
		left: 5%;
		text-align: left;
		font-size: 12pt;
		width: auto;
	}
	#name, #gname {
		position: relative;
		left: 30%;
		bottom: 115px;
		text-align: center;
		background-color: #ababab;
		color: white;
		font-size: 12pt;
		width: 150px;
	}
}
</style>
<script>
	var a1 = [];
	var a2 = [];
	var a3 = [];
	var a4 = [];
	var a5 = [];
	var p1_op = [];
	var p2_op = [];
	var p3_op = [];
	var p4_op = [];
	var bl1 = [ "b1", "b2", "b3", "b4" ];
	var bl2 = [ "b5", "b6", "b7", "b8" ];
	var bl3 = [ "b9", "b10", "b11", "b12" ];
	var bl4 = [ "b13", "b14", "b15", "b16" ];
	var bl5 = [ "b17", "b18", "b19", "b20", "b21", "b22", "b23", "b24" ];
	var turn = 0, cor = 0, mine = 4;
	function pagestart() {
		window.setTimeout("pagereload()", 10000);
		document.getElementById("hidden").value='0';
		document.getElementById("hidden").value = null;
		var radio=document.getElementsByName("block");
		<%
		String[] pl = new String[4];
		ServletContext sc = getServletContext();
		Connection con = (Connection) sc.getAttribute("DBconnection");
		String grp = (String) request.getParameter("grp");
		String user = (String) request.getParameter("user");
		String game = request.getParameter("game");
		System.out.println("user:" + user + " refreshed");
		System.out.println("user:" + user + " grp:" + grp + " game:" + game);
		ResultSet m = DBUtil.findDPlayer(con, user);
		int my_left = 4;
		while (m.next()) {
			my_left = m.getInt("closed");
		}
		if (my_left <= 0) {
			out.print("document.getElementById('grp').value='-1';");
			out.print("document.Ent.submit();");
		}
		ResultSet rs = DBUtil.getGrpMem(con, grp, 4);
		int b1_op = 0, b2_op = 0, b3_op = 0, b4_op = 0;
		int[] p1_op = new int[4];
		int[] p2_op = new int[4];
		int[] p3_op = new int[4];
		int[] p4_op = new int[4];
		while (rs.next()) {
			pl[0] = rs.getString("p1");
			pl[1] = rs.getString("p2");
			pl[2] = rs.getString("p3");
			pl[3] = rs.getString("p4");
			if (DBUtil.checkDPlayer(con, pl[0]) || DBUtil.checkDPlayer(con, pl[1])
					|| DBUtil.checkDPlayer(con, pl[2]) || DBUtil.checkDPlayer(con, pl[3])) {
				out.print("document.getElementById('grp').value='-1';");
				out.print("document.Ent.submit();");
			}
			System.out.println(pl[0] + pl[1] + pl[2] + pl[3]);
			ResultSet r = DBUtil.findDPlayer(con, pl[0]);
			while (r.next()) {
				int b1N = r.getInt(2);
				String b1C = r.getString(3);
				int b2N = r.getInt(4);
				String b2C = r.getString(5);
				int b3N = r.getInt(6);
				String b3C = r.getString(7);
				int b4N = r.getInt(8);
				String b4C = r.getString(9);
				p1_op[0] = r.getInt("b1_open");
				p1_op[1] = r.getInt("b2_open");
				p1_op[2] = r.getInt("b3_open");
				p1_op[3] = r.getInt("b4_open");
				out.print("p1_op[0]=[" + p1_op[0] + "];");
				out.print("p1_op[1]=[" + p1_op[1] + "];");
				out.print("p1_op[2]=[" + p1_op[2] + "];");
				out.print("p1_op[3]=[" + p1_op[3] + "];");
				out.print("a1[0]=[" + b1N + ",'" + b1C + "'];");
				out.print("a1[1]=[" + b2N + ",'" + b2C + "'];");
				out.print("a1[2]=[" + b3N + ",'" + b3C + "'];");
				out.print("a1[3]=[" + b4N + ",'" + b4C + "'];");
			}
			r = DBUtil.findDPlayer(con, pl[1]);
			while (r.next()) {
				int b1N = r.getInt(2);
				String b1C = r.getString(3);
				int b2N = r.getInt(4);
				String b2C = r.getString(5);
				int b3N = r.getInt(6);
				String b3C = r.getString(7);
				int b4N = r.getInt(8);
				String b4C = r.getString(9);
				p2_op[0] = r.getInt("b1_open");
				p2_op[1] = r.getInt("b2_open");
				p2_op[2] = r.getInt("b3_open");
				p2_op[3] = r.getInt("b4_open");
				out.print("p2_op[0]=[" + p2_op[0] + "];");
				out.print("p2_op[1]=[" + p2_op[1] + "];");
				out.print("p2_op[2]=[" + p2_op[2] + "];");
				out.print("p2_op[3]=[" + p2_op[3] + "];");
				out.print("a2[0]=[" + b1N + ",'" + b1C + "'];");
				out.print("a2[1]=[" + b2N + ",'" + b2C + "'];");
				out.print("a2[2]=[" + b3N + ",'" + b3C + "'];");
				out.print("a2[3]=[" + b4N + ",'" + b4C + "'];");
			}
			r = DBUtil.findDPlayer(con, pl[2]);
			while (r.next()) {
				int b1N = r.getInt(2);
				String b1C = r.getString(3);
				int b2N = r.getInt(4);
				String b2C = r.getString(5);
				int b3N = r.getInt(6);
				String b3C = r.getString(7);
				int b4N = r.getInt(8);
				String b4C = r.getString(9);
				p3_op[0] = r.getInt("b1_open");
				p3_op[1] = r.getInt("b2_open");
				p3_op[2] = r.getInt("b3_open");
				p3_op[3] = r.getInt("b4_open");
				out.print("p3_op[0]=[" + p3_op[0] + "];");
				out.print("p3_op[1]=[" + p3_op[1] + "];");
				out.print("p3_op[2]=[" + p3_op[2] + "];");
				out.print("p3_op[3]=[" + p3_op[3] + "];");
				out.print("a3[0]=[" + b1N + ",'" + b1C + "'];");
				out.print("a3[1]=[" + b2N + ",'" + b2C + "'];");
				out.print("a3[2]=[" + b3N + ",'" + b3C + "'];");
				out.print("a3[3]=[" + b4N + ",'" + b4C + "'];");
			}
			r = DBUtil.findDPlayer(con, pl[3]);
			while (r.next()) {
				int b1N = r.getInt(2);
				String b1C = r.getString(3);
				int b2N = r.getInt(4);
				String b2C = r.getString(5);
				int b3N = r.getInt(6);
				String b3C = r.getString(7);
				int b4N = r.getInt(8);
				String b4C = r.getString(9);
				p4_op[0] = r.getInt("b1_open");
				p4_op[1] = r.getInt("b2_open");
				p4_op[2] = r.getInt("b3_open");
				p4_op[3] = r.getInt("b4_open");
				out.print("p4_op[0]=[" + p4_op[0] + "];");
				out.print("p4_op[1]=[" + p4_op[1] + "];");
				out.print("p4_op[2]=[" + p4_op[2] + "];");
				out.print("p4_op[3]=[" + p4_op[3] + "];");
				out.print("a4[0]=[" + b1N + ",'" + b1C + "'];");
				out.print("a4[1]=[" + b2N + ",'" + b2C + "'];");
				out.print("a4[2]=[" + b3N + ",'" + b3C + "'];");
				out.print("a4[3]=[" + b4N + ",'" + b4C + "'];");
			}
			ResultSet dc = DBUtil.findDeq4(con, pl[0]);
			String[] color = new String[8];
			int[] num = new int[8];
			while (dc.next()) {
				//2 4 6 8 10 12 14 16 
				for (int i = 0; i < 8; i++) {
					color[i] = dc.getString((i+1)*2+1);
					num[i] = dc.getInt((i+1)*2);
					out.print("a5[" + i + "]=[" + num[i] + ",'" + color[i] + "'];");
				}
			}
		}
		int turn = 0;
		ResultSet t = DBUtil.findDPlayer(con, user);
		while (t.next()) {
			turn = t.getInt("next_turn");
		}
		System.out.println("present turn:" + turn);
		if (turn == 1) {
			if (user.equals(pl[0])) {
				for (int i = 0; i < 12; i++) {
					out.print("radio[" + i + "].disabled=false;");
					out.print("radio[" + i + "].style.color='black';");
				}
			}
			else if (user.equals(pl[1])) {
				for (int i = 0; i < 12; i++) {
					out.print("radio[" + i + "].disabled=true;");
					out.print("radio[" + i + "].style.color='black';");
				}

			}
			else if(user.equals(pl[2])){
				for (int i = 0; i < 12; i++) {
					out.print("radio[" + i + "].disabled=true;");
					out.print("radio[" + i + "].style.color='black';");
				}
			}
			else if(user.equals(pl[3])){
				for (int i = 0; i < 12; i++) {
					out.print("radio[" + i + "].disabled=true;");
					out.print("radio[" + i + "].style.color='black';");
				}
			} 
		}
		else if (turn == 2) {
			if (user.equals(pl[1])) {
				for (int i = 0; i < 12; i++) {
					out.print("radio[" + i + "].disabled=false;");
					out.print("radio[" + i + "].style.color='black';");
				}
			}
			else if (pl[0].equals(user)) {
				for (int i = 0; i < 12; i++) {
					out.print("radio[" + i + "].disabled=true;");
					out.print("radio[" + i + "].style.color='black';");
				}
			}
			else if(user.equals(pl[2])){
				for (int i = 0; i < 12; i++) {
					out.print("radio[" + i + "].disabled=true;");
					out.print("radio[" + i + "].style.color='black';");
				}
			}
			else if(user.equals(pl[3])){
				for (int i = 0; i < 12; i++) {
					out.print("radio[" + i + "].disabled=true;");
					out.print("radio[" + i + "].style.color='black';");
				}
			}
		}
		else if (turn == 3) {
			 if (pl[0].equals(user)) {
				for (int i = 0; i < 12; i++) {
					out.print("radio[" + i + "].disabled=true;");
					out.print("radio[" + i + "].style.color='black';");
				}
			}
			 else if(pl[1].equals(user)){
					for (int i = 0; i < 12; i++) {
						out.print("radio[" + i + "].disabled=true;");
						out.print("radio[" + i + "].style.color='black';");
					}
			}
			 else if(user.equals(pl[2])){
					for (int i = 0; i < 12; i++) {
						out.print("radio[" + i + "].disabled=false;");
						out.print("radio[" + i + "].style.color='black';");
					}
			}
			 else if(user.equals(pl[3])){
					for (int i = 0; i < 12; i++) {
						out.print("radio[" + i + "].disabled=true;");
						out.print("radio[" + i + "].style.color='black';");
					}
			} 
		}
		else if (turn == 4) {
			if (user.equals(pl[3])) {
				for (int i = 0; i < 12; i++) {
					out.print("radio[" + i + "].disabled=false;");
					out.print("radio[" + i + "].style.color='black';");
				}
			} 
			else if (pl[0].equals(user)) {
				for (int i = 0; i < 12; i++) {
					out.print("radio[" + i + "].disabled=true;");
					out.print("radio[" + i + "].style.color='black';");
				}
			}
			else if(pl[1].equals(user)){
				for (int i = 0; i < 12; i++) {
					out.print("radio[" + i + "].disabled=true;");
					out.print("radio[" + i + "].style.color='black';");
				}
			}
			else if(user.equals(pl[2])){
				for (int i = 0; i < 12; i++) {
					out.print("radio[" + i + "].disabled=true;");
					out.print("radio[" + i + "].style.color='black';");
				}
			}
		}
		%>
		for (var i = 0; i < 4; i++) {
			var e1 = document.getElementById(bl1[i]);
			var e2 = document.getElementById(bl2[i]);
			var e3 = document.getElementById(bl3[i]);
			var e4 = document.getElementById(bl4[i]);
			if (a1[i][1] == "black")
			e1.style.background = "black";
			else
			e1.style.background = "white";
			if (e1.style.background == "white")
				e1.style.color = "black";
			else if (e1.style.background == "black")
				e1.style.color = "white";
			<%
			if(pl[0].equals(user)) {
				out.print("e1.innerHTML=a1[i][0];");
			}
			%>
			if (p1_op[i] == 1) {
				e1.innerHTML = a1[i][0];
			}
			if (a2[i][1] == "black")
				e2.style.background = "black";
			else
				e2.style.background = "white";
			if (e2.style.background == "white")
				e2.style.color = "black";
			else if (e2.style.background == "black")
				e2.style.color = "white";
			<%
			if (pl[1].equals(user)) {
				out.print("e2.innerHTML=a2[i][0];");
			}
			%>
			if (p2_op[i] == 1) {
				e2.innerHTML = a2[i][0];
			}
			if (a3[i][1] == "black")
				e3.style.background = "black";
			else
				e3.style.background = "white";
			if (e3.style.background == "white")
				e3.style.color = "black";
			else if (e3.style.background == "black")
				e3.style.color = "white";
			<%
			if (pl[2].equals(user)) {
				out.print("e3.innerHTML=a3[i][0];");
			}
			%>
			if (p3_op[i] == 1) {
				e3.innerHTML = a3[i][0];
			}
			if (a4[i][1] == "black")
				e4.style.background = "black";
			else
				e4.style.background = "white";
			if (e4.style.background == "white")
				e4.style.color = "black";
			else if (e4.style.background == "black")
				e4.style.color = "white";
			<%
			if (pl[3].equals(user)) {
				out.print("e4.innerHTML=a4[i][0];");
			 }
			%>
			if (p4_op[i] == 1) {
				e4.innerHTML = a4[i][0];
			}
		}
		for (var i = 0; i < 8; i++) {
			var el = document.getElementById(bl5[i]);
			if (a5[i][1] == "black")
				el.style.background = "black";
			else
				el.style.background = "white";
			el.innerHTML = a5[i][0];
			if (el.style.background == "white")
				el.style.color = "black";
			else if (el.style.background == "black")
				el.style.color = "white";
		}
	}
	function pagereload() {
		location.reload();
		document.getElementById("hidden").value = '0';
	}
	function func() {
		document.getElementById("hidden").value = '1';
		if(document.getElementById("grp")==null) document.getElementById("grp").value=-1;
	}
</script>
<title>Davinci game</title>
</head>
<body onLoad="pagestart()">
	<table id="p1">
		<tr>
			<th id="b1">b1</th>
			<th id="b2">b2</th>
			<th id="b3">b3</th>
			<th id="b4">b4</th>
		</tr>
	</table>
	<table id="p2">
		<tr>
			<th id="b5">b5</th>
		</tr>
		<tr>
			<th id="b6">b6</th>
		</tr>
		<tr>
			<th id="b7">b7</th>
		</tr>
		<tr>
			<th id="b8">b8</th>
		</tr>
	</table>
	<form action="SelNum" method="post" name="Ent">
	<input type="text" name="hidden" value="0" id="hidden" style="display:none">
		<%
			Cookie[] list = request.getCookies();
			out.print("<input type='text' name='uname' value='" + user + "' id='name' readonly><br>");
			out.print("<input type='text' name='gname' value='" + pl[0] + "' id='gname' readonly><br>");
			out.print(
					"<input type='text' name='game' value='" + game + "' id='game' style='display:none' readonly><br>");
		%>
		<input type="text"  placeholder="input number" name="result" id="grp"><br>
	<%
	if (pl[0].equals(user)) {
		for (int i = 5; i < 9; i++)
			out.print("<input type='radio' class='rad' name='block' value='" + i + "'><span>block" + i
					+ "</span>");
		out.print("<br>");
		for (int i = 9; i < 13; i++)
			out.print("<input type='radio' class='rad' name='block' value='" + i + "'><span>block" + i
					+ "</span>");
		out.print("<br>");
		for (int i = 13; i < 17; i++)
			out.print("<input type='radio' class='rad' name='block' value='" + i + "'><span>block" + i
					+ "</span>");
	} else if (pl[1].equals(user)) {
		for (int i = 1; i < 5; i++)
			out.print("<input type='radio' class='rad' name='block' value='" + i + "'><span>block" + i
					+ "</span>");
		out.print("<br>");
		for (int i = 9; i < 13; i++)
			out.print("<input type='radio' class='rad' name='block' value='" + i + "'><span>block" + i
					+ "</span>");
		out.print("<br>");
		for (int i = 13; i < 17; i++)
			out.print("<input type='radio' class='rad' name='block' value='" + i + "'><span>block" + i
					+ "</span>");
	} else if (pl[2].equals(user)) {
		for (int i = 1; i < 5; i++)
			out.print("<input type='radio' class='rad' name='block' value='" + i + "'><span>block" + i
					+ "</span>");
		out.print("<br>");
		for (int i = 5; i < 9; i++)
			out.print("<input type='radio' class='rad' name='block' value='" + i + "'><span>block" + i
					+ "</span>");
		out.print("<br>");
		for (int i = 13; i < 17; i++)
			out.print("<input type='radio' class='rad' name='block' value='" + i + "'><span>block" + i
					+ "</span>");
	}
	else if(pl[3].equals(user)){
		for (int i = 1; i < 5; i++)
			out.print("<input type='radio' class='rad' name='block' value='" + i + "'><span>block" + i
					+ "</span>");
		out.print("<br>");
		for (int i = 5; i < 9; i++)
			out.print("<input type='radio' class='rad' name='block' value='" + i + "'><span>block" + i
					+ "</span>");
		out.print("<br>");
		for(int i=9;i<13;i++)
			out.print("<input type='radio' class='rad' name='block' value='" + i + "'><span>block" + i
					+ "</span>");
		out.print("<br>");
	}
	%>
		<br> <input type="submit" id="sub"
			style="background-color: #bdbdbd" value="enter" onclick="func();"><br>
	</form>
	<table id="p3">
		<tr>
			<th id="b9">b9</th>
		</tr>
		<tr>
			<th id="b10">b10</th>
		</tr>
		<tr>
			<th id="b11">b11</th>
		</tr>
		<tr>
			<th id="b12">b12</th>
		</tr>
	</table>
	<table id="deq">
		<tr>
			<th id="b17"><div>
					<br>
				</div></th>
			<th id="b18"><div>
					<br>
				</div></th>
			<th id="b19"><div>
					<br>
				</div></th>
			<th id="b20"><div>
					<br>
				</div></th>
		</tr>
		<tr>
			<th id="b21"><div>
					<br>
				</div></th>
			<th id="b22"><div>
					<br>
				</div></th>
			<th id="b23"><div>
					<br>
				</div></th>
			<th id="b24"><div>
					<br>
				</div></th>
		</tr>
	</table>
	<table id="p4">
		<tr>
			<th id="b13">b13</th>
			<th id="b14">b14</th>
			<th id="b15">b15</th>
			<th id="b16">b16</th>
		</tr>
		<tr>

		</tr>
	</table>
</body>
</html>