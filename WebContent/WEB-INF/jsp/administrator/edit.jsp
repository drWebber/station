<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>


<c:choose>
    <c:when test="${empty administrator}">
		<jsp:useBean id="administrator" class="station.domain.user.Administrator" />
		<c:set var = "pageHeading" value = "Создание администратора" />
		<c:set var = "isCreation" value = "true" />
    </c:when>
    <c:otherwise>
		<c:set var = "pageHeading" value = "Редактирование администратора ${administrator.user.login}" />
		<c:set var = "disabled" value = "disabled" />    	
		<c:set var = "isCreation" value = "false" />
    </c:otherwise>
</c:choose>

<u:html title="${pageHeading}" pageHeading="${pageHeading}">
	<div class="row">
		<form class="col-lg-5" action="${pageContext.request.contextPath}/administrator/save.html" method="post">
			<div class="input-group">
				<span class="input-group-addon">Логин</span>
				<input type="text" class="form-control" name="login"
					value="${administrator.user.login}" ${disabled}>
			</div>
    		<c:if test="${empty administrator.id}">
				<div class="input-group">
					<span class="input-group-addon">Пароль</span>
					<input type="password" class="form-control" name="password">
				</div>
			</c:if>
			<div class="input-group">
				<span class="input-group-addon">Фамилия</span>
				<input type="text" class="form-control" name="surname"
					value="${administrator.user.surname}">
			</div>
			<div class="input-group">
				<span class="input-group-addon">Имя</span>
				<input type="text" class="form-control" name="name"
					value="${administrator.user.name}">
			</div>
			<div class="input-group">
				<span class="input-group-addon">Отчество</span>
				<input type="text" class="form-control" name="patronymic"
					value="${administrator.user.patronymic}">
			</div>
			<div class="input-group">
				<span class="input-group-addon">Личное дело</span>
				<input type="text" class="form-control" name="personalId"
					value="${administrator.personalId}">
			</div>
			<div class='input-group'>
				<span class="input-group-addon">Должность</span>
				<input type="text" class="form-control" name="position"
					value="${administrator.position}">
			</div>
			<div class="radio">
				<label class="radio-inline">
				<input type="radio" name="isActive" value="true" ${administrator.user.active != false ? "checked": ""}>Активен</label>
				<label class="radio-inline">
				<input type="radio" name="isActive" value="false" ${administrator.user.active == "false" ? "checked": ""}>Заблокирован</label>
			</div>
        	<c:if test="${not isCreation}">
        		<input name="id" value="${administrator.id}" type="hidden">
        	</c:if>
			<div class="form-group">
					<button type="submit" class="btn btn-info">Сохранить</button>
					<c:if test="${not isCreation}">
						<button type="submit" class="btn btn-danger" formaction="${pageContext.request.contextPath}/administrator/delete.html">Удалить</button>
					</c:if>
					<button type="reset" class="btn btn-default">Сброс</button>
					<input type="button" class="btn btn-default" onclick="history.back();" value="Назад"/>
			</div>
		</form>
	</div>
</u:html>