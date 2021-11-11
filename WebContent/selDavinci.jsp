<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,java.util.HashMap" import="model.DBUtil" import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name='viewport' content="width=device-width,initial-scale=1.0">
<style type="text/css">
#head {
	border-style: solid;
	width: 40%;
	text-align: center;
	margin-left: 30%;
	margin-right: 30%;
	border-width: 2px;
	font-size: 30pt;
	margin-bottom:10pt;
}
.content1 {
	position: relative;
	top: 10%;
	left: 40%;
	width: 100%;
	display: inline;
	padding: 10px;
	list-style-positon: inside;
	font-size:20pt;
}
#border {
	width: 100%;
	background-color: black;
	height: 5px;
}
#sub{
font-size:18pt;
margin-left:5%;
}
#name{
visibility:hidden;
}
@media screen and (max-width:990px){

#head{
font-size:20pt;
}
.content1{
width:100%;
position:static;

}
}
</style>
<title>Select Davinci Code field</title>
<script>

</script>
</head>
<body >
	<div id="head">Select Davinci Code</div>
	<div id="border"></div>
	<div class="content1">
		<form action="SelDavinci" method="post">
		<input type="radio" name="game" value="2players" checked="checked"><span>2 players field</span><br>
		<input type="radio" name="game" value="3players"><span>3 players field</span><br>
		<input type="radio" name="game" value="4players"><span>4 players field</span><br>
		<br><br>
		<% String s=(String)request.getAttribute("name"); out.print("<input type='text' name='user' value='"+s+"' readonly><br>"); %>
		<input type="radio"  name="grp_by" value="my_grp" checked="checked"><span>make group by my id</span><br>
		<input type="radio"  name="grp_by" value="join_grp"><span>join group</span><br>
		<input type="submit" id="sub" value="play">
		</form>
	</div>
	
</body>
</html>