package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jeu.CaseVide;
import jeu.PotionVie;

/**
 * Classe de test pour les items (objets) du jeu
 * @author Lo�ck
 *
 */
public class TestItems {


	@Test
	/*
	 * Test potion de vie
	 */
	public void testPotionDeVie() {
		PotionVie pv = new PotionVie(10,10);
		assertEquals("la position x de la potion devrait etre 10", 10,pv.getPosX());
		assertEquals("la position y de la potion devrait etre 10", 10,pv.getPosY());
		
		PotionVie pv2 = new PotionVie(-1,-1);
		assertEquals("la position x de la potion devrait etre 0", 0,pv2.getPosX());
		assertEquals("la position y de la potion devrait etre 0", 0,pv2.getPosY());
	}
	
	@Test
	/*
	 * Test ramasse potion
	 */
	public void testRamassePotionDeVie() {
		PotionVie pv = new PotionVie(10,10);
		assertEquals("la position x de la potion devrait etre 10", 10,pv.getPosX());
		assertEquals("la position y de la potion devrait etre 10", 10,pv.getPosY());
		assertEquals("l'objet n'est pas ramasse", false,pv.getRamasse());
		pv.setRamasse();
		assertEquals("l'objet est pas ramasse", true,pv.getRamasse());
		
	}
	
		
	
	
}
