<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form class="form-horizontal" action="/admin/create_tour" method="post">
    <fieldset>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="tourType">Type tour</label>

            <div class="col-md-4">
                <select id="tourType" name="tourType" class="form-control">
                    <c:forEach var="TourType" items="${tourTypeList}">
                        <option value="${TourType.id}">${TourType.tourType}</option>
                    </c:forEach>
                </select>
                <span class="help-block">Select tour type</span>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="country">Country</label>

            <div class="col-md-4">
                <input id="country" name="country" type="text" placeholder="" class="form-control input-md">
                <span class="help-block">Enter country</span>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="transport">Transport</label>

            <div class="col-md-4">
                <select id="transport" name="transport" class="form-control">
                    <c:forEach var="Transport" items="${transportList}">
                        <option value="${Transport.id}">${Transport.transport}</option>
                    </c:forEach>
                </select>
                <span class="help-block">Select transport</span>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="hotelType">Hotel</label>

            <div class="col-md-4">
                <select id="hotelType" name="hotelType" class="form-control">
                    <c:forEach var="Hotel" items="${hotelList}">
                        <option value="${Hotel.id}">${Hotel.hotelType}</option>
                    </c:forEach>
                </select>
                <span class="help-block">Select hotel type</span>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="foodComplex">Food complex</label>

            <div class="col-md-4">
                <select id="foodComplex" name="foodComplex" class="form-control">
                    <c:forEach var="FoodComplex" items="${foodComplexList}">
                        <option value="${FoodComplex.id}">${FoodComplex.foodComplex}</option>
                    </c:forEach>
                </select>
                <span class="help-block">Select food complex</span>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="cost">Cost</label>

            <div class="col-md-4">
                <input id="cost" name="cost" type="text" placeholder="" class="form-control input-md">
                <span class="help-block">Enter cost</span>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebuttonCreateTour"></label>

            <div class="col-md-4">
                <button id="singlebuttonCreateTour" name="singlebuttonCreateTour" class="btn btn-success">Create
                </button>
            </div>
        </div>

        <div align="center">
            <h3>${operationMessage}<br></h3>
        </div>

    </fieldset>
</form>
