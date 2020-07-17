<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Proveedores - LogistikApp</title>
	
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
                                    <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                    <li class="active"><span><spring:message code="label.breadcrumb.admin"/></span></li>
                                </ol>
                                <h1><spring:message code="label.admin.supplier.list.title1"/></h1>
                            </div>
                        </div>
                        <!-- /breadcrumb -->
                        
                        <div id="supplier-box" class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">
                                	<form id="form_supplier" method="post" action="admdelsupplier.htm">
                                   		<input type="hidden" id="idsSupp" name="idsSupp"/>
                                    </form>
                                    <header class="main-box-header clearfix">
                                            <!--  
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
                                                    <button class="btn btn-primary" type="button" title="Modificar" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                        <i class="fa fa-pencil"></i>
                                                    </button>
                                                    <button class="btn btn-primary" type="button" title="Desactivar" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                        <i class="fa fa-minus"></i>
                                                    </button>
                                                    <button class="btn btn-primary" type="button" title="Eliminar" data-toggle="tooltip" data-placement="bottom" disabled="disabled"  id="eliminar" onclick="javascript:eliminar();" >
                                                        <i class="fa fa-trash-o"></i>
                                                    </button>
                                                    <button id="toolbars-refresh" class="btn btn-primary" type="button" title="Actualizar" data-toggle="tooltip" data-placement="bottom">
                                                        <i class="fa fa-refresh"></i>
                                                    </button>
                                                </div>
                                            </div>
                                            -->
                                            

                                            <!--  
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
                                                    1-10 de 14
                                                </div>
                                            </div>
                                            -->
                                    </header>
                                    
                                    <div class="main-box-body clearfix">
                                        <div class="table-responsive">
                                            <table id="table-supplier" class="table table-hover table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Nombre</th>
                                                        <th class="text-center">Código</th>
                                                        <!-- 
                                                        <th class="text-center">Orden</th>
                                                        -->
                                                        <th class="text-center">Estado</th>
                                                        <th class="text-center">Plan</th>
                                                        <th class="text-center">Termino del plan</th>
                                                        <th class="text-center">Usuario</th>
                                                        <th class="text-center">Editar</th>
                                                        <th class="text-center">Eliminar</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
	                                                 <c:forEach var="sup" items="${list}">
	                                                     <tr>
	                                                         <td>
	                                                         <!--
	                                                             <div class="checkbox-nice">
	                                                                 <input type="checkbox" id="m-checkbox-${sup.id_supplier}" value="${sup.id_supplier}"/>
	                                                                 <label for="m-checkbox-${sup.id_supplier}">${sup.name}</label>
	                                                             </div>
	                                                         -->
	                                                         ${sup.name}
	                                                         </td>
	                                                         <td class="text-center">${sup.code}</td>
	                                                         <!--  
	                                                         <td class="text-center"><span class="label label-default"><c:out value="${sup.orderby}"/></span></td>
	                                                         -->
	                                                         <td class="text-center">
	                                                             <span class="label label-${ sup.active == ACTIVE ? 'success' : 'danger' }">
	                                                                 ${ sup.active == ACTIVE ? 'Activo' : 'Inactivo' }
	                                                             </span>
	                                                         </td>
	                                                         <td class="text-center"><button tyoe="button" data-idplan="${sup.id_plan }" data-idsup="${sup.id_supplier }" class="label label-info btn-chg-plan" data-toggle="tooltip" data-placement="bottom" title="Cambiar plan">${sup.plan_name } <i class="fa fa-pencil"></i></button></td>
	                                                         <td class="text-center"><button type="button" class="label label-primary btn-add-month" data-id="${sup.id_supplier}" data-toggle="tooltip" data-placement="bottom" title="Incrementar un mes"><fmt:formatDate value="${sup.plan_end }" pattern="dd/MM/yyyy"/> <i class="fa fa-plus"></i></button></td>
	                                                         <td class="text-center">${sup.login}</td>
	                                                         <td class="text-center"><a href="admsupplierupd.htm?id=${sup.id_supplier}&accion=upd" class="badge badge-primary">Editar <i class="fa fa-pencil"></i></a></td>
	                                                         <td class="text-center"><button type="button" data-id="${sup.id_supplier}" class="badge badge-danger btn-rm">Eliminar <i class="fa fa-times"></i></button></td>
	                                                     </tr>
	                                                 </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /supplier-box -->
                    </div>
                </div>
                
	            <footer id="footer-bar" class="row">
	                <c:import url="/html/footer.html" />
	            </footer>
            </div>

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
    
    <!-- this page specific inline scripts -->
    <script>
    var table;
    $(document).ready( function() {

        $('#toolbars-new').click( function() {
        	location.href = "admsupplieradd.htm?accion=add";
        });
        
        $('#toolbars-refresh').click( function() {
        	location.reload(true);
        });
        
        table = $('#table-supplier').dataTable( {
        	search: true,
        	stateSave: false,
        	pageLength: 10,
            language: {
                url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
            }
       	});
        

        $( '#toolbars-checkall' ).on( 'click', function() {
        	var rows = table.fnGetNodes();
        	
        	if ( $(this).find("i").hasClass('fa-square-o') )
        		$('input[type="checkbox"]', rows).prop('checked', true );
        	else
        		$('input[type="checkbox"]', rows).prop('checked', false );
        	
        	$(this).find("i").toggleClass("fa-square-o fa-check-square-o")
        	
			var qty = $('input[type="checkbox"]:checked', rows).length;
    		if(qty > 0)
    			$('#eliminar').removeAttr('disabled');
    		else
    			$('#eliminar').attr('disabled', 'disabled');
        	
        	
        	} );
        
        } );
    
    	$('.btn-rm').on('click',function(){
    		var $btn = $(this)
            swal({
            	title: 'Eliminar proveedor',
            	text: 'Se eliminara toda la información correspondiente a este proveedor, ¿Desea continuar?',
            	showCancelButton: true,
            	cancelButtonText: 'No',
            	confirmButtonText: 'Si'
            }, function() {
            	document.getElementById("idsSupp").value = $btn.data('id')
        		document.getElementById("form_supplier").submit();
            })
    	})
    	
    	$("input[type=checkbox]").click(function(index,e) {
            var rows = table.fnGetNodes();
			var qty = $('input[type="checkbox"]:checked', rows).length;
    		if(qty > 0)
    			$('#eliminar').removeAttr('disabled');
    		else
    			$('#eliminar').attr('disabled', 'disabled');
        });
    	
    	$('.btn-add-month').on('click', function(){
    		var $btn = $(this)
    		swal({
    			title: 'Incrementar un mes al plan',
    			text: '¿Desea continuar?',
    			showCancelButton: true,
            	cancelButtonText: 'No',
            	confirmButtonText: 'Si',
    			closeOnConfirm: false,
    			showLoaderOnConfirm: true,
    		}, function(){
    			SupplierServiceBean.updSupplierPlan($btn.data('id'), function(res){
    				if (res > 0) {
    					swal('Exito', 'Se actualizo la fecha del plan', 'success')
	    				setTimeout(function(){
	    					location.reload()
	    				}, 5000)
    				} else {
    					swal('Error', 'No se pudo actualizar la fecha del plan intente más tarde', 'error')    					
    				}
    			})
    		})
    	})
    	
    	$('.btn-chg-plan').on('click', function(){
    		var $btn = $(this)
    		swal({   
    			title: "Obteniendo listado de planes",   
    			text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
    			html: true, 
    			showConfirmButton: false
    		});
    		
    		SupplierServiceBean.getAllPlans(function(data) {
				if (data && data.length > 0) {
					var opt = ""
					data.forEach(function(e, i) {
						if (e.id_plan != $btn.data('idplan')){
    						opt += "<option value='" + e.id_plan + "'>" + e.plan_name + "</option>"
						}
						
						swal({   
		        			title: "Seleccionar plan",   
		        			text: 'Seleccione el plan al que se transferirá <br><br><div class="row"><div class="col-xs-12"><div class="form-group"><label for="input" class="col-sm-2 control-label">Planes:</label><div class="col-sm-10"><select name="status" id="inputStatus" class="form-control" required="required">' + opt + '</select></div></div></div>',   
		        			html: true, 
		        			showCancelButton: true,
		        			cancelButtonText: 'Cancelar',
		        			confirmButtonText: 'Continuar',
		        			closeOnConfirm: false,
		        			showLoaderOnConfirm: true,
		        		}, function() {
		        			var idplan = $('#inputStatus').val()
		        			swal({   
		            			title: "Actualizando plan",   
		            			text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
		            			html: true, 
		            			showConfirmButton: false 
		            		});
		        			SupplierServiceBean.changeSupplierPlan($btn.data('idsup'), idplan, function(res) {
		        				console.log("Result", res === 1)
		        				if (res > 0) {
		        					swal('Exito', 'Se cambio el plan con exito', 'success')
		        					setTimeout(function(){
		        						location.reload()
		        					}, 5000)
		        				} else {
		        					swal('Error', 'No se cambio el plan, intente más tarde', 'error')    		        					
		        				}
		        			})
		        		});
					})
				} else {
					swal('Alerta', "Planes no obtenidos, intente más tarde", 'warning')
				}
			})
    	})

    </script>
    
</body>

</html>