<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Car Details</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<p>${car.name}</p>
<p>${car.price}</p>

<jsp:include page="footer.jsp"/>
</body>
</html>
