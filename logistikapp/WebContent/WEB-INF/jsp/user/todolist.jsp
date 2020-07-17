<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Ciudades - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />

	<!-- libraries -->
	<link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" href="css/libs/nanoscroller.css" type="text/css" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

	<!-- this page specific styles -->
	
	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon"/>
	
	<!-- Dwr script -->
	<script type='text/javascript' src='dwr/interface/UserServiceBean.js'></script>
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
	<!-- this page specific scripts -->

	<header class="navbar" id="header-navbar">
	    <c:import url="/html/menu-top.jsp"/>
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
										<li><a href="#">Dashboard</a></li>
										<li class="active"><span>Configuraci&oacute;n</span></li>
									</ol>

									<div class="clearfix">
										<h1 class="pull-left">Tareas</h1>

										<div class="pull-right top-page-ui">
											<a href="todoadd.htm" class="btn btn-primary pull-right"><i class="fa fa-plus-circle fa-lg"></i> Agregar</a>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-lg-12">
									<div class="main-box no-header clearfix">
										<div class="main-box-body clearfix">
											<div class="table-responsive">
											
											    <form id="form1" method="post">
<!-- =========================================================================================== -->
													<div class="col-lg-12">
														<div class="main-box clearfix">
															<div class="tabs-wrapper tabs-no-header">
																<div class="tab-content tab-content-body clearfix">
																	<div class="tab-pane fade active in" id="tab-todo">
																		<c:if test="${fn:length(list_todo)<1}">
																			No hay tareas.
																		</c:if>
		 																<c:if test="${fn:length(list_todo)>0}">
																			<ul class="widget-todo">
				 																<c:forEach var="td" items="${list_todo}" varStatus="vst" >
																					<li class="clearfix" id="${vst.index}">
																						<div class="name" >
																							<div class="checkbox-nice" >
																								<input type="checkbox" id="todo-${vst.index}" <c:out value="${ td.active == 'N' ? 'checked=\"checked\"' : '' }" /> onclick="javascript:estadoTarea(${td.id_todo},'${td.active}');" >
																								<c:if test="${ td.priority == 'A' }">
																									<c:set var="clase" value="label label-warning"/>
																									<c:set var="texto_clase" value="Media Prioridad"/>
																								</c:if>
																								<c:if test="${ td.priority == 'B' }">
																									<c:set var="clase" value="label label-danger"/>
																									<c:set var="texto_clase" value="Alta Prioridad"/>
																								</c:if>
																								
																								<label for="todo-${vst.index}">
																									<c:out value="${td.todo}"/><span class="${clase}"><c:out value="${texto_clase}"/></span>
																								</label>
																							</div>
																						</div>
																						<div class="actions">
																							<a href="todoupd.htm?id_todo=${td.id_todo}" class="table-link">
																								<i class="fa fa-pencil"></i>
																							</a>
																							<a href="#" class="table-link danger" onclick="delTarea(${td.id_todo});">
																								<span class="fa-stack">
																									<i class="fa fa-square fa-stack-2x"></i>
																									<i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
																								</span>
																							</a>
																						</div>
																					</li>
				 																</c:forEach>
																			</ul>
		 																</c:if>
																	</div>
																</div>
															</div>
														</div>
													</div>
<!-- =========================================================================================== -->

												</form>
											
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
	
	<script src="js/demo.js"></script> <!-- only for demo -->
	
	<!-- this page specific scripts -->
	
	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	
	<!-- this page specific inline scripts -->

</body>
<script>
	function estadoTarea(id_todo, active){
		var UpdToDoDTO = new Object();
		UpdToDoDTO.id_todo = id_todo;
		if(active == "S")
			UpdToDoDTO.active = "N";
		if(active == "N")
			UpdToDoDTO.active = "S";
		
		UserServiceBean.updToDo(UpdToDoDTO);
	}

	function delTarea(id_tarea){
		confirmar=confirm("¿Quieres borrar la tarea permanentemente? id_tarea = "+id_tarea); 
		if (confirmar){
			UserServiceBean.delToDoById(id_tarea);
			alert("Tarea borrada");
			document.location = "todolist.htm";
		} 
	} 
	
	var index_tarea = -1;
	function borrarTarea(id_todo, tarea_ind){
		index_tarea = tarea_ind;
		UserServiceBean.getToDoById(id_todo, reply);
	}
	var reply = function(data){
		if(data.active == "${NO_ACTIVE}"){
			UserServiceBean.delToDoById(data.id_todo);
			document.getElementById(index_tarea).hidden = true;
		}
		else
			alert("Se tiene que completar la tarea, \nmarcando la tarea del lado izquierdo de cada tarea \npara poder borrarla.");
	};
</script>
<div class="md-overlay"></div> 
 
</html>