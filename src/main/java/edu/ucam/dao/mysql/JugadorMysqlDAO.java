package edu.ucam.dao.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import edu.ucam.dao.DAO;
import edu.ucam.dao.Singleton;
import edu.ucam.domain.Jugador;
import edu.ucam.domain.Jugador;

public class JugadorMysqlDAO implements DAO<Jugador> {

	@Override
	public ArrayList<Jugador> getAll() {
		System.out.println("getAll Jugador Mysql en DAO");
		ArrayList<Jugador> resultado = new ArrayList<Jugador>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con =null;
		try {
			con = Singleton.getInstance().factoryDataSource.getConnection();
			ps = con.prepareStatement("SELECT j.*, c.nombre as \"clubName\" FROM jugador as j left JOIN club as c ON c.id = j.club_id ORDER BY j.goles DESC");
			rs = ps.executeQuery();		
			
			Jugador Jugador = null;
			while(rs.next()){								
				Jugador = new Jugador();
				Jugador.setId(rs.getInt("id"));
				Jugador.setNombre(rs.getString("nombre"));
				Jugador.setApellidos(rs.getString("apellidos"));
				Jugador.setGoles(rs.getInt("goles"));
				Jugador.setIdClub(rs.getInt("club_id"));
				Jugador.setClubName(rs.getString("clubName"));
				
				resultado.add(Jugador);
			}
			
		} catch (SQLException | NamingException e) {
			System.out.println("Error: "+ e.toString());
		}  finally{
			try {
				if (rs != null)	rs.close();
				if (ps != null) ps.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public Jugador getById(int id) {
		Jugador Jugador = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con =null;
		try {
			con = Singleton.getInstance().factoryDataSource.getConnection();
			ps = con.prepareStatement("SELECT j.*, c.nombre as \"clubName\" FROM jugador as j left JOIN club as c ON c.id = j.club_id WHERE j.id = ?");
			ps.setString(1, String.valueOf(id));
			rs = ps.executeQuery();		
			
			
			while(rs.next()){								
				Jugador = new Jugador();
				Jugador.setId(rs.getInt("id"));
				Jugador.setNombre(rs.getString("nombre"));
				Jugador.setApellidos(rs.getString("apellidos"));
				Jugador.setGoles(rs.getInt("goles"));
				Jugador.setIdClub(rs.getInt("club_id"));
				Jugador.setClubName(rs.getString("clubName"));
			}
			
		} catch (SQLException | NamingException e) {
			System.out.println("Error: "+ e.toString());
		}  finally{
			try {
				if (rs != null)	rs.close();
				if (ps != null) ps.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Jugador;
	}

	@Override
	public boolean delete(int id) {
		System.out.println("Borrar Jugador "+id);
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		Connection con =null;
		try {
			con = Singleton.getInstance().factoryDataSource.getConnection();
			ps = con.prepareStatement("DELETE FROM Jugador WHERE id = ?");
			ps.setInt(1, id);
			ps.execute();		
			
			
		} catch (SQLException | NamingException e) {
			System.out.println("Error: "+ e.toString());
			return false;
		}  finally{
			try {
				if (rs != null)	rs.close();
				if (ps != null) ps.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}	
		System.out.println("Borrado Jugador con exito");
		return true;
	}

	//TODO: check this
	@Override
	public boolean update(int id, Jugador Jugador) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con =null;
		try {
			con = Singleton.getInstance().factoryDataSource.getConnection();
			ps = con.prepareStatement("UPDATE Jugador SET nombre = ?, apellidos=?, goles = ?, club_id =? WHERE id=?;");
			ps.setString(1, Jugador.getNombre());
			ps.setString(2, Jugador.getApellidos());
			ps.setInt(3, Jugador.getGoles());
			if(Jugador.getIdClub()!=0) {
				ps.setInt(4, Jugador.getIdClub());
			}
			else {
				ps.setNull(4, java.sql.Types.INTEGER);
			}
			ps.setInt(5, Jugador.getId());
			 ps.execute();
			 System.out.println("Jugador editado");
			 return true;
		} catch (SQLException | NamingException e) {
			System.out.println("Error: "+ e.toString());
		}  finally{
			try {
				if (rs != null)	rs.close();
				if (ps != null) ps.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean insert(Jugador Jugador) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con =null;
		try {
			con = Singleton.getInstance().factoryDataSource.getConnection();
			ps = con.prepareStatement("INSERT INTO Jugador ( nombre, apellidos, goles, club_id ) VALUES (?,?,?,?)");
			ps.setString(1, Jugador.getNombre());
			ps.setString(2, Jugador.getApellidos());
			ps.setInt(3, Jugador.getGoles());
			if(Jugador.getIdClub()!=0) {
				ps.setInt(4, Jugador.getIdClub());
			}
			else {
				ps.setNull(4, java.sql.Types.INTEGER);
			}
			ps.execute();
			System.out.println("Jugador Ingresado");
			 return true;
		} catch (SQLException | NamingException e) {
			System.out.println("Error: "+ e.toString());
		}  finally{
			try {
				if (rs != null)	rs.close();
				if (ps != null) ps.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				System.out.println("Error: "+ e.toString());
			}
		}
		System.out.println("No se ha insertado el Jugador");
		return false;
	}

}
