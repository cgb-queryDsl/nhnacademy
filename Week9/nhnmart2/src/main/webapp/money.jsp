<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <title>NHN Mart</title>
</head>
<body>
<h1>잔돈</h1>

<hr/>

<p>현재 잔돈 : <%= request.getServletContext().getAttribute("money") %></p>
<a href="index.jsp">처음으로</a>

</body>
</html>
