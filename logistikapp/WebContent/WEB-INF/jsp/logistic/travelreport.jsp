<%@ include file="/includes/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Reporte De Viajes - LogistikApp</title>

<!-- bootstrap -->
<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">

<!-- libraries -->
<link type="text/css" rel="stylesheet" href="css/libs/font-awesome.css" />
<link type="text/css" rel="stylesheet" href="css/libs/nanoscroller.css" />

<!-- global styles -->
<link rel="stylesheet" type="text/css"
	href="css/compiled/theme_styles.css">

<!-- this page specific styles -->
<link rel="stylesheet" type="text/css" href="css/logistikapp.css">
<link rel="stylesheet" type="text/css"
	href="css/libs/jquery.datetimepicker.css" />

<!-- Favicon -->
<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />

<!-- google font libraries -->
<link
	href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400'
	rel='stylesheet' type='text/css'>

<!-- Sweetalert -->
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">

<!--  Dwr scripts -->
<script type='text/javascript' src='dwr/interface/LogisticServiceBean.js'></script>
<script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>
<script type='text/javascript' src='dwr/interface/UserServiceBean.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<!--[if lt IE 9]>
            <script src="js/html5shiv.js"></script>
            <script src="js/respond.min.js"></script>
        <![endif]-->
<!-- this page specific styles -->
<style type="text/css">
#map-canvas, #map-canvas-real {
	height: 69vh;
	margin: 0px;
	padding: 0px
}

.labels {
	color: #000000;
	font-weight: bold;
	font-size: 12px;
	opacity: 1;
	pointer-events: none;
	text-align: center;
	width: 60px;
	white-space: nowrap;
}

.btn-custom-circle {
	padding: 7px 7px;
	border-bottom: 0px solid;
	border-radius: 50% !important;
	font-size: 13px;
	line-height: 0;
}

.table tbody > tr > td{
	padding: 12px 0!important;
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
								<ol class="breadcrumb hidden-xs">
									<li><a href="home.htm">Home</a></li>
									<li><span>Log&iacute;stica</span></li>
									<li class="active"><span><a href="travelhistorylist.htm"><spring:message
													code="label.breadcrumb.logistic.travel.history" /></a></span></li>
								</ol>

								<div class="clearfix hidden-xs">
									<h1 class="pull-left">Reporte de Viajes</h1>
								</div>
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
							<div class="col-lg-4 col-sm-6 col-md-6 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-calendar yellow-bg box2"></i>
									<span class="headline">Programado</span>
									<span class="value value2"><fmt:formatDate value="${viaje.schedule}" type="date" pattern="dd/MM/yyyy" /></span>
								</div>
							</div>
							<div class="col-lg-4 col-sm-6 col-md-6 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-calendar green-bg box2"></i>
									<span class="headline">Iniciado</span>
									<span class="value value2"><c:if test="${viaje.started==null}" ><span>00:00</span></c:if><fmt:formatDate value="${viaje.started}" type="date" pattern="dd/MM HH:mm" /></span>
								</div>
							</div>
							<div class="col-lg-4 col-sm-12 col-md-12 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-calendar green-bg box2"></i>
									<span class="headline">Finalizado</span>
									<span class="value value2"><c:if test="${viaje.finished==null}" ><span>00:00</span></c:if><fmt:formatDate value="${viaje.finished}" type="date" pattern="dd/MM HH:mm" /></span>
								</div>
							</div>
							
						</div>

						<!-- Tabla de reporte -->
						<div class="row">
							<div class="col-lg-12" id="table-check">
								<div class="main-box clearfix no-header">
									<div class="main-box-body clearfix">
									<authz:authorize ifNotGranted="DRI,DRI1,DRI2,DRI3,DRI4,DRI5">
									<a class="btn btn-sm btn-primary" href="travelreportdoc.htm?idt=${id_travel }&dol=${useracegi.profile}">Descargar reporte <i class="fa fa-download"></i></a>
									</authz:authorize>
										<div id="scrollTable" class="table-responsive">
											<table id="table-example" class="table table-striped table-hover">
												<thead>
													<tr>
														<th>#</th>
														<th>Cliente</th>
														<th class="text-center" id="col-checkin">Checkin</th>
														<th id="col-estado">Estado</th>
														<c:if test="${supplier.shelf =='S'}">
														<!-- <th>Promoción</th> --> 
														</c:if>
														<c:if test="${fn:contains(useracegi.profile,'DRI') == false}">
														<th style="text-align:center;">Ubicar</th>
														</c:if>
														<th style="text-align:center;">Comentarios</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="waybill" items="${waybills}" varStatus="i">

														<!-- <input type="hidden" id="id${i.index}" value="${waybill.id_waybill}" />
														<input type="hidden" id="id_store${i.index}" value="${waybill.id_store}" />
														<input type="hidden" id="lat${i.index}" value="${waybill.latitude}" />
														<input type="hidden" id="lng${i.index}" value="${waybill.longitude}" />
														<input type="hidden" id="lon_real${i.index}" value="${waybill.lon_real}" />
														<input type="hidden" id="lat_real${i.index}" value="${waybill.lat_real}" />
														<input type="hidden" id="wbname${i.index}" value="${waybill.name}" />
														<input type="hidden" id="wbaddress1${i.index}" value="${waybill.address1}" />
														<input type="hidden" id="wbaddress2${i.index}" value="${waybill.address2}" />
														<input type="hidden" id="wbpostal${i.index}" value="${waybill.postal}" />
														<input type="hidden" id="check${i.index}" value="${waybill.checkin}" />
														<input type="hidden" id="shelf${i.index}" value="${waybill.shelf}" />
														<input type="hidden" class="waybill_ids" id="${waybill.id_waybill}" />
														<input type="hidden" id="outrange${i.index}" value="${waybill.outrange}"/> -->

														<tr>
															<td class="item" 
																data-id="${waybill.id_waybill}" 
																data-idstore="${waybill.id_store}" 
																data-lat="${waybill.latitude}" 
																data-lng="${waybill.longitude}"
																data-lonreal="${waybill.lon_real}" 
																data-latreal="${waybill.lat_real}" 
																data-wbname="${waybill.name}" 
																data-wbaddress1="${waybill.address1}" 
																data-wbaddress2="${waybill.address2}" 
																data-wbpostal="${waybill.postal}" 
																data-check="${waybill.checkin}" 
																data-shelf="${waybill.shelf}" 
																data-waybillid="${waybill.id_waybill}"
																data-outrange="${waybill.outrange}" 
																data-status="${waybill.status }" 
															><span class="badge badge-default">${i.index+1}</span></td>

															<td>${waybill.name}<c:if test="${waybill.shelf == 'S' }"><span class="fa fa-star" style="color: #f1c40f;" data-toggle="tooltip" data-placement="bottom" title="Promoción"></i></span></c:if></td>

															<td class="text-center">
																<c:set var="now" value="<%=new java.util.Date()%>" />
																<span style="display: none" class="text-primary"><fmt:formatDate
																		value="${now}" type="date" pattern="dd/MM HH:mm" /></span> <c:choose>
																	<c:when test="${waybill.status != 'N'}">
																		<span class="text-primary"><fmt:formatDate
																				value="${waybill.checkin}" type="date"
																				pattern="dd/MM HH:mm" /></span>
																	</c:when>
																</c:choose><br>
																<c:if test="${waybill.outrange == 'S'}">
																	<span class="label label-warning"><i	class="fa fa-times"></i> Fuera Rango</span>
																</c:if>
																<c:if test="${waybill.outrange != 'S' && waybill.status == 'S'}">
																	<span class="label label-success"><i	class="fa fa-check"></i> Correcto</span>
																</c:if>
															</td>
															<td><c:if test="${waybill.status == 'S'}">
																	<span class="label label-success"><i class="fa fa-check"></i> Visitada</span>
																</c:if> 
																<c:if test="${waybill.status == 'N'}">
																	<span class="label label-danger"><i	class="fa fa-times"></i> No Visitada</span>
																</c:if>
															</td>
															<!--<c:if test="${supplier.shelf =='S'}">
															<td>
																<c:if test="${waybill.shelf == 'S'}">
																	<span class="label label-success"><i	class="fa fa-check"></i></span>
																</c:if>
															</td>
															</c:if>-->
															<td style="text-align:center;">
																<button class="btn btn-info btn-xs btn-custom-circle seemarker" data-toggle="tooltip" data-placement="bottom" title="Ubicar" data-index="${i.index}"><i class="fa fa-eye"></i></button>
															</td>
                                                            <!-- <td style="text-align:center;"><c:if test="${waybill.comment != null && waybill.comment != '' }"><button type="button" data-comment="${waybill.comment}" class="btn btn-sm btn-primary btn-custom-circle comment"><i class="fa fa-comment"></i></button></c:if></td> -->
                                                            <td style="text-align:center;">
																<button data-id="${waybill.id_waybill}" data-comment="${waybill.comment }" data-note="${waybill.note }" class="btn btn-${((waybill.comment != null && waybill.comment != '') || (waybill.note != null && waybill.note != '') || (waybill.image1 != null && waybill.image1 != '') || (waybill.image2 != null && waybill.image2 != '') || (waybill.image3 != null && waybill.image3 != ''))?'success':'default'} btn btn-sm btn-primary btn-custom-circle comment" data-name="${waybill.name }" data-toggle="tooltip" data-placement="bottom" title="Comentario" data-index="${waybill.comment}">
																<i class="fa fa-comment"></i></button>
															</td>
														</tr>	
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
							<!-- Termino de tabla de reporte -->

						<!-- Mapas -->
						<authz:authorize ifNotGranted="DRI,DRI1,DRI2,DRI3,DRI4,DRI5">
						<div class="row">
							<div class="col-lg-12">
								<div class="main-box infographic-box">
                                    <i class="fa fa-road gray-bg box2"></i>
                                    <span class="headline">Distancia Programada</span>
                                    <span><b>0 km</b></span>
                                </div>
							</div>
							<div class="col-lg-12">
								<div class="main-box clearfix no-header">
									<div class="main-box-body clearfix">
										<h1 class="text-center">Ruta Programada</h1>
										<div class="row">
											<div class="col-xs-12">
												<span class="label label-danger"><i class="fa fa-map-marker"></i> Sin visitar</span>
												<span class="label label-success"><i class="fa fa-map-marker"></i> Visitado</span>
												<span class="label label-warning"><i class="fa fa-map-marker"></i> Fuera de rango</span>
											</div>
										</div>
										<br />
										<div id="map-canvas"></div>
									</div>
								</div>
							</div>
							
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div class="main-box infographic-box">
                                    	<i class="fa fa-road gray-bg box2"></i>
                                        <span class="headline">Distancia Real</span>
                                        <span><b>0 km</b></span>
                                    </div>
							</div>
							<div class="col-lg-12">
								<div class="main-box clearfix no-header">
									<div class="main-box-body clearfix">
										<h1 class="text-center">Ruta Real</h1>
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
										<div id="map-canvas-real"></div>
									</div>
								</div>
							</div>
						</div>
						</authz:authorize>
						<!-- /row -->

					</div>
				</div>

				<footer id="footer-bar" class="row">
					<c:import url="/html/footer.html" />
				</footer>
				<div class="modal fade" id="modal-comments">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header bg-primary">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="comment-title"></h4>
							</div>
							<div class="modal-body">
								<div class="row hidden">
									<div class="col-xs-12">
										<button type="button" class="btn btn-primary btn-md pull-right" data-toggle="tooltip" data-placement="left" title="Adjuntar imagen"><i class="fa fa-camera"></i></button>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-horizontal">
												<div class="form-group">
													<label for="textareaComment" class="col-sm-2 control-label">Movimientos:</label>
													<div class="col-sm-9">
														<textarea maxlength="255" name="comment" id="tcomment" class="form-control" style="resize:none;" rows="3" required="required" required="required" ${'disabled' }></textarea>
													</div>
												</div>
											<div class="form-group">
												<label for="textareaNote" class="col-sm-2 control-label">Comentarios:</label>
												<div class="col-sm-9">
													<textarea maxlength="255" name="note" id="tnote" class="form-control" style="resize:none;" rows="3" required="required" ${'disabled' }></textarea>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-4 imgcontent" style="padding:0!important;">
										<img class="img-responsive" id="imgCont1" src="img/img1.jpg" style="width:100%; height:250px;">
									</div>
									<div class="col-lg-4 imgcontent" style="padding:0!important;">
										<img class="img-responsive" id="imgCont2" src="img/img2.jpg" style="width:100%; height:250px;">
									</div>
									<div class="col-lg-4 imgcontent" style="padding:0!important;">
										<img class="img-responsive" id="imgCont3" src="img/img3.jpg" style="width:100%; height:250px;">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" id="closemodal" data-dismiss="modal">Cerrar</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- global scripts -->
	<script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/bootstrap.js" type="text/javascript"></script>
	<script src="js/jquery.nanoscroller.min.js" type="text/javascript"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js" type="text/javascript"></script>
	<script src="js/pace.min.js" type="text/javascript"></script>

	<!-- datetimepicker -->
	<script src="js/jquery.datetimepicker.js" type="text/javascript"></script>

	<!-- this page specific scripts -->
	<script src="js/markerwithlabel.js" type="text/javascript"></script>
	<script src="js/sweetalert.min.js" type="text/javascript"></script>
	<script src="js/jquery.nestable.js" type="text/javascript"></script>
	<script src="js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="js/jquery.dataTables.js"></script>
    <script src="js/jquery.dataTables.bootstrap.js"></script>
    <script src="js/gmap.js"></script>
	
	<!-- this page specific inline scripts -->
	<script type="text/javascript">
        $(document).ready(function() {
        	console.log("${viaje.km}");
        	$('#distance').html('<b>' + (${viaje.km == null?0:viaje.km}/1000) + " KMS</b>");
        	    //google.maps.event.addDomListener(window, 'load', initialize);
        	    //google.maps.event.addDomListener(window, 'load', initialize_map_real);
            
        	   /* $('#scrollTable').slimScroll({
        	    	    height: 'auto',
        	    	    start: 'top'
        	    	    }); // scrollTable
        	    */
        	    	    
        	    	    var table = $('#table-example').dataTable( {
        	    	    	responsive: true,
        	                'paging': false,
        	                'info': false,
        	                'searching': false,
        	                "language": {
        	                    "search": "Buscar Cliente:",
        	                    "zeroRecords":    "No se encontraron clientes"
        	                },            
        	                'pageLength': 100,
        	                "aoColumnDefs" : [{
        	                    "bSortable" : true,
        	                    }],
        	                }); // table
        	                
        		$('.seemarker').on('click', function(){
        			
        			map.markers.forEach(function(e, i){
	        			e.infoWindow.close()
	        		})
        			
        			map_real.markers.forEach(function(e, i){
        				e.infoWindow.close()
        			})
        			
        			markers[$(this).data('index')].infoWindow.open(map, markers[$(this).data('index')]);
        			markers_real[$(this).data('index')].infoWindow.open(map_real, markers_real[$(this).data('index')]);
        			
        			map.map.setCenter(locationCoordsArray[$(this).data('index')]);
        			map_real.map.setCenter(locationRealCoordsArray[$(this).data('index')]);
        		});
        	                
            }); // ready
   
        var map;
        var markers = [];
        var myLat = 0;
    	var myLng = 0;
        
        	//console.log($('#time').data('time'));
        	var latCenter = $($('.item')[0]).data('lat');
            var lngCenter = $($('.item')[0]).data('lng');
        	
            
            var mapOptions = {
            		el: "#map-canvas",
            		zoom: 12,
            		center: new google.maps.LatLng(latCenter,lngCenter)
                };
            map = new GMaps(mapOptions);
            
            var locationCoordsArray = new Array();
           	var size = $('.item').length;
            for ( var i=0; i<size; i++ ) {              
                var id = $($('.item')[i]).data('id');	
                var lat = $($('.item')[i]).data("lat");
                var lng = $($('.item')[i]).data("lng");
                var name = $($('.item')[i]).data("wbname");
                
                var address1 = $($('.item')[i]).data("wbaddress1");
                var address2 = $($('.item')[i]).data("wbaddress2");
                var postal = $($('.item')[i]).data("wbpostal");
                var checkin = $($('.item')[i]).data("check");
                var outrange = $($('.item')[i]).data("outrange");
                var shelf = $($('.item')[i]).data("shelf");
                var status = $($('.item')[i]).data("status");
                console.log("Fuera de rango", outrange)
                console.log("Shelf", shelf)
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
                var icono = shelf == 'S' ? 'img/pin6_green.png' :'img/icon-marker-green.png'
                
                var marker = map.addMarker({                   
                    id: id,
                    ind: i,
                    position: locationCoordsArray[i],
                    icon: icono, 
                    label: l,
                    labelAnchor: new google.maps.Point(30, 31),
                    labelClass: "labels", // the CSS class for the label
                    labelStyle: {opacity: 1},
                    infoWindow : {
                    	content: contentString
                    }
                });
                
                /*marker.infowindow = new google.maps.InfoWindow({
                    content: contentString,
                    maxWidth: 150
                    
                });
                
                marker.addListener('mouseout', function() {
                    this.infowindow.close();
                });
                marker.addListener('click', function() {
                	this.infowindow.open(map,this);
                    
                });*/

                markers.push(marker);               
            }
            /*var lineas = new google.maps.Polyline({
                path: locationCoordsArray,
                 map: map,
                 strokeColor: '#3498db',
                 strokeWeight: 4,
                 strokeOpacity: 0.6,
                 clickable: false
            });*/
        
        
        function obtenerDistancia(data){
        	var array = [];
        	data.forEach(function(e, i) {
        		array.push(new google.maps.LatLng(e.latitude, e.longitude))
        	})
        	
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
        			for (var i = 0; i < orgTmp.length; i++) {
        				var element = response.rows[i].elements[i];
        			   	var distance = element.distance.text;
        			   	var duration = element.duration.text;
        			   	km += element.distance.value;
        			   	time += element.duration.value;
        			}
        			$('#distance-real').html('<b>' + (km/1000) + " KMS</b>");
        			console.log("Real " + km);
        		} // status
        	} // callback
        	
        	
        }
        function getTime(x){
    		var hours = parseInt( x / 3600 ) % 24;
    		var minutes = parseInt( x / 60 ) % 60;
    		var seconds = x % 60;

    		return (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes);
    	} // gettime
    	
    	//Comienza Mapa 2

        var map_real;
        var markers_real = [];
        var myLat_real = 0;
        var myLng_real = 0;
        var id;
        var id_storee;
        var lat_reall;
        var lon_reall;
        	
        
            latCenter = $($('.item')[0]).data('lat');
            lngCenter = $($('.item')[0]).data('lng');
            
            var mapOptionsReal = {
            	el: "#map-canvas-real",
            	zoom: 12,
            	center: new google.maps.LatLng(latCenter,lngCenter)
            };
            
       	 	map_real = GMaps(mapOptionsReal);
            
            var locationRealCoordsArray = new Array();
            var locationRealCoordsNoArray = new Array();
           	var lons_real = ${fn:length(waybills)};
           	var ind = -1;
           	var size = $('.item').length;
            for ( var i=0; i< size; i++ ) {              
                id = $($('.item')[i]).data('id');	
                id_storee = $($('.item')[i]).data('idstore');
                lon_reall = ($($('.item')[i]).data('lonreal') == "" ? $($('.item')[i]).data('lng') : $($('.item')[i]).data('lonreal'));
               	lat_reall = ($($('.item')[i]).data('latreal') == "" ? $($('.item')[i]).data('lat') : $($('.item')[i]).data('latreal'));
               	//console.log("\nNORMAL: "+document.getElementById("lat"+i).value+"-->REAL "+document.getElementById("lat_real"+i).value);
               	
                var name = $($('.item')[i]).data('wbname');
                
                var address1 = $($('.item')[i]).data("wbaddress1");
                var address2 = $($('.item')[i]).data("wbaddress2");
                var postal = $($('.item')[i]).data("wbpostal");
                var checkin = $($('.item')[i]).data("check");
                var outrange = $($('.item')[i]).data("outrange");
                var status = $($('.item')[i]).data("status");
                locationRealCoordsArray.push(new google.maps.LatLng(lat_reall,lon_reall));
                
                if($($('.item')[i]).data("latreal") != ""){
                	locationRealCoordsNoArray.push(new google.maps.LatLng(lat_reall,lon_reall));
                }
                var contentString = '<div id="content">'+
                '<div id="siteNotice"></div>'+
                '<span id="firstHeading"><b>'+name+'</b></span>'+
                '<div id="bodyContent">'+
                '<p><span>'+address1+'<br/>'+address2+'</span></p>';
                if($($('.item')[i]).data("latreal"+i) != "")
                	contentString += "<a href=\"javascript:pruebas("+id+","+id_storee+","+lat_reall+","+lon_reall+",'"+name+"');\">Actualizar</a>";
                 
                contentString += '</div>'+ '</div>';
       
                var l = i+1 +"";
                var shelf = $($('.item')[i]).data("shelf");
                console.log("Shelf 2", shelf)
                console.log("Outrange 2", outrange)
                var icono = ''
                if (shelf == 'S') {
                   	if (status == 'S') {
                   		if (outrange == 'S') {
                   			icono = 'img/pin6_yellow.png'
                   		} else {
                   			icono = 'img/pin6_green.png'
                   		}
                   	} else {
                   		icono = 'img/pin6_red.png'
                   	}
                } else {
                   	if (status == 'S') {
                   		if (outrange == 'S') {
                   			icono = 'img/icon-marker-yellow.png'
                   		} else {
                   			icono = 'img/icon-marker-green.png'
                   		}
                   	} else {
                   		icono = 'img/icon-marker-red.png'
                   	}
                }
                
                console.log("Icono 2", icono)

                var markerreal = map_real.addMarker({
                    id: id,
                    ind: i,
                    position: new google.maps.LatLng(lat_reall,lon_reall),
                    icon: icono, 
                    label: l,
                    labelAnchor: new google.maps.Point(30, 31),
                    labelClass: "labels", // the CSS class for the label
                    labelStyle: {opacity: 1},
                    infoWindow : {
                    	content : contentString
                    }
                });
                
                /*marker.infowindow = new google.maps.InfoWindow({
                    content: contentString,
                    maxWidth: 150
                    
                });
                
                
                marker.addListener('click', function() {
                	this.infowindow.open(map_real,this);
                    
                });*/

                markers_real.push(markerreal);               
            }
            
            
            /*var lineas = new google.maps.Polyline({
                path: locationRealCoordsNoArray,
                 map: map_real,
                 strokeColor: '#3498db',
                 strokeWeight: 4,
                 strokeOpacity: 0.6,
                 clickable: false
            });*/

        function pruebas(id,id_storee,lat_reall,lon_reall,name){
        	console.log("Waybill: "+id+" Id Store: "+ id_storee+" Latitud: "+lat_reall+" Longitud: "+lon_reall+" Nombre: "+name);
        	swal({
                title: "Actualizar las coordenadas?",
                text: "Se actualizaran las coordenadas originales",
                type: "info",
                showCancelButton: true,
                confirmButtonClass: "btn-info",
                confirmButtonText: "Si, Actualizar!",
                cancelButtonText: "No, Cancelar!",
                closeOnConfirm: false,
                closeOnCancel: false
        	}, function(isConfirm) {
	        	if (isConfirm) {
	        		swal({
	        			title: "Actualizado!",
	                    text: "La posición se ha actualizado correctamente",
	                    type: "success",
	                    showCancelButton: false,
	                    confirmButtonClass: "btn-info",
	                    confirmButtonText: "OK!",
	                    closeOnConfirm: false,
	                    closeOnCancel: false
	                },function(isConfirm) {
	                	if (isConfirm) {
	                    	var updStoreDTO = new Object();
	                        updStoreDTO.id_store = id_storee;
	                        updStoreDTO.latitude = lat_reall;
	                        updStoreDTO.longitude = lon_reall;
	                        RetailServiceBean.updStore(updStoreDTO);
	                                     	   
	                        var WayBill = new Object();
	                        WayBill.id_waybill = id;
	                        WayBill.outrange = "N";
	                        LogisticServiceBean.updWayBillById(WayBill);
	                        // Se setea el dto para agregar una nueva notificacion
	                        var AddNotificationDTO = new Object();
	                        AddNotificationDTO.created = new Date();
	                        AddNotificationDTO.icon = "fa fa-map-marker notification";
	                        AddNotificationDTO.id_user = "${useracegi.id_user}";
	                        AddNotificationDTO.message = "Nuevas coordenadas <span class='label label-warning'>"+name+"</span>";
	                        AddNotificationDTO.priority = "1";
	                        AddNotificationDTO.id_supplier = "${id_sup}";
	                        // Se persiste el objeto
	                        UserServiceBean.addNotification(AddNotificationDTO, reply2);
	                    } 
	                });
	        	} else {
	        		swal("Cancelado", "No se ha modificado ninguna información", "error");
	        	}
	        });
        }
        
        var reply2 = function(data) {
        	 location.reload(true);        	
        };
        
        var actual_store = 0;
        $('.comment').on('click', function(){
        	$('#modal-comments').modal('show');
        	actual_store = $(this).data('id');
	        $('#comment-title').text($(this).data('name'));
        	LogisticServiceBean.getWayBillById(actual_store, function(d){
	        	$('#tcomment').val(d.comment);
	        	$('#tnote').val(d.note);
	        	$('#imgCont1').attr('src', d.image1!=null?d.image1:'img/img_default.jpg');
	        	$('#imgCont2').attr('src', d.image2!=null?d.image2:'img/img_default.jpg');
	        	$('#imgCont3').attr('src', d.image3!=null?d.image3:'img/img_default.jpg');
        	});
        });
        
        Concurrent.Thread.create(function() {
    		LogisticServiceBean.getUserPositionByIdTravel(${id_travel}, function(data) { 
    			
    			if (data.length == 0) return
    			
    			if (data.length == 1) {
    				var marker = map_real.addMarker({
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
    				var marker = map_real.addMarker({
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
    				var marker = map_real.addMarker({
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
    				
    				map_real.drawRoute({
	    			  origin: [data[0].latitude, data[0].longitude],
	    			  destination: [data[1].latitude, data[1].longitude],
	    			  travelMode: 'driving',
	    			  strokeColor: '#3498db',
	    			  strokeOpacity: 0.6,
	    			  strokeWeight: 6
	    			})
    			} else {
    			    			
	    			for (var i = 0, size = data.length - 2; i <= size; i++) {
	    				var route = map_real.drawRoute({
	    				  origin: [data[i].latitude, data[i].longitude],
	    				  destination: [data[i + 1].latitude, data[i + 1].longitude],
	    				  travelMode: 'driving',
	    				  strokeColor: '#3498db',
	    				  strokeOpacity: 0.6,
	    				  strokeWeight: 6
	    				})
	    				    				
	    				var marker = map_real.addMarker({
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
	    			
	    			map_real.addMarker({
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
    			
    			obtenerDistancia(data);
    			
    		})
    	})
   	
    </script>

</body>

</html>