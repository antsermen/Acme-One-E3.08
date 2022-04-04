<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<jstl:forEach var="announcement" items="${announcements}">
		<acme:list-column code="announcement.creationMoment" path="creationMoment" width="20%"/>
		<acme:list-column code="announcement.title" path="title" width="40%"/>
		<acme:list-column code="announcement.critical" path="critical" width="40%"/>	
	</jstl:forEach>	
</acme:list>