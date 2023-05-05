package edu.ucam.acciones;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AccionLogin extends Accion{

	@Override
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		String pageFinal = "login.jsp";


		//TODO: Analizar que el usuario exista en un base de datos o contexto!
		String email = request.getParameter("username").toString();
		String password =  request.getParameter("password").toString();

		if(email!=null && password!= null)
			if(email.equals(password))
			{
				pageFinal="index.jsp";
				request.getSession().setAttribute("EMAIL_LOGIN", email);
				System.out.println("\tLOGIN SUCCESSFUL "+request.getSession().getAttribute("EMAIL_LOGIN"));
				///TODO: REDIRECT AFTER LOGIN - Maybe
				/*
				 * When a user tried to do something without being logged in.
				 * Rediredt to login, the to the previous action, if possible??
				 * 1/2 Maybe
				 */
			}


		return pageFinal;


	}

}
