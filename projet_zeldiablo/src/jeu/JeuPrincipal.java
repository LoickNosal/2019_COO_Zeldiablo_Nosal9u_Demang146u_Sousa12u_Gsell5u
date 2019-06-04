package jeu;

import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 * modelise le jeu principal
 */
public class JeuPrincipal {

    public Labyrinthe labyrinthe;
    public Aventurier aventurier;
    public static final int level = 1;


    public JeuPrincipal() {
        chargerLVL(2);
        this.aventurier = new Aventurier(10, 100, 100, "Aventurier", this.labyrinthe);
    }


    private void chargerLVL(int lvl) {
        try {
            JSONParser parser = new JSONParser();
            URL url = getClass().getClassLoader().getResource("./");
            String ressourcesPath = url.getPath();
            Object obj;
            JSONObject json;

            switch (lvl) {
                case 1:
                    obj = parser.parse(new FileReader(ressourcesPath + "lvl1.json"));
                    json = (JSONObject) obj;
                    this.labyrinthe = chargerLabyrinthe(json);
                    break;
                case 2:
                    obj = parser.parse(new FileReader(ressourcesPath + "lvl2.json"));
                    json = (JSONObject) obj;
                    this.labyrinthe = chargerLabyrinthe(json);
                    break;
                default:
                    this.labyrinthe = new Labyrinthe();
            }

        } catch (ParseException | IOException e) {
            this.labyrinthe = new Labyrinthe();
        }
    }


    private Labyrinthe chargerLabyrinthe(JSONObject json) {
        JSONArray jsonArray = (JSONArray) json.get("labyrinthe");
        String[] modeleLabyrinthe = new String[jsonArray.size()];

        for (int i = 0; i < modeleLabyrinthe.length; i++) {
            modeleLabyrinthe[i] = (String) jsonArray.get(i);
        }

        return new Labyrinthe(modeleLabyrinthe);
    }

}
