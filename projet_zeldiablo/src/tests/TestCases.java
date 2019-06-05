package tests;

import jeu.Aventurier;
import jeu.CasePiege;
import jeu.CaseSortie;
import jeu.CaseVide;
import org.junit.Test;
import jeu.Mur;

import static org.junit.Assert.*;

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
	 * Test methode de la classe CaseVide
	 */
	public void testMethodeCaseVide() {
		CaseVide cv = new CaseVide(10, 10); 
		assertEquals("la case devrait etre en position 10 pour x", 10, cv.getPosX());
		assertEquals("la case devrait etre en position 10 pour y", 10, cv.getPosY());

		assertEquals("le String devrait renvoyer -","-",cv.toString());
		assertEquals("la case peut etre traversee",true,cv.peutTraverser());
		assertEquals("la type devrait etre 0",0,cv.typeCase());
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
	
	@Test
	/*
	 * Test methode de la classe Mur
	 */
	public void testMethodeMur() {
		Mur cv = new Mur(10, 10); 
		assertEquals("la case devrait etre en position 10 pour x", 10, cv.getPosX());
		assertEquals("la case devrait etre en position 10 pour y", 10, cv.getPosY());

		assertEquals("le String devrait renvoyer X","X",cv.toString());
		assertEquals("la case ne peut etre traversee",false,cv.peutTraverser());
		assertEquals("la type devrait etre 1",1,cv.typeCase());
	}
	
	@Test
	/*
	 * Test le constructeur de la classe CaseSortie
	 */
	public void testConstructeurCaseSortie() {
		CaseSortie m = new CaseSortie(10, 10);
		assertEquals("la case devrait etre en position 10 pour x", 10, m.getPosX());
		assertEquals("la case devrait etre en position 10 pour y", 10, m.getPosY());
		
		CaseSortie m2 = new CaseSortie(-1, -10);
		assertEquals("la case devrait etre en position 0 pour x", 0, m2.getPosX());
		assertEquals("la case devrait etre en position 0 pour y", 0, m2.getPosY());
		
	}
	
	@Test
	/*
	 * Test methode de la classe CaseSortie
	 */
	public void testMethodeCaseSortie() {
		CaseSortie cv = new CaseSortie(10, 10); 
		assertEquals("la case devrait etre en position 10 pour x", 10, cv.getPosX());
		assertEquals("la case devrait etre en position 10 pour y", 10, cv.getPosY());

		assertEquals("le String devrait renvoyer p","p",cv.toString());
		assertEquals("la case peut etre traversee",true,cv.peutTraverser());
		assertEquals("la type devrait etre 2",2,cv.typeCase());
	}
	
	@Test
	/*
	 * Test le constructeur de la classe CasePiege
	 */
	public void testConstructeurCasePiege() {
		CasePiege m = new CasePiege(10, 10);
		assertEquals("la case devrait etre en position 10 pour x", 10, m.getPosX());
		assertEquals("la case devrait etre en position 10 pour y", 10, m.getPosY());
		
		CasePiege m2 = new CasePiege(-1, -10);
		assertEquals("la case devrait etre en position 0 pour x", 0, m2.getPosX());
		assertEquals("la case devrait etre en position 0 pour y", 0, m2.getPosY());
		
	}
	
	@Test
	/*
	 * Test methode de la classe CasePiege
	 */
	public void testMethodeCasePiege() {
		CasePiege cv = new CasePiege(10, 10); 
		assertEquals("la case devrait etre en position 10 pour x", 10, cv.getPosX());
		assertEquals("la case devrait etre en position 10 pour y", 10, cv.getPosY());

		assertEquals("le String devrait renvoyer t","t",cv.toString());
		assertEquals("la case peut etre traversee",true,cv.peutTraverser());
		assertEquals("la type devrait etre 3",3,cv.typeCase());
	}
	
	
	

	
}
