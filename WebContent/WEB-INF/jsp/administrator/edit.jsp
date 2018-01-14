<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>


<c:choose>
    <c:when test="${empty administrator}">
        <jsp:useBean id="administrator"
            class="domain.user.Administrator" />
        <c:set var="pageHeading" value="Создание администратора" />
        <c:set var="isCreation" value="true" />
    </c:when>
    <c:otherwise>
        <c:set var="pageHeading"
            value="Редактирование администратора ${administrator.login}" />
        <c:set var="disabled" value="disabled" />
        <c:set var="isCreation" value="false" />
    </c:otherwise>
</c:choose>
<c:url var="urlSave" value="/administrator/save.html" />
<c:url var="urlDelete" value="/administrator/delete.html" />

<u:html title="${pageHeading}" pageHeading="${pageHeading}">
<div class="row nopadding">
    <div class="row col-lg-8">
	<c:if test="${not empty param.message}">
		<div class="alert alert-danger fade in row">
			<a href="#" class="close" data-dismiss="alert" 
				aria-label="close">&times;</a>
		  <strong>Error!</strong> ${param.message}.
		</div>
	</c:if>
    <form action="${urlSave}" method="post">
        <div class="input-group row">
            <span class="input-group-addon">Логин</span>
            <input type="text" class="form-control" name="login"
                value="${administrator.login}" ${disabled}>
        </div>
        <c:if test="${empty administrator.id}">
            <div class="input-group">
                <span class="input-group-addon">Пароль</span>
                <input type="password" class="form-control" name="password">
            </div>
        </c:if>
        <div class="input-group row">
            <span class="input-group-addon">Фамилия</span>
            <input type="text" class="form-control" name="surname"
                value="${administrator.surname}">
        </div>
        <div class="input-group row">
            <span class="input-group-addon">Имя</span>
            <input type="text" class="form-control" name="name"
                value="${administrator.name}">
        </div>
        <div class="input-group row">
            <span class="input-group-addon">Отчество</span>
            <input type="text" class="form-control" name="patronymic"
                value="${administrator.patronymic}">
        </div>
        <div class="input-group row">
            <span class="input-group-addon">Личное дело</span>
            <input type="number" class="form-control" name="personalId"
                value="${administrator.personalId}">
        </div>
        <div class="input-group row">
            <span class="input-group-addon">Должность</span>
            <input type="text" class="form-control" name="position"
                value="${administrator.position}">
        </div>
        <div class="radio row">
            <label class="radio-inline"> <input type="radio"
                name="isActive" value="true"
                ${administrator.active != false ? "checked": ""}>Активен
            </label>
            <label class="radio-inline"> <input type="radio"
                name="isActive" value="false"
                ${administrator.active == "false" ? "checked": ""}>Заблокирован
            </label>
        </div>
        <c:if test="${not isCreation}">
            <input name="id" value="${administrator.id}" type="hidden">
        </c:if>
        <div class="form-group row">
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
</div>
</u:html>