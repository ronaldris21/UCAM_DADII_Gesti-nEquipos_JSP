package edu.ucam.domain;

import java.io.Serializable;
/**
 * <p>
 * Esta es la clase en la que se crean los usuarios
 * </p>
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String nombre;
	private String contrasena;



    public User(int id, String nombre, String contrasena) {
    	this.id = id;
		this.nombre = nombre;
		this.contrasena = contrasena;
	}
	public User() {

	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
