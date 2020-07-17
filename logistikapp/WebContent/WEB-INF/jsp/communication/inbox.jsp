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
	                                <ol class="breadcrumb hidden-xs">
	                                    <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
	                                    <li class="active"><span><spring:message code="label.breadcrumb.communication"/></span></li>
	                                </ol>
	
	                                <div class="clearfix">
	                                    <h1 class="pull-left"><a href="inbox.htm"><spring:message code="label.communication.inbox.list.title1"/></a></h1>	                                    
	                                </div>
	                            </div>
                        	</div>
							<div id="email-box" class="clearfix" style="padding-left:0; margin-left:0">
								<div class="row">
									<div class="col-lg-12">
										<header id="email-header" class="clearfix">
											<div id="email-header-tools" style="margin-left:0; padding-left:2%">
												
												<div class="btn-group">
													<button onclick="location.href='inboxnew.htm'" class="btn btn-primary" title="Nuevo" data-toggle="tooltip" data-placement="bottom">
														<i class="fa fa-plus"></i> Nuevo
													</button>
												</div>
												
												<div class="btn-group">
													<button onclick="location.href='inbox.htm'" class="btn btn-primary" type="button" title="Actualizar" data-toggle="tooltip" data-placement="bottom">
														<i class="fa fa-refresh"></i>
													</button>
													<button class="hidden btn btn-primary" type="button" title="Spam" data-toggle="tooltip" data-placement="bottom">
														<i class="fa fa-exclamation-circle"></i> 
													</button>
													<button class="btn btn-primary" type="button" title="Eliminar" data-toggle="tooltip" data-placement="bottom" id="erase">
														<i class="fa fa-trash-o"></i>
													</button>
												</div>
											</div>
											
											<div id="email-header-pagination" class="pull-right">
												<div class="btn-group pagination pull-right">
													<button class="btn btn-primary" type="button" title="Previous" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
														<i class="fa fa-chevron-left"></i>
													</button>
													<button class="btn btn-primary" type="button" title="Next" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
														<i class="fa fa-chevron-right"></i>
													</button>
												</div>
												
												<c:if test="${fn:length(list_users) > 0}">
													<div class="num-items pull-right hidden-xs">
														1-${fn:length(list_users)} de ${fn:length(list_users)}
													</div>
												</c:if>
											</div>
											
										</header>
									</div>
								</div>
								
								<div class="row">
									<div class="col-lg-12">
										<div id="email-content" class="email-content-nano" style="margin-left:0; padding-left:2%">
											<div class="email-content-nano-content" style="margin-left:0; padding-left:2%; width:100%">
												<ul id="email-list">
												
                                                    <c:if test="${fn:length(list_users) == 0}">
	                                                    <li class="read clickable-row" >
	                                                       No hay mensajes
	                                                    </li>
                                                    </c:if>
												
													<c:forEach var="us" items="${list_users}" varStatus="count">
															<li class="${us.read == 'N' ? 'unread ' : ''} clickable-row" data-href="inboxmsge.htm?idr=${us.id_user_message}">
																<div class="chbox">
																	<div class="checkbox-nice">
																		<input type="checkbox" id="m-checkbox-${count.index}" name="inboxchk" value="${us.id_user_message}"/>
																		<label for="m-checkbox-${count.index}"></label>
																	</div>
																</div>
																<!-- <div class="star">
																	<a></a>
																</div>  -->
																<div class="name">
																	${us.username}
																</div>
																<div class="message">																
																	<span class="body">${us.message}</span>
																</div>
																
																	<div class="meta-info">
																		<c:if test="${us.attachments > 0}">
																			<a href="#" class="attachment">
																				<i class="fa fa-paperclip"></i>
																			</a>
																		</c:if>
																		<span class="date"><fmt:formatDate value="${us.since}" type="both" pattern="dd MMM"/></span>
																	</div>
																
															</li>        
                                                                                                       
                                                    </c:forEach>
												
												
													
												</ul>
											
												<form name="formBorrar" id="formBorrar" action="inboxdel.htm" method="post">
													<input type="hidden" name="idsBorrar" id="idsBorrar">
												</form>
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
		//nobackbutton();
		$(".clickable-row > div:not(.chbox,.star)").click(function(e) {
			if ((e.target instanceof HTMLAnchorElement) == true) {
				return;
			}
			
			var href = $(this).parent().data('href');
			
			if (href != '' && typeof href != 'undefined') {
				window.document.location = href;
			}
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
	
			$('#email-content').css('height', windowHeight - staticContentH);
		}
		else {
			$('#email-content').css('height', '');
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
			
			$('#email-content').nanoScroller({
		    	alwaysVisible: false,
		    	iOSNativeScrolling: false,
		    	preventPageScrolling: true,
		    	contentClass: 'email-content-nano-content'
		    });
		}
	}
	$(function(){
        $('#erase').click(function(){
            var selectedIds="";
            var separador = ",";
            $("input:checkbox[name=inboxchk]:checked").each(function(){
                var val = $(this).val();
                selectedIds = selectedIds  + val + separador;
            });   
        		swal({   
    				title: "Alerta",
    				text: "¿Está seguro que desea borrar la conversación?",   
    				type: "warning",   
    				showCancelButton: true,   
    				confirmButtonColor: "#DD6B55",   
    				confirmButtonText: "Aceptar",
    				cancelButtonText: 'Cancelar',
    				closeOnConfirm: false 
    			}, function(){   
    				document.getElementById("idsBorrar").value=selectedIds;
    				document.getElementById("formBorrar").submit();			
    			});                           
        });
 	});     
	
    /*function nobackbutton() {
        window.location.hash="no-back-button";
        window.location.hash="Again-No-back-button" //chrome
        window.onhashchange=function(){window.location.hash="no-back-button";}
        }*/
	
	</script>

</body>
 
</html>
