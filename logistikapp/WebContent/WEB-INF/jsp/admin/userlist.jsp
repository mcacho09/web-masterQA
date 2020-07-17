<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Usuarios - LogistikApp</title>
    
    <!-- bootstrap -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
    
    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="css/libs/font-awesome.css" />
    <link rel="stylesheet" type="text/css" href="css/libs/nanoscroller.css" />
	<link rel="stylesheet" type="text/css" href="css/libs/select2.css"/>
    
    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/compiled/theme_styles.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.fixedHeader.css">
    <link rel="stylesheet" type="text/css" href="css/libs/dataTables.tableTools.css">
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
                                <h1 class="pull-left">Usuarios</h1>
								<div class="pull-right">
									<div class="form-group form-group-select2">
										<select style="width:200px" id="sel21">
											<option value="NA">- Proveedor -</option>
											<c:forEach items="${suppliers }" var="i">
												<option value="${i.id_supplier }" ${ids == i.id_supplier?'selected':'' }>${i.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
                            </div>
                        </div>
                        <!-- /breadcrumb -->
                        
                        <div id="user-box" class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">
                                	<form action="admdeluser.htm" method="post" id="form_user">
                                		<input type="hidden" id="idsUser" name="idsUser"/>
                                	</form>
                                    <header class="main-box-header clearfix">
                                    <!--
                                            <div id="header-tools" class="pull-left">
                                            
                                                <div class="btn-group">
                                                    <button id="toolbars-checkall" class="btn btn-primary" type="button" title="Seleccionar" data-toggle="tooltip" data-placement="bottom">
                                                        <i class="fa fa-square-o"></i>
                                                    </button>
                                                </div>
                                                
                                                <div class="btn-group">
                                                    <button id="toolbars-new" class="btn btn-primary" type="button" title="Nuevo" data-toggle="tooltip" data-placement="bottom">
                                                        <i class="fa fa-plus"></i> <span class="hidden-xs">Nuevo</span>
                                                    </button>
                                                    <button class="btn btn-primary" type="button" title="Modificar" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                        <i class="fa fa-pencil"></i>
                                                    </button>
                                                    <button class="btn btn-primary" type="button" title="Desactivar" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                        <i class="fa fa-minus"></i>
                                                    </button>
                                                    <button class="btn btn-primary" type="button" title="Eliminar" data-toggle="tooltip" data-placement="bottom" disabled="disabled" id="eliminar" onclick="javascript:eliminar();" >
                                                        <i class="fa fa-trash-o"></i>
                                                    </button>
                                                    <button id="toolbars-refresh" class="btn btn-primary" type="button" title="Actualizar" data-toggle="tooltip" data-placement="bottom">
                                                        <i class="fa fa-refresh"></i>
                                                    </button>
                                                </div>
                                            </div>

                                            <div id="email-header-pagination" class="pull-right">
                                                <div class="btn-group pagination pull-right">
                                                    <button class="btn btn-primary" type="button" title="Anterior" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                        <i class="fa fa-chevron-left"></i>
                                                    </button>
                                                    <button class="btn btn-primary" type="button" title="Siguiente" data-toggle="tooltip" data-placement="bottom" disabled="disabled">
                                                        <i class="fa fa-chevron-right"></i>
                                                    </button>
                                                </div>
                                                
                                                <div class="num-items pull-right hidden-xs">
                                                    1-10 de 14
                                                </div>
                                            </div>
                                            
                                   -->
                                    </header>
                                    
                                    <div class="main-box-body clearfix">
                                        <div class="table-responsive">
                                            <table id="table-user" class="table table-hover table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Nombre</th>
                                                        <th>Login</th>
                                                        <th class="text-center">Perfil</th>
                                                        <th>Email</th>
                                                        <th class="text-center">Acceso</th>
                                                        <th class="text-center">Estado</th>
                                                        <th class="text-center">Usuario</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                     <c:forEach var="user" items="${list}">
                                                     
                                                        <!-- Se controla el tipo de perfil -->
                                                        <c:set var="profile" value="NaN"/>
                                                        <c:choose>
                                                            <c:when test="${ user.profile == 'ADM' }"><c:set var="profile" value="Admin"/></c:when>
                                                            <c:when test="${ user.profile == 'DEM' }"><c:set var="profile" value="Demo"/></c:when>
                                                            <c:when test="${ fn:contains(user.profile, 'SUP')}"><c:set var="profile" value="Supplier"/></c:when>
                                                            <c:when test="${ fn:contains(user.profile, 'DRI')}"><c:set var="profile" value="Vendedor"/></c:when>
                                                            <c:when test="${ user.profile == 'RET'}"><c:set var="profile" value="Retail"/></c:when>
                                                            <c:when test="${ user.profile == 'STO'}"><c:set var="profile" value="Store"/></c:when>
                                                        </c:choose>
                                                     
                                                         <tr>
                                                             <td>
                                                                 <div class="checkbox-nice">
                                                                     <input type="checkbox" id="m-checkbox-${user.id_user}" value="${user.id_user}"/>
                                                                     <label for="m-checkbox-${user.id_user}">${user.username}</label>
                                                                 </div>
                                                             </td>
                                                             <td>
                                                                 <c:if test="${user.profile != 'ADM' }">
                                                                     <a href="admuserupd.htm?id=${user.id_user}&accion=upd" class="badge badge-primary">${user.userlogin} <i class="fa fa-pencil"></i></a>
                                                                 </c:if>
                                                                 <c:if test="${user.profile == 'ADM' }">
                                                                     <span class="badge badge-default">${user.userlogin}</span>
                                                                 </c:if>
                                                             </td>
                                                             <td class="text-center">
                                                                 <c:if test="${ user.superuser == 'N' }">
                                                                     <span class="badge badge-default">${profile}</span>
                                                                 </c:if>
                                                                 <c:if test="${ user.superuser == 'S' }">
                                                                     <span class="badge badge-warning">${profile} <i class="fa fa-star"></i></span>
                                                                 </c:if>
                                                             </td>
                                                             <td>${user.email}</td>
                                                             <td class="text-center">
                                                                 <c:if test="${ not empty user.supplier && user.profile != 'ADM' }">
                                                                     <a href="#" class="badge badge-default">${user.supplier}</a>
                                                                 </c:if>
                                                                 <c:if test="${ empty user.supplier && user.profile != 'ADM' }">
                                                                     <span class="badge badge-danger">NaN</span>
                                                                 </c:if>
                                                             </td>
                                                             <td class="text-center">
                                                                 <span class="label label-${ user.active == ACTIVE ? 'success' : 'danger' }">
                                                                     ${ user.active == ACTIVE ? 'Activo' : 'Inactivo' }
                                                                 </span>
                                                             </td>
                                                             <td class="text-center">${user.login}</td>
                                                         </tr>
                                                     </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                        <!-- /user-box -->
                        
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

    <!-- this page specific scripts -->
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/dataTables.fixedHeader.js"></script>
    <script src="js/dataTables.tableTools.js"></script>
    <script src="js/jquery.dataTables.bootstrap.js"></script>
    
    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
    <script src="js/pace.min.js"></script>
    <script src="js/select2.min.js"></script>
    
    <!-- this page specific inline scripts -->
    <script>
    $(document).ready( function() {
    	
    	$('#sel21').select2();
    	$('#sel21').on('change', function(){
        	location.href = 'admuserlist.htm?ids=' + this.value
        })

        $('#toolbars-new').click( function() {
            location.href = "admuseradd.htm?accion=add";
        });
        
        $('#toolbars-refresh').click( function() {
            location.reload(true);
        });
        
        var table = $('#table-user').dataTable( {
        	search: true,
        	stateSave: false,
        	pageLength: 10,
            language: {
                url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
            }
        });
        

        $( '#toolbars-checkall' ).on( 'click', function() {
            var rows = table.fnGetNodes();
            
            if ( $(this).find("i").hasClass('fa-square-o') )
                $('input[type="checkbox"]', rows).prop('checked', true );
            else
                $('input[type="checkbox"]', rows).prop('checked', false );
            
            $(this).find("i").toggleClass("fa-square-o fa-check-square-o")
            
        } );
        
    	$("input[type=checkbox]").click(function(index,e) {
            var rows = table.fnGetNodes();
			var qty = $('input[type="checkbox"]:checked', rows).length;
    		if(qty > 0)
    			$('#eliminar').removeAttr('disabled');
    		else
    			$('#eliminar').attr('disabled', 'disabled');
			
        });
        
        } );
    
		function eliminar(){
	        var ids= [];
	        var ids_str= [];
	        var ids_usr = "";
	        var sep ="";                        
			if ( confirm("¿Está seguro que desea borrar los usuarios seleccionados?") ){
	            $("input[type=checkbox]:checked").each(function(index,e){
	            	ids_str = $(this).attr("id");
	            	ids = ids_str.split("-");
	                ids_usr = ids_usr + sep + ids[2];                 
	                sep = ",";
	            });  
	            document.getElementById("idsUser").value = ids_usr;            
	    		document.getElementById("form_user").submit();
			}
		}
    
    </script>
    
</body>

</html>