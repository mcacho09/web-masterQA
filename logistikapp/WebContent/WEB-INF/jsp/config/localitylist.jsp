<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Localidades - LogistikApp</title>
	
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
	<script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>
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
	    <input type="hidden" id="pagina" name="pagina" value="${pagina}"/>
	    <input type="hidden" id="lim_inf" name="lim_inf" value="${lim_inf}">
	    <input type="hidden" id="lim_sup" name="lim_sup" value="${lim_sup}">
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
										<h1 class="pull-left">Localidades</h1>
										
										<div class="pull-right">
											<!-- VOLVER -->
											<a href="citylist.htm?id=${state_id}" class="btn btn-default"><i class="fa fa-arrow-circle-left fa-lg"></i> Volver</a>
											<!-- AGREGAR -->
											<a href="localityadd.htm?city_id=${city_id}&state_id=${state_id}" class="btn btn-primary"><i class="fa fa-plus-circle fa-lg"></i> Agregar</a>
										</div>
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
											</div>
										</header>
										<!-- Tabla de datos -->
										<div class="main-box-body clearfix">
											<div class="table-responsive">
												<table class="table">
													<thead>
														<tr>
															<th class="text-center"><span>Localidad</span></th>
															<th class="text-center"><span>Ciudad</span></th>
															<th class="text-center"><span>C&oacute;digo</span></th>
															<th class="text-center"><span>orden</span></th>
															<th class="text-center"><span>Estado</span></th>
															<th>&nbsp;</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="loc" items="${list_locality}">
															<tr>
																<td><c:out value="${loc.name}"/></td>
																<td><c:out value="${loc.city_name}"/></td>
																<td><c:out value="${loc.code}"/></td>
																<td align="right"><c:out value="${loc.orderby}"/></td>
																<td class="text-center"><c:if test="${loc.active == ACTIVE}"><span class="label label-success">Activo</span></c:if><c:if test="${loc.active != ACTIVE }"><span class="label label-default">No activo</span></c:if></td>
																<!-- boton editar -->
																<td style="width: 15%;">
																	<a href="localityupd.htm?id=${loc.id_locality}&state_id=${state_id}&city_id=${city_id}" class="table-link">
																		<span class="fa-stack">
																			<i class="fa fa-square fa-stack-2x"></i>
																			<i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
																		</span>
																	</a>
                                                                    <!-- active -->
                                                                    <c:if test="${loc.active == ACTIVE}">
                                                                        <a href="localitydoactive.htm?id=${loc.id_locality}&active=${NO_ACTIVE}&state_id=${state_id}&city_id=${city_id}" class="table-link danger"><span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i></span></a>
																	</c:if>
																	<c:if test="${loc.active != ACTIVE}">
																	   <a href="localitydoactive.htm?id=${loc.id_locality}&active=${ACTIVE}&state_id=${state_id}&city_id=${city_id}" class="table-link"><span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-check fa-stack-1x fa-inverse"></i></span></a>
																	</c:if>
																	<!-- /active -->
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
<!-- ========================================== PAGGIN =========================================== -->
					                        <!-- SE DEFINE PAGINA FINAL -->
					                        <c:set var="end" value="${(total/PAGGING_LIMIT)}" />
					                        <c:if test="${total % PAGGING_LIMIT > 0}">
					                            <c:set var="end" value="${end-0.5}" />
					                            <fmt:formatNumber var="end" value="${end}" pattern="#" />
					                            <c:set var="end" value="${end+1}" />
					                        </c:if>
																<c:if test="${total % PAGGING_LIMIT == 0}">
																	<fmt:formatNumber var="end" value="${end}" pattern="#" />
																</c:if>
																<!-- SE DEFINE PAGINA INICIAL -->
					                        <c:set var="begin" value="1" />
					                        <c:set var="pag_begin" value="${pagina - PAGGING_SET}"/>
					                        <c:set var="pag_end" value="${pagina +  PAGGING_SET}"/>
					                        <c:if test="${pag_begin < begin}"><c:set var="pag_begin" value="${begin}"/></c:if>
					                        <c:if test="${pag_end > end}"><c:set var="pag_end" value="${end}"/></c:if>
					
					        
					                        <ul class="pagination pull-right">
					                            
					                            <!-- PAGINA INICIAL -->
					                            <c:if test="${pagina != begin}">
					                                <li><a href="javascript:pagging(${begin})" ><c:out value="${begin}"/></a></li>
					                                <li class="disabled"><a href="#">«</a></li>
					                            </c:if>
					                      
					                            <!-- PAGINAS -->                      
					                            <c:forEach var="pag" begin="${pag_begin}" end="${pag_end}" >
					                                <c:if test="${pagina == pag}"><li class="active"><a href="#"><c:out value="${pag}"/></a></li></c:if>
					                                <c:if test="${pagina != pag}"><li><a href="javascript:pagging(${pag})" ><c:out value="${pag}"/></a></li></c:if>
					                            </c:forEach>
					                            
					                            <!-- PAGINA FINAL -->
					                            <c:if test="${pagina != end}">
					                                <li class="disabled"><a href="#">»</a></li>
					                                <li><a href="javascript:pagging(${end})" ><c:out value="${end}"/></a></li>
					                            </c:if>
					
					                        </ul>
<!-- ========================================== PAGGIN =========================================== -->
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
    function pagging(pag) {
        document.getElementById("pagina").value = pag;
        document.getElementById("lim_inf").value = (pag*"${PAGGING_LIMIT}"*1 - "${PAGGING_LIMIT}"*1 + 1);
        document.getElementById("lim_sup").value = pag*"${PAGGING_LIMIT}"*1;
        document.getElementById("form1").submit();
    }
</script>
</html>