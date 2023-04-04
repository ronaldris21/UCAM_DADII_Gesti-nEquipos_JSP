package edu.ucam.acciones;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AccionLogout extends Accion{
	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("EMAIL_LOGIN");
		System.out.println("Atributo removido");
		return "login.html";
	}
	
}
