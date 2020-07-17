<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Mix de Productos - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	
	<!-- libraries -->
	<link type="text/css" rel="stylesheet" href="css/libs/font-awesome.css"/>
	<link type="text/css" rel="stylesheet" href="css/libs/nanoscroller.css"/>
	<link rel="stylesheet" type="text/css" href="css/libs/select2.css"/>
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">

	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
	
	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
	<style>
		.no-price {
			background-color: rgba(241, 196, 15, 0.5)!important;
		}
		
		.no-price:hover > td {
			background-color: #f1c40f!important;
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
                <section id="col-left" class="col-left-nano">
                    <c:import url="/html/menu-left.jsp"/>
                </section>
            </div>

				<div id="content-wrapper">
					<div class="row">
						<div class="col-lg-12">

							<div class="row">
								<div class="col-lg-12">
									<ol class="breadcrumb">
                                        <li><a href="home.htm">Dashboard</a></li>
                                        <li class="active"><span>Ventas</span></span></li>
									</ol>
									
									<div class="clearfix">
										<h1 class="pull-left">Mix de Productos</h1>
									
										<div class="pull-right">
											<div class="form-group form-group-select2">
												<select style="width:200px" id="sel21">
													<option value="NA">- Categoría -</option>
													<c:forEach items="${category_list }" var="i">
														<option value="${i.id_store_category }" ${idCat == i.id_store_category?'selected':'' }>${i.name }</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>

								</div>
							</div>

							<div class="row">
								<div class="col-lg-12">
									<div class="main-box clearfix">
                                        <header class="main-box-header clearfix">
											<h2 class="pull-left">Productos Activados</h2>
                                        </header>
										<div class="main-box-body clearfix">
											<div class="table-responsive">
												<table class="table table-striped table-hover" id="table-products">
													<thead>
														<tr>
															<th><span>Producto</span></th>
															<th class="text-center"><span>Costo</span></th>
															<th class="text-center"><span>Sugerido</span></th>
															<th><span>Venta</span></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${product_list }" var="i">
															<tr class="${i.id_store_category_product != null && i.id_store_category_product != 0 ?'':'no-price'}">
															    <td>
															    	${i.name_short } 
															    	<span class="badge badge-default">${i.code }</span>
															    	<br/>
															    	<small>${i.name_long }</small>
															    </td>
																<td class="text-center"><span class="badge badge-default">$${i.price_cost }</span></td>
																<td class="text-center"><span class="badge badge-info">$${i.price_sale }</span></td>
																<td class="col-md-2">
																	<input type="number" min="0.00" step="0.10" value="${i.id_store_category_product != null && i.id_store_category_product != 0?i.price_sale_category:0.0 }" data-id="${i.id_store_category_product }" data-id-product="${i.id_product }" class="col-md-6 form-control input-price"/>
																</td>
															</tr>
														</c:forEach>
															<!-- <tr class="no-price">
                                                                <td><input type="checkbox" checked="checked" /></td>
															    <td>Mega Nuez <span class="badge badge-default">7501055305339</span><br/><i>Descripci&oacute;n adicional del producto</i></td>
																<td class="text-right"><span class="badge badge-default">$12.37</span></td>
																<td class="text-right"><span class="badge badge-info">$26</span></td>
																<td class="col-md-2"><input type="text" value="$26" class="col-md-6"/></td>
															</tr>
															<tr>
                                                                <td><input type="checkbox" checked="checked" /></td>
															    <td>Mega Almendras <span class="badge">123456789012</span><br/><i>Descripci&oacute;n adicional del producto</i></td>
																<td class="text-right"><span class="badge badge-default">$15.99</span></td>
																<td class="text-right"><span class="badge badge-info">$26</span></td>
																<td class="col-md-2"><input type="text" value="$26" class="col-md-6"/></td>
															</tr> -->
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div><!-- /row -->

							<!-- <div class="row">
								<div class="col-lg-12">
									<div class="main-box clearfix">
                                        <header class="main-box-header clearfix">
											<h2 class="pull-left">Cat&aacute;logo de Productos</h2>
										
											<div class="filter-block pull-right">
												<div class="form-group pull-left">
													<input type="text" class="form-control" placeholder="Buscar por...">
													<i class="fa fa-search search-icon"></i>
												</div>
											</div>
                                        </header>
										<div class="main-box-body clearfix">
											<div class="table-responsive">
												<table class="table table-striped table-hover">
													<thead>
														<tr>
															<th><input type="checkbox"/></th>
															<th><span>Producto</span></th>
															<th class="text-right"><span>Costo</span></th>
															<th class="text-right"><span>Sugerido</span></th>
															<th><span>C&oacute;digo</span></th>
															<th></th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td><input type="checkbox" /></td>
															<td>Mega Cookies<br/><i>Descripci&oacute;n adicional del producto</i></td>
															<td class="text-right">$11.20</td>
															<td class="text-right">$23</td>
															<td>750244578744</td>
															<td><a href="#" class="table-link"><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-image fa-stack-1x fa-inverse"></i></span></a></td>
														</tr>
														<tr>
															<td><input type="checkbox" /></td>
															<td>Pela Pop<br/><i>Descripci&oacute;n adicional del producto</i></td>
															<td class="text-right">$5.10</td>
															<td class="text-right">12</td>
															<td>7501055305339</td>
															<td><a href="#" class="table-link"><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-image fa-stack-1x fa-inverse"></i></span></a></td>
														</tr>
														<tr>
															<td><input type="checkbox" /></td>
															<td>Nesquik<br/><i>Descripci&oacute;n adicional del producto</i></td>
															<td class="text-right">$7.99</td>
															<td class="text-right">$7.99</td>
															<td>750244578744</td>
															<td><a href="#" class="table-link"><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-image fa-stack-1x fa-inverse"></i></span></a></td>
														</tr>
														<tr>
															<td><input type="checkbox" /></td>
															<td>Galáctea<br/><i>Descripci&oacute;n adicional del producto</i></td>
															<td class="text-right">$7.99</td>
															<td class="text-right">$7.99</td>
															<td>7501055305339</td>
															<td><a href="#" class="table-link"><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-image fa-stack-1x fa-inverse"></i></span></a></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div><!-- /row --> 

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
    <script src="js/jquery.expander.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/select2.min.js"></script>
	
	<!-- Dwr script -->
    <script type='text/javascript' src='dwr/interface/FinancialServiceBean.js'></script>		
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>

	<!-- this page specific inline scripts -->
	<script>
	$(document).on('ready',function() {
		//nice select boxes
		$('#sel21').select2();
		
		var $table = $('#table-products').dataTable( {
            'paging': false,
            'pageLength' : 15,
            'lengthChange' : false,
            'info': false,
            'searching': true,
            "language": {
                "search": "Buscar Producto:",
                "zeroRecords":    "No se encontraron productos"
            },
            'pageLength': 100
        }); // table   
        
        $('#sel21').on('change', function(){
        	location.href = 'productmix.htm?idcat=' + this.value
        })
        
        $('.input-price').on('focusout', function(){
        	var $input = $(this)
        	if (!!parseInt(this.value)){
	        	swal({   
					title: "Un momento!",   
					text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Actualizando precio...</div></div>',   
					html: true, 
					showConfirmButton: false 
				});
	        	
	        	if (parseInt($input.data('id')) > 0) {
	        		var obj = {
		        			price_sale: $input.val(),
		        			id_store_category_product: $input.data('id')
	        		}
	        		console.log("upd")
	        		FinancialServiceBean.updateStoreCategoryProduct(obj, function(data){
	        			
	        			var res = {}
	        			if (data > 0) {
	        				res.title = "Exito";
	        				res.msg = "Se actualizo el precio";
	        				res.type = "success"
	        			} else {
	        				res.title = "Error";
	        				res.msg = "No se actualizo el precio\nIntente más tarde";
	        				res.type = "error"
	        			}
	        			swal(res.title, res.msg, res.type)
	        			
	        		})
	        	} else  {
	        		var obj = {
		        			id_store_category : parseInt("${idCat}"),
		        			id_product: parseInt($input.data('idProduct')),
		        			price_sale: parseFloat($input.val()).toFixed(2)
		        	}
	        		console.log("insert")
	        		FinancialServiceBean.addStoreCategoryProdut(obj, function(data){
	        			
	        			var res = {}
	        			if (data > 0) {
	        				res.title = "Exito";
	        				res.msg = "Se registro el nuevo precio";
	        				res.type = "success"
	        			} else {
	        				res.title = "Error";
	        				res.msg = "No se registro el nuevo precio\nIntente más tarde";
	        				res.type = "error"
	        			}
	        			swal(res.title, res.msg, res.type)
	        			$input.parent().parent().removeClass('no-price')
	        		})
	        	}
	        	
	        	console.log(JSON.stringify(obj))
	        	
        	}
        })
        
	});
	</script>

</body>

</html>