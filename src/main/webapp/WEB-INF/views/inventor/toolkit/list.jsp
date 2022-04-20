<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<jstl:forEach var="toolkit" items="${toolkits}">
		<acme:list-column code="toolkit.title" path="tittle" width="30%"/>
		<acme:list-column code="toolkit.description" path="description" width="40%"/>
		<acme:list-column code="toolkit.link" path="link" width="30%"/>	
	</jstl:forEach>	
</acme:list>