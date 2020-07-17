<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Locales - LogistikApp</title>
	
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
                                     <li><span><a href="retaillist.htm"><spring:message code="label.breadcrumb.logistic.retail"/></a></span></li>
                                     <li class="active"><span><a href="storelist.htm?id=${retail.id_retail}"><c:out value="${retail.name}"/></a></span></li>
                                </ol>
									
	                                <div class="clearfix">
	                                    <h1 class="pull-left">Informaci&oacute;n del local</h1>
	
	                                    <div class="pull-right hidden-xs">
	                                        <button type="button" onclick="location.href='storelist.htm?id=${retail.id_retail}';" class="btn btn-default btn-xs"><i class="fa fa-arrow-circle-left fa-lg"></i> Volver</button>
	                                    </div>
	                                    <div class="pull-right hidden-lg hidden-md hidden-sm">
	                                        <button type="button" onclick="location.href='storelist.htm?id=${retail.id_retail}';" class="btn btn-default btn-xs"><i class="fa fa-arrow-circle-left fa-lg"></i></button>
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
                                                            <td><c:out value="${store.name}"/> <span class="badge badge-${store.active == 'S' ? 'success' : 'danger'}"><c:out value="${store.active == 'S' ? 'Activo' : 'Inactivo'}"/></span></td>
                                                            <td class="text-right"><span class="badge badge-default">Nombre</span></td>
														</tr>
														<tr>
                                                            <td><c:out value="${store.category}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">Categor&iacute;a</span></td>
														</tr>
														<tr>
                                                            <td><c:out value="${store.code}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">C&oacute;digo</span></td>
														</tr>
														<tr>
                                                            <td><c:out value="${store.address1}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">Direcci&oacute;n 1</span></td>
														</tr>
														<tr>
                                                            <td><c:out value="${store.address2}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">Direcci&oacute;n 2</span></td>
														</tr>
														<tr>
                                                            <td><c:out value="${store.postal}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">CP</span></td>
														</tr>
														<tr>
                                                            <td><c:out value="${store.city}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">Municipio</span></td>
														</tr>
														<tr>
                                                            <td><c:out value="${store.state}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">Estado</span></td>
														</tr>
														<tr>
                                                            <td><c:out value="${store.latitude}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">Latitud</span></td>
														</tr>
														<tr>
                                                            <td><c:out value="${store.longitude}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">Longitud</span></td>
														</tr>
														
														<!-- map -->
														<tr>
															<td class="text-center" colspan="2">
																<div id="map-canvas" class="map-content"></div>
															</td>
														</tr>
														<!-- /map -->

                                                        <!-- operativos -->
                                                        <tr>
                                                            <td><c:out value="${store.login}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">Usuario</span></td>
                                                        </tr>
                                                        <tr>
                                                            <td><fmt:formatDate type="both" pattern="dd/MMM, yyyy HH:mm:ss" value="${store.created}"/></td>
                                                            <td class="text-right"><span class="badge badge-default">Creado</span></td>
                                                        </tr>
                                                        <c:if test="${not empty store.modified}">
                                                            <tr>
                                                                <td><fmt:formatDate type="both" pattern="dd/MMM, yyyy HH:mm:ss" value="${store.modified}"/></td>
                                                                <td class="text-right"><span class="badge badge-default">Modificado</span></td>
                                                            </tr>
                                                        </c:if>
                                                        <!-- /operativos -->
														
													</tbody>
												</table>
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
	
    <!-- this page specific inline scripts -->
    <script type="text/javascript">
    
    // When the window has finished loading create our google map below
    google.maps.event.addDomListener(window, 'load', init);
    
    function init() {
        var latitude = "${store.latitude}";
        var longitude = "${store.longitude}";
        
        var myLatlng = new google.maps.LatLng(latitude,longitude);
    
        var mapOptions = {
                center: myLatlng,
                zoom: 16
                };
        
        var map = new google.maps.Map(document.getElementById("map-canvas"),  mapOptions);
        
        var marker = new google.maps.Marker({
            position: myLatlng,
            map: map
        });
        
    };// init
    
    </script>

</body>
</html>