package tests;

import jeu.Labyrinthe;
import jeu.MonstreImmobile;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test pour les Monstres (ajout de test dans les test pour Entite
 * @author Alexandre Sousa
 */
public class TestMonstre {
	@Test
    public void testConstructeurMonstre() {
    	Labyrinthe lab = new Labyrinthe();
        MonstreImmobile m = new MonstreImmobile(10, 5, 5, 3, "Chevalier noir",lab);
        MonstreImmobile m2 = new MonstreImmobile(10, 5, 5, -5, "Chevalier noir",lab);
        MonstreImmobile m3 = new MonstreImmobile(10, -500000, -50, 3, "Chevalier noir",lab);
        
        assertEquals("Le constructeur doit fonctionner normalement (DEGAT)", 3, m.getDegat());
        assertEquals("Le constructeur 2 doit fonctionner normalement (DEGAT)", 0, m2.getDegat());
        assertEquals("Le constructeur 3 doit fonctionner normalement (NULL)", null, m3.getLab());
    }
}