<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/addPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>Редактировать преподавателя</title>
</head>
<body>
<h1>Редактировать преподавателя</h1>
<c:url value="/teachers/${teacher.teacher_id}/edit" var="var"/>
<form action="${var}" method="POST">
    <label for="full_name" id="teacherL">ФИО:</label>
    <input type="text" name="full_name" id="full_name" value="${teacher.full_name}">
    <br>
    <input type="submit" value="Редактировать" id="submit">
</form>
</body>
</html>