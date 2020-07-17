<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Comercios - LogistikApp</title>
	
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
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                    <li><span><spring:message code="label.breadcrumb.logistic"/></span></li>
                                    <li><a href="retaillist.htm"><span><spring:message code="label.breadcrumb.logistic.retail"/></span></a></li>
                                    <li class="active"><span><c:out value="${retail.name}"/></span></li>
                                </ol>

                                <div class="clearfix">
                                    <h1 class="pull-left"><spring:message code="${accion == 'add' ? 'label.logistic.retaildept.add.title1' : 'label.logistic.retaildept.upd.title1'}"/></h1>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-6">
                                <div class="main-box">
									
                                    <header class="main-box-header clearfix">
                                        <h2><spring:message code="${accion == 'add' ? 'label.logistic.retaildept.add.title2' : 'label.logistic.retaildept.upd.title2'}"/></h2>
                                    </header>
                                    
                                    <div class="main-box-body clearfix">
                                        <form id="form" method="post">
                                            <spring:hasBindErrors name="command">
	                                            <div class="form-group">
													<div class="alert alert-danger">
														${errors.errorCount} error${errors.errorCount==1?'':'es'} al ${accion == 'add' ? 'agregar' : 'modificar'} el departamento
													</div>                                                
	                                            </div>
											</spring:hasBindErrors>
											
                                            <spring:bind path="command.id_retail_dept"><input type="hidden" id="id_retail_dept" name="id_retail_dept" value="${status.value}"/></spring:bind>

                                            <spring:bind path="command.name">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="name">Nombre (*)</label>
                                                    <input type="text" class="form-control" id="name" name="name" placeholder="Ingresa el nombre del departamento" value="${status.value}" />
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind>
                                                
                                            <spring:bind path="command.contact">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="code">Contacto (*)</label>
                                                    <input type="text" class="form-control" id="contact" name="contact" placeholder="Ingresa un nombre de contacto"  value="${status.value}" />
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind>

                                            <spring:bind path="command.phone">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="code">Tel&eacute;fono (*)</label>
                                                    <input type="text" class="form-control" id="phone" name="phone" placeholder="Ingresa tel&eacute;fono"  value="${status.value}" />
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind>

                                            <spring:bind path="command.email">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="email">Correo Electr&oacute;nico</label>
                                                    <input type="text" class="form-control" id="email" name="email" placeholder="Ingresa correo electr&oacute;nico" value="${status.value}">
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind>

                                            <spring:bind path="command.orderby">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="orderby">Orden</label>
                                                    <input type="text" class="form-control" id="orderby" name="orderby" placeholder="Especifica un criterio de orden" value="${status.value}" />
		                                        </div>
                                            </spring:bind>

                                            <div class="form-group">
                                                <button type="button" class="btn btn-default btn-xs pull-left" onclick="location.href = 'retaildetail.htm?id=${command.id_retail}';"><i class="fa fa-times fa-lg"></i> Cancelar</button>
                                                <button type="submit" class="btn btn-success btn-xs  pull-right"><i class="fa fa-check fa-lg"></i> <spring:message code="${accion == 'add' ? 'label.logistic.retaildept.add.button' : 'label.logistic.retaildept.upd.button'}"/></button>
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
	
</body>

</html>
