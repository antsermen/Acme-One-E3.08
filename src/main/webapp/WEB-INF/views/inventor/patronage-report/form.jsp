<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
		
	<acme:input-textarea code="inventor.patronageReport.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="inventor.patronageReport.form.label.link" path="link"/>
	<acme:input-textbox code="${patronageId}" path="patronageCode" readonly="true"/>
<%-- 	<acme:input-select code="inventor.patronageReport.form.label.patronage" path="patronage"> --%>
	
<%-- 		<jstl:forEach items="${patronages}" var="p"> --%>
<%-- 			<acme:input-option code="${p.code}" value="${p.code}" selected="${p.code==patronage}"/>		 --%>
<%-- 		</jstl:forEach> --%>
<%-- 	</acme:input-select> --%>
	
	


<jstl:choose>

	<jstl:when test="${command == 'show'}">	
		<acme:input-moment code="inventor.patronageReport.form.label.creationMoment" path="creationMoment"/>
		<acme:input-textbox code="inventor.patronageReport.form.label.sequenceNumber" path="sequenceNumber"/>
		<acme:input-textbox code="inventor.patronageReport.form.label.serialNumber" path="serialNumber"/>
	</jstl:when>	
	
	<jstl:when test="${command == 'create' }">
			<acme:input-checkbox code="inventor.patronageReport.form.label.confirm" path="confirm"/>
			<acme:submit  code="inventor.patronageReport.form.button.create" action="/inventor/patronage-report/create"/> 
	</jstl:when>
	
</jstl:choose>	
</acme:form>