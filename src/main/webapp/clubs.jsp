<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="common/head.jsp"/>
		<title>Clubs</title>
	
	</head>
	<body>
			
		<jsp:include page="common/header.jsp"/>
		
		
		<h1 id="title-main">Clubs</h1>
		
		<div id="flex-container">
			<br>
			<aside>
					<form action="Control?action=<%=request.getAttribute("nombre") != null ? "editSave" : "new"%>" method="post">
						<h2><%=request.getAttribute("nombre") != null ? "Editar" : "Agregar"%></h2>
						<input type="hidden" name="id-jugador" value="<%= request.getParameter("id-jugador") %>">
						Nombre: <input type="text" name="nombre" value="<%=request.getAttribute("nombre") != null ? request.getAttribute("nombre") : ""%>"> <br>
						Apellidos: <input type="text" name="apellidos" value="<%=request.getAttribute("apellidos") != null ? request.getAttribute("apellidos") : ""%>"> <br>
						Goles: <input type="number" name="goles" value="<%=request.getAttribute("goles") != null ? request.getAttribute("goles") : 0%>"> <br>
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
			            <th scope="col">Apellidos</th>
			            <th scope="col">Goles</th>
			            <th scope="col">Acciones</th>
			          </tr>
			        </thead>
			        <tbody id="tableBody">
			          <tr >
			            <td>Mark</td>
			            <td>bcksjdb cjks cnsjlkdcb a sashd uadua sdhasd kahkdh askhdjka shdasdh aslkj dhasd kjldhas</td>
			            <td>@mdo</td>
			            <td> <a href="#" class="btn btn-primary" >Editar</a>  <a href="#" class="btn btn-danger" >Borrar</a> </td>
			          </tr>
			        </tbody>
			      </table>
			</main>
		</div>
		
		
		<%@ include file="common/footer.jsp" %>
			            
	</body>
</html>