package edu.ucam.dao.session;

import java.util.ArrayList;

import edu.ucam.dao.DAO;
import edu.ucam.domain.Club;
import edu.ucam.domain.Jugador;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class JugadorSessionDAO implements DAO<Jugador>{
	private static int idCounter=1;
	private HttpSession session;
	public static String JUGADORES="JUGADORES_SESSION";

	public JugadorSessionDAO(HttpSession session) {
		this.session = session;
		if(session.getAttribute(JUGADORES)==null)
			session.setAttribute(JUGADORES, new ArrayList<Jugador>());
		
	}

	@Override
	public ArrayList<Jugador> getAll() {
		return (ArrayList<Jugador>)session.getAttribute(JUGADORES);
	}

	@Override
	public Jugador getById(int id) {
		for(Jugador c: getAll())
		{
			if(c.getId() == id)
				return c;
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		
		ArrayList<Jugador> jugadores = getAll();
		
		boolean borrado =  jugadores.removeIf(c-> c.getId()==id);
		if(borrado)
			session.setAttribute(JUGADORES, jugadores);
		
		return borrado;
	}

	@Override
	public boolean update(int id, Jugador objNuevo) {
		
		objNuevo.setId(id);
		
		if(delete(id))
		{
			ArrayList<Jugador> jugadores = getAll();
			jugadores.add(objNuevo);
			session.setAttribute(JUGADORES, jugadores);
			return true;
		}
		return false;
	}

	@Override
	public boolean insert(Jugador objNuevo) {
		objNuevo.setId(idCounter++);
		
		ArrayList<Jugador> jugadores = getAll();
		jugadores.add(objNuevo);
		session.setAttribute(JUGADORES, jugadores);
		return true;
	}

}
