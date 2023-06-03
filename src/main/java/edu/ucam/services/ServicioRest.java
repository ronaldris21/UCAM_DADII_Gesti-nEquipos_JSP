package edu.ucam.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

import org.json.JSONObject;

import edu.ucam.dao.DAO;
import edu.ucam.dao.Singleton;
import edu.ucam.domain.Club;
import edu.ucam.domain.Jugador;
import edu.ucam.domain.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path ("/servicio")
public class ServicioRest {
	DAO<Club> dao = Singleton.getInstance().factoryDataSource.getDaoClub();
	
	static private Hashtable<String, Club> clubes = new Hashtable<String, Club>();
	static private Hashtable<String, Jugador> jugadores = new Hashtable<String, Jugador>();
	@GET
	@Path("/Club")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		
		System.out.println("Ejecutando getAll");
		JSONObject jsonRespuesta = new JSONObject();

		DAO<Club> dao = Singleton.getInstance().factoryDataSource.getDaoClub();
		
		for (Club club : dao.getAll()) {
			
			JSONObject jsonClub = new JSONObject();
			
			jsonClub.put("id", club.getId());
			jsonClub.put("nombre", club.getNombre());
			
			
			jsonRespuesta.append("clubes", jsonClub);
		}
		
		
		return Response.status(200).entity(jsonRespuesta.toString()).build();
	}
	
	@GET
	@Path("/Club/{ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClubId(@PathParam("ID") int ID) {
		
		System.out.println("Ejecutando byID ");
		JSONObject jsonRespuesta = new JSONObject();

		DAO<Club> dao = Singleton.getInstance().factoryDataSource.getDaoClub();
		Club c = dao.getById(ID);
		if(c!=null)
		{
			JSONObject jsonClub = new JSONObject();
			jsonClub.put("id", c.getId());
			jsonClub.put("nombre", c.getNombre());
			jsonRespuesta.append("club", jsonClub);
			return Response.status(200).entity(jsonClub.toString()).build();
		}
		return Response.status(404).build();
	}
	
	@POST
	@Path("/Club")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postClub(InputStream incomingData) {
		
		System.out.println("Ejecutando postClub");
		//Leer datos recibidos
		StringBuilder sb = new StringBuilder();
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//Crear el jsonRecibido, donde recibimos el club ID, Nombre
		JSONObject jsonRecibido = new JSONObject(sb.toString());
		
		//Creo objeto club, con los datos que he recibido
		Club club = new Club(jsonRecibido.getInt("id"), jsonRecibido.getString("nombre"));
		
		//Crear el json respuesta
		JSONObject jsonRespuesta = new JSONObject();
		DAO<Club> dao = Singleton.getInstance().factoryDataSource.getDaoClub();
		
		
		if(dao.insert(club))
		{
			return Response.status(200).build();
		}
		
		return null;
		
	}
	
	@DELETE
	@Path("/Club/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteClub(@PathParam("id") int idClub) {

	    System.out.println("Ejecutando deleteClub");
	    System.out.println("ID del club a eliminar: " + idClub);
	    System.out.println("ID del club a eliminar: ");
	    JSONObject jsonRespuesta = new JSONObject();

	    DAO<Club> dao = Singleton.getInstance().factoryDataSource.getDaoClub();
	    if (dao.delete(idClub)) {
	        jsonRespuesta.append("resultado", "Borrado");
	        return Response.ok().entity(jsonRespuesta.toString()).build();
	    }
	    // Si no existe el club con ese ID, devolver respuesta con estado 404 (Recurso no encontrado)
	    return Response.status(404).entity(jsonRespuesta.toString()).build();
	}
	
	@PUT
	@Path("/Club")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putClubs(InputStream incomingData) {
		
		System.out.println("Ejecutando putClub");
		//Leer datos recibidos
		StringBuilder sb = new StringBuilder();
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//Crear el jsonRecibido, donde recibimos el club ID, Nombre, imagen
		JSONObject jsonRecibido = new JSONObject(sb.toString());
		
		//Creo objeto club, con los datos que he recibido
		Club club = new Club(jsonRecibido.getInt("id"), jsonRecibido.getString("nombre"));
		
		//Crear el json respuesta
		JSONObject jsonRespuesta = new JSONObject();
		
		DAO<Club> dao = Singleton.getInstance().factoryDataSource.getDaoClub();
		//Modificar el club al hashtable
		if(dao.update(club.getId(), club))
		{
			return Response.status(200).entity(jsonRespuesta.toString()).build();
		}
		//Si no existe un imagen con ese ID, devuelvo null
		return null;
		
	}
	
	@GET
	@Path("/Jugador")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllJugador() {
		
		System.out.println("Ejecutando getAll");
		JSONObject jsonRespuesta = new JSONObject();
		DAO<Jugador> dao = Singleton.getInstance().factoryDataSource.getDaoJugador();
		for (Jugador jugador : dao.getAll()) {
			
			JSONObject jsonJugador = new JSONObject();
			
			jsonJugador.put("id", jugador.getId());
			jsonJugador.put("nombre", jugador.getNombre());
			jsonJugador.put("apellidos", jugador.getApellidos());
			jsonJugador.put("numeroGoles", jugador.getGoles());
			jsonJugador.put("clubId", jugador.getIdClub());
			jsonRespuesta.append("jugadores", jsonJugador);
		}
		
		return Response.status(200).entity(jsonRespuesta.toString()).build();
	}
	@GET
	@Path("/Jugador/{ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJugadorId(@PathParam("ID") int ID) {
		
		System.out.println("Ejecutando byID ");
		JSONObject jsonRespuesta = new JSONObject();

		DAO<Jugador> dao = Singleton.getInstance().factoryDataSource.getDaoJugador();
		Jugador jugador = dao.getById(ID);
		JSONObject jsonJugador = new JSONObject();
		if(jugador!=null)
		{
			jsonJugador.put("id", jugador.getId());
			jsonJugador.put("nombre", jugador.getNombre());
			jsonJugador.put("apellidos", jugador.getApellidos());
			jsonJugador.put("numeroGoles", jugador.getGoles());
			jsonJugador.put("clubId", jugador.getIdClub());
			jsonRespuesta.append("jugador", jsonJugador);
			return Response.status(200).entity(jsonJugador.toString()).build();
		}
		return Response.status(404).build();
	}
	
	@POST
	@Path("/Jugador")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postJugador(InputStream incomingData) {
		
		System.out.println("Ejecutando postJugador");
		//Leer datos recibidos
		StringBuilder sb = new StringBuilder();
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//Crear el jsonRecibido, donde recibimos el club ID, Nombre
		JSONObject jsonRecibido = new JSONObject(sb.toString());
		
		//Creo objeto club, con los datos que he recibido
		Jugador jugador = new Jugador(jsonRecibido.getInt("id"), jsonRecibido.getString("nombre"),jsonRecibido.getString("apellidos"),jsonRecibido.getInt("numeroGoles"));
		try {
			//clubId puede ser null, si es nul, se queda con un jugador sin club
			jugador = new Jugador(jsonRecibido.getInt("id"), jsonRecibido.getString("nombre"),jsonRecibido.getString("apellidos"),jsonRecibido.getInt("numeroGoles"), jsonRecibido.getInt("clubId") );
		} catch (Exception e) {
		}
		
		//Crear el json respuesta
		JSONObject jsonRespuesta = new JSONObject();
		DAO<Jugador> dao = Singleton.getInstance().factoryDataSource.getDaoJugador();
		if(dao.insert(jugador))
		{
			return Response.status(200).build();
		}
		//Si ya existe un club con ese ID, devuelvo null
		return null;
		
	}
	
	@DELETE
	@Path("/Jugador/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteJugador(@PathParam("id") int idJugador) {

	    System.out.println("Ejecutando deleteJugador");
	    System.out.println("ID del jugador a eliminar: " + idJugador);
	 
	    JSONObject jsonRespuesta = new JSONObject();

	    DAO<Jugador> dao = Singleton.getInstance().factoryDataSource.getDaoJugador();
	    if (dao.delete(idJugador)) {
	        jsonRespuesta.append("resultado", "Borrado");
	        return Response.ok().entity(jsonRespuesta.toString()).build();
	    }

	    // Si no existe el club con ese ID, devolver respuesta con estado 404 (Recurso no encontrado)
	    return Response.status(404).entity(jsonRespuesta.toString()).build();
	}
	
	@PUT
	@Path("/Jugador")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putJugador(InputStream incomingData) {
		
		System.out.println("Ejecutando putJugador");
		//Leer datos recibidos
		StringBuilder sb = new StringBuilder();
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
		JSONObject jsonRecibido = new JSONObject(sb.toString());
		
		//Creo objeto club, con los datos que he recibido
		Jugador jugador = new Jugador(jsonRecibido.getInt("id"), jsonRecibido.getString("nombre"),jsonRecibido.getString("apellidos"),jsonRecibido.getInt("numeroGoles"));
		try {
			//clubId puede ser null, si es nul, se queda con un jugador sin club
			jugador = new Jugador(jsonRecibido.getInt("id"), jsonRecibido.getString("nombre"),jsonRecibido.getString("apellidos"),jsonRecibido.getInt("numeroGoles"), jsonRecibido.getInt("clubId") );
		} catch (Exception e) {
			return null;
		}
		//Crear el json respuesta
		JSONObject jsonRespuesta = new JSONObject();
		DAO<Jugador> dao = Singleton.getInstance().factoryDataSource.getDaoJugador();
		if(dao.update(jugador.getId(), jugador))
		{
			return Response.status(200).build();
		}
		
		//Si no existe un imagen con ese ID, devuelvo null
		return null;
		
	}
}

