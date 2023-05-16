package edu.ucam.listeners;


import edu.ucam.dao.DAOSelector;
import edu.ucam.dao.FactoryDataSource;
import edu.ucam.dao.Singleton;
import edu.ucam.dao.session.ClubServletDAO;
import edu.ucam.dao.session.JugadorServletDAO;
import edu.ucam.dao.session.UsersServletDAO;
import edu.ucam.domain.Club;
import edu.ucam.domain.Jugador;
import edu.ucam.domain.User;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
public class InitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("contextInitialized "+sce.getServletContext());
    	
    	System.out.println("CLASE: "+sce.getClass());
    	
    	//INICIAR FACTORY
    	Singleton.getInstance().factoryDataSource = new FactoryDataSource(DAOSelector.SESSION, sce.getServletContext()); 
    	
    	UsersServletDAO daoUsuarios = new UsersServletDAO(sce.getServletContext());
    	daoUsuarios.insert(new User(0,"admin", "admin"));
    	daoUsuarios.insert(new User(0,"RonaldRis21", "1234"));
    	daoUsuarios.insert(new User(0,"pablo", "pablo"));
    	

    	
    	ClubServletDAO daoClubs = new ClubServletDAO(sce.getServletContext());
    	daoClubs.insert(new Club(1,"MADRID","null.png"));
    	
    	
    	JugadorServletDAO daoJugadores = new JugadorServletDAO(sce.getServletContext());
    	daoJugadores.insert(new Jugador(1, "Messi", "Ronald", 25,1));
    	
    	
    	
    	
        // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println(" contextDestroyed ");
         // TODO Auto-generated method stub
    }
	
}
