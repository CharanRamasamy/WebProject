<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="main.javafiles.TechnicianHome" %>
<%@ page import="main.javafiles.TechnicianDefect" %>
<%@ page import="main.javafiles.DefectDetails" %>
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
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

      
</head>
<body>
<% 
String defect_name = request.getParameter("defectname");
DefectDetails dd = new DefectDetails();
TechnicianDefect cd = new TechnicianDefect();
cd = dd.getTechnicianandDefect(defect_name);
%>

<div id="header"> </div>

<div class="container centre-form">
  	<h1 class="DefectHeadTitle">Confirmation Page</h1>
  	<hr>
 
 <form name="CommitDefectform" onSubmit="return validateLogin()" method="post" action="AcceptRequest?defectname=<%= defect_name %>">

<div class="col-md-offset-3 col-md-6 centre-form">

  <div class="form-group row">
    <label for="categoryName" class="col-md-6 col-form-label formDisplay"><b>Category</b></label>
    <div class="col-sm-6 md-6">
      <input type="text" readonly class="form-control-plaintext formValueDisplay" id="categoryName" value=<%= cd.getdefect_Category()%>>
    </div>
  </div>
  
      <div class="form-group row">
    <label for="techName" class="col-md-6 col-form-label formDisplay"><b>Technician Name</b></label>
    <div class="col-sm-6 md-6">
      <input type="text" readonly class="form-control-plaintext formValueDisplay" id="techName" value=<%= cd.getFirstName()%>>
    </div>
  </div>
  
    <div class="form-group row">
    <label for="defectDescription" class="col-md-6 col-form-label formDisplay"><b>Defect Description</b></label>
    <div class="col-sm-6 md-6">
      <input type="text" readonly class="form-control-plaintext formValueDisplay" id="defectDescription" value=<%= cd.getdefect_Description()%>>
    </div>
  </div>
  
     <div class="form-group row">
    <label for="techSkills" class="col-md-6 col-form-label formDisplay"><b>Technician Skills</b></label>
    <div class="col-sm-6 md-6">
      <input type="text" readonly class="form-control-plaintext formValueDisplay" id="techSkills" value=<%= cd.getSkills()%>>
    </div>
  </div>
  
       <div class="form-group row">
    <label for="contactTech" class="col-md-6 col-form-label formDisplay"><b>Contact Technician</b></label>
    <div class="col-sm-6 md-6">
      <input type="text" readonly class="form-control-plaintext formValueDisplay" id="contactTech" value=<%= cd.getPhone()%>>
      <input type="text" readonly class="form-control-plaintext formValueDisplay" id="contactTech" value=<%= cd.getEmail()%>>
    </div>
  </div>
  
       <div class="form-group row">
    <label for="proposedFee" class="col-md-6 col-form-label formDisplay"><b>Proposed Fees</b></label>
    <div class="col-sm-6 md-6">
      <input type="text" readonly class="form-control-plaintext formValueDisplay" id="proposedFee" value=<%= cd.getfees()%>>
    </div>
  </div>
  
         <div class="form-group row">
    <label for="techComments" class="col-md-6 col-form-label formDisplay"><b>Technician Comments</b></label>
    <div class="col-sm-6 md-6">
      <input type="text" readonly class="form-control-plaintext formValueDisplay" id="techComments" value=<%= cd.getComments()%>>
    </div>
  </div>
  
         <div class="form-group row">
    <label for="technicianAddr" class="col-md-6 col-form-label formDisplay"><b>Technician Address</b></label>
    <div class="col-sm-6 md-6">
    <textarea class="form-control" id="address" rows="3" disabled><%= cd.getAddress() %></textarea>
    <hr/>
        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">
    View on map
  	</button>
    </div>
  </div>
  <button type="submit" class="btn btn-success btn-block">Accept</button>
  
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
  <footer class="navbar navbar-expand-lg navbar-light bg-primary footer">

    <small class="mr-auto">@REST</small>

    <small class="ml-auto">Resolve with Tech!</small>

</footer>

  

  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>
  function myMap() {
	   	
	  
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