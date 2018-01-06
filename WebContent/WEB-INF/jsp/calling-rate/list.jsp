<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:url var="urlCreate" value="/calling-rate/edit.html" />
<c:if test="${not empty currentUser}">
    <c:if test="${currentUser.role == 'ADMINISTRATOR'}">
        <c:set var="isAdmin" value="true" />
    </c:if>
</c:if> 
<u:html title="Список тарифных планов на телефонные звонки"
    pageHeading="Тарифные планы на телефонные звонки">
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th>Наименование</th>
                <th>Стоимость мин. разговора</th>
                <c:if test="${isAdmin}">
                    <th></th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="rate" items="${rates}">
                <tr>
                    <td>${rate.name}</td>
                    <td>${rate.rate}</td>
                    <c:if test="${isAdmin}">
                        <td><c:url var="urlEdit"
                                value="/calling-rate/edit.html">
                                <c:param name="id" value="${rate.id}" />
                            </c:url>
                            <a href="${urlEdit}">
                                <span class="glyphicon glyphicon-edit"></span>
                            </a>
        	           </td>
                   </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	<c:if test="${isAdmin}">
        <form action="${urlCreate}">
            <button type="submit" class="btn btn-info">Создать тариф</button>
        </form>
    </c:if>
</u:html>