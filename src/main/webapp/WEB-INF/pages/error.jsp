<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/errorPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>Ошибка</title>
</head>
<body>
    <h2>${error}</h2>
    <a href="${back}" id="back">Назад</a>
</body>
</html>