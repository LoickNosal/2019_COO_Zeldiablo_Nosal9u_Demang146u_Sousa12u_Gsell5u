package tests;

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
}