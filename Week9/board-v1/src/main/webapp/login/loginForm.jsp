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

    <h2> <fmt:message key="loginMsg"/> </h2>
    <hr/>

    <form method="post" action="/login/check.do">
        <fmt:message key="loginId"/> <input type="text" name="id">          <br/>
        <fmt:message key="loginPw"/> <input type="text" name="password">    <br/>

        <input type="submit" name= <fmt:message key="loginSubmit"/>  >
    </form>

    <a href="http://localhost:8080"> <fmt:message key="goFirst"/> </a>

</body>
</html>
</fmt:bundle>
