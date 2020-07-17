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
    
    <link rel="stylesheet" type="text/css" href="css/libs/nifty-component.css"/>

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
        height: 582px;
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
	
	<!-- Cargar coordenadas de todos los locales -->
	<c:set var="coords" value=""/>
	<c:set var="infStore" value=""/>
	<c:set var="separador" value=""/>
	<input type="hidden" id="listCoords" name="listCoords"/>
	
	<c:forEach var="store" items="${listStore}">
		<c:set var="coords" value="${coords}${separador}${store.id_store}/${store.latitude}/${store.longitude}/${store.name}/${store.address1}/${store.address2}/${store.postal}/${store.shelf}"/>
		<c:set var="separador" value="@"/>
	</c:forEach>
	<script>
		document.getElementById("listCoords").value = "${coords}";
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
									
									    <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                        <li><span><spring:message code="label.breadcrumb.logistic"/></span></li>
                                        <li><a href="rutas.htm"><span><spring:message code="label.breadcrumb.logistic.route"/></span></a></li>
                                        <li class="active"><a href="rutasadd.htm"><span><spring:message code="label.breadcrumb.logistic.route.add"/></span></a></li>
									</ol>
									
									<div class="clearfix">
										<h1 class="pull-left"><spring:message code="label.logistic.route.add.title"/></h1>
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
		                        	                <div class="col-lg-12">
		                        	                	<div class="col-lg-7 form-group" >
		                        	                		<label for="name">Nombre (requerido)</label>
		                                               		<spring:bind path="command.name">
		                                               			<c:if test="${status.error}">
																	<div class="has-error">
				                                               			<span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span>
																	</div>			                                               			
			                                               		</c:if>
	                                               		    	<input type="text" class="form-control" id="name" name="name" placeholder="Nombre de la ruta"  maxlength="50" value="${status.value}"/> 
															</spring:bind>
														</div>
	                  		      	                	<div class="col-lg-3 form-group">
	                        								<label for="name">C&oacute;digo (requerido)</label>
									                		<spring:bind path="command.code"> 
																<c:if test="${status.error}">
																	<div class="has-error">
																		<span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span>
																	</div>
																</c:if>
			                                               		<input type="text" class="form-control col-lg-12" id="code" name="code" placeholder="C&oacute;digo"  maxlength="10" value="${status.value}"/> 
															</spring:bind>
														</div>
		                    	    	                <div class="col-lg-2 form-group">
															<label for="name">Color </label>
		                            	               		<spring:bind path="command.color">
																<select class="form-control"  style="background-color:darkcyan; color:darkcyan" id="selColor" name="selColor">
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
																<input type="hidden" id="color" name="color" value="darkcyan"/>
																<input type="hidden" id="ids" name="ids" value=""/>
															</spring:bind>	
														</div>
								    				</div>
			                                	</form>
											</div>
		                                	<div class="row">
			                                	<div class="col-lg-12">
													<div class="col-lg-6 form-group">
														<label for="plazaSel">Plazas Comerciales </label>
														<select class="form-control" id="plazaSel" name="routeSel"> 
		  													<!-- <option value=""> Todas </option>-->
															<c:forEach var="pla" items="${listRetail}">
																<option value="${pla.id_retail}"><c:out value="${pla.name}"/></option>
															</c:forEach>
				                                        </select>           
													</div>
													<div class="col-lg-6 form-group">
														<label for="categorySel">Categor&iacute;as de clientes</label>
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
			                                            	<button id="doBtn" type="button" class="btn btn-success pull-right">
			                                            	    <i class="fa fa-check"></i> <spring:message code="label.logistic.route.add.button"/>
			                                            	</button>
			                                            </div>
				                                        <div class="pull-right">
				                                        	<button type="button" onclick="location.href='rutas.htm';" class="btn btn-default">
				                                        		<i class="fa fa-times"></i> <spring:message code="label.logistic.route.cancel.button"/>
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
									<div class="main-box clearfix no-header">
										<div class="main-box-body clearfix">
											<!-- DATATABLE 1 -->
											<div class="table-responsive">
		                                        <header>
		                                        	<h2>Asignar Clientes <button type="button" class="btn btn-primary btn-sm pull-right" id="btnSelectStores" onclick="$('#modal-codes').toggleClass('md-show')"><i class="fa fa-list-ol"></i></button><span class="table-link badge pull-right" ><div id="strnosel">0</div></span></h2>
		                                        </header>
												<table id="tableList" class="table-condensed table-striped table-bordered" >
													<thead>
														<tr>
															<th width="20px"><input type="checkbox" id="selectall" value="A"/></th>
															<th>Listado de clientes</th>
														</tr>
													</thead>
													<tbody id="dataTable">
														<!--<c:forEach var="sto" items="${listStore}" varStatus="vst">
															<tr id="tr${sto.id_store}" >
														    	<td><input class="mItem" type="checkbox" id="${sto.id_store}" onclick="javascript:addStoreMap(${sto.id_store},'${sto.name}', '${sto.shelf }');"/></td>
														        <td>${sto.name}<c:if test="${sto.shelf == 'S' }"> <i class="fa fa-star" style="color: #f1c40f;" data-toggle="tooltip" data-placement="bottom" title="Promoción"></i></c:if></td>
														   
															</tr>
														</c:forEach>-->
													</tbody>
												</table>
												<!-- DATATABLE 2 -->
												<div class="table-responsive">
			                                        <header>
			                                        	<br>
			                                        	<h2>Clientes de ruta<span class="table-link badge pull-right" ><div id="strsel">0</div></span></h2>
			                                        </header>
													<table id="tableListSel" class="table-condensed table-striped table-bordered" >
														<thead>
															<tr>
																<th width="20px"><input type="checkbox" id="deselectall"/></th>
																<th>Clientes seleccionados</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div><br>
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
	
	<div class="md-modal md-effect-1" id="modal-codes">
		<div class="md-content">
			<div class="modal-header">
				<button type="button" class="md-close close" onclick="$('#modal-codes').toggleClass('md-show')">&times;</button>
				<h4 class="modal-title">Ingresar códigos</h4>
			</div>
			<div class="modal-body">
				<h1 class="text-primary">Ingresa un listado de códigos de clientes a seleccionar</h1>
				<h3 class="text-info">Separa tus códigos por coma, espacio o salto de linea</h3>
				<textarea name="ta-codes" id="ta-codes" class="form-control" rows="5"></textarea>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" onclick="$('#modal-codes').toggleClass('md-show')">Cancelar</button>
				<button type="button" class="btn btn-success" id="btn-search-code">Buscar</button>
			</div>
		</div>
	</div>

	<div class="md-overlay"></div>

    <!-- global scripts -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.nanoscroller.min.js"></script>
    <script src="js/jquery.slimscroll.min.js"></script>
    
    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
        
    <!-- this page specific scripts -->
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/dataTables.fixedHeader.js"></script>
    <script src="js/dataTables.tableTools.js"></script>
    
    <script src="js/modalEffects.js"></script>
  
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
		$('#selectall').change(function(){
    	    if($('#selectall').is(':checked')){
				swal({   
    				title: "Un momento!",   
    				text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
    				html: true, 
    				showConfirmButton: false 
    			});
           		seleccionarTodo();
   	    		document.getElementById("selectall").value="N";
   	    	}
    	});
    	$('#deselectall').change(function(){
			swal({   
    			title: "Un momento!",   
    			text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
    			html: true, 
    			showConfirmButton: false 
    		});
    		seleccionarNinguno();
    		document.getElementById("selectall").value="A";
    	});
		
	</script>

    <!-- this page specific inline scripts -->
    <script>

    	$(document).ready(function(){
		
    		// datatable no seleccionados
			$('#tableList').DataTable({
	        	"scrollY": '95px',
	            'paging': false,
	            "language": {
	                "search": "Buscar Cliente:",
	                "zeroRecords":    "No se encontraron clientes"
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
    		// datatable seleccionados
			$('#tableListSel').DataTable({
	        	"scrollY": '270px',
	            'paging': false,
	            "language": {
	                "search": "Buscar Cliente:",
	                "zeroRecords":    "No hay ningun cliente en esta ruta"
	              },
	            'info': false,
	             'pageLength': 100,
	             'filter': false,
	             "bFilter": false,
	             'searching' : false,
	             "ordering": false,
	             "order": [[ 1, 'asc' ]],
	             "aoColumnDefs": [
	                             	{ 'bSortable': false, 'aTargets': [ 0, 1 ] }
	                           	 ]
	        });
    		countStr();

    	}); // ready

    	// contador de las tiendas que se tienen en cada datatable
    	function countStr(){
			$('#strsel').text($('#tableListSel tbody tr input[type=checkbox]').length);
			$('#strnosel').text($('#tableList tbody tr input[type=checkbox]').length);
    	}

    	ids=[];
	    var cont=0;
		var id_sup = "${id_supplier}";
		var ret=false;
		var alert; 
    	$("#doBtn").click(function(){
			// clear search filter from datatable
        	$('#tableList').dataTable().fnFilter('');
        	$('#tableListSel').dataTable().fnFilter('');        	
       
   			if(ids.length>0 && document.getElementById("name").value != "" && document.getElementById("code").value != ""){   				
   				var sep="";
   				for (var i=0; i < ids.length; i++){
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

    	function getStr(){

    		swal({   
    			title: "Un momento!",   
    			text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
    			html: true, 
    			showConfirmButton: false 
    		});
    		console.log('Aquí');
    		$('#deselectall').prop('checked',false);
    		var AvailableStoreSearchCriteria = new Object();
    		
    		AvailableStoreSearchCriteria.idSupplier = "${id_supplier}";
    		AvailableStoreSearchCriteria.idStorecategory = $('#categorySel').val();
    		AvailableStoreSearchCriteria.idRetail = $('#plazaSel').val();

    		RetailServiceBean.getAvailableStoreByCriteria(AvailableStoreSearchCriteria,reply_str);
    	}
    	
    	var reply_str = function(data){
    		console.log(data);
    		var coords="";
			var separador = "";
			var rowid;
			var variable;
			clearTable();
			clearTableSel();
			var jsonn =[];
			for (var key=0; key<data.length;key++){
  				variable = data[key].name; 
				jsonn.push([ '<input class="mItem item-' + data[key].code + '" type="checkbox" id="'+data[key].id_store+'" onclick=\"javascript:addStoreMap('+data[key].id_store+',\''+variable+'\',\'' + data[key].shelf + '\',\'' + data[key].code + '\');">', data[key].name + (data[key].shelf=='S'?' <i class="fa fa-star" style="color: #f1c40f;" data-toggle="tooltip" data-placement="bottom" title="Promoción"></i>':'')]);
			}
			if(data.length != 0)
				$('#tableList').dataTable().fnAddData(jsonn);
			
    		for (var key=0; key<data.length;key++){
   				rowid = $('#tableList').dataTable().fnGetNodes(key);
   				$(rowid).attr('id', 'tr'+data[key].id_store);

   				coords = coords + separador + data[key].id_store + "/" + data[key].latitude + "/" + data[key].longitude + "/" + data[key].name + "/" + data[key].address1 + "/" + data[key].address2 + "/" + data[key].postal + "/" + data[key].shelf;
   				separador = "@";
           	}

    		document.getElementById("listCoords").value=coords;
   			for (var i = 0; i < Markers.length; i++)	 
				Markers[i].setMap(null);
   		 	addStores();
    		countStr();
    		swal.close();
    	};
    	
    	$("#routeSel").change(function(){
    		if(document.getElementById("routeSel").value=="all"){
				document.getElementById("selFilter").style.display="none";
				RetailServiceBean.getAvailableStoreByIdSupplier(id_sup, dataSelAll);
   			}
    		else if(document.getElementById("routeSel").value=="cat"){
   				ret=false;
   				document.getElementById("selFilter").style.display="inline"; 
       			RetailServiceBean.getAllStoreCategoryByIdSupplier(id_sup, dataSelCat);
			}
    		else if(document.getElementById("routeSel").value=="ret"){
				document.getElementById("selFilter").style.display="inline"; 
				var RetailSearchCriteria = new Object();
				ret=true;
				RetailSearchCriteria.id_supplier = id_sup;
       			RetailServiceBean.getRetailByCriteria(RetailSearchCriteria, dataSelRet);
			}
    	});

        function addStoreMap(id_store,name, shelf, code){
        		clearSearch();
    	  		var found="n";
    	  		var mShelf = shelf;
    	  		var mCode = code;
    	  		if(ids.length>0){
    	  			for(var i = 0; i < ids.length; i++) {
    	    			if(ids[i] == id_store){
    	    	    		ids.splice(i, 1);
    	      				cont--;
    	    	    		found="y";  		
    	    	    		for (var i=0; i < Markers.length; i++){	 
    	    	      	        if(Markers[i].id == id_store){
    	    	      	            Markers[i].setIcon('img/'+(Markers[i].icon.indexOf("pin6") >= 0 ? "pin6_" : "icon-marker-")+'red.png');
    	    	      	        }
    	    	      	    }
    	    				var idxSel = $('#tableListSel tbody tr').length-1;
    	    				if(idxSel==0)
    	    					$('#deselectall').prop('checked', false);
    		  		  		// elimina cliente de la tabla no seleccionados clientes
    						var rowd = $('#tr'+id_store).closest('tr');
    						$('#tableListSel').dataTable().fnDeleteRow(rowd);
    		  		  		// se agrega registro a la tabla de clientes seleccionados
    		    			$('#tableList').dataTable().fnAddData([
    	       					'<input class="mItem item-' + mCode + '" type="checkbox" id="'+id_store+'" onclick="javascript:addStoreMap('+id_store+',\''+name+'\', \'' + mShelf + '\',\'' + mCode + '\');" >', name + (mShelf == 'S'?' <i class="fa fa-star" style="color: #f1c40f;" data-toggle="tooltip" data-placement="bottom" title="Promoción"></i>':'')
    	       				]);
    	        			var idxdt = $('#tableList tr').length-2;
    	    				var rowid = $('#tableList').dataTable().fnGetNodes(idxdt);
    	    				$(rowid).attr('id', 'tr'+id_store);
    	    	   		 }
    	    		}
    	    	 	if(found=='n'){
    		    		ids.push(id_store);  		
    		    		cont++;
    		    		for (var i=0; i < Markers.length; i++){	 
    		      	        if(Markers[i].id == id_store)
    		      	            Markers[i].setIcon('img/'+(Markers[i].icon.indexOf("pin6") >= 0 ? "pin6_" : "icon-marker-")+'blue.png');
    		      	    } // for (i in Markers)
    	  		  		// elimina cliente de la tabla no seleccionados clientes
    					var rowd = $('#tr'+id_store).closest('tr');
    					$('#tableList').dataTable().fnDeleteRow(rowd);
    	  		  		// se agrega registro a la abla de clientes seleccionados
    	    			$('#tableListSel').dataTable().fnAddData([
           					'<input class="mItem item-' + mCode + '" checked type="checkbox" id="'+id_store+'" onclick="javascript:addStoreMap('+id_store+',\''+name+'\', \'' + mShelf + '\', \'' + mCode + '\');" >', name + (mShelf == 'S'?' <i class="fa fa-star" style="color: #f1c40f;" data-toggle="tooltip" data-placement="bottom" title="Promoción"></i>':'')
           				]);
            			var idxdt = $('#tableListSel tr').length-2;
        				var rowid = $('#tableListSel').dataTable().fnGetNodes(idxdt);
        				$(rowid).attr('id', 'tr'+id_store);

        				var idxSel = $('#tableListSel tbody tr').length;
            			if(idxSel>0)
        					$('#deselectall').prop('checked', true);
    		    	} // if(found=='n')
    			}else{
    				cont++;
      				ids.push(id_store);
      		  		for (var i=0; i < Markers.length; i++){	 
      		  	        if(Markers[i].id == id_store)
      		  	            Markers[i].setIcon('img/'+(Markers[i].icon.indexOf("pin6") >= 0 ? "pin6_" : "icon-marker-")+'blue.png');
      		  	    }
      		  		// elimina cliente de la tabla no seleccionados clientes
    				var rowd = $('#tr'+id_store).closest('tr');
    				$('#tableList').dataTable().fnDeleteRow(rowd);
    				// se agrega registro a la abla de clientes seleccionados
        			$('#tableListSel').dataTable().fnAddData([
    					'<input class="mItem item-' + mCode + '" type="checkbox" id="'+id_store+'" onclick="javascript:addStoreMap('+id_store+',\''+name+'\', \'' + mShelf + '\', \'' + mCode + '\');" checked >', name + (mShelf == 'S'?' <i class="fa fa-star" style="color: #f1c40f;" data-toggle="tooltip" data-placement="bottom" title="Promoción"></i>':'')
    				]);
        			var idxdt = $('#tableListSel tr').length-2;
    				var rowid = $('#tableListSel').dataTable().fnGetNodes(idxdt);
    				$(rowid).attr('id', 'tr'+id_store);
    				var idxSel = $('#tableListSel tbody tr').length;
    				if(idxSel>0)
    					$('#deselectall').prop('checked', true);
      			} // else (ids.length>0)
        		countStr();
      	}
        
    	var dataSelAll = (function(data){
    		var coords="";
			var separador = "";
			var rowid;
			clearTable();
			clearTableSel();
			var jsonn =[];
			for (var key=0; key<data.length;key++)
				jsonn.push([ '<input class="mItem item-' + data[key].code + '" type="checkbox" id="'+data[key].id_store+'" onclick="javascript:addStoreMap('+data[key].id_store+',\''+data[key].name+'\', \'' + data[key].shelf + '\', \'' + data[key].code + '\');" >', data[key].name + (data[key].shelf=='S'?' <i class="fa fa-star" style="color: #f1c40f;" data-toggle="tooltip" data-placement="bottom" title="Promoción"></i>':'')]);
			$('#tableList').dataTable().fnAddData(jsonn);
        	for (var key=0; key<data.length;key++){
    			// se obtiene el nodo del row 
				rowid = $('#tableList').dataTable().fnGetNodes(key);
    			// se le agrega un id al tr 
				$(rowid).attr('id', 'tr'+data[key].id_store);
        	  	coords = coords + separador + data[key].id_store + "/" + data[key].latitude + "/" + data[key].longitude  + "/" + data[key].name + "/" + data[key].address1 + "/" + data[key].address2 + "/" + data[key].postal + "/" + data[key].shelf ;
				separador = "@"; 
        	}
        	
    		document.getElementById("listCoords").value=coords;
  			for (var i = 0; i < Markers.length; i++){	 
				Markers[i].setMap(null);
  			}
   		 	addStores();
   		 	
    		document.getElementById("selectall").value="A";
    		countStr();
    	});	
    	
    	var dataSelCat = (function(data)
    	{
    		dwr.util.removeAllOptions("selFilter");
    		if(data.length > 0){
    			dwr.util.addOptions("selFilter",data,"id_store_category","name");
    		}
    		var sel = document.getElementById("selFilter");
    		sel.selectedIndex = 0;
    		$("#selFilter").trigger("change");
    	});	
    	
    	var dataSelRet = (function(data){
    		dwr.util.removeAllOptions("selFilter");
    		if(data.length > 0){
    			dwr.util.addOptions("selFilter",data,"id_retail","name");
    		}
    		var sel = document.getElementById("selFilter");
    		sel.selectedIndex = 0;
    		$("#selFilter").trigger("change");
    	});	
    	$("#selFilter").change(function(){	
    		if(ret){
   				var id_ret = document.getElementById("selFilter").value;
   				
   				var AvailableStoreByIdRetail = new Object();
   				AvailableStoreByIdRetail.idSupplier = id_sup;
   				AvailableStoreByIdRetail.idRetail   = id_ret;
       			
   				RetailServiceBean.getAvailableStoreByIdRetail(AvailableStoreByIdRetail, dataRet);
   			}else{
       			var id_sto_cat = document.getElementById("selFilter").value;
       			
       			var AvailableStoreByIdStoreCategory = new Object();
       			AvailableStoreByIdStoreCategory.idSuppler       = id_sup;
       			AvailableStoreByIdStoreCategory.idStorecategory = id_sto_cat;
       			
       			RetailServiceBean.getAvailableStoreByIdStoreCategory(AvailableStoreByIdStoreCategory, dataCatRet);
   			}
    	});

    	var dataCatRet = (function(data){
    		var coords="";
			var separador = "";
			var rowid;
			clearTable();
			clearTableSel();
			var jsonn =[];
			for (var key=0; key<data.length;key++)
				jsonn.push([ '<input class="mItem item-' + data[key].code + '" type="checkbox" id="'+data[key].id_store+'" onclick="javascript:addStoreMap('+data[key].id_store+',\''+data[key].name+'\', \'' + data[key].shelf + '\', \'' + data[key].code + '\');" >', data[key].name + data[key].shelf=='S'?'<i class="fa fa-star" style="color: #f1c40f;" data-toggle="tooltip" data-placement="bottom" title="Promoción"></i>':''  ]);
			
			if(data.length != 0)
				$('#tableList').dataTable().fnAddData(jsonn);
			
    		for (var key=0; key<data.length;key++){
   				rowid = $('#tableList').dataTable().fnGetNodes(key);
   				$(rowid).attr('id', 'tr'+data[key].id_store);
				coords = coords + separador + data[key].id_store + "/" + data[key].latitude  + "/" + data[key].longitude + "/" + data[key].name + "/" + data[key].address1 + "/" + data[key].address2 + "/" + data[key].postal + "/" + data[key].shelf;
   				separador = "@";
   			}    

    		document.getElementById("listCoords").value=coords;
   			for (var i = 0; i < Markers.length; i++){	 
				Markers[i].setMap(null);
  			}
   		 	addStores();
    		 	
    		document.getElementById("selectall").value="A";
    		countStr();
    	});
    	var dataRet = (function(data){
    		var coords="";
			var separador = "";
			var rowid;
			var variable;
			clearTable();
			clearTableSel();
			var jsonn =[];
			for (var key=0; key<data.length;key++){
  				variable = data[key].name; 
				jsonn.push([ '<input class="mItem item-' + data[key].code + '" type="checkbox" id="'+data[key].id_store+'" onclick=\"javascript:addStoreMap('+data[key].id_store+',\''+variable+'\', \'' + data[key].shelf + '\', \'' + data[key].code + '\');">', data[key].name] + data[key].shelf=='S'?'<i class="fa fa-star" style="color: #f1c40f;" data-toggle="tooltip" data-placement="bottom" title="Promoción"></i>':'');
			}
			if(data.length != 0)
				$('#tableList').dataTable().fnAddData(jsonn);
			
    		for (var key=0; key<data.length;key++){
   				rowid = $('#tableList').dataTable().fnGetNodes(key);
   				$(rowid).attr('id', 'tr'+data[key].id_store);

   				coords = coords + separador + data[key].id_store + "/" + data[key].latitude + "/" + data[key].longitude + "/" + data[key].name + "/" + data[key].address1 + "/" + data[key].address2 + "/" + data[key].postal + "/" + data[key].shelf;
   				separador = "@";
           	}

    		document.getElementById("listCoords").value=coords;
   			for (var i = 0; i < Markers.length; i++){	 
				Markers[i].setMap(null);
  			}
   		 	addStores();
    		document.getElementById("selectall").value="A";
    		countStr();
    	});
    	
    	$('#backBtn').click(function(){
    		window.location.replace("rutas.htm");	
    	 });
    	$('#selectall').change(function(){
    	    if($('#selectall').is(':checked')){
           		seleccionarTodo();
   	    		document.getElementById("selectall").value="N";
   	    	}
    	});
    	$('#deselectall').change(function(){
    		seleccionarNinguno();
    		document.getElementById("selectall").value="A";
    	});
    	$('#selColor').change(function() {
    		$('#selColor').css('background-color', $(this).val());
			$('#selColor').css('color', $(this).val());
			document.getElementById("color").value=document.getElementById("selColor").value;
    	});
    	
        function seleccionarTodo(){    
        	swal({   
    			title: "Un momento!",   
    			text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
    			html: true, 
    			showConfirmButton: false 
    		});
    	    $("input[type=checkbox].mItem:not(checked)").click();
			$('#selectall').prop('checked',false);
	    	//$(this).val()='N';
	    	swal.close();
      	}
     	function seleccionarNinguno(){    
     		swal({   
    			title: "Un momento!",   
    			text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
    			html: true, 
    			showConfirmButton: false 
    		});
     		$("input[type=checkbox].mItem:checked").click();

 	    	//$(this).val()='A';
    		swal.close();
    	}

		var AGS = { lat: 21.88072496638374 , lng: -102.2961151599884};
		var Markers=[];
		var map = new google.maps.Map(document.getElementById('map'), {
		zoom: 5,
		center: AGS
		});
     	function initialize() {
	   		/*/ This event listener calls addMarker() when the map is clicked.google.maps.event.addListener(map, 'click', function(event) {addMarker(event.latLng, map);});*/
	   		// Add a marker at the center of the map.
	   		// addMarker(AGS, map);
	   		addStores();
   		}

 		// Adds a marker to the map.
		function addMarker(location, id, name, dir1, dir2, postal,shelf, code) {
			// Add the marker at the clicked location, and add the next-available label
			// from the array of alphabetical characters.		
			var mShelf = shelf;
			var code = code;
			var icon = 'img/'+ ( shelf == 'S' ? 'pin6_red' : 'icon-marker-red')+'.png';
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
		  //'<p><b>Direccion</b> '+dir1+','+dir2+
		 //'<p><b>CP</b> '+postal+'</p>'+
			'<p><b></b> '+dir1+','+dir2+
			'<p><b></b> '+postal+'</p>'+
			'</div>'+
			'</div>';
			marker.infowindow = new google.maps.InfoWindow({
			    content: contentString
			});
			google.maps.event.addListener(marker, 'click', function() {
				// clear search filter from datatable
				clearSearch();
				// infowindow.open(map);
				//Change the marker icon
				addStoreMap(marker.id,name, shelf, code);
				 	          
				if(document.getElementById(marker.id).checked)
					document.getElementById(marker.id).checked=true;
				else
					document.getElementById(marker.id).checked=false;
			});
		}
			
			
		function addStores(){    
			cont=0;
			var cnt_str = 0;
			document.getElementById('selectall').value='N';
   			$("#selectall").prop("checked",false);
     		var listaCoordenadas = document.getElementById("listCoords").value.split("@");
         	for(var i=0; i<listaCoordenadas.length; i++){
                var store = listaCoordenadas[i].split("/");
                for(var j=0; j<store.length; j=j+6){
               		var pos = new google.maps.LatLng(store[j+1],store[j+2]);
                    addMarker(pos, store[j], store[j+3], store[j+4], store[j+5], store[j+6],store[j+7]);
                } // for(var j=0; j<store.length; j=j+3)
            } // for(var i=0; i<listaCoordenadas.length; i++)
         	
         	for(var i=0; i<Markers.length; i++){
                google.maps.event.addListener(Markers[i], 'click', function() {
                    this.infowindow.open(map,this);
                });
                google.maps.event.addListener(Markers[i], 'mouseout', function() {
                    this.infowindow.close();
                });
         	} // for(var i=0; i<Markers.length; i++)
      	}
   		google.maps.event.addDomListener(window, 'load', initialize);
    		
   		function clearTable(){
       		var tablepru = $('#tableList').DataTable();
       		tablepru.clear().draw();
   		}
    		
   		function clearTableSel(){
       		var tablepru = $('#tableListSel').DataTable();
       		tablepru.clear().draw();
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
							content: "Ingresa el nombre con el que identificaras tu nueva ruta",
							placement: "bottom",
							yOffset: -15
						},
						{
							target: 'code',
							title: "Código",
							content: "Ingresa el código de identificación para la nueva ruta",
							placement: "bottom",
							zindex: 999						
						},
						{
							target: 'selColor',
							title: "Color",
							content: "Selecciona el color con el que se identificaran los clientes que pertenecerán a la nueva ruta",
							placement: "left",
							zindex: 999,
							yOffset: -15
						},
						{
							target: 'plazaSel',
							title: "Plazas comerciales",
							content: "Selecciona la plaza comercial de la cual quieres mostrar clientes los cuales podrás asignar a tu nueva ruta",
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
							target: 'tableList_wrapper',
							title: "Listado de clientes",
							content: "Selecciona los clientes que pertenecerán a la nueva ruta",
							placement: "right",
							zindex: 999,
							yOffset: $('#tableList_wrapper').height() / 2
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
							target: 'map',
							title: "Mapa",
							content: "Aquí se mapearan los clientes seleccionados en el listado de clientes",
							placement: "left",
							zindex: 999
						},
						{
							target: 'doBtn',
							title: "Crear",
							content: "Clic para crear tu ruta",
							placement: "left",
							zindex: 999,
							yOffset: -15
						}
					],
					showPrevButton: true
				};
			hopscotch.startTour(tour);
		});
    	
    	$('#btn-search-code').on('click', function(){
			swal({   
    			title: "Un momento!",   
    			text: 'Espere por favor <br><br><div class="progress" style="height:20px;"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">Cargando...</div></div>',   
    			html: true, 
    			showConfirmButton: false 
    		});
			var tmp = $('#ta-codes').val()
			var codes = []
			if (tmp) {
				if (tmp.indexOf(',') > -1) {
					codes = tmp.split(',')
				} else if (tmp.indexOf('\n') > -1) {
					codes = tmp.split('\n')
				} else if (tmp.indexOf(' ') > -1) {
					codes = tmp.split(' ')
				} else {
					codes = tmp.split()
				}
				
				console.log(codes)
				
				if (codes) {
					codes.forEach(function(e, i) {
						
						var $item = $('#tableList tbody tr td .item-' + e).first()
						if (!$item.is(':checked')){
							$item.click()
						}
						console.log($item)
					})
				}
				$('#ta-codes').val('')
				$('#modal-codes').toggleClass('md-show')
			}
			swal('Finalizo', 'Terminamos de buscar tus clientes', 'info')
		})
    </script>

</body>

</html>