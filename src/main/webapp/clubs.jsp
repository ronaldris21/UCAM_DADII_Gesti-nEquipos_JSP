<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="misTags" prefix="tags" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
		<jsp:include page="common/head.jsp"/>
		<title>Clubs</title>
	
	</head>
	<body>
			
		<jsp:include page="common/header.jsp"/>
		
		
		<h1 id="title-main">Clubs</h1>
		
		<div id="flex-container">
			<br>
			<aside>
					<form action="Clubes?action=<%=request.getAttribute("nombre") != null ? "editSave" : "new"%>" method="post">
						<h2><%=request.getAttribute("nombre") != null ? "Editar" : "Agregar"%></h2>
						<input type="hidden" name="id-club" value="<%= request.getParameter("id-club") %>">
						Nombre: <input type="text" name="nombre" value="<%=request.getAttribute("nombre") != null ? request.getAttribute("nombre") : ""%>"> <br>
						Imagen: <input type="text" name="img" value="<%=request.getAttribute("img") != null ? request.getAttribute("img") : ""%>"> <br>
						<input type="submit" value="<%=request.getAttribute("nombre") != null ? "Editar" : "Agregar"%>  club" >
					</form>
			</aside>
			
			
			<main >
				<h2>Buscar</h2>
				<input type="text" id="searchInput" placeholder="Search...">
				<table class="table table-striped">
			        <thead class="thead-dark">
			          <tr>
			            <th scope="col">Nombre</th>
			            <th scope="col">Imagen</th>
			            <th scope="col">Acciones</th>
			          </tr>
			        </thead>
			        <tbody id="tableBody">
			        	<tags:ClubesTableBody/>
			        
			        </tbody>
			      </table>
			</main>
		</div>
		
		
		<%@ include file="common/footer.jsp" %>
			            
	</body>
</html>