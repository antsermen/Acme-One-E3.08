<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.rotelam.list.label.code" path="code" width="20%"/>
	<acme:list-column code="inventor.rotelam.list.label.explanation" path="explanation" width="20%"/>
	<acme:list-column code="inventor.rotelam.list.label.subject" path="subject" width="20%"/>
	<acme:list-column code="inventor.rotelam.list.label.expenditure" path="expenditure" width="20%"/>
	<acme:list-column code="inventor.rotelam.list.label.moreInfo" path="moreInfo" width="20%"/>

	
</acme:list>