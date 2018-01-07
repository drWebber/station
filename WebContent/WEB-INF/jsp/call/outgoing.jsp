<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:url var="urlMakeCall" value="/call/make.html" />

<u:html title="Исходящий вызов" pageHeading="Исходящий вызов">
<div class="call-wrapper">
    <form action="${urlMakeCall}" method="post">
        <div class="row">
            <div class="form-group col-xs-12 col-sm-3 col-md-2">
                <label for="sel1">Префикс</label>
                <select class="form-control" id="sel1" name="prefix">
                    <option value="000">0000 ДОБАВИТЬ</option>
                    <option value="00">0000 ДОБАВИТЬ</option>
                </select>
            </div>
            <div class="form-group col-xs-12 col-sm-3 col-md-3 col-lg-2">
                <label for="sel2">Номер телефона</label>
                <input id="sel2" class="form-control" name="phoneNum"
                    type="number" value="240000" min="101" max="9999999">
            </div>
        </div>
       <div class="row col-xs-12">
            <h4>Длительность звонка:</h4>
            <label class="radio-inline">
                <input type="radio" name="duration" value="0.5">
                <span>30 сек</span>
            </label>
            <label class="radio-inline">
                <input type="radio" name="duration" value="1">
                <span>1 мин</span>
            </label>
            <label class="radio-inline">
               <input type="radio" name="duration" value="3" checked="checked">
               <span>3 мин</span>
            </label>
            <label class="radio-inline">
               <input type="radio" name="duration" value="5">
               <span>5 мин</span>
            </label>
            <label class="radio-inline">
               <input type="radio" name="duration" value="30">
               <span>30 мин</span>
            </label>
        </div>
        <div class="row col-xs-12">
            <div class="input-group-btn">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-earphone"></span>
                    Совершить звонок
                </button>
            </div>
        </div>
    </form>
</div>
</u:html>