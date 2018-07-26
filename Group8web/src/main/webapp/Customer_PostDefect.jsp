<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="main.javafiles.CustomerDetails" %>
<%@ page import="main.javafiles.Customers" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <title>Customer Post Defect</title>
    <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
     <link rel="stylesheet" type="text/css" media="screen" href="./styles/styles.css" />
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
  
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
<div id="header"> </div>

<div class="container">

    <div>
        <h2>Post Defect</h2>
    </div>
    
     <%
 CustomerDetails c = new CustomerDetails();
Customers customer = new Customers();
HttpSession Session = request.getSession(false); 
if(Session!=null && session.getAttribute("cid") != null) 
{	System.out.println(session.getAttribute("cid"));
int cid=(int)session.getAttribute("cid");
customer = c.getCustomer(cid);

%>
<% } %>

    <div class="row">
        <div class="col-sm-12 col-xs-12 col-lg-6 col-md-6">
            <h2>Customer Details</h2>
            <div class="card">
                <div class="card-body">
            <form>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" placeholder="" value=<%= customer.getFirstName() %> disabled>
            </div>
            <div class="form-group">
                <label for="mail">Email</label>
                <input type="email" class="form-control" id="mail" placeholder="" value=<%= customer.getEmail() %> disabled>
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="text" class="form-control" id="phone" placeholder="" value=<%= customer.getPhone() %> disabled>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <textarea class="form-control" id="address" rows="5" disabled><%= customer.getAddress() %></textarea>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">View on map</button>
            </div>
         
            </form>
            <div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">


      <!-- Modal body -->
      <div class="modal-body">
        <div id="map" style="width:100%;height:300px"></div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
      <input id="submit" type="button" value="View Address">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
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
<script type="text/javascript">
  function myMap() {
	   	
	  address = '6216 chebucto';
	  var myCenter = new google.maps.LatLng(44.649661,-63.598035);
	
	   
    var mapCanvas = document.getElementById("map");
    var mapOptions = {center: myCenter, zoom: 5};
    var map = new google.maps.Map(mapCanvas, mapOptions);
    
    var geocoder = new google.maps.Geocoder();

    document.getElementById('submit').addEventListener('click', function() {
      geocodeAddress(geocoder, map);
    });
  }
  function geocodeAddress(geocoder, resultsMap) {
      var address = document.getElementById('address').value;
      geocoder.geocode({'address': address}, function(results, status) {
        if (status === 'OK') {
          resultsMap.setCenter(results[0].geometry.location);
          var marker = new google.maps.Marker({
            map: resultsMap,
            position: results[0].geometry.location
          });
        } else {
          alert('Geocode was not successful for the following reason: ' + status);
        }
      });
    }
  </script>


  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB5D8iYnTzWIxabHI-5NDOY77KrCkQEVsU&callback=myMap"></script>
  
  <script>
  $("#header").load("./header.jsp");
</script>




</body>
</html>