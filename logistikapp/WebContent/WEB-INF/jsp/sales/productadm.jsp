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
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">
	
    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">
	
	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
	
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
							<div class="col-lg-6">
								<ol class="breadcrumb">
                                   <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                   <li><span><spring:message code="label.breadcrumb.sales"/></span></li>
                                   <li class="active"><span><a href="productlist.htm"><spring:message code="label.breadcrumb.product"/></a></span></li>
								</ol>

								<div class="clearfix">
									<h1 class="pull-left"><spring:message code="${accion == 'add' ? 'label.sales.product.add.title1' : 'label.sales.product.upd.title1'}"/></h1>
								</div>
							</div>
						</div>

                        <div class="row">
                        <form id="form" method="post">
                            <div class="col-lg-6">
                                <div class="main-box">
								
                                    <header class="main-box-header clearfix">
                                        <h2><spring:message code="${accion == 'add' ? 'label.sales.product.add.title2' : 'label.sales.product.upd.title2'}"/></h2>
                                    </header>
                                   
                                    <div class="main-box-body clearfix">
                                        
                                        
                                            <spring:hasBindErrors name="command">
                                                <div class="form-group">
                                                    <div class="alert alert-danger">
                                                        ${errors.errorCount} error${errors.errorCount==1?'':'es'} al ${accion == 'add' ? 'agregar' : 'modificar'} el producto
                                                    </div>                                                
                                                </div>
                                            </spring:hasBindErrors>
                                            
                                            <spring:bind path="command.id_product"><input type="hidden" id="id_product" name="id_product" value="${status.value}"/></spring:bind>
                                               
                                             <spring:bind path="command.code">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="code">C&oacute;digo</label>
                                                    <input type="text" class="form-control" maxlength="13" id="code" name="code" placeholder="Ingresa un c&oacute;digo" value="${status.value}" />
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind>
                                               
                                            <spring:bind path="command.name_short">
	                                            <div class="form-group ${status.error ? 'has-error' : '' }">
		                                            <label for="name">Nombre</label>
  	                                                <input type="text" class="form-control" id="name_short" maxlength="50" name="name_short" placeholder="Ingresa un nombre para el producto" value="${status.value}" />
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
		                                        </div>
	                                        </spring:bind>
                                               
                                            <spring:bind path="command.name_long">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="name">Descripción</label>
	                                                <input type="text" class="form-control" id="name_long" maxlength="255" name="name_long" placeholder="Ingresa una descripción para el producto" value="${status.value}" />
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind>
                                            
                                            <spring:bind path="command.id_category_product">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label>Categor&iacute;a Producto</label>
                                                    <select class="form-control" id="id_category_product" name="id_category_product">
                                                        <c:forEach var="cat" items="${list}">
                                                          <c:if test="${cat.active == ACTIVE}">
                                                            <option value="${cat.id_category_product}" ${cat.id_category_product == status.value ? 'selected':''}><c:out value="${cat.name}"/></option>
                                                          </c:if>
                                                        </c:forEach>
                                                    </select>
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind>      
                                            
                                             <spring:bind path="command.id_brand">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label>Marca Producto</label>
                                                    <select class="form-control" id="id_brand" name="id_brand">
                                                        <c:forEach var="bran" items="${listb}">
                                                            <c:if test="${bran.active == 'S'}">
                                                            <option value="${bran.id_brand}" ${bran.id_brand == status.value ? 'selected':''}><c:out value="${bran.name}"/></option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </select>
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind>   
                                            
                                            <spring:bind path="command.price_cost">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="costo">Costo</label>
                                                    <input type="text" class="form-control" onkeypress=" return verificarNumero(this,event)" maxlength="20" id="price_cost" name="price_cost"  placeholder="Define un costo" value="${status.value}" />
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                      		</spring:bind> 
                                      		
                                      		 <spring:bind path="command.tax">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="tax">Impuesto</label>
                                                    <select class="form-control" id="tax" name="tax">
                                                    	<c:if test="${status.value=='0'}"><c:set var="tax_out" value="NA" /></c:if>
                                                    	<c:if test="${status.value=='1'}"><c:set var="tax_out" value="IVA" /></c:if>
                                                    	<c:if test="${status.value=='2'}"><c:set var="tax_out" value="IEPS" /></c:if>
                                                    	<c:if test="${status.value=='3'}"><c:set var="tax_out" value="IEPS e IVA" /></c:if>
                                                    	<option value="${status.value}"><c:out value="${tax_out}"/></option>                                             
														<c:if test="${empty status.value}">
                                                    		<option value='1'>IVA</option>
                                                    		<option value='2'>IEPS</option>
                                                    		<option value='3'>IEPS e IVA</option>
                                                    	</c:if>
														<c:if test="${status.value=='0'}">
                                                    		<option value='1'>IVA</option>
                                                    		<option value='2'>IEPS</option>
                                                    		<option value='3'>IEPS e IVA</option>
                                                    	</c:if>
														<c:if test="${status.value=='1'}">
                                                    		<option value='0'>NA</option>
                                                    		<option value='2'>IEPS</option>
                                                    		<option value='3'>IEPS e IVA</option>
                                                    	</c:if>
														<c:if test="${status.value=='2'}">
                                                    		<option value='0'>NA</option>
                                                    		<option value='1'>IVA</option>
                                                    		<option value='3'>IEPS e IVA</option>
                                                    	</c:if>                                                    	                                                    	                                                    	
                                                    	<c:if test="${status.value=='3'}">
                                                    		<option value='0'>NA</option>
                                                    		<option value='1'>IVA</option>
                                                    		<option value='2'>IEPS</option>
                                                    	</c:if>
                                                    </select>
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind> 
									</div>
								</div>
							</div>
							<div class="col-lg-6">
                                <div class="main-box">
                                
                                <header class="main-box-header clearfix"></header>
                                
                                    <div class="main-box-body clearfix">  
                                    
                                      <spring:bind path="command.price_sale">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="price_sale">Precio Venta Pieza</label>
                                                    <input type="text" class="form-control" id="price_sale" onkeypress=" return verificarNumero(this,event)" maxlength="20" name="price_sale" placeholder="Define un precio de venta por pieza" value="${status.value}" />
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind> 
                                            
                                            <spring:bind path="command.price_cost_box">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="price_cost_box">Costo Venta Caja</label>
                                                    <input type="text" class="form-control" id="price_cost_box" onkeypress=" return verificarNumero(this,event)" maxlength="20" name="price_cost_box" placeholder="Define un precio de venta por paquete" value="${status.value}" />
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind> 
                                            
                                            <spring:bind path="command.price_sale_box">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="price_sale_box">Precio Venta Caja</label>
                                                    <input type="text" class="form-control" id="price_sale_box" onkeypress=" return verificarNumero(this,event)" maxlength="20" name="price_sale_box" placeholder="Define un precio de venta por caja" value="${status.value}" />
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind> 
                                            
                                            <spring:bind path="command.piece_in_box">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="piece_in_box">Piezas por caja</label>
                                                    <input type="text" class="form-control" id="piece_in_box" onkeypress=" return verificarOrder(event)" maxlength="9" name="piece_in_box" placeholder="Define la cantidad de piezas por paquete" value="${status.value}" />
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind>

                                            <spring:bind path="command.orderby">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="orden">Orden</label>
                                                    <input type="text" class="form-control" id="orderby" onkeypress=" return verificarOrder(event)" maxlength="9" name="orderby" placeholder="Define un orden" value="${status.value}" />
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind>
                                            
                                            <fieldset>Tipo de venta</fieldset>
                                            
                                            <div class="row">
                                            	<div class="col-xs-6">
	                                            	<spring:bind path="command.type">
		                                                <div class="form-group">                                                
		                                                    <div class="radio">
		                                                        <input type="radio" id="typePza" name="type" value="PCS" ${status.value == 'PCS' || accion == 'add' ? 'checked=checked' : ''}/>
		                                                        <label for="typePza">Pieza</label>
		                                                    </div>                                                 
		                                                </div>
		                                            </spring:bind>
                                            	</div>
                                            	<div class="col-xs-6">
                                            		<spring:bind path="command.type">
		                                                <div class="form-group">                                                    
		                                                    <div class="radio">
		                                                        <input type="radio" id="typePack" name="type" value="PKG" ${status.value == 'PKG' ? 'checked=checked' : ''} />
		                                                        <label for="typePack">Caja</label>
		                                                    </div>                                                    
		                                                </div>
		                                            </spring:bind>
                                            	</div>
                                            </div>
                                            
                                            <div class="row">
                                            	<div class="col-xs-6">
	                                            	<spring:bind path="command.active">
		                                                <div class="form-group">                                                
		                                                    <div class="checkbox-nice">
		                                                        <input type="checkbox" id="active" name="active" value="S" ${status.value == ACTIVE || accion == 'add' ? 'checked=checked' : ''}/>
		                                                        <label for="active">Activo</label>
		                                                    </div>                                                 
		                                                </div>
		                                            </spring:bind>
                                            	</div>
                                            	<div class="col-xs-6">
                                            		<spring:bind path="command.flag">
		                                                <div class="form-group">                                                    
		                                                    <div class="checkbox-nice">
		                                                        <input type="checkbox" id="flag" name="flag" value="S" ${status.value == ACTIVE ? 'checked=checked' : ''} />
		                                                        <label for="flag">Flag</label>
		                                                    </div>                                                    
		                                                </div>
		                                            </spring:bind>
                                            	</div>
                                            </div>
                                                 
                                       <!--    <spring:bind path="command.image">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">                                                    
	                                                  	<img src="${accion == 'add' || command.image == '' || command.image == null ? 'img/img_default.jpg' : command.image}" class="image img-responsive" 
	                                                  	style="height:165px!important" id="imgCont1" 
	                                                  	data-toggle="tooltip" style="cursor:pointer; data-placement="bottom" title="Clic para cambiar imagen"/>  
														<input type="file"  class="hidden" accept="image/*" id="img1">
														<input type="hidden" value="${status.value}" id="image" name="image"/>								
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind> --> 
                                                
                                            <div class="form-group">
                                                <button type="button" class="btn btn-default btn-xs pull-left" onclick="location.href = 'productlist.htm';"><i class="fa fa-times fa-lg"></i> Cancelar</button>
                                                <button type="submit" class="btn btn-success btn-xs  pull-right"><i class="fa fa-check fa-lg"></i> <spring:message code="${accion == 'add' ? 'label.sales.product.add.button' : 'label.sales.product.upd.button'}"/></button>
                                            </div>
									</div>
								</div>
							</div><!-- FIN COL -->
						</form>
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
    
    function verificarOrder(evt){
    	var key = evt.keyCode;
    	return ((key <= 13) || (key>= 48 && key <= 57));
	}
    
	function verificarNumero(txt, evt){
	
		var charCode = (evt.which) ? evt.which : evt.keyCode;
    	if (charCode == 46) {
        
        if (txt.value.indexOf('.') === -1) {
            	return true;
        	} else {
            	return false;
        	}
    	} else {
        	if (charCode > 31
            	 && ((charCode < 48) || charCode > 57))
        	    return false;
    	}
    	return true;
	} 
    
    var actual_product = 0;
    var img = null;
    
    $('.image').on('click', function(){    	
    	actual_product = $(this).data('id');      
        $('#imgCont1').attr('src', this.src);
    });
    
    $('#imgCont1').on('click', function(){
    	$('input[type="file"]').click();
    });        

	$('input[type=file]').on('change', function(){
		var file = this;
		var img = $(this).parent().parent().find('img')[0];
	
    		if (file.files && file.files[0]){
    			var fr = new FileReader();
    			fr.onload = function(e){
    				img.src = e.target.result;
    				$('#image').val(img.src);
    			}
	    		fr.readAsDataURL(file.files[0]);
    		}
	});
        
	</script>	
</body>
</html>