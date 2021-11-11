<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name='viewport'
	content="width=device-width,initial-scale=1.0">
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

#inner {
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

#endHidden {
	font-size: 2.5em;
	font-weight: bold;
}

#headBase {
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
	font-size: 1em;
}

#hiddenView {
	display: none;
}

@media screen and (max-width:990px) {

#headBase {
	font-size: 2.5em;
	font-weight: bold;
}
#h2 {
	font-size: 1.5em;
	font-weight: bold;
}


}


</style>
<title>Base Ball</title>
<script>
	var cnt = 10;
	var chkInputNum = "0123456789";
	function start() {
		document.getElementById("play").style.display = "none";
		document.getElementById("main").style.display = "block";
		document.getElementById("inner").style.display = "none";
	}
	function enterTime() {
		var chk = true;
		var t = String(document.getElementById("inTimes").value);
		var lt = t.split("");
		if (t != "") {
			for (var i = 0; i < lt.length; i++) {
				if (!chkInputNum.includes(lt[i])) {
					chk = false;
					break;
				}
			}
			if (chk) {
				if (parseInt(t) < 1 || parseInt(t) > 15)
					chk = false;
			}
			if (chk) {

				cnt = parseInt(t);
				var ans = document.getElementById("ans");
				ans.innerHTML = "opportunity : " + cnt;
				document.getElementById("inner").style.display = "flex";
				document.getElementById("times").style.display = "none";
			} else
				document.getElementById("inTimes").value = "";
		} else {
			var ans = document.getElementById("ans");
			ans.innerHTML = "opportunity : " + cnt;
			document.getElementById("inner").style.display = "flex";
			document.getElementById("times").style.display = "none";
		}
	}
	function chkBase() {
		var baseNum = String(document.getElementById("rn").value);
		var inputNumV = String(document.getElementById("in").value);
		var inner = document.getElementById('inner');
		var listN = inputNumV.split("");
		var listB = baseNum.split("");
		var inputNum = document.getElementById("in");
		var eHidden = document.getElementById("endHidden");
		var chkOther = true;
		var strike = 0;
		var outt = 0;
		var ball = 0;
		var chkDuple = [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ];
		var xAns = document.getElementById("xans");
		var ans = document.getElementById("ans");
		var dNv = String(document.getElementById("dupleNum").value);
		var dN = document.getElementById("dupleNum");
		var rs = document.getElementById("rs");
		if (listN.length != 4 || dNv.includes(inputNumV))
			chkOther = false;
		for (var i = 0; i < 4; i++) {
			if (!chkInputNum.includes(listN[i])) {
				chkOther = false;
				break;
			}
		}
		if (chkOther) {
			for (var i = 0; i < 4; i++) {
				var tmp = parseInt(listN[i]);
				chkDuple[tmp]++;
				if (chkDuple[tmp] > 1) {
					chkOther = false;
					break;
				}
			}
		}

		if (chkOther && cnt > 0) {
			if (baseNum == inputNumV) {
				ans.innerHTML = baseNum;
				eHidden.innerHTML = "YOU WIN !!!";
				rs.value = "v";
			} else {
				for (var i = 0; i < 4; i++) {
					if (!baseNum.includes(listN[i])) {
						outt++;
					}
				}
				for (var i = 0; i < 4; i++) {
					for (var j = 0; j < 4; j++) {
						if (i == j && listB[i] == listN[j]) {
							strike++;
						} else if (i != j && listB[i] == listN[j]) {
							ball++;
						}
					}
				}
				cnt--;
				ans.innerHTML = "opportunity : " + cnt;
				xAns.innerHTML += "(" + cnt + ") " + inputNumV + " : " + strike
						+ "S " + ball + "B " + outt + "O<br>";
				dN.value += inputNumV + " ";
				if (cnt < 1) {
					ans.innerHTML = baseNum;
					eHidden.innerHTML = "YOU LOST zz";
					rs.value = "d";
				}

			}
		}
		inputNum.value = "";

	}
</script>
</head>
<body>
	<form action="EndBase">
		<div id="headBase">BASE BALL</div>
		<%
			String rn = (String) request.getAttribute("rn");
			String name = (String) request.getAttribute("name");
			System.out.println("base:" + name);
			out.print("<input type='text' name='uname' value='" + name + "' id='name' readonly>");
		%>
		<div id="h2">
			<a id='play' href='#' onClick='start()'>Play</a> <input type="submit"
				name="sub" value="End Game">
		</div>
		<div id="main">
			<div id="times">
				<br>1~15times in here //default 10times<br> <input
					type="text" id="inTimes" maxlength="2" placeholder="1~30times"
					value=""> <input type="button" value="enter"
					onClick="enterTime()">
			</div>
			<div id="inner">
				<div id="ans"></div>
				<div id="xans"></div>
				<div id="endHidden">
					<input id="in" type="text" placeholder="non-redundant 4number(0~9)"
						maxlength="4"> <input id="btn" type="button"
						value="enter" onClick="chkBase()">
				</div>
			</div>
		</div>

		<div id="hiddenView">
			<input type="text" id="dupleNum" value="">
			<input type="text" id="rs" name="result" value="d">
			<%
				out.print("<input type='text' name='rn' value='" + rn + "' id='rn' readonly>");
			%>
		</div>
	</form>

</body>
</html>