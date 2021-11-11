<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*,model.Block,model.DBUtil"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name='viewport' content="width=device-width,initial-scale=1.0">
<style>
body{
width:100%;
}
#p1{
width:300px;
margin-left:35%;
height:150px;
}
table,tr,th{
border-collapse:collapse;
border:1px solid black;
}
#deq{
width:600px;
height:300px;
position:absolute;
top:65%;
left:35%;
}
#p3{
width:150px;
height:300px;
position:relative;
bottom:400px;
left:90%;
}
#p2{
width:150px;
height:300px;
}
#btn{
font-size:18pt;
width:100px;
height:auto;
text-align:center;
position:relative;
bottom:380px;
left:52%;
}
#sub{
font-size:18pt;
width:auto;
height:auto;
text-align:center;
position:relative;
bottom:300px;
left:52%;
visibility:visible;
}
#grp{
position:relative;
left:48%;
bottom:250px;
text-align:center;
font-size:14pt;
}
.rad,span{
position:relative;
left:35%;
bottom:250px;
text-align:center;
font-size:14pt;
}
#name,#gname{
position:relative;
left:48%;
bottom:250px;
text-align:center;
background-color:#ababab;
color:white;
font-size:14pt;
}
@media screen and (max-width:990px){
#p1{
width:200px;
margin-left:28%;
height:100px;
}
#p3{
width:100px;
height:200px;
position:relative;
bottom:300px;
left:80%;
}
#p2{
width:100px;
height:200px;
}
#deq{
width:300px;
height:200px;
position:absolute;
top:80%;
left:17%;
}
#sel{
font-size:14pt;
width:50px;
height:auto;
text-align:center;
position:relative;
bottom:250px;
left:30%;
}
#sub{
font-size:14pt;
width:auto;
height:auto;
text-align:center;
position:relative;
bottom:120px;
margin-right:120px;
margin-left:0;
}
#grp{
position:relative;
left:30%;
bottom:150px;
text-align:center;
font-size:12pt;
width:150px;
}
.rad{
position:relative;
bottom:110px;
left:30%;
text-align:left;
font-size:12pt;
width:150px;
}
span{
position:relative;
bottom:110px;
left:25%;
text-align:left;
font-size:12pt;
width:150px;
}
#name,#gname{
position:relative;
left:30%;
bottom:150px;
text-align:center;
background-color:#ababab;
color:white;
font-size:12pt;
width:150px;
}
}
</style>
<script>
var a1=[];
var a2=[];
var a3=[];
var p1_op=[];
var p2_op=[];
var bl1=["b1","b2","b3","b4"];
var bl2=["b5","b6","b7","b8"];
var bl3=["b9","b10","b11","b12","b13","b14","b15","b16","b17","b18","b19","b20","b21","b22","b23","b24"];
var turn=0,cor=0,mine=4;
function pagestart() {
	window.setTimeout("pagereload()", 10000);
	document.getElementById("hidden").value='0';
	document.getElementById("hidden").value = null;
	var radio=document.getElementsByName("block");
	<% 
	String[] pl=new String[2];
	ServletContext sc=getServletContext();
	Connection con=(Connection)sc.getAttribute("DBconnection");
	String grp=(String)request.getParameter("grp");
	String user=(String)request.getParameter("user");
	String game=request.getParameter("game");
	ResultSet m=DBUtil.findDPlayer(con, user);
	int my_left=4;
	while(m.next()){
		my_left=m.getInt("closed");
	}
	if(my_left<=0){
		out.print("document.getElementById('grp').value='-1';");
		out.print("document.Ent.submit();");
	}
	ResultSet rs=DBUtil.getGrpMem(con,grp,2);
	int b1_op=0,b2_op=0,b3_op=0,b4_op=0;
	int[] p1_op=new int[4];
	int[] p2_op=new int[4];
	while(rs.next()){
		pl[0]=rs.getString("p1");
		pl[1]=rs.getString("p2");
		if(DBUtil.checkDPlayer(con,pl[0])||DBUtil.checkDPlayer(con,pl[1])){
			out.print("document.getElementById('grp').value='-1';");
			out.print("document.Ent.submit();");
		}
		ResultSet r=DBUtil.findDPlayer(con, pl[0]);
		while(r.next()){
			int b1N=r.getInt(2);
			String b1C=r.getString(3);
			int b2N=r.getInt(4);
			String b2C=r.getString(5);
			int b3N=r.getInt(6);
			String b3C=r.getString(7);
			int b4N=r.getInt(8);
			String b4C=r.getString(9);
			System.out.println(r.getString(1)+" "+b1N+" "+b2N+" "+b3N+" "+b4N);
			p1_op[0]=r.getInt("b1_open");
			p1_op[1]=r.getInt("b2_open");
			p1_op[2]=r.getInt("b3_open");
			p1_op[3]=r.getInt("b4_open");
			out.print("p1_op[0]=["+p1_op[0]+"];");
			out.print("p1_op[1]=["+p1_op[1]+"];");
			out.print("p1_op[2]=["+p1_op[2]+"];");
			out.print("p1_op[3]=["+p1_op[3]+"];");
			out.print("a1[0]=["+b1N+",'"+b1C+"'];");
			out.print("a1[1]=["+b2N+",'"+b2C+"'];");
			out.print("a1[2]=["+b3N+",'"+b3C+"'];");
			out.print("a1[3]=["+b4N+",'"+b4C+"'];");
		}
		r=DBUtil.findDPlayer(con, pl[1]);
		while(r.next()){
			int b1N=r.getInt(2);
			String b1C=r.getString(3);
			int b2N=r.getInt(4);
			String b2C=r.getString(5);
			int b3N=r.getInt(6);
			String b3C=r.getString(7);
			int b4N=r.getInt(8);
			String b4C=r.getString(9);
			p2_op[0]=r.getInt("b1_open");
			p2_op[1]=r.getInt("b2_open");
			p2_op[2]=r.getInt("b3_open");
			p2_op[3]=r.getInt("b4_open");
			out.print("p2_op[0]=["+p2_op[0]+"];");
			out.print("p2_op[1]=["+p2_op[1]+"];");
			out.print("p2_op[2]=["+p2_op[2]+"];");
			out.print("p2_op[3]=["+p2_op[3]+"];");
			out.print("a2[0]=["+b1N+",'"+b1C+"'];");
			out.print("a2[1]=["+b2N+",'"+b2C+"'];");
			out.print("a2[2]=["+b3N+",'"+b3C+"'];");
			out.print("a2[3]=["+b4N+",'"+b4C+"'];");
		}
		ResultSet dc=DBUtil.findDeq2Col(con, pl[0]);
		ResultSet dn=DBUtil.findDeq2Num(con,pl[0]);
		String[] color=new String[16];
		int[] num=new int[16];
		while(dc.next()&&dn.next()){
			for(int i=0;i<16;i++){
				color[i]=dc.getString(i+2);
				num[i]=dn.getInt(i+2);
				out.print("a3["+i+"]=["+num[i]+",'"+color[i]+"'];");
			}
		}
	}
	int turn=0;
	ResultSet t=DBUtil.findDPlayer(con, user);
	while(t.next()){
		turn=t.getInt("next_turn");
	}
	System.out.println("present turn:"+turn);
	if(turn==1){
		if(user.equals(pl[0])){
			for(int i=0;i<4;i++){
				out.print("radio["+i+"].disabled=false;");
				out.print("radio["+i+"].style.color='black';");
			}
		}
		else {
			for(int i=0;i<4;i++){
				out.print("radio["+i+"].disabled=true;");
				out.print("radio["+i+"].style.color='#dcdcdc';");
			}
		}
	}
	else if(turn==2){
		if(user.equals(pl[1])){
			for(int i=0;i<4;i++){
				out.print("radio["+i+"].disabled=false;");
				out.print("radio["+i+"].style.color='black';");
			}
		}
		else{
			for(int i=0;i<4;i++){
				out.print("radio["+i+"].disabled=true;");
				out.print("radio["+i+"].style.color='#dcdcdc';");
			}
		}
	}
	%>
	for(var i=0;i<4;i++){
		var e1=document.getElementById(bl1[i]);
		var e2=document.getElementById(bl2[i]);
		if(a1[i][1]=="black")
			e1.style.background="black";
		else
			e1.style.background="white";
		if(e1.style.background=="white")
			e1.style.color="black";
		else if(e1.style.background=="black")
			e1.style.color="white";
			<% 
				if(pl[0].equals(user)){
					out.print("e1.innerHTML=a1[i][0];");
				}
			%>
			if(p1_op[i]==1){
				e1.innerHTML=a1[i][0];
			}
		if(a2[i][1]=="black")
			e2.style.background="black";
		else
			e2.style.background="white";
		if(e2.style.background=="white")
			e2.style.color="black";
		else if(e2.style.background=="black")
			e2.style.color="white";
			<% 
				if(pl[1].equals(user)){
					out.print("e2.innerHTML=a2[i][0];");
				}
			%>
			if(p2_op[i]==1){
				e2.innerHTML=a2[i][0];
			}
	}
	for(var i=0;i<16;i++){
		var el=document.getElementById(bl3[i]);
		if(a3[i][1]=="black")
			el.style.background="black";
		else
			el.style.background="white";
		el.innerHTML=a3[i][0];
		if(el.style.background=="white")
			el.style.color="black";
		else if(el.style.background=="black")
			el.style.color="white";
	}
}
function pagereload() {
	location.reload();
	document.getElementById("hidden").value='0';
	document.getElementById("grp").value='input num';
}

function func(){
	document.getElementById("hidden").value='1';
	if(document.getElementById("grp")==null) document.getElementById("grp").value=-1;
}
</script>
<title>Davinci game</title>
</head>
<body onLoad="pagestart()" >
<table id="p1">
<tr>
<th id="b1">b1</th>
<th id="b2">b2</th>
<th id="b3">b3</div></th>
<th id="b4">b4</th>
</tr>
</table>
<table id="p2">
<tr>
<th id="b5">b5</th>
</tr>
<tr>
<th id="b6">b6</th>
</tr>
<tr>
<th id="b7">b7</th>
</tr>
<tr>
<th id="b8">b8</th>
</tr>
</table>
<form action="SelNum" method="post" name="Ent">
<input type="text" name="hidden" value="0" id="hidden" style="display:none">
<% 
String name=(String)request.getParameter("user");
out.print("<input type='text' name='uname' value='"+name+"' id='name' readonly><br>");
out.print("<input type='text' name='gname' value='"+pl[0]+"' id='gname' readonly><br>");
out.print("<input type='text' name='game' value='"+game+"' id='game' style='display:none' readonly><br>");
String s=(String)request.getParameter("user"); 
%>
<input type="text" placeholder="input number" name="result" id="grp" ><br>
<% if(pl[0].equals(user)){
	for(int i=5;i<9;i++)
		out.print("<input type='radio' class='rad' name='block' value='"+i+"'><span>block"+i+"</span><br>");
	}
else if(pl[1].equals(user)){
	for(int i=1;i<5;i++)
		out.print("<input type='radio' class='rad' name='block' value='"+i+"'><span>block"+i+"</span><br>");
}
	%>
<br>
<input type="submit" id="sub" style="background-color:#bdbdbd" value="enter" onclick="func();" ><br><br>
</form>
<table id="deq">
<tr>
<th id="b9"><div><br></div></th>
<th id="b10"><div><br></div></th>
<th id="b11"><div><br></div></th>
<th id="b12"><div><br></div></th>
<th id="b13"><div><br></div></th>
<th id="b14"><div><br></div></th>
<th id="b15"><div><br></div></th>
<th id="b16"><div><br></div></th>
</tr>
<tr>
<th id="b17"><div><br></div></th>
<th id="b18"><div><br></div></th>
<th id="b19"><div><br></div></th>
<th id="b20"><div><br></div></th>
<th id="b21"><div><br></div></th>
<th id="b22"><div><br></div></th>
<th id="b23"><div><br></div></th>
<th id="b24"><div><br></div></th>
</tr>
</table>

</body>
</html>