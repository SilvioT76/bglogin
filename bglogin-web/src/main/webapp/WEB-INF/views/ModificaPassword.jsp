<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <spring:url value="/resources/css/login.css" var="loginCss" />
	<link href="${loginCss}" rel="stylesheet" />
	
	<spring:url value="/resources/images/ADS.png" var="logo" />
	<spring:url value="/resources/images/password.png" var="pwd" />
 
<title>Modifica Password</title>
<style>
#error_msg{color:red; font-weight:bold;}
</style>
<script>
$(document).ready(function(){
    var $submitBtn = $("#btn");
    var $passwordBox = $("#password");
    var $confirmBox = $("#confirm_password");
    var $errorMsg =  $('<span id="error_msg">Passwords do not match.</span>');

    // This is incase the user hits refresh - some browsers will maintain the disabled state of the button.
    $submitBtn.removeAttr("disabled");

    function checkMatchingPasswords(){
        if($confirmBox.val() != "" && $passwordBox.val != ""){
            if( $confirmBox.val() != $passwordBox.val() ){
                $submitBtn.attr("disabled", "disabled");
                $errorMsg.insertAfter($confirmBox);
            }
        }
    }

    function resetPasswordError(){
        $submitBtn.removeAttr("disabled");
        var $errorCont = $("#error_msg");
        if($errorCont.length > 0){
            $errorCont.remove();
        }  
    }


    $("#confirm_password, #password")
         .on("keydown", function(e){
            /* only check when the tab or enter keys are pressed
             * to prevent the method from being called needlessly  */
            if(e.keyCode == 13 || e.keyCode == 9) {
                checkMatchingPasswords();
            }
         })
         .on("blur", function(){                    
            // also check when the element looses focus (clicks somewhere else)
            checkMatchingPasswords();
        })
        .on("focus", function(){
            // reset the error message when they go to make a change
            resetPasswordError();
        })

});
</script>
</head>
<body>
<div id="divLogo"><img alt="ads" src=${logo} /></div>
 
  <div class="imgcontainer">
    <img src=${pwd} alt="Pwd" class="avatar">
    <h2>Modifica password</h2>
  </div>

<form method="POST" action="<%= request.getContextPath()%>/modPwd" id="form" name="form">
 <div class="container">
    <label><b>Temporary Password</b></label>
    <input type="password"  name="tempPwd" required><br/>
    
   <label><b>New Password</b></label>
    <input type="password"  name="password" id="password" required><br/>
    
    <label><b>Confirm Password</b></label>
    <input type="password"  name="confirm_password" id="confirm_password" required><br/>

    <button type="submit" id="btn" name="btn">Aggiorna</button>
    
  </div>
  <div class="container">
  	<a href="<%= request.getContextPath()%>/index">
    <button type="button" class="btn btn-primary">Indietro</button>
    </a>
  </div>
 
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
          <p>Nuovo utente inserito</p>
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