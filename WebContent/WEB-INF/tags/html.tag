<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" rtexprvalue="true"
    type="java.lang.String"%>
<%@attribute name="pageHeading" required="true" rtexprvalue="true"
    type="java.lang.String"%>
<%@attribute name="useDatePicker" required="false" rtexprvalue="true"
    type="java.lang.Boolean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url var="bsCssUrl" value="/css/bootstrap.min.css" />
<c:url var="stylesheetCssUrl" value="/css/style.css" />
<c:url var="bsCssDatePicker"
    value="/css/bootstrap-datetimepicker.min.css" />
<c:url var="urlJqueryJs" value="/js/jquery.min.js" />
<c:url var="urlBsMinJs" value="/js/bootstrap.min.js" />
<c:url var="urlMomentJs" value="/js/moment-with-locales.js" />
<c:url var="urlDatePickerJs" value="/js/bootstrap-datetimepicker.js" />
<c:url var="urlHome" value="/" />
<c:url var="urlLogin" value="/login.html" />
<c:url var="urlSubscriberList" value="/subscriber/list.html" />
<c:url var="urlAdministratorList" value="/administrator/list.html" />
<c:url var="urlProvidedServiceList" value="/provided-service/list.html" />
<c:url var="urlServiceList" value="/service/list.html" />

<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${title}| Telephone Exchange 51</title>
<link rel="stylesheet" href="${bsCssUrl}">
<link rel="stylesheet" href="${stylesheetCssUrl}">
<c:if test="${useDatePicker == true}">
    <link rel="stylesheet" href="${bsCssDatePicker}">
    <script src="${urlJqueryJs}"></script>
    <script src="${urlBsMinJs}"></script>
    <script src="${urlMomentJs}"></script>
    <script src="${urlDatePickerJs}"></script>
</c:if>
</head>
<body class="container-fluid">
    <header class="row">
        <nav class="navbar navbar-inverse navbar-static-top"
            role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle"
                        data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span> <span
                            class="icon-bar"></span> <span
                            class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${urlHome}">Station 51</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="${urlHome}">Главная</a></li>
                        <li><a href="#">Услуги</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${urlLogin}"><span
                                class="glyphicon glyphicon-log-in"></span>
                                Вход</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <section class="row content-layout">
        <div class="content col-xs-12 col-lg-10 col-lg-push-2">
            <article>
                <h1>${pageHeading}</h1>
                <jsp:doBody />
            </article>
        </div>
        <aside class="sidebar col-xs-12 col-lg-2 col-lg-pull-10">
            <h4>Главное меню</h4>
            <ul>
                <li><a href="#">Главная</a></li>
                <li><a href="${urlSubscriberList}">Абоненты</a></li>
                <li><a href="${urlAdministratorList}">Администраторы</a></li>
                <li><a href="${urlProvidedServiceList}">Предоставляемые
                        услуги</a></li>
				<li><a href="${urlServiceList}">Мои услуги</a></li>
            </ul>
        </aside>
    </section>
    <div class="row">
        <footer class="navbar-inverse navbar-fixed-bottom">
            <p>©2017-2018 "Station 51"</p>
        </footer>
    </div>
</body>
</html>