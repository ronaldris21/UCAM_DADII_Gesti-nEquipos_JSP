package edu.ucam.domain;

import java.io.Serializable;
/**
 * <p> 
 * Esta es la clase en la que se crean los usuarios
 * </p>
 */
public class User implements Serializable{
    public User(String nombre, String contrasena) {
		super();
		this.nombre = nombre;
		this.contrasena = contrasena;
	}



	private static final long serialVersionUID = 1L;
    private String nombre;
    private String contrasena;
	
    
    
	public User() {
		
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getContrasena() {
		return contrasena;
	}



	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	

}
