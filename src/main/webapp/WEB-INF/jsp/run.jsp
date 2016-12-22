<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Configure Manager | Search</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
</head>
<body>
	<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="/" class="navbar-brand">Home</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="search-param">Search Parameter</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container text-center">
		<h3>Application Info</h3>
		<hr>
		<div class="table-responsive">
			<table class="table table-striped table-bordered text-left">
				<thead>
					<tr>
						<th>APPNAME</th>
						<th>APPVERSION</th>
						<th>APPENV</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${search.appName}</td>
						<td>${search.appVersion}</td>
						<td>${search.appenv}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="container text-center">
		<h3>Start Parameters</h3>
		<hr>
		<div class="table-responsive">
			<table class="table table-striped table-bordered text-left">
				<thead>
					<tr>
						<th>KEY</th>
						<th>VALUE</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="start" items="${sessionScope.startParams}">
						<tr>
							<td>${start.name}</td>
							<td>${start.value}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="container text-center">
		<h3>File Parameters</h3>
		<hr>
		<div class="table-responsive">
			<table class="table table-striped table-bordered text-left">
				<thead>
					<tr>
						<th>KEY</th>
						<th>VALUE</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="file" items="${sessionScope.fileParams}">
						<tr>
							<td>${file.name}</td>
							<td>${file.value}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>