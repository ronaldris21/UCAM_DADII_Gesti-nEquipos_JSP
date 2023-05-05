package edu.ucam.dao;

import edu.ucam.dao.mysql.*;
import edu.ucam.dao.session.*;
import edu.ucam.domain.*;
import jakarta.servlet.ServletContext;

public class FactoryDataSource {
	
	DAOSelector daoSelector = DAOSelector.SESSION;
	
	DAO<User> daoUser;
	DAO<Jugador> daoJugador;
	DAO<Club> daoClub;
	
	
	public FactoryDataSource(DAOSelector daoSelector, ServletContext context) {
		setDaoSelector(daoSelector, context);
	}
	
	


	public DAOSelector getDaoSelector() {
		return daoSelector;
	}
	

	public void setDaoSelector(DAOSelector daoSelector, ServletContext context) {
		this.daoSelector = daoSelector;
		switch (daoSelector) {
			case MYSQL:
				daoJugador = new  JugadorMysqlDAO();
				daoUser = new  UserMysqlDAO();
				daoClub = new  ClubMysqlDAO();
				
			break;
		case SESSION:
			daoJugador = new  JugadorServletDAO(context);
			daoUser = new  UsersServletDAO(context);
			daoClub = new  ClubServletDAO(context);
			
			break;
	
		
		default:
			break;
		
		}
	}



	public DAO<User> getDaoUser() {
		return daoUser;
	}


	public DAO<Jugador> getDaoJugador() {
		return daoJugador;
	}


	public DAO<Club> getDaoClub() {
		return daoClub;
	}


	
}
