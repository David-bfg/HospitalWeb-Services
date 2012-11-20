var systemusedgrid;

function handleGetAllSystemsUsed(str)
{

	//response
	document.getElementById('xml').innerHTML=str;
	systemusedgrid.parse(document.getElementById('v_xml')); 

	systemusedgrid.makeSearch("systemused_obj");
}


function refreshSystemUsed() {
	var visitNum  = getSelectedVisitData()[0];
	
	systemusedgrid.clearAll();
	
	SystemUsed.getAllSystemsUsedXML(parseInt(visitNum), handleGetAllSystemsUsed);

	systemusedgrid.clearSelection();
}

function addSystemUsed() {
	 $("#systemused_add").dialog();
	 
	 var visitNum  = getSelectedVisitData()[0];
	 var inputs = document.getElementById('systemused_add').getElementsByTagName('input');
	 inputs[1].value = visitNum;
	 
	 $( "#systemused_add" ).dialog( { width: 415 } );
}


function getSelectedSystemUsedData() {
	rowID = systemusedgrid.getSelectedId();
	var data = new Array();
	var i =0;
	systemusedgrid.forEachCell(rowID,function(c){
        data[i] = c.getValue();
        i++;
    });
    
	return data;
}


function editSystemUsed() {
	 var data = getSelectedSystemUsedData();
	 
	 var inputs = document.getElementById('systemused_edit').getElementsByTagName('input');
	 
	 inputs[0].value = data[0].replace("&nbsp;","");
	 inputs[1].value = getSelectedVisitData()[0];
	 
	 lastOldName = inputs[0].value;
	 
	 $("#systemused_edit").dialog();
	 $( "#systemused_edit" ).dialog( { width: 415 } );
}

function systemUsedCommitEdit() {
	var data = new Array();
		var inputs = document.getElementById('systemused_edit').getElementsByTagName('input');
		 var i=0;
		 data[0] = inputs[0].value;
		 data[1] = inputs[1].value;
		 
		 var newData = new Array();
		 newData[0] = data[0];
		 
		 lastEdit = newData;
		 
		 SystemUsed.modifySystemUsed(lastOldName, data[0], parseInt(data[1]),handleModifySystemUsed);
	} 

function handleModifySystemUsed(str) {
	if(str.indexOf("<error")<0)
	{
		rowID = systemusedgrid.getSelectedId();
		var i= 0;
		systemusedgrid.forEachCell(rowID,function(c){
	        c.setValue(lastEdit[i]);
	        i++;
	    });
	    
		$( "#systemused_edit" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function systemUsedCommitAdd() {
	var data = new Array();
	
	var inputs = document.getElementById('systemused_add').getElementsByTagName('input');
	 var i=0;
	 
	 data[0] = inputs[0].value;
	 data[1] = inputs[1].value;
	 
	 var newData = new Array();
	 newData[0] = data[0];
	 lastAdd = newData;
	 
	 SystemUsed.createSystemUsed(data[0],parseInt(data[1]), handleCreateSystemUsed);
}

function handleCreateSystemUsed(str) {
	
	if(str.indexOf("<error")<0)
	{
	systemusedgrid.addRow(systemusedgrid.uid(),lastAdd);
	 $( "#systemused_add" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function initSystemUsed() {
	document.getElementById("systemused").style.display="block";
	
	systemusedgrid = new dhtmlXGridObject('systemused_gridbox');
	systemusedgrid.setImagePath("js/codebase/imgs/");
	systemusedgrid.setHeader("name");
	systemusedgrid.setInitWidths("*");
	systemusedgrid.setColAlign("center");
	systemusedgrid.setColTypes("txt");
	systemusedgrid.setColSorting("str");
	systemusedgrid.init();
	
	refreshSystemUsed();
	
	systemusedgrid.setSkin("dhx_skyblue");
	systemusedgrid.setEditable(false);
}