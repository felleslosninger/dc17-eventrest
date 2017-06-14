<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<h1>Add new employee</h1>

<form:form modelAttribute="form">
    <form:errors path="" element="div" />
    <div>
        <form:label path="issuer">Name</form:label>
        <form:input path="issuer" />
        <form:errors path="issuer" />
    </div>
    <div>
        <input type="submit" />
    </div>
</form:form>
</body>
</html>
