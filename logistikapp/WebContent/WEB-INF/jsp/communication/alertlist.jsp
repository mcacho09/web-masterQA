<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Notificaciones - LogistikApp</title>
	
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
                                <ol class="breadcrumb hidden-xs">
                                    <li><a href="home.htm">Home</a></li>
                                    <li class="active"><span>Comunicaci&oacute;n</span></li>
                                </ol>

                                <div class="clearfix">
                                    <h1 class="pull-left"><a href="alertlist.htm">Notificaciones</a></h1>
                                </div>
                            </div>
                        </div>

                        <div class="row" id="user-profile">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            
                                <div class="main-box clearfix">
                                    <authz:authorize ifAnyGranted="SUP,SUP1,SUP2,SUP3,SUP4,SUP5,DEM">
                                    <header class="main-box-header clearfix">
                                        <!--<c:if test="${fn:length(list) == 0}">
                                            <h2 class="pull-left"><spring:message code="label.communication.alert.list.nodata"/></h2>
                                        </c:if>
                                        <c:if test="${fn:length(list) > 0}">
                                            <h2 class="pull-left"><spring:message code="label.communication.alert.list.title2"/></h2>
                                        </c:if>-->
	                                    		<button type="button" onclick="location.href='alertadd.htm';" class="btn btn-primary"><i class="fa fa-plus"></i> Nueva</button>
                                    </header>
	                                </authz:authorize>
                                    
                                    <div class="main-box-body clearfix" style="padding-top: 10px!important; padding-left: 10px!important; padding-right: 10px!important;">
                                        <div class="table-responsive">
                                        
                                                <table class="table table-hover">
                                                    <tbody>
                                                        <c:forEach var="nt" items="${list}" varStatus="status">
                                                            <tr>
                                                                <td class="text-center"><img src="${fn:contains(nt.message,'grupo') || fn:contains(nt.message,'grupal')? 'img/group.png' : nt.image }" class="responsive-img" alt="" style="height: 35px; border-radius: 50%;"></td>
                                                                <td id="ir${status.count}" style="width:70%;"><span>${nt.message}</span></td>
                                                                <td><i class="fa fa-clock-o"></i> <fmt:formatDate value="${nt.created}" pattern="MMM dd, HH:mm"/></td>
                                                                <td><span class="label label-default">${nt.username}</span></td>
                                                                <c:if test="${((!fn:contains(nt.message,'actualizado')) && (fn:contains(useracegi.profile,'DRI'))||(fn:contains(useracegi.profile, 'SUP')))}">
	                                                                <td><c:if test="${nt.link != 'alertlist.htm' }">
	                                                                <a href="${nt.link }" class="btn btn-primary btn-xs" style="border-radius: 50%; padding-top: 8px; border-bottom-width: 4px; padding-bottom: 2px; padding-right: 10px; padding-left: 10px;"><i class="fa fa-external-link"></i></a>
	                                                                </c:if></td>
                                                                </c:if>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>                                                
                                            	
                                        </div>
	                                    <div class="pull-right">
	                                        <form id="formmostrar" name="formmostrar" method="POST">
		                                        <button type="button" id="linkmas" onclick="javascript:mostrarmas();" class="btn btn-primary btn-xs"><c:out value="Mostrar ${limit} mas"/></button>
	                                    		<input name="limit" id="limit" value="" type="hidden">
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
    <script type="text/javascript">
    
    $(document).ready(function() {
        $('.notification-inner').slimScroll({
            height: '350px',
            start: 'bottom',
            scrollTo: '${currentlimit}'
        });
    });
    
    function mostrarmas(){
    	var limite = document.getElementById("limit");    	
    	limite.value = "${currentlimit}";  	
    	var ir = ${currentlimit} + 1;    	    	
    	location.href='alertlist.htm?limit=${currentlimit}#ir'+ir;
    	//document.getElementById("formmostrar").submit();    	
    }
    
    </script>
	
</body>
 
</html>