<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/tagPage.jsp" %>
<%@ page import="java.util.Date" %>
<%
    Date defaultDate = new Date();
    request.setAttribute("defaultDate", defaultDate);
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Book List</title>
    <!--springMVC配置映射-->
    <link rel="stylesheet" href="${webRoot}/css/main.css" type="text/css"/>
</head>
<body>

<div id="global">
    <h1>Book List</h1>
    <a href="<c:url value="/book_input"/>">Add Book</a>
    <table>
        <tr>
            <th>Category</th>
            <th>Title</th>
            <th>ISBN</th>
            <th>Author</th>
            <th>日期</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${books}" var="book" varStatus="status">
            <c:if test="${status.count%2==0}">
                <tr style="background: #e7e7e7;margin:10px 0">
            </c:if>
            <c:if test="${status.count%2!=0}">
                <tr style="background: white;margin:10px 0">
            </c:if>

            <td>${book.category.name}</td>
            <td>${book.title}</td>
            <td>${book.isbn}</td>
            <%--<td>${book.author}</td>--%>
            <td><c:out value="${book.author}"/></td>
            <%--<td>${book.addDate}</td>--%>
            <td>
                    <%--value属性接收Date类型，不可传入String类型，String类型可使用parseDate标签
                    http://blog.csdn.net/xiaobing_122613/article/details/70274891
                    --%>
                <fmt:formatDate value="${book.addDate==null?defaultDate:book.addDate}" type="date"
                                pattern="yyyy-MM-dd"/>
            </td>
            <td><a href="book_edit/${book.id}">Edit</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
