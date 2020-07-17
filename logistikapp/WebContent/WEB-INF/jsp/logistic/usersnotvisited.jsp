<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
	<title>No visitados - LogistikApp</title>
	
	<!-- bootstrap -->
	<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
	
	<!-- libraries -->
	<link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />
	<link rel="stylesheet" type="text/css" href="css/libs/select2.css"/>
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">
	
	<link rel="stylesheet" type="text/css" href="css/logistikapp.css">
    
    <!-- this page specific styles -->

	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
	
	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
	
	<!--[if lt IE 9]>
			<script src="js/html5shiv.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->
	<style>
		#map{
			height: 70vh;
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
            </div><!-- /nav-col -->

			<div id="content-wrapper">

                <div class="row">
                    <div class="col-lg-12">
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a href="home.htm">Inicio</a></li>
                                    <li>Clientes no visitados</li>
                                </ol>
                                
                                <h1>Clientes no visitados</h1>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">                                    
                                    <div class="main-box-body clearfix">
                                    	<br />
                                    	<div class="row">
											<div class="col-xs-12">
												<div class="pull-left">
													<span class="label label-default"><i class="fa fa-circle"></i> No asignado</span>
													<span class="label label-primary"><i class="fa fa-circle"></i> Por Asignados</span>
													<span class="label label-danger"><i class="fa fa-circle"></i> Asignados</span>
												</div>
											</div>
										</div>
                                    	<div class="table-responsive">
											<table class="table table-hover table-striped" id="table">
												<thead>
													<tr>
														<th>#</th>
														<th>Código</th>
														<th>Cliente</th>
														<th>Viaje</th>
														<th>Responsable</th>
													</tr>
												</thead>
												<tbody>
												<c:forEach var="i" items="${list }" varStatus="s">
													<tr>
														<td><span class="badge" id="item-${i.id_store }">${s.index + 1 }</span></td>
														<td>
															<div class="checkbox-nice">
																<input type="checkbox" id="m-checkbox-${i.id_store }" data-index="${s.index }" class="item" value="${i.id_store }" data-idstore="${i.id_store }" data-idwaybill="${i.id_waybill }" data-lat="${i.latitude }" data-lng="${i.longitude }"/>
																<label for="m-checkbox-${i.id_store }">
																	${i.code }
																</label>
															</div>
														</td>
														<td>
															${i.store }
														</td>
														<td>${i.travel }</td>
														<td>${i.driver }</td>
													</tr>
												</c:forEach>
												</tbody>
											</table>
										</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">          
                                	<header class="main-box-header clearfix">
                                		<h2 class="pull-left">Viaje</h2>
                                		<div class="pull-right">
											<div class="form-group form-group-select2">
												<select style="width:300px" id="sel">
													<option value="NA">- Viajes -</option>
													<c:forEach items="${travels }" var="i">
														<option value="${i.id_travel }">${i.name } <fmt:formatDate value="${i.schedule }" pattern="dd/MM/YYYY"/> </option>
													</c:forEach>
												</select>
											</div>
										</div>
                                	</header>                          
                                    <div class="main-box-body clearfix">
                                    	<div class="row">
											<div class="col-xs-12">
												<div class="pull-left">
													<span class="label label-default"><i class="fa fa-map-marker"></i> No asignar</span>
													<span class="label label-primary"><i class="fa fa-map-marker"></i> Por Asignar</span>
													<span class="label label-danger"><i class="fa fa-map-marker"></i> Asignados</span>
												</div>
												<div class="pull-right">
													<span class="label label-default"><i class="fa fa-male"></i> Inicio</span>
													<span class="label label-warning"><i class="fa fa-male"></i> Recorrido</span>
													<span class="label label-primary"><i class="fa fa-male"></i> Posición actual</span>
													<button id="btn-reasign" class="btn btn-primary btn-sm disabled">Reasignar <i class="fa fa-map-marker"></i></button>
												</div>
											</div>
										</div>
										<br />
                                    	<div id="map"></div>
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
    
    <!-- this page specific scripts -->
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/dataTables.fixedHeader.js"></script>
    <script src="js/dataTables.tableTools.js"></script>
    <script src="js/jquery.dataTables.bootstrap.js"></script>
    
    <script src="dwr/interface/LogisticServiceBean.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/pace.min.js"></script>
    <script src="js/select2.min.js"></script>
	
    <!-- this page specific inline scripts -->
    <script>
    	var map
    	var table
    	$(document).on('ready', function(){
    		
    		$('#sel').select2().on('change', function(){
    			swal({   
        			title: "Obteniendo clientes del viaje",   
        			text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
        			html: true, 
        			showConfirmButton: false
        		});
    			
    			if ($("#sel").val() == 'NA') {
    				swal.close()
    				$('#btn-reasign').addClass('enabled')
    				return
    			}
    			
    			LogisticServiceBean.getWayBillByIdTravel($("#sel").val(), function(data){
    				$('.item').prop('disabled', false)
    				$('.badge').removeClass('badge-primary')
    				$('.badge').removeClass('badge-default')
    				$('.badge').removeClass('badge-danger')
    				resetMarkersIcon()
    				if (data.length >= 0) {
    					while(map.markers.length > $('.item').size()) {
    						map.removeMarker(map.markers[map.markers.length - 1])
    					}
    					
    					$(data).each(function(index, e){
    						console.log("-->", index, '#item-' + e.id_store)
    						map.addMarker({
        	            		lat: e.latitude,
        	            		lng: e.longitude,
        	            		icon: 'img/icon-marker-green.png',
        	            		label: "" + (index + 1)
        	            	})
        	            	
        	            	$el = $('.item[value=' + e.id_store + ']').first()
        	            	$el.prop('disabled', true)
        	            	$el.prop('checked', false)
        	            	var index = $el.data('index')
        	            	if (index && index >= 0) {
        	            		map.markers[index].setIcon('img/icon-marker-red.png')
        	            	}
        	            	$('#item-' + e.id_store).first().addClass('badge-danger')
    					})
    				}
    				swal.close()
    			})
    			
            })
    		
    		table = $('#table').dataTable({
            	search: true,
            	stateSave: false,
            	pageLength: 10,
                language: {
                    url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
                }
            })
            var latCenter = $('.item').first().data('lat')
            var lngCenter = $('.item').first().data('lng')
            
            map = GMaps({
            	el: "#map",
            	zoom: 18,
        		center: new google.maps.LatLng(latCenter,lngCenter),
            })
            
            $('.item').each(function(i, e){
            	map.addMarker({
            		lat: e.dataset.lat,
            		lng: e.dataset.lng,
            		icon: 'img/icon-marker-grey.png',
            		label: "" + (i + 1)
            	})
            })
            
            $('input[type=checkbox].item').on('change', function(){
            	$('.item').each(function(i, e){
            		var $ch = $(e)
            		var index = $ch.data('index')
            		var $el = $('#item-' + $ch.val()).first()
            		if (!$el.hasClass('badge-danger')){
            			if ($ch.is(':checked')){
                			$el.removeClass('badge-default')
                			$el.addClass('badge-primary')
                			map.markers[index].setIcon('img/icon-marker-blue.png')
            			} else {
                			$el.addClass('badge-default')
                			$el.removeClass('badge-primary')
                			map.markers[index].setIcon('img/icon-marker-grey.png')
            			}
            		}
            	})
            	
            	if ($('input[type=checkbox].item:checked').size() > 0) {
            		$('#btn-reasign').removeClass('disabled')
            	} else {
            		$('#btn-reasign').addClass('enabled')
            	}
            })
            
            function resetMarkersIcon() {
    			$('.item').each(function(i, e){
    				map.markers[i].setIcon('img/icon-marker-grey.png')
    				$(e).prop('checked', false)
    			})
    		}
    		
    		$('#btn-reasign').on('click', function() {
    			
    			swal({   
        			title: "Reasignando clientes al viaje" + $("#sel option:selected").text(),
        			text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
        			html: true, 
        			showConfirmButton: false
        		});
    			
    			var ids = []
    			$('.item').each(function(i, e){
    				ids.push(e.value)
    			})
    			console.log("Los ids", ids)
    			var dto = {
    				id_travel: $("#sel").val(),
    				travelName: $("#sel option:selected").text(),
    				ids: ids
    			}
    			
    			LogisticServiceBean.reasignWaybillFromOldTravel(dto, function(data) {
    				if (data == 0) {
    					swal('Error', "No se pudieron reasignar los clientes al viaje\nIntente más tarde", 'error')
    					$('.item:checked').each(function(i, e) {
    						var index = $(e).parents('tr').index()
    						table.fnDeleteRow(index)
    					})
    				} else {
    					swal('Exito', "Se reasignaron los clientes al viaje con exito" , 'success')
    				}
    			})
    			
    		})
            
    	})
    </script>

</body>

</html>