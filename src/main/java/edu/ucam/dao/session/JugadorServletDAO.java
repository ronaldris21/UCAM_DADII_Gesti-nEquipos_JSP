package edu.ucam.dao.session;

import java.util.ArrayList;

import edu.ucam.dao.DAO;
import edu.ucam.domain.Jugador;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

public class JugadorServletDAO implements DAO<Jugador>{
	private static int idCounter=1;
	private ServletContext context;
	public static String JUGADORES="JUGADORES_SESSION";

	public JugadorServletDAO(HttpServletRequest request) {
		this.context = request.getServletContext();
		if(context.getAttribute(JUGADORES)==null)
			context.setAttribute(JUGADORES, new ArrayList<Jugador>());

	}


	public JugadorServletDAO(ServletContext context) {
		this.context = context;
		if(context.getAttribute(JUGADORES)==null)
			context.setAttribute(JUGADORES, new ArrayList<Jugador>());

	}
	@Override
	public ArrayList<Jugador> getAll() {
		return (ArrayList<Jugador>)context.getAttribute(JUGADORES);
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
			context.setAttribute(JUGADORES, jugadores);

		return borrado;
	}

	@Override
	public boolean update(int id, Jugador objNuevo) {

		objNuevo.setId(id);

		if(delete(id))
		{
			ArrayList<Jugador> jugadores = getAll();
			jugadores.add(objNuevo);
			context.setAttribute(JUGADORES, jugadores);
			return true;
		}
		return false;
	}

	@Override
	public boolean insert(Jugador objNuevo) {
		objNuevo.setId(idCounter++);

		ArrayList<Jugador> jugadores = getAll();
		jugadores.add(objNuevo);
		context.setAttribute(JUGADORES, jugadores);
		return true;
	}

}
