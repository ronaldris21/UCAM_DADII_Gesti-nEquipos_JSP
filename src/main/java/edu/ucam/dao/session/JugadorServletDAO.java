package edu.ucam.dao.session;

import java.util.ArrayList;

import edu.ucam.dao.DAO;
import edu.ucam.dao.Singleton;
import edu.ucam.dao.session.*;
import edu.ucam.domain.Club;
import edu.ucam.domain.Jugador;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
		ArrayList<Jugador> players = (ArrayList<Jugador>)context.getAttribute(JUGADORES);
		
		for (Jugador player : players) {
			if(player.getIdClub()!=0)
			{
				Club playerClub = Singleton.getInstance().factoryDataSource.getDaoClub().getById(player.getIdClub());
				player.setClubName(playerClub==null ? "" : playerClub.getNombre());
			}
			else
			{
				player.setClubName("");
			}
		}
		
		return players;
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
		boolean updated = false;
		objNuevo.setId(id);

		////TIENE QUE HACERSE SOBRE EL MISMO OBJETO
		ArrayList<Jugador> jugadores = getAll();
		for(Jugador j: jugadores)
		{
			if(j.getId() == id)
			{
				j.setNombre(objNuevo.getNombre());
				j.setApellidos(objNuevo.getApellidos());
				j.setGoles(objNuevo.getGoles());
				j.setIdClub(objNuevo.getIdClub());
				updated=true;
				break;
			}
		}
		
		context.setAttribute(JUGADORES, jugadores);
		return updated;
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
