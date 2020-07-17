<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>"Fecha", ${dates}
"Total de Productos",${ metrics.productsQty }
"Total de Ventas",${ metrics.sale }
"Merma",${ metrics.decrease }
"Utilidad",${ metrics.utility }
"Total de Transacciones",${ metrics.notrx }
"Ticket Promedio",${ metrics.ticketavg }
 
"Código","Cliente","Productos","Ventas","Merma","Utilidad","Transacciones","Ticket Promedio","Visitas Programadas","Checkins","Vendedor","Ruta","Categoría"
<c:forEach items="${ list }" var="i">"${i.code }","${i.store }","${i.noproducts }","${i.sale }","${i.decrease }","${i.utility }","${i.notrx }","${i.ticketavg }","${i.notravels }","${i.checkins }","${i.driver }","${i.route }","${i.store_category}"
</c:forEach>