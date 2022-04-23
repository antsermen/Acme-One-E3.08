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

<acme:form>
	<acme:input-textbox code="any.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="any.toolkit.form.label.code" path="code"/>
	<acme:input-textbox code="any.toolkit.form.label.description" path="description"/>
	<acme:input-textbox code="any.toolkit.form.label.notes" path="notes"/>
	<acme:input-url code="any.toolkit.form.label.link" path="url"/>
	<jstl:forEach var="item" items="${items}">
		<acme:input-textbox code="any.toolkit.form.label.itemName" path="itemName" placeholder="${item.name}"/>
		<acme:input-textbox code="any.toolkit.form.label.itemType" path="itemType" placeholder="${item.itemType}"/>
		<acme:input-textarea code="any.toolkit.form.label.itemDescription" path="itemDescription" placeholder="${item.description}"/>
		<acme:input-textbox code="any.toolkit.form.label.itemRetailPrice" path="itemRetailPrice" placeholder="${item.retailPrice}"/>
	</jstl:forEach>
	
	
</acme:form>