<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/tagPage.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Save Product</title>
    <link rel="stylesheet" href="${webRoot}/css/main.css" type="text/css"/>
</head>
<body>
<div id="global">
    <h4>The product has been saved.</h4>
    <p>
    <h5>Details:</h5>
    Product Name: ${product.name}<br/>
    Description: ${product.description}<br/>
    Price: $${product.price}
    <p>Following files are uploaded successfully.</p>
    <ol>
        <c:forEach items="${product.images}" var="image">
            <li>${image.originalFilename}
                    <%--config文件中做好映射，自动去/WEB-INF/images下匹配文件--%>
                <img width="100" src="${webRoot}/images/${image.originalFilename}"/>
            </li>
        </c:forEach>
    </ol>
    </p>
</div>
</body>
</html>