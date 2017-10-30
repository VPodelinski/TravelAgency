<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="/user/cancel" method="post">
    <fieldset>

        <div>
            <select id="reservingTour" name="reservingTour"
                    style="width: 300px; height: 300px;">
                <c:forEach var="Tour" items="${toursMap}">
                    <option value="${Tour.key}">${Tour.value}</option>
                </c:forEach>
            </select>
        </div>

        <div>
            <select name="orderStatus">
                <c:forEach var="orderStatus" items="${orderStatusesList}">
                    <option value="${orderStatus.status}">"${orderStatus}"</option>
                </c:forEach>
            </select>
            <span>***Select order status***</span>
        </div>

        <!-- Button -->
        <div>
            <button id="singlebuttonCancelReserv" name="singlebuttonCancelReserv"> Cancel
                reserving
            </button>
        </div>
    </fieldset>
</form>
