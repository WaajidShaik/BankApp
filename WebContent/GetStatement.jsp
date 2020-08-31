<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.*"%>
 <%@ page import="java.io.*"%>
<html>
<head>
<title>STATEMENT</title>
<h1 align="center">
  <img src="G:\web_anudip\logo_sbi.png">
</h1>
</head>
<body>
<br>
<br>
<br>
<%
PrintWriter pw=response.getWriter();
session=request.getSession();
ArrayList al1=(ArrayList)session.getAttribute("al1");
ArrayList al2=(ArrayList)session.getAttribute("al2");
%>
<h1>
<font face="impact">
your credit details are <%= al1 %>
</font>
</h1>
<br>
<br>
<h1>
<font face="impact">
your debit details are <%= al2 %>
</font>
</h1>

</body>
</html>