<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="authenticated.systemConfiguration.form.label.systemCurrency" path="systemCurrency"/>
	<acme:input-textbox code="authenticated.systemConfiguration.form.label.acceptedCurrencies" path="acceptedCurrencies"/>

</acme:form>