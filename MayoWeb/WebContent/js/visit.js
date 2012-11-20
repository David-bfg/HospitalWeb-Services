var visitgrid;

function handleGetAllVisits(str)
{

	//response
	document.getElementById('xml').innerHTML=str;
	visitgrid.parse(document.getElementById('v_xml')); 

	visitgrid.makeSearch("visit_obj");
}


function refreshVisit() {
	patientClinicNum  = getSelectedPatientData()[0];
	
	visitgrid.clearAll();
	
	Visit.getAllVisitsXML(patientClinicNum, handleGetAllVisits);

	visitgrid.clearSelection();
}

function addVisit() {
	 $("#visit_add").dialog();
	 
	 var clinicNum = getSelectedPatientData()[0];
	 var inputs = document.getElementById('visit_add').getElementsByTagName('input');
	 inputs[1].value = clinicNum;
	 
	 $( "#visit_add" ).dialog( { width: 415 } );
}


function getSelectedVisitData() {
	rowID = visitgrid.getSelectedId();
	var data = new Array();
	var i =0;
	visitgrid.forEachCell(rowID,function(c){
        data[i] = c.getValue();
        i++;
    });
    
	return data;
}


function editVisit() {
	 var data = getSelectedVisitData();
	 
	 var inputs = document.getElementById('visit_edit').getElementsByTagName('input');
	 var i=0;
	 for(i=0;i<9;i++)
	 {
		 inputs[i].value = data[i].replace("&nbsp;","");
	 }
	 
	 $("#visit_edit").dialog();
	 $( "#visit_edit" ).dialog( { width: 415 } );
}

function visitcommitEdit() {
	var data = new Array();
		var inputs = document.getElementById('visit_edit').getElementsByTagName('input');
		 var i=0;
		 for(i=0;i<9;i++)
		 {
			 data[i] = inputs[i].value;
		 }

		 lastEdit = data;
		 Visit.modifyVisit(parseInt(data[0]),data[1],parseInt(data[2]),parseInt(data[3]),data[4],data[5],data[6],data[7],data[8],
				 handleModifyVisit);
	} 

function handleModifyVisit(str) {
	if(str.indexOf("<error")<0)
	{
		rowID = visitgrid.getSelectedId();
		var i= 0;
		visitgrid.forEachCell(rowID,function(c){
	        c.setValue(lastEdit[i]);
	        i++;
	    });
	    
		$( "#visit_edit" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function visitcommitAdd() {
	var data = new Array();
	var newData = new Array();
	newData[0]= "&nbsp;";
	newData[1]= "&nbsp;";
	
	var inputs = document.getElementById('visit_add').getElementsByTagName('input');
	 var i=0;
	 for(i=0;i<7;i++)
	 {
		 data[i] = inputs[i].value;
		 newData[i+2] = data[i];
	 }
	 lastAdd = newData;
	 
	 Visit.createVisit(data[0],parseInt(data[1]),data[2],data[3],data[4],data[5],data[6],
			 handleCreateVisit);
}

function handleCreateVisit(str) {
	
	if(str.indexOf("<error")<0)
	{
	visitgrid.addRow(visitgrid.uid(),lastAdd);
	 $( "#visit_add" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function handleVisitDoubleClick() {
	//Hide all tables
	hideAll();
	
	//Set the Visit Header
	var visitNum  = getSelectedVisitData()[2];
	var visitDate = getSelectedVisitData()[1];
	setVisitHeader(visitNum, visitDate);
	
	//Display all tables corresponding to the selected visit
	initSystemUsed();
	initStudy();
	initResearchStudy();
	initBillingCodes();
}

function initVisit() {
	document.getElementById("visit").style.display="block";
	
	visitgrid = new dhtmlXGridObject('visit_gridbox');
	visitgrid.setImagePath("js/codebase/imgs/");
	visitgrid.setHeader("Visit ID,Date,Visit #,Clinic #,Provider,Kinesiologist,Date Processing Complete,Physical Therapist,Date Analysis Complete");
	visitgrid.setInitWidths("*,*,*,*,*,*,*,*,*");
	visitgrid.setColAlign("center,center,center,center,center,center,center,center,center");
	visitgrid.setColTypes("txt,txt,txt,txt,txt,txt,txt,txt,txt");
	visitgrid.setColSorting("int,data,int,int,str,str,date,str,date");
	visitgrid.init();
	
	refreshVisit();
	visitgrid.attachEvent("onRowDblClicked", handleVisitDoubleClick); 
	
	visitgrid.setSkin("dhx_skyblue");
	visitgrid.setEditable(false);
	//init visit script
}

function clearVisit() {
	hideAll();
	document.getElementById("visit").style.display="block";
	document.getElementById("condition").style.display="block";
	document.getElementById("icd9diagnostic").style.display="block";
	document.getElementById("tape").style.display="block";
	document.getElementById("file").style.display="block";
	document.getElementById("assistivedevice").style.display="block";
	document.getElementById("icd9procedurecode").style.display="block";
	document.getElementById("visitHeader").innerHTML = "";
}


function setVisitHeader(visitNum, visitDate) {
	var str = visitNum + " " + visitDate;
	document.getElementById("visitHeader").innerHTML="<a class='button' href='javascript:clearVisit()'><span>"+str+"</span></a> ";
	document.getElementById("visitHeader").style.display="";
}

function hideVisitHeader() {
	document.getElementById("visitHeader").style.display="none";
}