<%@ include file="/includes/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>Payment - LogistikApp</title>

<!-- bootstrap -->
<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />

<!-- libraries -->
<link type="text/css" rel="stylesheet" href="css/libs/font-awesome.css" />
<link type="text/css" rel="stylesheet" href="css/libs/nanoscroller.css" />

<!-- global styles -->
<link rel="stylesheet" type="text/css"
	href="css/compiled/theme_styles.css">

<!-- this page specific styles -->
<link rel="stylesheet" type="text/css" href="css/libs/fullcalendar.css" />
<link rel="stylesheet" type="text/css"
	href="css/libs/fullcalendar.print.css" media="print" />
<link rel="stylesheet" type="text/css" href="css/compiled/calendar.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/libs/morris.css" />
<link rel="stylesheet" type="text/css" href="css/libs/datepicker.css" />
<link rel="stylesheet" type="text/css" href="css/logistikapp.css">

<!-- Favicon -->
<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />

<!-- Sweetalert -->
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">

<!-- google font libraries -->
<link
	href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400'
	rel='stylesheet' type='text/css'>

<!--[if lt IE 9]>
			<script src="js/html5shiv.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->

<style type="text/css">
</style>

</head>

<body class="fixed-header fixed-leftmenu theme-blue">

	

	<div class="row">
		<div class="col-lg-12">
			<div class="main-box clearfix">
				<header id="login-header">
					<div id="login-logo">
						<img src="img/logo-white.png" alt="">
					</div>
				</header>
				<!-- Comienzo de tablas planes -->
				<div class="main-box-body clearfix">
					<div class="col-md-3 col-sm-6 col-xs-12 pricing-package">
						<div class="pricing-package-inner">
						<c:if test="${userplan == 2 }"><div class="pricing-star">Plan<br />Actual</div></c:if>
							<div class="package-header">
								<span class="stars center-block"> <i class="fa fa-star"></i>
								</span>
								<h3>Mapping</h3>
							</div>
							<div class="package-content">
								<div class="package-price">
									&dollar;349<span class="package-month">/MES</span>
								</div>
								<ul class="package-top-features">
									<li>Mapeo</li>
									<li>500 Clientes</li>
									<li>5 Usuarios</li>
								</ul>
								<ul class="package-features">
									<li class="has-feature">Implementaci&oacute;n</li>
									<li class="has-nofeature">Log&iacute;stica</li>
									<li class="has-nofeature">CRM Visitas</li>
									<li class="has-nofeature">Call Center</li>
								</ul>
							</div>
						</div>
						<div class="package-footer">
							<form action="https://www.paypal.com/cgi-bin/webscr"
								method="post" target="_top">
								<input type="hidden" name="cmd" value="_s-xclick"> <input
									type="hidden" name="hosted_button_id" value="ZL4Y955RWF8VL">
								<input type="image"
									src="https://www.paypalobjects.com/es_XC/MX/i/btn/btn_paynowCC_LG.gif"
									border="0" name="submit"
									alt="PayPal, la forma más segura y rápida de pagar en línea.">
								<img alt="" border="0"
									src="https://www.paypalobjects.com/es_XC/i/scr/pixel.gif"
									width="1" height="1">
							</form>
						</div>
					</div>

					<div class="col-md-3 col-sm-6 col-xs-12 pricing-package">
						<div class="pricing-package-inner">
						<c:if test="${userplan == 3 }"><div class="pricing-star">Plan<br />Actual</div></c:if>
							<div class="package-header green-bg">
								<span class="stars center-block"> <i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
								</span>
								<h3>Logistik</h3>
							</div>
							<div class="package-content">
								<div class="package-price">
									&dollar;1099<span class="package-month">/MES</span>
								</div>
								<ul class="package-top-features">
									<li>Mapeo</li>
									<li>750 Clientes</li>
									<li>10 Usuarios</li>
								</ul>
								<ul class="package-features">
									<li class="has-feature">Log&iacute;stica</li>
									<li class="has-feature">Implementaci&oacute;n</li>
									<li class="has-nofeature">CRM Visitas</li>
									<li class="has-nofeature">Call Center</li>
								</ul>
							</div>
						</div>
						<div class="package-footer">
							<form action="https://www.paypal.com/cgi-bin/webscr"
								method="post" target="_top">
								<input type="hidden" name="cmd" value="_s-xclick"> <input
									type="hidden" name="hosted_button_id" value="BKF29WXR2VBBL">
								<input type="image"
									src="https://www.paypalobjects.com/es_XC/MX/i/btn/btn_paynowCC_LG.gif"
									border="0" name="submit"
									alt="PayPal, la forma más segura y rápida de pagar en línea.">
								<img alt="" border="0"
									src="https://www.paypalobjects.com/es_XC/i/scr/pixel.gif"
									width="1" height="1">
							</form>
						</div>
					</div>

					<div class="col-md-3 col-sm-6 col-xs-12 pricing-package">
						<div class="pricing-package-inner">
						<c:if test="${userplan == 4 }"><div class="pricing-star">Plan<br />Actual</div></c:if>
							<div class="package-header yellow-bg">
								<span class="stars center-block"> <i class="fa fa-star"></i>
									<i class="fa fa-star"></i> <i class="fa fa-star"></i>
								</span>
								<h3>Commerce</h3>
							</div>
							<div class="package-content">
								<div class="package-price">
									&dollar;2,249<span class="package-month">/MES</span>
								</div>
								<ul class="package-top-features">
									<li>Mapeo</li>
									<li>1250 Clientes</li>
									<li>15 Usuarios</li>
								</ul>
								<ul class="package-features">
									<li class="has-feature">Log&iacute;stica</li>
									<li class="has-feature">CRM Visitas</li>									
									<li class="has-feature">Implementaci&oacute;n</li>
									<li class="has-nofeature">Call Center</li>
								</ul>
							</div>
						</div>
						<div class="package-footer">
							<form action="https://www.paypal.com/cgi-bin/webscr"
								method="post" target="_top">
								<input type="hidden" name="cmd" value="_s-xclick"> <input
									type="hidden" name="hosted_button_id" value="CUXVMJJCRBHQE">
								<input type="image"
									src="https://www.paypalobjects.com/es_XC/MX/i/btn/btn_paynowCC_LG.gif"
									border="0" name="submit"
									alt="PayPal, la forma más segura y rápida de pagar en línea.">
								<img alt="" border="0"
									src="https://www.paypalobjects.com/es_XC/i/scr/pixel.gif"
									width="1" height="1">
							</form>
						</div>
					</div>

					<div class="col-md-3 col-sm-6 col-xs-12 pricing-package">
						<div class="pricing-package-inner">
							<c:if test="${userplan == 5 }"><div class="pricing-star">Plan<br />Actual</div></c:if>
							<div class="package-header purple-bg">
								<span class="stars center-block"> <i class="fa fa-star"></i>
									<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i>
								</span>
								<h3>Retail</h3>
							</div>
							<div class="package-content">
								<div class="package-price">
									&dollar;3,249<span class="package-month">/MES</span>
								</div>
								<ul class="package-top-features">
									<li>Mapeo</li>
									<li>2000 Clientes</li>
									<li>20 Usuarios</li>
								</ul>
								<ul class="package-features">
									<li class="has-feature">Log&iacute;stica</li>
									<li class="has-feature">CRM Visitas</li>
									<li class="has-feature">Call Center</li>
									<li class="has-feature">Implementaci&oacute;n</li>
								</ul>
							</div>
						</div>
						<div class="package-footer">
						<!-- Testing Form
						<form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
								<input type="hidden" name="cmd" value="_s-xclick">
								<input type="hidden" name="hosted_button_id" value="8LW2NC8V4R4TL">
								<input type="image" src="https://www.paypalobjects.com/es_XC/MX/i/btn/btn_paynowCC_LG.gif" border="0" name="submit" alt="PayPal, la forma más segura y rápida de pagar en línea.">
								<img alt="" border="0" src="https://www.paypalobjects.com/es_XC/i/scr/pixel.gif" width="1" height="1">
							</form> -->
							<form action="https://www.paypal.com/cgi-bin/webscr"
								method="post" target="_top">
								<input type="hidden" name="cmd" value="_s-xclick"> <input
									type="hidden" name="hosted_button_id" value="NUFYS8TF6458G">
								<input type="image"
									src="https://www.paypalobjects.com/es_XC/MX/i/btn/btn_paynowCC_LG.gif"
									border="0" name="submit"
									alt="PayPal, la forma más segura y rápida de pagar en línea.">
								<img alt="" border="0"
									src="https://www.paypalobjects.com/es_XC/i/scr/pixel.gif"
									width="1" height="1">
							</form>
						</div>
					</div>
					
				</div>
			</div>
				<footer id="footer-bar" class="row">
					<c:import url="/html/footer.html" />
				</footer>
		</div>
	</div>


	<!-- global scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.min.js"></script>

	<!-- this page specific scripts -->
	<script src="js/jquery-ui.custom.min.js"></script>
	<script src="js/fullcalendar.min.js"></script>
	<script src="js/jquery.slimscroll.min.js"></script>
	<script src="js/morris.min.js"></script>
	<script src="js/raphael-min.js"></script>
	<script src="js/jquery.knob.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/locales/bootstrap-datepicker.es.js"></script>
	<script src="js/jquery.countTo.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/pace.min.js"></script>
	<script src="js/sweetalert.min.js" type="text/javascript"></script>


	<!-- this page specific inline scripts -->
	
</body>

</html>