<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h2>강의 등록</h2>
    <hr/>
    <a href="/course">
        <button type="button">이전으로</button>
    </a>
    <hr/>

    <form method="post" action="/course/create">
        새롭게 추가할 강사 이름 : <input type="text" name="teacherName"> <br/>
        새롭게 추가할 강의 이름 : <input type="text" name="subjectName"> <br/>

        <br/>
        <input type="submit" value="등록"/>
    </form>


</body>
</html>
