<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Geolocalizaci&oacute;n - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />

	<!-- libraries -->
	<link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" href="css/libs/nanoscroller.css" type="text/css" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">
	
	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon"/>

	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>

	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
	<![endif]-->

    <!-- this page specific styles -->
    <style type="text/css">
    /* Set a size for our map container, the Google Map will take up 100% of this container */
    .map-content {
        width: 100%;
        height: 400px;
    }
    </style>
</head>

<body class="fixed-header fixed-leftmenu theme-blue">
    
	<header class="navbar" id="header-navbar">
		<c:import url="/html/menu-top.jsp" />
	</header>
	
    <div id="page-wrapper" class="container">
        <div class="row">
            <div id="nav-col">
                <section id="col-left" class="col-left-nano">
                    <c:import url="/html/menu-left.jsp" />
                </section>
            </div>
            
            <div id="content-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                    
                        <div class="row">
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                     <li><span><spring:message code="label.breadcrumb.logistic"/></span></li>
                                     <li><span><a href="retailcategorylist.htm"><spring:message code="${from_retail ? 'label.breadcrumb.logistic.retailcategory' : 'label.breadcrumb.logistic.storecategory'}"/></a></span></li>
                                     <li class="active"><span><c:out value="${category}"/></span></li>
                                </ol>
									
                                <div class="clearfix">
                                    <h1 class="pull-left"><spring:message code="label.maps.title"/></h1>

                                    <c:set var="url" value="${from_retail ? 'retailcategorylist.htm' : 'storecategorylist.htm'}"/>
                                    <div class="pull-right hidden-xs">
                                        <!-- a href="${from_retail ? 'retailcategorylist.htm' : 'storecategorylist.htm'}" class="btn btn-default"><i class="fa fa-arrow-circle-left fa-lg"></i></a -->
                                        <button type="button" onclick="location.href='${url}';" class="btn btn-default btn-xs"><i class="fa fa-arrow-circle-left fa-lg"></i> Volver</button>
                                    </div>
                                    <div class="pull-right hidden-lg hidden-md hidden-sm">
                                        <button type="button" onclick="location.href='${url}';" class="btn btn-default btn-xs"><i class="fa fa-arrow-circle-left fa-lg"></i></button>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="main-box no-header clearfix">
                                    <div class="main-box-body clearfix">
                                        <div id="map" class="map-content"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
        
        </div>
    </div>

	<!-- global scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.min.js"></script>
	
	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	
	<!-- this page specific inline scripts -->
    <script type="text/javascript">
        // When the window has finished loading create our google map below
        google.maps.event.addDomListener(window, 'load', init);
        
        function init() {
            var latitude = "<c:out value='${storesLatitude}'/>".split("|");
            var longitude = "<c:out value='${storesLongitude}'/>".split("|");
            // Se construye listado de coordenadas
            var locationCoordsArray = new Array();
            for ( var i=0; i<latitude.length; i++ ) {
                locationCoordsArray[i] = new google.maps.LatLng(latitude[i],longitude[i]);
            }
            
            // Se construye lista de nombres de clientes
            var locationNameArray = "<c:out value='${storesName}'/>".split("|");
            
            var mapOptions = {
            		center: locationCoordsArray[0],
            		zoom: 12
            		};
            
            var map = new google.maps.Map(document.getElementById("map"),  mapOptions);

            var coord;
            for ( coord in locationCoordsArray ) {
                new google.maps.Marker({
                    position: locationCoordsArray[coord],
                    map: map,
                    title: locationNameArray[coord]
                });
            }
        };
    </script>

</body>

</html>
