<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Cliente - LogistikApp</title>
	
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
	<script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	
	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
	
	<!--[if lt IE 9]>
			<script src="js/html5shiv.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->
		<script type="text/javascript">
			var adv = ${advertencia};
    		if(adv)
    			window.location.href = "customer.htm";
    		if(${fn:contains(useracegi.profile,'DRI') && useracegi.superuser != 'S'})
    			window.location.href = "home.htm";
		</script>
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
									    <li class="active"><span>Clientes</span></li>
									</ol>
									<h1>Nuevo Cliente</h1>
								</div>
							</div>

							<div class="row">
							    <form id="form" method="post" action="customeradd.htm">
							        
							        <div class="col-lg-6" id="form-side-left">
							            <div class="main-box no-header">
							                <div class="main-box-body clearfix">
							                    
							                    <spring:hasBindErrors name="command">
							                        <div class="form-group">
							                            <div class="alert alert-danger">
							                                ${ errors.errorCount } error${ errors.errorCount == 1 ? '' : 'es' } al agregar el cliente
							                            </div>
							                        </div>
							                    </spring:hasBindErrors>

							                    <input type="hidden" id="id_country" name="id_country" value="${PAIS}"/>
							                    <input type="hidden" id="id_supplier" name="id_supplier" value="${id_supplier}"/>
							                    
							                    <!-- retail + category -->
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-sm-6  col-xs-6">
                                                        <spring:bind path="command.id_retail">
                                                            <div class="form-group ${status.error ? 'has-error' : '' }">
                                                                <label>Plaza Comercial</label>
                                                                <select class="form-control" id="id_retail" name="id_retail">
                                                                    <c:forEach var="ret" items="${retailers}">
                                                                        <c:if test="${ ret.active == 'S'}"><option value="${ ret.id_retail }" ${ ret.id_retail == status.value ? 'selected' : '' }>${ ret.name }</option></c:if>
                                                                    </c:forEach>
                                                                </select>
                                                                <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                            </div>
                                                        </spring:bind>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-sm-6  col-xs-6">
                                                        <spring:bind path="command.id_store_category">
                                                            <div class="form-group ${status.error ? 'has-error' : '' }">
                                                                <label>Categor&iacute;a Cliente</label>
                                                                <select class="form-control" id="id_store_category" name="id_store_category">
                                                                    <c:forEach var="str" items="${categories}">
                                                                        <option value="${str.id_store_category}" ${str.id_store_category == status.value ? 'selected' : ''}><c:out value="${str.name}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                        </spring:bind>
                                                    </div>
                                                </div>

                                                <!-- name + code -->
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-sm-6  col-xs-6">
                                                         <spring:bind path="command.name">
		                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
		                                                        <label for="name">Nombre (requerido)</label>
		                                                        <input type="text" class="form-control"  maxlength="50" id="name" name="name" placeholder="Nombre del local" value="${status.value}" />
		                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
		                                                    </div>
		                                                </spring:bind>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-sm-6  col-xs-6">
                                                         <spring:bind path="command.code">
		                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
		                                                        <label for="code">C&oacute;digo (requerido)</label>
		                                                        <input type="text" class="form-control" id="code" name="code" placeholder="C&oacute;digo"  value="${status.value}" maxlength="10" size="10"/>
		                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
		                                                    </div>
		                                                </spring:bind>
                                                    </div>
                                                </div>

                                                <!-- state + city -->
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-sm-6  col-xs-6">
                                                        <spring:bind path="command.id_state">
                                                            <div class="form-group ${status.error ? 'has-error' : '' }">
                                                                <label>Estado</label>
			                                                    <select class="form-control" id="id_state" name="id_state">
																	<c:forEach var="stt" items="${states}">
				                                                        <option value="${stt.id_state}" ${stt.id_state == status.value ? 'selected' : ''}><c:out value="${stt.name}"/></option>
																	</c:forEach>
			                                                    </select>
			                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
		                                                    </div>
		                                                </spring:bind>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
										                <spring:bind path="command.id_city">
		                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
		                                                        <label>Municipio</label>
										                        <select class="form-control" id="id_city" name="id_city" data-city="${status.value}">
										                            <c:forEach var="cty" items="${cities}">
										                                <option value="${cty.id_city}" ${cty.id_city == status.value ? 'selected' : ''}><c:out value="${cty.name}"/>${status.value}</option>
										                            </c:forEach>
										                        </select>
										                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
		                                                    </div>
										                </spring:bind>
                                                    </div>
                                                </div>

                                                <!-- address1 + address2 -->
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-sm-6  col-xs-6">
                                                        <spring:bind path="command.address1">
                                                            <div class="form-group ${status.error ? 'has-error' : '' }">
                                                                <label for="address1">Calle y n&uacute;mero</label>
                                                                <input type="text" maxlength="200" class="form-control" id="address1" name="address1" placeholder="Ingresa la calle y n&uacute;mero" value="${status.value}">
                                                                <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                            </div>
                                                        </spring:bind>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-sm-6  col-xs-6">
                                                        <spring:bind path="command.address2">
                                                            <div class="form-group ${status.error ? 'has-error' : '' }">
                                                                <label for="address2">Fraccionamiento</label>
                                                                <input type="text" class="form-control" id="address2" name="address2" placeholder="Ingresa el fraccionamiento" value="${status.value}">
                                                                <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                            </div>
                                                        </spring:bind>
                                                    </div>
                                                </div>

                                                <!-- postal -->
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-sm-6  col-xs-6">
                                                        <spring:bind path="command.postal">
                                                            <div class="form-group ${status.error ? 'has-error' : '' }">
                                                                <label for="postal">C&oacute;digo Postal</label>
                                                                <input type="text" class="form-control" onkeypress="return verificarNumero(event)" id="postal" name="postal" maxlength="10" placeholder="Ingresa el c&oacute;digo postal" value="${status.value}">
                                                                <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                            </div>
                                                        </spring:bind>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-sm-6  col-xs-6">
                                                    	<spring:bind path="command.phone">
		                                                	<div class="form-group ${status.error ? 'has-error' : '' }">
		                                                    	<label for="phone">Tel&eacute;fono</label>
		                                                        <input type="text" class="form-control" onkeypress="return verificarNumero(event)" maxlength="20" size="20" id="phone" name="phone" placeholder="Ingresa el tel&eacute;fono" value="${status.value}">
		                                                    	<c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
		                                                    </div>
		                                                </spring:bind>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-lg-6 col-md-6 col-sm-6  col-xs-6">
                                                          <spring:bind path="command.email">
		                                                	<div class="form-group ${status.error ? 'has-error' : '' }">
		                                                		<label for="email">Correo electr&oacute;nico</label>
		                                                        <input type="email" class="form-control" maxlength="255" size="255" id="email" name="email" placeholder="Ingresa el correo eléctronico" value="${status.value}">
		                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
															</div>
														</spring:bind>
                                                    </div>
                                                </div>                                                
										</div>
									</div>
								</div>
								
                                <div class="col-lg-6">
                                    <div class="main-box no-header">
                                        <div class="main-box-body  clearfix">
                                            
                                            <!--
                                            <div class="form-group pull-center">
                                                <button type="button" id="getCoordsButton" name="getCoordsButton" class="btn btn-primary">
                                                    <i class="fa fa-cloud-download"></i> Obtener coordenadas
                                                </button>
                                                <button type="button" id="getActualPosition" class="btn btn-primary">
                                                    <i class="fa fa-location-arrow"></i> Utilizar mi posición actual
                                                </button>
                                                <c:if test="${(fn:contains(useracegi.profile,'DRI') && useracegi.superuser == 'S') || useracegi.profile == 'SUP'}">
                                                <a class="btn btn-primary" href='geoclientlocation.htm'><i class="fa fa-search"></i> Buscar  cliente</a>
                                                </c:if>
                                            </div>
                                            -->
                                            
                                            <div class="row">
                                                	<button type="button" id="getCoordsButton" name="getCoordsButton" class="btn btn-primary">
		                                            	<i class="fa fa-cloud-download"></i> ¿Obtener coordenadas?
		                                            </button>
                                                    <button type="button" id="getActualPosition" class="btn btn-primary">
		                                            	<i class="fa fa-location-arrow"></i> Posición actual
		                                            </button>
	                                            <c:if test="${(fn:contains(useracegi.profile,'DRI') && useracegi.superuser == 'S') || (fn:contains(useracegi.profile,'SUP'))}">
	                                                <button type="button" class="btn btn-primary hidden-xs" id='geoclientlocation'><i class="fa fa-search"></i> Buscar cliente</button>
                                                </c:if>
                                            </div>
                                            

                                            <!-- latitude + longitude -->
                                            <div class="row">
                                                <div class="col-lg-6 col-md-6 col-sm-6  col-xs-6">
                                                    <spring:bind path="command.latitude">
                                                        <div class="form-group ${status.error ? 'has-error' : '' }">
                                                            <label for="postal">Latitud</label>
                                                            <input type="number" class="form-control" id="latitude" name="latitude" placeholder="Ingresa la latitud" value="${status.value == null ? 0 : status.value}" required="required">
                                                            <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                        </div>
                                                    </spring:bind>
                                                </div>
                                                <div class="col-lg-6 col-md-6 col-sm-6  col-xs-6">
                                                    <spring:bind path="command.longitude">
                                                        <div class="form-group ${status.error ? 'has-error' : '' }">
                                                            <label for="postal">Longitud</label>
                                                            <input type="number" class="form-control" id="longitude" name="longitude" placeholder="Ingresa la longitud" value="${status.value == null ? 0 : status.value}" required="required">
                                                            <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                        </div>
                                                    </spring:bind>
                                                </div>
                                            </div>
                                                    
                                            <!-- orden + active -->
                                            <div class="row">
                                                <div class="col-lg-6 col-md-6 col-sm-6  col-xs-6">
	                                                <spring:bind path="command.orderby">
	                                                    <div class="form-group">
	                                                        <label for="orderby">Orden</label>
	                                                        <input type="text" class="form-control" onkeypress="return verificarNumero(event)" maxlength="10" id="orderby" name="orderby" placeholder="Ingresa un criterio de orden" value="${not empty status.value ? status.value : ORDERBY }" />
	                                                    </div>
	                                                </spring:bind>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
	                                                <spring:bind path="command.active">
	                                                    <div class="form-group">
	                                                        <label for="active">¿Activo?</label>
	                                                        <div class="checkbox-nice">
	                                                            <input type="checkbox" id="active" name="active" value="S" checked="checked" />
	                                                            <label for="active">¡Si!</label>
	                                                        </div>
	                                                    </div>
	                                                </spring:bind>
                                                </div>
                                                <c:if test="${supplier.shelf =='S'}">
                                                <spring:bind path="command.shelf">
                                                	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
	                                                	<div class="form-group">
	                                                        <label for="shelf">¿Promoción?</label>
	                                                        <div class="checkbox-nice">
	                                                            <input type="checkbox" id="shelf" name="shelf" value="S" />
	                                                            <label for="shelf">¡Si!</label>
	                                                        </div>
	                                                    </div>
                                                	</div>
                                                </spring:bind>
                                                </c:if>
                                            </div>
                                                


                                                <div class="form-group">
                                                    <button type="button" id="cancelButton" class="btn btn-default pull-left">
                                                        <i class="fa fa-times"></i> Cancelar
                                                    </button>
                                                    <button type="button" class="btn btn-success pull-right" id="btnsave">
                                                        <i class="fa fa-check"></i> Agregar
                                                    </button>
                                                </div>
                                                
                                        </div>

                                    </div>
                                </div>
								
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
		

  
	<!-- global scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.min.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	
	<script type="text/javascript">
	
	$('#address1').on({
		 'keyup':coordenadas,
		 'click':coordenadas,
		 'focus':coordenadas
	 });
	
	 function coordenadas(e){			
			 if(document.getElementById("address1").value.length < 1){	
				 document.getElementById("getCoordsButton").disabled= true;
			 	}else{	
			 		document.getElementById("getCoordsButton").disabled= false;
			 	}		
	 }
	
	 function verificarNumero(evt){
	    	var key = evt.keyCode;
	    	return ((key <= 13) || (key>= 48 && key <= 57));
	    }
	
		$(document).ready(function() {
			
			RetailServiceBean.getAllCityByIdState( $('#id_state option:selected').val(), function(data){
   				dwr.util.removeAllOptions("id_city");
	    	    if ( data != null ){
	    	    	dwr.util.addOptions("id_city",data,"id_city","name");
	    	    	if ($('#id_city').data('city') != ""){
	    	    		$('#id_city option[value="' + $('#id_city').data('city') + '"]').prop('selected', true);
	    	    	}
	    	    }
			} );
			
			$('#cancelButton').click(function () {
  	    	    window.location.href = "customer.htm";
  	        });  
        
        	if(document.getElementById("address1").value.length < 1){	
    			 document.getElementById("getCoordsButton").disabled= true;
    	 	 }else{	
    		 	 document.getElementById("getCoordsButton").disabled= false;
    		 }	
        	
        	if ("${lat}" != ''){
        	
        		$('#address1').val("${cn}");
    	    	$('#address2').val("${fr}");
    	    	$('#postal').val("${cp}");
    	    	$('#latitude').val("${lat}" == '' ? 0 : "${lat}");
    	    	$('#longitude').val("${lng}" == '' ? '' : "${lng}");
    	    	$('#name').val("${name}");
    	    	$('#code').val("${code}");
    	    	$('#phone').val("${phone}");
    	    	$('#email').val("${email}");
    	    	$('#orderby').val("${order}");
    	    	if ($('#shelf').length > 0){
    	    		$('#shelf').prop('checked',${she});
    	    	}
    	    	if (("${est}") != ""){
        		
	        	$('#id_state option:contains("${est}")').prop('selected', true);
	        	
	        	RetailServiceBean.getAllCityByIdState( $('#id_state option:selected').val(), function(data){
	   				dwr.util.removeAllOptions("id_city");
		    	    if ( data != null ){
		    	    	dwr.util.addOptions("id_city",data,"id_city","name");
		    	    	$('#id_city option:contains("${mun}")').prop('selected', true);
		    	    }
				} );
        	}
        	
			if (("${retail}") != ""){
	        	$('#id_retail option:contains("${retail}")').prop('selected', true);
        	}
			
			if (("${cat}") != ""){
	        	$('#id_store_category option:contains("${cat}")').prop('selected', true);
        	}
        }
        	
        	    $('#cancelButton').click(function () {
        	    	    window.location.href = "customer.htm";
        	        });
        	    
        	    $('#getCoordsButton').click(function () {
        	    	$( "#address1").val();
                	$( "#address2").val();
                	$( "#id_state option:selected" ).text();
                	$( "#id_city option:selected" ).text();

                    var geocoder = new google.maps.Geocoder();
                    geocoder.geocode( { 'address': $( "#address1").val()+", "+$( "#address2").val()+", "+$( "#id_state option:selected" ).text()+", "+$( "#id_city option:selected" ).text()+", Mexico"}, function(results, status) {
                        if ( status == google.maps.GeocoderStatus.OK ) {
                            var latitude = results[0].geometry.location.lat();
                            var longitude = results[0].geometry.location.lng();
                            //alert(latitude + " ... " + longitude);
                            document.getElementById("latitude").value = latitude;
                            document.getElementById("longitude").value = longitude;
                        }
                     });  
        		}); // getCoordsButton        	    
        	   	    
        	    }); // ready
        	    
        	    $( "#id_state" ).change( function() {
    	    	    RetailServiceBean.getAllCityByIdState( this.value, reply1 );
    	    	    }); // id_state
    	    	    
    	    		var id_st = document.getElementById("id_state").value;        	    	      
    	    		var tot = 0;     	    		
	    	    var reply1 = function(data) {    	    	    	
	    	    		tot = data.length;
	    	    	    dwr.util.removeAllOptions("id_city");
	    	    	    if ( data != null )
	    	    	    	    dwr.util.addOptions("id_city",data,"id_city","name");    	    	    	    	    	    	   	    	    	    	
	    	    	    }; // reply1	    
        	    
        	    $('#tour').css({
            		display:'block',
            		cursor:'pointer'
            	});

            	$('#tour').on('click', function(){
        			var placementRight = 'right';
        			var placementLeft = 'left';
        			var formLeftPosition = $('#form-side-left').height();
        			var tour = {
        					id: "customer-tour",
        					steps: [
        						{
        							target: "id_retail",
        							title: "Plaza comercial",
        							content: "Aquí podrás seleccionar la plaza comercial a la que pertenecerá el nuevo cliente",
        							placement: "bottom"
        						},
        						{
        							target: 'id_store_category',
        							title: "Categoría cliente",
        							content: "Aquí podrás seleccionar la categoría a la que pertenecerá el nuevo cliente",
        							placement: "bottom",
        							zindex: 999						
        						},
        						{
        							target: 'form-side-left',
        							title: "Campos requeridos",
        							content: "Los campos marcados como \"requerido\" son indispensables para crear tu nuevo cliente",
        							placement: "right",
        							zindex: 999,
        							xOffset: -10,
        							yOffset: (formLeftPosition / 5)
        						},
        						{
        							target: 'form-side-left',
        							title: "Campos no requeridos",
        							content: "Los campos no marcados como \"requerido\" no son indispensables para crear un nuevo cliente, pero ayudan a complementar la información del mismo",
        							placement: "right",
        							zindex: 999,
        							yOffset: (formLeftPosition / 3) *2
        						},
        						{
        							target: 'getCoordsButton',
        							title: "Obtener coordenadas",
        							content: "Clic para obtener las coordenadas de tu nuevo cliente",
        							placement: "left",
        							zindex: 999,
        							yOffset: -15
        						},
        						{
        							target: 'btnsave',
        							title: "Agregar",
        							content: "Clic para guardar tu actual cliente",
        							placement: "left",
        							zindex: 999,
        							yOffset: -15
        						}
        					],
        					showPrevButton: true
        				};
        			hopscotch.startTour(tour);
        		});
            	
            	$('#btnsave').on('click', function(){
            		if ($('#id_store_category').val() == null){
            			alert('Debe seleccionar una categoría del cliente');
            		}else if ($('#id_retail').val() == null){
            			alert('Debe seleccionar un plaza comercial');
            		}else{
            			$('#form').submit();
            		}
            	});
            	
            	$('#getActualPosition').on('click', function(){
            		if (navigator.geolocation){
            			navigator.geolocation.getCurrentPosition(function(location){
            				$('#latitude').val(location.coords.latitude);
            				$('#longitude').val(location.coords.longitude);
            				var geocoder = new google.maps.Geocoder();
            	        	var position = {lat: location.coords.latitude, lng: location.coords.longitude};
            	        		lat = position.lat;
            	        		lng = position.lng;
            	        		geocoder.geocode({location:position}, function(results, status){
            	        	       	if (status == google.maps.GeocoderStatus.OK){
            	        	       		if (results[0]){
            	        	       			result = results[0].address_components;
            	        	       			
            	        	       			$('#address1').val(result[1].long_name + ", " + result[0].long_name);
            	        	       			$('#address2').val(result[2].long_name);
            	        	       			$('#postal').val(result[7].long_name);
            	        	       			$('#postal').val(result[7].long_name);
            	        	       			$('#id_state option:contains("' + result[5].long_name + '")').prop('selected',true);
            	        	       			RetailServiceBean.getAllCityByIdState( $('#id_state option:selected').val(), function(data){
            	        	       				dwr.util.removeAllOptions("id_city");
                	    	    	    	    if ( data != null ){
                	    	    	    	    	dwr.util.addOptions("id_city",data,"id_city","name");
                	    	    	    	    	$('#id_city option:contains("' + result[4].long_name + '")').prop('selected', true);
                	    	    	    	    }
                	       					} );
            	        	       		}
            	        	       	}
            	        	    });
            	        	
            			}, function showError(error) {
            				var msg = "";
            			    switch(error.code) {
            		        case error.PERMISSION_DENIED:
            		            msg = "El usuario denego la geolocalización."
            		            break;
            		        case error.POSITION_UNAVAILABLE:
            		            msg = "Localización no disponible."
            		            break;
            		        case error.TIMEOUT:
            		            msg = "El tiempo para obtener la localización termino sin respuesta."
            		            break;
            		        case error.UNKNOWN_ERROR:
            		            msg = "Error desconocido."
            		            break;
            		    }
            		});
            		}
            	});
            	$('#geoclientlocation').on('click', function(){
            		location.href = "geoclientlocation.htm?retail=" + $('#id_retail option:selected').text() + "&cat=" + $('#id_store_category option:selected').text() + "&name=" + $('#name').val() + "&code=" + $('#code').val() + '&phone=' + $('#phone').val() + "&email=" + $('#email').val() + "&order=" + $('#orderby').val() + "&she=" + ($('#shelf').length > 0?$('#shelf').prop('checked'):false);
            	});
	</script>
	
</body>
</html>
