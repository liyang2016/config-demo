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

	<form action="/run" method="post">
		<div class="container text-center" id="paramsDiv">
			<h3>Parameters for Start</h3>
			<hr>
			<div class="table-responsive">
				<table class="table table-striped table-bordered text-left">
					<thead>
						<tr>
							<th>ID</th>
							<th>ENV_ID</th>
							<th>KEY</th>
							<th>VALUE</th>
							<th>TYPE</th>
							<th>DESCRIPTION</th>
							<th>createTime</th>
							<th>updateTime</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="parameter" items="${params}">
							<tr>
								<td>${parameter.id}</td>
								<td>${parameter.envId}</td>
								<td>${parameter.name}</td>
								<td>${parameter.value}</td>
								<td>${parameter.type}</td>
								<td>${parameter.description}</td>
								<td>${parameter.createTime}</td>
								<td>${parameter.updateTime}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="Run" />
			</div>
		</div>

	</form>
</body>
</html>