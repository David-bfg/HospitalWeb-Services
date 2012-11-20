var icd9diagnosticgrid;

function handleGetAllICD9Diagnostics(str)
{

	//response
	document.getElementById('xml').innerHTML=str;
	icd9diagnosticgrid.parse(document.getElementById('v_xml')); 

	icd9diagnosticgrid.makeSearch("icd9diagnostic_obj");
}


function refreshICD9Diagnostic() {
	patientClinicNum = getSelectedPatientData()[0];
	
	icd9diagnosticgrid.clearAll();
	
	Icd9Diagnostic.getAllIcd9DiagnosticXML(patientClinicNum, handleGetAllICD9Diagnostics);

	icd9diagnosticgrid.clearSelection();
}

function addICD9Diagnostic() {
	 $("#icd9diagnostic_add").dialog();
	 
	 var clinicNum = getSelectedPatientData()[0];
	 var inputs = document.getElementById('icd9diagnostic_add').getElementsByTagName('input');
	 inputs[1].value = clinicNum;
	 
	 $("#icd9diagnostic_add" ).dialog( { width: 415 } );
}


function getSelectedICD9DiagnosticData() {
	rowID = icd9diagnosticgrid.getSelectedId();
	var data = new Array();
	var i =0;
	icd9diagnosticgrid.forEachCell(rowID,function(c){
        data[i] = c.getValue();
        i++;
    });
    
	return data;
}


function editICD9Diagnostic() {
	 var data = getSelectedICD9DiagnosticData();
	 
	 var inputs = document.getElementById('icd9diagnostic_edit').getElementsByTagName('input');
	 inputs[0].value = icd9diagnosticgrid.getSelectedId();
	 inputs[2].value = getSelectedPatientData()[0];
	 inputs[1].value = data[0];
	 
	 $("#icd9diagnostic_edit").dialog();
	 $( "#icd9diagnostic_edit" ).dialog( { width: 415 } );
}

function icd9DiagnosticCommitEdit() {
	var data = new Array();
		var inputs = document.getElementById('icd9diagnostic_edit').getElementsByTagName('input');
		 var i=0;
		 for(i=0;i<inputs.length-1;i++)
		 {
			 data[i] = inputs[i].value;
		 }

		 var newData = new Array();
		 newData[0] = data[1];
		 lastEdit = newData;
		 
		 Icd9Diagnostic.modifyIcd9Diagnostic(parseInt(data[0]),data[1],parseInt(data[2]), handleModifyICD9Diagnostic);
	} 

function handleModifyICD9Diagnostic(str) {
	if(str.indexOf("<error")<0)
	{
		rowID = icd9diagnosticgrid.getSelectedId();
		var i= 0;
		icd9diagnosticgrid.forEachCell(rowID,function(c){
	        c.setValue(lastEdit[i]);
	        i++;
	    });
	    
		$( "#icd9diagnostic_edit" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function ICD9DiagnosticCommitAdd() {
	var data = new Array();
	
	var inputs = document.getElementById('icd9diagnostic_add').getElementsByTagName('input');
	 var i=0;
	 for(i=0;i<inputs.length;i++)
	 {
		 data[i] = inputs[i].value;
	 }
	 lastAdd = data;
	 
	 Icd9Diagnostic.createIcd9Diagnostic(data[0],parseInt(data[1]), handleCreateICD9Diagnostic);
}

function handleCreateICD9Diagnostic(str) {
	
	if(str.indexOf("<error")<0)
	{
	icd9diagnosticgrid.addRow(icd9diagnosticgrid.uid(),lastAdd);
	 $( "#icd9diagnostic_add" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function initICD9Diagnostic() {
	document.getElementById("icd9diagnostic").style.display="block";
	
	icd9diagnosticgrid = new dhtmlXGridObject('icd9diagnostic_gridbox');
	icd9diagnosticgrid.setImagePath("js/codebase/imgs/");
	icd9diagnosticgrid.setHeader("ICD9 Diagnostic");
	icd9diagnosticgrid.setInitWidths("*");
	icd9diagnosticgrid.setColAlign("center");
	icd9diagnosticgrid.setColTypes("txt");
	icd9diagnosticgrid.setColSorting("str");
	icd9diagnosticgrid.init();
	
	refreshICD9Diagnostic();
	
	icd9diagnosticgrid.setSkin("dhx_skyblue");
	icd9diagnosticgrid.setEditable(false);
}