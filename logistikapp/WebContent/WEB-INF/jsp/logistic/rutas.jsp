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
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.fixedHeader.css">
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.tableTools.css">
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">
	
	<!-- Dwr script -->
	<script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	
	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
	
	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
	
	<!--[if lt IE 9]>
			<script src="js/html5shiv.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->
    <!-- this page specific styles -->
    <style>
      html, body, #map {
        height: 73vh;
        margin: 0px;
        padding: 0px
      }
    </style>
</head>

<body class="fixed-header fixed-leftmenu theme-blue">

	<header class="navbar" id="header-navbar">
		<c:import url="/html/menu-top.jsp" />
	</header>
	<!-- Cargar coordenadas de todos los locales -->
	<input type="hidden" id="listCoords" name="listCoords"/>
	<c:set var="coords" value=""/>
	<c:set var="separador" value=""/>
	<input type="hidden" id="ids" name="ids" value=""/>
	
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
                                        <li class="active"><span> Log&iacute;stica </span></li>
									</ol>
									
									<div class="clearfix">
										<h1 class="pull-left">Rutas</h1>
									</div>

								</div>
							</div>

							<div class="row">
								<div class="col-lg-4" >
									<div class="main-box clearfix">
                                        <header class="main-box-header clearfix">
                                        	<!-- BBARRA DE BOTONES -->
                                            <div id="header-tools" class="pull-left">
                                                <div class="btn-group">
                                                    <button id="checkAllButton" class="btn btn-primary" type="button" title="Seleccionar" data-toggle="tooltip" data-placement="bottom">
                                                        <i class="fa fa-square-o"></i>
                                                    </button>
                                                </div>
                                                <div class="btn-group">
		                                            <button onclick="location.href='rutasadd.htm?accion=add';" class="btn btn-primary" id="addBtn" ><i class="fa fa-plus"></i> Nuevo</button>      
		                                            <button class="btn btn-primary" type="button" id="delBtn" title="Eliminar" data-toggle="tooltip" data-placement="bottom" onclick="javascript:eliminar()" disabled>
		                                                <i class="fa fa-trash-o"></i>
		                                            </button>
                                                </div>
                                            </div>
											<!-- h2 class="pull-left">Rutas Por Categor&iacute;­a Plaza</h2-->
                                        </header>
										<div class="main-box-body clearfix" style="overflow:auto; height:70vh; border-top: 1px solid lightblue;">
											<div class="table-responsive" >
												<form action="routedel.htm" method="post" id="form_del" >
													<input type="hidden" id="idsRoute" name="idsRoute"/>
												</form>
												<table id="tableRoute" class="table table-striped table-hover">
													<thead>
														<tr>
															<th>Nombre Ruta</th>
															<th class="text-center"><span>Clientes</span></th>
														</tr>
													</thead>
													<tbody id="dataTable">
														<c:forEach var="ruta" items="${List}" varStatus="vst">
															<tr>
																<td>
																	<div class="checkbox-nice">
																	    <input type="checkbox" id="${ruta.id_route}" value="${ruta.color}"/>
																	    <label for="${ruta.id_route}"><a href="rutasupd.htm?accion=upd&id_route=${ruta.id_route}">${ruta.name}</a></label>
																	</div>
																</td>
                                                            	<td class="text-center">
                                                            		<c:if test="${ruta.color == 'red'}">
                                                            			<span class="badge badge-danger">${fn:length(ruta.store)} <i class="fa fa-map-marker"></i></span>
                                                					</c:if>
                                                            		<c:if test="${ruta.color == 'blue'}">
                                                            			<span class="badge badge-primary">${fn:length(ruta.store)} <i class="fa fa-map-marker"></i></span>
                                                					</c:if>
                                                            		<c:if test="${ruta.color == 'yellow'}">
                                                            			<span class="badge badge-warning">${fn:length(ruta.store)} <i class="fa fa-map-marker"></i></span>
                                                					</c:if>
                                                            		<c:if test="${ruta.color == 'green'}">
                                                            			<span class="badge badge-success">${fn:length(ruta.store)} <i class="fa fa-map-marker"></i></span>
                                                					</c:if>
                                                            		<c:if test="${ruta.color == 'purple'}">
                                                            			<span class="badge badge-info">${fn:length(ruta.store)} <i class="fa fa-map-marker"></i></span>
                                                					</c:if>
                                                					<c:if test="${ruta.color == 'beige'}">
                                                            			<span class="badge" style="color:black; background-color:beige">${fn:length(ruta.store)} <i class="fa fa-map-marker"></i></span>
                                                					</c:if>
                                                					<c:if test="${ruta.color == 'aqua'}">
                                                            			<span class="badge" style="color:black; background-color:aqua">${fn:length(ruta.store)} <i class="fa fa-map-marker"></i></span>
                                                					</c:if>
                                                					<c:if test="${ruta.color == 'darkcyan'}">
                                                            			<span class="badge" style="background-color:darkcyan">${fn:length(ruta.store)} <i class="fa fa-map-marker"></i></span>
                                                					</c:if>
                                                					<c:if test="${ruta.color == 'peru'}">
                                                            			<span class="badge" style="background-color:peru">${fn:length(ruta.store)} <i class="fa fa-map-marker"></i></span>
                                                					</c:if>
                                                					<c:if test="${ruta.color == 'orange'}">
                                                            			<span class="badge" style="background-color:orange">${fn:length(ruta.store)} <i class="fa fa-map-marker"></i></span>
                                                					</c:if>
                                                					<c:if test="${ruta.color == 'pink'}">
                                                            			<span class="badge" style="background-color:pink">${fn:length(ruta.store)} <i class="fa fa-map-marker"></i></span>
                                                					</c:if>
                                                            	</td>
															</tr>
															<c:forEach var="sto" items="${ruta.store}">
																<c:set var="coords" value="${coords}${separador}${sto.id_store}_${sto.latitude}_${sto.longitude}_${ruta.color}"/>
																<c:set var="separador" value="@"/>
															</c:forEach>
															<script>
																document.getElementById("listCoords").value = "${coords}";
															</script>
														</c:forEach>
													
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
                        		
							    <div class="col-lg-8">
                                    <div class="main-box clearfix no-header">
                                        <div class="main-box-body clearfix">
                                            <div id="map"></div>
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
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.nanoscroller.min.js"></script>
    <script src="js/jquery.slimscroll.min.js"></script>
    
    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
    
    <!-- this page specific inline scripts -->
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/dataTables.fixedHeader.js"></script>
    <script src="js/dataTables.tableTools.js"></script>
        
    <script>
    
	    $(document).ready(function(){
			// datatable no seleccionados
	        var table = $('#tableRoute').dataTable( {
	            'paging': false,
	            'info': false,
	            'searching': false,
	            "language": {
	                "sZeroRecords": "No hay rutas definidas"
	              },
	            'pageLength': 100
	        }); // table
	
	        
	    }); // ready
	    
	    $('#checkAllButton').click(function(){
	    	var id;
	    	$(this).find("i").toggleClass("fa-square-o fa-check-square-o");
	    	// fa-square-o check vacio
	    	if ( $(this).find("i").hasClass('fa-square-o') ){ 
		    	$("input[type=checkbox]").each(function() {
		    		id = $(this).attr('id');
		    		$('#'+id).prop('checked',false);
		    		$(this).trigger("change");
		    	});
	    	}else{
		    	$("input[type=checkbox]").each(function() {
		    		id = $(this).attr('id');
		    		$('#'+id).prop('checked',true);
		    		$(this).trigger("change");
		    	});
	    	}
	    });
	    
	   	var colorA = "red";
		$("#list_scroll").slimScroll
		({
			height: '400px',
			start: 'top'
		});
		var cont_str = 0;
		var addStoresInMap = (function(data){
	        for (var key=0; key<data.length;key++) {
        		var pos = new google.maps.LatLng(data[key].latitude, data[key].longitude);
                addMarker(pos, data[key].id_store, data[key].color, data[key],key);
                cont_str = cont_str+1;
        	}
	        swal.close();
	    });	
	
		var delStoresInMap = (function(data){
	        for (var key=0; key<data.length;key++){
	       	 	for (i = 0; i < Markers.length; i++){	 
					if(Markers[i].id==data[key].id_store){
						Markers[i].setMap(null);
					}
	  			}
	       	}
	        swal.close();
	    });	
	    
		var Markers=[];
	    var AGS = { lat: 21.88072496638374 , lng: -102.2961151599884};
		var map = new google.maps.Map(document.getElementById('map'), {
			    zoom: 5,
			    center: AGS
			  });
		function initialize() {
		 //addStores();
		}
	
		// Adds a marker to the map
		function addMarker(location, id, color, data,key){
            var shelf = data.shelf;
			var icon = 'img/'+ ( shelf == 'S' ? 'pin6_' : 'icon-marker-')+color+'.png';
		    var marker= new google.maps.Marker({
			id: id,
		    position: location,
		    icon: icon,
		    map: map
		});
		Markers.push(marker);

		var contentString = '<div id="content" style="max-width:150px;">'+
        '<div id="siteNotice">'+
        '</div>'+
        '<h5 id="firstHeading"><b>'+data.name+'</b></h5>'+
        '<div id="bodyContent">'+
        '<p><b></b> '+data.address1+','+data.address2+
        '<p><b></b> '+data.postal+'</p>'+
        '</div>'+
        '</div>';
        marker.infowindow = new google.maps.InfoWindow({
            content: contentString
        });
		
        google.maps.event.addListener(Markers[cont_str], 'click', function() {
            this.infowindow.open(map,this);
        });
        google.maps.event.addListener(Markers[cont_str], 'mouseout', function() {
            this.infowindow.close();
        });

		}
 		
		google.maps.event.addDomListener(window, 'load', initialize);
 		
        $("input[type=checkbox]").change(function(){
        	swal({   
				title: "Un momento!",   
				text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
				html: true, 
				showConfirmButton: false 
			});
        	//verifica si hay solo 1 checkbox seleccionado para habilitar o des habilitar boton borrar
        	var sel = $('input[type=checkbox]:checked').length;
        	if(sel == 1)
        		$('#delBtn').attr('disabled',false);
        	else
        		$('#delBtn').attr('disabled',true);
        
			// agrega o quita marcadores de tiendas en mapa si se checkea alguna tienda o se quita el check de la tienda
        	colorA=$(this).val();
        	if(this.checked) {
        		ids=[];
        		RetailServiceBean.getStoreByRoute(($(this).attr("id")), addStoresInMap);
        		ids.push($(this).attr("id"));
        	}
        	else{
       			ids=[];
       			RetailServiceBean.getStoreByRoute(($(this).attr("id")), delStoresInMap);
       			ids.push($(this).attr("id"));
       		}
        	var cont=0;
    	    $("input[type=checkbox]").each(function() {
    	    	if(this.checked==true)
    	    		cont++;
    	    });
    	    if(cont>1)
    	    	$("#updBtn").prop('disabled', true);
    	    else
    	    	$("#updBtn").prop('disabled', false);
    	    
    	});
    	$('#updBtn').click(function(){	
			if(ids[0]!=null)
				window.location.replace("rutasupd.htm?accion=upd&id_route="+ids[0]);
			else
				alert("Selecciona una ruta por favor");
		});

        function eliminar() {
            var ids= [];
            var ids_travel = "";
            var sep ="";   			       	          
        			swal({   
        				title: "Alerta",
        				text: "¿Está seguro que desea borrar la ruta seleccionada?",   
        				type: "warning",   
        				showCancelButton: true,   
        				confirmButtonColor: "#DD6B55",   
        				confirmButtonText: "Aceptar",
        				cancelButtonText: 'Cancelar',
        				closeOnConfirm: false 
        			}, function(){   
        				  $("input[type=checkbox]:checked").each(function(index,e){               
        		                ids.push($(this).attr("id"));
        		                ids_travel = ids_travel + sep + $(this).attr("id");                 
        		                sep = ",";
        		            });  
        		            document.getElementById("idsRoute").value = ids_travel; 
        		            if (ids.length>0)
        		                document.getElementById("form_del").submit();        		            
        					var obj = {};
        					if (ids.length>0){
        						obj.title = 'Mensaje';
        						obj.msj = 'Ruta eliminada con éxito';
        						obj.type = 'success';    						
        					}else{
        						obj.title = 'Error';
        						obj.msj = 'Error al eliminar la ruta, intente más tarde';
        						obj.type = 'error';
        					}
        					swal({
        						title: obj.title, 
        						text: obj.msj, 
        						type: obj.type,
        						showConfirmButton: true,
        						timer: 2000
        					});
        			});
        };
        
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
							target: "checkAllButton",
							title: "Seleccionar todas",
							content: "Clic para seleccionar todas las rutas",
							placement: placementRight,
							yOffset: -15
						},
						{
							target: 'addBtn',
							title: "Nuevo",
							content: "Clic para agregar un nueva ruta",
							placement: "bottom",
							zindex: 999						
						},
						{
							target: 'delBtn',
							title: "Eliminar",
							content: "Clic para eliminar la ruta seleccionada",
							placement: "bottom",
							zindex: 999,
							xOffset: -15
						},
						{
							target: 'tableRoute_wrapper',
							title: "Tabla de rutas",
							content: "Aquí se muestran las rutas que actualmente tienes guardadas",
							placement: "right",
							zindex: 999
						},
						{
							target: 'map',
							title: "Mapa",
							content: "Aquí se muestran mapeadas las rutas seleccionadas en la tabla de rutas",
							placement: placementLeft,
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