<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>Categor&iacute;a de Plaza - LogistikApp</title>
	
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
	
	<!-- Dwr script -->
	<script type='text/javascript' src='dwr/interface/RetailServiceBean.js'></script>
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

    <input type="hidden" id="idc" name="idc" value=""/>

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
                                    <li><a href="home.htm"><spring:message code="label.breadcrumb.dashboard"/></a></li>
                                     <li><span><spring:message code="label.breadcrumb.logistic"/></span></li>
                                     <li class="active"><span><a href="retaillist.htm"><spring:message code="label.breadcrumb.logistic.retail"/></a></span></li>
								</ol>
								
                                <div class="clearfix">
                                    <h1 class="pull-left"><a href="retailcategorylist.htm"><spring:message code="label.logistic.retailcategory.list.title1"/></a></h1>
                                    
                                    <div class="pull-right hidden-xs">
                                        <button type="button" onclick="location.href='retaillist.htm';" class="btn btn-default btn-xs"><i class="fa fa-arrow-circle-left fa-lg"></i> Volver</button>
	                                    <button type="button" onclick="location.href='retailcategoryadd.htm?accion=add';" class="btn btn-primary btn-xs"><i class="fa fa-plus-circle fa-lg"></i> Nueva</button>
                                    </div>
                                    <div class="pull-right hidden-lg hidden-md hidden-sm">
                                        <button type="button" onclick="location.href='retaillist.htm';" class="btn btn-default btn-xs"><i class="fa fa-arrow-circle-left fa-lg"></i></button>
	                                    <button type="button" onclick="location.href='retailcategoryadd.htm?accion=add';" class="btn btn-primary btn-xs"><i class="fa fa-plus-circle fa-lg"></i></button>
                                    </div>
                                    
                                </div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-lg-12">
							
                                   <div class="main-box clearfix">

                                       <header class="main-box-header clearfix">
                                           <h2 class="pull-left"><spring:message code="label.logistic.retailcategory.list.title2"/></h2>
                                       </header>

                                       <div class="main-box-body clearfix">
                                           <div class="table-responsive">
                                               <!-- list-category-retail -->
                                               <table class="table table-striped table-hover">
												<thead>
													<tr>
														<th><span>Nombre</span></th>
														<th class="hidden-xs"><span>C&oacute;digo</span></th>
														<th class="text-center hidden-xs"><span>Orden</span></th>
														<th class="text-center"><span>Activo</span></th>
														<th><span>Opciones</span></th>
													</tr>
												</thead>
												<tbody>
                                                    <c:if test="${fn:length(list) == 0}">
                                                        <tr><td colspan="5" class="text-center"><spring:message code="label.logistic.retailcategory.list.nodata"/></td></tr>
                                                    </c:if>
                                                    
													<c:forEach var="cat" items="${list}">
														<tr>
															<td><c:out value="${cat.name}"/></td>
															<td class="hidden-xs"><c:out value="${cat.code}"/></td>
                                                            <td class="text-center hidden-xs"><span class="badge badge-default"><c:out value="${cat.orderby}"/></span></td>
                                                            <td class="text-center"><span class="badge badge-${cat.active == ACTIVE ? 'success' : 'danger'}"><i class="fa fa-${cat.active == ACTIVE ? 'check' : 'times'}"></i></span></td>
                                                            <td style="width: 20%;">
                                                                
                                                                <!-- geolocation -->
                                                                <a href="javascript:geolocation(${cat.id_retail_category});" class="table-link maps">
                                                                    <span class="fa-stack">
                                                                        <i class="fa fa-square fa-stack-2x"></i>
                                                                        <i class="fa fa-map-marker fa-stack-1x fa-inverse"></i>
                                                                    </span>
                                                                </a><!-- /geolocation -->
                                                                
                                                                <!-- edit -->
                                                                <a href="retailcategoryupd.htm?id=${cat.id_retail_category}&accion=upd" class="table-link edit">
                                                                    <span class="fa-stack">
                                                                        <i class="fa fa-square fa-stack-2x"></i>
                                                                        <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                                                                    </span>
                                                                </a><!-- /edit -->
                                                            </td>
														</tr>
													</c:forEach>
												</tbody>
											</table><!-- /list-category-retail -->
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
	
	<!-- theme scripts -->
	<script src="js/scripts.js"></script>
	
    <!-- this page specific inline scripts -->
    <script>
    function geolocation(id) {
        document.getElementById("idc").value = id;
        RetailServiceBean.getStoreByIdRetailCategory(id,reply1)
        };
    var reply1 = function(data) {
        var ids = "";
        var sep = "";
        for( var i=0; i<data.length; i++ ) {
            ids = ids + sep + data[i].id_store;
            sep = ",";
            }

        var id = document.getElementById("idc").value;
        
        if( ids.length > 0 ) {
            location.href = "geocategory.htm?id="+id+"&ids="+ids+"&from_retail=true";
            return true;
            }
            
        alert("La categoria de plazas no está asociada a ninguna tienda");
        return false;
        };
    </script>
	
</body>
</html>