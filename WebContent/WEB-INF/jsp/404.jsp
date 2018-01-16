<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message var="title" key="404.message"/>
<fmt:message var="heading" key="404.heading"/>
<u:html title="${title}" 
    pageHeading="${heading}">
	<div class="container-fluid">
		<p><fmt:message key="404.message"/></p>
	</div>
</u:html>