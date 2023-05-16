package edu.ucam.tags;
import java.io.IOException;
import java.util.ArrayList;

import edu.ucam.dao.DAO;
import edu.ucam.dao.Singleton;
import edu.ucam.domain.Club;
import edu.ucam.domain.Jugador;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class ClubsTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public int doStartTag() throws JspException {
		
		String controllerName = "Clubes";
		String idName = "id-club";
		
		ArrayList<Club> clubs = Singleton.getInstance().factoryDataSource.getDaoClub().getAll();
		ArrayList<Jugador> players = Singleton.getInstance().factoryDataSource.getDaoJugador().getAll();
		for (Club c : clubs ) {
			try {
				pageContext.getOut().print("<tr>");
				
				pageContext.getOut().print(String.format("<td> %s </td>", c.getNombre()));
				pageContext.getOut().print(String.format("<td> %s </td>", c.getImg()));
				//TODO: Imprimir Jugadores
				
				pageContext.getOut().print(String.format("<td>"));
				pageContext.getOut().print(String.format("<ul>"));
				for (Jugador jugador : players) {
					System.out.println("Club: "+ c.getId() + " - club Id: "+ jugador.getIdClub() + "  - Jugador : "+jugador.getId() );
					if(jugador.getIdClub() == c.getId())
					{
						System.out.println("Club: "+ c.getId() + "  - Jugador : "+jugador.getId());
						pageContext.getOut().print(String.format("<li> %s </li>", jugador.getNombre()+" "+jugador.getApellidos()));
						
					}
				}
				pageContext.getOut().print(String.format("</ul>"));
				pageContext.getOut().print(String.format("</td>", c.getImg()));

				///ACCIONES
				pageContext.getOut().print(String.format("<td> "
						+ "<a href=\"%s?action=edit&%s=%s\" class=\"btn btn-primary\" >Editar</a> ",
								controllerName, idName, c.getId()));

				
				///MODIFICAR ESTO SEGUN CONVENIENCIA
				String datosObjeto = c.getNombre() ;
				String urlDelete = controllerName+"?action=delete&"+idName+"="+c.getId();

				
				pageContext.getOut().print(String.format("<a href=\"#\" class=\"btn btn-danger\" data-toggle=\"modal\" data-target=\"#confirmDeleteModal\" data-datos=\"%s\" data-url-delete=\"%s\">Borrar</a>",
						datosObjeto, urlDelete));

				pageContext.getOut().print(" </td>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return SKIP_BODY;
		
	}
	

}
