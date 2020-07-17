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
        height: 600px;
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
                                    <li><a href="home.htm">Home</a></li>
                                     <li class="active"><span><a href="customer.htm">Clientes</a></span></li>
                                </ol>
                                <h1 class="pull-left">Geolocalizaci&oacute;n de Plazas Comerciales</h1>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">
                                    <header class="main-box-header clearfix">
                                        <div class="btn-group">
	                                        <button id="backButton" class="btn btn-primary" type="button" title="Volver" data-toggle="tooltip" data-placement="bottom">
	                                            <i class="fa fa-arrow-left"></i> Volver
	                                        </button>
	                                        <button id="downloadButton" class="btn btn-primary" type="button" title="Volver" data-toggle="tooltip" data-placement="bottom"  disabled="disabled">
	                                            <i class="fa fa-download"></i>
	                                        </button>
	                                        <button id="shareButton" class="btn btn-primary" type="button" title="Volver" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
	                                            <i class="fa fa-share-alt"></i>
	                                        </button>
                                        </div>
                                    </header>
                                    <div class="main-box-body clearfix">
                                        <div id="map" class="map-content"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
                
                    <footer id="footer-bar" class="row">
                        <c:import url="/html/footer.html" />
                    </footer>
                </div>
        
        </div>
    </div>

	<!-- global scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.min.js"></script>
	
    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
    <script src="js/pace.min.js"></script>
	
	<!-- this page specific inline scripts -->
    <script src="js/markerwithlabel.js" type="text/javascript"></script>

    <script type="text/javascript">
        // When the window has finished loading create our google map below
        google.maps.event.addDomListener(window, 'load', init);
        
        function init() {
            var latitude = "${storesLatitude}".split("|");
            var longitude = "${storesLongitude}".split("|");
            var address1 = "${storesAddress1}".split('|');
            var address2 = "${storesAddress2}".split('|');
            var shelfs = "${storesShelf}".split('|');
            // Se construye listado de coordenadas
            var locationCoordsArray = new Array();
            for ( var i=0; i<latitude.length; i++ ) {
                locationCoordsArray[i] = new google.maps.LatLng(latitude[i],longitude[i]);
            }
            
            // Se construye lista de nombres de clientes
            var name = "${storesName}".split("|");
            
            var mapOptions = {
            	center: locationCoordsArray[0],
            	zoom: 8
            };
            
            var map = new google.maps.Map(document.getElementById("map"),  mapOptions);

            for  (var i=0; i < latitude.length; i++){
				var shelf = shelfs[i];

                var icono = ( shelf == 'S' ? 'img/pin6_red.png' : 'img/icon-marker-red.png');//Agregado de validacion con campo Shelf
                
                var contentString = '<div id="content" style="max-width:150px;">'+
                '<div id="siteNotice"></div>'+
                '<span id="firstHeading"><b>'+name[i]+'</b></span>'+
                '<div id="bodyContent">'+
                '<p><span>'+address1[i]+'<br/>'+address2[i]+'</span></p>'
                /*'<p><b>'+postal+'</b></p>'+*/
                '</div>'+
                '</div>';
                marker = new MarkerWithLabel({
					id: i,
					ind: i,
                    position: new google.maps.LatLng(latitude[i],longitude[i]),
                    map: map,
                    icon: icono,
                    labelAnchor: new google.maps.Point(30, 31),
                    labelClass: "labels", // the CSS class for the label
                    labelStyle: {opacity: 1}
                });
                marker.infowindow = new google.maps.InfoWindow({
                    content: contentString,
                    maxWidth: 150
                    
                });
                
                marker.addListener('mouseout', function() {
                    this.infowindow.close();
                });
                marker.addListener('click', function() {
                	this.infowindow.open(map,this);
                    
                });
			}
        };

        $(document).ready( function() {
        	
            $('#backButton').click( function() {
            	    window.location.href = "customer.htm";
                });
            
        }); // ready
        
        /*$('#tour').css({
    		display:'block',
    		cursor:'pointer'
    	});*/

    	$('#tour').on('click', function(){
			var placementRight = 'right';
			var placementLeft = 'left';
			var tour = {
					id: "customer-tour",
					steps: [
						{
							target: "backButton",
							title: "",
							content: "",
							placement: "bottom",
							xOffset: -15
						},
						{
							target: 'downloadButton',
							title: "",
							content: "",
							placement: "bottom",
							zindex: 999,
							xOffset: -15
						},
						{
							target: 'shareButton',
							title: "",
							content: "",
							placement: "bottom",
							zindex: 999,
							xOffset: -15
						},
						{
							target: 'map',
							title: "",
							content: "",
							placement: "top",
							zindex: 999
						}
					],
					showPrevButton: true
				};
			hopscotch.startTour(tour);
		});
    </script>

</body>
</html>