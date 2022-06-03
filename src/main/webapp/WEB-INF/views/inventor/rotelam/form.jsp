<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="inventor.rotelam.list.label.code" path="code" width="20%"/>
	<acme:input-textarea code="inventor.rotelam.list.label.creationMoment" path="creationMoment" width="20%"/>
	<acme:input-textarea code="inventor.rotelam.list.label.explanation" path="explanation" width="20%"/>
	<acme:input-textarea code="inventor.rotelam.list.label.subject" path="subject" width="20%"/>
	<acme:input-textbox code="inventor.rotelam.list.label.period" path="period" width="20%"/>
	<acme:input-textbox code="inventor.rotelam.list.label.moreInfo" path="moreInfo" width="20%"/>
	

	<jstl:if test="${command == 'show'}">
		<acme:input-textbox code="inventor.rotelam.form.label.expenditure" path="expenditure" readonly="true"/>
	</jstl:if>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false }"> 
			<acme:submit code="inventor.rotelam.form.button.update" action="/inventor/toolkit/update"/>
			<acme:submit code="inventor.rotelam.form.button.delete" action="/inventor/toolkit/delete"/>
			<acme:submit code="inventor.rotelam.form.button.publish" action="/inventor/toolkit/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.toolkit.form.button.create" action="/inventor/rotelam/create"/>
		</jstl:when>	
	</jstl:choose>
	
	
</acme:form>