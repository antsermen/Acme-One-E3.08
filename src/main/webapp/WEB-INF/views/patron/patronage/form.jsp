<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-select code="patron.patronage.form.label.status" path="status" readonly="true">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textbox code="patron.patronage.form.label.code" path="code" placeholder="ABC-123-D"/>	
	<acme:input-textarea code="patron.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="patron.patronage.form.label.creationDate" path="creationDate"/>
	<acme:input-moment code="patron.patronage.form.label.startDate" path="startDate"/>
	<acme:input-moment code="patron.patronage.form.label.deadline" path="deadline"/>
	<acme:input-url code="patron.patronage.form.label.info" path="info"/>
	<acme:input-textbox code="patron.patronage.form.label.patronProfile" path="patron" readonly="true"/>	
	<acme:input-textbox code="${command}" path="patron" readonly="true"/>	
	<acme:input-textbox code="${published}" path="patron" readonly="true"/>	
	<acme:input-textbox code="${command == published}" path="patron" readonly="true"/>	
 	<acme:input-select code="patron.patronage.form.label.inventorProfile" path="inventor">
		<jstl:forEach items="${inventors}" var="inv">
			<acme:input-option code="${inv.userAccount.username}" value="${inv.userAccount.username}" selected="${inv.userAccount.username == inventor}"/>			
		</jstl:forEach>
	</acme:input-select>
	
	<jstl:choose>
		<jstl:when test="${(command == 'show' || command == 'update' || command == 'delete' || command == 'publish')
							 && published == false }">
			<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
			<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
			<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create' }">
			<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>
		</jstl:when>
	</jstl:choose>
	
</acme:form>