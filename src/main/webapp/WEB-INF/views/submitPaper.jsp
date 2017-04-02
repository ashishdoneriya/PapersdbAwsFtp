<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<html>

	<head>
		<meta charset="utf-8">
		<title>Tutorials Point</title>
		<script src="http://code.jquery.com/jquery-1.11.3.min.js">
		</script>
		<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js">
		</script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js">
		</script>
<!-- 		<link href="bootstrapMaterial/css/ripples.min.css" rel="stylesheet"> -->
<!-- 		<link href="bootstrapMaterial/css/roboto.min.css" rel="stylesheet"> -->
<!-- 		<link href="bootstrapMaterial/css/material-fullpalette.min.css" rel="stylesheet"> -->
<!-- 		<script src="bootstrapMaterial/js/material.min.js" type="text/javascript"> -->
<!-- 		</script> -->
<!-- 		<script src="bootstrapMaterial/js/bootstrap-filestyle.min.js" type="text/javascript"> -->
<!-- 		</script> -->
<!-- 		<script src="bootstrapMaterial/js/ripples.min.js" type="text/javascript"> -->
<!-- 		</script> -->
	</head>

	<body>
		<script>
			//    $(":file").filestyle({input: false});
		</script>
		<div class="container">
			<div class="center">

				<form class="form-horizontal" method="POST" enctype="multipart/form-data" action="submitpaper">
					<fieldset>

						<!-- Form Name -->
						<legend>
							Submit Paper
						</legend>

						<!-- File Button -->
						<div class="form-group">
							<label class="col-md-4 control-label" for="papers">
								Add Paper
							</label>
							<div class="col-md-4">
								<input name="papers" id="papers" type="file" class="filestyle" multiple data-input="false">
							</div>
						</div>

						<!-- Text input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="college">
								College
							</label>
							<div class="col-md-4">
								<input id="college" name="college" type="text" placeholder="College" class="form-control input-md">
							</div>
						</div>

						<!-- Text input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="branch">
								Branch
							</label>
							<div class="col-md-4">
								<input id="branch" name="branch" type="text" placeholder="Branch" class="form-control input-md">


							</div>
						</div>

						<!-- Text input-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="subject">
								Subject
							</label>
							<div class="col-md-4">
								<input id="subject" name="subject" type="text" placeholder="subject" class="form-control input-md">


							</div>
						</div>

						<!-- Textarea -->
						<div class="form-group">
							<label class="col-md-4 control-label" for="description">
								Any Comments
							</label>
							<div class="col-md-4">
								<textarea class="form-control" id="description" name="description"></textarea>
							</div>
						</div>

						<!-- Button -->
						<div class="form-group">
							<label class="col-md-4 control-label" for="submit">
							</label>
							<div class="col-md-4">
								<input type="submit" name="submit" class="btn btn-success">
							</div>
						</div>

					</fieldset>
				</form>


			</div>
		</div>
		<script type="text/javascript">
		</script>
	</body>

	</html>