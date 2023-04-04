package edu.ucam.dao.session;

import java.util.ArrayList;
import java.util.Hashtable;

import edu.ucam.dao.DAO;
import edu.ucam.domain.Club;
import jakarta.servlet.http.HttpServletRequest;

public class ClubSessionDAO implements DAO<Club>{
	
	private static int idCounter=1;
	private HttpServletRequest req;
	public static String CLUBS="CLUBS_SESSION";

	public ClubSessionDAO(HttpServletRequest request) {
		this.req = request;
		if(req.getServletContext().getAttribute(CLUBS)==null)
			req.getServletContext().setAttribute(CLUBS, new ArrayList<Club>());
	}

	@Override
	public ArrayList<Club> getAll() {
		return (ArrayList<Club>)req.getServletContext().getAttribute(CLUBS);
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
			req.getServletContext().setAttribute(CLUBS, clubs);
		
		return borrado;
	}

	@Override
	public boolean update(int id, Club objNuevo) {
		
		objNuevo.setId(id);
		
		if(delete(id))
		{
			ArrayList<Club> clubs = getAll();
			clubs.add(objNuevo);
			req.getServletContext().setAttribute(CLUBS, clubs);
			return true;
		}
		return false;
	}

	@Override
	public boolean insert(Club objNuevo) {
		objNuevo.setId(idCounter++);
		
		ArrayList<Club> clubs = getAll();
		clubs.add(objNuevo);
		req.getServletContext().setAttribute(CLUBS, clubs);
		return true;
	}

}
