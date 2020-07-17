<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Geolocalizar nuevo cliente - LogistikApp</title>
	
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
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
	
	<!-- Dwr script -->
	<script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	
	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
	
	<style type="text/css">
		.controls{
			margin-top: 10px;
			border: 1px solid transparent;
			border-radius: 2px 0 0 2px;
			box-sizing: border-box;
			-moz-box-sizing: border-box;
			height: 35px;
			outline: none;
			box-shadow: 0 2px 6px rgba(0, 0, 0, .3);
		}
		
		#txt-search{
			margin-top: 12px;
			margin-right: 12px;
			padding: 0 13px 0 13px;
			text-overflow: ellipsis;
		}
		
		@media (max-width: 767px) {
			#txt-search {
				width: 40%;
			}
		}
		@media (min-width: 768px) and (max-width: 991px) {
			#txt-search {
				width: 60%;
			}
		}
		@media (min-width: 991px) and (max-width: 1199px) {
			#txt-search {
				width: 65%;
			}
		}
		@media (min-width: 1200px) {
			#txt-search {
				width: 70%;
			}
		}
		
		#txt-search:focus{
			border-color: #4d90fe;
		}
	</style>
	
	<!--[if lt IE 9]>
			<script src="js/html5shiv.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->
		<!-- <script type="text/javascript">
			var adv = ${advertencia};
    		if(adv)
    			window.location.href = "customer.htm";
		</script> -->
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
                                <h1 class="pull-left">Geolocalizaci&oacute;n del nuevo cliente</h1>
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
	                                        <button disabled="true" id="save" class="btn btn-primary" type="button" title="Enviar información obtenida" data-toggle="tooltip" data-placement="bottom">
	                                        	<i class="fa fa-check"></i> Enviar
	                                        </button>
	                                        <!-- <button id="downloadButton" class="btn btn-primary" type="button" title="Volver" data-toggle="tooltip" data-placement="bottom"  disabled="disabled">
	                                            <i class="fa fa-download"></i>
	                                        </button>
	                                        <button id="shareButton" class="btn btn-primary" type="button" title="Volver" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
	                                            <i class="fa fa-share-alt"></i>
	                                        </button> -->
                                        </div>
                                    </header>
                                    <div class="main-box-body clearfix">
                                    	<input type="text" id="txt-search" class="controls" placeholder="Busqueda">
                                        <div id="map" style="width:100%; height:600px;"></div>
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
	
	<!-- this page specific scripts -->
	
	<script type="text/javascript">
		var map;
		var marker;
		var result = [];
		var lat;
		var lng;
        $(document).ready(function() {   
        	var infowindow = new google.maps.InfoWindow;
        	map = new google.maps.Map(document.getElementById('map'),{
    			center: new google.maps.LatLng(0,0),
    			zoom: 8
    		});
        	var input = document.getElementById('txt-search');
        	var searchBox = new google.maps.places.SearchBox(input);
        	map.controls[google.maps.ControlPosition.TOP_CENTER].push(input);
        	searchBox.addListener('places_changed', function(){
        		searchBox.getPlaces().forEach(function(place){
        			var location = place.geometry.location;
        			console.log(location);
        			initMarker({lat: location.lat(), lng: location.lng()});
        		});
        	});
        		
        	if (navigator.geolocation){
        		navigator.geolocation.getCurrentPosition(function(location){
        			map.setCenter(new google.maps.LatLng(location.coords.latitude, location.coords.longitude));
        			initMarker({lat: location.coords.latitude, lng: location.coords.longitude});
        			getAddress({lat: location.coords.latitude, lng: location.coords.longitude});
        		});
        	}
        	var geocoder = new google.maps.Geocoder();
        	function getAddress(position){
        		console.log(position);
        		lat = position.lat;
        		lng = position.lng;
        		geocoder.geocode({location:position}, function(results, status){
        	       	if (status == google.maps.GeocoderStatus.OK){
        	       		if (results[0]){
        	       			result = results[0].address_components;
        	       			infowindow.setContent(results[0].formatted_address);
        	       			infowindow.open(map, marker);
        	       			$('#save').prop('disabled',false);
        	       		}
        	       	}
        	    });
        	}
        	
        	$('#save').on('click', function(){
        		location.href = "${page}cn=" + ((result[1] == undefined?'':result[1].long_name) + ", " + (result[0] == undefined?'':result[0].long_name)) + "&fr=" + (result[2] == undefined?'':result[2].long_name) + "&mun=" + (result[4] == undefined?'':result[4].long_name) + "&est=" + (result[5] == undefined?'':result[5].long_name) + "&cp=" + (result[7] == undefined?'':result[7].long_name) + "&lat=" + lat + "&lng=" + lng;
        	});
        	
        	google.maps.event.addListener(map, 'click', function(e){
        		console.log(e.latLng);
        		initMarker({lat: e.latLng.lat(), lng: e.latLng.lng()});
        	});
        	
        	function initMarker(location){
        		if (marker == null){
    				marker = new google.maps.Marker({
        				map: map,
        				draggable: true
    				});
        			google.maps.event.addListener(marker,'dragend',function(event) {
        	   	       getAddress({lat: event.latLng.lat(), lng: event.latLng.lng()}); 
        	   	    });
        			google.maps.event.addListener(marker,'drag',function(event) {
         	   	       getAddress({lat: event.latLng.lat(), lng: event.latLng.lng()}); 
         	   	    });
    			}
    			marker.setPosition(location);
    			map.setCenter(marker.getPosition());
    			map.setZoom(18);
    			getAddress({lat: location.lat, lng: location.lng});
        	}
        	
        	$('#backButton').click( function() {
        	    location.href = "${page}";
            });
        });
	</script>
	
</body>
</html>
