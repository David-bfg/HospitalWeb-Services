var studygrid;

function handleGetAllStudies(str)
{

	//response
	document.getElementById('xml').innerHTML=str;
	studygrid.parse(document.getElementById('v_xml')); 

	studygrid.makeSearch("study_obj");
}


function refreshStudy() {
	var visitNum  = getSelectedVisitData()[0];
	
	studygrid.clearAll();
	
	Study.getAllStudiesXML(parseInt(visitNum), handleGetAllStudies);

	studygrid.clearSelection();
}

function addStudy() {
	 $("#study_add").dialog();
	 
	 var visitID = getSelectedVisitData()[0];
	 var inputs = document.getElementById('study_add').getElementsByTagName('input');
	 inputs[1].value = visitID;
	 
	 $( "#study_add" ).dialog( { width: 415 } );
}


function getSelectedStudyData() {
	rowID = studygrid.getSelectedId();
	var data = new Array();
	var i =0;
	studygrid.forEachCell(rowID,function(c){
        data[i] = c.getValue();
        i++;
    });
    
	return data;
}


function editStudy() {
	 var data = getSelectedStudyData();
	 
	 var inputs = document.getElementById('study_edit').getElementsByTagName('input');
	 var i=0;
	 var visitID = getSelectedVisitData()[0];
	 
	 inputs[0].value = data[0].replace("&nbsp;","");
	 inputs[1].value = visitID;
	 
	 lastOldName = inputs[0].value;
	 
	 $("#study_edit").dialog();
	 $( "#study_edit" ).dialog( { width: 415 } );
}

function studyCommitEdit() {
	var data = new Array();
		var inputs = document.getElementById('study_edit').getElementsByTagName('input');
		 var i=0;
		 
		data[0] = inputs[0].value;
		 var visitID = inputs[1].value;

		 lastEdit = data;
		 
		 Study.modifyStudy(lastOldName, data[0], parseInt(visitID),handleModifyStudy);
	} 

function handleModifyStudy(str) {
	if(str.indexOf("<error")<0)
	{
		rowID = studygrid.getSelectedId();
		var i= 0;
		studygrid.forEachCell(rowID,function(c){
	        c.setValue(lastEdit[i]);
	        i++;
	    });
	    
		$( "#study_edit" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function studyCommitAdd() {
	var data = new Array();
	
	var inputs = document.getElementById('study_add').getElementsByTagName('input');
	 var i=0;
	 
	 data[0] = inputs[0].value;
	 data[1] = inputs[1].value;
	 lastAdd = data;
	 
	 Study.createStudy(data[0], data[1], handleCreateStudy);
}

function handleCreateStudy(str) {
	
	if(str.indexOf("<error")<0)
	{
	studygrid.addRow(studygrid.uid(),lastAdd);
	 $( "#study_add" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function initStudy() {
	document.getElementById("study").style.display="block";
	
	studygrid = new dhtmlXGridObject('study_gridbox');
	studygrid.setImagePath("js/codebase/imgs/");
	studygrid.setHeader("name");
	studygrid.setInitWidths("*");
	studygrid.setColAlign("center");
	studygrid.setColTypes("txt");
	studygrid.setColSorting("str");
	studygrid.init();
	
	refreshStudy();
	
	studygrid.setSkin("dhx_skyblue");
	studygrid.setEditable(false);
}