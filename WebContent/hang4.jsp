<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width,initial-scale=1.0">
<title>Insert title here</title>
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

form {
	width: 99vw;
	display: flex;
	flex-wrap: wrap;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

#main {
	display: none;
}

#innerMain {
	width: 99vw;
	display: flex;
	flex-wrap: wrap;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

#dd {
	display: none;
}

#h1 {
	font-size: 2.5em;
	font-weight: bold;
}

#h2 {
	font-size: 1.5em;
	font-weight: bold;
}

#ans {
	font-size: 2.2em;
	font-weight: bold;
}

#xans {
	font-size: 2em;
}

@media screen and (max-width:990px) {
	#h1 {
		font-size: 1.9em;
		font-weight: bold;
	}
	#h2 {
		font-size: 1.7em;
		font-weight: bold;
	}
	#ans {
		font-size: 1.7em;
		font-weight: bold;
	}
	#xans {
		font-size: 1.4em;
	}
	body {
		width: 100%;
		display: flex;
		flex-wrap: wrap;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	form {
		width: 99vw;
		display: flex;
		flex-wrap: wrap;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	#innerMain {
		width: 99vw;
		display: flex;
		flex-wrap: wrap;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	#hangim {
		height: 20em;
		width: 20em;
	}
}
</style>
<script>
	var cnt = 1;
	function start() {
		document.getElementById("play").style.display = "none";
		document.getElementById("main").style.display = "block";

	}
	function check() {
		var lowerP = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		var bText = String(document.getElementById("binggo").value);
		var bEle = document.getElementById("binggo");
		var printAns = document.getElementById("ans");
		var divB = bText.split(" ");
		var w = String(document.getElementById("words").value);
		var divA = w.split("");
		var u = document.getElementById("pp");
		var chk = String(document.getElementById("in").value);
		var inClear = document.getElementById("in");
		var xAns = document.getElementById("xans");
		var res = document.getElementById("result");
		var chkInput = String(document.getElementById("chkInput").value);

		var c = chk.toLowerCase();
		if (!lowerP.includes(chk) || chkInput.includes(chk))
			inClear.value = '';
		else {
			if (w.includes(c)) {
				for (var i = 0; i < 4; i++) {
					if (divA[i] == c) {
						divB[i] = divA[i];
					}
				}
				bEle.value = "";
				for (var i = 0; i < 4; i++) {
					bEle.value += divB[i] + " ";
				}
				printAns.innerHTML = bEle.value;

			} else {
				if (cnt == 15) {
					res.value = "d";
					document.getElementById("endHidden").innerHTML = "<h1>You Die</h1>";
				}
				cnt++;
				u.innerHTML = "<img id='hangim' src='h"+cnt+".png'>";
				xAns.innerHTML += c + " ";
				document.getElementById("chkInput").value += c;

			}
			var fin = String(document.getElementById("binggo").value);
			if (!fin.includes("_")) {
				res.value = "v";
				document.getElementById("endHidden").innerHTML = "<h1>You Win</h1>";
			}
			inClear.value = "";
		}

	}
</script>
</head>
<body>
	<form action="EndHang">
		<div id="h1">HANG MAN 4LETTERS</div>
		<%
			String words = (String) request.getAttribute("words");
			String mean = (String) request.getAttribute("mean");
			String name = (String) request.getAttribute("username");
			System.out.println("hang:"+name);
			out.print("<input type='text' name='uname' value='" + name + "' id='name' readonly>");
		%>
		<div id="h2">
			<a id='play' href='#' onClick='start()'>Play</a> <input type="submit"
				name="sub" value="End Game">
		</div>
		<div id="main">
			<div id="innerMain">
				<div id="ans">_ _ _ _</div>
				<div id="xans"></div>
				<div id="endHidden">
					<input id="in" type="text" placeholder="insert letters"
						maxlength="1"> <input id="btn" type="button" value="enter"
						onClick="check()">
				</div>
				<div id="pp">
					<img id="hangim" src='h1.png'>
				</div>
			</div>

		</div>

		<div id="dd">
			<input type="text" id="binggo" value="_ _ _ _"> <input
				type="text" id="chkInput" value="">
			<%
				out.print("<input type='text' name='words' value='" + words + "' id='words' readonly>");
				out.print("<input type='text' name='mean' value='" + mean + "' id='mean' readonly>");
			%>
			<input type="text" id="result" name="result" value="d">
		</div>
	</form>

</body>
</html>