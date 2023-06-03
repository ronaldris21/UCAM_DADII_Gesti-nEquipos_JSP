package edu.ucam.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ucam.dao.mysql.*;
import edu.ucam.dao.session.*;
import edu.ucam.domain.*;
import jakarta.servlet.ServletContext;

public class FactoryDataSource {
	
	DAOSelector daoSelector = DAOSelector.SERVLET;
	
	DAO<User> daoUser;
	DAO<Jugador> daoJugador;
	DAO<Club> daoClub;
	
	
	public FactoryDataSource(DAOSelector daoSelector, ServletContext context) {
		setDaoSelector(daoSelector, context);
		context.setAttribute("DAOSELECTOR", daoSelector.toString());
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
			case SERVLET:
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
	
	
	public Connection getConnection() throws NamingException, SQLException {
		if(this.daoSelector != daoSelector.MYSQL)
			return null;
		
		System.out.println("MySQLFactory");
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/dad2");
		
		System.out.println("MySQLFactory EXITOSA");
		return ds.getConnection();
	}


	
}
