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
        String userLogin = (String) context.getAttribute("userLogin");
        String adminLogin = (String) context.getAttribute("adminLogin");

        String name = "";

        if(adminLogin.contains("on")) {
            name = adminLogin.split("-")[0];
        } else {
            name = userLogin.split("-")[0];
        }
    %>
    <fmt:setLocale value= "<%= locale%>" scope="application"/>

    <h2> <fmt:message key="loginSuccessMsg"/> </h2>

    <hr/>

    <h4> <%=name%> <fmt:message key="loginSuccessWelcomeMsg"/> </h4>
    <a href="http://localhost:8080"> <fmt:message key="goFirst"/> </a>

</body>
</html>
</fmt:bundle>