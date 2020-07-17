<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Dominios - LogistikApp</title>
	
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
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon"/>
	
	<!-- Dwr script -->
	<script type='text/javascript' src='dwr/interface/DomainServiceBean.js'></script>
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
	<form id="form1" method="post">
		<div id="page-wrapper" class="container">
			<div class="row">
			<!-- Menu lateral -->
				<div id="nav-col">
					<section id="col-left" class="col-left-nano">
						<c:import url="/html/menu-left.jsp" />
					</section>
				</div>
				<div id="content-wrapper">
					<div class="row">
						<div class="col-lg-12">
							<!-- Arriba de tabla de datos confg>>estado -->
							<div class="row">
								<div class="col-lg-12">
									<ol class="breadcrumb">
										<li><a href="#">Dashboard</a></li>
										<li class="active"><span>Configuraci&oacute;n</span></li>
									</ol>
									
									<div class="clearfix">
										<h1 class="pull-left">Dominios</h1>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12">
									<div class="main-box clearfix">
										<!-- header para busqued y agregar empresas -->
										<header class="main-box-header clearfix">
											<div class="filter-block">
												<div class="form-group pull-left">
													<input type="text" class="form-control" placeholder="Search...">
													<i class="fa fa-search search-icon"></i>
												</div>
											</div>											
											<div class="filter-block pull-right">
												
												<a href="domainadd.htm" class="btn btn-primary pull-right">
													<i class="fa fa-plus-circle fa-lg"></i> Agregar
												</a>
											</div>
										</header>
										<!-- Tabla de datos -->
										<div class="main-box-body clearfix">
											<div class="table-responsive">
												<table class="table">
													<thead>
														<tr>
															<th class="text-center"><span>Dominio</span></th>
															<th class="text-center"><span>C&oacute;digo</span></th>
															<th class="text-center"><span>orden</span></th>
															<th class="text-center"><span>Estado</span></th>
															<th>&nbsp;</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="dom" items="${list_domain}">
															<tr>
																<td><c:out value="${dom.name}"/></td>
																<td><c:out value="${dom.code}"/></td>
																<td align="right"><c:out value="${dom.orderby}"/></td>
																<td class="text-center"><c:if test="${dom.active == ACTIVE}"><span class="label label-success">Activo</span></c:if><c:if test="${dom.active != ACTIVE }"><span class="label label-default">No activo</span></c:if></td>
																<td style="width: 15%;">
																	<!-- boton editar -->
																	<a href="domainupd.htm?id=${dom.id_domain}" class="table-link">
																		<span class="fa-stack">
																			<i class="fa fa-square fa-stack-2x"></i>
																			<i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																		</span>
																	</a>
																	<!-- boton detalle muestra el dominioCont definido para ese dominio -->
																	<a href="#" class="table-link" onclick="detalleDomain(${dom.id_domain},'${dom.name}');">
																		<span class="fa-stack">
																			<i class="fa fa-square fa-stack-2x"></i>
																			<i class="fa fa-bars fa-stack-1x fa-inverse"></i>
																		</span>
																	</a>
                                                                    <!-- active -->
                                                                    <c:if test="${dom.active == ACTIVE}">
                                                                        <a href="domaindoactive.htm?id=${dom.id_domain}&active=${NO_ACTIVE}" class="table-link danger"><span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i></span></a>
																	</c:if>
																	<c:if test="${dom.active != ACTIVE}">
																	   <a href="domaindoactive.htm?id=${dom.id_domain}&active=${ACTIVE}" class="table-link"><span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-check fa-stack-1x fa-inverse"></i></span></a>
																	</c:if>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											<!-- ============= paggin ============= -->
<!-- 
											<ul class="pagination pull-right">
												<li><a href="#"><i class="fa fa-chevron-left"></i></a></li>
												<li><a href="#">1</a></li>
												<li><a href="#">2</a></li>
												<li><a href="#">3</a></li>
												<li><a href="#">4</a></li>
												<li><a href="#">5</a></li>
												<li><a href="#"><i class="fa fa-chevron-right"></i></a></li>
											</ul>
 -->
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<footer id="footer-bar" class="row">
						<p id="footer-copyright" class="col-xs-12">
							&copy; 2014 <a href="http://www.logistikapp.com/" target="_blank">LogistikApp</a>. Powered by <a href="http://www.retailsbs.com/" target="_blank">RetailSBS</a>.
						</p>
					</footer>
				</div>
			</div>
		</div>
	</form>
	
	<!-- global scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.min.js"></script>
	
	<script src="js/demo.js"></script> <!-- only for demo -->
	
	<!-- this page specific scripts -->
	
	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	
	<!-- this page specific inline scripts -->
	
</body>
<script>
	function detalleDomain(id_domain, domain_name){
		document.location = "domaincontentlist.htm?id_domain="+id_domain+"&domain_name="+domain_name;
	}
</script>
</html>