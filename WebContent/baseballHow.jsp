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
	font-size:1.3em;
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
<title>How to play Baseball game</title>
</head>
<body>
<div id="head">Baseball game</div>
	<div class="content1">
	<h3>Game play</h3>
		<p>
		<ol>
		<li>The number you need to guess is a 4 digits number.
		<li>each numbers are consist of 0~9 without duplication.
		<li>input 4 digits number
		<li>"strike" is the number of digits you got it correct; both the digit and number.
		<li>"ball" is the number of digits which are in the answer but not in that place.
		<li>"out" means you got nothing correct.
		</ol>
		</p>
	</div>
	<div class="content2">
	<img src="baseball.jpg">
	</div>
	
</body>
</html>