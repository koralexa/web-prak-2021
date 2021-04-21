<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link href="<c:url value="/style/home.css"/>" rel="stylesheet" type="text/css"/>
        <title>РАСПИСАНИЕ</title>
    </head>
    <body>
        <h1>ДОМАШНЯЯ СТРАНИЦА</h1>

        <c:url value="/" var="home"/>
        <a href="${home}" id="homeIcon"></a>

        <div id="container">
            <c:url value="/lessons" var="lessons"/>
            <a href="${lessons}">Расписание</a>
            <br>
            <c:url value="/courses" var="courses"/>
            <a href="${courses}">Курсы</a>
            <br>
            <c:url value="/teachers" var="teachers"/>
            <a href="${teachers}">Преподаватели</a>
            <br>
            <c:url value="/students" var="students"/>
            <a href="${students}">Студенты</a>
            <br>
            <c:url value="/classrooms" var="classrooms"/>
            <a href="${classrooms}">Аудитории</a>
        </div>
    </body>
</html>