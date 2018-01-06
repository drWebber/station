<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Перечень подключенных услуг" pageHeading="Мои услуги">
<table class="table table-striped table-bordered">
    <thead>
        <tr>
            <th>Наименование услуги</th>
            <th>Ежемесячный платеж</th>
            <th>Дата подключения</th>
            <th>Дата отключения</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="service" items="${services}">
            <tr>
                <td>${service.providedService.name}</td>
                <td>${service.providedService.monthlyFee}</td>
                <td>${service.connected}</td>
                <td>${service.disconnected}</td>
                <td>
                    <c:url var="urlUnsubscribe" value="/service/unsubscribe.html">
                        <c:param name="id" value="${service.id}"></c:param>
                    </c:url>
                    <form action="${urlUnsubscribe}" method="post">
                        <button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-edit"></span></button>
                    </form>
	           </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</u:html>