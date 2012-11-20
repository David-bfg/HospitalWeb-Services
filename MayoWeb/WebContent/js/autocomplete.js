var autoComplete = new Array();
	function setAutoComplete(data, elem) {
		 $(document).ready(function(){
			   var dataArr =data.split("|");
			$("#auto_add_"+elem).autocomplete(dataArr);
		
			if($("#auto_edit_"+elem)!=null)
				$("#auto_edit_"+elem).autocomplete(dataArr);
			  });
	}

	function handleAutoGetAllAssistiveDevices(str) {
		autoComplete["AssistiveDevice"] = str;
		setAutoComplete(str,"assistive_device_name");
	}
	
	function handleAutoGetAllConditions(str) {
		autoComplete["Conditions"] = str;
		setAutoComplete(str,"condition_name");
		}
	
	function handleAutoGetAllICD9Diagnostic(str) {
		autoComplete["ICD9Diagnostic"] = str;
		setAutoComplete(str,"icd9diagnostic");
		}
	
	function handleAutoGetAllICD9ProcedureCode(str) {
		autoComplete["ICD9ProcedureCode"] = str;
		setAutoComplete(str,"icd9procedurecode");
		}
	
	function handleAutoGetAllSystemUsed(str) {
		autoComplete["SystemUsed"] = str;
		setAutoComplete(str,"system_name");
	}
	
	function handleAutoGetAllStudy(str) {
		autoComplete["Study"] = str;
		setAutoComplete(str,"study_name");
	}
	
	function handleAutoGetAllResearchStudy(str) {
		autoComplete["ResearchStudy"] = str;
		setAutoComplete(str,"research_study_name");
	}
	
	function handleAutoGetAllProvider(str) {
		autoComplete["Provider"] = str;
		setAutoComplete(str,"provider");
	}
	
	function handleAutoGetAllKinesiologist(str) {
		autoComplete["Kinesiologist"] = str;
		setAutoComplete(str,"kinesiologist");
	}
	
	function handleAutoGetAllPhysicalTherapist(str) {
		autoComplete["PhysicalTherapist"] = str;
		setAutoComplete(str,"physicalTherapist");
	}
	
	function initAutoComplete() {
		
		AutoComplete.getAllAssistiveDevices(handleAutoGetAllAssistiveDevices);
		AutoComplete.getAllConditions(handleAutoGetAllConditions);
		AutoComplete.getAllICD9Diagnostic(handleAutoGetAllICD9Diagnostic);
		AutoComplete.getAllICD9ProcedureCode(handleAutoGetAllICD9ProcedureCode);
		AutoComplete.getAllSystemUsed(handleAutoGetAllSystemUsed);
		AutoComplete.getAllStudy(handleAutoGetAllStudy);
		AutoComplete.getAllResearchStudy(handleAutoGetAllResearchStudy);
		AutoComplete.getAllProvider(handleAutoGetAllProvider);
		AutoComplete.getAllKinesiologist(handleAutoGetAllKinesiologist);
		AutoComplete.getAllPhysicalTherapist(handleAutoGetAllPhysicalTherapist);
		
	}
