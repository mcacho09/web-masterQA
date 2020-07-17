<%@ include file="/includes/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>Busqueda - LogistikApp</title>

    <!-- bootstrap -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
    
    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />
    
    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css" />
	
    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.fixedHeader.css">
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.tableTools.css">
    <link rel="stylesheet" type="text/css" href="css/logistikapp.css">

    <!-- Favicon -->
    <link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
    
    <!-- google font libraries -->
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>

	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
	<![endif]-->
	<style>
		td {
		    padding-top: 7px;
		    padding-right: 3px;
		}
		.radio{
			display:inline;
		}
		#radios{
			padding: 10px;
		}
		
		.visible{
			visibility:hidden;
		}
		
	</style>
        <script type="text/javascript">
            var timerStart = Date.now();
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
									<li class="active"><a href="customer.htm"><span>Clientes</span></a></li>
								</ol>
								<h1 class="pull-left">B&uacute;squeda de clientes</h1>
							</div>
						</div>
						<div class="row">
								<div class="col-lg-12">
									<div class="main-box clearfix">
										<header class="main-box-header clearfix">
										    <h2>${fn:length(list)} cliente${fn:length(list)>1?'s':''} encontrado${fn:length(list)>1?'s':''} para: <span class="emerald">&quot;${searchByName}&quot;</span></h2>
											<small class="gray">Tiempo transcurrido: </small>
										</header>
										
										<div class="main-box-body clearfix">
											<div id="search-form">											
													<div class="input-group">
														<input id="isearch" MaxLength="50" onkeypress=" return validar(event)" type="text" name="searchByName" value="${searchByName}" class="form-control input-lg" />
														<div class="input-group-btn">
															<button id="btn" class="btn btn-lg btn-primary visible" type="submit">
																<i class="fa fa-search"></i> Buscar
															</button>
														</div>
													</div>
													<div id="radios" class="form-group">
													
														<c:if test="${fn:length(list) > 0}">
														   <a href="storesearchdoc.htm?searchByName=${searchByName}&optionsRadios=${opcionRadio}" class="btn btn-info btn-md"><i class="fa fa-download"></i> Descargar reporte</a>
														</c:if>														
														
														<div class="radio">
															<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" ${opcionRadio == null || opcionRadio == 'option1'?'checked':'' }>
															<label for="optionsRadios1">
																B&uacute;squeda por nombre
															</label>
														</div>
														<div class="radio">
															<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2" ${opcionRadio == 'option2'?'checked':'' }>
															<label for="optionsRadios2">
																B&uacute;squeda por c&oacute;digo
															</label>
														</div>
													</div> 
											</div>

											<ul id="search-results">
											    <c:forEach var="str" items="${list}">
											    <li>
											        <h3 class="title text-primary">
											        <c:choose>
											        	<c:when test="${(fn:contains(useracegi.profile,'SUP') || fn:contains(useracegi.profile,'DRI')) && useracegi.superuser == 'S'}">
											        		<a href="storeupd.htm?ids=${str.id_store}&searchByName=${searchByName}">${str.store_name}</a>
											        	</c:when>
											        	<c:otherwise>
											        		${str.store_name}
											        	</c:otherwise>
											        </c:choose>
											        
											        </h3>
											        
											        <div class="link-title">
											            ${str.address1}, ${str.address2}. ${str.city_name}<br/>
											            <span class="label label-default"> <i class="fa fa-info-circle"></i> <b> ${str.store_code}</b></span>
														<span class="label label-info"><i class="fa fa-building"></i> ${str.retail_name}</span>
														<span class="label label-info"><i class="fa fa-tags"></i> ${str.store_category_name}</span>
														<c:if test="${not empty supplier.shelf && supplier.shelf == 'S' && not empty str.shelf && str.shelf == 'S'}"><span class="label label-warning"><i class="fa fa-star"></i> Promoci&oacute;n</span></c:if>
													</div>
													
														<div class="" style="border: 1px solid white;">
															<table class="">
															    <tbody>
															    <c:forEach var="tra" items="${str.list_travel}">
																<c:if test="${ useracegi.profile != 'DRI' || useracegi.id_user == tra.id_user }">
																	<c:choose>
																		<c:when test="${TRAVEL_CANCEL == tra.estado_travel}">
																			<c:set var="color" value="danger" />
																			<c:set var="edo_tra" value="${GLO_CANCEL}" />
																			<c:set var="icon" value="times" />
																		</c:when>
																		<c:when test="${TRAVEL_FINISHED == tra.estado_travel}">
																			<c:set var="color" value="success" />
																			<c:set var="edo_tra" value="${GLO_FINISHED}" />
																			<c:set var="icon" value="check" />
																		</c:when>
																		<c:when test="${TRAVEL_PROGRAM == tra.estado_travel}">
																			<c:set var="color" value="warning" />
																			<c:set var="edo_tra" value="${GLO_PROGRAM}" />
																			<c:set var="icon" value="clock-o" />
																		</c:when>
																		<c:when test="${TRAVEL_STARTED == tra.estado_travel}">
																			<c:set var="color" value="primary" />
																			<c:set var="edo_tra" value="${GLO_STARTED}" />
																			<c:set var="icon" value="truck" />
																		</c:when>
																	</c:choose>
	
															        <tr>
																		<c:choose>
																			<c:when test="${TRAVEL_PROGRAM == tra.estado_travel}">
																				<td>
																				    <span class="label label-${color}"><i class="fa fa-calendar"></i> <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${tra.schedule}" /></span>
																				</td>
																				<td>
																					<span class="label label-${color}"><i class="fa fa-${icon}"> </i> ${edo_tra}</span>
																				</td>
																				<td>
																					<a style="text-decoration:none;" href="travelonway.htm?idt=${tra.id_travel }"><span class="label label-default">Viaje</span> ${tra.travel_name}</a>
																				</td>
																				<td class="hidden-xs"></td>
																				<td>
																					<span class="hidden-xs"><span class="label label-default"> Conductor</span> ${tra.user_name}</span>
																				</td>
																				<td class="hidden-xs"></td>
				
																			</c:when>
																			<c:when test="${TRAVEL_STARTED == tra.estado_travel || TRAVEL_CANCEL == tra.estado_travel || TRAVEL_FINISHED == tra.estado_travel}">
																				<td>
																					<span class="label label-${color}"><i class="fa fa-calendar"></i> <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${tra.schedule}" /></span>
																				</td>
																				<td>
																					<span class="label label-${tra.checkin != null ? 'success':'danger'}"><i class="fa fa-${tra.checkin != null ? 'check':'times'}"> </i> ${tra.checkin != null ? 'Visitado' : 'No visitado'} <fmt:formatDate value="${tra.checkin}" pattern="dd/MM HH:mm" /></span>
																				</td>
																				<td>
																					<a style="text-decoration:none;" href="travelreport.htm?idt=${tra.id_travel }"><span class="label label-default">Viaje</span> ${tra.travel_name} </a>
																				</td>
																				<td>
																				    <span class="label label-${color} hidden-xs"><i class="fa fa-${icon}"></i> ${edo_tra}</span>
																				</td>
																				<td>
																					<span class="hidden-xs"><span class="label label-default"> Conductor</span> ${tra.user_name}</span>
																				</td>
																				<td>
																					<c:if test="${((tra.comment != null && tra.comment != '') || (tra.note != null && tra.note != '') || (tra.image1 != null && tra.image1 != '') || (tra.image2 != null && tra.image2 != '') || (tra.image3 != null && tra.image3 != ''))}">
																						<span data-id="${tra.id_waybill}" data-comment="${tra.comment }" data-note="${tra.note }" class="label label-${((tra.comment != null && tra.comment != '') || (tra.note != null && tra.note != '') || (tra.image1 != null && tra.image1 != '') || (tra.image2 != null && tra.image2 != '') || (tra.image3 != null && tra.image3 != ''))?'success':'default'} comment" data-toggle="tooltip" style="cursor:pointer; data-placement="bottom" title="Clic para ver comentario" data-index="${waybill.comment}">Comentario</span>
																						<!-- <button data-id="${waybill.id_waybill}" data-comment="${waybill.comment }" data-note="${waybill.note }" class="btn btn-${((waybill.comment != null && waybill.comment != '') || (waybill.note != null && waybill.note != '') || (waybill.image1 != null && waybill.image1 != '') || (waybill.image2 != null && waybill.image2 != '') || (waybill.image3 != null && waybill.image3 != ''))?'success':'default'} btn btn-sm btn-primary btn-custom-circle comment" data-name="${waybill.name }" data-toggle="tooltip" data-placement="bottom" title="Comentario" data-index="${waybill.comment}">
																						<i class="fa fa-comment"></i></button> -->
																					</c:if>
																				</td>	
																			</c:when>
																			
																		</c:choose>
																		<td>
																			<c:if test="${tra.outrange != null && tra.outrange == 'S'}">
																				<span class="label label-warning">Fuera de rango</span>
																			</c:if>
																		</td>
																		<td>
																			<span class="label label-default">Ruta</span> ${ tra.route_name }
																		</td>
																	</tr>
																</c:if>
																</c:forEach>
																</tbody>
															</table>
														</div>
													
												</li>
											</c:forEach>
											</ul>
											
										</div>
									</div>
								</div>
							</div>
					</div>
					<footer id="footer-bar" class="row">
						<c:import url="/html/footer.html" />
					</footer>
					<div class="modal fade" id="modal-comments">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header bg-primary">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="comment-title"></h4>
							</div>
							<div class="modal-body">
								<div class="row hidden">
									<div class="col-xs-12">
										<button type="button" class="btn btn-primary btn-md pull-right" data-toggle="tooltip" data-placement="left" title="Adjuntar imagen"><i class="fa fa-camera"></i></button>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-horizontal">
												<div class="form-group">
													<label for="textareaComment" class="col-sm-2 control-label">Movimientos:</label>
													<div class="col-sm-9">
														<textarea maxlength="255" name="comment" id="tcomment" class="form-control" style="resize:none;" rows="3" required="required" required="required" ${'disabled' }></textarea>
													</div>
												</div>
											<div class="form-group">
												<label for="textareaNote" class="col-sm-2 control-label">Comentarios:</label>
												<div class="col-sm-9">
													<textarea maxlength="255" name="note" id="tnote" class="form-control" style="resize:none;" rows="3" required="required" ${'disabled' }></textarea>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-4 imgcontent" style="padding:0!important;">
										<img class="img-responsive" id="imgCont1" src="img/img_default.jpg'" style="width:100%; height:250px;">
									</div>
									<div class="col-lg-4 imgcontent" style="padding:0!important;">
										<img class="img-responsive" id="imgCont2" src="img/img_default.jpg'" style="width:100%; height:250px;">
									</div>
									<div class="col-lg-4 imgcontent" style="padding:0!important;">
										<img class="img-responsive" id="imgCont3" src="img/img_default.jpg'" style="width:100%; height:250px;">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" id="closemodal" data-dismiss="modal">Cerrar</button>
							</div>
						</div>
					</div>
				</div>
				</div> <!-- Div del footer -->
			</div>
		</div>
	</div>

	<!-- global scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.min.js"></script>

	<!-- this page specific scripts -->
	<script src="js/jquery.dataTables.js"></script>
	<script src="js/dataTables.fixedHeader.js"></script>
	<script src="js/dataTables.tableTools.js"></script>
	<script src="js/jquery.dataTables.bootstrap.js"></script>
	
	<script type='text/javascript' src='dwr/interface/LogisticServiceBean.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	<script src="js/pace.min.js"></script>

    <!-- this page specific inline scripts -->
    <script type="text/javascript">
    
	    function validar(evt){
			ky = evt.keyCode;		    
		    patron =/^[0-9a-zA-ZáéíóúàèìòùÀÈÌÒÙÁÉÍÓÚñÑüÜ.,_ \-\&\!\#\$\%\=\*\s/]+$/i;     	       
		    k = String.fromCharCode(ky); 	    
		    return  patron.test(k);
	}	 
	 $('#isearch').on({
		 'keyup':colorBorde,
		 'click':colorBorde,
		 'focus':colorBorde
	 });
	 
	 $('#btn').on('click', function(){
		 if(document.getElementById("isearch").value.length >= 3){	
		  location.href = "?optionsRadios=" + $('input[name=optionsRadios]:checked').val() + "&searchByName=" + $('#isearch').val();
		 }
	 });
	 
	 function colorBorde(e){	
		 if (e.keyCode == 13){
			 if (document.getElementById("isearch").value.length >=3){
				 location.href = "?optionsRadios=" + $('input[name=optionsRadios]:checked').val() + "&searchByName=" + $('#isearch').val();
			 }
		 }else{
			 if(document.getElementById("isearch").value.length < 3){	
			 		this.style.borderColor = 'red';
			 		$('#btn').addClass('visible');
			 	}else{
			 		this.style.borderColor = 'green';		 		
			 		$('#btn').removeClass('visible');
			 	}
		 }
	 }
 
	$(document).ready( function() {
		
		if($('#isearch').val().length <3){	
	 		$('#btn').addClass('visible');
	 	}else{
	 		$('#btn').removeClass('visible');
	 	}    	
	
        var table = $('#table-example').dataTable( {
            'paging': false,
            'info': false,
            'searching': false,
            "language": {
                "zeroRecords":    "No se encontraron clientes"
            },            
            'pageLength': 100
            }); // table

        //$("#searchByName").focus();
        $("#searchByName").select();
            
        }); // ready

		function show(id_store) {
			document.getElementById("menos-" + id_store).style.display = "inline";
			document.getElementById("mas-" + id_store).style.display = "none";
			document.getElementById("idStr-" + id_store).style.display = "inline";
		};
		function hide(id_store) {
			document.getElementById("mas-" + id_store).style.display = "inline";
			document.getElementById("menos-" + id_store).style.display = "none";
			document.getElementById("idStr-" + id_store).style.display = "none";
		}
		
		//tiempo de busqueda
	    var stime = Date.now()-timerStart;
		
	$(window).load(function() {
		$(".gray").text("Tiempo transcurrido: 0."+stime+" segundos.");	

       //$("#isearch").focus();
        $("#isearch").select();
       });	
	
	  /*$('.btncomment').on('click', function () {
		var msg = $(this).data('comment');
		console.log($(this).data());
	    //$(this).button('complete') // button text will be "finished!"
	    swal("Comentario", msg, 'info')
	  });
	  $('.btnnote').on('click', function () {
			var msg = $(this).data('note');
			console.log($(this).data());
		    //$(this).button('complete') // button text will be "finished!"
		    swal("Nota", msg, 'info')
		  }); */
	$(window).on('resize',function(){
		if ($(document).width() <= 575){
			$('table').each(function(i,e){ $(e).addClass('table'); $(e).parent().addClass('table-responsive');});
		}else{
			$('table').each(function(i,e){ $(e).removeClass('table'); $(e).parent().removeClass('table-responsive');});
		}
	});
	
	var actual_store = 0;
    $('.comment').on('click', function(){
    	$('#modal-comments').modal('show');
    	actual_store = $(this).data('id');
        $('#comment-title').text($(this).data('name'));
        swal({
        	title: 'Espere por favor',
        	text: 'Cargando información ...',
        	showConfirmButton: false
        });
    	LogisticServiceBean.getWayBillById(actual_store, function(d){
        	$('#tcomment').val(d.comment);
        	$('#tnote').val(d.note);
        	$('#imgCont1').attr('src', d.image1!=null?d.image1:'img/img_default.jpg');
        	$('#imgCont2').attr('src', d.image2!=null?d.image2:'img/img_default.jpg');
        	$('#imgCont3').attr('src', d.image3!=null?d.image3:'img/img_default.jpg');
        	swal.close();
    	});
    });
    </script>
		
</body>
</html>
