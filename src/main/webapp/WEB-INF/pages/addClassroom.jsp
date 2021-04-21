<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/addPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>Добавить аудиторию</title>
</head>
<body>
    <h1>Добавить аудиторию</h1>
    <c:url value="/classrooms/addClassroom" var="var"/>
    <form action="${var}" method="POST">
        <label for="classroom_number" id="numberL">Номер аудитории:</label>
        <input type="text" name="classroom_number" id="classroom_number">
        <br>
        <label for="capacity" id="capacityL">Вместимость:</label>
        <input type="text" name="capacity" id="capacity">
        <br>
        <input type="submit" value="Добавить" id="submit">
    </form>
    </body>
</html>