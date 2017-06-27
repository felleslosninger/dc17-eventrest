<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<h1>Add new employee</h1>

<form:form modelAttribute="form">
    <form:errors path="" element="div" />
    <div>
        <form:label path="year">year</form:label>
        <form:input path="year" />
        <form:errors path="year" />
    </div>
    <div>
        <input type="submit" />
    </div>
</form:form>
</body>
</html>