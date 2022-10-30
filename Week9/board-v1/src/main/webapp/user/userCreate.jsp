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

    <h2> <fmt:message key="userService"/> </h2>
    <hr/>

    <form method="post" action="/users/user/create/success" enctype="multipart/form-data">

        <fmt:message key="userCreateId"/> <input type="text" name="id"> <br/>
        <fmt:message key="userCreatePw"/> <input type="text" name="password"> <br/>
        <fmt:message key="userCreateName"/> <input type="text" name="name"> <br/>
        <fmt:message key="userCreateProfileFileName"/> <input type="file" name="profile"> <br/>

        <input type="submit" name=<fmt:message key="userCreateSubmit"/>  >
    </form>

</body>
</html>
</fmt:bundle>
