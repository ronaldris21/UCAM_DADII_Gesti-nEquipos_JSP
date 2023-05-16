package edu.ucam.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import edu.ucam.dao.DAO;
import edu.ucam.dao.Singleton;
import edu.ucam.domain.User;

public class UserMysqlDAO implements DAO<User>{

	@Override
	public ArrayList<User> getAll() {
		System.out.println("getAll Mysql en DAO");
		ArrayList<User>resultado = new ArrayList<User>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con =null;
		try {
			con = Singleton.getInstance().factoryDataSource.getConnection();
			ps = con.prepareStatement("SELECT * FROM USER ORDER BY nombre");
			rs = ps.executeQuery();		
			
			User user = null;
			while(rs.next()){								
				user = new User();
				user.setId(rs.getInt("id"));
				user.setNombre(rs.getString("nombre"));
				user.setContrasena(rs.getString("contrasena"));
				
				resultado.add(user);
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
	public User getById(int id) {
		User user = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con =null;
		try {
			con = Singleton.getInstance().factoryDataSource.getConnection();
			ps = con.prepareStatement("SELECT * FROM USER WHERE id=?");
			ps.setString(1, String.valueOf(id));
			rs = ps.executeQuery();		
			
			
			while(rs.next()){								
				user = new User();
				user.setId(rs.getInt("id"));
				user.setNombre(rs.getString("nombre"));
				user.setContrasena(rs.getString("contrasena"));
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
		return user;
	}

	@Override
	public boolean delete(int id) {
		System.out.println("Borrar user "+id);
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		Connection con =null;
		try {
			con = Singleton.getInstance().factoryDataSource.getConnection();
			ps = con.prepareStatement("DELETE FROM user WHERE id = ?");
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
		System.out.println("Borrado con exito");
		return true;
	}

	//TODO: check this
	@Override
	public boolean update(int id, User user) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con =null;
		try {
			con = Singleton.getInstance().factoryDataSource.getConnection();
			ps = con.prepareStatement("UPDATE user SET id = ?, nombre=?, contrasena=? WHERE id=?;");
			ps.setInt(1, user.getId());
			ps.setString(2, user.getNombre());
			ps.setString(3, user.getContrasena());
			ps.setInt(4, user.getId());
			 ps.execute();
			 System.out.println("Usuario editado");
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
	public boolean insert(User user) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con =null;
		try {
			con = Singleton.getInstance().factoryDataSource.getConnection();
			ps = con.prepareStatement("INSERT INTO user ( nombre, contrasena ) VALUES (?, ?)");
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getContrasena());
			ps.execute();
			System.out.println("Usuario Ingresado");
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
		System.out.println("No se ha insertado el USUARIO");
		return false;
	}

}
