<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Rutas - LogistikApp</title>
    
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
    
    <!-- libraries -->
    <link type="text/css" rel="stylesheet" href="css/libs/font-awesome.css"/>
    <link type="text/css" rel="stylesheet" href="css/libs/nanoscroller.css"/>
    
    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">
    <link rel="stylesheet" type="text/css" href="css/libs/jquery.datetimepicker.css"/>
    
    <!-- Favicon -->
    <link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
    
    <!-- google font libraries -->
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
    
    <!--  Dwr scripts -->
    <script type='text/javascript' src='dwr/interface/LogisticServiceBean.js'></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>    
    
    <!--[if lt IE 9]>
            <script src="js/html5shiv.js"></script>
            <script src="js/respond.min.js"></script>
        <![endif]-->
    <!-- this page specific styles -->
    <style>
      html, body, #map-canvas {
        height: 500px;
        margin: 0px;
        padding: 0px
      }
       .labels{
            color: #000000;
            font-weight: bold;
            font-size: 12px;
            opacity: 1;
            pointer-events: none;
            text-align: center;
            width: 60px;
            white-space: nowrap;
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
                    <c:import url="/html/menu-left.jsp"/>
                </section>
            </div>

                <div id="content-wrapper">
                    <div class="row">
                        <div class="col-lg-12">

                            <div class="row">
                                <div class="col-lg-12">
                                    <ol class="breadcrumb hidden-xs">
                                        <li><a href="home.htm">Home</a></li>
                                        <li><span>Log&iacute;stica</span></li>
                                        <li class="active"><span>Geolocalizaci&oacute;n</span></li>
                                    </ol>
                                    
                                    <div class="clearfix">
                                        <h1 class="pull-left">${viaje_name}</h1>
                                    </div>

                                </div>
                            </div>

                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="main-box clearfix ">
	                                    <header class="main-box-header clearfix">
	                                        <div class="pull-left">
	                                        	<button type="button" onclick="location.href='${volver}';" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Volver</button>                                        	
	                                       </div>
    	                                </header>
                                        <div class="main-box-body clearfix">
                                        	<div class="row">
												<div class="col-xs-12">
													<span class="label label-danger"><i class="fa fa-map-marker"></i> Sin visitar</span>
													<span class="label label-success"><i class="fa fa-map-marker"></i> Visitado</span>
													<span class="label label-warning"><i class="fa fa-map-marker"></i> Fuera de rango</span>
													<div class="pull-right">
														<span class="label label-default"><i class="fa fa-male"></i> Inicio</span>
														<span class="label label-warning"><i class="fa fa-male"></i> Recorrido</span>
														<span class="label label-primary"><i class="fa fa-male"></i> Posición actual</span>
													</div>
												</div>
											</div>
											<br />
                                            <div id="map-canvas"></div>
                                        </div>
                                    </div>
                                </div>
                                
                            </div><!-- /row -->
                        </div>
                    </div>

                   <footer id="footer-bar" class="row">
                        <c:import url="/html/footer.html" />
                    </footer>
                    
                </div>
            </div>
        </div>

    <!-- global scripts -->
    <script src="js/demo.js"></script> <!-- only for demo -->    
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.nanoscroller.min.js"></script>
    
    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
    <script src="js/pace.min.js"></script>
    
     <!-- datetimepicker </body> -->
    <script src="js/jquery.datetimepicker.js"></script>
    
    <!-- this page specific scripts -->
    <script src="js/markerwithlabel.js"></script>
    <script src="js/jquery.nestable.js"></script>
    <script src="js/jquery.slimscroll.min.js"></script>
    <script src="js/gmap.js"></script>
    
    <!-- this page specific inline scripts -->
    <script>
	    $(document).on('ready',function() {
	            initialize()
	            showDRIPosition()
	    });
   
        var map;
        var markers = [];
        function initialize() {           
            
            var latCenter = 0;
            var lngCenter = 0;
            map = GMaps({
            	el: "#map-canvas",
            	zoom: 14,
            	center: new google.maps.LatLng(latCenter,lngCenter)
            })
            var locationCoordsArray = [];
            
            LogisticServiceBean.getWayBillByIdTravel("${id_travel}", function(data){
            	data.forEach(function(e, i) {
            		
            		var lat = e.latitude;
                    var lng = e.longitude;
                    var name = e.name;
                    var address1 = e.address1;
                    var address2 = e.address2;
                    var postal = e.postal;
                    var checkin = e.checkin || 'N';
                    var travel_name = e.travel_name;
                    var shelf = e.shelf || 'N';
                    
                    locationCoordsArray.push(new google.maps.LatLng(lat,lng));
                    var contentString = '<div id="content">'+
                    '<div id="siteNotice" class="alert alert-info">'+
                    	travel_name+
                    '</div>'+
                    '<h5 id="firstHeading"><b>'+name+'</b></h5>'+
                    '<div id="bodyContent">'+
                    '<p>'+address1+','+address2+
                    '<p>'+postal+'</p>'+
                    '</div>'+
                    '</div>';
                    var l = e.orderby + "";
                    var icono = (shelf == 'S' ? 'img/pin6_red.png' : 'img/icon-marker-red.png');
                    if (checkin && checkin != "")
                    	icono = (shelf == 'S' ? 'img/pin6_green.png' : 'img/icon-marker-green.png');
            		
            		var marker = map.addMarker({                   
	                    lat: lat,
	                    lng: lng,
	                    icon: icono,
	                    labelAnchor: new google.maps.Point(50, 51),
	                    labelClass: "labels", // the CSS class for the label
	                    labelStyle: {opacity: 1},
	                    title: 'Mi posición actual',
	                    label: l,
	                    labelAnchor: new google.maps.Point(30, 31),
	                    labelClass: "labels", // the CSS class for the label
	                    labelStyle: {opacity: 1},
	    				infoWindow: {
	    				  content: contentString
	    				}
	                })
	                
	                markers.push(marker)
            	})

            	if (data) {
	            	var positions = data[0]
	            	map.map.setCenter(new google.maps.LatLng(positions.latitude, positions.longitude))
	            }
            })
            
            
        };
        
        function showDRIPosition() {
        	Concurrent.Thread.create(function() {
        		LogisticServiceBean.getUserPositionByIdTravel("${id_travel}", function(data) { 
        			
        			if (data.length == 0) return
        			
        			if (data.length == 1) {
        				var marker = map.addMarker({
        					lat: data[0].latitude,
        					lng: data[0].longitude,
        		            icon: 'img/driver_icon_last' + '.png',
        		            labelAnchor: new google.maps.Point(50, 51),
        		            labelClass: "labels", // the CSS class for the label
        		            labelStyle: {opacity: 1},
        					infoWindow: {
        						content: '<div id="content">'+
        							'<div id="bodyContent">'+
        						    '<p><span>Registrado: <b>' + data[0].created.toLocaleDateString() + ' ' + data[0].created.toLocaleTimeString() + '</b></span></p>' +
        						    '</div>'+
        						    '</div>'
        					}
        				}) 
        				
        			} else if (data.length == 2) {
        				var marker = map.addMarker({
        					lat: data[0].latitude,
        					lng: data[0].longitude,
        		            icon: 'img/driver_icon_init' + '.png',
        		            labelAnchor: new google.maps.Point(50, 51),
        		            labelClass: "labels", // the CSS class for the label
        		            labelStyle: {opacity: 1},
        					infoWindow: {
        						content: '<div id="content">'+
        							'<div id="bodyContent">'+
        						    '<p><span>Registrado: <b>' + data[0].created.toLocaleDateString() + ' ' + data[0].created.toLocaleTimeString() + '</b></span></p>' +
        						    '</div>'+
        						    '</div>'
        					}
        				}) 
        				var marker = map.addMarker({
        					lat: data[1].latitude,
        					lng: data[1].longitude,
        		            icon: 'img/driver_icon_last' + '.png',
        		            labelAnchor: new google.maps.Point(50, 51),
        		            labelClass: "labels", // the CSS class for the label
        		            labelStyle: {opacity: 1},
        					infoWindow: {
        						content: '<div id="content">'+
        							'<div id="bodyContent">'+
        						    '<p><span>Registrado: <b>' + data[1].created.toLocaleDateString() + ' ' + data[1].created.toLocaleTimeString() + '</b></span></p>' +
        						    '</div>'+
        						    '</div>'
        					}
        				}) 
        				
        				map.drawRoute({
    	    			  origin: [data[0].latitude, data[0].longitude],
    	    			  destination: [data[1].latitude, data[1].longitude],
    	    			  travelMode: 'driving',
    	    			  strokeColor: '#3498db',
    	    			  strokeOpacity: 0.6,
    	    			  strokeWeight: 6
    	    			})
        			} else {
        			    			
    	    			for (var i = 0, size = data.length - 2; i <= size; i++) {
    	    				var route = map.drawRoute({
    	    				  origin: [data[i].latitude, data[i].longitude],
    	    				  destination: [data[i + 1].latitude, data[i + 1].longitude],
    	    				  travelMode: 'driving',
    	    				  strokeColor: '#3498db',
    	    				  strokeOpacity: 0.6,
    	    				  strokeWeight: 6
    	    				})
    	    				    				
    	    				var marker = map.addMarker({
    	    					lat: data[i].latitude,
    	    					lng: data[i].longitude,
    	    		            icon: 'img/driver_icon_' + (i == 0 ? 'init' : 'travel') + '.png',
    	    		            labelAnchor: new google.maps.Point(50, 51),
    	    		            labelClass: "labels", // the CSS class for the label
    	    		            labelStyle: {opacity: 1},
    	    					infoWindow: {
    	    						content: '<div id="content">'+
    	    							'<div id="bodyContent">'+
    	    						    '<p><span>Registrado: <b>' + data[i].created.toLocaleDateString() + ' ' + data[i].created.toLocaleTimeString() + '</b></span></p>' +
    	    						    '</div>'+
    	    						    '</div>'
    	    					}
    	    				})    				
    	    			}
    	    			
    	    			map.addMarker({
    	    				lat: data[data.length - 1].latitude,
        					lng: data[data.length - 1].longitude,
    			            icon: 'img/driver_icon_last.png',
    			            labelAnchor: new google.maps.Point(50, 51),
    			            labelClass: "labels", // the CSS class for the label
    			            labelStyle: {opacity: 1},
    						infoWindow: {
    							content: '<div id="content">'+
    								'<div id="bodyContent">'+
    							    '<p><span>Registrado: <b>' + data[data.length - 1].created.toLocaleDateString() + ' ' + data[data.length - 1].created.toLocaleTimeString() + '</b></span></p>' +
    							    '</div>'+
    							    '</div>'
    						}
    					})
        			}
        			
        		})
        	})
        }
        
    </script>
</body>
</html>