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
#emph{
color:red;
font-weight:bold;
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
img{
display:none;
}
#head{
font-size:20pt;
}
.content1{
width:100%;
position:absolute;
top:10%;
}
.content2{
width:100%;
position:absolute;
left:0;
top:40%;
font-size:1.2em;
}
}
</style>
<title>How to play Davinci Code</title>
</head>
<body>
	<div id="head">Davinci Code</div>
	<div class="content1">
		<ul>
			<li>The game can be played with 2-4players</li>
			<li>Two sets(one black and one white) of numbered tiles from
				0-11</li>
			<li>One game takes about 20 minutes</li>
			<li>Our game is the version which does not include Jokers</li>
			<li><a href="HowDavinci2.jsp">davinci for 2 players</a></li>
			<li><a href="HowDavinci3.jsp">davinci for 3 players</a></li>
			<li><a href="HowDavinci4.jsp">davinci for 4 players</a></li>
		</ul>
		<img src="enjoy.png">
		<img src="img2.png">
		<img src="davinciCode.jpg">
	</div>
	<div class="content2">
		<h3 id="txt">Game play</h3>
		<p>
			1.On your turn, begin by drawing any one of the remaining tiles. Make
			sure only you can see what is on the tile, do not place the tile in
			you own line up yet. 
			<br>
			2. Next, you must try to guess a number
			in an opponent's code. You may choose any opponent you wish. To do
			so, point to a specific tile and say what you think it is. If you are
			correct, the opponent must place its tile so that everyone can see
			its identity. If you are wrong, you have to place the tile you drew
			and then insert it face-up (so all opponents can see it) into your
			code in its correct position. By so doing, you give your opponents
			clues about the identities of your hidden tiles. 
			<br>
			3. As your
			turn continues, if your first guess was correct, you may go again --
			that is, you may continue your turn by attacking "any" other opposing
			tile. Or, you may decide to end your turn. If you end your turn,
			insert the tile you drew at the outset of your turn into your code.
			Do not show it to your opponents. Leave it standing. Your secret code
			is now longer by one tile.<br>
			<span id="emph">Our game is for one player and the cards are placed as the player's perspective.<br>
			Just imagine your playing this game on the table. For example, the norther card <br> 
			in the screen is placed(sorted) according to the perspective of the player at the north.</span>
		</p>
	</div>
</body>
</html>