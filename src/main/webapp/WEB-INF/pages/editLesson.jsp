<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/addPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>Редактировать занятие</title>
</head>
<body>
<h1>Редактировать занятие</h1>
<c:url value="/lessons/${lesson.lesson_id}/edit" var="var"/>
<form action="${var}" method="POST">
    <label for="course_id" id="course_idL">Курс:</label>
    <select class="selectpicker" id="course_id" name="course_id">
        <option selected value>${lesson.course.course_name}</option>
        <c:forEach items="${courses}" var="course">
            <c:if test="${course.course_id != lesson.course.course_id}">
                <option value="${course.course_id}">${course.course_name}</option>
            </c:if>
        </c:forEach>
    </select>
    <br>
    <label for="classroom_id" id="classroom_idL">Аудитория:</label>
    <select class="selectpicker" id="classroom_id" name="classroom_id">
        <option selected value>${lesson.classroom.classroom_number}</option>
        <c:forEach items="${classrooms}" var="classroom">
            <c:if test="${classroom.classroom_number != lesson.classroom.classroom_number}">
                <option value="${classroom.classroom_number}">${classroom.classroom_number}</option>
            </c:if>
        </c:forEach>
    </select>
    <br>
    <label for="day" id="dayL">День недели:</label>
    <select class="selectpicker" id="day" name="day">
        <c:if test="${lesson.week_day != 1}">
            <option value="1">Понедельник</option>
        </c:if>
        <c:if test="${lesson.week_day == 1}">
            <option selected value="1">Понедельник</option>
        </c:if>
        <c:if test="${lesson.week_day != 2}">
            <option value="2">Вторник</option>
        </c:if>
        <c:if test="${lesson.week_day == 2}">
            <option selected value="2">Вторник</option>
        </c:if>
        <c:if test="${lesson.week_day != 3}">
            <option value="3">Среда</option>
        </c:if>
        <c:if test="${lesson.week_day == 3}">
            <option selected value="3">Среда</option>
        </c:if>
        <c:if test="${lesson.week_day != 4}">
            <option value="4">Четверг</option>
        </c:if>
        <c:if test="${lesson.week_day == 4}">
            <option selected value="4">Четверг</option>
        </c:if>
        <c:if test="${lesson.week_day != 5}">
            <option value="5">Пятница</option>
        </c:if>
        <c:if test="${lesson.week_day == 5}">
            <option selected value="5">Пятница</option>
        </c:if>
        <c:if test="${lesson.week_day != 6}">
            <option value="6">Суббота</option>
        </c:if>
        <c:if test="${lesson.week_day == 6}">
            <option selected value="6">Суббота</option>
        </c:if>
        <c:if test="${lesson.week_day != 7}">
            <option value="7">Воскресенье</option>
        </c:if>
        <c:if test="${lesson.week_day == 7}">
            <option selected value="7">Воскресенье</option>
        </c:if>
    </select>
    <br>
    <label for="time" id="timeL">Время:</label>
    <input type="text" name="time" id="time" value="${lesson.lesson_time}">
    <br>
    <input type="submit" value="Редактировать" id="submit">
</form>
</body>
</html>