package fr.enib.pri2018.dao;

import java.sql.Connection;
import fr.enib.pri2018.dao.DAOFactory;
import fr.enib.pri2018.dao.DAOConfigurationException;

/**
DatabaseDAOFactory
*/
public abstract class DatabaseDAOFactory extends DAOFactory {

	/** Driver fully qualified class name*/
	private String driverClassName;

	/** Database connection url */
	private String databaseConnectionUrl;

	/**
	Constructor
	*/
	public DatabaseDAOFactory() {
		super();
	}

	/**
	@return The driver fully qualified class name
	*/
	protected String getDriverClassName() {
		return this.driverClassName;
	}

	/**
	@param driverClassName The driver fully qualified class name
	*/
	protected void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	} 

	/**
	@return Database connection url
	*/
	protected String getDatabaseConnectionUrl() {
		return this.databaseConnectionUrl;
	}

	/**
	@param driverClassName Database connection url
	*/
	protected void setDatabaseConnectionUrl(String databaseConnectionUrl) {
		this.databaseConnectionUrl = databaseConnectionUrl;
	}

	/**
	Throw DAOException if driver is not found
	*/
	public void loadDriver() throws DAOConfigurationException {
		try {
			Class.forName(this.driverClassName);
		} catch (ClassNotFoundException e) {
			throw new DAOConfigurationException("Driver class name not found in classpath", e);
		}
	}

	/**
	Connect to the database
	@return The database connection 
	*/
	public abstract Connection getConnection();


}