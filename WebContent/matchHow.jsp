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
	left: 0;
	width: 50%;
	display: inline;
	padding: 10px;
	list-style-positon: inside;
	font-size:1.2em;
}

.content2 {
	position: absolute;
	left: 50%;
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

}
img{
width:50%;
}
}
</style>
<title>How to play Match game</title>
</head>
<body>
<div id="head">Match game</div>
	<div class="content1">
	<h3>Game play</h3>
		<p>
		<ol>
		<li>Mix up the cards
		<li>Lay them in rows, face down.
		<li>Turn over any two cards.
		<li>If the two cards match, keep them.
		<li>If they don't match, turn them back over.
		<li>Remember what was on each card and where it was.
		<li>Watch and remember during the other player's turn.
		<li>The game is over when all the cards have been matched.
		<li>The player with the most matches wins.
		</ol>
		</p>
	</div>
	<div class="content2">
	<img src="img3.png">
	</div>
	
</body>
</html>