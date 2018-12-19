package fr.enib.pri2018.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.enib.pri2018.dao.DatabaseDAOFactory;

/**
SQLiteDAOFactory
*/
public class SQLiteDAOFactory extends DatabaseDAOFactory {

	/** SQLite JDBC driver class name */
	private static final String JDBC_DRIVER = "org.sqlite.JDBC";

	/** Database file uri */
	private String fileUri;

	/** Database connection */
	private Connection connection;

	/** 
	Constructor
	*/
	public SQLiteDAOFactory(String fileUri) throws DAOConfigurationException {
		super();
		this.fileUri = fileUri;
		this.setDriverClassName(SQLiteDAOFactory.JDBC_DRIVER);
		this.loadDriver();
		this.setDatabaseConnectionUrl("jdbc:sqlite:" + fileUri);
	}

	/**
	@return The database file name
	*/
	public String getFileUri() {
		return this.fileUri;
	}

	@Override
	public Connection getConnection() {
		return this.connection;
	}

	/**
	Connect to the database
	*/
	public void connect() throws DAOConfigurationException {
		try {
			this.connection = DriverManager.getConnection(this.getDatabaseConnectionUrl());
		} catch (SQLException e) {
			throw new DAOConfigurationException("Cannot connect the database", e);
		}
		
	}

}