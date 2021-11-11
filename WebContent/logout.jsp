<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*,model.DBUtil"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name='viewport'
	content="width=device-width,initial-scale=1.0">
<style type="text/css">
body {
   background: black;
   background-image: url("logout.jpg");
   background-size: 40%;
   background-position: top center;
   background-repeat: no-repeat;
   text-align: center;
}

.btn {
   font-size: 11pt;
   margin: 10px;
}

#header, #id, #pw {
   font-size:45px;
   color: white;
   text-shadow: 0 0 5px #000000;
}
#btn{
   padding:10px 15px 10px 15px;
   font-size:17px;
   background:white;

}
#footer {
   background-color: #bdbdbd;
   position: absolute;
   bottom: 0;
   left: 0;
   color: white;
   width: auto;
   width: 100%;
   margin: 0px;
}

</style>

<title>Log Out</title>
</head>
<body>

   <h1 id="header" align="center">See you later!</h1>
   <input type="button" id="btn" value="home" onclick="location.href='index.html'">
   <div id="footer">
      <p>developed by: Yeji An & Seowoo Lee& Sohee Hyoun</p>
      <p>e-mail: ann4913@naver.com & twsara@naver.com &
         hyounso2@naver.com</p>
   </div>
</body>
</html>