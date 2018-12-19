package fr.enib.pri2018.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import fr.enib.pri2018.dao.DAOAncre;
import fr.enib.pri2018.model.Ancre;

/**
Test collection for DAOAncre
*/
public class DAOAncreTest extends DAOTest {

    @Before
	public void initialize() {
		super.initialize();
	}

	@Test
	public void testNotNull() {
		DAOAncre dao = this.factory.getDAOAncre();
		Assert.assertNotNull(dao);
	}

	// Test if two call from getDAOStation give the same instance
	@Test
	public void testEqualInstances() {
		DAOAncre first = this.factory.getDAOAncre();
		DAOAncre second = this.factory.getDAOAncre();
		Assert.assertEquals(first, second);
	}

}