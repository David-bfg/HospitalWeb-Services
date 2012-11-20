var billingcodesgrid;

function handleGetAllBillingCodes(str)
{

	//response
	document.getElementById('xml').innerHTML=str;
	billingcodesgrid.parse(document.getElementById('v_xml')); 

	billingcodesgrid.makeSearch("billingcodes_obj");
}


function refreshBillingCodes() {
	var visitID  = getSelectedVisitData()[0];
	
	billingcodesgrid.clearAll();
	
	BillingCode.getAllBillingCodesXML(parseInt(visitID), handleGetAllBillingCodes);

	billingcodesgrid.clearSelection();
}

function addBillingCodes() {
	 $("#billingcodes_add").dialog();
	 
	 var clinicNum = getSelectedVisitData()[0];
	 var inputs = document.getElementById('billingcodes_add').getElementsByTagName('input');
	 inputs[3].value = clinicNum;
	 
	 $( "#billingcodes_add" ).dialog( { width: 415 } );
}


function getSelectedBillingCodesData() {
	rowID = billingcodesgrid.getSelectedId();
	var data = new Array();
	var i =0;
	billingcodesgrid.forEachCell(rowID,function(c){
        data[i] = c.getValue();
        i++;
    });
    
	return data;
}


function editBillingCodes() {
	 var data = getSelectedBillingCodesData();
	 
	 var inputs = document.getElementById('billingcodes_edit').getElementsByTagName('input');
	 var i=0;
	 for(i=0;i<3;i++)
	 {
		 inputs[i].value = data[i].replace("&nbsp;","");
	 }
	 
	 inputs[3].value = getSelectedVisitData()[0];
	 
	 $("#billingcodes_edit").dialog();
	 $( "#billingcodes_edit" ).dialog( { width: 415 } );
}

function billingCodesCommitEdit() {
	var data = new Array();
		var inputs = document.getElementById('billingcodes_edit').getElementsByTagName('input');
		 var i=0;
		 for(i=0;i<4;i++)
		 {
			 data[i] = inputs[i].value;
		 }

		 lastEdit = data;
		 var id = billingcodesgrid.getSelectedId();
		 BillingCode.modifyBillingCode(parseInt(id),(data[0]),parseInt(data[1]),parseInt(data[2]),parseInt(data[3]),
				 handleModifyVisit);
	}

function handleModifyVisit(str) {
	if(str.indexOf("<error")<0)
	{
		rowID = billingcodesgrid.getSelectedId();
		var i= 0;
		billingcodesgrid.forEachCell(rowID,function(c){
	        c.setValue(lastEdit[i]);
	        i++;
	    });
	    
		$( "#billingcodes_edit" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function billingCodesCommitAdd() {
	var data = new Array();
	
	var inputs = document.getElementById('billingcodes_add').getElementsByTagName('input');
	 var i=0;
	 
	 data[0] = inputs[0].value;
	 data[1] = inputs[1].value;
	 data[2] = inputs[2].value;
	 data[3] = inputs[3].value;
	 
	 lastAdd = data;
	 
	 BillingCode.createBillingCode((data[0]), parseInt(data[1]), parseInt(data[2]), parseInt(data[3]), handleCreateBillingCodes);
}

function handleCreateBillingCodes(str) {
	
	if(str.indexOf("<error")<0)
	{
	billingcodesgrid.addRow(billingcodesgrid.uid(),lastAdd);
	 $( "#billingcodes_add" ).dialog( "close" );
	}
	else
	{
		document.getElementById('error').innerHTML=str;
		$("#error").dialog();
	}
}

function initBillingCodes() {
	document.getElementById("billingcodes").style.display="block";
	
	billingcodesgrid = new dhtmlXGridObject('billingcodes_gridbox');
	billingcodesgrid.setImagePath("js/codebase/imgs/");
	billingcodesgrid.setHeader("Visit Charge,CPT Code, Integer Increment");
	billingcodesgrid.setInitWidths("*,*,*");
	billingcodesgrid.setColAlign("center,center, center");
	billingcodesgrid.setColTypes("txt,txt,txt");
	billingcodesgrid.setColSorting("int,data,int");
	billingcodesgrid.init();
	
	refreshBillingCodes();
	
	billingcodesgrid.setSkin("dhx_skyblue");
	billingcodesgrid.setEditable(false);
}