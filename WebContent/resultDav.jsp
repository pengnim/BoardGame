<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*,model.DBUtil"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name='viewport' content="width=device-width,initial-scale=1.0">
<style type="text/css">
#head {
	border-style: solid;
	width: 40%;
	text-align: center;
	border-width: 2px;
	font-size: 30pt;
	font-weight:bold;
	margin-left:30%;
	margin-bottom:10pt;
}
#border {
	width: 100%;
	background-color: black;
	height: 5px;
}
#score{
	border-style: solid;
	width: 40%;
	text-align: center;
	border-width: 1px;
	font-size: 20pt;
	margin-left:30%;
	margin-bottom:10pt;
}
#name{
text-align:center;
font-size:20pt;
margin-top:10pt;
}
#btn{
 margin-left:45%;
 font-size:20pt;
}
#result{
font-weight:bold;
font-size:30pt;
text-align:center;
}
.content2 {
	margin-top:15%;
	width: 50%;
	display: inline;
	font-size:1.1em;
}
.req{
visibility:hidden;
}
span{
color:red;
font-weight:bold;
font-size:10pt;
}
@media screen and (max-width:990px){
#score{
	border-style: solid;
	width: 40%;
	text-align: center;
	border-width: 1px;
	font-size: 14pt;
	margin-left:30%;
	margin-bottom:5px;
}
span{
color:red;
font-weight:bold;
font-size:8pt;
position:relative;
bottom:40px;
}
#btn{
 margin-left:40%;
 margin-top:20%;
 font-size:20pt;
}
#name{
text-align:center;
font-size:12pt;
margin-top:30px;
}
#head{
font-size:20pt;
}
#result{
font-weight:bold;
font-size:20pt;
text-align:center;
margin-top:10px;
}
.content2{
width:100%;
position:absolute;
left:0;
top:40%;
font-size:12pt;
}
}
</style>
<title> Davinci Code Result</title>
<script type="text/javascript">
function pagestart() {
	window.setTimeout("pagereload()", 2000);
	}
	function pagereload() {
	location.reload();
	}

</script>
</head>
<body onLoad="pagestart()">
	<div id="head">Davinci Code Result</div>
	<div id="border"></div>
	<div class="content2">
	<div id="name"><%
	Cookie[] list=request.getCookies();
	String user=(String)request.getParameter("user");
	int num=Integer.parseInt(request.getParameter("num"));
	String grp=(String)request.getParameter("grp");
	ServletContext sc=getServletContext();
	Connection conn=(Connection)sc.getAttribute("DBconnection");
	%></div>
		<div id="result">
		<%
		int re=Integer.parseInt(request.getParameter("result"));
		if(re==1)
			out.print("Victory!!!!");
		else if(re==0)
			out.print("Defeated");
		%>
		</div>
	</div>
	<div id="score">
	<% 
	int v=Integer.parseInt(request.getParameter("vic")); 
	int d=Integer.parseInt(request.getParameter("def"));
	float rate=Float.parseFloat(request.getParameter("vicRate"));
	out.print("<p>Victory: "+v+"</p><p>Defeat: "+d+"</p><p>Victory Rate: "+rate+"</p><br>");
	%>
	<span>If you're the leader, your button will appear when all of your members are out.</span>
	<span>Click until you go back to the home</span>
	</div>
	<form method="post" action="ToHome">
	<%
	String[] mem=new String[num];
	ResultSet rs=DBUtil.getGrpMem(conn, grp, num);
	while(rs.next()){
		for(int i=0;i<num;i++){
			mem[i]=rs.getString("p"+(i+1));
		}
	}
	if(num==2){
		if(!DBUtil.checkDPlayer(conn, user))
			DBUtil.deleteDPlayer(conn, user);
		if((mem[0]!=null)&&mem[0].equals(user)){
			if(DBUtil.checkDPlayer(conn, mem[1])&&!mem[0].equals(DBUtil.getGrp(conn,mem[1])))
				out.print("<input type='submit' id='btn' value='home'>");
		}
		else if((mem[1]!=null)&&mem[1].equals(user)){
			if(DBUtil.checkDPlayer(conn, mem[0]))
				out.print("<input type='submit' id='btn' value='home'>");
		}
	}
	else if(num==3){
		if(!DBUtil.checkDPlayer(conn, user))
			DBUtil.deleteDPlayer(conn, user);
		if((mem[0]!=null)&&mem[0].equals(user)){
			if(DBUtil.checkDPlayer(conn, mem[1])&&DBUtil.checkDPlayer(conn, mem[2])&&(!mem[0].equals(DBUtil.getGrp(conn,mem[1]))&&!mem[0].equals(DBUtil.getGrp(conn, mem[2]))))
				out.print("<input type='submit' id='btn' value='home'>");
		}
		else if((mem[1]!=null)&&mem[1].equals(user)){
			if(DBUtil.checkDPlayer(conn, mem[0])&&DBUtil.checkDPlayer(conn, mem[2]))
				out.print("<input type='submit' id='btn' value='home'>");
		}
		else if((mem[2]!=null)&&mem[2].equals(user)){
			if(DBUtil.checkDPlayer(conn, mem[0])&&DBUtil.checkDPlayer(conn, mem[1]))
				out.print("<input type='submit' id='btn' value='home'>");
		}
	}
	else if(num==4){
		if(!DBUtil.checkDPlayer(conn, user))
			DBUtil.deleteDPlayer(conn, user);
		if((mem[0]!=null)&&mem[0].equals(user)){
			if(DBUtil.checkDPlayer(conn, mem[1])&&DBUtil.checkDPlayer(conn, mem[2])&&DBUtil.checkDPlayer(conn, mem[3])&&(!mem[0].equals(DBUtil.getGrp(conn,mem[1]))&&!mem[0].equals(DBUtil.getGrp(conn, mem[2]))&&!mem[0].equals(DBUtil.getGrp(conn,mem[3]))))
				out.print("<input type='submit' id='btn' value='home'>");
		}
		else if((mem[1]!=null)&&mem[1].equals(user)){
			if(DBUtil.checkDPlayer(conn, mem[0])&&DBUtil.checkDPlayer(conn, mem[2])&&DBUtil.checkDPlayer(conn, mem[3]))
				out.print("<input type='submit' id='btn' value='home'>");
		}
		else if((mem[2]!=null)&&mem[2].equals(user)){
			if(DBUtil.checkDPlayer(conn, mem[0])&&DBUtil.checkDPlayer(conn, mem[1])&&DBUtil.checkDPlayer(conn, mem[3]))
				out.print("<input type='submit' id='btn' value='home'>");
		}
		else if((mem[3]!=null)&&mem[3].equals(user)){
			if(DBUtil.checkDPlayer(conn, mem[0])&&DBUtil.checkDPlayer(conn, mem[1])&&DBUtil.checkDPlayer(conn, mem[2]))
				out.print("<input type='submit' id='btn' value='home'>");
		}
	}
	%>
	<% 
	out.print("<input type='text' value='"+user+"' class='req' name='user' style='display:none'>");
	out.print("<input type='text' value='"+grp+"' name='grp' style='display:none'>");
	out.print("<input type='text' value='"+num+"' name='num' style='display:none'>");
	System.out.println("res:"+user);
	%>
	</form>
	
</body>
</html>