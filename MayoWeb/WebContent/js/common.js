var panels = "";

var panelArr = new Array();

function addPanels(str) {
	panelArr[str] = 1;
}

function removePanels(str) {
	panelArr[str] = 0;
}

function addAndSavePanel(str) {
	addPanels(str);
	$.cookie("MayoWeb", generatePanelStr());
}

function removeAndSavePanel(str) {
	removePanels(str);
	//alert("rem:"+generatePanelStr());
	$.cookie("MayoWeb", generatePanelStr());
}

function generatePanelStr() {
	var str = "";
	var i;
	for(i in panelArr) {
		if(panelArr[i]==1)
		{
			if(str!="")
				str = str+"," +i;
			else
				str = str + i;
		}
	}
	return str;
}


function checkCookies() {
	var panels = ( $.cookie("MayoWeb") );
	//alert(panels);
	if(panels!=null && panels!="null") {
		panel = panels.split(",");
		var i;
		for(i in panel) {
			var s = "#"+panel[i]+" .trigger";
			$(s).click();
			addAndSavePanel(panel[i]);
		}
	}
}

function hideAll() {
	document.getElementById("patient").style.display="none";
	document.getElementById("visit").style.display="none";
	document.getElementById("condition").style.display="none";
	document.getElementById("icd9diagnostic").style.display="none";
	document.getElementById("tape").style.display="none";
	document.getElementById("file").style.display="none";
	document.getElementById("assistivedevice").style.display="none";
	document.getElementById("icd9procedurecode").style.display="none";
	document.getElementById("systemused").style.display="none";
	document.getElementById("study").style.display="none";
	document.getElementById("billingcodes").style.display="none";
	document.getElementById("researchstudy").style.display="none";
}

function expandOrCollapse(elementID) {
	var el = document.getElementById(obj);
	if ( el.style.display != 'none' )
		el.style.display = 'none';
	else 
		el.style.display = '';
}



function initShowHide(){

	//Hide (Collapse) the toggle containers on load
	$(".toggle_container").hide(); 
	
	
	
	//Switch the "Open" and "Close" state per click
	$("div.trigger").toggle(function(){
		$(this).addClass("active");
		addAndSavePanel(this.parentNode.id);
	}, function () {
		$(this).removeClass("active");
		removeAndSavePanel(this.parentNode.id);
	});

	//Slide up and down on click
	$("div.trigger").click(function(){
		$(this).next(".toggle_container").slideToggle("slow");
	});
	
	
	
	$("#filters").hide(); 

	//Slide up and down on click
	$("#searchButton").click(function(){
		$("#filters").slideToggle("slow");
	});

	checkCookies();
};