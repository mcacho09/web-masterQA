<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
	<title>Carga inicial - LogistikApp</title>
	
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
                            <div class="col-lg-12 hidden-sm hidden-xs">
                                <ol class="breadcrumb">
                                    <li><a href="home.htm">Inicio</a></li>
                                    <li><a href="stock.htm">Almacén</a></li>
                                    <li class="active"><span>Carga Inicial</span></li>
                                </ol>
                                
                                <h1 class="pull-left">Carga Inicial</h1>
                            </div>
                        </div>
                        
                        <div class="row" id="app">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">
                                    <div role="tabs-wrapper tabs-no-header">
										<!-- Nav tabs -->
										<ul class="nav nav-tabs" role="tablist">
											<li role="presentation" class="active">
												<a href="#home" aria-controls="home" role="tab" data-toggle="tab">Listado</a>
											</li>
											<li role="presentation">
												<a href="#tab" aria-controls="tab" role="tab" data-toggle="tab">Nuevo</a>
											</li>
										</ul>
									
										<!-- Tab panes -->
										<div class="tab-content tab-content-body clearfix">
											<div role="tabpanel" class="tab-pane fade active in" id="home">
												<br/>
												<div class="row" v-if="configurations.length > 0">
													<div class="col-xs-12">

														<div class="form-group">
															<label for="configs">Cargas inciales:</label>
															<select id="configs" id="input" class="form-control" required="required" v-model="id_configuration" @change="changeConfig">
																<option :value="i.id_configuration_stock" v-for="i in configurations" v-text="i.name">Configuración 1</option>
															</select>
														</div>
														
													</div>
												</div>
												
												<div class="row" v-if="id_configuration">
													<div class="col-xs-12 text-right">
														<button @click="removeConfig" class="btn btn-danger">Eliminar <i class="fa fa-times"></i></button>
													</div>
												</div>
												
												<br v-if="id_configuration" />

												<div class="row" v-if="productsConfig.length > 0">
													<div class="col-xs-12">
														<div class="table-responsive">
															<table class="table table-hover">
																<thead>
																	<tr class="bg-success">
																		<th class="text-center">Total Productos</th>
																		<c:if test="${!fn:contains(useracegi.profile, 'DRI') }">
																		<th class="text-center">Total $ Costo</th>
																		</c:if>
																		<th class="text-center">Total $ Ventas</th>
																	</tr>
																</thead>
																<tbody>
																	<tr class="text-center text-primary">
																		<td>
																			<span v-text="qtyProductsConfig + ' Piezas'"></span>
																			<span v-text="qtyPkgConfig + ' Paquetes'"></span>
																		</td>
																		<td>{{ totalCostConfig | money }}</td>
																		<td>{{ totalSaleConfig | money }}</td>
																	</tr>
																</tbody>
															</table>
														</div>
													</div>
												</div>
												
												<hr v-if="productsConfig.length > 0"/>
												
												<div class="row" v-if="productsConfig.length == 0">
														<div class="col-xs-12">
															<div class="sk-fading-circle">
														        <div class="sk-circle1 sk-circle"></div>
														        <div class="sk-circle2 sk-circle"></div>
														        <div class="sk-circle3 sk-circle"></div>
														        <div class="sk-circle4 sk-circle"></div>
														        <div class="sk-circle5 sk-circle"></div>
														        <div class="sk-circle6 sk-circle"></div>
														        <div class="sk-circle7 sk-circle"></div>
														        <div class="sk-circle8 sk-circle"></div>
														        <div class="sk-circle9 sk-circle"></div>
														        <div class="sk-circle10 sk-circle"></div>
														        <div class="sk-circle11 sk-circle"></div>
														        <div class="sk-circle12 sk-circle"></div>
														      </div>
														</div>
													</div>
												
												<div v-else>
													<h3>Listado de productos</h3>
													<div class="row form-group">
														<label for="input" class="col-sm-2 control-label">Busqueda:</label>
														<div class="col-sm-10">
															<input type="search" class="form-control"
																v-model="searchConfig">
														</div>
													</div>
													<br />
													<div class="row">
														<div class="col-xs-12">
															<div class="table-responsive">
																<table class="table table-hover tb-products">
																	<thead>
																		<tr>
																			<th>Imagen</th>
																			<th>Producto</th>
																			<th>Min</th>
																			<th>Máx</th>
																			<th>Cantidad</th>
																			<th>Tipo</th>
																			<th>Piezas</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr v-for="i in getListProductsConfig">
																			<td>
																				<img :src="i.image" alt="" width="60px" />
																			</td>
																			<td width="40%">
																				<span>{{ i.name_short }}</span>
																				<br />
																				<small>{{ i.name_long | truncate(20) }}</small>
																			</td>
																			<td>
																				<span class="label label-primary" v-text="i.min"></span>
																			</td>
																			<td>
																				<span class="label label-success" v-text="i.max"></span>
																			</td>
																			<td>
																				<span class="label label-info" v-text="i.qty"></span>
																			</td>
																			<td>
																				<span class="label" :class="{'label-primary': (i.type == 'PKG'), 'label-info': (i.type != 'PKG')}" v-text="i.type == 'PKG' ? 'Caja' : 'Pieza'"></span>
																			</td>
																			<td>
																				<span class="label label-warning" v-text="i.piece_in_box || 'NA'"></span>
																			</td>
																		</tr>
																	</tbody>
																</table>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane fade" id="tab">
												<form id="form" class="form-horizontal" @submit.prevent="submit">
												<div class="row">
													<br />
													<div class="col-xs-12">
														<h2 class="text-info">Nueva carga inicial</h2>
														<br />
														<div class="form-group">
															<label for="input" class="col-sm-2 control-label">Nombre:</label>
															<div class="col-sm-10">
																<input type="text" name="" v-model="name" id="input" class="form-control" placeholder="Ingresa el nombre de la carga inicial" required="required">
															</div>
														</div>
													</div>
												</div>
												<br />
												<div class="row" v-if="products.length > 0">
													<div class="col-xs-12">
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
																		<td>
																			<span v-text="qtyProducts + ' Piezas'"></span>
																			<span v-text="qtyPkg + ' Paquetes'"></span>
																		</td>
																		<td>{{ totalCost | money }}</td>
																		<td>{{ totalSale | money }}</td>
																	</tr>
																</tbody>
															</table>
														</div>
													</div>
												</div>

												<hr v-if="products.length > 0"/>
												<div class="row" v-if="products.length == 0">
														<div class="col-xs-12">
															<div class="sk-fading-circle">
														        <div class="sk-circle1 sk-circle"></div>
														        <div class="sk-circle2 sk-circle"></div>
														        <div class="sk-circle3 sk-circle"></div>
														        <div class="sk-circle4 sk-circle"></div>
														        <div class="sk-circle5 sk-circle"></div>
														        <div class="sk-circle6 sk-circle"></div>
														        <div class="sk-circle7 sk-circle"></div>
														        <div class="sk-circle8 sk-circle"></div>
														        <div class="sk-circle9 sk-circle"></div>
														        <div class="sk-circle10 sk-circle"></div>
														        <div class="sk-circle11 sk-circle"></div>
														        <div class="sk-circle12 sk-circle"></div>
														      </div>
														</div>
													</div>
													<h3 v-if="products.length > 0">Listado de productos</h3>
													<div class="row form-group">
														<label for="input" class="col-sm-2 control-label">Busqueda:</label>
														<div class="col-sm-10">
															<input type="search" class="form-control"
																v-model="search">
														</div>
													</div>
													<br />
													<div class="table-responsive" v-if="products.length > 0">
														<table class="table table-hover tb-products">
															<thead>
																<tr>
																	<th>Imagen</th>
																	<th>Producto</th>
																	<th>Min</th>
																	<th>Máx</th>
																	<th>Cantidad</th>
																	<th>Tipo</th>
																	<th>Piezas</th>
																</tr>
															</thead>
															<tbody>
																<tr v-for="i in getListProducts">
																	<td>
																		<img :src="i.image" alt="" width="60px" />
																	</td>
																	<td width="40%">
																		<span>{{ i.name_short }}</span>
																		<br />
																		<small>{{ i.name_long | truncate(20) }}</small>
																	</td>
																	<td>
																		<input type="number" name="" id="ninput" class="form-control" v-model="i.min" :min="i.min" :max="i.max < 0 ? i.min : i.max" step="1" placeholder="Cantidad minima de este producto">
																	</td>
																	<td>
																		<input type="number" name="" id="ninput2" class="form-control" v-model="i.max" :min="i.min < 0 ? 0 : i.min" max="" step="1" placeholder="Cantidad maxima de este producto">
																	</td>
																	<td>
																		<input type="number" name="" id="ninput3" class="form-control" v-model="i.qty" :min="i.min < 0 ? 0 : i.min" :max="i.max" step="1" required="required" placeholder="Cantidad actual de este producto">
																	</td>
																	<td>
																		<span class="label" :class="{'label-primary': (i.type == 'PKG'), 'label-info': (i.type != 'PKG')}" v-text="i.type == 'PKG' ? 'Caja' : 'Pieza'"></span>
																	</td>
																	<td>
																		<span class="label label-warning" v-text="i.piece_in_box || 'NA'"></span>
																	</td>
																</tr>
															</tbody>
														</table>
													</div>
													<br />
													<div class="row">
														<a href="stock.htm" class="btn btn-default btn-lg col-xs-6">
															 Cancelar <i class="fa fa-times"></i>
														</a>
														<button type="submit" class="btn btn-primary btn-lg col-xs-6">
															Guardar <i class="fa fa-save"></i>
														</button>
													</div>
												</form>
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

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/pace.min.js"></script>

    <!-- this page specific inline scripts -->
    <!-- Dwr script -->
    <script type='text/javascript' src='dwr/interface/FinancialServiceBean.js'></script>		
    <script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>		
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	
	
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
		
		/*initTable("#tb-products-general")
		initTable("#tb-sub-stocks")
		initTable(".tb-products")*/
		
	</script>
	
	<script>
	
		Vue.filter('truncate', function(string, value) {
	    	return string.substring(0, value) + '...';
		})
		   
		Vue.filter('money', function(value) {
			return "$" + value
		})
	
		var vue = new Vue({
			el: '#app',
			data: {
				name: "",
				products: [],
				configurations: [],
				id_configuration: 0,
				productsConfig: [],
				search: "",
				searchConfig: "",
			},
			mounted: function() {
				
				FinancialServiceBean
					.getConfigurationsStockByIdSupplier("${id_supplier}", function(data) {
						vue.configurations = data
						if (data.length > 0) {
							vue.id_configuration = data[0].id_configuration_stock;
							vue.changeConfig()
						}
					})
				
				FinancialServiceBean
					.selectProductByCriteria({id_supplier: "${id_supplier}", active: 'S'}, function(data) {
						vue.products = vue.mapProducts(data)
					})
					
				
			},
			methods: {
				removeConfig: function() {
					if (vue.id_configuration) {
						swal({
	    					title: "Crear eliminarlo",
	    					text: '¿Desea continuar?',
	    					type: "info",
	    					showCancelButton: true,
	    					closeOnConfirm: false,
	    					showLoaderOnConfirm: true,
	    					confirmButtonText: "Si, Continuar",
	    					cancelButtonText: "No cancelar"
	    				},function(){
		    				FinancialServiceBean.deleteFullConfigurationStock(vue.id_configuration, function(res) {
		    					if (res) {
	    							swal({
	    								title: "Exito",
	    								text: "Configuración eliminada con exito",
	    								type: 'success',
	    		    					closeOnConfirm: false,
	    		    					confirmButtonText: "Aceptar",
	    							}, function() {
	    								location.reload()
	    							})
	    						} else {
	    							swal({
	    								title: "Error",
	    								text: "Configuración no fue eliminada con exito, intente más tarde o intente otro nombre",
	    								type: "error",
	    		    					closeOnConfirm: false,
	    		    					confirmButtonText: "Aceptar",
	    							}, function() {
	    								location.reload()
	    							})
	    						}
		    				})
	    				});
					}
				},
				mapProducts: function(products) {
    				return products.map(function(e) {
    					e.min = 0
    					e.max = 1
    					e.qty = 0
    					return e
    				})
    			},
				submit: function() {
					if (vue.name.trim().length == 0) {
    					swal('Alerta', 'Por favor ingresa el nombre de la configuración', 'warning')
    					return
    				}
    				
    				if (this.totalProducts == 0) {
    					swal('Alerta', 'Para crear un configuración debe tener minimo un producto disponible', 'warning')
    					return
    				}
    				
    				var data = {
    						configuratin: {
    							name : this.name,
    							id_supplier: "${id_supplier}"
    						}
    				}
    				
    				var tmp = this.products.map(function(e) {
    					return {
    						id_product: e.id_product,
    						qty: !e.qty ? 0 : e.qty,
    						min: !e.min ? 0 : e.min,
    						max: !e.max ? 0 : e.max
    					}
    				})
    				
    				data.products = tmp
    				swal({
    					title: "Crear almacen",
    					text: '¿Desea continuar?',
    					type: "info",
    					showCancelButton: true,
    					closeOnConfirm: false,
    					showLoaderOnConfirm: true,
    					confirmButtonText: "Si, Continuar",
    					cancelButtonText: "No cancelar"
    				},function(){
	    				FinancialServiceBean.addFullConfigurationStockProduct(data, function(res) {
	    					if (res) {
    							swal({
    								title: "Exito",
    								text: "Configuración creada con exito",
    								type: 'success',
    		    					closeOnConfirm: false,
    		    					confirmButtonText: "Aceptar",
    							}, function() {
    								location.reload()
    							})
    						} else {
    							swal({
    								title: "Error",
    								text: "Configuración no fue creada con exito, intente más tarde o intente otro nombre",
    								type: "error",
    		    					closeOnConfirm: false,
    		    					confirmButtonText: "Aceptar",
    							}, function() {
    								location.reload()
    							})
    						}
	    				})
    				});
    				
				},
				changeConfig: function() {
					FinancialServiceBean.getConfigurationStockProductByIdCondigurationStock({
						id_configuration_stock: vue.id_configuration,
						id_supplier: "${id_supplier}"
					}, function(res) {
						vue.productsConfig = res
					})
				}
			},
			computed: {
				qtyProducts : function() {
    				
    				if (this.products.length == 0) return 0
    				
    				var total = 0
    				this.products.forEach(function(e, i) {
    					
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
    				this.products.forEach(function(e, i) {
    					if (!!e.piece_in_box) {
    						var tmp = Math.trunc(e.qty / e.piece_in_box)
    						total += tmp
    					}
    				})
    				return total
    			},
    			totalProducts: function() {
    				if (this.products.length == 0) return 0
    				
    				var total = 0
    				this.products.forEach(function(e, i) {
    					total+= e.qty
    				})
    				return total
    			},
    			totalCost: function() {
    				if (!this.products) return 0.0
    				
    				var total = 0.0
    				this.products.forEach(function(e, i) {
    					if (e.type == 'PCS') total += parseInt(e.qty) * e.price_cost
    					else total += parseInt(e.qty) * e.price_cost_box
    				})
    				return total
    			},
    			totalSale: function() {
    				if (!this.products) return 0.0
    				
    				var total = 0.0
    				this.products.forEach(function(e, i) {
    					if (e.type == 'PCS') total += parseInt(e.qty) * e.price_sale
    					else total += parseInt(e.qty) * e.price_sale_box
    				})
    				return total
    			},
				qtyProductsConfig : function() {
    				
    				if (!this.productsConfig) return 0
    				
    				var total = 0
    				this.productsConfig.forEach(function(e, i) {
    					if (e.type == 'PCS') total += parseInt(e.qty)
    				})
    				return total
    			},
    			qtyPkgConfig: function() {
    				if (!this.products) return 0
    				
    				var total = 0
    				this.productsConfig.forEach(function(e, i) {
    					if (e.type !== 'PCS') total += parseInt(e.qty)
    				})
    				return total
    			},
    			totalCostConfig: function() {
    				if (!this.productsConfig) return 0.0
    				
    				var total = 0.0
    				this.productsConfig.forEach(function(e, i) {
    					if (e.type == 'PCS') total += parseInt(e.qty) * e.price_cost
    					else total += parseInt(e.qty) * e.price_cost * e.piece_in_box
    				})
    				return total
    			},
    			totalSaleConfig: function() {
    				if (!this.productsConfig) return 0.0
    				
    				var total = 0.0
    				this.productsConfig.forEach(function(e, i) {
    					if (e.type == 'PCS') total += parseInt(e.qty) * e.price_sale
    					else total += parseInt(e.qty) * e.price_cost_box
    				})
    				return total
    			},
    			getListProducts: function() {
    				return this.products.filter(function(e) {
    					return e.name_short.toLowerCase().indexOf(vue.search.toLowerCase()) > -1 || e.name_long.toLowerCase().indexOf(vue.search.toLowerCase()) > -1
    				})
    			},
    			getListProductsConfig: function() {
    				return this.productsConfig.filter(function(e) {
    					return e.name_short.toLowerCase().indexOf(vue.searchConfig.toLowerCase()) > -1 || e.name_long.toLowerCase().indexOf(vue.searchConfig.toLowerCase()) > -1
    				})
    			}
    		}
		})
	</script>

</body>

</html>