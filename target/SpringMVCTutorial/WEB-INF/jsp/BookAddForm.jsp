<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/tagPage.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Add Book Form</title>
    <link rel="stylesheet" href="${webRoot}/css/main.css" type="text/css"/>
    <script src="${webRoot}/js/jquery-1.8.2.js" type="text/javascript"></script>


</head>
<body>

<div id="global">
    <form:form modelAttribute="book" action="book_save" method="post">
        <%--<form:form commandName="book" action="book_save" method="post">--%>
        <fieldset>
            <legend>Add a book</legend>
            <p>
                <label for="category">Category: </label>
                <form:select id="category" path="category.id"
                             items="${categories}" itemLabel="name"
                             itemValue="id"/>
            </p>
            <p>
                <form:errors path="title" cssClass="error"/>
            </p>
            <p>
                <label for="title">Title: </label>
                <form:input id="title" path="title"/>
            </p>
            <p>
                <form:errors path="author" cssClass="error"/>
            </p>
            <p>
                <label for="author">Author: </label>
                <form:input id="author" path="author"/>
            </p>
            <p>
                <form:errors path="isbn" cssClass="error"/>
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
                <input type="button" id="getBook" value="获取" style="float: left">
                <input id="reset" type="reset" tabindex="4">
                <input id="submit" type="submit" tabindex="5"
                       value="Add Book">
            </p>
        </fieldset>
    </form:form>
</div>
<script type="text/javascript">
    $("#getBook").click(function () {
        $.ajax({
            type: "post",
            url: "${webRoot}/book_get",
            data: {isbn: "10010"},
            dataType: "json",
            success: function (data, status, xhr) {
                console.log(status)
                console.log(xhr)
                console.log(data)
                if (status === "success") {
                    $("#title").val(data.title)
                    $("#isbn").val(data.isbn)
                    $("#author").val(data.author)
                    var date = data.addDate;
                    $("#addDate").val(date)
                }
            }
        })
    });
</script>
</body>
</html>
