<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<jsp:include page="common/head.jsp"/>
<title>Index</title>


</head>
<body>


<jsp:include page="common/header.jsp"/>

<button class="btn-close"></button>
<h1>BIENVENIDO  <% out.print(request.getSession().getAttribute("EMAIL_LOGIN")!= null ? request.getSession().getAttribute("EMAIL_LOGIN").toString() : ""); %> </h1>
<a href="Control?accion=logout">Cerrar Sesión</a>

</body>
</html>