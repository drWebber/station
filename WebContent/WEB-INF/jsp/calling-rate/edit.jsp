<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:url var="urlSave" value="/calling-rate/save.html" />
<c:choose>
    <c:when test="${empty callingRate}">
        <jsp:useBean id="callingRate"
            class="domain.service.CallingRate" />
        <c:set var="pageHeading" value="Создание тарифа" />
        <c:set var="isCreation" value="true" />
    </c:when>
    <c:otherwise>
        <c:set var="pageHeading"
            value="Редактирование тарифа ${callingRate.name}" />
        <c:set var="isCreation" value="false" />
    </c:otherwise>
</c:choose>

<u:html title="${pageHeading}" pageHeading="${pageHeading}">
<div class="row">
    <form class="col-lg-7" action="${urlSave}" method="post">
        <div class="input-group">
            <span class="input-group-addon">Наименование</span> <input
                type="text" class="form-control" name="name"
                value="${callingRate.name}">
        </div>
        <div class="input-group">
            <span class="input-group-addon">Стоимость минуты разговора</span>
            <input class="form-control" type="number" name="rate"
                    value="${callingRate.rate}" step="0.01" />
        </div>
        <c:if test="${not isCreation}">
            <input name="id" value="${rate.id}" type="hidden">
        </c:if>
        <br />
        <div class="form-group">
            <button type="submit" class="btn btn-info">Сохранить</button>
            <c:if test="${not isCreation}">
                <c:url var="urlDelete" value="/calling-rate/delete.html">
                    <c:param name="id" value="${callingRate.id}" />
                </c:url>
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