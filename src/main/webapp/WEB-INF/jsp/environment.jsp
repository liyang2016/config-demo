<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Configure Manager | Environment</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
</head>

<body>
	<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="/" class="navbar-brand">Home</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="new-env">Add environment parameter</a></li>
					<li><a href="all-envs">All environment parameters</a></li>
				</ul>
			</div>
		</div>
	</div>

	<c:choose>
		<c:when test="${mode == 'MODE_ALLENVS'}">
			<div class="container text-center" id="envsDiv">
				<h3>Environment Manager</h3>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered text-left">
						<thead>
							<tr>
								<th>ID</th>
								<th>APP_ID</th>
								<th>APPENV</th>
								<th>createTime</th>
								<th>updateTime</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="env" items="${envs}">
								<tr>
									<td>${env.id}</td>
									<td>${env.appId}</td>
									<td>${env.appenv}</td>
									<td>${env.createTime}</td>
									<td>${env.updateTime}</td>
									<th><a href="delete-env?id=${env.id}"><span class="glyphicon glyphicon-trash"></span></a></th>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_NEWENV'||mode == 'MODE_UPDATEENV'}">
			<div class="container text-center">
				<h3>Environment Manager</h3>
				<hr>
				<form class="form-horizontal" method="post" action="save-env">
					<input type="hidden" name="id" value="${env.id}">
					<input type="hidden" name="createTime" value="${date}">
					<input type="hidden" name="updateTime" value="${date}">
					<input type="hidden" name="status" value="0">
					<div class="form-group">
						<label class="control-label col-md-3">APPID</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="appId"
								value="${env.appId}">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">APPENV</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="appenv"
								value="${env.appenv}">
						</div>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-primary" value="Save" />
					</div>
				</form>
			</div>
		</c:when>
	</c:choose>

	<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>