<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="main.javafiles.TechnicianHome" %>
<%@ page import="main.javafiles.CustomerDefect" %>
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
String pass = "commit";
String defect_name = request.getParameter("defectname");
String defect_id = request.getParameter("defectid");
int did = Integer.valueOf(defect_id);
int tid = 0;
DefectDetails dd = new DefectDetails();
CustomerDefect cd = new CustomerDefect();

cd = dd.getCustomerandDefect(defect_name);
HttpSession Session = request.getSession(false); 
if(Session!=null && session.getAttribute("tid") != null) 
{
    tid = (int)session.getAttribute("tid");
}
String flag = dd.getTechnicianFlag(did, tid);
int customer_assigned = dd.getCustomerIdForDefectId(did);
%>

<div id="header"> </div>

<div class="container centre-form">
  	<h1 class="DefectHeadTitle">Defect Details</h1>
  	<hr>
 
 <div class="col-md-offset-3 col-md-6 centre-form">

  <div class="form-group row">
    <label for="categoryStatic" class="col-md-6 col-form-label formDisplay"><b>Category</b></label>
    <div class="col-sm-6 md-6">
      <input type="text" readonly class="form-control-plaintext formValueDisplay" id="categoryStatic" value=<%= cd.getdefect_Category()%>>
    </div>
  </div>
  
  <div class="form-group row">
    <label for="cusNameStatic" class="col-md-6 col-form-label formDisplay"><b>Customer Name</b></label>
    <div class="col-sm-6 md-6">
      <input type="text" readonly class="form-control-plaintext formValueDisplay" id="cusNameStatic" value=<%= cd.getFirstName()%>>
    </div>
  </div>
  
      <div class="form-group row">
    <label for="defectDesc" class="col-md-6 col-form-label formDisplay"><b>Defect Description</b></label>
    <div class="col-sm-6 md-6">
      <input type="text" readonly class="form-control-plaintext formValueDisplay" id="defectDesc" value=<%= cd.getdefect_Description()%>>
    </div>
  </div>
  
    <div class="form-group row">
    <label for="contactCustomer" class="col-md-6 col-form-label formDisplay"><b>Contact Customer</b></label>
    <div class="col-sm-6 md-6">
      <input type="text" readonly class="form-control-plaintext formValueDisplay" id="contactCustomer" value=<%= cd.getPhone()%>>
      <input type="text" readonly class="form-control-plaintext formValueDisplay" id="contactCustomer" value=<%= cd.getEmail()%>>
    </div>
  </div>
  
      <div class="form-group row">
    <label for="CustomerAddress" class="col-md-6 col-form-label formDisplay"><b>Customer Address</b></label>
    <div class="col-sm-6 md-6">
    <textarea class="form-control" id="CustomerAddress" rows="3" disabled><%= cd.getAddress() %></textarea>
        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">
    View on map
  </button>
    </div>
  </div>
</div>
<hr/>

<div class = col-md-6>
 <form name="CommitDefectform" method="post" action="commitDefect?defectname=<%= defect_name %>&passv=<%= "commit"%>">
  <% if(flag==null)
            {%>
  <div class="control-group form-group">
              <div class="controls">
                <label>Proposed Fee</label>
                <input type="text" class="form-control" placeholder="Fee" name="proposedfee" id="proposedfee">
              </div>
            </div>
 <div class="control-group form-group">
              <div class="controls">
                <label>Comments</label>
                <input type="text" class="form-control" placeholder="Comments" name="Comments" id="Comments">
              </div>
            </div>
            <div class="control-group form-group">
              <div class="controls">
  			
            <button type="submit" class="btn btn-success">Commit</button>
            <%} else if(flag.equals("Requested")){ %>
            <button type="submit" class="btn btn-success" disabled>Commit</button>
            <%} else if(flag.equals("Assigned")){%>
             <button type="button" class="btn btn-info" data-toggle="modal" data-target="#closeModal">Close Service Request</button>
            
   
            <%} else if(flag.equals("Declined")){
            	
            	%>
             <div class="control-group form-group">
              <div class="controls">
                <label>Proposed Fee</label>
                <input type="text" class="form-control" placeholder="Fee" name="proposedfee" id="proposedfee">
              </div>
            </div>
 <div class="control-group form-group">
              <div class="controls">
                <label>Comments</label>
                <input type="text" class="form-control" placeholder="Comments" name="Comments" id="Comments">
              </div>
            </div>
            <div class="control-group form-group">
              <div class="controls">
            <button type="submit" class="btn btn-primary">Commit</button>
            <%} %>
  			</div>
  			</div>
 </form>
  			</div>



    <div class="modal" id="closeModal">
  <div class="modal-dialog">
    <div class="modal-content">
<form name="CommitDefectform" method="post" action="commitDefect?defectname=<%= defect_name %>&passv=<%= "close"%>">

      <!-- Modal body -->
      <div class="modal-body">
       <div class="control-group form-group">
              <div class="controls">
                <label>Are you sure you want to close the request??</label>
                
              </div>
            </div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
      <!-- <input id="submit" type="button" value="View Address">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button> -->
         <div class="control-group form-group">
              <div class="controls">
            <button type="submit" class="btn btn-primary" >Close Service Request</button>
             <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
            </div>
            </div>
            </form>
      </div>

    </div>
  </div>
</div>

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