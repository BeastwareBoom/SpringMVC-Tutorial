<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--这个命令可以使jsp输出的html时去除多余的空行（jsp上使用EL和tag会产生大量的空格和空行）--%>
<%@page trimDirectiveWhitespaces="true" %>
<%
    response.setHeader("cache-control", "max-age=5,public,must-revalidate"); //one day
    response.setDateHeader("expires", -1);
    String cdntime = String.valueOf(System.currentTimeMillis());
    request.setAttribute("cdntime", cdntime);
%>
<%--<c:set value="${pageContext.request.contextPath}" var="webRoot" />--%>
<%
    String webRoot = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    request.setAttribute("webRoot", webRoot);
%>