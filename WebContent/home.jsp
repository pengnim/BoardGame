<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*,model.DBUtil"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name='viewport'
	content="width=device-width,initial-scale=1.0">
<style type="text/css">
body {
	background-image: url("brain.png");
	background-repeat: no-repeat;
	background-position: 50% ;
	font-family: "Times new Roman", "Times new Roman", "serif";
	width: 100%;
	text-align: center;
}

#border {
	width: 100%;
	background-color: black;
	height: 5px;
	margin-top:10pt;
}

#head {
	font-size: 40pt;
	font-weight: bold;
	text-align: center;
	color:white;
	text-shadow: 0 0 5px #000000;	
}

#score {
	position: absolute;
	bottom: 15%;
	left: 0px;
	right: 0px;
	width: auto;
	text-align: center;
	display: inline;
	bottom: 10%;
}
#btn{
font-size:18pt;
margin-left:20px;
margin-top:10px;
}
.block {
	display: inline-block;
	text-align: center;
	width: 30%;
}

.h2 {
	font-size: 18pt;
	font-family: "papyrus", "papyrus", "fantasy";
	font-weight: normal;
}

.h3 {
	font-size: 16pt;
	font-family: "oldtown", "oldtown", "oldtown";
	font-weight: normal;
}

form {
	font-size: 20pt;
	padding: 20pt;
	padding-left: 20%;
	text-align:left;
}

input,.game {
	color: black;
	
}
#logout{
	font-size:18pt;
}
@media screen and (max-width:990px) {
#logout{
	font-size:14pt;
}
	body {
		background-position: 50% 25%;
		background-size: auto;
		width: 100%;
	}
	form {
	position:relative;
	width:auto;
	margin-left:0;
	text-align:left;
}
	input,.game {
		color: white;
		text-shadow: 0 0 5px #000000;
		font-size:18pt;
		text-align:left;
		margin-left:0;
	}
	#border {
	width: inherit;
	background-color: black;
	height: 5px;
	}

#head {
	font-size: 24pt;
	font-weight: bold;
	text-align: center;
	height:auto;
	width:100%;
	color:white;
}

#score {
	width: auto;
	text-align: center;
	display: inline-block;
	position:relative;
	top:10pt;
}

.block {
	display: inline-block;
	text-align: center;
	width: 100%;
}
#user{color:black;
width:auto;
font-size:12pt;}
.h2 {
	font-size: 20pt;
}

.h3 {
	font-size: 16pt;
}
#btn{
margin-left:20%;
}
</style>

<title>Select Game</title>
</head>
<body>
	<% 
		ServletContext sc=getServletContext();
		Connection conn=(Connection)sc.getAttribute("DBconnection");
		String name=(String)request.getAttribute("username");
		name=(String)request.getAttribute("username");
		out.print("<header id='head'>Welcome " + name + "</header>");
		String id=(String)session.getAttribute("name");
		session.setAttribute("name",name);
		sc=getServletContext();
		conn=(Connection)sc.getAttribute("DBconnection");
		ResultSet rs=DBUtil.findScore(conn, name);
		int vd=0,vm=0,vh=0,dd=0,dh=0,vmo=0,vb=0,db=0;
		while(rs.next()){
			vd=rs.getInt("victory_d");
			vm=rs.getInt("matching_score");
			vh=rs.getInt("victory_h");
			dd=rs.getInt("defeat_d");
			dh=rs.getInt("defeat_h");
			vmo=rs.getInt("mole_score");
			vb=rs.getInt("victory_b");
			db=rs.getInt("defeat_b");
		}
		System.out.println(vd+" "+vm+" "+vh+" "+dd+" "+dh);
	%>
	<form action="ChooseGame" method="post">
	<input type="radio" name="game" value="davinci" checked='checked'><span class="game">davinci code</span><br>
	<input type="radio" name="game" value="matching"><span class="game">matching game</span><br>
	<input type="radio" name="game" value="hangman"><span class="game">hangman</span><br>
	<input type="radio" name="game" value="mole"><span class="game">mole game</span><br>
	<input type="radio" name="game" value="baseball"><span class="game">baseball game</span><br>
	<input type="radio" name="game" value="howto" checked="checked"><span class="game">how to play</span><br>
	<% out.print("<input id='user' type='text' name='uid' value='"+name+"' readonly><br>"); %>
	<input type="submit" id="btn" value="select" >
	</form>
	<form action="LogOut" method="post">
	<% out.print("<input id='user' type='text' name='uid' value='"+name+"' readonly style='display:none'><br>"); %>
	<input type="submit" id="logout" value="log out" />
	</form>
	
	<div id="border"></div>
	<div id="score">
		<div class="block">
			<div class="h2">Victory</div>
			<div class="h3">
				Davinci code:<% 
				out.print(vd);
				%><br> Matching game:<%
				out.print(vm);
				%>
				<br> Mole game:<%
				out.print(vmo);
				%>
				<br> Hang man game:<% 
				out.print(vh);
				%>
				<br> baseball game:<% 
				out.print(vb);
				%>
			</div>
		</div>
		<div class="block">
			<div class="h2">Defeat</div>
			<div class="h3">
				Davinci code:<%
				out.print(dd);
				%>
				<br> Hang man game:<% 
				out.print(dh);
				%>
				<br> baseball game:<% 
				out.print(db);
				%>
			</div>
		</div>
		<div class="block">
			<div class="h2">Victory Rates</div>
			<div class="h3">
				Davinci code:<%
				float fd=0;
				if(vd==0&&dd==0)
					fd=0;
				else
					fd=(float)vd/(vd+dd); 
				out.print(fd);
				%>
				<br> Hang man game:<% 
				float fh=0;
				if(vh==0&&dh==0)
					fh=0;
				else
					fh=(float)vh/(vh+dh); 
				out.print(fh);
				%>
				<br> baseball game:<% 
				float fb=0;
				if(vb==0&&db==0)
					fb=0;
				else
					fb=(float)vb/(vb+db); 
				out.print(fb);
				%>
			</div>
		</div>
	</div>
</body>
</html>