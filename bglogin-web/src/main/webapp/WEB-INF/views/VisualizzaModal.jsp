<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li><a href="Index.html">Home</a></li>
			<li><a href="Inserimento.html">Inserimento attività</a></li>
			<li class="active"><a href="Visualizza.html">Visualizza
					attività</a></li>
			<li><a href="Modifica.html">Modifica attività</a></li>
			<li><a href="Elimina.html">Elimina attività</a></li>
		</ul>
	</div>
	</nav>
	<div class="container">
		<div class="row">

			<div class="col-md-12">
				<h4>Tabella attività</h4>
				<div class="table-responsive">


					<table class="table table-bordred table-striped">

						<thead>
							<tr class="success">
								<th class="">ID</th>
								<th class="">Name</th>
								<th class="">Date</th>
								<th class="">Owner</th>
								<th>Edit</th>
								<th>Delete</th>
						</thead>
						<tbody>

							<c:forEach items="${activities}" var="activity">
								<tr class="danger">
									<td><c:out value="${activity.id}" /></td>
									<td><c:out value="${activity.name}" /></td>
									<td><c:out value="${activity.date}" /></td>
									<td><c:out value="${activity.owner}" /></td>
									<td><p data-placement="top" data-toggle="tooltip"
											title="Edit">
											<button class="btn btn-primary btn-xs" data-title="Edit"
												data-toggle="modal" data-target="#edit/${activity.id} }">
												<span class="glyphicon glyphicon-pencil"></span>
											</button>
										</p></td>
									<td><p data-placement="top" data-toggle="tooltip"
											title="Delete">
											<button class="btn btn-danger btn-xs" data-title="Delete"
												data-toggle="modal" data-target="#delete">
												<span class="glyphicon glyphicon-trash"></span>
											</button>
										</p></td>
								</tr>
							</c:forEach>

						</tbody>

					</table>

				</div>
			</div>
		</div>


		<div class="modal fade" id="edit/${activity.id}" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content"></div>
			</div>
			<div class="modal-dialog">
				<div class="modal-content"></div>
			</div>
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true" class="">× </span><span class="sr-only">Close</span>

						</button>
						<h4 class="modal-title" id="myModalLabel">Modifica attività</h4>

					</div>
					<div class="modal-body">
			<form>
            <div class="form-group">
              <label for="name" class="form-control-label">Name:</label>
              <input type="text" class="form-control" id="name" value="${activity.name}">
            </div>
            <div class="form-group">
              <label for="date" class="form-control-label">Date:</label>
              <input type="text" class="form-control" id="date" value="${activity.date}">
            </div>
            <div class="form-group">
              <label for="owner" class="form-control-label">Owner:</label>
              <input type="text" class="form-control" id="owner" value="${activity.owner}">
            </div>
          </form></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<form method="POST" action="EditCheck/${activity.id}">
								 <input type="hidden" class="form-control" id="name" name="name" value="${activity.name}">
								 <input type="hidden" class="form-control" id="date" name="date" value="${activity.date}">
								 <input type="hidden" class="form-control" id="owner" name="owner" value="${activity.owner}">
								<button type="SUBMIT" class="btn btn-primary" action="Aggiorna"
							method="POST">Edit</button>
							</form>
						</div>
				</div>
			</div>
		</div>



		<div class="modal fade" id="delete" tabindex="-1" role="dialog"
			aria-labelledby="edit" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						</button>
						<h4 class="modal-title custom_align" id="Heading">Elimina
							attività</h4>
					</div>
					<div class="modal-body">

						<div class="alert alert-danger">
							<span class="glyphicon glyphicon-warning-sign"></span> Sicuro?
						</div>

					</div>
					<div class="modal-footer ">
						<button type="button" class="btn btn-success">
							<span class="glyphicon glyphicon-ok-sign"></span> Yes
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span> No
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
</body>
</html>