<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Select Matching Game</title>
</head>
<body>
	<div id="head">Select Hangman Level</div>
	<div id="border"></div>
	<div class="content1">
		<form action="SelHangman" method="post">
		<input type="radio" name="game" value="easy"><span>4letters(15times)</span><br>
		<input type="radio" name="game" value="nomal"><span>5letters(12times)</span><br>
		<input type="radio" name="game" value="hard"><span>6letters(10times)</span><br>
		<%
		String name=(String)request.getAttribute("user");
		String s=(String)session.getAttribute("name");
		System.out.println(s);
		out.print("<input type='text' name='user' value='"+s+"'id='name' readonly><br>");
		%>
		<input type="submit" id="sub" value="play">
		</form>
	</div>
	
</body>
</html>