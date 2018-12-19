package fr.enib.pri2018.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;

import fr.enib.pri2018.dao.DAOException;

/**
Interface DAO
*/
public abstract class DAO<T> {

	/** Database connection */
	public Connection connection;

	/**
	Get object from id
	*/
	public abstract T find(long id) throws DAOException ;

	/**
	Get the list of all objetc of type T
	*/
	public abstract List<T> findAll() throws DAOException ;

	/**
	Add the object T to the database
	*/
	public abstract void add(T object, DAOFactory factory) throws DAOException ;

	/** 
	Map a resultSet with an object
	*/
	protected abstract T map(ResultSet resultSet) throws DAOException ;


}