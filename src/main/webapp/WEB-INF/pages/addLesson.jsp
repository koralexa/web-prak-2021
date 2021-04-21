<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/addPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>Добавить занятие</title>
</head>
<body>
    <h1>Добавить занятие</h1>
    <c:url value="/lessons/addLesson" var="var"/>
    <form action="${var}" method="POST">
        <label for="course_id" id="course_idL">Курс:</label>
        <select class="selectpicker" id="course_id" name="course_id">
            <option disabled selected value> -- выберите курс -- </option>
            <c:forEach items="${courses}" var="course">
                <option value="${course.course_id}">${course.course_name}</option>
            </c:forEach>
        </select>
        <br>
        <label for="classroom_id" id="classroom_idL">Аудитория:</label>
        <select class="selectpicker" id="classroom_id" name="classroom_id">
            <option disabled selected value> -- выберите аудиторию -- </option>
            <c:forEach items="${classrooms}" var="classroom">
                <option value="${classroom.classroom_number}">${classroom.classroom_number}</option>
            </c:forEach>
        </select>
        <br>
        <label for="day" id="dayL">День недели:</label>
        <select class="selectpicker" id="day" name="day">
            <option disabled selected value> -- выберите день недели -- </option>
            <option value="1">Понедельник</option>
            <option value="2">Вторник</option>
            <option value="3">Среда</option>
            <option value="4">Четверг</option>
            <option value="5">Пятница</option>
            <option value="6">Суббота</option>
            <option value="7">Воскресенье</option>
        </select>
        <br>
        <label for="time" id="timeL">Время:</label>
        <input type="text" name="time" id="time">
        <br>
        <input type="submit" value="Добавить" id="submit">
    </form>
</body>
</html>