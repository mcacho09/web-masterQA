<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Plaza - LogistikApp</title>
	
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
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a href="home.htm">Home</a></li>
                                    <li><span>Configuraci&oacute;n</span></li>
                                    <li class="active"><span>Plazas</span></li>
                                </ol>

                                <div class="clearfix">
                                    <h1 class="pull-left"><spring:message code="${accion == 'add' ? 'label.logistic.retail.add.title1' : 'label.logistic.retail.upd.title1'}"/></h1>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-6">
                                <div class="main-box no-header">
									
                                    <div class="main-box-body clearfix">
                                        <form id="form" name="form" method="post">
                                            <spring:hasBindErrors name="command">
	                                            <div class="form-group">
													<div class="alert alert-danger">
														${errors.errorCount} error${errors.errorCount==1?'':'es'} al ${accion == 'add' ? 'agregar' : 'modificar'} la plaza
													</div>                                                
	                                            </div>
											</spring:hasBindErrors>
											
                                            <spring:bind path="command.id_retail"><input type="hidden" id="id_retail" name="id_retail" value="${status.value}"/></spring:bind>
                                            <spring:bind path="command.id_supplier"><input type="hidden" id="id_supplier" name="id_supplier" value="${idsupplier}"/></spring:bind>

                                            <spring:bind path="command.name">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="name">Nombre (requerido)</label>
                                                    <input type="text" class="form-control" maxlength="50" id="name" name="name" placeholder="Ingresa el nombre del plaza" value="${status.value}" />
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind>
                                                
                                            <spring:bind path="command.code">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="code">C&oacute;digo (requerido)</label>
                                                    <input type="text" class="form-control" id="code" name="code" placeholder="Ingresa un c&oacute;digo"  value="${status.value}" maxlength="9" />
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                </div>
                                            </spring:bind>

                                            <spring:bind path="command.id_retail_category">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label>Categor&iacute;a</label>
                                                    <select class="form-control" id="id_retail_category" name="id_retail_category">
                                                        <c:forEach var="cat" items="${categories}">
                                                            <option value="${cat.id_retail_category}" ${cat.id_retail_category == status.value ? 'selected' : ''}><c:out value="${cat.name}"/></option>
                                                        </c:forEach>                                                        
                                                    </select>
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                 </div>                                               
                                               
                                            </spring:bind>
                                            <spring:bind path="command.id_state">
	                                            <div class="form-group ${status.error ? 'has-error' : '' }">
		                                            <label>Cobertura</label>
	                                                <select class="form-control" id="id_state" name="id_state">
	                                                    <option value="">Nivel Nacional</option>
	                                                    <c:forEach var="stt" items="${states}">
	                                                        <option value="${stt.id_state}" ${stt.id_state == status.value ? 'selected' : ''}><c:out value="${stt.name}"/></option>
	                                                    </c:forEach>
	                                                </select>
                                                    <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
		                                        </div>
                                            </spring:bind>

                                            <spring:bind path="command.orderby">
                                                <div class="form-group ${status.error ? 'has-error' : '' }">
                                                    <label for="orderby">Orden</label>
                                                    <input maxlength="9" type="text" onkeypress="return verificarNumero(event)"  class="form-control" id="orderby" name="orderby" placeholder="Especifica un criterio de orden" value="${status.value}" />
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
                                            <!-- <spring:bind path="command.phone">
                                                	<div class="form-group ${status.error ? 'has-error' : '' }">
                                                    	<label for="phone">Tel&eacute;fono</label>
                                                        <input maxlength="10" type="text" onkeypress="return verificarNumero(event)" class="form-control" onkeypress="return verificarNumero(event)" maxlength="20" size="20" id="phone" name="phone" placeholder="Ingresa el tel&eacute;fono" value="${status.value}">
                                                    	<c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>-->
                                                <spring:bind path="command.email">
                                                	<div class="form-group ${status.error ? 'has-error' : '' }">
                                                		<label for="email">Correo electr&oacute;nico</label>
                                                        <input type="email" class="form-control" maxlength="255" size="255" id="email" name="email" placeholder="Ingresa el correo eléctronico" value="${status.value}">
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
													</div>
												</spring:bind>
                                                <spring:bind path="command.obs">
                                                	<div class="form-group ${status.error ? 'has-error' : '' }">
                                                		<label for="obs">Observaciones:</label>
                                                        <textarea class="form-control" maxlength="255" size="255" id="obs" name="obs" placeholder="Ingresa alguna observación" rows="5"><c:out value="${status.value }"></c:out></textarea>
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
													</div>
												</spring:bind>
                                                
                                            <div class="form-group">
                                                <button type="button" class="btn btn-default btn-xs pull-left" onclick="location.href = 'cfgretaillist.htm';"><i class="fa fa-times fa-lg"></i> Cancelar</button>
                                                <button type="submit" class="btn btn-success btn-xs  pull-right"><i class="fa fa-check fa-lg"></i> <spring:message code="${accion == 'add' ? 'label.logistic.retail.add.button' : 'label.logistic.retail.upd.button'}"/></button>
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
	<script>
		function agregarCategoria(){
			var name = document.getElementById("name").value;
			var code = document.getElementById("code").value;
			var id_state = document.getElementById("id_state").value;
			var orderby = document.getElementById("orderby").value;
			var active = document.getElementById("active").value;
			var id_retail = "${id_retail}";
			location.href = "retailcategoryfromretailadd.htm?accion=add&id_retail="+id_retail+"&retailname="+name+"&retailcode="+code+"&retailid_state="+id_state+"&retailorderby="+orderby+"&retailactive="+active+"&fromaccion="+"${accion}";	
			
		}
		
		 function verificarNumero(evt){
		    	var key = evt.keyCode;
		    	return (key <= 13 || (key>= 48 && key <= 57));
		    }
		 
		 
	
	</script>
</body>

</html>
