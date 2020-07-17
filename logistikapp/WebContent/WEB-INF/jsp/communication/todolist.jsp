<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Tareas - LogistikApp</title>
	
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
                                    <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                    <li class="active"><span><spring:message code="label.breadcrumb.communication"/></span></li>
                                </ol>

                                <div class="clearfix">
                                    <h1 class="pull-left"><a href="todolist.htm"><spring:message code="label.communication.todo.list.title1"/></a></h1>
                                    
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-12">
                            
                                <div class="main-box clearfix">
                                    <header class="main-box-header clearfix">
                                        <div class="row">
                                        	<div class="col-xs-12">
                                        		
                                        			<button type="button" onclick="location.href='todoadd.htm?accion=add';" class="btn btn-primary"><i class="fa fa-plus"></i> Nueva</button>
                                        		<div class="btn-group pull-right">
                                        		<!-- 		  			FILTROS		  				-->
                 	              				<a href="todolist.htm?priority=0" class="btn btn-primary <c:if test="${priority == '0' }">active</c:if>"><c:if test="${priority == '0' }"><i class="fa fa-check"></i></c:if> Todo</a>
                                  				<a href="todolist.htm?priority=1" class="btn btn-primary <c:if test="${priority == '1' }">active</c:if>"><c:if test="${priority == '1' }"><i class="fa fa-check"></i></c:if> Alta</a>                   
                                				<a href="todolist.htm?priority=2" class="btn btn-primary <c:if test="${priority == '2' }">active</c:if>"><c:if test="${priority == '2' }"><i class="fa fa-check"></i></c:if> Media</a>
                 	               				<a href="todolist.htm?priority=3" class="btn btn-primary <c:if test="${priority == '3' }">active</c:if>"><c:if test="${priority == '3' }"><i class="fa fa-check"></i></c:if> Baja</a>
                                    <!-- 				  END FILTROS						 -->
                                        		</div>
                                        	</div>
	                                    </div>
                                    </header>
                                    
                                    <div class="main-box-body clearfix">
                                        <ul class="widget-todo">
                                            <c:forEach var="td" items="${list}" varStatus="vst" >

                                                <c:set var="prioritycss" value=""/>
                                                <c:set var="prioritytxt" value=""/>
                                                <c:if test="${td.priority == '1'}">
	                                                <c:set var="prioritycss" value="danger"/>
	                                                <c:set var="prioritytxt" value="Alta"/>
                                                </c:if>
                                                <c:if test="${td.priority == '2'}">
	                                                <c:set var="prioritycss" value="warning"/>
	                                                <c:set var="prioritytxt" value="Media"/>
                                                </c:if>
                                                <c:if test="${td.priority == '3'}">
	                                                <c:set var="prioritycss" value="success"/>
	                                                <c:set var="prioritytxt" value="Baja"/>
                                                </c:if>
                                                                                            
                                                <li class="clearfix">
                                                    <div class="name">
                                                        <div class="checkbox-nice">
                                                            <c:set var="accion" value="${td.active=='S'?'complete':'active'}"/>
                                                            <input type="checkbox" id="todo-${vst.index}" ${td.active=='N'?'checked=true':''}  onclick="location.href='todoprocess.htm?id=${td.id_todo}&accion=${accion}';"/>
                                                            <label for="todo-${vst.index}"><c:out value="${td.todo}"/> <span class="badge badge-${prioritycss}"><c:out value="${prioritytxt}"/></span></label>
                                                        </div>
                                                    </div>
                                                    <div class="actions">
                                                        <a href="todoupd.htm?id=${td.id_todo}&accion=upd" class="table-link"><i class="fa fa-pencil"></i></a>
                                                        <a href="javascript:eliminar(${td.id_todo})" class="table-link danger"><i class="fa fa-trash-o"></i></a>
                                                    </div>
                                                </li>
                                                
                                            </c:forEach>
                                        </ul>
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
	
	<!-- theme scripts -->
	<script src="js/scripts.js"></script>

    <!-- this page specific scripts -->
    <script>
    function eliminar(id) {
    	   //if ( confirm("¿Eliminar la tarea?") ) {
    		   location.href = "todoprocess.htm?id="+id+"&accion=delete";
    		   return true;
    	   //}
    	   //return false;
    };
    </script>

</body>
 
</html>
