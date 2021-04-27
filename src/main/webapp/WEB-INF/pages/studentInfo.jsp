<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/infoPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>СТУДЕНТ</title>
</head>
<body>
    <c:url value="/" var="home"/>
    <a href="${home}" id="home"></a>

    <h1>Студент</h1>

    <label for="student_id" id="student_idL">#:</label>
    <div id="student_id">${student.student_id}</div>
    <br>
    <label for="full_name" id="full_nameL">ФИО:</label>
    <div id="full_name">${student.full_name}</div>
    <br>
    <label for="study_year" id="study_yearL">Год обучения:</label>
    <div id="study_year">${student.study_year}</div>
    <br>
    <label for="group" id="groupL">Группа:</label>
    <div id="group">${student.group.group_number}</div>
    <br>
    <label for="stream" id="streamL">Поток:</label>
    <div id="stream">${student.group.stream.stream_number}</div>
    <br>

    <h3 id="passed_coursesL">Пройденные курсы:</h3>
    <table id="passed_courses">
        <tr>
            <th>Курс</th>
            <th>Год обучения</th>
            <th>Действия</th>
        </tr>
        <c:forEach var="course" items="${student.courses}">
            <tr>
                <td>${course.course.course_name}</td>
                <td>${course.study_year}</td>
                <td><a href="/students/${student.student_id}/delete-course/${course.passed_course_id}" id="delete_course">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="/students/${student.student_id}/add-course" id="add_course">Добавить курс</a>
    <br>
    <c:url value="/students/${student.student_id}/edit" var="edit"/>
    <a href="${edit}" id="edit">Редактировать</a>
    <c:url value="/students/${student.student_id}/delete" var="delete"/>
    <a href="${delete}" id="delete">Удалить</a>
</body>
</html>