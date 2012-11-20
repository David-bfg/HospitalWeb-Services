<div id="left_tabs">
<%@page import="com.mayo.transform.MyTransformer" %>
<%@page import="com.mayo.transform.Files" %>

<%
out.println(MyTransformer.getInstance().styleDocument(Files.getInstance().LINKS_XML, Files.getInstance().LINKS_XSL));
%>

</div>
  <!-- <ul>
    <li><a href="Patient.jsp"><span>Patient</span></a></li>
    <li><a href="#"><span>Visit</span></a></li>
    <li><a href="test.jsp"><span>Test</span></a></li>
  </ul>
   -->