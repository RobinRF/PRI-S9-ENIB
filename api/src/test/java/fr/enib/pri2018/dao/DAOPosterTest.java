package fr.enib.pri2018.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import fr.enib.pri2018.dao.DAOPoster;
import fr.enib.pri2018.model.Poster;
import fr.enib.pri2018.model.Objet;
import fr.enib.pri2018.model.Image;
import fr.enib.pri2018.model.Texte;

/**
Test collection for DAOPoster
*/
public class DAOPosterTest extends DAOTest {

    @Before
	public void initialize() {
		super.initialize();
	}

	@Test
	public void testNotNull() {
		DAOPoster dao = this.factory.getDAOPoster();
		Assert.assertNotNull(dao);
	}

	// Test if two call from getDAOPoster give the same instance
	@Test
	public void testEqualInstances() {
		DAOPoster first = this.factory.getDAOPoster();
		DAOPoster second = this.factory.getDAOPoster();
		Assert.assertEquals(first, second);
	}

}