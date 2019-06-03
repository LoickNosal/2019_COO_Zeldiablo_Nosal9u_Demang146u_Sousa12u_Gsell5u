package tests;

import jeu.Aventurier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Classe de test pour les Entites
 * @author Paul Gsell
 */
public class TestEntite {

    @Test
    public void testConstructeurAventurier() {
        Aventurier a = new Aventurier(10, 0,0, "LOL");
    }

    @Test
    public void testSeDeplacer() {
        fail();
    }


}
