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

<acme:form >

	<acme:input-textarea code="inventor.patronage.form.label.legalStuff" path="legalStuff"/>	
	<acme:input-textbox code="inventor.patronage.form.label.budget" path="budget"/>
	<jstl:if test="${command == 'show'}">
		<acme:input-textbox code="inventor.patronage.form.label.systemBudget" path="systemBudget"/>
	</jstl:if>
	<acme:input-textbox code="inventor.patronage.form.label.deadline" path="deadline"/>
	<acme:input-url code="inventor.patronage.form.label.info" path="info"/>
	<acme:input-textbox code="inventor.patronage.form.label.patronName" path="patronName" placeholder="${patron.userAccount.identity.name}"/>
	<acme:input-textbox code="inventor.patronage.form.label.patronSurname" path="patronSurname" placeholder="${patron.userAccount.identity.surname}"/>
	<acme:input-textbox code="inventor.patronage.form.label.patronCompany" path="patronCompany" placeholder="${patron.company}" />
	<acme:input-textbox code="inventor.patronage.form.label.patronStatement" path="patronStatement" placeholder="${patron.statement}"/>
	<acme:input-url code="inventor.patronage.form.label.patronInfo" path="patronInfo" placeholder="${patron.info}"/>
			<acme:input-textbox code="inventor.patronage.form.label.status" path="status" readonly="true"/>
	
			
	<acme:submit test="${acme:anyOf(command, 'show, update') && status == 'PROPOSED'}" code="inventor.patronage.form.button.accept" action="/inventor/patronage/accept"/>
		<acme:submit test="${acme:anyOf(command, 'show, update') && status == 'PROPOSED'}" code="inventor.patronage.form.button.denied" action="/inventor/patronage/denied"/>
	
			
</acme:form>
