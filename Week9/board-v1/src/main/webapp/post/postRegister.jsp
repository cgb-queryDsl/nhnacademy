<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="msg">
<%
    ServletContext context = request.getServletContext();
    String locale = (String) context.getAttribute("locale");
%>
<fmt:setLocale value= "<%= locale%>" scope="application"/>
<html>
<head>
    <title>NHN Board</title>
</head>
<body>

    <h2> <fmt:message key="postRegisterMsg"/> </h2>
    <hr/>

    <form method="post" action="/posts/post/register.do">
        <p><fmt:message key="postRegisterTitle"/></p>
        <input type="text" name="title">

        <hr/>
        <p><fmt:message key="postRegisterContent"/> </p>

        <textarea name="content" cols="70" rows="10"></textarea>
        <hr/>

        <input type="submit" value= <fmt:message key="postRegisterSubmit"/>  />

    </form>

</body>
</html>
</fmt:bundle>