<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter" %>
<%@ page import="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.acegisecurity.AuthenticationException" %>

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
    	.video-container {
		  height: 100%;
		  width: 100%;
		  overflow: hidden;
		  position: fixed;
		}
		
		.video-container video {
		  min-width: 100%;
		  min-height: 100%;
		  width: auto;
		  height: auto;
		  position: absolute;
		  top: 50%;
		  left: 50%;
		  -webkit-transform: translate(-50%, -50%);
		      -ms-transform: translate(-50%, -50%);
		          transform: translate(-50%, -50%);
		}
		.input-group {
			border-bottom: 1px solid #3498db!important;
		}
		
		.input-group-addon > .fa {
			color: #3498db!important;
		}
		
		#particles-js {
			position: fixed;
			width: 100vw;
			height: 100vh;
			top: 0;
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
										<img src="img/logo-white.png" alt="" />
									</div>
								</header>
								
								<div id="login-box-inner">
									<!-- form role="form" action="index.html" -->
									<form id="form1" name="form1" method="post">

										<!-- MENSAJE DE ERROR -->
										<div class="row">
											<div class="col-xs-12">
												<c:if test="${not empty param.code}">
												<div class="alert alert-danger fade in">
													<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
													Usuario y/o clave incorrecta
                                                        <!--
                                                        <%= ((AuthenticationException) session.getAttribute(AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>
                                                        <%= ((AuthenticationException) session.getAttribute(AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY)).getAuthentication().getName() %>
                                                      -->
                                                    </div>
                                                  </c:if>
											</div>
										</div>
										<!-- MENSAJE DE ERROR -->
										
										<div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-user"></i></span>
											<input class="form-control" type="text" id="j_username" name="j_username" placeholder="Usuario" maxlength="100">
										</div>
										<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-key"></i></span>
											<input type="password" class="form-control" id="j_password" name="j_password" placeholder="Clave" maxlength="100">
										</div>
										<div id="remember-me-wrapper">
											<div class="row">
												<div class="col-xs-6">
													<div class="checkbox-nice">
														<input type="checkbox" id="remember-me" checked="checked" />
														<label for="remember-me"> Recordarme </label>
													</div>
												</div>
												<a href="forgotpasswd.htm" id="login-forget-link" class="col-xs-6"> ¿Olvidaste tu clave? </a>
											</div>
										</div>
										
										<div class="row">
											<div class="col-xs-12">
												<button type="submit" class="btn btn-primary col-xs-12" formaction="j_security_check">Ingresar</button>
											</div>
										</div>
										
										<div class="row text-center">
											<div class="col-xs-12">
												<p class="social-text">Crea una cuenta:</p>
												<a class="btn-sm btn-primary regis" href="registration-full.htm">Registrarse</a>
											</div>
										</div>
										
										<div class="row">
											<div class="col-xs-12">
											    <p class="social-text">&copy; 2020 <a href="http://www.logistikapp.com/" target="_blank">LogistikApp</a> </p>
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
    <script type="text/javascript">

    	$(document).ready( function() {
    		redimension();
    		$(window).on('resize',redimension);
    		$("#j_username").focus();
    		if(getUrlVars()["userlogin"] != null){
    			document.getElementById('j_username').value = getUrlVars()["userlogin"];
    			$("#j_password").focus();
    			window.history.pushState("", "", '/');

    		}
    	});

        // Se controla submit event
        $( "#form1" ).submit( function() {
        	var username = $('input[name="j_username"]').val().toLowerCase();
        	$('input[name="j_username"]').val( username );
        });

        //Obtenemos dato UserLogin de la URL enviada al correo del cliente
        
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
        
        function redimension(){
        	if ($('#login-box').height() > $('body').height()){
        		$('#login-full-wrapper').css('height',($('#login-box').height() + 10) + 'px');
        	}else{
        		$('#login-full-wrapper').css('height', 100 + '%');
        	}
        }
        
        (function(){
        	particlesJS.load('particles-js', 'js/particlesjs-config.json', function() {
        		console.log('callback - particles.js config loaded');
        	});
        })()
        
      </script>

</body>
</html>
