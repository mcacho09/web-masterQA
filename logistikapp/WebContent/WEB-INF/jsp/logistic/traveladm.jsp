<%@ include file="/includes/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Viajes - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	
	<!-- libraries -->
	<link type="text/css" rel="stylesheet" href="css/libs/font-awesome.css" />
	<link type="text/css" rel="stylesheet" href="css/libs/nanoscroller.css" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">
	
	<!-- this page specific styles -->
	<link rel="stylesheet" type="text/css" href="css/libs/dataTables.fixedHeader.css"/>
	<link rel="stylesheet" type="text/css" href="css/libs/dataTables.tableTools.css"/>
	<link rel="stylesheet" type="text/css" href="css/logistikapp.css"/>
	<link rel="stylesheet" type="text/css" href="css/libs/jquery.datetimepicker.css" />
	<link rel="stylesheet" href="css/libs/daterangepicker.css" type="text/css" />
	<link rel="stylesheet" href="css/iCheck/flat/blue.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="css/libs/nifty-component.css"/>
	<link rel="stylesheet" href="css/chosen.css" />
	
	<!-- Dwr script -->
	<script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	
	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
	
	<!-- google font libraries -->
	<link
		href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400'
		rel='stylesheet' type='text/css'>
	
	<!--[if lt IE 9]>
	            <script src="js/html5shiv.js"></script> 
	            <script src="js/respond.min.js"></script>
	        <![endif]-->
	<!-- this page specific styles -->
	<style>
		#map-canvas {
			height: 57vh !important;
			width: 100% !important;
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
		
		.from-div {
			overflow: scroll;
			height: 200px;
		}
		
		.dd-handle {
			white-space: nowrap;
			text-overflow: ellipsis;
			overflow: hidden;
		}
		
		.dd-item,.dd-empty,.dd-placeholder {
			min-height: 40px;
		}
		
		#itemsSteps.nav-pills>li {
			float: left;
		}
		
		#id_route_chosen {
			width: 100% !important;
		}
		
		form.card {
			padding: 15px 10px;
		    border: 1px solid lightgray;
		    border-radius: 5px;
		    box-shadow: 0 0 15px 0px grey;
		}
		
		ul#itemsSteps li a {
			padding-top: 20px;
    		padding-bottom: 20px;
		}
		
		ul#itemsSteps li.active a {
			background-color: #3498db!important;
		}
		
		ul#itemsSteps li a span {
		    border: 1px solid #337ab7;
		    border-radius: 50%;
		    margin: 60px 0;
		}
		ul#itemsSteps li.active a span {
			border:1px solid white;
		}
		
		ul#itemsSteps li.active:last-child a {
			background-color: #2ecc71!important;
		}
		
		span.padding-1 {
			padding: 10px 15px;
		}
		
		span.padding-2 {
			padding: 10px 10px;
		}
		
		.slimScrollDiv {
			width: 100%!important;
		}
		
		#ch-toggle-markers-content {
			position: absolute;
			top: 10px;
			right: 20px;
			padding-right: 5px;
			z-index: 1;
			background-color: white;
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
									<li><a href="home.htm"><spring:message
												code="label.breadcrumb.dashboard" /></a></li>
									<li><span><spring:message
												code="label.breadcrumb.logistic" /></span></li>
									<li class="active"><span><a href="travellist.htm"><spring:message
													code="label.breadcrumb.logistic.travel" /></a></span></li>
								</ol>

								<div class="clearfix">
									<h1 class="pull-left">
										<spring:message
											code="${accion == 'add' ? 'label.logistic.travel.add.title1' : 'label.logistic.travel.upd.title2'}" />
									</h1>
								</div>

							</div>
						</div>
						<!-- formulario datos travel -->
						<div class="row">
							<div class="col-lg-12">
								<div class="main-box clearfix no-header">
									<div class="main-box-body clearfix">
										<div id="tabs">
											<div class="navbar">
												<div class="navbar-inner">
													<div class="container">
														<ul id="itemsSteps">
															<li><a href="#tab1" data-toggle="tab"><span class="padding-1">1</span></a></li>
															<li><a href="#tab2" data-toggle="tab"><span class="padding-1">2</span></a></li>
															<li><a href="#tab3" data-toggle="tab"><span class="padding-1">3</span></a></li>
															<li><a href="#tab4" data-toggle="tab"><span class="padding-2"><i class="fa fa-check fa-lg"></i></span></a></li>
														</ul>
													</div>
												</div>
											</div>
											<div id="bar" class="progress progress-4x">
												<div class="progress-bar progress-bar-primary"
													role="progressbar" aria-valuenow="0" aria-valuemin="100"
													style="width: 100%;"></div>
											</div>
											<!-- Tab panes -->
											<div class="tab-content">
												<div class="tab-pane" id="tab1">
													<div class="row">
														<div class="col-sm-12">
															<h2>Primer paso:</h2>
															<p>Ingrese la siguiente información para configurar un viaje</p>
															<div class="row">
																<div
																	class="col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2">
																	<form method="POST" class="card" name="formadd" id="formadd">
																		<input type="hidden" id="ids" name="ids"> 
																		<input type="hidden" id="idt" name="idt" value="${viaje.id_travel}"> 
																		<input type="hidden" id="idr" name="id_route" value="${viaje.id_route}"> 
																		<input type="hidden" id="time" name="time"> 
																		<input type="hidden" id="km" name="km"> 
																		<input type="hidden" id="repetitions" name="repetitions" value="1" /> 
																		<input type="hidden" id="repetitionsday" name="repetitionsday" value="1" />

																		<spring:bind path="command.status">
																			<input type="hidden" id="status" name="status">
																		</spring:bind>

																		<spring:hasBindErrors name="command">
																			<div class="alert alert-danger fade in">
																				<button type="button" class="close"
																					data-dismiss="alert" aria-hidden="true">×</button>
																				<i class="fa fa-times-circle fa-fw fa-lg"></i> Para
																				continuar primero debes revisar los errores que
																				est&aacute;n m&aacute;s abajo.
																			</div>
																		</spring:hasBindErrors>
																		<div class="row">
																			<spring:bind path="command.name">
																				<div
																					class="form-group ${status.error ? 'has-error' : '' } col-lg-12">
																					<label for="name">Nombre (requerido)</label> <input
																						type="text" class="form-control"
																						maxlength="40" id="name" name="name"
																						placeholder="Ingresa el nombre del viaje"
																						value="${accion == 'add'? tname : status.value}" />
																					<c:if test="${status.error}">
																						<span class="help-block"><i
																							class="fa fa-exclamation-triangle"></i>
																							${status.errorMessage}</span>
																					</c:if>
																				</div>
																			</spring:bind>
																			<spring:bind path="command.schedule">
																				<div
																					class="form-group ${status.error ? 'has-error' : '' } col-lg-12">
																					<label for="dateformat">Fecha de
																						Programaci&oacute;n</label>
																					<div>
																						<input id="dateformat" name="dateformat"
																							type="hidden"
																							value=<fmt:formatDate value="${viaje.schedule}" type="both" pattern="dd/MM/yyyy"/>>
																						<input class="form-control" id="schedule"
																							name="schedule" type="text"
																							placeholder="Fecha de Programaci&oacute;n"
																							value="" size="11px" />
																						<c:if test="${status.error}">
																							<span class="help-block"><i
																								class="fa fa-exclamation-triangle"></i>
																								${status.errorMessage}</span>
																						</c:if>
																					</div>
																				</div>
																			</spring:bind>
																		</div>
																		<div class="row">
																			<!-- id_route -->
																			<!-- <spring:bind path="command.id_route">
																			<div class="form-group ${status.error ? 'has-error' : '' } col-lg-6">
																				<label for="id_route">Selecciona una o más
																					rutas y haz clic <span type="button"
																					class="btn-link" data-toggle="tooltip"
																					data-placement="bottom"
																					title="Obtener clientes de rutas seleccionadas"
																					id="btnGetRoutes"><b>aquí</b></span> para actualizar
																					listado de clientes
																				</label> <select
																					data-placeholder="Seleccionar una o más rutas"
																					class="form-control chosen-select" id="id_route"
																					name="id_route" multiple>
																					<c:forEach var="ruta" items="${rutas}" varStatus="i">
																						<option class="form-control"
																							value="${ruta.id_route}"
																							style="color:${ruta.color == 'yellow'?'#f1c40f':ruta.color}!important; ">${ruta.name}
																						</option>
																					</c:forEach>
																				</select>
	
																				<c:if test="${status.error}">
																					<span class="help-block"><i
																						class="fa fa-exclamation-triangle"></i>
																						${status.errorMessage}</span>
																				</c:if>
																			</div>
																		</spring:bind> -->
																			<!-- /id_route -->
																			<!-- vendedor -->

																			<spring:bind path="command.id_user">
																				<div
																					class="form-group ${status.error ? 'has-error' : '' } col-lg-12">
																					<!-- div class="col-lg-11 col-md-11 col-sm-11 col-xs-11"-->
																					<label for="id_user">Vendedor (requerido)</label> <select
																						class="form-control" id="id_user" name="id_user">
																						<option class="form-control" value="" selected>Ninguno</option>
																						<c:forEach var="chofer" items="${choferes}">
																							<option class="form-control"
																								value="${chofer.id_user}"
																								${chofer.id_user eq iduser? 'selected':''}>${chofer.username}</option>
																						</c:forEach>
																					</select>
																					<c:if test="${status.error}">
																						<span class="help-block"><i
																							class="fa fa-exclamation-triangle"></i>
																							${status.errorMessage}</span>
																					</c:if>
																					<!-- /div-->
																				</div>
																			</spring:bind>
																			<!-- /vendedor -->
																		</div>
																	</form>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="tab-pane" id="tab2">
													<div class="row">
														<div class="col-sm-12">
															<h2>Segundo paso</h2>
															<p>Selecciona las rutas necesarias que conformaran tu
																viaje</p>

															<div class="row">
																<div class="form-group col-xs-12">
																	<select data-placeholder="Seleccionar una o más rutas"
																		class="form-control chosen-select" id="id_route"
																		name="id_route" multiple>
																		<c:forEach var="ruta" items="${rutas}" varStatus="i">
																			<option class="form-control" value="${ruta.id_route}"
																				style="color:${ruta.color == 'yellow'?'#f1c40f':ruta.color}!important; ">${ruta.name}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
															<div class="table-responsive">
																<table class="table table-hover table-striped">
																	<thead>
																		<tr>
																			<th class="">
																				<div class="checkbox">
																					<label for="#chAll"><input class="icheck-all" type="checkbox" name="chAll"> Nombre de la ruta</label>
																				</div>
																			</th>
																			<th class="text-center">Color</th>
																		</tr>
																	</thead>
																	<tbody>
																		<c:forEach var="ruta" items="${rutas}" varStatus="i">
																			<tr>
																				<td>
																					<div class="checkbox">
																						<label for="#ch1"><input class="icheck"
																							type="checkbox" value="${ruta.id_route}"
																							name="ch${ruta.id_route}"> ${ruta.name}</label>
																					</div>
																				</td>
																				<td class="text-center"><span
																					class="label label-default"
																					style="background: ${ruta.color == 'yellow'?'#f1c40f':ruta.color};"><i
																						class="fa fa-map-marker"></i></span></td>
																			</tr>
																		</c:forEach>
																	</tbody>
																</table>
															</div>
														</div>
													</div>
												</div>
												<div class="tab-pane" id="tab3">
													<div class="row">
														<div class="col-xs-12">
															<h2>Tercer paso</h2>
															<p>Selecciona de la lista los clientes o lugares en el orden que se van a visitar, u ordenalos en el mapa directamente</p>
															<div class="row">
																<div class="col-lg-6">
																	<h4>Asignar Clientes</h4> <button class="btn btn-primary btn-sm pull-right" onclick="$('#modal-codes').toggleClass('md-show')"><i class="fa fa-list-ol"></i></button>
																	<table id="tableStore"
																		class="table-condensed table-striped table-bordered"
																		cellspacing="0"
																		style="width: 100%; background: #fdfdfd;">
																		<thead>
																			<tr>
																				<th width="20px"><input type="checkbox"
																					id="selall" value="${i.index}" /></th>
																				<th>Cliente</th>
																			</tr>
																		</thead>
																		<tbody>
																			<!--<c:forEach var="tienda" items="${list}" varStatus="i">
																				<tr>
																					<td><input class="${tienda.avaible} items"
																						type="checkbox" style="display: inline-block;"
																						id="${tienda.id_store}" value="${i.index}"
																						data-lat="${tienda.latitude}"
																						data-lng="${tienda.longitude}"
																						data-name="${tienda.name}"
																						data-avaible="${tienda.avaible}"
																						data-address1="${tienda.address1 }"
																						data-address2="${tienda.address2 }"
																						data-postal="${tienda.postal }"
																						data-shelf="${tienda.shelf }"
																						data-color="${tienda.color }" /></td>
																					<td>${tienda.name} <small class="label"
																						style="background:${tienda.color=='yellow'?'#f1c40f':tienda.color};"
																						data-toggle="tooltip" data-placement="bottom"
																						title="Ruta">${tienda.route }</small> <c:if
																							test="${tienda.shelf == 'S' }">
																							<i class="fa fa-star" style="color: #f1c40f;"
																								data-toggle="tooltip" data-placement="bottom"
																								title="Promoción"></i>
																						</c:if>
																					</td>
																				</tr>
																				<input type="text" id="inpstr${i.index}"
																					value="${tienda.id_store}" style="display: none;">
																			</c:forEach>-->
																		</tbody>
																	</table>
																</div>
																<div class="col-lg-6">
																	<h4>Clientes de ruta <button type="button" class="btn btn-primary btn-sm pull-right" id="btnSort"><span class="hidden-sm">Viaje sugerido</span> -<i class="fa fa-sort"></i></button></h4>
																	<br/>
																	<div id="list_scroll" class="row cf nestable-lists"
																		style="padding: 0; margin-top: 0; background: #fdfdfd;">
																		<div class="dd col-xs-12" style="width: 100%;"
																			id="nestable">
																			<input type="hidden"
																				value="${fn:length(tienda_disp)}" id="numclientes">
																			<ol class="dd-list" id="ntable"></ol>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-xs-12" style="position:relative;">
															<div class="checkbox-nice" id="ch-toggle-markers-content">
																<input type="checkbox" id="ch-toggle-markers" checked="checked">
																<label for="ch-toggle-markers">
																	Mostrar puntos
																</label>
															</div>
															<div id="map-canvas"></div>
														</div>
													</div>
												</div>
												
												<div class="tab-pane" id="tab4">
													<div class="row">
														<div class="col-xs-12">
															<h2>Último paso</h2>
															<p>Aquí podras crear o programar tu viaje</p>
															<p>Haz clic en "programar" para generar el viaje, en el cual podrás guardar tu viaje para ser iniciado o bien repetir tu viaje las veces que creas necesarias</p>
															<div class="row">
																<div class="col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2">
																	<div class="row">
																		<div class="col-lg-6 col-sm-6 col-md-6 col-xs-12">
																			<div class="main-box infographic-box">
																				<i class="fa fa-road gray-bg box2"></i> 
																				<span class="headline">Distancia (km)</span> 
																				<span><b>0 aprox</b></span>
																			</div>
																		</div>
																		<div class="col-lg-6 col-sm-6 col-md-6 col-xs-12">
																			<div class="main-box infographic-box">
																				<i class="fa fa-clock-o gray-bg box2"></i> 
																				<span class="headline">Tiempo (hrs)</span> 
																				<span><b>00:00 aprox</b></span>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-xs-12 text-center">
																	<div class="btn-group">
																		<button type="button" id="btnCre"
																			onclick="addTravel('CRE');"
																			class="btn btn-default">
																			<i class="fa fa-check"></i> Guardar
																		</button>

																		<button type="button" id="btnPro"
																			onclick="addTravel('PRO');"
																			class="btn btn-success">
																			<i class="fa fa-clock-o"></i>
																			<spring:message
																				code="${accion == 'add' ? 'label.logistic.travel.add.title4' : 'label.logistic.travel.add.title4'}" />
																		</button>

																		<button type="button" id="btnPro"
																			onclick="repTravel('PRO');"
																			class="btn btn-primary">
																			<i class="fa fa-clock-o"></i>
																			Repetir
																		</button>

																		<button onclick="location.href='travellist.htm'"
																			type="button" id="toolbars-cancel"
																			class="btn btn-danger">
																			<i class="fa fa-times"></i> Cancelar
																		</button>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<br>
												<ul class="pager wizard">
													<li class="previous first" style="display: none;"><a
														href="#">Primero</a></li>
													<li class="previous"><a href="#">Anterior</a></li>
													<li class="next last" style="display: none;"><a
														href="#">Último</a></li>
													<li class="next"><a href="#">Siguiente</a></li>
												</ul>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- <div class="row">
                                <div class="col-lg-5" >
                                    <div class="main-box clearfix">
                                            <div class="main-box-body clearfix">
												<div class="table-responsive">                                                    
													<header class="main-box-header clearfix">
														<div class="row">
															<h2>Asignar Clientes<span class="table-link badge pull-right">${totlist}</span></h2>
														</div>
													</header>
												    
												</div>
												<br>
                                                
                                                <div class="table-responsive">
														<header class="main-box-header clearfix">
															<h2>Clientes de viaje<span class="table-link badge pull-right" ><div id="selected">0</div></span></h2>
														</header>
	                                              	
												</div>
												
                                            </div>
                                    </div>
                                </div>
                                
                                
                            </div> -->
					</div>
				</div>

				<footer id="footer-bar" class="row">
					<c:import url="/html/footer.html" />
				</footer>

			</div>
		</div>
	</div>

	<div class="md-modal md-effect-1" id="modal-reps">
		<div class="md-content">
			<div class="modal-header bg-primary">
				<button class="md-close close" onclick="$('#modal-reps').toggleClass('md-show')">&times;</button>
				<h4 class="modal-title">¿Desea repetir el viaje?</h4>
			</div>
			<div class="modal-body">
				<div class="content">
					<div class="row" id="divReps">
						<div class="col-lg-12">
							<label for="nrepetitions">No. de repeticiones: </label> <input
								type="number" name="nrepetitions" id="nrepetitions"
								class="form-control"
								value="${viaje.repetitions == null?1:viaje.repetitions }"
								min="1" max="${maxdays }" step="1"
								placeholder="No. de repeticiones">
						</div>
						<br>
						<div class="col-lg-12">
							<span>A repetir: </span>
							<div class="row">
								<div class="col-lg-3">
									<div class="radio">
										<input type="radio" name="nrepetitionsday" id="input"
											value="1" checked> <label for="input">Diario</label>
									</div>
								</div>
								<div class="col-lg-3">
									<div class="radio">
										<input type="radio" name="nrepetitionsday" id="input2"
											value="2"> <label for="input2">Semanal</label>
									</div>
								</div>
								<div class="col-lg-3">
									<div class="radio">
										<input type="radio" name="nrepetitionsday" id="input4"
											value="4"> <label for="input4">Quincenal</label>
									</div>
								</div>
								<div class="col-lg-3">
									<div class="radio">
										<input type="radio" name="nrepetitionsday" id="input3"
											value="3"> <label for="input3">Mensual</label>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" onclick="$('#modal-reps').toggleClass('md-show')" id="noRepeat">Volver al viaje</button>
				<button type="button" class="btn btn-success" id="saveTravel">
					<i class="fa fa-clock-o"></i> Guardar
				</button>
			</div>
		</div>
	</div>

	<div class="md-modal md-effect-1" id="modal-codes">
		<div class="md-content">
			<div class="modal-header">
				<button type="button" class="md-close close" onclick="$('#modal-codes').toggleClass('md-show')">&times;</button>
				<h4 class="modal-title">Ingresar códigos</h4>
			</div>
			<div class="modal-body">
				<h1 class="text-primary">Ingresa un listado de códigos de clientes a seleccionar</h1>
				<h3 class="text-info">Separa tus códigos por coma, espacio o salto de linea</h3>
				<textarea name="ta-codes" id="ta-codes" class="form-control" rows="5"></textarea>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" onclick="$('#modal-codes').toggleClass('md-show')">Cancelar</button>
				<button type="button" class="btn btn-success" id="btn-search-code">Buscar</button>
			</div>
		</div>
	</div>

	<div class="md-overlay"></div>

	<!-- global scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.min.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/pace.min.js"></script>

	<!-- datetimepicker </body> -->
	<script src="js/jquery.datetimepicker.js"></script>

	<!-- this page specific inline scripts -->
	<script src="js/markerwithlabel.js"></script>

	<!-- this page specific scripts -->
	<script src="js/jquery.dataTables.js"></script>
	<script src="js/dataTables.fixedHeader.js"></script>
	<script src="js/dataTables.tableTools.js"></script>
	<script src="js/jquery.dataTables.bootstrap.js "></script>
	<script src="js/jquery.nestable.js"></script>
	<script src="js/jquery.slimscroll.min.js"></script>

	<!-- Multiselect -->
	<script src="js/chosen.jquery.min.js" type="text/javascript"></script>

	<script src="js/icheck.min.js"></script>

	<script src="js/jquery.bootstrap.wizard.min.js"></script>
	<script src="js/modalEffects.js"></script>

	<script>
	
		var $tableStore
	    	
	    if ('${id_route}'.indexOf(',')){
		    var list = "${id_route}".split(','); 
		    $(list).each(function(i,e){
		    	$('select option[value=' + e + ']').prop('selected',true);
		    });
	    }else{
	    	$('select option[value=' + '${id_route}' + ']').prop('selected',true);
	    }
	    
	    $chosen = $('select#id_route').chosen({
	    	no_results_text: 'Resultado no encontrado'
	    })
	    
	    $('select#id_route').trigger('chosen:yodated')
		    
		if("${date}"=="" && "${accion}" == "add"){
			document.getElementById("dateformat").value = ""
		}else if("${date}"!="" && "${accion}" == "add"){
			var fecha = "${date}"
		    document.getElementById("dateformat").value = fecha
		}
		    
	    $('#schedule').datetimepicker({
        	lang:'es',
            i18n:{
            	es:{
                	months:['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
                    dayOfWeek:['Dom','Lun','Mar','Mié','Jue','Vie','Sáb']
                }
           	},
            timepicker:false,
            format:'d/m/Y',
            minDate:new Date(),
            onChangeDateTime: function(dp, $input) {
            	console.log(dp)
            	UserServiceBean.getDisponibleDrivers({id_supplier: ${useraccess.id_supplier}, date : dp}, function(data){
            		$('#id_user option:not(:first-child)').remove()
            		data.forEach(function(e, i){
            			$('#id_user').append('<option class="form-control" value="' + e.id_user + '">' + e.username + '</option>')
            		})
            	})
            }
	    });
	        
	    document.getElementById("schedule").value = document.getElementById("dateformat").value ; 
	        
	        // activate Nestable for list 1
	    $('#nestable').nestable({
	    	group: 1,
	        customActions   : {
	        	"remove"    : function(item,button,e) { 
	        		item.remove();
	        		if (map){
	                	map.markers.forEach(function(e, i){
	    					var index = $('#li' + e.id).index()
	                    	e.setLabel('' + (index > -1?(index + 1):''))
	                    	e.setIcon((index == -1? e.defaultIcon: "img/icon-marker-grey.png"))
	               		})
	               	}
	        	}
	        }
	    }).on('change', updateOutput);
	            
        // output initial serialised data
	    updateOutput($('#nestable').data('output', $('#nestable-output')));
            
	    $('#list_scroll').slimScroll({
	    	height: '50vh',            
	        start: 'top'
	    });
		 
	     // datatable
		$tableStore = $('#tableStore').DataTable({
	       	"scrollY": '40vh',
	        'paging': false,
	    	"language": {
	        	"search": "Buscar:",
                "zeroRecords": "No se encontraron clientes"
	        },
	        'info': false,
	        'pageLength': 100,
	        'filter': false,
	        "bFilter": false,
	        'searching' : true,
	        "ordering": true,
	        "order": [[ 1, 'asc' ]],
	        "aoColumnDefs": [
	        	{ 'bSortable': false, 'aTargets': [ 0, 1 ] }
	        ]
	    });
	        
	    function obtenerLatLng(){
	    	$('#distance').html("<b>Cargando...</b>");
			$('#tiempo').html("<b>Cargando...</b>");
    		var array = [];
    		$('li.dd-item input').each(function(i,e){
    			if($(e).data('lat') != 0.0 && $(e).data('lng') != 0.0){
    				array.push(new google.maps.LatLng($(e).data('lat'), $(e).data('lng')));
    			}
	    	});
    		if (array.length > 1){
	    		obtenerDistancia(array);
	    	}else{
	    		$('#distance').html("<b>0 aprox</b>");
    			$('#tiempo').html("<b>00:00 aprox</b>");
	    	}
    		
    	}
	    
	    var map = new GMaps({
        	el: '#map-canvas',
        	zoom: 15,
        	center: new google.maps.LatLng(0, 0)
        })
	    
      	var markers = []
        var n_tienda_disp = parseInt("${fn:length(tienda_disp)}");  // numero de clientes disponibles 
        var n_tienda_viaje = parseInt("${fn:length(tienda_no_disp)}"); // numero de clientes que ya estan en un viaje
        
        function repTravel(action){
            document.getElementById("status").value = action;
            var ids= [];
            var name_travel = $('#name').val();
            $('.dd-item').find('input').each(function(index,e){
            	// no obtener los ids que se tienen en datatable y solo obtener ids de nestable
				ids.push(this.value)
            });
            
            var idr = []
            
            $('#id_route option:selected').each(function(index,e){
            	// no obtener los ids que se tienen en datatable y solo obtener ids de nestable
				idr.push(this.value)
            });
            
            document.getElementById("ids").value = "" + ids;
            document.getElementById("idr").value = "" + idr;
            if (ids.length>0){
                if(name_travel!='')
                	if ($('#id_user').val() != '')
                		if ($('#schedule').val() != "")
                			if (action == 'PRO'){
                				$('#modal-reps').addClass('md-show');
                			}else
				                document.getElementById("formadd").submit();
                		else
                			swal("Alerta",'Debe seleccionar la fecha del viaje','warning');
                	else
                		swal("Alerta",'Debe seleccionar al vendedor del viaje','warning');
                else
                	swal("Alerta","Debe de asignar un nombre al viaje.",'warning');
            }
            else 
            	swal("Alerta","No hay clientes seleccionadas.",'warning');
            
        }
        
        function addTravel(action){
            document.getElementById("status").value = action;
            var ids= [];
            var name_travel = $('#name').val();
            $('.dd-item').find('input').each(function(index,e){
            	// no obtener los ids que se tienen en datatable y solo obtener ids de nestable
				ids.push(this.value)
            });
            
            var idr = []
            
            $('#id_route option:selected').each(function(index,e){
            	// no obtener los ids que se tienen en datatable y solo obtener ids de nestable
				idr.push(this.value)
            });
            
            document.getElementById("ids").value = "" + ids;
            document.getElementById("idr").value = "" + idr;
            if (ids.length>0){
                if(name_travel!='')
                	if ($('#id_user').val() != '')
                		if ($('#schedule').val() != "")
                			if (action == 'PRO'){
                				$('#repetitions').val(1);
                        		$('#repetitionsday').val(1);
                    			$('form').submit();
                			}else
				                document.getElementById("formadd").submit();
                		else
                			swal("Alerta",'Debe seleccionar la fecha del viaje','warning');
                	else
                		swal("Alerta",'Debe seleccionar al vendedor del viaje','warning');
                else
                	swal("Alerta","Debe de asignar un nombre al viaje.",'warning');
            }
            else 
            	swal("Alerta","No hay clientes seleccionadas.",'warning');
            
        }
        
		// entra cuando se modifica el orden de nestable
        function updateOutput(e){
            var list   = e.length ? e : $(e.target),
                output = list.data('output');
            if (output != null)
            if (window.JSON) {
                output.val(JSON.stringify(list.nestable('serialize')));//, null, 2));
            } 
            
            $('.nested-links').find('.label').each(function(i, e){
            	this.innerHTML = "" + (i + 1)
            })
            
            if (map){
            	map.markers.forEach(function(e, i){
					var index = $('#li' + e.id).index()
                	e.setLabel('' + (index > -1?(index + 1):''))
                	e.setIcon((index == -1? e.defaultIcon: "img/icon-marker-grey.png"))
           		})
           	}
           	
        }
        
        //Creates a new nestable item
        function addItem($element, cont) {
    		// se obtienen datos de la tienda
            var lat  = $element.data('lat');                
            var lng  = $element.data('lng');                
            var name = $element.data('name');
            var address1 = $element.data('address1');
            var address2 = $element.data('address2');
            var postal = $element.data('postal');
            var shelf = $element.data('shelf');
            var color = $element.data('color');
            
            var id   = $element.attr("id");
            // se ingresa el dato en el nestable
            var html = "<li class='dd-item' data-id='"+id+"' id='li"+id+"'>" + 
            "<a href='javascript:delnest("+id+");' class='nested-link' style='position: absolute; top: 25%; left:0;' ><i class='fa fa-trash-o fa-lg' id='heylo'></i></a>" +
            "<input onclick='delnest("+id+")' type='checkbox' checked style='display: none;'  id='n"+id+"' value='"+id+"' data-lat='"+lat+"' data-lng='"+lng+"' data-name='"+name+"' data-address1='"+address1+"' data-address2='"+address2+"' data-postal='"+postal+"' data-shelf='"+shelf+"' data-color='" + color  +"'/>" + 
            "<div class='dd-handle' style='width:95%; position: absolute; right:0;'>" +  name +
            "<div class='nested-links'><span class='label label-default' id='num"+id+"'>"+cont+"</span></div>" +
            "</div></li>";
            $(html).hide().appendTo("#nestable .dd-list:first").fadeIn();
        }

        function delnest(id){
        	// elimina registro de nestable
        	$( "#li" + id).remove(); 
        	// quita check de datatable
        	$("#"+id).removeAttr("checked"); 
            // da nuevo orden de las tiendas seleccionadas
            $('.nested-links').find('.label').each(function(i, e){
            	this.innerHTML = "" + (i + 1)
            })
            
            map.markers.forEach(function(e, i){
				var index = $('#li' + e.id).index()
                e.setLabel('' + (index > -1?(index + 1):''))
                e.setIcon((index == -1? e.defaultIcon: "img/icon-marker-grey.png"))
            })
            
            obtenerLatLng()
        }
		
        function obtenerDistancia(array){
        	var origins = [];
        	var destinations = [];
        	
        	for (var i = 0; i < (array.length - 1); i++){
        		origins.push(array[i]);
        	}
        	for (var i = 1; i < array.length; i++){
        		destinations.push(array[i]);
        	}
        	var km = 0;
        	var time = array.length * ${traveltime} * 60;
        	var service = new google.maps.DistanceMatrixService();
        	while (origins.length > 0){
        		var orgTmp = [];
        		var distTemp = [];
        		for (var i = 0; i < (origins.length >= 10?10:origins.length); i++){
        			orgTmp.push(origins[i]);
        			distTemp.push(destinations[i]);
        		}
            	service.getDistanceMatrix({
            		origins: orgTmp,
            	    destinations: distTemp,
            	    travelMode: google.maps.TravelMode.DRIVING,
            	    drivingOptions: {
            	        departureTime: new Date(Date.now()),
            	        trafficModel: "pessimistic"
            	    },
            	    unitSystem: google.maps.UnitSystem.METRIC
            	 }, function(response, status) {
              		if (status == google.maps.DistanceMatrixStatus.OK) {
             			for (var i = 0; i < orgTmp.length; i++) {
            				var element = response.rows[i].elements[i];
            			   	km += element.distance.value;
            			   	time += element.duration.value;
            			}
            			$('#distance').html('<b>' + (km/1000) + " aprox.</b>");
            			$('#tiempo').html('<b>' +  getTime(time) + " aprox.</b>");
            			$('#time').val(time*60);
            			$('#km').val(km);
            		} else {
            			$('#distance').html("<b>0 aprox.</b>");
            			$('#tiempo').html("<b>0 aprox.</b>");
            			$('#time').val(0);
            			$('#km').val(0);
            		}
            	});
            	
    			origins.splice(0,(origins.length >= 10?10:origins.length));
    			destinations.splice(0,(origins.length >= 10?10:origins.length));
        	}
        	$('#distance').html('<b>' + (km/1000) + " aprox.</b>");
			$('#tiempo').html('<b>' +  getTime(time) + " aprox.</b>");
			$('#time').val(time*60);
			$('#km').val(km);
        	
        }
        
        function getTime(x){
    		var hours = parseInt( x / 3600 ) % 24;
    		var minutes = parseInt( x / 60 ) % 60;

    		return (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes);
    	}
        
        /*$('#tour').css({
    		display:'block',
    		cursor:'pointer'
    	});*/

    	$('#tour').on('click', function(){
			var tour = {
					id: "customer-tour",
					steps: [
						{
							target: "name",
							title: "Nombre",
							content: "Ingresa el nombre para el viaje",
							placement: "bottom"
						},
						{
							target: "schedule",
							title: "Fecha de programación",
							content: "Selecciona la fecha para programar el viaje",
							placement: "left",
							zindex: 999,
							yOffset: -15
						},
						{
							target: "id_route",
							title: "Selecciona una Ruta",
							content: "Selecciona la ruta de la cual se mostraran los clientes para el viaje",
							placement: "bottom",
							zindex: 999
						},
						{
							target: "id_user",
							title: "Vendedor",
							content: "Seleccione al vendedor responsable del viaje",
							placement: "bottom",
							zindex: 999
						},
						{
							target: "tableStore",
							title: "Tabla Cliente",
							content: "Aquí seleccionara los clientes que pertenecerán al viaje",
							placement: "right",
							zindex: 999
						},
						{
							target: "map-canvas",
							title: "Mapa",
							content: "Aquí se mapean los clientes seleccionados en la tabla Cliente",
							placement: "left",
							zindex: 999
						},
						{
							target: "btnCre",
							title: "Crear",
							content: "Clic si solo quieres crear el viaje",
							placement: "left",
							zindex: 999,
							yOffset: -15
						},
						{
							target: "btnPro",
							title: "Programar",
							content: "Clic para programar el viaje con la fecha que seleccionaste anteriormente",
							placement: "left",
							zindex: 999,
							yOffset: -15
						}
					],
					showPrevButton: true
				};
			hopscotch.startTour(tour);
		});
    	
    	var max = ${maxdays};
    	$('input[name="nrepetitionsday"]').on('change', function(){
    		$('#repetitionsday').val(this.value);
    		if (this.value == 1){
    			$('#nrepetitions').prop('max', parseInt("${maxdays}"));
    			max = parseInt("${maxdays}");
    		}else if (this.value == 2){
    			$('#nrepetitions').prop('max', 26);
    			max = 26;
    		}else if (this.value == 3){
    			$('#nrepetitions').prop('max', 6);
    			max = 6;
    		}else if (this.value == 4){
    			$('#nrepetitions').prop('max', 13);
    			max = 13;
    		}
    	});
    	
    	$('#nrepetitions').on('change', function(){
    		if (this.value > max){
    			this.value = max;
    		}
    		$('#repetitions').val(parseInt(this.value));
    	});
    	
    	$('#oneTime').on('click', function(){
    		
    		var i = $('#oneTime').find('i')[0];
    		
    		if($(i).hasClass('fa-check-square-o')){
    			$('#divReps').toggleClass('hidden');    			
    			$(i).removeClass('fa-check-square-o');
    			$(i).addClass('fa-square-o');
    			$('#repetitionsday').val(1);
    			$('#repetitions').val(1);    			
    			$('#saveTravel').html('<i class="fa fa-clock-o"></i> Programar & Repetir');
    		}else{
    			$('#repetitions').val(0);
    			$('#repetitionsday').val($('input[name=nrepetitionsday]:checked').val());
    			$(i).addClass('fa-check-square-o');
    			$(i).removeClass('fa-square-o');   
    			$('#divReps').toggleClass('hidden');   
    			$('#saveTravel').html('<i class="fa fa-clock-o"></i> Sólo Programar');
    		}
    	});
    	
    	$('#saveTravel').on('click', function(){
    		
    		if ($('#nrepetitions').val() > 0){
    			if ($('#nrepetitions').val() <= max){
    				$('#repetitions').val(parseInt($('#nrepetitions').val()));
    	    		$('#repetitionsday').val($('input[name=nrepetitionsday]:checked').val());
    				$('form').submit();
    			}else{
    				swal('Alerta', 'El no. de repeticiones debe ser menor o igual ' + max, 'warning');
    			}
    		}else{
    			swal('Alerta', 'El no. de repeticiones debe ser mayor a cero', 'warning');
    		}
    		
    	});
			
		$('#id_route').on('change', function(){
		   	$('.check').unbind('ifChanged')
		   	$('#id_route option').each(function(i, e){
		   		var val = e.value
		   		if (e.selected){
		   			$('.icheck[value=' + val + ']').iCheck('check')
		   		}else{
		   			$('.icheck[value=' + val + ']').iCheck('uncheck')
		   		}
		   	})
		   	$('.check').bind('ifChanged', syncCheck)
		})
		   
		$('.icheck, .icheck-all').iCheck({
			checkboxClass: 'icheckbox_flat-blue',
    		radioClass: 'iradio_flat-blue',
		})
		$('.icheck').on({
			ifChanged: syncCheck
		})
		
		$('.icheck-all').on({
			ifChanged: function() {
				$('.icheck').iCheck(this.checked?'check':'uncheck').trigger('ifChanged')
			}
		})
		
		$('.table-responsive').slimScroll({
	        height: '60vh'
	    })

	  	$('#tabs').bootstrapWizard({
	  		onNext: function(tab, navigation, index) {
		  		return validTabsInfo(index)
			}, onTabShow: function(tab, navigation, index) {
				var $total = navigation.find('li').length;
				var $current = index+1;
				var $percent = ($current/$total) * 100;
				$('#tabs .progress-bar').css({width:$percent+'%'});
			}, onTabClick: function(tab, navigation, index) {
				return validTabsInfo(index + 1)
			}
		});
	
		function validTabsInfo(index) {
			if (index == 1) {
				if (!$('#name').val()) {
		  			swal('Alerta', "Ingrese el nombre del viaje", "warning")
		  			return false;
		  		}
	
		  		if (!$('#schedule').val()) {
		  			swal('Alerta', "Ingrese la fecha de programación", "warning")
		  			return false;
		  		}
	
		  		if (!$('#id_user').val()) {
		  			swal('Alerta', "Seleccione al vendedor del viaje", "warning")
		  			return false;
		  		}
		  	}

		  	if (index == 2) {
		  		if ($('.icheck:checked').size() == 0) {
		  			swal('Alerta', "Selecciona minimo una ruta", 'warning')
		  			return false;
		  		} 
		  		
		  		addStoresInTable()
		  		
		  	}
		  	
		  	if (index == 3) {
		  		obtenerLatLng()
		  	}
		  	
		}
					
		function syncCheck() {
			if (!$chosen) return
			var data = []
			$('.icheck:checked').each(function(i, e){
			    data.push(e.value)
			})
			
			$chosen.chosen().val(data)
			$chosen.chosen().trigger("chosen:updated")
		}
		
		function addStoresInTable() {
			var ids = []
			$('#id_route option:selected').each(function(i, e){
				ids.push(e.value)		    		
			})
			RetailServiceBean.getStoresByIdsRoute({ids: ids}, function(data){
				$tableStore.clear().draw()
				data.forEach(function(e, i){
					$tableStore.row.add([
						'<input class="items item-' + e.code + '" type="checkbox" style="display: inline-block;" data-id="' + e.id_store + '" data-id="' + e.id_store + '" id="' + e.id_store + '" value="' + (i+1) + '" data-lat="' + e.latitude + '" data-lng="' + e.longitude + '" data-name="' + e.name + '" data-address1="' + e.address1 + '" data-address2="' + e.address2 + '" data-postal="' + e.postal + '" data-shelf="' + (e.shelf == 'S'?'S':'N') + '" data-color="' + e.color + '" />',
						e.name + ' <small class="label" style="background:' + (e.color=='yellow'?'#f1c40f':e.color) + ';" data-toggle="tooltip" data-placement="bottom" title="Ruta"> ' + e.route + '</small> <i class="fa fa-star" style="color: #f1c40f; display:' + (e.shelf == 'S'?'inline-block':'none' )  + '};" data-toggle="tooltip" data-placement="bottom" title="Promoción"></i>'
					])
					//$tbody.append('<tr><td></td><td>' + </td></tr><input type="text" id="inpstr' + (i+1) + '" value="' + e.id_store + '" style="display:none;" >')
				})
				$tableStore.draw()
				$('.items').on('change', function(){
					var checked = this.checked
					if (checked) {
						addItem($(this), $('.nested-links').size() + 1)
					}else{
						delnest(this.dataset.id)
					}
					
					map.markers.forEach(function(e, i){
						var index = $('#li' + e.id).index()
                		e.setLabel('' + (index > -1?(index + 1):''))
                		e.setIcon((index == -1? e.defaultIcon: "img/icon-marker-grey.png"))
                	})
				})
				
				map.refresh()
		  		insertMarkers()
			})

		}
			
		$('#selall').on('change', function(){
			$('.items').prop('checked', this.checked).trigger('change')
		})
		
		function insertMarkers() {
			Concurrent.Thread.create(function(){
				console.log('insertando markers')
				map.removeMarkers()
				$('.items').each(function(i, e){
					var idstr = $(e).data('id');
	                var lat  = $(e).data('lat');                
	                var lng  = $(e).data('lng');                
	                var name = $(e).data('name');                
	                var id   = $(e).data('id');
	                var dir1 = $(e).data('address1');
	                var dir2 = $(e).data('address2');
	                var postal = $(e).data('postal');
	                var shelf = $(e).data('shelf');
	                var color = $(e).data('color');
	                var icon = 'img/'+ ( shelf == 'S' ? 'pin6_' + color : 'icon-marker-' + color)+'.png';
	                var l = "" + (e.checked?(i + 1):' ');
	                var contentString = '<div id="content" style="max-width:120px;">'+
	                '<div id="siteNotice"></div>'+
	                '<h5 id="firstHeading"><b>'+name+'</b></h5>'+
	                '<div id="bodyContent">'+
	                '<p>'+dir1+', '+dir2+'</p>'+
	                '</div>'+
	                '</div>';
	                var marker = map.addMarker({
	                    id: id,
	                    ind: i,
	                    lat: lat,
	                    lng: lng,
	                    defaultIcon: icon,
	                    icon: icon, 
	                    label: l,
	                    labelAnchor: new google.maps.Point(30, 30),
	                    labelClass: "labels", // the CSS class for the label
	                    labelStyle: {opacity: 1},
	                    infoWindow: {
	                    	content: contentString
	                    },
		                click: function() {
		                	$ch = $('.items#' + this.id)
		                	var checked = $ch.is(':checked')
		                	//swal('oooh ' + checked + ' ' + this.id)
		                	if (checked) {
		                		this.setLabel('')
		                		this.setIcon(this.defaultIcon)
		                	} 
		                	$ch.prop('checked', !checked).trigger('change')
		                	
	                	}
	                });
	               	markers.push(marker)
				})
				latCenter = $('.items').first().data('lat');
				lngCenter = $('.items').first().data('lng');
			    map.map.setCenter(new google.maps.LatLng(latCenter, lngCenter))
	  		})
		}
		
		$('#btn-search-code').on('click', function(){
			swal({   
    			title: "Un momento!",   
    			text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
    			html: true, 
    			showConfirmButton: false 
    		});
			var text = $('#ta-codes').val()
			var codes = []
			if (text) {
				if (text.indexOf(',') > -1) {
					codes = text.split(',')
				} else if (text.indexOf('\n') > -1) {
					codes = text.split('\n')
				} else if (text.indexOf(' ') > -1) {
					codes = text.split(' ')
				} else {
					codes = text.split()
				}
				
				console.log(codes)
				var tmp = []
				if (codes) {
					codes.forEach(function(e, i) {
						
						var $item = $('.item-' + e).first()
						if (!$item.is(':checked')){
							$item.click()
						} 
						if (!document.querySelector('.item-' + e)) {
							tmp.push(e)
						}
						console.log($item)
					})
				}
				console.log("TMP", tmp)
				if (tmp && tmp.length > 0) {
					$('#ta-codes').val(tmp.join('\n'))
					swal('Alerta', 'Codigos no encontrados', 'warning')
				} else {
					$('#ta-codes').val('')
					$('#modal-codes').toggleClass('md-show')
					swal.close()
				}
			}
			
		})
		
		var initPosition = { lat: 0, lng: 0 }
		
		$('#btnSort').on('click', function() {
			
			if ($('.dd-item').size() == 0) {
				swal('Alerta', 'Debera tener seleccionado minimo un punto para continuar', 'warning')
			} else if ($('.dd-item').size() <= 2) {
				swal('Alerta', 'Debera tener seleccionado minimo dos punto para sugerir un viaje', 'warning')				
			} else {
				swal({
					title: 'Se tomara el primer punto como punto de partida',
					text: 'Esto demorara un momento, ¿Deseá continuar?',
					showCancelButton: true,
					closeOnConfirm: false,
					showLoaderOnConfirm: true,
					confirmButtonText: 'Si',
					cancelButtonText: 'No'
				}, function() {
					Concurrent.Thread.create(function(){
						
						var $firstItem = $('.dd-item').first().find('input')
						initPosition.lat = $firstItem.data('lat')
						initPosition.lng = $firstItem.data('lng')
						var items = []
						$('.dd-item').not(':first').each(function(i,e) {
							var $item = $(e).find('input')
							items.push({
								position: {
									lat: $item.data('lat'),
									lng: $item.data('lng'),
								},
								id: $item.parent('.dd-item').data('id'),
								result: 0
							})
						})
						
						while (items.length > 1) {
							items = items.map(function(e){ 
								e.result = getDistance(initPosition, e.position)
								return e 
							})
							.sort(function(a, b) { return a.result >= b.result ? 1 : -1})
														
							items.forEach(function(e, i){
								delnest(e.id)
								$('.items#' + e.id).click()
							})
							
							initPosition.lat = items[0].position.lat
							initPosition.lng = items[0].position.lng
							
							items.splice(0, 1)
							
						}
												
						swal.close()
					
					})
				})
			}
		})
		
		function getDistances(array) {
		  array.forEach(function(e, i) {
		  
		    var distance = getDistance(initPosition, e.position, 'km')
		    e.result = distance || 0
		  
		  })
		  
		  return array
		  
		}
		
		function getDistance(start, end, units) {
		
		    var earthRadius = {
		      miles: 3958.8,
		      km: 6371
		    }
		
		    var R = earthRadius[units || 'miles']
		    var lat1 = start.lat
		    var lon1 = start.lng
		    var lat2 = end.lat
		    var lon2 = end.lng
		
		    var dLat = toRad((lat2 - lat1))
		    var dLon = toRad((lon2 - lon1))
		    var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
		      Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
		      Math.sin(dLon / 2) *
		      Math.sin(dLon / 2)
		    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
		    var d = R * c
		
		    return d
		
		}
		
		function toRad(x) {
		  return x * Math.PI / 180;
		}
		
		$('#ch-toggle-markers').on('change', function(){
			console.log('adasda')
			var $chTg = $(this)
			map.markers.forEach(function(e, i) {
				var $ch = $('[type=checkbox]#' + e.id)
				if (!$chTg.is(':checked')) {
					e.setVisible($ch.is(':checked'))
				} else {
					e.setVisible(true)
				}
			})
			
			$('.items').prop('disabled', !$chTg.is(':checked'))
			
		})
    	
    </script>
</body>
</html>