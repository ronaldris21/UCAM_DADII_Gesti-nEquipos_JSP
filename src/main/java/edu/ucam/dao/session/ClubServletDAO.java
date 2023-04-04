package edu.ucam.dao.session;

import java.util.ArrayList;

import edu.ucam.dao.DAO;
import edu.ucam.domain.Club;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

public class ClubServletDAO implements DAO<Club>{

	private static int idCounter=1;
	private ServletContext context;
	public static String CLUBS="CLUBS_SESSION";

	public ClubServletDAO(HttpServletRequest req) {
		this.context = req.getServletContext();
		if(context.getAttribute(CLUBS)==null)
			context.setAttribute(CLUBS, new ArrayList<Club>());
	}
	
	public ClubServletDAO(ServletContext context) {
		this.context = context;
		if(context.getAttribute(CLUBS)==null)
			context.setAttribute(CLUBS, new ArrayList<Club>());
	}
	

	@Override
	public ArrayList<Club> getAll() {
		return (ArrayList<Club>)context.getAttribute(CLUBS);
	}

	@Override
	public Club getById(int id) {
		for(Club c: getAll())
		{
			if(c.getId() == id)
				return c;
		}
		return null;
	}

	@Override
	public boolean delete(int id) {

		ArrayList<Club> clubs = getAll();

		boolean borrado =  clubs.removeIf(c-> c.getId()==id);
		if(borrado)
			context.setAttribute(CLUBS, clubs);
		
		return borrado;
	}

	@Override
	public boolean update(int id, Club objNuevo) {

		objNuevo.setId(id);

		if(delete(id))
		{
			ArrayList<Club> clubs = getAll();
			clubs.add(objNuevo);
			context.setAttribute(CLUBS, clubs);
			return true;
		}
		return false;
	}

	@Override
	public boolean insert(Club objNuevo) {
		objNuevo.setId(idCounter++);

		ArrayList<Club> clubs = getAll();
		clubs.add(objNuevo);
		context.setAttribute(CLUBS, clubs);
		return true;
	}

}