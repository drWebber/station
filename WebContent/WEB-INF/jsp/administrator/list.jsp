<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:url var="urlCreate" value="/administrator/edit.html" />
<u:html title="Список администраторов"
    pageHeading="Список администраторов">
<div class="container-fluid">
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
	                    </c:url> <a href="${urlAdminEdit}" 
	                    	title="Редактировать профиль администратора">
	                    	<span class="glyphicon glyphicon-edit"></span>
	                </a></td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
	<form action="${urlCreate}">
	    <button type="submit" class="btn btn-info"
	    		title="Создать администратора">Создать администратора</button>
	</form>
</div>
</u:html>