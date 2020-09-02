<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib  prefix="authz" uri="http://acegisecurity.org/authz" %>

<div id="col-left-inner" class="col-left-nano-content">

    <!-- user -->
    <div id="user-left-box" class="clearfix hidden-sm hidden-xs">
        <img src="${useracegi.image}"/>
        <div class="user-box">
            <span class="name">${useracegi.fullname}</span>
            <span class="status">
                <i class="fa fa-circle"></i> Conectado
            </span>
        </div>
    </div>
    <!-- /user -->
    
    <div class="collapse navbar-collapse navbar-ex1-collapse" id="sidebar-nav">
        <ul class="nav nav-pills nav-stacked">
        
            <!-- dashboard -->
            
            <li class="${fn:contains(pageContext.request.servletPath, '/home/') ? 'active':''}" >
                <a href="home.htm">
                    <i class="fa fa-bar-chart-o"></i>
                    <c:if test="${ (useracegi.profile != 'ADM' ) }"><span>Inteligencia de Negocios</span></c:if>
                    <c:if test="${ (useracegi.profile == 'ADM')}"><span>Analytics</span></c:if>
                </a>
            </li>
             
            
            <!-- customer -->
            <authz:authorize ifNotGranted="ADM,LGK,DRI,DRI1,DRI2,DRI3,DRI4,DRI5,SOP">
            <li ${ fn:contains(pageContext.request.servletPath, '/customer/') ? 'class=active':'' }
                 ${ fn:contains(pageContext.request.servletPath, '/maps/geocustomer') ? 'class=active':'' }
                 ${ fn:contains(pageContext.request.servletPath, '/maps/geostore') ? 'class=active':'' }
                 ${ fn:contains(pageContext.request.servletPath, '/maps/geolocation') ? 'class=active':'' } >
                <a href="customer.htm">
                    <i class="fa fa-building"></i>
                    <span>Clientes</span>
                </a>
            </li>
            </authz:authorize>
            <c:if test="${fn:contains(useracegi.profile,'DRI') && useracegi.superuser == 'S' }">
            	<li class="${ fn:contains(pageContext.request.servletPath, '/customer/') ? 'active':'' }">
                	<a href="customeradd.htm">
                    	<i class="fa fa-building"></i>
                    	<span> Nuevo Cliente</span>
                	</a>
            	</li>
            </c:if>
            <!-- /customer -->
            
            <!-- sales -->          
            <authz:authorize ifNotGranted="ADM,SOP">
			<li class="${fn:contains(pageContext.request.servletPath, '/sales/') ? 'active':''}">
                <a href="#" class="dropdown-toggle">
                    <i class="fa fa-shopping-cart"></i> <span>Ventas</span>
                    <i class="fa fa-chevron-circle-right drop-icon"></i>
                </a>
                <ul class="submenu">
                    <li><a href="productlist.htm" class="${fn:contains(pageContext.request.servletPath, '/sales/productlist') ? 'active':''}"> Productos </a></li>
                    <c:if test="${!fn:startsWith(useracegi.profile,'DRI') || fn:startsWith(useracegi.superuser, 'S') }">
                    	<li><a href="productmix.htm" class="${fn:contains(pageContext.request.servletPath, '/sales/productmix') ? 'active':''}"> Mix de Producto </a></li>
                    </c:if>
                    <!-- <li><a href="pos.htm" ${fn:contains(pageContext.request.servletPath, '/sales/pos.htm') ? 'class=active':''}> Punto de Venta </a></li>-->
                    <authz:authorize ifNotGranted="DRI,DRI1,DRI2,DRI3,DRI4,DRI5">
                    <li><a href="reportbydri.htm" class="${fn:contains(pageContext.request.servletPath, '/sales/reportbydri') ? 'active':''}"> Reporte / Trx </a></li>
                    </authz:authorize>
                    <c:if test="${fn:contains(useracegi.profile, 'DRI') }">
                    <li><a href="productreport.htm?idu=${useracegi.id_user }" class="${fn:contains(pageContext.request.servletPath, '/sales/productreport') ? 'active':''}"> Reporte / Trx </a></li>
                    </c:if>
                    <c:if test="${!fn:contains(useracegi.profile, 'DRI') }">
                    <li><a href="stock.htm" class="${fn:contains(pageContext.request.servletPath, '/sales/stock') ? 'active':''}"> Almacén </a></li>
                    </c:if>
                    <c:if test="${fn:contains(useracegi.profile, 'DRI') }">
                    <li><a href="userstock.htm" class="${fn:contains(pageContext.request.servletPath, '/sales/stockdri') ? 'active':''}"> Almacén </a></li>
                    </c:if>
                </ul>
            </li>        
            </authz:authorize>
            <!-- /sales -->
            

            <!-- logistic -->            
            <authz:authorize ifNotGranted="ADM,SUP2,DRI2,SOP">
               <li class="${fn:contains(pageContext.request.servletPath, '/logistic/') || fn:contains(pageContext.request.servletPath, '/maps/geotravel') || fn:contains(pageContext.request.servletPath, '/callcenter/') ? 'active':''}">
	                <a href="#" class="dropdown-toggle">
	                    <i class="fa fa-truck"></i> <span> Log&iacute;stica </span>
	                    <i class="fa fa-chevron-circle-right drop-icon"></i>
	                </a>
	                <ul class="submenu">
	                    <authz:authorize ifNotGranted="DRI,DRI1,DRI2,DRI3,DRI4,DRI5">
	        	                <li><a href="rutas.htm" class="${fn:contains(pageContext.request.servletPath, '/logistic/rutas') ? 'active':''}"> Rutas </a></li>
	        	            </authz:authorize>
	        	            
	        	            <c:set var="activetag" value=""/>
	        	            <c:choose>
	        	                <c:when test="${fn:contains(pageContext.request.servletPath, '/logistic/travellist')}"><c:set var="activetag" value="class='active'"/></c:when>
	        	             
	        	                <c:when test="${fn:contains(pageContext.request.servletPath, '/logistic/traveladm')}"><c:set var="activetag" value="class='active'"/></c:when>
	        	                <c:when test="${fn:contains(pageContext.request.servletPath, '/logistic/travelupd')}"><c:set var="activetag" value="class='active'"/></c:when>
	        	                <c:when test="${fn:contains(pageContext.request.servletPath, '/logistic/travelconfirm')}"><c:set var="activetag" value="class='active'"/></c:when>
	        	                <c:when test="${fn:contains(pageContext.request.servletPath, '/logistic/travelwaybill')}"><c:set var="activetag" value="class='active'"/></c:when>
	        	                <c:when test="${fn:contains(pageContext.request.servletPath, '/logistic/travelonway')}"><c:set var="activetag" value="class='active'"/></c:when>
	        	                <c:otherwise><c:set var="activetag" value=""/></c:otherwise>
	        	            </c:choose>
	                    <li><a href="travellist.htm" ${activetag}> Viajes </a></li>
	                    
	                      <c:set var="activetag2" value=""/>
	        	            <c:choose>
	                       <c:when test="${fn:contains(pageContext.request.servletPath, '/logistic/travelreport')}"><c:set var="activetag2" value="class='active'"/></c:when>
	                        <c:otherwise><c:set var="activetag2" value=""/></c:otherwise>
	                      </c:choose>
	                      
	                    <li><a href="travelhistorylist.htm" ${activetag2} ${fn:contains(pageContext.request.servletPath, '/logistic/travelhistory') ? 'class=active':''}> Hist&oacute;rico </a></li>
		                    
		                <authz:authorize ifNotGranted="DRI,DRI1,DRI2,DRI3,DRI4,DRI5,SUP3,LGK">
		                    <li><a href="callcenter.htm" class="${fn:contains(pageContext.request.servletPath, '/callcenter/callcenter') ? 'active':''}"> Pedidos </a></li>
		                </authz:authorize>
	                </ul>
	            </li>
	           
            </authz:authorize>
            <!-- /logistic -->

            <!-- communication -->
            <authz:authorize ifNotGranted="ADM">
            <li class="${fn:contains(pageContext.request.servletPath, '/communication/') ? 'active':''}">
                <a href="#" class="dropdown-toggle">
                    <i class="fa fa-share-alt"></i> <span>Comunicaci&oacute;n</span>
                    <i class="fa fa-chevron-circle-right drop-icon"></i>
                </a>
                <ul class="submenu">
                    <authz:authorize ifNotGranted="SOP"><li><a href="alertlist.htm" class="${fn:contains(pageContext.request.servletPath, '/communication/alertlist') ? 'active':''}"> Notificaciones </a></li></authz:authorize>
                    <li><a href="inbox.htm" class="${fn:contains(pageContext.request.servletPath, '/communication/inbox.jsp') ? 'active':''}"> Mensajes </a></li>
                    <authz:authorize ifNotGranted="SOP">
                    <li><a href="inboxgroup.htm" class="${fn:contains(pageContext.request.servletPath, '/communication/inboxgroup') ? 'active':''}"> Grupos </a></li>
                    </authz:authorize>
                    <li><a href="todolist.htm" class="${fn:contains(pageContext.request.servletPath, '/communication/todolist') ? 'active':''}"> Tareas </a></li>
                    <authz:authorize ifNotGranted="SOP">
                    <li><a href="calendar.htm" class="${fn:contains(pageContext.request.servletPath, '/communication/calendar') ? 'active':''}"> Calendario </a></li>
                    </authz:authorize>
                </ul>
            </li>
            </authz:authorize>
            <!-- /communication -->

            <!-- configuration -->
            <authz:authorize ifAnyGranted="SUP,SUP1,SUP2,SUP3,SUP4,SUP5">
            	<!-- c:if test="${useracegi.superuser == 'S' }"-->
	                <li class="${fn:contains(pageContext.request.servletPath, '/config/') ?'active' : ''}">
	                    <a href="#" class="dropdown-toggle" >
	                        <i class="fa fa-cogs"></i> <span>Configuraci&oacute;n</span>
	                        <i class="fa fa-chevron-circle-right drop-icon"></i>
	                    </a>
	                    <ul class="submenu">
	                    <c:if test="${fn:contains(useracegi.profile, 'SUP') }">
	                        <li><a href="cfgsupplier.htm" class="${fn:contains(pageContext.request.servletPath, '/config/supplier') ? 'active':''}"> Empresa </a></li>
	                    </c:if>	                     
	                   	<c:if test="${fn:contains(useracegi.profile, 'SUP')}">
	                        <li><a href="cfguserlist.htm" class="${fn:contains(pageContext.request.servletPath, '/config/user') ? 'active':''}"> Usuarios </a></li>
	                    </c:if>
	                        <li><a href="cfgretaillist.htm" class="${fn:contains(pageContext.request.servletPath, '/config/retail') ? 'active':''}"> Plazas </a></li>
	                        <li><a href="cfgstorecatlist.htm" class="${fn:contains(pageContext.request.servletPath, '/config/storecategory') ? 'active':''}"> Categor&iacute;as de Clientes </a></li>
	                        <li><a href="categoryproductlist.htm" class="${fn:contains(pageContext.request.servletPath, '/config/categoryproductlist') ? 'active':''}">Categor&iacute;as de Productos </a></li>
	                        <li><a href="markproductlist.htm" class="${fn:contains(pageContext.request.servletPath, '/config/markproductlist') ? 'active':''}">Marca de Productos </a></li>
	                    </ul>
	                </li>
	            <!-- /c:if-->
            </authz:authorize>
            <!-- /configuration -->
            
            <!-- administration -->
            <authz:authorize ifAllGranted="ADM">
                <li class="${ fn:contains(pageContext.request.servletPath, '/admin/') ? 'active' : '' }">
                    <a href="#" class="dropdown-toggle" >
                        <i class="fa fa-cogs"></i> <span>Administraci&oacute;n</span>
                        <i class="fa fa-chevron-circle-right drop-icon"></i>
                    </a>
                    <ul class="submenu">
                        <li><a href="admsupplierlist.htm" class="${fn:contains(pageContext.request.servletPath, '/admin/supplier') ? 'active':''}"> Proveedores </a></li>
                        <li><a href="admuserlist.htm" class="${fn:contains(pageContext.request.servletPath, '/admin/user') ? 'active':''}"> Usuarios </a></li>
                        <li><a href="planslist.htm" class="${fn:contains(pageContext.request.servletPath, '/admin/plans') ? 'active':''} "> Planes </a></li>
                        <li><a href="countrylist.htm" class="${fn:contains(pageContext.request.servletPath, '/admin/country') ? 'active':''}"> Pa&iacute;ses </a></li>
                        <li><a href="statelist.htm" class="${fn:contains(pageContext.request.servletPath, '/admin/state') ? 'active':''}" > Estados </a></li>
                    </ul>
                </li>
            </authz:authorize>
            <!-- /administration -->
            
            <!-- Desarrollo pantalla cobro --> 
            <!-- <li ${ fn:contains(pageContext.request.servletPath, '/payment/') ? 'class=active':'' }>
                <a href="planpayment.htm">
                    <i class="fa fa-money"></i>
                    <span>Pagos</span>
                </a>
            </li> -->
             <!-- Desarrollo pantalla cobro -->
            
        </ul>            
	</div>
</div>