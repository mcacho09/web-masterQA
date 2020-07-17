<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    
    <title>Viajes - LogistikApp</title>
    
    <!-- bootstrap -->
    <link rel="stylesheet" type="text/	css" href="css/bootstrap/bootstrap.min.css" />
    
    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />
    
    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css" />

    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/libs/daterangepicker.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/select2.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.fixedHeader.css">
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.tableTools.css">

    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">

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
	<form action="travelaction.htm" method="post" id="formtravel" name="formtravel">
        <input type="hidden" id="idts" name="idts" value=""/>
        <input type="hidden" id="accion" name="accion" value="">
    </form>
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
                                <ol class="breadcrumb hidden-xs">
                                    <li><a href="home.htm">Home</a></li>
                                    <li class="active"><span>Log&iacute;stica</span></li>
                                </ol>
                                <div class="clearfix">
                                    <h1 class="pull-left"><a href="travelmanagerlist.htm">Viajes</a></h1>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">
                                
                                    <header class="main-box-header clearfix">
	                                    
	                                        <div class="row">
	                                            <div class="col-lg-5">
		                                            <div class="btn-group">
		                                                 <button id="toolbars-new" class="btn btn-primary" type="button" title="Nuevo" data-toggle="tooltip">
		                                                     <i class="fa fa-plus"></i> Nuevo
		                                                 </button>
		                                            </div>
		                                            
		                                            <div class="btn-group">
		                                                <button id="toolbars-refresh" class="btn btn-primary" type="button" title="Actualizar" data-toggle="tooltip" onclick="location.href='travelmanagerlist.htm'">
		                                                    <i class="fa fa-refresh"></i>
		                                                </button>
		                                                <button class="btn btn-primary" type="button" title="Geolocalizar" data-toggle="tooltip" data-placement="bottom">
		                                                    <i class="fa fa-map-marker"></i>
		                                                </button>
		                                                <button class="btn btn-primary" type="button" title="Modificar" data-toggle="tooltip" data-placement="bottom">
		                                                    <i class="fa fa-pencil"></i>
		                                                </button>
		                                                <button class="btn btn-primary" type="button" title="Duplicar" data-toggle="tooltip" data-placement="bottom">
		                                                    <i class="fa fa-copy"></i>
		                                                </button>
		                                                <button class="btn btn-primary" type="button" title="Cancelar" data-toggle="tooltip" data-placement="bottom">
		                                                    <i class="fa fa-times"></i>
		                                                </button>
		                                                <button class="btn btn-primary" type="button" title="Eliminar" data-toggle="tooltip" data-placement="bottom">
		                                                    <i class="fa fa-trash-o"></i>
		                                                </button>
		                                            </div>
	                                            </div>
	                                            <div class="col-lg-4">
			                                        <div class="btn-group">
			                                            <select class="form-control">
			                                                <option>- Ruta -</option>
			                                                <c:forEach var="rt" items="${routes}">
			                                                    <option value="${rt.id_route}">${rt.name}</option>
			                                                </c:forEach>
			                                            </select>
			                                        </div>
			                                    
			                                        <div class="btn-group">
			                                            <select class="form-control">
			                                                <option>- Estado -</option>
			                                                <option>Borrador</option>
			                                                <option>Programado</option>
			                                                <option>Confirmado</option>
			                                                <option>En Transito</option>
			                                            </select>
			                                        </div>
	                                            </div>
	                                            <div class="col-lg-3">
		                                            <div class="input-group">
		                                                <span class="input-group-addon"><i class="fa fa-calendar-o"></i></span>
		                                                <input type="text" class="form-control" id="datepickerDateRange">
		                                            </div>
	                                            </div>
	                                        </div>
                                                
                                    </header>
                                
                                    <div class="main-box-body clearfix">
                                        <div class="table-responsive">
                                            <table id="table1" class="table table-striped table-hover table-travel">
                                                <thead>
                                                    <tr>
                                                        <th>Viaje</th>
                                                        <th>Ruta</th>
                                                        <th>Estado</th>
                                                        <th>Usuario</th>
                                                        <th>Avance</th>                                                       
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                
                                                    <c:forEach var="tv" items="${travels}">
                                                    
                                                        <c:set var="statustag" value="default"/>
                                                        <c:set var="statustext" value="NaN"/>
                                                        <c:choose>
	                                                        <c:when test="${tv.status == 'CRE'}">
		                                                        <c:set var="statustag" value="default"/>
		                                                        <c:set var="statustext" value="Borrador"/>
	                                                        </c:when>
	                                                        <c:when test="${tv.status == 'PRO'}">
		                                                        <c:set var="statustag" value="warning"/>
		                                                        <c:set var="statustext" value="Programado"/>
	                                                        </c:when>
	                                                        <c:when test="${tv.status == 'REV'}">
		                                                        <c:set var="statustag" value="default"/>
		                                                        <c:set var="statustext" value="Confirmado"/>
	                                                        </c:when>
	                                                        <c:when test="${tv.status == 'TRA'}">
		                                                        <c:set var="statustag" value="primary"/>
		                                                        <c:set var="statustext" value="En Tr&aacute;nsito"/>
	                                                        </c:when>
	                                                        <c:when test="${tv.status == 'CAN'}">
		                                                        <c:set var="statustag" value="danger"/>
		                                                        <c:set var="statustext" value="Cancelado"/>
	                                                        </c:when>
	                                                        <c:when test="${tv.status == 'FIN'}">
		                                                        <c:set var="statustag" value="success"/>
		                                                        <c:set var="statustext" value="Finalizado"/>
	                                                        </c:when>
                                                        </c:choose>
                                                    
                                                        <tr>
                                                            <td>
                                                                <a href="#">${tv.name}</a><br/>
                                                                <span class="created">Creado el ${tv.log_created}</span>
                                                            </td>
                                                            <td><a href="#">${tv.name_route}</a></td>
                                                            <td><span class="label label-${statustag}">${statustext}</span></td>
                                                            <td>
                                                                <span class="label label-${statustag}">Juan Perez</span><br/>
                                                                <span class="schedule"><i class="fa fa-clock-o"></i> <fmt:formatDate value="${tv.schedule}" type="date" pattern="dd/MM/yyyy"/></span>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                    </c:forEach>
                                                
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>  
                                
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- /content-wrapper -->
            
        </div>
    </div>

    <!-- global scripts -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.nanoscroller.min.js"></script>
    
    <!-- this page specific scripts -->
    <script src="js/daterangepicker.js"></script>
    <script src="js/moment.min.js"></script>
    <script src="js/select2.min.js"></script>
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/dataTables.fixedHeader.js"></script>
    <script src="js/dataTables.tableTools.js"></script>
    <script src="js/jquery.dataTables.bootstrap.js"></script>
    
    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
    <script src="js/pace.min.js"></script>
    
    <!-- this page specific inline scripts -->
    <script type="text/javascript">
        $(document).ready(function() {
            //nice select boxes
            $('#sel2').select2();
            
            //daterange picker
            $('#datepickerDateRange').daterangepicker({
            	    format: "DD/MM/YYYY",
            	    ranges: {
            	    	    'Hoy': [moment(), moment()],
            	    	    'Ayer': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
            	    	    'Ultimos 7 dias': [moment().subtract(6, 'days'), moment()],
            	    	    'Ultimos 30 dias': [moment().subtract(29, 'days'), moment()],
            	    	    'Mes Actual': [moment().startOf('month'), moment().endOf('month')]
                    },
                "startDate": moment(),
                "endDate": moment(),
                locale: {
                	    applyLabel: 'Ok',
                	    fromLabel: 'Desde',
                	    toLabel: 'Hasta',
                	    customRangeLabel: 'Personalizado',
                	    daysOfWeek: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'],
                	    monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre']
                    }
                }); // datepickerDateRange
                
            // Se inicializa por defecto la fecha del calendario
            cb( moment(), moment() );
        	
            $('#table1').dataTable({
                'info': false,
                'searching': false,
                'paging': false
                }); // table1
            
        }); // ready
    </script>
    
</body>

</html>