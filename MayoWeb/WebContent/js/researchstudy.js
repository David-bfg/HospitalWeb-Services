var researchstudygrid;

function handleGetAllResearchStudies(str)
{

	//response
	document.getElementById('xml').innerHTML=str;
	researchstudygrid.parse(document.getElementById('v_xml')); 

	researchstudygrid.makeSearch("researchstudy_obj");
}


function refreshResearchStudy() {
	var visitNum  = getSelectedVisitData()[0];
	
	researchstudygrid.clearAll();
	
	ResearchStudy.getAllResearchStudiesXML(parseInt(visitNum), handleGetAllResearchStudies);

	researchstudygrid.clearSelection();
}

function addResearchStudy() {
	 $("#researchstudy_add").dialog();
	 
	 var visitID = getSelectedVisitData()[0];
	 var inputs = document.getElementById('researchstudy_add').getElementsByTagName('input');
	 inputs[2].value = visitID;
	 
	 $( "#researchstudy_add" ).dialog( { width: 415 } );
}


function getSelectedResearchStudyData() {
	rowID = researchstudygrid.getSelectedId();
	var data = new Array();
	var i =0;
	researchstudygrid.forEachCell(rowID,function(c){
        data[i] = c.getValue();
        i++;
    });
    
	return data;
}


function editResearchStudy() {
	 var data = getSelectedResearchStudyData();
	 
	 var inputs = document.getElementById('researchstudy_edit').getElementsByTagName('input');
	 var i=0;
	 
	 inputs[0].value = data[0].replace("&nbsp;","");
	 inputs[1].value = data[1].replace("&nbsp;","");
	 inputs[2].value = getSelectedVisitData()[0];
	 
	 lastOldName = data[0];
	 $("#researchstudy_edit").dialog();
	 $( "#researchstudy_edit" ).dialog( { width: 415 } );
}

function researchStudyCommitEdit() {
	var data = new Array();
		var inputs = document.getElementById('researchstudy_edit').getElementsByTagName('input');
		
		data[0] = inputs[0].value;
		data[1] = inputs[1].value;
		lastEdit = data;
		var visitID = parseInt(getSelectedVisitData()[0]);
		ResearchStudy.modifyResearchStudy(lastOldName, data[0], data[1], visitID,handleModifyResearchStudy); 
	} 

function handleModifyResearchStudy(str) {
	if(str.indexOf("<error")<0)
	{
		rowID = researchstudygrid.getSelectedId();
		var i= 0;
		researchstudygrid.forEachCell(rowID,function(c){
	        c.setValue(lastEdit[i]);
	        i++;
	    });
	    
		$( "#researchstudy_edit" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function researchStudyCommitAdd() {
	var data = new Array();
	
	var inputs = document.getElementById('researchstudy_add').getElementsByTagName('input');
	 var i=0;
	 
	 data[0] = inputs[0].value;
	 data[1] = inputs[1].value;
	 data[2] = inputs[2].value;
	 
	 lastAdd = data;
	 
	 ResearchStudy.createResearchStudy(data[0], data[1], parseInt(data[2]), handleCreateResearchStudy);
}

function handleCreateResearchStudy(str) {
	
	if(str.indexOf("<error")<0)
	{
	researchstudygrid.addRow(researchstudygrid.uid(),lastAdd);
	 $( "#researchstudy_add" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function initResearchStudy() {
	document.getElementById("researchstudy").style.display="block";
	
	researchstudygrid = new dhtmlXGridObject('researchstudy_gridbox');
	researchstudygrid.setImagePath("js/codebase/imgs/");
	researchstudygrid.setHeader("name,description");
	researchstudygrid.setInitWidths("*,*");
	researchstudygrid.setColAlign("center,center");
	researchstudygrid.setColTypes("txt,txt");
	researchstudygrid.setColSorting("str,str");
	researchstudygrid.init();
	
	refreshResearchStudy();
	
	researchstudygrid.setSkin("dhx_skyblue");
	researchstudygrid.setEditable(false);
}