<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/tagPage.jsp" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Edit Book Form</title>
    <link rel="stylesheet" href="${webRoot}/css/main.css" type="text/css"/>
</head>
<body>

<div id="global">
    <form:form commandName="book" action="/book_update" method="post">
        <fieldset>
            <legend>Edit a book</legend>
            <form:hidden path="id"/>
            <p>
                <label for="category">Category: </label>
                <form:select id="category" path="category.id" items="${categories}"
                             itemLabel="name" itemValue="id"/>
            </p>
            <p>
                <label for="title">Title: </label>
                <form:input id="title" path="title"/>
            </p>
            <p>
                <label for="author">Author: </label>
                <form:input id="author" path="author"/>
            </p>
            <p>
                <label for="isbn">ISBN: </label>
                <form:input id="isbn" path="isbn"/>
            </p>

                <%--form:errors--%>
            <p>
                <form:errors path="addDate" cssClass="error"/>
            </p>

            <p>
                <label for="addDate">日期: </label>
                <form:input id="addDate" path="addDate"/>
            </p>

            <p id="buttons">
                <input id="reset" type="reset" tabindex="4">
                <input id="submit" type="submit" tabindex="5"
                       value="Update Book">
            </p>
        </fieldset>
    </form:form>
</div>
</body>
</html>
