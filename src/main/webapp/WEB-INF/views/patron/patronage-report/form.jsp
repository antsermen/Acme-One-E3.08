<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	
<acme:input-textarea code="patron.patronageReport.list.label.memorandum" path="memorandum" />
		<acme:input-url code="patron.patronageReport.list.label.link" path="link" />
	
	<acme:input-moment code="patron.patronageReport.list.label.creationMoment" path="creationMoment"/>	
	
</acme:form>