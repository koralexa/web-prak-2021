<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/addPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>Добавить курс</title>
</head>
<body>
    <h1>Добавить курс</h1>
    <c:url value="/courses/addCourse" var="var"/>
    <form action="${var}" method="POST">
        <label for="course_name" id="courseL">Название:</label>
        <input type="text" name="course_name" id="course_name">
        <br>
        <label for="coverage" id="coverageL">Охват:</label>
        <select class="selectpicker" id="coverage" name="coverage">
            <option disabled selected value> -- выберите охват -- </option>
            <option value="Потоковый">Потоковый</option>
            <option value="Групповой">Групповой</option>
            <option value="Спецкурс">Спецкурс</option>
        </select>
        <br>
        <label for="intensity" id="intensityL">Интенсивность:</label>
        <input type="text" name="intensity" id="intensity">
        <br>
        <label for="study_year" id="study_yearL">Год обучения:</label>
        <input type="text" name="study_year" id="study_year">
        <br>
        <label for="teacher" id="teacherL">Преподаватель:</label>
        <select class="selectpicker" id="teacher" name="teacher_id">
            <option disabled selected value> -- выберите преподавателя -- </option>
            <c:forEach items="${teachers}" var="teacher">
                <option value="${teacher.teacher_id}">${teacher.full_name}</option>
            </c:forEach>
        </select>
        <br>
        <input type="submit" value="Добавить" id="submit">
    </form>
</body>
</html>