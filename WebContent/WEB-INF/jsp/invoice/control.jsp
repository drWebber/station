<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="urlCreate" value="/invoice/create.html" />
<c:url var="urlDelete" value="/invoice/delete.html" />
<c:if test="${not canCreate}">
    <c:set var="disabled" value="disabled"/>
</c:if>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="current_month" value="${now}" pattern="MMMM" />

<u:html title="Журнал «Управление счетами»" pageHeading="Управление счетами">
<h3>
    Выставление счетов за <span class="text-lowercase">${current_month}</span>
</h3>
<div class="container-fulid">
    <div class="row">
        <form action="${urlCreate}" method="post">
            <div class="row col-xs-12">
                <div class="input-group-btn">
                    <button class="btn btn-primary ${disabled}>" type="submit" 
                        title="${canCreate ? 'Выставить счета' : 
                        'Счета за текущий месяц уже выставлены'}" ${disabled}>
                        <span class="glyphicon glyphicon-piggy-bank"></span>
                        Выставить
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="container-fulid">
    <div class="row col-sm-12">
    <h3>Неоплаченные счета</h3>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>№п/п</th>
                    <th>Абонент</th>
                    <th>Состоние</th>
                    <th>Дата выставления</th>
                    <th>Сумма</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="invoice" items="${unpaid}">
                    <tr>
                        <td>${invoice.id}</td>
                        <c:url var="urlEdit" value="/subscriber/edit.html">
                            <c:param name="id"
                                value="${invoice.subscriber.id}" />
                        </c:url>
                        <td>
                            <a href="${urlEdit}" title="Редактировать профиль">
                                ${invoice.subscriber.surname}                                 
                            </a>
                        </td>
                        <td>${invoice.subscriber.active 
                            ? "активен" 
                            : "заблокирован"}
                        </td>
                        <fmt:parseDate var="dateTime"
                            value="${invoice.invoicingDate}"
                            pattern="yyyy-MM-dd HH:mm:ss" />
                        <td><fmt:formatDate value="${dateTime}" 
                            pattern="dd-MM-yyyy HH:mm:ss" /></td>
                        <td><fmt:formatNumber value="${invoice.amount}" 
                                minFractionDigits="2"/>
                        </td>
                        <td>
                            <c:url var="urlBan" value="/subscriber/ban.html">
                                <c:param name="id"
                                    value="${invoice.subscriber.id}" />
                            </c:url>
                            <a href="${urlBan}" title="Заблокировать">
                            	<span class="glyphicon glyphicon-thumbs-down">
                                </span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<c:if test="${not canCreate}">
    <div class="container-fulid">
        <div class="row col-xs-10 col-lg-10 bg-danger red-button">
            <h3 class="text-danger">
                <span class="glyphicon glyphicon-warning-sign"></span>
                Удаление оплат и выставленных счетов
            </h3>
            <p class="text-danger small">Are you sure?</p>
            <div class="row">
                <form action="${urlDelete}" method="post">
                    <div class="row col-xs-12">
                        <div class="input-group-btn">
                            <button class="btn btn-danger" type="submit"
                                title="Nevermind, just do it!">
                                Удалить
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</c:if>
</u:html>