<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Список абонентов" pageHeading="Список абонентов">
<table class="table table-striped table-bordered">
    <thead>
        <tr>
            <th>Логин</th>
            <th>Фамилия</th>
            <th>Идентификационный номер</th>
            <th>Префикс</th>
            <th>Телефон</th>
            <th>Статус</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="subscriber" items="${subscribers}">
            <tr>
                <td>${subscriber.login}</td>
                <td>${subscriber.surname}</td>
                <td>${subscriber.passportId}</td>
                <td>${subscriber.prefix.id}</td>
                <td>${subscriber.phoneNum}</td>
                <td>${subscriber.active ? "Активен" : "Заблокирован"}
                </td>
                <td><c:url var="urlUserEdit"
                        value="/subscriber/edit.html">
                        <c:param name="id" value="${subscriber.id}" />
                    </c:url>
                    <a href="${urlUserEdit}"  
                        title="Редактировать профиль абонента">
                        <span class="glyphicon glyphicon-edit"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<form action="${pageContext.request.contextPath}/subscriber/edit.html">
    <button type="submit" class="btn btn-info" title="Создать абонента">Создать абонента</button>
</form>
</u:html>