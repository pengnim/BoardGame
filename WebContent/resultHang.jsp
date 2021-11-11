<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name='viewport'
	content="width=device-width,initial-scale=1.0">
<style type="text/css">
#head {
	border-style: solid;
	width: 40%;
	text-align: center;
	border-width: 2px;
	font-size: 30pt;
	font-weight: bold;
	margin-left: 30%;
	margin-bottom: 10pt;
}

#border {
	width: 100%;
	background-color: black;
	height: 5px;
}

#score {
	border-style: solid;
	width: 40%;
	text-align: center;
	border-width: 1px;
	font-size: 20pt;
	margin-left: 30%;
	margin-bottom: 10pt;
}

#name {
	text-align: center;
	font-size: 20pt;
	margin-top: 10pt;
}

#btn {
	margin-left: 45%;
	font-size: 20pt;
}

#result {
	font-weight: bold;
	font-size: 30pt;
	text-align: center;
}

.content2 {
	margin-top: 15%;
	width: 50%;
	display: inline;
	font-size: 1.1em;
}

.req {
	visibility: hidden;
}

@media screen and (max-width:990px) {
	#score {
		border-style: solid;
		width: 80%;
		text-align: center;
		border-width: 1px;
		font-size: 14pt;
		margin-left: 10%;
		margin-bottom: 10pt;
		margin-top: 10px;
	}
	#btn {
		margin-left: 40%;
		font-size: 20pt;
	}
	#head {
		font-size: 20pt;
	}
	#result {
		font-weight: bold;
		font-size: 20pt;
		text-align: center;
	}
	.content2 {
		width: 100%;
		left: 0;
		top: 40%;
		font-size: 12pt;
	}
}
</style>
<title>HangMan Result</title>
</head>
<body>
	<div id="head">HangMan Result</div>
	<div id="border"></div>
	<div class="content2">
		<div id="name">
			<%
				String name = (String) request.getAttribute("user");
				out.print("<p>" + name + "</p>");
				String words = (String) request.getAttribute("words");
				String mean = (String) request.getAttribute("mean");
			%>
		</div>
		<div id="result">
			<%
				int re = (Integer) request.getAttribute("result");
				if (re == 1)
					out.print("Victory!!!!");
				else if (re == 0)
					out.print("Defeated");
			%>
		</div>
	</div>
	<div id="score">
		<%
			out.print("<p>words: " + words + "</p><p>mean: " + mean + "</p>");
			int v = (Integer) request.getAttribute("vic");
			int d = (Integer) request.getAttribute("def");
			float rate = (Float) request.getAttribute("vicRate");
			out.print("<p>Victory: " + v + "</p><p>Defeat: " + d + "</p><p>Victory Rate: " + rate + "</p><br>");
		%>
	</div>
	<form method="post" action="GoToHome">
		<input type="submit" id="btn" value="home">
		<%
			out.print("<input type='text' value='" + name + "' class='req' name='user'>");
		%>
	</form>

</body>
</html>