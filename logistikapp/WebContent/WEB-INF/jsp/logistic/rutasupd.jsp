<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>

	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Rutas - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	
	<!-- libraries -->
	<link type="text/css" rel="stylesheet" href="css/libs/font-awesome.css"/>
	<link type="text/css" rel="stylesheet" href="css/libs/nanoscroller.css"/>
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.fixedHeader.css">
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.tableTools.css">
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
    <!-- this page specific styles -->
    <style>
      html, body, #map
      {
        height: 600px;
        margin: 0px;
        padding: 0px
      }
      
      option:before {
    	content: " ";
    	height: 8px;
    	width: 8px;
    	border-radius: 5px;
    	border: 1px solid gray;
    	display: inline-block;
    	
	  }
	  
	  option.darkcyan:before { background: darkcyan; }
	  option.yellow:before { background: yellow; }
	  option.aqua:before { background: aqua; }
	  option.beige:before { background: beige; }
	  option.peru:before { background: peru; }
	  option.purple:before { background: purple; }
	  option.orange:before { background: orange; }
	  option.pink:before { background: pink; }
	  option.red:before { background: red; }
	  option.green:before { background: green; }
    </style>
</head>

<body class="fixed-header fixed-leftmenu theme-blue">

	<header class="navbar" id="header-navbar">
		<c:import url="/html/menu-top.jsp" />
	</header>
	
	<input type="hidden" id="listaBase" name="listaBase" value = "${listaBase}"/>
	
	<!-- Cargar coordenadas de todos los locales -->
	<input type="hidden" id="listCoords" name="listCoords"/>
	<c:set var="coords" value=""/>
	<c:set var="separador" value=""/>
	
	<c:forEach var="store" items="${listStore}">
		<c:set var="coords" value="${coords}${separador}${store.id_store}__${store.latitude}__${store.longitude}__${store.name}__${store.address1}__${store.address2}__${store.postal}__${store.shelf }"/>
		<c:set var="separador" value="@"/>
	</c:forEach>
	
	<script>
		document.getElementById("listCoords").value = '${coords}';
	</script>
	
	<!-- Cargar coordenadas de todos los locales Marcados -->
	<input type="hidden" id="listCoordsMark" name="listCoordsMark"/>
	<c:set var="coords" value=""/>
	<c:set var="separador" value=""/>
	
	<c:forEach var="storeM" items="${listStoreMarked}">
		<c:set var="coords" value="${coords}${separador}${storeM.id_store}/${storeM.latitude}/${storeM.longitude}/${storeM.name}/${storeM.address1}/${storeM.address2}/${storeM.postal}/${storeM.shelf }"/>
		<c:set var="separador" value="@"/>
	</c:forEach>
	<script>
		document.getElementById("listCoordsMark").value = '${coords}';
	</script>
	
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
								    <li><a href="dashboard.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                    <li><span><spring:message code="label.breadcrumb.logistic"/></span></li>
                                    <li><a href="rutas.htm"><span><spring:message code="label.breadcrumb.logistic.route"/></span></a></li>
                                    <li class="active"><a href="rutasupd.htm"><span><spring:message code="label.breadcrumb.logistic.route.upd"/></span></a></li>
								</ol>
								
								<div class="clearfix">
									<h1 class="pull-left"><spring:message code="label.logistic.route.upd.title"/></h1>
								</div>
							</div>
						</div>
						<!-- formulario de datos de ruta -->
						<div class="row">
							<div class="col-lg-12">
								<div class="main-box clearfix no-header">
									<div class="main-box-body clearfix">
										<div class="row">
		                                    <form id="form" method="post">
		                                    	<div id="ale"></div>
<!-- 
												<div id="msjColor"  class="col-lg-12" style="display:none" >
	                                                  <id="icon"class="fa fa-exclamation-triangle" ></i><span id="msjText">Debes seleccionar almenos una tienda</span>
	                                            </div><br><br>
 -->		                                    
	                        	                <div id="datos" class="col-lg-12 form-group">
	                        	                	<div class="col-lg-7" >
	                        	                		<label for="name">Nombre (requerido)</label>
	                                               		<spring:bind path="command.name">
	                                               			<c:if test="${status.error}">
																<div class="has-error">
			                                               			<span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span>
																</div>			                                               			
		                                               		</c:if>
	                                               		    <input type="text" class="form-control" onkeypress=" return nombre_valido(event)" id="name" name="name" placeholder="Nombre"  maxlength="50"  value="${status.value}"/> 
														</spring:bind>
													</div>
                  		      	                	<div class="col-lg-3">
                       								<label for="name">C&oacute;digo (requerido)</label>
							                		<spring:bind path="command.code"> 
														<c:if test="${status.error}">
															<div class="has-error">
																<span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span>
															</div>
														</c:if>
	                                               		<input type="text" class="form-control col-lg-12" id="code" name="code" placeholder="Codigo"  maxlength="10" value="${status.value}"/> 
													</spring:bind>
													</div>
	                    	    	                <div class="col-lg-2">
														<label for="name">Color </label>
	                            	               		<spring:bind path="command.color">
															<select class="form-control"  style="background-color:${ruta.color}; color:${ruta.color}" id="selColor" name="selColor">  
	    														<option  class="darkcyan" style="background-color:white;color:black!important;"	value="darkcyan">&nbsp;Agua Marina</option>
																	<option  class="yellow"   style="background-color:white;color:black!important;"    value="yellow" >&nbsp;Amarillo</option>
																	<option  class="aqua"     style="background-color:white;color:black!important;"	value="aqua">&nbsp;Azul</option>
																	<option  class="beige"    style="background-color:white;color:black!important;"	value="beige">&nbsp;Beige</option>
																	<option  class="peru"     style="background-color:white;color:black!important;"	value="peru">&nbsp;Caf&eacute;</option>
																	<option  class="purple"   style="background-color:white;color:black!important;"    value="purple" >&nbsp;Morado</option>
																	<option  class="orange"   style="background-color:white;color:black!important;"    value="orange" >&nbsp;Naranja</option>
																	<option  class="pink"     style="background-color:white;color:black!important;"    value="pink" >&nbsp;Rosa</option>  
		    														<option  class="red"      style="background-color:white;color:black!important;"    value="red" >&nbsp;Rojo</option>
		    														<option  class="green"    style="background-color:white;color:black!important;"    value="green" >&nbsp;Verde</option>
															</select>
														</spring:bind>	
													</div>
							    				</div>
												<input type="hidden" id="color" name="color" value="${ruta.color}"/>
												<input type="hidden" id="ids" name="ids"/>
												<input type="hidden" id="oldList" name="oldList"/>
		                                	</form>
										</div>
	                                	<div class="row  form-group">
		                                	<div class="col-lg-12">
												<div class="col-lg-6">
													<label for="plazaSel"> Plazas Comerciales </label>
													<select class="form-control" id="plazaSel" name="routeSel"> 
	  													<option value=""> Todas </option>
														<c:forEach var="pla" items="${listRetail}">
															<option value="${pla.id_retail}"><c:out value="${pla.name}"/></option>
														</c:forEach>
			                                        </select>           
												</div>
												<div class="col-lg-6">
													<label for="categorySel"> Categor&iacute;as de clientes </label>
													<select class="form-control" id="categorySel" name="categorySel">
	  													<option value=""> -- Selecciona -- </option>
	  													<c:forEach var="cat" items="${listCategory}">
		  													<option value="${cat.id_store_category}"><c:out value="${cat.name}"/></option>
	  													</c:forEach>                                       
													</select>           
												</div>
		                                	</div>
	                                	</div>
					    				<div class="row form-group">
					    					<div class="col-lg-12">
					    						<div class="col-lg-6 pull-right">
		                                            <div>
		                                            	<button id="doBtn" type="button" class="btn btn-success btn-xs pull-right"><i class="fa fa-check fa-lg"></i> <spring:message code="label.logistic.route.upd.button"/></button>
		                                            </div>
			                                        <div class="pull-right">
			                                        	<button type="button" onclick="location.href='rutas.htm';" class="btn btn-default btn-xs">
			                                        		<i class="fa fa-times fa-lg"></i> <spring:message code="label.logistic.route.cancel.button"/>
			                                        	</button>&nbsp;
			                                        </div>
					    						</div>
					    					</div>
					    				</div>
					    				
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-5">
								<div class="main-box clearfix">
									<div class="main-box-body clearfix" >
										<div class="table-responsive no-header">
											<!-- DATATABLE 2 -->
											<div class="table-responsive">
		                                        <header>
		                                        	<br>
		                                        	<h2>Clientes de ruta<span class="table-link badge pull-right" ><div id="strsel">0</div></span></h2>
		                                        </header>
												<table id="tableListSel" class="table-condensed table-striped table-bordered" >
													<thead>
														<tr>
															<th class="text-left" width="20px"><input type="checkbox" id="deselectall"/></th>
															<th>Clientes seleccionados</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
		                                        <br>
												<div class="table-responsive">
													<!-- DATATABLE 1 -->
			                                        <header>
			                                        	<h2>Asignar Clientes<span class="table-link badge pull-right" ><div id="strnosel">0</div></span></h2>
			                                        </header>
													<table id="tableList" class="table-condensed table-striped table-bordered" >
														<thead>
															<tr>
																<th width="20px"><input type="checkbox" id="selectall" value="A"/></th>
																<th>Listado de clientes</th>
															</tr>
															
														</thead>
														<tbody id="dataTable">
<!-- 
															<c:forEach var="sto" items="${listStore}" varStatus="vst">
																<tr id="tr${sto.id_store}">
															    	<td><input type="checkbox" id="${sto.id_store}" onclick="javascript:addStoreMap(${sto.id_store},'${sto.name}');"/> </td>
		                                                           	<td>${sto.name}</td>
																</tr>
															</c:forEach>
 -->														
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						    <div class="col-lg-7">
                                <div class="main-box clearfix no-header">
                                    <div class="main-box-body clearfix">
										<div class="col-lg-12" id="map"></div>
									</div>
								</div>
                            </div>
						</div><!-- /row -->
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
    <script src="js/jquery.slimscroll.min.js"></script>
    
    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
        
    <!-- this page specific inline scripts -->
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/dataTables.fixedHeader.js"></script>
    <script src="js/dataTables.tableTools.js"></script>
    
    <script type="text/x-script.multithreaded-js">

		$('#plazaSel').change(function(){
    		Concurrent.Thread.create(function(){
				getStr();
			});
    	});
    	
    	$('#categorySel').change(function(){
    		Concurrent.Thread.create(function(){
				getStr();
			});
    	});		

		Concurrent.Thread.create(function(){
			getStr();
		});

		$('#selectall').change(function()
    	{
			
			Concurrent.Thread.create(function(){
	    	    if( document.getElementById("selectall").value=="A"){
					swal({   
    					title: "Un momento!",   
    					text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
    					html: true, 
    					showConfirmButton: false 
    				});
	           		$("input[type=checkbox].mItem:not(checked)").click();
					$('#selectall').prop('checked',false);
					console.log("aqui");
	    			swal.close();
   		    		document.getElementById("selectall").value="N";
   	    		}
			});
    	});
    	// quitar seleccion de todo
    	$('#deselectall').change(function(){
			Concurrent.Thread.create(function(){
				swal({   
    				title: "Un momento!",   
    				text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
    				html: true, 
    				showConfirmButton: false 
    			});
    			$("input[type=checkbox].mItem:checked").click();

    			swal.close();
	 	    	//$(this).val()='A';
	
    			document.getElementById("selectall").value="A";
			});
    	});
		
	</script>
	
    <script>
    
    function nombre_valido(evt){ 
	    ky = evt.keyCode;
	    patron =/^[0-9a-zA-ZáéíóúàèìòùÀÈÌÒÙÁÉÍÓÚñÑüÜ_\s]+$/; 
	    k = String.fromCharCode(ky); 
	    return patron.test(k);
	}
    
    	$(document).ready(function(){
    		$('#selColor option[value="${ruta.color}"]').attr("selected", "selected");
    		// datatable "clientes no seleccionados"
			$('#tableList').DataTable({
	        	"scrollY": '95px',
	            'paging': false,
	            "language": {
	                "search": "Buscar Cliente:",
	                "zeroRecords":    "No se encontraron clientes"
	            },
	            'info'			: false,
	             'pageLength'	: 100,
	             'filter'		: false,
	             "bFilter"		: false,
	             'searching' 	: true,
	             'ordering'		: false, 
	             "order"		: [[ 1, 'asc' ]],
	             "aoColumnDefs"	: [
	                             	{ 'bSortable': false, 'aTargets': [ 0, 1 ] }
	                           	  ]                           	 
	        });
			$('#tableListSel').DataTable({
	        	"scrollY": '250px',
	            'paging': false,
	            "language": {
	                "search": "Buscar Cliente:",
	                "zeroRecords":    "No hay ningun cliente en esta ruta"
	            },
	            'info': false,
	             'pageLength': 100,
	             'filter': false,
	             "bFilter": false,
	             'searching' : true,
	             "ordering": false,
	             "order": [[ 1, 'asc' ]],
	             "aoColumnDefs": [
	                             	{ 'bSortable': false, 'aTargets': [ 0, 1 ] }
	                           	 ]
	        });
			$('#plazaSel').trigger("change");
    	}); // ready

    	// contador de las tiendas que se tienen en cada datatable
    	function countStr(){
			$('#strsel').text($('#tableListSel tbody tr input[type=checkbox]').length);
			$('#strnosel').text($('#tableList tbody tr input[type=checkbox]').length);
    	}
        
	    var cont= parseInt("${contLocales}");
    	var id_sup = "${id_supplier}";
        var listaBase = document.getElementById("listaBase").value;
		var ret=false;
		//clientes seleccionados
		ids=new Array();
		
	    $("#doBtn").click(function(){
			$('#name').focus();
			// clear search filter from datatable
        	$('#tableList').dataTable().fnFilter('');
        	$('#tableListSel').dataTable().fnFilter('');
	    	if(ids.length>0 && ids!="" && document.getElementById("name").value != "" && document.getElementById("code").value != ""){
				var sep="";
				for (var i=0; i < ids.length ; i++){
					document.getElementById("ids").value=document.getElementById("ids").value+sep+ids[i];
					sep="_";
				}
				document.getElementById("form").submit();
			}else{
				if(document.getElementById("name").value == ""){
					alert = '<div id="msjColor"  class="col-lg-12 alert alert-danger fade in"><id="icon"class="fa fa-exclamation-triangle" ></i><span id="msjText">Debes ingresar un nombre</span></div><br><br>';
				}else if(document.getElementById("code").value == "") {
					alert = '<div id="msjColor"  class="col-lg-12 alert alert-danger fade in"><id="icon"class="fa fa-exclamation-triangle" ></i><span id="msjText">Debes ingresar un código</span></div><br><br>';
				}else{
					alert = '<div id="msjColor"  class="col-lg-12 alert alert-danger fade in"><id="icon"class="fa fa-exclamation-triangle" ></i><span id="msjText">Debes seleccionar al menos una tienda</span></div><br><br>';
				}				 
				$('#ale').html(alert);			  		
			}
	    });
	
    	$('#plazaSel').change(function(){
    		getStr();
    	});
    	
    	$('#categorySel').change(function(){
    		getStr();
    	});

    	function getStr(){
    		swal({   
    			title: "Un momento!",   
    			text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
    			html: true, 
    			showConfirmButton: false 
    		});
    		$('#deselectall').prop('checked',false);
    		var AvailableStoreSearchCriteria = new Object();
    		
    		AvailableStoreSearchCriteria.idSupplier = "${id_supplier}";
    		AvailableStoreSearchCriteria.idStorecategory = $('#categorySel').val();
    		AvailableStoreSearchCriteria.idRetail = $('#plazaSel').val();
    		AvailableStoreSearchCriteria.idRoute = "${id_route}";

    		RetailServiceBean.getAvailableStoreByCriteria(AvailableStoreSearchCriteria,reply_str);
    	}
    	
    	var reply_str = function(data){
    		var coords="";
			var separador = "";
			var rowid;
			clearTable();
			clearTableSel();
			var jsonn =[];
			// se crea array con datos de todos los clientes que se agregan en la tabla
			for (var key=0; key<data.length;key++) {
				jsonn.push([ '<input class="mItem" type="checkbox" id="'+data[key].id_store+'" onclick="javascript:addStoreMap('+data[key].id_store+',\''+data[key].name+'\',\''+data[key].shelf+'\');" >', data[key].name + (data[key].shelf=='S'?' <i class="fa fa-star" style="color: #f1c40f;" data-toggle="tooltip" data-placement="bottom" title="Promoción"></i>':'')]);
			}
			// se agregan registros a la tabla
			if(data.length != 0)
				$('#tableList').dataTable().fnAddData(jsonn);
			// se le asigna un id a cada tr de la tabla a la cual se le agregaron los registros pasados
        	for (var key=0; key<data.length;key++) {
    			// se obtiene el nodo del row 
				rowid = $('#tableList').dataTable().fnGetNodes(key);
    			// se le agrega un id al tr 
				$(rowid).attr('id', 'tr'+data[key].id_store);
   				coords = coords + separador + data[key].id_store + "__" + data[key].latitude  + "__" + data[key].longitude + "__" + data[key].name + "__" + data[key].address1 + "__" + data[key].address2 + "__" + data[key].postal+ "__" + data[key].shelf;
   				separador = "@";
        	}
    		document.getElementById("listCoords").value=coords;
    		
  			for (var i = 0; i < Markers.length ; i++)	 
				Markers[i].setMap(null);
     		
  			addStores();
     		addMarkedStores();
   		 	
    		document.getElementById("selectall").value="A";
    		// obtiene las tiendas que deben estar seleccionadas
    		ids = new Array();
    		var listaCoordenadas = document.getElementById("listCoordsMark").value.split("@");
    		var id_tienda;
    		for(var a=0; a<listaCoordenadas.length; a++){
    			id_tienda = listaCoordenadas[a].split("/");
    			$('#'+id_tienda[0]).prop("checked", true);
    			if($('#'+id_tienda[0]).is(':checked')){
    				addStoreMap(id_tienda[0],id_tienda[3]);
    			}
    		}
    		swal.close();
    	};

		function selStoresMap(){
    		// obtiene las tiendas que deben estar seleccionadas
    		ids = new Array();
    		var listaCoordenadas = document.getElementById("listCoordsMark").value.split("@");
    		var id_tienda;
    		for(var a=0; a<listaCoordenadas.length; a++){
    			id_tienda = listaCoordenadas[a].split("/");
    			$('#'+id_tienda[0]).prop("checked", true);
    			if($('#'+id_tienda[0]).is(':checked')){
    				addStoreMap(id_tienda[0],id_tienda[3]);
    			}
    		}
		}
    	
    	$('#backBtn').click(function(){
    		window.location.replace("rutas.htm");	
    	});
    	// seleccionar todo
    	
    	$('#selColor').change(function() {
    		$('#selColor').css('background-color', $(this).val());
			$('#selColor').css('color', $(this).val());
			document.getElementById("color").value=document.getElementById("selColor").value;
    	});
     
        function addStoreMap(id_store,name,shelf){
	  		var found="n";													//bandera
	  		var mShelf = shelf;
	  		if(ids.length>0){												//Si hay ids seleccionados se entra al proceso de validacion
	  			for(var i = 0; i < ids.length; i++) {
	    			if(ids[i] == id_store){									//si la tienda seleccionada ya se encuentra en el arreglo
	    	    		ids.splice(i, 1);									//se elimina de la lista
	      				//cont--;												// se reduce el contador de locales seleccionados
	   	    		 	//document.getElementById('locales').innerHTML = cont;
	    	    		found="y"; 											//se cambia el estado de la bandera como si encontrado
	    	    		for (var i=0; i < Markers.length ; i++){ 									// se recorren los marcadores
	    	    			if(Markers[i].id == id_store){					//Se busca el id de la tienda a desmarcar en los markadores de maps
	    	      	            Markers[i].setMap(null);
	    	    				Markers[i].setIcon('img/'+(Markers[i].icon.indexOf("pin6") >= 0 ? "pin6_" : "icon-marker-")+'red.png');// se le asigna el color por default
	    	    				
	    	      	          }
	    	      	    }
	    	    		// unchecked checkbox clientes seleccionados
	    				var idxSel = $('#tableListSel tbody tr').length-1;
	    				if(idxSel==0)
	    					$('#deselectall').prop('checked', false);
		  		  		// elimina cliente de la tabla seleccionados clientes
		  		  		document.getElementById("selectall").value="A";
						var rowd = $('#tr'+id_store).closest('tr');
						$('#tableListSel').dataTable().fnDeleteRow(rowd);
		  		  		// se agrega registro a la abla de clientes seleccionados
		    			$('#tableList').dataTable().fnAddData([
	       					'<input class="mItem" type="checkbox" id="'+id_store+'" onclick="javascript:addStoreMap('+id_store+',\''+name+'\', \'' + mShelf + '\');" >', name + (mShelf=='S'?' <i class="fa fa-star" style="color: #f1c40f;" data-toggle="tooltip" data-placement="bottom" title="Promoción"></i>':'')]);
	        			var idxdt = $('#tableList tr').length-2;
	    				var rowid = $('#tableList').dataTable().fnGetNodes(idxdt);
	    				$(rowid).attr('id', 'tr'+id_store);
	    	   		 } // if(ids[i] == id_store) 
	    		} // for i<ids.length
	    	 	if(found=='n'){												// si no se encontro el marcador en la lista de seleccionados
		    		ids.push(id_store);										//se añade el id a la lista de is seleccionados  		
		    		for (var i=0; i < Markers.length; i++){ 									// se recorren los marcadores
	 	      	        if(Markers[i].id == id_store){						//Se busca el id de la tienda a marcar en los markadores de maps
	 	      	        	Markers[i].setMap(map);
	 	      	        	Markers[i].setIcon('img/'+(Markers[i].icon.indexOf("pin6") >= 0 ? "pin6_" : "icon-marker-")+'blue.png');// se le asigna el color por default
	 	      	        }
	 	      	    }
	  		  		// elimina cliente de la tabla no seleccionados clientes
					var rowd = $('#tr'+id_store).closest('tr');
					$('#tableList').dataTable().fnDeleteRow(rowd);
	  		  		// se agrega registro a la abla de clientes seleccionados
	    			$('#tableListSel').dataTable().fnAddData([
       					'<input class="mItem" type="checkbox" id="'+id_store+'" onclick="javascript:addStoreMap('+id_store+',\''+name+'\', \'' + mShelf + '\');" checked >', name + (mShelf=='S'?' <i class="fa fa-star" style="color: #f1c40f;" data-toggle="tooltip" data-placement="bottom" title="Promoción"></i>':'') 
       				]);

        			var idxdt = $('#tableListSel tr').length-2;
    				var rowid = $('#tableListSel').dataTable().fnGetNodes(idxdt);
    				$(rowid).attr('id', 'tr'+id_store);

    				var idxSel = $('#tableListSel tbody tr').length;
        			if(idxSel>0)
    					$('#deselectall').prop('checked', true);
		    	}
			}else{
				//se añade el id a la lista de is seleccionados
				ids.push(id_store);										  		
				for (var i=0; i < Markers.length; i++){ 										// se recorren los marcadores
					if(Markers[i].id == id_store){					//Se busca el id de la tienda a marcar en los markadores de maps
						Markers[i].setMap(map);
						Markers[i].setIcon('img/'+(Markers[i].icon.indexOf("pin6") >= 0 ? "pin6_" : "icon-marker-")+'blue.png');// se le asigna el color por default
					}
				}
  		  		// elimina cliente de la tabla "clientes no seleccionados"
				var rowd = $('#tr'+id_store).closest('tr');
				$('#tableList').dataTable().fnDeleteRow(rowd);
				// se agrega registro a la abla de "clientes seleccionados"
    			$('#tableListSel').dataTable().fnAddData([
					'<input class="mItem" type="checkbox" id="'+id_store+'" onclick="javascript:addStoreMap('+id_store+',\''+name+'\', \'' + mShelf + '\');" checked >', name + (mShelf=='S'?' <i class="fa fa-star" style="color: #f1c40f;" data-toggle="tooltip" data-placement="bottom" title="Promoción"></i>':'')
				]);
    			var idxdt = $('#tableListSel tr').length-2;
				var rowid = $('#tableListSel').dataTable().fnGetNodes(idxdt);
				// agrega id al tr
				$(rowid).attr('id', 'tr'+id_store);
				var idxSel = $('#tableListSel tbody tr').length;
				// cuando no hay datos en la tabla "clientes seleccionados" quita la seleccion del checkbox principal
				if(idxSel>0)
					$('#deselectall').prop('checked', true);
			}
	  		countStr();
	  	}     	
     	
		var num_str = ${contLocales}
		var AGS = new google.maps.LatLng('${lat}' || 0, '${lon}' || 0)
		//{ lat: '${lat}' || 0 , lng: '${lon}' || 0};
		var Markers=[];
     	var map = new google.maps.Map(document.getElementById('map'), {
   		    zoom: 12,
   		    center: AGS
   		});
     	function initialize() {
	   		//addStores();
	   		//addMarkedStores();
   		}
   		//CHECAR
     	// Adds a marker to the map.
   		function addMarker(location, id, name, dir1, dir2, postal,data, noMap) {
   			var shelf = data;
   			var icon = 'img/'+ ( shelf == 'S' ? 'pin6_red' : 'icon-marker-red')+'.png';
   		  	var marker= new google.maps.Marker({
				id: id,
	    		position: location,
	    		icon: icon,
	    		map: map
	  		});
   		  	if (noMap){
   		  		marker.setMap(null);
   		  	}
		 	Markers.push(marker);
		 	var contentString = '<div id="content" style="max-width:150px;">'+
            '<div id="siteNotice">'+
            '</div>'+
            '<h5 id="firstHeading"><b>'+name+'</b></h5>'+
            '<div id="bodyContent">'+
            '<p>'+dir1+','+dir2+
            '<p>'+postal+'</p>'+
            '</div>'+
            '</div>';
            marker.infowindow = new google.maps.InfoWindow({
                content: contentString
            });
   		  	google.maps.event.addListener(marker, 'click', function() {
				// clear search filter from datatable
				clearSearch();
				
        		addStoreMap(marker.id,name);
          		if(document.getElementById(marker.id).checked)
        			document.getElementById(marker.id).checked=true;
         		else
        			document.getElementById(marker.id).checked=false;
     		});
   		}
   		function addUsedMarker(location, id, name, dir1, dir2, postal,data) {
   			var shelf = data;
   			var icon = 'img/'+ ( shelf == 'S' ? 'pin6_blue' : 'icon-marker-blue')+'.png';
		  	var marker= new google.maps.Marker({
				id: id,
	    		position: location,
	    		icon: icon,
	    		map: map
	  		});
		 	Markers.push(marker);
		 	var contentString = '<div id="content" style="max-width:150px;">'+
            '<div id="siteNotice">'+
            '</div>'+
            '<h5 id="firstHeading"><b>'+name+'</b></h5>'+
            '<div id="bodyContent">'+
            '<p>'+dir1+','+dir2+
            '<p>'+postal+'</p>'+
            '</div>'+
            '</div>';
            marker.infowindow = new google.maps.InfoWindow({
                content: contentString
            });
		 	google.maps.event.addListener(marker, 'click', function() {
				// clear search filter from datatable
				clearSearch();
        		addStoreMap(marker.id, name);
          		if(document.getElementById(marker.id).checked)
        			document.getElementById(marker.id).checked=true;
         		else
        			document.getElementById(marker.id).checked=false;
     		});
   		}
   		function addStores(){
   			// tiendas que deben aparecer en el mapa
     		var listaCoordenadas = document.getElementById("listCoords").value.split("@");
         	for(var i=0; i<listaCoordenadas.length; i++){
                var store = listaCoordenadas[i].split("__");
                for(var j=0; j<store.length; j=j+7){
                   	var pos = new google.maps.LatLng(store[j+1],store[j+2]);
                    addMarker(pos, store[j], store[j+3], store[j+4], store[j+5], store[j+6],store[j+7],true);
                }
            }
         	
         	for(var i=0; i<Markers.length; i++){
                google.maps.event.addListener(Markers[i], 'click', function() {
                    this.infowindow.open(map,this);
                });
                google.maps.event.addListener(Markers[i], 'mouseout', function() {
                    this.infowindow.close();
                });
         	} // for(var i=0; i<Markers.length; i++)
      	}
   		function addMarkedStores(){	
   			// tiendas que deben estar seleccionadas
    		var listaCoordenadas = document.getElementById("listCoordsMark").value.split("@");
   			ids = new Array();
   			document.getElementById("oldList").value="";

   			$( "#selectall" ).prop( "checked", false );
   			var sep="";
   			var conc = "";
        	for(var i=0; i<listaCoordenadas.length; i++){
                var store = listaCoordenadas[i].split("/");
        		conc = conc + sep + store[0] ;
        		sep = "_";
                for(var j=0; j<store.length; j=j+7){
                  	var pos = new google.maps.LatLng(store[j+1],store[j+2]);
                  	ids.push(store[j]);
                  	// si los locales que se muestran en el datatatble " Clientes no seleccionados " no estan los loclaes que se tenian seleccionados, no los mapea
                  	if($('#'+store[j]).val()=="on")
	                    addUsedMarker(pos, store[j], store[j+3], store[j+4], store[j+5], store[j+6],store[j+7]);
                }
                $('#oldList').val(conc);
            }
         	for(var i=0; i<Markers.length; i++){
                google.maps.event.addListener(Markers[i], 'click', function() {
                    this.infowindow.open(map,this);
                });
                google.maps.event.addListener(Markers[i], 'mouseout', function() {
                    this.infowindow.close();
                });
         	} // for(var i=0; i<Markers.length; i++)
     	} // addMarkedStores
     	
   		// carga los datos que se cargan la primera vez que carga la pagina
  		google.maps.event.addDomListener(window, 'load', initialize);

		// limpia tabla de clientes no seleccionados		
  		function clearTable(){
    		var tablepru = $('#tableList').DataTable();
    		tablepru.clear().draw();
		}
		// limpia tabla de clientes seleccionados		
		function clearTableSel(){
    		var tablepru = $('#tableListSel').DataTable();
    		tablepru.clear().draw();
		}
		
		// elimina la relacion de las tiendas con la ruta
		function clearStore(id_ruta){
			if ( confirm("¿Está seguro que desea borrar las tiendas relacionadas a la ruta?") )
				location.href="rutasdel.htm?id_route="+id_ruta;
   		}
		
		function clearSearch(){
			$('#routeSel').focus();
			// clear search filter from datatable
        	$('#tableList').dataTable().fnFilter('');
        	$('#tableListSel').dataTable().fnFilter('');
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
							target: "name",
							title: "Nombre",
							content: "Ingresa el nuevo nombre con el que identificaras tu ruta",
							placement: "bottom",
							yOffset: -15
						},
						{
							target: 'code',
							title: "Código",
							content: "Ingresa el nuevo código de identificación para tu ruta",
							placement: "bottom",
							zindex: 999						
						},
						{
							target: 'selColor',
							title: "Color",
							content: "Selecciona el nuevo color con el que se identificaran los clientes pertenecientes a la ruta",
							placement: "left",
							zindex: 999,
							yOffset: -15
						},
						{
							target: 'plazaSel',
							title: "Plazas comerciales",
							content: "Selecciona la plaza comercial de la cual quieres mostrar clientes para asignar a tu ruta",
							placement: "right",
							zindex: 999,
							yOffset: -15
						},
						{
							target: 'categorySel',
							title: "Categorias de clientes",
							content: "Selecciona la categoría a la que pertenecerán tus clientes",
							placement: "left",
							zindex: 999,
							yOffset: -15
						},
						{
							target: 'tableListSel_wrapper',
							title: "Clientes de ruta",
							content: "Aquí se mostraran los clientes seleccionados en el listado de clientes",
							placement: "right",
							zindex: 999,
							yOffset: 10
						},
						{
							target: 'tableList_wrapper',
							title: "Listado de clientes",
							content: "Selecciona los clientes que pertenecerán a la ruta",
							placement: "right",
							zindex: 999,
							yOffset: $('#tableList_wrapper').height() / 2
						},
						{
							target: 'map',
							title: "Mapa",
							content: "Aquí se mapearan los clientes seleccionados en el listado de clientes",
							placement: "left",
							zindex: 999
						},
						{
							target: 'doBtn',
							title: "Modificar",
							content: "Clic para guardar los cambios de la ruta",
							placement: "left",
							zindex: 999,
							yOffset: -15
						}
					],
					showPrevButton: true
				};
			hopscotch.startTour(tour);
		});
    	
    	
    	document.getElementById('plazaSel').value=${selected_retail};
    </script>


</body>

</html>