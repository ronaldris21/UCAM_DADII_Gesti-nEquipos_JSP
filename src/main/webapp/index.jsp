<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="misTags" prefix="tags" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<jsp:include page="common/head.jsp"/>
<title>Index</title>


</head>
<body>


<jsp:include page="common/header.jsp"/>



<tags:Control_Login>

	LOS DATOS EST�N SIENDO OBTENIDOS DESDE: 
	<b>
		<%= request.getServletContext().getAttribute("DAOSELECTOR") %>
	</b>
	<h1>BIENVENIDO  <% out.print(request.getSession().getAttribute("EMAIL_LOGIN")!= null ? request.getSession().getAttribute("EMAIL_LOGIN").toString() : ""); %> </h1>

	<a href="Control?action=MYSQL">
		<button class="btn btn-primary"> Bases de datos</button>
	</a>

	<a href="Control?action=SERVLET">
		<button class="btn btn-primary"> Datos del context</button>
	</a>	
	

</tags:Control_Login>
</body>
</html>