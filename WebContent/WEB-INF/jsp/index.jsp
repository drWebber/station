<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
<c:url var="urlLogout" value="/logout.html" />
<c:url var="urlSubscriberList" value="/subscriber/list.html" />
<c:url var="urlAdministratorList" value="/administrator/list.html" />
<c:url var="urlOfferList" value="/offer/list.html" />
<c:url var="urlSubscriptionList" value="/subscription/list.html" />
<c:url var="urlCallingRateList" value="/rate/list.html" />
<c:url var="urlCallDial" value="/call/dial.html" />
<c:url var="urlInvoice" value="/invoice/control.html" />
<c:url var="urlInvoicesList" value="/invoice/list.html" />

<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Телефонная станция 51 | Национальный оператор электросвязи</title>
<link rel="stylesheet" href="${bsCssUrl}">
<link rel="stylesheet" href="${stylesheetCssUrl}">
<script src="${urlJqueryJs}"></script>
<script src="${urlBsMinJs}"></script>
<c:if test="${useDatePicker == true}">
    <link rel="stylesheet" href="${bsCssDatePicker}">
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
	               <c:choose>
	                   <c:when test="${empty currentUser}">
                            <ul class="nav navbar-nav navbar-right">
                                <li>
        	                       <a href="${urlLogin}">
        	                           <span class="glyphicon glyphicon-log-in"></span> Вход
        	                       </a>
                                </li>
                            </ul>
	                   </c:when>
	                   <c:otherwise>
	                       <div class="nav navbar-nav navbar-right">
	                           <span class="login-lable">Вы вошли как: </span>
	                           <button class="navbar-inverse dropdown-toggle login-navlink" type="button" data-toggle="dropdown">${currentUser.login}
	                           <span class="caret"></span></button>
	                           <ul class="dropdown-menu">
	                               <li><a href="${urlLogout}">Выход</a></li>
	                           </ul>
	                       </div>
                       </c:otherwise>
	               </c:choose>
                </div>
            </div>
        </nav>
    </header>
    <section>
        <div class="content col-xs-12">
            <article>
                    <div class="jumbotron text-center">
                        <h1>Station 51</h1> 
                        <p>Национальный оператор электросвязи</p> 
                    </div>
                    <div class="container-fluid text-center">
                      <h2>УСЛУГИ</h2>
                      <br>
                      <div class="row">
                        <div class="col-sm-4">
                            <span class="glyphicon glyphicon-phone-alt lead"></span>
                            <h4>Телефония</h4>
                            <p>Услуги телефонной связи</p>
                        </div>
                        <div class="col-sm-4">
                            <span class="glyphicon glyphicon-home lead"></span>
                            <h4>Для дома</h4>
                            <p>Работа с физическими лицами</p>
                        </div>
                        <div class="col-sm-4">
                            <span class="glyphicon glyphicon-lock lead"></span>
                            <h4>Для бизнеса</h4>
                            <p>Работа с юридическими лицами</p>
                        </div>
                        </div>
                        <br><br>
                      <div class="row">
                        <div class="col-sm-4">
                            <span class="glyphicon glyphicon-leaf lead"></span>
                            <h4>Техническая поддержка</h4>
                            <p>Техническая поддержка 24/7</p>
                        </div>
                        <div class="col-sm-4">
                            <span class="glyphicon glyphicon-certificate lead"></span>
                            <h4>Гарантия</h4>
                            <p>Гарантия качества обслуживания</p>
                        </div>
                        <div class="col-sm-4">
                            <span class="glyphicon glyphicon-ruble lead"></span>
                            <h4>Оплата услуг</h4>
                            <p>Оплата услуг онлайн</p>
                        </div>
                      </div>
                    </div>
            </article>
        </div>
    </section>
    <div class="row">
        <footer class="navbar-inverse navbar-fixed-bottom">
            <p>©2017-2018 "Station 51"</p>
        </footer>
    </div>
</body>
</html>