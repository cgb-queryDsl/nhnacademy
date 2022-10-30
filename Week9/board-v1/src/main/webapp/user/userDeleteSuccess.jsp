<%@ page import="com.nhnacademy.board.domain.User" %>
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

    <%
        User removeUser = (User) request.getAttribute("removeUser");
    %>

    <h2> <fmt:message key="userDeleteSuccessMsg"/> </h2>

    <hr/>

    <ul>
        <li> <fmt:message key="userDeleteSuccessId"/> : <%= removeUser.getId() %>  </li>
        <li> <fmt:message key="userDeleteSuccessPw"/> : <%= removeUser.getPassword() %>  </li>
        <li> <fmt:message key="userDeleteSuccessName"/> : <%= removeUser.getName() %>  </li>
        <li> <fmt:message key="userDeleteSuccessProfileName"/> : <%= removeUser.getProfileFileName() %>  </li>
    </ul>

    <br/>
    <a href="http://localhost:8080"> <fmt:message key="goFirst"/> </a>

</body>
</html>
</fmt:bundle>