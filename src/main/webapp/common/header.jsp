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
	     	<a class="nav-link" href="<%= request.getContextPath()%>/Control?accion=logout"><i class="bi bi-box-arrow-in-left">Logout</i></a>
	   </li>
	   <li>
	   		<a class="nav-link" href="<%= request.getContextPath()%>/myUser.jsp"> <i class="bi bi-person-circle">Mi usuario</i> </a>
	   </li>
	 </ul>
	</tags:Control_Login>
	
	
	<tags:Control_Not_Login>
	<ul class="navbar-nav ml-auto">
	   
	   <li>
	   		<a class="nav-link" href="<%= request.getContextPath()%>/login.html"> <i class="bi bi-person-circle">Iniciar Sesi�n</i> </a>
	   </li>
	 </ul>
	</tags:Control_Not_Login>
	
	 	
 </div>
</nav>
