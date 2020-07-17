<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
Orden,Tienda,Código,Dirección 1,Dirección 2,Código Postal,
<c:forEach var="st" items="${list}">${st.orderby},${st.name},${st.code},${st.address1}, ${st.address2},${st.postal},
</c:forEach>