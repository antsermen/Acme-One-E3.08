<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.quantity.item.list.label.itemType" path="item.itemType" width="20%"/>
	<acme:list-column code="inventor.quantity.item.list.label.name" path="item.name" width="20%"/>
	<acme:list-column code="inventor.quantity.item.list.label.retail-price" path="item.retailPrice" width="20%"/>
	<acme:list-column code="inventor.quantity.item.list.label.itemsNumber" path="itemsNumber" width="20%"/>
		
</acme:list>
