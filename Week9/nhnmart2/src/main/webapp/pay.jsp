<%@ page import="com.nhnacademy.domain.BuyList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <title>NHN Mart</title>
</head>
<body>

<%
    ServletContext context = request.getServletContext();
    BuyList buyList = (BuyList) context.getAttribute("buyList");
    int totalPrice = (int) context.getAttribute("buyLIstTotalPrice");
    int money = (int) context.getAttribute("money");
%>

<h2>구매 목록</h2>

<ul>
<%
    for(BuyList.Item item : buyList.getItems()) {
%>
    <li> <%=item.getName()%> </li>
<% }
%>
</ul>

<hr/>

<h2>가격</h2>
<p>물건 총 가격 : <%= totalPrice %></p>
<hr/>

<h2>잔돈</h2>
<p>현재 잔돈 : <%= money %></p> <br/>

<a href="index.jsp"> 처음으로 </a>

</body>
</html>
