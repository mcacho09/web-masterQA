<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Calendario - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	
	<!-- libraries -->
	<link type="text/css" rel="stylesheet" href="css/libs/font-awesome.css"/>
	<link type="text/css" rel="stylesheet" href="css/libs/nanoscroller.css"/>
	<link rel="stylesheet" type="text/css" href="css/libs/select2.css"/>
	<link rel="stylesheet" type="text/css" href="css/libs/jquery.datetimepicker.css"/>
	<link rel="stylesheet" href="css/libs/datepicker.css" type="text/css" />
	<link rel="stylesheet" href="css/libs/daterangepicker.css" type="text/css" />
	<link rel="stylesheet" href="css/libs/bootstrap-timepicker.css" type="text/css" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/libs/fullcalendar.css" type="text/css" />
    <link rel="stylesheet" href="css/libs/fullcalendar.print.css" type="text/css" media="print" />
    <link rel="stylesheet" href="css/compiled/calendar.css" type="text/css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/libs/morris.css" />
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">

	
	<!-- Dwr script -->
	<script type='text/javascript' src='dwr/interface/UserServiceBean.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	
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
	
	<input type="hidden" id="lista" name="lista"/>
	<c:set var="datos" value=""/>
	<c:set var="separador" value=""/>
	
	<c:forEach var="evento" items="${events}">
		<c:set var="datos" value="${datos}${separador}${evento.id_calendar}-${evento.cal_title}-${evento.cal_start}-${evento.cal_end}-${evento.cal_level}"/>
		<c:set var="separador" value="_"/>
	</c:forEach>
	<script>
		document.getElementById("lista").value = "${datos}";
	</script>
	
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
                                     <li class="active"><span>Comunicaci&oacute;n</span></li>
                                </ol>
									
                                <div class="clearfix">
                                    <h1 class="pull-left"><a href="calendar.htm">Calendario</a></h1>
                                </div>
                            </div>
                        </div>
                     	<div class="row">
                     		<div class="col-md-3">
								<div class="main-box" id="external-events">
									<header class="main-box-header clearfix">
										<h2>Eventos</h2>
									</header>
									
									<form id="formulario" method="post" action = "calendar.htm">
									
										<input type="hidden" name="evId" id="evId"  >
										<div class="main-box-body clearfix">
											<div class="form-group">
												<label for="name">Título</label>
												<input type="text" class="form-control" id="title" name="title" placeholder="Titulo"  maxlength="50"/> 
												
												
												 <c:if test="${events!=null}">
												 		<!-- Si el evento contiene algo que rellene los campos -->
												 		<!-- document.getElementById("title").value="Modificar" -->
												 </c:if>
                                                              
											</div>
											<!-- ------------------------------------------- Fechas -------------------------------------------- -->
											<div class="form-group">
												<label for="name">Inicio</label>
												<div>
													<input id="start" class="form-control" name="start" type="text" placeholder="Fecha de inicio" readonly/>
													<input id="start_format" name="start_format" type="hidden"/>
												</div>
											</div>
											<div class="form-group">
												<label for="name">Término</label>
												<div>
													<input id="end" class="form-control" name="end" type="text" placeholder="Fecha que termina" readonly/>
													<input id="end_format" name="end_format" type="hidden"/>
												</div>
											</div>
											  <!-- -------------------------------------------./Fechas ------------------------------------------ -->
											
											<div class="form-group">
												<label for="name">Tipo</label><br>
												
													<div class="radio" style="display: inline">
														<input type="radio" name="level" id="Personal" value="Personal" checked><label for="Personal"> Personal</label> 
													</div>
													
													<div class="radio" style="display: inline">
														<authz:authorize ifAnyGranted="SUP,SUP1,SUP2,SUP3,SUP4,SUP5,DEM">
													 		<input type="radio" name="level" id="Global" value="Global"><label for="Global"> Global</label>
													 	</authz:authorize>
													</div>
													 <input type="hidden" name="levelChar" id="levelChar" value="P">
												
											</div>
											<!--  
											
											-->
											<div class="form-group">
												<label>Alerta</label>
												<select class="form-control" id="alert" name="alert">      
													<!--  (MIN=0,1,5,10,15,30. HOUR=1,2. DAY=1,2) -->
													<option value="0">0 min</option>
  													<option value="1">1 min</option>
  													<option value="5">5 min</option>
  													<option value="10">10 min</option>
  													<option value="15">15 min</option>
  													<option value="30">30 min</option> 
  													<option value="60">1 hr</option> 
  													<option value="120">2 hrs</option>  
  													<option value="1440">1 dia</option> 
  													<option value="2880">2 dias</option>                                          
		                                        </select>           
											</div>
											<br>
											<button type="button" class="btn btn-danger btn-xs pull-left" id="deleteBtn" style="display:none"><i class="fa fa-trash-o"></i></button><span></span>
                                                
											<div class="form-group">
												 <button type="button" class="btn btn-success btn-xs pull-right" id="doBtn"><i id="do" class="fa fa-check"></i><span id="textBtn" name="textBtn" class="ui-button-text"> Agregar</span></button>
                                            	  <input type="hidden" name="action" id="action" value="">
                                            	  
                                            	  <button type="button" class="btn btn-default btn-xs pull-left"  id="cancelBtn" style="display:none" ><i class="fa fa-times"></i></button>
                                            	  
                                            </div>
                                           	
										</div>
									
									</form>
								</div>
							</div>
                            <div class="col-lg-9 col-md-9">
                            	<div class="main-box">
                            		<div class="main-box-body clearfix">
                                		<div id="calendar"></div>
                            		</div>	
                            	</div>	
                            </div>
						</div>
					</div>
				</div><!-- /row -->
				
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

    <!-- this page specific scripts -->
    <script src="js/jquery-ui.custom.min.js"></script>
    <script src="js/moment.min.js"></script>
    <script src="js/fullcalendar.min.js"></script>
    
    <script src="js/jquery.slimscroll.min.js"></script>
    <script src="js/raphael-min.js"></script>
    <script src="js/morris.min.js"></script>
    <script src="js/jquery.knob.js"></script>
    
    <!-- datetimepicker </body> -->
	<script src="js/jquery.datetimepicker.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/daterangepicker.js"></script>
	<script src="js/bootstrap-timepicker.min.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script type='text/javascript' src='dwr/interface/LogisticServiceBean.js'></script>	
    <!-- this page specific inline scripts -->

    <script>
    
	   var show = true;
   $(document).ready(function() {
    	var date = new Date();
		var hr=date.getHours()+1;
		var mes=(date.getMonth() + 1);
		var dia=date.getDate();

		if(date.getDate()<10)
		{
			dia= '0'+date.getDate();
		}
		if((date.getMonth() + 1)<10)
		{
			mes= '0'+(date.getMonth() + 1);
		}
		if(date.getHours()<10)
		{
			hr= '0'+date.getHours();
		}

		document.getElementById("start").value=dia+'/'+ mes + '/' + date.getFullYear() + ' ' +  hr + ':00';
		document.getElementById("end").value=dia+'/'+ mes + '/' + date.getFullYear() + ' ' +  hr + ':00';
		document.getElementById("start_format").value	= date.getFullYear()+'-'+mes + '-' + date.getDate() + 'T' +  hr + ":00:00";
		document.getElementById("end_format").value		= date.getFullYear()+'-'+mes + '-' + date.getDate() + 'T' +  hr + ":00:00";
		
    	var listass = document.getElementById("lista").value.split("_");
    	var formattedEventData = [];
    	var label;

    	for(var i=0; i<listass.length; i++){
    		var resultado = listass[i].split("-");
    		for(var j=0; j<resultado.length; j=j+5)
    		{
    			if(resultado[j+4]=='P')
            	{
    				label="${LABEL_PERSONAL}";
            	}
    			else
    			{
    				label="${LABEL_GLOBAL}";
    			}
    			formattedEventData.push({
        			id: resultado[j],
    		        title: resultado[j+1],
    		        start  : resultado[j+2],//'2015-07-07',
    		        end  : resultado[j+3],//'2015-07-07',
                    allDay: false,
                    className: label,
                    type: resultado[j+4]
        		});
    		}
    	}
    	
    	jQuery('#start').datetimepicker(
    			{
    				lang:'es',
    				 i18n:{
    				  es:{
    				   months:['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
    				   dayOfWeek:['Dom','Lun','Mar','Mié','Jue','Vie','Sáb']
    				  }
    				 },
    				format:'d/m/Y H:i',
    				step:30,
    				defaultTime:'08:00',
    				minDate:new Date()/*,
    				 minTime:new Date()*/
    				
    			});
    	jQuery('#end').datetimepicker(
    			{
    				lang:'es',
   				 	i18n:
   				 	{
   				  		es:{
   				   			months:['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
   				   			dayOfWeek:['Dom','Lun','Mar','Mié','Jue','Vie','Sáb']
   				  		}
   				 	},
   					format:'d/m/Y H:i',
   					step:30,
   					defaultTime:'08:00',
   					minDate:new Date()/*,
   					minTime:new Date()*/
   				
   			});
    
        /* initialize the calendar
        -----------------------------------------------------------------*/
        var TravelSearchCriteria = new Object(); 
	    TravelSearchCriteria.id_supplier = "${id_supplier}";  	   
	    var userProfile = "${perfil}";    
	    var id_user = "${id_user}";   
	    
	    var list = [];     	    	   
	    
	     	list.push('PRO'); 
	    	 	list.push('TRA');    	    	        	    	    	
	   
	    TravelSearchCriteria.status = list; 
	  
	    LogisticServiceBean.getTravelByCriteria(TravelSearchCriteria, function(data){ 
	    	$(data).each(function(i,e){
	    		
	    		if((userProfile == 'DRI' && id_user == e.id_user) || userProfile != 'DRI'){
	    			    	    	    		
    	    		var day;
    	    		var month;
    	    		var year;
    	    		    	    	    		 
    	    		if(e.schedule != null){
    	    			day   = e.schedule.getDate();
    	    			month = e.schedule.getMonth() + 1;
    	    			year  = e.schedule.getFullYear();    	    	    			
    	    		}else if (e.started){
    	    			day   = e.started.getDate();
    	    			month = e.started.getMonth() + 1;
    	    			year  = e.started.getFullYear();
    	    		}else{
    	    			day   = e.created.getDate();
    	    			month = e.created.getMonth() + 1;
    	    			year  = e.created.getFullYear();
    	    		}    	    	    		 
    	    		var fecha = year + "-" + month + "-" + day; 
    	    		
    	    		var obj = {
    	    	    	    id:    e.id_travel,
    	    	    	    title: e.travel,
    	    	    	    start: fecha,//e.schedule,//'2015-07-07',
    	    	    	    end:   fecha, //e.starter,//'2015-mes-dis',
    	    	    	    allDay: true,
    	    	    	    type: "t",
    	    	    	    className: e.status == 'PRO' ? 'label label-warning' : (e.status == 'TRA' ? 'label label-primary' : 'label label-success')	    	    	    	   
    	    	    	};
    	    		
    	    		if(e.status == 'PRO'){
	    			obj.link = 'travelwaybill.htm?idt=' + e.id_travel;
    	    		}else if (e.status == 'TRA'){
    	    			obj.link = 'travelonway.htm?idt=' + e.id_travel;
    	    		}else if(e.status == 'CRE'){
    	    			obj.link = 'travelupd.htm?idt=' + e.id_travel + '&accion=upd';
    	    		}    	    	    		
    	    		formattedEventData.push(obj);
	    		}
    	    });        
        
        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();

        
        var calendar = $('#calendar').fullCalendar({
        	monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
        	monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
        	dayNames: ['Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado'],
        	dayNamesShort: ['Dom','Lun','Mar','Mié','Jue','Vie','Sáb'],
        	header: {
				left: 'prev,next',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
            lang: 'es',
            selectable: true,
            selectHelper: false,
            
            editable: false,

            buttonText: {
                prev: '<i class="fa fa-chevron-left"></i>',
                next: '<i class="fa fa-chevron-right"></i>',
       		    month:    'Mes',
       		    week:     'Semana',
       		    day:      'Día'
            },
            allDayText:'Día completo',
            eventClick: function(event, element) {

	            show = true;
	            //console.log(event);
            	if(event.type != undefined && event.type == 't'){
            		location.href = event.link;
            	}else{
            		
	            	$('#do').removeClass('fa fa-check');
	            	$("#doBtn span").text(" Modificar");
	            	$('#do').addClass('fa fa-check');
	
	            	document.getElementById("evId").value=event.id;
	            	document.getElementById("action").value="modificar";
	            		            	
	            	UserServiceBean.getCalendarById(event.id, function(data){ 
	        	    	$(data).each(function(i,e){ 
		        	    		//console.log(e.id_user);	
		            	if ((e.id_user != '${useracegi.id_user}' && event.type == 'G') 
		            				  || ('${useracegi.profile}' == 'DRI' && event.type == 'G')){
		            		show = false;
		            	}
		            	
		            	UserServiceBean.getCalendarById(event.id, reply1);
		            	
		            	if (show){
		            		$('#doBtn').css('display','block');
		            		$('#cancelBtn').css('display','block');
		            		$('#deleteBtn').css('display','block');
		            	}else{
		            		$('#doBtn').css('display','none');
		            		$('#cancelBtn').css('display','block');
		            		$('#deleteBtn').css('display','none');
		            	}
            		}); 
	    				});	
            	}
            },
            
            events: formattedEventData            

        });
	    	}); 
 
        
    	  var reply1 = function(data) {
      			document.getElementById("title").value=data.cal_title;
    			

    			var date = new Date(data.cal_start);
    			var hr=date.getHours(), min=date.getMinutes();
    			var mes=(date.getMonth() + 1);
    			var dia=date.getDate();

    			if(date.getDate()<10)
				{
					dia= '0'+date.getDate();
				}
    			if((date.getMonth() + 1)<10)
    			{
    				mes= '0'+(date.getMonth() + 1);
    			}
    			if(date.getHours()<10)
				{
					hr= '0'+date.getHours();
				}
    			if(date.getMinutes()<10)
				{
					min= '0'+date.getMinutes();
				}
    			document.getElementById("start").value=dia+'/'+ mes + '/' + date.getFullYear() + ' ' +  hr + ':' + min;
    			
    			date = new Date(data.cal_end);
    			hr=date.getHours(), min=date.getMinutes();
    			mes=(date.getMonth() + 1);
    			dia=date.getDate();

    			if(date.getDate()<10)
				{
					dia= '0'+date.getDate();
				}
    			if((date.getMonth() + 1)<10)
    			{
    				mes= '0'+(date.getMonth() + 1);
    			}
    			if(date.getHours()<10)
				{
					hr= '0'+date.getHours();
				}
    			if(date.getMinutes()<10)
				{
					min= '0'+date.getMinutes();
				}
    			document.getElementById("end").value=dia+'/'+ mes+ '/' + date.getFullYear() + ' ' +  hr + ':' + min;
    			
    			//alert("->"+date.getDay());
    			var profile = "${userasegi.profile}";
    			var pro_dri = "${DRIVER_PROFILE}";
    			if(data.cal_level=="P")
    				{ 
    					if(!profile==pro_dri){
    						alert("'${useracegi.profile}'!=DRI");
							radiobtn = document.getElementById("Global");
							radiobtn.checked = false;
    					}
    					radiobtn = document.getElementById("Personal");
    					radiobtn.checked = true;
    				}
    			else
    				{ 
						radiobtn = document.getElementById("Personal");
						radiobtn.checked = false;
						if (show){
							radiobtn = document.getElementById("Global");
							radiobtn.checked = true;
						}
    				}
    			for(i = 0; i <  document.getElementById("alert").length; i++)
    				{
    					if(document.getElementById('alert').options[i].value==data.cal_alert)
    						{
    							document.getElementById('alert').selectedIndex = i;
    						}
    				}
        	};
        	
        $('#doBtn').click(function(){
        	
        	if(document.getElementById("action").value==""){
       			document.getElementById("action").value="agregar";
       		}if($('input[name="level"]:checked').val()=="Personal"){
				document.getElementById("levelChar").value="P";
			}else{
				document.getElementById("levelChar").value="G";
			}  			
  			var str_cal_end = document.getElementById("end_format").value;
  			var str_cal_start = document.getElementById("start_format").value;
   			var cal_start = new Date(str_cal_start);
  			var cal_end = new Date(str_cal_end);
			
  			if ((cal_end < cal_start) || (document.getElementById("end_format").value == "" 
  									  || document.getElementById("start_format").value == ""))
  				alert("La fecha de termino debe ser posterior a la de inicio. Favor de cambiarla.");
  			else 
   				document.getElementById("formulario").submit();
        		
         });
        
        $('#cancelBtn').click(function(){

        	document.getElementById("textBtn").textContent=" Agregar";
        	document.getElementById("title").value="";
        	document.getElementById("start").value="";
        	document.getElementById("end").value="";
        	if ('${useracegi.profile}' != 'DRI'){ 
	        	radiobtn = document.getElementById("Global");
	        	if (radiobtn != null)
					radiobtn.checked = true;
			}
			radiobtn = document.getElementById("Personal");
			radiobtn.checked = true;
        	document.getElementById('alert').selectedIndex = 0;
        	document.getElementById("do").className="fa fa-check";
        	document.getElementById("evId").value="";
        	document.getElementById("action").value="agregar";
        	$('#cancelBtn').css('display','none');
    		$('#deleteBtn').css('display','none');
    		$('#doBtn').css('display','block');

        	var date = new Date();
    		var hr=date.getHours()+1;
    		if(date.getHours()<10)
    		{
    			hr= '0'+date.getHours();
    		}

    		document.getElementById("start").value=date.getDate()+'/'+(date.getMonth() + 1) + '/' + date.getFullYear() + ' ' +  hr + ':00';
    		document.getElementById("end").value=date.getDate()+'/'+(date.getMonth() + 1) + '/' + date.getFullYear() + ' ' +  (hr+1) + ':00';
    		
        	var listass = document.getElementById("lista").value.split("_");
        	var formattedEventData = [];
        	var label;

        	for(var i=0; i<listass.length; i++){
        		var resultado = listass[i].split("-");
        		for(var j=0; j<resultado.length; j=j+5)
        		{
        			if(resultado[j+4]=='P'){
        				label="${LABEL_PERSONAL}";
                	}else{}
        				label="${LABEL_GLOBAL}";
        			}
        			formattedEventData.push({
            			id: resultado[j],
        		        title: resultado[j+1],
        		        start  : resultado[j+2],//'2015-07-07',
        		        end  : resultado[j+3],//'2015-07-07',
                        allDay: false,
                        className: label
            		});
        		}        	
        	
        	jQuery('#start').datetimepicker(
        			{
        				lang:'es',
        				 i18n:{
        				  es:{
        				   months:['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
        				   dayOfWeek:['Dom','Lun','Mar','Mié','Jue','Vie','Sáb']
        				  }
        				 },
        				format:'d/m/Y H:i',
        				step:30,
        				minDate:new Date()
        				
        			});
        	jQuery('#end').datetimepicker(
        			{
        				lang:'es',
       				 	i18n:
       				 	{
       				  		es:{
       				   			months:['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
       				   			dayOfWeek:['Dom','Lun','Mar','Mié','Jue','Vie','Sáb']
       				  		}
       				 	},
       					format:'d/m/Y H:i',
       					step:30,
       					minDate:new Date()
       			});       	 	
 		});
		
        $('#start').change(function(){
        	
        	var str_cal_start = document.getElementById("start").value;
        	var start_parts = str_cal_start.split(" ");
        	var date_start_parts = start_parts[0].split("/");
        	var time_start_parts = start_parts[1].split(":");
        	
        	document.getElementById("start_format").value= date_start_parts[2]+"-"+date_start_parts[1]+"-"+date_start_parts[0]+"T"+time_start_parts[0]+":"+time_start_parts[1]+":00";        	
        	
        });
        
		$('#end').change(function(){
        	
        	var str_cal_end = document.getElementById("end").value;
        	var end_parts = str_cal_end.split(" ");
        	
        	var date_end_parts = end_parts[0].split("/");
        	var time_end_parts = end_parts[1].split(":");
        	
        	document.getElementById("end_format").value= date_end_parts[2]+"-"+date_end_parts[1]+"-"+date_end_parts[0]+"T"+time_end_parts[0]+":"+time_end_parts[1]+":00";
        	
        	
        });
        
		$('#deleteBtn').click(function(){
			
        	document.getElementById("action").value="eliminar";
			document.getElementById("formulario").submit();
			
        	document.getElementById("textBtn").textContent=" Agregar";
        	document.getElementById("title").value="";
        	document.getElementById("start").value="";
        	document.getElementById("end").value="";
        	radiobtn = document.getElementById("Global");
			radiobtn.checked = false;
			radiobtn = document.getElementById("Personal");
			radiobtn.checked = true;
        	document.getElementById('alert').selectedIndex = 0;
        	
        	document.getElementById("do").className="fa fa-check";
        	

        	document.getElementById("evId").value="";
        	document.getElementById("action").value="agregar";

       	 	document.getElementById("cancelBtn").style.display="none";
       	 	document.getElementById("deleteBtn").style.display="none";
			
 		});
		
       
    });
    </script>
</body>

</html>