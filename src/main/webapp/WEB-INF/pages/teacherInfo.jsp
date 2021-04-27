<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/infoPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>ПРЕПОДАВАТЕЛЬ</title>
</head>
<body>
    <c:url value="/" var="home"/>
    <a href="${home}" id="home"></a>

    <h1>Преподаватель</h1>

    <label for="teacher_id" id="teacher_idL">#:</label>
    <div id="teacher_id">${teacher.teacher_id}</div>
    <br>
    <label for="full_name" id="full_nameL">ФИО:</label>
    <div id="full_name">${teacher.full_name}</div>
    <br>

    <h3 id="coursesL">Проводимые курсы:</h3>
    <table id="courses">
        <tr>
            <th>Название курса</th>
        </tr>
        <c:forEach var="course" items="${teacher.courses}">
            <tr>
                <td>${course.course_name}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <c:url value="/teachers/${teacher.teacher_id}/edit" var="edit"/>
    <a href="${edit}" id="edit">Редактировать</a>
    <c:url value="/teachers/${teacher.teacher_id}/delete" var="delete"/>
    <a href="${delete}" id="delete">Удалить</a>
</body>
</html>