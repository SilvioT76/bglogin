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

<title>Modifica attività</title>
</head>
<body>
	<jsp:include page="Menu.jsp" /> 
	<h2>Modifica attività</h2>
	<form id="updateForm_${activity.id}" class="${activity.id}">
	<div class="form-group">
			<label for="id">ID:</label> <input type="text"
				class="form-control" id="id" name="id" value="${activity.id}" disabled>
		</div>
		<div class="form-group">
			<label for="name">Name:</label> <input type="text"
				class="form-control" id="name" name="name" value="${activity.name}">
		</div>
		<div class="form-group">
			<label for="date">Date:</label> <input type="text"
				class="form-control" id="date" name="date" value="${activity.date}">
		</div>
		<div class="form-group">
			<label for="owner">Owner:</label> <input type="text"
				class="form-control" id="owner" name="owner"
				value="${activity.owner}">
		</div>
		<button type=SUBMIT class="btn btn-info btn-lg">Modifica</button>
		<script> 	
 $("#updateForm_${activity.id}").submit(function (event) { 	
	 event.preventDefault(); 
	 var mydata = $("form#updateForm_${activity.id}").serialize();
	 /* alert($(this).attr("class"));  */	
	 $.ajax({ 	
		 url: '<%=request.getContextPath()%>/activities/'+$(this).attr("class"), 
		 type: 'POST', 
		 data: mydata,
		 complete: function() { 
			 window.location.replace("<%=request.getContextPath()%>/activities")
			 alert("Aggiornata!"); 	
			 }	
		 }); 
	  }) 		
	
 </script>
	</form>
	<br>
</body>
</html>