<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker({
    	dateFormat: 'dd/mm/yy'});
  } );
  </script>

<title>Inserimento attività</title>
</head>
<body>
<jsp:include page="Menu.jsp" />
<h2>Inserisci nuova attività</h2>
<form method="POST" action="<%= request.getContextPath()%>/activities">
 <div class="form-group">
  <label for="name">Name:</label>
  <input type="text" class="form-control" id="name" name="name">
</div>
<div class="form-group">
  <label for="date">Date:</label>
  <input type="text" class="form-control" id="datepicker" name="date">
</div>
<div class="form-group">
  <label for="owner">Owner:</label>
  <input type="text" class="form-control" id="owner" name="owner">
</div>
 <button type="SUBMIT" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Inserisci</button>
</form>
<div class="container">
   <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Esito</h4>
        </div>
        <div class="modal-body">
          <p>Attività inserita</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
</body>
</html>