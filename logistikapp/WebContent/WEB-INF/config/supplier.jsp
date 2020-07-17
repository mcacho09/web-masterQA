<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Empresa - LogistikApp</title>

    <!-- bootstrap -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />

    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">
    <link rel="stylesheet" type="text/css" href="css/libs/bootstrap-editable.css">

	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
	
    <!-- Dwr script-->
    <script type='text/javascript' src='dwr/interface/SupplierServiceBean.js'></script>
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
									<li><a href="home.htm">Home</a></li>
									<li class="active"><span>Configuraci&oacute;n</span></li>
								</ol>

								<div class="clearfix">
									<h1 class="pull-left">Empresa</h1>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-lg-5">
								<div class="main-box clearfix no-header">
									<div class="main-box-body clearfix">
										<div class="table-responsive">
											<table id="user" class="table table-hover"
												style="clear: both">
												<thead>
													<tr>
														<th><span>Atributo</span></th>
														<th><span>Click para editar</span></th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td width="35%">Id</td>
														<td width="65%">${supplier.id_supplier}</td>
													</tr>
													<tr>
														<td>C&oacute;digo</td>
														<td><a href="#" id="code" data-type="text"
															data-pk="1" data-placement="bottom"
															data-placeholder="Código" maxlength="100" data-title="Codigo">${supplier.code}</a></td>
													</tr>
													<tr>
														<td>Nombre</td>
														<td><a href="#" id="name" data-type="text"
															data-pk="1" data-placement="bottom"
															data-placeholder="Nombre" maxlength="100" data-title="Nombre">${supplier.name}</a></td>
													</tr>
													<tr>
														<td>Tiempo promedio de viaje (min)</td>
														<td><a href="#" id="travel_time" data-type="text"
															data-pk="1" data-placement="bottom"
															data-placeholder="Tiempo en minutos" data-title="Tiempo">${supplier.travel_time == null? '15':supplier.travel_time}</a></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-7">
								<div class="main-box clearfix">
									<header class="main-box-header clearfix">
										<h3>Informaci&oacute;n del plan</h3>
									</header>
									<div class="main-box-body clearfix">
										<div class="col-lg-6">
											<div class="pricing-package">
												<div class="pricing-package-inner">
													<div class="package-header">
														<span class="stars center-block"> <i
															class="fa fa-star"></i> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i>
														</span>
														<h3>${plan.plan_name}</h3>
													</div>
													<div class="package-content">
														<div class="package-price">
															&dollar;${plan.amount}<span class="package-month">/
																mes</span>
														</div>
														<ul class="package-top-features">
															<li><c:choose>
																	<c:when test="${plan.customers == 999999999}">Clientes ilimitados</c:when>
																	<c:otherwise>${plan.customers} Clientes</c:otherwise>
																</c:choose></li>
															<li><c:choose>
																	<c:when test="${plan.users == 999999999}">Usuarios ilimitados</c:when>
																	<c:otherwise>${plan.users} Usuarios</c:otherwise>
																</c:choose></li>
															<li>Suscripci&oacute;n: <fmt:formatDate
																	value="${supplier.plan_created}" type="date"
																	pattern="dd/MM/yyyy" />
															</li>
															<li>V&aacute;lido hasta: <fmt:formatDate
																	value="${supplier.plan_end}" type="date"
																	pattern="dd/MM/yyyy" />
															</li>
														</ul>
														<ul class="package-features">

														</ul>
													</div>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<h2>Módulos habilitados</h2>
											<ul class="list-group">
												<li class="list-group-item"><span
													class="badge badge-success"><i class="fa fa-check"></i></span>
													Inteligencia de Negocios</li>
												<li class="list-group-item"><span
													class="${plan.routes eq 'S'? 'badge badge-success' : 'badge badge-danger'}"><i
														class="fa fa-check"></i></span> Rutas</li>
												<li class="list-group-item"><span
													class="${plan.travels eq 'S'? 'badge badge-success' : 'badge badge-danger'}"><i
														class="fa fa-check"></i></span> Viajes</li>
												<li class="list-group-item"><span
													class="badge badge-success"><i class="fa fa-check"></i></span>
													CRM Visitas</li>
												<li class="list-group-item"><span
													class="${plan.callcenter eq 'S'? 'badge badge-success' : 'badge badge-danger'}"><i
														class="fa fa-check"></i></span> Call Center</li>
												<li class="list-group-item"><span
													class="${plan.notifications eq 'S'? 'badge badge-success' : 'badge badge-danger'}"><i
														class="fa fa-check"></i></span> Notificaciones</li>
												<li class="list-group-item"><span
													class="${plan.messages eq 'S'? 'badge badge-success' : 'badge badge-danger'}"><i
														class="fa fa-check"></i></span> Mensajes Privados</li>
												<li class="list-group-item"><span
													class="${plan.groups eq 'S'? 'badge badge-success' : 'badge badge-danger'}"><i
														class="fa fa-check"></i></span> Chat Grupal</li>
												<li class="list-group-item"><span
													class="${plan.tasks eq 'S'? 'badge badge-success' : 'badge badge-danger'}"><i
														class="fa fa-check"></i></span> Tareas</li>
												<li class="list-group-item"><span
													class="${plan.scheduling eq 'S'? 'badge badge-success' : 'badge badge-danger'}"><i
														class="${plan.scheduling eq 'S'? 'fa fa-check' : 'fa fa-times'}"></i></span>
													Calendario</li>
												<li class="list-group-item"><span
													class="badge badge-success"><i class="fa fa-check"></i></span>
													Configuraci&oacute;n</li>
											</ul>
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
	<script src="js/bootstrap-editable.min.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/pace.min.js"></script>

	<!-- this page specific inline scripts -->
	<script>
	window.onload = function(e){
	   	<c:if test="${fn:contains(useracegi.profile, 'SUP')&&(useracegi.superuser == 'N') }">
    		window.location.href = "403.html";
		</c:if>
	}
	
    $(document).ready(function(){
    
 
        //toggle `popup` / `inline` mode
        $.fn.editable.defaults.mode = 'popup';     
        
        //make name, code editable
        $('#code').editable({
            validate: function(value) {
                if($.trim(value) == '') {
                    return 'Este campo es requerido';
                }else if($.trim(value).length > 9){
                	return 'Menos de 10 caracteres';
                }else {
                	   var UpdSupplierDTO = new Object();
                	   UpdSupplierDTO.id_supplier = "${supplier.id_supplier}";
                	   UpdSupplierDTO.code = value;
                	SupplierServiceBean.updSupplier(UpdSupplierDTO, reply);
                	//
                }
            }
        });
        //make name, code editable
        $('#name').editable({
            validate: function(value) {
                if($.trim(value) == '') {
                    return 'Este campo es requerido';
                }
                else {
                	var UpdSupplierDTO = new Object();
                	UpdSupplierDTO.id_supplier = "${supplier.id_supplier}";
                	UpdSupplierDTO.name = value;
                	SupplierServiceBean.updSupplier(UpdSupplierDTO, reply);
                	//
                }
            }
        });
        
        //make time  editable
        var patron=new RegExp(/^([0-9])*$/);
        $('#travel_time').editable({
            validate: function(value) {
                if($.trim(value) == '') {
                    return 'Este campo es requerido';
                }else if($.trim(value).length > 3){
                	return 'Ingrese un tiempo menor';
                }else if(!patron.test(value)){
                	return 'Valor incorrecto';
                }else{
                	var UpdSupplierDTO = new Object();
                	UpdSupplierDTO.id_supplier = "${supplier.id_supplier}";
                	UpdSupplierDTO.travel_time = value;
                	SupplierServiceBean.updSupplier(UpdSupplierDTO, reply);
                	//
                }
            }
        });
        
        var reply = function(data) {
        //alert(data);	
        };
                
    });
    </script>
</body>
</html>