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
									    <li><span>Clientes</span></li>
									    <li class="active"><span><a href="store.htm?id=${retail.id_retail}">${retail.name}</a></span></li>
									
                                        <!--li><a href="home.htm"><span><spring:message code="label.breadcrumb.dashboard"/></span></a></li>
                                        <li><span><spring:message code="label.breadcrumb.logistic"/></span></li>
                                        <li><a href="retaillist.htm"><span><spring:message code="label.breadcrumb.logistic.retail"/></span></a></li>
                                        <li class="active"><a href="storelist.htm?id=${command.id_retail}"><span><c:out value="${command.name}"/></span></a></li-->
									</ol>

									<div class="clearfix">
										<h1>${ accion == 'add' ? 'Ingresa los datos del nuevo cliente' : 'Modifica los datos del cliente' }</h1>
									</div>
								</div>
							</div>

							<div class="row">
							<form id="form" method="post">
							    <div class="col-lg-6">
							        <div class="main-box no-header">
									
                                        <div class="main-box-body clearfix" id="form-side-left">

                                            <spring:hasBindErrors name="command">
                                                <div class="form-group">
                                                    <div class="alert alert-danger">
                                                        ${ errors.errorCount } error${ errors.errorCount == 1 ? '' : 'es' } al ${accion == 'add' ? 'agregar' : 'modificar'} el cliente
                                                    </div>                                                
                                                </div>
                                            </spring:hasBindErrors>
                                            
                                            <spring:bind path="command.id_store"><input type="hidden" id="id_store" name="id_store" value="${status.value}"/></spring:bind>
                                            <spring:bind path="command.id_retail"><input type="hidden" id="id_retail" name="id_retail" value="${retail.id_retail}"/></spring:bind>
                                            <spring:bind path="command.id_country"><input type="hidden" id="id_country" name="id_country" value="${retail.id_country}"/></spring:bind>
                                            <input type="hidden" id="id_supplier" name="id_supplier" value="${supplier.id_supplier}"/>

                                                <spring:bind path="command.name">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="name">Nombre (requerido)</label>
                                                        <input type="text" class="form-control"  maxlength="50" id="name" name="name" placeholder="Nombre del local" value="${status.value}" />
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>

                                                <spring:bind path="command.code">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="code">C&oacute;digo (requerido)</label>
                                                        <input type="text" class="form-control" id="code" name="code" placeholder="C&oacute;digo"  value="${status.value}" maxlength="10" size="10"/>
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>
                                                
                                                <spring:bind path="command.id_state">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label>Estado (requerido)</label>
	                                                    <select class="form-control" id="id_state" name="id_state">
															<c:forEach var="stt" items="${states}">
		                                                        <option value="${stt.id_state}" ${stt.id_state == status.value ? 'selected' : ''}><c:out value="${stt.name}"/></option>
															</c:forEach>
	                                                    </select>
	                                                    
	                                                      
	                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>

								                <spring:bind path="command.id_city">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label>Municipio (requerido)</label>
								                        <select class="form-control" id="id_city" name="id_city">
								                            <c:forEach var="cty" items="${cities}">
								                                <option value="${cty.id_city}" ${cty.id_city == status.value ? 'selected' : ''}><c:out value="${cty.name}"/></option>
								                            </c:forEach>
								                        </select>
								                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
								                </spring:bind>

								                <spring:bind path="command.id_store_category">
								                    <div class="form-group ${status.error ? 'has-error' : '' }">
								                        <label>Categor&iacute;a (requerido)</label>
								                        <div class="row">
	                                                    	<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11">
		                                                    	<select class="form-control" id="id_store_category" name="id_store_category">
		                                                        	<c:forEach var="str" items="${categories}">
								                                		<c:if test="${str.active == 'S' }"><option value="${str.id_store_category}" ${str.id_store_category == status.value ? 'selected' : ''}><c:out value="${str.name}"/></option></c:if>
								                            		</c:forEach>                                                      
		                                                    	</select>
	                                                    	</div>
	                                                    	<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
	                                                    		<button type="button" onclick="javascript:sendForm()" class="btn btn-primary btn-xs"><i class="fa fa-plus"></i></button>                              
                                                    		</div>
                                                    	</div>
                                                    </div>     
								                        	<c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </spring:bind>

								                <spring:bind path="command.address1">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="address1">Calle y n&uacute;mero:</label>
	                                                    <input type="text" class="form-control" maxlength="200" id="address1" name="address1" placeholder="Ingresa la calle y n&uacute;mero" value="${status.value}">
	                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
	                                            </spring:bind>

								                <spring:bind path="command.address2">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="address2">Fraccionamiento</label>
	                                                    <input type="text" class="form-control" id="address2" name="address2" placeholder="Ingresa el fraccionamiento" value="${status.value}">
	                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
	                                            </spring:bind>

								                <spring:bind path="command.postal">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="postal">Postal</label>
	                                                    <input type="text" class="form-control" onkeypress="return verificarNumero(event)" maxlength="10" id="postal" name="postal" placeholder="Ingresa el c&oacute;digo postal" value="${status.value}">
	                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
	                                            </spring:bind>
                                                <spring:bind path="command.phone">
                                                	<div class="form-group ${status.error ? 'has-error' : '' }">
                                                    	<label for="phone">Tel&eacute;fono</label>
                                                        <input type="text" class="form-control" onkeypress="return verificarNumero(event)" maxlength="20" size="20" id="phone" name="phone" placeholder="Ingresa el tel&eacute;fono" value="${status.value}">
                                                    	<c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>
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
								
                                <div class="col-lg-6">
                                    <div class="main-box no-header">
                                    
                                        <div class="main-box-body  clearfix">
                                                <div class="row">
	                                                	<button type="button" id="getcoords" name="getcoords" class="btn btn-primary">
			                                            	<i class="fa fa-cloud-download"></i> Obtener coordenadas
			                                            </button>
	                                                    <button type="button" id="getActualPosition" class="btn btn-primary">
			                                            	<i class="fa fa-location-arrow"></i> Posición actual
			                                            </button>
		                                            <c:if test="${(fn:contains(useracegi.profile,'DRI') && useracegi.superuser == 'S') || fn:containsIgnoreCase(useracegi.profile,'SUP')}">
		                                                <button type="button" class="btn btn-primary hidden-xs" id="geoclientlocation"><i class="fa fa-search"></i> Buscar cliente</button>
	                                                </c:if>
	                                            </div>
                                                
                                                <spring:bind path="command.latitude">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="postal">Latitud</label>
                                                        <input type="number" class="form-control" id="latitude" name="latitude" placeholder="Ingresa la coordenada de latitud" value="${status.value == null ? 0 : status.value}" required="required" value="0">
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>
                                                
                                                <spring:bind path="command.longitude">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="postal">Longitud</label>
                                                        <input type="number" class="form-control" id="longitude" name="longitude" placeholder="Ingresa la coordenada de longitud" value="${status.value == null ? 0 : status.value}" required="required">
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>
                                                
                                                <spring:bind path="command.orderby">
                                                    <div class="form-group">
                                                        <label for="orderby">Orden</label>
                                                        <input type="text" class="form-control" maxlength="10" onkeypress="return verificarNumero(event)" id="orderby" name="orderby" placeholder="Ingresa un criterio de orden" value="${status.value}" />
                                                    </div>
                                                </spring:bind>

                                                <!-- activo + shelf -->
                                                <div class="row">
                                                    <div class="form-group col-md-6">
                                                        <spring:bind path="command.active">
                                                            <c:set var="chkactive" value=""/>
                                                            <c:choose>
                                                                <c:when test="${ accion == 'add' }"><c:set var="chkactive" value="checked=checked"/></c:when>
                                                                <c:otherwise><c:set var="chkactive" value="${status.value == ACTIVE ? 'checked=checked' : ''}"/></c:otherwise>
                                                            </c:choose>
	                                                        <div class="checkbox-nice">
	                                                            <input type="checkbox" id="active" name="active" value="S" ${chkactive}/>
	                                                            <label for="active">¿Activo?</label>
	                                                        </div>
	                                                    </spring:bind>
	                                                </div>
	                                                
	                                                <c:if test="${supplier.shelf =='S'}">
	                                                    <div class="form-group col-md-6">
			                                                <spring:bind path="command.shelf">
			                                                    <c:set var="chkshelf" value=""/>
			                                                    <c:choose>
			                                                        <c:when test="${ accion == 'add' }"><c:set var="chkshelf" value=""/></c:when>
			                                                        <c:otherwise><c:set var="chkshelf" value="${status.value == ACTIVE ? 'checked=checked' : ''}"/></c:otherwise>
			                                                    </c:choose>
			                                                    	<div class="checkbox-nice pull-right">
			                                                            <input type="checkbox" id="shelf" name="shelf" value="S" ${chkshelf}/>
			                                                            <label for="shelf">¿Promoción?</label>
			                                                        </div>
			                                                </spring:bind>
		                                                </div>
													</c:if>
                                                </div>
												
                                                
                                                <div class="form-group">
                                                <c:choose>
                                                	<c:when test="${namesearch == null}">
                                                		<button type="button" class="btn btn-default pull-left" onclick="location.href = 'store.htm?id=${retail.id_retail}';">
                                                        	<i class="fa fa-times"></i> Cancelar
                                                    	</button>
                                                	</c:when>
                                                	<c:otherwise>
                                                		<button type="button" class="btn btn-default pull-left" onclick="location.href = 'storesearch.htm?searchByName=${namesearch}';">
                                                        	<i class="fa fa-times"></i> Cancelar
                                                    	</button>
                                                	</c:otherwise>
                                                </c:choose>
                                                    
                                                    <button type="button" class="btn btn-success pull-right" id="btnsave"><i class="fa fa-check"></i> <spring:message code="${accion == 'add' ? 'label.logistic.store.add.button' : 'label.logistic.store.upd.button'}"/></button>
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
				 document.getElementById("getcoords").disabled= true;
			 	}else{	
			 		document.getElementById("getcoords").disabled= false;
			 	}		
	 }
	 
	 function verificarNumero(evt){
	    	var key = evt.keyCode;
	    	return (key <= 13 || (key>= 48 && key <= 57));
	    }  
	
	function sendForm()
	{
		var id_store = document.getElementById("id_store").value;
		var id_retail = document.getElementById("id_retail").value;
		
		var active = document.getElementById("active").value;
		var address1 = document.getElementById("address1").value;
		var address2 = document.getElementById("address2").value;
		var code = document.getElementById("code").value;
		var email = document.getElementById("email").value;
		var id_state = document.getElementById("id_state").value;
		var id_city = document.getElementById("id_city").value;
		var id_store_category = document.getElementById("id_store_category").value;
		var latitude = document.getElementById("latitude").value;
		var longitude = document.getElementById("longitude").value;
		var name = document.getElementById("name").value;
		var orderby = document.getElementById("orderby").value;
		var phone = document.getElementById("phone").value;
		var postal = document.getElementById("postal").value;
		var shelf = "";
		if ( "${supplier.shelf}" == "S" ) shelf = document.getElementById("shelf").value;

		var href = "&pactive="+active+
				"&paddress1="+address1+
				"&paddress2="+address2+
				"&pcode="+code+
				"&pemail="+email+
				"&pid_city="+id_city+
				"&pid_state="+id_state+
				"&pid_store_category="+id_store_category+
				"&platitude="+latitude+
				"&plongitude="+longitude+
				"&pname="+name+
				"&porderby="+orderby+
				"&pphone="+phone+
				"&ppostal="+postal+
				"&pshelf="+shelf;
		
		if ( "${accion}" == "add" )
			location.href = "storecategoryfromstoreadd.htm?accion=add&from=sto" + href + "&paccion=add&pidr="+id_retail;
		else
			location.href = "storecategoryfromstoreupd.htm?accion=add&from=sto" + href + "&paccion=upd&pids="+id_store;
	}

	function getcoords() {
		var state1 = document.getElementById("id_state");
		var state = state1.options[state1.selectedIndex].text;
		
		var city1 = document.getElementById("id_city");
		var city = city1.options[city1.selectedIndex].text;
		
		var address1 = document.getElementById("address1").value;
		var address2 = document.getElementById("address2").value;
		var country = "Mexico";
		
		var addressfull = address1 + ", " + address2 + ", " + state + ", " + city + ", " + country;
		
		if ( confirm("¿Deseas utilizar esta dirección?\n"+addressfull) ) {
            var geocoder = new google.maps.Geocoder();
            geocoder.geocode( { 'address': addressfull}, function(results, status) {
                if ( status == google.maps.GeocoderStatus.OK ) {
                    var latitude = results[0].geometry.location.lat();
                    var longitude = results[0].geometry.location.lng();
                    //alert(latitude + " ... " + longitude);
                    document.getElementById("latitude").value = latitude;
                    document.getElementById("longitude").value = longitude;
                    }
                });
            }
        return true;
	}
	
	
	$(document).ready(function() {
		
		 if(document.getElementById("address1").value.length < 1){	
			 document.getElementById("getcoords").disabled= true;
		 	}else{	
		 		document.getElementById("getcoords").disabled= false;
		 	}
		
		if ("${lat}" != ''){
			
			$('#address1').val("${cn}");
	    	$('#address2').val("${fr}");
	    	$('#postal').val("${cp}");
	    	$('#latitude').val("${lat}");
	    	$('#longitude').val("${lng}");
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
	    	
			if (("${cat}") != ""){
	        	$('#id_store_category option:contains("${cat}")').prop('selected', true);
	    	}
		}
		
        $('#getcoords').click(function () {
        	
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
         
    	});
	});

	// Se controla cualquier cambio en el filtro estado
    $( "#id_state" ).change( function() {
        RetailServiceBean.getAllCityByIdState(this.value, reply1);
        });

		var id_st = document.getElementById("id_state").value;
      
	var tot = 0;
	var reply1 = function(data) {
		tot = data.length;
		dwr.util.removeAllOptions("id_city");
		if ( data != null )
			dwr.util.addOptions("id_city",data,"id_city","name");
		if("${command.id_city}"==null || "${command.id_city}"!=0)
			keepSel();
	};
    update();
	function keepSel(){
	    var id=0;
		for(var i=0; i<tot; i++){
			id = document.getElementById('id_city').options[i].value;
			if(id == "${command.id_city}")
				document.getElementById('id_city').selectedIndex = i;			
		}
	}
	
        function update() {
            RetailServiceBean.getAllCityByIdState(id_st, reply1);
        }
        
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
							target: "form-side-left",
							title: "Formulario",
							content: "Aquí ingresaras la información necesaría para crear un nuevo cliente",
							placement: "top",
							zindex: 999
						},
						{
							target: 'form-side-left',
							title: "Campos requedidos",
							content: "Los campos con la palabra \"requerido\" son necesarios para crear un nuevo cliente",
							placement: "right",
							zindex: 999,
							yOffset: (formLeftPosition / 5)
						},
						{
							target: 'form-side-left',
							title: "Campos no requeridos",
							content: "Los campos sin la palabra \"requerido\" no son necesarios para crear un nuevo cliente, pero pueden ayudar a complementar la información de tu nuevo cliente",
							placement: "right",
							zindex: 999,
							yOffset: (formLeftPosition / 5) * 3
						},
						{
							target: 'getcoords',
							title: "Obtener coordenadas",
							content: "Clic para obtener las coordenadas de tu nuevo cliente",
							placement: "left",
							zindex: 999,
							yOffset: -15
						},
						{
							target: 'btnsave',
							title: "Agregar",
							content: "Clic para guardar tu cliente",
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
    			alert('Seleccione una categoría');
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
    	        	       			console.log(result);
    	        	       			
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
    		location.href = "geoclientlocation.htm?retail=" + "${retail.id_retail}" + "&cat=" + $('#id_store_category option:selected').text() + "&name=" + $('#name').val() + "&code=" + $('#code').val() + '&phone=' + $('#phone').val() + "&email=" + $('#email').val() + "&order=" + $('#orderby').val() + "&she=" + ($('#shelf').length > 0?$('#shelf').prop('checked'):true);
    	});
	</script>
	
</body>
</html>
