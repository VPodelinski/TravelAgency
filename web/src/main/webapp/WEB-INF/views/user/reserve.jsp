<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form class="form-horizontal" action="/user/reserve" method="post">
    <fieldset>

        <!-- Select Multiple -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="reservingTour">Tours</label>

            <div class="col-md-4">
                <select id="reservingTour" name="reservingTour" class="form-control" multiple="multiple"
                        style="width: 450px; height: 450px;">
                    <c:forEach var="Tour" items="${toursMap}">
                        <option value="${Tour.key}">${Tour.value}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebuttonReserv"></label>

            <div class="col-md-4">
                <button id="singlebuttonReserv" name="singlebuttonReserv" class="btn btn-success">Reserve</button>
            </div>
        </div>

    </fieldset>
</form>
