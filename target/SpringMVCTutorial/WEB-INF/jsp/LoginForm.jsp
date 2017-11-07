<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${webRoot}/css/main.css" type="text/css"/>
</head>
<body>
<div id="global">
    <form:form commandName="login" action="login" method="post">
        <fieldset>
            <legend>Login</legend>
            <p>
                <label for="userName">User Name: </label>
                <form:input id="userName" path="userName" cssErrorClass="error" value="paul"/>
            </p>
            <p>
                <label for="password">Password: </label>
                <form:password id="password" path="password" cssErrorClass="error" value="secret"/>
            </p>
            <p id="buttons">
                <input id="reset" type="reset" tabindex="4">
                <input id="submit" type="submit" tabindex="5"
                       value="Login">
            </p>
        </fieldset>
    </form:form>
</div>
</body>
</html>