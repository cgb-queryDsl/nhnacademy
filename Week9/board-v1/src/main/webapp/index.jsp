<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="msg">
<%
  ServletContext context = request.getServletContext();
  String locale = (String) context.getAttribute("locale");
  String adminLogin = (String) context.getAttribute("adminLogin");
  String userLogin = (String) context.getAttribute("userLogin");

  String name = "";

  if(adminLogin.contains("on")) {
    name = adminLogin.split("-")[0];
  } else {
    name = userLogin.split("-")[0];
  }
%>
<fmt:setLocale value= "<%= locale%>" scope="application"/>
<html>
  <head>
    <title>NHN Board</title>
  </head>

  <body>

  <h2> <fmt:message key="welcomeMsg"/> </h2>
  <hr/>

  <h3> <fmt:message key="login"/> </h3>
  <ul>
    <% if(adminLogin.contains("on") || userLogin.contains("on")) {  %>
      <p>  <%=name%>  <fmt:message key="loginStatus"/> </p>
      <li> <a href="/logout.do"> <fmt:message key="logout"/> </a> </li>
    <%
    } else {
    %>
      <li> <a href="/login.do"> <fmt:message key="login"/> </a> </li>
    <%
      }
    %>
  </ul>

  <hr/>

  <h3> <fmt:message key="service"/> </h3>
  <ul>
    <li> <a href="/users/user.do"> <fmt:message key="user"/> </a> </li>
    <li> <a href="/posts/post.do"> <fmt:message key="post"/> </a> </li>
  </ul>

  <hr/>
  <h3> <fmt:message key="visitCount"/> </h3>
  <p>  </p>


  <hr/>

  <h3><fmt:message key="setLocaleMsg"/> </h3>
  <p> <fmt:message key="refresh"/> </p>
  <ul>
    <li>  <a href="/ko"> <fmt:message key="setKo"/> </a>  </li>
    <li>  <a href="/en"> <fmt:message key="setEn"/> </a> </li>
  </ul>

  </body>
</html>
</fmt:bundle>