package fr.enib.pri2018.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import fr.enib.pri2018.dao.DAOObjet;
import fr.enib.pri2018.model.Objet;

/**
Test collection for DAOObjet
*/
public class DAOObjetTest extends DAOTest {

    @Before
	public void initialize() {
		super.initialize();
	}

	@Test
	public void testNotNull() {
		DAOObjet dao = this.factory.getDAOObjet();
		Assert.assertNotNull(dao);
	}

	// Test if two call from getDAOStation give the same instance
	@Test
	public void testEqualInstances() {
		DAOObjet first = this.factory.getDAOObjet();
		DAOObjet second = this.factory.getDAOObjet();
		Assert.assertEquals(first, second);
	}

}