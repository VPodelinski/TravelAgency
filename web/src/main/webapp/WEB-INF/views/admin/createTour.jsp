<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h4>Enter tour's parameters:</h4>
<form action="/admin/create_tour" method="post">
    <fieldset>
        <!-- tour name-->
        <div>
            <input id="nameTour" name="nameTour" type="text">
            <span>Enter tour name</span>
        </div>
        <br>
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
        <%--№ hotel--%>
        <div>
            <input id="hotelID" name="hotelID" type="text">
            <span>Enter № hotel</span>
        </div>
        <br>
        <%--Departure city--%>
        <div>
            <select name="departureCity">
                <c:forEach var="departureCity" items="${departureCityList}">
                    <option departureCity="${departureCity}"> ${departureCity}</option>
                </c:forEach>
            </select>
            <span>Select departure city</span>
        </div>
        <br>
        <%--duration--%>
        <div>
            <input id="duration" name="duration" type="text">
            <span>Enter tour count days</span>
        </div>
        <br>
        <%--price--%>
        <div>
            <input id="price" name="price" type="text">
            <span>Enter tour price</span>
        </div>
        <br>

        <!-- Button -->
        <div>
            <div>
                <button id="singlebuttonCreateTour" name="singlebuttonCreateTour">Create
                </button>
            </div>
        </div>

        <div align="center">
            <h3>${operationMessage}<br></h3>
        </div>

    </fieldset>
</form>
