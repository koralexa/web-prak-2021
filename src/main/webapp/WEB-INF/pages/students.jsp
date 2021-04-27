<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/tablePage.css"/>" rel="stylesheet" type="text/css"/>
    <title>СТУДЕНТЫ</title>
</head>
<body>
    <c:url value="/" var="home"/>
    <a href="${home}" id="home"></a>

    <h1>Студенты</h1>

    <c:url value="/students" var="var"/>
    <form action="${var}" method="POST">
        <label for="group_number" id="groupL">Группа:</label>
        <input type="text" name="group_number" id="group_number">
        <label for="stream_number" id="streamL">Поток:</label>
        <input type="text" name="stream_number" id="stream_number">
        <label for="study_year" id="yearL">Год обучения:</label>
        <input type="text" name="study_year" id="study_year">
        <input type="submit" value="Применить" id="submit">
    </form>
    <table>
        <tr>
            <th>#</th>
            <th>ФИО</th>
            <th>Год обучения</th>
            <th>Поток</th>
            <th>Группа</th>
        </tr>
        <c:forEach var="student" items="${studentsList}">
            <tr>
                <td>${student.student_id}</td>
                <td><a href="/students/${student.student_id}">${student.full_name}</a></td>
                <td>${student.study_year}</td>
                <td>${student.group.stream.stream_number}</td>
                <td>${student.group.group_number}</td>
            </tr>
        </c:forEach>
    </table>

    <c:url value="/students/addStudent" var="add"/>
    <a href="${add}" id="add">Добавить</a>
</body>
</html>