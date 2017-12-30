<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Список абонентов" pageHeading="Список абонентов">
    <table class="table table-striped table-bordered">
        <thead>
	        <tr>
	            <th>Логин</th>
	            <th>Имя</th>
	            <th>Паспорт</th>
	            <th>Префикс</th>
	            <th>Телефон</th>
	            <th></th>
	        </tr>
        </thead>
        <tbody>
	        <c:forEach var="subscriber" items="${subscribers}">
	            <tr>
	                <td>${subscriber.user.login}</td>
	                <td>${subscriber.user.name}</td>
	                <td>${subscriber.passportId}</td>
	                <td>${subscriber.prefix.id}</td>
	                <td>${subscriber.phoneNum}</td>
	                <td>
	                    <c:url var="urlUserEdit" value="/subscriber/edit.html">
	                        <c:param name="id" value="${subscriber.id}"/>
	                    </c:url>
	                	<a href="${urlUserEdit}">
	                		<span class="glyphicon glyphicon-edit"></span>
	                	</a>
	                </td>
	            </tr>
	        </c:forEach>
        </tbody>
    </table>
    <form action="${pageContext.request.contextPath}/subscriber/edit.html">
    	<button type="submit" class="btn btn-info">Создать абонента</button>
	</form>
</u:html>