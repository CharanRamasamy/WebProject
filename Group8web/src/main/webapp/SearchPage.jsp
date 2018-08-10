<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="main.javafiles.TechnicianHome" %>
<%@ page import="main.javafiles.SearchDetails" %>
<%@ page import="main.javafiles.TechnicianDefect" %>
<%@ page import="main.javafiles.utils.DBConnection" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


  <title>RESolvewithTech(REST)</title>

    <!-- Bootstrap core CSS -->
     <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" media="screen" href="./styles/modern-business.css" />
     <link rel="stylesheet" type="text/css" media="screen" href="./styles/styles.css" />
      
</head>
<body>
<div id="header"> </div>
   
      <div class="container">

      <!-- Page Heading/Breadcrumbs -->
      <h1 class="mt-4 mb-3">Technician
        <small>Page</small>
      </h1>

      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="index.jsp">Home</a>
        </li>
        <li class="breadcrumb-item active">Technicians</li>
      </ol>

   
		
           
             <!-- Blog Post -->
	   <div class="card mb-4">
	 
	  <div class="table">
<table >
 <%
 SearchDetails sd = new SearchDetails();
ArrayList<TechnicianDefect> techlist = new ArrayList<TechnicianDefect>();
String searchtxt = request.getParameter("search");
techlist = sd.gettechnician(searchtxt);
%>

<tr>
<th> Technician First Name </th>
         <th>Technician First Name</th>
             <th>Technician Skills</th>
             <th>Phone</th>
             <th>Email</th>
             <th>Technician Category</th>

</tr>

 <%for(int i=0; i < techlist.size(); i++)
    {
	 TechnicianDefect td = new TechnicianDefect();
	 td = (TechnicianDefect) techlist.get(i);
        %>
        <tr>
         
         <td style="width:20%"><%= td.getFirstName() %></td>
          
            <td style="width:20%" ><%= td.getLastName() %></td>
            <td style="width:10%" ><%= td.getSkills() %></td>
            <td style="width:10%" ><%= td.getPhone() %></td>
              <td style="width:20%" ><%= td.getEmail() %></td>
              <td style="width:20%" ><%= td.getdefect_Category() %></td>
              <%} %>
        
</table>
</div>
       
</div>
           
           
           
           
		</div>




<footer class="navbar navbar-expand-lg navbar-light bg-primary footer">

    <small class="mr-auto">@REST</small>

    <small class="ml-auto">Resolve with Tech!</small>

</footer>

  <!-- Bootstrap core JavaScript -->
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>
    <script>
  $("#header").load("./header.jsp");
</script>
</body>
</html>