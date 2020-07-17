<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Categor&iacute;a de Local - LogistikApp</title>
	
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
                                    <h1 class="pull-left"><spring:message code="label.logistic.storecategory.del.title1"/></h1>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-6">
                                <div class="main-box">
									<div class="alert alert-danger">
										    <strong>Cuidado!</strong> Est&aacute;s a punto de eliminar la categor&iacute;a ${category.name}
										  </div>
										  
                                    <header class="main-box-header clearfix">
                                        <h2><spring:message code="label.logistic.storecategory.del.title2"/>:</h2>
                                    </header>
                                    
                                    <div class="main-box-body clearfix">
                                        <form id="form" method="post">
                                        	<input type="hidden" value="${accion}" id="action" name="action"/>
                                            <spring:bind path="command.id_supplier"><input type="hidden" id="id_supplier" name="id_supplier" value="${idsupplier}"/></spring:bind>
                                            
                                            <spring:hasBindErrors name="command">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
													<div class="alert alert-danger">
														${errors.errorCount} error${errors.errorCount==1?'':'es'} al eliminar la categor&iacute;a de cliente
													</div>                                                
	                                            </div>
											</spring:hasBindErrors>
											
											<div class="form-group">
												<div class="radio">
													<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
													<label for="optionsRadios1">
														Eliminar la categor&iacute;a y los clientes asociados.
													</label>
												</div>
												<div class="radio">
													<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
													<label for="optionsRadios2">
														Reasignar las plazas de la categor&iacute;a a eliminar.
													</label>
													<select class="form-control" id="catSelect" name="catSelect">
													
														<c:forEach var="cat" items="${list}">
															<c:if test="${(not(category.id_store_category eq cat.id_store_category)) && cat.active eq 'S'}">
																<option value="${cat.id_store_category}">${cat.name}</option>
                                                        	</c:if>
                                                        </c:forEach>
													</select>
												</div>
											</div> 
											<div class="form-group">
                                            	<button type="button" class="btn btn-default btn-xs pull-left" onclick="location.href = 'cfgstorecatlist.htm';"><i class="fa fa-times fa-lg"></i> Cancelar</button>
                                                <button type="submit" class="btn btn-success btn-xs pull-right" onclick="location.href = 'cfgstorecatlist.htm';"><i class="fa fa-check fa-lg"></i> Eliminar</button>
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
	<script src="js/jquery.maskedinput.min.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/moment.min.js"></script>
	<script src="js/daterangepicker.js"></script>
	<script src="js/bootstrap-timepicker.min.js"></script>
	<script src="js/select2.min.js"></script>
	<script src="js/hogan.js"></script>
	<script src="js/typeahead.min.js"></script>
	<script src="js/jquery.pwstrength.js"></script>

	<!-- theme scripts -->
	<script src="js/scripts.js"></script>

    <!-- this page specific inline scripts -->
        <script type="text/javascript">
        $(document).ready( function(){
        	$('select').attr('disabled', 'disabled');
        	// 'input:radio[name=opcion]:nth(0)'
        	
       		$('#optionsRadios1').click( function() {
       			$('#optionsRadios1').attr('checked', true);
       			$('select').attr('disabled', 'disabled');     
        	}); //
        	
       		$('#optionsRadios2').click( function() {
       			$('#optionsRadios2').attr('checked', true);
       			$('select').removeAttr('disabled');     
        	}); //
        	
        	
        });
        	
        </script>
</body>

</html>
