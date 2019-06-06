package tests;

import jeu.Aventurier;
import jeu.Case;
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
        Aventurier a = new Aventurier(10, 0,0, "LOL");
        assertEquals("Le constructeur doit fonctionner normalement (PV)", 10, a.getPv());
        assertEquals("Le constructeur doit fonctionner normalement (NOM)", true, a.getNom().equals("LOL"));

        Aventurier p = new Aventurier(-1, 5,-5, "");
        assertEquals("Le constructeur 2 doit fonctionner normalement (PV)", 1, p.getPv());
        assertEquals("Le constructeur 2 doit fonctionner normalement (NOM)", true, p.getNom().equals("Entite"));
    }

    @Test
    public void testSeDeplacer() {
    	Labyrinthe lab = new Labyrinthe();
        Aventurier a = new Aventurier(10, 3*Case.TAILLE, 3*Case.TAILLE, "LOL");
        a.setLabyrinthe(lab);
        a.seDeplacer('N');
        a.seDeplacer('E');
        a.seDeplacer('S');
        a.seDeplacer('O');

        assertEquals("Se Deplacer (X)", 3*Case.TAILLE, a.getX());
        assertEquals("Se Deplacer (Y)", 3*Case.TAILLE, a.getY());
    }
    
    @Test
    public void testSubirDegat() {
    	Labyrinthe lab = new Labyrinthe();
        Aventurier a = new Aventurier(10, 0,0, "LOL");
        a.subirDegat(5);
       
       assertEquals("Devrait avoir perdu 5 pv", 5, a.getPv());
    }
    
    @Test
    public void testSubirDegatPvNegatif() {
    	Labyrinthe lab = new Labyrinthe();
        Aventurier a = new Aventurier(10, 0,0, "LOL");
       a.subirDegat(15);
       
       assertEquals("Devrait avoir 0 pv", 0, a.getPv());
       assertEquals("Devrait être mort", false, a.getVivant());
    }


}
