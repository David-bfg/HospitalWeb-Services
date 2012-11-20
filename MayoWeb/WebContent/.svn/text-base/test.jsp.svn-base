<%@ include file="templates/header.jsp" %>
<style>
#gridheader {
display:inline; 
margin:0px;
padding:0px;
}
#gridheader li{
display:inline; 
padding:10px;
}
</style>

<script>
var rowID = -1;
function add() {
	 $("#add").dialog();
	 $( "#add" ).dialog( { width: 415 } );
	//patientgrid.addRow(patientgrid.uid(), [ind1, ind2], 1);
}

function edit() {
	 $("#edit").dialog();
	 $( "#edit" ).dialog( { width: 415 } );
}

function deleteIt() {
	rowID = rowID-1;
	if(rowID!=-1)
		patientgrid.deleteRow(patientgrid.getRowId(rowID));
	rowID=-1;
}

function doOnRowSelected(r) {
	rowID = r;
}
</script>

<div id="add" title="Add" style="display:none">
<form method="post">
<table width="400px" id="form">
	<tr>
		<td width="150px">Clinic Number</td>
		<td><input type="text" name="clinicnumber" /></td>
	</tr>

	<tr>
		<td>Lastname</td>
		<td><input type="text" name="lastname" /></td>
	</tr>
	<tr>
		<td>Firstname</td>
		<td><input type="text" name="firstname" /></td>
	</tr>

	<tr>
		<td>Date of Birth</td>
		<td><input type="text" name="dob" /></td>
	</tr>

	<tr>
		<td>Gender</td>
		<td><input type="text" name="gender" /></td>
	</tr>

	<tr>
		<td>Height</td>
		<td><input type="text" name="height" /></td>
	</tr>

	<tr>
		<td>Weight</td>
		<td><input type="text" name="weight" /></td>
	</tr>

	<tr>
		<td>Side</td>
		<td><select name="side">
			<option value="K">Left</option>
			<option value="R">Right</option>
			<option value="B">Bilateral</option>
		</select></td>
	</tr>

	<tr>
		<td>Extremity</td>
		<td><select name="extrimity">
			<option value="LE">Lower Extrimity</option>
			<option value="UE">Upper Extrimity</option>
		</select></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="button" name="submit" class="submit"
			value="Submit" /></td>
	</tr>
</table>
</form>
</div>


<div id="edit" title="Edit" style="display:none">
<form method="post">
<table width="400px" id="form">
	<tr>
		<td width="150px">Clinic Number</td>
		<td><input type="text" name="clinicnumber" /></td>
	</tr>

	<tr>
		<td>Lastname</td>
		<td><input type="text" name="lastname" /></td>
	</tr>
	<tr>
		<td>Firstname</td>
		<td><input type="text" name="firstname" /></td>
	</tr>

	<tr>
		<td>Date of Birth</td>
		<td><input type="text" name="dob" /></td>
	</tr>

	<tr>
		<td>Gender</td>
		<td><input type="text" name="gender" /></td>
	</tr>

	<tr>
		<td>Height</td>
		<td><input type="text" name="height" /></td>
	</tr>

	<tr>
		<td>Weight</td>
		<td><input type="text" name="weight" /></td>
	</tr>

	<tr>
		<td>Side</td>
		<td><select name="side">
			<option value="K">Left</option>
			<option value="R">Right</option>
			<option value="B">Bilateral</option>
		</select></td>
	</tr>

	<tr>
		<td>Extremity</td>
		<td><select name="extrimity">
			<option value="LE">Lower Extrimity</option>
			<option value="UE">Upper Extrimity</option>
		</select></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="button" name="submit" class="submit"
			value="Submit" /></td>
	</tr>
</table>
</form>
</div>

<h1>Patient</h1>
<div id="gridboxHeader" style=" width:800px; background-color:white;">
<ul id="gridheader">
<li><a href="javascript:add()">Add</a></li>
<li><a href="javascript:edit()">Edit</a></li>
<li><a href="javascript:deleteIt()">Delete</a></li></ul>
</div>
<div id="gridbox" style=" width:800px;height:270px; background-color:white;"></div>
<br>
<br/>
<a href="javascript:void(0)" onClick="patientgrid.clearAll();patientgrid.parse(document.getElementById('isl_xml'));">Reload from XML island</a><br/>

<xml id="isl_xml" style="display:none;"><rows>
   <row id="1">
  <cell>0</cell>
  <cell>loaded from xml island</cell>
   </row>
   <row id="2">
  <cell>1</cell>
 <cell>loaded from xml island</cell>
   </row>
   <row id="3" selected="1">
  <cell>2</cell>
  <cell>loaded from xml island</cell>
   </row>
   <row id="4">
  <cell>3</cell>
 <cell>loaded from xml island</cell>
   </row>
   <row id="5">
  <cell>4</cell>
 <cell>loaded from xml island</cell>
   </row>
   <row id="6">
  <cell>5</cell>
  <cell>loaded from xml island</cell>
   </row>
   <row id="7">
  <cell>6</cell>
 <cell>loaded from xml island</cell>
   </row>
   <row id="8">
  <cell>7</cell>
  <cell>loaded from xml island</cell>
   </row>
   <row id="9">
  <cell>8</cell>
  <cell>loaded from xml island</cell>
   </row>
   <row id="10">
  <cell>9</cell>
  <cell>loaded from xml island</cell>
   </row>
   <row id="11">
  <cell>10</cell>
  <cell>loaded from xml island</cell>
   </row>
</rows></xml>
 
<script>
patientgrid = new dhtmlXGridObject('gridbox');
patientgrid.setImagePath("js/codebase/imgs/");
patientgrid.setHeader("Clinic #, Lastname, Firstname, Date of Birth, Gender, Height, Weight, Side, Extremity");
patientgrid.setInitWidths("40,*,*,*,*,*,*,*,*");
patientgrid.setColAlign("right,right");
patientgrid.setColTypes("ed,ed,txt,txt,txt,txt,txt,txt,txt");
patientgrid.setColSorting("int,str,str,date,str,str,str,str,str");
patientgrid.attachEvent("onRowSelect", doOnRowSelected);
patientgrid.init();
patientgrid.setSkin("dhx_skyblue");
patientgrid.load("data.xml", "xml");
patientgrid.setEditable(false);
</script>

  
  </script>
  
<%@ include file="templates/footer.jsp" %>
