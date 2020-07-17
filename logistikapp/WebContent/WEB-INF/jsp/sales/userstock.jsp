<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
	<title>Carga abordo - LogistikApp</title>
	
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
	
	<style type="text/css">
		.no-products {
			background-color: rgba(255, 0, 0, 0.28)!important;
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
                    <c:import url="/html/menu-left.jsp" />
                </section>
            </div><!-- /nav-col -->

			<div id="content-wrapper">

                <div class="row" id="app">
                    <div class="col-lg-12">
                        
                        <div class="row">
                            <div class="col-lg-12 hidden-sm hidden-xs">
                                <ol class="breadcrumb">
                                    <li><a href="home.htm">Inicio</a></li>
                                    <c:if test="${fn:contains(useracegi.profile, 'SUP') }">
                                    	<li><a href="stock.htm">Almacén</a></li>
                                    </c:if>
                                    <li class="active"><span>Carga abordo</span></li>
                                </ol>
                                
                                <h1 class="pull-left">Carga abordo</h1>
                            </div>
                        </div>
                        
                        <div class="row">
                        	<div class="col-lg-4 col-sm-12 col-xs-12">
                                <div class="main-box infographic-box">
                                    <span><i class="fa fa-archive emerald-bg"></i></span>
                                    <span class="headline">Vehículo</span>
                                    <span class="value">
                                        <small v-text="almacen.code">Code</small>
                                    </span>
                                </div>
                            </div>
                            <div class="col-lg-4 col-sm-6 col-xs-12">
                                <div class="main-box infographic-box">
                                    <span><i class="fa fa-user green-bg"></i></span>
                                    <span class="headline">Encargado</span>
                                    <span class="value">
                                        <span v-text="almacen.username">Username</span>
                                    </span>
                                </div>
                            </div>
                            <div class="col-lg-4 col-sm-6 col-xs-12">
                                <div class="main-box infographic-box">
                                    <span><i class="fa fa-building yellow-bg"></i></span>
                                    <span class="headline">Plaza</span>
                                    <span class="value">
                                        <span v-text="almacen.retail">Retail</span>
                                    </span>
                                </div>
                            </div>
                        </div>

						<div class="row" v-if="products.length > 0">
							<div class="col-xs-12">
								<div class="main-box clearfix">
									<div class="main-box-content">
										<div class="table-responsive">
											<table class="table table-hover">
												<thead>
													<tr class="bg-success">
														<th class="text-center">Productos</th>
														<c:if test="${!fn:contains(useracegi.profile, 'DRI') }">
														<th class="text-center">$ Costo</th>
														</c:if>
														<th class="text-center">$ Ventas</th>
													</tr>
												</thead>
												<tbody>
													<tr class="text-center text-primary">
														<td><span v-text="'Piezas: ' + qtyProducts"></span> <span
															v-text="'Cajas: ' + qtyPkg"></span> <span
															v-text="'Total: ' + totalProducts"></span></td>
														<td>{{ totalCost | money }}</td>
														<td>{{ totalSale | money }}</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row" v-if="productsChg.length > 0 || productsDev.length > 0">
							<div class="col-xs-12 text-right">
								<button type="button" @click="removeMerma" class="btn btn-xs btn-danger btn-liquidate">
									<!--<span class="hidden-xs hidden-sm">-->Devolver merma<!--</span>--> <i class="fa fa-send"></i>
								</button>
							</div>
						</div>
						<br v-if="productsChg.length > 0 || productsDev.length > 0" />
						<div class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">
                                    <div role="tabs-wrapper tabs-no-header">
										<!-- Nav tabs -->
										<ul class="nav nav-tabs" role="tablist">
											<li role="presentation">
												<a href="#tab" @click="showType('')" aria-controls="tab" role="tab" data-toggle="tab">Inicial</a>
											</li>
											<li role="presentation" class="active">
												<a href="#tab1" @click="showType('VTA')" aria-controls="tab" role="tab" data-toggle="tab">Stock</a>
											</li>
											<li role="presentation">
												<a href="#home" @click="showType('VDO')" aria-controls="home" role="tab" data-toggle="tab">Vendido</a>
											</li>
											<li role="presentation">
												<a href="#tab2" @click="showType('CHG')" aria-controls="tab" role="tab" data-toggle="tab">Cambios</a>
											</li>
											<li role="presentation">
												<a href="#tab3" @click="showType('DEV')" aria-controls="tab" role="tab" data-toggle="tab">Merma</a>
											</li>
										</ul>
									
										<!-- Tab panes -->
										<div class="tab-content tab-content-body clearfix">
											<div role="tabpanel" class="tab-pane fade" id="tab">
												<h2 class="text-info">Listado de productos</h2>
												<div class="row">
													<div class="col-xs-12">
														<div class="table-responsive">
															<table class="table table-hover table-striped tb-products">
																<thead>
																	<tr>
																		<th>Imagen</th>
																		<th>Nombre</th>
																		<th>Piezas</th>
																		<th>Cajas</th>
																		<th>Total</th>
																	</tr>
																</thead>
																<tbody>
																	<tr v-for="i in productsTotal">
																		<td><img :src="i.image" alt="" width="60px" /></td>
																		<td width="40%">
																			<span>{{ i.name_short }}</span> 
																			<br />
																			<small>{{ i.name_long | truncate(20) }}</small>
																			<br />
																			<small :class="{'text-primary': (i.type == 'PKG'), 'text-info': (i.type != 'PKG')}" v-text="i.type == 'PKG' ? 'Paquete' : 'Pieza'"></small>
																		</td>
																		<td><span class="label label-info" v-text="!!i.piece_in_box ? (i.qty - Math.trunc(i.qty / i.piece_in_box) * i.piece_in_box) : i.qty"></span>
																		</td>
																		<td><span class="label label-primary" v-text="!!i.piece_in_box ? Math.trunc(i.qty / i.piece_in_box) : 'NA'"></span>
																		</td>
																		<td><span class="label label-warning" v-text="i.qty"></span></td>
																	</tr>
																</tbody>
															</table>
														</div>
													</div>
												</div>
											
											</div>
											<div role="tabpanel" class="tab-pane fade active in" id="tab1">
												<h2 class="text-info">Listado de productos</h2>
												<div class="row">
													<div class="col-xs-12">
														<div class="alert alert-warning" v-if="productsSale.length == 0">
															Sin productos para vender
														</div>
														<div class="table-responsive" v-else>
															<table class="table table-hover table-striped tb-products">
																<thead>
																	<tr>
																		<th>Imagen</th>
																		<th>Nombre</th>
																		<!-- 
																		<th>Min</th>
																		<th>Máx</th>
																		-->
																		<th>Piezas</th>
																		<th>Cajas</th>
																		<th>Total</th>
																	</tr>
																</thead>
																<tbody>
																	<tr v-for="i in productsSale">
																		<td><img :src="i.image" alt="" width="60px" /></td>
																		<td width="40%"><span>{{ i.name_short }}</span> <br />
																			<small>{{ i.name_long | truncate(20) }}</small></td>
																		<!-- 
																		<td><span class="label label-primary"
																			v-text="i.min"></span></td>
																		<td><span class="label label-success"
																			v-text="i.max"></span></td>
																		-->
																		<td>
																			<span class="label label-info" v-text="!!i.piece_in_box ? (i.qty - Math.trunc(i.qty / i.piece_in_box) * i.piece_in_box) : i.qty"></span>
																		</td>
																		<td>
																			<span class="label label-primary" v-text="!!i.piece_in_box ? Math.trunc(i.qty / i.piece_in_box) : 'NA'"></span>
																		</td>
																		<td>
																			<span class="label label-warning" v-text="i.qty"></span>
																		</td>
																	</tr>
																</tbody>
															</table>
														</div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane fade" id="home">
												<h2 class="text-info">Listado de productos</h2>
												<div class="row">
													<div class="col-xs-12">
														<div class="alert alert-warning" v-if="productsVDO.length == 0">
															Sin productos vendidos
														</div>
														<div class="table-responsive" v-else>
															<table class="table table-hover table-striped tb-products">
																<thead>
																	<tr>
																		<th>Imagen</th>
																		<th>Nombre</th>
																		<!-- 
																		<th>Min</th>
																		<th>Máx</th>
																		-->
																		<th>Piezas</th>
																		<th>Caja</th>
																		<th>Total</th>
																	</tr>
																</thead>
																<tbody>
																	<tr v-for="i in productsVDO">
																		<td><img :src="i.image" alt="" width="60px" /></td>
																		<td width="40%"><span>{{ i.name_short }}</span> <br />
																			<small>{{ i.name_long | truncate(20) }}</small></td>
																		<!-- 
																		<td><span class="label label-primary"
																			v-text="i.min"></span></td>
																		<td><span class="label label-success"
																			v-text="i.max"></span></td>
																		-->
																		<td>
																			<span class="label label-info" v-text="!!i.piece_in_box ? (i.qty - Math.trunc(i.qty / i.piece_in_box) * i.piece_in_box) : i.qty"></span>
																		</td>
																		<td>
																			<span class="label label-primary" v-text="!!i.piece_in_box ? Math.trunc(i.qty / i.piece_in_box) : 'NA'"></span>
																		</td>
																		<td>
																			<span class="label label-warning" v-text="i.qty"></span>
																		</td>
																	</tr>
																</tbody>
															</table>
														</div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane fade" id="tab2">
												<h2 class="text-info">Listado de productos</h2>
												<div class="row">
													<div class="col-xs-12">
														<div class="alert alert-info" v-if="productsChg.length == 0">
															Sin productos cambiados
														</div>
														<div class="table-responsive" v-else>
															<table class="table table-hover table-striped tb-products">
																<thead>
																	<tr>
																		<th>Imagen</th>
																		<th>Nombre</th>
																		<!-- 
																		<th>Min</th>
																		<th>Máx</th>
																		-->
																		<th>Piezas</th>
																		<th>Cajas</th>
																		<th>Total</th>
																	</tr>
																</thead>
																<tbody>
																	<tr v-for="i in productsChg">
																		<td><img :src="i.image" alt="" width="60px" /></td>
																		<td width="40%"><span>{{ i.name_short }}</span> <br />
																			<small>{{ i.name_long | truncate(20) }}</small></td>
																		<!-- 
																		<td><span class="label label-primary"
																			v-text="i.min"></span></td>
																		<td><span class="label label-success"
																			v-text="i.max"></span></td>
																		-->
																		<td>
																			<span class="label label-info" v-text="!!i.piece_in_box ? (i.qty - Math.trunc(i.qty / i.piece_in_box) * i.piece_in_box) : i.qty"></span>
																		</td>
																		<td>
																			<span class="label label-primary" v-text="!!i.piece_in_box ? Math.trunc(i.qty / i.piece_in_box) : 'NA'"></span>
																		</td>
																		<td>
																			<span class="label label-warning" v-text="i.qty"></span>
																		</td>
																	</tr>
																</tbody>
															</table>
														</div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane fade" id="tab3">
												<h2 class="text-info">Listado de productos</h2>
												<div class="row">
													<div class="col-xs-12">
														<div class="alert alert-info" v-if="productsDev.length == 0">
															Sin productos devueltos
														</div>
														<div class="table-responsive" v-else>
															<table class="table table-hover table-striped tb-products">
																	<thead>
																		<tr>
																			<th>Imagen</th>
																			<th>Nombre</th>
																			<!-- 
																			<th>Min</th>
																			<th>Máx</th>
																			-->
																			<th>Piezas</th>
																			<th>Cajas</th>
																			<th>Total</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr v-for="i in productsDev">
																			<td>
																				<img :src="i.image" alt="" width="60px" />
																			</td>
																			<td width="40%">
																				<span>{{ i.name_short }}</span>
																				<br />
																				<small>{{ i.name_long | truncate(20) }}</small>
																			</td>
																			<!-- 
																			<td>
																				<span class="label label-primary" v-text="i.min"></span>
																			</td>
																			<td>
																				<span class="label label-success" v-text="i.max"></span>
																			</td>
																			-->
																			<td>
																				<span class="label label-info" v-text="!!i.piece_in_box ? (i.qty - Math.trunc(i.qty / i.piece_in_box) * i.piece_in_box) : i.qty"></span>
																			</td>
																			<td>
																				<span class="label label-primary" v-text="!!i.piece_in_box ? Math.trunc(i.qty / i.piece_in_box) : 'NA'"></span>
																			</td>
																			<td>
																				<span class="label label-warning" v-text="i.qty"></span>
																			</td>
																		</tr>
																	</tbody>
																</table>
														</div>
													</div>
												</div>
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
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/dataTables.fixedHeader.js"></script>
    <script src="js/dataTables.tableTools.js"></script>
    <script src="js/jquery.dataTables.bootstrap.js"></script>
    <script src="js/jquery.expander.js"></script>
    
    <!-- Dwr script -->
    <script type='text/javascript' src='dwr/interface/FinancialServiceBean.js'></script>		
    <script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>		
	<script type='text/javascript' src='dwr/engine.js'></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/pace.min.js"></script>

    <!-- this page specific inline scripts -->
    
    <script type="text/javascript">
		function initTable(id){
			$(id).dataTable( {
	            'paging': true,
	            'pageLength' : 15,
	            'lengthChange' : false,
	            'info': false,
	            'searching': true,
	            "language": {
	                "search": "Buscar:",
	                "zeroRecords":    "No se encontro información"
	            },
	         }); // table
		}
		
		
		
	</script>
    
    <script>
    
    	DWREngine.setErrorHandler(console.error);
    
		Vue.filter('truncate', function(string, value) {
		  	return string.substring(0, value) + '...';
		})
		   
		Vue.filter('money', function(value) {
			return "$" + value
		})
		
    	var vue = new Vue({
    		el: "#app",
    		mounted: function() {
    			FinancialServiceBean
	    			.getAlmacenInfoAndProductsByIdAlmacen("${id_almacen}", "${id_supplier}", function(res) {
	    				vue.almacen = res.almacen
	    				vue.products = res.products
	    			})
	    		
	    		FinancialServiceBean
	    			.getProductsFromAlmacen({
	    				id_almacen: "${id_almacen}", 
	    				id_supplier: "${id_supplier}"
	    			}, function(res) {
	    				vue.productsTotal = res
	    			})
	    			
	    		initTable('.tb-products')
    		},
    		data: {
    			almacen: {},
    			products: [],
    			productsTotal: [],
    			type: 'VTA'
    		},
    		computed: {
    			productsSale: function() {
    				return this.products.filter(function(e) {
    					return e.stock_type == "VTA"
    				})
    			},
    			productsVDO: function() {
    				return this.products.filter(function(e) {
    					return e.stock_type == "VDO"
    				})
    			},
    			productsDev: function() {
    				return this.products.filter(function(e) {
    					return e.stock_type == "DEV"
    				})
    			},
    			productsChg: function() {
    				return this.products.filter(function(e) {
    					return e.stock_type == "CHG"
    				})
    			},
    			qtyProducts : function() {
    				
    				if (this.products.length == 0) return 0
    				
    				var total = 0
    				this.products.filter(function(e) {
    					return e.stock_type.indexOf(vue.type) > -1
    				}).forEach(function(e, i) {
    					
    						if (!!e.piece_in_box) {
    							var tmp = e.qty - Math.trunc(e.qty / e.piece_in_box) * e.piece_in_box
    							total += tmp
    						} else {
    							total += parseInt(e.qty)
    						}
    					
    				})
    				return total
    			},
    			qtyPkg: function() {
    				if (this.products.length == 0) return 0
    				
    				var total = 0
    				this.products.filter(function(e) {
    					return e.stock_type.indexOf(vue.type) > -1
    				}).forEach(function(e, i) {
    					if (!!e.piece_in_box) {
    						var tmp = Math.trunc(e.qty / e.piece_in_box)
    						total += tmp
    					}
    				})
    				return total
    			},
    			totalCost: function() {
    				if (this.products.length == 0) return 0.0
    				
    				var total = 0.0
    				this.products.filter(function(e) {
    					return e.stock_type.indexOf(vue.type) > -1
    				}).forEach(function(e, i) {
    					
    					if (!!e.piece_in_box) {
    						var tmp = Math.trunc(e.qty / e.piece_in_box) 
    						total += tmp * e.price_cost_box
    						total += (e.qty - Math.trunc(e.qty / e.piece_in_box) * e.piece_in_box) * e.price_cost
    					} else {
    						total += e.qty * e.price_cost
    					}

    				})
    				return total
    			},
    			totalSale: function() {
    				if (this.products.length == 0) return 0.0
    				
    				var total = 0.0
    				this.products.filter(function(e) {
    					return e.stock_type.indexOf(vue.type) > -1
    				}).forEach(function(e, i) {
    						if (!!e.piece_in_box) {
    							var tmp = Math.trunc(e.qty / e.piece_in_box) 
    							total += tmp * e.price_sale_box
    							total += (e.qty - Math.trunc(e.qty / e.piece_in_box) * e.piece_in_box) * e.price_sale
    						} else {
    							total += e.qty * e.price_sale
    						}
    				})
    				return total
    			},
    			totalProducts: function() {
    				if (this.products.length == 0) return 0
    				
    				var total = 0
    				this.products.filter(function(e) {
    					return e.stock_type.indexOf(vue.type) > -1
    				}).forEach(function(e, i) {
    					total+= e.qty
    				})
    				return total
    			}
    		},
    		methods: {
    			removeMerma: function() {
					var aid = this.almacen.id_almacen
					swal({
    					title: "Esto removera todos los productos que no se puedan vender",
    					text: "¿Desea continuar?",
    					type: "info",
    					showCancelButton: true,
    					closeOnConfirm: false,
    					showLoaderOnConfirm: true,
    					confirmButtonText: "Si, Continuar",
    					cancelButtonText: "No cancelar"
    				},function(){
    					FinancialServiceBean
    						.removeProductsNotSale(aid, function(res) {
    							if (res) {
        							swal({
        								title: "Exito",
        								text: "Removidos con exito",
        								type: 'success',
        		    					closeOnConfirm: false,
        		    					confirmButtonText: "Aceptar",
        							}, function() {
        								location.reload()
        							})
        						} else {
        							swal({
        								title: "Error",
        								text: "No fueron removidos, intente más tarde",
        								type: "error",
        		    					closeOnConfirm: false,
        		    					confirmButtonText: "Aceptar",
        							}, function() {
        								location.reload()
        							})
        						}
    						})
    				})
				},
    			showType: function(type) {
    				this.type = type
    			}
    		}
    	})
    </script>


</body>

</html>