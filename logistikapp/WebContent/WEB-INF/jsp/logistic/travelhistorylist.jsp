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
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.fixedHeader.css">
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.tableTools.css">
    <link rel="stylesheet" href="css/libs/daterangepicker.css" type="text/css" />
    <link rel="stylesheet" type="text/css" href="css/libs/select2.css" />

    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">

    <!-- Favicon -->
    <link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
    
    <!-- Dwr script--> 
    <script type='text/javascript' src='dwr/interface/LogisticServiceBean.js'></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>
    
    <!-- google font libraries -->
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
    
    <!--[if lt IE 9]>
            <script src="js/html5shiv.js"></script>
            <script src="js/respond.min.js"></script>
        <![endif]-->
        
   <style>
    .btn-custom-circle {
        padding: 7px 10.3px;
        border-bottom: 0px solid;
        border-radius: 50% !important;
        font-size: 15px;
        line-height: 0;
    }
   </style>
</head>

<body class="fixed-header fixed-leftmenu theme-blue">

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
					<form action="travelaction.htm" method="post" id="formtravel" name="formtravel">
						<input type="hidden" id="idts" name="idts" value=""/>
						<input type="hidden" id="accion" name="accion" value="">
					</form>
					<div class="row">
						<div class="col-lg-12">

							<div class="row">
								<div class="col-lg-12">
								
									<ol class="breadcrumb hidden-xs">
										<li><a href="home.htm">Home</a></li>
										<li class="active"><span>Log&iacute;stica</span></li>
									</ol>
									<div class="clearfix">
										<h1 class="pull-left"><a href="travelhistorylist.htm"><spring:message code="label.breadcrumb.logistic.travel.history"/></a></h1>
									</div>
									
								</div>
							</div>

							<div class="row">
								<div class="col-lg-12">
									<div class="main-box clearfix">
										
										<header class="main-box-header clearfix">

											<div id="header-tools" class="pull-left">
												<form name="form1" id ="form1" method="GET">
													<input type="hidden" id="find" name="find" value="true" />
													<input type="hidden" id="fini" name="fini" value=""/>
													<input type="hidden" id="ffin" name="ffin" value=""/>
												</form>
												<authz:authorize ifNotGranted="DRI,DRI1,DRI2,DRI3,DRI4,DRI5">
													<div class="btn-group">
														<button id="checkAllButton" class="btn btn-primary" type="button" title="Seleccionar Todos" data-toggle="tooltip" data-placement="bottom">
															<i class="fa fa-square-o"></i>
														</button>
														<button id="toolbars-new" class="btn btn-primary" type="button" title="Nuevo" data-toggle="tooltip" onclick="location.href='traveladd.htm?accion=add'">
															<i class="fa fa-plus"></i> <span class="hidden-xs">Nuevo</span>
														</button>
													</div>
												</authz:authorize>
												<div class="btn-group">
													<button id="toolbars-refresh" class="btn btn-primary" type="button" title="Actualizar" data-toggle="tooltip" onclick="location.href='travelhistorylist.htm'">
														<i class="fa fa-refresh"></i>
													</button>
													<authz:authorize ifNotGranted="DRI,DRI1,DRI2,DRI3,DRI4,DRI5">
														<button class="btn btn-primary" type="button" title="Duplicar" data-toggle="tooltip" data-placement="bottom" onclick="javascript:duplicar()" id="btnDup" disabled>
															<i class="fa fa-copy"></i>
														</button>
													</authz:authorize>
													<button class="btn btn-primary" type="button" title="Geolocalizar" data-toggle="tooltip" data-placement="bottom" onclick="javascript:geolocalizar()" id="btnMap" disabled>
														<i class="fa fa-map-marker"></i>
													</button>
													<button id="downloadreports" class="btn btn-primary" type="button" title="Descargar" data-toggle="tooltip" data-placement="bottom" disabled style="display: none">
															<i class="fa fa-download"></i> Descargar Reportes
													</button>
												</div>
											</div>
											<!-- filtro fecha -->
											<div class="col-lg-3 pull-right">
												<div class="input-group">
													<span class="input-group-addon"><i class="fa fa-calendar-o"></i></span>
													<input type="text" class="form-control" id="reportrange" readonly >
												</div>
											</div>
											
										</header>
										<div class="main-box-body clearfix">

											<div class="table-responsive">
												<table id="table-example" class="table table-striped table-hover table-travel">
													<thead>
														<tr>
															<th>Viaje</th>
															<th>Estado</th>
															<th>Usuario</th>
															<th>Inicio</th>
															<th>T&eacute;rmino</th>
															<th>Avance</th>
															<th>KMS</th>
															<th>HRS</th>
															<c:if test="${fn:contains(useracegi.profile,'DRI') == false}">
															<th>Reporte</th>
															</c:if>    
														</tr>
													</thead>
													<tbody>
														<c:forEach var="tv" items="${list}">
															<c:set var="statustag" value="default"/>
															<c:set var="statustext" value="NaN"/>
															<c:set var="statusicon" value="NaN"/>
															<c:choose>
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
																<input type="checkbox" name="chktv" id="${tv.id_travel}" value="option${tv.id_travel}" data-status="${tv.status}">
																<a href="travelreport.htm?idt=${tv.id_travel}"><c:out value="${tv.travel}"/></a>
																	<br/>
																	<span class="created">Creado el <fmt:formatDate value="${tv.created}" type="date" pattern="dd/MM/yyyy"/></span>
																</td>
																<td><span class="label label-${statustag}">${statustext}</span></td>
																<td>${tv.username}</td>
																<td><c:if test="${not empty tv.started}"><fmt:formatDate value="${tv.started}" type="both" pattern="dd/MM HH:mm"/></c:if></td>
																<td><c:if test="${not empty tv.finished}"><fmt:formatDate value="${tv.finished}" type="both" pattern="dd/MM HH:mm"/></c:if></td>
																<td>
																	<c:choose>
																		<c:when test="${tv.qtystore > 0}">
																			<i class="fa fa-map-marker"></i> ${tv.qtystore} cliente${tv.qtystore>1?'s':''}
																			<br/>
																			<i class="fa fa-check"></i> ${tv.qtycheckin} checkin <span class="progress"><span class="label label-info"><fmt:formatNumber type="percent" value="${tv.qtycheckin/tv.qtystore}" maxFractionDigits="1"/></span></span><br/>
																			<c:if test="${tv.qtycomment > 0}"><i class="fa fa-comments-o"></i> ${tv.qtycomment} comentario${tv.qtycomment>1?'s':''}<br/></c:if>
																			<c:if test="${tv.qtyimg1+tv.qtyimg2+tv.qtyimg3 > 0}"><i class="fa fa-file-image-o"></i>  ${tv.qtyimg1+tv.qtyimg2+tv.qtyimg3}${tv.qtyimg1+tv.qtyimg2+tv.qtyimg3 > 1 ? ' imágenes':' imagen'}</c:if>
																		</c:when>
																	</c:choose>
																</td>
																<td data-km="${tv.km }" class="item-km"></td>
																<td data-time="${tv.time }" class="item-time"></td>
																<c:if test="${fn:contains(useracegi.profile,'DRI') == false}">
																<td>
																	<button class="btn-report btn btn-xs btn-custom-circle btn-primary" data-idt="${tv.id_travel }"><i class="fa fa-download"></i></button>
																</td>
																</c:if>
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
    
    <script src="js/moment.min.js"></script>
    <script src="js/daterangepicker.js"></script>
    
    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
    <script src="js/pace.min.js"></script>
    
    <!-- this page specific inline scripts -->
    <script type="text/javascript">
        var dto = new Object();
        var status = [];        
        var ids;
        var startString;
        var endString;
        var startDate;                
        var endDate;              
        var endMilis;
        var fini;
        var ffin;
        $(document).ready(function() {
            
            $('td.item-km').each(function(i, e){
                if ( $(e).data('km') >0 ) $(e).html($(e).data('km')/1000);
            });
            
            $('td.item-time').each(function(i, e){
                if ( $(e).data('time') > 0 ) $(e).html(getTime($(e).data('time')/60));
            });
            
             function cb(start, end) {
                 $('#reportrange span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
             }
            fini = "${fini}";
            ffin = "${ffin}";
    
            cb(moment().startOf('month'), moment().endOf('month'));
    
            $('#reportrange').daterangepicker({
            	 "format": "DD/MM/YYYY",
         	    "ranges": {
         	     'Hoy': [moment(), moment()],
                 'Ayer': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                 'Mañana': [moment().add(1, 'days'), moment().add(1, 'days')],
                 'Últimos 3 dias': [moment().subtract(3, 'days'), moment()],
                 'Próximos 3 dias': [moment().add(1, 'days'),moment().add(3, 'days')],
                 'Semana actual': [moment().startOf('week'), moment().endOf('week')],
                 'Semana pasada': [moment().subtract(1, 'week').startOf('week'), moment().subtract(1, 'week').endOf('week')],
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
            }
            , cb);
            $('#table-example').dataTable({
                "language": {
                    "search": "Buscar:",
                    "lengthMenu": "Mostrar _MENU_ registros",
                    "info": "</BR>Mostrando pagina _PAGE_ de _PAGES_",
                    "zeroRecords": "No hay datos"
                 }
            });           
            $('#reportrange').val( '${fini} - ${ffin}' );
    
        });
    
        function duplicar() {
            idTravel = $('input[type=checkbox]:checked').attr("id");
            
            document.getElementById("accion").value = "DUP";
            document.getElementById("idts").value = idTravel;            
            if ($('input[type=checkbox]:checked').length>0)
                document.getElementById("formtravel").submit();
            
        }
        function geolocalizar() {
            var idTravel;
            if($('input[type=checkbox]:checked').attr("id").length > 0){
                idTravel = $('input[type=checkbox]:checked').attr("id");
                location.href = 'geotravel.htm?idts='+idTravel+'&volver=travelhistorylist.htm';
            }
        }
        function getTime(x){
            var hours = parseInt( x / 3600 ) % 24;
            var minutes = parseInt( x / 60 ) % 60;
            var seconds = x % 60;

            return (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes);
        }
        
        
        $('.btn-report').on('click', function(){
            location.href = "travelreportfromhistorico.htm?history=true&idt=" + $(this).data('idt')+"&dol=${useracegi.profile}";
        });
        
        
        $('#checkAllButton').click(function(){
            var id;
            $(this).find("i").toggleClass("fa-square-o fa-check-square-o");
            // fa-square-o check vacio
            if ( $(this).find("i").hasClass('fa-square-o') ){ 
                $("input[type=checkbox]").each(function() {
                    id = $(this).attr('id');
                    $('#'+id).prop('checked',false);
                    $(this).trigger("change");
                    $('#downloadreports').prop('disabled',true);

                });
            }else{
                $("input[type=checkbox]").each(function() {
                    id = $(this).attr('id');
                    $('#'+id).prop('checked',true);
                    $(this).trigger("change");
                    $('#downloadreports').prop('disabled',false);
                });
            }
        });
        
        $("input[type=checkbox]").change(function(){
            //verifica si hay solo 1 checkbox seleccionado para habilitar o des habilitar boton borrar
            var sel = $('input[type=checkbox]:checked').size();
            if(sel == 1){ 
                $('#downloadreports').prop('disabled',true);
                document.getElementById("downloadreports").style.display= "none";
                $('#btnMap').prop('disabled',false);
                $('#btnDup').prop('disabled',false);
            }else{
                $('#downloadreports').prop('disabled',false);
                document.getElementById("downloadreports").style.display= "";
                $('#btnMap').prop('disabled',true);
                $('#btnDup').prop('disabled',true);
            }
            
            if(sel == 0){
                document.getElementById("downloadreports").style.display= "none";
            }
            if (sel < $('input[type=checkbox]').size()){
                $($('#checkAllButton').find('i')[0]).addClass("fa-square-o");
                $($('#checkAllButton').find('i')[0]).removeClass("fa-check-square-o");
    		}else if (sel == $('input[type=checkbox]').size()){
    			$($('#checkAllButton').find('i')[0]).removeClass("fa-square-o");
            	$($('#checkAllButton').find('i')[0]).addClass("fa-check-square-o");
    		}
        });
        
        $('#downloadreports').click( function() {
            // Se obtienen todos los clientes seleccionados
            var ids = [];
            $("input[type=checkbox]:checked").each(function(index,e) {
                ids.push(this.id);
			});
            //alert(ids.size())
            location.href = "travelreportfromhistorico.htm?history=true&idt=" + ids+"&dol=${useracegi.profile}";
        }); // mapButton
        
        
        //Implementación descarga multilple
        
    </script>
    
</body>

</html>