<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<form class="form-horizontal" action="/user/select" method="post">
    <fieldset>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="tourType">Type tour</label>

            <div class="col-md-4">
                <select id="tourType" name="tourType" class="form-control">
                    <c:forEach var="TourType" items="${tourTypeList}">
                        <option value="${TourType.id}">${TourType.tourType}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="country">Country</label>

            <div class="col-md-4">
                <select id="country" name="country" class="form-control">
                    <c:forEach var="Countries" items="${countryList}">
                        <option value="${Countries.id}">${Countries.country}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="transport">Transport</label>

            <div class="col-md-4">
                <select id="transport" name="transport" class="form-control">
                    <c:forEach var="Transport" items="${transportList}">
                        <option value="${Transport.id}">${Transport.transport}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="hotelType">Hotel</label>

            <div class="col-md-4">
                <select id="hotelType" name="hotelType" class="form-control">
                    <c:forEach var="Hotel" items="${hotelList}">
                        <option value="${Hotel.id}">${Hotel.hotelType}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="foodComplex">Food complex</label>

            <div class="col-md-4">
                <select id="foodComplex" name="foodComplex" class="form-control">
                    <c:forEach var="FoodComplex" items="${foodComplexList}">
                        <option value="${FoodComplex.id}">${FoodComplex.foodComplex}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Button (Double) -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="buttonSelect"></label>

            <div class="col-md-8">
                <button id="buttonSelect" name="buttonSelect" class="btn btn-info">Select tour</button>
            </div>
        </div>

        <div align="center">
            <h3>${errorListIsEmpty}<br></h3>
        </div>

        <div align="center">
            <h3>${operationMessage}<br></h3>
        </div>

    </fieldset>
</form>
