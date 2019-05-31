package src.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import src.jeu.CaseVide;
import src.jeu.Mur;

/**
 * Classe de test pour les Cases
 * @author Loïck
 *
 */
public class TestCases {

	@Test
	/*
	 * Test le constructeur de la classe CaseVide
	 */
	public void testConstructeurCaseVide() {
		CaseVide cv = new CaseVide(10, 10); 
		assertEquals("la case devrait etre en position 10 pour x", 10, cv.getPosX());
		assertEquals("la case devrait etre en position 10 pour y", 10, cv.getPosY());
		
		CaseVide cv2 = new CaseVide(-1, -10); 
		assertEquals("la case devrait etre en position 0 pour x", 0, cv2.getPosX());
		assertEquals("la case devrait etre en position 0 pour y", 0, cv2.getPosY());
	}
	
	@Test
	/*
	 * Test le constructeur de la classe Mur
	 */
	public void testConstructeurMur() {
		Mur m = new Mur(10, 10);
		assertEquals("la case devrait etre en position 10 pour x", 10, m.getPosX());
		assertEquals("la case devrait etre en position 10 pour y", 10, m.getPosY());
		
		Mur m2 = new Mur(-1, -10);
		assertEquals("la case devrait etre en position 0 pour x", 0, m2.getPosX());
		assertEquals("la case devrait etre en position 0 pour y", 0, m2.getPosY());
	}

	
}
