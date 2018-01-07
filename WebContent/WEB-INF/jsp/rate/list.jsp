<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
                <th>Дата начала действия</th>
                <th>Тариф, руб/мин</th>
                <c:if test="${isAdmin}">
                    <th></th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="rate" items="${rates}">
                <tr>
                    <td>${rate.type}</td>
                    <c:set var="introdutionDate" 
                        value="${rate.introdutionDate}"/>
                    <fmt:parseDate var="dateTime" value="${introdutionDate}"
                                    pattern="yyyy-MM-dd HH:mm:ss" />
                    <td><fmt:formatDate value="${dateTime}" 
                        pattern="dd-MM-yyyy HH:mm:ss" /></td>
                    <td>${rate.tariff}</td>
                    <c:if test="${isAdmin}">
                        <td><c:url var="urlEdit"
                                value="/rate/edit.html">
                                <c:param name="id" value="${rate.id}" />
                            </c:url>
                            <a href="${urlEdit}"
                                title="Редактировать тарифный план">
                                <span class="glyphicon glyphicon-edit"></span>
                            </a>
        	           </td>
                   </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</u:html>