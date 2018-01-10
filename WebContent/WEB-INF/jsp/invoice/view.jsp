<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="current_month" value="${now}" pattern="MM.YYYY" />
<fmt:formatDate var="invoicingDate" value="${invoice.invoicingDate}" 
    pattern="dd.MM.YYYY" />

<c:url var="urlPay" value="/payment/pay.html" />
<c:set var="pageHeading" value="Счет-акт оказанных услуг СчР-${invoice.id}" />
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
                <td>3.01</td>
            </tr>
            <tr>
                <td colspan="4"><i>Исходящие телефонные
                        соединения</i></td>
            </tr>
            <tr>
                <td>Местные</td>
                <td>00:41:01</td>
                <td>2.27</td>
            </tr>
            <tr>
                <td>Междугородние</td>
                <td>00:01:01</td>
                <td>1.35</td>
            </tr>
            <tr>
                <td>Сетей мобильных операторов</td>
                <td>00:00:01</td>
                <td>0.01</td>
            </tr>
            <tr>
                <td colspan="4"><i>Входящие телефонные
                        соединения</i></td>
            </tr>
            <tr>
                <td>Местные</td>
                <td>00:41:01</td>
                <td>0.35</td>
            </tr>
            <tr>
                <td>Междугородние</td>
                <td>00:01:01</td>
                <td>1.01</td>
            </tr>
            <tr>
                <td>Сетей мобильных операторов</td>
                <td>00:00:01</td>
                <td>0</td>
            </tr>
            <tr>
                <td colspan="4"><i>Дополнительные услуги</i></td>
            </tr>
            <tr>
                <td>Абонентская плата</td>
                <td></td>
                <td>1.17</td>
            </tr>
            <tr>
                <td>Стоимость подключения</td>
                <td></td>
                <td>10.01</td>
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
    <a href="#" class="btn btn-info" role="button" 
        title="Оплатить счет">Оплатить</a>
</div>
<br />
</u:html>