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
                                    <ol class="breadcrumb hidden-xs">

                                        <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                        <li><span><spring:message code="label.breadcrumb.communication"/></span></li>
                                        <li class="active"><a href="todolist.htm"><span><spring:message code="label.breadcrumb.communication.todo"/></span></a></li>
									</ol>

                                    <div class="clearfix">
                                        <h1 class="pull-left"><spring:message code="${accion == 'add' ? 'label.communication.todo.add.title1' : 'label.communication.todo.upd.title1'}"/></h1>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="main-box">
                                    
                                        <header class="main-box-header clearfix">
                                            <h2><spring:message code="${accion == 'add' ? 'label.communication.todo.add.title2' : 'label.communication.todo.upd.title2'}"/></h2>
                                        </header>

                                        <div class="main-box-body clearfix">
                                            <form id="form" method="post">
                                                <spring:bind path="command.id_todo"><input type="hidden" id="id_todo" name="id_todo" value="${status.value}"/></spring:bind>
                                                
                                                <div class="form-group">
                                                    <label for="todo">Tarea</label>
                                                    <spring:bind path="command.todo">
                                                        <textarea maxlength="255" rows="3" cols="50" class="form-control" id="todo" name="todo" placeholder="Ingresa una descripci&oacute;n para la tarea" ><c:out value="${status.value}"/></textarea>
                                                        <span class="pull-right" id="text_counter"></span>
                                                    </spring:bind>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label for="priority">Prioridad</label>
                                                    <spring:bind path="command.priority">
                                                        <c:set var="check_radio_alta" value="" />
                                                        <c:set var="check_radio_media" value="" />
                                                        <c:set var="check_radio_baja" value="" />
                                                        <c:if test="${status.value == '1'}"><c:set var="check_radio_alta" value="checked" /></c:if>
                                                        <c:if test="${status.value == '2'}"><c:set var="check_radio_media" value="checked" /></c:if>
                                                        <c:if test="${status.value == '3'}"><c:set var="check_radio_baja" value="checked" /></c:if>
                                                        
                                                        <!-- valor por defecto -->
                                                        <c:if test="${empty status.value}"><c:set var="check_radio_media" value="checked" /></c:if>
                                                        
                                                        <input type="radio" id="priority1" name="priority" value="1" ${check_radio_alta}> Alta
                                                        <input type="radio" id="priority2" name="priority" value="2" ${check_radio_media}> Media
                                                        <input type="radio" id="priority3" name="priority" value="3" ${check_radio_baja}> Baja
                                                    </spring:bind>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <button type="button" class="btn btn-default btn-xs pull-left" onclick="location.href = 'todolist.htm';"><i class="fa fa-times fa-lg"></i> Cancelar</button>
                                                    <button type="button" onclick="addtodo(); " class="btn btn-success btn-xs pull-right"><i class="fa fa-check fa-lg"></i> <spring:message code="${accion == 'add' ? 'label.communication.todo.add.button' : 'label.communication.todo.upd.button'}"/></button>
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
	
	<!-- this page specific scripts -->
	<script>
	$(document).ready(function() {
	      //alert("document ready occurred!");
	      $("#todo").focus();
	      $("#todo").select();
	      
	      
	      var left = 255;
	        $('#text_counter').text('Caracteres restantes: ' + left);
	 
	            $('#todo').keyup(function () {
	            left = 255 - $(this).val().length;
	            $('#text_counter').text('Caracteres restantes: ' + left);
	        });
	         
	});
	function addtodo(){
		if ($('#todo').val().length == 0) swal('Alerta','Ingrese la descripción de la tarea','warning');
	    else $('form').submit();  
	}
	</script>
	
</body>

</html>