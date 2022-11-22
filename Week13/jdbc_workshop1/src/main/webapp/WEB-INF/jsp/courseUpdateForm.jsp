<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>강의 수정</h2>
    <hr/>
    <a href="/course">
        <button type="button">이전으로</button>
    </a>
    <hr/>

    <form method="post" action="/course/update/success">
        강의 Id : <input type="text" name="id" value="${course.id}" readonly style="background-color: darkgray"> <br/>

        강사 Id : <input type="text" name="teacherId" value="${course.teacher.id}" readonly style="background-color: darkgray"> <br/>
        강사 Name : <input type="text" name="teacherName" value="${course.teacher.name}">      <br/>

        강의 Id : <input type="text" name="subjectId" value="${course.subject.id}" readonly style="background-color: darkgray">      <br/>
        강의 Name : <input type="text" name="subjectName" value="${course.subject.name}" style="width: 600px;">  <br/>

        강의 Created : <c:out value="${course.createAt}"/>        <br/>

        <br/>
        <input type="submit" value="수정">

    </form>

</body>
</html>
