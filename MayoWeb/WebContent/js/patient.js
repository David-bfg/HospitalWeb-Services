var lastAdd;
var lastEdit;

function addPatient() {
	 $("#patient_add").dialog();
	 $("#patient_add").dialog( { width: 415 } );
}

function editPatient() {
	 var data = getSelectedPatientData();
	
	 var inputs = document.getElementById('patient_edit').getElementsByTagName('input');
	 var i=0;
	 for(i=0;i<7;i++)
	 {
		 inputs[i].value = data[i].replace("&nbsp;","");
	 }
	 
	 var selects = document.getElementById('patient_edit').getElementsByTagName('select');
	 selects[0].value = data[7];
	 selects[1].value = data[8];
	 selects[2].value = data[9];
	 selects[3].value = data[10];
			
	 
	 var textareas = document.getElementById('patient_edit').getElementsByTagName('textarea');
	 inputs[7].value = data[11];
	 textareas[0].value = data[12];
	 
	 $("#patient_edit").dialog();
	 $("#patient_edit" ).dialog( { width: 415 } );
	 
}

function getSelectedPatientData() {
	rowID = patientgrid.getSelectedId();
	var data = new Array();
	var i =0;
	patientgrid.forEachCell(rowID,function(c){
        data[i] = c.getValue();
        i++;
    });
    
	return data;
}

function deleteIt() {
	var data = getSelectedPatientData();
	Patient.deletePatient(parseInt(data[0]), handleDeletePatient);
}

function refreshPatient() {
	patientgrid.clearAll();
	Patient.getAllPatientXML(handleGetAllPatientData);
	patientgrid.clearSelection();
}


function filter()
{
	
}


//////////////////////////////////////////////////////////DWR
function handleGetAllPatientData(str) {
	document.getElementById('xml').innerHTML=str;
	
	patientgrid.parse(document.getElementById('p_xml')); 

	patientgrid.makeSearch("obj");
}

function handleDeletePatient(str) {
patientgrid.deleteSelectedRows();
}

function handleCreatePatient(str) {
	
	if(str.indexOf("<error")<0)
	{
	patientgrid.addRow(patientgrid.uid(),lastAdd);
	 $( "#patient_add" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function handleModifyPatient(str) {
	if(str.indexOf("<error")<0)
	{
		rowID = patientgrid.getSelectedId();
		var i= 0;
		patientgrid.forEachCell(rowID,function(c){
	        c.setValue(lastEdit[i]);
	        i++;
	    });
	    
		$( "#patient_edit" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function commitAdd() {
	var data = new Array();
	
	var inputs = document.getElementById('patient_add').getElementsByTagName('input');
	 var i=0;
	 for(i=0;i<7;i++)
	 {
		 data[i] = inputs[i].value;
	 }

	 var selects = document.getElementById('patient_add').getElementsByTagName('select');
	 data[7] = selects[0].value;
	 data[8] = selects[1].value;
	 data[9] = selects[2].value;
	 data[10] = selects[3].value;
	 
	 data[11] = inputs[7].value;
	 
	 var textareas = document.getElementById('patient_add').getElementsByTagName('textarea');
	 data[12] = textareas[0].value;
	 
	 
	 lastAdd = data;
	 
	 Patient.createPatient(data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],
			 data[9],data[10],data[11],data[12],
			 handleCreatePatient);
}

function commitEdit() {
var data = new Array();
	
	var inputs = document.getElementById('patient_edit').getElementsByTagName('input');
	 var i=0;
	 for(i=0;i<7;i++)
	 {
		 data[i] = inputs[i].value;
	 }

	 var selects = document.getElementById('patient_edit').getElementsByTagName('select');
	 data[7] = selects[0].value;
	 data[8] = selects[1].value;
	 data[9] = selects[2].value;
	 data[10] = selects[3].value;
	 
	 data[11] = inputs[7].value;
	 
	 var textareas = document.getElementById('patient_edit').getElementsByTagName('textarea');
	 data[12] = textareas[0].value;
	 
	 lastEdit = data;
	 alert(data);
	 Patient.modifyPatient(parseInt(data[0]),data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],
			 data[9],data[10],data[11],data[12],
			 handleModifyPatient);
}

function handlePatientDoubleClick() {
	//Hide all tables
	hideAll();
	
	//Set the Patient Header
	patientClinicNum  = getSelectedPatientData()[0];
	patientLastName = getSelectedPatientData()[1];
	patientFirstName = getSelectedPatientData()[2];
	setPatientHeader(patientClinicNum, patientLastName, patientFirstName);
	
	//Display all tables corresponding to the selected patient
	initVisit();
	initCondition();
	initAssistiveDevice();
	initFile();
	initTape();
	initICD9Diagnostic();
	initICD9ProcedureCode();
}

function initPatient() {
	patientgrid = new dhtmlXGridObject('gridbox');
	patientgrid.setImagePath("js/codebase/imgs/");
	patientgrid.setHeader("Clinic #, Last Name, First Name, Date of Birth, Gender, Height, Weight, Side, Extremity, Involved, Dominant,Measured Side, Problem Descriptor");
	patientgrid.setInitWidths("60px,70px,70px,60px,50px,50px,60px,40px,60px,60px,60px,60px,*");
	patientgrid.setColAlign("center,center,center,center,center,center,center,center,center,center,center,center,center");
	patientgrid.setColTypes("ed,ed,txt,txt,txt,txt,txt,txt,txt,txt,txt,txt,txt");
	patientgrid.setColSorting("int,str,str,date,str,int,int,str,str,str,str,str,str");
	patientgrid.init();
	refreshPatient();
	patientgrid.attachEvent("onRowDblClicked", handlePatientDoubleClick);  

	patientgrid.setSkin("dhx_skyblue");
	patientgrid.setEditable(false);
}

function clearPatient() {
	hideAll();
	document.getElementById("patient").style.display="block";
	document.getElementById("patientHeader").innerHTML = "";
	document.getElementById("visitHeader").innerHTML = "";
}

function setPatientHeader(clinicNum, lastName, firstName) {
	var str ="#"+clinicNum + ": " + lastName + ", " + firstName;
	document.getElementById("patientHeader").innerHTML = "<a class=\"button\" href=\"javascript:clearPatient()\"><span>"+str+"</span></a>";
	document.getElementById("patientHeader").style.display="";
}

function hidePatientHeader() {
	document.getElementById("patientHeader").style.display="none";
}
