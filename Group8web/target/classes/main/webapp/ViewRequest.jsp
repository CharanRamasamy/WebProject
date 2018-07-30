<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="main.javafiles.DefectDetails" %>
<%@ page import="main.javafiles.Defects" %>
<%@ page import="main.javafiles.utils.DBConnection" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
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
      <h1 class="mt-4 mb-3">View
        <small>Request</small>
      </h1>          
            
	   <div class="card mb-4">
	  <h5 class="card-header text-center" style="color: #71268e; font-size: 150%;">Pending Requests</h5>
	  <%
 DefectDetails d = new DefectDetails();
ArrayList<Defects> defectlist = new ArrayList<Defects>();
HttpSession Session = request.getSession(false); 
String pass = "";
if(Session!=null && session.getAttribute("cid") != null) 
{	System.out.println(session.getAttribute("cid"));
int cid=(int)session.getAttribute("cid");
defectlist = d.getRequestDefects(cid);


%>
<% } %>
	  
	  <div class="table">
	  
<table>
 
<tr>
<th class="tableHeader"> Category </th>
         <th class="tableHeader">Service Request</th>        
            <th class="tableHeader">Details</th>
             <th class="tableHeader">Status</th>
             <th class="tableHeader">Accept</th>
              <th class="tableHeader">Decline</th>
</tr>

 <%for(int i=0; i < defectlist.size(); i++)
    {
	 Defects defect = new Defects();
	 defect = (Defects) defectlist.get(i);
        %>
        <tr>
         <td style="width:20%"><%= defect.getdefect_Category() %></td>
         <td style="width:20%"><%= defect.getDefect_Name() %></td>
          
            <td style="width:30%" ><%= defect.getdefect_Description()%></td>
             <td style="width:10%" ><%=defect.getdefect_Status() %></td>
             <td style="width:10%" >
             <a href="AcceptDecision.jsp?defectname=<%= defect.getDefect_Name() %>" class="btn btn-success">Accept</a>
             </td>
           	<td style="width:10%" >
           	<a href="DeclineDecision.jsp?defectname=<%= defect.getDefect_Name() %>" class="btn btn-danger">Decline</a>
           	</td>
            </tr>
        
        <%}%>
        
</table>
</div>
</div>
		</div>
		
		<script>
function doSubmit(){
var actionURL ="MENTION URL YOU WANT TO REDIRECT";
// perform your operations

myForm.submit(actionURL); OR
myForm.submit();
}

</script>

		<hr/>
		
    

  
  <!-- /.container -->



  <!-- Bootstrap core JavaScript -->
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
  $("#header").load("./header.jsp");
</script>

</body>
</html>