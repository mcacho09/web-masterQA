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
	
    <!-- this page specific styles -->
    <style type="text/css">
    /* Set a size for our map container, the Google Map will take up 100% of this container */
    .map-content {
        width: 100%;
        height: 400px;
    }
    </style>
	
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
                                     <li class="active"><span><a href="retaillist.htm"><spring:message code="label.breadcrumb.logistic.retail"/></a></span></li>
                                </ol>
									
	                                <div class="clearfix">
	                                    <h1 class="pull-left">Informaci&oacute;n del comercio</h1>
	
	                                    <div class="pull-right hidden-xs">
	                                        <button type="button" onclick="location.href='retaillist.htm';" class="btn btn-default btn-xs"><i class="fa fa-arrow-circle-left fa-lg"></i> Volver</button>
	                                    </div>
	                                    <div class="pull-right hidden-lg hidden-md hidden-sm">
	                                        <button type="button" onclick="location.href='retaillist.htm';" class="btn btn-default btn-xs"><i class="fa fa-arrow-circle-left fa-lg"></i></button>
	                                    </div>
	                                </div>
								</div>
							</div>
							
							<div class="row">						
								<div class="col-lg-12">
									<div class="main-box clearfix no-header">
										
										<div class="main-box-body clearfix">
	
											<div class="table-responsive">
												<table class="table table-hover">
													<tbody>
														<tr>
                                                            <td><c:out value="${retail.name}"/> <span class="badge badge-${retail.active == 'S' ? 'success' : 'danger'}"><c:out value="${retail.active == 'S' ? 'Activo' : 'Inactivo'}"/></span></td>
                                                            <td class="text-right"><span class="badge badge-default">Nombre</span></td>
														</tr>
														<tr>
                                                            <td><c:out value="${retail.category}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">Categor&iacute;a</span></td>
														</tr>
														<tr>
                                                            <td><c:out value="${retail.state}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">Estado</span></td>
														</tr>
														<tr>
                                                            <td><c:out value="${retail.city}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">Municipio</span></td>
														</tr>
														
                                                        <tr>
                                                            <td><c:out value="${retail.login}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">Usuario</span></td>
                                                        </tr>
                                                        <tr>
                                                            <td><fmt:formatDate type="both" pattern="dd/MMM, yyyy HH:mm:ss" value="${retail.created}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">Creado</span></td>
                                                        </tr>
                                                        <c:if test="${not empty retail.modified}">
                                                            <tr>
                                                                <td><fmt:formatDate type="both" pattern="dd/MMM, yyyy HH:mm:ss" value="${retail.modified}"/></td>
                                                                <td class="text-right"><span class="badge badge-default">Modificado</span></td>
                                                            </tr>
                                                        </c:if>
														
													</tbody>
												</table>
												
												<div class="panel-group accordion" id="accordion">
													<div class="panel panel-default">
														<div class="panel-heading">
															<h1 class="panel-title">
															    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><i class="fa fa-tags"></i> Departamentos</a>
															</h1>
														</div>
														<div id="collapseOne" class="panel-collapse collapse" style="height: 1px;">
															<div class="panel-body">
								                                   <div class="main-box clearfix">
								
								                                       <header class="main-box-header clearfix">
								                                            <div class="filter-block pull-right"  align="right">
										                                        <button type="button" onclick="location.href='retaildeptadd.htm?accion=add&id_retail=${retail.id_retail}';" class="btn btn-primary btn-xs"><i class="fa fa-plus-circle fa-lg"></i></button>
								                                            </div>
								                                       </header>

								                                       <div class="main-box-body clearfix">
								                                           <div class="table-responsive">
																
								                                               <table class="table table-striped table-hover">
																				<thead>
																					<tr>
																						<th><span>Nombre</span></th>
																						<th><span>Contacto</span></th>
																						<th><span>Tel&eacute;fono</span></th>
																						<th><span>Email</span></th>
																						<th class="text-center"><span>Orden</span></th>
																						<th><span>Opciones</span></th>
																					</tr>
																				</thead>
																				<tbody>
								                                                    <c:if test="${fn:length(list_retaildept) == 0}">
								                                                        <tr><td colspan="6" class="text-center"><spring:message code="label.logistic.retaildept.list.nodata"/></td></tr>
								                                                    </c:if>
																					<c:forEach var="dep" items="${list_retaildept}">
																						<tr>
																							<td><c:out value="${dep.name}"/></td>
																							<td><c:out value="${dep.contact}"/></td>
																							<td><c:out value="${dep.phone}"/></td>
																							<td><c:out value="${dep.email}"/></td>
								                                                            <td class="text-center"><span class="badge badge-default"><c:out value="${dep.orderby}"/></span></td>
								                                                            <td style="width: 15%;">
								                                                                
								                                                                <!-- edit -->
								                                                                <a href="retaildeptupd.htm?id=${dep.id_retail_dept}&accion=upd&id_retail=${dep.id_retail}" class="table-link edit">
								                                                                    <span class="fa-stack">
								                                                                        <i class="fa fa-square fa-stack-2x"></i>
								                                                                        <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
								                                                                    </span>
								                                                                </a><!-- /edit -->
						                                                                        <a href="#" onclick="del(${dep.id_retail_dept});" class="table-link danger">
						                                                                        	<span class="fa-stack"> 
						                                                                        		<i class="fa fa-square fa-stack-2x"></i> 
						                                                                        		<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
						                                                                        	</span>
						                                                                        </a>
								                                                            </td>
																						</tr>
																					</c:forEach>
																				</tbody>
																			</table>
																		</div>
																	</div>
																</div>

															</div>
														</div>
													</div>
												</div>
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
	
	<script>
		function del(id_retaildept){
			RetailServiceBean.delRetailDeptById(id_retaildept);
			document.location.href="retaildetail.htm?id=${retail.id_retail}";
		}
	</script>
</body>
</html>