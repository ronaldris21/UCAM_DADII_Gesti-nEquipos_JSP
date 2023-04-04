package edu.ucam.tags;

import java.io.IOException;

import edu.ucam.dao.session.JugadorSessionDAO;
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

		JugadorSessionDAO dao = new JugadorSessionDAO(this.pageContext.getSession());
		for (Jugador j : dao.getAll() ) {
			try {
				pageContext.getOut().print("<tr>");

				pageContext.getOut().print(String.format("<td> %s </td>", j.getNombre()));
				pageContext.getOut().print(String.format("<td> %s </td>", j.getApellidos()));
				pageContext.getOut().print(String.format("<td> %s </td>", j.getGoles()));
				pageContext.getOut().print(String.format("<td> "
						+ "<a href=\"%s?action=edit&%s=%s\" class=\"btn btn-primary\" >Editar</a> ",
								controllerName, idName, j.getId()));

				
				///MODIFICAR ESTO SEGUN CONVENIENCIA
				String datosJugador = j.getNombre() + " " +j.getApellidos();
				String urlDelete = "Jugadores?action=delete&id-jugador="+j.getId();

				
				pageContext.getOut().print(String.format("<a href=\"#\" class=\"btn btn-danger\" data-toggle=\"modal\" data-target=\"#confirmDeleteModal\" data-datos=\"%s\" data-url-delete=\"%s\">Borrar</a>",
						datosJugador, urlDelete));

				pageContext.getOut().print(" </td>");

				//<a href="#" class="btn btn-danger" data-toggle="modal" data-target="#confirmDeleteModal" data-id="<%=j.getId()%>">Borrar</a>


			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return SKIP_BODY;

	}


}
