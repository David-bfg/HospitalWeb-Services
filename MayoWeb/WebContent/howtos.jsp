<%@ include file="templates/header.jsp" %>

<style>
img {
}

div {
font-weight: normal;
}
</style>

<div>
<h1>How To's</h1>
  <script type='text/javascript' src='/MayoWeb/dwr/interface/Patient.js'></script>
  <script type='text/javascript' src='/MayoWeb/dwr/engine.js'></script>
  
  <h2>Home page</h2>
  <br /><img align='center' src="images/h1.png" width="80%" /><br />
<br>There is a table of patients, you can select one by single clicking or by typing in the search field.
<br>There are 3 buttons to press first being add then refresh and edit. Edit and add will bring up dialog boxes for adding and editing a customer; edit needs a patient to be selected in the table before being clicked.
<br>The last feature on this page is the search button in the upper right. Once this is pressed a new search bar appears as shown in the following picture.
  <br /><img align='center' src="images/h2.png" width="80%" /><br />
<br><br>There is a plus button that allows you to dynamically add more criteria to your searches, and a drop down box that will help you specify queries once you have specified your query click on the big green search button to retrieve your results.<br>
<br>Once you have all that done double click on the patient you want to edit/view information for.
<br>Note: each table in the website uses the same add, refresh, edit and search(this search is simple not the query search) functions
<br /><br>Double clicking patient will bring you to the visit table:<br>
  <br /><img align='center' src="images/h3.png" width="80%" /><br />
<br>You will be able to see the visit table and can look at other information associated with the patient like Tape, ICD9 Diagnostic, Condition, Assistive Device, etc by clicking on the lower gray buttons and then again to hide the table.
<br><br>Now you can double click on a visit to get to the page with information about the visit:
<br><br>You will see buttons for all attributes associated with this visit these act the same way as they do in the page with the visit information
<br>
  <br /><img align='center' src="images/h4.png" width="80%" /><br />
  
  <br>Lastly the upper right has breadcrumbs that you can click on to go back to the page containing patients or the page containing your current patients visits. The page for the visit info only has the button to return to the patient/home page.<br><br>

  <br /><img align='center' src="images/h5.png" width="30%" /><br /><br />
Another feature is the auto complete. This feature will suggest user previously entered data for a given field as the user enter the info.
<br />
</div>
  <script>
	 select(1);
  </script>
<%@ include file="templates/footer.jsp" %>