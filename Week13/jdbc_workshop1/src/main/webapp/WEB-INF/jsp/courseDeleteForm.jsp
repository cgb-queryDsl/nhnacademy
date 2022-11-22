<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>강의 삭제</h2>
    <hr/>
    <a href="/course">
        <button type="button">이전으로</button>
    </a>
    <hr/>

    <form method="post" action="/course/delete">
    삭제할 강의 Id : <input type="text" name="id"> <br/>

    <br/>
    <input type="submit" value="삭제"/>
    </form>

</body>
</html>
