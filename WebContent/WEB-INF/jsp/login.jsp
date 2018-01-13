<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:url var="bsCssUrl" value="/css/bootstrap.min.css" />
<c:url var="stylesheetCssUrl" value="/css/style.css" />

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="login.title"/> | Telephone Exchange 51</title>
    <link rel="stylesheet" href="${bsCssUrl}">
    <link rel="stylesheet" href="${stylesheetCssUrl}">
</head>
<body class="container-fluid">
    <div class="login-layout">
        <div class="row text-center bg-primary">
            <h1>Station 51</h1>
        </div>
        <div class="row">
            <div class="login-input-wrapper">
            	<c:if test="${not empty message}">
					<div class="row login-error text-danger bg-danger">
						<p>
							<span class="glyphicon 
								glyphicon-remove-circle"></span> 
							<fmt:message key="login.authorizationFail"/>
						</p>
					</div>
				</c:if>
                <form method="post">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-user"></i>
                        </span>
                        <fmt:message var="loginPlaceholder" 
                        		key="login.loginPlaceholder"/>
                        <input type="text" class="form-control" name="login"
                            	placeholder="${loginPlaceholder}">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-lock"></i>
                        </span>
                        <fmt:message var="password" key="login.password"/>
                        <input type="password" class="form-control"
                            	name="password" placeholder="${password}">
                    </div>
                    <div class="form-group">
                        <label for="sel">
                        	<fmt:message key="login.desiredRole"/>
                        </label>
                        <select class="form-control" id="sel" name="role">
                            <option value="SUBSCRIBER">
                            	<fmt:message key="login.subscriber"/>
                            </option>
                            <option value="ADMINISTRATOR">
                            	<fmt:message key="login.administrator"/>
                            </option>
                        </select>
                    </div>
                    <div class="form-group text-right">
                    	<fmt:message var="back" key="login.back"/>
                        <input type="button" class="btn btn-link"
                            	onclick="history.back();" value="${back}" />
                        <fmt:message var="signIn" key="login.signIn"/>
                        <button type="submit" title="${signIn}" 
                            	class="btn btn-info">${signIn}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>