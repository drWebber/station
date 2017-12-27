<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>


<u:html title="test">
	<p>${subscriber.address}</p>
	<p>${subscriber.user.name}</p>
	<p>${subscriber.user.role}</p>
</u:html>