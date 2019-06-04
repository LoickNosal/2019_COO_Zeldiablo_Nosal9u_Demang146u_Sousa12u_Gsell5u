package tests;

import jeu.Labyrinthe;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestLabyrinthe {

    private String stringModeleDefaut;

    @Before
    public void initTest() {
        stringModeleDefaut =
                "XXXXXXXXXXXXXXX\n" +
                "X-------------X\n" +
                "X-------------X\n" +
                "X-------------X\n" +
                "X-------------X\n" +
                "X-------------X\n" +
                "X-------------X\n" +
                "X-------------X\n" +
                "X-------------X\n" +
                "X-------------X\n" +
                "X-------------X\n" +
                "X-------------X\n" +
                "X-------------X\n" +
                "X-------------X\n" +
                "XXXXXXXXXXXXXXX\n";
        String[] modeleDefaut =
                {
                        "X X X X X X X X X X X X X X X",
                        "X - - - - - - - - - - - - - X",
                        "X - - - - - - - - - - - - - X",
                        "X - - - - - - - - - - - - - X",
                        "X - - - - - - - - - - - - - X",
                        "X - - - - - - - - - - - - - X",
                        "X - - - - - - - - - - - - - X",
                        "X - - - - - - - - - - - - - X",
                        "X - - - - - - - - - - - - - X",
                        "X - - - - - - - - - - - - - X",
                        "X - - - - - - - - - - - - - X",
                        "X - - - - - - - - - - - - - X",
                        "X - - - - - - - - - - - - - X",
                        "X - - - - - - - - - - - - - X",
                        "X X X X X X X X X X X X X X X"
                };
    }


    /**
     * test du constructeur defaut
     */
    @Test
    public void testConstructeurDefaut() {
        Labyrinthe l = new Labyrinthe();
        assertEquals(stringModeleDefaut, l.toString());
    }


    /**
     * test construit un labyrinthe custom
     */
    @Test
    public void testConstructeurCustom() {
        String[] modele =
                {
                        "X X X X X X X",
                        "X - - - - - X",
                        "X - - - - - X",
                        "X - - - - - X",
                        "X X X X X X X"
                };
        String res =
                "XXXXXXX\n" +
                "X-----X\n" +
                "X-----X\n" +
                "X-----X\n" +
                "XXXXXXX\n";
        Labyrinthe l = new Labyrinthe(modele);
        assertEquals(7, l.getLargeur());
        assertEquals(5, l.getHauteur());
        assertEquals(res, l.toString());
    }


    /**
     * test construit un labyrinthe pas assez large
     */
    @Test
    public void testConstructeurLargeurMin() {
        String[] modele =
                {
                        "X X X X",
                        "X - - X",
                        "X - - X",
                        "X - - X",
                        "X X X X"
                };
        Labyrinthe l = new Labyrinthe(modele);
        assertEquals(stringModeleDefaut, l.toString());
    }


    /**
     * test construit un labyrinthe trop large
     */
    @Test
    public void testConstructeurLargeurMax() {
        String[] modele =
                {
                        "X X X X X X X X X X X X X X X X",
                        "X - - - - - - - - - - - - - - X",
                        "X - - - - - - - - - - - - - - X",
                        "X - - - - - - - - - - - - - - X",
                        "X X X X X X X X X X X X X X X X"
                };
        Labyrinthe l = new Labyrinthe(modele);
        assertEquals(stringModeleDefaut, l.toString());
    }


    /**
     * test construit un labyrinthe pas assez haut
     */
    @Test
    public void testConstructeurHauteureurMin() {
        String[] modele =
                {
                        "X X X X X",
                        "X - - - X",
                        "X - - - X",
                        "X X X X X"
                };
        Labyrinthe l = new Labyrinthe(modele);
        assertEquals(stringModeleDefaut, l.toString());
    }


    /**
     * test construit un labyrinthe trop haut
     */
    @Test
    public void testConstructeurHauteurMax() {
        String[] modele =
                {
                        "X X X X X",
                        "X - - - X",
                        "X - - - X",
                        "X - - - X",
                        "X - - - X",
                        "X - - - X",
                        "X - - - X",
                        "X - - - X",
                        "X - - - X",
                        "X - - - X",
                        "X - - - X",
                        "X - - - X",
                        "X - - - X",
                        "X - - - X",
                        "X - - - X",
                        "X X X X X"
                };
        Labyrinthe l = new Labyrinthe(modele);
        assertEquals(stringModeleDefaut, l.toString());
    }


    /**
     * test construit un labyrinthe avec un modele invalide
     */
    @Test
    public void testConstructeurModeleInvalide() {
        String[] modele =
                {
                        "X X X X X",
                        "X - - - X",
                        "X - - - X",
                        "X - - - X",
                        "X X X X X X"
                };
        Labyrinthe l = new Labyrinthe(modele);
        assertEquals(stringModeleDefaut, l.toString());
    }


    @Test
    public void testConstructeurDepuisJSON() {
        boolean reussi = true;
        try {
            JSONParser parser = new JSONParser();
            URL url = getClass().getClassLoader().getResource("lvl1.json");
            String path = url.getPath();
            Object obj = parser.parse(new FileReader(path));
            JSONObject json = (JSONObject) obj;

            String titre = (String) json.get("titre");
            System.out.println(titre);

            JSONArray jsonArray = (JSONArray) json.get("labyrinthe");
            String[] modeleLabyrinthe = new String[jsonArray.size()];

            for (int i = 0; i < modeleLabyrinthe.length; i++) {
                modeleLabyrinthe[i] = (String) jsonArray.get(i);
            }

        } catch (ParseException | IOException e) {
            //e.printStackTrace();
            reussi = false;
        }
        assertTrue(reussi);
    }

}
