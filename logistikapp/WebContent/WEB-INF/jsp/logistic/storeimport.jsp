<%@ include file="/includes/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Importar - Locales</title>

<!-- bootstrap -->
<link rel="stylesheet" type="text/css"
	href="css/bootstrap/bootstrap.min.css" />

<!-- libraries -->
<link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
<link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />
<link rel="stylesheet" href="css/libs/dropzone.css" type="text/css" />

<!-- global styles -->
<link rel="stylesheet" type="text/css"
	href="css/compiled/theme_styles.css">


<!-- this page specific styles -->
<link rel="stylesheet" type="text/css" href="css/logistikapp.css">

<!-- Favicon -->
<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />

<!-- Dwr script -->
<script type='text/javascript' src='dwr/interface/UserServiceBean.js'></script>
<script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>


<!-- google font libraries -->
<link
	href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400'
	rel='stylesheet' type='text/css'>

<!--[if lt IE 9]>
			<script src="js/html5shiv.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->

<style type="text/css">
.dropzone {
	min-height: 200px;
}

table thead tr th {
	margin: 0;
	padding: 0;
}
</style>
</head>

<body class="fixed-header fixed-leftmenu theme-blue">

	<c:set var="user_id_modal" value="0" />
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
									<li><span><spring:message code="label.breadcrumb.logistic" /></span></li>
									<li><span><a onclick="javascript:history.back();" style="cursor:pointer;"><spring:message code="label.breadcrumb.logistic.retail" /></a></span></li>
									<li class="active"><span><c:out value="${retail.name}" /></span></li>
								</ol>

								<div class="clearfix col-lg-6">
									<h1 class="pull-left">
										<a href="#">Importar</a>
									</h1>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div class="main-box clearfix col-lg-12">

									<header class="main-box-header clearfix">
										<div class="btn-group">
											<button type="button" onclick="location.href='store.htm?id=${id_retail}';" class="btn btn-primary">
												<i class="fa fa-arrow-left"></i> Volver
											</button>
										</div>
										<div class="btn-group">
											<a class="btn btn-primary" href="./downloads/importar.xls" id="btnPlantilla">
												<i class="fa fa-download"></i> Plantilla
											</a>
										</div>
									</header>

									<div class="main-box-body clearfix">

										<!-- ======================================================================================== -->
										<div class="row form-group">
											<div class="col-xs-4">
												Estado: <select id="state" name="state" class="form-control">
													<c:forEach var="stt" items="${list_state}">
														<option value="${stt.id_state}"
															<c:out value="${id_state==stt.id_state ? 'selected' : ''}"/>><c:out
																value="${stt.name}" /></option>
													</c:forEach>
												</select>
											</div>
											<div class="col-xs-4">
												Municipio: <select id="city" class="form-control">
												</select>
											</div>
											<div class="col-xs-4">
												Categoría: <select id="categoria" name="categoria"
													class="form-control">
													<c:forEach var="ctg" items="${list_category}">
														<option value="${ctg.id_store_category}"
															<c:out value="${id_categoria == ctg.id_store_category ? 'selected':''}"/>><c:out
																value="${ctg.name}" /></option>
													</c:forEach>
												</select>
											</div>
										</div>
										<form id="myAwesomeDropzone" class="dropzone dz-clickable"
											method="post" action="importstore.htm"
											enctype="multipart/form-data" style="width: 100%;">
											<input type="hidden" name="id_state" id="id_state" /> <input
												type="hidden" name="id_city" id="id_city" /> <input
												type="hidden" name="id_categoria" id="id_categoria" /> <input
												type="hidden" name="readFile" id="readFile" value="true" />
											<div class="dz-default dz-message">
												<span>Drop files here to upload</span>
											</div>
										</form>
										<form method="post" id="form1">
											<input type="hidden" name="id_retail" id="id_retail"
												value="${id_retail}" /> <input type="hidden"
												name="importar" id="importar" value="true" /> <input
												type="hidden" name="userlogin" id="userlogin"
												value="${useracegi.userlogin}" />
											<!-- ================================================================================= -->
											<c:if
												test="${fn:length(storeNoValid) != 0 || fn:length(storeFileError) != 0}">
												<c:if test="${fn:length(storeNoValid) != 0}">
													<div class="alert alert-danger">
														<i class="fa fa-times-circle fa-fw fa-lg"></i> <strong>Verificar
															formato de archivo.</strong><br> <i
															class="fa fa-times-circle fa-fw fa-lg"></i> <strong>Verificar
															que ningún código a importar ya exista.</strong>
													</div>
												</c:if>

												<!-- Datos incorrectos -->

												<table class="table table-striped table-hover">
													<thead>
														<tr>
															<th><span>C&oacute;digo</span></th>
															<th><span>Local</span></th>
															<th><span>Direcci&oacute;n 1</span></th>
															<th><span>Direcci&oacute;n 2</span></th>
															<th><span>Postal</span></th>
															<th><span>Tel&eacute;fono</span></th>
															<th><span>Correo electr&oacute;nico</span></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="snv" items="${storeNoValid}">
															<tr>
																<td><c:out value="${snv.code}" /></td>
																<td><c:out value="${snv.name}" /></td>
																<td><c:out value="${snv.address1}" /></td>
																<td><c:out value="${snv.address2}" /></td>
																<td><c:out value="${snv.postal}" /></td>
																<td><c:out value="${snv.phone}" /></td>
																<td><c:out value="${snv.email}" /></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
												<c:if test="${fn:length(storeFileError) != 0}">
													<div class="alert alert-danger">
														<i class="fa fa-times-circle fa-fw fa-lg"></i> <strong>Hay
															códigos repetidos dentro del archivo</strong><br>
													</div>
													<table class="table table-striped table-hover">
														<thead>
															<tr>
																<th><span>C&oacute;digo</span></th>
																<th><span>Local</span></th>
																<th><span>Direcci&oacute;n 1</span></th>
																<th><span>Direcci&oacute;n 2</span></th>
																<th><span>Postal</span></th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="snv" items="${storeFileError}">
																<tr>
																	<td><c:out value="${snv.code}" /></td>
																	<td><c:out value="${snv.name}" /></td>
																	<td><c:out value="${snv.address1}" /></td>
																	<td><c:out value="${snv.address2}" /></td>
																	<td><c:out value="${snv.postal}" /></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</c:if>

											</c:if>

											<!-- ================================================================================= -->
											<c:if
												test="${fn:length(storeNoValid)==0 && readFile==true && fn:length(storeFileError) == 0 && correctFile == true}">
												<input type="hidden" id="filename" name="filename"
													value="${filename}" />
												<input type="hidden" id="idstate" name="idstate" />
												<input type="hidden" id="idcity" name="idcity" />
												<input type="hidden" id="idcategoria" name="idcategoria" />
												<div class="alert alert-success col-lg-9">
													<i class="fa fa-check-circle fa-fw fa-lg"></i> <strong>Archivo
														correcto.</strong><br> <i
														class="fa fa-check-circle fa-fw fa-lg"></i> <strong>Número
														de locales a importar : </strong>
													<c:out value="${tot_ok}" />
												</div>
												<div class="col-lg-3" align="right">
													<input type="button" class="btn btn-primary"
														value="Importar" onclick="importFile();">
												</div>
											</c:if>
											<c:if test="${correctFile == false }">
												<div class="alert alert-danger">
													<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
													<strong>Archivo incorrecto</strong> La plantilla que intenta subir presenta errores, una o varias columnas no encontradas.
												</div>
											</c:if>
										</form>
										<!-- ========================================= /FORMULARIO PARA ENVIAR IMAGEN =============================================== -->

									</div>
								</div>
							</div>
						</div>

						<div class="main-box clearfix">

							<header class="main-box-header clearfix text-center">
								<h1>
									<i class="fa fa-info-circle fa-fw fa-lg"></i>¿C&oacute;mo importar
									archivo csv o xls?
								</h1>
							</header>

							<div class="col-lg-6">

								<div class="main-box-body clearfix">

									<div class="table-responsive">
										<!-- ======================================================================================== -->


										<p>
											<strong>¿Cómo seleccionar un archivo?</strong>
										</p>

										<form id="myAwesomeDropzoneExample"
											class="dropzone dz-clickable">
											<div class="dz-default dz-message">
												<span>Drop files here to upload</span>
											</div>
										</form>


										<div>

											<ul>
												<li><p align="left">
														Arrastra y suelta el archivo dentro del recuadro <br>
														<br> <img src="img/arrastrararchivo.png" width="100%"></li>
												<li><p align="left">
														Da click sobre el recuadro y selecciona un archivo <br>
														<br> <img src="img/seleccionararchivo.png"
															width="100%"></li>
											</ul>
										</div>

										<!-- <br><br><button type="button" class="btn btn-info btn-xs">Descargar ejemplo .csv</button>   -->


									</div>
								</div>
							</div>
							<div class="col-lg-6">
								<p>
									<strong>Formato y reglas:</strong>
								</p>
								<ul>
									<li>Para importar se requiere descargar el archivo dando
										clic al bot&oacute;n <a class="btn btn-primary btn-sm" href="./downloads/importar.xls">Plantilla
											<i class="fa fa-download"></i>
									</a> localizado en la parte de arriba:
									</li>
									<li>Descargará un archivo con el nombre <i>importar.xls</i>.</li>
									<img src="img/ejemploImport.png" class="responsive-img">

								</ul>

							</div>
							<div class="col-xs-12 ">
								<p class="text-center"><strong>Es importante que el archivo cumpla con las siguientes
										condiciones antes de ser importado:</strong></p>
								<div class="table-responsive">
									<table class="table table-bordered">
										<thead>
											<tr class="bg-info center">
												<th>C&oacute;digo:</th>
												<th>Nombre:</th>
												<th>Calle:</th>
												<th>Número:</th>
												<th>Colonia:</th>
												<th>C&oacute;digo Postal:</th>
												<th>Tel&eacute;fono:</th>
												<th>Correo electr&oacute;nico:</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Texto</td>
												<td>Texto</td>
												<td>Texto</td>
												<td>N&uacute;mero</td>
												<td>Texto</td>
												<td>N&uacute;mero</td>
												<td>N&uacute;mero</td>
												<td>Texto</td>
											</tr>
											<tr>
												<td>L&iacute;mite de 10 caracteres.</td>
												<td>L&iacute;mite de 255 caracteres.</td>
												<td>L&iacute;mite de 255 caracteres.</td>
												<td>Introducir s&oacute;lo valores num&eacute;ricos</td>
												<td>L&iacute;mite de 255 caracteres.</td>
												<td>Introducir s&oacute;lo valores num&eacute;ricos</td>
												<td>Campo no obligatorio</td>
												<td>Campo no obligatorio</td>
											</tr>
										</tbody>
									</table>
								</div>
								<p><strong>Nota:</strong> Los campos deben cumplir con lo especificado en la tabla anterior.</p>
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
	<script src="js/dropzone.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/pace.min.js"></script>
	<!-- this page specific scripts -->
	<script>
		$(document).ready(function() {
			$('.bs-example-tooltips span').popover();
		});
		//metodo que ejecuta cuando hay un cambio en el select de state
		$("#state").change(function() {
			var id_state = document.getElementById("state").value;
			RetailServiceBean.getAllCityByIdState(id_state, retry);
		});
		$("#city").change(
				function() {
					document.getElementById("id_city").value = document
							.getElementById("city").value;
				});
		$("#categoria").change(
				function() {
					document.getElementById("id_categoria").value = document
							.getElementById("categoria").value;
				});
		var retry = (function(data) {
			dwr.util.removeAllOptions("city");
			if (data.length > 0) {
				dwr.util.addOptions("city", data, "id_city", "name");
			}
			var sel = document.getElementById("city");
			var idCity = "${id_city}";
			for (var k = 0; k < sel.options.length; k++) {
				if (sel.options[k].value == idCity)
					sel.selectedIndex = k;
			}
			document.getElementById("id_state").value = document
					.getElementById("state").value;
			document.getElementById("id_city").value = document
					.getElementById("city").value;
			document.getElementById("id_categoria").value = document
					.getElementById("categoria").value;
		});
		//configuracion de drop zone
		Dropzone.options.myAwesomeDropzone = {
			paramName : "file",
			maxFilesize : 20, // MB
			maxFiles : 1,
			addRemoveLinks : true,
			acceptedFiles : ".csv,.xls",
			dictFileTooBig : "Archivo muy grande. Maximo 5 MB!",
			dictMaxFilesExceeded : "Solo se permite un archivo.",
			success : function(file) {
				location.href = "storeimport.htm?id=${id_retail}";
			}
		};
		function importFile() {
			document.getElementById("idstate").value = document
					.getElementById("state").value;
			document.getElementById("idcity").value = document
					.getElementById("city").value;
			document.getElementById("idcategoria").value = document
					.getElementById("categoria").value;
			var id_categoria = document.getElementById("idcategoria").value;
			if (id_categoria != null && id_categoria != "")
				document.getElementById("form1").submit();
			else
				alert("La categoria no puede estar vacia" + id_categoria);
		}
		var id_stt = document.getElementById("state").value;
		RetailServiceBean.getAllCityByIdState(id_stt, retry);
		
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
							target: "btnPlantilla",
							title: "Plantilla",
							content: "Clic para descargar la plantilla, la cual es requerida para importar nuevos clientes",
							placement: "bottom",
							yOffset: -10
						},
						{
							target: 'state',
							title: "Estado",
							content: "Clic para seleccionar el estado al que perteneceran los nuevos clientes",
							placement: "bottom",
							zindex: 999						
						},
						{
							target: 'city',
							title: "Municipio",
							content: "Clic para seleccionar el municipio al que perteneceran los nuevos clientes",
							placement: "bottom",
							zindex: 999
						},
						{
							target: 'categoria',
							title: "Categoría",
							content: "Clic para seleccionar la categoría al que perteneceran los nuevos clientes",
							placement: "bottom",
							zindex: 999
						},
						{
							target: 'myAwesomeDropzone',
							title: "",
							content: "Arrastra la plantilla que descargaste y sueltala aquí para importar tus nuevos clientes",
							placement: "top",
							zindex: 999
						}
					],
					showPrevButton: true
				};
			hopscotch.startTour(tour);
		});
	</script>
</body>

</html>