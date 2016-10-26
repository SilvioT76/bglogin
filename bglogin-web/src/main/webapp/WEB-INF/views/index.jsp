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
	
	<spring:url value="/resources/images/logo.png" var="logo" />
</head>
<body onload='document.loginForm.username.focus();'>
<jsp:include page="Menu.jsp" />
<div id="divLogo"><img alt="Logo" src="${logo}"/></div>
	<div id="divLogin">
		<h2>Login</h2>

		<c:if test="${not empty error}">
			<div class="error"><spring:message code="${error}"/></div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg"><spring:message code="${msg}"/></div>
		</c:if>

		<form name='loginForm' action="<c:url value='/index' />" method='POST'>
		    <table>
				<tr>
					<td><spring:message code="login.field.username"/></td>
					<td><input type='text' name='username' value=''></td>
				</tr>
				<tr>
					<td><spring:message code="login.field.password"/></td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2' style="height: 50px"></td>
				</tr>
				<tr>
			        <td colspan='2'>
                    	<input width="80%" name="submit" type="submit" value="<spring:message code="login.btn.validate"/>"/>
                    </td>
				</tr>
		   </table>
		   <h3><spring:eval expression="@environment.getProperty('build.environment')" /> v<spring:eval expression="@environment.getProperty('build.version')" /></h3>
		   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
</body>
</html>