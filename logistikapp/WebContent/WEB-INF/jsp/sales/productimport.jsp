<%@ include file="/includes/taglib.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Importar Productos - logistikapp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	
	<!-- libraries -->
	<link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet" />
	<link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="css/libs/dropzone.css" type="text/css" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">
	
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
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
	
	<!--[if lt IE 9]>
			<script src="js/html5shiv.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->
	
	<style type="text/css">
	.dropzone {
    min-height: 120px;
    }

	</style>
</head>

<body class="fixed-header fixed-leftmenu theme-blue">
<script>
</script>
<c:set var="user_id_modal" value="0"/>
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
                                   <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                   <li><span><spring:message code="label.breadcrumb.sales"/></span></li>
                                   <li class="active"><span><a href="productlist.htm"><spring:message code="label.breadcrumb.product"/></a></span></li>
									</ol>

									<div class="clearfix col-lg-6">
										<h1 class="pull-left"><a href="#">Importar</a></h1>

	                                    <div class="pull-right hidden-xs">
	                                        <button type="button" onclick="location.href='productlist.htm';" class="btn btn-default btn-xs"><i class="fa fa-arrow-circle-left fa-lg"></i> Volver</button>
	                                    </div>
	                                    <div class="pull-right hidden-lg hidden-md hidden-sm">
	                                        <button type="button" onclick="location.href='productlist.htm';" class="btn btn-default btn-xs"><i class="fa fa-arrow-circle-left fa-lg"></i></button>
	                                    </div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12">
									<div class="main-box clearfix col-lg-6">

                                        <header class="main-box-header clearfix">
                                            <h2 class="pull-left"><spring:message code="label.breadcrumb.product.import"/></h2>
                                        </header>
									
										<div class="main-box-body clearfix">
											<div class="table-responsive">
											<!-- ======================================================================================== -->
												<table style="border-collapse:separate; border-spacing:5px;">
													<tr>
														<td><c:out value="Categoría: "/></td>
														<td>
															<select id="categoria" name="categoria" class="form-control" <c:out value="${fn:length(list_category) == 0 ? 'style=width:100px;':''}"/>>
																<c:forEach var="ctg" items="${list_category}">
																	<option value="${ctg.id_category_product}" <c:out value="${id_categoria == ctg.id_category_product ? 'SELECTED':''}"/> ><c:out value="${ctg.name}"/></option>
																</c:forEach>
															</select>
														</td>
													</tr>
												</table>
												
												<form id="myAwesomeDropzone" class="dropzone dz-clickable" method="post" action="importproduct.htm" enctype="multipart/form-data" style="width: 100%;">
													<input type="hidden" name="readFile" id="readFile" value="true"/>
													<input type="hidden" name="id_categoria" id="id_categoria"/>
													<div class="dz-default dz-message">
														<span>Drop files here to upload</span>
													</div>
												</form>
												<form method="post" id="form1">
													<input type="hidden" name="importar" id="importar" value="true"/>
													<input type="hidden" name="userlogin" id="userlogin" value="${useracegi.userlogin}"/>
													<input type="hidden" id="filename" name="filename" value="${filename}"/>
													<input type="hidden" name="idcategoria" id="idcategoria"/>
													<!-- ================================================================================= -->
													<c:if test="${fn:length(productNoValid) != 0 || fn:length(productFileError) != 0}">
														<c:if test="${fn:length(productNoValid) != 0}">
															<div class="alert alert-danger">
																<i class="fa fa-times-circle fa-fw fa-lg"></i>
																<strong>Verificar formato de archivo.</strong><br> 
																<i class="fa fa-times-circle fa-fw fa-lg"></i>
																<strong>Verificar que ning&uacute;n c&oacute;digo a importar ya exista.</strong>
															</div>
															<!-- Datos incorrectos -->
															<table class="table table-striped table-hover">
																<thead>
																	<tr>
																		<th><span>C&oacute;digo</span></th>
																		<th><span>Nombre corto</span></th>
																		<th><span>Nombre largo</span></th>
																		<th><span>Precio costo</span></th>
																		<th><span>Precio venta</span></th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach var="snv" items="${productNoValid}">
																		<tr>
																			<td><c:out value="${snv.code}"/></td>
																			<td><c:out value="${snv.name_short}"/></td>
																			<td><c:out value="${snv.name_long}"/></td>
																			<td><c:out value="${snv.price_cost}"/></td>
																			<td><c:out value="${snv.price_sale}"/></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</c:if>
														
														<c:if test="${fn:length(productFileError) != 0}">
															<div class="alert alert-danger">
																<i class="fa fa-times-circle fa-fw fa-lg"></i>
																<strong>Hay c&oacute;digos repetidos dentro del archivo</strong><br> 
															</div>
															<table class="table table-striped table-hover">
																<thead>
																	<tr>
																		<th><span>C&oacute;digo</span></th>
																		<th><span>Nombre corto</span></th>
																		<th><span>Nombre largo</span></th>
																		<th><span>Precio costo</span></th>
																		<th><span>Precio venta</span></th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach var="snv" items="${productFileError}">
																		<tr>
																			<td><c:out value="${snv.code}"/></td>
																			<td><c:out value="${snv.name_short}"/></td>
																			<td><c:out value="${snv.name_long}"/></td>
																			<td><c:out value="${snv.price_cost}"/></td>
																			<td><c:out value="${snv.price_sale}"/></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</c:if>
														<div class="alert alert-info">
															<i class="fa fa-info-circle fa-fw fa-lg"></i>
															<strong>El archivo .csv debe tener los siguientes campos:</strong><br>
															<img src="img/camposProductos.png" width="100%";>
															<br><br><strong>Formato y reglas:</strong><br>
															<ul>
																<li>Campo 1. C&oacute;digo. El c&oacute;digo debe ser de tipo alfanumerico, 
																con un l&iacute;mite de 10 caracteres.Ej. "code1234SW"
																<li>Campo 2. Nombre corto. Nombre corto que lleva el art&iacute;culo, l&iacute;mite de 
																100 caracteres.Ej "cel"
																<li>Campo 3. Nombre largo. Nombre largo que lleva el art&iacute;culo, l&iacute;mite de 
																255 caracteres.Ej "celular iphone 6"
																<li>Campo 4. Precio costo. Precio que costo el art&iacute;culo, es de tipo numerico.Ej 100.00    
																<li>Campo 5. Precio venta. Precio al cual se vender&aacute; el art&iacute;culo, es de tipo numerico.Ej. 567.50    
															</ul>
															Nota: La informaci&oacute;n que no es num&eacute;rica debe de ir dentro de comillas &#34;informaci&oacute;n&#34;
														</div>													
													</c:if>
													<!-- ================================================================================= -->
													<c:if test="${fn:length(productNoValid)==0 && readFile==true && fn:length(productFileError) == 0}">
														<div class="alert alert-success col-lg-9">
															<i class="fa fa-check-circle fa-fw fa-lg"></i>
															<strong>Archivo correcto.</strong><br>
															<i class="fa fa-check-circle fa-fw fa-lg"></i>
															<strong>N&uacute;mero de productos a importar : </strong>
															<c:out value="${tot_ok}"/>
														</div>													
														<div class="col-lg-3" align="right">
															<input type="button" class="btn btn-primary" value="Importar" onclick="importFile();">
														</div>			 
													</c:if>
												</form>
											<!-- ========================================= /FORMULARIO PARA ENVIAR IMAGEN =============================================== -->
											</div>
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
	<script src="js/dropzone.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>

	<!-- this page specific scripts -->
<script>
    $(document).ready(function() {
        $('.bs-example-tooltips span').popover();
    });
    $("#categoria").change(function(){
		document.getElementById("id_categoria").value = document.getElementById("categoria").value;
    });
    //metodo que ejecuta cuando hay un cambio en el select de state
    $("#state").change(function(){
    	var id_state = document.getElementById("state").value;
    	RetailServiceBean.getAllCityByIdState(id_state, retry);
    });
    var retry = (function(data){
    	dwr.util.removeAllOptions("city");
    	if(data.length > 0){
    		dwr.util.addOptions("city",data,"id_city","name");
    	}
    	var sel = document.getElementById("city");
    	var idCity = "${id_city}";
    	for(var  k=0; k<sel.options.length; k++){
    		if(sel.options[k].value == idCity)
    			sel.selectedIndex = k;
    	}
    });
    //configuracion de drop zone
    Dropzone.options.myAwesomeDropzone = {
			paramName: "file", 
			maxFilesize: 20, // MB
			maxFiles: 1,
			addRemoveLinks: true,
			acceptedFiles: ".csv", 
			//dictInvalidFileType: "Solo se permiten imagenes de formato .png", 
			dictFileTooBig: "Archivo muy grande. Maximo 5 MB!", 
			dictMaxFilesExceeded: "Solo se permite un archivo.",
    		success: function (file){
    			location.href="productimport.htm";
    		}
	};
    function importFile(){
    	document.getElementById("idcategoria").value = document.getElementById("categoria").value;
    	document.getElementById("form1").submit();
    }
    document.getElementById("id_categoria").value = document.getElementById("categoria").value;
    
</script>
</body>

</html>