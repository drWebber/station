<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
    <c:when test="${empty subscriber}">
        <jsp:useBean id="subscriber"
            class="domain.user.Subscriber" />
        <c:set var="pageHeading" value="Создание абонента" />
        <c:set var="isCreation" value="true" />
    </c:when>
    <c:otherwise>
        <c:set var="pageHeading"
            value="Редактирование абонента ${subscriber.login}" />
        <c:set var="disabled" value="disabled" />
        <c:set var="isCreation" value="false" />
    </c:otherwise>
</c:choose>
<c:url var="urlSave" value="/subscriber/save.html" />
<c:url var="urlDelete" value="/subscriber/delete.html" />
<c:url var="urlAdminProfile" value="/administrator/view.html">
    <c:param name="id" value="${subscriber.administrator.id}" />
</c:url>

<u:html title="${pageHeading}" useDatePicker="true"
    pageHeading="${pageHeading}">
<div class="container-fluid user-edit">
    <form action="${urlSave}" method="post">
        <div class="input-group">
            <span class="input-group-addon">Логин</span> <input
                type="text" class="form-control" name="login"
                value="${subscriber.login}" ${disabled}>
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
                value="${subscriber.surname}">
        </div>
        <div class="input-group">
            <span class="input-group-addon">Имя</span>
            <input type="text" class="form-control" name="name"
                value="${subscriber.name}">
        </div>
        <div class="input-group">
            <span class="input-group-addon">Отчество</span>
            <input type="text" class="form-control" name="patronymic"
                value="${subscriber.patronymic}">
        </div>
        <div class="input-group">
            <span class="input-group-addon">Личный номер</span>
            <input type="text" class="form-control" name="passportId"
                value="${subscriber.passportId}">
        </div>
        <div class='input-group'>
            <span class="input-group-addon">Дата рождения</span>
            <input type='text' class="form-control" name="birthday"
                id='datetimepicker' value="${subscriber.birthDay}" />
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
            <label class="radio-inline"> <input type="radio"
                name="isActive" value="true"
                ${subscriber.active != false ? "checked": ""}>Активен
            </label>
            <label class="radio-inline"> <input type="radio"
                name="isActive" value="false"
                ${subscriber.active == "false" ? "checked": ""}>Заблокирован
            </label>
        </div>
        <div class="subscriber-edit-phone">
            <div class="col-xs-5">
                <label for="sel">Код города:</label> <select
                    class="form-control" id="sel" name="prefix">
                    <c:forEach var="prefix" items="${prefixes}">
                        <option ${prefix.id == subscriber.prefix.id 
                            ? "selected" 
                            : ""} value="${prefix.id}">
                            	0${prefix.id} ${prefix.city}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <c:choose>
            	<c:when test="${subscriber.phoneNum > 0}">
            		<c:set var="phone" value="${subscriber.phoneNum}"></c:set>
            	</c:when>
            	<c:otherwise>
            		<c:set var="phone" value="240000"></c:set>
            	</c:otherwise>
            </c:choose>
            <div class="col-xs-7">
                <label for="phoneNum">Телефон:</label>
                <input class="form-control" type="number" name="phoneNum"
                    value="${phone}" min="100" max="9999999" />
            </div>
        </div>
        <c:if test="${not isCreation}">
        <div class="subscriber-static">
	        <div class="form-group form-horizontal">
	            <label class="control-label col-sm-2">Администратор</label>
	            <div class="col-sm-10">
	                <p>
	                    <a href="${urlAdminProfile}" 
	                    		title="Профиль администратора">
	                        ${subscriber.administrator.surname}
	                        ${subscriber.administrator.name}
	                        ${subscriber.administrator.patronymic} </a>
	                </p>
	            </div>
	        </div>
        </div>
            <input name="id" value="${subscriber.id}" type="hidden">
        </c:if>
        <div class="form-group subscriber-submit">
            <button type="submit" class="btn btn-info" title="Сохранить">
                Сохранить</button>
            <c:if test="${not isCreation}">
                <button type="submit" class="btn btn-danger" title="Удалить"
                    formaction="${urlDelete}">Удалить</button>
            </c:if>
            <button type="reset" class="btn btn-default">Сброс</button>
            <input type="button" class="btn btn-default"
                onclick="history.back();" value="Назад" />
        </div>
    </form>
</div>
</u:html>