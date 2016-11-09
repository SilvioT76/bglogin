<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title><spring:message code="login.page.title"/></title>
	<spring:url value="/resources/css/login.css" var="loginCss" />
	<link href="${loginCss}" rel="stylesheet" />
	
	<spring:url value="/resources/images/ADS.png" var="logo" />
	<spring:url value="/resources/images/accesso.png" var="key" />
</head>
<body>
<div id="divLogo"><img alt="ads" src=${logo} /></div>
 <form name='loginForm' action="<c:url value='/index' />" method='POST'>
  <div class="imgcontainer">
    <img src=${key} alt="Key" class="avatar">
  </div>
  
  	<c:if test="${not empty error}">
			<div class="error"><spring:message code="${error}"/></div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg"><spring:message code="${msg}"/></div>
		</c:if>

  <div class="container">
    <label><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="username" required>

    <label><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>
		<div class="container" style="background-color:#f1f1f1">
    	<button type="submit"  >Login</button>
    	<input type="checkbox" checked="checked"> Remember me
    	<span class="psw">Forgot <a href="<%= request.getContextPath()%>/recuperaPassword">password?</a></span>
    	</div>
  </div>

  <div class="container" >
  	<a href="<%= request.getContextPath()%>/newUser">
    <button type="button" class="btn btn-primary">Crea nuovo utente</button>
    </a>
    
  </div>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</body>
</html>