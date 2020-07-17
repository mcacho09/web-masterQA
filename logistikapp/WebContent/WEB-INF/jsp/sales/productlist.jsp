<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Productos - LogistikApp</title>
	
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
                                        <li class="active"><span><spring:message code="label.breadcrumb.sales"/></span></li>
									</ol>
									
									<div class="clearfix">
										<h1 class="pull-left"><a href="productlist.htm"><spring:message code="label.sales.product.list.title1"/></a></h1>                                      
									</div>
								</div>
							</div>

							<div class="row">													
							
								<div class="col-lg-12">
								
									<div class="main-box clearfix">							
													
                                        <header class="main-box-header clearfix">
                                        	
                                               <div id="header-tools" class="pull-left">                                
		                                            <c:if test="${fn:contains(useracegi.profile, 'DRI') == false}"> 
		                                            <div class="btn-group">
		                                                <button id="checkAllButton" class="btn btn-primary" type="button" title="Seleccionar" data-toggle="tooltip" data-placement="bottom">
		                                                    <i class="fa fa-square-o"></i>
		                                                </button>
		                                            </div>	 
		                                            </c:if>                                           
		                                                <div class="btn-group">
			                                                <c:if test="${fn:contains(useracegi.profile, 'DRI') == false}">
		                                                        <button id="newButton" onclick="location.href='productadd.htm?accion=add';" class="btn btn-primary" type="button" title="Nuevo" data-toggle="tooltip" data-placement="bottom">
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
		                                                    </c:if>
	                                                    </div>    	  
	                                                    <div id="header-num-selected" class="btn-group num-selected" style="display:none;"><span>NaN</span></div>                         
		                                            <!-- <div class="btn-group hidden-xs">
		                                                <button id="importButton" class="btn btn-primary" type="button" title="Importar" data-toggle="tooltip" data-placement="bottom">
		                                                    <i class="fa fa-upload"></i> <span class="hidden-xs">Importar</span>
		                                                </button>
		                                                <button class="btn btn-primary hidden-xs hidden-sm" data-toggle="tooltip" data-placement="bottom" title="Reporte clientes con promoción" id="repPromo">
		                                                	<i class="fa fa-download"></i> <span class="hidden-xs">Descargar</span>
		                                                </button>
		                                            </div>	-->                                            
		                                            <div id="header-num-selected" class="btn-group num-selected" style="display:none;"><span>NaN</span></div>
		                                        </div>
                                        </header>
                                        
										<div class="main-box-body clearfix">
											<div class="table-responsive">
												<form method="post" id="form_1" >
													<input type="hidden" name="orderby" id="orderby" />
												</form>
												<!-- list-product -->
												<div class="table-responsive">
                                                <table id="table1" class="table table-striped table-hover">
                                                    <thead>
                                                       <tr>
															<th class="text-center"><span>C&oacute;digo</span></th>												
															<th class="text-center"><span>Producto</span></th>
															<th><span>Imagen</span></th>
															<c:if test="${fn:contains(useracegi.profile, 'DRI') == false}">
															<th class="text-center"><span>Impuesto</span></th>		
															</c:if>	
															<c:if test="${fn:contains(useracegi.profile, 'DRI') == false}">
															<th class="text-center"><span>Costo</span></th>		
															</c:if>										
															<c:if test="${fn:contains(useracegi.profile, 'DRI') == false}">
															<th class="text-center"><span>Costo Caja</span></th>		
															</c:if>										
															<th class="text-center"><span>$ Pieza</span></th>
															<th class="text-center"><span>$ Caja</span></th>
															<th class="text-center"><span># Piezas</span></th>
															<th class="text-center"><span>Tipo</span></th>
															<th class="text-center"><span>Estado</span></th>														
														</tr>
                                                    </thead>
													<tbody>
                                                         <c:forEach var="pro" items="${list}">
                                                            <tr>
                                                                <td>
	                                                                <c:if test="${!fn:contains(useracegi.profile, 'DRI')}">      	                                                             
		                                                                <div class="checkbox-nice">
		                                                                	<input type="checkbox" id="${pro.id_product}" value="${pro.id_product}"/>
		                                                                	<label for="${pro.id_product}">${pro.code}</label>
		                                                                </div>    
		                                                            </c:if> 
		                                                            <c:if test="${fn:contains(useracegi.profile, 'DRI')}">
		                                                            	${pro.code }
		                                                            </c:if>                                                 
	                                                            </td>  
	                                                            <td width="20%">
	                                                            	<c:if test="${pro.flag == ACTIVE}"><span class="glyphicon glyphicon-flag" style="color:${pro.flag == ACTIVE ? 'red' : ''}"></span></c:if>
                                                                    <strong><span class="product-link">${pro.name_short}</span></strong>
                                                                    <br />
                                                                    <small><b>Categor&iacute;a:</b> ${pro.category}</small>
                                                                    <br />
                                                                    <small><b>Marca:</b> ${pro.brand }</small>
                                                                    <br />
                                                                    <div class="product-subhead" align="justify">
                                                                   		<div class="comentario">${pro.name_long}</div>
                                                                   	</div>
                                                                </td>                                                                
                                                                <td>                                                                                                                                                                                      
	                                                                <img src="${pro.image == '' || pro.image == null ? 'img/img_default.jpg' : pro.image}"  width="50" height="50" 
	                                                                 <c:if test="${fn:contains(useracegi.profile, 'DRI') == false}">
	                                                                	class="image" id="imagenes" name="imagenes" data-id="${pro.id_product}" data-name="${pro.name_short}" data-toggle="tooltip" style="cursor:pointer; data-placement="bottom" title="Clic para cambiar imagen"
	                                                                 </c:if>
	                                                                />                                                                                                                                                    
                                                                </td>	
                                                                
                                                                <c:if test="${fn:contains(useracegi.profile, 'DRI') == false}">		
	                                                                <td class="text-center">
																		<c:if test="${ pro.tax == 0 }">
																			<span class="label label-info">NA</span>
																		</c:if>
																		<c:if test="${ pro.tax == 1 }">
																			<span class="label label-primary">IVA</span>
																		</c:if>
																		<c:if test="${ pro.tax == 2 }">
																			<span class="label label-primary">IEPS</span>
																		</c:if>
																		<c:if test="${ pro.tax == 3 }">
																			<span class="label label-primary">IEPS e IVA</span>
																		</c:if>
																	</td>	
																</c:if>	
																							
															    <c:if test="${fn:contains(useracegi.profile, 'DRI') == false}">			    
																<td><fmt:formatNumber type="currency" currencySymbol="$" value="${pro.price_cost}"/></td>
																</c:if>																	    														
															    <c:if test="${fn:contains(useracegi.profile, 'DRI') == false}">			    
																<td><fmt:formatNumber type="currency" currencySymbol="$" value="${pro.price_cost_box}"/></td>
																</c:if>																	    														
																<td class="text-center"><fmt:formatNumber type="currency" currencySymbol="$" value="${pro.price_sale}"/></td>
																<td class="text-center"><fmt:formatNumber type="currency" currencySymbol="$" value="${pro.price_sale_box}"/></td>
																<td class="text-center">${pro.piece_in_box }</td>
																<td class="text-center">
																	<c:if test="${pro.type == 'PCS' }">
																	<span class="label label-info">Pieza</span>
																	</c:if>
																	<c:if test="${pro.type == 'PKG' }">
																	<span class="label label-primary">CAJA</span>
																	</c:if>
																</td>
																<td class="text-center">
																	<span class="badge badge-${pro.active == ACTIVE ? 'success' : 'danger'}
	                                                            	<c:if test="${fn:contains(useracegi.profile, 'DRI') == false}">	
	           														activebtn" style="cursor:pointer;" data-id="${pro.id_product}" data-active="${pro.active}" data-toggle="tooltip" data-placement="bottom" title="Clic para ${pro.active==ACTIVE ? 'desactivar':'activar'}">
	                                                            	 </c:if>
	                                                                <i class="fa fa-${pro.active == ACTIVE ? 'check' : 'times'}"></i>${pro.active == ACTIVE ? 'Activo' : 'Inactivo'}
	                                                                </span>
	                                                            </td>  																                                                                                                                                                                                                                                                                                                                                                                                                                    
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div><!-- /Table -->
                                            <!--  <div id="email-header-pagination" class="pull-right">
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
                                                </div>--> 
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
		
				<div class="modal fade" id="modal-image">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header bg-primary">								
								<h4 class="modal-title" id="modal-title"></h4>
							</div>
							<div class="modal-body">					
								<div class="row" style="text-align:center">
								
								   <div class="col-lg-6 col-lg-offset-3 imgcontent" style="padding:0!important;">
										
										<img class="img-responsive" id="imgCont1" src="img/img_default.jpg" style="width:200%; height:250px;">
									
										<div class="contedit">
											<div class="design" >
												<label type="button" class="btn btn-primary"><label class="fa fa-arrow-up fa-lg"></label>Subir imagen</label>
											</div>
											<input type="file" class="hidden" accept="image/*" name="image" id="img1">
										</div>
									</div>
								
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" id="closemodal" data-dismiss="modal">Cerrar</button>								
								<button type="button" class="btn btn-primary" onclick="saveImage();" id="saveimage">Guardar</button>							
							</div>
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
    <script src="js/jquery.expander.js"></script>
	
    <!-- this page specific inline scripts -->
    <script type="text/javascript"> 
    var actual_product = 0;
    var img_actual = "";
    var img = null;
    
    $('.image').on('click', function(){
    	$('#modal-image').modal('show');
    	actual_product = $(this).data('id');
        $('#modal-title').text($(this).data('name'));        
        $('#imgCont1').attr('src', this.src);
        img = this;
    });
    
    function saveImage(){    
    	  	var UpdProductDTO = new Object();   
			UpdProductDTO.id_product = actual_product;
			UpdProductDTO.image = img_actual;
      		FinancialServiceBean.updProduct(UpdProductDTO, function(dato){	
 				if (dato >= 1){
 					swal('Éxito', "Se agregó la imagen con éxito", "success");
 					img.src = img_actual;
 					$('#closemodal').click();
 				}else{
 					swal("Error", "No se logró agregar la imagen\nIntente más tarde", "error");
 				}
            });
    }
    
    $('.design').on('click', function(){
		var file = $($(this).parent().find('input[type=file]'));
		$(file).click();
	});
	
	$('input[type=file]').on('change', function(){
		var file = this;
		var img = $(this).parent().parent().find('img')[0];
	
    		if (file.files && file.files[0]){
    			var fr = new FileReader();
    			fr.onload = function(e){
    				img.src = e.target.result;
    				img_actual = e.target.result;
    			}
	    		fr.readAsDataURL(file.files[0]);
    		}
	});
    
    
    $(document).ready( function() {
    	
    	$('div.comentario').expander({
            slicePoint:40, 
            expandText: '[leer más]', 
            collapseTimer: 5000, 
            userCollapseText: '[-]' 
          });
    	
        var table = $('#table1').dataTable( {
            'paging': true,
            'pageLength' : 15,
            'lengthChange' : false,
            'info': false,
            'searching': true,
            "language": {
                "search": "Buscar Producto:",
                "zeroRecords":    "No se encontraron productos"
            },
            'pageLength': 100,
            'order': [[ 8, "asc" ]]
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
	                        $("#header-num-selected").text( qty + " producto seleccionado" );
	                    }else{
	                    	$('#editButton').attr('disabled', 'disabled');
	                    	$("#header-num-selected").text( qty + " productos seleccionados" );	                    	 
	                }	               
                }            
            }); // checkAllButton
            
       $('#importButton').click( function() {
         	  location.href = "productimport.htm"; //?id="+ids;
            }); // importButton
        
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
                       		location.href = "productupd.htm?id="+ids+"&accion=upd";
              }); // editButton
            
              $('#deleteButton').click( function() {            
                  // Se obtienen todos los ids de las categorias a eliminar
                var ids = [];
                var id = [];
                $("input[type=checkbox]:checked").each(function(index,e) {
                	ids.push(e.value);
                  	id.push(parseInt(e.value));
                });
                swal({
                	title: "Alert",
                	text:'Verificando productos<br><div class="progress"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%"><span class="sr-only"></span></div></div>', 
                	html: true
                })
                
                FinancialServiceBean.getProductsUsedsInOrderDetail({id_supplier: ${useraccess.id_supplier}, ids: id}, function(data){
                	if (data.length == 0) {
                		swal({   
                        	title: "Alerta",
                        	text: '¿En verdad desea eliminar?',   
                        	type: "warning",   
                        	showCancelButton: true,   
                        	confirmButtonColor: "#DD6B55",   
                        	confirmButtonText: "Aceptar",
                        	cancelButtonText: 'Cancelar',
                        	closeOnConfirm: false 
                        }, function(){
                        	location.href = "productdel.htm?ids="+ids; 
                        })
                	} else {
                		var tmpids = $(id).not(data).get()
                		console.log(tmpids)
                		// 1, 2, 3, 4, 5, 6 not 1, 4 => 2, 3, 5, 6
                		if (tmpids.length == 0 ) {
                			swal("Alerta", "Los productos seleccionados estan relacionados a algunas transacciones", "warning");
                		} else  {
                			swal({   
                            	title: "Alerta",
                            	text: 'Algunos productos no seran eliminados por estar relacionados a algunas transacciones\n¿Desea continuar?',   
                            	type: "warning",   
                            	showCancelButton: true,   
                            	confirmButtonColor: "#DD6B55",   
                            	confirmButtonText: "Aceptar",
                            	cancelButtonText: 'Cancelar',
                            	closeOnConfirm: false 
                            }, function(){
                            	location.href = "productdel.htm?ids="+ids; 
                            })
                		}
                	}
                })
                      
            });// deleteButton
            
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
                    $("#header-num-selected").text( qty + " producto seleccionado" );
                    $("#header-num-selected").show();
           }else {
	                if ( qty == 0 ) {
	                        $('#editButton').attr('disabled', 'disabled');
	                        $('#deleteButton').attr('disabled', 'disabled');
	                        $("#header-num-selected").hide();
	                }else{
							$('#editButton').attr('disabled', 'disabled');
	                		$('#deleteButton').removeAttr('disabled');
	                		$("#header-num-selected").text( qty + " productos seleccionados" );
	                  	    $("#header-num-selected").show();
	                        }
                }
            });
    	}); // ready  
    	
    	$('.activebtn').click( function() {   
        	var btn = this;    
        	FinancialServiceBean.getProductById($(this).data('id'), function(data){ 
             		if (data != null && data != undefined){            
        			swal({   
        				title: "Alerta",
        				text: "¿En verdad desea " + ($(btn).data('active') == 'S'?'desactivar':'activar') + ' este producto?',   
        				type: "warning",   
        				showCancelButton: true,   
        				confirmButtonColor: "#DD6B55",   
        				confirmButtonText: "Aceptar",
        				cancelButtonText: 'Cancelar',
        				closeOnConfirm: false 
        			}, function(){   
        				var UpdProductDTO = data;
        				UpdProductDTO.active = ($(btn).data('active') == 'S'?'N':'S');
        				FinancialServiceBean.updProduct(UpdProductDTO, function(data){
        					var obj = {};
        					if (data > 0){
        						obj.title = 'Mensaje';
        						obj.msj = 'Producto ' + ($(btn).data('active') == 'S'?'desactivado':'activado') + ' con éxito';
        						obj.type = 'success';
        						$(btn).removeClass('badge-' + ($(btn).data('active') == 'S'?'success':'danger'));
        						$(btn).addClass('badge-' + ($(btn).data('active') == 'S'?'danger':'success'));
        						$(btn).html(($(btn).data('active') == 'S'?'<i class="fa fa-times"></i> Inactivo':'<i class="fa fa-check"></i> Activo'));
        						$(btn).data('active', ($(btn).data('active') == 'S'?'N':'S'));        						
        					}else{
        						obj.title = 'Error';
        						obj.msj = 'Error al ' + ($(btn).data('active') == 'S'?'activar':'desactivar') + ' el producto, intente más tarde';
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