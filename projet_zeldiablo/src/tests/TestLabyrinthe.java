package tests;

import static org.junit.Assert.*;

import jeu.Labyrinthe;
import org.junit.Before;
import org.junit.Test;

public class TestLabyrinthe {

    public Labyrinthe l;

    @Before
    public void initTest() {
        l = new Labyrinthe(10, 8);
    }

    /**
     * test du constructeur normal
     */
    @Test
    public void testConstructeur() {
        assertEquals(10, l.getLargeur());
        assertEquals(8, l.getHauteur());
    }

    /**
     * test construit un labyrinthe pas assez large
     */
    @Test
    public void testConstructeurLargeurMin() {
        Labyrinthe l = new Labyrinthe(4, 10);
        assertEquals(5, l.getLargeur());
    }

    /**
     * test construit un labyrinthe trop large
     */
    @Test
    public void testConstructeurLargeurMax() {
        Labyrinthe l = new Labyrinthe(16, 10);
        assertEquals(15, l.getLargeur());
    }

    /**
     * test construit un labyrinthe pas assez haut
     */
    @Test
    public void testConstructeurHauteureurMin() {
        Labyrinthe l = new Labyrinthe(10, 4);
        assertEquals(5, l.getHauteur());
    }

    /**
     * test construit un labyrinthe trop haut
     */
    @Test
    public void testConstructeurHauteurMax() {
        Labyrinthe l = new Labyrinthe(10, 16);
        assertEquals(15, l.getHauteur());
    }
}
