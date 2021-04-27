<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/addPage.css"/>" rel="stylesheet" type="text/css"/>
    <title>Добавить слушателя</title>
</head>
<body>
<h1>Добавить слушателя</h1>
<c:url value="/courses/${course.course_id}/add-listener" var="var"/>
<form action="${var}" method="POST">
    <label for="listener" id="listenerL">Слушатель:</label>
    <select class="selectpicker" id="listener" name="listener_num">
        <option disabled selected value> -- выберите слушателя -- </option>
        <c:forEach items="${streams}" var="stream">
            <option value="${stream.stream_number}">${stream.stream_number}</option>
        </c:forEach>
        <c:forEach items="${groups}" var="group">
            <option value="${group.group_number}">${group.group_number}</option>
        </c:forEach>
        <c:forEach items="${students}" var="student">
            <option value="${student.student_id}">${student.full_name}</option>
        </c:forEach>
    </select>
    <br>
    <input type="submit" value="Добавить" id="submit">
</form>
</body>
</html>