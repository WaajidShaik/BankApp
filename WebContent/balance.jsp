 
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.PrintWriter" %>
<html>
<head>
<title>BALANCE</title>
</head>
<body>
<h1 align="center">
  <img src="G:\web_anudip\logo_sbi.png">
</h1>
<%
PrintWriter pw=response.getWriter();
session=request.getSession();
String balance=session.getAttribute("balance").toString();
%>
<br>
<h1>
<font face="impact">
YOUR ACCOUNT BALANCE IS <%= balance %>
</font>
</h1>
<a href="home.html"><button>Back</button></a>
</body>
</html>