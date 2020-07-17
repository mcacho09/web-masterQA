<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Mensajes - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	<!-- libraries -->
	<link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet" />
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

	<!-- this page specific styles -->
	
	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon"/>
	
	<!-- Dwr script -->
	<script type='text/javascript' src='dwr/interface/UserServiceBean.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	

	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>

	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
	<![endif]-->

</head>

<body onload="javascript:inicio();">
	<!-- this page specific scripts -->

	<header class="navbar" id="header-navbar">
	    <c:import url="/html/menu-top.jsp"/>
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
								<ol class="breadcrumb">
									<li><a href="#">Dashboard</a></li>
									<li class="active"><span>Comunicaci&oacute;n</span></li>
								</ol>
	
								<div class="clearfix">
									<h1 class="pull-left">Mensajes</h1>
								</div>
							</div>
						</div>
						<input type="hidden" id="id_usr_to"/>
						<input type="hidden" id="last_user" value="${id_user_to == 0 ? '' : id_user_to}"/>
						<div class="row">
							<div class="col-lg-12">
								<div class="main-box no-header clearfix">
									<div class="main-box-body clearfix">
										<div class="table-responsive">
											<div class="row" id="user-profile">
												<!-- list-users -->
												<div class="main-box clearfix">
													<div class="conversation-inner" id="usuarios">
														<c:forEach var="usr" items="${list_user}">
															<c:if test="${id_user_login != usr.id_user}">
																<c:set var="usrSel" value=""/>
																<c:if test="${id_user_to == usr.id_user}">
																	<c:set var="usrSel" value="#4C6B8A"/>
																</c:if>
																<div class="conversation-item item-left clearfix" >
																	<table width="100%">
																		<tr>
																			<td id="row_${usr.id_user}" bgcolor="${usrSel}">
																		        <img src="${URL}${usr.image}" alt="" width="50px" height="50px" />
																		        <a href="#" class="user-link" onclick="javascript:getConvertation(${usr.id_user});user_click(${usr.id_user});">
																		        	<c:out value="${usr.username}"/>
																		        </a>
																			</td>
																		</tr>
																	</table>
																</div>
															</c:if>
														</c:forEach>
													</div>
												</div>
												<!-- /list-users -->
												<!-- delete message -->													
												<div class="clearfix">
													<input type="button" class="btn btn-danger pull-right" value="Borrar mensajes" onclick="javascript:delMessage();" id="btn_eliminar"/>
												</div>												
												<br>
												<!-- /delete message -->													
												<!-- conversacion -->
												<div class="main-box clearfix">
													<div class="tabs-wrapper profile-tabs">
														<!-- COMIENZA LA PARTE DE LA CONVERSACION -->
														<div class="tab-pane fade active in" id="tab-chat">
															<div class="conversation-wrapper">
																<div class="conversation-content">
																	<div class="conversation-inner" id="chat">
																		<div id="conversacion"></div>
																	</div>
																</div>
																<div class="conversation-new-message">
																	<div class="form-group">
																		<textarea class="form-control"  id="mensaje" rows="2" placeholder="Ingresa tu mensaje..." onkeyup="javascript:contarCaracteresDelTexto();" maxlength="255" ></textarea>
																	</div>
																	<div class="clearfix">
																		<input type="button" class="btn btn-success pull-right" disabled="disabled" value="Enviar mensaje" onclick="javascript:addMessage();" id="btn_aceptar"/>
																	</div>
																</div>
															</div>
														</div>
														<!-- /COMIENZA LA PARTE DE LA CONVERSACION -->
													</div>
												</div>
												<!-- conversacion -->
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

	<!-- global scripts -->
	<!-- 
	<script src="js/demo.js"></script> <!-- only for demo -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.min.js"></script>
	<script src="js/jquery.slimscroll.min.js"></script>
	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#chat').slimScroll({
		        height: '340px'		    
		    });
		});
		$(document).ready(function() {
			$('#usuarios').slimScroll({
		        height: '100px',
		        alwaysVisible: true
		    });
		});
	</script>
	<!-- this page specific inline scripts -->
 

<script>

	function inicio(){
		if(parseInt("${id_user_to}") != 0){
			getConvertation("${id_user_to}");
		}
	}
	function agregarMessage(id_user_msj,send){
		//agrega mensaje a  usuario que envia mensaje
		var id_user_to = document.getElementById("id_usr_to").value;
		var msj = document.getElementById("mensaje").value;
		var fecha = new Date();
		var AddMessageDTO = new Object();

		AddMessageDTO.id_user_message = id_user_msj;
		AddMessageDTO.send = send;
		AddMessageDTO.message = msj;
		AddMessageDTO.created = fecha;
		AddMessageDTO.read = "${NO_READ}";
		
		UserServiceBean.addMessage(AddMessageDTO);
	}
	function addMessage(){
		var id_user_to = document.getElementById("id_usr_to").value;
		
		var RelUserUsertoDTO = new Object();
		RelUserUsertoDTO.id_user = "${id_user_login}";
		RelUserUsertoDTO.id_user_to = id_user_to;
		
		UserServiceBean.getRelUserUserto(RelUserUsertoDTO, replyUserUserto);
	}
	//si hay una relacion entre usuarios que se hallan mandado msj solo la obtiene y agrega el nuevo mensaje
	//si no existe ninguna relacion de usuarios que se esten mandando mensajes crea la relacion y agrega el msj nuevo
	var replyUserUserto = function(data){
		var id_user_to = document.getElementById("id_usr_to").value;
		if(data.length > 0){//si hay ya existe la relacion agrega el mensaje
			agregarMessage(data[0].id_user_message,"${SEND}");
			//obtiene el id_usr_message del cual utilizamos la relacion de usuarios que recibe el mensaje
			var RelUserUsertoDTO = new Object();
			RelUserUsertoDTO.id_user = id_user_to;
			RelUserUsertoDTO.id_user_to = "${id_user_login}";
			
			UserServiceBean.getRelUserUserto(RelUserUsertoDTO, relUsrReciveMsg);
	
		}else{//como no hay ninguna relacion de usuarios que tenga una conversacion, agrega la relacion y en replyUsrMessage agrega el nuevo mensaje
			var id_user_to = document.getElementById("id_usr_to").value;
			var AddUserMessageDTO = new Object();
			var fecha = new Date();
			AddUserMessageDTO.id_user = "${id_user_login}";
			AddUserMessageDTO.id_user_to = id_user_to;
			AddUserMessageDTO.created = fecha;

			//Agrega a tabla de relacion usuario message
			UserServiceBean.addUserMessage(AddUserMessageDTO, replyUsrMessage);
		}
	};
	var relUsrReciveMsg = function(data){//obtiene la relacion de usuarios que recive el mensaje
		var id_user_to = document.getElementById("id_usr_to").value;
		if(data.length > 0){
			var id_user_to = document.getElementById("id_usr_to").value;
			agregarMessage(data[0].id_user_message,"${NO_SEND}");
			document.location = "message.htm?id_user_to="+id_user_to;
		}else{
			var AddUserMessageDTO = new Object();
			var fecha = new Date();
			AddUserMessageDTO.id_user = id_user_to;
			AddUserMessageDTO.id_user_to = "${id_user_login}";
			AddUserMessageDTO.created = fecha;

			//Agrega a tabla de relacion usuario message
			UserServiceBean.addUserMessage(AddUserMessageDTO, replyUsrMessageRecive);
		}
	}
	var replyUsrMessageRecive = function(data){
		var id_user_to = document.getElementById("id_usr_to").value;
		agregarMessage(data,"${NO_SEND}");
		document.location = "message.htm?id_user_to="+id_user_to;
	};
	var replyUsrMessage = function(data){
		var id_user_to = document.getElementById("id_usr_to").value;
		agregarMessage(data,"${SEND}");
		//obtiene el id_usr_message del cual utilizamos la relacion de usuarios que recibe el mensaje
		var RelUserUsertoDTO = new Object();
		RelUserUsertoDTO.id_user = id_user_to;
		RelUserUsertoDTO.id_user_to = "${id_user_login}";
		
		UserServiceBean.getRelUserUserto(RelUserUsertoDTO, relUsrReciveMsg);
	};
	function user_click(id_user){
		var last_usr = document.getElementById("last_user").value;
		//verifica si hay algun usuario seleccionado anteriormente y si hay, quita la seleccion
		if(last_usr != ""){
			document.getElementById("row_"+last_usr).setAttribute("bgcolor", "");
		}
		document.getElementById("row_"+id_user).setAttribute("bgcolor", "#4C6B8A");
		document.getElementById("last_user").value=id_user;
	}
	function getConvertation(id_user_to){//obtiene convesacion entre dos usuarios
		document.getElementById("conversacion").innerHTML = "";
		document.getElementById("id_usr_to").value = id_user_to;
		
		var RelUserUsertoDTO = new Object();
		RelUserUsertoDTO.id_user = "${id_user_login}";
		RelUserUsertoDTO.id_user_to = id_user_to;
		
		UserServiceBean.getRelUserUserto(RelUserUsertoDTO, replyChat);
		
	}
	var replyChat = function(data){//obtiene la conversacion de por medio de id_user_message
		if(data.length > 0){
			var MessageByIdUserMsgDTO = new Object();
			MessageByIdUserMsgDTO.id_user_message = data[0].id_user_message;
			UserServiceBean.getMessageByIdUserMsg(MessageByIdUserMsgDTO, replyMessage);
		}
	}
	var replyMessage = function(data){
		var datos = "";
		for(var x=0; x<data.length; x++){//los mensajes que va leyendo si alguno tiene read=N lo cambia por read=S 
				if(data[x].read == "${NO_READ}"){
					var UpdMessage = new Object();
					UpdMessage.id_message = data[x].id_message;
					UpdMessage.read = "${READ}";
					UserServiceBean.updMessage(UpdMessage);
				}
		}
		
		for(var i=0; i<data.length; i++){//arma la conversacion de dos usuarios
			if(data[i].send != "${SEND}"){
				datos = datos + "<div class='conversation-item item-left clearfix'>";
			}else{
				datos = datos + "<div class='conversation-item item-right clearfix'>";
			}
			var preFecha = data[i].created;
			var fecha = new Date(preFecha);
			var day = fecha.getDate();
			var month = fecha.getMonth()+1;
			var year = fecha.getFullYear();
			var hr = fecha.getHours();
			var min = fecha.getMinutes();
			var fullDate = day+"/"+month+"/"+year+" "+hr+":"+min;

			datos = datos + "<div class='conversation-user'><img src='img/samples/ryan.png' alt=''></div>";//imagen
			datos = datos + "<div class='conversation-body'>";
			if(data[i].send == "${SEND}")
				datos = datos + "<div class='name'>"+data[i].user_name+"</div>";//nombre usuario
			if(data[i].send == "${NO_SEND}")
				datos = datos + "<div class='name'>"+data[i].user_name_to+"</div>";//nombre usuario
			datos = datos + "<div class='time hidden-xs'> "+fullDate+"</div>";//fecha
			datos = datos + "<div class='text'>"+data[i].message+"</div>";//mensaje
			datos = datos + "</div>";
			datos = datos + "</div>";//fin
		} //for
		//inserta la conversacion entre usuario logeado y usuario seleccionado
		document.getElementById("conversacion").innerHTML = datos;
		//posiciona el escroll al final
		var scrollTo_val = $('#chat').prop('scrollHeight') + 'px'; 
		$('#chat').slimScroll({ scrollTo : scrollTo_val });
	};
	//comprueba que se escriba algo en el textarea de mensaje para activar el boton de enviar, si no hay ningun
	//caracter se desactiva el boton
	function contarCaracteresDelTexto () {
	    var txt = document.getElementById('mensaje');
	    var div = document.createElement('div');
	    div.innerHTML = txt.value;
	    if(div.textContent) {
	    	document.getElementById("btn_aceptar").disabled = false;
	    } else {
	    	document.getElementById("btn_aceptar").disabled = true;
	    }
	}
	function delMessage(){
		var id_user_to = document.getElementById("id_usr_to").value;

		//obtener el id_user_message dek usuario logeado y usuario que esta seleccionado
		var RelUserUsertoDTO = new Object();
		RelUserUsertoDTO.id_user = "${id_user_login}";
		RelUserUsertoDTO.id_user_to = id_user_to;
		
		UserServiceBean.getRelUserUserto(RelUserUsertoDTO, delChat);
	}
	var delChat = function(data){
		var id_user_to = document.getElementById("id_usr_to").value;
		if(data.length > 0){
			UserServiceBean.deleteMessageByIdUserMessage(data[0].id_user_message);
			UserServiceBean.delUserMessage(data[0].id_user_message);
		}
		getConvertation(id_user_to);
	}
</script>
<script>
	var time = new Date().getTime();
	$(document.body).bind("mousemove keypress", function(e) {
		time = new Date().getTime();
	});

	function refresh() {
		var id_user_to = document.getElementById("id_usr_to").value;
		if(new Date().getTime() - time >= "${TIME_RELOAD}") {
			getConvertation(id_user_to);
			setTimeout(refresh, "${TIME_RELOAD}");
		} 
		else 
			setTimeout(refresh, "${TIME_RELOAD}");
	}

	setTimeout(refresh, "${TIME_RELOAD}");
</script></body>
 
</html>