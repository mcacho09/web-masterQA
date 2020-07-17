<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Clientes - LogistikApp</title>
	
    <!-- bootstrap -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
	
    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">
	
    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.fixedHeader.css">
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.tableTools.css">
    <link rel="stylesheet" type="text/css" href="css/libs/ns-default.css"/>
    <link rel="stylesheet" type="text/css" href="css/libs/ns-style-growl.css"/>
    <link rel="stylesheet" type="text/css" href="css/libs/ns-style-bar.css"/>
    <link rel="stylesheet" type="text/css" href="css/libs/ns-style-attached.css"/>
    <link rel="stylesheet" type="text/css" href="css/libs/ns-style-other.css"/>
    <link rel="stylesheet" type="text/css" href="css/libs/ns-style-theme.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" />
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.1/css/buttons.dataTables.min.css" />
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">
    
    <link rel="stylesheet" type="text/css" href="css/libs/jquery.datetimepicker.css"/>
    <link rel="stylesheet" href="css/libs/daterangepicker.css" type="text/css" />
	
	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon"/>

	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
	
	<style>
	.html5buttons {
	    text-align: right !important;
	    float: right;
	    padding: 19px 15px;
	}
	
	.html5buttons .btn-white {
	    color: black;
	}
	
	.dataTables_wrapper .dataTables_length {
	    float: left;
	}
	
	.dataTables_wrapper .dataTables_filter {
	    float: right;
	    text-align: right;
	}
	
	#data_table_filter {
		margin-top: 20px;
	}
	
	#data_table_filter input {
	    margin-left: 10px;
	}
	
	#data_table_info {
	    float: left;
	    margin-top: 22px;
	}
	
	#data_table_length {
	    float: left;
	}
	
	#data_table_paginate {
	    float: right;
	    padding: 0;
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
            </div>
            
            <div id="content-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                    
                        <div class="row">
                            <div class="col-lg-12">
                                <ol class="breadcrumb hidden-xs">
                                    <li><a href="home.htm">Home</a></li>
                                    <li><span><a href="customer.htm">Clientes</a></span></li>
                                    <li class="active"><span><c:out value="${retail.name}"/></span></li>
                                </ol>
									
                                <div class="clearfix">
                                    <h1 class="pull-left">Listado de clientes</h1>
                                </div>
                                
                                <c:if test="${advertencia}">
                            		<div id=mensaje class="row" >
                            			<div class="col-lg-4 ">
                            				<div class="alert alert-danger">
    											<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    											<strong>Atenci&oacute;n! </strong> Ha llegado al limite de creaci&oacute;n de clientes.
  											</div>
  										</div>
                            		</div>
								</c:if>
                                
                            </div>
                        </div>
                        
	                        <div class="row">
	                            <div class="col-lg-12">
	                                <div class="main-box clearfix">
	                                	
	                                    <header class="main-box-header clearfix">
	                                        <div id="header-tools" class="pull-left">
	                                            
	                                            <div class="btn-group">
	                                                <button id="checkAllButton" class="btn btn-primary" type="button" title="Seleccionar" data-toggle="tooltip" data-placement="bottom">
	                                                    <i class="fa fa-square-o"></i>
	                                                </button>
	                                            </div>
	                                            
	                                            <div class="btn-group">
	                                                <button id="backButton" class="btn btn-primary" type="button" title="Volver" data-toggle="tooltip" data-placement="bottom">
	                                                    <i class="fa fa-arrow-left"></i> <span class="hidden-xs">Volver</span>
	                                                </button>
	                                            </div>
	                                            
	                                            <div class="btn-group">
	                                                <button id="newButton" class="btn btn-primary" type="button" title="Nuevo" data-toggle="tooltip" data-placement="bottom">
	                                                    <i class="fa fa-plus"></i> <span class="hidden-xs">Nuevo</span>
	                                                </button>
	                                                <script type="text/javascript">
	                                                /*Control de limte cliente pasado desde el controlador*/
	                                             	 var desh = ${advertencia};  	
	                                             	 document.getElementById("newButton").disabled = desh;
	                                                </script>
	                                                <button id="refreshButton" class="btn btn-primary" type="button" title="Actualizar" data-toggle="tooltip" data-placement="bottom">
	                                                    <i class="fa fa-refresh"></i>
	                                                </button>
	                                                <button id="mapButton" class="btn btn-primary" type="button" title="Geolocalizar" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
	                                                    <i class="fa fa-map-marker"></i>
	                                                </button>
	                                                <button id="editButton" class="btn btn-primary" type="button" title="Modificar" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
	                                                    <i class="fa fa-pencil"></i>
	                                                </button>
	                                                <!-- <button id="activateButton" class="btn btn-primary hidden-xs" type="button" title="Activar/Desactivar" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
	                                                    <i class="fa fa-minus"></i>
	                                                </button>-->
	                                                <button id="deleteButton" class="btn btn-primary hidden-xs" type="button" title="Eliminar" data-toggle="tooltip" data-placement="bottom"  onclick="javascript:eliminar()" disabled="disabled" >
	                                                    <i class="fa fa-trash-o"></i>
	                                                </button>
	                                                <form action="storedel.htm" method="post" id="form_delstr">
	                                                	<input type="hidden" id="ids_store" name="ids_store"/>
	                                                	<input type="hidden" id="id_retail" name="id_retail" value="${id_retail}"/>
	                                                </form>
	                                            </div>
	                                            
	                                            <div class="btn-group hidden-xs">
	                                                <a href="storeimport.htm?id=${retail.id_retail}" class="btn btn-primary" type="button" title="Importar" data-toggle="tooltip" data-placement="bottom">
	                                                    <i class="fa fa-upload"></i> <span class="hidden-xs">Importar</span>
	                                                </a>
	                                                <c:if test="${!fn:contains(useracegi.profile,'DRI') }">
	                                                <button class="btn btn-primary hidden-xs hidden-sm" data-toggle="tooltip" data-placement="bottom" title="Reporte promoción" id="repPromo">
	                                                	<i class="fa fa-download"></i> <span class="hidden-xs">Reporte promoción</span>
	                                                </button>
	                                                </c:if>
	                                            </div>
	                                            
	                                            <div id="header-num-selected" class="btn-group num-selected" style="display:none;"><span>NaN</span></div>
	                                        </div>
	                                    </header>
	                                    
	                                    <div class="main-box-body clearfix">
	                                        <div class="table-responsive">
	                                            <table id="data_table" class="table table-striped table-hover customer-list">
	                                                <thead>
	                                                    <tr>
	                                                        <th class="text-left"><span class="desc">Nombre</span></th>
	                                                        <th class="text-left"><span class="desc">Ubicaci&oacute;n</span></th>
	                                                        <c:if test="${supplier.shelf =='S'}">
	                                                        	<th class="text-center"><span class="asc">Promoción</span></th>
	                                                        </c:if>
	                                                        <th class="text-center"><span class="asc">Municipio</span></th>
	                                                        <th class="text-center"><span class="desc">Categor&iacute;a</span></th>
	                                                        <th class="text-center"><span class="desc">C&oacute;digo</span></th>
	                                                        <th class="text-center"><span class="desc">Orden</span></th>
	                                                        <th class="text-center"><span class="desc">Activo</span></th>
	                                                        <th class="text-center"><span class="desc">Activos</span></th>
	                                                        <th class="text-center"><span class="desc"># Activos</span></th>
	                                                    </tr>
	                                                </thead>
	                                                <tbody>
	                                                    <c:forEach var="sto" items="${list}" varStatus="vst">
	                                                        <tr>
	                                                            <td class="text-left">
	                                                                <div class="checkbox-nice">
	                                                                    <input type="checkbox" id="m-checkbox-${sto.id_store}" value="${sto.id_store}"/>
	                                                                    <label for="m-checkbox-${sto.id_store}"><a href="storeupd.htm?ids=${sto.id_store}&accion=upd">${sto.name}</a></label>
	                                                                </div>
	                                                            </td>
	                                                            
	                                                            <td class="text-left">
	                                                                <a href="#" onclick="javascript:geoStore(${sto.id_store});"><span class="badge badge${sto.latitude != 0 ? '-primary' : '-danger'}"><i class="fa fa-map-marker"></i> ${sto.latitude} .. ${sto.longitude}</span></a>
	                                                            </td>
	                                                            
	                                                            <c:if test="${supplier.shelf =='S'}">
		                                                            <td class="text-center">
		                                                            	<c:choose>
																		    <c:when test="${sto.shelf=='S'}">
																		        <span class="badge badge-success"><i class="fa fa-check"></i>  Si</span>
																		    </c:when>    
																		    <c:otherwise>
																		        <span class="badge badge-danger"><i class="fa fa-times"></i>  No</span>
																		    </c:otherwise>
																		</c:choose>
		                                                            </td>
	                                                            </c:if>
	                                                            
		                                                        <td class="text-center">
	                                                                <span>${sto.city}</span>
	                                                            </td>
	                                                            <td class="text-center">
	                                                                <span class="badge badge-default">${sto.category}</span>
	                                                            </td>
	                                                            <td class="text-center">
	                                                            	<span><b>${sto.code}</b></span>
	                                                            </td>
	                                                            <td class="text-center"><span class="badge badge-default"><c:out value="${sto.orderby}"/></span></td>
	                                                            <td class="text-center"><span class="badge badge-${sto.active == ACTIVE ? 'success' : 'danger'} activeButton" data-id="${sto.id_store }" data-active="${sto.active }" style="cursor:pointer;" data-toggle="tooltip" data-placement="bottom" title="Clic para ${ usr.active==ACTIVE ? 'desactivar':'activar'}"><i class="fa fa-${sto.active == ACTIVE ? 'check' : 'times'}"></i> ${sto.active == ACTIVE ? 'Activo' : 'Inactivo'}</span></td>
	                                                            <td class="text-center">
	                                                            	<a href="actives_list.htm?ids=${sto.id_store }" class="badge badge-${sto.qty_actives > 0 ? 'success': 'danger'}"><i class="fa fa-${sto.qty_actives > 0 ? 'check' : 'times'}"></i>  ${sto.qty_actives > 0 ? 'Si': 'No'}</a>
		                                                        </td>
		                                                        <td class="text-center">
		                                                        	<span><b>${sto.qty_actives}</b></span>
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
							
						</div>
					</div>

                    <footer id="footer-bar" class="row">
                        <c:import url="/html/footer.html" />
                    </footer>

				</div>
			</div>
		</div>
		
		<div class="modal fade" id="modal-id">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Reporte promoción</h4>
					</div>
					<div class="modal-body">
						<div class="form-group col-lg-12">
							<label for="dateformat">Fecha inicio</label>
							<div>
								<input id="dateformat" name="dateformat" type="hidden" value=<fmt:formatDate value="${viaje.schedule}" type="both" pattern="dd/MM/yyyy"/>>    
								<input class="form-control" id="schedule" name="schedule" type="text" placeholder="Fecha de inicio" value="" size="11px" />
							</div>
						</div>
						<div class="form-group col-lg-12">
							<label for="dateformat">Fecha final</label>
							<div>
								<input id="dateformat" name="dateformat" type="hidden" value=<fmt:formatDate value="${viaje.schedule}" type="both" pattern="dd/MM/yyyy"/>>    
								<input class="form-control" id="schedule2" name="schedule" type="text" placeholder="Fecha final" value="" size="11px" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
						<button type="button" class="btn btn-primary" id="btnDownload">Descargar</button>
					</div>
				</div>
			</div>
		</div>

	<!-- global scripts -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.nanoscroller.min.js"></script>

    <!-- this page specific scripts -->
    <script src="js/jquery.dataTables.min.js"></script>
    <script type="text/javascript"
            src="https://cdn.datatables.net/v/bs/jszip-2.5.0/dt-1.10.16/b-1.5.1/b-html5-1.5.1/b-print-1.5.1/r-2.2.1/datatables.min.js"></script>
    <script src="js/dataTables.fixedHeader.js"></script>
    <script src="js/dataTables.tableTools.js"></script>
    <script src="js/jquery.dataTables.bootstrap.min.js"></script>
    <script src="js/modernizr.custom.js"></script>
    <script src="js/snap.svg-min.js"></script> <!-- For Corner Expand and Loading circle effect only -->
    <script src="js/classie.js"></script>
    <script src="js/notificationFx.js"></script>
    
    <!-- Dwr script -->
    <script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>

    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
    <script src="js/pace.min.js"></script>
	
	<!-- datetimepicker </body> -->
    <script src="js/jquery.datetimepicker.js"></script>
    
    <!-- this page specific inline scripts -->
    <script type="text/javascript">
    $(document).ready( function() {
    	
    	$('#schedule').datetimepicker({
            lang:'es',
             i18n:{
              es:{
               months:['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
               dayOfWeek:['Dom','Lun','Mar','Mié','Jue','Vie','Sáb']
              }
             },
             timepicker:false,
             format:'d-m-Y'
    	});
    	$('#schedule2').datetimepicker({
            lang:'es',
             i18n:{
              es:{
               months:['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
               dayOfWeek:['Dom','Lun','Mar','Mié','Jue','Vie','Sáb']
              }
             },
             timepicker:false,
             format:'d-m-Y'
    	});
    	
        var table = $('#data_table').dataTable({
            bInfo: true,
            "language": {
                //"url": " //cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json",
                "infoEmpty": "Registros del _PAGE_ al _PAGES_",
                "info": "Registros del _PAGE_ al _PAGES_ de _TOTAL_",
                "emptyTable":     "Sin datos que mostrar",
                "decimal":        "",
                "infoFiltered":   "(filtered from _MAX_ total entries)",
                "infoPostFix":    "",
                "thousands":      ",",
                "lengthMenu":     "Mostrar _MENU_ registros",
                "loadingRecords": "Cargando...",
                "processing":     "Procesando...",
                "search":         "Búsqueda:",
                "zeroRecords":    "Ningun registro encontrado",
                "paginate": {
                    "first":      "Primero",
                    "last":       "Último",
                    "next":       "Siguiente",
                    "previous":   "Anterior"
                },
                "aria": {
                    "sortAscending":  ": activate to sort column ascending",
                    "sortDescending": ": activate to sort column descending"
                }
            },
            dom: '<"top"i<"html5buttons"B>f>t<"bottom"lrp>',
            buttons: [
                {
                    extend: 'copy',
                    text: 'Copiar',
                    className: 'btn-primary btn-xs',
                    exportOptions: {
                        //columns: ':not(:last-child)',
                    }
                },
                {
                    extend: 'csv',
                    title: 'Listado',
                    className: 'btn-primary btn-xs',
                    exportOptions: {
                    	//columns: ':not(:last-child)',
                    }
                },
                {
                    extend: 'excel',
                    title: 'Listado',
                    className: 'btn-primary btn-xs',
                    exportOptions: {
                    	//columns: ':not(:last-child)',
                    }
                },
                {
                    extend: 'pdf',
                    title: 'Listado',
                    className: 'btn-primary btn-xs',
                    exportOptions: {
                    	//columns: ':not(:last-child)',
                    },
                    orientation: 'landscape',
                    pageSize: 'letter'
                },

                {
                    extend: 'print', text: 'Imprimir', className: 'btn-primary btn-xs',
                    /*customize: function (win) {
                        $(win.document.body).addClass('white-bg');
                        $(win.document.body).css('font-size', '10px');

                        $(win.document.body).find('table')
                            .addClass('compact')
                            .css('font-size', 'inherit');
                    }*/
                }
            ]
        }); // table
        
        $( '#checkAllButton' ).on( 'click', function() {
            var rows = table.fnGetNodes();
            
            if ( $(this).find("i").hasClass('fa-square-o') )
                $('input[type="checkbox"]', rows).prop('checked', true );
            else
                $('input[type="checkbox"]', rows).prop('checked', false );
            
            $(this).find("i").toggleClass("fa-square-o fa-check-square-o")

            var qty = $('input[type="checkbox"]:checked', rows).length;
            if ( qty == 0 ) {
            	    $('#mapButton').attr('disabled', 'disabled');
            	    $('#editButton').attr('disabled', 'disabled');
            	    //$('#activateButton').attr('disabled', 'disabled');
            	    $('#deleteButton').attr('disabled', 'disabled');
            	    $("#header-num-selected").hide();
            	    }
            else if ( qty > 0 ) {
            	    $('#mapButton').removeAttr('disabled');
            	    $('#deleteButton').removeAttr('disabled');
            	    if( qty == 1 ) {
            	    	    $('#editButton').removeAttr('disabled');
            	    	    //$('#activateButton').removeAttr('disabled');
            	    	    $('#deleteButton').removeAttr('disabled');
            	    	    $("#header-num-selected").text( qty + " cliente seleccionado" );
            	        }
            	    else
            	        	$("#header-num-selected").text( qty + " clientes seleccionados" );
            	    
                $("#header-num-selected").show();
            	    }
            }); // checkAllButton
        
        $('#importButton').click( function() {
            window.location.href = "storeimport.htm?id=${retail.id_retail}";
            }); // importButton
            
        $('#newButton').click( function() {
            window.location.href = "storeadd.htm?idr=${retail.id_retail}&accion=add";
            }); // newButton
            
        $('#editButton').click( function() {
            var ids = [];
            $("input[type=checkbox]:checked").each(function(index,e) {
                    ids.push($(this).attr("value"));
                    });
            window.location.href = "storeupd.htm?ids="+ids+"&accion=upd";
            }); // newButton
            
        $('#backButton').click( function() {
            window.location.href = "customer.htm";
            }); // backButton
            
        $("input[type=checkbox]").click(function(index,e) {
        	    // Se controla el evento de cada click de input[type=checkbox]
        	    // Se obtienen todos los registros de la tabla
        	    var rows = table.fnGetNodes();
        	    // Se obtiene la cantidad de input[type="checkbox"] == checked
        	    // para determinar que botones quedan habilitados o no
        	    var qty = $('input[type="checkbox"]:checked', rows).length;
        	    if ( qty == 1 ) {
        	    	    $('#mapButton').removeAttr('disabled');
        	    	    $('#editButton').removeAttr('disabled');
        	    	    //$('#activateButton').removeAttr('disabled');
        	    	    $('#deleteButton').removeAttr('disabled');
        	    	    $("#header-num-selected").text( qty + " cliente seleccionado" );
        	    	    $("#header-num-selected").show();
        	        }
        	    else {
        	    	    	$('#editButton').attr('disabled', 'disabled');
        	    	    	//$('#activateButton').attr('disabled', 'disabled');
        	    	    	$('#deleteButton').attr('disabled', 'disabled');
        	    	    	    
        	    	    if ( qty == 0 ) {
    		    	    	    $('#mapButton').attr('disabled', 'disabled');
	    	    	    	    $('#deleteButton').attr('disabled', 'disabled');
        	    	    	    $("#header-num-selected").hide();
        	    	    	    }
        	    	    else {
        	    	    	    $("#header-num-selected").text( qty + " clientes seleccionados" );
        	    	    	    $("#header-num-selected").show();
        	    	    	    $('#deleteButton').removeAttr('disabled');
        	    	    	    }
        	    	    }
            });
            
        $('#refreshButton').click( function() {
            location.reload(true);
            }); // refreshButton
        
        $('#mapButton').click( function() {
        	    // Se obtienen todos los clientes seleccionados
        	    var ids = [];
        	    $("input[type=checkbox]:checked").each(function(index,e) {
        	    	    ids.push($(this).attr("value"));
        	    	    });
        	    location.href = "geolocation.htm?id=${retail.id_retail}&ids="+ids;
            }); // mapButton

        }); // ready

        function geoStore(sto) {
            location.href = "geostore.htm?ret=${retail.id_retail}&sto="+sto;
            }; // geostore
        
        function eliminar() {
            var id_array =  $("input[type=checkbox]:checked").get();
            var ids= [];
            var ids_travel = "";
            var sep ="";
            var uno = "";
            var dos = [];            
   			swal({   
				title: "Alerta",
				text: "¿Está seguro que desea borrar los clientes seleccionados?",   
				type: "warning",   
				showCancelButton: true,   
				confirmButtonColor: "#DD6B55",   
				confirmButtonText: "Aceptar",
				cancelButtonText: 'Cancelar',
				closeOnConfirm: false 
			}, function(){   
				$("input[type=checkbox]:checked").each(function(index,e){               
	                ids.push($(this).attr("id"));
	                uno = $(this).attr("id");
	                dos = uno.split("-");
	                ids_travel = ids_travel + sep + dos[2];                 
	                sep = ",";
	            });  	            
	            document.getElementById("ids_store").value = ids_travel;            
	            if (ids.length>0)
	                document.getElementById("form_delstr").submit();	            
					if (ids.length>0){
						obj.title = 'Mensaje';
						obj.msj = 'Cliente eliminado con éxito';
						obj.type = 'success';    						
					}else{
						obj.title = 'Error';
						obj.msj = 'Error al eliminar el cliente, intente más tarde';
						obj.type = 'error';
					}
					swal({
						title: obj.title, 
						text: obj.msj, 
						type: obj.type,
						showConfirmButton: true,
						timer: 2000
					});
			});   			
        };
        
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
							target: "checkAllButton",
							title: "Seleccionar todo",
							content: "Clic para seleccionar todos los clientes",
							placement: placementRight,
							yOffset: -15
						},{
							target: "backButton",
							title: "Regresar",
							content: "Clic para regresar a la pantalla anterior",
							placement: "bottom"
						},
						{
							target: 'newButton',
							title: "Nuevo",
							content: "Clic para agregar un nuevo cliente",
							placement: "bottom",
							zindex: 999						
						},
						{
							target: 'refreshButton',
							title: "Actualizar",
							content: "Clic para actualizar la pantalla actual",
							placement: "bottom",
							zindex: 999,
							xOffset: -15
						},
						{
							target: 'mapButton',
							title: "Geolocalizar",
							content: "Al tener seleccionado un o más clientes, podras verlo localizados en un mapa",
							placement: "bottom",
							zindex: 999,
							xOffset: -15
						},{
							target: "editButton",
							title: "Modificar",
							content: "Clic para modificar un cliente seleccionado",
							placement: "bottom",
							xOffset: -15
						}/*,{
							target: "activateButton",
							title: "Activar/Desactivar",
							content: "Clic para activar o desactivar un cliente",
							placement: "bottom",
							xOffset: -15
						}*/,{
							target: "deleteButton",
							title: "Eliminar",
							content: "Clic para eliminar un cliente seleccionado",
							placement: "bottom",
							xOffset: -15
						},{
							target: "importButton",
							title: "Importar",
							content: "Clic para importar un listado de clientes",
							placement: "bottom",
							xOffset: -15
						},
						{
							target: 'table-store',
							title: "Tabla de clientes",
							content: "Selecciona cliente para realizar alguna de las anteriores opciones.",
							placement: "top",
							zindex: 999,
							yOffset: -15
						}
					],
					showPrevButton: true
				};
			hopscotch.startTour(tour);
		});
        
        $('.activeButton').on('click', function(){
        	var active = $(this).data('active') == "S";
        	var Store = new Object();
        	var id = $(this).data('id');
        	Store.id_store = $(this).data('id');
        	Store.active = (active?"N":"S");
        	var btn = this;
        	swal({   
        		title: "Alerta",   
        		text: "Va a " + (active?"bloquear":"desbloquear" + " el cliente seleccionado.\n¿Deseá continuar?"),   
        		type: "warning",   
        		showCancelButton: true,   
        		confirmButtonColor: "#DD6B55",   
        		confirmButtonText: "Si, continuar",   
        		closeOnConfirm: false 
        	}, function(){   
        		RetailServiceBean.updStore(Store, function(data){
        			if (data >= 1){
        				swal('Exito',"Se " + (active?'desactivo':'activo') + " el cliente correctamente","success");
        				$(btn).removeClass('badge-' + ($(btn).data('active') == 'S'?'success':'danger'));
						$(btn).addClass('badge-' + ($(btn).data('active') == 'S'?'danger':'success'));
						$(btn).html(($(btn).data('active') == 'S'?'<i class="fa fa-times"></i> Inactivo':'<i class="fa fa-check"></i> Activo'));
						$(btn).data('active', ($(btn).data('active') == 'S'?'N':'S'));
        			}else{
        				swal('Error',"No se " + (active?'desactivo':'activo') + " el cliente correctamente","error");
        			}
        		})
        	});
        });
        
        $('#repPromo').on('click', function(){
        	$('#modal-id').modal('show');
        });
        
        $('#btnDownload').on('click', function(){
        	if ($('#schedule').val().length == 0){
        		swal('Alerta','Seleccione la fecha de inicio','warning');
        	}else if($('#schedule2').val().length == 0){
        		swal('Alerta','Seleccione la fecha final','warning');
        	}else {
        		var fini = $('#schedule').val();
        		var fini = fini.split('-')[2] + "-" + fini.split('-')[1] + "-" + fini.split('-')[0];
        		var ffin = $('#schedule2').val();
        		var ffin = ffin.split('-')[2] + "-" + ffin.split('-')[1] + "-" + ffin.split('-')[0];
        		var id = ${retail.id_retail};
        		var url = 'reppromotionvisited.htm?idr=' + id + "&fini=" + fini + "&ffin=" + ffin;
        		console.log(url);
        		location.href = url;
        	}
        });
        
    </script>

</body>
</html>
