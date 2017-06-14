<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<h1>Events page</h1>

<ul>
    <c:forEach items="${events}" var="event">
        <li>${event}</li>
    </c:forEach>
</ul>