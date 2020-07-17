<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>"Métricas de venta"
"Productos",${metrics.productsQty }
"Merma", ${metrics.decrease }
"Utilidad", ${metrics.utility }
"No de trx", ${metrics.notrx }
"Ticket promedio", ${metrics.ticketavg }
"Ventas", ${metrics.sale }
<c:forEach var="i" items="${categorysales}"> ,${i.category },${i.sale }
</c:forEach>

"Fecha","Hora","Código","Cliente","Usuario","Num Trx","Tipo Trx","Total","Estado",<c:forEach var="pdt" items="${products}">"${pdt.name_short}",</c:forEach>
<c:forEach var="trx" items="${list}">"<fmt:formatDate  pattern="dd/MM/yyyy" value="${trx.invoice}"/>","<fmt:formatDate  pattern="HH:mm:ss" value="${trx.invoice}"/>","${trx.storeCode}","${trx.storeName}","${trx.userName}","${trx.numtrx}",<c:set var="typetrx" value=""/><c:choose><c:when test="${trx.typetrx == 'VTA'}"><c:set var="typetrx" value="Venta"/></c:when><c:when test="${trx.typetrx == 'VTQ'}"><c:set var="typetrx" value="Productos"/></c:when><c:when test="${trx.typetrx == 'CTO'}"><c:set var="typetrx" value="Costo de Vta"/></c:when><c:when test="${trx.typetrx == 'CHG'}"><c:set var="typetrx" value="Cambio"/></c:when><c:when test="${trx.typetrx == 'DEV'}"><c:set var="typetrx" value="Devolucion"/></c:when><c:otherwise><c:set var="typetrx" value="NaN"/></c:otherwise></c:choose>"${typetrx}","${trx.total}",<c:if test="${trx.statustrx == 'APR' }">"Pagado"</c:if><c:if test="${trx.statustrx == 'CAN' }">"Cancelada"</c:if><c:if test="${trx.statustrx == 'NP' }">"Por pagar"</c:if>,<c:forEach var="pv" items="${trx.values}">"${pv}",</c:forEach>
</c:forEach>