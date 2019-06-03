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

    @Test
    public void testConstructeur() {
        assertEquals(10, l.getLargeur());
        assertEquals(8, l.getHeuteur());
    }

}
