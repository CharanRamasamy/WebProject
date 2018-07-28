<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>RESolvewithTech(REST)</title>

     <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen" href="./styles/styles.css" />
</head>
<body>

    <nav class="navbar navbar-expand-sm  navbar-light">
    <a class="navbar-brand" href="#">
        <img src="./styles/images/logo.png" alt="logo" style="width:100px;">
    </a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li>
                <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
            </li>
            <%
HttpSession Session = request.getSession(false); 
if(Session!=null && session.getAttribute("cid") != null) 
{	
	String name=session.getAttribute("fname").toString();
%>
			<li>
			<a class="nav-link" href="#">Welcome! <%=name %> <span class="sr-only">(current)</span></a>
               
            </li>
			<li>
                <a class="nav-link" href="Logout">Logout <span class="sr-only">(current)</span></a>
            </li>
<% } %>

        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button type="submit">Search</button>
        </form>


    </div>
    </nav>

</body>
</html>