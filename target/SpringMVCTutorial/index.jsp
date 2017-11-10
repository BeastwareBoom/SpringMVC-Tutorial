<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/tagPage.jsp" %>
<%
    response.sendRedirect(webRoot + "/book_input");
%>
<html>
<body>
<h2>Hello World!</h2>
</body>
<script type="text/javascript">
    //那个按钮的onclick引用这个方法
    function scan() {
        if (window.android !== undefined) {
            window.android.scan();
        }
    }

    function openapp() {
        document.getElementById('divOpen').innerHTML = '<iframe src="tuanlemei://platformapi/startApp"></iframe>';
    }
</script>

<div style="display:none;" id="divOpen"></div>
<input type="button" value="打开app" onclick="openapp();">
</html>

