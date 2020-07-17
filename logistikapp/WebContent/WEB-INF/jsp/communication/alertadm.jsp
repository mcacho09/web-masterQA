<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Notificaciones - LogistikApp</title>

    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />

    <!-- libraries -->
    <link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" href="css/libs/nanoscroller.css" type="text/css" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">
    
    <!-- Favicon -->
    <link type="image/x-icon" href="favicon.png" rel="shortcut icon" />

    <!-- google font libraries -->
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>

    <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
    <![endif]-->
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
                                <div class="col-lg-6">
                                    <ol class="breadcrumb hidden-xs">

                                        <li><a href="home.htm">Home</a></li>
                                        <li><span>Comunicaci&oacute;n</span></li>
                                        <li class="active"><a href="alertlist.htm"><span>Notificaciones</span></a></li>
									</ol>

                                    <div class="clearfix">
                                        <h1 class="pull-left">Notificaci&oacute;n Masiva</h1>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="main-box">
                                    
                                        <header class="main-box-header clearfix">
                                            <h2><spring:message code="label.communication.alert.add.title2"/></h2>
                                        </header>

                                        <div class="main-box-body clearfix">
                                            <form id="form" method="post">
                                                
                                                <div class="form-group">
                                                    <label for="message">Notificaci&oacute;n</label>
                                                    <spring:bind path="command.message">
                                                        <textarea rows="3" cols="50" maxlength="255" class="form-control" id="message" name="message" placeholder="Ingresa el mensaje" ><c:out value="${status.value}"/></textarea>
                                                    </spring:bind>
                                                </div>

                                                <!-- control de usuario -->
                                                <c:if test="${ useracegi.superuser == 'S' }">
                                                    <div class="form-group">
                                                        <label for="profile">¿Usuarios?</label>
                                                        <spring:bind path="command.profile">
                                                            <input type="radio" checked="checked">Todos
                                                            <input type="hidden" id="profile" name="profile" value="null"/>
                                                            <!--select id="profile" name="profile">
                                                                <option value="">- Todos -</option>
                                                                <authz:authorize ifAnyGranted="ADM"><option value="ADM">Administradores</option></authz:authorize>
                                                                <authz:authorize ifAnyGranted="ADM,SUP"><option value="SUP">Proveedores</option></authz:authorize>
                                                                <authz:authorize ifAnyGranted="ADM,SUP,RET"><option value="RET">Comercios</option></authz:authorize>
                                                                <authz:authorize ifAnyGranted="ADM,SUP,RET,STO"><option value="STO">Locales</option></authz:authorize>
                                                            </select-->
                                                        </spring:bind>
                                                    </div>
                                                </c:if>
                                                <!-- /control de usuario -->
                                                
                                                <div class="form-group">
                                                    <button type="button" class="btn btn-default pull-left" onclick="location.href = 'alertlist.htm';"><i class="fa fa-times"></i> Cancelar</button>
                                                    <button type="button" onclick="enviarMensaje();" class="btn btn-primary pull-right"><i class="fa fa-check"></i> Enviar</button>
                                                </div>
                                                
                                            </form>
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

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	
	<!-- this page specific scripts -->
	<script>
	$(document).ready(function() {
	      //alert("document ready occurred!");
	      $("#todo").focus();
	      $("#todo").select();
	});
	function enviarMensaje() {
		if ($('#message').val().length == 0) swal('Alerta','Ingrese la descripción del mensaje','warning');
	   	else $('form').submit();
	}
	</script>
	
</body>

</html>