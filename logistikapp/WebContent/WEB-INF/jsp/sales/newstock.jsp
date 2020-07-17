<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
	<title>Almacén - LogistikApp</title>
	
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
                                    <li class="active"><span>${ update == 'N' ? 'Nuevo' : 'Actualizar' } almacén</span></li>
                                </ol>
                                
                                <h1>${ update == 'N' ? 'Nuevo' : 'Actualizar' } almacén</h1>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">
                                    <header class="main-box-header clearfix">
                                        <h2 class="pull-left">${ update == 'N' ? 'Creación' : 'Modificación' } de almacén</h2>
                                    </header>
                                    
                                    <div class="main-box-body clearfix" id="main">
                                        <div class="row">
                                        	<div class="col-xs-12">
                                        		<form id="form" class="form-horizontal" @submit.prevent="submit">
                                        		
                                        			<div class="form-group">
														<label for="code" class="col-sm-2 control-label">Código:</label>
														<div class="col-sm-10">
															<input type="text" name="code" id="code" class="form-control" v-model="code" value="" required="required" placeholder="Ingresa el código del almacen" autofocus>
														</div>
													</div>
													
													<div class="form-group" v-if="!isFirstAlmacen">
														<label for="id_user" class="col-sm-2 control-label">Responsable:</label>
														<div class="col-sm-10">
															<select name="id_user" v-model="id_user" id="id_user" class="form-control" required="required">
																<option value="0"> - N/A - </option>
																<option :value="i.id_user" v-for="i in users" v-text="i.username">nombre</option>
															</select>
														</div>
													</div>
													
													<div class="form-group" v-if="!isFirstAlmacen">
														<label for="id_retail" class="col-sm-2 control-label">Plaza:</label>
														<div class="col-sm-10">
															<select name="id_retail" v-model="id_retail" id="id_retail" class="form-control" required="required">
																<option value="0"> - N/A - </option>
																<option :value="i.id_retail" v-for="i in retails" v-text="i.name">nombre</option>
															</select>
														</div>
													</div>
													
													<!-- <div class="form-group">
														<label for="id_config_stock" class="col-sm-2 control-label">Plantilla:</label>
														<div class="col-sm-10">
															<select name="id_config_stock" id="id_config_stock" class="form-control" required="required" v-model="id_configuration" @change="changeConfig">
																<option value="0"> - N/A - </option>
																<option :value="i.id_configuration_stock" v-for="i in configurations" v-text="i.name"></option>
															</select>
														</div>
													</div> -->
													
													<div class="row">
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
														<table class="table table-hover" id="tb-products">
															<thead>
																<tr>
																	<th>Imagen</th>
																	<th>Producto</th>
																	<!-- <th>Min</th>
																	<th>Máx</th> -->
																	<th>Cantidad</th>
																	<th>Tipo</th>
																	<th>Piezas X Caja</th>
																</tr>
															</thead>
															<tbody>
																<tr v-for="i in getListProducts">
																	<td>
																		<img :src="i.image" alt="" width="60px" />
																	</td>
																	<td width="35%">
																		<span>{{ i.name_short }}</span>
																		<br />
																		<small>{{ i.name_long | truncate(20) }}</small>
																	</td>
																	<!-- 
																	<td width="200px">
																		<input type="number" name="" id="ninput" class="form-control" v-model="i.min" :min="i.min" :max="i.max < 0 ? i.min : i.max" step="1" placeholder="Cantidad minima de este producto">
																	</td>
																	<td width="200px">
																		<input type="number" name="" id="ninput2" class="form-control" v-model="i.max" :min="i.min < 0 ? 0 : i.min" max="" step="1" placeholder="Cantidad maxima de este producto">
																	</td>
																	 -->
																	<td width="200px">
																		<input type="number" name="" id="ninput3" class="form-control" v-model="i.qty" :min="i.min < 0 ? 0 : i.min" step="1" required="required" placeholder="Cantidad actual de este producto">
																	</td>
																	<td width="200px" class="text-center">
																		<template v-if="i.tmp_type == 'PKG'">
																			<select v-model="i.type" id="input" class="form-control" required="required">
																				<option value="PCS">Pieza</option>
																				<option value="PKG">Caja</option>
																			</select>
																		</template>
																		<span v-else class="label label-info">Pieza</span>
																	</td>
																	<td class="text-center">
																		<span class="label label-warning" v-text="i.piece_in_box || 'NA'"></span>
																	</td>
																</tr>
															</tbody>
														</table>
													</div>
													
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
	    
	<script>
		function initTable(id){
			$('#' + id).dataTable( {
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
		
		//initTable("tb-products")
	
	</script>
	    
    <script>
    	
    	var vue = new Vue({
    		el: "#main",
    		data: {
    			code: "",
    			id_user: 0,
    			users: [],
    			id_retail: 0,
    			retails: [],
    			products: [],
    			update: ${update == 'S'},
    			isFirstAlmacen: ${isFirstAlmacen},
    			id_almacen: ${id_almacen},
    			almacen: {},
    			search: "",
    			//id_configuration: 0,
    			//configurations: []
    		},
    		mounted: function() {
    			
    			/*FinancialServiceBean
					.getConfigurationsStockByIdSupplier("${id_supplier}", function(data) {
						vue.configurations = data
					})*/
    			
    			if (!this.isFirstAlmacen) {
	    			UserServiceBean
	    				.getUserByCriteria({id_supplier: "${id_supplier}", profile: 'DRI'}, function(data) {
	    					vue.users = data
	    				})
	    				
	    			RetailServiceBean
	    				.getRetailByCriteria({id_supplier: "${id_supplier}", active: 'S'}, function(data) {
	    					vue.retails = data
	    				})
    			}
    				
    			if (this.update) {
    				FinancialServiceBean
	    				.getAlmacenAndProductsByIdAlmacen(this.id_almacen, "${id_supplier}", function(data) {
	    					vue.almacen = data.almacen
	    					vue.products = data.products
	    					.filter(function(e) {
	    						return e.stock_type == "VTA"
	    					})
	    					.map(function(e) {
	    						if (e.type == 'PKG') {
	    							e.tmp_qty = e.qty
	    							e.tmp_type = e.type
	    							if (e.piece_in_box) {
	    								e.qty = parseFloat((e.qty / e.piece_in_box).toFixed(1))
	    							} 
	    						}
	    						return e
	    					})
	    					vue.code = vue.almacen.code
	    					vue.id_user = vue.almacen.id_user
	    					vue.id_retail = vue.almacen.id_retail
	    				})
    			} else {
    				FinancialServiceBean
	    				.selectProductByCriteria({id_supplier: "${id_supplier}", active: 'S'}, function(data) {
	    					vue.products = vue.mapProducts(data)
	    				})
    			}
    		},
    		methods: {
    			mapProducts: function(products) {
    				return products.map(function(e) {
    					e.min = 0
    					e.max = 1
    					e.qty = 0
    					//e.type = !!e.piece_in_box ? e.type : 'PCS'
    					return e
    				})
    			},
    			submit: function(e) {
    				
    				if (vue.code.trim().length == 0) {
    					swal('Alerta', 'Por favor ingresa el código del almacén', 'warning')
    					return
    				}
    				if (!this.isFirstAlmacen) {
	    				if (vue.id_user == 0) {
	    					swal('Alerta', 'Por favor selecciona al responsable', 'warning')
	    					return
	    				}
	    				
	    				if (vue.id_retail == 0) {
	    					swal('Alerta', 'Por favor selecciona la plaza a la que pertenecerá el almacén', 'warning')
	    					return
	    				}
    				}
    				
    				if (!this.update){
	    				var msg = this.isFirstAlmacen ? "Este almacen se guardara como almacén general," : ""
	    				msg += "¿Deseas continuar?"
	    				swal({
	    					title: "Crear almacen",
	    					text: msg,
	    					type: "info",
	    					showCancelButton: true,
	    					closeOnConfirm: false,
	    					showLoaderOnConfirm: true,
	    					confirmButtonText: "Si, Continuar",
	    					cancelButtonText: "No cancelar"
	    				},function(){
	    					vue.sendData()
	    				});
    				} else {
    					swal({
	    					title: "Actualizar almacen",
	    					text: "¿Deseas continuar?",
	    					type: "info",
	    					showCancelButton: true,
	    					closeOnConfirm: false,
	    					showLoaderOnConfirm: true,
	    					confirmButtonText: "Si, Continuar",
	    					cancelButtonText: "No cancelar"
	    				},function(){
	    					vue.sendData()
	    				});
    				}
    			},
    			sendData: function() {
    				if (this.update) {
    					console.log("Updating")
    					this.almacen.code = this.code
    					var data = {
    							almacen: this.almacen
    					}
    					
    					data.products = this.products.map(function(e) {
    						var qty = 0
    						if (e.type == 'PKG') {
    							qty = e.qty * e.piece_in_box
    						} else {
    							qty = e.qty
    						}
    						return {
								id_product: e.id_product,
								qty,
								min: e.min,
								max: e.max,
								type: "VTA"
							}
    					})
    					FinancialServiceBean.updateAlmacenFull(data, function(res) {
    						if (res) {
    							swal({
    								title: "Exito",
    								text: "Almacen actualizado con exito",
    								type: 'success',
    		    					closeOnConfirm: false,
    		    					confirmButtonText: "Aceptar",
    							}, function() {
    								location.reload()
    							})
    						} else {
    							swal({
    								title: "Error",
    								text: "Almacen no fue actualizado, intente más tarde o intente otro nombre",
    								type: "error",
    		    					closeOnConfirm: false,
    		    					confirmButtonText: "Aceptar",
    							}, function() {
    								location.reload()
    							})
    						}
    					})
    					
    				} else {
    					
    					var data = {
    							almacen: {
    								code: this.code,
    								id_supplier: ${id_supplier}
    							}
    					}
    					
    					data.products = this.products.map(function(e) {
    						var qty = 0
    						if (e.type == 'PCS') {
    							qty = e.qty
    						} else {
    							if (!!e.piece_in_box) {
    								qty =  e.qty * e.piece_in_box
    							}
    						}
    						return {
    								id_product: e.id_product,
    								qty,
    								min: e.min,
    								max: e.max,
    								type: "VTA"
    						}
    					})
    					
    					FinancialServiceBean.addAlmacenFull(data, function(res) {
    						if (res) {
    							swal({
    								title: "Exito",
    								text: "Almacen creado con exito",
    								type: 'success',
    		    					closeOnConfirm: false,
    		    					confirmButtonText: "Aceptar",
    							}, function() {
    								location.href = "stock.htm"
    							})
    						} else {
    							swal({
    								title: "Error",
    								text: "Almacen no fue creado, intente más tarde o intente otro nombre",
    								type: "error",
    		    					closeOnConfirm: false,
    		    					confirmButtonText: "Aceptar",
    							}, function() {
    								location.href = "stock.htm"
    							})
    						}
    					})
    				}
    			}
    		},
    		computed: {
    			qtyProducts : function() {
    				
    				if (this.products.length == 0) return 0
    				
    				var total = 0
    				this.products.forEach(function(e, i) {
    					if (e.type == 'PCS') {
    						if (!!e.piece_in_box) {
    							var tmp = e.qty - Math.trunc(e.qty / e.piece_in_box) * e.piece_in_box
    							total += tmp
    						} else {
    							total += parseInt(e.qty)
    						}
    					}
    				})
    				return total
    			},
    			qtyPkg: function() {
    				if (this.products.length == 0) return 0
    				
    				var total = 0
    				this.products.forEach(function(e, i) {
    					if (e.type == 'PKG') {
    						total += parseInt(e.qty)
    					} else if (!!e.piece_in_box) {
    						var tmp = Math.trunc(e.qty / e.piece_in_box)
    						total += tmp
    					}
    				})
    				return total
    			},
    			totalCost: function() {
    				if (this.products.length == 0) return 0.0
    				
    				var total = 0.0
    				this.products.forEach(function(e, i) {
    					if (e.type == 'PCS') {
    						if (!!e.piece_in_box) {
    							var tmp = Math.trunc(e.qty / e.piece_in_box) 
    							total += tmp * e.price_cost_box
    							total += (e.qty - Math.trunc(e.qty / e.piece_in_box) * e.piece_in_box) * e.price_cost
    						} else {
    							total += e.qty * e.price_cost
    						}
    					}
    					else total += parseInt(e.qty) * e.price_cost_box
    				})
    				return total
    			},
    			totalSale: function() {
    				if (this.products.length == 0) return 0.0
    				
    				var total = 0.0
    				this.products.forEach(function(e, i) {
    					if (e.type == 'PCS') {
    						if (!!e.piece_in_box) {
    							var tmp = Math.trunc(e.qty / e.piece_in_box) 
    							total += tmp * e.price_sale_box
    							total += (e.qty - Math.trunc(e.qty / e.piece_in_box) * e.piece_in_box) * e.price_sale
    						} else {
    							total += e.qty * e.price_sale
    						}
    					}
    					else total += parseInt(e.qty) * e.price_sale_box
    				})
    				return total
    			},
    			getListProducts: function() {
    				return this.products.filter(function(e) {
    					return e.name_short.toLowerCase().indexOf(vue.search.toLowerCase()) > -1 || e.name_long.toLowerCase().indexOf(vue.search.toLowerCase()) > -1
    				}).map(function(e) {
    					
    					if (e.tmp_type && e.tmp_type != e.type) {
    						if (e.type == 'PCS') {
    							e.qty = e.tmp_qty
    						} else {
    							e.qty = parseFloat((e.tmp_qty / e.piece_in_box).toFixed(1))
    						}
    					} else {
    						if (e.type == 'PKG') {
    							e.qty = parseFloat((e.tmp_qty / e.piece_in_box).toFixed(1))
    						}
    					}
    					
    					return e
    				})
    			}
    		},
    		filters: {
    		  	truncate: function(string, value) {
    		    	return string.substring(0, value) + '...';
    		    },
    		    money: function(value) {
    		    	return "$" + value
    		    }
    		}
    	})
    	
    </script>

</body>

</html>