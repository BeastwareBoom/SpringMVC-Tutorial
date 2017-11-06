<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/tagPage.jsp" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Add Product Form</title>
    <link rel="stylesheet" href="${webRoot}/css/main.css" type="text/css"/>
</head>
<body>

<div id="global">
    <%--保存测试重定向Flash属性--%>
    <%--<form:form commandName="product" action="product_save_and_view" method="post" enctype="multipart/form-data">--%>
    <%--保存测试图片上传--%>
    <form:form commandName="product" action="product_save" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>Add a product</legend>
            <p>
                <label for="name">Product Name: </label>
                <form:input id="name" path="name" cssErrorClass="error"/>
                <form:errors path="name" cssClass="error"/>
            </p>
            <p>
                <label for="description">Description: </label>
                <form:input id="description" path="description"/>
            </p>
            <p>
                <label for="price">Price: </label>
                <form:input id="price" path="price" cssErrorClass="error"/>
            </p>
            <p>
                <label for="image">Product Image: </label>
                <input type="file" name="images[0]"/>
            </p>
            <p id="buttons">
                <input id="reset" type="reset" tabindex="4">
                <input id="submit" type="submit" tabindex="5"
                       value="Add Product">
            </p>
        </fieldset>
    </form:form>
</div>
</body>
</html>
