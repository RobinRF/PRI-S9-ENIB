package fr.enib.pri2018.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import fr.enib.pri2018.dao.DAOStation;
import fr.enib.pri2018.dao.DAOAccroche;
import fr.enib.pri2018.model.Station;
import fr.enib.pri2018.model.Accroche;

/**
Test collection for DAOStaion
*/
public class DAOStationTest extends DAOTest {

    @Before
	public void initialize() {
		super.initialize();
	}

	@Test
	public void testNotNull() {
		DAOStation dao = this.factory.getDAOStation();
		Assert.assertNotNull(dao);
	}

	// Test if two call from getDAOStation give the same instance
	@Test
	public void testEqualInstances() {
		DAOStation first = this.factory.getDAOStation();
		DAOStation second = this.factory.getDAOStation();
		Assert.assertEquals(first, second);
	}

}