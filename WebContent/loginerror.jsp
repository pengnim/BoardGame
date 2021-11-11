<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name='viewport' content="width=device-width,initial-scale=1.0">
<style type="text/css">
body {
	background-image:url("logic.jpg");
	background-position: top center;
	background-repeat: no-repeat;
	background-size:contain;
	text-align:center;
}
.btn{font-size:16pt; margin:10px;}

#header, #text{
	padding:10px;
	color:white;
	text-shadow: 0 0 5px #000000;	
}
#header{
font-size:40pt;
}
#text, #error{
font-size:20pt;
}
#error{
	color:white;
	text-shadow: 0 0 5px #000000;
	font-size:30pt;
	font-weight:bold;
}
#footer{
background-color:#bdbdbd;
 position:absolute;
 bottom:0;
 left:0;
 color:white;
 width:auto;
 width:100%;
 margin:0px; 
}
@media screen and (max-width: 990px) {
  body {
  padding-top:50px;
    background-size:80%;
  }

 #footer{
 position:relative;
 width:auto;
 height:auto;
  font-size:1.2em;
  bottom:0;
  right:0;
  left:0;
 width:inherit;
padding-top:10px;
padding-bottom:10px;
margin-top:10%;
 }
#text,#error{
font-size:2.0em;
}
#header{
font-size:3.0em;
}
 .btn{
 	padding-top:10px;
 	padding-bottom:10px;
 	font-size:1.5em;
 	border:none;
 	width:100%;
 	height:auto;
 	}
}
</style>
<title>Log in Error</title>
</head>
<body>
	<div id="header" align="center">Log in Error</div>
	<%
	int status=(int)request.getAttribute("status");
	if(status==0){
		out.print("<p id='error' align='cneter'>"+"Wrong Password!!!"+"</p><br>");
	}
	else if(status==-1){
		out.print("<p id='error' align='cneter'>"+"Invalid user name!!!"+"</p><br>");
	}
	else if(status==-2){
		out.print("<p id='error' align='cneter'>"+"User is already online!!!"+"</p><br>");
	}
	%>
		<p>
		<div id="text"> Error occurred while logging in</div>
		</p>
		<input class="btn" type="submit" value="Return" onclick="location.href='index.html'" >
	<div id="footer" >
	<p>developed by: Yeji An & Seowoo Lee& Sohee Hyoun </p>
	<p>e-mail: ann4913@naver.com & twsara@naver.com & hyounso2@naver.com</p>
	</div>
</body>
</html>