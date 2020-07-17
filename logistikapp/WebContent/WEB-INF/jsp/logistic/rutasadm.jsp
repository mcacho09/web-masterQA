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
        height: 450px;
        margin: 0px;
        padding: 0px
      }
    </style>
</head>

<body class="fixed-header fixed-leftmenu theme-blue">

	<header class="navbar" id="header-navbar">
		<c:import url="/html/menu-top.jsp" />
	</header>
	
	<!-- Cargar coordenadas de todos los locales -->
	<input type="hidden" id="listCoords" name="listCoords"/>
	<c:set var="coords" value=""/>
	<c:set var="separador" value=""/>
	
	<c:forEach var="store" items="${listStore}">
		<c:set var="coords" value="${coords}${separador}${store.id_store}_${store.latitude}_${store.longitude}"/>
		<c:set var="separador" value="@"/>
	</c:forEach>
	<script>
		document.getElementById("listCoords").value = "${coords}";
	</script>
	
	<!-- Cargar coordenadas de todos los locales Marcados -->
	<input type="hidden" id="listCoords" name="listCoordsMark"/>
	<c:set var="coords" value=""/>
	<c:set var="separador" value=""/>
	
	<c:forEach var="storeM" items="${listStoreMarked}">
		<c:set var="coords" value="${coords}${separador}${storeM.id_store}_${storeM.latitude}_${storeM.longitude}"/>
		<c:set var="separador" value="@"/>
	</c:forEach>
	<script>
		document.getElementById("listCoordsMark").value = "${coords}";
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
									<ol class="breadcrumb">
									
									     <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                        <li><span><spring:message code="label.breadcrumb.logistic"/></span></li>
                                        <li><a href="rutas.htm"><span><spring:message code="label.breadcrumb.logistic.route"/></span></a></li>
                                        <li class="active"><a href="rutasadm.htm"><span><spring:message code="label.breadcrumb.logistic.route.add"/></span></a></li>
									</ol>
									
									<div class="clearfix">
										<h1 class="pull-left"><spring:message code="label.logistic.route.add.title"/></h1>

                                        <div class="pull-right">
                                        	<button type="button" onclick="location.href='rutas.htm';" class="btn btn-default btn-xs"><i class="fa fa-arrow-circle-left fa-lg"></i> Volver</button>
                                        </div>
									</div>

								</div>
							</div>

							<div class="row">
							        <div class="col-lg-6">
                                    <div class="main-box clearfix no-header">
                                    <form id="form" method="post">
                                        <div class="main-box-body clearfix">
                                         	<div class="col-lg-12 pull-right">
												<label class="pull-right" ><h4 id="locales">0</h4></label>
												<label class="pull-right" ><h4 >Locales Seleccionados:  </h4></label>
											</div>
                        	                <div class="col-lg-12">
												<label for="name">Titulo (*)</label>
                                                <spring:bind path="command.name">
													<input type="text" class="form-control" id="name" name="name" placeholder="Titulo"  maxlength="50"/> 
												</spring:bind>
												
                        	                	<div class="col-lg-9">
													<label for="name">Codigo (*)</label>
                                                	<spring:bind path="command.code">
														<input type="text" class="form-control" id="code" name="code" placeholder="Codigo"  maxlength="10"/> 
													</spring:bind>
												</div>
                        	                	<div class="col-lg-3">
													<label for="name">Color </label>
                                               		<spring:bind path="command.color">
														<select class="form-control"  style="background-color:red; color:red" id="selColor" name="selColor">  
    														<option  style="color:red" value="red" >Rojo</option>
    														<option  style="color:blue" value="blue" >Azul</option>
    														<option  style="color:green" value="green" >Verde</option>
    														<option  style="color:purple" value="purple" >Morado</option>
    														<option  style="color:yellow" value="yellow" >Amarillo</option>
														</select> 
														<input type="hidden" id="color" name="color" value="red"/>
														<input type="hidden" id="ids" name="ids" value=""/>
													</spring:bind>	
			                                      	<br>
												</div>
						    				</div>
                                            <div class="main-box clearfix no-header">
                                           		<div class="col-lg-12" id="map"></div>
                                            </div>
                                            <br>
                                            <div>
                                            	<button id="doBtn" type="submit" class="btn btn-success btn-xs pull-right"><i class="fa fa-check fa-lg"></i> <spring:message code="${accion == 'add' ? 'label.logistic.route.add.button' : 'label.logistic.route.upd.button'}"/></button>
                                            </div>
                                       		</div>
                                       	</form>
                                    </div>
                                </div>
								<div class="col-lg-6">
									<div class="main-box clearfix no-header">
                                        <header class="main-box-header clearfix">
												<div >
													<select class="form-control" id="routeSel" name="routeSel"> 
														<option value="all">Todos</option>
	  													<option value="cat">Categoria Plaza</option>
	  													<option value="ret">Plaza</option>                                       
			                                        </select>           
												</div>
												<br>
												<div >
													<select class="form-control" id="selFilter" name="selFilter" style="display:none"></select>           
												</div>
												<br>

                                        </header>
										<div class="main-box-body clearfix">
											<div class="table-responsive">
												<table class="table table-striped table-hover" >
													<thead>
														<tr>
															
														
															<th><input type="checkbox" id="selectall" value="A"/> Local</th>
															<th class="text-center"><span>Codigo</span></th>
															<!--th>Opciones</th-->
														</tr>
														<tr>
														</tr>
													</thead>
													<tbody id="dataTable">
                                                        <c:if test="${fn:length(listStore) == 0}">
                                                            <tr><td colspan="8" class="text-center"><spring:message code="label.logistic.store.list.nodata"/></td></tr>
                                                        </c:if>
                                                        
														<c:forEach var="sto" items="${listStore}" varStatus="vst">
															<tr>
														    	<td>
														    		<input type="checkbox" id="${sto.id_store}" onclick="javascript:addStoreMap(${sto.id_store});"/> ${sto.name}
														    	</td>
                                                            	<td class="text-center"><span class="badge badge-info">
                                                            		<span>${sto.code}</span>
                                                            	</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
                        
							</div><!-- /row -->

						</div>
					</div>

                    <p id="footer-copyright" class="col-xs-12">&copy; 2015 <a href="http://www.logistikapp.com/" target="_blank">LogistikApp</a> - Powered by <a href="http://www.retailsbs.com/" target="_blank">Retail Software Business Solutions</a></p>
					
				</div>
			</div>
		</div>

    <!-- global scripts -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.nanoscroller.min.js"></script>
    
    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
        
    <!-- this page specific inline scripts -->
    <script>
    /*
        $("input[type=checkbox]").change(function(){               
        
    	ids=[];
        $("input[type=checkbox]:checked").each(function(index,e){               
           ids.push($(this).attr("id"));
        });
        alert($(this).attr("id"));
    });
        */

   
    ids=[];
    var cont=0;
	var id_sup = "${id_supplier}";
	var ret=false;
	
    $("#doBtn").click(function()
        	{
    			var sep="";
    			for (var i=0; i < ids.length ; i++)
  				{
    				//alert(ids[i]+"<--")
    				document.getElementById("ids").value=document.getElementById("ids").value+sep+ids[i];
    				sep="_";
  				}
    			//alert ("FINAL"+document.getElementById("ids").value);
        	});
	
    	$("#routeSel").change(function(){
    		if(document.getElementById("routeSel").value=="all")
    			{
					document.getElementById("selFilter").style.display="none";
					RetailServiceBean.getAviableStoreByIdSupplier(id_sup, dataSelAll);
    			}
    		else if(document.getElementById("routeSel").value=="cat")
				{
    				ret=false;
    				document.getElementById("selFilter").style.display="inline"; 
        			RetailServiceBean.getAllStoreCategoryByIdSupplier(id_sup, dataSelCat);
				}
    		else if(document.getElementById("routeSel").value=="ret")
				{
					document.getElementById("selFilter").style.display="inline"; 
					var RetailSearchCriteria = new Object();
					ret=true;
					RetailSearchCriteria.id_supplier = id_sup;
        			RetailServiceBean.getRetailByCriteria(RetailSearchCriteria, dataSelRet);
				}
    		
    	});

        function addStoreMap(id_store){ 
  		var found="n";
  		if(ids.length>0)
		{
  			for(var i = 0; i < ids.length; i++) 
    		{
    			if(ids[i] == id_store) 
    			{
    	    		ids.splice(i, 1);
      				//alert(id_store+ " eliminado de la lista");
      				cont--;
   	    		 document.getElementById('locales').innerHTML = cont;
    	    		found="y";  		
    	    		for (var i=0; i < Markers.length; i++)
    	      		{	 
    	      	        if(Markers[i].id == id_store){
    	      	            Markers[i].setIcon('http://maps.google.com/mapfiles/ms/icons/red-dot.png');
    	      	        }
    	      	    }
    	   		 }
    		}
    	 	if(found=='n')
	    	{

  				//alert(id_store+ " añadido a la lista");
	    		ids.push(id_store);  		
	    		cont++;
	    		 document.getElementById('locales').innerHTML = cont;
	    		for (var i=0; i < Markers.length; i++)
	      		{	 
	      	        if(Markers[i].id == id_store){
	      	            Markers[i].setIcon('http://maps.google.com/mapfiles/ms/icons/blue-dot.png');
	      	            
	      	        }
	      	    }
	    	}
		}
  		else
  			{
				//alert(id_store+ " añadido a la lista");
				cont++;
	    		 document.getElementById('locales').innerHTML = cont;
  				ids.push(id_store);
  		  		for (var i=0; i < Markers.length; i++)
  		  		{	 
  		  	        if(Markers[i].id == id_store){
  		  	            Markers[i].setIcon('http://maps.google.com/mapfiles/ms/icons/blue-dot.png');
  		  	        }
  		  	    }
  			}

  		
      	}
        
    	var dataSelAll = (function(data)
    	{
    		var html = "<tbody id=\"dataTable\">";
    		var coords="";
			var separador = "";
        	for (var key=0; key<data.length;key++) 
        	{
        	  	html += "<tr><td><input type=\"checkbox\" onclick=\"javascript:addStoreMap("+data[key].id_store+");\" id=\""+data[key].id_store+"\"/>"+data[key].name+"</td><td class=\"text-center\"><span class=\"badge badge-info\"><span>"+data[key].code+"</span></td></tr>";
        	  	coords = coords + separador + data[key].id_store + "_" + data[key].latitude  + "_" + data[key].longitude;
				separador = "@"; 
        	}
        	html = html +"</tbody>";
        	$('#dataTable').replaceWith(html);
        	
    		document.getElementById("listCoords").value=coords;
  			 for (var i = 0 ; i < Markers.length ; i++)
	  			{	 
					Markers[i].setMap(null);
	  			}
   		 	addStores();
   		 	
    		document.getElementById("selectall").value="A";
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
    	
    	var dataSelRet = (function(data)
    	{
    		dwr.util.removeAllOptions("selFilter");
    		if(data.length > 0){
    			dwr.util.addOptions("selFilter",data,"id_retail","name");
    		}
    		var sel = document.getElementById("selFilter");
    		sel.selectedIndex = 0;
    		$("#selFilter").trigger("change");
    	});	
    	$("#selFilter").change(function()
    	{	
    		if(ret)
    			{

    				var id_ret = document.getElementById("selFilter").value;
        			RetailServiceBean.getAviableStoreByIdRetail(id_ret, dataRet);
    			}
    		else
    			{
        			var id_sto_cat = document.getElementById("selFilter").value;
        			RetailServiceBean.getAviableStoreByIdStoreCategory(id_sto_cat, dataCatRet);
    			}
    	});

    	var dataCatRet = (function(data){
    		
    		var html = "<tbody id=\"dataTable\">";
    		var coords="";
			var separador = "";
    		for (var key=0; key<data.length;key++) 
    			{
    				html += "<tr><td><input type=\"checkbox\" onclick=\"javascript:addStoreMap("+data[key].id_store+");\" id=\""+data[key].id_store+"\"/>"+data[key].name+"</td><td class=\"text-center\"><span class=\"badge badge-info\"><span>"+data[key].code+"</span></td></tr>";
					coords = coords + separador + data[key].id_store + "_" + data[key].latitude  + "_" + data[key].longitude;
    				separador = "@";   			
    			}    		
    		html = html +"</tbody>";
    		$('#dataTable').replaceWith(html);

    		document.getElementById("listCoords").value=coords;
   			 for (var i = 0; i < Markers.length; i++)
	  			{	 
					Markers[i].setMap(null);
	  			}
    		 	addStores();
    		 	
    		document.getElementById("selectall").value="A";
    	});
    	var dataRet = (function(data){
    		
    		var html = "<tbody id=\"dataTable\">";
    		var coords="";
			var separador = "";
    		for (var key=0; key<data.length;key++) 
    			{
    				html += "<tr><td><input type=\"checkbox\" onclick=\"javascript:addStoreMap("+data[key].id_store+");\" id=\""+data[key].id_store+"\"/>"+data[key].name+"</td><td class=\"text-center\"><span class=\"badge badge-info\"><span>"+data[key].code+"</span></td></tr>";
    				coords = coords + separador + data[key].id_store + "_" + data[key].latitude  + "_" + data[key].longitude;
    				separador = "@";
            	}
    		html = html +"</tbody>";
    		$('#dataTable').replaceWith(html);

    		document.getElementById("listCoords").value=coords;
   			 for (var i = 0; i < Markers.length; i++)
	  			{	 
					Markers[i].setMap(null);
	  			}
    		 	addStores();

    		document.getElementById("selectall").value="A";
    	});
    	
    	$('#backBtn').click(function()
    	{
    		window.location.replace("rutas.htm");	
    	 });
    	$('#selectall').change(function()
    	{
    	    if(document.getElementById("selectall").value=="A")
    	    	{

            		seleccionarTodo();
    	    		document.getElementById("selectall").value="N";
    	    	}
    	    else
    	    	{
            		seleccionarNinguno();
    	    		document.getElementById("selectall").value="A";
    	    	}
    	 });
    	$('#selColor').change(function() {
    		$('#selColor').css('background-color', $(this).val());
			$('#selColor').css('color', $(this).val());
			document.getElementById("color").value=document.getElementById("selColor").value;
    	});
        function seleccionarTodo(){        	  
    	    $("input[type=checkbox]").prop('checked', true);
    	    for (var i=0; i < Markers.length; i++)
      		{
      	            Markers[i].setIcon('http://maps.google.com/mapfiles/ms/icons/blue-dot.png');
      	    }
      	}
     	function seleccionarNinguno(){             
          	$("input[type=checkbox]").prop('checked', false);  
          	for (var i=0; i < Markers.length; i++)
      		{	 
      	            Markers[i].setIcon('http://maps.google.com/mapfiles/ms/icons/red-dot.png');
      	    }
    	}
     	
     	
     	

		  var AGS = { lat: 21.88072496638374 , lng: -102.2961151599884};
		  var Markers=[];
     	  var map = new google.maps.Map(document.getElementById('map'), {
   		    zoom: 12,
   		    center: AGS
   		  });
     	function initialize() {
     		

     		  /*/ This event listener calls addMarker() when the map is clicked.
     		  google.maps.event.addListener(map, 'click', function(event) {
     		    addMarker(event.latLng, map);
     		  });*/

     		  // Add a marker at the center of the map.
     		 // addMarker(AGS, map);
     		 addStores();
     		}

     		// Adds a marker to the map.
     		function addMarker(location, id) 
     		{
     		  // Add the marker at the clicked location, and add the next-available label
     		  // from the array of alphabetical characters.
     		  var marker= new google.maps.Marker({
     			id: id,
     		    position: location,
     		    label: 'a',
     		    icon: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
     		    map: map
     		  });
     		 Markers.push(marker);
     		 
     		google.maps.event.addListener(marker, 'click', function() {
     	         // infowindow.open(map);
     	          //Change the marker icon
     	          addStoreMap(marker.id)
     	          
     	         if(document.getElementById(marker.id).checked)
     	        	 {
     	        		document.getElementById(marker.id).checked=false;
     	        	 }
     	         else
     	        	 {
     	        		document.getElementById(marker.id).checked=true;
     	        	 }
     	         
     	     });
     		}

     		

			
			function addStores()
			{    
				/*
				alert("AddStore");

				alert(document.getElementById("listCoords").value);
				*/
	     		var listaCoordenadas = document.getElementById("listCoords").value.split("@");
	         	for(var i=0; i<listaCoordenadas.length; i++)
	            {
	                var store = listaCoordenadas[i].split("_");
	                
	                    for(var j=0; j<store.length; j=j+3)
	                    {
	                        if(store[j]!="0.0" && store[j+1]!="0.0")
	                        {
	                            
	                            //encuntrar si la tienda esta seleccionada
	                            if(ids.length>0)
	                                {
	                                    for(var k = 0; k < ids.length; k++) 
	                                    {
	                                    	/*
	                                        if((ids[k]+"") == store[j]) 
	                                        {
	                                        	var pos = new google.maps.LatLng(store[j+1],store[j+2]);
	                                        	//var icon='http://maps.google.com/mapfiles/ms/icons/blue-dot.png';
	                                            addMarker(pos, store[j]);

	                                        }
	                                        else
	                                        {
	                                        	var pos = new google.maps.LatLng(store[j+1],store[j+2]);
	                                        	//var icon='http://maps.google.com/mapfiles/ms/icons/red-dot.png';
	                                            addMarker(pos, store[j]);
	                                        }
	                                    	*/
	                                    }
	                                }
	                            else
	                                {
	                            	var pos = new google.maps.LatLng(store[j+1],store[j+2]);
	                            	//var icon='http://maps.google.com/mapfiles/ms/icons/blue-dot.png';
                                    addMarker(pos, store[j]);
	                                }
	                        }
	                    }
	            }
	      	}
     		google.maps.event.addDomListener(window, 'load', initialize);
         	
    </script>

</body>

</html>