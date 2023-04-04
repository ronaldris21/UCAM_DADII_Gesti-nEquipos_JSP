package edu.ucam.servlets;

import java.io.IOException;
import java.util.Hashtable;

import edu.ucam.acciones.Accion;
import edu.ucam.acciones.AccionLogin;
import edu.ucam.acciones.AccionLogout;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Control
 */
public class Control extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static void getRequest()
	{

	}

	private Hashtable<String, Accion> acciones;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Control() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
    	super.init();
    	///Crea la primer instanncia
		System.out.println("SERVLET CONTROL INICIADO");

		if (this.acciones == null) {
			this.acciones = new Hashtable<>();
			this.acciones.put("logout", new AccionLogout());
			this.acciones.put("login", new AccionLogin());

		}


    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("CONTROL SERVLET->" +req.getContextPath());
		String nombreAccion = req.getParameter("accion");
		String jsp = "index.jsp";

		try {
			System.out.println("\tACCION: "+nombreAccion);
			Accion accion =  acciones.get(nombreAccion);
			jsp = accion.ejecutar(req, resp);
		} catch (Exception e) {

		}
		System.out.println("\tDISPATCHED TO: "+jsp);
		req.getRequestDispatcher(jsp).forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
