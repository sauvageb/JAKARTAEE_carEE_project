<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Car List</title>
</head>
<body>

<c:forEach items="${carList}" var="car">

    <p>${car.name}</p>
    <p>${car.price}</p>
    <a href="${pageContext.request.contextPath}/details-car?id=${car.id}">Show Details</a>

</c:forEach>
</body>
</html>
