<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Categor&iacute;a de Producto - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	
	<!-- libraries -->
	<link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="css/libs/nanoscroller.css" type="text/css" />
	<link rel="stylesheet" href="css/libs/select2.css" type="text/css" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">
	
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
                                    <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                    <li><span><spring:message code="label.breadcrumb.sales"/></span></li>
                                    <li class="active"><span><a href="productlist.htm"><spring:message code="label.breadcrumb.product"/></a></span></li>
                                </ol>
									
                                <div class="clearfix">
                                    <h1 class="pull-left"><a href="categoryproductlist.htm"><spring:message code="label.sales.categoryproduct.list.title1"/></a></h1>                                   
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
                                                        <button id="newButton" onclick="location.href='categoryproductadd.htm?accion=add';" class="btn btn-primary" type="button" title="Nuevo" data-toggle="tooltip" data-placement="bottom">
                                                            <i class="fa fa-plus"></i> <span class="hidden-xs">Nuevo</span>
                                                        </button>
                                                        <button id="refreshButton" class="btn btn-primary" type="button" title="Actualizar" data-toggle="tooltip" data-placement="bottom">
                                                            <i class="fa fa-refresh"></i>
                                                        </button>
                                                        <button id="editButton"   class="btn btn-primary" type="button" title="Modificar" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                            <i class="fa fa-pencil"></i>
                                                        </button>
                                                        <button id="deleteButton" class="btn btn-primary" type="button" title="Eliminar" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                            <i class="fa fa-trash-o"></i>
                                                        </button>
                                                    </div>
                                                    <div id="header-num-selected" class="btn-group num-selected" style="display:none;"><span>NaN</span></div>
                                                </div>
                                        </header>
                                    <div class="main-box-body clearfix">
                                            <!-- list-category-product -->                                            
                                            <div class="table-responsive">
                                                <table id="table1" class="table table-striped table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th><span>Nombre</span></th>
                                                            <th class="text-center"><span>Estado</span></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                         <c:forEach var="cat" items="${list}">
                                                            <tr>
                                                                <td>
	                                                                <div class="checkbox-nice">
	                                                                    <input type="checkbox" id="${cat.id_category_product}" value="${cat.id_category_product}"/>
	                                                                    <label for="${cat.id_category_product}">${cat.name}</label>
	                                                                </div>
                                                                  <td class="text-center"><span class="badge badge-${cat.active == ACTIVE ? 'success' : 'danger'} activebtn" style="cursor:pointer;" 
	                                                            	data-id="${cat.id_category_product}" data-active="${cat.active}" data-toggle="tooltip" data-placement="bottom" title="Clic para ${cat.active==ACTIVE ? 'desactivar':'activar'}"> 
	                                                            <i class="fa fa-${cat.active == ACTIVE ? 'check' : 'times'}"></i> ${cat.active == ACTIVE ? 'Activo' : 'Inactivo'}</span></td>                                                                                                                                                                                                                                                                                                
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
	
	   <!-- Dwr script -->
    <script type='text/javascript' src='dwr/interface/FinancialServiceBean.js'></script>		
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	
    <!-- global scripts -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.nanoscroller.min.js"></script>

    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
    <script src="js/pace.min.js"></script>
	
    <!-- this page specific scripts -->
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/dataTables.fixedHeader.js"></script>
    <script src="js/dataTables.tableTools.js"></script>
    <script src="js/jquery.dataTables.bootstrap.js"></script>
	
    <!-- this page specific inline scripts -->
    <script type="text/javascript">
    
    $(document).ready( function() {
    	
        var table = $('#table1').dataTable( {
            'paging': false,
            'info': false,
            'searching': false,
            'pageLength': 100
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
                    $('#editButton').attr('disabled', 'disabled');
                    $('#deleteButton').attr('disabled', 'disabled');
                    $("#header-num-selected").hide();
                    }
                else if ( qty > 0 ) {
                    $('#deleteButton').removeAttr('disabled');
                    $("#header-num-selected").show();
    	                if( qty == 1 ) {
    	                        $('#editButton').removeAttr('disabled');
    	                        $("#header-num-selected").text( qty + " categoría seleccionada" );
    	                    }else{
    	                    	$('#editButton').attr('disabled', 'disabled');
    	                    	$("#header-num-selected").text( qty + " categorías seleccionadas" );	                    	 
    	                }	               
                    }            
                }); // checkAllButton
        
        $('#refreshButton').click( function() {
            location.reload(true);
            }); // refreshButton      
            
       	$('#editButton').click( function() {
                // Se obtienen todos los ids de las categorias a modificar
                	var ids = [];
                    $("input[type=checkbox]:checked").each(function(index,e) {
                    	ids.push($(this).attr("value"));
                       	});
                        if ( ids.length >0 )
                       		location.href = "categoryproductupd.htm?id="+ids+"&accion=upd";
              }); // editButton
            
        $('#deleteButton').click( function() {            
            // Se obtienen todos los ids de las categorias a eliminar
            	//var ids = [];
            	var id;
            	var tr;
                  $("input[type=checkbox]:checked").each(function(index,e) {
                          //ids.push($(this).attr("value"));
                          id= parseInt($(e).val());
                          tr = $(e).parent().parent().parent();
                          console.log(id);
                          });                     
                			swal({   
                				title: "Alerta",
                				text: '¿En verdad desea eliminar esta categoría?',   
                				type: "warning",   
                				showCancelButton: true,   
                				confirmButtonColor: "#DD6B55",   
                				confirmButtonText: "Aceptar",
                				cancelButtonText: 'Cancelar',
                				closeOnConfirm: false 
                			}, function(){                   				
                				if ( id > 0 ){
                					var ProductSearchCriteria = {
	                					id_category_product : id,
	                					id_supplier : "${supplier.id_supplier}"
                					};
                					FinancialServiceBean.selectProductByCriteria(ProductSearchCriteria,function(d){
                						console.log("asdasdsad");
                						console.log(d);
                						if (d.length == 0){
                							FinancialServiceBean.delCategoryProductByIdCategoryProduct(id, function(res){
                								if(res == 0){
                            						swal({
                            							title:'Error',
                            							text:'Error al eliminar categoría',
                            							type:'error',                            						
                                						showConfirmButton: true,
                                						timer: 2000
                                					});
                            						
                        						}else{
                        							//location.href = "categoryproductdel.htm?ids="+id; 
                        							var obj = {};
                                						obj.title = 'Mensaje';
                                						obj.msj = 'Categoría eliminada con éxito';
                                						obj.type = 'success'; 			
                                					
                                					swal({
                                						title: obj.title, 
                                						text: obj.msj, 
                                						type: obj.type,
                                						showConfirmButton: true,
                                						timer: 2000
                                					});
                                					
                                					$(tr).remove();
                                					
                                					return false;
                        						}
                							});
                    						
    									}else {
    										swal({
                    							title:'Error',
                    							text:'Error al eliminar categoría, contiene productos',
                    							type:'error',                            						
                        						showConfirmButton: true,
                        						timer: 2000
                        					});
    									}
    								});   
                			}
                			}); 
            /******************************************************************/
            /*var ids = [];
            $("input[type=checkbox]:checked").each(function(index,e) {
                    ids.push($(this).attr("value"));
                    });
            console.log("ids: "+ids.length);
            if ( ids.length >0){
                    location.href = "categoryproductdel.htm?ids="+ids;
            }else{
            	swal({   
    				title: "Alerta",
    				text: '!No se puede elimar categoria con productos!',   
    				type: "warning",   
    				showCancelButton: false,   
    				/*confirmButtonColor: "#DD6B55",   
    				confirmButtonText: "Aceptar",
    				cancelButtonText: 'Aceptar',
    				closeOnConfirm: false
    			})
            }*/
            	
            }); // deleteButton
            
        $("input[type=checkbox]").click(function(index,e) {
            // Se controla el evento de cada click de input[type=checkbox]
            // Se obtienen todos los registros de la tabla
            var rows = table.fnGetNodes();
            // Se obtiene la cantidad de input[type="checkbox"] == checked
            // para determinar que botones quedan habilitados o no
            var qty = $('input[type="checkbox"]:checked', rows).length;
            if ( qty == 1 ) {
                    $('#editButton').removeAttr('disabled');
                    $('#deleteButton').removeAttr('disabled');
                    $("#header-num-selected").text( qty + " categoría seleccionada" );
                    $("#header-num-selected").show();
           }else {
	                if ( qty == 0 ) {
	                        $('#editButton').attr('disabled', 'disabled');
	                        $('#deleteButton').attr('disabled', 'disabled');
	                        $("#header-num-selected").hide();
	                }else{
							$('#editButton').attr('disabled', 'disabled');
	                		$('#deleteButton').removeAttr('disabled');
	                		$("#header-num-selected").text( qty + " categorías seleccionadas" );
	                  	    $("#header-num-selected").show();
	                        }
                }
            });
    	}); // ready  
    	
    	$('.activebtn').click( function() {   
        	var btn = this;    
             	FinancialServiceBean.getCategoryProductById($(this).data('id'), function(data){ 
             		if (data != null && data != undefined){            
        			swal({   
        				title: "Alerta",
        				text: "¿En verdad desea " + ($(btn).data('active') == 'S'?'desactivar':'activar') + ' esta categoría?',   
        				type: "warning",   
        				showCancelButton: true,   
        				confirmButtonColor: "#DD6B55",   
        				confirmButtonText: "Aceptar",
        				cancelButtonText: 'Cancelar',
        				closeOnConfirm: false 
        			}, function(){   
        				var UpdCategoryProductDTO = data;
        				UpdCategoryProductDTO.active = ($(btn).data('active') == 'S'?'N':'S');
        				FinancialServiceBean.updCategoryProduct(UpdCategoryProductDTO, function(data){
        					var obj = {};
        					if (data > 0){
        						obj.title = 'Mensaje';
        						obj.msj = 'Categoría ' + ($(btn).data('active') == 'S'?'desactivada':'activada') + ' con éxito';
        						obj.type = 'success';
        						$(btn).removeClass('badge-' + ($(btn).data('active') == 'S'?'success':'danger'));
        						$(btn).addClass('badge-' + ($(btn).data('active') == 'S'?'danger':'success'));
        						$(btn).html(($(btn).data('active') == 'S'?'<i class="fa fa-times"></i> Inactivo':'<i class="fa fa-check"></i> Activo'));
        						$(btn).data('active', ($(btn).data('active') == 'S'?'N':'S'));
        					}else{
        						obj.title = 'Error';
        						obj.msj = 'Error al ' + ($(btn).data('active') == 'S'?'activar':'desactivar') + ' la categoría intente más tarde';
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
        			});
        		}
        	});
        }); //activateButton
        
    </script>	
</body>
</html>