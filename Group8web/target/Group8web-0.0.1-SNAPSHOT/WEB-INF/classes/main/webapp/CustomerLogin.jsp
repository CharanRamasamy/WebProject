<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
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
    <!-- Page Content -->
    <div class="container">

      <!-- Page Heading/Breadcrumbs -->
      <h1 class="mt-4 mb-3">&nbsp;
        <small></small>
      </h1>

      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="index.jsp">Home</a>
        </li>
        <li class="breadcrumb-item active">Customer</li>
      </ol>      
      <div class="row">
	  <div class="col-lg-5 mb-5">
	  <div class="card mb-5">
            <h5 class="card-header">Login</h5>
            <div class="card-body">
			<form name="login_customer" onSubmit="return validateLogin()" method="post" action="logincheck">
			  <%   if(request.getAttribute("Message")!=null) { %>
                           <span class="Error_block" id="error"><%=request.getAttribute("Message") %></span><br> 
                           <%} %>
			 <div class="control-group form-group">
              <div class="controls">
                <label>Username:</label>
                <input type="text" class="form-control" placeholder="Username" name="lusername" id="lusername">
                <p class="help-block"></p>
              </div>
            </div>

			<div class="control-group form-group">
              <div class="controls">
                <label>Password:</label>
                <input type="password" class="form-control" placeholder="Password" name="lpassword" id="lpassword">
                <p class="help-block"></p>
              </div>
            </div>
			  
			  <span class="input-group-btn">
                 
            <!-- For success/fail messages -->
           
             <input type="submit"  class="btn btn-success btn-lg btn-block" value="Login">
                </span>
			</form>
            </div>
          </div>
</div>
        <div class="col-lg-7 mb-4">
		  <div class="card mb-5">
            <h5 class="card-header">New Customer</h5>
            <div class="card-body">
          <form name="register_customer" onSubmit="return validateForm()"  method="post" action="addcustomer">
            <div class="control-group form-group">
              <div class="controls">
                <label>First Name:</label>
                <input type="text" class="form-control" name="fname" id="fname">
                <p class="help-block"></p>
              </div>
            </div>
			<div class="control-group form-group">
              <div class="controls">
                <label>Last Name:</label>
                <input type="text" class="form-control" name="lname" id="lname">
                <p class="help-block"></p>
              </div>
            </div>
             <div class="control-group form-group">
              <div class="controls">
                <label>Email Address:</label>
                <input type="email" class="form-control" name="email" id="email">
              </div>
            </div>
			<div class="control-group form-group">
              <div class="controls">
                <label>Username:</label>
                <input type="text" class="form-control" name="username" id="username">
              </div>
            </div>
			<div class="control-group form-group">
              <div class="controls">
                <label>Password:</label>
                <input type="password" class="form-control" name="password" id="password" >
              </div>
            </div>
			<div class="control-group form-group">
              <div class="controls">
                <label>Phone Number:</label>
                <input type="text" class="form-control" name="phone" id="phone" >
              </div>
            </div>
            <div class="control-group form-group">
              <div class="controls">
                <label>Address:</label>
                <textarea rows="10" cols="100" class="form-control" name="address" id="address" ></textarea>
              </div>
            </div>
			<div class="control-group form-group">
              <div class="controls">
                <label>Postal Code:</label>
                <input type="text" class="form-control"  name="code"  id="code" >
              </div>
            </div>
            <div id="successnow"></div>
            <!-- For success/fail messages -->
            <input type="submit" style="width: 100%" class="btn btn-success btn-lg btn-block" value="Register">
          </form>
        </div>
        </div>
        </div>

      </div>
      <!-- /.row -->

    </div>
    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; REST 2018</p>
      </div>
      <!-- /.container -->
    </footer>
    
     <script>
  function validateForm() {
	  var v = document.forms["register_customer"]["fname"].value;
	    if (v == "") {
	        alert("Please enter the first name");
	        return false;
	    }
    var w = document.forms["register_customer"]["lname"].value;
    if (w == "") {
        alert("Please enter lastname");
        return false;
    }
      var x = document.forms["register_customer"]["email"].value;
      if (x == "") {
          alert("Please enter email");
          return false;
      }
      var y = document.forms["register_customer"]["username"].value;
      if (y == "") {
          alert("Please enter username");
          return false;
      }
      var z = document.forms["register_customer"]["password"].value;
      if (z == "") {
          alert("Please enter password");
          return false;
      }
      var u = document.forms["register_customer"]["phone"].value;
      if (u == "") {
          alert("Please enter phone number");
          return false;
      }
      var t = document.forms["register_customer"]["address"].value;
      if (t == "") {
          alert("Please enter address");
          return false;
      }
      var s = document.forms["register_customer"]["code"].value;
      if (s == "") {
          alert("Please enter code");
          return false;
      }
  }
  </script>
  
  <script>
  function validateLogin() {
	  var v = document.forms["login_customer"]["lusername"].value;
	    if (v == "") {
	        alert("Please enter the username");
	        return false;
	    }
    var w = document.forms["login_customer"]["lpassword"].value;
    if (w == "") {
        alert("Please enter password");
        return false;
    }
     
  }
  </script>

    <!-- Bootstrap core JavaScript -->
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>

   
   
<script>
  $("#header").load("./header.jsp");
</script>

</body>
</html>