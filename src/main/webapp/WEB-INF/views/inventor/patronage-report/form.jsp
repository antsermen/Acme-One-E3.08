<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="inventor.patronageReport.form.label.seqNumber" path="seqNumber"/>
	<acme:input-moment code="inventor.patronageReport.form.label.creationMoment" path="creationMoment"/>		
	<acme:input-textarea code="inventor.patronageReport.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="inventor.patronageReport.form.label.link" path="link"/>

</acme:form>