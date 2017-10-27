<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="/user/reserve" method="post">
    <fieldset>
        <div>
            <select id="reservingTour" name="reservingTour"
                    style="width: 500px; height: 300px;">
                <c:forEach var="Tour" items="${toursMap}">
                    <option value="${Tour.key}">${Tour.value}</option>
                </c:forEach>
            </select>
        </div>
        <!-- Button -->
        <div>
            <button id="singlebuttonReserv" name="singlebuttonReserv">Reserve</button>
        </div>
    </fieldset>
</form>
