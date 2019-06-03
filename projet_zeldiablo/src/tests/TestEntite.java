package tests;

import jeu.Aventurier;
import jeu.Labyrinthe;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test pour les Entites
 * @author Paul Gsell
 */
public class TestEntite {

    @Test
    public void testConstructeurAventurier() {
    	Labyrinthe lab = new Labyrinthe();
        Aventurier a = new Aventurier(10, 0,0, "LOL",lab);
        assertEquals("Le constructeur doit fonctionner normalement (PV)", 10, a.getPv());
        assertEquals("Le constructeur doit fonctionner normalement (NOM)", true, a.getNom().equals("LOL"));

        Aventurier p = new Aventurier(-1, 5,-5, "",lab);
        assertEquals("Le constructeur 2 doit fonctionner normalement (PV)", 1, p.getPv());
        assertEquals("Le constructeur 2 doit fonctionner normalement (NOM)", true, p.getNom().equals("Entite"));
    }

    @Test
    public void testSeDeplacer() {
    	Labyrinthe lab = new Labyrinthe();
        Aventurier a = new Aventurier(10, 0,0, "LOL",lab);
        a.seDeplacer('N');
        a.seDeplacer('E');
        a.seDeplacer('S');
        a.seDeplacer('O');

        assertEquals("Se Deplacer (X)", 0, a.getX());
        assertEquals("Se Deplacer (Y)", 0, a.getY());
    }


}
