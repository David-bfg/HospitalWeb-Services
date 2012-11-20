var tapegrid;

function handleGetAllTapes(str)
{

	//response
	document.getElementById('xml').innerHTML=str;
	tapegrid.parse(document.getElementById('v_xml')); 

	tapegrid.makeSearch("tape_obj");
}


function refreshTape() {
	var clinicNum  = getSelectedPatientData()[0];
	
	tapegrid.clearAll();
	
	Tape.getAllTapesXML(clinicNum, handleGetAllTapes);

	tapegrid.clearSelection();
}

function addTape() {
	 $("#tape_add").dialog();
	 
	 var clinicNum = getSelectedPatientData()[0];
	 
	  var inputs = document.getElementById('tape_add').getElementsByTagName('input');
	 inputs[0].value = clinicNum;
	 
	 $( "#tape_add" ).dialog( { width: 415 } );
}


function getTapeSelectedData() {
	rowID = tapegrid.getSelectedId();
	var data = new Array();
	var i =0;
	tapegrid.forEachCell(rowID,function(c){
        data[i] = c.getValue();
        i++;
    });
    
	return data;
}


function editTape() {
	 var data = getTapeSelectedData();
	 
	 var inputs = document.getElementById('tape_edit').getElementsByTagName('input');
	 var i=0;
	 for(i=0;i<4;i++)
	 {
		 inputs[i+1].value = data[i].replace("&nbsp;","");
	 }
	 
	 inputs[0].value = tapegrid.getSelectedId();
	 
	 var selects = document.getElementById('tape_edit').getElementsByTagName('select');
	 selects[0].value = data[4];
	 
	 $("#tape_edit").dialog();
	 $( "#tape_edit" ).dialog( { width: 415 } );
}

function tapeCommitEdit() {
	var data = new Array();
	var newData = new Array();
		var inputs = document.getElementById('tape_edit').getElementsByTagName('input');
		 var i=0;
		 for(i=0;i<4;i++)
		 {
			 data[i] = inputs[i+1].value;
		 }
		 
		 var selects = document.getElementById('tape_edit').getElementsByTagName('select');
		data[4] = selects[0].value;
		 lastEdit = data;
		 
		 var id = inputs[0].value;
		 
		 Tape.modifyTape(parseInt(id),parseInt(data[0]), parseInt(data[1]), parseInt(data[2]), parseInt(data[3]),
				 parseInt(data[4]),
				 handleModifyTape);
	}

function handleModifyTape(str) {
	if(str.indexOf("<error")<0)
	{
		rowID = tapegrid.getSelectedId();
		var i= 0;
		tapegrid.forEachCell(rowID,function(c){
	        c.setValue(lastEdit[i]);
	        i++;
	    });
	    
		$( "#tape_edit" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function tapeCommitAdd() {
	var data = new Array();
	
	var inputs = document.getElementById('tape_add').getElementsByTagName('input');
	 var i=0;
	 for(i=0;i<4;i++)
	 {
		 data[i] = inputs[i].value;
	 }
	 lastAdd = data;
	 var selects = document.getElementById('tape_add').getElementsByTagName('select');
		
	 data[4] = selects[0].value;
	 
	 Tape.createTape(parseInt(data[0]), parseInt(data[1]), parseInt(data[2]), parseInt(data[3]),
			 parseInt(data[4]),	 handleCreateTape);
}

function handleCreateTape(str) {
	
	if(str.indexOf("<error")<0)
	{
	tapegrid.addRow(tapegrid.uid(),lastAdd);
	 $( "#tape_add" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function initTape() {
	document.getElementById("tape").style.display="block";
	
	tapegrid = new dhtmlXGridObject('tape_gridbox');
	tapegrid.setImagePath("js/codebase/imgs/");
	tapegrid.setHeader("Clinic #, Visit ID, Tape Number, Study ID, Backup");
	tapegrid.setInitWidths("*,*,*,*, *");
	tapegrid.setColAlign("center,center,center,center, center");
	tapegrid.setColTypes("txt,txt,txt,txt,txt");
	tapegrid.setColSorting("int,int,int,int,int");
	tapegrid.init();
	
	refreshTape();
	
	tapegrid.setSkin("dhx_skyblue");
	tapegrid.setEditable(false);
}