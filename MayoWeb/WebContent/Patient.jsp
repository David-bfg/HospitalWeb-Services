<%@ include file="templates/header.jsp" %>
<style>
.headerLinks {
display:inline; 
margin:0px;
padding:0px;
font: bold 11px/1.5em Verdana;
}

.headerLinks li{
display:inline; 
padding:10px;
font: bold 11px/1.5em Verdana;
}
</style>

<script src="js/patient.js"></script>
<script src="js/search.js"></script>


<div id="top_filter_bar_new" style="float:right;margin-top:-6px">
<!-- Header for a particular Patient. This gets set via JavaScript. -->
<span id="patientHeader" style="display:none;"></span>

<!-- Header for a particular Visit. This gets set via JavaScript. -->
<span id="visitHeader" style="display:none;"></span>

</div>

  <div style="clear:both"></div>

<%@include file="includes/add.html" %>
<%@include file="includes/edit.html" %>

<div id="error" title="Error" style="display:none;width:540px; height:80px"></div>


  
<div id="patient">

<div style="float:right" id="top_bar"><div id="searchButton">Search</div></div>
<h1>Patient</h1>


<%@include file="includes/search.html" %><br />


<div id="gridboxHeader" style=" width:100%; background-color:white;">
<ul id="gridheader" class="headerLinks">
<li><a href="javascript:addPatient()"><img src="images/add.png"  border="0px" /> Add</a></li>
<li><a href="javascript:refreshPatient()"><img src="images/refresh.png"  border="0px" /> Refresh</a></li>
<li><a href="javascript:editPatient()"><img src="images/edit.png"  border="0px" /> Edit</a></li>
<li><img src="images/search.png"  border="0px" /> Search: <input type="text" id="obj" name="obj" style="height:20px;width:130px;font-size:10px" /></li>
</ul>
</div>

<div id="gridbox" style=" width:100%;height:350px; background-color:white;"></div>
</div>

<%@include file="includes/Visit.html" %>
<%@include file="includes/Tape.html" %>
<div style="clear:both"></div>
<div id="first_col" style="float:left; width:495px; padding:0px; margin:0px;padding-right:9px;" >
<%@include file="includes/ICD9Diagnostic.html" %>
<%@include file="includes/Condition.html" %>
<%@include file="includes/BillingCodes.html" %>
<%@include file="includes/Study.html" %>
<%@include file="includes/AssistiveDevice.html" %>
</div><!-- End of first_col -->


<div id="right_col" style="float:left;  padding:0px; margin:0px; width:495px">
<%@include file="includes/ICD9ProcedureCode.html" %>
<%@include file="includes/File.html" %>
<%@include file="includes/ResearchStudy.html" %>
<%@include file="includes/SystemUsed.html" %>
</div><!-- End of right_col -->

<div style="clear:both"></div>



<div id="xml" style="display:none; height:0px; width:0px"></div>  
<%@ include file="templates/footer.jsp" %>


<script src="js/condition.js"></script>
<script src="js/visit.js"></script>
<script src="js/file.js"></script>
<script src="js/tape.js"></script>
<script src="js/icd9diagnostic.js"></script>
<script src="js/icd9procedurecode.js"></script>
<script src="js/assistivedevice.js"></script>
<script src="js/systemused.js"></script>
<script src="js/study.js"></script>
<script src="js/researchstudy.js"></script>
<script src="js/billingcodes.js"></script>

<script>
initPatient();
select(0);
initShowHide();
initAutoComplete();
</script>
