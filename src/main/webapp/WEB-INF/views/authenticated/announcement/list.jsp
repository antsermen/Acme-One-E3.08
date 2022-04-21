<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.announcement.list.creationMoment" path="creationMoment"/>
	<acme:list-column code="authenticated.announcement.list.title" path="title"/>
	<acme:list-column code="authenticated.announcement.list.critical" path="critical"/>
</acme:list>