<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Categor&iacute;as de Clientes - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />

	<!-- libraries -->
	<link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" href="css/libs/nanoscroller.css" type="text/css" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.fixedHeader.css">
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.tableTools.css">
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">
	
	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon"/>
	
	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>

	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
	<![endif]-->
</head>
<body class="fixed-header fixed-leftmenu theme-blue">

    <input type="hidden" id="idc" name="idc" value=""/>

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
                                     <li class="active"><span>Configuraci&oacute;n</span></li>
								</ol>
								
                                <div class="clearfix">
                                    <h1 class="pull-left"><a href="cfgstorecatlist.htm">Categor&iacute;as de Clientes</a></h1>
                       
                                </div>
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
                                                        <button id="newButton" class="btn btn-primary" type="button" title="Nuevo" data-toggle="tooltip" data-placement="bottom">
                                                            <i class="fa fa-plus"></i> <span class="hidden-xs">Nuevo</span>
                                                        </button>
                                                        <button id="refreshButton" class="btn btn-primary" type="button" title="Actualizar" data-toggle="tooltip" data-placement="bottom">
                                                            <i class="fa fa-refresh"></i>
                                                        </button>
                                                        <button id="editButton" class="btn btn-primary" type="button" title="Modificar" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                            <i class="fa fa-pencil"></i>
                                                        </button>
                                                        <button id="deleteButton" class="btn btn-primary" type="button" title="Eliminar" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                            <i class="fa fa-trash-o"></i>
                                                        </button>
                                                    </div>
                                                    
                                                    <div id="header-num-selected hidden-xs" class="btn-group num-selected" style="display:none;"><span>NaN</span></div>
                                                </div>
    
                                                <div id="email-header-pagination" class="pull-right">
                                                    <div class="btn-group pagination pull-right">
                                                        <button class="btn btn-primary" type="button" title="Anterior" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                            <i class="fa fa-chevron-left"></i>
                                                        </button>
                                                        <button class="btn btn-primary" type="button" title="Siguiente" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                            <i class="fa fa-chevron-right"></i>
                                                        </button>
                                                    </div>
                                                    
                                                    <div class="num-items pull-right hidden-xs">
                                                        1-${fn:length(list)} de ${fn:length(list)}
                                                    </div>
                                                </div>
                                       </header>

                                       <div class="main-box-body clearfix">
                                           <div class="table-responsive">
                                               <!-- list-category-retail -->
                                               <table id="table1" class="table table-striped table-hover">
												<thead>
													<tr>
														<th><span>Nombre</span></th>
														<th class="text-center"><span>C&oacute;digo</span></th>
														<th class="text-center"><span>Orden</span></th>
														<th class="text-center"><span>Estado</span></th>
														
													</tr>
												</thead>
												<tbody>
                                                    <c:if test="${fn:length(list) == 0}">
                                                        <tr><td colspan="5" class="text-center"><spring:message code="label.logistic.storecategory.list.nodata"/></td></tr>
                                                    </c:if>
                                                    
													<c:forEach var="cat" items="${list}">
														<tr>
															<td>
															<div class="checkbox-nice">
																<input type="checkbox" id="${cat.id_store_category}" value="${cat.id_store_category}"
																<c:if test="${(fn:contains(useracegi.profile, 'SUP') || fn:contains(useracegi.profile, 'DRI'))&&(useracegi.superuser == 'N') }">
																<c:out value="disabled='disabled'"/>
																</c:if>/>
	                                                            <label for="${cat.id_store_category}">${cat.name}</label>
															</div>
															</td>
															<td class="text-center"><c:out value="${cat.code}"/></td>
															<td class="text-center"><span class="label label-default"><c:out value="${cat.orderby}"/></span></td>
															<td class="text-center"><span class="label label-${cat.active == ACTIVE ? 'success' : 'danger'}"><i class="fa fa-${cat.active == ACTIVE ? 'check' : 'times'}"></i> ${cat.active == ACTIVE ? 'Activo' : 'Inactivo'}</span></td>
														</tr>
													</c:forEach>
												</tbody>
											</table><!-- /list-category-retail -->
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
	
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/dataTables.fixedHeader.js"></script>
    <script src="js/dataTables.tableTools.js"></script>
    <script src="js/jquery.dataTables.bootstrap.js"></script>
	
	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
    <script src="js/pace.min.js"></script>
		
    <!-- this page specific inline scripts -->
        <script type="text/javascript">
    $(document).ready( function() {

    	<c:if test="${(fn:contains(useracegi.profile, 'SUP') || fn:contains(useracegi.profile, 'DRI'))&&(useracegi.superuser == 'N') }">
        	$('#newButton').attr('disabled', 'disabled');
        	$('#checkAllButton').attr('disabled','disabled');
        	$('#refreshButton').attr('disabled','disabled');
        </c:if>
    	
        var table = $('#table1').dataTable( {
            'paging': false,
            'info': false,
            'searching': false,
            'pageLength': 100
            }); // table
            
            var count = table.fnGetNodes().length;
       		if(count <= 1){
        		$('#deleteButton').attr('disabled', 'disabled');
       		}
       		
        $( '#checkAllButton' ).on( 'click', function() {
            var rows = table.fnGetNodes();
            
            if ( $(this).find("i").hasClass('fa-square-o') )
                $('input[type="checkbox"]', rows).prop('checked', true );
            else
                $('input[type="checkbox"]', rows).prop('checked', false );
            
            $(this).find("i").toggleClass("fa-square-o fa-check-square-o")
            
            var qty = $('input[type="checkbox"]:checked', rows).length;
            
            if ( qty == 0 ) {
                $('#editButton').attr('disabled', 'disabled');
                $('#deleteButton').attr('disabled', 'disabled');
                $("#header-num-selected").hide();
                }
            else if ( qty > 0 ) {
                $('#deleteButton').removeAttr('disabled');
                if( qty == 1 ) {
                		$('#deleteButton').attr('disabled', 'disabled');
                        $('#editButton').removeAttr('disabled');
                        $("#header-num-selected").text( qty + " categoría seleccionada" );
                }
                else {
	                	$('#deleteButton').attr('disabled', 'disabled');
	                	$('#editButton').attr('disabled', 'disabled');
                        $("#header-num-selected").text( qty + " categorías seleccionadas" );
                }
                $("#header-num-selected").show();
                }
            
            }); // checkAllButton
        
        $('#newButton').click( function() {
            window.location.href = "storecategoryadd.htm?accion=add";
            }); // newButton
        
        $('#refreshButton').click( function() {
            location.reload(true);
            }); // refreshButton
            
          
        $('#editButton').click( function() {
            // Se obtienen todos los ids de los usuarios a modificar
        	var ids = [];
            $("input[type=checkbox]:checked").each(function(index,e) {
            	ids.push($(this).attr("value"));
               	});
                if ( ids.length > 0 )
               		location.href = "storecategoryupd.htm?id="+ids;
                }); // editButton
                
        $('#deleteButton').click( function() {
            // Se obtienen todos los ids de las plazas a eliminar
            var count = table.fnGetNodes().length;
            var ids = [];
           
            	$("input[type=checkbox]:checked").each(function(index,e) {
                	    ids.push($(this).attr("value"));
                    	});
            	if ( ids.length > 0 )
              	      location.href = "storecategorydel.htm?id="+ids;
            	
            }); // deleteButton
        
        $("input[type=checkbox]").click(function(index,e) {
            // Se controla el evento de cada click de input[type=checkbox]
            // Se obtienen todos los registros de la tabla
            var rows = table.fnGetNodes();
            var count = table.fnGetNodes().length;
       		if(count <= 1){
        		$('#deleteButton').attr('disabled', 'disabled');
       		}
            // Se obtiene la cantidad de input[type="checkbox"] == checked
            // para determinar que botones quedan habilitados o no
            var qty = $('input[type="checkbox"]:checked', rows).length;
            if ( qty == 1 ) {
                    $('#editButton').removeAttr('disabled');
                    if(count <= 1){
                		$('#deleteButton').attr('disabled', 'disabled');
               		}else{
               			$('#deleteButton').removeAttr('disabled');
               		}
                    $("#header-num-selected").text( qty + " categoría seleccionada" );
                    $("#header-num-selected").show();
                }
            else {
                if ( qty == 0 ) {
                        $('#editButton').attr('disabled', 'disabled');
                        $('#deleteButton').attr('disabled', 'disabled');
                        $("#header-num-selected").hide();
               }
                else {
                		$('#editButton').attr('disabled', 'disabled');
                		$('#deleteButton').attr('disabled', 'disabled');
                        $("#header-num-selected").text( qty + " categorías seleccionadas" );
                        $("#header-num-selected").show();
                        }
                }
            });
    	}); // ready
    
</script>
</body>
</html>