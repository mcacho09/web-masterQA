<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Accesos - LogistikApp</title>
	
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
                                        <li><span><spring:message code="label.breadcrumb.configuration"/></span></li>
                                        <li class="active"><span><a href="userlist.htm"><spring:message code="label.breadcrumb.users"/></a></span></li>
									</ol>

									<div class="clearfix">
										<h1 class="pull-left"><a href="useraccesslist.htm"><spring:message code="label.config.useraccess.list.title1"/></a></h1>

                                        <div class="pull-right hidden-xs">
                                            <button type="button" onclick="location.href='userlist.htm';" class="btn btn-primary btn-xs"><i class="fa fa-arrow-circle-left fa-lg"></i> Volver</button>
                                        </div>
                                        <div class="pull-right hidden-lg hidden-md hidden-sm">
                                            <button type="button" onclick="location.href='userlist.htm';" class="btn btn-primary btn-xs"><i class="fa fa-arrow-circle-left fa-lg"></i></button>
                                        </div>
                                        
									</div>
								</div>
							</div>

							<div class="row">

                                <!-- user -->
                                <div class="col-lg-3">
                                    <div class="main-box clearfix">
                                        <header class="main-box-header clearfix">
                                            <h2 class="pull-left">Usuario</h2>
                                        </header>
                                    
                                        <div class="main-box-body clearfix">
	                                        <c:set var="userprofile" value="NaN"/>
	                                        <c:choose>
	                                            <c:when test="${user.profile == 'ADM'}"><c:set var="userprofile" value="Administrador"/></c:when>
	                                            <c:when test="${user.profile == 'SUP'}"><c:set var="userprofile" value="Proveedor"/></c:when>
	                                            <c:when test="${user.profile == 'RET'}"><c:set var="userprofile" value="Comercio"/></c:when>
	                                            <c:when test="${user.profile == 'STO'}"><c:set var="userprofile" value="Local"/></c:when>
	                                        </c:choose>
	                                        
                                            <div class="table-responsive">
                                                <table class="table table-striped table-hover">
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <span class="badge badge-primary">nombre</span><br/>
                                                                <span><c:out value="${user.username}"/></span><br/>
                                                                <span class="badge badge-default">perfil</span><br/>
                                                                <span><c:out value="${userprofile}"/></span>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /user -->								
								
								<div class="col-lg-4">
									<div class="main-box clearfix">
                                        <header class="main-box-header clearfix">
                                            <h2 class="pull-left">Proveedor</h2>
                                        </header>
                                        
                                        <c:if test="${not empty supplier }">
											<div class="main-box-body clearfix">
											    <div class="table-responsive">
											        <table class="table table-striped table-hover">
											            <tbody>
											                <c:forEach var="sup" items="${supplier}">
											                    <tr>
											                        <td><input type="checkbox" id="checkedsupplier${sup.id}" onclick="doaccess('sup',${sup.id},'${sup.id_access}',this.checked);" name="checkedsupplier" ${sup.active == ACTIVE ? 'checked=checked' : '' }/></td>
											                        <td><span><c:out value="${sup.name}"/></span></td>
											                    </tr>
											                </c:forEach>
											            </tbody>
											        </table>
											    </div>
											</div>
                                        </c:if>
									</div>
								</div>
								
                                <div class="col-lg-5">
                                    <div class="main-box clearfix">
                                        <header class="main-box-header clearfix">
                                            <h2 class="pull-left">Comercio</h2>
                                        </header>
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
    <script type="text/javascript">
    function doaccess(orig,ide,ida,chk) {
    	
    	var active = null;
    	if ( chk == true ) active = "${ACTIVE}";
    	else active = "${NO_ACTIVE}";
    	
    	var url = "useraccessdo.htm?orig="+orig+"&ide="+ide+"&ida="+ida+"&active="+active+"&idu=${user.id_user}";
    	location.href = url;
    	return true;
    };
    </script>
	
</body>

</html>