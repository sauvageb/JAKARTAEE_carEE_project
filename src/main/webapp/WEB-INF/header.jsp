<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav>
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/list-car">List Car</a>
        </li>

        <c:if test="${! empty sessionScope.user}">
            <li>
                <a href="/add-car">Add Car</a>
            </li>
        </c:if>

        <li>
            <a href="#">Contact</a>
        </li>
    </ul>
</nav>
