<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<form action="/user/select" method="post">
    <fieldset>

        <%--select tour type--%>
        <div>
            <select name="tourType">
                <c:forEach var="tourType" items="${tourTypeList}">
                    <option tourType="${tourType}"> ${tourType}</option>
                </c:forEach>
            </select>
            <span>Select tour type</span>
        </div>
        <br>
        <%--country--%>
        <div>
            <select name="country">
                <c:forEach var="country" items="${countryList}">
                    <option country="${country}"> ${country}</option>
                </c:forEach>
            </select>
            <span>Select country</span>
        </div>
        <br>
        <%--transport type--%>
        <div>
            <select name="transportType">
                <c:forEach var="transportType" items="${transportTypeList}">
                    <option transportType="${transportType}"> ${transportType}</option>
                </c:forEach>
            </select>
            <span>Select transport type</span>
        </div>
        <br>
            <%--hotel category--%>
            <div>
                <select name="hotelCategory">
                    <c:forEach var="hotelCategory" items="${hotelCategoryList}">
                        <option hotelCategory="${hotelCategory}"> ${hotelCategory}</option>
                    </c:forEach>
                </select>
                <span>Select hotel category</span>
            </div>
            <br>
            <%--typeOfMealsList--%>
            <div>
                <select name="typeOfMeals">
                    <c:forEach var="typeOfMeals" items="${typeOfMealsList}">
                        <option typeOfMeals="${typeOfMeals}"> ${typeOfMeals}</option>
                    </c:forEach>
                </select>
                <span>Select type of meals</span>
            </div>
            <br>
        <!-- Button (Double) -->

        <div>
            <button id="buttonSelect" name="buttonSelect">Select tour</button>
        </div>

        <div align="center">
            <h3>${errorListIsEmpty}<br></h3>
        </div>

        <div align="center">
            <h3>${operationMessage}<br></h3>
        </div>
    </fieldset>
</form>
