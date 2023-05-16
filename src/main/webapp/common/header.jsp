<%@ taglib uri="misTags" prefix="tags" %>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
 <div class="container-fluid">
 	<a class="navbar-brand" href="index.jsp">Fiffa</a>
	<ul class="navbar-nav mr-auto">
	  <li class="nav-item">
	    <a class="nav-link" href="<%= request.getContextPath()%>/clubs.jsp">Clubs</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="<%= request.getContextPath()%>/jugadores.jsp">Jugadores</a>
	  </li>
	  
	  <tags:Control_Admin>
	   <li class="nav-item">
	     <a class="nav-link" href="<%= request.getContextPath()%>/usuarios.jsp">Usuarios </a>
	   </li>
	  </tags:Control_Admin>
	  
	</ul>
	
	<tags:Control_Login>
	 <ul class="navbar-nav ml-auto">
	   <li class="nav-item">
	     	<a class="nav-link" href="<%= request.getContextPath()%>/Control?action=logout"><i class="bi bi-box-arrow-in-left">Logout</i></a>
	   </li>
	   <li>
	   		<a class="nav-link" href="<%= request.getContextPath()%>/index.jsp"> 
	   		<i class="bi bi-person-circle">
	   		<%= request.getSession().getAttribute("EMAIL_LOGIN") == null ? "No hay sesión activa" : request.getSession().getAttribute("EMAIL_LOGIN")%>
	   		</i> 
	   		</a>
	   </li>
	 </ul>
	</tags:Control_Login>
	
	
	<tags:Control_Not_Login>
	<ul class="navbar-nav ml-auto">
	   
	   <li>
	   		<a class="nav-link" href="<%= request.getContextPath()%>/login.jsp"> <i class="bi bi-person-circle">Iniciar Sesión</i> </a>
	   </li>
	 </ul>
	</tags:Control_Not_Login>
	
	 	
 </div>
</nav>

