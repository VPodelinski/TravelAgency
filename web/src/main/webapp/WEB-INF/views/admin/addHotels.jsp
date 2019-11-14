<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h4>Enter hotel's parameters:</h4>
<form action="/admin/add_hotel" method="post">
    <fieldset>
        <!--hotel city-->
        <div>
            <input id="city" name="city" type="text">
            <span>Enter tour hotel's city</span>
        </div>
        <br>
        <%--select street--%>
        <div>
            <input id="street" name="street" type="text">
            <span>Enter tour hotel's street</span>
        </div>
        <br>

        <div>
            <input id="numbOfBuilding" name="numbOfBuilding" type="text">
            <span>Enter tour hotel's numbOfBuilding</span>
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
        <!-- Button -->
        <div>
            <div>
                <button id="singlebuttonCreateHotel" name="singlebuttonCreateHotel">Create
                </button>
            </div>
        </div>

        <div align="center">
            <h3>${operationMessage}<br></h3>
        </div>

    </fieldset>
</form>