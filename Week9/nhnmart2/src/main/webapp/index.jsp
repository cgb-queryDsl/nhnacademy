<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
  <head>
    <title>NHN Mart</title>
  </head>
  <body>

  <%
    ServletContext context = request.getServletContext();
    String loginStatus = (String) context.getAttribute("loginStatus");
  %>
  
  <h1>환영합니다. NHN 마트 입니다.</h1>

  <ul>
    <% if(loginStatus.equals("off")) { %>
      <li> 로그인 : <a href="/login.do"> /login</a> </li>
  <%}
  %>
<%--    <li> 로그인 : <a href="/login.do"> /login</a> </li>--%>
    <li> 초기화 :  <a href="/init.do"> /init</a>   </li>
    <li> 음식 리스트 :  <a href="/foods.do"> /foods</a> </li>
    <li> 장바구니 :  <a href="/pay.do"> /pay </a>   </li>
    <li> 잔돈 : <a href="/money.do"> /money </a> </li>
  </ul>

  <hr/>

  <h1><del>언어 설정</del></h1>
  <ul>
    <li> <del>한국어 : ko</del> </li>
    <li> <del>영어 : en</del> </li>
  </ul>
  <p>다국어 처리는 구현 못했습니다.</p>

  </body>
</html>
