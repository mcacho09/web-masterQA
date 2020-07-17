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
									
									<div class="clearfix">
										<h1 class="pull-left"><a href="productreport.htm">Reporte / Trx</a></h1>
                                        
                                     <!--    <div class="pull-right hidden-xs">
                                            <button type="button" onclick="location.href='productadd.htm?accion=add';" class="btn btn-primary btn-xs"><i class="fa fa-plus-circle fa-lg"></i> Nuevo</button>
                                            button type="button" class="btn btn-default btn-xs"><i class="fa fa-download fa-lg"></i> Exportar</button 
                                            <button type="button" class="btn btn-primary btn-xs" onclick="location.href='productimport.htm';" ><i class="fa fa-cloud-upload fa-lg"></i> Importar</button>
                                        </div>
                                     -->
                                        <div class="pull-right hidden-lg hidden-md hidden-sm">
                                            <button type="button" onclick="location.href='productadd.htm?accion=add';" class="btn btn-primary btn-xs"><i class="fa fa-plus-circle fa-lg"></i></button>
                                            <!-- button type="button" class="btn btn-default btn-xs"><i class="fa fa-download fa-lg"></i> Exportar</button -->
                                            <button type="button" onclick="location.href='productimport.htm';" class="btn btn-primary btn-xs"><i class="fa fa-cloud-upload fa-lg"></i></button>
                                        </div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
			                        <div class="main-box infographic-box">
			                            <i class="fa fa-user red-bg"></i>
			                            <span class="headline">Vendedor</span>
			                            <span class="value">${user.username }</span>
			                        </div>
			                    </div>
			                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
			                        <div class="main-box infographic-box">
			                            <i class="fa fa-shopping-cart emerald-bg"></i>
			                            <span class="headline">Ventas</span>
			                            <span class="value">$${metrics.sale }</span>
			                        </div>
			                    </div>
								<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
			                        <div class="main-box infographic-box">
			                            <i class="fa fa-cube emerald-bg"></i>
			                            <span class="headline">Productos</span>
			                            <span class="value">${metrics.productsQty }</span>
			                        </div>
			                    </div>
								<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
			                        <div class="main-box infographic-box">
			                            <i class="fa fa-usd red-bg"></i>
			                            <span class="headline">Merma</span>
			                            <span class="value">${metrics.decrease }</span>
			                        </div>
			                    </div>
								<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
			                        <div class="main-box infographic-box">
			                            <i class="fa fa-money green-bg"></i>
			                            <span class="headline">Utilidad</span>
			                            <span class="value">${metrics.utility }</span>
			                        </div>
			                    </div>
								<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
			                        <div class="main-box infographic-box">
			                            <i class="fa fa-line-chart emerald-bg"></i>
			                            <span class="headline">Ticket Promedio</span>
			                            <span class="value">$<fmt:formatNumber maxFractionDigits="2" type="number" value="${metrics.ticketavg }"></fmt:formatNumber></span>
			                        </div>
			                    </div>
							</div>

							<div class="row">
								<div class="col-lg-12">
								
									<div class="main-box clearfix">
									
                                        <header class="main-box-header clearfix">
                                        <div id="header-tools" class="pull-left">
                                                <form name="form1" id ="form1" method="GET" class="form-inline" role="form">
                                                    <input type="hidden" id="idts" name="idts" value=""/>
        											<input type="hidden" id="accion" name="accion" value="">
                                                    <input type="hidden" id="find" name="find" value="true" />
                                                    <input type="hidden" id="fini" name="fini" value=""/>
                                                    <input type="hidden" id="ffin" name="ffin" value=""/>
                                                    <input type="hidden" id="idu" name="idu" value="${idu }" />
                                                </form>
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
                                            <div class="col-lg-3 col-sm-6 pull-right">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-calendar-o"></i></span>
                                                    <input type="text" class="form-control" id="datepickerDateRange" >
                                                </div>
                                            </div>
                                        </header>

										<div class="main-box-body clearfix">
											<div class="table-responsive">
												<form method="post" id="form_1" >
													<input type="hidden" name="id_category_product" id="id_category_product" />
												</form>
												<!-- list-product -->
												<table id="table-example"class="table table-striped table-hover table-product">
													<thead>
														<tr>
															<th></th>
															<th class="text-center"><span>NumTrx</span></th>
															<th class="text-left"><span>Fecha</span></th>
															<th class="text-left"><span>Cliente</span></th>
															<th class="text-center hidden-xs"><span>Vendedor</span></th>
															<th class="text-right"><span>Venta</span></th>
															<th class="text-right"><span>Cambio</span></th>
															<th class="text-right"><span>Devolución</span></th>
															<th class="text-center"><span>Ticket total</span></th>
															<th class="text-center"><span>Estado</span></th>
															<th class="text-center">Nota</th>
														</tr>
													</thead>
													<tbody>
                                                        <c:if test="${fn:length(list) == 0}">
                                                            <tr>
	                                                            <td class="text-left"><spring:message code="label.sales.product.list.notran"/></td>
	                                                            <td></td>
	                                                            <td></td>
	                                                            <td></td>
	                                                           	<td></td>
	                                                            <td></td>
	                                                            <td></td>
	                                                            <td></td>
	                                                            <td></td>
	                                                            <td></td>
                                                            </tr>
                                                        </c:if>
														<c:forEach var="rep" items="${list}">
															<c:set var="statustag" value="default"/>
                                                            <c:set var="statustext" value="NaN"/>
                                                            <c:set var="statusicon" value="NaN"/>
                                                            <c:choose>
                                                                <c:when test="${rep.status == 'CAN'}">
                                                                    <c:set var="statustag" value="danger"/>
                                                                    <c:set var="statustext" value="Cancelado"/>
                                                                    <c:set var="statusicon" value="times"/>
                                                                </c:when>
                                                                <c:when test="${rep.status == 'APR'}">
                                                                    <c:set var="statustag" value="success"/>
                                                                    <c:set var="statustext" value="Pagado"/>
                                                                    <c:set var="statusicon" value="check"/>
                                                                </c:when>
                                                            </c:choose>
														
															<tr>
																<td>
																	<div class="checkbox-nice">
		                                                                	<input type="checkbox" id="ch_${rep.id_order }" class="repSelect" data-id="${rep.id_order}" >
		                                                                	<label for="ch_${rep.id_order }"></label>
		                                                            </div>
																</td>
																<td><a href="saledetail.htm?ido=${rep.id_order }" class="btn btn-link">${rep.trx_num}</a></td>
															    <td class="text-left">
															        <small><fmt:formatDate value="${rep.invoice}" pattern="dd/MM/yyyy"/></small>
															    </td>
															    <td class="text-left">
															        ${rep.name}
															    </td>
																<!-- <td class="text-center"></td>-->
																<td class="text-center hidden-xs">
																    ${rep.username}
																</td>
																<td class="text-right">
																    <fmt:formatNumber type="currency" currencySymbol="$" value="${rep.qty_vta_sug}" minFractionDigits="2"/><br/>
																    <small class="text-primary">Cant.: ${rep.qty_vta}</small>
																</td>
																<td class="text-right">
																    ${ rep.qty_chg > 0 ? rep.qty_chg : '' }
																</td>
																<td class="text-right">
																	<fmt:formatNumber type="currency" currencySymbol="$" value="${rep.qty_vta_dev_sug}" minFractionDigits="2"/><br/>
																    <small class="text-primary">Cant.: ${rep.qty_dev}</small>
																</td>
																<td class="text-center">
																	$${ rep.qty_vta_sug - rep.qty_vta_dev_sug }
																</td>
																<td class="text-center">
																		<span class="badge badge-${rep.status == 'APR' ? 'success' : rep.status == 'NP'?'warning':'danger'}
			                                                            	 	<c:if test="${fn:contains(useracegi.profile, 'DRI') == false}">activebtn"
																			style="cursor:pointer;</c:if>"
																			data-id="${rep.id_order}" data-active="${rep.status}"
																			data-toggle="tooltip" data-placement="bottom"
																			title="Clic para cambiar estado" data-notrx="${rep.trx_num }">
																<c:choose>
																	<c:when test="${rep.status == 'APR' }">																	
																			<i class="fa fa-check"></i> Pagado
																	</c:when>
																	<c:when test="${rep.status == 'NP' }">																	
																			<i class="fa fa-times"></i> Por Pagar
																	</c:when>
																	<c:when test="${rep.status == 'CAN' }">																	
																			<i class="fa fa-times"></i> Cancelado
																	</c:when>
																</c:choose>   
																		</span>
                                                                </td>
                                                                <td class="text-center">
                                                                	<button type="button" class="send-ticket btn btn-primary btn-xm" data-id="${rep.id_order }">Enviar <i class="fa fa-send"></i></button>
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

<!-- Dwr script -->
    <script type='text/javascript' src='dwr/interface/FinancialServiceBean.js'></script>		
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	
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
    var dto = new Object();
    var status = [];        
    var ids;
    var startString;
    var endString;
    var startDate;                
    var endDate;              
    var endMilis;
    var fini;
    var ffin;
    $(document).ready(function() {
        
         function cb(start, end) {
             $('#datepickerDateRange span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
         }
        fini = "${fini}";
        ffin = "${ffin}";

        cb(moment().startOf('month'), moment().endOf('month'));

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
         		$( '#fini' ).attr( 'value', start.format("DD/MM/YYYY") );
         		$( '#ffin' ).attr( 'value', end.format("DD/MM/YYYY") );
         		$( '#form1' ).submit();             	
        	}, cb);
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
        $('#cancelButton').prop('disabled',true);

    });
    
   
    
    
    $('#checkAllButton').click(function(){
        var id;
        $(this).find("i").toggleClass("fa-square-o fa-check-square-o");
        // fa-square-o check vacio
        
        var res = !$(this).find("i").hasClass('fa-square-o')
		$('.repSelect').prop('checked', res).trigger('change')
    });
    
    $('#cancelButton').click( function() {            
        // Se obtienen las tranacciones que estan en estdo 'ACP'
        var ids = [];
        var id = [];
        $("input[type=checkbox]:checked").each(function(index,e) {
	            ids.push($(this).attr("value"));
	            id.push(parseInt($(this).attr("value")));
	            alert (ids);
	            FinancialServiceBean.getOrderById($(this).data('id'), function(data){
	    			alert (data.status);
	    		if (data != null && data != undefined){
	    	    	        
	            swal({   
	    			title: "Alerta",
	    			text: '¿En verdad desea cancelar esta transacción?',   
	    			type: "warning",   
	    			showCancelButton: true,   
	    			confirmButtonColor: "#DD6B55",   
	    			confirmButtonText: "Aceptar",
	    			cancelButtonText: 'Cancelar',
	    			closeOnConfirm: false 
	            }, function(){                   				
	    			if ( ids.length > 0 )                				
	    				FinancialServiceBean.getOrderById(function(d){
	    					d.forEach(function(e,i){
	    						if(id.indexOf(e.id_order) > -1){                    						
	        						swal({
	        							title:'Error',
	        							text:'Error al eliminar una o más transacciones, pertenecen a una orden',
	        							type:'error',                            						
	            						showConfirmButton: true,
	            						timer: 2000
	            					});
	    						}else{
	    							var UpdOrder = data;
	    							UpdOrder.status = (data.status == 'APR'?'CAN':'APR');
	    							FinancialServiceBean.updOrder(UpdOrder, function(data){
	    								var obj = {};
	    								if (data > 0){
	    									obj.title = 'Mensaje';
	    									obj.msj = 'Orden ' + (data.status == 'APR'?'cancelada':'aprobada') + ' con exito';
	    									obj.type = 'success';
	    									$(btn).removeClass('badge-' + (data.status == 'APR'?'success':'danger'));
	    									//$(btn).addClass('badge-' + (data.status == 'APR'?'danger':'success'));
	    									//$(btn).html((data.status == 'APR'?'<i class="fa fa-times"></i> Cancelado':'<i class="fa fa-check"></i> Pagado'));
	    									$(btn).data('status', (data.status == 'APR'?'CAN':'APR'));        						
	    								}else{
	    									obj.title = 'Error';
	    									obj.msj = 'Error al cambiar el estado de la orden, intente más tarde';
	    									obj.type = 'error';
	    								}
	    								swal({
	    									title: obj.title, 
	    									text: obj.msj, 
	    									type: obj.type,
	    									showConfirmButton: true,
	    									timer: 2000
	    								});
	    							}); //UpdOrder                   							
	    						} //else
	    					});
	    				});
	            	});
	            }
	    	});
	            
        }); //input
		
});
    
    
    $('.repSelect').on('change', function(){
    	var size = $('.repSelect:checked').size()
    	var $header = $("#header-num-selected")
    	if (size > 0)
    		$header.text( $('.repSelect:checked').size() + " transaccion(es) seleccionada(s)" ).show()
    	else
    		$header.text( $('.repSelect:checked').size() + " transaccion(es) seleccionada(s)" ).hide()
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
	       		href += "&ids=" + tmp
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
	       		href += "&ids=" + tmp
	       	}
        	location.href = href + "&idu=${idu}"
        }); // downloadreports
    
    $('.activebtn').click( function() { 
    	var $btn = $(this);
    	swal({   
    		title: "Alerta",
    		text: '¿En verdad desea cambiar el estado de la orden?<br/><br/> <div class="row"><div class="col-xs-12"><div class="form-group"><label for="input" class="col-sm-2 control-label">Estado:</label><div class="col-sm-10"><select name="status" id="inputStatus" class="form-control" required="required"><option value="APR">Pagado</option><option value="NP">Por Pagar</option><option value="CAN">Cancelar</option></select></div></div></div></div>',
    		type: "warning",   
    		showCancelButton: true,   
    		confirmButtonColor: "#DD6B55",   
    		confirmButtonText: "Aceptar",
    		cancelButtonText: 'Cancelar',
    		closeOnConfirm: false,
    		html : true
    	}, function(){   
    		var UpdOrder = {};
    		UpdOrder.id_order = $btn.data('id')
    		UpdOrder.status = $('#inputStatus').val();
    		var dto = {
    				updOrder: UpdOrder,
    				id_user: ${idu},
    				id_supplier: ${useraccess.id_supplier}
    		};
    		FinancialServiceBean.updateOrderAndRecoverToAlmacen(dto, function(data){
    			var obj = {};
    			if (data > 0){
    				obj.title = 'Mensaje';
    				obj.msj = 'Éxito al cambiar el estado de la orden';
    				obj.type = 'success';
    				var bClass = 'success'
    				if (UpdOrder.status == 'CAN') bClass = 'danger'
    				if (UpdOrder.status == 'NP') bClass = 'warning'
    				$btn.removeClass('badge-' + ($btn.data('active') == 'CAN'?'danger':($btn.data('active') == 'NP'?'warning':'success')));
    				$btn.addClass('badge-' + bClass);
    				$btn.html((UpdOrder.status == 'APR'?'<i class="fa fa-check"></i> Pagado':('<i class="fa fa-times"></i> ' + (UpdOrder.status == 'NP'?'Por pagar':'Cancelado'))));
    				$btn.data('status', UpdOrder.status); 
    				var AddNotificationDTO = new Object();
	             	AddNotificationDTO.created = new Date();
	             	AddNotificationDTO.icon = 'fa fa-shopping-cart';
	             	AddNotificationDTO.id_user = ${useracegi.id_user};
	             	AddNotificationDTO.message = 'Se cambió el estado a la transacción número <span class="label label-info">' + $btn.data('notrx') + '</span> en el viaje <b>${viaje.name}</b>';
	             	AddNotificationDTO.priority = '1';
	             	AddNotificationDTO.id_supplier = ${useraccess.id_supplier};
	             	AddNotificationDTO.link = "productreport.htm";
	             	// Se persiste el objeto para agregar notificacion
	             	if ("${profile}" == 'DRI'){
		             	UserNotificationBean.createNotification(AddNotificationDTO, "003", function(data){});
	 				}else{
	 					var list = [];
	 					list.push(parseInt('${useracegi.id_user}'));
	 					UserNotificationBean.createNotificationWithList(AddNotificationDTO, list, function(data){});
	 				}
    			}else{
    				obj.title = 'Error';
    				obj.msj = 'Error al cambiar el estado de la orden, intente más tarde';
    				obj.type = 'error';
    			}
    			swal({
    				title: obj.title, 
    				text: obj.msj, 
    				type: obj.type,
    				showConfirmButton: true,
    				timer: 2000
    			});
    		});
    	});
    }); //activateButton
    
    $('.send-ticket').on('click', function(){
    	var $btn = $(this)
    	$btn.prop('disabled', true)
    	
    	var html = $btn.html()
    	
    	$btn.html("Enviando <i class='fa fa-spinner fa-pulse'></i>")
    	
    	FinancialServiceBean.sendTicketByIdOrder($btn.data('id'), function(data){
    		
    		if (!!data) {
    			swal('Alerta', data, "warning")
    		}
    		
    	})
    	
    	setTimeout(function(){
    		$btn.prop('disabled', false)
        	$btn.html(html)
    	}, 3000)
    })
    
	</script>
    
</body>

</html>