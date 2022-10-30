<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="msg">
<html>
<head>
    <title>NHN Board</title>
</head>
<body>
    <h2> <fmt:message key="noAuthorityMsg"/> </h2>
    <hr/>
    <a href="http://localhost:8080"> <fmt:message key="goFirst"/> </a>
</body>
</html>
</fmt:bundle>