<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" rtexprvalue="true"
	type="java.lang.String"%>
<%@attribute name="stylesheet" required="false" rtexprvalue="true"
	type="java.lang.String"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ru">
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Page title</title>
		<c:url var="bootstrapCssUrl" value="/css/bootstrap.min.css" />
		<c:url var="stylesheetCssUrl" value="/css/style.css" />
		<link rel="stylesheet" href="${bootstrapCssUrl}">
		<link rel="stylesheet" href="${stylesheetCssUrl}">
	</head>
	<body class="container-fluid">
		<header class="row">
			<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#myNavbar">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">Station 51</a>
					</div>
					<div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav">
							<li class="active"><a href="#">Главная</a></li>
							<li><a href="#">Услуги</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
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
					<li><a href="#">Абоненты</a></li>
				</ul>
			</aside>
		</section>
		<div class="row">
			<footer class="navbar-inverse navbar-fixed-bottom">
				<p>©2017-2018 "Station 51"</p>
			</footer>
		</div>
		<script src="/js/jquery.min.js"></script>
		<script src="/js/bootstrap.min.js"></script>
	</body>
</html>