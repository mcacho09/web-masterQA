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

                <div class="row">
                    <div class="col-lg-12">
                        
                        <div class="row">
                            <div class="col-lg-12 hidden-sm hidden-xs">
                                <ol class="breadcrumb">
                                    <li><a href="home.htm">Inicio</a></li>
                                    <li class="active"><span>Almacen</span></li>
                                </ol>
                                
                                <h1>Información de almacenes</h1>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">
                                    
										<div role="tabs-wrapper tabs-no-header">
											<!-- Nav tabs -->
											<ul class="nav nav-tabs" role="tablist">
												<li role="presentation" class="active">
													<a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">
														<i class="fa fa-archive"></i> Almacén general
													</a>
												</li>
												<li role="presentation">
													<a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab"><i class="fa fa-truck"></i> Carga Abordo</a>
												</li>
											</ul>

											<!-- Tab panes -->
											<div class="tab-content tab-content-body clearfix">
												<div role="tabpanel" class="tab-pane fade active in" id="tab1">
													<br />
																										
													<div class="row">
														<div class="col-xs-12">
															<h2 class="text-info pull-left" v-if="!!almacen">{{ almacen.code }}</h2>
															<div class="btn-group pull-right">
																<a href="newstock.htm" class="btn btn-primary" v-if="!almacen"><span>Nuevo</span> <i class="fa fa-plus"></i></a>
																<a href="updstock.htm" class="btn btn-primary" v-if="!!almacen"><span>Editar</span> <i class="fa fa-edit"></i></a>
																<a href="admtemplatestock.htm" class="btn btn-primary" v-if="!!almacen"><span>Carga Inicial</span> <i class="fa fa-cog"></i></a>
																<a class="btn btn-primary" href="#" v-if="false"><span v-if="!!almacen">Reporte</span> <i class="fa fa-download"></i></a> 
																<button type="button" class="btn btn-warning" v-if="!!almacen" @click="showModalSurtir"><i class="fa fa-plus"></i> Surtir</button>
																<!-- <button type="button" class="btn btn-danger" v-if="!!almacen"><span class="hidden-sm hidden-x">Eliminar</span> <i class="fa fa-trash"></i></button> -->
															</div>
														</div>
													</div>
													
													<br v-if="!almacen"/>
													
													<div class="alert alert-info" v-if="!almacen">
														<strong>Ooops!</strong> No tienes creado tu almacén principal ...
													</div>
													
													<br v-if="!!almacen" />
													
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
																				<span v-text="'Piezas: ' + qtyProducts"></span>
																				<span v-text="'Cajas: ' + qtyPkg"></span>
																				<span v-text="'Total: ' + totalProducts"></span>
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
	
													<div class="row" v-else>
														<div class="form-group">
															<label for="input" class="col-sm-2 control-label">Busqueda:</label>
															<div class="col-sm-10">
																<input type="search" class="form-control" v-model="search">
															</div>
														</div>
														<br />
														<div class="col-xs-12">
															<!-- <h4>Listado de productos</h4> -->
															<div class="table-responsive">
																<table class="table table-hover table-striped" id="tb-products-general">
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
																			<th>Total Pzs</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr v-for="i in getProducts">
																			<td>
																				<img :src="i.image" alt="" width="60px" />
																			</td>
																			<td width="40%">
																				<span>{{ i.name_short }}</span>
																				<br />
																				<small>{{ i.name_long | truncate(25) }}</small>
																				<br />
																				<small :class="{'text-primary': (i.type == 'PKG'), 'text-info': (i.type != 'PKG')}" v-text="i.type == 'PKG' ? 'Caja' : 'Pieza'"></small>
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
												<div role="tabpanel" class="tab-pane fade" id="tab2">
													<br />
													
													<div class="row">
														<div class="col-xs-12 text-right">
															<button class="btn btn-info" @click="showCreateSubAlmacenModal" v-if="hasAlmacen && drivers > 0"><span>Nuevo</span> <i class="fa fa-plus"></i></button>
														</div>
													</div>
													
													<div class="row">
														<br />
														<div class="form-group">
															<label for="input" class="col-sm-2 control-label">Busqueda:</label>
															<div class="col-sm-10">
																<input type="search" class="form-control" v-model="search">
															</div>
														</div>
														<br />
														<div class="col-xs-12">
															<h2 class="text-info">Listado</h2>
															<div class="table-responsive">
																<table class="table table-hover table-striped" id="tb-sub-stocks">
																	<thead>
																		<tr>
																			<th>Vehículo</th>
																			<th>Responsable</th>
																			<th>Plaza</th>
																			<th># Piezas</th>
																			<th># Cajas</th>
																			<th>Total Piezas</th>
																			<th>Acciones</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr v-for="i in getSubalmacens" :class="{'no-products' : i.total_pieces == 0 }">
																			<td v-text="i.code">ALM-VTA-12</td>
																			<td v-text="i.username">Vendedor 1</td>
																			<td v-text="i.retail">Plaza 1</td>
																			<td>
																				<span class="badge badge-primary" v-text="i.qty_pieces">154</span>
																			</td>
																			<td>
																				<span class="badge badge-info" v-text="i.qty_package">154</span>
																			</td>
																			<td>
																				<span class="badge badge-success" v-text="i.total_pieces">154</span>
																			</td>
																			<td>
																				<div class="btn-group">
																					<a :href="'userstock.htm?aid=' + i.id_almacen" class="btn btn-xs btn-primary"><span class="hidden-xs hidden-sm">Ver</span> <i class="fa fa-eye"></i></a>
																					<button type="button" @click="edit(i)" class="btn btn-xs btn-primary" data-toggle="modal"><span class="hidden-xs hidden-sm">Editar</span> <i class="fa fa-edit"></i></button>
																					<button type="button" @click="showModal(i.id_almacen)" class="btn btn-xs btn-success btn-liquidate"><span class="hidden-xs hidden-sm">Re surtir</span> <i class="fa fa-plus"></i></button>
																					<button type="button" @click="liquidate(i.id_almacen)" class="btn btn-xs btn-danger btn-liquidate"><span class="hidden-xs hidden-sm">Devolución</span> <i class="fa fa-send"></i></button>
																					<button type="button" @click="remove(i.id_almacen)" class="btn btn-xs btn-danger"><i class="fa fa-trash"></i></button>
																				</div>
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
	
	<div class="modal fade" id="modal-id">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
			
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Nueva carga abordo</h4>
				</div>
				
				<div class="modal-body">
					<div class="row form-group">
						<label for="code" class="col-sm-2 control-label">Vehículo:</label>
						<div class="col-sm-6">
							<input type="text" name="code" id="code" placeholder="Ingresa el Vehículo" class="form-control" v-model="code" />
						</div>
					</div>

					<div class="row form-group">
						<label for="id_user" class="col-sm-2 control-label">Responsable:</label>
						<div class="col-sm-6">
							<select name="id_user" id="id_user" class="form-control"
								required="required" v-model="id_user">
								<option disabled value="">Seleccionar responsable</option>
								<option :value="i.id_user" v-for="i in users" v-text="i.username">Usuario 1</option>
							</select>
						</div>
					</div>
					
					<div class="row form-group">
						<label for="id_retail" class="col-sm-2 control-label">Plaza:</label>
						<div class="col-sm-6">
							<select name="id_retail" id="id_retail" class="form-control"
								required="required" v-model="id_retail">
								<option disabled value="">Seleccionar plaza</option>
								<option :value="i.id_retail" v-for="i in retails" v-text="i.name">Plaza 1</option>
							</select>
						</div>
					</div>
					
					<div class="row form-group">
						<label for="id_config_stock" class="col-sm-2 control-label">Carga inicial:</label>
						<div class="col-sm-6">
							<select name="id_config_stock" id="id_config_stock" class="form-control" required="required" v-model="id_config" @change="changeConfig">
								<option value="0">Seleccionar carga inicial</option>
								<option :value="i.id_configuration_stock" v-for="i in configs" v-text="i.name">Configuración 1</option>
							</select>
						</div>
					</div>

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
											<td><span v-text="qtyProducts + ' Piezas'"></span> <span
												v-text="qtyPkg + ' Cajas'"></span></td>
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
							<input type="search" class="form-control" v-model="search">
						</div>
					</div>
					<br />

					<div class="table-responsive" v-if="products.length > 0">
						<table class="table table-hover tb-products">
							<thead>
								<tr>
									<th>Imagen</th>
									<th>Producto</th>
									<!-- <th>Min</th>
									<th>Máx</th> -->
									<th>Cantidad</th>
									<th>Tipo</th>
									<th>Piezas</th>
									<th>Disponibles</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="i in getListProducts">
									<td><img :src="i.image" alt="" width="60px" /></td>
									<td width="40%"><span>{{ i.name_short }}</span> <br /> <small>{{
											i.name_long | truncate(20) }}</small></td>
									<!-- 
									<td width="200px"><input type="number" name="" id="ninput"
										class="form-control" v-model="i.min" :min="i.min"
										:max="i.max < 0 ? i.min : i.max" step="1"
										placeholder="Cantidad minima de este producto"></td>
									<td width="200px"><input type="number" name=""
										id="ninput2" class="form-control" v-model="i.max"
										:min="i.min < 0 ? 0 : i.min" :max="i.qty_stock_general" step="1"
										placeholder="Cantidad maxima de este producto"></td>
									-->
									<td width="200px"><input type="number" name=""
										id="ninput3" class="form-control" v-model="i.qty"
										:min="i.min < 0 ? 0 : i.min" :max="i.max" step="1"
										required="required"
										placeholder="Cantidad actual de este producto"></td>
									<td width="200px" class="text-center"><template
											v-if="!!i.piece_in_box"> <select v-model="i.type"
											id="input" class="form-control" required="required">
											<option value="PCS">Pieza</option>
											<option value="PKG">Caja</option>
										</select> </template> <span v-else class="label label-info">Pieza</span></td>
									<td class="text-center"><span class="label label-warning"
										v-text="i.piece_in_box || 'NA'"></span></td>
									<td class="text-center"><span class="label label-primary"
										v-text="i.qty_stock_general"></span></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
					<button type="button" class="btn btn-primary" @click="submit">Enviar</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="modal-edit">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Editar inventario</h4>
				</div>
				<div class="modal-body">
					<h2 class="text-primary" v-text="almacen.code" style="padding-left: 0;"></h2>

					<div class="row form-group">
						<label for="id_user" class="col-sm-2 control-label">Responsable:</label>
						<div class="col-sm-6">
							<select name="id_user" id="id_user" class="form-control"
								required="required" v-model="almacen.id_user">
								<option disabled value="">Seleccionar responsable</option>
								<option :value="i.id_user" v-for="i in users"
									v-text="i.username">Usuario 1</option>
							</select>
						</div>
					</div>

					<div class="row form-group">
						<label for="id_retail" class="col-sm-2 control-label">Plaza:</label>
						<div class="col-sm-6">
							<select name="id_retail" id="id_retail" class="form-control"
								required="required" v-model="almacen.id_retail">
								<option disabled value="">Seleccionar plaza</option>
								<option :value="i.id_retail" v-for="i in retails"
									v-text="i.name">Plaza 1</option>
							</select>
						</div>
					</div>

					<div class="row form-group">
						<label for="id_config_stock" class="col-sm-2 control-label">Carga inicial:</label>
						<div class="col-sm-6">
							<select name="id_config_stock" id="id_config_stock"
								class="form-control" required="required" v-model="id_config"
								@change="changeConfig">
								<option value="0">Seleccionar carga inicial</option>
								<option :value="i.id_configuration_stock" v-for="i in configs"
									v-text="i.name">Configuración 1</option>
							</select>
						</div>
					</div>

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
											<td><span v-text="qtyProducts + ' Piezas'"></span> <span
												v-text="qtyPkg + ' Cajas'"></span></td>
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
							<input type="search" class="form-control" v-model="search">
						</div>
					</div>
					<br />
					<div class="table-responsive" v-if="products.length > 0">
						<table class="table table-hover tb-products">
							<thead>
								<tr>
									<th>Imagen</th>
									<th>Producto</th>
									<!-- 
									<th>Min</th>
									<th>Máx</th>
									-->
									<th>Cantidad</th>
									<th>Tipo</th>
									<th>Piezas</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="i in getListProducts">
									<td><img :src="i.image" alt="" width="60px" /></td>
									<td width="30%"><span>{{ i.name_short }}</span> <br /> <small>{{
											i.name_long | truncate(20) }}</small></td>
									<!-- 
									<td width="200px"><input type="number" name="" id="ninput"
										class="form-control" v-model="i.min" :min="i.min"
										:max="i.max < 0 ? i.min : i.max" step="1"
										placeholder="Cantidad minima de este producto"></td>
									<td width="200px"><input type="number" name="" id="ninput2"
										class="form-control" v-model="i.max"
										:min="i.min < 0 ? 0 : i.min" max="" step="1"
										placeholder="Cantidad maxima de este producto"></td>
									-->
									<td width="200px"><input type="number" name=""
										id="ninput3" class="form-control" v-model="i.qty"
										:min="i.min < 0 ? 0 : i.min" :max="i.max" step="1"
										required="required"
										placeholder="Cantidad actual de este producto"></td>
									<td width="200px" class="text-center">
										<template v-if="!!i.piece_in_box"> 
											<select v-model="i.type"
												id="input" class="form-control" required="required">
												<option value="PCS">Pieza</option>
												<option value="PKG">Caja</option>
											</select> 
										</template> 
										<span v-else class="label label-info">Pieza</span></td>
									<td class="text-center">
										<span class="label label-warning" v-text="i.piece_in_box || 'NA'"></span>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
					<button type="button" class="btn btn-primary" @click="submit">Enviar</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="modal-surtir">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Surtir inventario</h4>
				</div>
				<div class="modal-body">

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
					
					<template v-if="isSubAlmacen">
					<div class="row form-group pull-left">
						<label for="id_config_stock" class="col-sm-4 control-label">Carga inicial:</label>
						<div class="col-sm-8">
							<select name="id_config_stock" id="id_config_stock" class="form-control" required="required" v-model="id_config" @change="changeConfig">
								<option value="0">Seleccionar carga inicial</option>
								<option :value="i.id_configuration_stock" v-for="i in configs" v-text="i.name">Configuración 1</option>
							</select>
						</div>
					</div>
					
					<button class="btn btn-warning pull-right" @click="addMissing"><i class="fa fa-refresh"></i> Surtir faltantes</button>
					<br />
					</template>
					
					
					<h3 v-if="products.length > 0">Listado de </h3>

					<div class="row form-group">
						<label for="input" class="col-sm-2 control-label">Busqueda:</label>
						<div class="col-sm-10">
							<input type="search" class="form-control" v-model="search">
						</div>
					</div>
					
					<div class="table-responsive" v-if="products.length > 0">
						<table class="table table-hover tb-products">
							<thead>
								<tr>
									<th>Imagen</th>
									<th>Producto</th>
									<th>Tipo</th>
									<th>Cantidad</th>
									<th v-if="isSubAlmacen">Disponibles</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="i in getListProducts">
									<td><img :src="i.image" alt="" width="60px" /></td>
									<td width="40%"><span>{{ i.name_short }}</span> <br /> <small>{{
											i.name_long | truncate(30) }}</small></td>
									<td width="200px">
										<template v-if="i.tmp_type == 'PKG'">
											<select v-model="i.type" id="input" class="form-control" required="required">
												<option value="PCS">Pieza</option>
												<option value="PKG">Caja</option>
											</select>
										</template>
										<span v-else class="label label-info">Pieza</span>
									</td>
									<td><input type="number" name="" id="ninput3"
										class="form-control" v-model="i.qty"
										:min="i.min < 0 ? 0 : i.min" :max="i.qty_stock_general" step="1"
										required="required"
										placeholder="Cantidad actual de este producto"></td>
										
									<td class="text-center" v-if="isSubAlmacen"><span class="label label-primary"
										v-text="i.qty_stock_general"></span></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
					<button type="button" class="btn btn-primary" @click="submit">Enviar</button>
				</div>
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
			el: '#tab1',
			data: {
				products: [],
				almacen: null,
				search: ''
			},
			watch: {
    			getProducts: function() {
    				return this.products.filter(function(e) {
    					return (e.name_short.toLowerCase().indexOf(vue.search.toLowerCase()) > -1 || e.name_long.toLowerCase().indexOf(vue.search.toLowerCase()) > -1) && e.stock_type == 'VTA'
    				})
    			}
			},
			mounted: function(){
				FinancialServiceBean.getAlmacenAndProductsByIdSupplier("${ id_supplier }", function(data) {
					vue.almacen = data.almacen
					vue.products = data.products
				})
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
    				if (this.products.length == 0) return 0.0
    				
    				var total = 0.0
    				this.products.forEach(function(e, i) {
    					
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
    				this.products.forEach(function(e, i) {
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
    			getProducts: function() {
    				return this.products;
    			}

    		},
    		methods: {
    			showModalSurtir: function() {
    				vueSurtir.init(this.almacen.id_almacen, false)
    			}
    		}
		})
	
	</script>
	
	<script>
		var vue2 = new Vue({
			el: '#modal-id',
			data: {
				code: "",
				id_user: 0,
				users: [],
				id_retail: 0,
				retails: [],
				products: [],
				id_config: 0,
				configs: [],
				search: ""
			},
			methods: {
				init: function() {
					
					UserServiceBean
						.getUserWithOutStockByIdSupplier("${ id_supplier }", function(res) {
							vue2.users = res;
							if (res && res.length > 0) vue2.id_user = res[0].id_user;
						});
						
					RetailServiceBean
						.getRetailByCriteria({ id_supplier: "${ id_supplier }" }, function(res) {
							vue2.retails = res;
							if (res && res.length > 0) vue2.id_retail = res[0].id_retail;
						});
						
					FinancialServiceBean
						.getConfigurationsStockByIdSupplier("${ id_supplier }", function(res) {
							vue2.configs = res;
						});
					
					FinancialServiceBean
						.getProductsToCreateSubStock({id_supplier: "${ id_supplier }", active: 'S'}, function(res) {
							vue2.products = res
						});
				},
				changeConfig: function() {
					console.log('Change Config');
					this.getProducts();
				},
				getProducts: function() {
					if (this.id_config == 0) {
 						this.init();
					}
					FinancialServiceBean
						.getConfigurationStockProductByIdCondigurationStock({
							id_supplier: "${id_supplier}",
							id_configuration_stock: vue2.id_config
						}, function(res) {
							vue2.products = res.map(function(e){
								e.type = !e.type ? 'PCS' : e.type
								return e
							});
							console.log('Getting products', res);
						})
				},
				submit: function() {
					
					if (this.code.trim().length == 0) {
						swal('Alerta', 'Ingrese el nombre', 'warning');
						return;
					}
					
					if (this.id_user == 0) {
						swal('Alerta', 'Seleccione al responsable', 'warning');
						return;
					}
					
					if (this.id_retail == 0) {
						swal('Alerta', 'Seleccione la plaza a la que pertenece', 'warning');
						return;
					}
					
					/*if (this.qtyProducts == 0) {
						swal('Alerta', 'Debe contener minimo un producto', 'warning');
						return;
					}*/
					
					this.sendData();
				},
				sendData: function() {
					swal({
    					title: "Crear",
    					text: "¿Desea continuar?",
    					type: "info",
    					showCancelButton: true,
    					closeOnConfirm: false,
    					showLoaderOnConfirm: true,
    					confirmButtonText: "Si, Continuar",
    					cancelButtonText: "No cancelar"
    				},function(){
    					var data = {
    							almacen: {
    								code: vue2.code,
    								id_user: vue2.id_user,
    								id_retail: vue2.id_retail,
    								id_supplier: ${ id_supplier }
    							},
    							products : vue2.products.map(function(e) {
    								var qty = 0
    	    						if (e.type == 'PCS') {
    	    							qty = e.qty
    	    							console.log('PCS ', qty)
    	    						} else {
    	    							if (!!e.piece_in_box) {
    	    								qty =  e.qty * e.piece_in_box
    	    								console.log("BOX", qty)
    	    							} else {
    	    								console.log("NADA")
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
    					};
    					
    					console.log('Enviando nuevo sub almacén', data)
    					
    					FinancialServiceBean.addSubAlmacenFull(data, function(res) {
    						if (res) {
    							swal({
    								title: "Exito",
    								text: "Carga abordo creado con exito",
    								type: 'success',
    		    					closeOnConfirm: false,
    		    					confirmButtonText: "Aceptar",
    							}, function() {
    								location.reload();
    							});
    						} else {
    							swal({
    								title: "Error",
    								text: "Carga abordo no fue creado, intente más tarde o intente otro nombre",
    								type: "error",
    		    					closeOnConfirm: false,
    		    					confirmButtonText: "Aceptar",
    							}, function() {
    								location.reload();
    							});
    						}
    					});
    				});
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
    					return e.name_short.toLowerCase().indexOf(vue2.search.toLowerCase()) > -1 || e.name_long.toLowerCase().indexOf(vue2.search.toLowerCase()) > -1
    				})
    			}
			}
		})
		
	</script>
	
	<script>

		var vue3 = new Vue({
			el: "#tab2",
			data: {
				subalmacens: [],
				drivers: 0,
				search: ""
			},
			mounted: function() {
				FinancialServiceBean
					.getSubAlmacenInfo("${ id_supplier }", function(res) {
						vue3.subalmacens = res
					})
					
				UserServiceBean
					.getUserWithOutStockByIdSupplier("${ id_supplier }", function(res) {
						vue3.drivers = res.length
					})
			},
			methods: {
				showCreateSubAlmacenModal: function() {
					vue2.init()
					$('#modal-id').modal('show')
				},
				edit: function(i) {
					vueModalEdit.getInfo(i.id_almacen)
				},
				showModal: function(id_almacen) {
					console.log("IDALMACE", id_almacen)
					vueSurtir.init(id_almacen, true)
				},
				liquidate: function(id_almacen) {
					var aid = id_almacen
					swal({
    					title: "Esto regresará todos los productos al almacén general",
    					text: "¿Desea continuar?",
    					type: "info",
    					showCancelButton: true,
    					closeOnConfirm: false,
    					showLoaderOnConfirm: true,
    					confirmButtonText: "Si, Continuar",
    					cancelButtonText: "No cancelar"
    				},function(){
    					FinancialServiceBean
    						.liquidateSubAlmacen(aid, function(res) {
    							if (res) {
        							swal({
        								title: "Exito",
        								text: "Liquidado con exito",
        								type: 'success',
        		    					closeOnConfirm: false,
        		    					confirmButtonText: "Aceptar",
        							}, function() {
        								location.reload()
        							})
        						} else {
        							swal({
        								title: "Error",
        								text: "No fue loquidado, intente más tarde",
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
				remove: function(id_almacen) {
					var aid = id_almacen
					swal({
    					title: "Esto regresará todos los productos al almacén general y lo eliminaran",
    					text: "¿Desea continuar?",
    					type: "info",
    					showCancelButton: true,
    					closeOnConfirm: false,
    					showLoaderOnConfirm: true,
    					confirmButtonText: "Si, Continuar",
    					cancelButtonText: "No cancelar"
    				},function(){
    					FinancialServiceBean
    						.removeAlmacen(aid, function(res) {
    							if (res) {
        							swal({
        								title: "Exito",
        								text: "Removido con exito",
        								type: 'success',
        		    					closeOnConfirm: false,
        		    					confirmButtonText: "Aceptar",
        							}, function() {
        								location.reload()
        							})
        						} else {
        							swal({
        								title: "Error",
        								text: "No fue removido, intente más tarde",
        								type: "error",
        		    					closeOnConfirm: false,
        		    					confirmButtonText: "Aceptar",
        							}, function() {
        								location.reload()
        							})
        						}
    						})
    				})
				}
			},
			computed: {
				hasAlmacen: function() {
					return !!vue.almacen
				},
				getSubalmacens: function() {
					return this.subalmacens.filter(function(e) {
						return e.code.toLowerCase().indexOf(vue3.search.toLowerCase()) > -1 || e.username.toLowerCase().indexOf(vue3.search.toLowerCase()) > -1
					})
				}
			}
		})
		
	</script>
	
	<script>
		
		var vueModalEdit = new Vue({
			el: '#modal-edit',
			data: {
				almacen: {},
				products: [],
				id_config: 0,
				configs: [],
				users: [],
				retails: [],
				search: ""
			},
			mounted: function() {
				UserServiceBean
					.getUserByCriteria({id_supplier: "${id_supplier}", profile: 'DRI'},  function(res) {
						vueModalEdit.users = res
						if (res && res.length > 0) vueModalEdit.id_user = res[0].id_user
					})
					
				RetailServiceBean
					.getRetailByCriteria({ id_supplier: "${ id_supplier }" }, function(res) {
						vueModalEdit.retails = res
						if (res && res.length > 0) vueModalEdit.id_retail = res[0].id_retail
					})
				
				FinancialServiceBean
					.getConfigurationsStockByIdSupplier("${id_supplier}", function(res) {
						vueModalEdit.configs = res
					})
			},
			methods: {
				getInfo : function(id_almacen) {
					FinancialServiceBean
						.getAlmacenAndProductsByIdAlmacen(id_almacen, "${id_supplier}", function(res) {
							console.log("Res", res)
							vueModalEdit.almacen = res.almacen
							vueModalEdit.products = res.products.map(function(e){
								if (e.type == 'PKG') {
	    							e.tmp_qty = e.qty
	    							e.tmp_type = e.type
	    							if (e.piece_in_box) {
	    								e.qty = parseFloat((e.qty / e.piece_in_box).toFixed(1))
	    							}
	    						}
								return e
							}).filter(function(e) {
								return e.stock_type == 'VTA'
							})
							$('#modal-edit').modal('show')
						})
				},
				changeConfig: function() {
					console.log('Change Config')
					this.getProducts()
				},
				getProducts: function() {
					FinancialServiceBean
						.getConfigurationStockProductByIdCondigurationStock({id_configuration_stock: vueModalEdit.id_config, id_supplier: "${id_supplier}"}, function(res) {
							vue2.products = res
							console.log('Getting products', res)
						})
				},
				submit: function() {
					console.log("Submit ME")
					if (this.almacen.code.trim().length == 0) {
						swal('Alerta', 'Ingrese el nombre', 'warning')
						return
					}
					
					if (this.almacen.id_user == 0) {
						swal('Alerta', 'Seleccione al responsable', 'warning')
						return
					}
					
					if (this.almacen.id_retail == 0) {
						swal('Alerta', 'Seleccione la plaza a la que pertenece', 'warning')
						return
					}
					
					/*if (this.qtyProducts == 0) {
						swal('Alerta', 'Debe contener minimo un producto', 'warning')
						return
					}*/
					
					swal({
    					title: "Editar",
    					text: "¿Desea continuar?",
    					type: "info",
    					showCancelButton: true,
    					closeOnConfirm: false,
    					showLoaderOnConfirm: true,
    					confirmButtonText: "Si, Continuar",
    					cancelButtonText: "No cancelar"
    				},function(){
    					var data = {
    							almacen: vueModalEdit.almacen,
    							products : vueModalEdit.products.map(function(e) {
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
    					}
    					
    					FinancialServiceBean.updateSubAlmacenFull(data, function(res) {
    						if (res) {
    							swal({
    								title: "Exito",
    								text: "Editado con exito",
    								type: 'success',
    		    					closeOnConfirm: false,
    		    					confirmButtonText: "Aceptar",
    							}, function() {
    								location.reload()
    							})
    						} else {
    							swal({
    								title: "Error",
    								text: "No fue editado, intente más tarde o intente otro nombre",
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
					console.log('Change Config')
					this.getProducts()
				},
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
    					return e.name_short.toLowerCase().indexOf(vueModalEdit.search.toLowerCase()) > -1 || e.name_long.toLowerCase().indexOf(vueModalEdit.search.toLowerCase()) > -1
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
    		}
		})
	</script>
	
	<script>
		var vueSurtir = new Vue({
			el: "#modal-surtir",
			data: {
				id_almacen: 0,
				products: [],
				isSubAlmacen: false,
				configs: [],
				id_config: 0,
				search: ""
			},
			methods: {
				init: function(id_almacen, isSubAlmacen) {
					vueSurtir.id_almacen = id_almacen
					vueSurtir.isSubAlmacen = isSubAlmacen
					
					FinancialServiceBean
						.getAlmacenAndProductsByIdSupplier("${id_supplier}", function(res) {
							console.log("VueSurtirRES", res)
							vueSurtir.products = res.products
							.filter(function(e) {
								return e.stock_type == 'VTA'
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
							
							$('#modal-surtir').modal('show')
						})
						
					FinancialServiceBean
						.getConfigurationsStockByIdSupplier("${id_supplier}", function(res) {
							vueSurtir.configs = res
						})
				},
				submit: function() {
					swal({
    					title: "Surtir",
    					text: "¿Desea continuar?",
    					type: "info",
    					showCancelButton: true,
    					closeOnConfirm: false,
    					showLoaderOnConfirm: true,
    					confirmButtonText: "Si, Continuar",
    					cancelButtonText: "No cancelar"
    				},function(){
    					
    					var products = vueSurtir.products.map(function(e) {
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
        				
    					if (vueSurtir.isSubAlmacen) {
    						console.log("isSubAlmacen")
    						console.log("ENVIANDO", "ID SUPPLIER:", "${id_supplier}", "IDALMACEN: ", vueSurtir.id_almacen, "PRODUCTS: ", products)
    						FinancialServiceBean
    							.addProductsToSubAlmacen("${ id_supplier }", vueSurtir.id_almacen, products, function(res) {
    								vueSurtir.showResult(res)
    							})
    					} else {
    						console.log("isAlmacen")
	    					FinancialServiceBean
	    						.addProductsToAlmacen(vueSurtir.id_almacen, products, function(res) {
	    							vueSurtir.showResult(res)
	    						})
    					}
    				})
				},
				showResult: function(res) {
					if (res) {
						swal({
							title: "Exito",
							text: "Productos agregados",
							type: 'success',
	    					closeOnConfirm: false,
	    					confirmButtonText: "Aceptar",
						}, function() {
							location.reload()
						})
					} else {
						swal({
							title: "Error",
							text: "Productos no agregados, intente más tarde",
							type: "error",
	    					closeOnConfirm: false,
	    					confirmButtonText: "Aceptar",
						}, function() {
							location.reload()
						})
					}
				},
				addMissing: function() {
					FinancialServiceBean
						.getSaledProductsByIdAlmacen({id_almacen: vueSurtir.id_almacen, id_supplier: "${id_supplier}"}, function(res) {
							vueSurtir.products = res
						})
				},
				changeConfig: function() {
					if (vueSurtir.id_config == 0) return 
					console.log('Change Config')
					this.getProducts()
				},
				getProducts: function() {
					FinancialServiceBean
						.getConfigurationStockProductByIdCondigurationStock({id_configuration_stock: vueSurtir.id_config, id_supplier: "${id_supplier}"}, function(res) {
							vueSurtir.products = res
							console.log('Getting products', res)
						})
				}
			},
			computed: {
				getListProducts: function() {
    				return this.products.filter(function(e) {
    					return e.name_short.toLowerCase().indexOf(vueSurtir.search.toLowerCase()) > -1 || e.name_long.toLowerCase().indexOf(vueSurtir.search.toLowerCase()) > -1
    				}).map(function(e) {
    					
    					
    					e.qty = 0;
    					return e;
    				})
    			}
			}
		})
	</script>
	
</body>

</html>