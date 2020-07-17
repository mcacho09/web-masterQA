<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
	<title>No visitados - LogistikApp</title>
	
	<!-- bootstrap -->
	<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
	
	<!-- libraries -->
	<link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/daterangepicker.css" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">
	
	<link rel="stylesheet" type="text/css" href="css/logistikapp.css">
    
    <!-- this page specific styles -->

	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
	
	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
	
	<style>
		#map {
			height: 500px!important;
			width: 100%!important;
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
                                    <li><a href="actives_list.htm?ids=${id_store }">Activos</a></li>
                                    <li class="active">Visitas</li>
                                </ol>
                                
                                <h1 class="pull-left">Listado de Visitas</h1>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">
                                    <header class="main-box-header clearfix">
                                        <div class="row">
	                                        <a href="actives_list.htm?ids=${id_store }" class="btn btn-primary pull-left"><i class="fa fa-arrow-left"></i> Volver</a>
											<div class="col-sm-5 pull-right">
												<div class="input-group">
													<span class="input-group-addon"><i class="fa fa-calendar-o"></i></span>
													<input type="text" class="form-control" id="datepickerDateRange">
												</div>
											</div>
                                        </div>
									</header>
                                    
                                    <div class="main-box-body clearfix">
                                        <div class="row">
                                        	<div class="col-xs-12">
                                        		<div class="table-responsive">
													<table class="table table-hover table-striped">
														<thead>
															<tr>
																<th>Cliente</th>
																<th>Ubicación</th>
																<th>Alerta</th>
																<th>Registro</th>
															</tr>
														</thead>
														<tbody>
															<tr v-for="i in list">
																<td>
																	{{i.store}}
																</td>
																<td>
																	<a @click.prevent="showMap(i)" class="badge badge-primary"><i class="fa fa-map-marker"></i> {{i.latitude + ',' + i.longitude}}</a>
																</td>
																<td>
																	<a class="badge badge-danger" v-if="(!!i.comment || !!i.image1 || !!i.image2)" @click="showProblems(i)">
																		<i class="fa fa-times"></i>
																	</a>
																	<a class="badge badge-default" v-else>
																		<i class="fa fa-minus"></i>
																	</a>
																</td>
																<td>
																	{{i.created | format('DD/MM/YYYY')}}
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

				<footer id="footer-bar" class="row">
                    <c:import url="/html/footer.html" />
				</footer>
				
			</div>
		</div>
		
		
		<div class="modal fade" id="modal-map">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Ubicación</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12">
								<span class="label label-primary"><i class="fa fa-map-marker"></i> Activo</span>
								<span class="label label-success"><i class="fa fa-map-marker"></i> Cliente</span>
							</div>
						</div>
						<br />
						<div id="map"></div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="modal-problems">
			<div class="modal-dialog">
				<div class="modal-content">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<div class="modal-header bg-danger">
						<h4 class="modal-title">Comentarios e Imágenes</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12">
								<p v-text="item.comment"></p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6" v-if="!!item.image1">
								<img :src="item.image1" alt="" />
							</div>
							<div class="col-md-6" v-if="!!item.image2">
								<img :src="item.image2" alt="" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- global scripts -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.nanoscroller.min.js"></script>
    <script type="text/javascript" src="js/moment.min.js"></script>
    <script src="js/daterangepicker.js"></script>
    <script src="js/select2.min.js"></script>
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/dataTables.fixedHeader.js"></script>
    <script src="js/dataTables.tableTools.js"></script>
    <script src="js/jquery.dataTables.bootstrap.js"></script>

	<!-- this page specific inline scripts -->
	<script src="js/markerwithlabel.js"></script>
    <script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>		
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/pace.min.js"></script>

    <!-- this page specific inline scripts -->
    
    <script>
    
    	var app = new Vue({
    		el: "#page-wrapper",
    		data: {
    			list: [],
    			dateInit: new Date(),
    			dateEnd: new Date(),
    			item: {}
    		},
    		mounted: function() {
    			this.getList()
    		},
    		methods: {
    			getList: function() {

    				RetailServiceBean.getVisitedList({
    					id_active: ${id_active},    
        				dateInit: this.dateInit,
        				dateEnd: this.dateEnd
        			}, function(res) {
        				app.list = res
        			})
    			},
    			showMap: function(el) {
    				app.item = el
    				$('#modal-map').modal('show')
    			},
    			showProblems: function(i) {
    				console.log(i)
    				this.item = i
    				$('#modal-problems').modal('show')
    			}
    		},
    		filters: {
    			format: function(value, f) {
    				return moment(value).format(f)
    			}
    		},
    		watch: {
    			dateInit: function(newValue) {
    				app.getList()
    			},
    			item: function(newValue) {
    				markerStore.setPosition(new google.maps.LatLng(app.item.store_lat, app.item.store_lng))
    				markerActive.setPosition(new google.maps.LatLng(app.item.latitude, app.item.longitude))
    			}
    		}
    	})
    	
    	$('#datepickerDateRange').val(moment(new Date()).format('DD/MM/YYYY') + ' - ' +  moment(new Date()).format('DD/MM/YYYY'))
    	$('#datepickerDateRange').daterangepicker({
    	    "format": "DD/MM/YYYY",
    	    "ranges": {
        	    'Hoy': [moment(), moment()],
                'Ayer': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                'Mañana': [moment().add(1, 'days'), moment().add(1, 'days')],
                'Últimos 3 dias': [moment().subtract(3, 'days'), moment()],
                'Próximos 3 dias': [moment().add(1, 'days'),moment().add(3, 'days')],
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
        	"startDate": moment(new Date()).format('DD/MM/YYYY') + '00:00:00',
        	"endDate": moment(new Date()).format('DD/MM/YYYY') + " 23:59:59" ,
        	"opens": "left",
        	"buttonClasses": "btn btn-xs"
        }, function(start, end, label) {
        	app.dateInit = start.toDate()
        	app.dateEnd = end.toDate()
        });
    	
    	var gmap = new GMaps({
        	el: '#map',
        	zoom: 15,
        	center: new google.maps.LatLng(0, 0)
        })
    	
    	var markerActive = gmap.addMarker({
    		lat: 0,
    		lng: 0,
    		icon: 'img/icon-marker-green.png',
    		title: 'Activo',
    		infoWindow : {
    			content: '<p>Posición activo</p>'
    		}
    	})
    	
    	var markerStore = gmap.addMarker({
    		lat: 0,
    		lng: 0,
    		icon: 'img/icon-marker-blue.png',
    		title: 'Cliente',
    		infoWindow : {
    			content: '<p>Posición cliente</p>'
    		}
    	})
    	
    	$('#modal-map').on('show.bs.modal', function() {
    		console.log('adsdas')
    		setTimeout(function() {
    			gmap.refresh()
    			gmap.setCenter(app.item.latitude, app.item.longitude)
    		}, 1000)
    	})
    </script>

</body>

</html>