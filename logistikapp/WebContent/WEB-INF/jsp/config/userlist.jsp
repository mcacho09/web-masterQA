<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Usuarios - LogistikApp</title>
    
    <!-- Dwr script -->
	<script type='text/javascript' src='dwr/interface/UserServiceBean.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
    
    <!-- Sweetalert -->
	<link rel="stylesheet" type="text/css" href="css/sweetalert.css">
	<script src="js/sweetalert.min.js" type="text/javascript"></script>
    
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
    <link rel="stylesheet" type="text/css" href="css/popr.css">
        
    <!-- Favicon -->
    <link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
    
    <!-- google font libraries -->
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
    
    <style>
    	#user-map{
    		height: 450px;
    	}
    </style>
    
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
                                        <li class="active"><span>Configuraci&oacute;n</span></li>
                                    </ol>
                                    <h1 class="pull-left">Usuarios</h1>
                                </div>
                            </div>
                            <c:if test="${fn:length(list) > plan.users}">
                            <div class="row">
                            	<div class="col-lg-4 ">
                            		<div class="alert alert-danger">
    									<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    									<strong>Atenci&oacute;n! </strong> Ha llegado al limite de creaci&oacute;n de usuarios.
  									</div>
  								</div>
                            </div>
							</c:if>
							
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="main-box clearfix">

                                        <header class="main-box-header clearfix">
												<div id="header-tools" class="pull-left">
                                                    <div class="btn-group">
                                                        <button id="checkAllButton" class="btn btn-primary" type="button" title="Seleccionar" data-toggle="tooltip" data-placement="bottom">
                                                            <i class="fa fa-square-o"></i>
                                                        </button>
                                                    </div>
                                                    <div class="btn-group">
                                                        <button id="newButton" class="btn btn-primary" type="button" title="Nuevo" data-toggle="tooltip" data-placement="bottom" <c:out value="${fn:length(list) > plan.users ? 'disabled' : ''}"/> >
                                                            <i class="fa fa-plus"></i> <span class="hidden-xs">Nuevo</span>
                                                        </button>
                                                        <button id="refreshButton" class="btn btn-primary" type="button" title="Actualizar" data-toggle="tooltip" data-placement="bottom">
                                                            <i class="fa fa-refresh"></i>
                                                        </button>
                                                        <button id="editButton" class="btn btn-primary" type="button" title="Modificar" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                            <i class="fa fa-pencil"></i>
                                                        </button>
                                                        <button id="unlockButton" class="btn btn-primary" type="button" title="Resetear Clave" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                            <i class="fa fa-lock"></i>
                                                        </button>
                                                        <!--<button id="activateButton" class="btn btn-primary" type="button" title="Activar/Desactivar" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                            <i class="fa fa-times"></i>
                                                        </button>-->
                                                        <button id="deleteButton" class="btn btn-primary" type="button" title="Eliminar" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                            <i class="fa fa-trash-o"></i>
                                                        </button>
                                                    </div>
                                                    <div id="header-num-selected" class="btn-group num-selected" style="display:none;"><span>NaN</span></div>
                                                </div>
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
                                        </header>

                                        <div class="main-box-body clearfix">
                                            <div class="table-responsive">
                                                <table id="table-user" class="table table-striped table-hover userlist">
                                                    <thead>
                                                        <tr>
                                                            <th><span>Nombre</span></th>
                                                            <th class="text-center"><span>Foto</span></th>
                                                            <th><span>Perfil</span></th>
                                                            <th><span>Puesto</span></th>
                                                            <th><span>Tel&eacute;fono</span></th>
                                                            <th><span>Email</span></th>
                                                            <th class="text-center"><span>Estado</span></th>
                                                            <th class="text-center"><span>Ubicar</span></th>
                                                            <th class="text-center">Opciones</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                         <c:forEach var="usr" items="${list}">

                                                            <c:set var="userprofile" value="NaN"/>
                                                            <c:choose>
                                                                <c:when test="${usr.profile == 'ADM'}"><c:set var="userprofile" value="${GLO_ADM}"/></c:when>
                                                                <c:when test="${fn:contains(usr.profile, 'SUP')}"><c:set var="userprofile" value="${GLO_SUP}"/></c:when>
                                                                <c:when test="${usr.profile == PERFIL_RET}"><c:set var="userprofile" value="${GLO_RET}"/></c:when>
                                                                <c:when test="${fn:contains(usr.profile, 'DRI')}"><c:set var="userprofile" value="${GLO_DRI}"/></c:when>
                                                                <c:when test="${usr.profile == PERFIL_STO}"><c:set var="userprofile" value="${GLO_STO}"/></c:when>
                                                                <c:when test="${usr.profile == PERFIL_DEM}"><c:set var="userprofile" value="${GLO_DEM}"/></c:when>
                                                                <c:when test="${usr.profile == PERFIL_LGK}"><c:set var="userprofile" value="${GLO_LGK}"/></c:when>
                                                                <c:when test="${usr.profile == PERFIL_CCT}"><c:set var="userprofile" value="${GLO_CCT}"/></c:when>
                                                                <c:when test="${usr.profile == PERFIL_SOP}"><c:set var="userprofile" value="${GLO_SOP}"/></c:when>
                                                            </c:choose>

                                                            <tr>
                                                                <c:choose>
                                                                    <c:when test="${usr.profile != 'SOP' }">
		                                                                <td>
		                                                                    <div class="checkbox-nice">
		                                                                        <input type="checkbox" id="m-checkbox-${usr.id_user}" value="${usr.id_user}" <c:if test="${fn:contains(useracegi.profile, 'SUP')&&(useracegi.superuser == 'N')}"><c:out value="disabled='disabled'"/></c:if>/>
		                                                                        <label for="m-checkbox-${usr.id_user}"><a href="profile.htm?id=${usr.id_user}">${usr.username}</a></label><br/>
		                                                                        <div class="usersubhead">
		                                                                            <span class=" badge badge-default"><c:out value="${usr.userlogin}" /></span>
		                                                                        </div>
		                                                                    </div>
		                                                                </td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td>
                                                                            <div class="checkbox-nice">
                                                                                <input type="checkbox" id="m-checkbox-${usr.id_user}" value="${usr.id_user}"  disabled="disabled"/>
                                                                                <label for="m-checkbox-${usr.id_user}">${usr.username}</label>
                                                                            </div>
                                                                        </td>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                <td class="text-center"><img src="${usr.image}" width="50" height="50"/></td>
                                                                <td>
                                                                    <span class="badge badge-${usr.profile != 'SOP' ? 'info' : 'default'}"><c:out value="${userprofile}" /></span>
                                                                    <c:if test="${usr.superuser == 'S' && usr.profile != 'SOP'}">
                                                                        <div style="color:#FABA03;">
                                                                            <i class="fa fa-star"></i>
                                                                            <span>Super Usuario</span>
                                                                        </div>
                                                                    </c:if>
                                                                </td>
                                                                <td><span>${usr.job}</span></td>
                                                                <td><span>${usr.phone1}</span></td>
                                                                <td><span>${usr.email}</span></td>
                                                                <td class="text-center"><c:if test="${usr.profile != 'SOP'}"><span class="badge badge-${usr.active == ACTIVE ? 'success' : 'danger'} activebtn" style="cursor:pointer;" data-id="${usr.id_user }" data-active="${usr.active }" data-toggle="tooltip" data-placement="bottom" title="Clic para ${ usr.active==ACTIVE ? 'desactivar':'activar'}"><i class="fa fa-${usr.active == ACTIVE ? 'check' : 'times'}"></i> ${usr.active == ACTIVE ? 'Activo' : 'Inactivo'}</span></c:if></td>
                                                                <td class="text-center">
                                                                <c:if test="${usr.profile != 'SOP'}">
                                                                	<button type="button" data-id="${usr.id_user }" class="locate btn btn-link btn-sm">
                                                                		<i class="fa fa-map-marker"></i>
                                                                	</button>
                                                                </c:if>
                                                                </td>
                                                                <td class="text-center">
                                                                <c:if test="${usr.profile != 'SOP'}">
                                                                	<button type="button" class="btn btn-link btn-sm popr" data-id="${usr.id_user }">
                                                                		<i class="fa fa-ellipsis-v fa-lg"></i>
                                                                	</button>
                                                                	<div class="popr-box list-group" data-box-id="${usr.id_user }">
																		<li class="list-group-item popr-item edit" data-id="${usr.id_user }">Editar</li>
																		<li class="list-group-item popr-item delete" data-id="${usr.id_user }">Eliminar</li>
																		<li class="list-group-item popr-item change-password" data-id="${usr.id_user }">Cambiar contraseña</li>
																		<li class="list-group-item popr-item locate" data-id="${usr.id_user }">Ubicar</li>
																		<li data-id="${usr.id_user}" class="list-group-item popr-item notify">Enviar notificación</li>
																	</div>
																</c:if>
                                                                </td>
                                                            </tr>
													</c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>

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
        
        <div class="modal fade" id="modal-user-map">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Ubicando usuario...</h4>
					</div>
					<div class="modal-body" id="user-map"></div>
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
    
    <!-- this page specific scripts -->
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/dataTables.fixedHeader.js"></script>
    <script src="js/dataTables.tableTools.js"></script>
    <script src="js/jquery.dataTables.bootstrap.js"></script>
    <script src="js/popr.min.js"></script>
	
	<script type='text/javascript' src='dwr/interface/LogisticServiceBean.js'></script>
	<script type='text/javascript' src='dwr/interface/UserNotificationBean.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
    
    <!-- this page specific inline scripts -->
    <script type="text/javascript">
    var id
    var mapOptions = {
    		zoom: 20,
    		center: new google.maps.LatLng(0,0)
        };
    var map = new google.maps.Map(document.getElementById('user-map'), mapOptions);
    var markerUser = new MarkerWithLabel({                   
        position: new google.maps.LatLng(0,0),
        map: map,                                       
        icon: 'img/driver_icon.png',
        labelAnchor: new google.maps.Point(50, 51),
        labelClass: "labels", // the CSS class for the label
        labelStyle: {opacity: 1},
        title: 'Posición actual'
    });
   var myContentString = '<div id="content">'+
    '<div id="bodyContent">'+
    '<p><span>Posición actual</span></p>'
    '</div>'+
    '</div>';
    markerUser.infowindow = new google.maps.InfoWindow({
    	content: myContentString,
        maxWidth: 150
    });
        
    markerUser.addListener('mouseout', function() {
    	this.infowindow.close();
    });
    markerUser.addListener('mouseover', function() {
    	this.infowindow.open(map,this);
    });
    $("#modal-user-map").on("shown.bs.modal", function () {
        google.maps.event.trigger(map, "resize");
        if (id || id > 0) {
        	setDRIPosition(id, true)
        }
    });
     $(document).ready( function() {
    	
    	$('.popr').popr()
    	
    	$(document).on('click', '.edit', function(){
    		location.href = "cfguserupd.htm?id="+this.dataset.id+"&accion=upd"
    	})
    	
    	$(document).on('click', '.delete', function() {
    		location.href = "userdel.htm?ids="+this.dataset.id;
    	})
    	
    	$(document).on('click', '.change-password', function() {
    		location.href = 'passwdprofileupd.htm?id=' + this.dataset.id +'&list=true';
    	})

    	$(document).on('click', '.notify', function() {
    		var btn = this
    		swal({
    			title: "Enviar notificación",
    			text: "Ingrese el texto:",
    			type: "input",
    			showCancelButton: true,
    			closeOnConfirm: false,
    			animation: "slide-from-top",
    			inputPlaceholder: "Notificación"
    		}, function(inputValue){
    			if (inputValue === false) return false;
    			  
    			if (inputValue === "") {
    				swal.showInputError("Ingrese alguna palabra!");
    				return false
    			}
    			  
    			var obj = {created: Date.now(), icon: 'fa fa-bell-o', id_user: ${my_id_user} , message: inputValue, priority: 1, id_supplier: ${useraccess.id_supplier}, link: "alertlist.htm"}
    			var ids = [btn.dataset.id]
    			UserNotificationBean.createNotificationWithList(obj, ids, function(data) {
    				if (data == 0) {
    					swal('Error', "Notificación no enviada\nIntente más tarde", 'error')
    				} else {
    					swal('Exito', 'Notificación enviada', 'success')
    				}
    			})
    		});
    	})
    	
    	$(document).on('click', '.locate', function() {
    		$('#modal-user-map').modal()
    		var isRun = !!id || id > 0
    		id = this.dataset.id
    		if (!isRun) {
    			Concurrent.Thread.create(function(){setInterval(function(){setDRIPosition(id)}, 10000 * 60)})
    		}
    	})
    	
    	<c:if test="${fn:contains(useracegi.profile, 'SUP')&&(useracegi.superuser == 'N')}">
    		$('#newButton').attr('disabled', 'disabled');
    		$('#checkAllButton').attr('disabled','disabled');
    		$('#refreshButton').attr('disabled','disabled');
    	</c:if>
        
        var table = $('#table-user').dataTable( {
            'paging': false,
            'info': false,
            'searching': false,
            'pageLength': 100
            }); // table

        $( '#checkAllButton' ).on( 'click', function() {
            var rows = table.fnGetNodes();
            
            if ( $(this).find("i").hasClass('fa-square-o') )
                $('input[type="checkbox"]', rows).prop('checked', true );
            else
                $('input[type="checkbox"]', rows).prop('checked', false );
            
            $(this).find("i").toggleClass("fa-square-o fa-check-square-o")
            
            var qty = $('input[type="checkbox"]:checked', rows).length;
            if ( qty == 0 ) {
                $('#editButton').attr('disabled', 'disabled');
                $('#unlockButton').attr('disabled', 'disabled');
                $('#activateButton').attr('disabled', 'disabled');
                $('#deleteButton').attr('disabled', 'disabled');
                $("#header-num-selected").hide();
                }
            else if ( qty > 0 ) {
                $('#editButton').attr('disabled', 'disabled');
                $('#unlockButton').attr('disabled', 'disabled');
                if( qty == 1 ) {
                        $('#editButton').removeAttr('disabled');
                        $('#unlockButton').removeAttr('disabled');
                        $("#header-num-selected").text( qty + " usuario seleccionado" );
                    }
                else
                        $("#header-num-selected").text( qty + " usuarios seleccionados" );
                
                $("#header-num-selected").show();
                }
            
            }); // checkAllButton
        
        $('#newButton').click( function() {
            window.location.href = "cfguseradd.htm?accion=add";
            }); // newButton
            
        $('#editButton').click( function() {

            // Se obtienen todos los ids de los usuarios a modificar
            var ids = [];
            $("input[type=checkbox]:checked").each(function(index,e) {
                    ids.push($(this).attr("value"));
                    });
            if ( ids.length >0 )
                    location.href = "cfguserupd.htm?id="+ids+"&accion=upd";
        	
            }); // editButton
        
        $('#refreshButton').click( function() {
            location.reload(true);
            }); // refreshButton
            
        $('#deleteButton').click( function() {
        	
            // Se obtienen todos los ids de los usuarios a eliminar
            var ids = [];
            $("input[type=checkbox]:checked").each(function(index,e) {
            	    ids.push($(this).attr("value"));
            	    });
            if ( ids.length >0 )
            	    location.href = "userdel.htm?ids="+ids;
            
            }); // deleteButton
        
        $("input[type=checkbox]").click(function(index,e) {
            // Se controla el evento de cada click de input[type=checkbox]
            // Se obtienen todos los registros de la tabla
            var rows = table.fnGetNodes();
            // Se obtiene la cantidad de input[type="checkbox"] == checked
            // para determinar que botones quedan habilitados o no
            var qty = $('input[type="checkbox"]:checked', rows).length;
            if ( qty == 1 ) {
                    $('#editButton').removeAttr('disabled');
                    $('#unlockButton').removeAttr('disabled');
                    $('#activateButton').removeAttr('disabled');
                    $('#deleteButton').removeAttr('disabled');
                    $("#header-num-selected").text( qty + " usuario seleccionado" );
                    $("#header-num-selected").show();
                    
                    // se obtiene id de check seleccionado
                    if($('#m-checkbox-${useracegi.id_user}').is(':checked')){
                	    $('#activateButton').attr('disabled', 'disabled');
                	    $('#deleteButton').attr('disabled', 'disabled');
                    }
                }
            else {
                if ( qty == 0 ) {
                	    $('#editButton').attr('disabled', 'disabled');
                	    $('#unlockButton').attr('disabled', 'disabled');
                	    $('#activateButton').attr('disabled', 'disabled');
                	    $('#deleteButton').attr('disabled', 'disabled');
                	    $("#header-num-selected").hide();
                	    }
                else {
                    $('#editButton').attr('disabled', 'disabled');
                    $('#unlockButton').attr('disabled', 'disabled');
                	    $('#activateButton').removeAttr('disabled');
                	    $('#deleteButton').removeAttr('disabled');
                	    $("#header-num-selected").text( qty + " usuarios seleccionados" );
                	    $("#header-num-selected").show();
                	}
                }
            });
            
            $('#unlockButton').on('click',function(){
            	var id_usr = parseInt($($('input[type="checkbox"]:checked')[0]).val());
            	location.href = 'passwdprofileupd.htm?id=' + id_usr+'&list=true';
            });
            
        }); // ready
        
        $('.activebtn').on('click', function(){
        	var btn = this;
        	UserServiceBean.getUserById($(this).data('id'), function(data){
        		if (data != null && data != undefined && $(btn).data('id') != ${my_id_user}){
        			swal({   
        				title: "Alerta",
        				text: "¿En verdad desea " + ($(btn).data('active') == 'S'?'desactivar':'activar') + ' este usuario?',   
        				type: "warning",   
        				showCancelButton: true,   
        				confirmButtonColor: "#DD6B55",   
        				confirmButtonText: "Aceptar",
        				cancelButtonText: 'Cancelar',
        				closeOnConfirm: false 
        			}, function(){   
        				var UpdUserDTO = data;
        				UpdUserDTO.active = ($(btn).data('active') == 'S'?'N':'S');
        				UserServiceBean.updUser(UpdUserDTO, function(data){
        					var obj = {};
        					if (data > 0){
        						obj.title = 'Mensaje';
        						obj.msj = 'Usuario ' + ($(btn).data('active') == 'S'?'activado':'desactivado') + ' con exito';
        						obj.type = 'success';
        						$(btn).removeClass('badge-' + ($(btn).data('active') == 'S'?'success':'danger'));
        						$(btn).addClass('badge-' + ($(btn).data('active') == 'S'?'danger':'success'));
        						$(btn).html(($(btn).data('active') == 'S'?'<i class="fa fa-times"></i> Inactivo':'<i class="fa fa-check"></i> Activo'));
        						$(btn).data('active', ($(btn).data('active') == 'S'?'N':'S'));
        					}else{
        						obj.title = 'Error';
        						obj.msj = 'Error al ' + ($(btn).data('active') == 'S'?'activar':'desactivar') + ' el usuario, intente más tarde';
        						obj.type = 'error';
        					}
        					swal({
        						title: obj.title, 
        						text: obj.msj, 
        						type: obj.type,
        						showConfirmButton: true,
        						timer: 2000
        					});
        				});
        			});
        		}
        	});
        });
        
        function setDRIPosition(id, alert) {
        	LogisticServiceBean.getUserPositionByIdUserAndDate(id, Date.now(), function(data) {
    			if (data && data.length > 0) {
		    		var item = data[data.length - 1]
		    		var latlng = new google.maps.LatLng(item.latitude, item.longitude)
		    		map.setCenter(latlng)
		    		markerUser.setPosition(map.getCenter())
    			}else if (alert){
    				var latlng = new google.maps.LatLng(0, 0)
		    		map.setCenter(latlng)
		    		markerUser.setPosition(map.getCenter())
    				swal('Alerta', "Sin ubicaciones registradas para el día de hoy", "info")
    			}
    		})
        }
        
    </script>   
    
</body>

</html>