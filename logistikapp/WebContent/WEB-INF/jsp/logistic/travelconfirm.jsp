<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Viajes - LogistikApp</title>
    
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
    
    <!-- libraries -->
    <link type="text/css" rel="stylesheet" href="css/libs/font-awesome.css"/>
    <link type="text/css" rel="stylesheet" href="css/libs/nanoscroller.css"/>
    
    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">
    <link rel="stylesheet" type="text/css" href="css/libs/jquery.datetimepicker.css"/>
    
    <!-- Favicon -->
    <link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
    
    <!-- google font libraries -->
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
    
    <!--[if lt IE 9]>
            <script src="js/html5shiv.js"></script>
            <script src="js/respond.min.js"></script>
        <![endif]-->
    <!-- this page specific styles -->
    <style>
      html, body, #map-canvas {
        height: 470px;
        margin: 0px;
        padding: 0px
      }
       .labels{
            color: #000000;
            font-weight: bold;
            font-size: 12px;
            opacity: 1;
            pointer-events: none;
            text-align: center;
            width: 60px;
            white-space: nowrap;
      }
    </style>
</head>

<body class="fixed-header fixed-leftmenu theme-blue">

    <header class="navbar" id="header-navbar">
        <c:import url="/html/menu-top.jsp" />
    </header>

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
                                        <li><a href="home.htm">Home</a></li>
                                        <li><span>Log&iacute;stica</span></li>
                                        <li class="active"><span><a href="travellist.htm">Viajes</a></span></li>
                                    </ol>
                                    
                                    <div class="clearfix">
                                        <h1 class="pull-left">Confirmaci&oacute;n</h1>
                                    </div>
                                </div>
                            </div>
							
							<div class="row">
                                <div class="col-lg-4 col-sm-6 col-xs-12">
                                    <div class="main-box infographic-box">
                                        <i class="fa fa-truck emerald-bg"></i>
                                        <span class="headline">Viaje</span>
                                        <span class="value">${viaje.name}</span>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-sm-6 col-xs-12">
                                    <div class="main-box infographic-box">
                                        <i class="fa fa-calendar yellow-bg"></i>
                                        <span class="headline">Programado</span>
                                        <span class="value"><fmt:formatDate value="${viaje.schedule}" type="date" pattern="dd.MM.yyyy"/></span>
                                    </div>
                                </div>
                                <div class="col-lg-4 col-sm-6 col-xs-12">
                                    <div class="main-box infographic-box">
                                        <i class="fa fa-user purple-bg"></i>
                                        <span class="headline">Asignado</span>
                                        <span class="value">${chofer.username}</span>
                                    </div>
                                </div>
							</div>
							
                            <div class="row">
                                <div class="col-lg-4">
                                    <div class="main-box clearfix">
                                    
                                        <header class="main-box-header clearfix">
                                            <h2>Listado de clientes</h2>
                                        </header>
                                        
                                            <div class="main-box-body clearfix">
                                              <div id="list_scroll" class="row cf nestable-lists" >
                                                <div class="col-lg-12 dd" style="width:100%;" id="nestable">
                                                    <ol class="dd-list">
                                                         <c:forEach var="waybill" items="${waybills}" varStatus="i">
                                                              <li class="dd-item" data-id="1">
	                                                              <input type = "hidden" id="lat${i.index}" value="${waybill.latitude}">
	                                                              <input type = "hidden" id="lng${i.index}" value="${waybill.longitude}">
	                                                              <input type = "hidden" id="wbname${i.index}" value="${waybill.name}">
	                                                              <input type = "hidden" id="wbaddress1${i.index}" value="${waybill.address1}">
	                                                              <input type = "hidden" id="wbaddress2${i.index}" value="${waybill.address2}">
	                                                              <input type = "hidden" id="wbpostal${i.index}" value="${waybill.postal}">                                                                                      
                                                                  <input type = "hidden" class="waybill_ids" id="${waybill.id_waybill}" value="${i.index}">
																  <span class="label label-default" id="num${i.index}">${waybill.orderby}</span>
                                                                  <div class="dd-handle" style="display: inline-block; width:90%">
																	<span id="wbid${i.index}"><c:out value="${waybill.name}"/></span>                                                                        
																</div>
                                                              </li>
                                                            </c:forEach> 
                                                    </ol>
                                                </div>
                                                </div>
                                                <div class="form-group">
                                                    <button type="button" onclick="location.href='travellist.htm';" class="btn btn-default pull-left">
                                                        <i class="fa fa-times"></i> Cancelar
                                                    </button>
                                                    <button type="button" onclick="javascript:confirmTravel();" class="btn btn-primary pull-right">
                                                        <i class="fa fa-check"></i> Confirmar
                                                    </button>
                                                </div>
                                            </div>
                                        
                                    </div>
                                </div>
                                <div class="col-lg-8">
                                    <div class="main-box clearfix no-header">
                                        <div class="main-box-body clearfix">
                                            <div id="map-canvas"></div>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- /row -->

                            <form name="confirmForm" id="confirmForm" action="confirmtravel.htm">
                               <input type="hidden" id="ids" name="ids">
                               <input type="hidden" id="idt" name="idt" value="${viaje.id_travel}">
                            </form>

                        </div>
                    </div>

                   <footer id="footer-bar" class="row">
                        <c:import url="/html/footer.html" />
                    </footer>
                    
                </div>
            </div>
        </div>

    <!-- global scripts -->
    <script src="js/demo.js"></script> <!-- only for demo -->    
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.nanoscroller.min.js"></script>
    
    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
    <script src="js/pace.min.js"></script>
    
     <!-- datetimepicker </body> -->
     
    <script src="js/jquery.datetimepicker.js"></script>
    
    <!-- this page specific scripts -->
    <script src="js/markerwithlabel.js"></script>
    <script src="js/jquery.nestable.js"></script>
    <script src="js/jquery.slimscroll.min.js"></script>
    
    <!-- this page specific inline scripts -->
    <script>
    var markers = new Array();
    $(document).ready(function() {
            google.maps.event.addDomListener(window, 'load', initialize);
            // activate Nestable for list 1
            var updateOutput = function(e){
                
                var list   = e.length ? e : $(e.target),
                    output = list.data('output');
                if (output != null)
                if (window.JSON) {
                    output.val(window.JSON.stringify(list.nestable('serialize')));//, null, 2));
                } 
                else {
                    output.val('JSON browser support required for this demo.');
                }
                var cont = 1;
                $("input.waybill_ids").each(function(index,e){
                    
                    var id = $(this).attr("id");
                    
                    var i = document.getElementById(id).value;
                    if (markers[i]!= null)
                        if( $(this).is(':checked') ){
                            markers[i].set('labelContent',index+1);
                            $('#num'+i).html(index+1);
                            cont++;
                        }
                        else{
                            markers[i].set('labelContent',index+1);
                            $('#num'+i).html(index+1);
                        }
                        
                });
            };   
            
            
            $('#list_scroll').slimScroll({
                height: '420px',            
                start: 'top'
             });
            
            $('#nestable').nestable({
                group: 1
            })
            .on('change', updateOutput);
            // output initial serialised data
            updateOutput($('#nestable').data('output', $('#nestable-output')));
        });
   
        var map;
          
        function initialize() {
            var latCenter = document.getElementById("lat0").value;
            var lngCenter = document.getElementById("lng0").value;
            var mapOptions = {
                    zoom: 15,
                    center: new google.maps.LatLng(latCenter,lngCenter)
            };
            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            var locationCoordsArray = new Array();
            markers = new Array();
            for ( var i=0; i<${fn:length(waybills)}; i++ ) {
                var id = "wbid"+i;
                var lat = document.getElementById("lat"+i).value;
                var lng = document.getElementById("lng"+i).value;
                var name = document.getElementById("wbname"+i).value;
                
                var address1 = document.getElementById("wbaddress1"+i).value;
                var address2 = document.getElementById("wbaddress2"+i).value;
                var postal = document.getElementById("wbpostal"+i).value;
                locationCoordsArray.push(new google.maps.LatLng(lat,lng));
                var contentString = '<div id="content" style="max-width:150px;">'+
                '<div id="siteNotice">'+
                '</div>'+
                '<h5 id="firstHeading"><b>'+name+'</b></h5>'+
                '<div id="bodyContent">'+
                '<p>'+address1+','+address2+
                '<p>'+postal+
                '</div>'+
                '</div>';
                var l = i+1 +"";
                
                var marker = new MarkerWithLabel({                   
                    id: id,
                    ind: i,
                    position: locationCoordsArray[i],
                    map: map,                                       
                    icon: 'img/icon-marker-blue.png', 
                    labelContent: l,
                    labelAnchor: new google.maps.Point(30, 30),
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
                	$("#"+this.id).focus();
                    
                });
                markers.push(marker);
                
            }
        };

        
        function confirmTravel(){
            
            var ids= [];
            var ids_travel = "";
            var sep ="";
            
            $("input.waybill_ids").each(function(index,e){
                ids.push($(this).attr("id"));
                ids_travel = ids_travel + sep + $(this).attr("id");
                sep = ",";
                
            });
            document.getElementById("ids").value = ids_travel;
            if (ids.length>0){
				var id_trav = "${viaje.id_travel}";
                //document.getElementById("confirmForm").action="travelwaybill.htm?idt="+id_trav;
                document.getElementById("confirmForm").submit();
            }
            else 
                alert("No hay clientes seleccionadas.");
            
        }
        $('#id_route').change( function(){
               idr = document.getElementById("id_route").value;
               location.href="traveladd.htm?accion="+"${accion}&idr="+idr;
        });
        
    </script>

</body>

</html>