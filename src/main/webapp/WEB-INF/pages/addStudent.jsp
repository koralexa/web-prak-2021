<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/addPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>Добавить студента</title>
</head>
<body>
    <h1>Добавить студента</h1>
    <c:url value="/students/addStudent" var="var"/>
    <form action="${var}" method="POST">
        <label for="full_name" id="nameL">ФИО:</label>
        <input type="text" name="full_name" id="full_name">
        <br>
        <label for="study_year" id="yearL">Год обучения:</label>
        <input type="text" name="study_year" id="study_year">
        <br>
        <label for="group_number" id="groupL">Группа:</label>
        <input type="text" name="group_number" id="group_number">
        <br>
        <input type="submit" value="Добавить" id="submit">
    </form>
</body>
</html>