var filegrid;

function handleGetAllFiles(str) {

	// response
	document.getElementById('xml').innerHTML = str;
	filegrid.parse(document.getElementById('v_xml'));

	filegrid.makeSearch("file_obj");
}

function refreshFile() {
	patientClinicNum = getSelectedPatientData()[0];

	filegrid.clearAll();

	File.getAllFilesXML(patientClinicNum, handleGetAllFiles);

	filegrid.clearSelection();
}

function addFile() {
	$("#file_add").dialog();

	var clinicNum = getSelectedPatientData()[0];
	var inputs = document.getElementById('file_add').getElementsByTagName(
			'input');
	inputs[0].value = clinicNum;
	
	$("#file_add").dialog( {
		width : 415
	});
}

function getSelectedfileData() {
	rowID = filegrid.getSelectedId();
	var data = new Array();
	var i = 0;
	filegrid.forEachCell(rowID, function(c) {
		data[i] = c.getValue();
		i++;
	});

	return data;
}

function editFile() {
	var data = getSelectedfileData();

	var inputs = document.getElementById('file_edit').getElementsByTagName(
			'input');
	var i = 0;

	 inputs[0].value = filegrid.getSelectedId();
	 inputs[1].value = getSelectedPatientData()[0];
	 inputs[2].value = data[0].replace("&nbsp;","");
	 
	
	$("#file_edit").dialog();
	$("#file_edit").dialog( {
		width : 415
	});
}

function filecommitEdit() {
	var data = new Array();
	var inputs = document.getElementById('file_edit').getElementsByTagName('input');
	 var i=0;
	 for(i=0;i<3;i++)
	 {
		 data[i] = inputs[i].value;
	 }
	 
	 
	 var newData = new Array();
	 newData[0] = data[2];
	 lastEdit = newData;
	 
	 File.modifyFile(parseInt(data[0]),data[2], parseInt(data[1]),handleModifyFile);
}

function handleModifyFile(str) {
	if (str.indexOf("<error") < 0) {
		rowID = filegrid.getSelectedId();
		var i = 0;
		filegrid.forEachCell(rowID, function(c) {
			c.setValue(lastEdit[i]);
			i++;
		});

		$("#file_edit").dialog("close");
	} else {
		document.getElementById('error').innerHTML = str;
		$("#error").dialog();
	}
}

function filecommitAdd() {
	var data = new Array();

	var inputs = document.getElementById('file_add').getElementsByTagName(
			'input');
	var i = 0;
	for (i = 0; i < 2; i++) {
		data[i] = inputs[i].value;
	}
	var newData = new Array();
	newData[0]  = data[1];
	
	lastAdd = newData;
	
	File.createFile(data[1], (data[0]), handleCreatefile);
}


function handleCreatefile(str) {

	if (str.indexOf("<error") < 0) {
		filegrid.addRow(filegrid.uid(), lastAdd);
		$("#file_add").dialog("close");
	} else {
		document.getElementById('error').innerHTML = str;
		$("#error").dialog();
	}
}

function initFile() {
	document.getElementById("file").style.display = "block";

	filegrid = new dhtmlXGridObject('file_gridbox');
	filegrid.setImagePath("js/codebase/imgs/");
	filegrid.setHeader("File Name");
	filegrid.setInitWidths("*");
	filegrid.setColAlign("center");
	filegrid.setColTypes("txt");
	filegrid.setColSorting("str");
	filegrid.init();

	refreshFile();
	filegrid.setSkin("dhx_skyblue");
	filegrid.setEditable(false);
}