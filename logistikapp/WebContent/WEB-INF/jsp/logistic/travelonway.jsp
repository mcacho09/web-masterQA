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
<script type='text/javascript' src='dwr/interface/UserServiceBean.js'></script>
<script type='text/javascript' src='dwr/interface/UserNotificationBean.js'></script>
<script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>
<script type='text/javascript' src='dwr/interface/LogisticServiceBean.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<!--[if lt IE 9]>
            <script src="js/html5shiv.js"></script>
            <script src="js/respond.min.js"></script>
        <![endif]-->
<!-- this page specific styles -->
<style type="text/css">
#map-canvas {
	height: calc(100vh - 150px);
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
	padding: 7px 10.3px;
	border-bottom: 0px solid;
	border-radius: 50% !important;
	font-size: 15px;
	line-height: 0;
}

.btn-custom-circle2 {
	padding: 7px 7px;
	border-bottom: 0px solid;
	border-radius: 50% !important;
	font-size: 15px;
	line-height: 0;
}
.btn-custom-circle3 {
	padding: 7px 9px;
	border-bottom: 0px solid;
	border-radius: 50% !important;
	font-size: 15px;
	line-height: 0;
}

/* Diseño imagenes*/
.imgcontent{
	position: relative;
	height: 250px;
}
.imgcontent img{
	position: absolute;
	z-index:1;
	top:0;
	left:0;
	width: 100%;
	height: 100%;
}
.imgcontent .contedit{
	position: absolute;
	z-index: 2;
	top:0;
	left:0;
	width: 100%;
	height: 100%;
	color: white;
	transition: all 1.2s linear;
}
.imgcontent .contedit input[type=file]{
	position: absolute;
	left: -9999999px;
}
.imgcontent .contedit:hover{
	display: block;
	opacity: 1;
}

.imgcontent .contedit .design{
	position: relative;
	top: 35%;
	text-align: center;
	padding: 20px 10px 10px 15px;
	border: 1px solid white;
	margin-left: 20px;
	margin-right: 20px;
	background: white;
	color: black;
	opacity: 2!important;
	cursor: pointer;
}

#notify {
	position: absolute;
    right: 30px;
    top: 50px;
    z-index: 1;
    background: white;
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
									<li class="active"><span><a href="travellist.htm"><spring:message
													code="label.breadcrumb.logistic.travel" /></a></span></li>
								</ol>

								<div class="clearfix hidden-xs">
									<h1 class="pull-left">Hoja de ruta</h1>
								</div>
							</div>
						</div>

						<div class="row" id="dtsGenerales">
							<div class="col-lg-6 col-sm-6 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-truck emerald-bg box2"></i> <span
										class="headline">Viaje</span> <span class="value value2">${viaje.name}</span>
								</div>
							</div>
							<div class="col-lg-6 col-sm-6 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-user purple-bg box2"></i> <span
										class="headline">Asignado</span> <span class="value value2">${chofer.username}</span>
								</div>
							</div>
							<div class="col-lg-3 col-sm-6 col-md-6 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-calendar yellow-bg box2"></i> <span
										class="headline">Programado</span> <span class="value value2"><fmt:formatDate
											value="${viaje.schedule}" type="date" pattern="dd/MM/yyyy" /></span>
								</div>
							</div>
							<div class="col-lg-3 col-sm-6 col-md-6 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-calendar green-bg box2"></i> <span
										class="headline">Iniciado</span> <span class="value value2"><fmt:formatDate
											value="${viaje.started}" type="date" pattern="dd/MM HH:mm" /></span>
								</div>
							</div>
							<div class="col-lg-3 col-sm-6 col-md-6 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-road gray-bg box2"></i> <span class="headline">Distancia</span>
									<span><b>0 aprox</b></span>
								</div>
							</div>
							<div class="col-lg-3 col-sm-6 col-md-6 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-clock-o gray-bg box2"></i> <span
										class="headline">Tiempo</span> <span><b>00:00 aprox</b></span>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-lg-12" id="table-check">
								<div class="main-box clearfix no-header">
									<div class="main-box-body clearfix">
											<button data-toggle="tooltip" data-placement="bottom" title="Agregar cliente" class="btn btn-primary" id="addUserModal"><i class="fa fa-plus"></i> <span class="hidden-xs">Agregar cliente</span></button>
											<c:if test="${(fn:contains(useracegi.profile, 'DRI')&&(useracegi.superuser == 'S')) ||  (useracegi.superuser == 'S') || fn:contains(useracegi.profile, 'SUP')}">
												<button data-toggle="tooltip" data-placement="bottom" title="Quitar cliente" class="btn btn-primary disabled pull-right" type="button" id="quitUser"><i class="fa fa-times"></i> <span class="hidden-xs">Quitar cliente</span></button>
											</c:if>	   
											<!-- <button data-toggle="tooltip" data-placement="bottom" title="Comentario y Notas" class="btn btn-primary disabled" type="button" id="comment"><i class="fa fa-comment"></i> <span class="hidden-xs">Comentario y notas</span></button>-->
										
										<br><br>
										<div id="scrollTable" class="table-responsive" style="max-height:400px; overflow-y:auto;">
											<table id="table-example"
												class="table table-striped table-hover">
												<thead>
													<tr>
														<th style="text-align:right;">#</th>
														<th>Cliente</th>
														<th class="text-center" id="col-checkin">Checkin</th>
														<!-- th style="text-align:center;">Ubicar</th>
														<th style="text-align:center;">Quitar</th>-->
														<th class="text-center">Comentarios</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="waybill" items="${waybills}" varStatus="i">

														<input type="hidden" id="id${i.index}"
															value="${waybill.id_waybill}" />
														<input type="hidden" id="lat${i.index}"
															value="${waybill.latitude}" />
														<input type="hidden" id="lng${i.index}"
															value="${waybill.longitude}" />
														<input type="hidden" id="wbname${i.index}"
															value="${waybill.name}" />
														<input type="hidden" id="wbaddress1${i.index}"
															value="${waybill.address1}" />
														<input type="hidden" id="wbaddress2${i.index}"
															value="${waybill.address2}" />
														<input type="hidden" id="wbpostal${i.index}"
															value="${waybill.postal}" />
														<input type="hidden" id="check${i.index}"
															value="${waybill.checkin}" />
														<input type="hidden" id="shelf${i.index}"
															value="${waybill.shelf}" />
														<input type="hidden" class="waybill_ids"
															id="${waybill.id_waybill}" />
														<input type="hidden" id="comment${i.index}"
															value="${waybill.comment}"/>
														<input type="hidden" id="status${i.index }" 
															value="${waybill.status }" />
														<input type="hidden" id="outrange${i.index }" 
															value="${waybill.outrange }" />

														<tr>
															<td>
																<div class="radio seemarker" data-id="${waybill.id_waybill}" data-index="${i.index }" data-name="${ waybill.name }" data-checkin="${waybill.status }" data-comment="${waybill.comment }" data-note="${waybill.note }">
												       				<input type="radio" name="repetitionsday" id="input${waybill.id_waybill }">
														       		<label for="input${waybill.id_waybill }" style="padding-left: 22px;padding-right: 0px;"><small class="badge badge-default">${i.index+1}</small></label>
														        </div>
															</td>

															<td style="width: 350px;">
																${waybill.name}
																<c:if test="${waybill.shelf == 'S' }"><i class="fa fa-star" data-toggle="tooltip" title="Promoción" data-placement="bottom" style="color: goldenrod;"></i></c:if>
																<c:if test="${waybill.outrange == 'S' }"><i class="fa fa-exclamation-triangle" data-toggle="tooltip" title="Fuera de rango" data-placement="bottom" style="color: #e44539;"></i></c:if> 
															<br>
																<c:if test="${waybill.status == 'N'}"><span class="label label-danger"><i class="fa fa-times"></i> No Visitada</span>
																</c:if><c:if test="${waybill.status == 'S'}"><span class="label label-success"><i class="fa fa-check"></i> Visitada</span></c:if>
															</td>
															<td class="text-center" >
																<!--<c:if test="${waybill.status == 'N'}">
																	<button data-lat="${waybill.latitude }" data-lng="${waybill.longitude }" data-cliente="${ waybill.name }" type="button" onclick="javascript:checkin(${waybill.id_waybill},${waybill.orderby}, this, ${i.index });" class="btn btn-success btn-custom-circle btn-xs">
																	    <span id="chk${waybill.id_waybill}">
																	        <i class="fa fa-map-marker"></i>
																	    </span>
																	</button>
																</c:if>--> <!--
																<button onclick="javascript:addComment(${waybill.id_waybill})"><span id="cmm${waybill.id_waybill}" class="label label-info label-circle"><i class="fa fa-comment"></i></span></button>
																--> <c:set var="now" value="<%=new java.util.Date()%>" />
																<span style="display: none" class="text-primary"><fmt:formatDate
																		value="${now}" type="date" pattern="dd/MM HH:mm" /></span> <c:choose>
																	<c:when test="${waybill.status == 'N'}">
															<c:if test="${fn:contains(useracegi.profile,'DRI')}">
																		<button data-lat="${waybill.latitude }"
																			data-lng="${waybill.longitude }"
																			data-cliente="${ waybill.name }" type="button"
																			onclick="checkin(${waybill.id_waybill},${waybill.orderby}, this, ${i.index });"	
																			class="btn btn-success btn-custom-circle btn-xs" data-toggle="tooltip" data-placement="bottom" title="Checkin">
																			<span id="chk${waybill.id_waybill}"> 
																				<i class="fa fa-map-marker"></i>
																			</span>
																		</button>
															</c:if>
																	</c:when>
																	<c:otherwise>
																		<span class="text-primary"><fmt:formatDate
																				value="${waybill.checkin}" type="date"
																				pattern="dd/MM HH:mm" /></span><br>
																		<small style="cursor:pointer;" class="label label-danger btncancelcheck" data-cliente="${ waybill.name }" data-idway="${waybill.id_waybill }"><i class="fa fa-times"></i> Cancelar</small>
																	</c:otherwise>
																</c:choose>
															</td>
															<!-- <td style="text-align:center;">
																<button class="btn btn-info btn-xs btn-custom-circle2 seemarker" data-toggle="tooltip" data-placement="bottom" title="Ubicar" data-index="${i.index}"><i class="fa fa-eye"></i></button>
															</td>
															<td style="text-align:center;">
																<button data-id="${waybill.id_waybill}" data-comment="${waybill.comment }" data-note="${waybill.note }" class="btn btn-primary btn-xs btn-custom-circle2 comments" data-toggle="tooltip" data-placement="bottom" title="Comentario" data-index="${waybill.comment}"
																${fn:contains(useracegi.profile, 'SUP') ? 'disabled' : ''}>
																<i class="fa fa-comment"></i></button>
															</td>
															<td style="text-align:center;">
																<button data-id="${waybill.id_waybill}" data-index="${i.index }" data-name="${ waybill.name }" data-checkin="${waybill.status }" class="btn btn-danger btn-xs btn-custom-circle3 deleteclient" data-toggle="tooltip" data-placement="bottom" title="Quitar cliente">
																<i class="fa fa-times"></i></button>
															</td> -->
															
															<td style="text-align:center;">
																<!--<c:choose>
																	<c:when test="${waybill.comment != null || waybill.comment != ''}">
																		<span class="label label-danger"><i class="fa fa-times"></i></span>
																	</c:when>
																	<c:otherwise>
																		<span class="label label-success"><i class="fa fa-check"></i></span>
																	</c:otherwise>
																</c:choose>-->
																<button data-id="${waybill.id_waybill}" data-comment="${waybill.comment }" data-note="${waybill.note }" class="btn btn-${((waybill.comment != null && waybill.comment != '') || (waybill.note != null && waybill.note != '') || (waybill.image1 != null && waybill.image1 != '') || (waybill.image2 != null && waybill.image2 != '') || (waybill.image3 != null && waybill.image3 != ''))?'success':'default'} btn-xs btn-custom-circle2 comments" data-name="${waybill.name }" data-toggle="tooltip" data-placement="bottom" title="Comentario" data-index="${waybill.comment}">
																<i class="fa fa-comment"></i></button>
															</td>
														</tr>
														<tr class="hidden divcomment" id="divwbcomment${waybill.id_waybill}">
															<td colspan="6">
																<div style="width: 100%">
																	<div class="form-group">
																		<textarea id="commentwbarea${waybill.id_waybill}"
																			maxlength="512" class="form-control" rows="6"
																			style="resize: none;" id="message" name="message"
																			placeholder="A&ntilde;adir comentario..." cols="">${waybill.comment }</textarea>
																	</div>
																	<div class="form-group">
																		<button
																			onclick="javascript:cancelarWbComentario(${waybill.id_waybill})"
																			class="btn btn-default pull-left">
																			<i class="fa fa-times"> Cancelar</i>
																		</button>
																		<button
																			onclick="javascript:comment(${waybill.id_waybill})"
																			id="btncomment2${waybill.id_waybill}"
																			class="btn btn-success pull-right">
																			<i class="fa fa-check">Guardar</i>
																		</button>
																	</div>
																	<br>
																</div>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<form name="formTravel" id="formTravel"
											action="actionwaybill.htm" method="POST">
											<input type="hidden" id="idt" name="idt"
												value="${viaje.id_travel}"> <input type="hidden"
												id="accion" name="accion" value=""> <input
												type="hidden" id="comment" name="comment" value="">
											<input type="hidden" id="name_v" name="name_v"
												value="${viaje.name}">
										</form>

										<div class="row hidden" id="divcomment">
											<div class="form-group">
												<textarea id="commentarea" maxlength="512"
													class="form-control" rows="4" style="resize: none"
													id="message" name="message"
													placeholder="A&ntilde;adir comentario..." cols=""></textarea>
											</div>
											<div class="form-group">
												<button onclick="javascript:cancelarComentario()"
													class="btn btn-default pull-left">
													<i class="fa fa-times"></i>
												</button>
												<button onclick="javascript:finalizar()"
													class="btn btn-primary pull-right">
													<i class="fa fa-check"></i>
												</button>
											</div>
											<br>
										</div>
										<br><br>
										<div id="divbotones" class="row">
											<div class="col-xs-12">
												<button type="button"
												onclick="javascript:agregarComentario('CAN');"
												id="btnCancelar" class="btn btn-default pull-left">
													<i class="fa fa-times"></i> Cancelar
												</button>
												<button type="button" onclick="finalizarViaje();"
													id="btnFinalizar" class="btn btn-primary pull-right">
													<i class="fa fa-check"></i> Finalizar Viaje
												</button>
											</div>
										</div>

									</div>

								</div>
							</div>
							<div class="col-lg-12">
								<div class="main-box clearfix no-header">
									<div class="main-box-body clearfix" style="position: relative;">
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
										<c:if test="${useracegi.profile != 'DRI' }">
											<button id="notify" data-id="${viaje.id_user}" class="btn btn-md btn-link pull-right">
												Enviar notificación <i class="fa fa-bell-o"></i>
											</button>
										</c:if>
										<div id="map-canvas"></div>
									</div>
								</div>
							</div>
						</div>
						<!-- /row -->

					</div>
				</div>
				

				<footer id="footer-bar" class="row">
					<c:import url="/html/footer.html" />
				</footer>

				<div class="modal fade" id="modal-adduser">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header bg-primary">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title">Agregar nuevo cliente</h4>  
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-lg-12">
										<div class="table-responsive">
										Buscar Cliente
										<form action="javascript:buscar();">
												<div class="input-group">
														<input id="isearch" MaxLength="50" onClick="this.value='';" type="text" name="searchByName" value="${searchByName}" class="form-control input-lg" />
														<div class="input-group-btn">
															<button class="btn btn-lg btn-primary visible" type="button" onclick="javascript:buscar();">
																<i class="fa fa-search"></i> Buscar
															</button>
														</div>
												</div>
										</form>
											<table class="table table-hover" id="newStore">
												<thead>
													<tr>
														<th>Nombre</th>
														<th></th>
													</tr>
												</thead>
												<tbody>
												<!-- 
												<c:forEach var="str" items="${storeavailable}">
													<tr>
														<td style="width:90%;">${str.name }</td>
														<td><button data-toggle="tooltip" data-placement="bottom" title="Agregar" type="button" onclick="addUser('${str.id_store}', '${ str.name}', '${str.latitude}', '${str.longitude }')" class="btn btn-primary"><i class="fa fa-plus"></i> <span class="hidden-xs">Agregar</span></button></td>
													</tr>
												</c:forEach>-->
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
								<button type="button" class="btn btn-primary">Guardar</button>
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal fade" id="modal-comments">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header bg-primary">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="comment-title"></h4>
							</div>
							<div class="modal-body">
							<c:if test="${useracegi.profile == 'DRI' }">
								<div class="row hidden">
									<div class="col-xs-12">
										<button type="button" class="btn btn-primary btn-md pull-right" data-toggle="tooltip" data-placement="left" title="Adjuntar imagen"><i class="fa fa-camera"></i></button>
									</div>
								</div>
							</c:if>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-horizontal">
												<div class="form-group">
													<label for="textareaComment" class="col-sm-2 control-label">Movimientos:</label>
													<div class="col-sm-9">
														<textarea maxlength="255" name="comment" id="tcomment" class="form-control" style="resize:none;" rows="3" required="required" ${useracegi.profile == 'DRI'?'':'disabled' }></textarea>
													</div>
												</div>
											<div class="form-group">
												<label for="textareaNote" class="col-sm-2 control-label">Comentarios:</label>
												<div class="col-sm-9">
													<textarea maxlength="255" name="note" id="tnote" class="form-control" style="resize:none;" rows="3" required="required" ${useracegi.profile == 'DRI'?'':'disabled' }></textarea>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-4 imgcontent" style="padding:0!important;">
										<img class="img-responsive" id="imgCont1" src="img/img_default.jpg" style="width:100%; height:250px;">
										<c:if test="${useracegi.profile == 'DRI' }">
										<div class="contedit">
											<div class="design">
												<i class="fa fa-arrow-up fa-lg"></i>
												<p>Subir imágen<p>
											</div>
											<input type="file" data-index="1" accept="image/*" name="image" id="img1">
										</div>
										</c:if>
									</div>
									<div class="col-lg-4 imgcontent" style="padding:0!important;">
										<img class="img-responsive" id="imgCont2" src="img/img_default.jpg" style="width:100%; height:250px;">
										<c:if test="${useracegi.profile == 'DRI' }">
										<div class="contedit">
											<div class="design">
												<i class="fa fa-arrow-up fa-lg"></i>
												<p>Subir imágen<p>
											</div>
											<input type="file" data-index="2" accept="image/*" name="image" id="img2">
										</div>
										</c:if>
									</div>
									<div class="col-lg-4 imgcontent" style="padding:0!important;">
										<img class="img-responsive" id="imgCont3" src="img/img_default.jpg" style="width:100%; height:250px;">
										<c:if test="${useracegi.profile == 'DRI' }">
										<div class="contedit">
											<div class="design">
												<i class="fa fa-arrow-up fa-lg"></i>
												<p>Subir imágen<p>
											</div>
											<input type="file" data-index="3" accept="image/*" name="image" id="img3">
										</div>
										</c:if>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" id="closemodal" data-dismiss="modal">Cerrar</button>
								<c:if test="${useracegi.profile == 'DRI' }">
									<button type="button" class="btn btn-primary" onclick="saveComment();" id="savecomment">Guardar</button>
								</c:if>
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
    <script src="js/dataTables.fixedHeader.js"></script>
    <script src="js/dataTables.tableTools.js"></script>
    <script src="js/jquery.dataTables.bootstrap.js"></script>

	<!-- this page specific inline scripts -->
	<script type="text/javascript">
	
		var map;
	    var markers = [];
	    var myLat = 0;
		var myLng = 0;
		var myMarker;
		var locationCoordsArray = [];

		function buscar(){
			var availablecriteria = new Object();
			availablecriteria.id_supplier = ${id_sup}
			availablecriteria.id_travel = ${id_travel}
			availablecriteria.store_name = document.getElementById('isearch').value
			
			RetailServiceBean.getStoreAvailableInTravel(availablecriteria,function(dato){
				var table = $('#newStore').DataTable();
				var rows = table
			    .rows()
			    .remove()
			    .draw();
				
				dato.forEach(function(y){
					table.row.add([y.name,"<button data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Agregar\" type=\"button\"  onclick=\"addUser(\'"+y.id_store+"\',\'"+y.name+"\',\'"+y.latitude+"\',\'"+y.longitude+"\')\" class=\"btn btn-primary\"><i class=\"fa fa-plus\"></i><span class=\"hidden-xs\"> Agregar</span></button>"]).draw(false);
				});
				
			});
		}
		
		var latCenter = document.getElementById("lat0").value;
        var lngCenter = document.getElementById("lng0").value;
		
		map = GMaps({
        	el: "#map-canvas",
        	zoom: 18,
    		center: new google.maps.LatLng(latCenter,lngCenter),
        })
		
        $('#newStore').dataTable({
	            'paging': true,
	            'info': false,
	            'searching': false,
	            "language": {
	                "search": "Buscar Cliente:",
	                "zeroRecords":    "No se encontraron clientes",
	                "lengthMenu": "Mostrar _MENU_",
	            },            
	            'pageLength': 5,
	            "aoColumnDefs" : [{
	                "bSortable" : false,
	            }],
	            "columnDefs": [{ 
	            	"targets": [1], 
	            	//"searchable": false, 
	            	"orderable": false, 
	            	"visible": true 
	            }]
	        }); // table1 
	        
        	$($('#newStore_paginate').parent().parent().find('div')[1]).removeClass('col-xs-6');
        	$($('#newStore_paginate').parent().parent().find('div')[1]).addClass('col-xs-12');
        	$('.seemarker').on('click', function(){
        		
        		map.markers.forEach(function(e, i){
        			e.infoWindow.close()
        		})
            		
            	map.markers[$(this).data('index')].infoWindow.open(map, markers[$(this).data('index')]);            			
            	map.map.setCenter(locationCoordsArray[$(this).data('index')])
            });
       	
        	myMarker = map.addMarker({                   
                position: new google.maps.LatLng(0,0),
                icon: 'img/icon-marker-blue.png',
                labelAnchor: new google.maps.Point(50, 51),
                labelClass: "labels", // the CSS class for the label
                labelStyle: {opacity: 1},
                title: 'Mi posición actual',
				infoWindow: {
				  content: '<div id="content">'+
				            '<div id="bodyContent">'+
				            '<p><span>Mi posición actual</span></p>' +
				            '</div>'+
				            '</div>'
				}
            })
            
            locationCoordsArray = [];
           
            for ( var i=0; i<${fn:length(waybills)}; i++ ) {              
                var id = document.getElementById("id"+i).value;	
                var lat = document.getElementById("lat"+i).value;
                var lng = document.getElementById("lng"+i).value;
                var name = document.getElementById("wbname"+i).value;
                
                var address1 = document.getElementById("wbaddress1"+i).value;
                var address2 = document.getElementById("wbaddress2"+i).value;
                var postal = document.getElementById("wbpostal"+i).value;
                var checkin = document.getElementById("check"+i).value;
                locationCoordsArray.push(new google.maps.LatLng(lat,lng));
                var comment = document.getElementById("comment"+i).value;
                var status = document.getElementById("status"+i).value;
                var outrange = document.getElementById("outrange"+i).value;
                
                var contentString = '<div id="content" style="max-width:150px;">'+
                '<div id="siteNotice"></div>'+
                '<span id="firstHeading"><b>'+name+'</b></span>'+
                '<div id="bodyContent">'+
                '<p><span>'+address1+'<br/>'+address2+'</span></p>'
                /*'<p>CP <b>'+postal+'</b></p>'+*/
                '</div>'+
                '</div>';
                
                var l = i+1 +"";
                var shelf = document.getElementById("shelf"+i).value;
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
            obtenerDistancia(locationCoordsArray);
            
        	if (${fn:contains(useracegi.profile, 'DRI')}){
        		if (navigator.geolocation){
	        		var options = {
	    	    		enableHighAccuracy: true, 
	    	    		maximumAge        : 30, 
	    	    		timeout           : 27000
	    	    	};
	        		
	        		var circle = map.drawCircle( {
	                    strokeColor: "#e74c3c",
	                    strokeOpacity: 0.8,
	                    strokeWeight: 0,
	                    fillColor: "red",
	                    fillOpacity: 0.20,
	                    map: map,
	                    radius: 30
	                });
        		
	                circle.bindTo('center', myMarker, 'position');
	
	     	    	navigator.geolocation.getCurrentPosition(function(position){
	     	    		myLat = position.coords.latitude;
	    	    		myLng = position.coords.longitude;
	    	    		
		                myMarker.setPosition(new google.maps.LatLng(myLat,myLng));
		                map.setCenter(new google.maps.LatLng(myLat,myLng));
		                map.setZoom(18);
	     	    	},function(){swal('Alerta','No se puede obtener su posición actual','warning');});
	
	    	    	
					navigator.geolocation.watchPosition(myPosition,function(){},options);
	    	    	
	    	    	function myPosition(position){
	    	    		myLat = position.coords.latitude;
	    	    		myLng = position.coords.longitude;
	    	    		
		                myMarker.setPosition(new google.maps.LatLng(myLat,myLng));	
	    	    	}
	        	}else{
	    	    	swal('Alerta','Checkin no puede ser efectuado en este momento','warning');
	    	    }
    	    }
        	
        var id_waybill;
        var orderby;
        
        function checkin(id_wb,ob, btn, index) {
        	id_waybill = id_wb;
        	orderby = ob;
        	var WayBill = new Object();
        	WayBill.id_waybill = id_waybill;
        	WayBill.checkin = new Date();
        	WayBill.status = "S";
        	WayBill.latitude = myLat;
        	WayBill.longitude = myLng;
        	
        	var service = new google.maps.DistanceMatrixService();
            service.getDistanceMatrix({
            	origins: [new google.maps.LatLng(myLat, myLng)],
                destinations: [new google.maps.LatLng($(btn).data('lat'),$(btn).data('lng'))],
                travelMode: google.maps.TravelMode.DRIVING,
                drivingOptions: {
                	departureTime: new Date(Date.now()),
                	trafficModel: "pessimistic"
                },
                unitSystem: google.maps.UnitSystem.METRIC
            }, function (response, status) {
                	
            	    if (status == google.maps.DistanceMatrixStatus.OK) {
                	    	
            	    	    var element = response.rows[0].elements[0];
            	    	    if (element != undefined){
	            	    	    var dist = element.distance.value;
	            	          	//console.log(element.distance.value + ' ' + element.distance.text);
	                      	
	                			// Se controla si se quiere hacer el checkin aun estando fuera de rango
		                     	// o bien cuando está dentro del rango de 30 mts permitido
		                     	if ( dist > 30 ) {
		                     		WayBill.outrange = "S";
		                     		swal({   
		                     			title: "Alerta",   
		                     			text: "Estás fuera del rango permitido para hacer checkin.\n¿Deseas continuar?",   
		                     			type: "warning",   
		                     			showCancelButton: true,   
		                     			confirmButtonColor: "#3498db",
		                     			cancelButtonColor: '#e74c3c',
		                     			confirmButtonText: "Si, continuar",
		                     			calcelButtonText: "No, cancelar",
		                     			closeOnConfirm: false 
		                     		}, function(){
		                     			LogisticServiceBean.updWayBillById(WayBill, function(dato){
		                     				if (dato >= 1){
		                     					swal({
		                    						title: "Exito!", 
		                    						text: "Checkin realizado", 
		                    						type: "success",
		                    						showConfirmButton: false,
		                    						timer: 2000
		                    					});
				                     			var AddNotificationDTO = new Object();
				    	                     	AddNotificationDTO.created = new Date();
				    	                     	AddNotificationDTO.icon = 'fa fa-map-marker';
				    	                     	AddNotificationDTO.id_user = ${useracegi.id_user};
				    	                     	AddNotificationDTO.message = 'Checkin en <span class="label label-warning">' + $(btn).data('cliente') + '</span> fuera del rango en el viaje <b>${viaje.name}</b>';
				    	                     	AddNotificationDTO.priority = '1';
				    	                     	AddNotificationDTO.id_supplier = ${id_sup};
				    	                     	AddNotificationDTO.link = "travelonway.htm?idt=${viaje.id_travel}";
				    	                     	// Se persiste el objeto para agregar notificacion
				    	                     	UserNotificationBean.createNotification(AddNotificationDTO, "003", function(data){
				    	                     		if (data > 0){
				    	                     			var list = [];
				    	                     			list.push(${viaje.id_user});
				    	                     			UserNotificationBean.createNotificationWithIdNotification(data, list);
				    	                     		}
				    	                     	});
				    	                     	/*$($(btn).parent().parent().find('td')[1]).find('span').remove();
				    	                     	//$($(btn).parent().parent().find('td')[2]).find('small').css('display','block');
				    	                     	$($(btn).parent().parent().find('td')[2]).html('<small style="cursor:pointer;" onclick="cancelCheckin()" class="label label-danger btncancelcheck" data-idway="' + id_waybill + '"><i class="fa fa-times"></i> Cancelar</small>');
				    	                     	var shelf = document.getElementById("shelf"+index).value;
				    	                        var icono = ( shelf == 'S' ? 'img/pin6_green.png' : 'img/icon-marker-green.png');//Agregado de validacion con campo Shelf
				    	                     	markers[index].setIcon(icono);
				    	                     	$(btn).remove();*/
				    	                     	setTimeout(function(){
				    	                     		location.reload();
				    	                     	}, 500);
		                     				}else{
		                     					swal("Error!", "Checkin no realizado", "error"); 
		                     				}
		                     			});	                     			
		                     		});
		                     	}else{
		                     	
		                     		// Se persiste el objeto para realizar checkin
		                     		LogisticServiceBean.updWayBillById(WayBill, function(data){
		                     			if (data >= 1){
		                     				swal("Exito!", "Checkin realizado", "success"); 
		                     				$($(btn).parent().parent().find('td')[3]).find('span').removeClass('label-danger').addClass('label-success').html('<i class="fa fa-check"></i> Visitada');
		                     				$($(btn).parent().parent().find('td')[2]).find('small').show();
		                     				$(btn).css('display','none');
		                     				var shelf = document.getElementById("shelf"+index).value;
		                                    var icono = ( shelf == 'S' ? 'img/pin6_green.png' : 'img/icon-marker-green.png');//Agregado de validacion con campo Shelf
			    	                     	markers[index].setIcon(icono);
		                     			}else{
		                     				swal("Error!", "Checkin no realizado", "error");
		                     			}
		                     		});
		                     			
		                     		// Una vez realizado el checkin se recarga la página
		                     		//location.reload();
		                     	}
            	    	    }else{
            	    	    	swal("Error!", "Checkin no realizado, Error al obtener distancia", "error");
            	    	    }
                    } // status == OK
                });
            
        } // checkin
        
        var reply1 = function(data) {
        	// funcion para controlar la respuesta al persistir objeto para el checkin
        	
        	    if ( data >= 1) {
        	    	var shelf = document.getElementById("shelf"+markers[orderby-1].id).value;
                    var icono = ( shelf == 'S' ? 'img/pin6_green.png' : 'img/icon-marker-green.png');//Agregado de validacion con campo Shelf
        	    	    markers[orderby-1].setIcon(icono);
        	    	    $("#chk"+id_waybill).prop('disabled', true);
        	    }
       	}; // reply1
        	    
       	function agregarComent(element){
       		$('.divcomment').each(function(i,e){
       			if (!$(e).hasClass('hidden')){
       				$(e).addClass('hidden');
       			}
       		});
       		$("#"+element).toggleClass("hidden");
       	}
        	    
        	
       	function agregarComentario() {
            $( "#divcomment" ).removeClass( "hidden" );
            $( "#divbotones" ).addClass( "hidden" );
            document.getElementById("commentarea");
            }
       	
        function cancelarComentario() {
            $( "#divcomment" ).addClass( "hidden" );
            $( "#divbotones" ).removeClass( "hidden" );
            document.getElementById("commentarea").value = "";
            }

        function finalizar() {
        	    document.getElementById("comment").value = document.getElementById("commentarea").value;
        	    document.getElementById("formTravel").submit();
        	    }
        
        var id_waybill2;
        function addComment(id_waybill) {
            $("#divwbcomment"+id_waybill).removeClass("hidden");
            LogisticServiceBean.getWayBillById(id_waybill,reply4);
            id_waybill2 = id_waybill;
            }
        function reply4(data) {
        	    document.getElementById("commentwbarea"+id_waybill2).value = data.comment;        	
            };
            
        function cancelarWbComentario(id_waybill) {
        	    $("#divwbcomment"+id_waybill).addClass("hidden");
            }
        
        function finalizarWb(id_waybill){
        	var comment = document.getElementById("commentwbarea"+id_waybill).value;
        	var WayBill = new Object();
        	id_waybill2 = id_waybill;
            WayBill.id_waybill = id_waybill;
            WayBill.comment= comment;
            LogisticServiceBean.updWayBillById(WayBill,reply3);
            
        }
        var reply3 = function(data) {
            if (data >= 1)
            {
            	
                $("#divwbcomment"+id_waybill2).addClass('hidden');         
                
            }
        };
        
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
        			for (var i = 0; i < orgTmp.length; i++) {
        				var element = response.rows[i].elements[i];
        			   	var distance = element.distance.text;
        			   	var duration = element.duration.text;
        			   	km += element.distance.value;
        			   	time += element.duration.value;
        			}
        			$('#distance').html('<b>' + (km/1000) + " KMS</b>");
        			$('#time').html('<b>' +  getTime(time) + " Hrs</b>");
        		} // status
        	} // callback
        	
        	
        }
       	function getTime(x){
       		var hours = parseInt( x / 3600 ) % 24;
       		var minutes = parseInt( x / 60 ) % 60;
       		var seconds = x % 60;
       		return (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes);
       	} // gettime
        
        function finalizarViaje(){
        	swal({   
        		title: "Alerta",   
        		text: "¿Está seguro que desea finalizar el viaje?",   
        		type: "warning",
        		showCancelButton: true,   
        		confirmButtonColor: "#DD6B55",   
        		confirmButtonText: "Si",
        		cancelButtonText: "No",
        		closeOnConfirm: false 
        	}, function(){		
	        	$('#accion').val('FIN');
    	    	$('#formTravel').submit();
        	});
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
							target: "table-check",
							title: "Tabla de checkin",
							content: "Aquí se muestran los clientes a los cuales se les realizara checkin",
							placement: "top",
							xOffset: ($('#table-check').width() / 2) - 30
						},
						{
							target: "col-checkin",
							title: "Botones checkin",
							content: "En esta columna se muestran los botones de checkin para realizar el chequeo de los clientes",
							placement: "top",
							zindex: 999,
							xOffset: 15
						},
						{
							target: "col-estado",
							title: "Columna de estado",
							content: "En esta columna se muestra el estado del checkin si es o no visitada",
							placement: "top",
							zindex: 999,
							xOffset: 15
						},
						{
							target: "map-canvas",
							title: "Mapa",
							content: "Aquí se muestran las ubicaciones mapeadas de los clientes",
							placement: "left",
							zindex: 999
						},
						{
							target: "btnFinalizar",
							title: "Finalizar viaje",
							content: "Clic para finalizar el viaje cuando este ha sido concluido",
							placement: "right",
							zindex: 999,
							yOffset: -15
						}
					],
					showPrevButton: true
				};
			hopscotch.startTour(tour);
		});
    	var newLat = 0;
    	var newLon = 0;
    	var newStore = "";
    	function addUser(id_store, name, lat, lon){
    		newLat = lat;
    		newLon = lon;
    		newStore = name;
    		var WayBill = new Object();
    		WayBill.id_store = id_store;
    		WayBill.id_travel = ${id_travel};
    		WayBill.log_created = new Date();
    		WayBill.log_created_login = "${useracegi.login}";
    		WayBill.orderby = ${fn:length(waybills) + 1};
    		WayBill.status = "N";
    		
    		LogisticServiceBean.addWayBill(WayBill, function(data){
    			if (data >= 1){
    				var array = locationCoordsArray;
    				array.push(new google.maps.LatLng(newLat, newLon));
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
    	        			var Travel = new Object();
    	        			Travel.id_travel = ${id_travel};
    	        			Travel.km = km;
    	        			Travel.time = (time * 60);
    	        			LogisticServiceBean.updTravelById(Travel);
    	        		} // status
    	        	} // callback
    				swal({
    					title : "Exito",
        				text : "Exito al agregar cliente a la ruta",
        				type : "success",
        				showConfirmButton: false,
    	    			timer : 5000
    				});
    				var AddNotificationDTO = new Object();
	             	AddNotificationDTO.created = new Date();
	             	AddNotificationDTO.icon = 'fa fa-map-marker';
	             	AddNotificationDTO.id_user = ${useracegi.id_user};
	             	AddNotificationDTO.message = 'Se agrego al cliente <span class="label label-warning">' + newStore + '</span> en el viaje <b>${viaje.name}</b>';
	             	AddNotificationDTO.priority = '1';
	             	AddNotificationDTO.id_supplier = ${id_sup};
	             	AddNotificationDTO.link = "travelonway.htm?idt=${viaje.id_travel}";
	             	// Se persiste el objeto para agregar notificacion
	             	if ("${userace.profile}" == 'DRI'){
		             	UserNotificationBean.createNotification(AddNotificationDTO, "002", function(data){
		             		if (data >= 1){
		             			setTimeout(function(){ location.reload(); }, 1000);
		             		}
		             	});
	 				}else{
	 					var list = [];
	 					list.push(${useracegi.id_user});
	 					UserNotificationBean.createNotificationWithList(AddNotificationDTO, list, function(data){
	 						if (data >= 1){
		             			setTimeout(function(){ location.reload(); }, 1000);
		             		}
	 					});
	 				}
    			}else{
    				swal({
    					title : "Error",
        				text : "Error al agregar cliente a la ruta",
        				type : "error",
        				showConfirmButton: false,
    	    			timer : 1000
    				});
    			}
    			
    		});
    		
    	}
    	
    	/*$('#newStore').dataTable({
            'paging': true,
            'info': false,
            //'searching': true,
            "language": {
              //  "search": "Buscar Cliente:",
                "zeroRecords":    "No se encontraron clientes",
                "lengthMenu": "Mostrar _MENU_",
            },            
            'pageLength': 5,
            "aoColumnDefs" : [{
                "bSortable" : false,
            }],
            "columnDefs": [{ 
            	"targets": [1], 
            	//"searchable": false, 
            	"orderable": false, 
            	"visible": true 
            }]
        }); // table1 */	
            
        $('.btncancelcheck').on('click', cancelCheckin);
        function cancelCheckin(){
        	var id = $(this).data('idway');
        	swal({   
        		title: "Alerta",   
        		text: "¿Está seguro que desea cancelar el checkin?",   
        		type: "warning",
        		showCancelButton: true,   
        		confirmButtonColor: "#DD6B55",   
        		confirmButtonText: "Si",
        		cancelButtonText: "No",
        		closeOnConfirm: false 
        	}, function(){   
        		var WayBill = new Object();
        		WayBill.id_waybill = id;
        		WayBill.longitude = 0;
        		WayBill.latitude = 0;
        		WayBill.status = "N";
        		WayBill.outrange = 'N';
        		WayBill.checkin = '';
        		LogisticServiceBean.updWayBillById(WayBill, function(dato){
     				if (dato >= 1){
     					swal('Exito', "Se cancelo el check-in", "success");
     				}else{
     					swal("Error", "No se logró cancelar el check-in", "error");
     				}
     				
     				setTimeout(function(){ location.reload(); }, 2000);
                });
        	});
        }
            
        var actual_store = 0;
        $('.comments').on('click', function(){
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
        
        /*$('#closemodal').on('click', function(){
        	location.reload();
        });*/
        
        function saveComment(){
        	if (document.getElementById('tcomment').value.length >= 1 || document.getElementById('tnote').value >= 1){
        		var WayBill = new Object();
            	WayBill.id_waybill = actual_store;
            	WayBill.comment = document.getElementById('tcomment').value;
            	WayBill.note = document.getElementById('tnote').value;
            	LogisticServiceBean.updWayBillById(WayBill, function(dato){
     				if (dato >= 1){
     					swal('Exito', "Se agrego el comentario con exito", "success");
     					$('#closemodal').click();
     					setTimeout(function(){
     						location.reload();
     					}, 1000);
     				}else{
     					swal("Error", "No se logró agregar el comentario\nIntente más tarde", "error");
     				}
                });
        	}else{
        		swal('Alerta', "Los campos de comentario y nota estan vacios", "warning");
        	}
        }
    	function quitClient(checkin, id, name, index){
    		if ($('.radio').size() > 1){
    			if (checkin == 'S'){
    				swal('Alerta', 'No se puede quitar un cliente que ya tiene check-in', 'warning');
    			}else{
    				var id = id
            		var store = name;
            		var index = index;
                	swal({   
                		title: "Alerta",   
                		text: "¿Está seguro que desea quitar el cliente del viaje ?",   
                		type: "warning",
                		showCancelButton: true,   
                		confirmButtonColor: "#DD6B55",   
                		confirmButtonText: "Si",
                		cancelButtonText: "No",
                		closeOnConfirm: false 
                	}, function(){   
        	        	LogisticServiceBean.delWayBillById(id, function(dato){
        	 				if (dato >= 1){
        	 					var array = locationCoordsArray;
        	 					array = array.splice(index, 1);
        	    				array.push(new google.maps.LatLng(newLat, newLon));
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
        	    	        			var Travel = new Object();
        	    	        			Travel.id_travel = ${id_travel};
        	    	        			Travel.km = km;
        	    	        			Travel.time = (time * 60);
        	    	        			LogisticServiceBean.updTravelById(Travel);
        	    	        		} // status
        	    	        	} // callback
        	 					swal('Exito', "Se elimino el cliente con exito", "success");
        	 					
        	 					var AddNotificationDTO = new Object();
        		             	AddNotificationDTO.created = new Date();
        		             	AddNotificationDTO.icon = 'fa fa-map-marker';
        		             	AddNotificationDTO.id_user = ${useracegi.id_user};
        		             	AddNotificationDTO.message = 'Se elimino al cliente <span class="label label-danger">' + store + '</span> en el viaje <b>${viaje.name}</b>';
        		             	AddNotificationDTO.priority = '1';
        		             	AddNotificationDTO.id_supplier = ${id_sup};
        		             	AddNotificationDTO.link = "travelonway.htm?idt=${viaje.id_travel}";
        		             	// Se persiste el objeto para agregar notificacion
        		             	if ("${userace.profile}" == 'DRI'){
	        		             	UserNotificationBean.createNotification(AddNotificationDTO, "002", function(data){
	        		             		if (data >= 1){
	        		             			setTimeout(function(){ location.reload(); }, 1000);
	        		             		}
	        		             	});
	        	 				}else{
	        	 					var list = [];
	        	 					list.push(${useracegi.id_user});
	        	 					UserNotificationBean.createNotificationWithList(AddNotificationDTO, list, function(data){
	        	 						if (data >= 1){
	        		             			setTimeout(function(){ location.reload(); }, 1000);
	        		             		}
	        	 					});
	        	 				}
        					}else{
        						swal("Error", "No se logró quitar el cliente\nIntente más tarde", "error");
        					}
        	          });
                	});
    			}
    		}else{
    			swal('Alerta', "No puede dejar el viaje sin clientes", "warning");
    		}
    	}
    	
    	var check = "";
    	var id = 0;
    	var name = "";
    	var index = 0;
    	var comment = "";
    	var note = "";
    	
    	$('.radio').on('click', function(){
    		check = $(this).data('checkin');
    		id = $(this).data('id');
    		name = $(this).data('name');
    		index = $(this).data('index');
    		comment = $(this).data('comment');
    		note = $(this).data('note');
    		
    		if ($('input[type="radio"]:checked').size() > 0){
    			$('#quitUser').removeClass('disabled');
    			$('#comment').removeClass('disabled');
    		}
    	});
    	
    	$('#quitUser').on('click', function(){
    		quitClient(check, id, name, index);
    	});
    	
    	$('#addUserModal').on('click', function(){
    		$('#modal-adduser').modal('show');
    	})
    	
    	$('.design').on('click', function(){
    		var file = $($(this).parent().find('input[type=file]'));
    		$(file).click();
    	});
    	
    	$('input[type=file]').on('change', function(){
    		var file = this;
    		var img = $(this).parent().parent().find('img')[0];
    		var index = $(this).data('index');
    		swal({
    			title: '¿Guardar imagen?',
    			text: '¿Desea guardar la imagen seleccionada?',
    			type: 'info',
    			showConfirmButton: true,
    			showCancelButton: true,
    			confirmButtonText: 'Si',
    			cancelButtonText: "No",
    			closeOnConfirm: false,
    			showLoaderOnConfirm: true
    		}, function(){
	    		if (file.files && file.files[0]){
	    			var fr = new FileReader();
	    			fr.onload = function(e){
	    				img.src = e.target.result;
	    				var string = e.target.result;
	    				var WayBill = new Object();
	    				WayBill.id_waybill = actual_store;
	    				if (index == 1){
	    					WayBill.image1 = string;
	    				}else if (index == 2){
	    					WayBill.image2 = string;
	    				}else if (index == 3){
	    					WayBill.image3 = string;
	    				}
	    				console.log(WayBill);
	    				LogisticServiceBean.updWayBillById(WayBill,function(data){
	    					if (data > 0){
	    						swal('Exito', 'Se guardo la imágen con exito', 'success');
	    					}else{
	    						swal('Error', 'No se pudo guardar la imágen con exito\nIntentelo más tarde', 'error');
	    					}
	    				});
	    			}
		    		fr.readAsDataURL(file.files[0]);
	    		}
    		});
    	});
    	
    	Concurrent.Thread.create(function() {
    		LogisticServiceBean.getUserPositionByIdTravel(${viaje.id_travel}, function(data) { 
    			
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
    	
    	$('#notify').on('click', function(){
    		var btn = this
    		swal({
    			title: "Enviar notificación",
    			text: "Ingrese el texto:",
    			type: "input",
    			showCancelButton: true,
    			closeOnConfirm: false,
    			animation: "slide-from-top",
    			inputPlaceholder: "Notificación"
    		}, function(inputValue){
    			if (inputValue === false) return false;
    			  
    			if (inputValue === "") {
    				swal.showInputError("Ingrese alguna palabra!");
    				return false
    			}
    			  
    			var obj = {created: Date.now(), icon: 'fa fa-bell-o', id_user: ${useracegi.id_user}, message: inputValue, priority: 1, id_supplier: ${useraccess.id_supplier}, link: "alertlist.htm"}
    			var ids = [btn.dataset.id]
    			UserNotificationBean.createNotificationWithList(obj, ids, function(data) {
    				if (data == 0) {
    					swal('Error', "Notificación no enviada\nIntente más tarde", 'error')
    				} else {
    					swal('Exito', 'Notificación enviada', 'success')
    				}
    			})
    		});
    	})
    	
    </script>

</body>

</html>