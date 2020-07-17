<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
	<title>Inicio - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	
	<!-- libraries -->
	<link type="text/css" rel="stylesheet" href="css/libs/font-awesome.css"/>
	<link type="text/css" rel="stylesheet" href="css/libs/nanoscroller.css"/>
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/libs/daterangepicker.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/fullcalendar.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/fullcalendar.print.css" media="print" />
    <link rel="stylesheet" type="text/css" href="css/compiled/calendar.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/libs/morris.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/datepicker.css" />
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">

	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
	
	<!-- Sweetalert -->
	<link rel="stylesheet" type="text/css" href="css/sweetalert.css">

	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
	
	<!--[if lt IE 9]>
			<script src="js/html5shiv.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->
		
	<style type="text/css">
.full-width {
	width: 100% !important;
}
</style>

</head>

<body class="fixed-header fixed-leftmenu theme-blue">

	<header class="navbar" id="header-navbar">
		<c:import url="/html/menu-top.jsp" />
	</header>
	
	<!--input type="hidden" id="listaEv" name="listaEv"/-->
    <input type="hidden" id="lista" name="lista"/>
    <input type="hidden" id="lista2" name="lista2"/>
    
	<c:set var="datosEv" value=""/>
	<c:set var="separadorEv" value=""/>
	
    
	<c:forEach var="evento" items="${events}">
		<c:set var="datosEv" value="${datosEv}${separadorEv}${evento.id_calendar}-${evento.cal_title}-${evento.cal_start}-${evento.cal_end}-${evento.cal_level}"/>
		<c:set var="separadorEv" value="_"/>
	</c:forEach>    
    <c:set var="datos" value="" />
    <c:set var="separador" value="" />
    <c:forEach var="nam" items="${retailList}">
        <c:set var="datos" value="${datos}${separador}${nam.retail_name}=${nam.tot_store}"/>
        <c:set var="separador" value="_"/>
    </c:forEach>
    
    <c:set var="datos2" value="" />
    <c:set var="separador2" value="" />
    <c:forEach var="nam" items="${categoryList}">
        <c:set var="datos2" value="${datos2}${separador2}${nam.name}=${nam.qty}"/>
        <c:set var="separador2" value="_"/>
    </c:forEach>
	

    <div id="page-wrapper" class="container">
        <div class="row">
            <div id="nav-col">
                <section id="col-left" class="col-left-nano">
                    <c:import url="/html/menu-left.jsp" />
                </section>
            </div>
            
            <div id="content-wrapper">
                
                <div class="row">
                    <div class="col-lg-12 hidden-sm hidden-xs">
                        <ol class="breadcrumb">
                            <li><a href="home.htm">HOME</a></li>
                            <li class="active"><span>Inteligencia de Negocios</span></li>
                        </ol>
                        <div class="clearfix">
                            <h1 class="pull-left">Inteligencia de Negocios</h1>
                        </div>
                    </div>
                </div>
                
                
				
                <div class="row">
                    <div class="col-lg-12">
	                    <div class="row">
	                    	<c:if test="${fn:contains(useracegi.profile, 'DRI') == false}">
	                        <div class="col-lg-6">
	                           
	                            <div class="main-box">
	                                <header class="main-box-header clearfix">
	                                    <h2><i class="fa fa-building"></i> Clientes </h2>
	                                </header>
	                                <div class="main-box clearfix">
                                        <div class="row">
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	                                                <div style="text-align: center;">Clientes por plaza</div>
	                                                <div id="graphDonut1" style="height: 345px;"></div>
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
	                                                <div style="text-align: center;">Categor&iacute;as</div>
	                                                <div id="graphDonut2" style="height: 345px;"></div>
                                            </div>
                                        </div>
	                                </div>
	                            </div>
	                           

	                        </div> <!-- FIN COL -->
	                        </c:if>
	                        <div class="col-lg-6">
								<div class="main-box">
	                                <header class="main-box-header clearfix">
	                                    <div class="row">
	                                        <div class="col-lg-8 col-md-6 col-sm-6 col-xs-6">
			                                    <h2 class="pull-left">
			                                        <i class="fa fa-bar-chart-o"></i> M&eacute;tricas Operativas
			                                    </h2>
	                                        </div>
	                                        <div class="col-lg-4 col-md-6  col-sm-6 col-xs-6">
	                                            <div class="input-group">
	                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                                                <input type="text" class="form-control" id="datepicker" name="datepicker" value="${schedule}">
	                                            </div>
	                                        </div>
	                                    </div>
	                                </header>
	    
	                                <div class="main-box">
	                                    <div class="clearfix">
	                                        <div class="infographic-box merged merged-top pull-left">
	                                            <a href="travellist.htm"><i id="img-contenedor" class="fa fa-truck emerald-bg"></i></a>
	                                            <span class="value emerald" id="travelQty">${travelQty}</span>
	                                            <span class="headline">Viajes</span>
	                                        </div>
	                                        <div class="infographic-box merged merged-top merged-right pull-left">
	                                            <i class="fa fa-map-marker green-bg"></i>
	                                            <span class="value green" id="travelStoreQty">${travelStoreQty}</span>
	                                            <span class="headline">Clientes</span>
	                                        </div>
	                                    </div>
	                                    <div class="clearfix">
	                                        <div class="infographic-box merged pull-left">
	                                            <i class="fa fa-check yellow-bg"></i>
	                                            <span class="value yellow" id="travelStoreQtyCheck">${travelStoreQtyCheck}</span>
	                                            <span class="headline">Visitados</span>
	                                        </div>
	                                        <div class="infographic-box merged merged-right pull-left">
	                                            <i class="fa fa-times red-bg"></i>
	                                            <span class="value red" id="travelStoreQtyNoCheck">${travelStoreQtyNoCheck}</span>
	                                            <span class="headline">No Visitados</span>
	                                        </div>
	                                    </div>
	                                    
	                                    <div class="clearfix">
	                                    	<div class="main-box-body clearfix">
                                
				                                <hr>
				                                
				                                <label><strong>Avance General</strong> <span class="badge" id="total-adv"><fmt:formatNumber type="number" pattern="##.##" value="${ (travelStoreQtyCheck * 100) / travelStoreQty }"></fmt:formatNumber>%</span></label>
				                                <div class="progress progress-4x">
				                                    <div class="progress-bar progress-bar-primary" id="progress-total-adv" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: ${ (travelStoreQtyCheck * 100) / travelStoreQty }%;"></div>
				                                </div>
				
				                                <div id="progressMetricsOpt">
				                                	<c:forEach var="i" items="${listPMO }">
					                                <label><strong>${i.category }</strong> <span class="badge">${i.per_visited }%</span></label>
					                                <div class="progress">
					                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="${i.per_visited }" aria-valuemin="0" aria-valuemax="100" style="width: ${i.per_visited }%;"></div>
					                                </div>
				                                	</c:forEach>
				                                </div>
				                            </div>
	                                    </div>
	                                    
	                                </div>
	                            </div>
	                        </div>
	                      
	                    </div> <!-- FIN ROW -->
                    </div>
                </div>
  				
  				<div class="row">
					<div class="col-lg-12">
						<div class="main-box">
							<header class="main-box-header clearfix">
								<div class="row">
									<div class="col-xs-3">
										<h2 class="pull-left">
											<i class="fa fa-area-chart"></i> M&eacute;tricas de venta
										</h2>
									</div>
									<div class="col-xs-7">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="fa fa-calendar"></i></span> <input type="text"
												class="form-control" id="datepickerDateRange">
										</div>
									</div>
									<div class="col-xs-2">
										<c:if test="${fn:contains(useracegi.profile, 'SUP')}">
											<button id="btnMetricsSaleReport" type="button" target="_blank" class="btn btn-info btn-sm"><i class="fa fa-download fa-lg"></i> <span class="hidden-sm hidden-xs">Reporte</span></button>
										</c:if>
									</div>
								</div>
							</header>

							<div class="main-box clearfix">
								<div class="clearfix">
									<c:if test="${fn:startsWith(perfil, 'DRI') == false}">
										<div class="infographic-box merged merged-top pull-left">
											<i class="fa fa-cube emerald-bg"></i> <span
												class="value emerald" id="productsQty">${metricsSale.productsQty}</span>
											<span class="headline">Productos</span>
										</div>
									</c:if>
									<div
										class="infographic-box merged merged-top merged-right pull-left">
										<i class="fa fa-usd green-bg"></i> <span class="value green"
											id="sales">${metricsSale.sale }</span> <span class="headline">Ventas</span>
									</div>
								</div>
								<c:if test="${fn:contains(useracegi.profile, 'DRI') == false && useracegi.superuser == 'S'}">
									<div class="clearfix">
										<div class="infographic-box merged merged-top pull-left">
											<i class="fa fa-usd red-bg"></i> <span class="value emerald"
												id="decrease">${metricsSale.decrease }</span> <span
												class="headline">Merma</span>
										</div>
										<div
											class="infographic-box merged merged-top merged-right pull-left">
											<i class="fa fa-money green-bg"></i> <span
												class="value green" id="utility">${metricsSale.utility }</span>
											<span class="headline">Utilidad</span>
										</div>
									</div>
								</c:if>
								<div class="clearfix">
									<div
										class="infographic-box merged merged-top pull-left">
										<i class="fa fa-shopping-cart emerald-bg"></i> <span
											class="value emerald" id="noTrx">${metricsSale.notrx}</span>
										<span class="headline">No de trx.</span>
									</div>
									<div
										class="infographic-box merged merged-top pull-left">
										<i class="fa fa-line-chart green-bg"></i> <span class="value green"
											id="ticketAvg">${metricsSale.ticketavg}</span> <span
											class="headline">Ticket promedio</span>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div id="graph1"></div>
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
            
            <form id="form1" name="form1" method="post" action="">
                <input type="hidden" id="find" name="find" value="true" />
                <input type="hidden" id="schedule" name="schedule" />
            </form>
            
				
		</div>
	</div>
	
	<!-- global scripts -->
	<script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/bootstrap.js" type="text/javascript"></script>
	<script src="js/jquery.nanoscroller.min.js" type="text/javascript"></script>

    <!-- this page specific scripts -->
    <script src="js/jquery-ui.custom.min.js" type="text/javascript"></script>
    <script src="js/fullcalendar.min.js" type="text/javascript"></script>
    <script src="js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
    <script src="js/raphael-min.js" type="text/javascript"></script>
    <script src="js/jquery.knob.js" type="text/javascript"></script>
    <script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
    <script src="js/locales/bootstrap-datepicker.es.js" type="text/javascript"></script>
    <script src="js/jquery.countTo.js" type="text/javascript"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js" type="text/javascript"></script>
	<script src="js/pace.min.js" type="text/javascript"></script>
	<script src="js/sweetalert.min.js" type="text/javascript"></script>	
	
	<script type='text/javascript' src='dwr/interface/LogisticServiceBean.js'></script>	
	<script type='text/javascript' src='dwr/interface/FinancialServiceBean.js'></script>	
	
	<script src="js/moment.min.js" type="text/javascript"></script>
    <script src="js/daterangepicker.js" type="text/javascript"></script>
     
    <!-- this page specific inline scripts -->
    <script type="text/javascript">
    
        document.getElementById("lista").value = "${datos}";
        document.getElementById("lista2").value = "${datos2}";
        //document.getElementById("listaEv").value = "${datosEv}";
        
        var graphArea2
        var y = ['sale', 'salecost', 'decrease', 'utility']
        var l = ['Venta', 'Costo de venta', 'Merma', 'Utilidad']
        var d = new Date();
        var today = d.getDate() + '/' + (d.getMonth() + 1) + '/' + d.getFullYear()
        var gData = [{
        	sale: 0,
        	salecost: 0,
        	decrease: 0,
        	utility: 0,
        	delivery: today
        }]
        var $dp
        var $dpTC
        var $dpNV
        var $dpMet
        
        $(document).ready(function() {
        	
        	
        	if ("${perfil}".indexOf('SUP') < 0 || "${useracegi.superuser}" != 'S') {
				y = ['sale']
				l = ['Venta']
				delete today.salecost
				delete today.decrease
				delete today.utility
			}
        		
        		graphArea2 = Morris.Line({
                    element: 'graph1',
                    data: [],
                    /*lineColors: ['#2980b9', '#7f8c8d', '#27ae60', '#8e44ad', '#c0392b', '#f39c12'],*/
                    lineColors: ['#2ecc71', '#f1c40f', '#e74c3c', '#3498db', '#9b59b6', '#95a5a6'],
                    xkey: 'delivery',
                    ykeys: y,
                    labels: l,
                    hideHover: 'auto',
                    resize: true
                });
            	
            	updGraph(new Date(), new Date());
        	
        	
        	//daterange picker
            $dp = $('#datepickerDateRange').daterangepicker({
            	    "format": "DD/MM/YYYY",
            	    "ranges": {
	            	    'Hoy': [moment(), moment()],
	                    'Ayer': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	                    'MaÒana': [moment().add(1, 'days'), moment().add(1, 'days')],
	                    '⁄ltimos 3 dias': [moment().subtract(3, 'days'), moment()],
	                    'PrÛximos 3 dias': [moment().add(1, 'days'),moment().add(3, 'days')],
	                    'Semana actual': [moment().startOf('week'), moment().endOf('week')],
	                    'Semana pasada': [moment().subtract(1, 'week').startOf('week'), moment().subtract(1, 'week').endOf('week')],
	                    'Mes Actual': [moment().startOf('month'), moment().endOf('month')],
	                    'Mes Anterior': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                    },
                "locale": {
                	    "separator": " - ",
                	    "applyLabel": "Ok",
                	    "cancelLabel": "Cancelar",
                	    "fromLabel": "Desde",
                	    "toLabel": "Hasta",
                	    "customRangeLabel": "Rango Personalizado",
                	    'showCustomRangeLabel': false,
                	    "daysOfWeek": ["Do","Lu","Ma","Mi","Ju","Vi","Sa"],
                	    "monthNames": ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],
                	    "firstDay": 1
                	    },
                	"startDate": new Date(),
                	"endDate": new Date(),
                	//"opens": "left",
                	"buttonClasses": "btn btn-xs"
                	}, function(start, end, label) {
                		var initDate = start.format("DD/MM/YYYY");
                		var finalDate = end.format("DD/MM/YYYY");
                		
                		//console.log(start)
                		//console.log(end)
                		$('#datepickerDateRange').val(initDate + ' - ' + finalDate);
                		var obj = {
            				id_supplier: "${id_supplier}", 
            				initDate : start.toDate(), 
            				finalDate : end.toDate()
            			}
                		if ("${perfil}" == 'DRI') {
                			obj.id_user = "${id_user}"
                		}
                		updGraph(start.toDate(), end.toDate())
                		FinancialServiceBean.getMetricsSale(obj, function(data) { 
                			$('#productsQty').html(data.productsQty);
                			$('#decrease').html(data.decrease);
                			$('#sales').html(data.sale);
                			$('#utility').html(data.utility);
                			$('#noTrx').html(data.notrx);
                			$('#ticketAvg').html(data.ticketavg);
                		})
                	});
        	
            $('#datepickerDateRange').val(today + ' - ' + today);
            
            $dpTC = $('#datepickerTopClientes').daterangepicker({
	        	    "format": "DD/MM/YYYY",
	        	    "ranges": {
	            	    'Hoy': [moment(), moment()],
	                    'Ayer': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	                    'MaÒana': [moment().add(1, 'days'), moment().add(1, 'days')],
	                    '⁄ltimos 3 dias': [moment().subtract(3, 'days'), moment()],
	                    'PrÛximos 3 dias': [moment().add(1, 'days'),moment().add(3, 'days')],
	                    'Semana actual': [moment().startOf('week'), moment().endOf('week')],
	                    'Semana pasada': [moment().subtract(1, 'week').startOf('week'), moment().subtract(1, 'week').endOf('week')],
	                    'Mes Actual': [moment().startOf('month'), moment().endOf('month')],
	                    'Mes Anterior': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	                },
	            "locale": {
	            	    "separator": " - ",
	            	    "applyLabel": "Ok",
	            	    "cancelLabel": "Cancelar",
	            	    "fromLabel": "Desde",
	            	    "toLabel": "Hasta",
	            	    "customRangeLabel": "Rango Personalizado",
	            	    'showCustomRangeLabel': false,
	            	    "daysOfWeek": ["Do","Lu","Ma","Mi","Ju","Vi","Sa"],
	            	    "monthNames": ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],
	            	    "firstDay": 1
	            	    },
	            	"startDate": new Date(),
	            	"endDate": new Date(),
	            	//"opens": "left",
	            	"buttonClasses": "btn btn-xs"
	            	}, function(start, end, label) {
	            		var initDate = start.format("DD/MM/YYYY");
	            		var finalDate = end.format("DD/MM/YYYY");
	            		
	            		//console.log(start)
	            		//console.log(end)
	            		$('#datepickerTopClientes').val(initDate + ' - ' + finalDate);
	            		var dto = {
		            		id_supplier: "${id_supplier}",
		            		fini : start.toDate(), 
	            			ffin : end.toDate()
		            	}
	            		if ("${useracegi.profile}".indexOf("DRI") > -1) {
	            			dto.id_user = ${useracegi.id_user}
	            		}
	            		FinancialServiceBean.getTopClients(dto, function(data) {
	            			$('#topClients tbody tr').remove()
	            			if (data) {
	            				data.reverse().forEach(function(e, index) {
	            					//console.log(e)
	            					$('#topClients tbody').prepend('<tr><td>' + e.store + '</td><td class="text-center">' + e.sales + '</td><td class="text-center">' + e.utility + '</td><td class="text-center">' + e.no_trx + '</td><td class="text-center">' + e.ticket_avg + '</td></tr>')
	            				})
	            			} else {
            					$('#topClients tbody').prepend('<tr><td colspan="5">Sin clientes que mostrar</td></tr>')
            				}
	            		})
	            		
	            	});
	    	
	        $('#datepickerTopClientes').val(today + ' - ' + today);
	        
	        $dpMet = $('#datepicker').daterangepicker({
        	    "format": "DD/MM/YYYY",
        	    "ranges": {
            	    'Hoy': [moment(), moment()],
                    'Ayer': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                    'MaÒana': [moment().add(1, 'days'), moment().add(1, 'days')],
                    '⁄ltimos 3 dias': [moment().subtract(3, 'days'), moment()],
                    'PrÛximos 3 dias': [moment().add(1, 'days'),moment().add(3, 'days')],
                    'Semana actual': [moment().startOf('week'), moment().endOf('week')],
                    'Semana pasada': [moment().subtract(1, 'week').startOf('week'), moment().subtract(1, 'week').endOf('week')],
                    'Mes Actual': [moment().startOf('month'), moment().endOf('month')],
                    'Mes Anterior': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                },
            "locale": {
            	    "separator": " - ",
            	    "applyLabel": "Ok",
            	    "cancelLabel": "Cancelar",
            	    "fromLabel": "Desde",
            	    "toLabel": "Hasta",
            	    "customRangeLabel": "Rango Personalizado",
            	    'showCustomRangeLabel': false,
            	    "daysOfWeek": ["Do","Lu","Ma","Mi","Ju","Vi","Sa"],
            	    "monthNames": ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],
            	    "firstDay": 1
            	    },
            	"startDate": new Date(),
            	"endDate": new Date(),
            	//"opens": "left",
            	"buttonClasses": "btn btn-xs"
            	}, function(start, end, label) {
            		var initDate = start.format("DD/MM/YYYY");
            		var finalDate = end.format("DD/MM/YYYY");
            		
            		//console.log(start)
            		//console.log(end)
            		$('#datepicker').val(initDate + ' - ' + finalDate);
            		var dto = {
	            		id_supplier: "${id_supplier}",
	            		fini : start.toDate(), 
            			ffin : end.toDate()
	            	}
            		if ("${useracegi.profile}".indexOf("DRI") > -1) {
            			dto.id_user = ${useracegi.id_user}
            		}
            		
            		//console.log(start.format('YYYY-MM-DD'))
            		
            		LogisticServiceBean.getMetricsCounter({
            			id_supplier: '${id_supplier}', 
            			schedule: start.toDate(), 
            			scheduleFin: end.toDate(),
            			statusOne: 'S', 
            			statusTwo: 'N', 
            			statusRoute: ['ACT'], 
            			statusTravel: ['PRO', 'TRA', 'FIN'] 
            		}, function(data) {
            			$('#travelQty').text(data.qty)
            			$('#travelStoreQty').text(data.total)
            			$('#travelStoreQtyCheck').text(data.visited)
            			$('#travelStoreQtyNoCheck').text(data.no_visited)
            		})
            		
            		LogisticServiceBean.getProgressMetricsOperatives(dto, function(data) {
            			//console.log("Metricas operativas progress", data)
            			$('#progressMetricsOpt').children().remove()
            			var total_per = 0.00
            			if (data) {
            				data.forEach(function(i, index){
            					$('#progressMetricsOpt').append('<label><strong>' + i.category + '</strong> <span class="badge">' + i.per_visited + '%</span></label><div class="progress"><div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="' + i.per_visited + '" aria-valuemin="0" aria-valuemax="100" style="width: ' + i.per_visited + '%;"></div></div>')
            					total_per += i.per_visited
            				})
            				total_per = (data.length == 0 ? 0 : (total_per / data.length))
            				$('#progress-total-adv').css('width', total_per.toFixed(2) + '%')
            				$('#total-adv').text(total_per.toFixed(2) + '%')
            			}
            		})
            		
            	});
    	
        $('#datepicker').val(today + ' - ' + today);
            
        	    	/* initialize the calendar */
        	    	//var listassEv = document.getElementById("listaEv").value.split("_");
    	    	    var formattedEventData = [];
    	    	    /*var resultado;
    	    	    for ( var i=0; i<listassEv.length; i++ ) {
    	    	    	    resultado = listassEv[i].split("-");
    	    	    	    
    	    	    	    for ( var j=0; j<resultado.length; j=j+5 ) {
    	    	    	    	    formattedEventData.push({
    	    	    	    	    	    id: resultado[j],
    	    	    	    	    	    title: resultado[j+1],
    	    	    	    	    	    start: resultado[j+2],//'2015-07-07',
    	    	    	    	    	    end:   resultado[j+3],//'2015-07-07',
    	    	    	    	    	    allDay: false,
    	    	    	    	    	    className: resultado[j+4] == 'P' ? '${PERSONAL}' : '${GLOBAL}'    	    	    	    	    	       
    	    	    	    	    });
    	    	    	    	    //console.log(resultado[j+2]);
    	    	    	   	}
    	    	    }*/
    	    	    
    	    	    UserServiceBean.getEventByCriteria({id_supplier: ${id_supplier}, id_user: ${useracegi.id_user}}, function(data) {
    	    	    	if (!!data.length) {
    	    	    		data.forEach(function(e) {
    	    	    			formattedEventData.push({
    	    	    	    	    id: e.id_calendar,
    	    	    	    	    title: e.cal_title,
    	    	    	    	    start: e.cal_start,//'2015-07-07',
    	    	    	    	    end:   e.cal_end,//'2015-07-07',
    	    	    	    	    allDay: false,
    	    	    	    	    className: (e.cal_level == 'P' ? '${PERSONAL}' : '${GLOBAL}')
    	    	    	    	})
    	    	    		})
    	    	    		
    	    	    	}
    	    	    	//console.log('Events', formattedEventData)
    	    	    })
    	    	    
    	    	    var TravelSearchCriteria = new Object(); 
    	    	    TravelSearchCriteria.id_supplier = "${id_supplier}";  	   
    	    	    var userProfile = "${perfil}";    
    	    	    var id_user = "${id_user}";   
    	    	    
    	    	    var list = [];     	    	   
    	    	    
    	    	     	list.push('PRO'); 
  	    	    	 	list.push('TRA');    	    	        	    	    	
    	    	   
    	    	    TravelSearchCriteria.status = list; 
    	    	  
    	    	    LogisticServiceBean.getTravelByCriteria(TravelSearchCriteria, function(data){ 
    	    	    	$(data).each(function(i,e){
    	    	    		
    	    	    		if((userProfile == 'DRI' && id_user == e.id_user) || userProfile != 'DRI'){
    	    	    			    	    	    		
	    	    	    		var day;
	    	    	    		var month;
	    	    	    		var year;
	    	    	    		    	    	    		 
	    	    	    		if(e.schedule != null){
	    	    	    			day   = e.schedule.getDate();
	    	    	    			month = e.schedule.getMonth() + 1;
	    	    	    			year  = e.schedule.getFullYear();    	    	    			
	    	    	    		}else if (e.started){
	    	    	    			day   = e.started.getDate();
	    	    	    			month = e.started.getMonth() + 1;
	    	    	    			year  = e.started.getFullYear();
	    	    	    		}else{
	    	    	    			day   = e.created.getDate();
	    	    	    			month = e.created.getMonth() + 1;
	    	    	    			year  = e.created.getFullYear();
	    	    	    		}    	    	    		 
	    	    	    		var fecha = year + "-" + month + "-" + day; 
	    	    	    		
	    	    	    		var obj = {
	    	    	    	    	    id:    e.id_travel,
	    	    	    	    	    title: e.travel,
	    	    	    	    	    start: fecha,//e.schedule,//'2015-07-07',
	    	    	    	    	    end:   fecha, //e.starter,//'2015-mes-dis',
	    	    	    	    	    allDay: true,
	    	    	    	    	    type: "t",
	    	    	    	    	    className: e.status == 'PRO' ? 'label label-warning' : (e.status == 'TRA' ? 'label label-primary' : 'label label-success')	    	    	    	   
	    	    	    	    	};
	    	    	    		
	    	    	    		if(e.status == 'PRO'){
		    	    			obj.link = 'travelwaybill.htm?idt=' + e.id_travel;
	    	    	    		}else if (e.status == 'TRA'){
	    	    	    			obj.link = 'travelonway.htm?idt=' + e.id_travel;
	    	    	    		}else if(e.status == 'CRE'){
	    	    	    			obj.link = 'travelupd.htm?idt=' + e.id_travel + '&accion=upd';
	    	    	    		}    	    	    		
	    	    	    		formattedEventData.push(obj);
    	    	    		}
	    	    	    });
    	    	    	
    	    	    	
    	    	    	/* initialize the calendar */
            	    	var date = new Date();
    	        var d = date.getDate();
    	        var m = date.getMonth();
    	        var y = date.getFullYear();

    	        var calendar = $('#calendar').fullCalendar({
    	        	
    	        	    monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
    	        	    monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
    	        	    dayNames: ['Domingo','Lunes','Martes','MiÈrcoles','Jueves','Viernes','S·bado'],
    	        	    dayNamesShort: ['Dom','Lun','Mar','MiÈ','Jue','Vie','S·b'],
    	        	    header: {
    	        	    	    right: 'prev,next',
    	        	    	    left: 'prev,next',
    	        				center: 'title',
    	        				right: 'month,agendaWeek,agendaDay'
    	        	    	    },
    				lang: 'es',
    				selectable: false,
    	            buttonText: {
    	            	    prev: '<i class="fa fa-chevron-left"></i>',
    	            	    next: '<i class="fa fa-chevron-right"></i>',
    	            	    month:    'Mes',
    	                 	week:     'Semana',
    	                 	day:      'DÌa'
    	            	    },
    	           	eventLimit: true,	    
    	            events: formattedEventData,
    	    	            eventClick:function(event,element){
    	    	            	if(event.type != undefined){
    	    	            		location.href = event.link;
    	    	            	}
    	    	            }

    	            
    	            }); // calendar
    	    	    });
    	    	    
        	    	    
        	    	
        
	        var listaName1 = new Array() ;
	        var listaTot1 = new Array() ;
	        var listaDatos1 = [];
	        
	        listaName1 = document.getElementById("lista").value.split("_");
	        for ( var i = 0; i < listaName1.length; i++ ) {
	            listaTot1 = listaName1[i].split("=");
	            for ( var j = 0; j < listaTot1.length; j = j + 2 ) {
	            	    listaDatos1.push({
	            	    	    label: listaTot1[j], 
	            	    	    value: listaTot1[j+1]
	            	        });
	                }
	            }
	            
	        graphDonut1 = Morris.Donut({
	        	    element: 'graphDonut1',
	            data: listaDatos1,
	            colors: ['#2ecc71', '#f1c40f', '#e74c3c', '#3498db', '#9b59b6', '#95a5a6'],
	            formatter: function (y) { return y; },
	            resize: false
	            });
	            
            var listaName2 = new Array() ;
            var listaTot2 = new Array() ;
            var listaDatos2 = [];
            
            listaName2 = document.getElementById("lista2").value.split("_");
            for ( var i = 0; i < listaName2.length; i++ ) {
                listaTot2 = listaName2[i].split("=");
                for ( var j = 0; j < listaTot2.length; j = j + 2 ) {
                        listaDatos2.push({
                                label: listaTot2[j], 
                                value: listaTot2[j+1]
                            });
                    }
                }
	        
	        graphDonut2 = Morris.Donut({
	        	    element: 'graphDonut2',
	            data: listaDatos2,
	            colors: ['#2ecc71', '#f1c40f', '#e74c3c', '#3498db', '#9b59b6', '#95a5a6'],
	            formatter: function (y) { return y; },
	            resize: false
	            });
	         
	        $('.infographic-box .value .timer').countTo({});
	        
	        recordar();
	        
            }); // document ready
            
            function recordar(){

            	var date_today = new Date();
            	var date_end = new Date("${supplier_obj.plan_end}");
      
            	var dias = Math.round((date_end - date_today)/(1000 * 60 * 60 * 24));
            	if( dias == 1  ){
                swal({
                    title: "Recordatorio",
                    text: "El plazo de su '<span>${userplan.plan_name}</span>' "
                    + "expira el: <span><b><fmt:formatDate	value='${supplier_obj.plan_end}' type='date' pattern='dd/MM/yyyy' /></b></span> "
                    + "<br><span>Favor de comunicarse con nuestro equipo de soporte</span>"
                    + "<br><span>V&iacute;a email: <b>soporte@logistikapp.com</b></span>"
                    + "<br><span>V&iacute;a telef&oacute;nica: <b>+52 449 2812158</b></span>",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonClass: "btn-info",
                    confirmButtonText: "Aceptar",
                    closeOnConfirm: true,
                    html: true
            	});
                
            	}else{
            		if(dias <= 0){
            		swal({
                        title: "Recordatorio",
                        text: "El plazo de su plan ha expirado el: <span><b><fmt:formatDate	value='${supplier_obj.plan_end}' type='date' pattern='dd/MM/yyyy' /></b></span> "
                        + "<br><span>Favor de comunicarse con nuestro equipo de soporte para evitar la suspensi&oacute;n de su servicio</span>"
                        + "<br><span>V&iacute;a email: <b>soporte@logistikapp.com</b></span>"
                        + "<br><span>V&iacute;a telef&oacute;nica: <b>+52 449 2812158</b></span>",
                        type: "error",
                        showCancelButton: false,
                        confirmButtonClass: "btn-info",
                        confirmButtonText: "Aceptar",
                        //confirmButtonText: "Pagar!",
                        cancelButtonText: "Aceptar!",
                        closeOnConfirm: true,
                        html: true
                	});/*,function(isConfirm) {
                        if (isConfirm) {
                        	window.location.assign("planpayment.htm");
                        }
                      });*/
            		
            	}
            	}
                
        	};
            
            
            function validate_fecha(fecha)
            {
            	var dtCh= "/";
            	var minYear=1900;
            	var maxYear=2100;
            	function isInteger(s){
            		var i;
            		for (i = 0; i < s.length; i++){
            			var c = s.charAt(i);
            			if (((c < "0") || (c > "9"))) return false;
            		}
            		return true;
            	}
            	function stripCharsInBag(s, bag){
            		var i;
            		var returnString = "";
            		for (i = 0; i < s.length; i++){
            			var c = s.charAt(i);
            			if (bag.indexOf(c) == -1) returnString += c;
            		}
            		return returnString;
            	}
            	function daysInFebruary (year){
            		return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
            	}
            	function DaysArray(n) {
            		for (var i = 1; i <= n; i++) {
            			this[i] = 31
            			if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
            			if (i==2) {this[i] = 29}
            		}
            		return this
            	}
            	function isDate(dtStr){
            		var daysInMonth = DaysArray(12)
            		var pos1=dtStr.indexOf(dtCh)
            		var pos2=dtStr.indexOf(dtCh,pos1+1)
            		var strDay=dtStr.substring(0,pos1)
            		var strMonth=dtStr.substring(pos1+1,pos2)
            		var strYear=dtStr.substring(pos2+1)
            		strYr=strYear
            		if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
            		if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
            		for (var i = 1; i <= 3; i++) {
            			if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
            		}
            		month=parseInt(strMonth)
            		day=parseInt(strDay)
            		year=parseInt(strYr)
            		if (pos1==-1 || pos2==-1){
            			return false
            		}
            		if (strMonth.length<1 || month<1 || month>12){
            			return false
            		}
            		if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
            			return false
            		}
            		if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
            			return false
            		}
            		if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
            			return false
            		}
            		return true
            	}
            	if(isDate(fecha)){
            		return true;
            	}else{
            		return false;
            	}
            }
            
            function updGraph(start, end) {
            	var obj = {
        			id_supplier: "${id_supplier}", 
        			initDate : start, 
        			finalDate : end
        		}
            	FinancialServiceBean.getMetricsSaleFull(obj, function(res) {
            		var a = []
            		res.forEach(function(e, i){
            			//console.log('Antes')
            			//console.log(e)
            			if ("${perfil}".indexOf('DRI') >= 0) {
            				delete e.products
            				delete e.decrease
            				delete e.utility
            				delete e.salecost
            				//console.log('Soy DRI')
            			}
            			
            			if ("${perfil}".indexOf('SUP') >= 0 && "${useracegi.superuser}" != 'S'){
            				delete e.decrease
            				delete e.utility
            				delete e.salecost
            				//console.log('NO SOY SUP-SU')
            			}
            			//console.log('Despu√©s')
            			//console.log(e)
            			var temp = e;
            			temp.delivery =  temp.delivery.getFullYear() + "-" + (temp.delivery.getMonth() + 1) + '-' + temp.delivery.getDate()
            			a.push(temp)
            		})
            		if (a.length == 0) a = gData
            		graphArea2.setData(a)
            		
            	})
            }
            
            $('#btnMetricsSaleReport').on('click', function(){
            	location.href = "metricssalereport.htm?fini=" + $dp.data('daterangepicker').startDate.format("DD-MM-YYYY") + "&ffin=" + $dp.data('daterangepicker').endDate.format("DD-MM-YYYY"); 
            })
            
    </script>

	<script>
		
		var vapp = new Vue({
			el: '#per_sales',
			
			mounted: function() {
				if ("${useracegi.profile}".indexOf("DRI") > -1) {
	    			this.per_data.id_user = ${useracegi.id_user}
	    		} else {
	    			delete this.per_data.id_user
	    		}
				//console.log(this.per_data)
				
				this.getPerSale()
			},
			
			data: {
				per_sale: {
					list: [],
					per_total_not_visited: 0,
					per_total_visited: 0,
					total_no_visited: 0,
					total_stores: 0,
					total_visited: 0
				},
				per_data: {
	        		id_supplier: "${id_supplier}",
	        		fini : new Date(), 
	    			ffin : new Date(),
	    			id_user : 0
	        	}
			},
			
			methods: {
				getPerSale: function() {
					FinancialServiceBean.getPerTotalVisited(this.per_data, function(data) {
		    			
		    			vapp.per_sale = data
		    			//console.log('PerSale', data)
		    		})
				}
			},
			
			watch: {
				per_data: function() {
					this.getPerSale()
				}
			}
		})
		
		$dpNV = $('#datepickerNotVisited').daterangepicker({
		    "format": "DD/MM/YYYY",
		    "ranges": {
	    	    'Hoy': [moment(), moment()],
	            'Ayer': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	            'MaÒana': [moment().add(1, 'days'), moment().add(1, 'days')],
	            '⁄ltimos 3 dias': [moment().subtract(3, 'days'), moment()],
	            'PrÛximos 3 dias': [moment().add(1, 'days'),moment().add(3, 'days')],
	            'Semana actual': [moment().startOf('week'), moment().endOf('week')],
	            'Semana pasada': [moment().subtract(1, 'week').startOf('week'), moment().subtract(1, 'week').endOf('week')],
	            'Mes Actual': [moment().startOf('month'), moment().endOf('month')],
	            'Mes Anterior': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	        },
	    "locale": {
	    	    "separator": " - ",
	    	    "applyLabel": "Ok",
	    	    "cancelLabel": "Cancelar",
	    	    "fromLabel": "Desde",
	    	    "toLabel": "Hasta",
	    	    "customRangeLabel": "Rango Personalizado",
	    	    'showCustomRangeLabel': false,
	    	    "daysOfWeek": ["Do","Lu","Ma","Mi","Ju","Vi","Sa"],
	    	    "monthNames": ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],
	    	    "firstDay": 1
	    	    },
	    	"startDate": new Date(),
	    	"endDate": new Date(),
	    	//"opens": "left",
	    	"buttonClasses": "btn btn-xs"
	    	}, function(start, end, label) {
	    		vapp.per_data.fini = start.toDate()
	    		vapp.per_data.fend = end.toDate()
	    		
	    		var initDate = start.format("DD/MM/YYYY");
	    		var finalDate = end.format("DD/MM/YYYY");
	    		
	    		//console.log(start)
	    		//console.log(end)
	    		$('#datepickerNotVisited').val(initDate + ' - ' + finalDate);
	    		/*var dto = {
	        		id_supplier: "${id_supplier}",
	        		fini : start.toDate(), 
	    			ffin : end.toDate()
	        	}
	    		if ("${useracegi.profile}".indexOf("DRI") > -1) {
	    			dto.id_user = ${useracegi.id_user}
	    		}
	    		FinancialServiceBean.getPerTotalVisited(dto, function(data) {
	    			$('#clientsNV tbody tr').remove()
	    			if (data) {
	    				//console.log(data)
	    				
	    				// Se agrega el listado del ultimo al primero
	    				
	    				data.list.reverse().forEach(function(i, ind) {
	    					$('#clientsNV tbody').prepend('<tr><td> ' + i.category  + ':</td><td><span class="label label-primary" style="font-size: 1em;">' + i.total_stores + '</span></td><td style="width: 100%;"><div class="progress" style="height: 30px; margin-bottom: 0px;"><div class="progress-bar progress-bar-success" style="width: ' + i.per_total_visited + '%"><span style="font-size: 20px!important;">' + i.per_total_visited  + '%</span></div><div class="progress-bar progress-bar-danger" style="width: ' + i.per_total_not_visited  + '%"><span style="font-size: 20px!important;">' + i.per_total_not_visited + '%</span></div></div></td><td><span class="label label-success" style="font-size: 1em;">' + i.total_visited + '</span></td><td><span class="label label-danger" style="font-size: 1em;">' + i.total_not_visited + '</span></td></tr>')
	    				})
	    				
	    				// Se agrega el total
	    				
	    				$('#clientsNV tbody').prepend('<tr><td>Total:</td><td><span class="label label-primary" style="font-size: 1em;">' + data.total_stores + '</span></td><td style="width: 100%;"><div class="progress" style="height: 30px; margin-bottom: 0px;"><div class="progress-bar progress-bar-success" style="width:' + data.per_total_visited + '%"><span style="font-size: 20px!important;">' + data.per_total_visited +'%</span></div><div class="progress-bar progress-bar-danger" style="width:'+ data.per_total_not_visited + '%"><span style="font-size: 20px!important;">' + data.per_total_not_visited + '%</span></div></div></td><td><span class="label label-success" style="font-size: 1em;">' + data.total_visited + '</span></td><td><span class="label label-danger" style="font-size: 1em;">' + data.total_no_visited + '</span></td></tr>')
	    				
	    				
	    			} else {
						$('#clientsNV tbody').prepend('<tr><td colspan="5">Sin clientes que mostrar</td></tr>')
					}
	    		})*/
	    		
	    	});

			$('#datepickerNotVisited').val(today + ' - ' + today);
	</script>

</body>

</html>