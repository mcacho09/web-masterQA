<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Clientes - LogistikApp</title>
	
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
	
    <!-- Dwr script -->
    <script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>
	
	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
    <style>
      #map {
        height: 75vh;
        margin: 0px;
        padding: 0px
      }
    </style>
	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
	<![endif]-->
	<script type="text/javascript">
	if(${fn:contains(useracegi.profile,'DRI')})
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
                                    <h1 class="pull-left">Listado de clientes</h1>
                                </div>
                            </div>
                            
                            <c:if test="${advertencia}">
                            <div id=mensaje class="row" >
                            	<div class="col-lg-4 ">
                            		<div class="alert alert-danger">
    									<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    									<strong>Atenci&oacute;n! </strong> Ha llegado al limite de creaci&oacute;n de clientes.
  									</div>
  								</div>
                            </div>
							</c:if>
                            
                            <div class="row">
                                <div class="col-lg-5">
                                    <div class="main-box clearfix" >

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
	                                                    <button id="mapButton" class="btn btn-primary" type="button" title="Geolocalizar" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
	                                                        <i class="fa fa-map-marker"></i>
	                                                    </button>
	                                                </div>
	                                                
	                                                <!--<div id="header-num-selected" class="btn-group num-selected" style="display:none;"><span>NaN</span></div>-->
	                                            </div>
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
	                                                    1-${fn:length(list)} de ${fn:length(list)}
	                                                </div>
	                                            </div>
												 -->
	                                    </header>

										<div class="row">
											<div class="col-xs-12">
												<div class="table-responsive" style="height:73vh;">
													<table id="table-user" class="table table-striped table-hover">
														<thead>
															<tr>
																<th><span>Plaza Comercial</span></th>
																<th class="text-center">Clientes</th>
																<!-- <th>Categor&iacute;a</th>-->
															</tr>
														</thead>
														<tbody>
			
															<c:forEach var="ret" items="${list}">
																<tr>
																	<td>
																		<div class="checkbox-nice">
																			<input type="checkbox" id="m-checkbox-${ret.id_retail}"
																				value="${ret.id_retail}" data-stores="${ret.stores}" />
																			<label for="m-checkbox-${ret.id_retail}"><a
																				href="store.htm?id=${ret.id_retail}">${ret.name}</a></label>
																		</div>
																	</td>
																	<td class="text-center"><c:if
																			test="${ ret.stores > 0 }">
																			<span class="badge badge-primary">${ret.stores}</span>
																		</c:if></td>
																	<!-- <td><span class="badge badge-default">${ret.category}</span></td> -->
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
                                </div>
                                <div class="col-lg-7">
                                    <div class="main-box clearfix no-header">
                                        <div class="main-box-body clearfix">
                                            <div id="map"></div>
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
    	
        var table = $('#table-user').dataTable( {
            'paging': false,
            'info': false,
            'searching': false,
            'pageLength': 100
            }); // table

        $( '#checkAllButton' ).on( 'click', function() {
        	
            var rows = table.fnGetNodes();
            
            if ( $(this).find("i").hasClass('fa-square-o') ){
                $('input[type="checkbox"]').prop('checked', true );
            }
            else{
                $('input[type="checkbox"]').prop('checked', false );
	       	 	// quita todos los pines del mapa
                for (var i=0; i < Markers.length; i++)	 
						Markers[i].setMap(null);
            }
            
            $(this).find("i").toggleClass("fa-square-o fa-check-square-o")
            
            var qty = $('input[type="checkbox"]:checked').length;
            if ( qty == 0 ) {
                $('#mapButton').attr('disabled', 'disabled');
                $("#header-num-selected").hide();
            }
            else if ( qty > 0 ) {
                $('#mapButton').removeAttr('disabled');
                $("#header-num-selected").text( qty + " plazas seleccionadas" );
                $("#header-num-selected").show();
            }
            mappedLocations();
        }); // checkAllButton
        
        $('#newButton').click( function() {
            window.location.href = "customeradd.htm";
            }); // newButton
        
        $('#refreshButton').click( function() {
            location.reload(true);
            }); // refreshButton
        
        $('#mapButton').click( function() {
       	    // Se obtienen todos las clientes activos para el cliente
       	    var ids = [];
       	    $("input[type=checkbox]:checked").each(function(index,e) {
   	    	    
   	    	    if ($(e).data('stores') > 0 ){
   	    	    	ids.push($(this).attr("value"));
   	    	    }
    	    });
            if ( ids.length >0 ){
            	location.href = "geocustomer.htm?ids="+ids;
            }
		}); // mapButton

        $("input[type=checkbox]").click(function(index,e) {
            // Se controla el evento de cada click de input[type=checkbox]
            // Se obtienen todos los registros de la tabla
            var rows = table.fnGetNodes();
            // Se obtiene la cantidad de input[type="checkbox"] == checked
            // para determinar que botones quedan habilitados o no
            var qty = $('input[type="checkbox"]:checked', rows).length;
            if ( qty == 1 ) {
                    $('#mapButton').removeAttr('disabled');
                    $("#header-num-selected").text( qty + " plaza seleccionada" );
                    $("#header-num-selected").show();
                }
            else {
                if ( qty == 0 ) {
                	    $('#mapButton').attr('disabled', 'disabled');
                    $("#header-num-selected").hide();
                }
                else {
                    $("#header-num-selected").text( qty + " plazas seleccionadas" );
                    $("#header-num-selected").show();
                }
            }
            
            });
            
        }); // ready

    </script>	

    <script src="js/markerwithlabel.js" type="text/javascript"></script>

    <script>
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 5,
            center: { lat: 21.88072496638374 , lng: -102.2961151599884}
        });
		var Markers=[];

        $('input[type=checkbox]').on('change', mappedLocations);
        
        var replyStore = function(data){
			if (data.length > 0){
				var idsStore = [];
    			for (var i = 0 ; i < data.length ; i++){
    				idsStore.push(data[i]);
    			}
    			
    			var StoreByIdsSearchCriteria = new Object();
    			StoreByIdsSearchCriteria.ids = idsStore;
	        	

    			RetailServiceBean.getStoreByIds(StoreByIdsSearchCriteria, function(datas){

    				if (datas.length > 0){
        				var marker;
    					for  (var i=0; i < datas.length; i++){
    						var shelf = datas[i].shelf;
    						var checkin = datas[i].checkin;
    		                var icono = ( shelf == 'S' ? 'img/pin6_blue.png' : 'img/icon-marker-blue.png');//Agregado de validacion con campo Shelf
    		                if (checkin != null && checkin != "")
    		                	icono = ( shelf == 'S' ? 'img/pin6_green.png' : 'img/icon-marker-green.png');
    		                var contentString = '<div id="content" style="max-width:150px;">'+
    		                '<div id="siteNotice"></div>'+
    		                '<span id="firstHeading"><b>'+datas[i].name+'</b></span>'+
    		                '<div id="bodyContent">'+
    		                '<p><span>'+datas[i].address1+'<br/>'+datas[i].address2+'</span></p>'
    		                /*'<p>CP <b>'+postal+'</b></p>'+*/
    		                '</div>'+
    		                '</div>';
    		                marker = new MarkerWithLabel({
    							id: datas[i].id_store,
    							ind: i,
    		                    position: new google.maps.LatLng(datas[i].latitude,datas[i].longitude),
    		                    map: map,
    		                    icon: icono,
    		                    labelAnchor: new google.maps.Point(30, 31),
    		                    labelClass: "labels", // the CSS class for the label
    		                    labelStyle: {opacity: 1}
    		                });
    		                marker.infowindow = new google.maps.InfoWindow({
    		                    content: contentString,
    		                    maxWidth: 150
    		                    
    		                });
    		                
    		                marker.addListener('mouseout', function() {
    		                    this.infowindow.close();
    		                });
    		                marker.addListener('click', function() {
    		                	this.infowindow.open(map,this);
    		                    
    		                });
    		                Markers.push(marker);
    					}
    				}
    				
    	        	$( '#checkAllButton' ).attr("disabled",false);
    			});
			}
        };
        
        function mappedLocations(){
        	console.log("--- Entro ---");
        	if ($('input[type=checkbox]:checked').length > 0){
        		var ids = [];
            	$('input[type=checkbox]:checked').each(function(index,e){
            		ids.push(e.value);
            	});
            	
            	if (ids.length > 0){
            		for(var mar=0; mar<Markers.length; mar++)
            			Markers[mar].setMap(null);
            		
            		var StoreActiveByIdsRetailSearchCriteria = new Object();
                	StoreActiveByIdsRetailSearchCriteria.ids = ids;
                	
            		RetailServiceBean.getIdStoresActiveByIdsRetail(StoreActiveByIdsRetailSearchCriteria, replyStore);
            		
            	}
        	}
        	
    		$('input[type="checkbox"]:not(:checked)').each(function(i, e){
    			RetailServiceBean.getStoreByIdRetail(e.value,delStoresInMap);
    		});
        }
		var delStoresInMap = function(data){
			console.log(data);
	        for (var key=0; key<data.length;key++){
	       	 	for (var i=0; i < Markers.length; i++){	 
					if(Markers[i].id==data[key].id_store){
						Markers[i].setMap(null);
					}
	  			} 			
	       	}
	    };	
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
							target: "checkAllButton",
							title: "Seleccionar todo",
							content: "Clic para seleccionar todas las plazas comerciales",
							placement: placementRight,
							yOffset: -15
						},
						{
							target: 'newButton',
							title: "Nuevo",
							content: "Clic para agregar un nuevo cliente",
							placement: "bottom",
							zindex: 999						
						},
						{
							target: 'refreshButton',
							title: "Actualizar",
							content: "Clic para actualizar la pantalla",
							placement: "bottom",
							zindex: 999,
							xOffset: -15
						},
						{
							target: 'mapButton',
							title: "Geolocalizar",
							content: "Al tener seleccionado un o más plazas comerciales, podrás verlas localizadas en un mapa",
							placement: placementRight,
							zindex: 999,
							yOffset: -15
						},
						{
							target: 'table-user',
							title: "Listado de clientes",
							content: "Aquí se muestran todas las plazas comerciales que tienes agregadas actualmente",
							placement: placementRight,
							zindex: 999,
							yOffset: -15
						},
						{
							target: 'map',
							title: "Mapa",
							content: "Aquí se verán las plazas comerciales mapeadas cada vez que selecciones una del listado de clientes",
							placement: placementLeft,
							zindex: 999,
							yOffset: -15
						}
					],
					showPrevButton: true
				};
			hopscotch.startTour(tour);
		});
    </script>
	<script type="text/javascript">

    var suma = 0;
   	 $('#table-user tr').each(function(){
    	 suma += parseInt($(this).find('td').eq(1).text()||0,10)
   	 })
	
   	 
   	 /*Control de limte cliente pasado desde el controlador*/
   	 var desh = ${advertencia};  	
   	 document.getElementById("newButton").disabled = desh;
   	 
    </script>
</body>
</html>
