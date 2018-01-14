<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:url var="urlPay" value="/payment/pay.html" />
<fmt:message var="heading" key="invoice.view.heading"/>
<c:set var="pageHeading" value="${heading}-${invoice.id}" />
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="current_month" value="${now}" pattern="MM.YYYY" />
<fmt:formatDate var="invoicingDate" value="${invoice.invoicingDate}" 
    pattern="dd.MM.YYYY" />

<u:html title="${pageHeading}" pageHeading="${pageHeading}">
<div class="row">
    <table class="table table-condensed">
        <tbody>
            <tr>
                <td><fmt:message key="invoice.view.period"/></td>
                <td>01.${current_month}-${invoicingDate}</td>
            </tr>
            <tr>
                <td><fmt:message key="invoice.view.payer"/>:</td>
                <td>${currentUser.surname}
                    ${currentUser.name}
                    ${currentUser.patronymic}
                </td>
            </tr>
            <tr>
                <td><fmt:message key="invoice.view.address"/>:</td>
                <td>${currentUser.address}</td>
            </tr>
        </tbody>
    </table>
</div>
<div class="row">
    <table class="table table-condensed table-striped">
        <thead>
            <tr>
                <th><fmt:message key="invoice.view.charges"/></th>
                <th></th>
                <th><fmt:message key="invoice.view.total"/></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><fmt:message key="invoice.view.subscriptionFee"/></td>
                <td></td>
                <td>
                    <fmt:formatNumber 
                       value="${invoice.subscriptionsDetail.majorFee}" 
                       minFractionDigits="2"/>
                </td>
            </tr>
            <tr>
                <td colspan="4"><i>
                	<fmt:message key="invoice.view.phoneCalls"/>
                </i></td>
            </tr>
			<c:forEach var="details" items="${invoice.callsDetail}">
	            <tr>
	                <td><fmt:message key="${details.rateType}"/></td>
	                <td>${details.formattedDuration}</td>
	                <td>
	                	<fmt:formatNumber 
	                		value="${details.duration/60*details.tariff}" 
                        	minFractionDigits="2"/>
                    </td>
	            </tr>
			</c:forEach>
            <tr>
                <td colspan="4"><i>
                	<fmt:message key="invoice.view.additionalServices"/>
                </i></td>
            </tr>
            <tr>
                <td>
                	<fmt:message key="invoice.view.monthlyPayment"/>
                </td>
                <td></td>
                <td>
                    <fmt:formatNumber 
                       value="${invoice.subscriptionsDetail.fee}" 
                       minFractionDigits="2"/>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="invoice.view.subscriptionCost"/></td>
                <td></td>
                <td>
                    <fmt:formatNumber 
	                   value="${invoice.subscriptionsDetail.subscriptionsCost}" 
	                   minFractionDigits="2"/>
                </td>
            </tr>
            <tr>
                <td><strong>
                	<fmt:message key="invoice.view.amount"/>
                </strong></td>
                <td></td>
                <td>
                    <strong><fmt:formatNumber value="${invoice.amount} " 
                                minFractionDigits="2"/>
                            <span class="glyphicon glyphicon-ruble"></span>
                    </strong>
                </td>
            </tr>
        </tbody>
    </table>
</div>
<p class="small">
    <em>* <fmt:message key="invoice.view.note"/></em>
</p>
<div class="row text-right">
    <c:url var="urlPay" value="/payment/pay.html">
        <c:param name="id" value="${invoice.id}" />
    </c:url>
    <fmt:message var="billPayment" key="invoice.view.billPayment"/>
    <a href="${urlPay}" class="btn btn-info" role="button" 
        title="${billPayment}"><fmt:message key="invoice.view.pay"/></a>
</div>
<br />
</u:html>