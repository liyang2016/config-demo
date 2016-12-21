<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Configure Manager | Application</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="/" class="navbar-brand">Home</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="new-app">Add application</a></li>
					<li><a href="all-apps">All applications</a></li>
				</ul>
			</div>
		</div>
	</div>

	<c:choose>
		<c:when test="${mode == 'MODE_ALLAPPS'}">
			<div class="container text-center" id="appsDiv">
				<h3>Application Manager</h3>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered text-left">
						<thead>
							<tr>
								<th>ID</th>
								<th>NAME</th>
								<th>DESCRIPTION</th>
								<th>VERSION</th>
								<th>createTime</th>
								<th>updateTime</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="app" items="${apps}">
								<tr>
									<td>${app.id}</td>
									<td>${app.name}</td>
									<td>${app.description}</td>
									<td>${app.version}</td>
									<td>${app.createTime}</td>
									<td>${app.updateTime}</td>
									<th><a href="delete-app?id=${app.id}"><span
											class="glyphicon glyphicon-trash"></span></a></th>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>

		<c:when test="${mode == 'MODE_NEWAPP'||mode == 'MODE_UPDATEAPP'}">
			<div class="container text-center">
				<h3>Application Manager</h3>
				<hr>
				<form class="form-horizontal" method="post" action="save-app">
					<input type="hidden" name="id" value="${app.id}"> <input
						type="hidden" name="createTime" value="${date}"> <input
						type="hidden" name="updateTime" value="${date}"> <input
						type="hidden" name="status" value="0">
					<div class="form-group">
						<label class="control-label col-md-3">NAME</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="name"
								value="${app.name}">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">DESCRIPTION</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="description"
								value="${app.description}">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">VERSION</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="version"
								value="${app.version}">
						</div>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-primary" value="Save" />
					</div>
				</form>
			</div>
		</c:when>
	</c:choose>
</body>
</html>