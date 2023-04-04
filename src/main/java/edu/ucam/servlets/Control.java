package edu.ucam.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;
import edu.ucam.acciones.*;
import edu.ucam.dao.session.ClubSessionDAO;
import edu.ucam.domain.Club;
import edu.ucam.domain.User;

/**
 * Servlet implementation class Control
 */
public class Control extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static void getRequest()
	{
		
	}

	private Hashtable<String, Accion> acciones;
	
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
		String jsp = "usuarios.jsp";
		Hashtable<String,User> data = null;
		Club c;
		String id;
		ClubSessionDAO dao = new ClubSessionDAO(request.getSession());
		
		switch (action) {
		case "login":
			jsp = "login.html";
			
			
			//TODO: Analizar que el usuario exista en un base de datos o contexto!
			String email = request.getParameter("username").toString();
			String password =  request.getParameter("password").toString();
			
			if(email!=null && password!= null)
				if(email.equals(password))
				{
					jsp="index.jsp";
					request.getSession().setAttribute("EMAIL_LOGIN", email);
					System.out.println("\tLOGIN SUCCESSFUL "+request.getSession().getAttribute("EMAIL_LOGIN"));
					response.sendRedirect(jsp);
					return ;
					///TODO: REDIRECT AFTER LOGIN - Maybe
					/*
					 * When a user tried to do something without being logged in.
					 * Rediredt to login, the to the previous action, if possible??
					 * 1/2 Maybe
					 */
				}
			break;
		case "logout":
				request.getSession().removeAttribute("EMAIL_LOGIN");
				System.out.println("Atributo removido");
				jsp= "login.jsp";
			break;
		case "new": 
			
			String name = request.getParameter("nombre");
			String imagen = request.getParameter("contrasena");
			c = new Club(0, name, imagen);
			System.out.println("new");
			if (name.equals("") || imagen.equals("")) {
				request.setAttribute("ERROR_REQUEST", "No puedes dejar el nombre ni la imagen en blanco");
				break;
			}
			
			
			
			if(dao.insert(c)) {
				System.out.println("Inserta");
				response.sendRedirect(jsp);
			return;
			}
			break;
		case "edit": 
			
			id = request.getParameter("id-club");
			
			c = dao.getById(Integer.valueOf(id));
			if(c!=null)
			{
				request.setAttribute("id",c.getId());
				request.setAttribute("nombre",c.getNombre());
				request.setAttribute("img",c.getImg());
				
			}
			break;
			
			case "editSave": 
			
				id = request.getParameter("id-club");
				System.out.println("Guardar cambios id-jugador: "+id );
				
				c = dao.getById(Integer.valueOf(id));
				if (c!=null) {
					c.setNombre(request.getParameter("nombre"));
					c.setImg(request.getParameter("img"));
					
					if (c.getNombre().equals("") || c.getImg().equals("")) {
						request.setAttribute("ERROR_REQUEST", "No puedes dejar el nombre nila imagen en blanco");
						jsp = "Jugadores?action=edit&id-jugador="+c.getId();
						break;
					}
					
					dao.update(Integer.valueOf(id), c);
				}
				//Listo para ser redirigido
			break;
		case "delete": 
			id = request.getParameter("id-club");
			
			dao.delete(Integer.valueOf(id));
			
			break;
		default:
			jsp="jugadores.jsp";
			
		}
		
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
