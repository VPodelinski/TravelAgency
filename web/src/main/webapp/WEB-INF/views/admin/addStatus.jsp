<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h4>Enter status for orders :</h4>
<form action="/admin/add_status" method="post">
    <fieldset>
        <!--order status-->
        <div>
            <input id="orderStatus" name="orderStatus" type="text">
            <span>Enter order status</span>
        </div>
        <br>

        <!-- Button -->
        <div>
            <div>
                <button id="singlebuttonCreateOrderStatus" name="singlebuttonCreateOrderStatus">Create
                </button>
            </div>
        </div>

        <div align="center">
            <h3>${operationMessage}<br></h3>
        </div>

    </fieldset>
</form>