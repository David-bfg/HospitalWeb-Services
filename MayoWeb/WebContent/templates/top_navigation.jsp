<div id="top_tabs">
<%@page import="com.mayo.transform.MyTransformer" %>
<%@page import="com.mayo.transform.Files" %>

<%
out.println(MyTransformer.getInstance().styleDocument(Files.getInstance().TOP_LINKS_XML, Files.getInstance().LINKS_XSL));
%>

</div>

<script>
function select(index)
{
var li = document.getElementById('top_tabs').getElementsByTagName('li');
li[index].className="active";
}

function deselect(index)
{
var li = document.getElementById('top_tabs').getElementsByTagName('li');
li[index].className="none";
}
</script>