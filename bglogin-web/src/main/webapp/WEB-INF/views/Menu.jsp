<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
      <ul class="nav navbar-nav">
      <li class="active"><a href="<c:url value="/logout" />">Logout</a></li>
      <li class="active"><a href="<%= request.getContextPath()%>/activities/new">Inserimento attività</a></li>
      <li class="active"><a href="<%= request.getContextPath()%>/activities">Visualizza attività</a></li>
      <li class="active"><a href="<%= request.getContextPath()%>/activities"><c:out value="${session.getAttribute(role)}" /></a></li>
    </ul>
    </ul>
  </div>
</nav>