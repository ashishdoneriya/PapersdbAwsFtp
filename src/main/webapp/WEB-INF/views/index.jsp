<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Download previous exam Question Papers for College, Branch or Subject">
	<title>PapersDB - Download Previous Exam Question Papers</title>
	<script src="static/others/js/jquery.min.js" defer></script>
	<script src="static/others/js/bootstrap.min.js" defer></script>
<!-- 	<script src="js/material.min.js" type="text/javascript" defer></script>  -->
<!-- 	<script src="js/ripples.min.js" type="text/javascript" defer></script> -->
<!--<script>
		var cb = function() {
			var h = document.getElementsByTagName('head')[0];
			var l = document.createElement('link'); l.rel = 'stylesheet';
			l.href = 'static/bootstrapMaterial/css/ripples.min.css';
			h.parentNode.insertBefore(l, h);
			
			var l = document.createElement('link'); l.rel = 'stylesheet';
			l.href = 'static/bootstrapMaterial/css/roboto.min.css';
			h.parentNode.insertBefore(l, h);
			
			var l = document.createElement('link'); l.rel = 'stylesheet';
			l.href = 'static/bootstrapMaterial/css/material-fullpalette.min.css';
			h.parentNode.insertBefore(l, h);
		};
		
		var raf = requestAnimationFrame || mozRequestAnimationFrame || webkitRequestAnimationFrame || msRequestAnimationFrame;
		if (raf) raf(cb);
		else window.addEventListener('load', cb);
	</script>-->
	<link rel="stylesheet" href="static/others/css/bootstrap.min.css">
<!--  	<link href="css/ripples.min.css" rel="stylesheet"> -->
<!-- 	<link href="css/roboto.min.css" rel="stylesheet"> -->
<!-- 	<link href="css/material-fullpalette.min.css" rel="stylesheet">  -->
	<style>
		body {
			background-color: #fff;
		}
		
		.starter-template {
			padding: 40px 15px;
			padding-bottom: 10px;
			text-align: center;
		}
		
		img#titlelogo {
			height: 60px;
			font-family: Impact;
			font-size: 65px;
			line-height: 65px;
			color: #3f525b;
		}
		
		.container {
			margin: 0 auto;
			width: 100%;
		}
		
		.lead {
			margin-top: 15px;
			color: #4d6570;
		}
		
		#mainNavbar {
			background-color: #fff;
			height: 50px;
			
		}
		
		a#submitPaperButton {
			color: #4d6570;
 			font-size:18px; 
			float: right;
			padding-top:15px;
		}
		
		img.logoInverse {
			margin-top:5px;
			height:38px;
		}
		
		#results {
			margin-top:20px;
		}
		
		.textCenter {
			text-align: center;
		}
		.navbar {
			min-height: 50px;
			box-shadow: none;
		}
		@-moz-keyframes three-quarters-loader {
			0% {
				-moz-transform: rotate(0deg);
				transform: rotate(0deg);
			}
			100% {
				-moz-transform: rotate(360deg);
				transform: rotate(360deg);
			}
		}
		
		@-webkit-keyframes three-quarters-loader {
			0% {
				-webkit-transform: rotate(0deg);
				transform: rotate(0deg);
			}
			100% {
				-webkit-transform: rotate(360deg);
				transform: rotate(360deg);
			}
		}
		
		@keyframes three-quarters-loader {
			0% {
				-moz-transform: rotate(0deg);
				-ms-transform: rotate(0deg);
				-webkit-transform: rotate(0deg);
				transform: rotate(0deg);
			}
			100% {
				-moz-transform: rotate(360deg);
				-ms-transform: rotate(360deg);
				-webkit-transform: rotate(360deg);
				transform: rotate(360deg);
			}
		}
		/* :not(:required) hides this rule from IE9 and below */
		
		.three-quarters-loader:not(:required) {
			-moz-animation: three-quarters-loader 1250ms infinite linear;
			-webkit-animation: three-quarters-loader 1250ms infinite linear;
			animation: three-quarters-loader 1250ms infinite linear;
			border: 8px solid #38e;
			border-right-color: transparent;
			border-radius: 16px;
			box-sizing: border-box;
			display: inline-block;
			position: relative;
			overflow: hidden;
			text-indent: -9999px;
			width: 32px;
			height: 32px;
			left: 45%;
			margin-top: 150px;
		}
		
@media only screen and (max-width: 800px) {
    
    /* Force table to not be like tables anymore */
	#no-more-tables table, 
	#no-more-tables thead, 
	#no-more-tables tbody, 
	#no-more-tables th, 
	#no-more-tables td, 
	#no-more-tables tr { 
		display: block; 
	}
 
 	#no-more-tables tr {
 		margin-bottom: 10px;
 	}
 	
	/* Hide table headers (but not display: none;, for accessibility) */
	#no-more-tables thead tr { 
		position: absolute;
		top: -9999px;
		left: -9999px;
	}
 
	#no-more-tables tr { border: 1px solid #ccc; }
 
	#no-more-tables td { 
		/* Behave  like a "row" */
		border: none;
		border-bottom: 1px solid #eee; 
		position: relative;
		padding-left: 50%; 
		white-space: normal;
		text-align:left;
	}
 
	#no-more-tables td:before { 
		/* Now like a table header */
		position: absolute;
		/* Top/left values mimic padding */
		top: 6px;
		left: 6px;
		width: 45%; 
		padding-right: 10px; 
		white-space: nowrap;
		text-align:left;
		font-weight: bold;
	}
 
	/*
	Label the data
	*/
	#no-more-tables td:before { content: attr(data-title); }
}

.table-bordered {
	border: 0px solid #ddd;
}
.table-bordered>thead>tr>td, .table-bordered>thead>tr>th {
	border-bottom-width: 0px;
}
	</style> 
</head>

<body>
	<nav id="mainNavbar" class="navbar navbar-static-top">
		<div class="container">
			<div id="navbarLogoDivMobile" class="navbar-header hidden-xs hidden-sm hidden-md hidden-lg active">
				<a href="/" ><img class="logoInverse" src="images/logoinverse.png" alt="PapersDB"></a>
			</div>
			<div id="navbarLogoDivDesktop" class="navbar-header hidden-xs hidden-sm hidden-md hidden-lg active">
				<a href="/" ><img class="logoInverse" src="images/logoinverse.png" alt="PapersDB"></a>
			</div>
			<div id="submitPaperNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li class="visible-md-block visible-lg-block">
						<a onclick="displaySubmitModal()" id="submitPaperButton" href="#"><span class="glyphicon glyphicon-plus"></span> Submit Paper</a>
					</li>
					<li class="visible-xs-block visible-sm-block">
						<a href="submit-paper.html" id="submitPaperButton"><span class="glyphicon glyphicon-plus"></span> Submit Paper</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<div id="mainBody" class="container">

		<div class="starter-template" id="titleHeader">
			<a href="/"><img id="titlelogo" src="images/logo.png" alt="PapersDB"></a>
			<p class="lead">Get previous exam question papers</p>
		</div>

		<div class="textCenter">
			<form id="search" class="form-inline" role="form">
				<div class="visible-xs-block">
					<div class=" col-xs-1"></div>
				</div>
				<div class="input-group col-xs-10 col-sm-3 col-md-3 col-lg-2">
					<select id="selectedCollege" class="form-control" name="college" onchange="setBranchOptions()">
						<option value="" disabled selected>Select College</option>
					</select>
				</div>
				<div class="visible-xs-block">
					<div class=" col-xs-1"></div>
				</div>
				<div class="input-group col-xs-10 col-sm-3 col-md-3 col-lg-2">
					<select id="selectedBranch" class="form-control" name="branch" onchange="setSubjectOptions()">
						<option value="" disabled selected>Select Branch</option>
					</select>
				</div>
				<div class="visible-xs-block">
					<div class=" col-xs-1"></div>
				</div>
				<div class="input-group col-xs-10 col-sm-3 col-md-3 col-lg-2">
					<select id="selectedSubject" class="form-control" name="subject">
						<option value="" disabled selected>Select Subject</option>
					</select>
				</div>
				<div class="visible-sm-block">
					<div class="clearfix"></div>
					<br>
				</div>
				<div class="visible-xs-block">
					<div class="col-xs-1"></div>
				</div>
				<div class="input-group col-xs-10 col-sm-4 col-md-1 col-lg-1">
					<input type="button" value="Submit" name="submit" onclick="getPapers()" class="btn btn-success">
				</div>
			</form>
		</div>
		<div id="results"></div>
	</div>

	<!-- Paper Submit Modal -->
	<div id="paperSubmitModal" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">X</button>
					<h3>Papers Submit</h3>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="paperSubmitForm" method="POST" enctype="multipart/form-data">
						<fieldset>
						
							<!-- File Button -->
							<div class="form-group">
								<label class="col-md-4 control-label" for="papers">
									Add Papers
								</label>
								<div class="col-md-4">
									<input name="papers" id="modalPapers" type="file" class="filestyle" multiple data-input="false">
								</div>
							</div>

							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="college">
									College
								</label>
								<div class="col-md-4">
									<input id="modalCollege" name="college" type="text" placeholder="College" class="form-control input-md">
								</div>
							</div>

							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="branch">
									Branch
								</label>
								<div class="col-md-4">
									<input id="modalBranch" name="branch" type="text" placeholder="Branch" class="form-control input-md">


								</div>
							</div>

							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="subject">
									Subject
								</label>
								<div class="col-md-4">
									<input id="modalSubject" name="subject" type="text" placeholder="subject" class="form-control input-md">


								</div>
							</div>

							<!-- Textarea -->
							<div class="form-group">
								<label class="col-md-4 control-label" for="description">
									Any Comments
								</label>
								<div class="col-md-4">
									<textarea class="form-control" id="modalDescription" name="description"></textarea>
								</div>
							</div>

							<!-- Button -->
							<div class="form-group">
								<label class="col-md-4 control-label" for="submit"></label>
								<div class="col-md-4">
									<input type="button" value="Submit" name="submit"  onclick="submitPaper()" class="btn btn-success">
								</div>
							</div>

						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>

  <!-- Paper Success Modal -->
  <div id="successModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-body">
          <p>Paper Submitted</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
  <!-- Paper Success Modal -->
  <div id="pleaseWaitModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-body">
          <p>Please Wait...</p>
        </div>
      </div>
    </div>
  </div>

	<script type="text/javascript">
 		window.onunload = function() {};
		window.onload = function() {
			initializeCollegeMap();
		}
/* 		var cb = function() {
			var l = document.createElement('link'); l.rel = 'stylesheet';
			l.href = 'static/others/css/bootstrap.min.css';
			var h = document.getElementsByTagName('head')[0]; h.parentNode.insertBefore(l, h);
		};
		var raf = requestAnimationFrame || mozRequestAnimationFrame || webkitRequestAnimationFrame || msRequestAnimationFrame;
		if (raf) raf(cb);
		else window.addEventListener('load', cb); */
			
		var elementsChanged = false;
		var loaderSet = false;
		var mobileView = false;
		function getPapers() {
			setElementsFirstTime();
			getSearchResults();
		}

		function displaySubmitModal() {
			document.getElementById("modalPapers").value = null;
			document.getElementById("modalCollege").value = null;
			document.getElementById("modalBranch").value = null;
			document.getElementById("modalSubject").value = null;
			document.getElementById("modalDescription").value = null;
			$('#paperSubmitModal').modal('show');
		}
		
		function submitPaper() {
			console.log('Inside submitPaper() function');
			$('#paperSubmitModal').modal('hide');
			
			var paperElement = document.getElementById("modalPapers");
			if ( ! paperElement.value) {
				$('#successModal').modal('show');
				return;
			}
			var form = document.getElementById("paperSubmitForm");
			var formData = new FormData(form);
			var xhr = getXMLHTTP();
			xhr.open('POST', "submitpapers");
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					$('#pleaseWaitModal').modal('hide');
					$('#successModal').modal('show');
				}
			}
			xhr.send(formData);
			$('#pleaseWaitModal').modal('show');
		}

		function getSearchResults() {
			var resultsDiv = document.getElementById('results');
			while (resultsDiv.firstChild) {
				resultsDiv.removeChild(resultsDiv.firstChild);
			}
			var selectedCollegeID = document.getElementById("selectedCollege").value;
			var selectedBranchID = document.getElementById("selectedBranch").value;
			var selectedSubjectID = document.getElementById("selectedSubject").value;
			var query = "collegeID=" + selectedCollegeID + "&branchID=" + selectedBranchID + "&subjectID=" + selectedSubjectID;
			var xhr = getXMLHTTP();
			xhr.open('GET', "papers?" + query);
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					removeLoader();
					displayResults(xhr.responseText);
				}
			}
			xhr.send();
			setLoader();
		}


		function setLoader() {
			var iDiv = document.createElement('div');
			iDiv.className = 'three-quarters-loader';
			iDiv.id = "pageLoader";
			document.getElementsByTagName('body')[0].appendChild(iDiv);
			loaderSet = true;
		}

		function removeLoader() {
			if (loaderSet) {
				var loader = document.getElementById("pageLoader");
				loader.parentNode.removeChild(loader);
			}
		}

		function displayResults(responseText) {
			var papers = JSON.parse(responseText);
			/* var table = '<table class="table table-bordered table-hover table-responsive">'; */
			var table = '<div id="no-more-tables"><table class="col-md-12 table-bordered table-striped table-condensed cf">';
			table = table + '<thead class="cf"><tr><th>College</th><th>Branch</th><th>Subject</th><th>Month/Year</th><th>Download Link</th></tr></thead>';
			table = table + '<tbody>';

			for (var paper in papers) {
				table = table + getRow(papers[paper]);
			}
			table = table + '</tbody></table></div>';
			document.getElementById('results').innerHTML = table;
		}

		function getRow(paper) {
			var row = '<tr>'
			row += '<td data-title="College">' + paper.subject.branch.college.name + '</td>';
			row += '<td data-title="Branch">' + paper.subject.branch.name + '</td>';
			row += '<td data-title="Subject">' + paper.subject.name + '</td>';
			if (paper.month != null ) {
				row += '<td data-title="Date">' + paper.month + ' ' + paper.year+ '</td>';	
			} else if (paper.year != null) {
				row += '<td data-title="Year">' + paper.year+ '</td>';
			} else {
				row += '<td data-title="Date">Unknown</td>';
			}
			//onclick="increaseCounter(' + paper.paperID + ')"
			row += '<td data-title="Download Link"><a href="downloadPaper/' + paper.paperID + '"><span class="glyphicon glyphicon-download-alt"></span> Download</a</td></tr>';
			return row;
		}
		
		function increaseCounter(paperID) {
			var xhr = getXMLHTTP();
			xhr.open('POST', "increasecounter/" + paperID);
			xhr.send();
		}

		function setElementsFirstTime() {
			if (elementsChanged == true) {
				return;
			}
			elementsChanged = true;
			/* Removing title logo */
			var mainBody = document.getElementById("mainBody");
			var titleHeader = document.getElementById("titleHeader");
			mainBody.removeChild(titleHeader);
			/* Displaying navbar logo */
			document.getElementById("navbarLogoDivDesktop").className = "navbar-header hidden-xs hidden-sm visible-md visible-lg";
			document.getElementById("navbarLogoDivMobile").className = "navbar-header visible-xs visible-sm hidden-md hidden-lg";
			document.getElementById("navbarLogoDivMobile").style.textAlign = "center";
			/* Hiding submit paper button for mobile devices */
			document.getElementById("submitPaperNavbar").className = "hidden-xs";
			/* Changing font size of submit paper button */
			document.getElementById("submitPaperButton").style.fontSize = "";
			/* Change navigation bar color */
			document.getElementById("mainNavbar").style.backgroundColor = "#5c7986";
			document.getElementById("mainNavbar").style.borderColor = "#455a64";
			document.getElementById("submitPaperButton").style.color = "#fff";
			
		}


		var collegeMap;
		var collegeOptions = document.getElementById("selectedCollege");
		var branchOptions = document.getElementById("selectedBranch");
		var subjectOptions = document.getElementById("selectedSubject");

		function initializeCollegeMap() {
			var collegeMapJson = null;
			/*		collegeMapJson = getCollegeMapJsonFromLocalStorage(); */
			if (collegeMapJson == null) {
				var xhr = getXMLHTTP();
				xhr.open('GET', "collegeMapJson");
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						collegeMapJson = xhr.responseText;
						collegeMap = JSON.parse(collegeMapJson);
						setCollegeOptions();
				/*		setCollegeMapToLocalStorage(collegeMapJson); */
					}
				}
				xhr.send();
			}
		}

		function setSubjectOptions() {
			removeChilds(subjectOptions, "Select Subject");

			var selectedCollegeName = collegeOptions.options[collegeOptions.selectedIndex].text;
			var selectedCollegeObj = collegeMap[selectedCollegeName];
			var selectedBranchName = branchOptions.options[branchOptions.selectedIndex].text;
			var selectedBranchObj = collegeMap[selectedCollegeName].branches[selectedBranchName];
			var subjectsObj = selectedBranchObj.subjects;

			for (var subjectName in subjectsObj) {
				var option = document.createElement("option");
				option.text = subjectName;
				option.value = subjectsObj[subjectName];
				subjectOptions.add(option);
			}
		}

		function setBranchOptions() {
			removeChilds(branchOptions, "Select Branch");
			removeChilds(subjectOptions, "Select Subject");

			var selectedCollegeName = collegeOptions.options[collegeOptions.selectedIndex].text;
			var selectedCollegeObj = collegeMap[selectedCollegeName];
			var branchesObj = selectedCollegeObj.branches;

			for (var branchName in branchesObj) {
				var branchObj = branchesObj[branchName];
				var option = document.createElement("option");
				option.text = branchName;
				option.value = branchObj.branchID;
				branchOptions.add(option);
			}
		}

		function setCollegeOptions() {
			for (var collegeName in collegeMap) {
				var collegeObj = collegeMap[collegeName];
				var option = document.createElement("option");
				option.text = collegeName;
				option.value = collegeMap[collegeName].collegeID;
				collegeOptions.add(option);
			}
		}

		function removeChilds(myNode, name) {
			while (myNode.firstChild) {
				myNode.removeChild(myNode.firstChild);
			}
			var option = document.createElement("option");
			option.text = name;
			option.disabled = true;
			option.selected = true;
			option.value = "";
			myNode.add(option)
		}

		/* Compatibility functions */

		function getXMLHTTP() { /* fuction to return the xml http object */
			var xmlhttp = false;
			try {
				xmlhttp = new XMLHttpRequest();
			} catch (e) {
				try {
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e1) {
					try {
						xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
					} catch (e2) {
						xmlhttp = false;
					}
				}
			}
			return xmlhttp;
		}

		function getDifference(date1, date2) {
			/* Convert both dates to milliseconds */
			var date1_ms = date1.getTime();
			var date2_ms = date2.getTime();
			/* Calculate the difference in milliseconds */
			var difference_ms = date2_ms - date1_ms;
			/*take out milliseconds
			difference_ms = difference_ms / 1000;
			/* 			var seconds = Math.floor(difference_ms % 60); */
			difference_ms = difference_ms / 60;
			var minutes = Math.floor(difference_ms % 60);
			return minutes;
			/* 			difference_ms = difference_ms/60;  */
			/* 			var hours = Math.floor(difference_ms % 24);   */
			/* 			var days = Math.floor(difference_ms/24); */
		}

		function getCollegeMapJsonFromLocalStorage() {
			if (typeof(Storage) == "undefined") {
				return null;
			}
			var dateJson = localStorage.get("collegeMapTimeStamp");
			if (dateJson == null) {
				return null;
			}
			var diff = getDifference(JSON.stringify(dateJson), new Date());
			if (diff > 1) {
				return localStorage.get("collegeMapJson");
			}
		}

		function setCollegeMapToLocalStorage(collegeMapJson) {
			try {
				localStorage.set("collegeMapJson", collegeMapJson);
				localStorage.set("collegeMapTimeStamp", JSON.stringify(new Date()));
			} catch (e) {
				if (e == QUOTA_EXCEEDED_ERR) {
					console.log("Error: Local Storage limit exceeds.");
				} else {
					console.log("Error: Saving to local storage.");
				}
			}
		}
	</script>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-60245752-2', 'auto');
  ga('send', 'pageview');

</script>
</body>

</html>