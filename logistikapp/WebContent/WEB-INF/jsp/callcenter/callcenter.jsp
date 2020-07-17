<%@ include file="/includes/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Locales - LogistikApp</title>

<!-- bootstrap -->
<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css" />
<!-- libraries -->
<link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
<link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />
<!-- global styles -->
<link rel="stylesheet" type="text/css"
	href="css/compiled/theme_styles.css">
<!-- this page specific styles -->
<link rel="stylesheet" type="text/css"
	href="css/libs/dataTables.fixedHeader.css">
<link rel="stylesheet" type="text/css"
	href="css/libs/dataTables.tableTools.css">
<link rel="stylesheet" type="text/css" href="css/logistikapp.css">
<!-- 
<link rel="stylesheet" href="css/libs/select2.css" type="text/css" />
 -->
<!-- Favicon -->
<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />

<!-- google font libraries -->
<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>

<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<script src="js/respond.min.js"></script>
<![endif]-->

	<style>		
		.visible{
			visibility:hidden;
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
									<li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard" /></a></li>
									<li class="active"><span><c:out value="Call Center"/></span></li>
								</ol>

								<div class="clearfix">
									<h1 class="pull-left">
										<a href="#"><c:out value="Call Center"/></a>
									</h1>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-lg-12">
								<div class="main-box clearfix">
									<header class="main-box-header clearfix">
                                        <div class="pull-left col-lg-12">
											<div class="row">												
													<div class="col-lg-12">
													<form id="form1" class="col-lg-12" >
			 											<input type="hidden" name="id_store" id="id_store" />
			 											<input type="hidden" name="lat" id="lat" />
			 											<input type="hidden" name="lon" id="lon" />
			 											<input type="hidden" name="nombre_str" id="nombre_str" />
			 											<input type="hidden" name="dir1" id="dir1" />
			 											<input type="hidden" name="dir2" id="dir2" />
			 											<input type="hidden" name="postal" id="postal" />			
			 											
			 											Buscar Cliente
			 											<div class="input-group">
															<input id="dir_name" MaxLength="50" type="text" name="dir_name" value="${dir_name}" class="form-control input-lg" />
																<div class="input-group-btn">
																	<button id="btn" class="btn btn-lg btn-primary visible" style="height:46px" type="button">
																		<i class="fa fa-search"></i> Buscar
																	</button>
																</div>
														</div>	   
															
														<div class="form-group pull-left" >
				 											<div class='radio-nice'>
				 												<input type='radio' name='busqueda' id='nombre' value="2" ${busqueda == 2 ? 'checked' : ''}${busqueda == null ? 'checked' : ''} /><label for='nombre'>&nbsp;Nombre</label>
				 												<input type='radio' name='busqueda' id='direccion' value="1" ${busqueda == 1 ? 'checked' : ''}/><label for='direccion'>&nbsp;Direcci&oacute;n</label>
				 											</div>
														</div>
														</form>
		 										</div>
											</div>
										<div class="pull-rigth col-lg-12 alert alert-warning fade in" style="display:${notravel == '1' ? 'inline' : 'none'}">
											<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
											<i class="fa fa-warning fa-fw fa-lg"></i>
											<strong>No se tiene ning&uacute;n viaje definido.</strong>
										</div>											
                                        </div>
                                        
									</header>
									
									<!-- Tabla de datos -->
									<div class="main-box-body clearfix">
										<table id="table-example" class="table table-striped table-hover"  >
											<thead>
												<tr>
													<th>Cliente</th>
													<th>Direcci&oacute;n</th>
													<th>Coordenadas</th>
													<th class="text-center">Estado</th>
													<th class="text-center">Viajes</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="str" items="${list}">
													<tr>
														<td>
													 		<a href="#" onclick="geolocalizar(${str.latitude},${str.longitude},'${str.store_name}','${str.address1}','${str.address2}','${str.postal}',${str.id_store})" >&nbsp;<c:out value="${str.store_name}"/></a>
														</td>
														<td>
															<c:out value="${str.address1}"/><c:out value="${str.address2}"/><c:out value="${str.postal}"/>
														</td>
														<td>
															<span class="badge badge-primary"><i class="fa fa-map-marker"></i> <c:out value="${str.latitude}"/> .. <c:out value="${str.longitude}"/></span>
														</td>
														<td class="text-center">															
															<span class="badge badge-${str.active == 'S' ? 'success' : 'danger'}"><i class="fa fa-${str.active == 'S' ? 'check' : 'times'}"></i></span>
														</td>
														<td>
															<ul class="package-features">
																<c:forEach var="tra" items="${str.travelStore}">
																	<li class="has-feature">
																		<a href="geotravel.htm?idts=${tra.id_travel}&volver=callcenter.htm"><b><c:out value="${tra.travel_name}"/></b></a>, <fmt:formatDate value="${tra.schedule}" pattern="dd/MM/yyyy" />, <c:out value="${tra.chofer}"/>, <c:out value="${tra.status}"/> 
																	</li>
																</c:forEach>
															</ul>																		
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<!-- /Tabla de datos -->
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
		<!-- <div class="row"> -->
	</div>
	<!-- div id="page-wrapper" class="container"-->

	<!-- global scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.min.js"></script>

	<!-- this page specific scripts -->
	<script src="js/jquery.dataTables.js"></script>
	<script src="js/dataTables.fixedHeader.js"></script>
	<script src="js/dataTables.tableTools.js"></script>
	<script src="js/jquery.dataTables.bootstrap.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/pace.min.js"></script>
	
	<script>
	</script>
	
	<script>
	 
	 $('#dir_name').on({
		 'keyup':colorBorde,
		 'click':colorBorde,
		 'focus':colorBorde
	 });

	 $('#btn').on('click', function(){
		 if($('#dir_name').val().length >= 3){
			 location.href = "?busqueda=" + $('input[name=busqueda]:checked').val() + "&dir_name=" + $('#dir_name').val();
		 }
	 })
	 
	 function colorBorde(e){	
		 if (e.keyCode == 13){
			 if(document.getElementById("dir_name").value.length >= 3){	
				 location.href = "?busqueda=" + $('input[name=busqueda]:checked').val() + "&dir_name=" + $('#dir_name').val();
			 }
		 }else{
			 if(document.getElementById("dir_name").value.length < 3){	
				    document.getElementById("dir_name").style.borderColor = 'lightgray';
			 		$('#btn').addClass('visible');
			 	}else{
			 		 document.getElementById("dir_name").style.borderColor = '#1abc9c';		
			 		$('#btn').removeClass('visible');
			 	}
		 }
	 }
	 
		$(document).ready(function() {
			
			if($('#dir_name').val().length <3){	
			 		$('#btn').addClass('visible');
			 	}else{
			 		$('#btn').removeClass('visible');
			 	}
			
			var tableFixed = $('#table-example').dataTable({
				'paging' : true,
				'info' : false,
				'pageLength' : 15,
//	            'filter': true,
				'searching' : false, // buscador en la tabla
				'lengthChange' : false, // filtro de numero de registros a mostrar
				'bFilter' : false,
				"dom" : '<"clearfix">rt<"html5buttons"p>i<"clear">', //acomodo de las secciones del dom
				"language" : {
					"search" : "Buscar:",
					"lengthMenu" : "Mostrar MENU registros",
					"info" : "</BR>Mostrando pagina PAGE de PAGES",
					"zeroRecords" : "No hay datos"
				},
				'oTableTools' : {
					"sSwfPath" : "swf/copy_csv_xls_pdf.swf",
					'aButtons' : [ {
						'sExtends' : 'collection',
						'sButtonText' : '<i class="fa fa-cloud-download" id="botones" ></i>&nbsp;&nbsp;&nbsp;<i class="fa fa-caret-down"></i>',
						'aButtons' : [
								'csv',
								'xls',
								'pdf',
								'copy',
								'print' ]
					} ]
				}
			});
			$('#table-example_info').addClass("hidden-xs");
			$('#table-example_info').addClass("hidden-sm");

			$('#table-example_filter').addClass("visible-xs");
			$('#table-example_filter').addClass("visible-sm");
			$('#table-example_filter').addClass("visible-md");
			$('#table-example_filter').addClass("visible-lg");
			$('#table-example_filter').addClass("pull-right");
			$('#table-example_filter').addClass("col-ln-6");
		});

		function geolocalizar(lat,lon,nombre,dir1,dir2,postal,id_store){
			
			document.getElementById("form1").action="geocallcenter.htm";
			
			document.getElementById("id_store").value=id_store;
			document.getElementById("lat").value=lat;
			document.getElementById("lon").value=lon;
			document.getElementById("nombre_str").value=escape(nombre);
			document.getElementById("dir1").value=escape(dir1);
			document.getElementById("dir2").value=escape(dir2);
			document.getElementById("postal").value=postal;
			
			document.getElementById("form1").submit();
		}
		//document.getElementById("dir_name").focus();
	</script>
</body>
</html>