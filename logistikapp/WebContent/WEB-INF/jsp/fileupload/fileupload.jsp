<%@ include file="/includes/taglib.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Usuarios - Imagen</title>
	
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
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>

	<style>
		.dropzone 
		{		
			width: 100%;
			height:100%;
			min-height: 230px !important;
		}   
	</style>
	
	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
	
	<!--[if lt IE 9]>
			<script src="js/html5shiv.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->
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
                                        <li><span><spring:message code="label.breadcrumb.configuration"/></span></li>
                                        <li class="active"><span><spring:message code="label.breadcrumb.users"/></span></li>
									</ol>

									<div class="clearfix">
										<h1 class="pull-left"><a href="#">Imagen</a></h1>

                                        <div class="pull-right">
                                        </div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-lg-12">
									<div class="main-box clearfix">

                                        <header class="main-box-header clearfix">
                                            <h2 class="pull-left">Actualizar imagen</h2>
                                        </header>
									
										<div class="main-box-body clearfix">
											<div class="table-responsive">
											<!-- ========================================= FORMULARIO PARA ENVIAR IMAGEN =============================================== -->
												<!-- 
												<form:form method="POST" commandName="fileUploadForm" enctype="multipart/form-data">
													Please select a file to upload : <input type="file" name="file" />
													<input type="hidden" id="id_usuario" name="id_usuario" value="1"/>
													<input type="submit" value="upload" />
												</form:form>
												 -->
												<div style="height:30%">
													<div class= "row">
														<div class="col-lg-8 col-md-7 col-sm-12 col-xs-12">
															<form id="myAwesomeDropzone" class="dropzone dz-clickable" method="post" action="#" enctype="multipart/form-data">
																<input type="hidden" name="login_user" id="login_user" value="${login_user}" />
																<input type="hidden" name="id_product" id="id_product" value="${id_product}"/>
																<input type="hidden" name="name_product" id="name_product" value="${name_product}"/>
																<div class="dz-default dz-message">
																	<span>Seleccionar imagen</span>
																</div>
															</form>
														</div>	
														
														<div class="col-lg-4 col-md-5 col-sm-12 col-xs-12">
															<div class="alert alert-info" style="text-align: justify; height:230px; max-height:230px">
																<i class="fa fa-info-circle fa-fw fa-lg"></i>
																<strong>Especificaciones de la imagen:</strong>
																	
																<br><br><strong>Formato y reglas:</strong><br><br>
																<ul>
																	<li>La imagen debe ser de tipo .png
																	<li>El tamaño m&aacute;ximo de la imagen es 5 mb.
																	<li>Se recomienda que las im&aacute;genes sean cuadradas.	
																</ul>															
															</div>		
														</div>
													</div>					
													<div class="pull-right">
														<input type="button" class="btn btn-primary" value="Finalizar" onclick="aceptar();">
													</div>			 
											<!-- ========================================= /FORMULARIO PARA ENVIAR IMAGEN =============================================== -->
												</div>
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
    function aceptar(){
    	//redireccion = "profile.htm?id="+${user.user_id}
    	document.location="${redireccion}";
    }
    $(document).ready(function() {
        $('.bs-example-tooltips span').popover();
    });
    Dropzone.options.myAwesomeDropzone = {
			paramName: "file", 
			maxFilesize: 5, // MB
			maxFiles: 1,
			addRemoveLinks: true,
			acceptedFiles: ".png, .jpg, .jpeg", 
			dictInvalidFileType: "Solo se permiten imagenes de formato .png, .jpg o .jpeg", 
			dictFileTooBig: "Archivo muy grande. Maximo 5Mb!", 
			dictMaxFilesExceeded: "Solo se necesita una imagen.", 
			dictDefaultMessage: "Put your custom message here"
	};
   
    </script>
</body>

</html>