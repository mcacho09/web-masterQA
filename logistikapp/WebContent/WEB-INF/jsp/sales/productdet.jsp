<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Productos - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	
	<!-- libraries -->
	<link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="css/libs/nanoscroller.css" type="text/css" />
	<link rel="stylesheet" href="css/libs/select2.css" type="text/css" />
	
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

	<style type="text/css">
	#bluetxt{
		color: blue;
	}
	</style>

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
                <section id="col-left" class="col-left-nano">f
                    <c:import url="/html/menu-left.jsp" />
                </section>
            </div>
				<div id="content-wrapper">
<!-- ======================================================================================================================== -->                                        
					<div class="col-lg-5 col-md-6 col-sm-7 col-xs-12" >
						<div class="main-box clearfix profile-box-menu">
							<div class="main-box-body clearfix">
								<div class="profile-box-header clearfix" style=" background-color: #577998;" >
									<img src="${producto.image}" alt="" class="">
									<h2><c:out value="${producto.name_long}"/><c:out value="(${producto.name_short})"/><br>
									<c:out value="Precio: "/><fmt:formatNumber value="${producto.price_sale}" pattern="$###,###.##"/><br>
									<c:out value="Costo: "/><fmt:formatNumber value="${producto.price_cost}" pattern="$###,###.##"/>  </h2>
									<div class="job-position"><c:out value="${producto.code}"/></div>
								</div>
								<div class="profile-box-content clearfix">
									<table class="table table-striped table-hover">
										<tbody>
											<tr>
												<td>
													<ul class="fa-ul">
														<span id="bluetxt">Active </span><span class="badge badge-${producto.active == ACTIVE ? 'success' : 'danger'}"><i class="fa fa-${producto.active == ACTIVE ? 'check' : 'times'}"></i></span> 
													</ul>
												</td>
											</tr>
											<tr>
												<td>
													<ul class="fa-ul">
														<li><span id="bluetxt" >Categor&iacute;a: </span><c:out value="${category}"/></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td>
													<ul class="fa-ul">
														<li><span id="bluetxt" >Creaci&oacute;n: </span><fmt:formatDate value="${producto.created}" pattern="dd/MM/yyyy HH:mm:ss" /></li>
													</ul>
												</td>
											</tr>
											<tr>
												<td>
													<ul class="fa-ul">
														<li><span id="bluetxt" >Modificaci&oacute;n: </span><fmt:formatDate value="${producto.modified}" pattern="dd/MM/yyyy HH:mm:ss"/><c:out value="(${producto.login})"/></li>
													</ul>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
<!-- ======================================================================================================================== -->                                        
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
	<script src="js/select2.min.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>

	<!-- this page specific scripts -->
    
</body>

</html>