<%@ page import="com.nhnacademy.domain.FoodStand" %>
<%@ page import="com.nhnacademy.domain.Food" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<head>
    <title>NHN Mart</title>
</head>

    <h2>상품 목록</h2>

    <a href="/logout.do" > 로그아웃</a>   <br/>
    <a href="/init.do"> 초기화 </a>       <br/>

    <hr/>

    <form method="post" action="/pay.do">

    <%
        ServletContext context = request.getServletContext();
        FoodStand foods = (FoodStand) context.getAttribute("foodStand");

        for(Food food : foods.getFoods()) {
    %>
        <input type="checkbox" name="<%=food.getName()%>" value="<%=food.getName()%>-<%=food.getPrice()%>"/>  <%= food.getName() %>

        <br/>
    <%
    }
    %>
        <br/>

        <input type="submit" name="제출">
    </form>

</body>
</html>
