<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/tablePage.css"/>" rel="stylesheet" type="text/css"/>
    <title>РАСПИСАНИЕ</title>
</head>
<body>
    <c:url value="/" var="home"/>
    <a href="${home}" id="home"></a>

    <h1>Расписание</h1>

    <c:url value="/lessons" var="var"/>
    <form action="${var}" method="POST">
        <label for="teacher" id="teacherL">Преподаватель:</label>
        <select class="selectpicker" id="teacher" name="teacher_id">
            <option disabled selected value> -- выберите преподавателя -- </option>
            <c:forEach items="${teachers}" var="teacher">
                <option value="${teacher.teacher_id}">${teacher.full_name}</option>
            </c:forEach>
        </select>
        <label for="student" id="studentL">Студент:</label>
        <select class="selectpicker" id="student" name="student_id">
            <option disabled selected value> -- выберите студента -- </option>
            <c:forEach items="${students}" var="student">
                <option value="${student.student_id}">${student.full_name} (${student.group.group_number})</option>
            </c:forEach>
        </select>
        <label for="classroom" id="classroomL">Аудитория:</label>
        <select class="selectpicker" id="classroom" name="classroom_id">
            <option disabled selected value> -- выберите аудиторию -- </option>
            <c:forEach items="${classrooms}" var="classroom">
                <option value="${classroom.classroom_number}">${classroom.classroom_number}</option>
            </c:forEach>
        </select>
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
        <input type="submit" value="Применить" id="submit">
    </form>
    <table>
        <tr>
            <th>Время</th>
            <th>День недели</th>
            <th>Курс</th>
            <th>Аудитория</th>
            <th>Преподаватель</th>
        </tr>
        <c:forEach var="lesson" items="${lessonsList}">
            <tr>
                <td><a href="/lessons/${lesson.lesson_id}">${lesson.lesson_time}</a></td>
                <c:if test="${lesson.week_day == 1}">
                    <td>Понедельник</td>
                </c:if>
                <c:if test="${lesson.week_day == 2}">
                    <td>Вторник</td>
                </c:if>
                <c:if test="${lesson.week_day == 3}">
                    <td>Среда</td>
                </c:if>
                <c:if test="${lesson.week_day == 4}">
                    <td>Четверг</td>
                </c:if>
                <c:if test="${lesson.week_day == 5}">
                    <td>Пятница</td>
                </c:if>
                <c:if test="${lesson.week_day == 6}">
                    <td>Суббота</td>
                </c:if>
                <c:if test="${lesson.week_day == 7}">
                    <td>Воскресенье</td>
                </c:if>
                <td>${lesson.course.course_name}</td>
                <td>${lesson.classroom.classroom_number}</td>
                <td>${lesson.course.teacher.full_name}</td>
            </tr>
        </c:forEach>
    </table>

    <c:url value="/lessons/addLesson" var="add"/>
    <a href="${add}" id="add">Добавить</a>
</body>
</html>