package fr.enib.pri2018.dao;

/**
Test build up class
*/
public class DAOTest {

    // DAO factory
	protected SQLiteDAOFactory factory;

	public void initialize() {
		// Connect the database
		this.factory = new SQLiteDAOFactory("test.db");
		this.factory.connect();
	}

}