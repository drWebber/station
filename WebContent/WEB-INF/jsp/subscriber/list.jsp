<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="test">
	<h2>Список абонентов</h2>
    <table class="table table-striped table-bordered">
        <thead>
	        <tr>
	            <th>Логин</th>
	            <th>Имя</th>
	            <th>Паспорт</th>
	            <th>Префикс</th>
	            <th>Телефон</th>
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
	            </tr>
	        </c:forEach>
        </tbody>
    </table>
</u:html>