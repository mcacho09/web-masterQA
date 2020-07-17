<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Categor&iacute;a de Cliente - LogistikApp</title>
	
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
	<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />
	
	<!-- google font libraries -->
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400' rel='stylesheet' type='text/css'>
	
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
                            <div class="col-lg-6">
                                <ol class="breadcrumb">
                                    <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                    <li><span><spring:message code="label.breadcrumb.configuration"/></span></li>
                                    <li class="active"><span>Categorías</span></li>
                                </ol>

                                <div class="clearfix">
                                    <h1 class="pull-left"><spring:message code="${accion == 'add' ? 'label.logistic.storecategory.add.title1' : 'label.logistic.storecategory.upd.title1'}"/></h1>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-6">
                                <div class="main-box">
									
                                    <header class="main-box-header clearfix">
                                        <h2><spring:message code="${accion == 'add' ? 'label.logistic.storecategory.add.title2' : 'label.logistic.storecategory.upd.title2'}"/></h2>
                                    </header>
                                    
                                    <div class="main-box-body clearfix">
                                        <form id="form" method="post">
                                            <input type="hidden" value="${accion}" id="accion" name="accion"/>
                                        	  
                                            <spring:bind path="command.id_supplier"><input type="hidden" id="id_supplier" name="id_supplier" value="${idsupplier}"/></spring:bind>
                                            
                                            <spring:hasBindErrors name="command">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
													<div class="alert alert-danger">
														${errors.errorCount} error${errors.errorCount==1?'':'es'} al ${accion == 'add' ? 'agregar' : 'modificar'} la categor&iacute;a de cliente
													</div>                                                
	                                            </div>
											</spring:hasBindErrors>

                                            <spring:bind path="command.name">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
	                                                <label for="name">Nombre (*)</label>
                                                    <input type="text" class="form-control" maxlength="50" id="name" name="name" placeholder="Ingresa el nombre de la categoria" value="${status.value}" />
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
	                                            </div>
                                            </spring:bind>
                                                
                                            <spring:bind path="command.code">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
	                                                <label for="code">C&oacute;digo (*)</label>
                                                    <input type="text" class="form-control" id="code" name="code" placeholder="Ingresa un c&oacute;digo"  value="${status.value}" maxlength="10"/>
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
	                                            </div>
                                            </spring:bind>

                                            <spring:bind path="command.orderby">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
	                                                <label for="orderby">Orden</label>
                                                    <input maxlength="10" type="text" onkeypress="return verificarNumero(event)" class="form-control" id="orderby" name="orderby" placeholder="Especifica un criterio de orden" value="${status.value}" />
	                                            </div>
                                            </spring:bind>

                                            <spring:bind path="command.active">
                                            
                                                <c:set var="chkactive" value=""/>
                                                <c:choose>
                                                    <c:when test="${ accion == 'add' }"><c:set var="chkactive" value="checked=checked"/></c:when>
                                                    <c:otherwise><c:set var="chkactive" value="${status.value == ACTIVE ? 'checked=checked' : ''}"/></c:otherwise>
                                                </c:choose>
                                            
	                                            <div class="form-group">
	                                                <div class="checkbox-nice">
	                                                        <input type="checkbox" id="active" name="active" value="S" ${chkactive} />
	                                                    <label for="active">¿Activo?</label>
	                                                </div>
	                                            </div>
                                            </spring:bind>
                                            
                                            <div class="form-group">
                                            	<c:if test="${cancelView == 'storecategorylist.htm'}">
                                            		<button type="button" class="btn btn-default pull-left" onclick="location.href = '${cancelView}';"><i class="fa fa-times"></i> Cancelar</button>    
                                              	</c:if>
                                           		<c:if test="${cancelView == 'storeadd.htm?'}">
                                            		<button type="button" class="btn btn-default pull-left" onclick="location.href = '${cancelView}idr=${id_retail}&accion=add';"><i class="fa fa-times"></i> Cancelar</button>  
                                                </c:if>
                                           		<c:if test="${cancelView == 'storeupd.htm?'}">
                                            		<button type="button" class="btn btn-default pull-left" onclick="location.href = '${cancelView}ids=${id_store}&accion=upd';"><i class="fa fa-times"></i> Cancelar</button>  
                                                </c:if>
                                                <c:if test="${cancelView == 'cfgstorecatlist.htm'}">
                                                	<button type="button" class="btn btn-default pull-left" onclick="location.href = 'cfgstorecatlist.htm';"><i class="fa fa-times"></i> Cancelar</button>
                                                </c:if>
                                                <button type="submit" class="btn btn-success pull-right"><i class="fa fa-check"></i> <spring:message code="${accion == 'add' ? 'label.logistic.storecategory.add.button' : 'label.logistic.storecategory.upd.button'}"/></button>                                              	
                                            	
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

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	
	    <!-- this page specific inline scripts -->
    <script type="text/javascript">
       
    function verificarNumero(evt){
	    	var key = evt.keyCode;
	    	return (key <= 13 || (key>= 48 && key <= 57));
	    }
        
	</script>

</body>

</html>
