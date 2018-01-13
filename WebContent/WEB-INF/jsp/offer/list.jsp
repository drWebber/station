<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
	<c:when test="${currentUser.role == 'SUBSCRIBER'}">
		<c:set var="isUser" value="true" />
	</c:when>
	<c:when test="${currentUser.role == 'ADMINISTRATOR'}">
		<c:set var="isAdmin" value="true" />
	</c:when>
</c:choose>

<u:html title="Перечень предоставляемых услуг"
    pageHeading="Предоставляемые услуги">
<c:if test="${isAdmin}">
	<c:url var="offerEditUrl" value="/offer/edit.html" />
	<form action="${offerEditUrl}">
	    <button type="submit" class="btn btn-info" 
	    	title="Создать услугу">Создать услугу</button>
	</form>
</c:if>
<h3>Дополнительные услуги</h3>
<div class="container-fluid">
    <c:set var="count" value="0" />
    <c:forEach var="offer" items="${additionalOffers}">
			${count % 2 == 0 ? '<div class="row">' : ''}
			<div class="col-sm-6 offers">
            <h4>${offer.name}
            	<c:if test="${isAdmin}">
	                <c:url var="editUrl" value="/offer/edit.html">
	                    <c:param name="id" value="${offer.id}" />
	                </c:url>
	                <a href="${editUrl}" title="Редактировать услугу"><span
	                    class="glyphicon glyphicon-edit"></span></a>
                </c:if>
            </h4>
            <p>${offer.description}</p>
            <div class="row text-right">
                <div class="col-sm-12 subscription-conditions">
                    <p class="small">
                        Абонентская плата: ${offer.monthlyFee}<span
                            class="glyphicon glyphicon-ruble"></span>
                    </p>
                    <p class="small">
                        Стоимость подключения:
                        <c:choose>
                            <c:when test="${offer.subscriptionRate > 0}">
							        ${offer.subscriptionRate}
								<span class="glyphicon glyphicon-ruble"></span>
                            </c:when>
                            <c:otherwise>
                                <span class="text-info">Бесплатно</span>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
                <c:if test="${isUser}">
	                <c:url var="subscribeUrl" value="/subscription/accept.html">
	                    <c:param name="id" value="${currentUser.id}" />
	                    <c:param name="offerId" value="${offer.id}" />
	                </c:url>
	                <form action="${subscribeUrl}" method="post">
	                    <button type="submit" class="btn btn-success" 
	                    	title="Подключить услугу"> Подключить
	                    </button>
	                </form>
                </c:if>
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
        <c:forEach var="offer" items="${requiredOffers}">
            <div class="col-sm-12">
                <h4>${offer.name}
                	<c:if test="${isAdmin}">
			            <c:url var="editUrl" value="/offer/edit.html">
			                <c:param name="id" value="${offer.id}" />
			            </c:url>
                    	<a href="${editUrl}" title="Редактировать услугу">
                    		<span class="glyphicon glyphicon-edit"></span>
                        </a>
                    </c:if>
                </h4>
                <p>${offer.description}
                    Абонентская плата ${offer.monthlyFee}
                    <span class="glyphicon glyphicon-ruble"></span> в
                    месяц.
                </p>
            </div>
        </c:forEach>
    </div>
</div>
</u:html>