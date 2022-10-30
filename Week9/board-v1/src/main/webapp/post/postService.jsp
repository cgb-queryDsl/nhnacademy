<%@ page import="com.nhnacademy.board.domain.PostRepository" %>
<%@ page import="com.nhnacademy.board.domain.Post" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="msg">
<%
    ServletContext context = request.getServletContext();
    String locale = (String) context.getAttribute("locale");

    PostRepository postRepository = (PostRepository) context.getAttribute("postRepository");
    List<Post> postList = postRepository.getPosts();
%>
<fmt:setLocale value= "<%= locale%>" scope="application"/>
<html>
<head>
    <title>NHN Board</title>
</head>
<body>
    <h2> NHN Board </h2>
    <hr/>
    <a href="/posts/post/registerForm.do"> <fmt:message key="postServiceRegister"/> </a>
    <hr/>

    <h4> <fmt:message key="postServiceList"/> </h4>

    <table border="1" style="border-collapse: collapse">
        <thead>
            <tr>
                <th> <fmt:message key="postServiceId"/> </th>
                <th> <fmt:message key="postServiceTitle"/> </th>
                <th> <fmt:message key="postServiceWriter"/> </th>
                <th> <fmt:message key="postServiceViewCount"/>  </th>
                <th> <fmt:message key="postServiceTime"/>  </th>
            </tr>
        </thead>
        <tbody>
            <% for(int i = 0; i < postList.size(); i++) { %>
                <tr>
                    <td> <a href="/posts/post/view.do?id=<%=postList.get(i).getId()%>"> <%=postList.get(i).getId()%>  </a> </td>
                    <td> <a href="/posts/post/view.do?id=<%=postList.get(i).getId()%>"> <%=postList.get(i).getTitle()%> </a> </td>
                    <td> <%=postList.get(i).getWriterUserId()%> </td>
                    <td> <%=postList.get(i).getViewCount()%> </td>
                    <td> <%=postList.get(i).getWriteTime()%> </td>
                </tr>
            <% } %>
        </tbody>
    </table>

    <hr/>

    <a href="http://localhost:8080"> <fmt:message key="goFirst"/> </a>

</body>
</html>
</fmt:bundle>
