<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Configure Manager | Parameter</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
</head>
<body>
	<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="/" class="navbar-brand">Home</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="new-param">Add parameter</a></li>
					<li><a href="all-params">All parameters</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	
	<c:choose>
		<c:when test="${mode == 'MODE_ALLPARAMS'}">
			<div class="container text-center" id="paramsDiv">
				<h3>Parameter Manager</h3>
				<hr>
				<div class="table-responsive">
					<table class="table table-striped table-bordered text-left">
						<thead>
							<tr>
								<th>ID</th>
								<th>ENV_ID</th>
								<th>KEY</th>
								<th>VALUE</th>
								<th>DESCRIPTION</th>
								<th>createTime</th>
								<th>updateTime</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="parameter" items="${params}">
								<tr>
									<td>${parameter.id}</td>
									<td>${parameter.envId}</td>
									<td>${parameter.name}</td>
									<td>${parameter.value}</td>
									<td>${parameter.description}</td>
									<td>${parameter.createTime}</td>
									<td>${parameter.updateTime}</td>
									<th><a href="delete-param?id=${parameter.id}"><span
											class="glyphicon glyphicon-trash"></span></a></th>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:when>
		<c:when test="${mode == 'MODE_NEWPARAM'||mode == 'MODE_UPDATEPARAM'}">
			<div class="container text-center">
				<h3>Parameter Manager</h3>
				<hr>
				<form class="form-horizontal" method="post" action="save-param">
					<input type="hidden" name="id" value="${param.id}"> 
					<input type="hidden" name="createTime" value="${date}"> 
					<input type="hidden" name="updateTime" value="${date}"> 
					<input type="hidden" name="status" value="0">
					<div class="form-group">
						<label class="control-label col-md-3">ENVID</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="envId"
								value="${param.envId}">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">NAME</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="name"
								value="${param.name}">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">VALUE</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="value"
								value="${param.value}">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">DESCRIPTION</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="description"
								value="${param.description}">
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