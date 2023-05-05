package edu.ucam.dao.session;

import java.util.ArrayList;

import edu.ucam.dao.DAO;
import edu.ucam.domain.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UsersServletDAO implements DAO<User> {

	private static int idCounter=1;
	private ServletContext context;
	public static String USERS = "USERS_SESSION";
	
	public UsersServletDAO(HttpServletRequest request) {
		this.context = request.getServletContext();
		if(context.getAttribute(USERS)==null)
			context.setAttribute(USERS, new ArrayList<User>());
	}

	
	public UsersServletDAO(ServletContext context) {
		this.context = context;
		if(context.getAttribute(USERS)==null)
			context.setAttribute(USERS, new ArrayList<User>());
	}

	
	
	@Override
	public ArrayList<User> getAll() {
		return (ArrayList<User> )context.getAttribute(USERS);
	}

	@Override
	public User getById(int id) {
		for(User u : getAll())
		{
			if(u.getId() == id)
				return u;
		}
		
		return null;
	}
	

	@Override
	public boolean delete(int id) {
		ArrayList<User> users = getAll();

		boolean borrado =  users.removeIf(c-> c.getId()==id);
		if(borrado)
			context.setAttribute(USERS, users);
		
		return borrado;
	}

	@Override
	public boolean update(int id, User objNuevo) {
		objNuevo.setId(id);

		if(delete(id))
		{
			ArrayList<User> users = getAll();
			users.add(objNuevo);
			context.setAttribute(USERS, users);
			return true;
		}
		return false;
	}

	@Override
	public boolean insert(User objNuevo) {
		
		if(existsUsername(objNuevo.getNombre()))
		{
			return false;
		}
		
		objNuevo.setId(idCounter++);

		ArrayList<User> users = getAll();
		users.add(objNuevo);
		context.setAttribute(USERS, users);
		return true;

	}
	
	public boolean existsUsername(String username)
	{
		for(User u : getAll())
		{
			if(u.getNombre().equals(username))
				return true;
		}
		
		return false;
	}

}
