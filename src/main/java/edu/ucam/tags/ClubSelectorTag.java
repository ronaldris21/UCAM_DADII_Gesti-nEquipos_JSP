package edu.ucam.tags;

import java.io.IOException;
import edu.ucam.dao.DAO;
import edu.ucam.dao.Singleton;
import edu.ucam.domain.Club;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class ClubSelectorTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idClubSelected;
	public void setIdClubSelected(int idClubParameter)
	{
		System.out.println("VALOR DEL IDCLUB");
		System.out.println(idClubParameter);
		idClubSelected = idClubParameter;
	}
	
	public void setidClubSelected(int idClubParameter)
	{
		System.out.println("VALOR DEL IDCLUB i");
		System.out.println(idClubParameter);
		idClubSelected = idClubParameter;
	}
	
	
	
	
	@Override
	public int doStartTag() throws JspException {
		DAO<Club> dao = Singleton.getInstance().factoryDataSource.getDaoClub();
		try {
			pageContext.getOut().print("<select id=\"idClub\" name=\"idClub\">");
			pageContext.getOut().print(String.format("<option value=\"%s\" %s>%s</option>", 0, idClubSelected!=0 ?"" : "selected", "Sin equipo"));
			for (Club c : dao.getAll() ) {
				try {
					String selected = c.getId() == idClubSelected ? "selected" : "";
					pageContext.getOut().print(String.format("<option value=\"%s\" %s>%s</option>", c.getId(), selected, c.getNombre()));
	
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			pageContext.getOut().print(" </select>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	

}
