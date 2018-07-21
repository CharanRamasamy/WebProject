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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Customer Post Defect</title>
    <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
     <link rel="stylesheet" type="text/css" media="screen" href="./styles/styles.css" />
    <script>
        function validateForm() {
            var name = document.forms["DetailForm"]["defectName"].value;
            var category = document.forms["DetailForm"]["category"].value;
            var description = document.forms["DetailForm"]["description"].value;
            if (name == "") {
                alert("Please Enter a Name");
                return false;
            } else if(description == "") {
                alert("Please Enter a Defect Description");
                return false;
            }
            else {
                alert("Form successfully submitted");
                return true;
            }
        }
    </script>
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
<header>
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
</header>

<div class="container">

    <div>
        <h2>Post Defect</h2>
    </div>

    <div class="row">
        <div class="col-sm-12 col-xs-12 col-lg-6 col-md-6">
            <h2>Customer Details</h2>
            <div class="card">
                <div class="card-body">
            <form>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" placeholder="" value="Philippe" disabled>
            </div>
            <div class="form-group">
                <label for="mail">Email</label>
                <input type="email" class="form-control" id="mail" placeholder="" value="Coutinho" disabled>
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="text" class="form-control" id="phone" placeholder="" value="902-909-6142" disabled>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <textarea class="form-control" id="address" rows="5" disabled>1333 South Park Street</textarea>
            </div>
            </form>
                </div>
            </div>
        </div>
        <div class="col-sm-12
 col-xs-12 col-lg-6 col-md-6">
            <h2>Add Defect Detail</h2>
            <div class="card"> 
                <div class="card-body">
            <form name="DetailForm" onSubmit="return validateForm()" method="post" action="postnewdefect" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="defectName">Defect Name*</label>
                    <input type="text" class="form-control" name="defectName" id="defectName" placeholder="Name of Defect">
                </div>
                <div class="form-group">
                    <label for="category">Select Category*</label>
                    <select class="form-control" name="category" id="category">
                        <option>Automobile</option>
                        <option>Electronics</option>
                        <option>Furniture</option>
                        <option>Home Appliance</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="uploadFile">Upload an Image</label>
                    <input type="file" class="form-control-file" name="photo"id="photo" size="50">
                     
                </div>
                <div class="form-group">
                    <label for="description">Defect Description*</label>
                    <textarea class="form-control" name="description" id="description" rows="3" placeholder="Description of the Problem"></textarea>
                </div>
                 <div class="form-group">
                    <label for="deadline">Deadline to fix the Defect*</label>
                     <input type="date" name="deadline">
                </div>
                
                <div class="text-center mt-4">
                    <button class="btn btn-primary" type="submit">SUBMIT</button>
                </div>
            </form>
                </div>
            </div>
        </div>
    </div>
</div>

<footer class="navbar navbar-expand-lg navbar-light bg-primary footer">

    <small class="mr-auto">@REST</small>

    <small class="ml-auto">Resolve with Tech!</small>

</footer>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>



</body>
</html>