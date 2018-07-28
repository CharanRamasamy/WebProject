<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="main.javafiles.TechnicianHome" %>
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

      
</head>
<body>
<div id="header"> </div>
<table align="center">
 <%
TechnicianHome d = new TechnicianHome();
ArrayList<Defects> defectlist = new ArrayList<Defects>();
HttpSession Session = request.getSession(false); 

defectlist = d.getDefectsbyCategory("Furniture");


%>

<tr>
<th> Service Request name </th>
         <th>Service Request Details</th>
          
            <th>Details</th>
             <th>View Details</th>

</tr>

 <%for(int i=0; i < defectlist.size(); i++)
    {
	 Defects defect = new Defects();
	 defect = (Defects) defectlist.get(i);
        %>
        <tr>
         
         <td style="width:20%"><%= defect.getDefect_Name() %></td>
          
            <td style="width:60%" ><%= defect.getdefect_Description()%></td>
             <td style="width:20%" >
             <a href="TechnicianDefectDetails.jsp?defectname=<%= defect.getDefect_Name()  %>" class="btn btn-primary">View Details</a>
             </td>
           
            </tr>
        
        <%}%>
        
</table>



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