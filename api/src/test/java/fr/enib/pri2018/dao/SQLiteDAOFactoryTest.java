package fr.enib.pri2018.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
Test collection for SQLiteDAOFactory
*/
public class SQLiteDAOFactoryTest {

	// Test database file name
	private String databaseFileUri;

	@Before
	public void initialize() {
		this.databaseFileUri = "test.db";
	}

	@Test
	public void testInstantiate() {
		SQLiteDAOFactory factory = new SQLiteDAOFactory(this.databaseFileUri);
		Assert.assertNotNull(factory);
	}

	@Test
	public void testConnect() {
		SQLiteDAOFactory factory = new SQLiteDAOFactory(this.databaseFileUri);
		factory.connect();
		Assert.assertNotNull(factory.getConnection());
	}
}