<%@ page import="com.nhnacademy.board.domain.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="msg">
<%
    ServletContext context = request.getServletContext();
    String locale = (String) context.getAttribute("locale");

    Post post = (Post) request.getAttribute("post");
    post.increaseViewCount();
%>
<fmt:setLocale value= "<%= locale%>" scope="application"/>
<html>
<head>
    <title>NHN Board</title>
</head>
<body>

    <h2> <%=post.getId()%> - <fmt:message key="postView"/>  </h2>

    <hr/>

    <table border="1" style="border-collapse: collapse">
        <tr>
            <th> <fmt:message key="postViewId"/> </th>
            <th> <fmt:message key="postViewTitle"/> </th>
            <th> <fmt:message key="postViewWriter"/> </th>
            <th> <fmt:message key="postViewCount"/> </th>
            <th> <fmt:message key="postViewTime"/> </th>
        </tr>

        <tr>
            <td> <%=post.getId()%> </td>
            <td> <%=post.getTitle()%> </td>
            <td> <%=post.getWriterUserId()%> </td>
            <td> <%=post.getViewCount()%> </td>
            <td> <%=post.getWriteTime()%> </td>
        </tr>
    </table>

    <hr/>

    <h4> <fmt:message key="postViewContent"/> </h4>
    <p> <%=post.getContent()%> </p>

    <hr/>

    <ul>
        <li> <a href="/posts/post.do"> <fmt:message key="postRegisterGoList"/> </a> </li>

        <%-- 본인이 작성한 게시글이면 수정 삭제 링크가 나오도록 --%>
        <%
            String userLogin = (String) context.getAttribute("userLogin");
            String user = userLogin.split("-")[0];

            if(user.equals(post.getWriterUserId())) {
        %>
            <li> <a href="/posts/post/updateForm.do?updateId=<%=post.getId()%>"> <fmt:message key="postViewUpdate"/> </a> </li>
            <li> <a href="/posts/post/delete.do?id=<%=post.getId()%>"> <fmt:message key="postViewDelete"/> </a> </li>
        <%
        }
        %>
    </ul>

</body>
</html>
</fmt:bundle>