<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    
    <title>Viajes - LogistikApp</title>
    
    <!-- bootstrap -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
    
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
                                    <h1 class="pull-left"><a href="travellist.htm">Viajes</a></h1>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">
                                
                                    <header class="main-box-header clearfix">
	                                    
	                                        <div class="row">
	                                            <div class="col-lg-9">
	                                            	<authz:authorize ifNotGranted="DRI,DRI1,DRI2,DRI3,DRI4,DRI5">
			                                            <div class="btn-group">
			                                                 <button id="newButton" class="btn btn-primary" type="button" title="Nuevo" data-toggle="tooltip">
			                                                     <i class="fa fa-plus"></i> Nuevo
			                                                 </button>
			                                            </div>
	                                            	</authz:authorize>
		                                            
		                                            <div class="btn-group">
		                                                <button id="refreshButton" class="btn btn-primary" type="button" title="Actualizar" data-toggle="tooltip">
		                                                    <i class="fa fa-refresh"></i>
		                                                </button>
		                                                <button id="geoBtn" class="btn btn-primary" type="button" title="Geolocalizar" data-toggle="tooltip" data-placement="bottom">
		                                                    <i class="fa fa-map-marker"></i>
		                                                </button>
                          	                            <authz:authorize ifNotGranted="DRI,DRI1,DRI2,DRI3,DRI4,DRI5">
			                                                <button id="copyButton" class="btn btn-primary" type="button" title="Duplicar" data-toggle="tooltip" data-placement="bottom">
			                                                    <i class="fa fa-copy"></i>
			                                                </button>
			                                                <button id="cancelButton" class="btn btn-primary" type="button" title="Cancelar" data-toggle="tooltip" data-placement="bottom">
			                                                    <i class="fa fa-times"></i>
			                                                </button>
			                                                <button id="deleteButton" class="btn btn-primary" type="button" title="Eliminar" data-toggle="tooltip" data-placement="bottom">
			                                                    <i class="fa fa-trash-o"></i>
			                                                </button>
			                                                <button id="repeatButton" class="btn btn-primary disabled" type="button" title="Repetir" data-toggle="tooltip" data-placement="bottom">
			                                                    <i class="fa fa-retweet"></i>
			                                                </button>
			                                                <a href="usersnotvisited.htm" class="btn btn-warning" type="button" title="No visitados" data-toggle="tooltip" data-placement="right"><i class="fa fa-user"></i></a>
                          	                            </authz:authorize>
		                                            </div>
	                                            </div>
	                                            <!--
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
	                                            -->
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
                                                        <!--<th>Ruta</th> -->
                                                        <th class="text-center">Estado</th>
                                                        <th>Programaci&oacute;n</th>
                                                        <th class="text-center">Inicio</th>
                                                        <th>Avance</th>
                                                        <th class="text-center">KMS</th>
                                                        <th class="text-center">HRS</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                
                                                    <c:forEach var="tv" items="${travels}">
                                                    
                                                        <c:set var="statustag" value="default"/>
                                                        <c:set var="statustext" value="NaN"/>
                                                        <c:set var="statusicon" value="NaN"/>
                                                        <c:choose>
	                                                        <c:when test="${tv.status == 'CRE'}">
		                                                        <c:set var="statustag" value="default"/>
		                                                        <c:set var="statustext" value="Creado"/>
	                                                        </c:when>
	                                                        <c:when test="${tv.status == 'PRO'}">
		                                                        <c:set var="statustag" value="warning"/>
		                                                        <c:set var="statustext" value="Programado"/>
		                                                        <c:set var="statusicon" value="clock-o"/>
	                                                        </c:when>
	                                                        <c:when test="${tv.status == 'REV'}">
		                                                        <c:set var="statustag" value="success"/>
		                                                        <c:set var="statustext" value="Confirmado"/>
		                                                        <c:set var="statusicon" value="check"/>
	                                                        </c:when>
	                                                        <c:when test="${tv.status == 'TRA'}">
		                                                        <c:set var="statustag" value="primary"/>
		                                                        <c:set var="statustext" value="En Tr&aacute;nsito"/>
		                                                        <c:set var="statusicon" value="truck"/>
	                                                        </c:when>
	                                                        <c:when test="${tv.status == 'CAN'}">
		                                                        <c:set var="statustag" value="danger"/>
		                                                        <c:set var="statustext" value="Cancelado"/>
		                                                        <c:set var="statusicon" value="times"/>
	                                                        </c:when>
	                                                        <c:when test="${tv.status == 'FIN'}">
		                                                        <c:set var="statustag" value="success"/>
		                                                        <c:set var="statustext" value="Finalizado"/>
		                                                        <c:set var="statusicon" value="check"/>
	                                                        </c:when>
                                                        </c:choose>
                                                    
                                                        <tr>
                                                            <td>
                                                                <input type="radio" name="chktv" class="radiolist" id="${tv.id_travel}" value="option${tv.id_travel}" data-status="${tv.status}">
                                                                <c:choose>
                                                                    <c:when test="${ tv.status == 'CRE' && useracegi.profile == 'DRI' }">${tv.travel}</c:when>
                                                                    <c:when test="${ tv.status == 'CRE' && useracegi.profile != 'DRI' }"><a href="travelupd.htm?idt=${tv.id_travel}&accion=upd">${tv.travel}</a></c:when>
                                                                    <c:when test="${ tv.status == 'PRO' }"><a href="travelwaybill.htm?idt=${tv.id_travel}">${tv.travel}</a></c:when>
                                                                    <c:when test="${ tv.status == 'REV'}"><a href="travelwaybill.htm?idt=${tv.id_travel}">${tv.travel}</a></c:when>
                                                                    <c:when test="${ tv.status == 'TRA' }"><a href="travelonway.htm?idt=${tv.id_travel}">${tv.travel}</a></c:when>
                                                                </c:choose>
                                                                <br/>
                                                                <span class="created">Creado el <fmt:formatDate value="${tv.created}" type="date" pattern="dd/MM/yyyy"/></span>
                                                            </td>
                                                            
                                                            <!-- <td><a href="#">${tv.route}</a></td> -->
                                                            
                                                            <td class="text-center">
                                                                <span class="label label-${statustag}">${statustext}</span>
                                                            </td>
                                                            
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${empty tv.username}"><span class="text-danger"><i class="fa fa-exclamation"></i> No hay usuario asignado</span></c:when>
                                                                    <c:when test="${not empty tv.username}"><i class="fa fa-user"></i> ${tv.username}</c:when>
                                                                </c:choose>
                                                                <br/>
                                                                <c:choose>
                                                                    <c:when test="${empty tv.schedule}">
                                                                        <span class="text-danger"><i class="fa fa-exclamation"></i> No hay fecha seleccionada</span>
                                                                    </c:when>
                                                                    <c:when test="${not empty tv.schedule && empty tv.started && tv.status == 'CRE'}">
                                                                        <span class="schedule"><span class="label label-default"><fmt:formatDate value="${tv.schedule}" type="date" pattern="dd/MM/yyyy"/></span></span>
                                                                    </c:when>
                                                                    <c:when test="${not empty tv.schedule && empty tv.started}">
                                                                        <span class="schedule"><span class="label label-warning"><fmt:formatDate value="${tv.schedule}" type="date" pattern="dd/MM/yyyy"/></span></span>
                                                                    </c:when>
                                                                    <c:when test="${not empty tv.schedule && not empty tv.started}">
                                                                        <span class="schedule"><span class="label label-warning"><fmt:formatDate value="${tv.schedule}" type="date" pattern="dd/MM/yyyy"/></span></span>
                                                                    </c:when>
                                                                </c:choose>
                                                            </td>
                                                            
                                                            <td class="text-center">
                                                                <c:if test="${not empty tv.started}">
                                                                    <span class="label label-primary"><fmt:formatDate value="${tv.started}" type="both" pattern="dd/MM HH:mm"/></span>
                                                                </c:if>
                                                            </td>
                                                            
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${tv.qtystore == 0}"><span class="text-danger"><i class="fa fa-exclamation"></i> No hay clientes seleccionados</span></c:when>
                                                                    <c:when test="${tv.qtystore > 0}">
                                                                        <i class="fa fa-map-marker"></i> ${tv.qtystore} cliente${tv.qtystore>1?'s':''}
                                                                        <c:if test="${tv.status == 'TRA' }">
	                                                                        <br/>
			                                                                <i class="fa fa-check"></i> ${tv.qtycheckin} checkin <span class="progress"><span class="label label-info"><fmt:formatNumber type="percent" value="${tv.qtycheckin/tv.qtystore}" maxFractionDigits="1"/></span></span>
			                                                                <c:if test="${tv.qtycomment > 0}"><br/><i class="fa fa-comments-o"></i> ${tv.qtycomment} comentario${tv.qtycomment>1?'s':''}</c:if>
			                                                                <c:if test="${tv.qtyimg1+tv.qtyimg2+tv.qtyimg3 > 0}"><br/><i class="fa fa-file-image-o"></i>  ${tv.qtyimg1+tv.qtyimg2+tv.qtyimg3}${tv.qtyimg1+tv.qtyimg2+tv.qtyimg3 > 1 ? ' imágenes':' imagen'}</c:if>
                                                                        </c:if>
                                                                    </c:when>
                                                                </c:choose>
                                                            </td>
                                                            
                                                            <td data-km="${tv.km }" class="item-km text-center"></td>
                                                            <td data-time="${tv.time }" class="item-time  text-center"></td>
                                                            
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
                
	            <form id="form1" name="form1" method="post">
	                <input type="hidden" id="find" name="find" value="true" />
	                <input type="hidden" id="fini" name="fini" value=""/>
	                <input type="hidden" id="ffin" name="ffin" value=""/>
	            </form>
            </div><!-- /content-wrapper -->
            
        </div>
    </div>
    
    <div class="modal fade" id="modal-reps">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-success">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">¿Repetir viaje?</h4>
				</div>
				<div class="modal-body">
					<div class="content">
						<div class="row">
							<div class="col-lg-12">
								<label for="nrepetitions">No. de repeticiones: </label>
								<input type="number" name="nrepetitions" id="nrepetitions" class="form-control" value="${viaje.repetitions == null?1:viaje.repetitions }" min="1" max="${maxdays }" step="1" placeholder="No. de repeticiones">
							</div><br>
							<div class="col-lg-12">
								<span>A repetir: </span>
								<div class="row">
									<div class="col-lg-3">
										<div class="radio">
											<input type="radio" name="nrepetitionsday" id="input" value="1" checked>
											<label for="input">Diario</label>
										</div>
									</div>
									<div class="col-lg-3">
										<div class="radio">
											<input type="radio" name="nrepetitionsday" id="input2" value="2">
											<label for="input2">Semanal</label>
										</div>
									</div>
									<div class="col-lg-3">
										<div class="radio">
											<input type="radio" name="nrepetitionsday" id="input4" value="4">
											<label for="input4">Quincenal</label>
										</div>
									</div>
									<div class="col-lg-3">
										<div class="radio">
											<input type="radio" name="nrepetitionsday" id="input3" value="3">
											<label for="input3">Mensual</label>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
					<button type="button" class="btn btn-primary" id="saveTravel">Guardar</button>
				</div>
			</div>
		</div>
	</div>

    <!-- global scripts -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.nanoscroller.min.js"></script>
    
    <!-- this page specific scripts -->
    <script src="js/moment.min.js"></script>
    <script src="js/daterangepicker.js"></script>
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
        	
        	    $('td.item-km').each(function(i, e){
        	    	    if ( $(e).data('km') >0 ) $(e).html($(e).data('km')/1000);
        	    	    }); // calculo de distancia
        	    
        	    $('td.item-time').each(function(i, e){
        	    	    if ( $(e).data('time') > 0 ) $(e).html(getTime($(e).data('time')/60));
        	    	    }); // calculo de tiempo
        	
            //nice select boxes
            $('#sel2').select2();
            
            $('#newButton').click( function() {
                location.href = 'traveladd.htm?accion=add';
                }); // newButton
                
            $('#refreshButton').click( function() {
                location.reload(true);
                }); // refreshButton
                
            $('#geoBtn').click( function() {
            	    var idt = $('input[name=chktv]:checked').attr('id');
            	    location.href = 'geotravel.htm?idts='+idt+"&volver=travellist.htm";
            	    }); // geoBtn
                
            $('#copyButton').click( function() {
            	    var idt = $('input[name=chktv]:checked').attr('id');
            	    $('#accion').val("DUP");
            	    $('#idts').val(idt);
            	    document.getElementById("formtravel").submit();
           	}); // copyButton
                
            $('#cancelButton').click( function() {
            	    var idt = $('input[name=chktv]:checked').attr('id');
            	    $('#accion').val("CAN");
            	    $('#idts').val(idt);
                document.getElementById("formtravel").submit();
                }); // cancelButton
                
            $('#deleteButton').click( function() {
            	    var idt = $('input[name=chktv]:checked').attr('id');
            	    $('#accion').val("DEL");
            	    $('#idts').val(idt);
                document.getElementById("formtravel").submit();
                }); // deleteButton
                
            function eliminar() {
                // Se obtienen todos las clientes activos para el cliente
                if ( confirm("¿Está seguro que desea borrar los viajes seleccionados?") ){
        	            document.getElementById("accion").value = "DEL";
        	            var id_array =  $("input[type=checkbox]:checked").get();
        	            var ids= [];
        	            var ids_travel = "";
        	            var sep ="";                        
        	            $("input[type=checkbox]:checked").each(function(index,e){               
        	                ids.push($(this).attr("id"));
        	                ids_travel = ids_travel + sep + $(this).attr("id");                 
        	                sep = ",";
        	                });
        	            document.getElementById("idts").value = ids_travel;
        	            
        	            if ( ids.length > 0 ) {
        	            	    document.getElementById("formtravel").action = "travelaction.htm";
        	            	    document.getElementById("formtravel").submit();
        	            	    }
        	            else 
        	            	    alert("No hay viajes seleccionados.");
        	            }
                }; // eliminar
                
            //daterange picker
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
                	"startDate": "${fini}",
                	"endDate": "${ffin}",
                	"opens": "left",
                	"buttonClasses": "btn btn-xs"
                	}, function(start, end, label) {
                		$( '#fini' ).attr( 'value', start.format("DD/MM/YYYY") );
                		$( '#ffin' ).attr( 'value', end.format("DD/MM/YYYY") );
                		$( '#form1' ).submit();
                		});
                
            // Configuración daterangepicker 
            //$('#datepickerDateRange').daterangepicker({ startDate: '${fini}', endDate: '${ffin}' });
            $('#datepickerDateRange').val( '${fini} - ${ffin}' );
                
            $('#table1').dataTable({
            	    "info": false,
            	    "searching": false,
            	    "paging": false,
            	    "language": {
            	    	    "sZeroRecords": "No hay datos"
            	    	    }
                }); // table1
                
            $('#geoBtn').prop('disabled',true);
            $('#cancelButton').prop('disabled',true);
            $('#deleteButton').prop('disabled',true);
            $('#copyButton').prop('disabled',true);
        }); // ready
        
        // metodo llamado cuando se da click en un radio
        $('input[type=radio].radiolist').click(function(){
            var status = $(this).data('status'); 
            $('#geoBtn').prop('disabled',false);
            $('#copyButton').prop('disabled',false);
            
            $('#deleteButton').prop('disabled',(!(status == 'CRE' || status == 'PRO')));
            
            if ( status == 'CRE' ) {
                $('#cancelButton').prop('disabled',true);
	        }
            if ( status != 'CRE' ) {
           	    $('#cancelButton').prop('disabled',false);
           	}
            console.log(status);
           	if(status == 'PRO'){
           		$('#repeatButton').removeClass('disabled');
           	}
        }); // click
        
        function getTime(x){
    		var hours = parseInt( x / 3600 ) % 24;
    		var minutes = parseInt( x / 60 ) % 60;
    		var seconds = x % 60;

    		return (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes);
    	}
            
        $('#tour').css({
    		display:'block',
    		cursor:'pointer'
    	});

    	$('#tour').on('click', function(){
			var placementRight = 'right';
			var placementLeft = 'left';
			var tour = {
					id: "customer-tour",
					steps: [
						{
							target: "newButton",
							title: "Nuevo",
							content: "Clic para crear un nuevo viaje",
							placement: "right",
							yOffset: -10
						},
						{
							target: "refreshButton",
							title: "Actualizar",
							content: "Clic para actualizar la pantalla",
							placement: "bottom",
							zindex: 999,
							xOffset: -15
						},
						{
							target: "geoBtn",
							title: "Geolozalizar",
							content: "Clic para geolocalizar los clientes de la ruta",
							placement: "bottom",
							zindex: 999,
							xOffset: -15
						},
						{
							target: "copyButton",
							title: "Duplicar",
							content: "Clic para duplicar el viaje seleccionado",
							placement: "bottom",
							zindex: 999,
							xOffset: -15
						},
						{
							target: "cancelButton",
							title: "Cancelar",
							content: "Clic para cancelar el viaje actual",
							placement: "bottom",
							zindex: 999,
							xOffset: -15
						},
						{
							target: "deleteButton",
							title: "Eliminar",
							content: "Clic para eliminar el viaje seleccionado",
							placement: "right",
							zindex: 999,
							yOffset: -15
						},
						{
							target: "table1",
							title: "Lista de viajes",
							content: "Aquí se muestran los viajes que hayas programado con anterioridad",
							placement: "top",
							zindex: 999
						},
						{
							target: "datepickerDateRange",
							title: "Lista de viajes",
							content: "Aquí se muestran los viajes que hayas programado con anterioridad",
							placement: "left",
							xOffset: -35,
							zindex: 999
						}
					],
					showPrevButton: true
				};
			hopscotch.startTour(tour);
		});
    	
    	$('#repeatButton').on('click', function(){
    		$('#modal-reps').modal('show');
    	});
    	
    	var max = ${maxdays};
    	$('input[name="nrepetitionsday"]').on('change', function(){
    		if (this.value == 1){
    			$('#nrepetitions').prop('max', ${maxdays});
    			max = ${maxdays};
    		}else if (this.value == 2){
    			$('#nrepetitions').prop('max', 26);
    			max = 26;
    		}else if (this.value == 3){
    			$('#nrepetitions').prop('max', 6);
    			max = 6;
    		}else if (this.value == 4){
    			$('#nrepetitions').prop('max', 13);
    			max = 13;
    		}
    	});
    	
    	$('#nrepetitions').on('change', function(){
    		if (this.value > max){
	    		console.log('nrepetitions ' + this.value + ' max ' + max);
    			this.value = max;
    		}
    	});
    	
    	$('#saveTravel').on('click', function(){
    		var idt = $('.radiolist:checked').attr('id');
    		var rep = $('#nrepetitions').val();
    		var type = $('input[name=nrepetitionsday]:checked').val();
    		var url = "travelrepeat.htm?idt=" + idt + "&rep=" + rep + "&type=" + type;
    		if (rep <= 0 || rep > max){
    			swal('Alerta', "El numero de repeticiones es incorrecto", "warning");
    		}else{
    			location.href = url;
    		}
    	});
    </script>
    
</body>

</html>