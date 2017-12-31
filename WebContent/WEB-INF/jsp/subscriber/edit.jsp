<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>


<c:choose>
    <c:when test="${empty subscriber}">
		<jsp:useBean id="subscriber" class="station.domain.user.Subscriber" />
		<c:set var = "pageHeading" value = "Создание абонента" />
		<c:set var = "isCreation" value = "true" />
    </c:when>
    <c:otherwise>
		<c:set var = "pageHeading" value = "Редактирование абонента ${subscriber.user.login}" />
		<c:set var = "disabled" value = "disabled" />    	
		<c:set var = "isCreation" value = "false" />
    </c:otherwise>
</c:choose>

<u:html title="${pageHeading}" useDatePicker="true" pageHeading="${pageHeading}">
	<div class="row">
		<form class="col-lg-5" action="${pageContext.request.contextPath}/subscriber/save.html" method="post">
			<div class="input-group">
				<span class="input-group-addon">Логин</span>
				<input type="text" class="form-control" name="login"
					value="${subscriber.user.login}" ${disabled}>
			</div>
    		<c:if test="${empty subscriber.id}">
				<div class="input-group">
					<span class="input-group-addon">Пароль</span>
					<input type="password" class="form-control" name="password">
				</div>
			</c:if>
			<div class="input-group">
				<span class="input-group-addon">Фамилия</span>
				<input type="text" class="form-control" name="surname"
					value="${subscriber.user.surname}">
			</div>
			<div class="input-group">
				<span class="input-group-addon">Имя</span>
				<input type="text" class="form-control" name="name"
					value="${subscriber.user.name}">
			</div>
			<div class="input-group">
				<span class="input-group-addon">Отчество</span>
				<input type="text" class="form-control" name="patronymic"
					value="${subscriber.user.patronymic}">
			</div>
			<div class="input-group">
				<span class="input-group-addon">Личный номер</span>
				<input type="text" class="form-control" name="passportId"
					value="${subscriber.passportId}">
			</div>
			<div class='input-group'>
				<span class="input-group-addon">Дата рождения</span>
				<input type='text' class="form-control" name="birthday"
					id='datetimepicker' value="${subscriber.birthDay}"/>
			</div>
			<script type="text/javascript">
				$(function() {
					$('#datetimepicker').datetimepicker({
						format : "YYYY-MM-DD"
					});
				});
			</script>
			<div class="input-group">
				<span class="input-group-addon">Адрес</span>
				<input type="text" class="form-control" name="address"
					value="${subscriber.address}">
			</div>
			<div class="radio">
				<label class="radio-inline">
				<input type="radio" name="isActive" value="true" ${subscriber.user.active != false ? "checked": ""}>Активен</label>
				<label class="radio-inline">
				<input type="radio" name="isActive" value="false" ${subscriber.user.active == "false" ? "checked": ""}>Заблокирован</label>
			</div>
			<div class="input-group row">
				<div class="col-xs-5">
					<label for="sel">Код города:</label>
					<select class="form-control" id="sel" name="prefix">
						<c:forEach var="prefix" items="${prefixes}">
							<option ${prefix.id == subscriber.prefix.id ? "selected" : ""}>${prefix.id}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-xs-7">
					<label for="phoneNum">Телефон:</label>
					<input class="form-control" type="number" name="phoneNum" value="${subscriber.phoneNum}" />
				</div>
			</div>
			<div class="form-group form-horizontal">
				<label class="control-label col-sm-3">Администратор</label>
				<div class="col-sm-7">
					<p class="form-control-static">
						<a href="${pageContext.request.contextPath}/administrator/view.html?id=${subscriber.administrator.id}">
							${subscriber.administrator.user.surname} 
							${subscriber.administrator.user.name} 
							${subscriber.administrator.user.patronymic}
						</a>
					</p>
				</div>
			</div>
        	<c:if test="${not isCreation}">
        		<input name="id" value="${subscriber.id}" type="hidden">
        	</c:if>
			<div class="form-group">
					<button type="submit" class="btn btn-info">Сохранить</button>
					<c:if test="${not isCreation}">
						<button type="submit" class="btn btn-danger" formaction="${pageContext.request.contextPath}/subscriber/delete.html">Удалить</button>
					</c:if>
					<button type="reset" class="btn btn-default">Сброс</button>
					<input type="button" class="btn btn-default" onclick="history.back();" value="Назад"/>
			</div>
		</form>
	</div>
</u:html>