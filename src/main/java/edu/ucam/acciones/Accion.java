package edu.ucam.acciones;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public abstract class Accion {
	
	public abstract String ejecutar(HttpServletRequest request, HttpServletResponse response);

}
