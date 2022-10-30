<%@ page import="com.nhnacademy.board.domain.UserRepository" %>
<%@ page import="com.nhnacademy.board.domain.User" %>
<%@ page import="java.util.Map" %>
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
        UserRepository repository = (UserRepository) context.getAttribute("repository");
        Map<String, User> users = repository.getUsers();
    %>
    <fmt:setLocale value= "<%= locale%>" scope="application"/>

    <h2> <fmt:message key="userReadMsg"/> </h2>

    <hr/>

    <table border="1" style="border-collapse: collapse" >
        <tr>
            <th> ID </th>
            <th> PW </th>
            <th> Name </th>
            <th> Profile Image </th>
        </tr>

        <% for(String key: users.keySet()) { %>
        <tr>
            <th> <%=users.get(key).getId()%> </th>
            <th> <%=users.get(key).getPassword()%> </th>
            <th> <%=users.get(key).getName()%> </th>
            <th> <%=users.get(key).getProfileFileName()%> </th>
        </tr>
        <%
        }
        %>

    </table>

    <br/>

    <a href="http://localhost:8080"> <fmt:message key="goFirst"/> </a>

</body>
</html>
</fmt:bundle>
