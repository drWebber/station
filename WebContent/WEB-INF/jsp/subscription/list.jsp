<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<u:html title="Перечень подключенных услуг" pageHeading="Мои услуги">
<c:if test="${not empty param.message}">
	<div class="alert alert-success fade in row">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>Success!</strong> ${param.message}.
	</div>
</c:if>
<div class="container-fluid">
<c:choose>
	<c:when test="${empty activeSubscriptions}">
		<p>Подключенных услуг нет.</p>
	</c:when>
	<c:otherwise>
	<h4>Список подключенных услуг</h4>
	<table class="table table-striped table-bordered">
	    <thead>
	        <tr>
	            <th>Наименование услуги</th>
	            <th>Ежемесячный платеж</th>
	            <th>Дата подключения</th>
	            <th></th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach var="subscription" items="${activeSubscriptions}">
	            <tr>
	                <td>${subscription.offer.name}</td>
	                <td>${subscription.offer.monthlyFee}</td>
	                <c:set var="timestamp" 
		               value="${subscription.connected}"/>
		            <fmt:parseDate var="dateTime" value="${timestamp}"
	                    pattern="yyyy-MM-dd HH:mm:ss" />
	                <td><fmt:formatDate value="${dateTime}" 
	                    pattern="dd-MM-yyyy HH:mm:ss" /></td>
	                <td>
	                    <c:url var="urlReject"
	                    		value="/subscription/reject.html">
	                        <c:param name="id" value="${subscription.id}" />
	                    </c:url>
	                    <form action="${urlReject}" method="post">
	                        <button type="submit" class="btn btn-danger btn-xs"
	                            title="Отписаться">
	                            <span class="glyphicon glyphicon-remove"></span>
	                        </button>
	                    </form>
		           </td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
	</c:otherwise>
</c:choose>
</div>
<c:if test="${not empty archievedSubscriptions}">
<div class="container-fluid">
<h4>Архив подключенных услуг</h4>
<table class="table table-striped table-bordered">
    <thead>
        <tr>
            <th>Наименование услуги</th>
            <th>Дата подключения</th>
            <th>Дата отключения</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="subscription" items="${archievedSubscriptions}">
            <tr>
                <td>${subscription.offer.name}</td>
                <c:set var="timestamp" 
	               value="${subscription.connected}"/>
	            <fmt:parseDate var="dateTime" value="${timestamp}"
                    pattern="yyyy-MM-dd HH:mm:ss" />
                <td><fmt:formatDate value="${dateTime}" 
                    pattern="dd-MM-yyyy HH:mm:ss" /></td>
                <c:set var="timestamp" 
	               value="${subscription.disconnected}"/>
	            <fmt:parseDate var="dateTime" value="${timestamp}"
                    pattern="yyyy-MM-dd HH:mm:ss" />
                <td><fmt:formatDate value="${dateTime}" 
                    pattern="dd-MM-yyyy HH:mm:ss" /></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>
</c:if>
</u:html>