<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	left: 0;
	width: 50%;
	display: inline;
	padding: 10px;
	list-style-positon: inside;
	font-size:1.2em;
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
<title>Insert title here</title>
</head>
<body>
<div id="head">Match game</div>
	<div class="content1">
		<p>
		One player thinks of a word or phrase; the others try to guess what it is one letter at a time.<br>
		The player draws a number of dashes equivalent to the number of letters in the word.<br>
		If a guessing player suggests a letter that occurs in the word, the other player fills in the blanks with that letter in the right places.<br>
		If the word does not contain the suggested letter, the other player draws one element of a hangman’s gallows.<br> 
		As the game progresses, a segment of the gallows and of a victim is added for every suggested letter not in the word. <br>
		The number of incorrect guesses before the game ends is up to the players, but completing a character in a noose provides a minimum of six wrong answers until the game ends.<br>
		The first player to guess the correct answer thinks of the word for the next game.
		</p>
	</div>
	<div class="content2">
	<img src="img6.png">
	</div>
</body>
</html>