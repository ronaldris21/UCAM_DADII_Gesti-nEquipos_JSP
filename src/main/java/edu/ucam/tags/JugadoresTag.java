package edu.ucam.tags;

import java.io.IOException;

import edu.ucam.dao.DAO;
import edu.ucam.dao.Singleton;
import edu.ucam.dao.session.JugadorServletDAO;
import edu.ucam.domain.Jugador;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class JugadoresTag extends TagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {

		String controllerName = "Jugadores";
		String idName = "id-jugador";

		DAO<Jugador> dao = Singleton.getInstance().factoryDataSource.getDaoJugador();
		for (Jugador j : dao.getAll() ) {
			try {
				pageContext.getOut().print("<tr>");

				pageContext.getOut().print(String.format("<td> %s </td>", j.getNombre()));
				pageContext.getOut().print(String.format("<td> %s </td>", j.getApellidos()));
				pageContext.getOut().print(String.format("<td> %s </td>", j.getGoles()));
				pageContext.getOut().print(String.format("<td>%s</td>", (j.getClubName() != null) ? j.getClubName() : "Sin equipo"));

				
				
				///ACCIONES
				pageContext.getOut().print(String.format("<td> "
						+ "<a href=\"%s?action=edit&%s=%s\" class=\"btn btn-primary\" >Editar</a> ",
								controllerName, idName, j.getId()));

				
				///MODIFICAR ESTO SEGUN CONVENIENCIA
				String datosObjeto = j.getNombre() +" " +j.getApellidos() ;
				String urlDelete = controllerName+"?action=delete&"+idName+"="+j.getId();

				
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
