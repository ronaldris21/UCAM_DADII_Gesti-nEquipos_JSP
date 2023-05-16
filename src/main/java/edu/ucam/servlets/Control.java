package edu.ucam.servlets;

import java.io.IOException;
import java.util.Hashtable;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import edu.ucam.dao.DAO;
import edu.ucam.dao.Singleton;
import edu.ucam.domain.User;

/**
 * Servlet implementation class Control
 */
public class Control extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static void getRequest()
	{

	}


    /**
     * @see HttpServlet#HttpServlet()
     */
    public Control() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
    	super.init();
    	///Crea la primer instanncia
		System.out.println("SERVLET CONTROL INICIADO");

	
		
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entra en el doGet");
		String action = request.getParameter("action");
		if(action==null)
			action= "null";
		
		String jsp = "usuarios.jsp";
		Hashtable<String,User> data = null;
		User u;
		String id;
		DAO<User> dao = Singleton.getInstance().factoryDataSource.getDaoUser();
		
		switch (action) {
		case "login":
			jsp = "login.jsp";
			
			
			//TODO: Analizar que el usuario exista en un base de datos o contexto!
			String email = request.getParameter("username").toString();
			String password =  request.getParameter("password").toString();
			if(email!=null && password!= null)
			{
				for(User uu : dao.getAll())
				{
					if(uu.getContrasena().equals(password) && uu.getNombre().equals(email))
					{
						jsp="index.jsp";
						request.getSession().setAttribute("EMAIL_LOGIN", email);
						System.out.println("\tLOGIN SUCCESSFUL "+request.getSession().getAttribute("EMAIL_LOGIN"));
						response.sendRedirect(jsp);
						return ;
					}
				}
				
				
				
			}
			request.setAttribute("ERROR_REQUEST", "Credenciales inválidas");
			break;
		case "logout":
				request.getSession().removeAttribute("EMAIL_LOGIN");
				System.out.println("Atributo removido");
				jsp= "login.jsp";
			break;
		case "new": 
			
			String name = request.getParameter("nombre");
			String contra = request.getParameter("contrasena");
			u = new User(0, name, contra);
			System.out.println("new");
			if (name.equals("") || contra.equals("")) {
				request.setAttribute("ERROR_REQUEST", "No puedes dejar el usuario ni la contraseña en blanco");
				break;
			}
			
			
			
			if(dao.insert(u)) {
				System.out.println("Inserta");
				response.sendRedirect(jsp);
				return;
			}
			else
			{
				request.setAttribute("ERROR_REQUEST", "Ya existe un usuario así, no es posible agregar");
			}
			break;
		case "edit": 
			
			id = request.getParameter("id-user");
			
			u = dao.getById(Integer.valueOf(id));
			if(u!=null)
			{
				request.setAttribute("id-user",u.getId());
				request.setAttribute("nombre",u.getNombre());
				request.setAttribute("contrasena",u.getContrasena());
				
			}
			break;
			
			case "editSave": 
			
				id = request.getParameter("id-user");
				System.out.println("Guardar cambios id-user: "+id );
				
				u = dao.getById(Integer.valueOf(id));
				if (u!=null) {
					u.setNombre(request.getParameter("nombre"));
					u.setContrasena(request.getParameter("contrasena"));
					
					if (u.getNombre().equals("") || u.getContrasena().equals("")) {
						request.setAttribute("ERROR_REQUEST", "No puedes dejar el usuario ni la contraseña en blanco");
						jsp = "Jugadores?action=edit&id-user="+u.getId();
						break;
					}
					dao.update(Integer.valueOf(id), u);
				}
				//Listo para ser redirigido
			break;
		case "delete": 
			id = request.getParameter("id-user");
			
			dao.delete(Integer.valueOf(id));
			
			break;
			
		case "null":
			jsp="login.jsp";
			break;
		default:
			jsp="usuarios.jsp";
			
		}
		
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
