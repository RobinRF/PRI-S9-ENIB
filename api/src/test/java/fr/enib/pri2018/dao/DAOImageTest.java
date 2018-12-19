package fr.enib.pri2018.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import fr.enib.pri2018.dao.DAOImage;
import fr.enib.pri2018.model.Image;

/**
Test collection for DAOImage
*/
public class DAOImageTest extends DAOTest {

    @Before
	public void initialize() {
		super.initialize();
	}

	@Test
	public void testNotNull() {
		DAOImage dao = this.factory.getDAOImage();
		Assert.assertNotNull(dao);
	}

	// Test if two call from getDAOStation give the same instance
	@Test
	public void testEqualInstances() {
		DAOImage first = this.factory.getDAOImage();
		DAOImage second = this.factory.getDAOImage();
		Assert.assertEquals(first, second);
	}

}