var lastChange;

/*
function initFilter() {
	var filter = document.getElementById("filters");
	var filterdata = document.getElementById("searchFilterRep");
	var s = document.createElement("div");
	s.name="searchFilter";
	s.innerHTML = filterdata.innerHTML;
	filter.appendChild(s);

	document.getElementById("searchFilter").getElementsByTagName("span")[0].style.display = "none";
}
*/

function addFilter() {
	var filter = document.getElementById("filters");
	var filterdata = document.getElementById("searchFilterRep");
	var s = document.createElement("div");
	s.innerHTML = filterdata.innerHTML;
	filter.appendChild(s);
}

function deleteFilter(div) {
	var child = div.parentNode.parentNode;
	child.parentNode.removeChild(child);
}

function handleInnerSearchResponse(str) {
	var position = lastChange.parentNode.getElementsByTagName("span")[2];
	position.innerHTML = str;
	
	if(lastChange.value=="DOB")
	{
		var inputs = position.getElementsByTagName("input");
		
		$(function() {
			$(inputs[0]).datepicker( { dateFormat: 'yy-mm-dd' } );
			$(inputs[0]).datepicker( "option", "changeYear", true );
			$(inputs[0]).datepicker( "option", "yearRange", '1900:2015' );
		});
		
		$(function() {
			$(inputs[1]).datepicker( { dateFormat: 'yy-mm-dd' } );
			$(inputs[1]).datepicker( "option", "changeYear", true );
			$(inputs[1]).datepicker( "option", "yearRange", '1900:2015' );
		});
	}
}

function getInnerSearch(sel) {
	lastChange = sel;
	Search.getSearchHTML(sel.value, handleInnerSearchResponse);
}

function getXML(div) {
	
	selects = div.getElementsByTagName("select");	
	inputs = div.getElementsByTagName("input");

	var i =0;
	var str = "";
	var name = selects[0].value;
	var type;

	if(name=="None")
		return "";
	
	if(name=="DOB")
	{
		
			type="like";
			if(inputs[0].type=="radio")
				type="equals";
		
		value = inputs[0].value;
		value1 = inputs[1].value;
		return "<filter name=\""+name+"\" value=\""+value+"\" type=\"gt\" />" + 
		"<filter name=\""+name+"\" value=\""+value1+"\" type=\"lt\" />";
	}
	else
	{
		value = inputs[0].value;
		if(selects.length>1)
		{
			type=selects[1].value;
		}
		else
		{
			type="like";
			if(inputs[0].type=="radio")
			{
				type="equals";
				
				for(i=0;i<inputs.length;i++)
				{
					if(inputs[i].checked==true)
						value=inputs[i].value;
				}
			}
			
			
		}
		
		
		
		return "<filter name=\""+name+"\" value=\""+value+"\" type=\""+type+"\" />";
	}
}

function handleProcessSearchFinished(str) {
	patientgrid.clearAll();
	handleGetAllPatientData(str);
}


function submitSearch() {
	var divs = document.getElementById("filters").getElementsByTagName("div");
	var i = 0;
	str = "<filters>";
	for (i=0;i<divs.length;i++)
	{
		str+=getXML(divs[i]);
	}
	str += "</filters>";
	
	Search.processSearch(str, handleProcessSearchFinished);
}
