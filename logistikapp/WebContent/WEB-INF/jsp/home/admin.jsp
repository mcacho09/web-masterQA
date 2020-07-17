<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
	<title>Dashbaord - LogistikApp</title>
	
	<!-- bootstrap -->
	<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
	
	<!-- libraries -->
	<link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">
	
	<link rel="stylesheet" type="text/css" href="css/logistikapp.css">
    
    <!-- this page specific styles -->

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
            </div><!-- /nav-col -->

			<div id="content-wrapper">

                <div class="row">
                    <div class="col-lg-12">
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a href="home.htm">Home</a></li>
                                </ol>
                                
                                <h1>Dashboard</h1>
                            </div>
                        </div>
                        
                        <div class="row">
							<div class="col-lg-3 col-sm-6 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-building yellow-bg"></i> <span class="headline">Proveedores</span>
									<span class="value"> <span class="timer" data-from="120"
										data-to="2562" data-speed="1000" data-refresh-interval="50">${ metricsAdm.suppliers }</span>
									</span>
								</div>
							</div>
							<div class="col-lg-3 col-sm-6 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-map-marker green-bg"></i> <span class="headline">Total de checkins</span>
									<span class="value"> <span class="timer" data-from="120"
										data-to="2562" data-speed="1000" data-refresh-interval="50">${ metricsAdm.checkinsadm }</span>
									</span>
								</div>
							</div>
							<div class="col-lg-3 col-sm-6 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-user red-bg"></i> <span class="headline">Usuarios</span>
									<span class="value"> <span class="timer" data-from="120"
										data-to="2562" data-speed="1000" data-refresh-interval="50">${ metricsAdm.usersadm }</span>
									</span>
								</div>
							</div>
							<div class="col-lg-3 col-sm-6 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-shopping-cart emerald-bg"></i> <span class="headline">Transacciones</span>
									<span class="value"> <span class="timer" data-from="120"
										data-to="2562" data-speed="1000" data-refresh-interval="50">${ metricsAdm.trxsadmin }</span>
									</span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-4 col-sm-6 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-usd green-bg"></i> <span class="headline">Ventas Totales</span>
									<span class="value"> <span class="timer" data-from="120" data-to="2562" data-speed="1000" data-refresh-interval="50">
										<fmt:formatNumber maxFractionDigits="2" pattern="000,000.00" value="${ metricsSaleAdm.sale }"></fmt:formatNumber>
										</span>
									</span>
								</div>
							</div>
							<div class="col-lg-4 col-sm-6 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-cube emerald-bg"></i> <span class="headline">Productos Vendidos</span>
									<span class="value"> <span class="timer" data-from="120"
										data-to="2562" data-speed="1000" data-refresh-interval="50">${ metricsSaleAdm.productsQty }</span>
									</span>
								</div>
							</div>
							<div class="col-lg-4 col-sm-6 col-xs-12">
								<div class="main-box infographic-box">
									<i class="fa fa-line-chart green-bg"></i> <span class="headline">Ticket Promedio Total</span>
									<span class="value"> <span class="timer" data-from="120"
										data-to="2562" data-speed="1000" data-refresh-interval="50">${ metricsSaleAdm.ticketavg }</span>
									</span>
								</div>
							</div>
						</div>
                        
                        <!--
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">
                                    <header class="main-box-header clearfix">
                                        <h2>Box Header</h2>
                                    </header>
                                    
                                    <div class="main-box-body clearfix">
                                        Content...
                                    </div>
                                </div>
                            </div>
                        </div>
                        -->
                        
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

    <!-- this page specific scripts -->

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/pace.min.js"></script>

    <!-- this page specific inline scripts -->

</body>

</html>