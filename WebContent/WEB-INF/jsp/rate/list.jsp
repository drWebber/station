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
<fmt:message var="title" key="rate.list.title"/>
<fmt:message var="heading" key="rate.list.heading"/>
<u:html title="${title}"
    pageHeading="${heading}">
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th><fmt:message key="rate.list.name"/></th>
                <th><fmt:message key="rate.list.since"/></th>
                <th><fmt:message key="rate.list.tariff"/></th>
                <c:if test="${isAdmin}">
                    <th></th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="rate" items="${rates}">
                <tr>
                    <td><fmt:message key="${rate.type}"/></td>
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
                            <fmt:message var="edit" key="rate.list.edit"/>
                            <a href="${urlEdit}"
                                title="${edit}">
                                <span class="glyphicon glyphicon-edit"></span>
                            </a>
        	           </td>
                   </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</u:html>