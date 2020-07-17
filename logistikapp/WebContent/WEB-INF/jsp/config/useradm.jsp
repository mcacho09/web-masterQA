<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Usuarios - LogistikApp</title>
	
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
		<script type="text/javascript">
			
			
			var adv = ${advertencia};
    		if(adv)
    			window.location.href = "cfguserlist.htm";
		</script>
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
									<ol class="breadcrumb">
                                        <li><a href="home.htm">Home</a></li>
                                        <li><span>Configuraci&oacute;n</span></li>
                                        <li class="active"><span><a href="cfguserlist.htm">Usuarios</a></span></li>
									</ol>

									<div class="clearfix">
										<h1 class="pull-left"><spring:message code="${accion == 'add' ? 'label.config.user.add.title1' : 'label.config.user.upd.title1'}"/></h1>
									</div>
								</div>
							</div>

							<div class="row">
							    <form id="form" method="post" autocomplete="off">
							    <input type="hidden" id="action" name="action" value="${accion}">
							    <input class="shad" style="display:none" type="text" name="evitarautocompletado"/>
								<input class="shad" style="display:none" type="password" name="evitarautocompletadopassword"/>
                                                
								<div class="col-lg-6">
									<div class="main-box no-header">
									
                                        <div class="main-box-body clearfix">
                                            
                                                <spring:hasBindErrors name="command">
                                                    <div class="form-group">
                                                        <div class="alert alert-danger">${errors.errorCount} error${errors.errorCount==1?'':'es'} al ${accion == 'add' ? 'agregar' : 'modificar'} el usuario</div>
                                                    </div>
                                                </spring:hasBindErrors>
                                            
                                                <spring:bind path="command.id_user"><input type="hidden" id="id_user" name="id_user" value="${status.value}"/></spring:bind>
                                            
                                                <spring:bind path="command.username">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
	                                                    <label for="username">Nombre completo (requerido)</label>
                                                        <input type="text" class="form-control" maxlength="50"
                                                        id="username" name="username" placeholder="Ingresa el nombre del usuario" value="${status.value}" >
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>
                                                
                                                <spring:bind path="command.profile">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label>Perfil de usuario (requerido)</label>
	                                                    <select class="form-control" id="profile" name="profile">
	                                                        <authz:authorize ifAnyGranted="ADM"><option value="ADM" <c:out value="${status.value == 'ADM' ? 'SELECTED' : ''}"/> >Administrador</option></authz:authorize>
	                                                        <authz:authorize ifAnyGranted="ADM,DEM"><option value="DEM" <c:out value="${status.value == 'DEMO' ? 'SELECTED' : ''}"/> >Demo</option></authz:authorize>
	                                                        <authz:authorize ifAnyGranted="ADM,SUP,SUP1,SUP2,SUP3,SUP4,SUP5,DEM"><option value="${useracegi.profile}" <c:out value="${fn:contains(status.value, 'SUP') ? 'SELECTED' : ''}"/> >Administraci&oacute;n</option></authz:authorize>
	                                                        <authz:authorize ifAnyGranted="ADM,SUP,SUP1,SUP2,SUP3,SUP4,SUP5,DEM"><option value="LGK" <c:out value="${status.value == 'LGK' ? 'SELECTED' : ''}"/> >Log&iacute;stica</option></authz:authorize>
	                                                        <!--<authz:authorize ifAnyGranted="ADM,SUP,SUP1,SUP2,SUP3,SUP4,SUP5,DEM"><option value="CCT" <c:out value="${status.value == 'CCT' ? 'SELECTED' : ''}"/> >Call Center</option></authz:authorize> -->
	                                                        <authz:authorize ifAnyGranted="ADM,RET,DEM"><option value="RET" <c:out value="${status.value == 'RET' ? 'SELECTED' : ''}"/> >Comercio</option></authz:authorize>
	                                                        <authz:authorize ifAnyGranted="ADM,STO,DEM"><option value="STO" <c:out value="${status.value == 'STO' ? 'SELECTED' : ''}"/> >Local</option></authz:authorize>
	                                                        <authz:authorize ifAnyGranted="ADM,SUP,SUP1,SUP3,SUP4,SUP5,DEM"><option value="DRI" <c:out value="${status.value == 'DRI' ? 'SELECTED' : ''}"/> >Ventas</option></authz:authorize>
	                                                    </select>
	                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>
                                                
                                                <spring:bind path="command.userlogin">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="userlogin">Login usuario (requerido)</label>
                                                        <input type="text" class="form-control" id="userlogin" name="userlogin" placeholder="Ingresa un login para el usuario" value="${status.value}" maxlength="100">
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>
                                                
                                                <c:if test="${accion != 'upd'}">
	                                                <spring:bind path="command.passwd">
	                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
	                                                        <label for="passwd">Contraseña (requerido)</label>
	                                                        <input type="password" class="form-control" id="passwd" onBlur="CheckPass(this);" name="passwd" placeholder="Ingresa la contraseña" value="${status.value}" maxlength="100" autocomplete="new-password">
	                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
	                                                    </div>
	                                                </spring:bind>
                                                </c:if>
                                                
                                                <spring:bind path="command.email">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="email">Correo Electr&oacute;nico (requerido)</label>
                                                        <input type="text" class="form-control" id="email" name="email" placeholder="Ingresa correo electr&oacute;nico" value="${status.value}">
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>
                                                
										</div>
										
									</div>
								</div>
								
								<div class="col-lg-6">
                                    <div class="main-box no-header">
                                        <div class="main-box-body clearfix">

                                                <spring:bind path="command.job">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="job">Puesto</label>
                                                        <input type="text" class="form-control" maxlength="100" id="job" name="job" placeholder="Ingresa el puesto o cargo" value="${status.value}">
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>

                                                <spring:bind path="command.phone1">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="phone1">Tel&eacute;fono</label>
                                                        <input type="text" class="form-control" onkeypress="return verificarNumero(event)" id="phone1" name="phone1" placeholder="Ingresa un n&uacute;mero de tel&eacute;fono 1" value="${status.value}" maxlength="10" >
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>
                                                
                                                <!--
                                                <spring:bind path="command.phone2">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="phone2">Tel&eacute;fono</label>
                                                        <input type="text" class="form-control" id="phone2" name="phone2" placeholder="Ingresa un n&uacute;mero de tel&eacute;fono 2" value="${status.value}">
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>
                                                -->

                                                <spring:bind path="command.orderby">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="orderby">Orden</label>
                                                        <input type="text" class="form-control" maxlength="9" onkeypress="return verificarNumero(event)" id="orderby" name="orderby" placeholder="Especifica un criterio de orden" value="${status.value}" />
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>

                                                <div class="form-group">
                                                    <div class="checkbox-nice">
                                                        <spring:bind path="command.active">
                                                        
		                                                    <c:set var="chkactive" value=""/>
		                                                    <c:choose>
		                                                        <c:when test="${ accion == 'add' }"><c:set var="chkactive" value="checked=checked"/></c:when>
		                                                        <c:otherwise><c:set var="chkactive" value="${status.value == ACTIVE ? 'checked=checked' : ''}"/></c:otherwise>
		                                                    </c:choose>
                                                        
                                                            <input type="checkbox" id="active" name="active" value="S" ${chkactive} />
                                                        </spring:bind>
                                                        <label for="active">¿Activo?</label>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <div class="checkbox-nice">
                                                        <spring:bind path="command.superuser">
                                                            <input type="checkbox" id="superuser" name="superuser" value="S" ${status.value == ACTIVE ? 'checked=checked' : ''} />
                                                        </spring:bind>
                                                        <label for="superuser">¿Super Usuario?</label>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <button type="button" class="btn btn-default btn-xs pull-left" onclick="location.href = 'cfguserlist.htm';"><i class="fa fa-times fa-lg"></i> Cancelar</button>
                                                    <button type="submit" class="btn btn-success btn-xs  pull-right"><i class="fa fa-check fa-lg"></i> <spring:message code="${accion == 'add' ? 'label.config.user.add.button' : 'label.config.user.upd.button'}"/></button>
                                                </div>

                                        </div>
                                    </div>
                                </div>
								
								</form>
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
	
	<!-- this page specific scripts -->
	
    <!-- theme scripts -->
    <script src="js/scripts.js"></script>

    <!-- this page specific inline scripts -->
    <script type="text/javascript">
    
    function verificarNumero(evt){
    	var key = evt.keyCode;
    	return (key <= 13 || (key>= 48 && key <= 57));
    }   
    function nombre_valido(evt){ 
	    ky = evt.keyCode;
	    patron =/^[0-9a-zA-ZáéíóúàèìòùÀÈÌÒÙÁÉÍÓÚñÑüÜ_\s]+$/; 
	    k = String.fromCharCode(ky); 
	    return patron.test(k);
	}
    
    function CheckPass(ele) {
    	if (/\s/.test(ele.value)) { swal('Alerta','No se permiten espacios en blanco en la contraseña','warning') }
    	}
    
    $(".shad").show();
    window.setTimeout(function () {
        $(".shad").hide();
    },1);
    
    
        
	</script>
	
</body>

</html>