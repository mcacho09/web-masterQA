<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib  prefix="authz" uri="http://acegisecurity.org/authz" %>


<div class="container">

	<a href="home.htm" id="logo" class="navbar-brand"> <img
		src="img/logo-white.png" alt=""
		class="normal-logo logo-white hidden-xs" /> <img
		src="img/logo-color.png" alt=""
		class="normal-logo logo-black hidden-xs" /> <img
		src="img/logo-small-color.png" alt=""
		class="small-logo hidden-lg hidden-md hidden-sm" />
	</a>

	<div class="clearfix">
		<button class="navbar-toggle" data-target=".navbar-ex1-collapse"
			data-toggle="collapse" type="button">
			<span class="sr-only">Toggle navigation</span> <span
				class="fa fa-bars"></span>
		</button>

		<div class="nav-no-collapse navbar-left pull-left">
			<ul class="nav navbar-nav pull-left">
				<li class=" hidden-sm hidden-xs"><a class="btn"
					id="make-small-nav"> <i class="fa fa-bars"></i></a></li>
			</ul>
		</div>

		<div class="nav-no-collapse pull-right" id="header-nav">
			<ul class="nav navbar-nav pull-right">

				<!-- user -->
				<li class="dropdown profile-dropdown hidden-xs"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <img
						src="${useracegi.image}" alt="" /> <span class="hidden-xs">${useracegi.fullname}
						<authz:authorize ifNotGranted="SOP"><b class="caret"></b></authz:authorize></span>
						
				</a>
				<authz:authorize ifNotGranted="SOP">
					<ul class="dropdown-menu">
						<li><a href="profile.htm?id=${useracegi.id_user}"><i
								class="fa fa-user"></i>Mi Perfil</a></li>
					</ul>
				</authz:authorize>
				</li>
				<!-- /user -->

				<!-- user -->
				<li class="dropdown profile-dropdown hidden-lg hidden-md hidden-sm">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					    <img src="${useracegi.image}" alt="" /> <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="profile.htm"><i class="fa fa-user"></i>Mi Perfil</a></li>
						<li><a href="todolist.htm"><i class="fa fa-tasks"></i>Tareas</a></li>
						<!--<li><a href="#"><i class="fa fa-question-circle"></i>Ayuda</a></li>-->
						<li><a href="j_security_logout"><i class="fa fa-power-off"></i> <span>Salir</span></a></li>
					</ul>
				</li>
				<!-- /user -->

				<!-- Buscar -->
				<authz:authorize ifNotGranted="SOP, ADM">
				<li class="mobile-search"><a class="btn"> <i
						class="fa fa-search"></i>
				</a>

					<div class="drowdown-search" id="drowdown-search">
						<form id="search" action="javascript:verificador();">
							<div class="form-group" style= "margin: 0px 4px";>
								<input type="text" onkeyup="Borde(event)" onclick="Borde(event)" onfocus="Borde(event)" onkeypress=" return valida(event)" MaxLength="50" class="form-control"
									placeholder="Buscar cliente.." id="searchByName"
									name="searchByName" value="${searchByName}" /> <i
									class="fa fa-search nav-search-icon"></i>
							</div>
						</form>
					</div></li>
					</authz:authorize>
				<!-- /Buscar -->

				<!-- notifications -->
				<authz:authorize ifNotGranted="SOP, ADM">
				<li class="dropdown" ><a class="btn dropdown-toggle"
					data-toggle="dropdown" id="bell" onclick="getAlert()" data-action="a"> <i class="fa fa-bell-o"></i>
						<c:if test="${nttdto.alert_qty>0}">
							<span class="count red-bg"><c:out
									value="${nttdto.alert_qty}" /></span>
						</c:if>
				</a>

					<ul class="dropdown-menu notifications-list" id="notifications">
						
					</ul>
				</li>
				</authz:authorize>
				<!-- /notifications -->

				<!-- messages -->
				<authz:authorize ifNotGranted="ADM">
				<li class="dropdown"><a class="btn dropdown-toggle"
					data-toggle="dropdown" id="envelope" data-action="m"> <i
						class="fa fa-envelope-o"></i> <c:if test="${nttdto.message_qty>0}">
							<span class="count red-bg"><c:out
									value="${nttdto.message_qty}" /></span>
						</c:if>
				</a>
					<ul class="dropdown-menu notifications-list messages-list" id="messages">
				
					</ul></li>
				</authz:authorize>
				<!-- /messages -->

				<!-- todo -->
				<authz:authorize ifNotGranted="ADM">
				<li class="dropdown hidden-xs">
				    <a class="btn dropdown-toggle" data-toggle="dropdown" id="tasks" data-action="t">
				        <i class="fa fa-tasks"></i>
						<c:if test="${nttdto.todo_qty>0}"><span class="count red-bg"><c:out value="${nttdto.todo_qty}" /></span></c:if>
					</a>
					<ul class="dropdown-menu notifications-list" id="tareas">
						
					</ul></li>
				</authz:authorize>
				<!-- /todo -->
				
				<!-- Tour Layout -->
				<!-- hidden-xs -->
				<li>
					<a class="hidden-xs" id="tour" style="display:none;">
						<i class="fa fa-question-circle"></i>
					</a>
				</li>
				<!-- Tour layout -->
				
				<!-- logout -->
				<li class="hidden-xs"><a href="j_security_logout" class="btn">
						<i class="fa fa-power-off"></i>
				</a></li>
				<!-- /logout -->

			</ul>
		</div>

	</div>
</div>

<!-- hopscotch -->
<script src="js/hopscotch.js"></script>
<link rel="stylesheet" type="text/css" href="css/libs/hopscotch.css">

<script src="js/concurrent.thread.js" type="text/javascript"></script>
<!-- Dwr script -->
<script type='text/javascript' src='dwr/interface/UserServiceBean.js'></script>
<script type='text/javascript' src='dwr/interface/UserNotificationBean.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<!-- Sweetalert -->
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">
<script src="js/sweetalert.min.js" type="text/javascript"></script>

<script src="js/push.min.js"></script>
<script src="js/vue.js"></script>
<script src="js/tinycon.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/spinkit/1.2.5/spinkit.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/spinkit/1.2.5/spinners/10-fading-circle.min.css" />

<style>
	#notifications > .item.new{
		background: lightgoldenrodyellow;
	}
	
	#notifications > .item{
		display: block;
		height: 50px;
		width: 320px;
		max-width: 320px;
	}

	#notifications > .item a {
		padding: 0!important;
		height: 100%;
		width: 100%;
	}

	#notifications > .item a img, #notifications > .item a .content-list{
		display: inline-block;
		float: left;
	}

	#notifications > .item a img{
		width: 36px;
		height: 36px;
		border-radius: 50%;
		margin: 7px;
	}

	#notifications > .item a .content-list {
		width: 270px;
		height: 100%;
		position: relative;
	}

	#notifications > .item a .content-list .content {
		height: 100%;
		margin-bottom: 0;
		padding: 2px;
		overflow: hidden;
		font-size: 1.05em;
		overflow: hidden;
    	white-space: nowrap;
    	text-overflow: ellipsis;
	}
	
	#notifications > .item a .time {
	    margin-left: 0px!important;
	    position: absolute;
	    right: 0!important;
	    bottom: 0;
	    background: transparent;
	    font-size: .75em;
	    margin-right: 5px;
	}
	
	.strong{
		font-weight: bold;
		color: #797979;
	}
	@media screen and (max-width: 320px){
		#notifications{
			position: absolute;
			left: 90px;
		}
	}
	
	@media screen and (min-width: 321px) and ( max-width: 400px){
		#notifications{
			position: absolute;
			left: 60px;
		}
	}
</style>

<script type="text/x-script.multithreaded-js">
var total = "${nttdto.alert_qty}" || 0;
var totalmsgs = "${nttdto.message_qty}" || 0;
Tinycon.setBubble(parseInt(total))
		$('#envelope,#tasks').on('click', function(){
			var btn = this;

			UserServiceBean.getHeaderNotificationByIdUser("${useracegi.id_user}" || 0,function(data){
			
			if($(btn).data('action') == 'm'){
			//Control de Mensajes

				$('#messages  > li').each(function(i, e){
					$(e).remove();
				});
			
			var size = data.message_qty;

			$('<li class="pointer">' + '<div class="pointer-inner">'
								+ '<div class="arrow"></div>'
								+ '</div>'
								+ '</li>').appendTo($('#messages'));

			if (size == 0)
			 $('<li class="item-header" id="no_alert">' + 'No hay mensajes' + '</li>').appendTo($('#messages'));
			else
			 $('<li class="item-header" >' + 'Tienes '+ size + (size == 1 ? ' Mensaje Nuevo' : ' Mensajes Nuevos') + '</li>').appendTo($('#messages'));
     		   			
			var month = ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"];

    
			for(var i=0; i < data.message.length ; i++){
			var horas = data.message[i].created.getHours();
			var minutos = data.message[i].created.getMinutes();
			var dia =  data.message[i].created.getDate();
			var mes = month[data.message[i].created.getMonth()];
			
				$('<li class="item profile-message">'
					+ '<a href="inboxmsge.htm?idr=' + data.message[i].id_user_message + '">'
					+ '<img src="' + data.message[i].image + '" width="40" alt="" />'
					+ '<span class="content">'
					+ '<span class="content-headline">' + data.message[i].username
					+ '<span class="badge badge-danger"> ' + data.message[i].qty + '</span></span>'
					+ '<span class="content-text">' + data.message[i].message +'</span>'
					+ '</span> <span class="time"><i class="fa fa-clock-o"></i>'
					+ '<span class="content-text">' + (dia < 10 ? ('0' + dia) : dia) + '/' + mes +' '+ (horas < 10 ? ('0' + horas) : horas) + ':' + (minutos < 10 ? ('0' + minutos) : minutos) + '</span>' 
					+ '</span></a></li>').appendTo($('#messages'));
			}



			$('<li class="item-footer"><a href="inbox.htm"> Ver todos los mensajes </a></li>').appendTo('#messages');

			}//termino Mensajes

			if($(btn).data('action') == 't'){
				$('#tareas  > li').each(function(i, e){
					$(e).remove();
				});

				var size = data.todo_qty;

				$('<li class="pointer">' + '<div class="pointer-inner">'
								+ '<div class="arrow"></div>'
								+ '</div>'
								+ '</li>').appendTo($('#tareas'));
     	       if (size == 0)
        	     $('<li class="item-header">' + 'No hay ninguna tarea definida' + '</li>').appendTo($('#tareas'));
               else
             	 $('<li class="item-header" >' + 'Tienes '+ size + (size == 1 ? ' Tarea' : ' Tareas') + '</li>').appendTo($('#tareas'));
     
			   for(var i=0; i < data.todo.length ; i++){
 
                var prioritycss = (data.todo[i].priority == 1 ? "danger" : (data.todo[i].priority == 2 ? "warning" : "success"));
                var prioritytxt = (data.todo[i].priority == 1 ? "Alta" : (data.todo[i].priority == 2 ? "Media" : "Baja"));
				var cadena = (data.todo[i].todo.length >= 30 ? data.todo[i].todo.substring(0,30) : data.todo[i].todo); 
            
                $('<li class="item">'
                    + '<a href="#">'
                    + '<i class="fa fa-circle"></i>'
					+ '<span class="content">'+cadena+'...</span>'
                    + '<span class="time"><span class="badge badge-'+prioritycss+'btn-xs">'+prioritytxt+'</span></span>'
                    + '</a></li>').appendTo($('#tareas'));
            	}



			   $('<li class="item-footer"><a href="todolist.htm"> Ver todas las tareas </a></li>').appendTo('#tareas');

			}
			
			});
		
		});

		/*Concurrent.Thread.create(function(){
        setInterval(function(){
		var NotificationTodaySearchCriteria = new Object();
		NotificationTodaySearchCriteria.profile = '${useracegi.profile}';
		NotificationTodaySearchCriteria.fecha = new Date();
		NotificationTodaySearchCriteria.id_supplier = "${useraccess.id_supplier}" || 0;
		NotificationTodaySearchCriteria.id_user = ${useracegi.id_user};

		UserServiceBean.getQtyAlertAndMessagesByIdUser(NotificationTodaySearchCriteria, function(data){
			var mensaje = data.message_qty;
			var todo = data.todo_qty;

			(total != 0 ? $('#bell').html('<i class="fa fa-bell-o"></i><span class="count red-bg">' + total + '</span>') : '');
			if (total != data.alert_qty) {
				showPushNoty(parseInt(data.alert_qty),'Alerta', 'Tienes nuevas notificaciones', function(){
					location.href = "alertlist.htm"
				})
			}
			total = data.alert_qty;
			
			(mensaje != 0 ? $('#envelope').html('<i class="fa fa-envelope-o"></i><span class="count red-bg">' + mensaje + '</span>') : '');
			console.log(mensaje)
			if (totalmsgs != mensaje) {
				showPushNoty(-1,'Alerta', 'Tienes nuevos mensajes', function(){
					location.href = "inbox.htm"
				})
			} 
			totalmsgs = mensaje;

			(todo != 0 ? $('#tasks').html('<i class="fa fa-tasks"></i><span class="count red-bg">' + todo + '</span>') : '');
        	});
        	
		},60000);	

		
		});*/
</script>
<script type="text/javascript">

function valida(evt){
    ky = evt.keyCode;		    
	patron =/^[0-9a-zA-ZáéíóúàèìòùÀÈÌÒÙÁÉÍÓÚñÑüÜ.,_ \-\&\!\#\$\%\=\*\s\/]+$/i;     	       
	k = String.fromCharCode(ky); 	    
 return  patron.test(k);
}	

 function Borde(e){
		 if(document.getElementById("searchByName").value.length > 2){	
			    document.getElementById("drowdown-search").style.background = '#1abc9c';	
		 }else{
		 		document.getElementById("drowdown-search").style.background = 'lightgray';				 	
	 }
 } 
 
 function verificador(e){	
	if(document.getElementById("searchByName").value.length >= 3)
		location.href="storesearch.htm?searchByName=" + document.getElementById("searchByName").value;			
 }

function showPushNoty(pTotal, pTitle, pMsg, callback) {
	console.log("Ptotal", pTotal)
	if (pTotal > -1) Tinycon.setBubble(pTotal)
	if (!Push.isSupported) return
	if (Push.Permission.get() == Push.Permission.DENIED) return
	Push.create(pTitle, {
		body: pMsg,
		icon: 'img/logo-small-color.png',
		timeout: 4000,
		onClick: function(){
			window.focus()
			if (typeof callback == 'function') {
				callback()
			}
			this.close()
		}
	})
	console.log('Push created', pTitle, pMsg)
}

function getAlert(){
	if (total != undefined){
		var id_user = "${useracegi.id_user}" || 0;
		UserNotificationBean.searchNotification(id_user, 'C', function(data){
			$('#notifications > li').remove();
			$('<li class="pointer">' + '<div class="pointer-inner">'
							+ '<div class="arrow"></div>'
							+ '</div>'
							+ '</li>').appendTo($('#notifications'));
			if (total == 0)
				$('<li class="item-header" id="no_alert">' + 'No hay nuevas alertas' + '</li>').appendTo($('#notifications'));
			else
				$('<li class="item-header" >' + 'Tienes '+ total + (total == 1 ? ' Alerta Nueva' : ' Alertas Nuevas') + '</li>').appendTo($('#notifications'));

			for (var i = 0; i < data.size(); i++){
				var tiempo_minutos = data[i].created.getMinutes();
				var tiempo_horas = data[i].created.getHours();
				var profile = "${useracegi.profile};"
				var msn = data[i].message;
				$('<li class="item first-item ' + (i < total? 'new':'') + '">'
	                + '<a href="' + ( data[i].message.indexOf("grupo") > -1 || data[i].message.indexOf("grupal") > -1 ? "inboxgroup.htm" :((profile.includes("DRI") && msn.includes("actualizado") ) ? '#': data[i].link) )+ '">'
					+ '<img src="' + ( data[i].message.indexOf('grupo') > -1 || data[i].message.indexOf('grupal') > -1 ? 'img/group.png' :data[i].image ) + '" class="img-responsive" alt="Image">'
					+ '<div class="content-list">'
					+	'<p class="content">' + data[i].message + '</p>'
					+	'<span class="time"><i class="fa fa-clock-o"></i>' + (tiempo_horas < 10 ? ('0' + tiempo_horas) : tiempo_horas) + ' : '+ 	(tiempo_minutos < 10 ? ('0' + tiempo_minutos) : tiempo_minutos) + '</span>'
					+ '</div>'
					+ '</a></li>').appendTo($('#notifications'));
			}

			$('<li class="item-footer"><a href="alertlist.htm">' + ' Ver todas las notificaciones '+ '</a></li>').appendTo($('#notifications'));
			$('.felicitacion').each(function(i, e){
				var element = $(e).parent().parent().parent();
				$(element).removeAttr('href');
				var tr = $(element).parent();
				$(tr).css('cursor','pointer');
				$(tr).on('click', function(){
					swal({   
						title: "! Felicitaciones !",   
						text: $(e).html().replace('label-primary','').replace('label-info','').replace(/label/g,'strong'),   
						html: true,
						confirmButtonText: "Aceptar",
						imageUrl: 'img/logo-small-color.png'
					});
				});
			});
		});
		
		UserNotificationBean.updateNotification(id_user, function(data){
			$('#bell').html('<i class="fa fa-bell-o"></i>');
			Tinycon.setBubble(0)
		});
		
		setInterval(function(){
			$('.new').removeClass('new');
			$('#notifications .item-header').html('No hay nuevas alertas');
		}, 5000);
	}
}
</script>

<!--<script src="http://localhost:3000/socket.io/socket.io.js"></script>
<script>
(function() {
	var socket = io.connect("http://localhost:3000")
	socket.on('notification', function(msg) {
		console.log("MSG", msg)
		if (Push.isSupported) {
            if (Push.Permission.get() == Push.Permission.DEFAULT || Push.Permission.get() == Push.Permission.GRANTED) {
              Push.create("Logistikapp", {
                body: "Tienes una nueva notificación",
                icon: 'img/logo-small-color.png',
                timeout: 4000,
                tag: "lgk",
                onClick: function() {
                  window.focus()
                  this.close()
                }
              })
            }
          }
	})
})()
</script> -->
