package fr.enib.pri2018.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import fr.enib.pri2018.dao.DAOAccroche;
import fr.enib.pri2018.model.Accroche;
import fr.enib.pri2018.model.Ancre;
import fr.enib.pri2018.model.Poster;

/**
Test collection for DAOAccroche
*/
public class DAOAccrocheTest extends DAOTest {

    @Before
	public void initialize() {
		super.initialize();
	}

	@Test
	public void testNotNull() {
		DAOAccroche dao = this.factory.getDAOAccroche();
		Assert.assertNotNull(dao);
	}

	// Test if two call from getDAOStation give the same instance
	@Test
	public void testEqualInstances() {
		DAOAccroche first = this.factory.getDAOAccroche();
		DAOAccroche second = this.factory.getDAOAccroche();
		Assert.assertEquals(first, second);
	}
}