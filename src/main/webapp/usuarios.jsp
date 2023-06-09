<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="misTags" prefix="tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Usuarios</title>

<%@ include file="common/head.jsp" %>
</head>
<body>
	
	
	<jsp:include page="common/header.jsp"/>
		
	<tags:Control_Admin>
	
		
		<h1 id="title-main">Usuarios registrados en el sistema</h1>
		
		<div id="flex-container">
			<aside>
					<form action="Control?action=<%=request.getAttribute("nombre") != null ? "editSave" : "new"%>" method="post">
						<h2><%=request.getAttribute("nombre") != null ? "Editar" : "Agregar"%></h2>
						<input type="hidden" name="id-user" value="<%= request.getParameter("id-user") %>">
						Nombre: <input required type="text" name="nombre" value="<%=request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>"> <br>
						Contraseņa: <input required type="password" name="contrasena" value="<%=request.getAttribute("contrasena") != null ? request.getAttribute("contrasena") : ""%>"> <br>
						<br>
						<input type="submit" value="<%=request.getAttribute("nombre") != null ? "Editar" : "Agregar"%>  usuario" >
						
						<tags:RequestErrorMessageTag/>
						
				</form>
			</aside>
			
			
			<main >
				<h2>Buscar</h2>
				<input type="text" id="searchInput" placeholder="Search...">
				<table class="table table-striped">
			        <thead class="thead-dark">
			          <tr>
			            <th scope="col">Nombre</th>
			            <th scope="col">Contraseņa</th>
			            <th scope="col">Acciones</th>
			          </tr>
			        </thead>
			        <tbody id="tableBody">
			        
			        	<tags:UsuariosTableBody/>
			        
			          
			        </tbody>
			      </table>
			</main>
		</div>
		
		
		<%@ include file="common/modal.jsp" %>
		
	</tags:Control_Admin>
	
	
	<%@ include file="common/footer.jsp" %>
		            
</body>
</html>