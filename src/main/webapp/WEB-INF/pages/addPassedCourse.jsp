<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/addPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>Добавить курс</title>
</head>
<body>
    <h1>Добавить пройденный курс</h1>
    <c:url value="/students/${student.student_id}/add-course" var="var"/>
    <form action="${var}" method="POST">
        <input type="hidden" name="student_id" id="student_id" value="${student.student_id}">
        <br>
        <label for="course" id="courseL">Название:</label>
        <select class="selectpicker" id="course" name="course_id">
            <option disabled selected value> -- выберите курс -- </option>
            <c:forEach items="${courses}" var="course">
                <option value="${course.course_id}">${course.course_name}</option>
            </c:forEach>
        </select>
        <br>
        <label for="study_year" id="study_yearL">Год обучения:</label>
        <input type="text" name="study_year" id="study_year">
        <br>
        <input type="submit" value="Добавить" id="submit">
    </form>
</body>
</html>