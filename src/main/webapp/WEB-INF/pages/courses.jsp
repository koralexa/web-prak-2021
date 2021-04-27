<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/tablePage.css"/>" rel="stylesheet" type="text/css"/>
    <title>КУРСЫ</title>
</head>
<body>
    <c:url value="/" var="home"/>
    <a href="${home}" id="home"></a>

    <h1>Курсы</h1>

    <c:url value="/courses" var="var"/>
    <form action="${var}" method="POST">
        <label for="teacher" id="teacherL">Преподаватель:</label>
        <select class="selectpicker" id="teacher" name="teacher_id">
            <option disabled selected value> -- выберите преподавателя -- </option>
            <c:forEach items="${teachers}" var="teacher">
                <option value="${teacher.teacher_id}">${teacher.full_name}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Применить" id="submit">
    </form>
    <table>
        <tr>
            <th>#</th>
            <th>Название</th>
            <th>Охват</th>
            <th>Занятий в неделю</th>
            <th>Год обучения</th>
            <th>Преподаватель</th>
        </tr>
        <c:forEach var="course" items="${coursesList}">
            <tr>
                <td>${course.course_id}</td>
                <td><a href="/courses/${course.course_id}">${course.course_name}</a></td>
                <td>${course.coverage}</td>
                <td>${course.intensity}</td>
                <td>${course.study_year}</td>
                <td>${course.teacher.full_name}</td>
            </tr>
        </c:forEach>
    </table>

    <c:url value="/courses/addCourse" var="add"/>
    <a href="${add}" id="add">Добавить</a>
</body>
</html>