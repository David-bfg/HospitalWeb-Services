var conditiongrid;

function handleGetAllConditions(str)
{

	//response
	document.getElementById('xml').innerHTML=str;
	conditiongrid.parse(document.getElementById('v_xml')); 

	conditiongrid.makeSearch("condition_obj");
}


function refreshCondition() {
	patientClinicNum  = getSelectedPatientData()[0];
	
	conditiongrid.clearAll();
	
	Condition.getAllConditionsXML(patientClinicNum, handleGetAllConditions);

	conditiongrid.clearSelection();
}

function addCondition() {
	 $("#condition_add").dialog();
	 
	 var clinicNum = getSelectedPatientData()[0];
	 var inputs = document.getElementById('condition_add').getElementsByTagName('input');
	 inputs[0].value = clinicNum;
	 
	 $( "#condition_add" ).dialog( { width: 415 } );
}


function getSelectedConditionData() {
	rowID = conditiongrid.getSelectedId();
	var data = new Array();
	var i =0;
	conditiongrid.forEachCell(rowID,function(c){
        data[i] = c.getValue();
        i++;
    });
    
	return data;
}


function editCondition() {
	 var data = getSelectedConditionData();
	 
	 var inputs = document.getElementById('condition_edit').getElementsByTagName('input');
	 
	 inputs[0].value = getSelectedPatientData()[0];
	 inputs[1].value = data[0].replace("&nbsp;","");
	 
	 
	 
	 lastOldName = inputs[1].value;
	 
	 $("#condition_edit").dialog();
	 $( "#condition_edit" ).dialog( { width: 415 } );
}

function conditionCommitEdit() {
	var data = new Array();
		var inputs = document.getElementById('condition_edit').getElementsByTagName('input');
		 var i=0;
		 for(i=0;i<2;i++)
		 {
			 data[i] = inputs[i].value;
		 }
		 data[2] = lastOldName;
		 
		 var newData = new Array();
		 newData[0] = data[1];
		 lastEdit = newData;
		 Condition.modifyCondition(data[2],data[1], data[0],handleModifyCondition);
	}

function handleModifyCondition(str) {
	if(str.indexOf("<error")<0)
	{
		rowID = conditiongrid.getSelectedId();
		var i= 0;
		conditiongrid.forEachCell(rowID,function(c){
	        c.setValue(lastEdit[i]);
	        i++;
	    });
	    
		$( "#condition_edit" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function conditionCommitAdd() {
	var data = new Array();
	
	var inputs = document.getElementById('condition_add').getElementsByTagName('input');
	 var i=0;
	 for(i=0;i<2;i++)
	 {
		 data[i] = inputs[i].value;
	 }
	 var newData = new Array();
	 newData[0] = data[1];
	 lastAdd = newData;
	 
	 Condition.createCondition(data[1], data[0], handleCreateCondition);
}

function handleCreateCondition(str) {
	
	if(str.indexOf("<error")<0)
	{
	conditiongrid.addRow(conditiongrid.uid(),lastAdd);
	 $( "#condition_add" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function initCondition() {
	document.getElementById("condition").style.display="block";
	
	conditiongrid = new dhtmlXGridObject('condition_gridbox');
	conditiongrid.setImagePath("js/codebase/imgs/");
	conditiongrid.setHeader("Name");
	conditiongrid.setInitWidths("*");
	conditiongrid.setColAlign("center");
	conditiongrid.setColTypes("txt");
	conditiongrid.setColSorting("data");
	conditiongrid.init();
	
	refreshCondition();
	
	conditiongrid.setSkin("dhx_skyblue");
	conditiongrid.setEditable(false);
}