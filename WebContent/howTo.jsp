<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name='viewport' content="width=device-width,initial-scale=1.0">
<style type="text/css">
body {
	background-image: url("img1.png");
	background-repeat: no-repeat;
	background-position: 100% 50%;
	font-family:"Times new Roman","Times new Roman","serif"
}

#border {
	width: inherit;
	background-color: black;
	height: 5px;
}

#head {
	font-size: 40pt;
	font-weight: bold;
	text-align: center;
}

#score {
	position: absolute;
	bottom: 15%;
	left: 0px;
	right: 0px; width : auto;
	text-align: center;
	display: inline;
	width: auto;
	bottom:10%;
}

.block {
	display: inline-block;
	text-align: center;
	width: 30%;
}

.h2 {
	font-size: 18pt;
	font-family:"papyrus","papyrus","fantasy";
	font-weight:normal;
}

.h3 {
	font-size: 16pt;
	font-family:"oldtown","oldtown","oldtown";
	font-weight:normal;
}

ul li {
	font-size: 25pt;
	padding: 20pt;
	padding-left:25%;
	list-style-position: inside;
}
ul li a{
	color:#5e5e5e;
}
@media screen and (max-width:990px){
body{
background-position:50% 0%;
background-size:100%;
background-image:none;
}
	#head{
	font-size:30pt;
	}
	ul{
	text-align:left;
	}
	ul li{
	padding-left:0;
	padding:10px;
	}
	ul li a{
	font-size:20pt;
	list-style-position:inside;
	background-color:white;
}
}
</style>
<title>How to Play</title>
</head>
<body>
	<header id="head"> How To Play</header>
	<ul>
		<li><a href="davinciHow.jsp">davinci code</a>
		<li><a href="matchHow.jsp">matching game</a>
		<li><a href="hangmanHow.jsp">hangman game</a>
		<li><a href="moleHow.jsp">mole game</a>
		<li><a href="baseballHow.jsp">baseball game</a>
	</ul>
	<div id="border"></div>
	<img src="ban2.jpg">
</body>
</html>