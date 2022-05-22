<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
		
		<jstl:if test="${acme:anyOf(command, 'show, update, delete')}">
		
			<acme:input-integer code="inventor.quantity.form.label.itemsNumber"
			path="number"/>
			
			<acme:input-textbox code="inventor.item.form.label.itemType" 
				path="item.itemType" readonly="true"/>
				
			<acme:input-textbox code="inventor.item.form.label.name"
				path="item.name" readonly="true"/>
				
			<acme:input-textbox code="inventor.quantity.item.form.label.code" 
				path="item.code" readonly="true"/>
				
			<acme:input-money code="inventor.item.form.label.retail-price"
				path="item.retailPrice" readonly="true" />
			
			<acme:input-textbox code="inventor.quantity.toolkit.form.label.code" 
				path="toolkit.code" readonly="true"/>
				
							
			</jstl:if>
				
			<jstl:if test="${toolkitPublished == false}">
				<acme:submit code="inventor.quantity.form.button.update"
					action="/inventor/quantity/update" />
				<acme:submit code="inventor.quantity.form.button.delete"
					action="/inventor/quantity/delete" />
			</jstl:if>
		
		
		<jstl:when test="${command == 'create'}">		
			<acme:input-select code="inventor.quantity.form.label.select.artifact" path="artifact.name">
				<jstl:forEach items="${artifacts}" var="optionArtifact">
					<acme:input-option code="${optionArtifact.name}" value="${optionArtifact.name}"/>
				</jstl:forEach>
			</acme:input-select>
			<acme:submit code="inventor.quantity.form.button.create" action="/inventor/quantity/create?toolkitId=${toolkitId}"/>			
		</jstl:when>	
</acme:form>