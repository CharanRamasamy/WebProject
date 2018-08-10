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
<%
if(Session!=null && session.getAttribute("tid") != null) 
{	
	String tname=session.getAttribute("tfname").toString();
%>
			<li>
			<a class="nav-link" href="#">Welcome! <%=tname %> <span class="sr-only">(current)</span></a>
               
            </li>
			<li>
                <a class="nav-link" href="Logout">Logout <span class="sr-only">(current)</span></a>
            </li>
<% } %>

        </ul>
        <form name="search_form" class="form-inline my-2 my-lg-0" onSubmit="return validateSearchForm()" method="post" action="searchtech">
             <div class="control-group form-group">
            <div class="controls">
             
                     
                        <select name="category" id="category" class="control-group form-group">
                            <option>--Search Technicians in following categories--</option>
                            <option>Electronics</option>
                            <option>Furniture</option>
                            <option>Home Appliance</option>
                            <option>Auto Mobile</option>
                        </select>
                         </div>
            </div>
                       
            <button type="submit">Search</button>
        </form>
    </div>
    </nav>
    
    <script>
  function validateSearchForm() {
	  var v = document.forms["search_form"]["category"].value;
	    if (v == "--Search Technicians in following categories--") {
	        alert("Please enter the valid catgory for viewing technicians");
	        return false;
	    }
    
    }
     
  
  </script>
</body>
</html>