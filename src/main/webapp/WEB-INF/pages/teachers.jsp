<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/tablePage.css"/>" rel="stylesheet" type="text/css"/>
    <title>ПРЕПОДАВАТЕЛИ</title>
</head>
<body>
    <c:url value="/" var="home"/>
    <a href="${home}" id="home"></a>

    <h1>Преподаватели</h1>

    <table>
        <tr>
            <th>#</th>
            <th>ФИО</th>
        </tr>
        <c:forEach var="teacher" items="${teachersList}">
            <tr>
                <td>${teacher.teacher_id}</td>
                <td>${teacher.full_name}</td>
            </tr>
        </c:forEach>
    </table>

    <c:url value="/teachers/addTeacher" var="add"/>
    <a href="${add}" id="add">Добавить</a>
</body>
</html>