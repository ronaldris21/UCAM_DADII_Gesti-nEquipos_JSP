package edu.ucam.listeners;


import edu.ucam.dao.DAO;
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
    	Singleton.getInstance().factoryDataSource = new FactoryDataSource(DAOSelector.SERVLET, sce.getServletContext()); 
    	
    	DAO<User> daoUsuarios = Singleton.getInstance().factoryDataSource.getDaoUser();
    	if(daoUsuarios.getAll().size() == 0)
    	{
    		System.out.println("Inicializar USERS por defecto");
	    	daoUsuarios.insert(new User(1,"admin", "admin"));
	    	daoUsuarios.insert(new User(2,"RonaldRis21", "1234"));
	    	daoUsuarios.insert(new User(3,"pablo", "pablo"));
    	}

    	
    	DAO<Club> daoClubs = Singleton.getInstance().factoryDataSource.getDaoClub(); 
    	if(daoClubs.getAll()== null || daoClubs.getAll().size() == 0)
    	{
    		System.out.println("Inicializar CLUBS por defecto");
    		daoClubs.insert(new Club(1,"MADRID","null.png"));
    	}
    	
    	
    	DAO<Jugador> daoJugadores = Singleton.getInstance().factoryDataSource.getDaoJugador();
    	if(daoJugadores.getAll().size() == 0)
    	{
    		System.out.println("Inicializar JUGADOR por defecto");
    		daoJugadores.insert(new Jugador(1, "Messi", "Ronald", 25,1));
    	}
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println(" contextDestroyed ");
         // TODO Auto-generated method stub
    }
	
}
