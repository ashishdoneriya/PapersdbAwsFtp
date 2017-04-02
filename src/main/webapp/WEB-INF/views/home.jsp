<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<html>

	<head>
		<meta charset="utf-8">
		<title>
			Tutorials Point
		</title>
		<script src="http://code.jquery.com/jquery-1.11.3.min.js">
		</script>
		<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js">
		</script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js">
		</script>
		<link rel="stylesheet" href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css">
		<script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js">
		</script>
		<!--     <link href="css/ripples.min.css" rel="stylesheet"> -->
		<!--     <link href="css/roboto.min.css" rel="stylesheet"> -->
		<!--     <link href="css/material-fullpalette.min.css" rel="stylesheet"> -->
		<!--     <script src="js/material.min.js" type="text/javascript"> -->
		<!--     </script> -->
		<!--     <script src="js/ripples.min.js" type="text/javascript"> -->
		<!--     </script> -->
		<style>
			body {
				background-color: #fff;
			}
			
			.container {
				margin: 0 auto;
				width: 100%;
			}
			
			.btn {
				margin-top: 0;
				margin-bottom: 0;
			}
			
			.btn {
				padding: 5px;
				margin: 0px
			}
		</style>
		<script type="text/javascript">
		</script>
	</head>
	<a class="btn btn-default" onclick="addPaperDirectly()">Add Paper</a>

	<body>
		<div class="container">
			<table id="requests" class="table">
				<thead>
					<tr class="info">
						<th>College</th>
						<th>Branch</th>
						<th>Subject</th>
						<th>Description</th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="request" items="${requests}">
						<tr id="row${request.requestID}">
							<th>
								${request.college}
							</th>
							<th>
								${request.branch}
							</th>
							<th>
								${request.subject}
							</th>
							<th>
								${request.description}
							</th>
							<th>
								<a class="btn btn-default" id="ok${request.requestID}" onclick="approveFormModal(${request.requestID})">
									<span class="glyphicon glyphicon-ok"></span>
								</a>
							</th>
							<th>
								<a class="btn btn-danger" id="trash${request.requestID}" onclick="deleteRequest(${request.requestID})">
									<span class="glyphicon glyphicon-trash"></span>Delete
								</a>
							</th>
							<th>
								<a class="btn btn-info" href="downloadzip/file_${request.requestID}.zip">
									<span class="glyphicon glyphicon-download-alt"></span> Download
								</a>
							</th>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<!-- Paper Approved successfully model -->
		<div id="successModal" class="modal fade" tabindex="-1" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body">
						<p>Paper Approved</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Approval Modal -->
		<div id="paperApprovalModal" class="modal fade" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">x</button>
						<h3>Paper Approval</h3>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="approvePaperForm" method="POST" enctype="multipart/form-data">
							<fieldset>
								<!-- File Button -->
								<div class="form-group">
									<label class="col-md-4 control-label" for="papers">Add Paper</label>
									<div class="col-md-4">
										<input name="file" id="modalPaper" type="file" class="filestyle" data-input="false">
									</div>
								</div>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="college">College</label>
									<div class="col-md-4">
										<input id="modalCollege" list="collegeList" name="college" type="text" placeholder="College" onchange="addBranchOptions()" class="form-control input-md" required>
										<datalist id="collegeList"></datalist>
									</div>
								</div>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="branch">Branch</label>
									<div class="col-md-4">
										<input id="modalBranch" list="branchList" name="branch" type="text" placeholder="Branch" onchange="addSubjectOptions()" class="form-control input-md">
										<datalist id="branchList"></datalist>
									</div>
								</div>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="subject">Subject</label>
									<div class="col-md-4">
										<input id="modalSubject" list="subjectList" name="subject" type="text" placeholder="subject" class="form-control input-md" required>
										<datalist id="subjectList"></datalist>
									</div>
								</div>

								<!-- Select Basic -->
								<div class="form-group">
									<label class="col-md-4 control-label" for="month">Select Month</label>
									<div class="col-md-4">
										<select id="modalMonth" name="month" class="form-control">
											<option value="">Select Month</option>
											<option value="January">January</option>
											<option value="February">February</option>
											<option value="March">March</option>
											<option value="April">April</option>
											<option value="May">May</option>
											<option value="June">June</option>
											<option value="July">July</option>
											<option value="August">August</option>
											<option value="September">September</option>
											<option value="October">October</option>
											<option value="November">November</option>
											<option value="December">December</option>
										</select>
									</div>
									<div class="col-md-4">
										<input id="modalYear" name="year" type="text" placeholder="Year" value="" class="form-control input-md">
									</div>
								</div>

								<!-- Textarea -->
								<div class="form-group">
									<label class="col-md-4 control-label" for="description">Description</label>
									<div class="col-md-4">
										<textarea class="form-control" id="modalDescription" name="description"></textarea>
									</div>
								</div>
								<input id="modalRequestID" name="requestID" type="hidden">
								<!-- Button -->
								<div class="form-group">
									<label class="col-md-4 control-label" for="submit">
									</label>
									<div class="col-md-4">
										<input type="button" value="Submit" name="submit" onclick="approveRequest()" class="btn btn-success">
									</div>
								</div>

							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>

		<script>
			function getXMLHTTP() { //fuction to return the xml http object
				var xmlhttp = false;
				try {
					xmlhttp = new XMLHttpRequest();
				} catch (e) {
					try {
						xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e) {
						try {
							xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
						} catch (e1) {
							xmlhttp = false;
						}
					}
				}
				return xmlhttp;
			}

			function deleteRequest(requestID) {
				var anchorID = "trash" + requestID;
				var anchorElement = document.getElementById(anchorID);
				anchorElement.innerHTML = "Please Wait...";
				$(anchorElement).toggleClass('disabled');
				//	 x.disabled=true;
				var url1 = "deleterequest/" + requestID;
				$.ajax({
					url: url1,
					type: "POST",
					cache: false,
					success: function(response) {
						document.getElementById("row" + requestID).remove();
					},
					error: function() {
						anchorElement.innerHTML = "Some exception occured";
					}
				});
			}
			
			function clearModal() {
				document.getElementById("approvePaperForm").reset();
			}
			
			var isAddPaperDirectly = false;
			function addPaperDirectly() {
				addCollegeOptions();
				clearModal();
				$('#paperApprovalModal').modal('show');
				isAddPaperDirectly = true;
			}
			
			function addPaperWithoutApproval(){
				$('#paperApprovalModal').modal('hide');
				var form = document.getElementById('approvePaperForm');
				var formData = new FormData(form);

				var xhr = getXMLHTTP();
				xhr.open('POST', "addPaperDirectly", true);
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						$('#successModal').modal('show');
					}
				}
				xhr.send(formData);
			}

			function approveRequest() {
				if (isAddPaperDirectly == true) {
					isAddPaperDirectly = false;
					addPaperWithoutApproval();
					return;
				}
				$('#paperApprovalModal').modal('hide');
				var paperElement = document.getElementById("modalPaper")
				if ( ! paperElement.value) {
					paperElement.parentNode.removeChild(paperElement);
				}
				var form = document.getElementById('approvePaperForm');
				var formData = new FormData(form);
				var xhr = getXMLHTTP();
				xhr.open('POST', "approvepaper", true);
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						var requestID = document.getElementById("modalRequestID");
						document.getElementById("row" + requestID.value).remove();
						$('#successModal').modal('show');
					}
				}
				xhr.send(formData);
			}

			function approveFormModal(requestID) {
				var url1 = "getrequest/" + requestID;
				$.ajax({
					url: url1,
					type: "GET",
					cache: false,
					success: function(response) {
						clearModal();
						var requestObj = JSON.parse(response);
						fillForm(requestObj);
						addCollegeOptions();
						$('#paperApprovalModal').modal('show');
					},
					error: function() {
						alert("Exception");
					}
				});
			}

			function addCollegeOptions() {
				var xhr = getXMLHTTP();
				xhr.open('GET', "colleges");
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						var collegeList = JSON.parse(xhr.responseText);
						var collegeOptions = document.getElementById("collegeList");
						for (var i = 0; i < collegeList.length; i++) {
							var option = document.createElement("option");
							option.setAttribute("value", collegeList[i].name);
							collegeOptions.appendChild(option);
						}
					}
				}
				xhr.send();
			}

			function addBranchOptions() {
				var data1 = "collegeID=" + document.getElementById("modalCollege").value;
				$.get("branches", {
						collegeName: document.getElementById("modalCollege").value
					},
					function(data) {
						var branchList = JSON.parse(data);
						var branchOptions = document.getElementById("branchList");
						for (var i = 0; i < branchList.length; i++) {
							var option = document.createElement("option");
							option.setAttribute("value", branchList[i].name);
							branchOptions.appendChild(option);
						}
					});
			}

			function addSubjectOptions() {
				var data1 = "branchID=" + document.getElementById("modalBranch").value;
				$.ajax({
					url: "subjects",
					data: data1,
					type: "GET",
					cache: false,
					success: function(response) {
						var subjectList = JSON.parse(response);
						var subjectOptions = document.getElementById("subjectList");
						for (var i = 0; i < subjectList.length; i++) {
							var option = document.createElement("option");
							option.setAttribute("value", subjectList[i].name);
							subjectOptions.appendChild(option);
						}
					},
					error: function() {
						alert("error");
					}
				});
			}

			function fillForm(requestObj) {
				clearModal();
				document.getElementById("modalRequestID").value = requestObj.requestID;
				document.getElementById("modalCollege").value = requestObj.college;
				document.getElementById("modalBranch").value = requestObj.branch;
				document.getElementById("modalSubject").value = requestObj.subject;
				document.getElementById("modalDescription").value = requestObj.description;
			}
		</script>
	</body>

	</html>