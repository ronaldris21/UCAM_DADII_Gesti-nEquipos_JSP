package edu.ucam.tags;
import java.io.IOException;

import edu.ucam.dao.session.ClubSessionDAO;
import edu.ucam.dao.session.JugadorSessionDAO;
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
		
		ClubSessionDAO dao = new ClubSessionDAO(this.pageContext.getSession());
		for (Club c : dao.getAll() ) {
			try {
				pageContext.getOut().print("<tr>");
				
				pageContext.getOut().print(String.format("<td> %s </td>", c.getNombre()));
				pageContext.getOut().print(String.format("<td> %s </td>", c.getImg()));
				pageContext.getOut().print(String.format("<td> <a href=\"%s?action=edit&%s=%s\" class=\"btn btn-primary\" >Editar</a>  <a href=\"%s?action=delete&%s=%s\" class=\"btn btn-danger\" >Borrar</a> </td>", 
																controllerName, idName, c.getId(),
																controllerName, idName, c.getId()));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return SKIP_BODY;
		
	}
	

}
