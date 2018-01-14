<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="current_month" value="${now}" pattern="MMMM" />

<u:html title="Счета за звонки и услуги" pageHeading="Перечень счетов">
<c:if test="${not empty param.message}">
	<div class="alert alert-success fade in row">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>Success!</strong> ${param.message}.
	</div>
</c:if>
<h3>Неоплаченные счета</h3>
<c:choose>
<c:when test="${not empty unpaidInvoices}">
    <div class="container-fulid">
        <div class="row col-sm-12">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>№п/п</th>
                        <th>Дата выставления</th>
                        <th>Сумма</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="invoice" items="${unpaidInvoices}">
                        <tr>
                            <td>${invoice.id}</td>
                            <fmt:parseDate var="dateTime"
                                value="${invoice.invoicingDate}"
                                pattern="yyyy-MM-dd HH:mm:ss" />
                            <td>
                                <fmt:formatDate value="${dateTime}" 
                                    pattern="dd-MM-yyyy HH:mm:ss" /></td>
                            <td><fmt:formatNumber value="${invoice.amount} " 
                                    minFractionDigits="2"/>
                                <span class="glyphicon glyphicon-ruble"></span>
                            </td>   
                            <td>
                                <c:url var="urlPayment"
                                	value="/invoice/view.html">
                                	<c:param name="id" value="${invoice.id}" />
                                </c:url>
                                <form action="${urlPayment}" method="post">
                                    <button type="submit" 
                                        class="btn btn-link btn-xs">
                                        <span class="glyphicon 
                                            glyphicon-credit-card" 
                                            title="Перейти к оплате">
                                        </span>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:when>
    <c:otherwise>
        <p>Неоплаченных счетов нет.</p>
    </c:otherwise>
</c:choose>    
<c:if test="${not empty payments}">
    <h3>Оплаты</h3>
    <div class="container-fulid">
        <div class="row col-sm-12">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>№п/п</th>
                        <th>Дата выставления счета</th>
                        <th>Дата оплаты</th>
                        <th>Сумма</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="payment" items="${payments}">
                        <tr>
                            <td>${payment.id}</td>
                            <td>
                                <fmt:parseDate var="invoicingDate"
                                    value="${payment.invoice.invoicingDate}"
                                    pattern="yyyy-MM-dd HH:mm:ss" />
                                <fmt:formatDate value="${invoicingDate}" 
                                    pattern="dd-MM-yyyy HH:mm:ss" />
                            </td>
                            <td>
                                <fmt:parseDate var="paymentDate"
                                    value="${payment.date}"
                                    pattern="yyyy-MM-dd HH:mm:ss" />
                                <fmt:formatDate value="${paymentDate}" 
                                    pattern="dd-MM-yyyy HH:mm:ss" />
                            </td>
                            <td>
                                <fmt:formatNumber
                                    value="${payment.invoice.amount} " 
                                    minFractionDigits="2"/>
                                <span class="glyphicon glyphicon-ruble"></span>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:if>
</u:html>