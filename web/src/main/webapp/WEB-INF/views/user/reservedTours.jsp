<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="/user/cancel" method="post">
    <fieldset>

        <div>
            <select id="reservingTour" name="reservingTour"
                    style="width: 300px; height: 100px;">
                <c:forEach var="Tour" items="${toursMap}">
                    <option value="${Tour.key}">${Tour.value.toString}</option>
                </c:forEach>
            </select>
        </div>
        <!-- Button -->
        <div>
            <button id="singlebuttonCancelReserv" name="singlebuttonCancelReserv"> Cancel
                reserving
            </button>
        </div>
    </fieldset>
</form>
