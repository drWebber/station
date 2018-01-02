<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Перечень предоставляемых услуг" pageHeading="Предоставляемые услуги">
    <table class="table table-striped table-bordered">
        <thead>
	        <tr>
	            <th>Наименование</th>
	            <th>Описание</th>
	            <th>Ежемесячный платеж</th>
	            <th>Стоимость подключения</th>
	            <th>Является обязательной</th>
	            <th></th>
	        </tr>
        </thead>
        <tbody>
	        <c:forEach var="service" items="${providedServices}">
	            <tr>
	                <td>${service.name}</td>
	                <td>${service.description}</td>
	                <td>${service.monthlyFee}</td>
	                <td>${service.subscriptionRate}</td>
	                <td>${service.required ? "да" : "нет"}</td>
	                <td>
	                    <c:url var="editUrl" value="/provided-service/edit.html">
	                        <c:param name="id" value="${service.id}"/>
	                    </c:url>
	                	<a href="${editUrl}">
	                		<span class="glyphicon glyphicon-edit"></span>
	                	</a>
	                </td>
	            </tr>
	        </c:forEach>
        </tbody>
    </table>
    <form action="${pageContext.request.contextPath}/provided-service/edit.html">
    	<button type="submit" class="btn btn-info">Создать услугу</button>
	</form>
</u:html>