var icd9procedurecodegrid;

function handleGetAllICD9ProcedureCodes(str)
{

	//response
	document.getElementById('xml').innerHTML=str;
	icd9procedurecodegrid.parse(document.getElementById('v_xml')); 

	icd9procedurecodegrid.makeSearch("icd9procedurecode_obj");
}


function refreshICD9ProcedureCode() {
	patientClinicNum  = getSelectedPatientData()[0];
	
	icd9procedurecodegrid.clearAll();
	
	Icd9ProcedureCode.getAllIcd9ProcedureCodesXML(patientClinicNum, handleGetAllICD9ProcedureCodes);

	icd9procedurecodegrid.clearSelection();
}

function addICD9ProcedureCode() {
	 $("#icd9procedurecode_add").dialog();
	 
	 var clinicNum = getSelectedPatientData()[0];
	 var inputs = document.getElementById('icd9procedurecode_add').getElementsByTagName('input');
	 inputs[0].value = clinicNum;
	 
	 $( "#icd9procedurecode_add" ).dialog( { width: 415 } );
}


function getSelectedICD9ProcedureCodeData() {
	rowID = icd9procedurecodegrid.getSelectedId();
	var data = new Array();
	var i =0;
	icd9procedurecodegrid.forEachCell(rowID,function(c){
        data[i] = c.getValue();
        i++;
    });
    
	return data;
}


function editICD9ProcedureCode() {
	 var data = getSelectedICD9ProcedureCodeData();
	 var inputs = document.getElementById('icd9procedurecode_edit').getElementsByTagName('input');
	 var i=0;
	
	 inputs[0].value = icd9procedurecodegrid.getSelectedId();
	 inputs[2].value = data[0].replace("&nbsp;","");
	 inputs[3].value = data[1].replace("&nbsp;","");
	 inputs[1].value =getSelectedPatientData()[0];
	 
	 $("#icd9procedurecode_edit").dialog();
	 $( "#icd9procedurecode_edit" ).dialog( { width: 415 } );
}

function icd9ProcedureCodeCommitEdit() {
	var data = new Array();
		var inputs = document.getElementById('icd9procedurecode_edit').getElementsByTagName('input');
		 var i=0;
		 for(i=0;i<4;i++)
		 {
			 data[i] = inputs[i].value;
		 }
		 var newData = new Array();
		 newData[0] = data[2];
		 newData[1] = data[3];
		 
		 lastEdit = newData;
		 
		 Icd9ProcedureCode.modifyIcd9ProcedureCode(parseInt(data[0]), data[2],parseInt(data[1]),data[3],
				 handleModifyICD9ProcedureCode);
	}

function handleModifyICD9ProcedureCode(str) {
	if(str.indexOf("<error")<0)
	{
		rowID = icd9procedurecodegrid.getSelectedId();
		var i= 0;
		icd9procedurecodegrid.forEachCell(rowID,function(c){
	        c.setValue(lastEdit[i]);
	        i++;
	    });
	    
		$( "#icd9procedurecode_edit" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function icd9ProcedureCodeCommitAdd() {
	var data = new Array();
	
	var inputs = document.getElementById('icd9procedurecode_add').getElementsByTagName('input');
	 var i=0;
	 for(i=0;i<3;i++)
	 {
		 data[i] = inputs[i].value;
	 }
	 var newData = new Array();
	 newData[0] = data[1];
	 newData[1] = data[2];
	 lastAdd = newData;
	 
	 Icd9ProcedureCode.createIcd9ProcedureCode(data[1],parseInt(data[0]),data[2],
			 handleCreateICD9ProcedureCode);
	 }

function handleCreateICD9ProcedureCode(str) {
	
	if(str.indexOf("<error")<0)
	{
	icd9procedurecodegrid.addRow(icd9procedurecodegrid.uid(),lastAdd);
	 $( "#icd9procedurecode_add" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function initICD9ProcedureCode() {
	document.getElementById("icd9procedurecode").style.display="block";
	
	icd9procedurecodegrid = new dhtmlXGridObject('icd9procedurecode_gridbox');
	icd9procedurecodegrid.setImagePath("js/codebase/imgs/");
	icd9procedurecodegrid.setHeader("icd9procedurecode,Procedure Descriptor");
	icd9procedurecodegrid.setInitWidths("*, *");
	icd9procedurecodegrid.setColAlign("center,center");
	icd9procedurecodegrid.setColTypes("txt,txt");
	icd9procedurecodegrid.setColSorting("str,str");
	icd9procedurecodegrid.init();
	
	refreshICD9ProcedureCode();
	
	icd9procedurecodegrid.setSkin("dhx_skyblue");
	icd9procedurecodegrid.setEditable(false);
}