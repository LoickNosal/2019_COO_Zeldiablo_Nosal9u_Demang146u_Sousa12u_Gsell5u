package tests;

import jeu.Aventurier;
import jeu.Labyrinthe;
import jeu.Monstre;
import jeu.MonstreAleatoire;
import jeu.MonstreImmobile;
import jeu.MonstreSuivi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test pour les Monstres (ajout de test dans les test pour Entite)
 * @author Alexandre Sousa
 */
public class TestMonstre {
	@Test
    public void testConstructeurMonstre() {
		
		
    	Labyrinthe lab = new Labyrinthe();
    	Monstre m = new MonstreImmobile(10, 5, 5, 3, 50, "Chevalier noir");
    	Monstre m2 = new MonstreAleatoire( 10, 5, 5, -5, 50, "Chevalier noir");
        
        assertEquals("Le constructeur doit fonctionner normalement (DEGAT)", 3, m.getDegat());
        assertEquals("Le constructeur 2 doit fonctionner normalement (DEGAT)", 0, m2.getDegat());
    }
	
	@Test
    public void testMonstreSuiviComportement() {
		
        Labyrinthe lab = new Labyrinthe();
        Aventurier perso = new Aventurier(50, 200, 100, "Eoloc");
        MonstreSuivi m = new MonstreSuivi(10, 300, 100, 5, 50, "Blob");
        perso.setLabyrinthe(lab);
        m.setLabyrinthe(lab);
        //m.comportement(perso);
        
        assertEquals("Le MonstreSuivi aurait du se diriger a gauche vers le personnage ", 297, m.getX());
        assertEquals("Le MonstreSuivi n'aurait pas du se diriger en haut ou en bas vers le personnage", 100, m.getY());
    }
	
	@Test
    public void testMonstreImmobile() {
		
        Labyrinthe lab = new Labyrinthe();
        Aventurier perso = new Aventurier(50, 200, 100, "Eoloc");
        MonstreImmobile m = new MonstreImmobile(10, 300, 100, 5, 50, "Blob");
        perso.setLabyrinthe(lab);
        m.setLabyrinthe(lab);
        //m.comportement(perso);
        
        assertEquals("Le MonstreSuivi n'aurait pas du se diriger a gauche ou a droite", 300, m.getX());
        assertEquals("Le MonstreSuivi n'aurait pas du se diriger en haut ou en bas", 100, m.getY());
    }
	
	@Test
    public void testMonstreAleatoire() {
		
        Labyrinthe lab = new Labyrinthe();
        Aventurier perso = new Aventurier(50, 200, 100, "Eoloc");
        MonstreImmobile m = new MonstreImmobile(10, 300, 100, 5, 50, "Blob");
        perso.setLabyrinthe(lab);
        m.setLabyrinthe(lab);
        //m.comportement(perso);
        
        assertEquals("Le MonstreSuivi n'aurait pas du se diriger a gauche ou a droite", 300, m.getX());
        assertEquals("Le MonstreSuivi n'aurait pas du se diriger en haut ou en bas", 100, m.getY());
    }
}