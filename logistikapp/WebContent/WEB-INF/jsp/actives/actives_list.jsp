<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
	<title>Activos - LogistikApp</title>
	
	<!-- bootstrap -->
	<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
	
	<!-- libraries -->
	<link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/select2.css"/>
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">
	
	<link rel="stylesheet" type="text/css" href="css/logistikapp.css">
    
    <!-- this page specific styles -->

	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
	
	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
	<link href="https://fonts.googleapis.com/css?family=Libre+Barcode+39+Extended+Text" rel="stylesheet">
	
	<style>
		.barcode {
			font-family: 'Libre Barcode 39 Extended Text', cursive;
			font-size: 1.5em!important;
		}
	</style>
	
	<!--[if lt IE 9]>
			<script src="js/html5shiv.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->
</head>

<body class="fixed-header fixed-leftmenu theme-blue" >

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
									<li><a href="customer.htm">Clientes</a></li>
									<li class="active">Activos</li>
								</ol>

								<h1 class="pull-left">Activos</h1>
							</div>
						</div>

						<div class="row">
							<div class="col-lg-12">
								<div class="main-box clearfix">
									<div role="tabs-wrapper tabs-no-header">
										<!-- Nav tabs -->
										<ul class="nav nav-tabs" role="tablist">
											<li role="presentation" class="active"><a href="#tab1"
												aria-controls="tab1" role="tab" data-toggle="tab"> <i
													class="fa fa-cubes"></i> Listado de activos
											</a></li>
											<li role="presentation"><a href="#tab2"
												aria-controls="tab2" role="tab" data-toggle="tab"><i
													class="fa fa-plus"></i> Nuevo activo</a></li>
										</ul>

										<!-- Tab panes -->
										<div class="tab-content tab-content-body clearfix" >
											<div role="tabpanel" class="tab-pane fade active in" id="tab1">
												<div class="row">
													<div class="col-xs-12">
														<h2 class="text-info">Listado</h2>
														<div class="table-responsive">
															<table class="table table-hover table-striped">
																<thead>
																	<tr>
																		<th>Imagen</th>
																		<th>Código</th>
																		<th>Barcode</th>
																		<th>Registrado</th>
																		<th>Activo</th>
																		<th>Acciones</th>
																	</tr>
																</thead>
																<tbody>
																	<tr v-for="i in actives">
																		<td>
																			<img :src="i.image" v-if="i.image" alt="" width="40px" />
																			<span class="badge badge-warning" v-else>NA</span>
																		</td>
																		<td v-text="i.code"></td>
																		<td class="barcode">
																			{{i.code}}
																		</td>
																		<td>
																			{{i.created | diffForHumans}}
																		</td>
																		<td>
																			<a @click.prevent="toggleActive(i)" class="badge" :class="{'badge-success': i.active == 'S', 'badge-danger': i.active != 'S'}">
																				<i class="fa" :class="{'fa-check': i.active == 'S', 'fa-times': i.active != 'S'}"></i> {{i.active == 'S' ? 'Si':'No'}}
																			</a>
																		</td>
																		<td>
																			<div class="btn-group">
																				<a :href="'active_visited_list.htm?ids=${id_store}&ida=' + i.idActives" class="btn btn-success btn-sm"><i class="fa fa-list"></i></a>
																				<button class="btn btn-primary btn-sm" @click="showEdit(i)">
																					<i class="fa fa-edit"></i>
																				</button>
																				<button class="btn btn-danger btn-sm" @click="remove(i)">
																					<i class="fa fa-trash"></i>
																				</button>
																				<button class="btn btn-info btn-sm" @click="showTransfer(i)">
																					<i class="fa fa-exchange"></i>
																				</button>
																			</div>
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
													<form class="col-md-12 form-horizontal" role="form" @submit.prevent="save">
														<div class="row text-center">
															<img :src="active.image || 'img/products/default.png'" alt="" style="heigth: auto; max-width: 45%;" />
														</div>
														<br />
														<div class="form-group">
															<label for="code" class="col-sm-2 control-label">Código:</label>
															<div class="col-sm-10">
																<input type="text" v-model="active.code" name="" id="code" class="form-control" value="" required="required" placeholder="Ingresa el código del activo">
															</div>
														</div>
														<div class="form-group">
															<label for="image" class="col-sm-2 control-label">Imagen:</label>
															<div class="col-sm-10">
																<input type="file" accept="image/*" name="" id="image" class="form-control">
															</div>
														</div>
														<div class="btn-group text-center">
															<button type="submit" class="btn btn-primary">Guardar</button>
															<button type="button" @click="downloadBarCode(active.code, '#img-barcode', false)" id="barcodeDownload" class="btn btn-success" v-if="!!active.code">Generar <i class="fa fa-barcode"></i></button>
															<!--  <a :href="urlImage" :download="downloadImage" class="btn btn-info" id="linkImage" v-if="!!urlImage"><i class="fa fa-download"></i></a> -->
														</div>
													</form>
												</div>
												<div class="row">
													<div class="col-xs-12">
														<img id="img-barcode" class="img-responsive" style="border:1px solid lightblue; margin-top: 20px;">
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
		
		<div class="modal fade" id="modal-edit-active">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Editar activo</h4>
					</div>
					<div class="modal-body">
						<form class="col-md-12 form-horizontal" role="form" @submit.prevent="edit">
							<div class="row text-center">
								<img :src="editActive.image || 'img/products/default.png'" alt="" style="heigth: auto; max-width: 45%;" />
							</div>
							<br />
							<div class="form-group">
								<label for="code" class="col-sm-2 control-label">Código:</label>
								<div class="col-sm-10">
									<input type="text" v-model="editActive.code" name="" id="code" class="form-control" value="" required="required" placeholder="Ingresa el código del activo">
								</div>
							</div>
							<div class="form-group">
								<label for="imageEdit" class="col-sm-2 control-label">Imagen:</label>
								<div class="col-sm-10">
									<input type="file" accept="image/*" name="" id="image-edit" class="form-control" value="" >
								</div>
							</div>
							<div class="btn-group text-center">
								<button type="submit" class="btn btn-primary">Guardar</button>
								<button type="button" @click="downloadBarCode(editActive.code, '#img-barcode-edit', true)" id="barcodeDownload" class="btn btn-success" v-if="!!editActive.code"> Generar <i class="fa fa-barcode"></i>
								</button>
								<a :href="urlImage" :download="downloadImage" class="btn btn-info" id="linkImage" v-if="!!urlImage"><i class="fa fa-download"></i></a>
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
								<canvas id="barcode-edit" :width="canvasW" height="150px" v-show="false"></canvas>
							</div>
						</form>
						<div class="row">
							<div class="col-xs-12">
								<img id="img-barcode-edit" class="img-responsive" style="border:1px solid lightblue; margin-top: 20px;">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
		<div class="modal fade" id="modal-transfer">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Transferir activo</h4>
					</div>
					<div class="modal-body">
						<h2>Selecciona la tienda a la que se transferira el activo</h2>

						<div class="form-group form-group-select2">
							<select style="width: 100%" id="sel21" v-model="id_store_selected">
								<option value="0" disabled="disabled">- Cliente -</option>
								<option :value="i.id_store" v-for="i in stores" v-text="'Código: '+ i.code + ', ' + i.name"></option>
							</select>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
						<button type="button" class="btn btn-primary" @click="transfer">Transferir</button>
					</div>
				</div>
			</div>
		</div>
		
	</div>

	
	<!-- global scripts -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.nanoscroller.min.js"></script>

    <!-- this page specific scripts -->
    <script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>		
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="js/moment-with-locales.min.js"></script>
	<script src="js/select2.min.js"></script>
	<script src="js/JsBarcode.all.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/pace.min.js"></script>

    <!-- this page specific inline scripts -->
    <script>
    	
    	var app = new Vue({
    		el: '#page-wrapper',
    		data: {
    			actives: [],
    			stores: [],
    			active: {
    				idActives: 0,
    				created: null,
    				modified: null,
    				login: "${useracegi.userlogin}",
    				active: 'S',
    				code: null,
    				image: null,
    				id_store: ${id_store},
    				id_supplier: ${supplier.id_supplier}
    			},
    			urlImage: null,
    			canvasW: 'auto',
    			downloadImage: null,
    			editActive: {
    				idActives: 0,
    				created: null,
    				modified: null,
    				login: "${useracegi.userlogin}",
    				active: 'S',
    				code: null,
    				image: null,
    				id_store: ${id_store},
    				id_supplier: ${supplier.id_supplier}
    			},
    			id_active_transfer: 0,
    			id_store_selected: 0
    		},
    		mounted : function() {
    			moment.locale('es')
    			RetailServiceBean.getActives(${supplier.id_supplier}, ${id_store}, function(res) {
    				app.actives = res
    			})
    			
    			RetailServiceBean.getStoreByIdSupplier(${supplier.id_supplier}, function(res) {
    				app.stores = res.filter(function(e) {
    					return e.id_store != ${id_store}
    				})
    			})

    		},
    		methods: {
    			downloadBarCode: function(code, id, edit) {
    				if (!code) return
    				console.log(code, id)
    				JsBarcode(id, code)
    			},
    			save : function() {
    				swal({
    					  title: "Guardar activo",
    					  text: "¿Deseas guardar el activo?",
    					  type: "info",
    					  showCancelButton: true,
    					  closeOnConfirm: false,
    					  showLoaderOnConfirm: true,
    					  cancelButtonText: 'Cancelar'
    					},
    					function(){
    						RetailServiceBean.addActive(app.active, function(res) {
    	    					if (!!res) {
    	    						swal('Exito', 'Activo Agregado', 'success')
    	    						app.active.created = new Date()
    	    						app.active.idActives = res
    	    						app.actives.push(Object.assign({}, app.active))
    	    						
    	    						app.active.idActives = 0
    	    						app.active.code = null
    	    						app.active.image = null
    	    						app.urlImage = null
    	    						app.canvasW = 'auto'
    	    						app.downloadImage = null
    	    					} else {
    	    						swal('Alerta', 'Activo no agregado, intente con otro código', 'warning')
    	    					}
    	    				})
    					});
    			},
    			remove: function(item) {
    				swal({
    					title: "Alerta",
    					text: "¿Está seguro que desea borrar el activo?",   
    					type: "warning",   
    					showCancelButton: true,   
    					confirmButtonColor: "#DD6B55",   
    					confirmButtonText: "Aceptar",
    					cancelButtonText: 'Cancelar',
    					closeOnConfirm: false,
    					showLoaderOnConfirm: true,
    				}, function() {
    					console.log(item)
    					RetailServiceBean.deleteActive(item.idActives, function(res) {
    						console.log("RES", res )
    						if (!!res) {
    							app.actives = app.actives.filter(function(e) {
    								return e.idActives != item.idActives
    							})
    							swal('Exito', "Activo eliminado con exito", "success")
    						} else {
    							swal("Error", "Activo no eliminado, intente más tarde", "error")
    						}
    					})
    				})
    			},
    			toggleActive: function(item) {
    				swal({
    					title: "Alerta",
    					text: "¿Está seguro que desea cambiar el estado del activo?",   
    					type: "warning",   
    					showCancelButton: true,   
    					confirmButtonColor: "#DD6B55",   
    					confirmButtonText: "Aceptar",
    					cancelButtonText: 'Cancelar',
    					closeOnConfirm: false,
    					showLoaderOnConfirm: true,
    				}, function() {
    					console.log(item)
    					item.active = item.active == 'S' ? 'N' : 'S'
    					item.login = "${useracegi.userlogin}"
    					RetailServiceBean.updateActive(item, function(res) {
    						console.log("RES", res )
    						if (!!res) {
    							swal('Exito', "Activo eliminado con exito", "success")
    						} else {
    							item.active = item.active == 'S' ? 'N' : 'S'
    							swal("Error", "Activo no eliminado, intente más tarde", "error")
    						}
    					})
    				})
    			}, 
    			showEdit: function(item) {
    				Object.keys(item).forEach(function(key) {
    					app.editActive[key] = item[key]
    					console.log(key)
    				})
    				$('#modal-edit-active').modal('show')
    			},
    			edit: function() {
    				if (!Object.keys(app.editActive).length) return
    				swal({
    					title: "Alerta",
    					text: "¿Está seguro que desea actualizar el activo?",   
    					type: "warning",   
    					showCancelButton: true,   
    					confirmButtonColor: "#DD6B55",   
    					confirmButtonText: "Aceptar",
    					cancelButtonText: 'Cancelar',
    					closeOnConfirm: false,
    					showLoaderOnConfirm: true,
    				}, function() {
    					app.editActive.login = "${useracegi.userlogin}"
    					app.editActive.modified = new Date()
	    				RetailServiceBean.updateActive(app.editActive, function(res) {
	    					if (!!res) {
	    						swal({
	    							title: 'Exito',
	    							text: "Activo eliminado con exito",
	    							type: "success"
	    						}, function() {
	    							location.reload()
	    						})
    						} else {
    							swal("Error", "Activo no eliminado, intente más tarde", "error")
    						}
	    				})
    				})
    			},
    			showTransfer: function(item) {
    				app.id_active_transfer = item.idActives
    				app.id_store_selected = 0
    				$('#modal-transfer').modal('show')
    			},
    			transfer: function() {
    				if (!app.id_active_transfer || !app.id_store_selected) return
    				swal({
    					title: "Alerta",
    					text: "¿Está seguro que desea transferir el activo?",   
    					type: "warning",   
    					showCancelButton: true,   
    					confirmButtonColor: "#DD6B55",   
    					confirmButtonText: "Aceptar",
    					cancelButtonText: 'Cancelar',
    					closeOnConfirm: false,
    					showLoaderOnConfirm: true,
    				}, function() {
    					app.editActive.login = "${useracegi.userlogin}"
    					app.editActive.modified = new Date()
    					RetailServiceBean.transferActive(app.id_active_transfer, app.id_store_selected, function(res) {
	    					if (!!res) {
	    						swal({
	    							title: 'Exito',
	    							text: "Activo transferido con exito",
	    							type: "success"
	    						}, function() {
	    							location.reload()
	    						})
    						} else {
    							swal("Error", "Activo no transferido, intente más tarde", "error")
    						}
	    				})
    				})
    			}
    		},
    		filters: {
    			diffForHumans: function(date) {
    				return moment(date).fromNow()
    			}
    		}
    	})
    	
    	$('#sel21').select2();
    	$('#sel21').on('change', function(){
        	app.id_store_selected = parseInt(this.value)
        })
    	
    	document.querySelector('#image').addEventListener('change', evt => {
    		if (!evt.target.files.length) return
    		getBase64(evt.target.files[0]).then(function(result) {
    			app.active.image = result
    		})
    	})
    	
    	document.querySelector('#image-edit').addEventListener('change', evt => {
    		if (!evt.target.files.length) return
    		getBase64(evt.target.files[0]).then(function(result) {
    			app.editActive.image = result
    		})
    	})
    	
    	function getBase64(file) {
    		return new Promise(function(resolve, reject) {
    			var reader = new FileReader();
    			reader.readAsDataURL(file);
    			reader.onload = function () {
    				resolve(reader.result);
    			};
    			reader.onerror = function (error) {
    				reject(error)
    			};
    		})
    	}
    	
    	$('#modal-edit-active').on('hide.bs.modal', function () {
    		Object.keys(app.editActive).forEach(function(key) {
    			app.editActive[key] = null
    		})
    		app.urlImage = null
    		app.canvasW = 'auto'
    		app.downloadImage = null
	  		document.querySelector('#img-barcode-edit').src = ""
    	})
    </script>
</body>

</html>