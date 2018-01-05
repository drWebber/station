<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:if test="${empty subscriber}">
	<jsp:useBean id="subscriber" class="station.domain.user.Subscriber" />
</c:if>

<u:html title="Перечень предоставляемых услуг"
    pageHeading="Предоставляемые услуги">
<form
    action="${pageContext.request.contextPath}/provided-service/edit.html">
    <button type="submit" class="btn btn-info">Создать услугу</button>
</form>
<h3>Дополнительные услуги</h3>
<div class="container-fluid">
    <c:set var="count" value="0" />
    <c:forEach var="service" items="${additionalServices}">
			${count % 2 == 0 ? '<div class="row">' : ''}
			<div class="col-sm-6 provided-services">
            <h4>${service.name}
                <c:url var="editUrl" value="/provided-service/edit.html">
                    <c:param name="id" value="${service.id}" />
                </c:url>
                <a href="${editUrl}"><span
                    class="glyphicon glyphicon-edit"></span></a>
            </h4>
            <p>${service.description}</p>
            <div class="row text-right">
                <div class="col-sm-12 subscription-conditions">
                    <p class="small">
                        Абонентская плата: ${service.monthlyFee}<span
                            class="glyphicon glyphicon-ruble"></span>
                    </p>
                    <p class="small">
                        Стоимость подключения:
                        <c:choose>
                            <c:when
                                test="${service.subscriptionRate > 0}">
							        ${service.subscriptionRate}
							        <span class="glyphicon glyphicon-ruble"></span>
                            </c:when>
                            <c:otherwise>
                                <span class="text-info">Бесплатно</span>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
                <c:url var="subscribeUrl" value="/service/subscribe.html">
                    <c:param name="id" value="${subscriber.id}" />
                    <c:param name="serviceId" value="${service.id}" />
                </c:url>
                <form action="${subscribeUrl}" method="post">
                    <button type="submit" class="btn btn-success">Подключить</button>
                </form>
            </div>
        </div>
        <c:set var="count" value="${count + 1}" />
			${count % 2 == 0 ? '</div>' : ''}
		</c:forEach>
    ${count % 2 == 0 ? '' : '</div>'}
</div>
<h3>Установка и пользование телефоном</h3>
<div class="container-fluid">
    <div class="row">
        <c:forEach var="service" items="${requiredServices}">
            <c:url var="editUrl" value="/provided-service/edit.html">
                <c:param name="id" value="${service.id}" />
            </c:url>
            <div class="col-sm-12">
                <h4>${service.name}
                    <a href="${editUrl}"><span
                        class="glyphicon glyphicon-edit"></span></a>
                </h4>
                <p>${service.description}
                    Абонентская плата ${service.monthlyFee}
                    <span class="glyphicon glyphicon-ruble"></span> в
                    месяц.
                </p>
            </div>
        </c:forEach>
    </div>
</div>
</u:html>