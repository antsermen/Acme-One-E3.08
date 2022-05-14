<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-checkbox code="inventor.item.form.label.published" path="published" readonly="true"/>
	<acme:input-select code="inventor.item.form.label.itemType" path="itemType">
		<acme:input-option code="Component" value="COMPONENT" selected="${itemType == 'COMPONENT'}"/>
		<acme:input-option code="Tool" value="TOOL" selected="${itemType == 'TOOL'}"/>
	</acme:input-select>
	<acme:input-textbox code="inventor.item.form.label.name" path="name"/>	
	<acme:input-textbox placeholder="ABC-1234-D" code="inventor.item.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.item.form.label.technology" path="technology"/>
	<acme:input-textarea code="inventor.item.form.label.description" path="description"/>
	<acme:input-money code="inventor.item.form.label.retailPrice" path="retailPrice"/>
	<acme:input-textbox code="any.item.form.label.systemRetailPrice" path="moneyExchange" readonly="true"/>
	<acme:input-url code="inventor.item.form.label.link" path="link"/>


	
	<jstl:choose>
			<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false }">
							 
			<acme:submit code="inventor.item.form.button.update" action="/inventor/item/update"/>
			<acme:submit code="inventor.item.form.button.delete" action="/inventor/item/delete"/>
			<acme:submit code="inventor.item.form.button.publish" action="/inventor/item/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create' }">
			<acme:submit code="inventor.item.form.button.create" action="/inventor/item/create"/>
		</jstl:when>
	</jstl:choose>
	
</acme:form>