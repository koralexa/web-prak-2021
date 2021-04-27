<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/addPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>Редактировать аудиторию</title>
</head>
<body>
<h1>Редактировать аудиторию</h1>
<c:url value="/classrooms/${classroom.classroom_number}/edit" var="var"/>
<form action="${var}" method="POST">
    <label for="capacity" id="capacityL">Вместимость:</label>
    <input type="text" name="capacity" id="capacity" value="${classroom.capacity}">
    <br>
    <input type="submit" value="Редактировать" id="submit">
</form>
</body>
</html>