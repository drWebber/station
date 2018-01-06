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
        <c:forEach var="subscription" items="${subscriptions}">
            <tr>
                <td>${subscription.offer.name}</td>
                <td>${subscription.offer.monthlyFee}</td>
                <td>${subscription.connected}</td>
                <td>${subscription.disconnected}</td>
                <td>
                    <c:url var="urlReject" value="/subscription/reject.html">
                        <c:param name="id" value="${subscription.id}"></c:param>
                    </c:url>
                    <form action="${urlReject}" method="post">
                        <button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-edit"></span></button>
                    </form>
	           </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</u:html>