<%@ include file="/includes/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Plazas - LogistikApp</title>
    
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

    <input type="hidden" id="idr" name="idr" value=""/>

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
                                     <li class="active"><span><spring:message code="label.breadcrumb.logistic"/></span></li>
                                </ol>
                                
                                <div class="clearfix">
                                    <h1 class="pull-left"><a href="retaillist.htm"><spring:message code="label.logistic.retail.list.title1"/></a></h1>
                                    
                                    <div class="pull-right hidden-xs">
                                        <button type="button" onclick="location.href='retailadd.htm?accion=add';" class="btn btn-primary btn-xs"><i class="fa fa-plus-circle fa-lg"></i> Nuevo</button>
                                    </div>
                                    <div class="pull-right hidden-lg hidden-md hidden-sm">
                                        <button type="button" onclick="location.href='retailadd.htm?accion=add';" class="btn btn-primary btn-xs"><i class="fa fa-plus-circle fa-lg"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-lg-12">
                            
                                   <div class="main-box clearfix">

                                       <header class="main-box-header clearfix">
                                           <h2 class="pull-left"><spring:message code="label.logistic.retail.list.title2"/></h2>

                                            <div class="filter-block pull-right">
                                                <a href="retailcategorylist.htm" class="badge badge-info"><i class="fa fa-tags"></i> Plazas</a>
                                                <a href="storecategorylist.htm" class="badge badge-info"><i class="fa fa-tags"></i> clientes</a>
                                                <!--span class="badge bagde-default"><c:out value="${supplier.name}"/> <i class="fa fa-external-link-square"></i></span-->
                                            </div>
                                       </header>

                                       <div class="main-box-body clearfix">
                                           <div class="table-responsive">
                                               <!-- list-retail -->
                                               <table class="table table-striped table-hover">
                                                <thead>
                                                    <tr>
                                                        <th><span>Plaza</span></th>
                                                        <th><span>Categor&iacute;a</span></th>
                                                        <th class="text-center"><span>clientes</span></th>
                                                        <th class="text-center"><span>Orden</span></th>
                                                        <th class="hidden-xs"><span>C&oacute;digo</span></th>
                                                        <th class="text-center"><span>Activo</span></th>
                                                        <th><span>Opciones</span></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${fn:length(list) == 0}">
                                                        <tr><td colspan="7" class="text-center"><spring:message code="label.logistic.retail.list.nodata"/></td></tr>
                                                    </c:if>
                                                    <c:forEach var="ret" items="${list}">
                                                        <tr>
                                                            <td><c:out value="${ret.name}"/></td>
                                                            <td><span class="badge badge-info"><c:out value="${ret.category}"/></span></td>
                                                            <td class="text-center"><a href="storelist.htm?id=${ret.id_retail}" class="badge badge${ret.stores > 0 ? '-primary' : ''}"><i class="fa fa-search-plus"></i> <c:out value="${ret.stores}"></c:out></a></td>
                                                            <td class="text-center"><span class="badge badge-default"><c:out value="${ret.orderby}"/></span></td>
                                                            <td class="hidden-xs"><c:out value="${ret.code}"/></td>
                                                            <td class="text-center"><span class="badge badge-${ret.active == ACTIVE ? 'success' : 'danger'}"><i class="fa fa-${ret.active == ACTIVE ? 'check' : 'times'}"></i></span></td>
                                                            <td style="width: 15%;">
                                                                <!-- geolocation -->
                                                                <a href="javascript:geolocation(${ret.id_retail});" class="table-link maps">
                                                                    <span class="fa-stack">
                                                                        <i class="fa fa-square fa-stack-2x"></i>
                                                                        <i class="fa fa-map-marker fa-stack-1x fa-inverse"></i>
                                                                    </span>
                                                                </a><!-- /geolocation -->
                                                                
                                                                <!-- detail -->
                                                                <a href="retaildetail.htm?id=${ret.id_retail}" class="table-link detail">
                                                                    <span class="fa-stack">
                                                                        <i class="fa fa-square fa-stack-2x"></i>
                                                                        <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
                                                                    </span>
                                                                </a><!-- /detail -->
                                                                
                                                                <!-- edit -->
                                                                <a href="retailupd.htm?id=${ret.id_retail}&accion=upd" class="table-link edit">
                                                                    <span class="fa-stack">
                                                                        <i class="fa fa-square fa-stack-2x"></i>
                                                                        <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                                                                    </span>
                                                                </a><!-- /edit -->
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table><!-- /list-retail -->
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
    
    <!-- theme scripts -->
    <script src="js/scripts.js"></script>
    
    <!-- this page specific inline scripts -->
    <script type="text/javascript">
        function geolocation(id) {
            // Se obtienen todos las clientes activos para el plaza
            document.getElementById("idr").value = id;
            //RetailServiceBean.getStoreByIdRetail(id,reply1)
            RetailServiceBean.getIdStoresActiveByIdRetail(id,reply1)
        };
        var reply1 = function(data) {
            var ids = "";
            var sep = "";
            for( var i=0; i<data.length; i++ ) {
                //ids = ids + sep + data[i].id_store;
                ids = ids + sep + data[i];
                sep = ",";
                }
            
            // Id retail
            var id = document.getElementById("idr").value;
            
            if( ids.length > 0 ) {
                location.href = "geolocation.htm?id="+id+"&ids="+ids;
                return true;
                }
            
            alert("La plaza no tiene clientes definidas y/o activas");
            return false;
            };
    </script>
    
</body>
</html>
