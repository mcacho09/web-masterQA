<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>LogistikApp - Gesti&oacute;n &amp; Inteligencia de Negocios</title>
	
	<!-- bootstrap -->
	<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
	
	<!-- libraries -->
	<link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
	<link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />

	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css" />

	 <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/logistikapp-full-form.css">
	
	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
	
	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon"/>
	
	<style type="text/css">
		* {
			overflow: none;
		}
    	.video-container {
		  height: 100%;
		  width: 100%;
		  overflow: hidden;
		  position: absolute;
		}
		
		.video-container video {
		  min-width: 100%;
		  min-height: 100%;
		  width: auto;
		  height: auto;
		  position: fixed;
		  top: 50%;
		  left: 50%;
		  -webkit-transform: translate(-50%, -50%);
		      -ms-transform: translate(-50%, -50%);
		          transform: translate(-50%, -50%);
		}
		#login-box-inner {
			padding-top: 10px;
			padding-bottom: 10px;
		}
		
		.input-group {
			border-bottom: 1px solid #3498db!important;
		}
		
		.input-group-addon > .fa {
			color: #3498db!important;
		}
		
		input:invalid, textarea:invalid {
			border-bottom: rgb(253, 98, 98)!important;
		}
		
		#particles-js {
			position: fixed;
			width: 100vw;
			height: 100vh;
		}
    </style>
	
	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
	<![endif]-->
</head>
<body id="login-page-full">
	<div class="video-container">
      <video autoplay="autoplay" loop="loop" poster="videos/snapshots/In-And-Out.jpg">
        <source src="videos/mp4/In-And-Out.mp4" type="video/mp4"/>
        <source src="videos/webm/In-And-Out.webm" type="video/webm"/>
      </video>
    </div>
    
	<div id="login-full-wrapper">
		<div class="container">
			<div class="cont-all">
					<div id="particles-js"></div>
					<div id="login-box">
						<div class="row">
							<div class="col-xs-12">
								<header id="login-header">
									<div id="login-logo">
										<img src="img/logo-white.png" alt=""/>
									</div>
								</header>
								<div id="login-box-inner">
									<form role="form" method="post" >
									
										<spring:bind path="command.username">
										<div class="input-group ${status.error ? 'has-error' : '' }">
											<span class="input-group-addon"><i class="fa fa-user"></i></span>
											<input class="form-control" type="text" id="username" name="username" placeholder="Nombre completo" value="${status.value}" required>
										</div>
				                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
										</spring:bind>
										
										
										<spring:bind path="command.email">
										<div class="input-group ${status.error ? 'has-error' : '' }">
											<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
											<input class="form-control" type="text" id="email" name="email" placeholder="Correo electrónico" value="${status.value}" required>
										</div>
											<c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
										</spring:bind>
										<spring:bind path="command.userlogin">
										<div class="input-group ${status.error ? 'has-error' : '' }">
											<span class="input-group-addon"><i class="fa fa-user"></i></span>
											<input type="text" class="form-control" id="userlogin" name="userlogin" placeholder="Usuario" value="${status.value}" pattern="[A-Za-z0-9]+" required>
										</div>
											<c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
										</spring:bind>
										<spring:bind path="command.passwd">
										<div class="input-group ${status.error ? 'has-error' : '' }">
											<span class="input-group-addon"><i class="fa fa-lock"></i></span>
											<input type="password" class="form-control" id="passwd" name="passwd" placeholder="Clave" value="${status.value}" required>
										</div>
											<c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
										</spring:bind>
										<spring:bind path="command.phone1">
										<div class="input-group ${status.error ? 'has-error' : '' }">
											<span class="input-group-addon"><i class="fa fa-phone"></i></span>
											<input type="text" class="form-control" id="phone1" name="phone1" placeholder="Tel&eacute;fono" value="${status.value}" maxlength="10" required pattern="[0-9]{7,10}">
										</div>
											<c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
										</spring:bind>
                                            <div class="form-group hidden">
                                                <label>Tipo de usuario</label>
                                                <div class="radio">
                                                    <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
                                                    <label for="optionsRadios1">
                                                        Empresas &mdash; Marcas distribuidoras
                                                    </label>
                                                </div>
                                                <div class="radio">
                                                    <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                                                    <label for="optionsRadios2">
                                                        Cliente &mdash; Comercios/Personas
                                                    </label>
                                                </div>
                                            </div>
										<spring:bind path="command.name">
										<div class="input-group ${status.error ? 'has-error' : '' }">
											<span class="input-group-addon"><i class="fa fa-building"></i></span>
											<input type="text" class="form-control" id="name" name="name" placeholder="Nombre de Empresa" value="${status.value}" required>
										</div>
											<c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
										</spring:bind>
										
										<div class="form-group hidden">
      										<label>Planes Disponibles:</label>
      										<select class="form-control" id="id_plan" name="id_plan">
                								<!-- <option disabled  class="especial">Selecciona un plan</option> -->
        										<option value="1">Plan 30 dias&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Prueba</option>
        										<option value="2" disabled>Mapping &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$ 99/usuario</option>
        										<option value="3" disabled>Logistik&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$ 179/usuario</option>
        										<option value="4" disabled>Commerce&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$ 279/usuario</option>
        										<option value="5" disabled>Retail&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$ 209/usuario</option>
      										</select>
    									</div>
										
										
										<div id="remember-me-wrapper">
											<div class="row">
												<div class="col-xs-12">
													<div class="checkbox-nice">
														<input type="checkbox" id="terms-cond" checked="checked" />
														<label for="terms-cond">Acepto los términos y condiciones</label>
													</div>
												</div>
											</div>
										</div>

									<!-- Implementación Modal -->
									<div class="modal fade" id="myModal" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-body">
													<div class="row">
  														<div class="col-xs-6 col-xs-offset-3">
    														<img src="img/spriteregister2x.png" class="img-responsive" alt="Image">
  														</div>
													</div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default btn-lg" 
														onclick="javascript:location.href='j_security_logout'">Cerrar</button>
												</div>
											</div>
										</div>
									</div>
									<!-- Termino de Implementación -->


									<div class="row">
											<div class="col-xs-12">
												<button type="submit" class="btn btn-primary col-xs-12">Registrar</button>
												<br/>
												<a href="j_security_logout" id="login-forget-link" class="forgot-link pull-right">Volver al inicio</a>
											</div>
										</div>
										
									</form>
								</div>
							</div>
						</div>
					</div>
			</div>
		</div>
	</div>
	
	<!-- global scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.min.js"></script>
	
	<!-- this page specific scripts -->
	<script src="js/particles.min.js"></script>
	
	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/pace.min.js"></script>
	
	<!-- this page specific inline scripts -->
	<script>
		$(document).on('ready',function(){
			
			  if(getUrlVars()["exitoso"] != null){
	  	    	window.history.pushState("", "", '$URLAUTOADD');
	  	  		$('#myModal').modal({
				  keyboard: false,
				  backdrop: 'static'
				})
				
	  	    	$('#myModal').modal(options);
	  	    	$('#myModal').modal('show');
	  	      }
		});
	 
	 function getUrlVars()
		{
	    var vars = [], hash;
	    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
	    for(var i = 0; i < hashes.length; i++)
	    {
	        hash = hashes[i].split('=');
	        vars.push(hash[0]);
	        vars[hash[0]] = hash[1];
	    }
	    return vars;
		} 
		
		document.getElementById("optionsRadios2").disabled = true;
		
		(function(){
        	particlesJS.load('particles-js', 'js/particlesjs-config.json', function() {
        		console.log('callback - particles.js config loaded');
        	});
        })()
	</script>
</body>
</html>