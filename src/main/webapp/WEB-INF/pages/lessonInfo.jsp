<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/infoPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>ЗАНЯТИЕ</title>
</head>
<body>
    <c:url value="/" var="home"/>
    <a href="${home}" id="home"></a>

    <h1>Занятие</h1>

    <label for="course" id="courseL">Курс:</label>
    <div id="course">${lesson.course.course_name}</div>
    <br>
    <label for="classroom" id="classroomL">Аудитория:</label>
    <div id="classroom">${lesson.classroom.classroom_number}</div>
    <br>
    <label for="day" id="dayL">День недели:</label>
    <c:if test="${lesson.week_day == 1}">
        <div id="day">Понедельник</div>
    </c:if>
    <c:if test="${lesson.week_day == 2}">
        <div id="day">Вторник</div>
    </c:if>
    <c:if test="${lesson.week_day == 3}">
        <div id="day">Среда</div>
    </c:if>
    <c:if test="${lesson.week_day == 4}">
        <div id="day">Четверг</div>
    </c:if>
    <c:if test="${lesson.week_day == 5}">
        <div id="day">Пятница</div>
    </c:if>
    <c:if test="${lesson.week_day == 6}">
        <div id="day">Суббота</div>
    </c:if>
    <c:if test="${lesson.week_day == 7}">
        <div id="day">Воскресенье</div>
    </c:if>
    <br>
    <label for="time" id="timeL">Время:</label>
    <div id="time">${lesson.lesson_time}</div>
    <br>
    <label for="teacher" id="teacherL">Преподаватель:</label>
    <div id="teacher">${lesson.course.teacher.full_name}</div>
    <br>
    <br>
    <br>
    <c:url value="/lessons/${lesson.lesson_id}/edit" var="edit"/>
    <a href="${edit}" id="edit">Редактировать</a>
    <c:url value="/lessons/${lesson.lesson_id}/delete" var="delete"/>
    <a href="${delete}" id="delete">Удалить</a>
</body>
</html>