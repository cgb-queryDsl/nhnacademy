<%@ page import="com.nhnacademy.board.domain.PostRepository" %>
<%@ page import="com.nhnacademy.board.domain.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="msg">
<%
    ServletContext context = request.getServletContext();
    String locale = (String) context.getAttribute("locale");
%>
<fmt:setLocale value= "<%= locale%>" scope="application"/>
<html>
<head>
    <title>NHN Board</title>
</head>
<body>
    <h2> <fmt:message key="postUpdateMsg"/> </h2>
    <hr/>
    <%
        PostRepository postRepository = (PostRepository) context.getAttribute("postRepository");
        long id = Long.parseLong(request.getAttribute("updateId").toString());

        Post post = postRepository.getPost(id);
    %>

    <form method="post" action="/posts/post/update.do?id=<%=post.getId()%>">
        <p> <fmt:message key="postUpdateTitle"/>  </p>
        <input type="text" name="title" value=<%=post.getTitle()%>>
        <hr/>
        <p> <fmt:message key="postUpdateContent"/> </p>
        <textarea name="content" cols="70" rows="10"> <%=post.getContent()%> </textarea>

        <hr/>

        <input type="submit" value=<fmt:message key="postUpdateSubmit"/> >
    </form>

</body>
</html>
</fmt:bundle>