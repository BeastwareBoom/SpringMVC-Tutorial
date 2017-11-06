<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/tagPage.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>View Product</title>
    <link rel="stylesheet" href="${webRoot}/css/main.css" type="text/css"/>
</head>
<body>
<div id="global">
    <h4>${message_model}</h4>
    <h4>${message_flash}</h4>
    <p>
    <h5>Details:</h5>
    Product Name: ${product.name}<br/>
    Description: ${product.description}<br/>
    Price: $${product.price}
    </p>
</div>
</body>
</html>