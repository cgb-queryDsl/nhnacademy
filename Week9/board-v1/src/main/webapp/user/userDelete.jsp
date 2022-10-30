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

    <h2> <fmt:message key="userDeleteMsg"/> </h2>

    <hr/>

    <form method="post" action="/users/user/delete/check.do">
        <fmt:message key="userDeleteId"/> <input type="text" name="id"> <br/>

        <input type="submit" name=<fmt:message key="userDeleteSubmit"/> >
    </form>


</body>
</html>
</fmt:bundle>