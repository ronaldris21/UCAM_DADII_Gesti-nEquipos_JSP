<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
<jsp:include page="common/head.jsp"/>
</head>
<body>


<jsp:include page="common/header.jsp"/>

ERROR PAGE: <BR>
<%= exception.getMessage() %>

</body>
</html>