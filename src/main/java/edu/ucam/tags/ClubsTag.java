package edu.ucam.tags;
import java.io.IOException;

import edu.ucam.dao.session.ClubServletDAO;
import edu.ucam.domain.Club;
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
		
		ClubServletDAO dao = new ClubServletDAO(this.pageContext.getServletContext());
		for (Club c : dao.getAll() ) {
			try {
				pageContext.getOut().print("<tr>");
				
				pageContext.getOut().print(String.format("<td> %s </td>", c.getNombre()));
				pageContext.getOut().print(String.format("<td> %s </td>", c.getImg()));
				


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
