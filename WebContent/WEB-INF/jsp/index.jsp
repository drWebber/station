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
<c:url var="urlRateList" value="/rate/list.html" />
<c:url var="urlCallDial" value="/call/dial.html" />
<c:url var="urlInvoice" value="/invoice/control.html" />
<c:url var="urlInvoicesList" value="/invoice/list.html" />

<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<fmt:message var="title" key="index.title"/>
<title>${title}</title>
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
	                   <li class="active"><a href="${urlHome}">
	                       <fmt:message key="index.home"/>
	                   </a></li>
	                   <li><a href="${urlOfferList}">
					   		<fmt:message key="index.offers"/>
					   </a></li>
	                   <li><a href="${urlRateList}">
					   		<fmt:message key="index.rates"/>
					   </a></li>
	               </ul>
	               <c:choose>
	                   <c:when test="${empty currentUser}">
                            <ul class="nav navbar-nav navbar-right">
                                <li>
        	                       <a href="${urlLogin}">
        	                           <span class="glyphicon 
        	                               glyphicon-log-in"></span>
        	                           <fmt:message key="index.logIn"/>
        	                       </a>
                                </li>
                            </ul>
	                   </c:when>
	                   <c:otherwise>
	                       <div class="nav navbar-nav navbar-right">
	                           <span class="login-lable">
							   		<fmt:message key="index.loginInfo"/>
							   </span>
	                           <button class="navbar-inverse dropdown-toggle 
	                               login-navlink" type="button" 
	                               data-toggle="dropdown">${currentUser.login}
	                           <span class="caret"></span></button>
	                           <ul class="dropdown-menu">
	                               <li><a href="${urlLogout}">
								   		<fmt:message key="index.logOut"/>
								   </a></li>
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
                        <p><fmt:message key="index.description"/></p> 
                    </div>
                    <div class="container-fluid text-center">
                      <h2 class="text-uppercase">
                      		<fmt:message key="index.offers"/>
                      </h2>
                      <br>
                      <div class="row">
                        <div class="col-sm-4">
                            <span class="glyphicon 
                            	glyphicon-phone-alt lead"></span>
                            <h4><fmt:message key="index.telephony"/></h4>
                            <p><fmt:message key="index.telephonyDesc"/></p>
                        </div>
                        <div class="col-sm-4">
                            <span class="glyphicon glyphicon-home lead"></span>
                            <h4><fmt:message key="index.forHome"/></h4>
                            <p><fmt:message key="index.forHomeDesc"/></p>
                        </div>
                        <div class="col-sm-4">
                            <span class="glyphicon glyphicon-lock lead"></span>
                            <h4><fmt:message key="index.forBusiness"/></h4>
                            <p><fmt:message key="index.forBusinessDesc"/></p>
                        </div>
                        </div>
                        <br><br>
                      <div class="row">
                        <div class="col-sm-4">
                            <span class="glyphicon glyphicon-leaf lead"></span>
                            <h4><fmt:message key="index.techSupport"/></h4>
                            <p><fmt:message key="index.techSupportDesc"/></p>
                        </div>
                        <div class="col-sm-4">
                            <span class="glyphicon 
                            	glyphicon-certificate lead"></span>
                            <h4><fmt:message key="index.guarantee"/></h4>
                            <p><fmt:message key="index.guaranteeDesc"/></p>
                        </div>
                        <div class="col-sm-4">
                            <span class="glyphicon glyphicon-ruble lead"></span>
                            <h4><fmt:message key="index.payment"/></h4>
                            <p><fmt:message key="index.paymentDesc"/></p>
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