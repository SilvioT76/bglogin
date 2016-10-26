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

<title>Visualizza</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<jsp:include page="Menu.jsp" />
	<div class="container">
		<h2>Attività</h2>
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
				
					<tr id=item_${activity.id} class="danger">
						<td><c:out value="${activity.id}" /></td>
						<td><c:out value="${activity.name}" /></td>
						<td><c:out value="${activity.date}" /></td>
						<td><c:out value="${activity.owner}" /></td>
						<td><form method="GET" action="<%= request.getContextPath()%>/activities/${activity.id}/edit">
								<button type="SUBMIT" class="btn btn-primary btn-xs">
									<span class="glyphicon glyphicon-pencil"></span>
								</button>
							</form>
							</td>
						<td><form id="deleteForm_${activity.id}" class="${activity.id}"> 
								<button type="submit" value="DELETE" class="btn btn-primary btn-xs btn-danger">
								<span class="glyphicon glyphicon-trash"></span>
								</button> 	
								<script> 	
 $("#deleteForm_${activity.id}").submit(function (event) { 	
	 event.preventDefault(); 
	/*  alert($(this).attr("class")); 	 */
	 $.ajax({ 	
		 url: '<%=request.getContextPath()%>/activities/'+$(this).attr("class"), 
		 type: 'DELETE', 
		 success: function(result) { 	
			 alert("Cancellata!"); 	
			 } 		
		 }); 	
	 var element = document.getElementById("item_${activity.id}"); 	
	 element.parentNode.removeChild(element); 	
	 }) 		
	
 </script>
								</form>
							</td>

					</tr>
				</c:forEach>

			</tbody>

		</table>

	</div>
</body>
</html>