<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:url var="urlSave" value="/rate/save.html" />
<c:set var="pageHeading" value="Редактирование тарифа ${rate.type}" />
<u:html title="${pageHeading}" pageHeading="${pageHeading}">
<div class="row">
	<form class="col-lg-7" action="${urlSave}" method="post">
	   <div class="input-group">
	       <span class="input-group-addon">Тариф, руб/мин</span>
	       <input class="form-control" type="number" name="tariff"
           pattern="[0-9]+([\.,][0-9]{,2})?"
	           value="${rate.tariff}" step="0.01" />
        </div>
	   <input type="hidden" name="type" value="${rate.type}" />
	   <br />
	   <div class="form-group">
            <button type="submit" class="btn btn-info">Сохранить</button>
            <button type="reset" class="btn btn-default">Сброс</button>
            <input type="button" class="btn btn-default"
                onclick="history.back();" value="Назад" />
	   </div>
    </form>
</div>
</u:html>