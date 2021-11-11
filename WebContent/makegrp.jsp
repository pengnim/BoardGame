<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.DBUtil,java.sql.*,java.util.ArrayList"%>
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
	font-size:20pt;
}

.content2 {
	position: absolute;
	left: 60%;
	top: 10%;
	width: 50%;
	display: inline;
	font-size:1.1em;
}
#btn{
font-size:18pt;
margin-left:5%;
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
	window.setTimeout("pagereload()", 2000);
	}
	function pagereload() {
	location.reload();
	}
</script>
<title>make group by my ID</title>
</head>
<body onLoad="pagestart()">
<div id="head">Make group by my iD</div>
	<div class="content1">
		<%
		String game=(String)request.getAttribute("game");
		String name=(String)request.getAttribute("user");
		int n=2;
		if(game.equals("2players")){
			n=2;
		}
		else if(game.equals("3players")){
			n=3;
		}
		else{
			n=4;
		}
		out.print("<h3> "+n+" players group</h3><br>");
		out.print("<form method='post' action='StartGame'>");
		Connection con=(Connection)request.getAttribute("connect");
		String g=DBUtil.getGrp(con, name);
		ArrayList<String> mem=new ArrayList<String>();
		out.println("<span style='color:red;font-size:14pt;'>Your leader must start first or else your button will do nothing.</span>");
		if(!g.equals("none")){
			ResultSet rs=DBUtil.getGrpMem(con, g,n);
			while(rs.next()){
				mem.add(rs.getString("grp_name"));
				for(int i=3;i<=n+1;i++){
					if(!rs.getString(i).equals("none"))
						mem.add(rs.getString(i));
				}
			}
		}

		out.print("<ul>");
		for(String s:mem){
			out.print("<li>"+s+"</li>");
		}
		out.print("</ul>");
		out.print("<input type='text' value='"+g+"' name='grp_name' readonly><br>");
		out.print("<input type='text' value='"+name+"' name='user' readonly><br>");
		out.print("<input type='text' value='"+game+"' name='game' readonly><br>");
		if((game.equals("2players")&&mem.size()==2)||(game.equals("3players")&&mem.size()==3)||(game.equals("4players")&&mem.size()==4))
			out.print("<input type='submit' value='start' id='btn'>");
		out.print("</form>");
		%>
	</div>

</body>
</html>