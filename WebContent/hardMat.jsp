<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
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

#t {
	display: none;
}

#myCanvas {
	margin-left: 28%;
}

@media screen and (max-width:980px) {
	#myCanvas {
	margin-left: -11%;
	}
	form {
		width: 100vw;
		display: flex;
		flex-wrap: wrap;
		flex-direction: coloumn;
		align-items: center;
		justify-content: center;
	}
}
</style>
<title>Matching Game</title>
<script src="baseGa.js"></script>
<script type="text/javascript">
	var canvas;
	var stage;
	var squareSide;
	var squareOutline;
	var max_rgb_color_value = 255;
	var gray = Graphics.getRGB(20, 20, 20);
	var placementArray = [];
	var tileClicked;
	var timeAllowable;
	var totalMatchesPossible;
	var matchesFound;
	var txt;
	var matchesFoundText;
	var squares;
	var rows = 6;
	var columns = 6;
	var numcnt = 0;
	var numlist = [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
			17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 ];
	function devChk() {
		document.getElementById("play").style.display = "none";
		var devW = window.innerWidth;
		if (devW <= 420) {
			document.getElementById("createCvs").innerHTML = "<Canvas id='myCanvas' width='375' height='375'></Canvas>";
			initM();
		} else {
			document.getElementById("createCvs").innerHTML = "<Canvas id='myCanvas' width='1000' height='700'></Canvas>";
			init();
		}
	}
	function initM() {
		squareSide = 40;
		squareOutline = 3;
		var squarePadding = 10;
		canvas = document.getElementById('myCanvas');
		stage = new Stage(canvas);
		stage.x = 70;
		stage.y = 70;
		var numberOfTiles = rows * columns;
		matchesFound = 0;
		timeAllowable = 180;
		txt = new Text("TIME:" + timeAllowable, "15px Monospace", "#000");
		txt.textBaseline = "top"; // draw text relative to the top of the em box.
		txt.x = 0;
		txt.y = -50;
		stage.addChild(txt);
		squares = [];
		totalMatchesPossible = numberOfTiles / 2;
		Ticker.init();
		Ticker.addListener(window);
		Ticker.setPaused(false);
		matchesFoundText = new Text("Found: " + matchesFound + "/"
				+ totalMatchesPossible, "15px Monospace", "#000");
		matchesFoundText.textBaseline = "top"; // draw text relative to the top of the em box.
		matchesFoundText.x = 120;
		matchesFoundText.y = -50;
		stage.addChild(matchesFoundText);
		setPlacementArray(numberOfTiles);
		for (var i = 0; i < numberOfTiles; i++) {
			var placement = getRandomPlacement(placementArray);
			if (i % 2 === 0) {
				var color = randomColor();
			}
			var square = drawSquare(gray);
			square.color = color;
			square.x = (squareSide + squarePadding) * (placement % rows);
			square.y = (squareSide + squarePadding)
					* Math.floor(placement / rows);
			squares.push(square);
			stage.addChild(square);
			square.cache(0, 0, squareSide + squarePadding, squareSide
					+ squarePadding);
			square.onPress = handleOnPress;
			stage.update();
		}

	}
	function init() {
		squareSide = 70;
		squareOutline = 5;
		var squarePadding = 10;
		canvas = document.getElementById('myCanvas');
		stage = new Stage(canvas);
		var numberOfTiles = rows * columns;
		matchesFound = 0;
		timeAllowable = 200;
		txt = new Text("TIME:" + timeAllowable, "30px Monospace", "#000");
		txt.textBaseline = "top"; // draw text relative to the top of the em box.
		txt.x = 700;
		txt.y = 0;
		stage.addChild(txt);
		squares = [];
		totalMatchesPossible = numberOfTiles / 2;
		Ticker.init();
		Ticker.addListener(window);
		Ticker.setPaused(false);
		matchesFoundText = new Text("Found: " + matchesFound + "/"
				+ totalMatchesPossible, "30px Monospace", "#000");
		matchesFoundText.textBaseline = "top"; // draw text relative to the top of the em box.
		matchesFoundText.x = 700;
		matchesFoundText.y = 40;
		stage.addChild(matchesFoundText);
		setPlacementArray(numberOfTiles);
		for (var i = 0; i < numberOfTiles; i++) {
			var placement = getRandomPlacement(placementArray);
			if (i % 2 === 0) {
				var color = randomColor();
			}
			var square = drawSquare(gray);
			square.color = color;
			square.x = (squareSide + squarePadding) * (placement % columns);
			square.y = (squareSide + squarePadding)
					* Math.floor(placement / columns);
			squares.push(square);
			stage.addChild(square);
			square.cache(0, 0, squareSide + squarePadding, squareSide
					+ squarePadding);
			square.onPress = handleOnPress;
			stage.update();
		}
	}

	function drawSquare(color) {
		var shape = new Shape();
		var graphics = shape.graphics;
		graphics.setStrokeStyle(squareOutline);
		graphics.beginStroke(gray);
		graphics.beginFill(color);
		graphics.rect(squareOutline, squareOutline, squareSide, squareSide);
		return shape;
	}

	function randomColor() {
		var tf = false;
		var numm;
		if (numcnt != 0) {
			do {
				numm = Math.floor(Math.random() * 18);
				for (var i = 0; i < 18; i++) {
					if (numlist[i] == numm) {
						numlist[i] = -1;
						numcnt++;
						tf = true;
						break;
					}
				}

			} while (tf == false);

		} else {
			numm = Math.floor(Math.random() * 18);
			for (var i = 0; i < 18; i++) {
				if (numlist[i] == numm) {
					numlist[i] = -1;
					numcnt++;
					break;
				}
			}

		}
		var pal = [ [255,0,0],[0,0,255],[0,255,0],[255,187,0],
			[171,242,0],[29,219,22],[0,216,255],[0,84,255],
			[95,0,255],[255,0,221],[255,255,255],[255,167,167],
			[178,204,255],[209,178,255],[140,140,140],[152,0,0],
			[107,153,0],[5,0,153]];

		return Graphics.getRGB(pal[numm][0], pal[numm][1], pal[numm][2]);
	}
	function setPlacementArray(numberOfTiles) {
		for (var i = 0; i < numberOfTiles; i++) {
			placementArray.push(i);
		}
	}
	function getRandomPlacement(placementArray) {
		randomNumber = Math.floor(Math.random() * placementArray.length);
		return placementArray.splice(randomNumber, 1)[0];
	}
	function handleOnPress(event) {
		var tile = event.target;
		tile.graphics.beginFill(tile.color).rect(squareOutline, squareOutline,
				squareSide, squareSide);
		if (!!tileClicked === false || tileClicked === tile) {
			tileClicked = tile;
			tileClicked.updateCache("source-overlay");
		} else {
			if (tileClicked.color === tile.color && tileClicked !== tile) {
				tileClicked.visible = false;
				tile.visible = false;
				matchesFound++;
				matchesFoundText.text = "Found: " + matchesFound + "/"
						+ totalMatchesPossible;
				if (matchesFound === totalMatchesPossible) {
					gameOver(true);
				}
			} else {
				tileClicked.graphics.beginFill(gray).rect(squareOutline,
						squareOutline, squareSide, squareSide);
			}
			tileClicked.updateCache("source-overlay");
			tile.updateCache("source-overlay");
			tileClicked = tile;
		}
		stage.update();
	}
	function tick() {
		secondsLeft = Math.floor((timeAllowable - Ticker.getTime() / 1000));
		txt.text = "TIME:" + secondsLeft;
		if (secondsLeft <= 0) {
			gameOver(false);
		}
		stage.update();
	}
	function gameOver(win) {
		Ticker.setPaused(true);
		for (var i = 0; i < squares.length; i++) {
			squares[i].graphics.beginFill(squares[i].color).rect(squareOutline,
					squareOutline, squareSide, squareSide);
			squares[i].onPress = null;
			if (win === false) {
				squares[i].uncache();
			}

		}
		var total = 0;
		var r = document.getElementById("res");
		var p = document.getElementById("point");
		if (win == true) {
			total = secondsLeft * 15 + 16 * 20;
			r.value = "v";
			p.value = total;
			matchesFoundText.text = "You win!";
		} else {
			r.value = "d";
			total = matchesFound * 20;
			p.value = total;
			matchesFoundText.text = "GAME OVER X,X";
		}
	}
</script>
</head>
<body>
	<form action="EndMatching">
		<h1>HARD MATCHING</h1>
		<%
			String name = (String) request.getAttribute("username");
			out.print("<input type='text' name='uname' value='" + name + "' id='name' readonly>");
		%>
		<h2>
			<a id='play' href='#' onClick='devChk()'>Play</a> <input
				type="submit" name="sub" value="End Game">
		</h2>
		<div id="t">
			<input type="text" name="res" id="res" value="d" /> <input
				type="text" name="point" id="point" value="0" />
		</div>
	</form>
	<div id="createCvs"></div>


</body>
</html>
