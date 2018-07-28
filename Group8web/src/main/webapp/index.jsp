<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>RESolvewithTech(REST)</title>

     <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen" href="./styles/styles.css" />
</head>
<body>

<div id="header"> </div>

<div id="carouselExampleIndicators" class="carousel slide container " data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <!--http://www.wallpapersfans.com/wp-content/uploads/2016/11/WDF_981113.jpg-->
            <img class="d-block w-100" src="./styles/images/Keyboard.jpg" alt="Third slide" style="width: 500px; height: 400px">
        </div>
        <div class="carousel-item">
            <!--http://getwallpapers.com/wallpaper/full/3/c/c/1087099-best-mechanic-wallpapers-2400x1599.jpg-->
            <img class="d-block w-100" src="./styles/images/Toolkits.jpg" alt="First slide" style="width: 500px; height: 400px">
        </div>
        <div class="carousel-item">
            <!--http://wallpaperswide.com/tool_shed-wallpapers.html-->
            <img class="d-block w-100" src="./styles/images/tool_shed-wallpaper-1920x1200.jpg" alt="First slide" style="width: 500px; height: 400px">
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

<div class="container buttons">
    <div class="row">
    
    <%
HttpSession Session = request.getSession(false); 
if(Session!=null && session.getAttribute("cid") != null) 
{	
%>
<div class="col-sm-12 col-xs-6 col-lg-6 col-md-6"><a class="btn btn-md btn-success" href="CustomerPage.jsp">I am a Customer</a></div>
<% } %>
 <%
if(session.getAttribute("cid") == null){%>
        <div class="col-sm-12 col-xs-6 col-lg-6 col-md-6"><a class="btn btn-md btn-success" href="CustomerLogin.jsp">I am a Customer</a></div>
        <% } %>
        <div class="clearfix visible-sm-block"></div>
        <div class="col-sm-12 col-xs-6 col-lg-6 col-md-6"><a class="btn btn-md btn-success" href="#">I am a Technician</a></div>
    </div>
</div>

<div class="container grad-container">
<div class="row">
    <div class="col-sm-6 col-xs-12 col-lg-6 col-md-6">
         <h2>Available Categories</h2>

        <div class="row">
        <div class="col-sm-12 col-xs-12 col-lg-6 col-md-6">
            <!--https://thelogocompany.net/wp-content/uploads/2016/10/main_automobileinc.jpg-->
        <img src="./styles/images/Automobiles.jpg" alt="Automobiles" style="width: 200px; height: 200px">
        </div>

        <div class="col-sm-12 col-xs-12 col-lg-6 col-md-6">
            <!--https://www.brandcrowd.com/gallery/brands/pictures/picture12478191394460.jpg-->
            <img src="./styles/images/Electronics.jpg" alt="Electronics" style="width: 200px; height: 200px">
        </div>
        </div>

        <div class="row">
        <div class="col-sm-12 col-xs-12 col-lg-6 col-md-6">
            <!-- https://st2.depositphotos.com/3573725/8471/v/950/depositphotos_84711776-stock-illustration-furniture-logo.jpg -->
            <img src="./styles/images/Furnitures.jpg" alt="Furnitures" style="width: 200px; height: 200px">
        </div>

        <div class="col-sm-12 col-xs-12 col-lg-6 col-md-6">
            <!--http://www.canadianseniors.com/media/catalog/product/cache/1/image/742x431/9df78eab33525d08d6e5fb8d27136e95/h/o/home-appliance-logo.png-->
            <img src="./styles/images/Home_Appliances.png" alt="Home_Applicances" style="width: 200px; height: 200px">
        </div>
        </div>
        <button class="btn btn-md btn-success buttons">Post Defect</button>
    </div>

    <div class="col-sm-6 col-xs-12 col-lg-6 col-md-6">
        <h2>Technician details</h2>

        <div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <!-- Card image -->
                    <div class="view view-cascade overlay">
                        <!--https://avante.biz/wp-content/uploads/Robert-Downey-Jr-Photo-Wallpapers/Robert-Downey-Jr-Photo-Wallpapers-030.jpg-->
                        <img class="card-img-top" src="./styles/images/Robert%20Downey.jpg" alt="Card image cap">
                        <a href="#!">
                            <div class="mask rgba-white-slight"></div>
                        </a>
                    </div>

                    <!-- Card content -->
                    <div class="card-body card-body-cascade text-center">

                        <!-- Title -->
                        <p class="card-title"><strong>Tony Stark</strong></p>
                        <!-- Subtitle -->
                        <h4 class="blue-text pb-2"><strong>Electronics</strong></h4>
                        <!-- Text -->
                        <h3 class="card-text">Tony is a leading watch mechanic and has solved 24 problems in the last 2 months through our website. </h3>

                        <!-- Linkedin -->
                        <a class="px-2 fa-lg li-ic"><i class="fa fa-linkedin"></i></a>
                        <!-- Twitter -->
                        <a class="px-2 fa-lg tw-ic"><i class="fa fa-twitter"></i></a>
                        <!-- Dribbble -->
                        <a class="px-2 fa-lg fb-ic"><i class="fa fa-facebook"></i></a>

                    </div>
                </div>
                <div class="carousel-item">
                    <!-- Card image -->
                    <div class="view view-cascade overlay">
                        <!--https://stmed.net/wallpaper-59567-->
                        <img class="card-img-top" src="./styles/images/Benedict.jpg" alt="Card image cap">
                        <a href="#!">
                            <div class="mask rgba-white-slight"></div>
                        </a>
                    </div>

                    <!-- Card content -->
                    <div class="card-body card-body-cascade text-center">

                        <!-- Title -->
                        <p class="card-title"><strong>Benedict</strong></p>
                        <!-- Subtitle -->
                        <h4 class="blue-text pb-2"><strong>Automobiles</strong></h4>
                        <!-- Text -->
                        <h3 class="card-text">Dr.Cumberbatch is a mechanical engineer with 30 years experience and rated 5 star by our customers. </h3>

                    </div>
                </div>
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
<script src="bootstrap/js/bootstrap.min.js"></script>
<script>
  $("#header").load("./header.jsp");
</script>
</body>
</html>