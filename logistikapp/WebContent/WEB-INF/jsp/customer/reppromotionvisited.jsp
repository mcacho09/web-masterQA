<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>Reporte de clientes visitados con promoción

Fecha de inicio:, ${fini }, Fecha de termino:, ${ffin }

#,CÓDIGO,CLIENTE,VIAJE,VISITADO,CHECK-IN,CONDUCTOR
<c:forEach items="${list }" var="item" varStatus="i">${i.index + 1 },"${item.code }","${item.store }","${item.travel }","${item.status == 'S'?'SI':'NO' }","<fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${item.checkin}" />","${item.username }"
</c:forEach>