<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>История конвертаций</title>
        <style>
            <%@include file="/WEB-INF/css/style.css"%>
        </style>
    </head>
    <body>
        <div class = center>
            <h2>История конвертаций</h2>
        </div>
        <div class="historySearch">
            <p class="searchLabel">Поиск: </p>
            <form:form method='post' action="/history" modelAttribute="searchForm">
                <div><form:input cssClass="searchDate" type='date' path="conversionDate"/></div>
                <div><form:select cssClass="searchFromCurr" path = "fromCurrNumCode" items="${currencyList}">
                </form:select></div>
                <div><form:select cssClass="searchToCurr" path = "toCurrNumCode" items="${currencyList}">
                </form:select></div>
                <button class = 'button orange' id = 'searchButton' type='submit'>Найти</button>
                <button class = 'button orange' id = 'resetSearchButton' type="reset" onclick="location.href='/history'">
                    Сброс</button>
            </form:form>
        </div>
        <div class="center1">
            <table class="blueTable">
                <thead>
                <tr>
                    <th class="Исходная валюта">Исходная валюта</th>
                    <th class="Целевая валюта">Целевая валюта</th>
                    <th class="Исходная сумма">Исходная сумма</th>
                    <th class="Получаемая сумма">Получаемая сумма</th>
                    <th class="Дата">Дата</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${conversionHistoryList}" var="conversionHistory" varStatus="tagStatus">
                    <tr>
                        <td>${conversionHistory.fromCurr.charCode}</td>
                        <td>${conversionHistory.toCurr.charCode}</td>
                        <td>${conversionHistory.value}</td>
                        <td>${conversionHistory.result}</td>
                        <td>${conversionHistory.date}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class = center1>
            <button class="button blue" onclick="location.href='/'">На главную</button>
        </div>
    </body>
</html>
