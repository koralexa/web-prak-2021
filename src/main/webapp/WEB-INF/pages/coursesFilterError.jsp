<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/errorPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>Ошибка</title>
</head>
<body>
    <h2>Недопустимые значения фильтров</h2>
    <c:url value="/courses" var="back"/>
    <a href="${back}">Назад</a>
</body>
</html>