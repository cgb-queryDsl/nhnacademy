<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    for (int i = 2; i < 10; i++) {
%>
<%--<%= i %>단 시작 <Br/>--%>
<%
        for(int j = 1; j < 10; j++) {
%>
<%= i %> * <%= j %> = <%= i * j %> <br/>
<%
        }
    }
%>

</body>
</html>
