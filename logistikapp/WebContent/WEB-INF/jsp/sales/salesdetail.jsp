<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
	<title>Reporte Trx - LogistikApp</title>
	
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
                                	<li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                    <li><span><spring:message code="label.breadcrumb.sales"/></span></li>
                                    <li class="active"><span>Detalle de venta</span></li>
								</ol>
                                
                                <h1>Detalle de venta</h1>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">
                                    <header class="main-box-header clearfix">
                                        <h2>No. de trx: ${infoticket.trx_num }</h2>
                                    </header>
                                    
                                    <div class="main-box-body clearfix">
										<div class="main-box-body clearfix">
											<div id="invoice-companies" class="row">
												<div class="col-sm-4 invoice-box">
													<div class="invoice-icon hidden-sm" style="color: #5c9fd2;">
														<i class="fa fa-home"></i> Cliente
													</div>
													<div class="invoice-company">
														<h4>${infoticket.store }</h4>
														<p>
															Plaza: ${infoticket.retail }
														</p>
													</div>
												</div>
												<div class="col-sm-4 invoice-box">
													<div class="invoice-icon hidden-sm" style="color: #5c9fd2;">
														<i class="fa fa-truck"></i> Vendedor
													</div>
													<div class="invoice-company">
														<h4>${infoticket.seller }</h4>
														<p>
															${infoticket.supplier }
														</p>
													</div>
												</div>
												<div class="col-sm-4 invoice-box invoice-box-dates">
													<div class="invoice-dates">
														<div class="invoice-number clearfix">
															<strong>No de trx.</strong> <span class="pull-right">${infoticket.trx_num }</span>
														</div>
														<div class="invoice-date clearfix">
															<strong>Fecha:</strong> <span class="pull-right">${infoticket.date }</span>
														</div>
														<div class="invoice-date invoice-due-date clearfix">
															<strong>Hora:</strong> <span class="pull-right">${infoticket.hour }</span>
														</div>
													</div>
												</div>
											</div>
											<c:if test="${fn:length(listvta) > 0 }">
											<h2 class="text-center text-primary">Venta</h2>
											<div class="table-responsive">
												<table class="table">
													<thead>
														<tr>
															<th class="text-center"><span>#</span></th>
															<th><span>Producto</span></th>
															<th class="text-center"><span>Cantidad</span></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="i" items="${ listvta }" varStatus="s">
														<tr>
															<td class="text-center">${s.index + 1 }</td>
															<td>${i.name_short }</td>
															<td class="text-center">${i.quantity }</td>
															<td class="text-center">$ ${i.price_sale }</td>
															<td class="text-center">$ ${i.sale }</td>
														</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											<div class="invoice-box-total clearfix">
												<div class="row">
													<div
														class="col-sm-9 col-md-10 col-xs-6 text-right invoice-box-total-label">
														Total Productos:</div>
													<div
														class="col-sm-3 col-md-2 col-xs-6 text-right invoice-box-total-value">
														${totalProducts }</div>
												</div>
												<div class="row grand-total">
													<div
														class="col-sm-9 col-md-10 col-xs-6 text-right invoice-box-total-label">
														Total Venta:</div>
													<div
														class="col-sm-3 col-md-2 col-xs-6 text-right invoice-box-total-value">
														$ ${totalVenta }</div>
												</div>
											</div>
											</c:if>
											<c:if test="${fn:length(listchg) > 0 }">
											<h2 class="text-center text-primary">Cambio</h2>
											<div class="table-responsive">
												<table class="table">
													<thead>
														<tr>
															<th class="text-center"><span>#</span></th>
															<th><span>Producto</span></th>
															<th class="text-center"><span>Cantidad</span></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="i" items="${ listchg }" varStatus="s">
														<tr>
															<td class="text-center">${s.index + 1 }</td>
															<td>${i.name_short }</td>
															<td class="text-center">${i.quantity }</td>
														</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											</c:if>
											<c:if test="${fn:length(listdev) > 0 }">
											<h2 class="text-center text-primary">Devolución</h2>
											<div class="table-responsive">
												<table class="table">
													<thead>
														<tr>
															<th class="text-center"><span>#</span></th>
															<th><span>Producto</span></th>
															<th class="text-center"><span>Cantidad</span></th>
															<th class="text-center"><span>Precio</span></th>
															<th class="text-center"><span>Total</span></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="i" items="${ listdev }" varStatus="s">
														<tr>
															<td class="text-center">${s.index + 1 }</td>
															<td>${i.name_short }</td>
															<td class="text-center">${i.quantity }</td>
															<td class="text-center">$ ${i.price_sale }</td>
															<td class="text-center">$ ${i.sale }</td>
														</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											<div class="invoice-box-total clearfix">
												<div class="row">
													<div
														class="col-sm-9 col-md-10 col-xs-6 text-right invoice-box-total-label">
														Total Productos:</div>
													<div
														class="col-sm-3 col-md-2 col-xs-6 text-right invoice-box-total-value">
														${totalProductsDev }</div>
												</div>
												<div class="row grand-total">
													<div
														class="col-sm-9 col-md-10 col-xs-6 text-right invoice-box-total-label">
														Total Devolución:</div>
													<div
														class="col-sm-3 col-md-2 col-xs-6 text-right invoice-box-total-value">
														$ ${totalVentaDev }</div>
												</div>
											</div>
											</c:if>
											<div class="invoice-summary row">
												<div class="col-xs-${totalVenta > 0 && totalVentaDev > 0 ? '6' : '12'}">
													<div class="invoice-summary-item">
														<span>Estado</span>
														<div>${infoticket.status }</div>
													</div>
												</div>
												<c:if test="${totalVenta > 0 && totalVentaDev > 0}">
												<div class="col-xs-6">
													<div class="invoice-summary-item">
														<span>Total Ticket</span>
														<div>$${totalVenta - totalVentaDev }</div>
													</div>
												</div>
												</c:if>
											</div>
											<c:if test="${totalVenta > 0 && totalVentaDev > 0}">
											<h3 class="text-info text-center">Total Ticket = Total Venta - Total Devoluciones</h3>
											</c:if>
											<div class="clearfix">
												<button data-ido="${id_order }" class="btn btn-primary pull-right" id="btn-send"> 
													<i class="fa fa-mail-forward fa-lg"></i> Enviar ticket
												</button>
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

    <!-- this page specific scripts -->

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/pace.min.js"></script>

    <!-- this page specific inline scripts -->
    <script src="dwr/interface/FinancialServiceBean.js"></script>
    <script>
    	$(document).on('ready', function(){
    		$('#btn-send').on('click', function(){
    			var $btn = $(this)
    			$btn.prop('disabled', true)
			    	
			    var html = $btn.html()
			    	
			    $btn.html("<i class='fa fa-spinner fa-pulse'></i> Enviando")
			    	
			    FinancialServiceBean.sendTicketByIdOrder($btn.data('ido'), function(data){
			    		
			    	if (!!data) {
			    		swal('Alerta', data, "warning")
			    	}
			    		
			    })
			    	
			    setTimeout(function(){
			    	$btn.prop('disabled', false)
			       	$btn.html(html)
			    }, 3000)
    		})
    	})
    </script>

</body>

</html>