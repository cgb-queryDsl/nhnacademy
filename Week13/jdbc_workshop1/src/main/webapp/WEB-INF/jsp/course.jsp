<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>로그인 ON</h2>
    <hr/>

    <h4>강의 목록</h4>

    <c:forEach var="course" items="${allCourseList}">
        강의 ID : <c:out value="${course.id}" /> <br/>
        강사 이름 : <c:out value="${course.teacher.name}" />     <br/>
        강의 이름 : <c:out value="${course.subject.name}" />     <br/>
        생성 시간 : <c:out value="${course.createAt}" />         <br/>

        <br/>
    </c:forEach>

    <hr/>

    <ul>
        <li>
            <a href="/course/create">
                <button type="button"> 강의 등록 </button>
            </a>
        </li>

        <li>
            <form method="post" action="/course/delete">
                삭제할 강의 ID : <input type="text" name="id">
                <button type="submit"> 강의 삭제 </button>
            </form>
        </li>

        <li>
            <form method="post" action="/course/update">
                수정할 강의 ID : <input type="text" name="id">
                <button type="submit"> 강의 수정 </button>
            </form>
        </li>

    </ul>


</body>
</html>
