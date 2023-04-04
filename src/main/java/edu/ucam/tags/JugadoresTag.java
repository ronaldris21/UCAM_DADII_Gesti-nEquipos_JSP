package edu.ucam.tags;

import java.io.IOException;

import edu.ucam.dao.session.JugadorSessionDAO;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;
import edu.ucam.domain.Jugador;

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
				pageContext.getOut().print(String.format("<td> %s </td>", j.getGoles()));
				pageContext.getOut().print(String.format("<td> <a href=\"%s?action=edit&%s=%s\" class=\"btn btn-primary\" >Editar</a>  <a href=\"%s?action=delete&%s=%s\" class=\"btn btn-danger\" >Borrar</a> </td>", 
																controllerName, idName, j.getId(),
																controllerName, idName, j.getId()));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return SKIP_BODY;
		
	}
	

}
