<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Viajes - LogistikApp</title>
    
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
    <script type='text/javascript' src='dwr/interface/UserServiceBean.js'></script>
	<script type='text/javascript' src='dwr/interface/UserNotificationBean.js'></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>    
    
    <!--[if lt IE 9]>
            <script src="js/html5shiv.js"></script>
            <script src="js/respond.min.js"></script>
        <![endif]-->
    <!-- this page specific styles -->
    <style>
      html, body, #map-canvas {
        height: 600px;
        margin: 0px;
        padding: 0px
      }
       .labels{
            color: #000000;
            font-weight: bold;
            font-size: 14px;
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
                                        <li class="active"><span><a href="travellist.htm">Viajes</a></span></li>
                                    </ol>

                                </div>
                            </div>                            
             				<div class="row">
                                <div class="col-lg-6 col-sm-6 col-xs-12">
                                    <div class="main-box infographic-box">
                                        <i class="fa fa-truck emerald-bg box2"></i>
                                        <span class="headline">Viaje</span>
                                        <span class="value value2">${viaje.name}</span>
                                    </div>
                                </div> 
                                <div class="col-lg-6 col-sm-6 col-xs-12">
                                    <div class="main-box infographic-box">
                                        <i class="fa fa-user purple-bg box2"></i>
                                        <span class="headline">Asignado</span>
                                        <span class="value value2">${chofer.username}</span>
                                	</div>
                            	</div>
                           	</div>
                           	<div class="row" id="dtsGenerales">
                           		<div class="col-lg-3 col-sm-6 col-xs-12">
                                    <div class="main-box infographic-box">
                                        <i class="fa fa-calendar yellow-bg box2"></i>
                                        <span class="headline">Programado</span>
                                        <span class="value value2"><fmt:formatDate value="${viaje.schedule}" type="date" pattern="dd.MM"/></span>
                                    </div>
                                </div>	
                                <div class="col-lg-3 col-sm-6 col-xs-12">
                                    <div class="main-box infographic-box">
                                    	<i class="fa fa-map-marker green-bg box2"></i>
                                        <span class="headline">Clientes</span>
                                        <span class="value value2"><span id="clientes"><b>0</b></span></span>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-sm-6 col-xs-12">
                                    <div class="main-box infographic-box">
                                    	<i class="fa fa-road gray-bg box2"></i>
                                        <span class="headline">Distancia </span>
                                        <span><b>0 km</b></span>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-sm-6 col-xs-12">
                                    <div class="main-box infographic-box">
                                    	<i class="fa fa-clock-o gray-bg box2"></i>
                                        <span class="headline">Tiempo </span>
                                        <span><b>0 hrs</b></span>
                                    </div>
                                </div>
                           	</div>                           
                            <div class="row">
                                <div class="col-lg-4" id="listadoClientes">
                                    <div class="main-box clearfix">
                                        <header class="main-box-header clearfix">
                                            <h2>Listado de clientes</h2>
                                        </header>
                                        
                                        <div class="main-box-body clearfix">
                                            <div id="scrollTable">
                                                <table id="table-example" class="table table-striped table-hover table-condensed">
                                                    <thead>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td>Nombre</td>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="waybill" items="${waybills}" varStatus="i">
                                                            <tr>
                                                                <td>
                                                                    <span class="badge badge-badge">${i.index+1}</span>
                                                                </td>
                                                                <td>
                                                                    <span id="wbid${i.index}">${waybill.name}<c:if test="${waybill.shelf == 'S' }"> <i class="fa fa-star" style="color: #f1c40f;" data-toggle="tooltip" data-placement="bottom" title="Promoción"></i></c:if></span>
                                                                    <input type = "hidden" id="lat${i.index}" value="${waybill.latitude}">
                                                                    <input type = "hidden" id="lng${i.index}" value="${waybill.longitude}">
                                                                    <input type = "hidden" id="wbname${i.index}" value="${waybill.name}">
                                                                    <input type = "hidden" id="wbaddress1${i.index}" value="${waybill.address1}">
                                                                    <input type = "hidden" id="wbaddress2${i.index}" value="${waybill.address2}">
                                                                    <input type = "hidden" id="wbpostal${i.index}" value="${waybill.postal}">
                                                                    <input type = "hidden" id="wbshelf${i.index}" value="${waybill.shelf}">
                                                                    <input type = "hidden" class="waybill_ids" id="${waybill.id_waybill}" value="${i.index}">
                                                                </td>
                                                            </tr>
                                                        </c:forEach> 
                                                    </tbody>
                                                </table>
                                            </div>
                                            
                                            <div class="form-group">
	                                            <authz:authorize ifAnyGranted="SUP,SUP1,SUP3,SUP4,SUP5,DRI,LGK" >
	                                                <button type="button" id="cancelButton" name="cancelButton" class="btn btn-default pull-left">
	                                                    <i class="fa fa-times"></i> Cancelar
	                                                </button>
	                                                <button type="button" id="startButton" name="startButton" class="btn btn-primary pull-right">
	                                                    <i class="fa fa-truck"></i> Iniciar 
	                                                </button>
	                                            </authz:authorize>                        
                                            </div>
                                        </div>
                                        
                                    </div>
                                </div>
                                <div class="col-lg-8">
                                    <div class="main-box clearfix no-header">
                                        <div class="main-box-body clearfix">
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
    
    <!-- this page specific inline scripts -->
    <script type="text/javascript">
    $(document).ready(function() {
		var child = $('#table-example tr').length-1;
		$('#clientes').html('<b>'+child+'</b>');
    	$('#distance').html('<b>Cargando...</b>');
		$('#tiempo').html('<b>Cargando...</b>');
        	google.maps.event.addDomListener(window, 'load', initialize);
        	    
        	$('#scrollTable').slimScroll({
        	    	height: '534px',
        	    	start: 'top'
        	    	}); // scrollTable
        	    	    
        }); // ready
  
        $('#cancelButton').click( function() {
        	    location.href = 'travellist.htm';
        	    }); // cancelButton
        	    
        $('#startButton').click( function() {
        	    // Se define una variable del objeto de dominio Travel
        	    // para actualiza el viaje
        	    var Travel = new Object();
        	    Travel.id_travel = "${viaje.id_travel}";
        	    Travel.started = new Date();
        	    Travel.status = "TRA";
        	    // Se persiste el objeto
        	    LogisticServiceBean.updTravelById(Travel,reply);       
        	    }); // startButton
        	    
        	    var reply = function(data) {
        	    	    if ( data >= 1 ) {
        	    	    	    var AddNotificationDTO = new Object();
        	    	    	    AddNotificationDTO.created =new Date();
        	    	    	    AddNotificationDTO.icon = "fa fa-building";
        	    	    	    AddNotificationDTO.id_user = "${useracegi.id_user}";
        	    	    	    AddNotificationDTO.message = "Se inició viaje <span class='label label-primary'>${viaje.name}</span>";
        	    	    	    AddNotificationDTO.priority = "1";
        	    	    	    AddNotificationDTO.id_supplier = "${id_sup}";
        	    	    	    AddNotificationDTO.link = "travelonway.htm?idt=${viaje.id_travel}";
        	    	    	    // Se persiste el objeto
        	    	    	    UserNotificationBean.createNotification(AddNotificationDTO, "003", function(data){
        	    	    	    	var list = [];
        	    	    	    	list.push(${viaje.id_user});
        	    	    	    	UserNotificationBean.createNotificationWithIdNotification(data, list);
        	    	    	    });
        	    	    	    
        	    	    	    // Se redirecciona a la pantalla de checkin
        	    	    	    location.href="travelonway.htm?idt=${viaje.id_travel}";
                    }
                };
        	    
        var map;
        var markers = new Array();
        function initialize() {
        	    var latCenter = document.getElementById("lat0").value;
        	    var lngCenter = document.getElementById("lng0").value;
        	    var mapOptions = {
        	    		zoom: 14,
        	    		center: new google.maps.LatLng(latCenter,lngCenter)
        	        };
        	    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
        	    var locationCoordsArray = [];
            markers = [];
            
            var len = "${fn:length(waybills)}";
            for ( var i=0; i<len; i++ ) {
                var id = "wbid"+i;
                var lat = document.getElementById("lat"+i).value;
                var lng = document.getElementById("lng"+i).value;
                var name = document.getElementById("wbname"+i).value;                
                var address1 = document.getElementById("wbaddress1"+i).value;
                var address2 = document.getElementById("wbaddress2"+i).value;
                var postal = document.getElementById("wbpostal"+i).value;
                var shelf = document.getElementById("wbshelf"+i).value;
                var icon = 'img/'+ ( shelf == 'S' ? 'pin6_blue' : 'icon-marker-blue')+'.png';
                
                locationCoordsArray.push(new google.maps.LatLng(lat,lng));
                var contentString = '<div id="content" style="max-width:150px;">'+
                    '<div id="siteNotice"></div>'+
                    '<span id="firstHeading"><b>'+name+'</b></span>'+
                    '<div id="bodyContent">'+
                    '<p><span>'+address1+'<br/>'+address2+'</span></p>'
                    /*'<p>CP <b>'+postal+'</b></p>'+*/
                    '</div>'+
                    '</div>';
                
                var l = i+1 +"";
                
                var marker = new MarkerWithLabel({
                	    id: id,
                	    ind: i,
                	    position: locationCoordsArray[i],
                	    map: map,
                	    icon: icon,
                	    labelContent: l,
                	    labelAnchor: new google.maps.Point(30, 30),
                	    labelClass: "labels",
                	    labelStyle: {opacity: 1}
                    });
                
                marker.infowindow = new google.maps.InfoWindow({
                	    content: contentString,
                	    maxWidth: 150
               	});
                
                marker.addListener('click', function() { 
                	    this.infowindow.open(map,this);
                });
                
                marker.addListener('mouseout', function() {
                	    this.infowindow.close();
                });
                
                markers.push(marker);                
            }
            obtenerDistancia(locationCoordsArray);
            var lineas = new google.maps.Polyline({
                path: locationCoordsArray,
                 map: map,
                 strokeColor: '#3498db',
                 strokeWeight: 4,
                 strokeOpacity: 0.6,
                 clickable: false
            });
        }; // initialize
        function obtenerDistancia(array){
        	var origins = [];
        	var destinations = [];
        	
        	for (var i = 0; i < (array.length - 1); i++){
        		origins.push(array[i]);
        	}
        	for (var i = 1; i < array.length; i++){
        		destinations.push(array[i]);
        	}
        	while (origins.length > 0){
        		var orgTmp = [];
        		var distTemp = [];
        		for (var i = 0; i < (origins.length >= 10?10:origins.length); i++){
        			orgTmp.push(origins[i]);
        			distTemp.push(destinations[i]);
        		}
        		var service = new google.maps.DistanceMatrixService();
            	service.getDistanceMatrix(
            	  {
            	    origins: orgTmp,
            	    destinations: distTemp,
            	    travelMode: google.maps.TravelMode.DRIVING,
            	    drivingOptions: {
            	        departureTime: new Date(Date.now()),
            	        trafficModel: "pessimistic"
            	    },
            	    unitSystem: google.maps.UnitSystem.METRIC
            	  }, callback);
            	
    			origins.splice(0,(origins.length >= 10?10:origins.length));
    			destinations.splice(0,(origins.length >= 10?10:origins.length));
        	}
        	var km = 0;
        	var time = array.length * ${traveltime} * 60;
        	function callback(response, status) {
        		if (status == google.maps.DistanceMatrixStatus.OK) {
        			console.log(response);
        			for (var i = 0; i < orgTmp.length; i++) {
        				console.log('Fila ' + (i+1) + ' elementos ' + response.rows[i].elements.length);
        				var element = response.rows[i].elements[i];
        			   	var distance = element.distance.text;
        			   	var duration = element.duration.text;
        			   	km += element.distance.value;
        			   	time += element.duration.value;
        			   	console.log('Tiempo ' + time + ' km ' + km);
        			}
        			$('#distance').html('<b>' + (km/1000) + " KMS</b>");
        			$('#tiempo').html('<b>' +  getTime(time) + " Hrs</b>");
        		}
        	}
        	
        	function getTime(x){
        		var hours = parseInt( x / 3600 ) % 24;
        		var minutes = parseInt( x / 60 ) % 60;
        		var seconds = x % 60;

        		return (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes);
        	}
        }
        
        $('#tour').css({
    		display:'block',
    		cursor:'pointer'
    	});

    	$('#tour').on('click', function(){
			var placementRight = 'right';
			var placementLeft = 'left';
			var tour = {
					id: "customer-tour",
					steps: [
						{
							target: "dtsGenerales",
							title: "Información del viaje",
							content: "Aquí se muestra la información general del viaje",
							placement: "bottom",
							xOffset: ($('#dtsGenerales').width() / 2) - 30,
							yOffset: -15
						},
						{
							target: "listadoClientes",
							title: "Listado de clientes",
							content: "Aquí se muestran los clientes pertenecientes a la ruta",
							placement: "top",
							zindex: 999,
							xOffset: ($('#listadoClientes').width() / 2) - 20
						},
						{
							target: "map-canvas",
							title: "Mapa",
							content: "Aquí se mapean los clientes pertenecientes a la ruta",
							placement: "left",
							zindex: 999
						},
						{
							target: "startButton",
							title: "Iniciar",
							content: "Clic para iniciar el viaje",
							placement: "right",
							zindex: 999,
							yOffset: -15
						}
					],
					showPrevButton: true
				};
			hopscotch.startTour(tour);
		});
    </script>

</body>

</html>
