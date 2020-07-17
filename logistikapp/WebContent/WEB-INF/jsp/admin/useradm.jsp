<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Usuarios - LogistikApp</title>
	
	<!-- bootstrap -->
	<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
	<link href="css/bootstrap/fileinput.css" rel="stylesheet" />
	
	<!-- libraries -->
    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css" />
	
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
            <!-- /nav-col -->

            <div id="content-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        
                        <div id="breadcrumb" class="row">
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a href="home.htm">Home</a></li>
                                    <li><span>Administraci&oacute;n</span></li>
                                    <li class="active"><span><a href="admuserlist.htm">Usuarios</a></span></li>
                                </ol>
                                <h1><c:out value="${ accion == 'add' ? 'Agrega un usuario' : 'Modifica los datos del usuario' }"/></h1>
                            </div>
                        </div>
                        <!-- /breadcrumb -->

                        <spring:hasBindErrors name="command">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="alert alert-danger fade in">
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                        <i class="fa fa-times-circle fa-fw fa-lg"></i>
                                        Error al <c:out value="${ accion == 'add' ? 'agregar un nuevo usuario' : 'modificar  los datos del usuario' }"/><br/>
                                        Para continuar primero debes revisar los errores que est&aacute;n m&aacute;s abajo.
                                    </div>
                                </div>
                            </div>
                        </spring:hasBindErrors>

                        <div class="row">
                        <form id="form" method="post">
                        
                            <div class="col-lg-6">
                                <div class="main-box no-header clearfix">
									
                                    <div class="main-box-body clearfix">
                                        
                                            <input type="hidden" id="action" name="action" value="${accion}">
                                            <spring:bind path="command.id_user"><input type="hidden" id="id_user" name="id_user" value="${status.value}"/></spring:bind>
                                            
                                            <!-- nombre + login -->
                                            <div class="row">
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
		                                            <spring:bind path="command.username">
		                                                <div class="form-group ${status.error ? 'has-error' : '' }">
		                                                 <label for="username">Nombre (requerido)</label>
		                                                    <input type="text" class="form-control" id="username" name="username" placeholder="Ingresa el nombre del usuario" value="${status.value}" >
		                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
		                                                </div>
		                                            </spring:bind>
                                                </div>
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
		                                            <spring:bind path="command.userlogin">
		                                                <div class="form-group ${status.error ? 'has-error' : '' }">
		                                                    <label for="userlogin">Login (requerido)</label>
		                                                    <input type="text" class="form-control" id="userlogin" name="userlogin" placeholder="Ingresa un login para el usuario" value="${status.value}">
		                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
		                                                </div>
		                                            </spring:bind>
                                                </div>
                                            </div>
                                            
                                            <!-- perfil + acceso -->
                                            <div class="row">
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
		                                            <spring:bind path="command.profile">
		                                                <div class="form-group ${ status.error ? 'has-error' : '' }">
		                                                    <label>Perfil (requerido)</label>
		                                                    <select class="form-control" id="profile" name="profile">
		                                                        <option value="SUP" ${ status.value == 'SUP' ? 'selected="selected"' : '' } >Proveedor</option>
		                                                        <!-- option value="RET" ${ status.value == 'RET' ? 'selected="selected"' : '' } >Comercio</option -->
		                                                        <!-- option value="STO" ${ status.value == 'STO' ? 'selected="selected"' : '' } >Local</option -->
		                                                        <!-- option value="DEMO" ${ status.value == 'DEMO' ? 'selected="selected"' : '' } >Demo</option -->
		                                                        <!-- option value="ADM"  ${ status.value == 'ADM' ? 'selected="selected"' : '' } >Administrador</option -->
		                                                    </select>
		                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
		                                                </div>
		                                            </spring:bind>
                                                </div>
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	                                            <div class="form-group ${ status.error ? 'has-error' : '' }">
	                                                <label>Acceso (requerido)</label>
	                                                <select class="form-control" id="id_supplier" name="id_supplier">
	                                                    <c:forEach var="sup" items="${suppliers}">
	                                                        <option value="${sup.id_supplier}" ${access.id_supplier == sup.id_supplier ? 'selected="selected"' : '' }>${sup.name}</option>
	                                                    </c:forEach>
	                                                </select>
	                                            </div>
                                                </div>
                                            </div>

                                            <!-- clave -->
                                            <c:if test="${ accion == 'add' }">
                                                <spring:bind path="command.passwd">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="passwd">Contraseña (requerido)</label>
                                                        <input type="text" class="form-control" id="passwd" name="passwd" placeholder="Ingresa la contraseña" value="${status.value}">
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>
                                            </c:if>

                                            <!-- email -->
                                            <spring:bind path="command.email">
                                                <div class="form-group ${ status.error ? 'has-error' : '' }">
                                                    <label for="email">Correo Electr&oacute;nico (requerido)</label>
                                                    <input type="text" class="form-control" id="email" name="email" placeholder="Ingresa correo electr&oacute;nico" value="${status.value}">
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind>

                                    </div>
									
								</div>
							</div>
						    <div class="col-lg-6">
                                <div class="main-box no-header clearfix">
                                    <div class="main-box-body clearfix">

                                        <!-- telefono1 + telefono2 -->
                                        <div class="row">
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	                                            <spring:bind path="command.phone1">
	                                                <div class="form-group ${ status.error ? 'has-error' : '' }">
	                                                    <label for="phone1">Tel&eacute;fono 1</label>
	                                                    <input type="text" class="form-control" id="phone1" name="phone1" placeholder="Ingresa un n&uacute;mero de tel&eacute;fono 1" value="${status.value}" maxlength="12" size="12"/>
	                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
	                                                </div>
	                                            </spring:bind>
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	                                            <spring:bind path="command.phone2">
	                                                <div class="form-group ${ status.error ? 'has-error' : '' }">
	                                                    <label for="phone2">Tel&eacute;fono 2</label>
	                                                    <input type="text" class="form-control" id="phone2" name="phone2" placeholder="Ingresa un n&uacute;mero de tel&eacute;fono 2" value="${status.value}">
	                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
	                                                </div>
	                                            </spring:bind>
                                            </div>
                                        </div>

                                        <!-- puesto -->
                                        <spring:bind path="command.job">
                                            <div class="form-group ${ status.error ? 'has-error' : '' }">
                                                <label for="job">Puesto</label>
                                                <input type="text" class="form-control" id="job" name="job" placeholder="Ingresa el puesto o cargo" value="${status.value}">
                                                <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                            </div>
                                        </spring:bind>

                                        <!-- orden, actvo, super -->
                                        <div class="row">
                                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
	                                            <spring:bind path="command.orderby">
	                                                <div class="form-group ${ status.error ? 'has-error' : '' }">
	                                                    <label for="orderby">Orden</label>
	                                                    <input type="text" class="form-control" id="orderby" name="orderby" placeholder="Especifica un criterio de orden" value="${status.value}" />
	                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
	                                                </div>
	                                            </spring:bind>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
	                                            <div class="form-group">
	                                                <label for="active">¿Activo?</label>
	                                                <div class="checkbox-nice">
	                                                    <spring:bind path="command.active">
	                                                        <input type="checkbox" id="active" name="active" value="S" ${ status.value == ACTIVE ? 'checked="checked"' : '' } />
	                                                    </spring:bind>
	                                                    <label for="active">Si</label>
	                                                </div>
	                                            </div>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
	                                            <div class="form-group">
	                                                <label for="superuser">¿Super Usuario?</label>
	                                                <div class="checkbox-nice">
	                                                    <spring:bind path="command.superuser">
	                                                        <input type="checkbox" id="superuser" name="superuser" value="S" ${ status.value == ACTIVE ? 'checked="checked"' : '' } />
	                                                    </spring:bind>
	                                                    <label for="superuser">Si</label>
	                                                </div>
	                                            </div>
                                            </div>
                                        </div>
                                        
                                        <!-- botones -->
                                        <div class="form-group">
                                            <button type="button"  id="toolbars-cancel" class="btn btn-default pull-left">
                                                <i class="fa fa-times"></i> Cancelar
                                            </button>
                                            <button type="submit" class="btn btn-success pull-right">
                                                <i class="fa fa-check"></i>
                                                <spring:message code="${accion == 'add' ? 'label.admin.user.add.button' : 'label.admin.user.upd.button'}"/>
                                            </button>
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

    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
    
    <!-- this page specific inline scripts -->
    <script>
    $(document).ready(function() {
        
        $('#toolbars-cancel').click( function(){
            window.location.href = "admuserlist.htm";
            });
        
        });
    </script>
	
</body>

</html>