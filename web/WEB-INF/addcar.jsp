<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Car</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/add-car" method="post">

    <label for="carName">Car name :</label>
    <input type="text" name="carName" id="carName">

    <label for="carPrice">Car price :</label>
    <input type="number" min="0.0" name="carPrice" id="carPrice">

    <input type="submit" value="Add">
</form>

</body>
</html>
