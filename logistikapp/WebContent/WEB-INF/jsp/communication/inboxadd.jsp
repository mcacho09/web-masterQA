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
                    <div class="col-lg-12">

                        <div class="row">
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>

                                    <li class="hidden-xs active"><span><spring:message code="label.breadcrumb.communication"/></span></li>
                                    <li class="hidden-lg hidden-md hidden-sm"><span><spring:message code="label.breadcrumb.communication"/></span></li>
                                    
                                    <li class="hidden-lg hidden-md hidden-sm active"><a href="inboxlist.htm"><span>Mensajes</span></a></li>
                                </ol>

                                <div class="clearfix hidden-xs">
                                    <h1 class="pull-left"><a href="inboxlist.htm"><spring:message code="label.communication.inbox.list.title1"/></a></h1>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                        
                            <div class="col-lg-4 col-md-6 col-sm-6 hidden-xs">
                                <div class="main-box clearfix">

	                                <header class="main-box-header clearfix hidden-lg hidden-md hidden-sm">
	                                    <h2 class="pull-left"><span>Mensajes</span></h2>
	                                </header>
                                
                                    <header class="main-box-header clearfix hidden-xs"><h2><span>Mensajes Recientes</span></h2></header>
                                    
                                    <div class="main-box-body clearfix">
                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover">
                                                <tbody>
                                                
                                                    <c:if test="${fn:length(list_users) == 0}">
                                                        <tr><td colspan="3" class="text-center">No hay conversaciones previas</td></tr>
                                                    </c:if>

                                                    <c:forEach var="us" items="${list_users}">
                                                        <tr>
                                                            <td><span><c:out value="${us.username}"/></span></td>
                                                            <td class="text-center"><a href="inboxlist.htm?idr=${us.id_user_message}" class="badge badge-${us.qty>0?'primary':'default'}"><c:out value="${us.qty}"/></a></td>
                                                            <td class="text-right"><span><fmt:formatDate type="date" pattern="dd/MM" value="${us.since}"/></span></td>
                                                        </tr>
                                                    </c:forEach>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-lg-8 col-md-6 col-sm-6 col-xs-12">
                                <div class="main-box clearfix">
                                    <header class="main-box-header clearfix">
                                        <h2 class="pull-left">Mensaje Nuevo</h2>
                                    </header>
                                    
                                    <div class="main-box-body clearfix">
                                        <form name="form" method="post" action="inboxdosend.htm">
                                            <input type="hidden" id="id_user" name="id_user" value="${useracegi.id_user}"/>
                                        
                                            <div class="conversation-wrapper">
                                                <div class="conversation-new-message">
                                                        
                                                        <div class="form-group">
                                                            <label>Selecciona un usuario</label>
                                                            <select class="form-control" id="id_user_to" name="id_user_to">
                                                                <c:forEach var="ua" items="${list_users_available}">
                                                                    <option value="${ua.id_user}"><c:out value="${ua.username}"/></option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        
                                                        <div class="form-group">
                                                            <label>Mensaje</label>
                                                            <textarea class="form-control" id="message" name="message" rows="2" placeholder="Ingresa el mensaje"></textarea>
                                                        </div>
                                                        
                                                        <div class="form-group">
                                                            <button type="button" onclick="location.href='inboxlist.htm';" class="btn btn-default btn-xs pull-left"><i class="fa fa-times"></i> Cancelar</button>
                                                            <button type="submit" class="btn btn-success btn-xs pull-right"><i class="fa fa-send"></i> Enviar Mensaje</button>
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
            height: '250px'
            });
        });
    
    $(document).ready(function() {
        $("#message").focus();
        $("#message").select();
        });
	</script>

</body>
 
</html>
