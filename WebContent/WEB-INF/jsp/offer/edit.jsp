<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>


<c:choose>
    <c:when test="${empty offer}">
        <jsp:useBean id="offer"
            class="domain.service.Offer" />
        <c:set var="pageHeading" value="Создание услуги" />
        <c:set var="isCreation" value="true" />
    </c:when>
    <c:otherwise>
        <c:set var="pageHeading"
            value="Редактирование услуги «${offer.name}»" />
        <c:set var="isCreation" value="false" />
    </c:otherwise>
</c:choose>
<c:url var="urlSave" value="/offer/save.html" />
<c:url var="urlDelete" value="/offer/delete.html" />

<u:html title="${pageHeading}" pageHeading="${pageHeading}">
<div class="row">
    <form class="col-lg-8" action="${urlSave}" method="post">
        <div class="input-group">
            <span class="input-group-addon">Наименование</span>
            <input type="text" class="form-control" name="name"
                value="${offer.name}">
        </div>
        <div class="input-group">
            <span class="input-group-addon">Описание</span>
            <textarea class="form-control" rows="5" 
            	name="description">${offer.description}</textarea>
        </div>
        <div class="input-group">
            <span class="input-group-addon">
                Абонентская плата, BYN в мес.
            </span>
            <input type="number" class="form-control" name="monthlyFee" 
            value="${offer.monthlyFee}" step="0.01">
        </div>
        <div class="input-group">
            <span class="input-group-addon">Стоимость
                подключения, BYN</span>
            <input type="number" class="form-control" name="subscriptionRate"
                value="${offer.subscriptionRate}" step="0.01">
        </div>
        <div class="radio">
            <label class="radio-inline"> <input type="radio"
                name="required" value="true"
                ${offer.required != false ? "checked": ""}>Обязательная
            </label>
            <label class="radio-inline"> <input type="radio"
                name="required" value="false"
                ${offer.required == "false" ? "checked": ""}>Необязательная
            </label>
        </div>
        <c:if test="${not isCreation}">
            <input name="id" value="${offer.id}" type="hidden">
        </c:if>
        <div class="form-group">
            <button type="submit" class="btn btn-info">Сохранить</button>
            <c:if test="${not isCreation}">
                <button type="submit" class="btn btn-danger"
                    formaction="${urlDelete}">Удалить</button>
            </c:if>
            <button type="reset" class="btn btn-default">Сброс</button>
            <input type="button" class="btn btn-default"
                onclick="history.back();" value="Назад" />
        </div>
    </form>
</div>
</u:html>