<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>LogistikApp - Gesti&oacute;n &amp; Inteligencia de Negocios</title>

<!-- bootstrap -->
<link href="css/bootstrap/bootstrap.css" rel="stylesheet" />

<!-- libraries -->
<link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet" />

<!-- global styles -->
<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

<!-- this page specific styles -->
<link rel="stylesheet" type="text/css" href="css/logistikapp-full-form.css">
<!-- google font libraries -->
<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>

<!-- Favicon -->
<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />

<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
	<![endif]-->
</head>

<body id="login-page-full">
	<div id="login-full-wrapper">
		<div class="container">
			<div class="cont-all">
					<div id="login-box">
						<div class="row">
							<div class="col-xs-12">
								
								<header id="login-header">
									<div id="login-logo">
										<img src="img/logo-white.png" alt="" />
									</div>
								</header>
								
								<div id="login-box-inner" class="with-heading">
										<h4 class="text-center">¿Olvidaste tu clave?</h4>

										<form role="form" id="formulario" action="">							
											<div class="input-group reset-pass-input">
												<span class="input-group-addon"><i class="fa fa-user"></i></span>
												<input class="form-control" id="email" name="email"  type="text" placeholder="Correo Electr&oacute;nico">
											</div>
											<div id="msj"></div>											
											<div class="row">
												<div class="col-xs-12">
												  <button type="button" class="btn btn-primary col-xs-12" id="send" onclick="javascript:validateEmail()"> Enviar </button>
												  
												</div>
												<div class="col-xs-12">
													<br/>
													<a href="j_security_logout" id="login-forget-link" class="forgot-link col-xs-12">Volver al inicio</a>
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
	<script src="js/pace.min.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>

    <!-- this page specific inline scripts -->
    <script type="text/javascript">

    
    $(document).on('ready',function(){
    	init();
    	redimension();
		$(window).on('resize',redimension);
    });

var send="${send}";
var found="${found}";
		if(send=="true")
			{
  		      $('#msj').html($('<div class="alert alert-success fade in text-center"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><span><i class="fa fa-check-square fa-fw fa-lg"></i> Se ha enviado la nueva clave a tu correo electrónico</span></div>'));
			}

		if(found=="false")
			{
  			  $('#msj').html($('<div class="alert alert-danger fade in text-center"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><span><i class="fa fa-exclamation-triangle fa-fw fa-lg"></i> Correo no está registrado</span></div>'));

			}
     	
	function validateEmail() {
	var email =	document.getElementById("email").value;
    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	   if(re.test(email))
		   {
		   		document.getElementById("formulario").submit();
		   }
	   else //correo no valido
		   {
       	 		
       		  $('#msj').html($('<div class="row"><div class="col-xs-12"><div class="alert alert-danger text-center"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><span><i class="fa fa-times-circle fa-fw fa-lg"></i> Correo electrónico no es válido</span></div></div></div>'));

		   }
	}
	  $('#btnClose').click(init);
	  
	  function init()
		{
 			$('#msj').html($('<div class="alert alert-info text-center"><span><i class="fa fa-info-circle fa-fw fa-lg"></i> Ingresa un correo electrónico</span></div>'));
	  	}
	  
	  function redimension(){
			if ($('#login-box').height() > $('body').height()){
				$('#login-full-wrapper').css('height',($('#login-box').height() + 10) + 'px');
			}else{
				$('#login-full-wrapper').css('height', 100 + '%');
			}
		}
</script>

</body>
</html>