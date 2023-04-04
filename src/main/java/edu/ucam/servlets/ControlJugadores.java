package edu.ucam.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;

import edu.ucam.dao.session.JugadorSessionDAO;
import edu.ucam.domain.Jugador;

/**
 * Servlet implementation class ControlJugadores
 */
public class ControlJugadores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlJugadores() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String jsp = "jugadores.jsp";
		Hashtable<String,Jugador> data = null;
		Jugador j;
		String id;
		JugadorSessionDAO dao = new JugadorSessionDAO(request.getSession());
		
		switch (action) {
		case "new": 
			
			String name = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			int goles = Integer.valueOf(request.getParameter("goles"));
			j = new Jugador(0, name, apellidos, goles);
			
			if (name.equals("") || apellidos.equals("")) {
				request.setAttribute("ERROR_REQUEST", "No puedes dejar el nombre ni el apellido en blanco");
				break;
			}
			
			
			
			if(dao.insert(j))
				response.sendRedirect(jsp);
			return;
			
		case "edit": 
			
			id = request.getParameter("id-jugador");
			
			j = dao.getById(Integer.valueOf(id));
			if(j!=null)
			{
				request.setAttribute("id",j.getId());
				request.setAttribute("nombre",j.getNombre());
				request.setAttribute("apellidos",j.getApellidos());
				request.setAttribute("goles",j.getGoles());
				
			}
			break;
			
			case "editSave": 
			
				id = request.getParameter("id-jugador");
				System.out.println("Guardar cambios id-jugador: "+id );
				
				j = dao.getById(Integer.valueOf(id));
				if (j!=null) {
					j.setNombre(request.getParameter("nombre"));
					j.setApellidos(request.getParameter("apellidos"));
					j.setGoles(Integer.valueOf(request.getParameter("goles")));
					
					if (j.getNombre().equals("") || j.getApellidos().equals("")) {
						request.setAttribute("ERROR_REQUEST", "No puedes dejar el nombre ni el apellido en blanco");
						jsp = "Jugadores?action=edit&id-jugador="+j.getId();
						break;
					}
					
					dao.update(Integer.valueOf(id), j);
				}
				//Listo para ser redirigido
			break;
		case "delete": 
			id = request.getParameter("id-jugador");
			
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
