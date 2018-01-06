<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:url var="bsCssUrl" value="/css/bootstrap.min.css" />
<c:url var="stylesheetCssUrl" value="/css/style.css" />

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход на сайт | Telephone Exchange 51</title>
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
						<p><span class="glyphicon glyphicon-remove-circle"></span> Authorisation fail</p>
					</div>
				</c:if>
                <form method="post">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-user"></i>
                        </span>
                        <input type="text" class="form-control" name="login"
                            placeholder="Login">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="glyphicon glyphicon-lock"></i>
                        </span>
                        <input type="password" class="form-control"
                            name="password" placeholder="Password">
                    </div>
                    <div class="form-group">
                        <label for="sel">Войти как:</label>
                        <select class="form-control" id="sel" name="role">
                            <option value="SUBSCRIBER">Абонент</option>
                            <option value="ADMINISTRATOR">Администратор</option>
                        </select>
                    </div>
                    <div class="form-group text-right">
                        <input type="button" class="btn btn-link"
                            onclick="history.back();" value="Назад" />
                        <button type="submit" title="Войти в систему" 
                            class="btn btn-info">Вход</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>