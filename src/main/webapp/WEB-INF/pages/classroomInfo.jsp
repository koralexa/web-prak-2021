<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/infoPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>АУДИТОРИЯ</title>
</head>
<body>
    <c:url value="/" var="home"/>
    <a href="${home}" id="home"></a>

    <h1>Аудитория</h1>

    <label for="classroom_number" id="classroom_numberL">Номер аудитории:</label>
    <div id="classroom_number">${classroom.classroom_number}</div>
    <br>
    <label for="capacity" id="capacityL">Вместимость:</label>
    <div id="capacity">${classroom.capacity}</div>
    <br>
    <br>
    <br>
    <c:url value="/classrooms/${classroom.classroom_number}/edit" var="edit"/>
    <a href="${edit}" id="edit">Редактировать</a>
    <c:url value="/classrooms/${classroom.classroom_number}/delete" var="delete"/>
    <a href="${delete}" id="delete">Удалить</a>
</body>
</html>