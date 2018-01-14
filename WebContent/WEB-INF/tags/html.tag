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
<c:url var="urlLogout" value="/logout.html" />
<c:url var="urlSubscriberList" value="/subscriber/list.html" />
<c:url var="urlAdministratorList" value="/administrator/list.html" />
<c:url var="urlOfferList" value="/offer/list.html" />
<c:url var="urlSubscriptionList" value="/subscription/list.html" />
<c:url var="urlRateList" value="/rate/list.html" />
<c:url var="urlCallDial" value="/call/dial.html" />
<c:url var="urlInvoice" value="/invoice/control.html" />
<c:url var="urlInvoicesList" value="/invoice/list.html" />

<c:choose>
	<c:when test="${currentUser.role == 'SUBSCRIBER'}">
		<c:set var="isUser" value="true" />
	</c:when>
	<c:when test="${currentUser.role == 'ADMINISTRATOR'}">
		<c:set var="isAdmin" value="true" />
	</c:when>
</c:choose>

<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${title} | Telephone Exchange 51</title>
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
	                   <li><a href="${urlOfferList}">Услуги</a></li>
	                   <li><a href="${urlRateList}">Тарифные планы</a></li>
	               </ul>
	               <c:choose>
	                   <c:when test="${empty currentUser}">
                            <ul class="nav navbar-nav navbar-right">
                                <li>
        	                       <a href="${urlLogin}">
        	                           <span class="glyphicon 
        	                               glyphicon-log-in"></span> Вход
        	                       </a>
                                </li>
                            </ul>
	                   </c:when>
	                   <c:otherwise>
	                       <div class="nav navbar-nav navbar-right">
	                           <span class="login-lable">Вы вошли как: </span>
	                           <button class="navbar-inverse dropdown-toggle 
	                           		login-navlink" type="button" 
	                           		data-toggle="dropdown">${currentUser.login}
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
    <section class="row content-layout">
        <div class="content col-xs-12 col-lg-9 col-lg-push-3">
            <article>
				<c:if test="${not empty param.err_msg}">
					<div class="alert alert-danger fade in row">
						<a href="#" class="close" data-dismiss="alert" 
							aria-label="close">&times;</a>
					  <strong>Error!</strong> ${param.err_msg}.
					</div>
				</c:if>
				<c:if test="${not empty param.succ_msg}">
					<div class="alert alert-success fade in row">
						<a href="#" class="close" data-dismiss="alert" 
							aria-label="close">&times;</a>
					  <strong>Success!</strong> ${param.succ_msg}.
					</div>
				</c:if>
                <h1>${pageHeading}</h1>
                <jsp:doBody />
            </article>
        </div>
        <aside class="sidebar col-xs-12 col-lg-3 col-lg-pull-9">
            <h4>Главное меню</h4>
            <ul>
                <li><a href="${urlHome}">Главная</a></li>
                <c:choose>
                	<c:when test="${empty currentUser}">
						<li><a href="${urlOfferList}">Услуги</a></li>
						<li><a href="${urlRateList}">Тарифные планы</a></li>
				    </c:when>
                	<c:when test="${isAdmin}">
		                <li><a href="${urlSubscriberList}">Абоненты</a></li>
		                <li>
		                	<a href="${urlAdministratorList}">Администраторы</a>
		                </li>
		                <li><a href="${urlInvoice}">Счета и оплаты</a></li>
				    </c:when>
					<c:otherwise>
						<li><a href="${urlSubscriptionList}">Мои услуги</a></li>
		                <li>
		                	<a href="${urlInvoicesList}">Мои счета и оплаты</a>
		                </li>
						<li><a href="${urlCallDial}">Совершить звонок</a></li>
					</c:otherwise>
				</c:choose>
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