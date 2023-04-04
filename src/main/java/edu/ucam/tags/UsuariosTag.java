package edu.ucam.tags;

import java.io.IOException;

import edu.ucam.dao.session.UsersServletDAO;
import edu.ucam.domain.User;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class UsuariosTag extends TagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {

		String controllerName = "Control";
		String idName = "id-user";

		UsersServletDAO dao = new UsersServletDAO(this.pageContext.getServletContext());
		for (User j : dao.getAll() ) {
			try {
				pageContext.getOut().print("<tr>");

				pageContext.getOut().print(String.format("<td> %s </td>", j.getNombre()));
				pageContext.getOut().print(String.format("<td> %s </td>", j.getContrasena()));
				
				///ACCIONES
				pageContext.getOut().print(String.format("<td> "
						+ "<a href=\"%s?action=edit&%s=%s\" class=\"btn btn-primary\" >Editar</a> ",
								controllerName, idName, j.getId()));

				
				///MODIFICAR ESTO SEGUN CONVENIENCIA
				String datosObjeto = j.getNombre() ;
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