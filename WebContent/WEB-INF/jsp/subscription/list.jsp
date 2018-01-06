<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Перечень подключенных услуг" pageHeading="Мои услуги">
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
                <td>${subscription.connected}</td>
                <td>
                    <c:url var="urlReject" value="/subscription/reject.html">
                        <c:param name="id" value="${subscription.id}"></c:param>
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
<h4>Архив услуг</h4>
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
                <td>${subscription.connected}</td>
                <td>${subscription.disconnected}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</u:html>