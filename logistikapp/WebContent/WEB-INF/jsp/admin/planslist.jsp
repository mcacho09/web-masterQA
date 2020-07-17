<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Planes - LogistikApp</title>
    
    <!-- bootstrap -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
    
    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />
    
    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.fixedHeader.css">
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.tableTools.css">
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">
    <link rel="stylesheet" type="text/css" href="css/libs/nifty-component.css"/>
    <script src="dwr/interface/SupplierServiceBean.js"></script>
        
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
            </div>
            <!-- /nav-col -->

            <div id="content-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        
                        <div id="breadcrumb" class="row">
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a href="home.htm">INICIO</a></li>
                                    <li><span><spring:message code="label.breadcrumb.admin"/></span></li>
                                    <li class="active">PLANES</li>
                                </ol>
                                <h1>Planes</h1>
                            </div>
                        </div>
                        <!-- /breadcrumb -->
                        
                        <div id="user-box" class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">
                                
                                    <header class="main-box-header clearfix">
                                            <div id="header-tools" class="pull-left">
                                            
                                                <div class="btn-group">
                                                    <button id="toolbars-checkall" class="btn btn-primary" type="button" title="Seleccionar" data-toggle="tooltip" data-placement="bottom">
                                                        <i class="fa fa-square-o"></i>
                                                    </button>
                                                </div>
                                                
                                                <div class="btn-group">
                                                    <button id="toolbars-new" class="btn btn-primary" type="button" title="Nuevo" data-toggle="tooltip" data-placement="bottom">
                                                        <i class="fa fa-plus"></i> <span class="hidden-xs">Nuevo</span>
                                                    </button>
                                                </div>
                                            </div>
                                    </header>
                                    
                                    <div class="main-box-body clearfix">
                                        <div class="table-responsive">
                                            <table id="table" class="table table-hover table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Nombre</th>
                                                        <th>Clientes</th>
                                                        <th>Usuarios</th>
                                                        <th>Busqueda</th>
                                                        <th>Programación</th>
                                                        <th>Rutas</th>
                                                        <th>Viajes</th>
                                                        <th>Historico</th>
                                                        <th>Call center</th>
                                                        <th>Notificaciones</th>
                                                        <th>Mensajes</th>
                                                        <th>Grupos</th>
                                                        <th>Tareas</th>
                                                        <th>Precio</th>
                                                        <th>Editar</th>
                                                        <th>Eliminar</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                     <c:forEach var="i" items="${plans }">
                                                     	<tr>
                                                     		<td>${i.plan_name }</td>
	                                                     	<td>${i.customers }</td>
	                                                     	<td>${i.users }</td>
	                                                     	<td>
		                                                     	<c:choose>
			                                                     	<c:when test="${i.searchbox == 'S' }">
		                                                     			<span class="label label-success"><i class="fa fa-check"></i></span>
			                                                     	</c:when>
		                                                     		<c:otherwise>
		                                                     			<span class="label label-danger"><i class="fa fa-times"></i></span>
		                                                     		</c:otherwise>
		                                                     	</c:choose>
	                                                     	</td>
	                                                     	<td>
		                                                     	<c:choose>
			                                                     	<c:when test="${i.scheduling == 'S' }">
		                                                     			<span class="label label-success"><i class="fa fa-check"></i></span>
			                                                     	</c:when>
		                                                     		<c:otherwise>
		                                                     			<span class="label label-danger"><i class="fa fa-times"></i></span>
		                                                     		</c:otherwise>
		                                                     	</c:choose>
	                                                     	</td>
	                                                     	<td>
		                                                     	<c:choose>
			                                                     	<c:when test="${i.routes == 'S' }">
		                                                     			<span class="label label-success"><i class="fa fa-check"></i></span>
			                                                     	</c:when>
		                                                     		<c:otherwise>
		                                                     			<span class="label label-danger"><i class="fa fa-times"></i></span>
		                                                     		</c:otherwise>
		                                                     	</c:choose>
	                                                     	</td>
	                                                     	<td>
		                                                     	<c:choose>
			                                                     	<c:when test="${i.travels == 'S' }">
		                                                     			<span class="label label-success"><i class="fa fa-check"></i></span>
			                                                     	</c:when>
		                                                     		<c:otherwise>
		                                                     			<span class="label label-danger"><i class="fa fa-times"></i></span>
		                                                     		</c:otherwise>
		                                                     	</c:choose>
	                                                     	</td>
	                                                     	<td>
		                                                     	<c:choose>
			                                                     	<c:when test="${i.historical == 'S' }">
		                                                     			<span class="label label-success"><i class="fa fa-check"></i></span>
			                                                     	</c:when>
		                                                     		<c:otherwise>
		                                                     			<span class="label label-danger"><i class="fa fa-times"></i></span>
		                                                     		</c:otherwise>
		                                                     	</c:choose>
	                                                     	</td>
	                                                     	<td>
		                                                     	<c:choose>
			                                                     	<c:when test="${i.callcenter == 'S' }">
		                                                     			<span class="label label-success"><i class="fa fa-check"></i></span>
			                                                     	</c:when>
		                                                     		<c:otherwise>
		                                                     			<span class="label label-danger"><i class="fa fa-times"></i></span>
		                                                     		</c:otherwise>
		                                                     	</c:choose>
	                                                     	</td>
	                                                     	<td>
		                                                     	<c:choose>
			                                                     	<c:when test="${i.notifications == 'S' }">
		                                                     			<span class="label label-success"><i class="fa fa-check"></i></span>
			                                                     	</c:when>
		                                                     		<c:otherwise>
		                                                     			<span class="label label-danger"><i class="fa fa-times"></i></span>
		                                                     		</c:otherwise>
		                                                     	</c:choose>
	                                                     	</td>
	                                                     	<td>
		                                                     	<c:choose>
			                                                     	<c:when test="${i.messages == 'S' }">
		                                                     			<span class="label label-success"><i class="fa fa-check"></i></span>
			                                                     	</c:when>
		                                                     		<c:otherwise>
		                                                     			<span class="label label-danger"><i class="fa fa-times"></i></span>
		                                                     		</c:otherwise>
		                                                     	</c:choose>
	                                                     	</td>
	                                                     	<td>
		                                                     	<c:choose>
			                                                     	<c:when test="${i.searchbox == 'S' }">
		                                                     			<span class="label label-success"><i class="fa fa-check"></i></span>
			                                                     	</c:when>
		                                                     		<c:otherwise>
		                                                     			<span class="label label-danger"><i class="fa fa-times"></i></span>
		                                                     		</c:otherwise>
		                                                     	</c:choose>
	                                                     	</td>
	                                                     	<td>
	                                                     	<c:choose>
		                                                     	<c:when test="${i.groups == 'S' }">
	                                                     			<span class="label label-success"><i class="fa fa-check"></i></span>
		                                                     	</c:when>
	                                                     		<c:otherwise>
	                                                     			<span class="label label-danger"><i class="fa fa-times"></i></span>
	                                                     		</c:otherwise>
	                                                     	</c:choose>
	                                                     	</td>
                                                     		<td>
                                                     			<span class="label label-primary">$${i.amount }</span>
                                                     		</td>
                                                     		<td>
                                                     			<button type="button" class="btn btn-primary btn-xs btn-edit" data-id="${i.id_plan }"><i class="fa fa-pencil"></i></button>
                                                     		</td>
                                                     		<td>
                                                     			<button type="button" class="btn btn-danger btn-xs btn-rm" data-id="${i.id_plan }"><i class="fa fa-trash"></i></button>
                                                     		</td>
                                                     	</tr>
                                                     </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                        <!-- /user-box -->
                        
                    </div>
                </div>
                
                <footer id="footer-bar" class="row">
                    <c:import url="/html/footer.html" />
                </footer>
            </div>
            
            <div class="md-modal md-effect-1" id="modal-plan">
				<div class="md-content">
					<div class="modal-header">
						<button type="button" class="md-close close" onclick="$('#modal-plan').toggleClass('md-show')">&times;</button>
						<h4 class="modal-title">Nuevo plan</h4>
					</div>
					<div class="modal-body">
						<form id="form" class="form-horizontal" role="form">
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Nombre:</label>
								<div class="col-sm-10">
									<input type="text" name="name" id="name" class="form-control" value="Plan" pattern=".{7,255}" required="required">
								</div>
							</div>
						
							<div class="form-group">
								<label for="clients" class="col-sm-2 control-label">Clientes:</label>
								<div class="col-sm-10">
									<input type="number" min="1" step="1" name="clients" id="clients" class="form-control" value="" required="required">
								</div>
							</div>
						
							<div class="form-group">
								<label for="users" class="col-sm-2 control-label">Usuarios:</label>
								<div class="col-sm-10">
									<input type="number" name="users" step="1" min="1" id="users" class="form-control" value="" required="required">
								</div>
							</div>
							<div class="row">
								<div class="col-xs-11 col-xs-offset-1">
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="search" id="search">
											Busqueda
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="programation" id="programation">
											Programación
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="routes" id="routes">
											Rutas
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="travels" id="travels">
											Viajes
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="historic" id="historic">
											Histórico
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="callcenter" id="callcenter">
											Call center
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="notifications" id="notifications">
											Notificaciones
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="messages" id="messages">
											Mensajes
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="groups" id="groups">
											Grupos
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="tasks" id="tasks">
											Tareas
										</label>
									</div>
								</div>
							</div>
						
							<div class="form-group">
								<label for="amount" class="col-sm-2 control-label">Precio:</label>
								<div class="col-sm-10">
									<input type="number" min=".10" step=".10" name="amount" id="amount" class="form-control" value="" required="required">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" onclick="$('#modal-plan').toggleClass('md-show')">Cancelar</button>
						<button type="button" class="btn btn-primary" data-save="true" id="btn-save">Guardar</button>
					</div>
				</div>
			</div>
		
            <div class="md-modal md-effect-1" id="modal-upd-plan">
				<div class="md-content">
					<div class="modal-header">
						<button type="button" class="md-close close" onclick="$('#modal-upd-plan').toggleClass('md-show')">&times;</button>
						<h4 class="modal-title">Actualizar plan</h4>
					</div>
					<div class="modal-body">
						<form id="form-upd" class="form-horizontal" role="form">
							<input type="hidden" id="id_plan" name="id_plan" />
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Nombre:</label>
								<div class="col-sm-10">
									<input type="text" name="name" id="name" class="form-control" value="Plan" pattern=".{7,255}" required="required">
								</div>
							</div>
						
							<div class="form-group">
								<label for="clients" class="col-sm-2 control-label">Clientes:</label>
								<div class="col-sm-10">
									<input type="number" min="1" step="1" name="clients" id="clients" class="form-control" value="" required="required">
								</div>
							</div>
						
							<div class="form-group">
								<label for="users" class="col-sm-2 control-label">Usuarios:</label>
								<div class="col-sm-10">
									<input type="number" name="users" step="1" min="1" id="users" class="form-control" value="" required="required">
								</div>
							</div>
							<div class="row">
								<div class="col-xs-11 col-xs-offset-1">
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="search" id="search">
											Busqueda
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="programation" id="programation">
											Programación
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="routes" id="routes">
											Rutas
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="travels" id="travels">
											Viajes
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="historic" id="historic">
											Histórico
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="callcenter" id="callcenter">
											Call center
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="notifications" id="notifications">
											Notificaciones
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="messages" id="messages">
											Mensajes
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="groups" id="groups">
											Grupos
										</label>
									</div>
									<div class="checkbox">
										<label>
											<input type="checkbox" value="S" name="tasks" id="tasks">
											Tareas
										</label>
									</div>
								</div>
							</div>
						
							<div class="form-group">
								<label for="amount" class="col-sm-2 control-label">Precio:</label>
								<div class="col-sm-10">
									<input type="number" min=".10" step=".10" name="amount" id="amount" class="form-control" value="" required="required">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" onclick="$('#modal-upd-plan').toggleClass('md-show')">Cancelar</button>
						<button type="button" class="btn btn-primary" id="btn-upd">Guardar</button>
					</div>
				</div>
			</div>
		
			<div class="md-overlay"></div>

        </div>
    </div>
    <!-- /page-wrapper -->

    <!-- global scripts -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.nanoscroller.min.js"></script>

    <!-- this page specific scripts -->
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/dataTables.fixedHeader.js"></script>
    <script src="js/dataTables.tableTools.js"></script>
    <script src="js/jquery.dataTables.bootstrap.js"></script>
    
    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
    <script src="js/pace.min.js"></script>
    <script src="js/sweetalert.min.js"></script>
	<script src="js/modalEffects.js"></script>
    
    <!-- this page specific inline scripts -->
    <script>
    $(document).on('ready', function() {

        $('#toolbars-new').click( function() {
            $('#modal-plan').addClass('md-show')
        });
        
        var table = $('#table').dataTable({
        	search: true,
        	stateSave: false,
        	pageLength: 10,
            language: {
                url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
            }
        })
        
        $('.btn-rm').on('click', function() {
        	
        	var $btn = $(this)
        	
        	swal({   
    			title: "Verificando plan!",   
    			text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
    			html: true, 
    			showConfirmButton: false 
    		});
        	
        	SupplierServiceBean.getTotalSupplier($btn.data('id'), function(data) {
        		if (data > 0) {
        			swal('Alerta', 'Este plan cuenta con empresas asignadas', 'warning')
        		} else {
        			swal({
        				title: 'Eliminar plan',
        				text: '¿Desea eliminar el plan?',
        				showCancelButton: true,
        				cancelButtonText: 'No',
        				confirmButtonText: 'Si',
        				closeOnConfirm: false,
        				showLoaderOnConfirm: true,
        			}, function() {
        				SupplierServiceBean.delPlanByIdPlan($btn.data('id'), function(data){
        					if (data == 0) {
        						swal('Alerta', 'El plan no fue eliminado, intente más tarde', 'error')
        					} else {
        						$btn.parent().parent().remove()
        						swal('Exito', 'Plan eliminado', 'success')
        					}
        				})
        			})
        		}
        	})
        	
        })
        
        $('#btn-save').on('click', function() {
        	if (document.forms.form.checkValidity()) {
        		swal({   
        			title: "Guardando plan!",   
        			text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
        			html: true, 
        			showConfirmButton: false 
        		});
        		
        		var form = document.forms.form
        		
        		var obj = {
        			plan_name: form.name.value,
        			customers: form.clients.value,
        			users: form.users.value,
        			searchbox: form.search.checked ? 'S' : 'N',
        			scheduling: form.programation.checked ? 'S': 'N',
        			routes: form.routes.checked ? 'S' : 'N',
        			travels: form.travels.checked ? 'S' : 'N',
        			historical: form.historic.checked ? 'S' : 'N',
        			callcenter: form.callcenter.checked ? 'S' : 'N',
        			notifications: form.notifications.checked ? 'S' : 'N',
        			messages: form.messages.checked ? 'S' : 'N',
        			groups: form.groups.checked ? 'S' : 'N',
        			tasks: form.tasks.checked ? 'S' : 'N',
        			amount: form.amount.value
        		}
        		
        		SupplierServiceBean.addPlan(obj, function(data){
        			if (data > 0) {
        				swal('Exito', 'Plan guardado', 'success')
        			} else {
        				swal('Alerta', 'El plan no fue guardado, intente más tarde', 'error')
        			}
        			setTimeout(function(){
        				location.reload()
        			}, 5000)
        		})
        		
        	} else {
        		swal('Alerta', 'Ingresa todos los valores', 'warning')
        	}
        })
        
        $('.btn-edit').on('click', function(){
        	swal({   
    			title: "Guardando plan!",   
    			text: 'Espere por favor, obteniendo información del plan <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
    			html: true, 
    			showConfirmButton: false 
    		});
        	SupplierServiceBean.getPlanById($(this).data('id'), function(data) {
        		if (data) {
        			var form = document.forms['form-upd']
            		
        			$('#modal-upd-plan').addClass('md-show')
            		
            		form.id_plan.value = data.id_plan
            		form.name.value = data.plan_name
            		form.clients.value = data.customers
            		form.users.value = data.users
            		form.search.checked = data.searchbox == 'S'
            		form.programation.checked = data.scheduling == 'S'
            		form.routes.checked = data.routes == 'S'
            		form.travels.checked = data.travels == 'S'
            		form.historic.checked = data.historical == 'S'
            		form.callcenter.checked = data.callcenter == 'S'
            		form.notifications.checked = data.notifications == 'S'
            		form.messages.checked = data.messages == 'S'
            		form.groups.checked = data.groups == 'S'
            		form.tasks.checked = data.tasks == 'S'
            		form.amount.value = data.amount
            		
            		swal.close()
            		
        		} else {
        			swal('Alerta', 'El plan no se pudo obtener, intente más tarde', 'warning')
        		}
        	})
        })
        
        $('#btn-upd').on('click', function() {
        	if (document.forms['form-upd'].checkValidity()) {
        		swal({   
        			title: "Actualizando plan!",   
        			text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
        			html: true, 
        			showConfirmButton: false 
        		});
        		
        		var form = document.forms['form-upd']
        		
        		var obj = {
        			id_plan: form.id_plan.value,
        			plan_name: form.name.value,
        			customers: form.clients.value,
        			users: form.users.value,
        			searchbox: form.search.checked ? 'S' : 'N',
        			scheduling: form.programation.checked ? 'S': 'N',
        			routes: form.routes.checked ? 'S' : 'N',
        			travels: form.travels.checked ? 'S' : 'N',
        			historical: form.historic.checked ? 'S' : 'N',
        			callcenter: form.callcenter.checked ? 'S' : 'N',
        			notifications: form.notifications.checked ? 'S' : 'N',
        			messages: form.messages.checked ? 'S' : 'N',
        			groups: form.groups.checked ? 'S' : 'N',
        			tasks: form.tasks.checked ? 'S' : 'N',
        			amount: form.amount.value
        		}
        		
        		SupplierServiceBean.updPlan(obj, function(data){
        			if (data > 0) {
        				swal('Exito', 'Plan actualizado', 'success')
        			} else {
        				swal('Alerta', 'El plan no fue actualizado, intente más tarde', 'error')
        			}
        			setTimeout(function(){
        				location.reload()
        			}, 5000)
        		})
        		
        	} else {
        		swal('Alerta', 'Ingresa todos los valores', 'warning')
        	}
        })
    })
    
    </script>
    
</body>

</html>