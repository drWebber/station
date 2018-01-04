<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>


<c:choose>
    <c:when test="${empty providedService}">
        <jsp:useBean id="providedService"
            class="station.domain.service.ProvidedService" />
        <c:set var="pageHeading" value="Создание услуги" />
        <c:set var="isCreation" value="true" />
    </c:when>
    <c:otherwise>
        <c:set var="pageHeading"
            value="Редактирование услуги «${providedService.name}»" />
        <c:set var="isCreation" value="false" />
    </c:otherwise>
</c:choose>

<u:html title="${pageHeading}" pageHeading="${pageHeading}">
<div class="row">
    <form class="col-lg-8"
        action="${pageContext.request.contextPath}/provided-service/save.html"
        method="post">
        <div class="input-group">
            <span class="input-group-addon">Наименование</span>
            <input type="text" class="form-control" name="name"
                value="${providedService.name}">
        </div>
        <div class="input-group">
            <span class="input-group-addon">Описание</span>
            <textarea class="form-control" rows="5" name="description">${providedService.description}</textarea>
        </div>
        <div class="input-group">
            <span class="input-group-addon">
                Абонентская плата, BYN в мес.
            </span>
            <input type="number" class="form-control" name="monthlyFee" 
            value="${providedService.monthlyFee}" step="0.01">
        </div>
        <div class="input-group">
            <span class="input-group-addon">Стоимость
                подключения, BYN</span>
            <input type="number" class="form-control" name="subscriptionRate"
                value="${providedService.subscriptionRate}" step="0.01">
        </div>
        <div class="radio">
            <label class="radio-inline"> <input type="radio"
                name="required" value="true"
                ${providedService.required != false ? "checked": ""}>Обязательная
            </label>
            <label class="radio-inline"> <input type="radio"
                name="required" value="false"
                ${providedService.required == "false" ? "checked": ""}>Необязательная
            </label>
        </div>
        <c:if test="${not isCreation}">
            <input name="id" value="${providedService.id}" type="hidden">
        </c:if>
        <div class="form-group">
            <button type="submit" class="btn btn-info">Сохранить</button>
            <c:if test="${not isCreation}">
                <button type="submit" class="btn btn-danger"
                    formaction="${pageContext.request.contextPath}/provided-service/delete.html">Удалить</button>
            </c:if>
            <button type="reset" class="btn btn-default">Сброс</button>
            <input type="button" class="btn btn-default"
                onclick="history.back();" value="Назад" />
        </div>
    </form>
</div>
</u:html>