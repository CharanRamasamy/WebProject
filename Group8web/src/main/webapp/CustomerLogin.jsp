<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


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

         
      <!-- Navigation -->
      <nav class="navbar navbar-expand-sm  navbar-light"">
      <a class="navbar-brand" href="#">
          <img src="./styles/images/logo.png" alt="logo" style="width:100px;">
      </a>

      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
              <li>
                  <a class="nav-link" href="./index.html">Home <span class="sr-only">(current)</span></a>
              </li>


          </ul>
          <form class="form-inline my-2 my-lg-0">
              <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
              <button type="submit">Search</button>
          </form>


      </div>
      </nav>
    <!-- Page Content -->
    <div class="container">

      <!-- Page Heading/Breadcrumbs -->
      <h1 class="mt-4 mb-3">&nbsp;
        <small></small>
      </h1>

      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="index.html">Home</a>
        </li>
        <li class="breadcrumb-item active">Customer</li>
      </ol>

      <!-- Content Row -->

      <!-- Contact Form -->
      <!-- In order to set the email address and subject line for the contact form go to the bin/contact_me.php file. -->
      <div class="row">
	  <div class="col-lg-5 mb-5">
	  <div class="card mb-5">
            <h5 class="card-header">Login</h5>
            <div class="card-body">
			<form name="sentMessage" id="loginForm" novalidate method="post" action="checklogin">
			 <div class="control-group form-group">
              <div class="controls">
                <label>Username:</label>
                <input type="text" class="form-control" placeholder="Username" name="lusername" id="lusername" required data-validation-required-message="Please enter your Username.">
                <p class="help-block"></p>
              </div>
            </div>

			<div class="control-group form-group">
              <div class="controls">
                <label>Password:</label>
                <input type="password" class="form-control" placeholder="Password" name="lpassword" id="lpassword" required data-validation-required-message="Please enter your Password.">
                <p class="help-block"></p>
              </div>
            </div>
			  
			  <span class="input-group-btn">
                 <div id="success"></div>
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
          <h3></h3>
          <form name="sentMessage" id="regForm" novalidate method="post" action="addcustomer">
            <div class="control-group form-group">
              <div class="controls">
                <label>First Name:</label>
                <input type="text" class="form-control" name="fname" id="fname" required data-validation-required-message="Please enter your name.">
                <p class="help-block"></p>
              </div>
            </div>
			<div class="control-group form-group">
              <div class="controls">
                <label>Last Name:</label>
                <input type="text" class="form-control" name="lname" id="lname" required data-validation-required-message="Please enter your name.">
                <p class="help-block"></p>
              </div>
            </div>
             <div class="control-group form-group">
              <div class="controls">
                <label>Email Address:</label>
                <input type="email" class="form-control" name="email" id="email" required data-validation-required-message="Please enter your email address.">
              </div>
            </div>
			<div class="control-group form-group">
              <div class="controls">
                <label>Username:</label>
                <input type="text" class="form-control" name="username" id="username" required data-validation-required-message="Please enter your Username.">
              </div>
            </div>
			<div class="control-group form-group">
              <div class="controls">
                <label>Password:</label>
                <input type="password" class="form-control" name="password" id="password" required data-validation-required-message="Please enter your Password.">
              </div>
            </div>
			<div class="control-group form-group">
              <div class="controls">
                <label>Phone Number:</label>
                <input type="text" class="form-control" name="phone" id="phone" required data-validation-required-message="Please enter your phone number.">
              </div>
            </div>
            <div class="control-group form-group">
              <div class="controls">
                <label>Address:</label>
                <textarea rows="10" cols="100" class="form-control" name="address" id="address" required data-validation-required-message="Please enter your message" maxlength="999" style="resize:none"></textarea>
              </div>
            </div>
			<div class="control-group form-group">
              <div class="controls">
                <label>Postal Code:</label>
                <input type="text" class="form-control"  name="code"  id="code" required data-validation-required-message="Please enter your phone number.">
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

    <!-- Bootstrap core JavaScript -->
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Contact form JavaScript -->
    <!-- Do not edit these files! In order to set the email address and subject line for the contact form go to the bin/contact_me.php file. -->
   


</body>
</html>