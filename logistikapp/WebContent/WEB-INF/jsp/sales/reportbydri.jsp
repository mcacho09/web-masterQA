<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
	<title>Reporte Trx - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	
	<!-- libraries -->
	<link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="css/libs/nanoscroller.css" type="text/css" />
	<link rel="stylesheet" href="css/libs/select2.css" type="text/css" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/libs/daterangepicker.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/select2.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.fixedHeader.css">
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.tableTools.css">
    
    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">

	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
	
	 <!-- Dwr script--> 
    <script type='text/javascript' src='dwr/interface/LogisticServiceBean.js'></script>
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
                                        <li class="active"><span><spring:message code="label.breadcrumb.sales"/></span></li>
									</ol>
								</div>
							</div>

							<div class="row">
								<div class="col-lg-12">
								
									<div class="main-box clearfix">
									
                                        <header class="main-box-header clearfix">
                                        	<div id="header-tools" class="pull-left">
                                        	<authz:authorize ifNotGranted="DRI,DRI1,DRI2,DRI3,DRI4,DRI5">
                                                    <div class="pull-left hidden-xs">
	                                                    <c:if test="${fn:length(list) > 0}">
                                                        <div class="btn-group">
	                                                        <button id="checkAllButton" class="btn btn-primary" type="button" title="Seleccionar Todos" data-toggle="tooltip" data-placement="bottom">
	                                                            <i class="fa fa-square-o"></i>
	                                                        </button>
			                                                 <button id="downloadreports" class="btn-report btn btn-primary" type="button" title="Descargar Reporte Trx Resumido" data-toggle="tooltip" data-placement="bottom">
			                                                 	<i class="fa fa-download"></i> Resumen TRX
			                                                 </button>
			                                                 <button id="reportTrxButton" class="btn-report btn btn-primary" type="button" title="Descargar Reporte Trx Detallado" data-toggle="tooltip" data-placement="bottom">
			                                                    <i class="fa fa-download"></i> TRX / Producto
				                                             </button>
	                                                    </div>
	                                                 
                                                    	<div id="header-num-selected" class="btn-group num-selected" style="display:none;"><span>NaN</span></div> 
			                                            </c:if>
                                                    </div>
                                                                                                        
                                                </authz:authorize>
                                        	</div>
                                            
                                            
                                            <!-- filtro fecha -->
                                            <div class="col-lg-3 pull-right">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-calendar-o"></i></span>
                                                    <input type="text" class="form-control" id="datepickerDateRange" >
                                                </div>
                                            </div>
                                        </header>

										<div class="main-box-body clearfix">
											<div class="table-responsive">
												<!-- list-product -->
												<table id="table-example"class="table table-striped table-hover table-product">
													<thead>
														<tr>
															<th>Vendedor</th>
															<th>Ventas</th>
															<th>Productos</th>
															<th>No. de trx</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${ list }" var="i">
															<tr>
																<td>
																	<div class="checkbox-nice" style="display: inline-block;">
		                                                                	<input type="checkbox" id="ch_${i.id_user }" class="repSelect" data-id="${i.id_user}" >
		                                                                	<label for="ch_${i.id_user }"></label>
		                                                            </div>
																	<button tipe="button" data-idu="${i.id_user }" class="btn btn-link btn-dri">${i.username }</button>
																</td>
																<td>
																	<span class="label label-primary">$${i.sales }</span>
																</td>
																<td>
																	${i.total_products }
																</td>
																<td>
																	${i.no_trx }
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table><!-- /list-product -->
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
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/dataTables.fixedHeader.js"></script>
    <script src="js/dataTables.tableTools.js"></script>
    <script src="js/jquery.dataTables.bootstrap.js"></script>
    
    <script src="js/moment.min.js"></script>
    <script src="js/daterangepicker.js"></script>
    
    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
    <script src="js/pace.min.js"></script>
    
    <!-- this page specific inline scripts -->
    <script type="text/javascript">
    
	    $(document).on("ready",function() {
	    	
	        fini = "${fini}";
	        ffin = "${ffin}";
	
	        $('#datepickerDateRange').daterangepicker({
	        	"format": "DD/MM/YYYY",
	     	    "ranges": {
		     	    'Hoy': [moment(), moment()],
		            'Ayer': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		            'Mañana': [moment().add(1, 'days'), moment().add(1, 'days')],
		            'Últimos 3 dias': [moment().subtract(3, 'days'), moment()],
		            'Próximos 3 dias': [moment().add(1, 'days'),moment().add(3, 'days')],
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
	         	    "daysOfWeek": ["Do","Lu","Ma","Mi","Ju","Vi","Sa"],
	         	    "monthNames": ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],
	         	    "firstDay": 1
	         	},
	         	"startDate": "${fini}",
	         	"endDate": "${ffin}",
	         	"opens": "left",
	         	"buttonClasses": "btn btn-xs"
	         }, function(start, end, label) {
	        	 location.href = "?fini=" + start.format('DD/MM/YYYY') + "&ffin=" + end.format('DD/MM/YYYY')
	        	 $('#datepickerDateRange span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
	         }, cb);
	        
	        function cb(start, end) {
	             $('#datepickerDateRange span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
	         }
	    	
	    	cb(moment().startOf('month'), moment().endOf('month'))
	        
	        $('#table-example').dataTable({
	            'paging': true,
	            'info': true,
	            'pageLength': 25,
	            'lengthChange' : true,
	            'filter': false,
	            "bFilter": false,
	            'searching': true,
	            "order": [[ 0, "desc" ]],
	            "language": {
	                "search": "Buscar:",
	                "lengthMenu": "Mostrar _MENU_ registros",
	                "info": "P&aacute;gina _PAGE_ de _PAGES_",
	                "zeroRecords": "No hay datos"
	             },
	             'oTableTools': {
	                "sSwfPath": "swf/copy_csv_xls_pdf.swf"
	             }
	        });           
	        $('#datepickerDateRange').val( '${fini} - ${ffin}' );
	        
	        $('.btn-dri').on('click', function() {
	        	var $btn = $(this)
	        	location.href = "productreport.htm?idu=" + $btn.data('idu') + "&fini=${fini}&ffin=${ffin}"
	        })
	        
	        $('#reportTrxButton').click( function() {
        	    // Se obtienen rango de fechas para obtener datos
	        	console.log("report/trx ==> fini=${fini}");
	        	console.log("report/trx ==> ffin=${ffin}");
	        	var href = "reporttrxproduct.htm?fini=${fini}&ffin=${ffin}"
	        	if ($('.repSelect:checked').size() > 0 ) {
		       		var tmp = []
		       		$('.repSelect:checked').each(function(i, e){
		       			tmp.push(e.dataset.id)
		       		})
		       		href += "&idus=" + tmp
		       	} else {
		       		var tmp = []
		       		$('.repSelect').each(function(i, e){
		       			tmp.push(e.dataset.id)
		       		})
		       		href += "&idus=" + tmp
		       	}
	        	location.href = href + "&idu=${idu}"
	       	}); // reportTrxButton
	        	    
	        $('#downloadreports').click( function() {
	        	// Se obtienen todos los clientes seleccionados
	        	href = "productreportfromtrans.htm?trx=true&dol=${useracegi.profile}&fini=${fini}&ffin=${ffin}";
	        	if ($('.repSelect:checked').size() > 0 ) {
		       		var tmp = []
		       		$('.repSelect:checked').each(function(i, e){
		       			tmp.push(e.dataset.id)
		       		})
		       		href += "&idus=" + tmp
		       	} else {
		       		var tmp = []
		       		$('.repSelect').each(function(i, e){
		       			tmp.push(e.dataset.id)
		       		})
		       		href += "&idus=" + tmp
		       	}
	        	location.href = href + "&idu=${idu}"
	        }); // downloadreports
	
	    });
    
	</script>
    
</body>

</html>