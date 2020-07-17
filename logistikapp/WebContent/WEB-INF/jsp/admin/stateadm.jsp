<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>Estados - LogistikApp</title>
	
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
	
	<!-- Dwr script -->
	<script type='text/javascript' src='dwr/interface/UserServiceBean.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	
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
                                        <li class="active"><a href="statelist.htm"><spring:message code="label.breadcrumb.state"/></a></li>
								</ol>

								<div class="clearfix">
									<h1 class="pull-left"><spring:message code="${accion == 'add' ? 'label.config.state.add.title1' : 'label.config.state.upd.title1'}"/></h1>
								</div>
							</div>
						</div>

                        <div class="row">
                            <div class="col-lg-6">
                                <div class="main-box">
								
                                    <header class="main-box-header clearfix">
                                        <h2><spring:message code="${accion == 'add' ? 'label.config.state.add.title2' : 'label.config.state.upd.title2'}"/></h2>
                                    </header>
                                   
                                    <div class="main-box-body clearfix">
                                        <form id="form" method="post">
                                            <spring:bind path="command.login"><input type="hidden" id="login" name="login" value="${login}"/></spring:bind>
                                            <spring:bind path="command.id_state"><input type="hidden" id="id_state" name="id_state" value="${status.value}"/></spring:bind>
                                           
                                                <div class="form-group">
                                                    <label>Pa&iacute;s</label>
                                                    <spring:bind path="command.id_country">
                                                        <select class="form-control" id="id_country" name="id_country">
                                                            <c:forEach var="cty" items="${list_country}">
                                                                <option value="${cty.id_country}" ${status.value == cty.id_country ? 'SELECTED' : ''} ><c:out value="${cty.name}"/></option>
                                                            </c:forEach>
                                                        </select>
                                                    </spring:bind>
                                                </div>
                                                
                                               <div class="form-group">
                                                   <label for="name">Nombre</label>
							                    <spring:bind path="command.name">
       	                                            <input type="text" class="form-control" id="name" name="name" placeholder="Ingresa el nombre de estado" value="${status.value}" >
                                                </spring:bind>
                                               </div>
                                               
                                                <div class="form-group">
                                                    <label for="code">C&oacute;digo</label>
                                                    <spring:bind path="command.code">
                                                        <input type="text" class="form-control" id="code" name="code" placeholder="Ingresa un c&oacute;digo" value="${status.value}">
                                                    </spring:bind>
                                                </div>
                                               
                                                <div class="form-group">
                                                    <label for="prefix">Prefijo</label>
                                                    <spring:bind path="command.prefix">
                                                        <input type="text" class="form-control" id="prefix" name="prefix" placeholder="Ingresa un prefijo" value="${status.value}">
                                                    </spring:bind>
                                                </div>
                                               
                                                <div class="form-group">
                                                    <label for="orderby">Orden</label>
                                                    <spring:bind path="command.orderby">
                                                        <input type="text" class="form-control" id="orderby" name="orderby" placeholder="Especifica un criterio de orden" value="${status.value}">
                                                    </spring:bind>
                                                </div>

                                                <div class="form-group">
                                                    <div class="checkbox-nice">
                                                        <spring:bind path="command.active">
                                                            <input type="checkbox" id="active" name="active" value="S" ${status.value == ACTIVE ? 'checked=checked' : ''} />
                                                        </spring:bind>
                                                        <label for="active">¿Activo?</label>
                                                    </div>
                                                </div>
                                               
                                               <button type="button" class="btn btn-default" onclick="javascript:cancelar();">Cancelar</button>
                                               <button type="submit" class="btn btn-success"><spring:message code="${accion == 'add' ? 'label.config.state.add.button' : 'label.config.state.upd.button'}"/></button>
                                               
                                           </form>
									</div>
									
							        <!-- bloque error -->
							        <div class="span4">
							        
							            <spring:hasBindErrors name="command">
								            <div class="alert alert-danger">
								                 ${errors.errorCount} error${errors.errorCount==1?'':'es'} al agregar el estado:
								                 <ul>
								                    <c:forEach var="errMsgObj" items="${errors.allErrors}">
								                       <li>
								                          <spring:message code="${errMsgObj.code}" text="${errMsgObj.defaultMessage}"/>
								                       </li>
								                    </c:forEach>
								                 </ul>
								            </div>
							            </spring:hasBindErrors>
							        
							        </div>
								</div>
							    <!-- /bloque error -->
							
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
	function cancelar() {
        location.href = "statelist.htm";
        return true;
	};
	</script>
	
</body>

</html>