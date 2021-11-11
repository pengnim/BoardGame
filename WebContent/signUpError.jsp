<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css" name='viewport' content="width=device-width,initial-scale=1.0">
body {
	background-color:white;
height:100%;
}

#inputForm {
	text-align: center;
	margin-top:50px;
	color:white;
	width:inherit;
	height:500px;
	text-shadow:0 0 5px #000000;
	background-image:url('memory.png');
	background-repeat:no-repeat;
	background-position:top;
	background-size:auto;
}

#error {
	color: black;
	font-size: 30pt;
}

#line {
	width: inherit;
	background-color: black;
	height: 5px;
}

#head {
	font-size: 36pt;
	font-weight: bold;
}

.btn {
	font-size: 20pt;
	width: 20%;
	margin: 20px;
}
@media screen and (max-width: 990px) {
body {
  padding-top:50px;
   
  }
 #inputForm {
  padding-top:50px;
  }
  input{
  font-size:40pt;
  border:solid black 2px;
  }
 #footer{
 position:relative;
 width:auto;
 height:auto;
  font-size:30pt;
  bottom:0;
  right:0;
  left:0;
 width:inherit;
padding-top:10px;
padding-bottom:10px;
margin-top:10%;
 }
 #id, #pw,#pwck{
	font-size:46pt;
	font-weight:bold;
}
#head{
font-size:54pt;
}
 .btn{
 	padding-top:10px;
 	padding-bottom:10px;
 	font-size:30pt;
 	border:none;
 	width:50%;
 	height:auto;
 	}
}
</style>
<title>Sign Up</title>
<script type="text/javascript">
	status = 1
</script>
</head>
<body>
	<header>
		<p align="center" id="head">Fill out the form to Sign up</p>
		<%
			int status = (int) request.getAttribute("status");
			if (status == -1) {
				String id = (String) request.getAttribute("uid");
				out.print("<dir align='center' id='error'>" + id + " already exists!!!" + "</dir>");
			} else if (status == 0) {
				out.print("<dir align='center' id='error'>" + "Password Mismatches!!!!!" + "</dir>");
			} else if (status == -2) {
				out.print("<dir align='center' id='error'>" + "Invalid input!!!!!" + "</dir>");
			}
		%>
	</header>

	<div id="inputForm">
		<input align="center" class="btn" type="button" value="return"
			onclick="location.href='signUp.html'">
	</div>

</body>
</html>