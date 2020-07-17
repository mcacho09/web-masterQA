<%@ include file	="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Inbox - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />

	<!-- libraries -->
	<link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" href="css/libs/nanoscroller.css" type="text/css" />

	<!-- global styles -->
	<link rel="stylescomheet" type="text/css" href="css/compiled/theme_styles.css">
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

	<!-- this page specific styles -->
	<link rel="stylesheet" type="text/css" href="css/logistikapp.css">
	
	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon"/>
	
	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>

	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
	<![endif]-->
</head>

<body class="fixed-header fixed-leftmenu theme-blue">
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
						<div class="col-lg-12" style="padding-right:2%;">
							<div class="row">
	                            <div class="col-lg-12">
	                                <ol class="breadcrumb">
	                                    <li><a href="home.htm">Home</a></li>
	                                    <li><span>Comunicaci&oacute;n</span></li>
	                                    <li class="active"><span><a href="inbox.htm"><spring:message code="label.breadcrumb.communication.inbox"/></a></span></li>
	                                </ol>
	
	                                <div class="clearfix">
	                                    <h1 class="pull-left">${grpName}<h1>
										<span class="subject" style="font-size: .7em;">${txtIntegrantes}</span>	                                    
	                                </div>
	                            </div>
                        	</div>
							<div id="email-box" class="clearfix" style="padding-left:0; margin-left:0">
								<div class="row">
									<div class="col-lg-12">
											
										<header id="email-header" class="clearfix">
																						
											<div id="email-header-tools" style="margin-left:0; padding-left:2%">
												<div class="btn-group">
													<a href="inboxgroup.htm" class="btn btn-primary">
														<i class="fa fa-arrow-left"></i> Volver
													</a>
												</div>
												
												<div class="btn-group">
												
													<button onclick="location.href='inboxmsggroup.htm?id_gp=${id_group}'" class="btn btn-primary" type="button" title="Actualizar" data-toggle="tooltip" data-placement="bottom">
														<i class="fa fa-refresh"></i> Actualizar
													</button>
													<!--button class="btn btn-primary hidden" type="button" title="Spam" data-toggle="tooltip" data-placement="bottom">
														<i class="fa fa-exclamation-circle"></i>
													</button>
													<button class="btn btn-primary hidden" type="button" title="Erase" data-toggle="tooltip" data-placement="bottom" id="erase">
														<i class="fa fa-trash-o"></i>
													</button-->
												</div>
											</div>
										</header>
									</div>
								</div>
								
								<div class="row">
									<div class="col-lg-12">
										<div id="email-detail" class="email-detail-nano" style="margin-left:0; padding-left:2%">
											<div class="email-detail-nano-content">
												<div id="email-detail-inner">
													
													
													<div id="email-body">
														<div class="conversation-wrapper">
	                                            			 <div class="conversation-content">
	                                                		 	<div class="conversation-inner">
	                                                
																<!-- message block -->
                                                        		<c:forEach var="msg" items="${list_message}">
                                                            		<div class="conversation-item item-${msg.id_user != id_user ? 'left' : 'right'} clearfix">
                                                                		<div class="conversation-user"><img src="${msg.image}" width="50" height="50"/></div>
                                                                			<div class="conversation-body">
                                                                					
																					<div class="time hidden-xs hidden-sm">&nbsp;<fmt:formatDate value="${msg.created}" type="both" pattern="dd/MM/yyyy HH:mm:ss"/> 
	                                                                    				<!-- a href="javascript:borrarMensaje(${msg.id_message}, ${msg.id_user})"><i class="fa fa-trash-o"></i></a-->
	                                                                    			</div>
	                                                                    			<div class="time hidden-lg hidden-md">&nbsp;<fmt:formatDate value="${msg.created}" type="both" pattern="dd/MM/yyyy"/> 
	                                                                    				<!--  a href="javascript:borrarMensaje(${msg.id_message}, ${msg.id_user})"><i class="fa fa-trash-o"></i></a-->
	                                                                    			</div>
	                                                                    			<br class="hidden-lg hidden-md hidden-sm">
                                                                					<div class="name">${msg.username}</div>
	                                                                    			<div class="text"><c:out value="${msg.message}"/></div>
                                                                			</div>
                                                                	</div>
	                                                    		</c:forEach>
	                                                    		<!-- /message block -->
	                                                
	                                                </div>
	                                            </div>
														</div>
													</div>
													
													
													<div id="email-new-footer clearfix" style="padding-button:20; margin-button:20">
															<div class="conversation-new-message" style="margin-right:5%">
			                                                	<form id="form1" name="form1" method="post" action="inboxmsggroupdosend.htm" enctype="multipart/form-data">
			                                                    	<input type="hidden" id="id_user" name="id_user" value="${id_user}"/>
			                                                    	<input type="hidden" id="id_group" name="id_group" value="${id_group}"/>
			                                                    	<div class="row">
			                                                    		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			                                                   				<div class="form-group" style="padding-left:2%; margin-bottom:2%; padding-bottom:2%">
			                                                        			<textarea maxlength="255" class="form-control" rows="2" style="resize:none" id="message" name="message" placeholder="Escribe el mensaje aqu&iacute;..."value=""></textarea>
			                                                        			<br>
                                                            					<span style="color:gray;" class="pull-right" id="text_counter"></span>
			                                                    			</div>
			                                                    		</div>
			                                                    		<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
			                                                    			
																		</div>
																		<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
			                                                    			<div class="form-group">
			                                                        			<button onclick="javascript:enviarMensaje()" class="btn btn-primary pull-right" id="btnEnviar"><i class="fa fa-send" disabled="true"></i> Enviar</button>
			                                                    			</div>
			                                                    		</div>	
			                                                    	</div>
			                                                	</form>
	                                            			</div>		
														
													</div>
													
												</div>
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
					<form name="formBorrar" id="formBorrar" action="inboxdel.htm" method="post">
						<input type="hidden" name="idsBorrar" id="idsBorrar" value="${user_message.id_user_message}">
					</form>
					<form name="borrarMsg" id="borrarMsg" action="msgdel.htm" method="post">
						<input type="hidden" name="id_msg_del" id="id_msg_del" value="">
						<input type="hidden" name="id_user_message_del" id="id_user_message_del" value="${user_message.id_user_message}">
					</form>
					
				</div>
        </div>
        
    </div>

	<!-- global scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.min.js"></script>
	
    <!-- this page specific scripts -->
    <script src="js/jquery.slimscroll.min.js"></script>
	
	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	
    <!-- this page specific inline scripts -->
    <script>

    $(document).ready(function() {
    	
    	$("#btnEnviar").prop("disabled",true);
    	
		$('#email-list li > .star > a').on('click', function() {
			$(this).toggleClass('starred');
		});
		
		$(".has-tooltip").each(function (index, el) {
			$(el).tooltip({
				placement: $(this).data("placement") || 'bottom'
			});
		});
		
		setHeightEmailContent();		
		initEmailScroller();
		$('.conversation-inner').slimScroll({
	     	   height: '250px',
	        start: 'bottom'
	        });
	     
	     // Por defecto se selecciona y se hace focus del elemento
	     	$("#message").focus();
	     	$("#message").select();

	     	var left = 255;
	        $('#text_counter').text(left + '/255');
	 
	        $('#message').keyup(function () {
	            left = 255 - $(this).val().length;
	            $('#text_counter').text(left + '/255');
	            
	        	var text = $('#message').val();
	        	if(text!="")
	        		{
	        			//alert("deshabilitado");
	        			$("#btnEnviar").prop("disabled",false);
	        		}
	        	else
	        		{
	    				//alert("habilitado");
	    				$("#btnEnviar").prop("disabled",true);
	        		}
	        });
	});
	/*
	$(window).smartresize(function(){
		setHeightEmailContent();
		
		initEmailScroller();
	});
	*/
	function setHeightEmailContent() {
		if ($( document ).width() >= 992) {
			var windowHeight = $(window).height();
			var staticContentH = $('#header-navbar').outerHeight() + $('#email-header').outerHeight();
			staticContentH += ($('#email-box').outerHeight() - $('#email-box').height());
	
			$('#email-detail').css('height', windowHeight - staticContentH);
		}
		else {
			$('#email-detail').css('height', '');
		}
		
		
	}
	
	function initEmailScroller() {
		if ($( document ).width() >= 992) {
			$('#email-navigation').nanoScroller({
		    	alwaysVisible: false,
		    	iOSNativeScrolling: false,
		    	preventPageScrolling: false,
		    	contentClass: 'email-nav-nano-content'
		    });
			/*
			$('#email-detail').nanoScroller({
		    	alwaysVisible: false,
		    	iOSNativeScrolling: false,
		    	preventPageScrolling: false,
		    	contentClass: 'email-detail-nano-content'
		    });	*/
		}
	}
	
	$(function(){
        $('#erase').click(function(){            
        	if ( confirm("¿Está seguro que desea borrar la conversación?") ){
				document.getElementById("formBorrar").submit();				
            }            
        });
 	});     
	function borrarMensaje(id_message) 
	{
		var num = ${fn:length(list_message)};
		
		document.getElementById("id_msg_del").value=id_message;
		alert();
		if (num == 1)
			document.getElementById("formBorrar").submit();	
		else 
			document.getElementById("borrarMsg").submit();
		
	}
	function enviarMensaje() {
		$('#btnEnviar').attr("disabled", "disabled");
		document.getElementById("form1").submit();
	}	
	</script>
	
</body>
 
</html>
