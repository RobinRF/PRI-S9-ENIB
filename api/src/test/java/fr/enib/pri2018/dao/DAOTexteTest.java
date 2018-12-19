package fr.enib.pri2018.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import fr.enib.pri2018.dao.DAOTexte;
import fr.enib.pri2018.model.Texte;

/**
Test collection for DAOTexte
*/
public class DAOTexteTest extends DAOTest {

    @Before
	public void initialize() {
		super.initialize();
	}

	@Test
	public void testNotNull() {
		DAOTexte dao = this.factory.getDAOTexte();
		Assert.assertNotNull(dao);
	}

	// Test if two call from getDAOTexte give the same instance
	@Test
	public void testEqualInstances() {
		DAOTexte first = this.factory.getDAOTexte();
		DAOTexte second = this.factory.getDAOTexte();
		Assert.assertEquals(first, second);
	}
}