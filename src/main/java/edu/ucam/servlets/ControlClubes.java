package edu.ucam.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;

import edu.ucam.dao.DAO;
import edu.ucam.dao.Singleton;
import edu.ucam.dao.session.ClubServletDAO;
import edu.ucam.domain.Club;
import edu.ucam.domain.Jugador;

/**
 * Servlet implementation class ControlJugadores
 */

public class ControlClubes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */ 
	public ControlClubes() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entra en el doGet");
		String action = request.getParameter("action");
		String jsp = "clubs.jsp";
		Hashtable<String,Club> data = null;
		Club c;
		String id;
		DAO<Club> dao = Singleton.getInstance().factoryDataSource.getDaoClub();
		
		
		switch (action) {
		case "new": 
			
			String name = request.getParameter("nombre");
			String imagen = request.getParameter("img");
			c = new Club(0, name);
			System.out.println("new");
			if (name.equals("") ) {
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
				
				
			}
			break;
			
			case "editSave": 
			
				id = request.getParameter("id-club");
				System.out.println("Guardar cambios id-jugador: "+id );
				
				c = dao.getById(Integer.valueOf(id));
				if (c!=null) {
					c.setNombre(request.getParameter("nombre"));
				
					
					if (c.getNombre().equals("") ) {
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

