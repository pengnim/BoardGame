<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.DBUtil,java.util.ArrayList,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"  name='viewport' content="width=device-width,initial-scale=1.0">
<style type="text/css">
#head {
	border-style: solid;
	width: 40%;
	position: absolute;
	text-align: center;
	left: 30%;
	right: 30%;
	border-width: 2px;
	font-size: 30pt;
}
.content1 {
	position: absolute;
	top: 10%;
	left: 40%;
	width: 50%;
	display: inline;
	padding: 10px;
	list-style-positon: inside;
	font-size:18pt;
}
#btn{
font-size:18pt;
margin-left:5%;
}
.content2 {
	position: absolute;
	left: 60%;
	top: 10%;
	width: 50%;
	display: inline;
	font-size:1.1em;
}
@media screen and (max-width:990px){

#head{
font-size:20pt;
position:static;
margin-left:30%;
}
.content1{
width:100%;
position:static;

}
.content2{
width:100%;
position:static;
margin-left:10%;
}
img{
width:50%;
}
}
</style>
<script type="text/javascript">
function pagestart() {
	window.setTimeout("pagereload()", 5000);
	}
	function pagereload() {
	location.reload();
	}
</script>
<title>Join group</title>
</head>
<body onLoad="pagestart()">
<div id="head">Join group</div>
	<div class="content1">
		<%
		String game=(String)request.getAttribute("game");
		String name=(String)request.getAttribute("user");
		out.print("<form method='post' action='JoinGrp'>");
		Connection con=(Connection)request.getAttribute("connect");
		Statement s=con.createStatement();
		int n;
		out.print("my id:<input type='text' value='"+name+"' name='user' readonly><br>");
		out.print("group personnel:<input type='text' value='"+game+"'name='game' readonly><br>");
		if(game.equals("2players")) n=2;
		else if(game.equals("3players")) n=3;
		else n=4;
		ArrayList<String> list=new ArrayList<String>();
		out.print("<h3> "+ n+" player group </h3>");
		ResultSet rs=s.executeQuery("select * from group"+n+";");
		while(rs.next()){
			for(int i=2;i<=n+1;i++){
				if(!rs.getString(i).equals("none"))
					list.add(rs.getString(i));
			}
			if(list.size()<n){
				out.print("<input type='radio' name='group' value='"+rs.getString(1)+"' >"+rs.getString(1)+"<br>");
				out.print("<ul>");
				for(String a:list){
					out.print("<li>"+a+"</li>");
				}
				out.print("</ul>");
			}
			list.clear();
		}
		out.print("<input type='submit' value='join' id='btn'>");
		out.print("</form>");
		%>
	</div>

	</div>
</body>
</html>