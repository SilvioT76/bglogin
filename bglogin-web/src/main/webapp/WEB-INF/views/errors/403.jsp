<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page session="true"%>
<html>
<head>
	<title><spring:message code="login.page.title"/></title>
	<spring:url value="/resources/css/login.css" var="loginCss" />
	<link href="${loginCss}" rel="stylesheet" />
	
	<spring:url value="/resources/images/ADS.png" var="logo" />
</head>

<body onload='document.loginForm.username.focus();'>
	<div id="divLogo"><img alt="Logo" src="${logo}"/> <h4><spring:message code="error.403.message"/></h4></div>
	<h2><spring:message code="${errorMessage}"/></h2>
	<h3>
		<a href="<c:url value="/index" />"> <spring:message code="login.btn.home"/></a><br/>
	</h3>
</body>
</html>