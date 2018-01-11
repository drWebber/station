<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>


<c:set var="pageHeading"
    value="Профиль администратора" />

<u:html title="${pageHeading}" useDatePicker="true"
    pageHeading="${pageHeading}">
<table class="table">
    <tbody>
        <tr>
            <td>ФИО</td>
            <td>${administrator.surname} ${administrator.name} ${administrator.patronymic}</td>
        </tr>
        <tr>
            <td>Должность</td>
            <td>${administrator.position}</td>
        </tr>
        <tr>
            <td>Состояние</td>
            <td>${administrator.active ? "Активен" : "Заблокирован"}</td>
        </tr>
    </tbody>
</table>
</u:html>