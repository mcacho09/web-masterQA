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
                    <div class="col-xs-12">

                        <div class="row">
                            <div class="col-xs-12">
                                <ol class="breadcrumb">
                                    <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                    <li><span><spring:message code="label.breadcrumb.communication"/></span></li>
                                    <li class="active"><a href="inboxlist.htm"><span>Mensajes</span></a></li>
                                </ol>
                            </div>
                        </div>

                        <div class="row">
                        
                            <div class="col-xs-12">
                                <div class="main-box clearfix">
                                
                                       <header class="main-box-header clearfix">
                                           <h2 class="pull-left">Conversaci&oacute;n</h2>

                                            <div class="filter-block pull-right">
                                                <a href="inboxlist.htm" class="badge badge-info"><i class="fa fa-envelope"></i> Mensajes</a>
                                            </div>
                                        </header>
                                    
                                    <div class="main-box-body clearfix">
                                    
                                        <!-- conversation -->
                                        <c:if test="${fn:length(list_message) > 0}">
                                            <div class="conversation-wrapper">
                                                <div class="conversation-content">
                                                    <div class="conversation-inner">
                                                
                                                        <!-- message block -->
                                                        <c:forEach var="msg" items="${list_message}">
                                                            <div class="conversation-item item-${msg.send == 'S' ? 'right' : 'left'} clearfix">
                                                                <div class="conversation-user"><img src="${msg.send == 'S' ? msg.user_from_image : msg.user_to_image}" width="50" height="50"/></div>
                                                                <div class="conversation-body">
                                                                    
                                                                    <c:if test="${msg.read == 'N'}">
                                                                        <div class="name"><c:out value="${msg.send == 'S' ? msg.user_from_name : msg.user_to_name}"/> <i class="fa fa-asterisk"></i></div>
                                                                    </c:if>
                                                                    <c:if test="${msg.read == 'S'}">
                                                                        <div class="name"><c:out value="${msg.send == 'S' ? msg.user_from_name : msg.user_to_name}"/></div>
                                                                    </c:if>
                                                                    
                                                                    <div class="time">&nbsp;<fmt:formatDate value="${msg.created}" type="both" pattern="dd/MM HH:mm"/></div>
                                                                    <div class="text"><c:out value="${msg.message}"/></div>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                        <!-- /message block -->
                                                    
                                                    </div>
                                                </div>
                                                    
                                                <div class="conversation-new-message">
                                                    <form name="form1" method="post" action="inboxdosend.htm">
                                                        <input type="hidden" id="id_user" name="id_user" value="${user_message.id_user}"/>
                                                        <input type="hidden" id="id_user_to" name="id_user_to" value="${user_message.id_user_to}"/>
                                                        <input type="hidden" id="ismobile" name="ismobile" value="true"/>
                                                        <input type="hidden" id="id_user_message" name="id_user_message" value="${user_message.id_user_message}"/>
                                                        
                                                        <div class="form-group">
                                                            <textarea class="form-control" rows="2" id="message" name="message" placeholder="Escribe el mensaje aqu&iacute;..." maxlength="255"></textarea>
                                                        </div>
                                                        <div class="form-group">
                                                            <button type="submit" class="btn btn-success btn-xs pull-right"><i class="fa fa-send"></i></button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </c:if>
                                        <!-- /conversation -->
                                        
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
    <script type="text/javascript">
    $(document).ready(function() {
        $('.conversation-inner').slimScroll({
            height: '250px',
            start: 'bottom'
            });
        });
    $(document).ready(function() {
        $("#message").focus();
        $("#message").select();
        });
	</script>

</body>
 
</html>
