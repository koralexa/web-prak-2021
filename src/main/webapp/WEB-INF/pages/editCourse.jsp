<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/addPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>Редактировать курс</title>
</head>
<body>
    <h1>Редактировать курс</h1>
    <c:url value="/courses/${course.course_id}/edit" var="var"/>
    <form action="${var}" method="POST">
        <label for="course_name" id="courseL">Название:</label>
        <input type="text" name="course_name" id="course_name" value="${course.course_name}">
        <br>
        <label for="coverage" id="coverageL">Охват:</label>
        <select class="selectpicker" id="coverage" name="coverage">
            <c:if test="${course.coverage.equals('Потоковый')}">
                <option selected value="Потоковый">Потоковый</option>
            </c:if>
            <c:if test="${!course.coverage.equals('Потоковый')}">
                <option value="Потоковый">Потоковый</option>
            </c:if>
            <c:if test="${course.coverage.equals('Групповой')}">
                <option selected value="Групповой">Групповой</option>
            </c:if>
            <c:if test="${!course.coverage.equals('Групповой')}">
                <option value="Групповой">Групповой</option>
            </c:if>
            <c:if test="${course.coverage.equals('Спецкурс')}">
                <option selected value="Спецкурс">Спецкурс</option>
            </c:if>
            <c:if test="${!course.coverage.equals('Спецкурс')}">
                <option value="Спецкурс">Спецкурс</option>
            </c:if>
        </select>
        <br>
        <label for="intensity" id="intensityL">Интенсивность:</label>
        <input type="text" name="intensity" id="intensity" value="${course.intensity}">
        <br>
        <label for="study_year" id="study_yearL">Год обучения:</label>
        <input type="text" name="study_year" id="study_year" value="${course.study_year}">
        <br>
        <label for="teacher" id="teacherL">Преподаватель:</label>
        <select class="selectpicker" id="teacher" name="teacher_id">
            <c:forEach items="${teachers}" var="teacher">
                <c:if test="${course.teacher.teacher_id == teacher.teacher_id}">
                    <option selected value="${teacher.teacher_id}">${teacher.full_name}</option>
                </c:if>
                <c:if test="${course.teacher.teacher_id != teacher.teacher_id}">
                    <option value="${teacher.teacher_id}">${teacher.full_name}</option>
                </c:if>
            </c:forEach>
        </select>
        <br>
        <input type="submit" value="Редактировать" id="submit">
    </form>
</body>
</html>