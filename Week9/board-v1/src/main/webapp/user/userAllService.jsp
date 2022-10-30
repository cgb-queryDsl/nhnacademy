<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="msg">
<html>
<head>
    <title>NHN Board</title>
</head>
<body>

    <%
        ServletContext context = request.getServletContext();
        String locale = (String) context.getAttribute("locale");
    %>
    <fmt:setLocale value= "<%= locale%>" scope="application"/>

    <h2> <fmt:message key="userServiceMsg"/> </h2>

    <hr/>

    <ul>
        <li> <a href="/users/user/create.do"/> <fmt:message key="createUser"/> </li>
        <li> <a href="/users/user/update.do"/> <fmt:message key="updateUser"/> </li>
        <li> <a href="/users/user/delete.do"/> <fmt:message key="deleteUser"/> </li>
        <li> <a href="/users/user/get.do"/> <fmt:message key="getUser"/> </li>
    </ul>

    <hr/>
    <a href="http://localhost:8080"> <fmt:message key="goFirst"/> </a>

</body>
</html>
</fmt:bundle>