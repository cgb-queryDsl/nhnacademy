<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h1>로그인</h1>
    <hr/>

    <form method="post" action="/login">
        ID : <input type="text" name="username"/>  <br/>
        PW : <input type="password" name="pwd"/> <br/>

        <input type="submit" value="로그인"/>
    </form>
</body>
</html>
