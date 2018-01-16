<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message var="error" key="error.title"/>
<u:html title="${error}" pageHeading="${error}">
	<div class="container-fluid">
	    <p><fmt:message key="error.message"/></p>
	</div>
</u:html>