package edu.ucam.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import edu.ucam.dao.DAO;
import edu.ucam.dao.Singleton;
import edu.ucam.domain.Club;

public class ClubMysqlDAO implements DAO<Club> {

	@Override
	public ArrayList<Club> getAll() {
		System.out.println("getAll CLUB Mysql en DAO");
		ArrayList<Club> resultado = new ArrayList<Club>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con =null;
		try {
			con = Singleton.getInstance().factoryDataSource.getConnection();
			ps = con.prepareStatement("SELECT * FROM CLUB ORDER BY nombre");
			rs = ps.executeQuery();		
			
			Club club = null;
			while(rs.next()){								
				club = new Club();
				club.setId(rs.getInt("id"));
				club.setNombre(rs.getString("nombre"));
				
				resultado.add(club);
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
	public Club getById(int id) {
		Club club = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con =null;
		try {
			con = Singleton.getInstance().factoryDataSource.getConnection();
			ps = con.prepareStatement("SELECT * FROM club WHERE id=?");
			ps.setString(1, String.valueOf(id));
			rs = ps.executeQuery();		
			
			
			while(rs.next()){								
				club = new Club();
				club.setId(rs.getInt("id"));
				club.setNombre(rs.getString("nombre"));
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
		return club;
	}

	@Override
	public boolean delete(int id) {
		System.out.println("Borrar club "+id);
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		Connection con =null;
		try {
			con = Singleton.getInstance().factoryDataSource.getConnection();
			ps = con.prepareStatement("DELETE FROM club WHERE id = ?");
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
		System.out.println("Borrado club con exito");
		return true;
	}

	//TODO: check this
	@Override
	public boolean update(int id, Club club) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con =null;
		try {
			con = Singleton.getInstance().factoryDataSource.getConnection();
			ps = con.prepareStatement("UPDATE club SET id = ?, nombre=? WHERE id=?;");
			ps.setInt(1, club.getId());
			ps.setString(2, club.getNombre());
			ps.setInt(3, club.getId());
			 ps.execute();
			 System.out.println("CLUB editado");
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
	public boolean insert(Club club) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con =null;
		try {
			con = Singleton.getInstance().factoryDataSource.getConnection();
			ps = con.prepareStatement("INSERT INTO club ( nombre ) VALUES (?)");
			ps.setString(1, club.getNombre());
			ps.execute();
			System.out.println("Club Ingresado");
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
		System.out.println("No se ha insertado el CLUB");
		return false;
	}

}
