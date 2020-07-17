<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Proveedores - LogistikApp</title>
    
    <!-- bootstrap -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
    
    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />
    
    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css" />
    
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
            <!-- /nav-col -->

            <div id="content-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        
                        <div id="breadcrumb" class="row">
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                    <li class="active"><span><spring:message code="label.breadcrumb.admin"/></span></li>
                                </ol>
                                <h1><a href="admsupplierlist.htm"><spring:message code="label.admin.supplier.list.title1"/></a></h1>
                            </div>
                        </div>
                        <!-- /breadcrumb -->
                        
                        <div id="supplier-box" class="row">
                            <div class="col-lg-6">
                                <div class="main-box clearfix">

                                    <header class="main-box-header clearfix">
                                    
                                            <spring:hasBindErrors name="command">
                                            <div class="alert alert-danger fade in">
                                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                                <i class="fa fa-times-circle fa-fw fa-lg"></i>
                                                Para continuar primero debes revisar los errores que est&aacute;n m&aacute;s abajo.
                                            </div>
                                            </spring:hasBindErrors>
                                            
                                        <h2><spring:message code="${accion == 'add' ? 'label.admin.supplier.add.title2' : 'label.admin.supplier.upd.title2'}"/></h2>
                                    </header>
                                    
                                        <div class="main-box-body clearfix">
                                            <form id="form" method="post">
                                            
                                                <spring:bind path="command.id_supplier"><input type="hidden" id="id_supplier" name="id_supplier" value="${status.value}"/></spring:bind>
                                            
                                                <spring:bind path="command.name">
                                                   <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="name">Nombre (requerido)</label>
                                                        <input type="text" class="form-control" id="name" name="name" placeholder="Ingresa el nombre del proveedor" value="${status.value}" />
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>
                                                
                                                <spring:bind path="command.code">
                                                    <div class="form-group ${status.error ? 'has-error' : '' }">
                                                        <label for="code">C&oacute;digo (requerido)</label>
                                                        <input type="text" class="form-control" id="code" name="code" placeholder="Ingresa un c&oacute;digo"  value="${status.value}" />
                                                        <c:if test="${status.error}"><span class="help-block"><i class="fa fa-exclamation-triangle"></i> ${status.errorMessage}</span></c:if>
                                                    </div>
                                                </spring:bind>
                                                
                                                <spring:bind path="command.orderby">
                                                    <div class="form-group">
                                                        <label for="orderby">Orden (opcional)</label>
                                                        <input type="text" class="form-control" id="orderby" name="orderby" placeholder="Especifica un criterio de orden" value="${status.value}" />
                                                    </div>
                                                </spring:bind>

                                                <spring:bind path="command.active">
                                                    <div class="form-group">
                                                        <div class="checkbox-nice">
                                                            <input type="checkbox" id="active" name="active" value="S" ${status.value == ACTIVE ? 'checked=checked' : ''} />
                                                            <label for="active">¿Activo?</label>
                                                        </div>
                                                    </div>
                                                </spring:bind>
                                                
                                                <div class="form-group">
                                                    <button type="button" id="toolbars-cancel" class="btn btn-default btn-xs pull-left">
                                                        <i class="fa fa-times fa-lg"/></i> Cancelar
                                                    </button>
                                                    <button type="submit" class="btn btn-success btn-xs pull-right">
                                                        <i class="fa fa-check fa-lg"/></i>
                                                        <spring:message code="${accion == 'add' ? 'label.admin.supplier.add.button' : 'label.admin.supplier.upd.button'}"/>
                                                    </button>
                                                </div>
                                                
                                            </form>
                                        </div>
                                    

                                </div>
                            </div>
                        </div>
                        <!-- /supplier-box -->
                        
                    </div>
                </div>
                
                <footer id="footer-bar" class="row">
                    <c:import url="/html/footer.html" />
                </footer>
            </div>

        </div>
    </div>
    <!-- /page-wrapper -->

    <!-- global scripts -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.nanoscroller.min.js"></script>

    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
    
    <!-- this page specific inline scripts -->
    <script>
    $(document).ready(function() {
        
        $('#toolbars-cancel').click( function(){
            window.location.href = "admsupplierlist.htm";
            });
	
    });
    </script>
    
</body>

</html>
