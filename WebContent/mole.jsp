<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name='viewport'
	content="width=device-width,initial-scale=1.0">
<title>Catch a mole</title>
<style type="text/css">
a:link {
	text-decoration: none;
	color: black;
}

a:visited {
	text-decoration: none;
	color: black;
}

a:hover {
	color: gray;
	text-decoration: underline;
}

body {
	background: #b5b46e;
}

form {
	width: 99vw;
	display: flex;
	flex-wrap: wrap;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

#moleBack {
	display: none;
	background-image: url("moleBack.jpg");
	background-repeat: no-repeat;
	background-position: center;
	width:615px;
	height:800px;
	background-size: 100%;
	background-image: url("moleBack.jpg");
}

#h2 {
	font-size: 1.4em;
	font-weight: bold;
}

#hiddenView {
	display: none;
}

#po {
	font-size: 1.5em;
	font-weight: bold
}

@media screen and (max-width:990px) {
	#po {
		font-size: 1.4em;
		font-weight: bold
	}
	form {
		width: 99vw;
		display: flex;
		flex-wrap: wrap;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	#moleBack {
		display: none;
		background-image: url("moleBack.jpg");
		background-repeat: no-repeat;
		background-position: center;
		background-size: 99%;
		background-image: url("moleBack.jpg");
		width:24em;
		height:28em;
	}
	table {
		
	}
	#b1 {
		width: 100px;
		height: 100px;
	}
	#b2 {
		width: 100px;
		height: 100px;
	}
	#b3 {
		width: 100px;
		height: 100px;
	}
	#b4 {
		width: 100px;
		height: 100px;
	}
	#b5 {
		width: 100px;
		height: 100px;
	}
	#b6 {
		width: 100px;
		height: 100px;
	}
	#b7 {
		width: 100px;
		height: 100px;
	}
	#b8 {
		width: 100px;
		height: 100px;
	}
	#b9 {
		width: 100px;
		height: 100px;
	}
}
</style>
<script>
	var times = 20000;
	var cnt = 0;
	var beforeM = parseInt(Math.random() * 9 + 1);
	var stop;
	function start() {
		document.getElementById("play").style.display = "none";
		document.getElementById("moleBack").style.display = "flex";
		rr();
	}
	function rr() {
		stop = setInterval(randNum, 500);
	}
	function randNum() {
		times -= 500;
		var n = parseInt(Math.random() * 9 + 1);
		var mo = document.getElementById("n" + n);
		var bMo = document.getElementById("n" + beforeM);
		if (times <= 0) {
			stopInter();
		} else {
			bMo.innerHTML = "<input id='b"
					+ beforeM
					+ "' type='image' src='noMole.png' onclick='getPoint();return false;' disabled='disabled'>";
			mo.innerHTML = "<input id='b"
					+ n
					+ "' type='image' src='imgMole.png' onclick='getPoint();return false;'>";
			beforeM = n;
		}
	}
	function stopInter() {
		for (var i = 1; i < 10; i++) {
			var k = document.getElementById("n" + i);
			k.innerHTML = "<input id='b"
					+ i
					+ "' type='image' src='imgMole.png' onclick='getPoint();return false;' disabled='disabled'>";
		}
		var p = document.getElementById("po");
		p.innerHTML = "TotalPoint : " + cnt + " / GAME OVER X,X";
		var rs = document.getElementById("result");
		rs.value = cnt;
		clearInterval(stop);

	}
	function getPoint() {
		var bMo = document.getElementById("n" + beforeM);
		bMo.innerHTML = "<input id='b"
				+ beforeM
				+ "' type='image' src='noMole.png' onclick='getPoint();return false;' disabled='disabled'>";
		cnt = cnt + 10;
		var a = document.getElementById("po");
		a.innerHTML = "Point : " + cnt;

	}
</script>
</head>
<body>
	<form action="EndMole">
		<div id="po">Catch a mole</div>
		<%
			String name = (String) request.getAttribute("name");
			System.out.println("mole:" + name);
			out.print("<input type='text' name='uname' value='" + name + "' id='name' readonly>");
		%>
		<div id="h2">
			<a id='play' href='#' onClick='start()'>Play</a> <input type="submit"
				name="sub" value="End Game">
		</div>
		<div id="moleBack">
			<table align="center">
				<tr>
					<td id="n1"><input id='b1' type='image' src='noMole.png'
						onclick='getPoint();return false;' disabled="disabled"></td>
					<td id="n2"><input id='b2' type="image" src="noMole.png"
						onclick="getPoint();return false;" disabled="disabled"></td>
					<td id="n3"><input id='b3' type="image" src="noMole.png"
						onclick="getPoint();return false;" disabled="disabled"></td>
				</tr>
				<tr>
					<td id="n4"><input id='b4' type="image" src="noMole.png"
						onclick="getPoint();return false;" disabled="disabled"></td>
					<td id="n5"><input id='b5' type="image" src="noMole.png"
						onclick="getPoint();return false;" disabled="disabled"></td>
					<td id="n6"><input id='b6' type="image" src="noMole.png"
						onclick="getPoint();return false;" disabled="disabled"></td>
				</tr>
				<tr>
					<td id="n7"><input id='b7' type="image" src="noMole.png"
						onclick="getPoint();return false;" disabled="disabled"></td>
					<td id="n8"><input id='b8' type="image" src="noMole.png"
						onclick="getPoint();return false;" disabled="disabled"></td>
					<td id="n9"><input id='b9' type="image" src="noMole.png"
						onclick="getPoint();return false;" disabled="disabled"></td>
				</tr>
			</table>
		</div>
		<div id="hiddenView">
			<input type="text" name="result" id="result" value="0">
		</div>
	</form>

</body>
</html>