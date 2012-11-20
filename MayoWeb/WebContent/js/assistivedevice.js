
var assistivedevicegrid;
var lastOldName;

function handleGetAllAssistiveDevices(str)
{

	//response
	document.getElementById('xml').innerHTML=str;
	assistivedevicegrid.parse(document.getElementById('v_xml')); 

	assistivedevicegrid.makeSearch("assistivedevice_obj");
}


function refreshAssistiveDevice() {
	patientClinicNum  = getSelectedPatientData()[0];
	
	assistivedevicegrid.clearAll();
	
	AssistiveDevice.getAllAssistiveDevicesXML(patientClinicNum, handleGetAllAssistiveDevices);

	assistivedevicegrid.clearSelection();
}

function addAssistiveDevice() {
	 $("#assistivedevice_add").dialog();
	 
	 var clinicNum = getSelectedPatientData()[0];
	 var inputs = document.getElementById('assistivedevice_add').getElementsByTagName('input');
	 inputs[0].value = clinicNum;
	 
	 $( "#assistivedevice_add" ).dialog( { width: 415 } );
}


function getSelectedAssistiveDeviceData() {
	rowID = assistivedevicegrid.getSelectedId();
	var data = new Array();
	var i =0;
	assistivedevicegrid.forEachCell(rowID,function(c){
        data[i] = c.getValue();
        i++;
    });
    
	return data;
}


function editAssistiveDevice() {
	 var data = getSelectedAssistiveDeviceData();
	 
	 var inputs = document.getElementById('assistivedevice_edit').getElementsByTagName('input');
	 var i=0;
	 	 inputs[0].value = getSelectedPatientData()[0];
		 inputs[1].value = data[0].replace("&nbsp;","");
	 
		 lastOldName = inputs[1].value;
		 
	 $("#assistivedevice_edit").dialog();
	 $("#assistivedevice_edit" ).dialog( { width: 415 } );
}

function assistiveDeviceCommitEdit() {
	var data = new Array();
		var inputs = document.getElementById('assistivedevice_edit').getElementsByTagName('input');
		 var i=0;
		 for(i=0;i<2;i++)
		 {
			 data[i] = inputs[i].value;
		 }
		 data[2] = lastOldName;
		 
		 var newData = new Array();
		 newData[0] = data[1];
		 lastEdit = newData;
		 AssistiveDevice.modifyAssistiveDevice(data[2],data[1], data[0],handleModifyAssistiveDevice);
	} 

function handleModifyAssistiveDevice(str) {
	if(str.indexOf("<error")<0)
	{
		rowID = assistivedevicegrid.getSelectedId();
		var i= 0;
		assistivedevicegrid.forEachCell(rowID,function(c){
	        c.setValue(lastEdit[i]);
	        i++;
	    });
	    
		$( "#assistivedevice_edit" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function assistiveDeviceCommitAdd() {
	var data = new Array();
	var newData = new Array();
	var inputs = document.getElementById('assistivedevice_add').getElementsByTagName('input');
	 var i=0;
	 for(i=0;i<2;i++)
	 {
		 data[i] = inputs[i].value;
	 }
	 newData[0] = data[1];
	 lastAdd = newData;
	 
	 AssistiveDevice.createAssistiveDevice(data[1], parseInt(data[0]), handleCreateAssistiveDevice);
}

function handleCreateAssistiveDevice(str) {
	
	if(str.indexOf("<error")<0)
	{
	assistivedevicegrid.addRow(assistivedevicegrid.uid(),lastAdd);
	 $( "#assistivedevice_add" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function initAssistiveDevice() {
	document.getElementById("assistivedevice").style.display="block";
	
	assistivedevicegrid = new dhtmlXGridObject('assistivedevice_gridbox');
	assistivedevicegrid.setImagePath("js/codebase/imgs/");
	assistivedevicegrid.setHeader("name");
	assistivedevicegrid.setInitWidths("*");
	assistivedevicegrid.setColAlign("center");
	assistivedevicegrid.setColTypes("txt");
	assistivedevicegrid.setColSorting("str");
	assistivedevicegrid.init();
	
	refreshAssistiveDevice();
	
	assistivedevicegrid.setSkin("dhx_skyblue");
	assistivedevicegrid.setEditable(false);
}