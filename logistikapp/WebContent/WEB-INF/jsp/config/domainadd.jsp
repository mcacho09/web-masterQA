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
	
	<!-- Dwr script -->
	<script type='text/javascript' src='dwr/interface/UserServiceBean.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	
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
									<ol class="breadcrumb">
										<li><a href="home.htm">Dashboard</a></li>
										<li><span>Configuraci&oacute;n</span></li>
										<li><span>Dominio</span></li>
										<li><span>Nuevo Dominio</span></li>
										<li class="active"><a href="cfguserlist.htm"><c:out value="${titulo_estado}"/></a></li>
									</ol>

									<div class="clearfix">
										<h1 class="pull-left"><c:out value="${titulo_estado}"/></h1>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-lg-6">
									<div class="main-box">
                                        <header class="main-box-header clearfix">
                                            <h2>Ingresa los datos para el dominio</h2>
                                        </header>
                                    
                                        <div class="main-box-body clearfix">
                                            <form id="form" method="post">
                                                <div class="form-group">
                                                    <spring:hasBindErrors name="command">
                                                        <div class="alert alert-danger">
                                                            ${errors.errorCount} error${errors.errorCount==1?'':'es'} al agregar el usuario
                                                         </div>
                                                    </spring:hasBindErrors>
                                                </div>
							                    <spring:bind path="command.login">
	                                                <input type="hidden" id="login" name="login" value="${login}"/>
	                                            </spring:bind>
                                            
                                                <div class="form-group">
								                    <spring:bind path="command.name">
	                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
		                                                    <label for="passwd">Dominio</label>
        	                                            	<input type="text" class="form-control" id="name" name="name" placeholder="Ingresa nombre de de dominio." value="${status.value}" >
	                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
        	                                        	</div>
	                                                </spring:bind>
                                                </div>
                                                
                                                <div class="form-group">
								                    <spring:bind path="command.code">
	                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
		                                                    <label for="passwd">Codigo</label>
		                                                    <input type="text" class="form-control" id="code" name="code" placeholder="Ingresa codigo." value="${status.value}">
	                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
		                                                </div>
	                                                </spring:bind>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label for="email">Orden</label>
								                    <spring:bind path="command.orderby">
	                                                    <input type="email" class="form-control" id="orderby" name="orderby" placeholder="Ingresa el orden en que aparesca el registro" value="${status.value}">
	                                                </spring:bind>
                                                </div>

                                                <div class="form-group">
                                                    <!--label>¿Activo?</label-->
                                                    <div class="checkbox-nice">
                                                    	<spring:bind path="command.active">
	                                                    	<input type="checkbox" id="active" name="active" value="S" ${command.active == ACTIVE ? 'checked=checked' : ''} />
                                                    	</spring:bind>
	                                                    <label for="active">¿Activo?</label>
                                                    </div>
                                                </div>
                                         
                                                
                                                <button type="button" class="btn btn-default" onclick="javascript:cancelar();">Cancelar</button>
                                                <button type="submit" class="btn btn-success"><c:out value="${accion}"/></button>
                                                
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
	function cancelar() {
        location.href = "domainlist.htm";
        return true;
	};
	</script>
	
</body>

</html>