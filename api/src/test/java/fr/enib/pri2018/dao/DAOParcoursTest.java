package fr.enib.pri2018.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import fr.enib.pri2018.dao.DAOParcours;
import fr.enib.pri2018.dao.DAOStation;
import fr.enib.pri2018.model.Parcours;
import fr.enib.pri2018.model.Station;

/**
Test collection for DAOParcours
*/
public class DAOParcoursTest extends DAOTest {

	@Before
	public void initialize() {
		super.initialize();
	}

	// Test getDAOParcours non null return
	@Test
	public void testNotNull() {
		DAOParcours dao = this.factory.getDAOParcours();
		Assert.assertNotNull(dao);
	}

	// Test if two call from getDAOParcours give the same instance
	@Test
	public void testEqualInstances() {
		DAOParcours first = this.factory.getDAOParcours();
		DAOParcours second = this.factory.getDAOParcours();
		Assert.assertEquals(first, second);
	}

}