<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/tablePage.css"/>" rel="stylesheet" type="text/css"/>
    <title>АУДИТОРИИ</title>
</head>
<body>
<c:url value="/" var="home"/>
<a href="${home}" id="home"></a>

<h1>Аудитории</h1>

<c:url value="/classrooms" var="var"/>
<form action="${var}" method="POST">
    <label for="min" id="minL">Минимальная вместимость:</label>
    <input type="text" name="min" id="min">
    <label for="max" id="maxL">Максимальная вместимость:</label>
    <input type="text" name="max" id="max">
    <input type="submit" value="Применить" id="submit">
</form>
<table>
    <tr>
        <th>Номер аудитории</th>
        <th>Вместимость</th>
    </tr>
    <c:forEach var="classroom" items="${classroomsList}">
        <tr>
            <td><a href="/classrooms/${classroom.classroom_number}">${classroom.classroom_number}</a></td>
            <td>${classroom.capacity}</td>
        </tr>
    </c:forEach>
</table>

<c:url value="/classrooms/addClassroom" var="add"/>
<a href="${add}" id="add">Добавить</a>
</body>
</html>