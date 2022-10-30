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

    <h2> <fmt:message key="userUpdateMsg"/> </h2>
    <hr/>

    <form method="post" action="/users/user/update/check" enctype="multipart/form-data">

        <fmt:message key="userUpdateId"/> <input type="text" name="id">
            <i> <fmt:message key="userUpdateNoticeMsg"/> </i>
        <br/>
        <fmt:message key="userUpdatePw"/> <input type="text" name="password"> <br/>
        <fmt:message key="userUpdateName"/> <input type="text" name="name"> <br/>
        <fmt:message key="userUpdateProfileFileName"/> <input type="file" name="profile"> <br/>

        <input type="submit" name=<fmt:message key="userUpdateSubmit"/>  >
    </form>

</body>
</html>
</fmt:bundle>