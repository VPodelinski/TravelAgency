<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html xmlns:jsp="http://java.sun.com/JSP/Page">

<head>
    <title>Travel Agency</title>
</head>

<body>
<div >
    <tiles:insertAttribute name="header"/>
    <tiles:insertAttribute name="sidebar"/>
    <tiles:insertAttribute name="body"/>
    <tiles:insertAttribute name="footer"/>
</div>
</body>

</html>
