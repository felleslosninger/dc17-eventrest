<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<h1>Event page</h1>

<ul>
    <c:forEach items="${events}" var="event">
        <li>${event}</li>
    </c:forEach>
</ul>