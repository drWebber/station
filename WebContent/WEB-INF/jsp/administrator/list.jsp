<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Список администраторов"
    pageHeading="Список администраторов">
<table class="table table-striped table-bordered">
    <thead>
        <tr>
            <th>Логин</th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Личное дело</th>
            <th>Должность</th>
            <th>Статус</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="administrator" items="${administrators}">
            <tr>
                <td>${administrator.login}</td>
                <td>${administrator.surname}</td>
                <td>${administrator.name}</td>
                <td>${administrator.patronymic}</td>
                <td>${administrator.personalId}</td>
                <td>${administrator.position}</td>
                <td>${administrator.active ? "Активен" : "Заблокирован"}
                </td>
                <td><c:url var="urlAdminEdit"
                        value="/administrator/edit.html">
                        <c:param name="id" value="${administrator.id}" />
                    </c:url> <a href="${urlAdminEdit}" title="Редактировать профиль администратора"> <span
                        class="glyphicon glyphicon-edit"></span>
                </a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<form
    action="${pageContext.request.contextPath}/administrator/edit.html">
    <button type="submit" class="btn btn-info" title="Создать администратора">Создать
        администратора</button>
</form>
</u:html>