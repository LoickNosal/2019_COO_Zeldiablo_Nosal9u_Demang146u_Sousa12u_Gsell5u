package tests;

import jeu.Aventurier;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de test pour les Entites
 * @author Paul Gsell
 */
public class TestEntite {

    @Test
    public void testConstructeurAventurier() {
        Aventurier a = new Aventurier(10, 0,0, "LOL");
        assertEquals("Le constructeur doit fonctionner normalement (PV)", 10, a.getPv());
        assertEquals("Le constructeur doit fonctionner normalement (POS X)", 0, a.getX());
        assertEquals("Le constructeur doit fonctionner normalement (POS Y)", 0, a.getY());
        assertEquals("Le constructeur doit fonctionner normalement (NOM)", true, a.getNom().equals("LOL"));

        Aventurier p = new Aventurier(-1, 5,-5, "");
        assertEquals("Le constructeur doit fonctionner normalement (PV)", 1, p.getPv());
        assertEquals("Le constructeur doit fonctionner normalement (POS X)", 5, p.getX());
        assertEquals("Le constructeur doit fonctionner normalement (POS Y)", -5, p.getY());
        assertEquals("Le constructeur doit fonctionner normalement (NOM)", true, p.getNom().equals("Entite"));
    }

    @Test
    public void testSeDeplacer() {
        fail();
    }


}
