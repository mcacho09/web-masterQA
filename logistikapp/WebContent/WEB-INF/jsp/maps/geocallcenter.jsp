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
	<link rel="stylesheet" href="css/libs/datepicker.css" type="text/css" />
	<link rel="stylesheet" href="css/libs/daterangepicker.css" type="text/css" />
	<link rel="stylesheet" href="css/libs/bootstrap-timepicker.css" type="text/css" />
    
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
                                        <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                        <li><a href="callcenter.htm">Call Center</a></li>
                                        <li class="active">Agregar cliente a viaje</li>
                                    </ol>
                                    
                                    <div class="clearfix">
                                        <h1 class="pull-left"><spring:message code="label.maps.title"/></h1>
                                    </div>
                                    
                                </div>
                            </div>

                            <div class="row">
                            	
                                <c:forEach var="tienda" items="${tiendas}" varStatus="i">
                                    <input type = "hidden" id="id_str${i.index}" value="${tienda.id_store}">
                                    <input type = "hidden" id="lat${i.index}" value="${tienda.latitude}">
                                    <input type = "hidden" id="lng${i.index}" value="${tienda.longitude}">
                                    <input type = "hidden" id="name${i.index}" value="${tienda.name}">
                                    <input type = "hidden" id="address1${i.index}" value="${tienda.address1}">
                                    <input type = "hidden" id="address2${i.index}" value="${tienda.address2}">
                                    <input type = "hidden" id="postal${i.index}" value="${tienda.postal}">
                                	<input type = "hidden" id="checkin${i.index}" value="${tienda.checkin}">                                                               
                                	<input type = "hidden" id="travel_name${i.index}" value="${tienda.travel_name}">                                                               
                                </c:forEach>
                                <div class="col-lg-12">
                                    <div class="main-box clearfix ">
	                                    <header class="main-box-header clearfix">
											<div class="row">
												<div class="pull-left">
		                                        	<form action="callcenter.htm" method="post" id="form_volver">
		                                        		<input type="hidden" value="${dir_name}" id="dir_name" name="dir_name" />
		                                        		<input type="hidden" value="${busqueda}" id="busqueda" name="busqueda" />
			                                        	<button type="submit" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Volver</button>
		                                        	</form>
		                                        </div>
												<form method="post" id="form_tra" >
													<input type="hidden" name="id_store" id="id_store" value="${id_store}" />
													<input type="hidden" name="lat" id="lat" value="${lat_center}" />
													<input type="hidden" name="lon" id="lon" value="${lon_center}" />
													<input type="hidden" name="nombre_str" id="nombre_str" value="${nombre_str}" />
													<input type="hidden" name="dir1" id="dir1" value="${dir1}" />
													<input type="hidden" name="dir2" id="dir2" value="${dir2}" />
													<input type="hidden" name="postal" id="postal" value="${postal}" />
													
													<input type="hidden" id="nocheck" name="nocheck" />
													<input type="hidden" id="newstr" name="newstr"/>
													<input type="hidden" name="dis1" id="dis1"/>
													<input type="hidden" name="dis2" id="dis2"/>
													
													<div class="pull-left col-lg-4">
														<select class="form-control" name="travel_sel" id="travel_sel"  onchange="cambiar();" ${returnAdd == 'true' ? 'style=\"display: none;\"' : ''} >
															<c:if test="${fn:length(list_travel)==1}">
																<option value="">Viaje</option>
															</c:if>
															<c:forEach var="tra" items="${list_travel}">
																<option value="${tra.id_travel}" ${tra.id_travel == id_travel ? 'selected' : ''} > <fmt:formatDate value="${tra.schedule}" pattern="dd/MM/yyyy"/><c:out value=" - ${tra.username}"/></option>
															</c:forEach>
														</select>
													</div>
													<div class="col-ln-2 pull-left">
													<c:if test="${fn:length(list_orderby)>0}"> 
														<div class="radio-nice pull-left" ${returnAdd == 'true' ? 'style=\"display: none;\"' : ''} >
															<!-- 
															<input name="group" id="proximo" type="radio" value="proximo"${gruop == 'proximo' ? 'checked' : ''} />
															<label for="proximo">&nbsp;proximo</label>
															 -->
															<input name="group" id="final" type="radio" value="final" ${gruop == 'final' ? 'checked' : ''} />
															<label for="final">&nbsp;Final</label>
															<input name="group" id="manual" type="radio" value="manual" ${gruop == 'manual' ? 'checked' : ''} />
															<label for="final">&nbsp;Manual</label>
														</div> &nbsp;&nbsp;
														<select class="pull-right" id="orden" name="orden" style="display:none;">
															
															<option value=""> - Orden - </option>
															<c:forEach var="ord" items="${list_orderby}" begin="0" >
																<option value="${ord}"><c:out value="${ord}"/></option>
															</c:forEach>
														</select>
													</c:if>
													</div>
													<div class="pull-right" style="display:none;" id="agrega" >
		                                                 <button id="toolbars-new" class="btn btn-primary" type="button" title="Agregar" data-toggle="tooltip" onclick="#">
		                                                     <i class="fa fa-plus-circle"></i><span class="hidden-xs">&nbsp;Agregar</span>
		                                                 </button>
													</div>
												</form>
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
                        </div><!-- fin -->
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
	<script src="js/bootstrap-timepicker.min.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
    <script src="js/gmap.js"></script>
    
    <!-- this page specific inline scripts -->
    <script>
    
	    $(document).ready(function() {
	            initialize()
	            showDRIPosition()
	    });
   
        var map;
        var markers = new Array();
        function initialize() {           
           	var latCtr = "${lat_center}";
           	var lonCtr = "${lon_center}";

            var latCenter = latCtr;
            var lngCenter = lonCtr;
            
            map = map = GMaps({
            	el: "#map-canvas",
            	zoom: 15,
            	center: new google.maps.LatLng(latCenter,lngCenter)
            })
            var locationCoordsArray = new Array();
           
            var seprdr = "";
            for ( var i=0; i<${fn:length(tiendas)}; i++ ) {              
                
                var id_str = document.getElementById("id_str"+i).value;
                var lat = document.getElementById("lat"+i).value;
                var lng = document.getElementById("lng"+i).value;
                var name = document.getElementById("name"+i).value;
                var address1 = document.getElementById("address1"+i).value;
                var address2 = document.getElementById("address2"+i).value;
                var postal = document.getElementById("postal"+i).value;
                var checkin = document.getElementById("checkin"+i).value;
                var travel_name = document.getElementById("travel_name"+i).value;
                
                if(checkin==''){
                	var noc = document.getElementById("nocheck").value; 
                	document.getElementById("nocheck").value = noc+seprdr+id_str+"_"+lat+"&"+lng;
                	seprdr = ";";
                }
                var str_id = "${id_store}";
                locationCoordsArray.push(new google.maps.LatLng(lat,lng));
                var contentString = '<div id="content" style="max-width:150px;">'+
                '<div id="siteNotice" class="alert alert-info">'+
                	travel_name+
                '</div>'+
                '<h5 id="firstHeading"><b>'+name+'</b></h5>'+
                '<div id="bodyContent">'+
                '<p><b></b> '+address1+','+address2+
                '<p><b>'+postal+'</b></p>'+
                '</div>'+
                '</div>';
                var l = i+1 +"";
                var icono = 'img/icon-marker-red.png';
                if (checkin != null && checkin != "")
                	icono = 'img/icon-marker-green.png';
                if (checkin != null && checkin != "")
                	icono = 'img/icon-marker-green.png';
                if(str_id==id_str)
                    icono = 'img/icon-marker-blue.png';
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
                markers.push(marker);             
            } // for ( var i=0; i<${fn:length(tiendas)}; i++ )
            	
       		if(latCtr!=""){
                var id_store = "${id_store}";
                var lat = "${lat_center}";
                var lng = "${lon_center}";
                var name = "${nombre_str}";
                var address1 = "${dir1}";
                var address2 = "${dir2}";
                var postal = "${postal}";
                
                document.getElementById("newstr").value=id_store+"_"+lat+"&"+lng;
                
                locationCoordsArray.push(new google.maps.LatLng(lat,lng));
                var contentString = '<div id="content" style="max-width:150px;">'+
                '<div id="siteNotice">'+
                '</div>'+
                '<h5 id="firstHeading"><b>'+name+'</b></h5>'+
                '<div id="bodyContent">'+
                '<p><b></b> '+address1+','+address2+
                '<p><b>'+postal+'</b></p>'+
                '</div>'+
                '</div>';
                var l = i+1 +"";
                var icono = 'img/icon-marker-blue.png';
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
                markers.push(marker);             
       		}
            
            // activate Nestable for list 1
            var updateOutput = function(e){
                
                var list   = e.length ? e : $(e.target),
                    output = list.data('output');
                if (output != null)
                if (window.JSON) {
                    output.val(window.JSON.stringify(list.nestable('serialize')));//, null, 2));
                } 
                else {
                    output.val('JSON browser support required for this demo.');
                }
                var cont = 1;
                $("input.waybill_ids").each(function(index,e){
                    
                    var id = $(this).attr("id");
                    
                    var i = document.getElementById(id).value;
                        
                });
            };   
            
            $('#nestable').nestable({
                group: 1
            })
            .on('change', updateOutput);
          
            // output initial serialised data
            updateOutput($('#nestable').data('output', $('#nestable-output')));
            
        };
        
        function cambiar(){
        	var valor = document.getElementById("travel_sel").value;
        	if(valor!="")
        		document.getElementById("acomodo").style.display="block";
        	else
        		document.getElementById("acomodo").style.display="none";
        }
        
		//datepicker
		var today = new Date();
		$('#datepickerDate').datepicker({
		  format: 'dd-mm-yyyy',
		  minDate: today
		});
		
		$("#travel_sel").change(function(){
			document.getElementById("form_tra").submit();
		});

		var ini_ds = 0;
		var fin_ds = 0;
		var coo_lat_start = "";
		var coo_lng_start = "";
        $("#proximo").click(function(){
        	var nochk = []; // separa "idStr_lat&lng"
        	var nochk_string = document.getElementById("nocheck").value;
        	var nochkById = []; //separa nochkById[0]=id_store      nochkById[1]=lat&lng
        	var new_str = (document.getElementById("newstr").value).split("_");
        	
        	var coor_newstr = new_str[1].split("&");//tiene las coordenadas de la tienda nueva lat=0  lng=1
        	var coor_nochk = [];//tiene las coordenadas de cada tienda que ya existe lat=0  lng=1
        	var coor_nochk2 = [];//tiene las coordenadas de cada tienda que ya existe lat=0  lng=1
        	nochk = nochk_string.split(";"); 
       		var coor = [];
   	        document.getElementById("dis1").value = "";
   	        document.getElementById("dis1").value = "";

        		//obtiene distancios hacia las tiendas que ya estan definidas en el viaje
        	for(var i=0; i<nochk.length-1; i++){
        		var coor_pre = nochk[i].split("_");//separa id de local y coordenadas "123_lat&lon"
        		
        		coor = coor_pre[1].split("&");
        		coo_lat_start = coor[0]; // latitude
        		coo_lng_start = coor[1]; // longitude
        		
        		coor_pre = nochk[i+1].split("_");
        		coor = coor_pre[1].split("&");
        		var coo_lat_end = coor[0];
        		var coo_lng_end = coor[1];
        		
        		var origin1 = new google.maps.LatLng(coo_lat_start, coo_lng_start);
        		var destinationA = new google.maps.LatLng(coo_lat_end, coo_lng_end);
        		var service = new google.maps.DistanceMatrixService();
        		service.getDistanceMatrix(
        		  {
        		    origins: [origin1],
        		    destinations: [destinationA],
        		    travelMode: google.maps.TravelMode.DRIVING,
        		    durationInTraffic: false,
        		    avoidHighways: false,
        		    avoidTolls: false,
        		  }, callback);
        	} // for(var i=0; i<nochk.length; i++)
        		
        	//obtiene distancios hacia la tienda que se quiere agregar al viaje
        	for(var i=0; i<nochk.length-1; i++){
        		var coor_pre = nochk[i].split("_");//separa id de local y coordenadas "123_lat&lon"
        		
        		coor = coor_pre[1].split("&");
        		coo_lat_start = coor[0]; // latitude
        		coo_lng_start = coor[1]; // longitude
        		
        		var nueva_coor = document.getElementById("newstr").value;
        		coor_pre = nueva_coor.split("_");
        		coor = coor_pre[1].split("&");
        		var coo_lat_end = coor[0];
        		var coo_lng_end = coor[1];
        		
        		var origin1 = new google.maps.LatLng(coo_lat_start, coo_lng_start);
        		var destinationA = new google.maps.LatLng(coo_lat_end, coo_lng_end);
        		var service = new google.maps.DistanceMatrixService();
        		service.getDistanceMatrix(
        		  {
        		    origins: [origin1],
        		    destinations: [destinationA],
        		    travelMode: google.maps.TravelMode.DRIVING,
        		    durationInTraffic: false,
        		    avoidHighways: false,
        		    avoidTolls: false,
        		  }, callback2);
        	} // for(var i=0; i<nochk.length; i++)
        	$("#agrega").show()
        }); //$("#proximo").click
        
        $("#final").click(function(){
        	$("#agrega").show();
        	document.getElementById("orden").selectedIndex = "0";
        	$("#orden").hide()
        });
        
        $("#toolbars-new").click(function(){
        	if (($('#manual').is(':checked') && $('#orden option:selected').index() > 0) || $('#manual').is(':checked') == false){
	        	document.getElementById("form_tra").action = "callcenteradm.htm";
	        	document.getElementById("form_tra").submit();
        	}else{
        		swal('Alerta','Seleccione una opción', 'warning');
        	}
        });
        $("#manual").click(function(){
        	$("#agrega").show()
        	$("#orden").show()
        });

        function callback(response, status) {
        	var dist1 = "";
       	  if (status == google.maps.DistanceMatrixStatus.OK) {
       	    var origins = response.originAddresses;
       	    var destinations = response.destinationAddresses;

       	    for (var i = 0; i < origins.length; i++) {
       	      var results = response.rows[i].elements;
       	      for (var j = 0; j < results.length; j++) {
       	        var element = results[j];
       	        var distance = element.distance.text;
       	        var duration = element.duration.text;
       	        var from = origins[i];
       	        var to = destinations[j];
       	        dist1 = document.getElementById("dis1").value;
       	        document.getElementById("dis1").value = dist1 + "/" + distance +"-"+duration; 
       	      }
       	    }
       	  }else
       		  alert("status="+status);
       	}        
        
        function callback2(response, status) {
        	var dist1 = "";
       	  if (status == google.maps.DistanceMatrixStatus.OK) {
       	    var origins = response.originAddresses;
       	    var destinations = response.destinationAddresses;

       	    for (var i = 0; i < origins.length; i++) {
       	      var results = response.rows[i].elements;
       	      for (var j = 0; j < results.length; j++) {
       	        var element = results[j];
       	        var distance = element.distance.text;
       	        var duration = element.duration.text;
       	        var from = origins[i];
       	        var to = destinations[j];
       	        dist1 = document.getElementById("dis2").value;
       	        document.getElementById("dis2").value = dist1 + "/" + distance; 
       	      }
       	    }
       	  }else
       		  alert("status="+status);
       	}        
        
        
        function computeTotalDistance1(result) {
	       	var total = 0;
	       	var myroute = result.routes[0];
	       	var concat_dis1 = document.getElementById("dis1").value;

	       	for (var i = 0; i < myroute.legs.length; i++) {
		       	total += myroute.legs[i].distance.value;
	       	}
       		document.getElementById("dis1").value = concat_dis1+"/"+total;
       	}
        
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