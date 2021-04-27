<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/infoPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>КУРС</title>
</head>
<body>
    <c:url value="/" var="home"/>
    <a href="${home}" id="home"></a>

    <h1>Курс</h1>

    <label for="course_id" id="course_idL">#:</label>
    <div id="course_id">${course.course_id}</div>
    <br>
    <label for="course_name" id="course_nameL">Название:</label>
    <div id="course_name">${course.course_name}</div>
    <br>
    <label for="coverage" id="coverageL">Охват:</label>
    <div id="coverage">${course.coverage}</div>
    <br>
    <label for="intensity" id="intensityL">Интенсивность:</label>
    <div id="intensity">${course.intensity}</div>
    <br>
    <c:if test="${course.study_year != null}">
        <label for="study_year" id="study_yearL">Год обучения:</label>
        <div id="study_year">${course.study_year}</div>
        <br>
    </c:if>
    <label for="teacher" id="teacherL">Преподаватель:</label>
    <div id="teacher">${course.teacher.full_name}</div>
    <br>

    <h3 id="listenersL">Слушатели:</h3>
    <table id="listeners">
        <tr>
            <th>Слушатель</th>
            <th>Действия</th>
        </tr>
        <c:forEach var="listener" items="${course.listeners}">
            <tr>
                <td>${listener.listener_num}</td>
                <td><a href="/courses/${course.course_id}/delete-listener/${listener.listener_id}" id="delete_listener">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="/courses/${course.course_id}/add-listener" id="add_listener">Добавить слушателя</a>
    <br>
    <c:url value="/courses/${course.course_id}/edit" var="edit"/>
    <a href="${edit}" id="edit">Редактировать</a>
    <c:url value="/courses/${course.course_id}/delete" var="delete"/>
    <a href="${delete}" id="delete">Удалить</a>
</body>
</html>