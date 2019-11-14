<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="/user/reserve" method="post">
    <fieldset>
        <div>
            <select id="reservingTour" name="reservingTour"
                    style="width: 500px; height: 200px;">
                <c:forEach var="Tour" items="${toursMap}">
                    <option value="${Tour.key}">${Tour.value.toString}</option>
                </c:forEach>
            </select>
        </div>
        <br>
        <div>
            <select name="orderStatus">
                <c:forEach var="orderStatus" items="${orderStatusesList}">
                    <option value="${orderStatus.status}">"${orderStatus.status}"</option>
                </c:forEach>
            </select>
            <span>***Select order status***</span>
        </div>
        <br>
        <!-- Button -->
        <div>
            <button id="singlebuttonReserv" name="singlebuttonReserv">Action</button>
        </div>
    </fieldset>
</form>
