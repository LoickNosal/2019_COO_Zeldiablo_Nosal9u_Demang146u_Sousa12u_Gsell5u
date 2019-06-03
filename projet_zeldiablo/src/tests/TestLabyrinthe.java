package tests;

import static org.junit.Assert.*;

import jeu.Labyrinthe;
import org.junit.Before;
import org.junit.Test;

public class TestLabyrinthe {

    public String modeleDefaut =    "xxxxxxxxxx\n" +
                                    "x--------x\n" +
                                    "x--------x\n" +
                                    "x--------x\n" +
                                    "x--------x\n" +
                                    "x--------x\n" +
                                    "x--------x\n" +
                                    "x--------x\n" +
                                    "x--------x\n" +
                                    "xxxxxxxxxx\n";

    @Before
    public void initTest() {
        //l = new Labyrinthe(10, 8);
    }

    /**
     * test du constructeur defaut
     */
    @Test
    public void testConstructeurDefaut() {
        Labyrinthe l = new Labyrinthe();
        assertEquals(modeleDefaut, l.toString());
    }

    /**
     * test construit un labyrinthe custom
     */
    @Test
    public void testConstructeurCustom() {
        String modele = "xxxxx\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "xxxxx\n";
        Labyrinthe l = new Labyrinthe(modele);
        assertEquals(modele, l.toString());
    }

    /**
     * test construit un labyrinthe pas assez large
     */
    @Test
    public void testConstructeurLargeurMin() {
        String modele = "xxxx\n" +
                        "x--x\n" +
                        "x--x\n" +
                        "x--x\n" +
                        "xxxx\n";
        Labyrinthe l = new Labyrinthe(modele);
        assertEquals(modeleDefaut, l.toString());
    }

    /**
     * test construit un labyrinthe trop large
     */
    @Test
    public void testConstructeurLargeurMax() {
        String modele = "xxxxxxxxxxxxxxxx\n" +
                        "x--------------x\n" +
                        "x--------------x\n" +
                        "x--------------x\n" +
                        "xxxxxxxxxxxxxxxx\n";
        Labyrinthe l = new Labyrinthe(modele);
        assertEquals(modeleDefaut, l.toString());
    }

    /**
     * test construit un labyrinthe pas assez haut
     */
    @Test
    public void testConstructeurHauteureurMin() {
        String modele = "xxxxx\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "xxxxx\n";
        Labyrinthe l = new Labyrinthe(modele);
        assertEquals(modeleDefaut, l.toString());
    }

    /**
     * test construit un labyrinthe trop haut
     */
    @Test
    public void testConstructeurHauteurMax() {
        String modele = "xxxxx\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "x---x\n" +
                        "xxxxx\n";
        Labyrinthe l = new Labyrinthe(modele);
        assertEquals(modeleDefaut, l.toString());
    }

}
