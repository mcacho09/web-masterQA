<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>Reporte de viaje
Generado el ${generado}
${salto}<c:if test="${control == false}"><c:forEach var="res" items="${resultados}">
${salto}Datos del viaje
${salto}Nombre:,"${res.travel.name}",
${salto}${salto}Asignado:,"${res.chofer.username}",
${salto}Programado:,<fmt:formatDate pattern="dd/MM/yyyy" value="${res.travel.schedule}"/>,
${salto}Iniciado:, <c:if test="${res.iniciado == null}">No Iniciado</c:if><c:if test="${res.iniciado != null}"><fmt:formatDate value="${res.iniciado}" pattern="dd/MM/YYYY HH:mm:ss"/></c:if>,
${salto}Finalizado:,"<c:if test="${res.finalizado == null}">No Finalizado</c:if><c:if test="${res.finalizado != null}"><fmt:formatDate value="${res.finalizado}" pattern="dd/MM/YYYY HH:mm:ss"/></c:if>",
${salto}Cientes,"${fn:length(res.waybills)}",
${salto}Visitados (qty),"${res.visitados}",
${salto}Visitados (%),"${res.porcentaje}",
${salto}

#,NOMBRE,CÓDIGO, DIRECCIÓN,MUNICIPIO,ESTADO,PROMOCIÓN,VISITADO,FECHA CHECKIN,HORA CHECK IN,FUERA DE RANGO,MOVIMIENTOS,COMENTARIOS
<c:forEach var="dato" items="${ res.waybills }" varStatus="i">${i.index + 1},"${dato.name}","${dato.code}","${dato.address1}","${dato.city }","${dato.state }",<c:if test="${dato.shelf == 'S' }">SI,</c:if><c:if test="${dato.shelf != 'S' }">NO,</c:if><c:if test="${dato.status == 'S'}">SI,</c:if><c:if test="${dato.status != 'S'}">NO,</c:if><c:if test="${dato.checkin == null}">NO,NO,</c:if><c:if test="${dato.checkin != null}"><fmt:formatDate value="${dato.checkin}" type="both" pattern="dd/MM"/>,<fmt:formatDate value="${dato.checkin}" type="both" pattern="HH:mm"/>,</c:if><c:if test="${dato.outrange == 'S'}">SI</c:if><c:if test="${dato.outrange == null || dato.outrange == 'N'}">NO</c:if>,"${dato.comment}","${ dato.note}",
</c:forEach>

</c:forEach>

</c:if>
<c:if test="${control == true}">
${salto}Datos
${salto}Cientes,"${clients}",
${salto}Visitados (qty),"${visited}",
${salto}Visitados (%),"${percent}",
${salto}No visitados (qty), "${clients - visited }"
${salto}No visitados (%), "${100.00 - percent }"
${salto}
# Viaje, Nombre del Viaje,Fecha,Asignado,Fecha Iniciado, Hora Iniciado,Fecha Finalizado, Hora Finalizado,#,NOMBRE,CÓDIGO,DIRECCIÓN,MUNICIPIO,ESTADO,PROMOCIÓN,VISITADO,CHECKIN,FUERA DE RANGO,MOVIMIENTOS,COMENTARIOS
 <c:forEach var="res" items="${resultados}" varStatus="ii"><c:forEach var="dato" items="${ res.waybills }" varStatus="i">${ii.index + 1},"${res.travel.name}",<fmt:formatDate pattern="dd/MM/yyyy" value="${res.travel.schedule}"/>,"${res.chofer.username}",<c:if test="${res.iniciado == null}">No Iniciado</c:if><c:if test="${res.iniciado != null}"><fmt:formatDate value="${res.iniciado}" pattern="dd/MM/YYYY"/></c:if>,<c:if test="${res.iniciado == null}">No Iniciado</c:if><c:if test="${res.iniciado != null}"><fmt:formatDate value="${res.iniciado}" pattern="HH:mm:ss"/></c:if>,<c:if test="${res.finalizado == null}">No Finalizado</c:if><c:if test="${res.finalizado != null}"><fmt:formatDate value="${res.finalizado}" pattern="dd/MM/YYYY"/></c:if>,<c:if test="${res.finalizado == null}">No Finalizado</c:if><c:if test="${res.finalizado != null}"><fmt:formatDate value="${res.finalizado}" pattern="HH:mm:ss"/></c:if>,${i.index + 1},"${dato.name}","${dato.code}","${dato.address1}","${dato.city }","${dato.state }",<c:if test="${dato.shelf == 'S' }">SI,</c:if><c:if test="${dato.shelf != 'S' }">NO,</c:if><c:if test="${dato.status == 'S'}">SI,</c:if><c:if test="${dato.status != 'S'}">NO,</c:if><c:if test="${dato.checkin == null}">NO,</c:if><c:if test="${dato.checkin != null}"><fmt:formatDate value="${dato.checkin}" type="both" pattern="dd/MM HH:mm"/>,</c:if><c:if test="${dato.outrange == 'S'}">SI</c:if><c:if test="${dato.outrange == null || dato.outrange == 'N'}">NO</c:if>,"${dato.comment}","${ dato.note}",
</c:forEach></c:forEach>
</c:if>