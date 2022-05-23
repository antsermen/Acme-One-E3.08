<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-textbox code="authenticated.system-configuration.form.label.acceptedCurrencies" path="acceptedCurrencies"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.systemCurrency" path="systemCurrency"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.strongSpamTerms" path="strongSpamTerms"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.strongSpamThreshold" path="strongSpamThreshold"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.weakSpamTerms" path="weakSpamTerms"/>
	<acme:input-textbox code="authenticated.system-configuration.form.label.weakSpamThreshold" path="weakSpamThreshold"/>

</acme:form>