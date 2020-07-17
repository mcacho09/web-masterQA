<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>"Métricas de venta"
"Productos",${metrics.productsQty }
"Merma", ${metrics.decrease }
"Utilidad", ${metrics.utility }
"No de trx", ${metrics.notrx }
"Ticket promedio", ${metrics.ticketavg }
"Ventas", ${metrics.sale }
<c:forEach var="i" items="${categorysales}"> ,${i.category },${i.sale }
</c:forEach>
 
"Fecha","Hora","Nombre","Num Trx","Usuario","Venta Qty","Venta Mto","Cambio","Devolucion","Estado",
<c:forEach var="rpt" items="${list}" >"<fmt:formatDate pattern="dd/MM/yyyy" value="${rpt.invoice}"/>","<fmt:formatDate value="${rpt.invoice }" pattern="HH:mm:ss"/>","${rpt.name}","${rpt.trx_num}","${rpt.username}","${rpt.qty_vta}","${rpt.qty_vta_sug}","${rpt.qty_chg}","${rpt.qty_dev}",<c:if test="${rpt.status == 'APR' }">"Pagado"</c:if><c:if test="${rpt.status == 'CAN' }">"Cancelada"</c:if><c:if test="${rpt.status == 'NP' }">"Por pagar"</c:if>,
</c:forEach>
