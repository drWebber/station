<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="urlPay" value="/payment/pay.html" />
<c:set var="pageHeading" value="Счет-акт оказанных услуг СчР-${invoice.id}" />
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="current_month" value="${now}" pattern="MM.YYYY" />
<fmt:formatDate var="invoicingDate" value="${invoice.invoicingDate}" 
    pattern="dd.MM.YYYY" />

<u:html title="${pageHeading}" pageHeading="${pageHeading}">
<div class="row">
    <table class="table table-condensed">
        <tbody>
            <tr>
                <td>За расчетный период</td>
                <td>01.${current_month}-${invoicingDate}</td>
            </tr>
            <tr>
                <td>Плательщик:</td>
                <td>${currentUser.surname}
                    ${currentUser.name}
                    ${currentUser.patronymic}
                </td>
            </tr>
            <tr>
                <td>Адрес:</td>
                <td>${currentUser.address}</td>
            </tr>
        </tbody>
    </table>
</div>
<div class="row">
    <table class="table table-condensed table-striped">
        <thead>
            <tr>
                <th>Начисления</th>
                <th></th>
                <th>Всего</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Абонентская плата</td>
                <td></td>
                <td>
                    <fmt:formatNumber 
                       value="${invoice.subscriptionsDetail.majorFee}" 
                       minFractionDigits="2"/>
                </td>
            </tr>
            <tr>
                <td colspan="4"><i>Телефонные
                        соединения</i></td>
            </tr>
			<c:forEach var="details" items="${invoice.callsDetail}">
	            <tr>
	                <td>${details.rateType}</td>
	                <td>${details.formattedDuration}</td>
	                <td>
	                	<fmt:formatNumber 
	                		value="${details.duration/60*details.tariff}" 
                        	minFractionDigits="2"/>
                    </td>
	            </tr>
			</c:forEach>
            <tr>
                <td colspan="4"><i>Дополнительные услуги</i></td>
            </tr>
            <tr>
                <td>Ежемесячный платеж</td>
                <td></td>
                <td>
                    <fmt:formatNumber 
                       value="${invoice.subscriptionsDetail.fee}" 
                       minFractionDigits="2"/>
                </td>
            </tr>
            <tr>
                <td>Стоимость подключения</td>
                <td></td>
                <td>
                    <fmt:formatNumber 
	                   value="${invoice.subscriptionsDetail.subscriptionsCost}" 
	                   minFractionDigits="2"/>
                </td>
            </tr>
            <tr>
                <td><strong>Итого начислений:</strong></td>
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
    <em>* Начисления указаны в бел. рублях</em>
</p>
<div class="row text-right">
    <c:url var="urlPay" value="/payment/pay.html">
        <c:param name="id" value="${invoice.id}" />
    </c:url>
    <a href="${urlPay}" class="btn btn-info" role="button" 
        title="Оплатить счет">Оплатить</a>
</div>
<br />
</u:html>