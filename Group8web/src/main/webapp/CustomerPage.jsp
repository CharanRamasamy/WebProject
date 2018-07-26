<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="main.javafiles.DefectDetails" %>
<%@ page import="main.javafiles.Defects" %>
<%@ page import="main.javafiles.utils.DBConnection" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

  <title>RESolvewithTech(REST)</title>

    <!-- Bootstrap core CSS -->
     <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" media="screen" href="./styles/modern-business.css" />
     <link rel="stylesheet" type="text/css" media="screen" href="./styles/styles.css" />

      <style>
          body{
              background-image: url("./styles/images/bgimage.jpg");
              height: 100%;
              width: 100%;
              background-position: center;
              background-repeat: no-repeat;
              background-size: cover;
              position: relative;
              margin: 0;
          }
      </style>
</head>
<body>
 <div id="header"> </div>
      
      <div class="container">

      <!-- Page Heading/Breadcrumbs -->
      <h1 class="mt-4 mb-3">Customer
        <small>Page</small>
      </h1>

      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="index.html">Home</a>
        </li>
        <li class="breadcrumb-item active">Customer Page</li>
      </ol>

   
		<div class="mb-4">
		<h5 class=""><a class="btn btn-warning" href="Customer_PostDefect.jsp">Add New  Defects</a></h5>
		</div>
           <div class="mb-4">
               <h5 class=""><a class="btn btn-warning" href="confirm_technician.html">View Request</a></h5>
           </div>
           
             <!-- Blog Post -->
	   <div class="card mb-4">
	  <h5 class="card-header">Previous History</h5>
	  <div class="table">
<table align="center">
 <%
 DefectDetails d = new DefectDetails();
ArrayList<Defects> defectlist = new ArrayList<Defects>();
HttpSession Session = request.getSession(false); 
if(Session!=null && session.getAttribute("cid") != null) 
{	System.out.println(session.getAttribute("cid"));
int cid=(int)session.getAttribute("cid");
defectlist = d.getDefects(cid);


%>
<% } %>
<tr>
<th> Category </th>
         <th>Service Request</th>
          
            <th>Details</th>
             <th>Status</th>

</tr>

 <%for(int i=0; i < defectlist.size(); i++)
    {
	 Defects defect = new Defects();
	 defect = (Defects) defectlist.get(i);
        %>
        <tr>
         <td style="width:20%"><%= defect.getdefect_Category() %></td>
         <td style="width:20%"><%= defect.getDefect_Name() %></td>
          
            <td style="width:40%" ><%= defect.getdefect_Description()%></td>
             <td style="width:10%" ><%=defect.getdefect_Status() %></td>
           
            </tr>
        
        <%}%>
        
</table>
</div>
       
</div>
           
           
           
           
		</div>

		<hr/>
		
    

  
  <!-- /.container -->

  <!-- Footer -->
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