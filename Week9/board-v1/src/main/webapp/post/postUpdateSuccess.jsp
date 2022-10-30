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
    <h2> <fmt:message key="postUpdateSuccessMsg"/> </h2>
    <hr/>
    <a href="/posts/post.do"> <fmt:message key="postRegisterGoList"/>  </a>

</body>
</html>
</fmt:bundle>