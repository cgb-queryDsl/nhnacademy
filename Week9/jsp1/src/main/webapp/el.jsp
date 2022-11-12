<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%
  pageContext.setAttribute("array", new String[] { "this", "is", "how", "we", "do", "it" });

  List<Integer> list = new ArrayList<>();
  list.add(1);
  list.add(3);
  list.add(5);

  pageContext.setAttribute("list", list);

  Map<String, String> map1 = new HashMap<>();
  map1.put("Mariah Carey", "Without you");
  map1.put("Whitney Houston", "I will always love you");
  map1.put("Celine Dion", "My heart will go on");

  pageContext.setAttribute("map1", map1);

  Map<String, Integer> map2 = new HashMap<>();
  map2.put("j", 1);
  map2.put("s", 2);
  map2.put("p", 7);

  pageContext.setAttribute("map2", map2);
%>
array=${array[2]}<br />
list=${list[1]}<br />
map1=${map1['Whitney Houston']}<br />
map2=${map2.p}<br />