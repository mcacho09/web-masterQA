<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Inbox - LogistikApp</title>
	
	<!-- bootstrap -->
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet" />

	<!-- libraries -->
	<link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css"/>
    <link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css"/>
	
	<!-- global styles -->
	<link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css">

	<!-- this page specific styles -->
	<link rel="stylesheet" type="text/css" href="css/logistikapp.css"/>

	<!-- Favicon -->
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon"/>
	
	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>

	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
		<script src="js/respond.min.js"></script>
	<![endif]-->
		<link rel="stylesheet" href="css/libs/select2.css" type="text/css" />
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
	                                <ol class="breadcrumb hidden-xs">
	                                    <li><a href="home.htm">Home</a></li>
	                                    <li><span>Comunicaci&oacute;n</span></li>
	                                    <li class="active"><span><a href="inbox.htm">Mensajes</a></span></li>
	                                </ol>
	
	                                <div class="clearfix">
	                                    <h1 class="pull-left">Nuevo Mensaje</h1>	                                    
	                                </div>
	                            </div>
                        	</div>
							<div id="email-box" class="clearfix" style="padding-left:0; margin-left:0">
								<div class="row">
									<div class="col-lg-12">
										
										<header id="email-header" class="clearfix">
																						
											<div id="email-header-tools" style="margin-left:0; padding-left:2%">
												<div class="btn-group">
													<a href="inbox.htm" class="btn btn-primary">
														<i class="fa fa-arrow-left"></i> Volver
													</a>
												</div>												
												<div class="btn-group">
													<button class="btn btn-primary hidden" type="button" title="Refresh" data-toggle="tooltip" data-placement="bottom">
														<i class="fa fa-refresh"></i>
													</button>
													<button class="btn btn-primary hidden" type="button" title="Spam" data-toggle="tooltip" data-placement="bottom">
														<i class="fa fa-exclamation-circle"></i>
													</button>
													<button class="btn btn-primary hidden" type="button" title="Erase" data-toggle="tooltip" data-placement="bottom">
														<i class="fa fa-trash-o"></i>
													</button>
												</div>
											</div>
											
										</header>		
										
									</div>
								</div>
								
								<div class="row">
									<div class="col-lg-12">
										<div id="email-new" class="email-new-nano" style="margin-left:0; padding-left:2%">
											<div class="email-new-nano-content" style="margin-left:0; padding-left:2%; width:100%">
												<div id="email-new-inner" >
													<form id="form" name="form" method="post" action="inboxmsgdosend.htm" enctype="multipart/form-data">
														<div id="email-new-header">
															<div class="row form-group">
															
																<div class="col-md-12" id="group" style="display:none">
																	<label id="nameGrouptxt" class="col-md-2">Nombre del grupo: </label>
																	<div class="col-md-10">
																		<input type="text" class="form-control col-md-10" id="nameGroup" name="nameGroup" placeholder="Nombre del grupo" value="" maxlength="50"/> 		
																	</div>
																</div>

																<label for="exampleInpTo" class="col-md-2">Para: </label>														
																<div class="col-md-10">
																	<select multiple style="width:97%; margin-right:3%" id="id_user_to" name="id_user_to">
                                                                		<c:forEach var="ua" items="${list_users_available}">
                                                                    		<option value="${ua.id_user}">${ua.username}</option>
                                                                		</c:forEach>
                                                            		</select>
																</div>
															</div>													
														</div>
														
														<div id="email-new-body">
															 
                                            					<input type="hidden" id="id_user" name="id_user" value="${useracegi.id_user}"/>
	                                        
    					                                        <div class="conversation-wrapper">
                        					                        <div class="conversation-new-message">
                                            		            
                                                    				   
                                                        
                                                        				<div class="form-group">
                                                            				<label>Mensaje</label>
                                                            				<textarea maxlength="255" style="margin-top: 1%; margin-right:1%; padding-right:1%; resize:none" class="form-control" id="message" name="message" rows="3" placeholder="Ingresa el mensaje"></textarea>
                                                            				<br>
                                                            				<span style="color:gray;" class="pull-right" id="text_counter"></span>
                                                        				</div>
                                                        	
                                                        				
                                                        
                                                 					</div>
                                             					</div>
														</div>
														<div id="email-new-footer">
															<div class="row">
																<div class="col-xs-12 col-md-5 col-sm-12 col-lg-4" style="margin-left:0; padding-left:0;">
																	<div class="pull-left btn btn-primary" style="padding-left:2%; margin-left:2%; float:left"  id="subir">
																		<input type="file" id="inputFile" name="attachFile" multiple style="display:none; position:absolute; left:1000000px;"/>
																		<i class="fa fa-arrow-circle-o-up" id="btn-file"></i><label><b>&nbsp;Adjuntar Archivos</b></label>
																	</div>
																
																</div>
																<div class="col-xs-12 col-md-7 col-sm-12 col-lg-8" style="margin-left:0; padding-left:0; margin-top:5%">
																	<div class="pull-right" style="padding-right:2%;">
																		<div class="btn-group">
																			<button type="button" onclick="location.href='inbox.htm';" class="btn btn-default pull-left"><i class="fa fa-times"></i> Cancelar</button>
																		</div>
																		<div class="btn-group">
																			<button onclick="javascript:enviarMensaje();" class="btn btn-primary pull-right" id="btnEnviar"><i class="fa fa-send"></i> Enviar</button>
																		</div>
																	</div>
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
					<footer id="footer-bar" class="row">
                    	<c:import url="/html/footer.html" />
					</footer>
					
				</div>
          
        </div>
    </div>
  
	<!-- global scripts -->
	<!-- global scripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.nanoscroller.min.js"></script>
	<script src="js/select2.min.js"></script>
    <!-- this page specific scripts -->
    <script src="js/jquery.slimscroll.min.js"></script>
	
	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
		
	<!-- this page specific inline scripts -->
	<script>
	$('#btn-file,#btn-file+label').on('click',function(){ $('input[type="file"]').click(); });
	
	$('input[type="file"]').on('change', function(){
		var files = '';
		for (var i = 0; i < $('input[type="file"]')[0].files.length; i++) {
				files += $('input[type="file"]')[0].files[i].name + '<br>';
			}
		
			$('#btn-file+label').html('<b>' +  ($('input[type="file"]')[0].files.length > 1 ? (('&nbsp;'+$('input[type="file"]')[0].files.length) + ' Archivos Seleccionados') : ('&nbsp;'+files)) +'</b>');

	});
	$(document).ready(function() {
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
		$('#id_user_to').select2({
			placeholder: 'Selecciona un usuario'			
		});
		
		var left = 255;
        $('#text_counter').text(left + '/255');
 
        $('#message').keyup(function () {
            left = 255 - $(this).val().length;
            $('#text_counter').text(left + '/255');
        });
		
	});
	
	$(window).smartresize(function(){
		setHeightEmailContent();
		
		initEmailScroller();
	});
	
	function setHeightEmailContent() {
		if ($( document ).width() >= 992) {
			var windowHeight = $(window).height();
			var staticContentH = $('#header-navbar').outerHeight() + $('#email-header').outerHeight();
			staticContentH += ($('#email-box').outerHeight() - $('#email-box').height());
	
			$('#email-new').css('height', windowHeight - staticContentH);
		}
		else {
			$('#email-new').css('height', '');
		}
	}
	
	function initEmailScroller() {
		if ($( document ).width() >= 992) {
			$('#email-navigation').nanoScroller({
		    	alwaysVisible: false,
		    	iOSNativeScrolling: false,
		    	preventPageScrolling: true,
		    	contentClass: 'email-nav-nano-content'
		    });
			
			$('#email-new').nanoScroller({
		    	alwaysVisible: false,
		    	iOSNativeScrolling: false,
		    	preventPageScrolling: true,
		    	contentClass: 'email-new-nano-content'
		    });
		}
	}
	
	$(function(){
		function initToolbarBootstrapBindings() {
			var fonts = ['Serif', 'Sans', 'Arial', 'Arial Black', 'Courier', 
						'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
						'Times New Roman', 'Verdana'],
				fontTarget = $('[title=Font]').siblings('.dropdown-menu');
			
			$.each(fonts, function (idx, fontName) {
				fontTarget.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'+fontName + '</a></li>'));
			});
			$('a[title]').tooltip({container:'body'});
			$('.dropdown-menu input').click(function() {return false;})
				.change(function () {$(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');})
				.keydown('esc', function () {this.value='';$(this).change();});

			$('[data-role=magic-overlay]').each(function () { 
				var overlay = $(this), target = $(overlay.data('target')); 
				overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
			});
			if ("onwebkitspeechchange"	in document.createElement("input")) {
				var editorOffset = $('#editor').offset();
				$('#voiceBtn').css('position','absolute').offset({top: editorOffset.top, left: editorOffset.left+$('#editor').innerWidth()-35});
			} else {
				$('#voiceBtn').hide();
			}
		};
		function showErrorAlert (reason, detail) {
			var msg='';
			if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
			else {
				console.log("error uploading file", reason, detail);
			}
			$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+ 
			 '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
		};
		
		initToolbarBootstrapBindings();	
				
		
	});
	function enviarMensaje() {
		$('#btnEnviar').attr("disabled", "disabled");
		 var selected=[];
		   var peronas=0;
		   var destinatarios="";
		   $('#id_user_to :selected').each(function(){
		       selected[$(this).val()]=$(this).text();

		      peronas=peronas+1;
		      });
		   
		   if(peronas>1)//accion grupal
			   {
				$('#form').attr('action', 'inboxmsggroupdosend.htm');
				document.getElementById("form").submit();
			   }
		   else {
			   if(peronas == 1)
			   {
					$('#form').attr('action', 'inboxmsgdosend.htm');
					document.getElementById("form").submit();
				}else{
					swal({   
						title: "Campo Faltante",   
						text: "No has seleccionado el destinatario",   
						confirmButtonText: "Aceptar",
						imageUrl: 'img/logo-small-color.png',
						allowEscapeKey: false
					},function(isConfirm){   
						if (isConfirm) {     
							$('#btnEnviar').attr("disabled", false);   
							} 
					});

				}
		   }//accion normal
			   
	}
	 $('#id_user_to').change(function(){
		   var selected=[];
		   var peronas=0;
		   $('#id_user_to :selected').each(function(){
		       selected[$(this).val()]=$(this).text();
		      // alert($(this).text());
		      peronas=peronas+1;
		      });
		   if(peronas>1)//accion grupal
			   {
			  	 	document.getElementById("subir").style.display="none";
    	 			document.getElementById("group").style.display="inline"; 
			   }
		   else
			   {
		  	 		document.getElementById("subir").style.display="inline";
	 				document.getElementById("group").style.display="none"; 
			   }
		   
	   });
	</script>
	
</body>
 
</html>
