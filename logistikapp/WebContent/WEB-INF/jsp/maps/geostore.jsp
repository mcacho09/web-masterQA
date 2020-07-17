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
    
	<!-- Dwr script -->
	<script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>
	<script type='text/javascript' src='dwr/interface/UserServiceBean.js'></script>
	<script type='text/javascript' src='dwr/interface/UserNotificationBean.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
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
                                     <li><span><a href="customer.htm">Clientes</a></span></li>
                                     <li><span><a href="store.htm?id=${retail.id_retail}"><c:out value="${retail.name}"/></a></span></li>
                                     <li class="active"><span>Geolocalizaci&oacute;n</span></li>
                                </ol>
                                <h1 class="pull-left">${store.name}</h1>
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
                                        </div>
                                        <!--div class="btn-group">
                                            <button id="downloadButton" class="btn btn-primary" type="button" title="Volver" data-toggle="tooltip" data-placement="bottom"  disabled="disabled">
                                                <i class="fa fa-download"></i>
                                            </button>
                                            <button id="shareButton" class="btn btn-primary" type="button" title="Volver" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                <i class="fa fa-share-alt"></i>
                                            </button>
                                        </div-->
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
	
    <script type="text/javascript">
        $(document).ready( function() {
        	
            $('#backButton').click( function() {
                window.location.href = "store.htm?id=${retail.id_retail}";
                }); // backButton
        	
            }); // ready
    
        // When the window has finished loading create our google map below
        google.maps.event.addDomListener(window, 'load', init);
        
        function init() {
            var latitude = "${store.latitude}";
            var longitude = "${store.longitude}";
            
            var myLatlng = new google.maps.LatLng(latitude,longitude);

            var contentString = '<div id="content" style="max-width:150px;">'+
            '<div id="siteNotice"></div>'+
            '<span id="firstHeading"><b>${store.name}</b></span>'+
            '<div id="bodyContent">'+
            '<p>${store.address1}<br/> ${store.address2}</p>'+
            /*'<p>CP <b>${store.postal}</b></p>'+*/
            '</div>'+
            '</div>';
            
            var mapOptions = {
            		center: myLatlng,
            		zoom: 16
            		};
            
            var map = new google.maps.Map(document.getElementById("map"),  mapOptions);

            var infowindow = new google.maps.InfoWindow({
                content: contentString
            });
            var shelf = "${store.shelf}";
            var icon = (shelf == 'S' ? 'img/pin6_red.png' : 'img/icon-marker-red.png');
            var marker = new google.maps.Marker({
                position: myLatlng,
                map: map,
                icon: icon
            });

            google.maps.event.addListener(marker, 'mouseover', function() {
                infowindow.open(map,marker);
            });
            
            google.maps.event.addListener(map, "click", function(event) {
                var lat = event.latLng.lat();
                var lng = event.latLng.lng();
                //alert("Lat=" + lat + "; Lng=" + lng);
                if( confirm("¿Actualizar coordenadas para ${store.name}?\n"+lat+" ... "+lng) ) {
                	   var updStoreDTO = new Object();
                	   updStoreDTO.id_store = "${store.id_store}"*1;
                	   updStoreDTO.latitude = lat;
                	   updStoreDTO.longitude = lng;
                	   RetailServiceBean.updStore(updStoreDTO, reply1);
                }
            });
            
        };
        
        var reply1 = function(data) {
        	// Se setea el dto para agregar una nueva notificacion
			var AddNotificationDTO = new Object();
			AddNotificationDTO.created = new Date();
			AddNotificationDTO.icon = "fa fa-map-marker notification";
			AddNotificationDTO.id_user = "${useracegi.id_user}";
			AddNotificationDTO.message = "Nuevas coordenadas <span class='label label-warning'>${store.name}</span>";
			AddNotificationDTO.priority = "1";
			AddNotificationDTO.id_supplier = "${id_suplier}";
			// Se persiste el objeto
			UserNotificationBean.createNotification(AddNotificationDTO, "001", reply2);
        };
        var reply2 = function(data) {
            location.href = "geostore.htm?ret=${store.id_retail}&sto=${store.id_store}";        	
        };
    </script>

</body>
</html>