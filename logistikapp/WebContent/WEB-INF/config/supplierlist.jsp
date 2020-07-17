<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Proveedores - LogistikApp</title>
	
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
                                       <li class="active"><span><spring:message code="label.breadcrumb.configuration"/></span></li>
								</ol>

								<div class="clearfix">
                                    <h1 class="pull-left"><a href="supplierlist.htm"><spring:message code="label.config.supplier.list.title1"/></a></h1>

                                    <div class="pull-right">
                                        <button type="button" onclick="location.href='supplieradd.htm?accion=add';" class="btn btn-primary btn-xs"><i class="fa fa-plus-circle fa-lg"></i> Nuevo</button>
                                        <button type="button" class="btn btn-default btn-xs"><i class="fa fa-download fa-lg"></i> Exportar</button>
                                    </div>
                                    
                                </div>
                            </div>
						</div>

						<div class="row">
							<div class="col-lg-12">
								<div class="main-box clearfix">

                                       <header class="main-box-header clearfix">
                                           <h2 class="pull-left"><spring:message code="label.config.supplier.list.title2"/></h2>
                                       </header>
								
									<div class="main-box-body clearfix">
										<div class="table-responsive">
										
											<!-- list-supplier -->
											<table class="table table-striped table-hover">
												<thead>
													<tr>
														<th><span>Nombre</span></th>
														<th><span>C&oacute;digo</span></th>
														<th class="text-center"><span>Orden</span></th>
														<th class="text-center"><span>Activo</span></th>
														<th><span>Opciones</span></th>
													</tr>
												</thead>
												<tbody>
                                                    <c:if test="${fn:length(list) == 0}">
                                                        <tr><td colspan="5" class="text-center"><spring:message code="label.config.supplier.list.nodata"/></td></tr>
                                                    </c:if>
												
                                                    <c:forEach var="sup" items="${list}">
                                                        <tr>
                                                            <td><span><c:out value="${sup.name}" /></span></td>
                                                            <td><span><c:out value="${sup.code}" /></span></td>
                                                            <td class="text-center"><span class="badge badge-default"><c:out value="${sup.orderby}"/></span></td>
                                                            <td class="text-center"><span class="badge badge-${sup.active == ACTIVE ? 'success' : 'danger'}"><i class="fa fa-${sup.active == ACTIVE ? 'check' : 'times'}"></i></span></td>
                                                            <td style="width: 20%;">
                                                                <!-- edit -->
                                                                <a href="supplierupd.htm?id=${sup.id_supplier}&accion=upd" class="table-link edit">
                                                                    <span class="fa-stack">
                                                                        <i class="fa fa-square fa-stack-2x"></i>
                                                                        <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                                                                    </span>
                                                                </a><!-- /edit -->
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table><!-- /list-supplier -->
										
										</div>
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