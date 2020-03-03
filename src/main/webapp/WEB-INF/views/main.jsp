<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Конвертер валют</title>
        <style>
            <%@include file="/WEB-INF/css/style.css"%>
        </style>
    </head>
    <body>
        <div>
            <h1>Конвертер валют</h1>
        </div>
        <div class= center1>
            <form:form cssClass="formSelect" method="POST" action="/" modelAttribute="conversionForm">
                <div>
                    <form:select cssClass="fromCurrSelect" path = "fromCurrencyNumCode" items="${currencyList}"> </form:select>
                    <form:select cssClass="toCurrSelect" path = "toCurrencyNumCode" items="${currencyList}" > </form:select>
                </div>
                <div>
                    <div class="fromCurrSelectErr"><form:errors  path ="fromCurrencyNumCode"/></div>
                    <div class="toCurrSelectErr"><form:errors path ="toCurrencyNumCode"/></div>
                </div>
                <div>
                    <form:input cssClass="fromValue" type = "number"  path = "value" />
                    <form:input cssClass="resultValue" readonly="true" path = "result" />
                </div>
                <div>
                    <div class="fromValueErr"><form:errors path ="value"/></div>
                </div>
                <div class= center1>
                    <button class="button orange" id="convertButton" type="submit" onclick="location.href='./'">Конвертировать
                    </button>
                </div>
            </form:form >
        </div>
        <div class= center1>
            <button class="button blue" onclick="location.href='./history'">История</button>
            <button class="button blue" onclick="location.href='./logout'">Выход</button>
        </div>
    </body>
</html>
